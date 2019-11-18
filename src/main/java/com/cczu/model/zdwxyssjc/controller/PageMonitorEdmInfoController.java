package com.cczu.model.zdwxyssjc.controller;

import com.cczu.model.zdwxyssjc.entity.Main_SignalEdmEntity;
import com.cczu.model.zdwxyssjc.service.MonitorEdmInfoService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.service.ShiroRealm;
import com.cczu.sys.system.utils.UserUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 在线监控预警-二道门信息controller
 */
@Controller
@RequestMapping("zxjkyj/edm")
public class PageMonitorEdmInfoController extends BaseController {

	@Autowired
	private MonitorEdmInfoService monitorEdmInfoService;

	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(HttpServletRequest request, Model model) {
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "zxjkyj/edm/index";
	}
	
	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
        map.put("qyname", request.getParameter("ssjc_edm_qy_name"));
		map.put("ygcode", request.getParameter("ygcode"));
		map.put("ygname", request.getParameter("ygname"));
		map.put("edmname", request.getParameter("edmname"));
		map.put("starttime", request.getParameter("starttime"));
		map.put("finishtime", request.getParameter("finishtime"));
		map.putAll(getAuthorityMap());
		return monitorEdmInfoService.dataGrid(map);
	}
	
	/**
	 * 获取最新的二道门信息
	 */
	@RequestMapping(value="getEdmInfo")
	@ResponseBody
	public String getEdmInfo(HttpServletRequest request) {
		ShiroRealm.ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		List<Main_SignalEdmEntity> edmList = monitorEdmInfoService.findEdmInfo(sessionuser.getQyid());
		return JsonMapper.toJsonString(edmList);
	}

	/**
	 * 获取二道门实时监测企业名称下拉框内容（安监端）
	 * @param model
	 * @return
	 */
	@RequestMapping(value="qyjson")
	@ResponseBody
	public String qyJson(Model model) {
		Map<String, Object> map = new HashMap<>();
		map.putAll(getAuthorityMap());
		return monitorEdmInfoService.getQyJson(map);
	}

	/**
	 * 统计二道门当前日期在场人员数量
	 * @param model
	 * @return
	 */
	@RequestMapping(value="tj")
	@ResponseBody
	public String statistics(Model model) {
		Map<String, Object> map = new HashMap<>();
		map.putAll(getAuthorityMap());
		return JsonMapper.toJsonString(monitorEdmInfoService.statistics(map));
	}

}
