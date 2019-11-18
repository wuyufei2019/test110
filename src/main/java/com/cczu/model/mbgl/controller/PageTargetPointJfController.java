package com.cczu.model.mbgl.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.mbgl.entity.Target_Points_jf;
import com.cczu.model.mbgl.service.TargetPointJfService;
import com.cczu.sys.comm.controller.BaseController;

/**
 * 积分设置Controller
 */
@Controller
@RequestMapping("target/jfsz")
public class PageTargetPointJfController extends BaseController{

	@Autowired
	private TargetPointJfService targetPointJfService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index",method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("jf", targetPointJfService.findInfor());
		return "targetmanger/safetyculture/jfsz/index";
	}
	
	/**
	 * 添加或修改操作
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update" , method = RequestMethod.POST)
	@ResponseBody
	public String create(Target_Points_jf jf, Model model) {
		targetPointJfService.updateInfo(jf);
		return "success";
	}
}
