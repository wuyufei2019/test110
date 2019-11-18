package com.cczu.model.controller;

import com.cczu.sys.comm.controller.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 风险告知卡
 */
@Controller
@RequestMapping("fxgk/fxgzk")
public class PageFxgkFxgzkController extends BaseController {

	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model,HttpServletRequest request) {
		return "fxgk/fxgzk/index";	
	}

}
