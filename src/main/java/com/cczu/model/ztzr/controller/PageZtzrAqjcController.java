package com.cczu.model.ztzr.controller;

import com.cczu.model.mbgl.service.TargetAqzzService;
import com.cczu.model.service.BisGzxxService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 目标管理-安全职责controller
 * 
 * @author jason
 * 
 */

@Controller
@RequestMapping("ztzr/aqjc")
public class PageZtzrAqjcController extends BaseController {

	@Autowired
	private TargetAqzzService targetAqzzService;
	@Autowired
	private BisGzxxService bisGzxxService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model,HttpServletRequest request) {
		return "ztzr/fzr/aqjc/index";
	}

}
