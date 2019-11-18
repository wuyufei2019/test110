package com.cczu.model.controller;


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

import com.cczu.model.entity.YHPC_GridManKpiContent;
import com.cczu.model.service.YhpcGridManKpiContentService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 *网格化管理-网格员绩效考核规则
 * @author zpc
 * @date 2017/08/19
 */
@Controller
@RequestMapping("yhpc/wgykpi/content")
public class PageYhpcGridManKpiContentController extends BaseController{

	@Autowired
	private YhpcGridManKpiContentService yhpcGridManKpiContentService;
	@Autowired
	private RoleService roleService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if("1".equals(sessionuser.getUsertype())){
			return "../error/403";
		}else{
			return "yhpc/wgykpi/content/index";
		}
	}
	
	/**
	 * list页面
	 */
	@RequiresPermissions("yhpc:wgykpicont:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		String code=request.getParameter("wgcode");
		map.put("wgcode",StringUtils.isBlank(code)?UserUtil.getCurrentShiroUser().getXzqy():code);
		return yhpcGridManKpiContentService.dataGrid(map);
	}

	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("yhpc:wgykpicont:add")
	@RequestMapping(value = "create")
	public String create(Model model,HttpServletRequest request) {
		String wgid = request.getParameter("wgid");
		model.addAttribute("wgid", wgid);
		model.addAttribute("action", "createSub");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("wgid", wgid);
		map.put("name", "安全巡查");
		List<YHPC_GridManKpiContent> list=yhpcGridManKpiContentService.findByIdName(map);
		if(list.size()==0){
			YHPC_GridManKpiContent cont= new YHPC_GridManKpiContent();
			cont.setName("安全巡查");
			String content="网格员利用"+request.getParameter("wgname")+"智慧安监平台,每月至少要到本网格内所有生产经营单位巡查一次，及时掌握网格内的安全生产信息"
					+"(如职业卫生情况、用电安全情况、操作程序是否规范、消防安全情况等)，发现问题立即下发整改指令书，要求其限期整改到位，同时向所属网格安监办上报。";
			cont.setContent(content);
			cont.setScore(30);
			model.addAttribute("cont", cont);
		}
		return "yhpc/wgykpi/content/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequiresPermissions("yhpc:wgykpicont:add")
	@RequestMapping(value = "createSub" )
	@ResponseBody
	public String create(YHPC_GridManKpiContent cont, HttpServletRequest request) {
		String datasuccess="failed";
		Timestamp t=DateUtils.getSysTimestamp();
		cont.setS1(t);
		cont.setS2(t);
		cont.setS3(0);
		long id = yhpcGridManKpiContentService.addInfoReturnId(cont);
		if(id>0)
			datasuccess="success";
		return datasuccess;
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("yhpc:wgykpicont:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		YHPC_GridManKpiContent cont = yhpcGridManKpiContentService.findById(id);
		model.addAttribute("cont", cont);
		model.addAttribute("action", "update");
		return "yhpc/wgykpi/content/form";
	}
	
	/**
	 * 修改
	 * @param request,model
	 */
	@RequiresPermissions("yhpc:wgykpicont:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(YHPC_GridManKpiContent cont, HttpServletRequest request, Model model){
		String datasuccess="success";
		cont.setS2(DateUtils.getSysTimestamp());
		yhpcGridManKpiContentService.updateInfo(cont);
		return datasuccess;
	}
	
	/**
	 * 删除
	 * @param user
	 * @param model
	 * 
	 */
	@RequiresPermissions("yhpc:wgykpicont:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			yhpcGridManKpiContentService.deleteInfo(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("yhpc:wgykpicont:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		YHPC_GridManKpiContent cont = yhpcGridManKpiContentService.findById(id);
		model.addAttribute("cont", cont);
		return "yhpc/wgykpi/content/view";
	}
	
}
