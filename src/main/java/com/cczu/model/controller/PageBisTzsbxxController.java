package com.cczu.model.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.BIS_Specequipment_Second;
import com.cczu.model.entity.BIS_SpecialequipmentEntity;
import com.cczu.model.service.BisTzsbSecService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.IBisTzsbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 特种设备信息controller
 * 
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("bis/tzsbxx")
public class PageBisTzsbxxController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private IBisTzsbxxService bisTzsbxxService;
	@Autowired
	private BisTzsbSecService bisTzsbSecService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model,HttpServletRequest request) {
		if(request.getParameter("sys")!=null &&"1".equals(request.getParameter("sys")))
			model.addAttribute("sys", "sys");
		model.addAttribute("qyid", request.getParameter("qyid"));
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					return "qyxx/tzsbxx/adminindex";
				else
					return "qyxx/tzsbxx/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "qyxx/tzsbxx/adminindex";
		}	
	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequestMapping(value = "ajlist")
	@ResponseBody
	public Map<String, Object> ajgetData(HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		String qyid = request.getParameter("qyid");
		if(StringUtils.isNotEmpty(qyid))
			map.put("qyid", qyid);
		map.put("qyname", request.getParameter("qy_name"));
		map.put("m1", request.getParameter("bis_tzsbxx_cx_m1"));
		map.put("m3", request.getParameter("M3"));
		map.put("time1", request.getParameter("time1"));
		map.put("time2", request.getParameter("time2"));
		map.putAll(getAuthorityMap());
		return bisTzsbxxService.ajdataGrid(map);
	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("bis:tzsbxx:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());

		Map<String, Object> map = getPageMap(request);
		if (be != null) {
			map.put("qyid", be.getID());
		}
		map.put("m1", request.getParameter("bis_tzsbxx_cx_m1"));
		map.put("m3", request.getParameter("M3"));
		map.put("time1", request.getParameter("time1"));
		map.put("time2", request.getParameter("time2"));
		return bisTzsbxxService.asd(map);
		// return bisTzsbxxService.dataGrid(map);
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

		return bisTzsbxxService.asd(map);
	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("bis:tzsbxx:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "qyxx/tzsbxx/form";
	}

	/**
	 * 添加特种设备信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("bis:tzsbxx:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(BIS_SpecialequipmentEntity bs, Model model) {
		String datasuccess = "success";
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		if (sessionuser.getUsertype().equals("1")) {
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			long ID = be.getID();// 获取到企业id
			bs.setID1(ID);
		}
		Timestamp t = DateUtils.getSysTimestamp();
		bs.setS1(t);
		bs.setS2(t);
		bs.setS3(0);
		bisTzsbxxService.addInfo(bs);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--特种设备信息  【增加操作】");

		return datasuccess;

	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("bis:tzsbxx:update")
	@RequestMapping(value = "update/{id}")
	public String update(@PathVariable("id") Integer id, Model model) {
		// 查询选择的特种设备信息

		long id1 = id;
		BIS_SpecialequipmentEntity bs = bisTzsbxxService.findById2(id1);
		model.addAttribute("qylist", bs);
		// 返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "qyxx/tzsbxx/form";
	}
	
	/**
	 * 更新检测日期
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("bis:tzsbxx:update")
	@RequestMapping(value = "update2/{id}", method = RequestMethod.GET)
	public String update2(@PathVariable("id") Integer id, Model model) {
		// 查询选择的特种设备信息
		long id1 = id;
		BIS_SpecialequipmentEntity bs = bisTzsbxxService.findById2(id1);
		model.addAttribute("qylist", bs);
		// 返回页面
		model.addAttribute("action", "update2");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "qyxx/tzsbxx/form2";
	}
	
	/**
	 * 更新历史页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("bis:tzsbxx:add")
	@RequestMapping(value = "hisindex/{id}", method = RequestMethod.GET)
	public String showhistory(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("ID1",id);
		return "qyxx/tzsbxx/hisindex";
	}
	
	/**
	 * 更新历史list页面
	 * 
	 * @param request
	 */
	@RequestMapping(value = "hislist")
	@ResponseBody
	public Map<String, Object> getData2(HttpServletRequest request) {

		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());

		Map<String, Object> map = getPageMap(request);
		map.put("qyid", be.getID());
		map.put("ID1", request.getParameter("ID1").toString());
		
		return bisTzsbSecService.dataGrid(map);
		// return bisTzsbxxService.dataGrid(map);
	}

	/**
	 * 修改特种设备信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("bis:tzsbxx:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(BIS_SpecialequipmentEntity bs, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		bs.setS2(t);
		bisTzsbxxService.updateInfo(bs);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--特种设备信息  【修改操作】");


		// 返回结果
		return datasuccess;
	}
	
	/**
	 * 更新特种设备信息检测日期
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("bis:tzsbxx:update")
	@RequestMapping(value = "update2")
	@ResponseBody
	public String update2(BIS_SpecialequipmentEntity bs, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		BIS_SpecialequipmentEntity equip=bisTzsbxxService.findById2(bs.getID());
		equip.setM9(bs.getM9());
		equip.setM10(bs.getM10());
		equip.setS2(t);
		
		bisTzsbxxService.updateInfo(equip);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--特种设备信息  【更新日期操作】");

		BIS_Specequipment_Second equipsec=new BIS_Specequipment_Second();
		equipsec.setID1(equip.getID());//特种设备id
		equipsec.setQyid(equip.getID1());//企业id
		equipsec.setM1(bs.getM9());
		equipsec.setM2(bs.getM10());
		equipsec.setS1(t);
		equipsec.setS2(t);
		equipsec.setS3(0);
		//添加副表信息
		bisTzsbSecService.addInfo(equipsec);

		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除特种设备信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("bis:tzsbxx:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			bisTzsbxxService.deleteInfo(Long.parseLong(arrids[i]));
		}
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--特种设备信息  【删除操作】");

		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("bis:qyjbxx:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		// 查询选择的产品信息
		long id1 = id;
		Map<String, Object> bs = bisTzsbxxService.findById(id1);
		model.addAttribute("qylist", bs);
		// 返回页面
		return "qyxx/tzsbxx/view";
	}

	/**
	 * 查看页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("bis:tzsbxx:view")
	@RequestMapping(value = "sview/{id}", method = RequestMethod.GET)
	public String sview(@PathVariable("id") Integer id, Model model) {
		// 查询选择的产品信息
		long id1 = id;
		Map<String, Object> bs = bisTzsbxxService.findById(id1);
		model.addAttribute("qylist", bs);
		// 返回页面
		model.addAttribute("action", "view");
		return "qyxx/tzsbxx/view";
	}

	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("bis:tzsbxx:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyname", request.getParameter("qy_name"));
		map.put("m1", request.getParameter("bis_tzsbxx_cx_m1"));
		map.put("m3", request.getParameter("M3"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.putAll(getAuthorityMap());
		bisTzsbxxService.exportExcel(response, map);

	}

	/**
	 * 显示所有列
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("bis:tzsbxx:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url", "bis/tzsbxx/export");
		return "common/formexcel";
	}

	@RequestMapping(value = "exinjump", method = RequestMethod.GET)
	public String exin(Model model) {
		model.addAttribute("action", "exin");
		return "common/importForm";
	}
	/**
	 * 导入
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequiresPermissions("bis:tzsbxx:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			resultmap = bisTzsbxxService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		String ss=JsonMapper.toJsonString(resultmap);
		return ss;
	}
	/**
	 * 统计页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "statistics", method = RequestMethod.GET)
	public String statistics(Model model) {
		List<Map<String, Object>>maplist = bisTzsbxxService.statistics(getAuthorityMap());
		model.addAttribute("data", JsonMapper.getInstance().toJson(maplist));
		return "qyxx/tzsbxx/statistics";
	}

}
