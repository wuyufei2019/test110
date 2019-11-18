package com.cczu.model.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.service.IMonitorTankDataService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 安监端首页 sysHome:controller
 * 
 * @author jason
 * 
 */
@Controller
@RequestMapping("zxjkyj/dsjfx")
public class PageMonitorDsjfxController extends BaseController {
	@Autowired
	private IMonitorTankDataService monitorTankDataService;
	/**
	 * 物料实时数据页面跳转
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "zxjkyj/dsjfx/index";
	}
	/**
	 * 物料波动数据页面跳转
	 */
	@RequestMapping(value="wlbdindex")
	public String wlbdindex(Model model) {
		ShiroUser user = UserUtil.getCurrentShiroUser();
		if("1".equals(user.getUsertype()))
			model.addAttribute("usertype", "1");
		return "zxjkyj/dsjfx/wlbdindex";
	}
	/**
	 * 吞吐量大数据数据页面跳转
	 */
	@RequestMapping(value="ttlindex")
	public String ttlindex(Model model) {
		ShiroUser user = UserUtil.getCurrentShiroUser();
		if("1".equals(user.getUsertype()))
			model.addAttribute("usertype", "1");
		return "zxjkyj/dsjfx/ttlindex";
	}
	/**
	 * 物料实时饼图获取数据
	 */
	@RequestMapping(value="getpiejson")
	@ResponseBody
	public String getBarDataJson(HttpServletRequest request) {
		Map<String,Object> map= getAuthorityMap();
		map.put("type", "pie");
		List<Map<String,Object>> list= monitorTankDataService.getMatSsDate(map);
		return JsonMapper.getInstance().toJson(list);
	}
	/**
	 * 物料实时柱状图获取数据
	 */
	@RequestMapping(value="getbarjson")
	@ResponseBody
	public String getPieDataJson(HttpServletRequest request) {
		Map<String,Object> map= getAuthorityMap();
		map.put("type", "bar");
		List<Map<String,Object>> list= monitorTankDataService.getMatSsDate(map);
		return JsonMapper.getInstance().toJson(list);
	}
	
	/**
	 * 物料波动线状图获取数据
	 */
	@RequestMapping(value="getlinejson")
	@ResponseBody
	public String getLineDataJson(HttpServletRequest request) {
		Map<String,Object> map= getAuthorityMap();
		map.put("datestart", request.getParameter("starttime"));
		map.put("dateend", request.getParameter("endtime"));
		map.put("tankid", request.getParameter("tankid"));
		String s= monitorTankDataService.getMatLsbdDate(map);
		return s;
	}
	/**
	 * 物料吞吐量大数据获取数据
	 */
	@RequestMapping(value="getTtljson")
	@ResponseBody
	public String getTtlDataJson(HttpServletRequest request) {
	    Map<String,Object> map= getAuthorityMap();
		map.put("datestart", request.getParameter("starttime"));
		map.put("dateend", request.getParameter("endtime"));
		map.put("type", request.getParameter("type"));//按物料名称还是类型
		return JsonMapper.getInstance().toJson(monitorTankDataService.getMatTtlDate(map));
	}
	
	/**
	 * 物料吞吐量获取数据（根据时间统计）
	 */
	@RequestMapping(value="getTtlbytime")
	@ResponseBody
	public String getTtlDataJsonByTime(HttpServletRequest request) {
		Map<String,Object> map= getAuthorityMap();
		map.put("datestart", request.getParameter("starttime"));
		map.put("dateend", request.getParameter("endtime"));
		map.put("type", request.getParameter("type"));//按日期还是按月份
		return monitorTankDataService.getTtlDateByTime(map);
	}
	
}
