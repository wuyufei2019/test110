package com.cczu.sys.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 导航引导菜单controller
 * @author jason
 * @date 2017年5月31日
 */
@Controller
@RequestMapping(value = "system/menu")
public class MenuController{

	/**
	 * 默认页面
	 */
	@RequestMapping(value="index",method = RequestMethod.GET)
	public String index(Model model) {
		return "system/menu/index";
	}
	
	/**
	 * app下载页面
	 */
	@RequestMapping(value="appindex",method = RequestMethod.GET)
	public String appindex(Model model) {
		return "system/menu/app/index";
	}
	
	/**
	 * 用户手册下载页面
	 */
	@RequestMapping(value="manualindex",method = RequestMethod.GET)
	public String manualindex(Model model) {
		return "system/menu/yhsc";
	}
}
