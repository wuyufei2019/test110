package com.cczu.model.zdgl.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.zdgl.entity.ZDGL_WJFBEntity;
import com.cczu.model.zdgl.service.ZdglWjckService;
import com.cczu.model.zdgl.service.ZdglWjfbService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 制度管理-文件查看controller
 */
@Controller
@RequestMapping("zdgl/wjck")
public class PageZdglWjckController extends BaseController {

	@Autowired
	private ZdglWjckService zdglWjckService;
	@Autowired
	private ZdglWjfbService zdglWjfbService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "zdgl/aqwjgl/wjck/index";
	}
	
	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("wjname", request.getParameter("zdgl_wjck_wjname"));
		map.put("m2", request.getParameter("zdgl_wjck_ydqk"));
		map.put("m3", request.getParameter("zdgl_wjck_xzqk"));
		map.put("userid", UserUtil.getCurrentUser().getId());
		return zdglWjckService.dataGrid(map);
	}
	
	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="list2")
	@ResponseBody
	public Map<String, Object> getData2(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("wjname", request.getParameter("zdgl_wjck_wjname"));
		map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		return zdglWjckService.dataGrid2(map);
	}
	
	/**
	 * 在线查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public String view2(Long id, Long wjid, Model model) {
		ZDGL_WJFBEntity wjfb = zdglWjfbService.find(wjid);
		model.addAttribute("wjfb", wjfb);
		model.addAttribute("cdjsid", id);
		//修改阅读状态
		Map<String,Object> map = new HashMap<>();
		map.put("cdjsid", id);
		map.put("zt", "m2");
		zdglWjckService.updateztbyid(map);
		return "zdgl/aqwjgl/wjck/view2";
	}
	
	/**
	 * 修改下载状态
	 * id 接受传递id
	 */
	@RequestMapping(value = "down/{id}", method = RequestMethod.GET)
	public void down(@PathVariable("id") Long id, Model model) {
		//修改阅读状态
		Map<String,Object> map = new HashMap<>();
		map.put("cdjsid", id);
		map.put("zt", "m3");
		zdglWjckService.updateztbyid(map);
	}
}
