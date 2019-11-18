package com.cczu.model.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.ISSUE_SafetyProductionDynamicEntity;
import com.cczu.model.entity.MSG_detailEntity;
import com.cczu.model.service.IIssueAqscdtService;
import com.cczu.model.service.IIssueWjcdjsService;
import com.cczu.model.service.IMsgService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.UserRoleService;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 安全生产动态信息发布controller
 * @author jason
 *
 */
@Controller
@RequestMapping("issue/aqscdt")
public class PageIssueAqscdtController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IIssueAqscdtService issueAqscdtService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private IIssueWjcdjsService issueWjcdjsService;
	@Autowired
	private UserService userService;
	@Autowired
	private IMsgService msgService;

	
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "issue/aqscdt/index";
	}

	
	
	/**
	 * list页面
	 */
	@RequiresPermissions("issue:aqscdt:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		
		Map<String, Object> map = getPageMap(request);
		map.put("wjname", request.getParameter("issue_aqscdt_cx_m1"));
		//发布日期
		map.put("fbdatestart", request.getParameter("check_company_fbstarttime"));
		map.put("fbdateend", request.getParameter("check_company_fbendtime"));
		map.put("sort", request.getParameter("sort"));
		map.put("order", request.getParameter("order"));
		if(sessionuser.getUsertype().equals("0")){//安监用户  添加网格区域编码查询条件
			map.put("xzqy",sessionuser.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
				map.put("jglx",sessionuser.getUserroleflg());
		}
		return issueAqscdtService.dataGrid(map);

	}
	

	
	/**
	 * 添加安全生产动态信息页面跳转
	 * @param model
	 */
	@RequiresPermissions("issue:aqscdt:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "createSub");
		return "issue/aqscdt/form";
	}
	
	
	/**
	 * 添加安全生产动态信息
	 * @param request,model
	 */
	@RequiresPermissions("issue:aqscdt:add")
	@RequestMapping(value = "createSub")
	@ResponseBody
	public String createSub(ISSUE_SafetyProductionDynamicEntity sfr, Model model,HttpServletRequest request)throws IOException {
		String datasuccess="failed";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		long ID =sessionuser.getId(); //当前用户id
//		String paths=UPload(request, model);//文件保存路径

		Timestamp t=DateUtils.getSysTimestamp();
		sfr.setM2(request.getParameter("M2"));
		sfr.setID1(ID);
		sfr.setS1(t);
		sfr.setS2(t);
		sfr.setS3(0);
		if(issueAqscdtService.addInfor(sfr)>0);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("xzqy", sessionuser.getXzqy());
		List<User> list = userService.findByAllUserByContent(map);
		for (User ur : list) {
			//添加消息对象
			MSG_detailEntity msg = new MSG_detailEntity();
			
			msg.setS1(t);
			msg.setS2(t);
			msg.setS3(0);
			msg.setID1(ID);
			msg.setType("4");//安全文件发布
			msg.setInfo("有新动态");
			msg.setContent(sfr.getM1());
			msg.setSengObj((long)ur.getId());
			msg.setStatus("2");//未读
			msg.setReleaseTime(t);
			msgService.addInfo(msg);
		}
			datasuccess="success";
		//返回结果
		return datasuccess;
	}
	/**
	 * 修改安全生产动态信息页面跳转
	 * @param model
	 */
	@RequiresPermissions("issue:aqscdt:update")
	@RequestMapping(value = "update/{id}" )
	public String update(@PathVariable("id") long id,Model model) {

		ISSUE_SafetyProductionDynamicEntity sfr = issueAqscdtService.findInfoById(id);
		model.addAttribute("sfr", sfr);
		model.addAttribute("action", "updateSub");
		return "issue/aqscdt/form";
	}
	
	/**
	 * 修改安全生产动态信息
	 * @param model
	 */
	@RequiresPermissions("issue:aqscdt:update")
	@RequestMapping(value = "updateSub" )
	@ResponseBody
	public String updateSub(ISSUE_SafetyProductionDynamicEntity sfr, Model model,HttpServletRequest request) {
		String datasuccess="failed";
		Timestamp t=DateUtils.getSysTimestamp();
		sfr.setS2(t);
		sfr.setM2(request.getParameter("M2"));
		if(issueAqscdtService.updateInfoByID(sfr.getID(), sfr)>0);
			datasuccess="success";
		return datasuccess;
	}
	
	
	
	
	/**
	 * 查看安全生产动态信息
	 * @param model
	 */
	@RequiresPermissions("issue:aqscdt:view")
	@RequestMapping(value = "view/{id}" )
	public String view(@PathVariable("id") long id,Model model) {

		ISSUE_SafetyProductionDynamicEntity sfr = issueAqscdtService.findInfoById(id);
		model.addAttribute("sfr", sfr);
		model.addAttribute("action", "view");
		return "issue/aqscdt/view";
	}

	

	/**
	 * 删除安全生产动态信息
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("issue:aqscdt:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			issueAqscdtService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 查询安全生产动态信息
	 * @param model
	 */
	@RequiresPermissions("issue:aqscdt:view")
	@RequestMapping(value = "search/{id}" )
	public String search(@PathVariable("id") long id,Model model) {

		ISSUE_SafetyProductionDynamicEntity sfr = issueAqscdtService.findInfoById(id);
		model.addAttribute("sfr", sfr);
		model.addAttribute("action", "view");
		return "issue/aqscdt/form";
	}
	
	
}
