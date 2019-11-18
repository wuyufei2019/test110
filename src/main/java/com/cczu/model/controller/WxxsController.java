package com.cczu.model.controller;

import java.text.ParseException;
import java.util.HashMap;
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

import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.model.entity.WxxsCL;
import com.cczu.model.entity.WxxsLB;
import com.cczu.model.service.impl.ClWxxsService;
import com.cczu.model.service.impl.LbWxxsService;
/**
 * 危险系数 Controller
 * @author jason
 *
 */

@Controller
@RequestMapping("system/wxxs")
public class WxxsController  extends BaseController{
	@Autowired
	private LbWxxsService lbWxxsService;
	@Autowired
	private ClWxxsService clWxxsService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "system/wxxs/index";
	}
	/**
	 * 类别危险系数页面
	 */
	@RequestMapping(value="lb/index")
	public String lbindex(Model model) {
		return "system/wxxs/wllb/index";
	}
	
	/**
	 * 储量危险系数页面
	 */
	@RequestMapping(value="cl/index")
	public String clindex(Model model) {
		return "system/wxxs/wlcl/index";
	}
	
	
	/**
	 * 类别危险系数——list页面
	 */
	@RequiresPermissions("sys:wxxs:view")
	@RequestMapping(value="lb/list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		int pageNo=1;	//当前页码
		int pageSize=20;	//每页行数
		if(StringUtils.isNotEmpty(request.getParameter("page")))
			pageNo=Integer.valueOf(request.getParameter("page"));
		if(StringUtils.isNotEmpty(request.getParameter("rows")))
			pageSize=Integer.valueOf(request.getParameter("rows"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);
		return lbWxxsService.dataGrid(map);
	}
	
	
	/**
	 * 类别危险系数——添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("sys:wxxs:add")
	@RequestMapping(value = "lb/create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
			return "system/wxxs/wllb/form";
		
	}
	
	/**
	 * 类别危险系数——添加
	 * @param request,model
	 * @throws ParseException 
	 */
	@RequiresPermissions("sys:wxxs:add")
	@RequestMapping(value = "lb/create")
	@ResponseBody
	public String create(WxxsLB xs, Model model) throws ParseException {
		String datasuccess="保存成功！";
		lbWxxsService.addInfo(xs);	
		return datasuccess;
	}	
	
	/**
	 * 类别危险系数——修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("sys:wxxs:update")
	@RequestMapping(value = "lb/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		Map<String, Object> map=new HashMap<>();
		map.put("id", id);
		WxxsLB xs=lbWxxsService.findByContent(map);
		model.addAttribute("wxxs",xs);
		model.addAttribute("action", "update");
		return "system/wxxs/wllb/form";
	}
	
	/** 
	 * 类别危险系数——修改 
	 * @param xs
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequiresPermissions("sys:wxxs:update")
	@RequestMapping(value = "lb/update")
	@ResponseBody
	public String update(WxxsLB xs, Model model) throws ParseException{
		String datasuccess="更新成功";
		lbWxxsService.updateInfo(xs);
		return datasuccess;
	}
	
	/**
	 * 类别危险系数——删除
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("sys:wxxs:delete")
	@RequestMapping(value = "lb/delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			lbWxxsService.deleteInfo(Integer.parseInt(arrids[i]));
		}
		return datasuccess;
	}
	
	
	
	
	
	/**
	 * 储量危险系数——list页面
	 */
	@RequiresPermissions("sys:wxxs:view")
	@RequestMapping(value="cl/list")
	@ResponseBody
	public Map<String, Object> getData2(HttpServletRequest request) {
		int pageNo=1;	//当前页码
		int pageSize=20;	//每页行数
		if(StringUtils.isNotEmpty(request.getParameter("page")))
			pageNo=Integer.valueOf(request.getParameter("page"));
		if(StringUtils.isNotEmpty(request.getParameter("rows")))
			pageSize=Integer.valueOf(request.getParameter("rows"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);
		return clWxxsService.dataGrid(map);
	}
	
	
	/**
	 * 储量危险系数——添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("sys:wxxs:add")
	@RequestMapping(value = "cl/create" , method = RequestMethod.GET)
	public String create2(Model model) {
		model.addAttribute("action", "create");
		return "system/wxxs/wlcl/form";
		
	}
	
	/**
	 * 储量危险系数——添加
	 * @param request,model
	 * @throws ParseException 
	 */
	@RequiresPermissions("sys:wxxs:add")
	@RequestMapping(value = "cl/create")
	@ResponseBody
	public String create2(WxxsCL xs, Model model) throws ParseException {
		String datasuccess="保存成功！";
		clWxxsService.addInfo(xs);	
		return datasuccess;
	}	
	
	/**
	 * 储量危险系数——修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("sys:wxxs:update")
	@RequestMapping(value = "cl/update/{id}", method = RequestMethod.GET)
	public String update2(@PathVariable("id") Integer id, Model model) {
		Map<String, Object> map=new HashMap<>();
		map.put("id", id);
		WxxsCL xs=clWxxsService.findByContent(map);
		model.addAttribute("wxxs",xs);
		model.addAttribute("action", "update");
		return "system/wxxs/wlcl/form";
	}
	
	/** 
	 * 储量危险系数——修改 
	 * @param xs
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequiresPermissions("sys:wxxs:update")
	@RequestMapping(value = "cl/update")
	@ResponseBody
	public String update2(WxxsCL xs, Model model) throws ParseException{
		String datasuccess="更新成功";
		clWxxsService.updateInfo(xs);
		return datasuccess;
	}
	
	/**
	 * 储量危险系数——删除
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("sys:wxxs:delete")
	@RequestMapping(value = "cl/delete/{ids}")
	@ResponseBody
	public String delete2(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			clWxxsService.deleteInfo(Integer.parseInt(arrids[i]));
		}
		return datasuccess;
	}
}
