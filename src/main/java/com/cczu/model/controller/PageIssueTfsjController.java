package com.cczu.model.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.ISSUE_SjplEntity;
import com.cczu.model.entity.ISSUE_TfsjEntity;
import com.cczu.model.service.IssueSjpjService;
import com.cczu.model.service.IssueTfsjService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 突发事件
 * @author zpc
 * @date 2017/12/23
 */
@Controller
@RequestMapping("issue/tfsj")
public class PageIssueTfsjController extends BaseController{

	@Autowired
	private IssueTfsjService issueTfsjService;
	@Autowired
	private IssueSjpjService issueSjpjService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "issue/tfsj/index";
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("m1", request.getParameter("issue_tfsj_m1"));
		map.put("starttime", request.getParameter("issue_tfsj_fbstarttime"));
		map.put("endtime", request.getParameter("issue_tfsj_fbendtime"));
		map.put("m5", request.getParameter("issue_tfsj_m5"));
		ShiroUser user = UserUtil.getCurrentShiroUser();
		if("0".equals(user.getUsertype())){//安监
			map.put("xzqy",user.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(user.getUserroleflg()!=null&&user.getUserroleflg()!=0)
				map.put("jglx",user.getUserroleflg());
		}
		return issueTfsjService.dataGrid(map);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			issueTfsjService.deleteInfo(Long.parseLong(aids[i]));//删除添加事件
			issueSjpjService.deleteInfoByid1(Long.parseLong(aids[i]));//删除事件评论
		}
		return datasuccess;
	}

	/**
	 * 添加
	 * @param model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "issue/tfsj/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "create" )
	@ResponseBody
	public String create(ISSUE_TfsjEntity tfsj, HttpServletRequest request) {
		String datasuccess="success";
		tfsj.setID1(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));
		tfsj.setM5("1");
		issueTfsjService.addInfo(tfsj);
		return datasuccess;
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		ISSUE_TfsjEntity tfsj = issueTfsjService.findById(id);
		model.addAttribute("tfsj", tfsj);
		model.addAttribute("action", "update");
		return "issue/tfsj/form";
	}
	
	/**
	 * 修改
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(ISSUE_TfsjEntity wfxw, Model model){
		String datasuccess="success";	
		issueTfsjService.updateInfo(wfxw);
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		ISSUE_TfsjEntity tfsj = issueTfsjService.findById(id);		
		model.addAttribute("tfsj", tfsj);
		model.addAttribute("userid", UserUtil.getCurrentUser().getId());
		return "issue/tfsj/view";
	}
	
	/**
	 * 添加结束评论跳转
	 * @param model
	 */
	@RequestMapping(value = "jspl/{id}" , method = RequestMethod.GET)
	public String jspl(@PathVariable("id") Long id1,Model model) {
		model.addAttribute("action", "jspl");
		ISSUE_SjplEntity pl = new ISSUE_SjplEntity();
		pl.setID1(id1);
		pl.setFid((long) 0);
		model.addAttribute("pl", pl);
		return "issue/tfsj/plform";
	}
	
	/**
	 * 添加结束评论
	 * @param request,model
	 */
	@RequestMapping(value = "jspl")
	@ResponseBody
	public String addjspl(ISSUE_SjplEntity jspl, Model model){
		String datasuccess="success";
		//添加结束评论
		jspl.setID2(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));
		issueSjpjService.addInfo(jspl);
		//修改事件状态
		ISSUE_TfsjEntity tfsj = issueTfsjService.findById(jspl.getID1());
		tfsj.setM5("3");
		issueTfsjService.updateInfo(tfsj);
		return datasuccess;
	}
	
	/**
	 * 添加评论跳转
	 * @param model
	 */
	@RequestMapping(value = "zcpl/{id}" , method = RequestMethod.GET)
	public String zcpl(@PathVariable("id") Long id1,Model model) {
		model.addAttribute("action", "zcpl");
		ISSUE_SjplEntity pl = new ISSUE_SjplEntity();
		pl.setID1(id1);
		pl.setFid((long) 0);
		model.addAttribute("pl", pl);
		return "issue/tfsj/plform";
	}
	
	/**
	 * 添加评论
	 * @param request,model
	 */
	@RequestMapping(value = "zcpl")
	@ResponseBody
	public String addzcpl(ISSUE_SjplEntity jspl, Model model){
		String datasuccess="success";
		//添加评论
		jspl.setID2(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));
		issueSjpjService.addInfo(jspl);
		//修改事件状态
		ISSUE_TfsjEntity tfsj = issueTfsjService.findById(jspl.getID1());
		tfsj.setM5("2");
		issueTfsjService.updateInfo(tfsj);
		return datasuccess;
	}
	
	/**
	 * 修改评论页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "updatepl/{id}", method = RequestMethod.GET)
	public String updatepl(@PathVariable("id") Long id, Model model) {
		ISSUE_SjplEntity pl = issueSjpjService.findById(id);
		model.addAttribute("pl", pl);
		model.addAttribute("action", "updatepl");
		return "issue/tfsj/plform";
	}
	
	/**
	 * 修改评论
	 * @param request,model
	 */
	@RequestMapping(value = "updatepl")
	@ResponseBody
	public String updatepl(ISSUE_SjplEntity pl, Model model){
		String datasuccess="success";	
		issueSjpjService.updateInfo(pl);
		return datasuccess;
	}
	
	/**
	 * 删除评论
	 */
	@RequestMapping(value = "deletepl/{id}")
	@ResponseBody
	public String deletepl(@PathVariable("id") Long id) {
		String datasuccess="删除成功";
		issueSjpjService.deleteInfoByid(id);//删除事件评论
		return datasuccess;
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="pllist/{id}")
	@ResponseBody
	public Map<String, Object> getData(@PathVariable("id") Long id1,HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("id1", id1);
		return issueSjpjService.dataGrid(map);
	}
}
