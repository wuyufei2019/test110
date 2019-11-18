package com.cczu.model.controller;

import com.cczu.model.service.YhpcDsjService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * 双重机制大数据Controller
 * @author YZH
 */
@Controller
@RequestMapping("yhpc/dsj")
public class PageYhpcDsjController extends BaseController {

	@Autowired
	private YhpcDsjService yhpcDsjService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;

	/**
	 *安监端 获取企业信息总览json
	 * @param model
	 */
	@RequestMapping(value = "xxzl")
	@ResponseBody
	String getDateJson(Model model,HttpServletRequest request) {
		Long qyid=Long.parseLong(request.getParameter("qyid"));
		return yhpcDsjService.getxxzl(qyid);
	}

	/**
	 *安监端 风险点统计
	 * @param model
	 */
	@RequestMapping(value = "fxdtj")
	@ResponseBody
	String getDateJson2(Model model,HttpServletRequest request) {
		Long qyid=Long.parseLong(request.getParameter("qyid"));
		return yhpcDsjService.fxdtj(qyid);
	}

	/**
	 *安监端 隐患整改情况
	 * @param model
	 */
	@RequestMapping(value = "yhzgqk")
	@ResponseBody
	String getDateJson3(Model model,HttpServletRequest request) {
		Long qyid=Long.parseLong(request.getParameter("qyid"));
		return yhpcDsjService.yhzgqk(qyid);
	}

	/**
	 *安监端 实时隐患排查数(本周)
	 * @param model
	 */
	@RequestMapping(value = "yhpc")
	@ResponseBody
	String getDateJson4(Model model,HttpServletRequest request) {
		Long qyid=Long.parseLong(request.getParameter("qyid"));
		return yhpcDsjService.yhpcs(qyid);
	}
}
