package com.cczu.model.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.service.FxgkFxytService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.Barrio;
import com.cczu.sys.system.service.BarrioService;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 风险管控-风险点分布地图
 * @author zpc
 * @date 2017/08/10
 */
@Controller
@RequestMapping("fxgk/fxyt")
public class PageFxgkFxytController extends BaseController{
	
	@Autowired
	private FxgkFxytService fxgkFxytService;
	@Autowired
	private BarrioService barrioService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value = "map")
	public String map(Model model) {
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "fxgk/fxyt/mapindex";
	}
	
	/**
	 * 添加坐标
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "maplist")
	@ResponseBody
	public Map<String, Object> list(Model model,HttpServletRequest request) {
		Map<String, Object> mapdata = new HashMap<String, Object>();
		mapdata.putAll(getAuthorityMap());
		List<Map<String, Object>> list = fxgkFxytService.findMapList(mapdata);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		map.put("barrio", null);
		if(!StringUtils.isEmpty(UserUtil.getCurrentShiroUser().getXzqy())){
			Barrio barrio = barrioService.findPointBycode(UserUtil.getCurrentShiroUser().getXzqy());
			map.put("barrio", barrio);
		}
		return map;
	}
	
	/**
	 * 搜索
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "searchlist/{keyword}")
	@ResponseBody
	public Map<String, Object> searchlist(@PathVariable("keyword") String keyword, Model model) {
		Map<String, Object> mapdata = new HashMap<String, Object>();
		mapdata.putAll(getAuthorityMap());
		mapdata.put("qyname", keyword);
		List<Map<String, Object>> list = fxgkFxytService.findMapList(mapdata);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		return map;
	}
}
