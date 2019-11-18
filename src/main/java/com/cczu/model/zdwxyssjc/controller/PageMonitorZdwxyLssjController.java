package com.cczu.model.zdwxyssjc.controller;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.zdwxyssjc.service.MonitorZdwxyBjDataService;
import com.cczu.model.zdwxyssjc.service.MonitorZdwxyLssjService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.service.ShiroRealm;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 在线监控预警-历史数据controller
 */
@Controller
@RequestMapping("zxjkyj/lssj")
public class PageMonitorZdwxyLssjController extends BaseController {

	@Autowired
	private MonitorZdwxyLssjService monitorZdwxyLssjService;
	@Autowired
	private IBisQyjbxxService qyjbxxService;

	/**
	 * 储罐波动线状图获取数据
	 */
	@RequestMapping(value="getlinejson/{id}/{jctype}")
	@ResponseBody
	public String getLineDataJson(@PathVariable("id") Long id, @PathVariable("jctype") String jctype, HttpServletRequest request) {
		Map<String,Object> map= new HashMap<String, Object>();
		map.put("datestart", request.getParameter("datestart"));
		map.put("dateend", request.getParameter("dateend"));
		map.put("type", request.getParameter("type"));
		map.put("jctype", jctype);
		map.put("pointid", id);
		String s= monitorZdwxyLssjService.getbdDate(map);
		return s;
	}

	/**
	 * 储罐所有监测类型波动线状图获取数据
	 */
	@RequestMapping(value="getcglinejson/{tankid}")
	@ResponseBody
	public String getLineDataJson(@PathVariable("tankid") Long tankid, HttpServletRequest request) {
		Map<String,Object> map= new HashMap<String, Object>();
		map.put("datestart", request.getParameter("datestart"));
		map.put("dateend", request.getParameter("dateend"));
		map.put("tankid", tankid);
		String s= monitorZdwxyLssjService.getCgbdDate(map);
		return s;
	}

	/**
	 * 高危工艺温度、压力、液位波动线状图获取数据
	 */
	@RequestMapping(value="getgwgylinejson/{gwgyid}")
	@ResponseBody
	public String getGwgyLinejson(@PathVariable("gwgyid") Long gwgyid, HttpServletRequest request) {
		Map<String,Object> map= new HashMap<String, Object>();
		map.put("datestart", request.getParameter("datestart"));
		map.put("dateend", request.getParameter("dateend"));
		map.put("gwgyid", gwgyid);
		String s= monitorZdwxyLssjService.getGwgybdDate(map);
		return s;
	}


}
