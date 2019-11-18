package com.cczu.model.controller;


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

import com.cczu.model.entity.AQZF_SafetyCheckItemEntity;
import com.cczu.model.service.IAqzfJcbkService;
import com.cczu.model.service.IAqzfJcdyService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 检查表库
 * @author zpc
 * @date 2017/07/26
 */
@Controller
@RequestMapping("aqzf/jcbk")
public class PageAqzfJcbkController extends BaseController{

	@Autowired
	private IAqzfJcbkService AqzfJcbkService;
	@Autowired
	private IAqzfJcdyService AqzfJcdyService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "aqzf/jcbk/index";
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("jcdy", request.getParameter("aqzf_jcbk_M1"));
		map.put("jcnr", request.getParameter("aqzf_jcbk_M2"));
		return AqzfJcbkService.dataGrid(map);
	}
	
	/**
	 * 添加检查表库页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("aqzf:jcbk:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "aqzf/jcbk/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequiresPermissions("aqzf:jcbk:add")
	@RequestMapping(value = "create" )
	@ResponseBody
	public String create(AQZF_SafetyCheckItemEntity jcbk, HttpServletRequest request) {
		String datasuccess="success";
		long a = UserUtil.getCurrentShiroUser().getId();
		jcbk.setID1(a);
		AqzfJcbkService.addInfo(jcbk);
		return datasuccess;
	}
	
	/**
	 * 删除检查表库记录
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			AqzfJcbkService.deleteInfo(Long.parseLong(aids[i]));
		}
		
		return datasuccess;
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		AQZF_SafetyCheckItemEntity jcbk  = AqzfJcbkService.findById(id);
		jcbk.setM1(AqzfJcdyService.findById(Long.parseLong(jcbk.getM1())).getM1());
		model.addAttribute("jcbk", jcbk);
		model.addAttribute("action", "update");
		return "aqzf/jcbk/form";
	}
	
	/**
	 * 修改
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQZF_SafetyCheckItemEntity jcbk, Model model){
		String datasuccess="success";	
		long a = UserUtil.getCurrentShiroUser().getId();
		jcbk.setID1(a);
		AqzfJcbkService.updateInfo(jcbk);
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		//查询选择的安全备案信息
		AQZF_SafetyCheckItemEntity jcbk = AqzfJcbkService.findById(id);		
		jcbk.setM1(AqzfJcdyService.findById(Long.parseLong(jcbk.getM1())).getM1());
		model.addAttribute("jcbk", jcbk);
		return "aqzf/jcbk/view";
	}
	
	/**
	 * 显示所有列
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("aqzf:jcbk:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","aqzf/jcbk/export");
		return "common/formexcel";
	}
	
	/**
	 * 导出Excel
	 * 
	 * @param request
	 */
	@RequiresPermissions("aqzf:jcbk:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void getExcel(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.put("jcdy", request.getParameter("aqzf_jcbk_M1"));
		map.put("jcnr", request.getParameter("aqzf_jcbk_M2"));
		AqzfJcbkService.exportExcel(response, map);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
