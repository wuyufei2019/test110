package com.cczu.model.zdgl.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.zdgl.service.ZdglWjcdjsService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 制度管理-文件传递接受controller
 */
@Controller
@RequestMapping("zdgl/cdjs")
public class PageZdglWjcdjsController extends BaseController {

	@Autowired
	private ZdglWjcdjsService zdglWjcdjsService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "zdgl/aqwjgl/wjcdjs/index";
	}
	
	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("wjname", request.getParameter("zdgl_jscd_wjname"));
		map.put("qyid", UserUtil.getCurrentUser().getId2());
		return zdglWjcdjsService.dataGrid(map);
	}
	
	/**
	 * 查看传阅情况 文件id
	 * @param model
	 */
	@RequestMapping(value = "view1/{id}", method = RequestMethod.GET)
	public String view1(@PathVariable("id") Long id,Model model) {
		model.addAttribute("wjid",id);
		return "zdgl/aqwjgl/wjcdjs/view";
	}
	
	/**
	 * 传阅情况
	 * @param request
	 */
	@RequestMapping(value="cyqklist")
	@ResponseBody
	public Map<String, Object> getcyqkData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("wjid", request.getParameter("wjid"));
		map.put("flag", request.getParameter("flag"));
		return zdglWjcdjsService.cyqkGrid(map);
	}
	
	/**
	 * 查看下载情况 文件id
	 * @param model
	 */
	@RequestMapping(value = "view2/{id}", method = RequestMethod.GET)
	public String view2(@PathVariable("id") Long id,Model model) {
		model.addAttribute("wjid",id);
		return "zdgl/aqwjgl/wjcdjs/view2";
	}
	
	/**
	 * 下载情况
	 * @param request
	 */
	@RequestMapping(value="xzqklist")
	@ResponseBody
	public Map<String, Object> getxzqkData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("wjid", request.getParameter("wjid"));
		map.put("flag2", request.getParameter("flag"));
		return zdglWjcdjsService.cyqkGrid(map);
	}
}
