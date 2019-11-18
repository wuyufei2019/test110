package com.cczu.model.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cczu.sys.comm.utils.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.BIS_TankEntity;
import com.cczu.model.service.IBisCgxxService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.ITsDeviceChanelService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 储罐信息controller
 * 
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("bis/cgxx")
public class PageBisCgxxController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private IBisCgxxService bisCgxxService;
	@Autowired
	private ITsDeviceChanelService tsDeviceChanelService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					return "qyxx/cgxx/ajindex";
				else
					return "qyxx/cgxx/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "qyxx/cgxx/ajindex";
		}	
	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("bis:cgxx:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		// 获取企业id
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());

		Map<String, Object> map = getPageMap(request);
		if (be != null) {
			map.put("qyid", be.getID());
		}
		map.put("m1", request.getParameter("bis_cgxx_cx_m1"));
		map.put("m6", request.getParameter("M6"));
		map.put("m7", request.getParameter("M7"));
		map.put("m2", request.getParameter("M2"));
		return bisCgxxService.dataGrid(map);
	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequestMapping(value = "tablist/{qyid}")
	@ResponseBody
	public Map<String, Object> getDataTab(@PathVariable("qyid") Integer qyid, HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		map.put("qyid", qyid);
		return bisCgxxService.dataGrid(map);
	}

	/**
	 * list页面(安监局查看所有企业储罐信息)
	 * 
	 * @param request
	 */
	@RequiresPermissions("bis:cgxx:view")
	@RequestMapping(value = "ajlist")
	@ResponseBody
	public Map<String, Object> getAllData(HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("bis_cgxx_cx_qyname"));
		map.put("m1", request.getParameter("bis_cgxx_cx_m1"));
		map.put("m6", request.getParameter("M6"));
		map.put("m7", request.getParameter("M7"));
		map.put("m2", request.getParameter("M2"));
		map.putAll(getAuthorityMap());
		return bisCgxxService.dataGridAJ(map);
	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("bis:cgxx:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		ShiroUser user = UserUtil.getCurrentShiroUser();
		model.addAttribute("action", "create");
		model.addAttribute("usertype",user.getUsertype());
		model.addAttribute("qyid",user.getQyid());
		if ("1".equals(user.getUsertype())) {
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(user.getQyid());
			if (StringUtils.isNotEmpty(be.getM16()) && StringUtils.isNotEmpty(be.getM17())) {
				model.addAttribute("qylng", be.getM16());// 企业经度
				model.addAttribute("qylat", be.getM17());// 企业纬度
			}
		}
		return "qyxx/cgxx/form";

	}

	/**
	 * 添加储罐信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("bis:cgxx:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(BIS_TankEntity bt,HttpServletRequest request, Model model) {
		String datasuccess = "success";
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--储罐信息  【添加操作】");

		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){
			bt.setID1(sessionuser.getQyid());
		}
		
		bt.setJarid(UUID.randomUUID().toString().replaceAll("-", ""));//化学品仓库标识uuid
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		bt.setCompanycode(be.getCompanycode());//企业编码
		bt.setParkid(be.getParkid());//所属园区域标识
		bt.setDistrictcode(be.getID2());//所属行政区划
		Timestamp t=DateUtils.getSysTimestamp();
		bt.setS1(t);
		bt.setS2(t);
		bt.setS3(0);
		bt.setCreator(UserUtil.getCurrentUser().getName());//创建人
		bt.setUpdator(UserUtil.getCurrentUser().getName());//最后修改人
		bisCgxxService.addInfo(bt);
		return datasuccess;

	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id
	 *            ,model
	 */
	@RequiresPermissions("bis:cgxx:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		ShiroUser user = UserUtil.getCurrentShiroUser();
		// 查询选择的储罐信息
		long id1 = id;
		BIS_TankEntity bt = bisCgxxService.findById(id1);
		/*
		Map<String, Object> map = tsDeviceChanelService.findDeviceByID(bt.getID2());
		if (map != null) {
			model.addAttribute("deviceNo", map.get("m1"));
			model.addAttribute("channelNo", map.get("m2"));
		}
		*/
		model.addAttribute("cglist", bt);
		model.addAttribute("action", "update");
		model.addAttribute("usertype",user.getUsertype());
		model.addAttribute("qyid",user.getQyid());

		if ("1".equals(user.getUsertype())) {
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(user.getQyid());
			if (StringUtils.isNotEmpty(be.getM16()) && StringUtils.isNotEmpty(be.getM17())) {
				model.addAttribute("qylng", be.getM16());// 企业经度
				model.addAttribute("qylat", be.getM17());// 企业纬度
			}
		}
		return "qyxx/cgxx/form";
	}

	/**
	 * 修改储罐信息
	 * 
	 * @param request
	 *     
	 */
	@RequiresPermissions("bis:cgxx:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(BIS_TankEntity bt,HttpServletRequest request, Model model) {
		String datasuccess = "success";
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--储罐信息  【修改操作】");

		Timestamp t=DateUtils.getSysTimestamp();
		bt.setS2(t);

//		TS_DeviceChanel dev = tsDeviceChanelService.findDeviceChanelByContent(request.getParameter("deviceNo"), request.getParameter("channelNo"));
//		if (dev != null)
//			bt.setID2(dev.getID());
//		else {
//			bt.setID2(0);
//			datasuccess = "信道绑定失败！设备编号或信道号不存在！";
//		}

		bt.setID3(request.getParameter("ID3"));
		
		BIS_TankEntity bis = bisCgxxService.findById(bt.getID());
		bt.setLevel1(bis.getLevel1());
		bt.setLevel2(bis.getLevel2());
		bt.setTemperature1(bis.getTemperature1());
		bt.setTemperature2(bis.getTemperature2());
		bt.setPressure1(bis.getPressure1());
		bt.setPressure2(bis.getPressure2());
		bt.setUpdator(UserUtil.getCurrentUser().getName());//最后修改人
		bisCgxxService.updateInfo(bt);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除储罐信息
	 * 
	 * @param ids
	 * @throws ParseException
	 */
	@RequiresPermissions("bis:cgxx:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";

		// 可以批量删除
		String[] aids = ids.split(",");
		for (int i = 0; i < aids.length; i++) {
			bisCgxxService.deleteInfo(Long.parseLong(aids[i]));
		}
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--储罐信息  【删除操作】");
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * 
	 * @param id
	 *            ,model
	 */
	@RequiresPermissions("bis:qyjbxx:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		// 查询选择的产品信息

		long id1 = id;
		BIS_TankEntity bs = bisCgxxService.findById2(id1);
		model.addAttribute("cglist", bs);

		// 返回页面
		model.addAttribute("action", "view");
		return "qyxx/cgxx/view";
	}

	/**
	 * 查看页面跳转
	 * 
	 * @param id
	 *            ,model
	 */
	@RequiresPermissions("bis:cgxx:view")
	@RequestMapping(value = "sview/{id}", method = RequestMethod.GET)
	public String sview(@PathVariable("id") Integer id, Model model) {
		// 查询选择的产品信息

		long id1 = id;
		BIS_TankEntity bs = bisCgxxService.findById(id1);
		model.addAttribute("qylist", bs);

		// 返回页面
		model.addAttribute("action", "view");
		return "qyxx/cgxx/view";
	}

	//导入页面跳转
	@RequestMapping(value = "exinjump", method = RequestMethod.GET)
	public String exin(Model model) {
		model.addAttribute("action", "exin");
		return "common/importForm";
	}
	
	/**
	 * 统计页面跳转
	 * @param model
	 */
	@RequestMapping(value = "statistics", method = RequestMethod.GET)
	public String statistics(Model model) {
		Map<String, Object> remap = bisCgxxService.statistics(getAuthorityMap());
		model.addAttribute("data", JsonMapper.getInstance().toJson(remap));
		return "qyxx/cgxx/statistics";
	}
	
	/**
	 * 导入
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequiresPermissions("bis:cgxx:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			resultmap = bisCgxxService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}

	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("bis:cgxx:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyname", request.getParameter("bis_cgxx_cx_qyname"));
		map.put("m1", request.getParameter("bis_cgxx_cx_m1"));
		map.put("m6", request.getParameter("M6"));
		map.put("m7", request.getParameter("M7"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.putAll(getAuthorityMap());
		bisCgxxService.exportExcel(response, map);

	}

	/**
	 * 显示所有列
	 * 
	 * @param model
	 */
	@RequiresPermissions("bis:cgxx:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","bis/cgxx/export");
		return "common/formexcel";
	}

	/**
	 * 
	 * @param model
	 */
	@RequiresPermissions("bis:cgxx:view")
	@RequestMapping(value = "cgmapjson", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>>getCgMapJson(Model model) {
		Map<String, Object> mapdata = getAuthorityMap();
		List<Map<String, Object>> list = bisCgxxService.getCgMapJson(mapdata);
		return list;
	}

	/**
	 * 获取储罐下拉框内容
	 * @param model
	 */
	@RequiresPermissions("bis:cgxx:view")
	@RequestMapping(value = "getjson", method = RequestMethod.POST)
	@ResponseBody
	public String getJson(Model model) {
		Map<String, Object> mapdata = getAuthorityMap();
		return bisCgxxService.getCgxxJson(mapdata);
	}

	/**
	 * 获取储罐下拉框内容
	 * @param model
	 */
	@RequiresPermissions("bis:cgxx:view")
	@RequestMapping(value = "getjson/{qyid}", method = RequestMethod.POST)
	@ResponseBody
	public String getJson(@PathVariable("qyid") Long qyid, Model model) {
		Map<String, Object> mapdata = new HashMap<>();
		mapdata.put("qyid", qyid);
		return bisCgxxService.getCgxxJson(mapdata);
	}
}
