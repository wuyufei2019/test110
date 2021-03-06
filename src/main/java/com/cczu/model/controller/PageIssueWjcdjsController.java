package com.cczu.model.controller;

import java.io.IOException;
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

import com.cczu.model.entity.ISSUE_FileTransmissionReceivingEntity;
import com.cczu.model.service.IIssueWjcdjsService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;


/**
 * 文件传递接收controller
 * @author jason
 *
 */
@Controller
@RequestMapping("issue/wjcdjs")
public class PageIssueWjcdjsController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IIssueWjcdjsService issueWjcdjsService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "issue/wjcdjs/index";
	}
	/**
	 * list页面
	 */
	@RequiresPermissions("issue:wjcdjs:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		
		Map<String, Object> map = getPageMap(request);
		map.put("wjname", request.getParameter("issue_wjcdjs_cx_m1"));
		//发布日期
		map.put("fbdatestart", request.getParameter("check_company_fbstarttime"));
		map.put("fbdateend", request.getParameter("check_company_fbendtime"));
		map.put("m1", request.getParameter("issue_wjcdjs_m1"));
		map.put("m2", request.getParameter("issue_wjcdjs_m2"));
		map.put("m5", request.getParameter("issue_wjcdjs_m5"));
		map.put("sort", request.getParameter("sort"));
		map.put("order", request.getParameter("order"));
		if(sessionuser.getUsertype().equals("0")){//安监用户  添加网格区域编码查询条件
			map.put("xzqy",sessionuser.getXzqy());
			map.put("uid", sessionuser.getId());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
				map.put("jglx",sessionuser.getUserroleflg());
		}
		return issueWjcdjsService.dataGrid(map);

	}
	
	
	/**
	 * 添加回执信息页面跳转
	 * @param model
	 */
	@RequestMapping(value = "create/{id}")
	public String create(@PathVariable("id")long id,Model model) {
		model.addAttribute("fid", id);//文件id
		model.addAttribute("action", "createSub");
		return "issue/wjcdjs/hzform";
	}
	
	/**
	 * 修改回执信息页面跳转
	 * @param model
	 */
	@RequestMapping(value = "update/{id}")
	public String update(@PathVariable("id")long id,Model model) {
		ISSUE_FileTransmissionReceivingEntity ife=issueWjcdjsService.findInfoByIds(UserUtil.getCurrentShiroUser().getId(),id);
		model.addAttribute("action", "updateSub");
		model.addAttribute("ife", ife);
		return "issue/wjcdjs/hzform";
	}

	/**
	 * 添加回执信息
	 * @param request
	 * model
	 */
	@RequestMapping(value = "updateSub", method = RequestMethod.POST)
	@ResponseBody
	public String updateSub(ISSUE_FileTransmissionReceivingEntity e,HttpServletRequest request) throws IOException {
		e.setID2(UserUtil.getCurrentShiroUser().getId());
		// 返回结果
		return issueWjcdjsService.addHzInfor(e);
	}
	/**
	 * 添加回执信息
	 * @param request
	 * model
	 */
	@RequestMapping(value = "createSub", method = RequestMethod.POST)
	@ResponseBody
	public String createSub(ISSUE_FileTransmissionReceivingEntity e,HttpServletRequest request) throws IOException {
		e.setID2(UserUtil.getCurrentShiroUser().getId());
		// 返回结果
		return issueWjcdjsService.addHzInfor(e);
	}
	
	
	/**
	 *查看回执记录
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("issue:wjcdjs:view")
	@RequestMapping(value = "view/{id}")
	public String view(@PathVariable("id") long id,Model model) {
		ISSUE_FileTransmissionReceivingEntity e= issueWjcdjsService.findInfoById(id);
		model.addAttribute("ife", e);
		return "issue/wjcdjs/view";
	}

	/**
	 * 删除文件传递接收记录
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("issue:wjcdjs:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			issueWjcdjsService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("issue:wjcdjs:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		map.put("wjname", request.getParameter("issue_wjcdjs_cx_m1"));
		map.put("fbdatestart", request.getParameter("check_company_fbstarttime"));
		map.put("fbdateend", request.getParameter("check_company_fbendtime"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		if(sessionuser.getUsertype().equals("0")){//安监用户  添加网格区域编码查询条件
			map.put("xzqy",sessionuser.getXzqy());
			map.put("uid", sessionuser.getId());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
				map.put("jglx",sessionuser.getUserroleflg());
		}
		issueWjcdjsService.exportExcel(response, map);
		
	}
	/**
	 * 显示所有列
	 * @param id,model 
	 */
	@RequiresPermissions("issue:wjcdjs:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","issue/wjcdjs/export");
		return "common/formexcel";
	}
	
}
