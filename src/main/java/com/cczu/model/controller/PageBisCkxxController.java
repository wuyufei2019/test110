package com.cczu.model.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.BIS_StorageEntity;
import com.cczu.model.service.IBisCkxxService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 仓库信息controller
 * 
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("bis/ckxx")
public class PageBisCkxxController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private IBisCkxxService bisCkxxService;

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
					return "qyxx/ckxx/ajindex";
				else
					return "qyxx/ckxx/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "qyxx/ckxx/ajindex";
		}	
	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("bis:ckxx:view")
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

		map.put("m1", request.getParameter("bis_ckxx_cx_m1"));
		map.put("m4", request.getParameter("M4"));
		map.put("m5", request.getParameter("M5"));
		map.put("m6", request.getParameter("M6"));
		return bisCkxxService.dataGrid(map);
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
		return bisCkxxService.dataGrid(map);
	}

	/**
	 * list页面(安监局查看所有企业仓库信息)
	 * 
	 * @param request
	 */
	@RequiresPermissions("bis:ckxx:view")
	@RequestMapping(value = "ajlist")
	@ResponseBody
	public Map<String, Object> getAllData(HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("bis_ckxx_cx_qyname"));
		map.put("m1", request.getParameter("bis_ckxx_cx_m1"));
		map.put("m4", request.getParameter("M4"));
		map.put("m5", request.getParameter("M5"));
		map.put("m6", request.getParameter("M6"));
		map.putAll(getAuthorityMap());
		return bisCkxxService.dataGridAJ(map);
	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("bis:ckxx:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		ShiroUser user = UserUtil.getCurrentShiroUser();
		model.addAttribute("action", "create");
		model.addAttribute("usertype",user.getUsertype());
		model.addAttribute("qyid",user.getQyid());
		return "qyxx/ckxx/form";
	}

	/**
	 * 添加仓库信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("bis:ckxx:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(BIS_StorageEntity bs, Model model,HttpServletRequest request) {
		String datasuccess = "success";
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			long ID = be.getID();//获取到企业id
			bs.setID1(ID);
		}
		Timestamp t=DateUtils.getSysTimestamp();
		bs.setS1(t);
		bs.setS2(t);
		bs.setS3(0);
		bisCkxxService.addInfo(bs);
		log.info(sessionuser.getLoginName()+":  一企一档--仓库信息  【添加操作】");
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("bis:ckxx:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		ShiroUser user = UserUtil.getCurrentShiroUser();
		// 查询选择的仓库信息
		long id1 = id;
		BIS_StorageEntity bs = bisCkxxService.findById(id1);

		model.addAttribute("cklist", bs);
		model.addAttribute("action", "update");
		model.addAttribute("usertype",user.getUsertype());
		model.addAttribute("qyid",user.getQyid());
		return "qyxx/ckxx/form";
	}

	/**
	 * 修改仓库信息
	 * 
	 * @param bs,model
	 */
	@RequiresPermissions("bis:ckxx:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(BIS_StorageEntity bs, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		bs.setS2(t);
		bisCkxxService.updateInfo(bs);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--仓库信息  【修改操作】");
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除仓库信息
	 * 
	 * @param ids
	 * @throws ParseException
	 */
	@RequiresPermissions("bis:ckxx:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--仓库信息  【删除操作】");
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			bisCkxxService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("bis:qyjbxx:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		
		Map<String, Object> bs = bisCkxxService.findById2(id);
		model.addAttribute("cklist", bs);
		// 返回页面
		return "qyxx/ckxx/view";
	}

	/**
	 * 查看页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("bis:ckxx:view")
	@RequestMapping(value = "sview/{id}", method = RequestMethod.GET)
	public String sview(@PathVariable("id") Integer id, Model model) {
		// 查询选择的产品信息
		long id1 = id;
		BIS_StorageEntity bs = bisCkxxService.findById(id1);

		model.addAttribute("cklist", bs);
		// 返回页面
		model.addAttribute("action", "view");
		return "qyxx/ckxx/view";
	}

	//导入页面跳转
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
	@RequiresPermissions("bis:ckxx:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			resultmap = bisCkxxService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("bis:ckxx:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyname", request.getParameter("bis_ckxx_cx_qyname"));
		map.put("m1", request.getParameter("bis_ckxx_cx_m1"));
		map.put("m4", request.getParameter("M4"));
		map.put("m5", request.getParameter("M5"));
		map.put("m6", request.getParameter("M6"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.putAll(getAuthorityMap());
		bisCkxxService.exportExcel(response, map);

	}

	/**
	 * 显示所有列
	 * 
	 * @param model
	 */
	@RequiresPermissions("bis:ckxx:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","bis/ckxx/export");
		return "/common/formexcel";
	}
	
	/**
	 * 统计页面跳转
	 * @param model
	 */
	@RequestMapping(value = "statistics", method = RequestMethod.GET)
	public String statistics(Model model) {
		Map<String, Object> map = bisCkxxService.statistics(getAuthorityMap());
		model.addAttribute("mapdata", JsonMapper.getInstance().toJson(map));
		return "qyxx/ckxx/statistics";
	}

}
