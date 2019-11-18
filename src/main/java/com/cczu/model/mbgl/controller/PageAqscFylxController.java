package com.cczu.model.mbgl.controller;

import java.sql.Timestamp;
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

import com.cczu.model.mbgl.entity.AQSC_ExpenseType;
import com.cczu.model.mbgl.service.AqscFylxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;


/**
 * 安全生产-费用类型Controller
 * @author YZH
 */
@Controller
@RequestMapping("aqsc/fylx")
public class PageAqscFylxController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private AqscFylxService aqscFylxService;
	 
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "targetmanger/aqsctr/fylx/index";
	}
	
	/**
	 * 企业端list 
	 * @param request
	 */
	@RequiresPermissions("aqsc:fylx:view")
	@RequestMapping(value="list")
	@ResponseBody
	public String getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		return aqscFylxService.dataGrid(map);	
		
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("aqsc:fylx:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "targetmanger/aqsctr/fylx/form";
	}
	
	/**
	 * 添加费用类型信息
	 * @param request,model
	 */
	@RequiresPermissions("aqsc:fylx:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(AQSC_ExpenseType entity, Model model) {
		String datasuccess="success";
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		aqscFylxService.addInfo(entity);
		return datasuccess;
		
	}
	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("aqsc:fylx:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的粉尘信息
		AQSC_ExpenseType fylx = aqscFylxService.findById(id);
		
		model.addAttribute("fylx", fylx);
		//返回页面
		model.addAttribute("action", "update");
		return "targetmanger/aqsctr/fylx/form";
	}
	
	/**
	 * 修改费用类型信息
	 * @param request,model
	 */
	@RequiresPermissions("aqsc:fylx:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQSC_ExpenseType entity,  Model model){
		String datasuccess="success";	
		entity.setS1(new java.sql.Timestamp(new java.util.Date().getTime()));
		entity.setS2(new java.sql.Timestamp(new java.util.Date().getTime()));
		entity.setS3(0);
		
		aqscFylxService.updateInfo(entity);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除费用类型信息
	 */
	@RequiresPermissions("aqsc:fylx:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			aqscFylxService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("aqsc:fylx:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		AQSC_ExpenseType fylx = aqscFylxService.findById(id);
		
		model.addAttribute("fylx", fylx);
		//返回页面
		model.addAttribute("action", "view");
		return "targetmanger/aqsctr/fylx/view";
	}
	
	/**
	 * 区域字典显示 {网格id   ： 网格名称}
	 * @param
	 */
	@RequestMapping(value="idjson")
	@ResponseBody
	public String xzqyjson2() {
		return aqscFylxService.jsonListBycode();
	}
}
