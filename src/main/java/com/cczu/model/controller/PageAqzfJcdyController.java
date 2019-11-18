package com.cczu.model.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.AQZF_SafetyCheckUnitEntity;
import com.cczu.model.service.IAqzfJcdyService;
import com.cczu.sys.comm.controller.BaseController;

/**
 * 检查单元
 * @author zpc
 * @date 2017/07/26
 */
@Controller
@RequestMapping("aqzf/jcdy")
public class PageAqzfJcdyController extends BaseController{

	@Autowired
	private IAqzfJcdyService AqzfJcdyService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index",method = RequestMethod.GET)
	public String index(Model model) {
		return "aqzf/jcdy/index";
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		return AqzfJcdyService.dataGrid(map);
	}
	
	@RequestMapping(value="jcdylist")
	@ResponseBody
	public List<Map<String, Object>> getJcdylist(Model model) {
		return AqzfJcdyService.getjcdylist();
	}
	
	/**
	 * 添加检查单元页面跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "aqzf/jcdy/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "create" )
	@ResponseBody
	public String create(AQZF_SafetyCheckUnitEntity jcdy, HttpServletRequest request) {
		String datasuccess="success";
		AqzfJcdyService.addInfo(jcdy);
		return datasuccess;
	}
	
	/**
	 * 删除检查单元
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
			AqzfJcdyService.deleteInfo(Long.parseLong(aids[i]));
		}
		
		return datasuccess;
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的成员信息
		AQZF_SafetyCheckUnitEntity jcdy  = AqzfJcdyService.findById(id);
		model.addAttribute("jcdy", jcdy);
		//返回页面
		model.addAttribute("action", "update");
		return "aqzf/jcdy/form";
	}
	
	/**
	 * 修改
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQZF_SafetyCheckUnitEntity jcdy, Model model){
		String datasuccess="success";	
		AqzfJcdyService.updateInfo(jcdy);
		return datasuccess;
	}

}
