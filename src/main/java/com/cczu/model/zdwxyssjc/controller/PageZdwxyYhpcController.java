package com.cczu.model.zdwxyssjc.controller;

import com.cczu.sys.comm.controller.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 在线监控预警-重大危险源隐患排查controller
 */
@Controller
@RequestMapping("zdwxy/yhpc")
public class PageZdwxyYhpcController extends BaseController {

	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		model.addAttribute("iszdwxy", "1");
		return "zxjkyj/zdwxy/yhpc/index";
	}
	
}
