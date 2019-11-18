package com.cczu.model.controller;

import java.text.ParseException;
import java.util.ArrayList;
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
import com.cczu.model.entity.BIS_SafetyFacilitiesEntity;
import com.cczu.model.service.BisAqssService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 安全设施controller
 */
@Controller
@RequestMapping("bis/aqss")
public class PageBisAqssController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private BisAqssService bisAqssService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model,HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(request.getParameter("sys")!=null &&"1".equals(request.getParameter("sys")))
			model.addAttribute("sys", "sys");
		model.addAttribute("qyid", request.getParameter("qyid"));
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					return "qyxx/aqss/ajindex";
				else
					return "qyxx/aqss/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "qyxx/aqss/ajindex";
		}	
	}

	/**
	 * 重大危险源监测预警-安全设施（只查看数据）
	 * @param model
	 */
	@RequestMapping(value="viewindex")
	public String viewIndex(Model model,HttpServletRequest request) {
			return "qyxx/aqss/viewindex";
	}
	
	/**
	 * list页面（企业用户显示界面）
	 * @param request
	 */
	@RequiresPermissions("bis:aqss:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		if(be!=null){
			map.put("qyid", be.getID());
		}
		map.put("m1", request.getParameter("bis_aqss_lb"));
		map.put("m9", request.getParameter("bis_aqss_sbmc"));
		map.put("m6", request.getParameter("bis_aqss_dqsj"));
		map.put("dqsj2", request.getParameter("bis_aqss_dqsj2"));
		map.put("m7", request.getParameter("bis_aqss_zt"));
		return bisAqssService.dataGrid(map);
		
	}

	/**
	 * list页面（非企业用户显示界面）
	 * @param request
	 */
	@RequestMapping(value="ajlist")
	@ResponseBody
	public Map<String, Object> getData2(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		String qyid = request.getParameter("qyid");
		if(StringUtils.isNotEmpty(qyid))
			map.put("qyid", qyid);
		map.put("m1", request.getParameter("bis_aqss_lb"));
		map.put("m9", request.getParameter("bis_aqss_sbmc"));
		map.put("m6", request.getParameter("bis_aqss_dqsj"));
		//map.put("dqsj2", request.getParameter("bis_aqss_dqsj2"));
		map.put("m7", request.getParameter("bis_aqss_zt"));
		map.put("qyname", request.getParameter("bis_aqss_qy_name"));
		map.putAll(getAuthorityMap());
		return bisAqssService.dataGrid2(map);
		
	}
	
	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="tablist/{qyid}")
	@ResponseBody
	public Map<String, Object> getDataTab(@PathVariable("qyid") Integer qyid, HttpServletRequest request) {
		
		Map<String, Object> map = getPageMap(request);
		map.put("qyid", qyid);
		return bisAqssService.dataGrid(map);
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("bis:aqss:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "qyxx/aqss/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequiresPermissions("bis:aqss:add")
	@RequestMapping(value = "create" , method = RequestMethod.POST)
	@ResponseBody
	public String create(BIS_SafetyFacilitiesEntity aqss, Model model) {
		String datasuccess="success";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--安全设施信息  【添加操作】");
		//用户类型(1企业，0安监局，9系统管理员)
		if(sessionuser.getUsertype().equals("1")){
			aqss.setID1(sessionuser.getQyid());
		}
		bisAqssService.addInfo(aqss);
		//返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的成员信息
		BIS_SafetyFacilitiesEntity aqss = bisAqssService.findById(id);
		model.addAttribute("aqss", aqss);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "qyxx/aqss/form";
	}
	
	/**
	 * 修改信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(BIS_SafetyFacilitiesEntity aqss, Model model){
		String datasuccess="success";	
		bisAqssService.updateInfo(aqss);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--安全设施信息  【修改操作】");
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除公用工程信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--安全设施信息  【删除操作】");
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			bisAqssService.deleteInfo(Long.parseLong(aids[i]));
		}
		
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		//查询选择的安全备案信息
		BIS_SafetyFacilitiesEntity aqss = bisAqssService.findById(id);		
		model.addAttribute("aqss", aqss);
		//返回页面
		model.addAttribute("action", "view");
		return "qyxx/aqss/view";
	}
	
 
	/**
	 * 显示所有列
	 */
	@RequiresPermissions("bis:aqss:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","bis/aqss/export");
		return "common/formexcel";
	}
	
	/**
	 * 获取设备类别list
	 * @param id,model
	 */
	@RequestMapping(value = "sblblist")
	@ResponseBody
	public String sblbJson() {
		List<Map<String, Object>> sblbList= bisAqssService.findSblbList();
		return JsonMapper.getInstance().toJson(sblbList);
	}
	
	/**
	 * 获取设备名称list
	 * @param id,model
	 */
	@RequestMapping(value = "sbmclist")
	@ResponseBody
	public String sbmcJson(String sblb) {
		List<Map<String, Object>> sbmcList=new ArrayList<Map<String, Object>>();
		sbmcList = bisAqssService.findSbmcList(sblb);
		return JsonMapper.getInstance().toJson(sbmcList);
	}
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("bis:aqss:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("m1", request.getParameter("bis_aqss_lb"));
		map.put("m2", request.getParameter("bis_aqss_sbmc"));
		map.put("m6", request.getParameter("bis_aqss_dqsj"));
		map.put("dqsj2", request.getParameter("bis_aqss_dqsj2"));
		map.put("m7", request.getParameter("bis_aqss_zt"));
		map.put("qyname", request.getParameter("bis_aqss_qy_name"));
		
		map.put("colval", request.getParameter("colval"));	
		map.put("coltext", request.getParameter("coltext"));
		map.putAll(getAuthorityMap());
		bisAqssService.exportExcel(response, map);
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
	@RequiresPermissions("bis:aqss:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			resultmap = bisAqssService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}

	
}
