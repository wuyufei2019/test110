package com.cczu.model.zdwxyssjc.controller;

import com.cczu.model.zdwxyssjc.service.MonitorZdwxyDsjService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 在线监控预警-重大危险源大数据controller
 */
@Controller
@RequestMapping("zxjkyj/zdwxydsj")
public class PageMonitorZdwxyDsjController extends BaseController {

	@Autowired
	private MonitorZdwxyDsjService monitorZdwxyDsjService;

	/**
	 * 储罐波动数据页面跳转
	 */
	@RequestMapping(value="cgindex")
	public String wlbdindex(Model model) {
		ShiroRealm.ShiroUser user = UserUtil.getCurrentShiroUser();
		if("1".equals(user.getUsertype())) {
			model.addAttribute("usertype", "1");
			model.addAttribute("qyid", user.getQyid());
		}
		return "zxjkyj/zdwxy/cgdsj/index";
	}

	/**
	 * 储罐波动线状图获取数据
	 */
	@RequestMapping(value="getlinejson")
	@ResponseBody
	public String getLineDataJson(HttpServletRequest request) {
		Map<String,Object> map= getAuthorityMap();
		map.put("datestart", request.getParameter("starttime"));
		map.put("dateend", request.getParameter("endtime"));
		map.put("tankid", request.getParameter("tankid"));
		String s= monitorZdwxyDsjService.getMatLsbdDate(map);
		return s;
	}
	
	/**
	 * 气体浓度波动图页面跳转
	 */
	@RequestMapping(value="qtindex")
	public String qtindex(Model model) {
		ShiroRealm.ShiroUser user = UserUtil.getCurrentShiroUser();
		if("1".equals(user.getUsertype())) {
			model.addAttribute("usertype", "1");
			model.addAttribute("qyid", user.getQyid());
		}
		return "zxjkyj/zdwxy/qtnddsj/index";
	}
	
	/**
	 * 气体浓度波动线状图获取数据
	 */
	@RequestMapping(value="getqtlinejson")
	@ResponseBody
	public String getQtLineDataJson(HttpServletRequest request) {
		Map<String,Object> map= getAuthorityMap();
		map.put("datestart", request.getParameter("starttime"));
		map.put("dateend", request.getParameter("endtime"));
		String s= monitorZdwxyDsjService.getQtbdDate(map);
		return s;
	}

	/**
	 * 高危工艺波动数据页面跳转
	 */
	@RequestMapping(value="gwgyindex")
	public String gwgyindex(Model model) {
		ShiroRealm.ShiroUser user = UserUtil.getCurrentShiroUser();
		if("1".equals(user.getUsertype())) {
			model.addAttribute("usertype", "1");
			model.addAttribute("qyid", user.getQyid());
		}
		return "zxjkyj/zdwxy/gwgydsj/index";
	}

	/**
	 * 高危工艺波动线状图获取数据
	 */
	@RequestMapping(value="getgwgylinejson")
	@ResponseBody
	public String getGwgylinejson(HttpServletRequest request) {
		Map<String,Object> map= getAuthorityMap();
		map.put("datestart", request.getParameter("starttime"));
		map.put("dateend", request.getParameter("endtime"));
		map.put("tankid", request.getParameter("tankid"));
		String s= monitorZdwxyDsjService.getGwgybdDate(map);
		return s;
	}

	/**
	 * 二道门波动数据页面跳转
	 */
	@RequestMapping(value="edmindex")
	public String edmindex(Model model) {
		ShiroRealm.ShiroUser user = UserUtil.getCurrentShiroUser();
		if("1".equals(user.getUsertype())) {
			model.addAttribute("usertype", "1");
			model.addAttribute("qyid", user.getQyid());
		}
		return "zxjkyj/zdwxy/edmdsj/index";
	}

	/**
	 * 二道门波动线状图获取数据
	 */
	@RequestMapping(value="getedmlinejson")
	@ResponseBody
	public String getEdmlinejson(HttpServletRequest request) {
		Map<String,Object> map= getAuthorityMap();
		map.put("datestart", request.getParameter("starttime"));
		map.put("dateend", request.getParameter("endtime"));
		String s= monitorZdwxyDsjService.getEdmbdDate(map);
		return s;
	}
	
}
