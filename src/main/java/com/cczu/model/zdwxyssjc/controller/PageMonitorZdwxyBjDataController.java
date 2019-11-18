package com.cczu.model.zdwxyssjc.controller;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.zdwxyssjc.service.MonitorZdwxyBjDataService;
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
 * 在线监控预警-报警信息controller
 */
@Controller
@RequestMapping("zxjkyj/bjxx")
public class PageMonitorZdwxyBjDataController extends BaseController {

	@Autowired
	private MonitorZdwxyBjDataService monitorZdwxyBjDataService;
	@Autowired
	private IBisQyjbxxService qyjbxxService;

	/**
	 * 首页报警信息页面
	 */
	@RequestMapping(value="index")
	public String index(HttpServletRequest request, Model model) {
		ShiroRealm.ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		model.addAttribute("usertype", sessionuser.getUsertype());
		model.addAttribute("qyid", sessionuser.getQyid());
		model.addAttribute("sigid", request.getParameter("sigid"));
		model.addAttribute("type", request.getParameter("type"));

		if("1".equals(sessionuser.getUsertype())){
			BIS_EnterpriseEntity be = qyjbxxService.findInfoById(sessionuser.getQyid());
			if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
				model.addAttribute("usertype", "0");
		}
		return "zxjkyj/bjxx/index";
	}

	/**
	 * 首页报警信息list
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("lx", request.getParameter("lx"));
		map.put("type", request.getParameter("type"));
		map.put("starttime", request.getParameter("starttime"));
		map.put("endtime", request.getParameter("endtime"));
		map.put("sigid", request.getParameter("sigid"));
		map.put("pointid", request.getParameter("pointid"));
		map.putAll(getAuthorityMap());
		return monitorZdwxyBjDataService.dataGrid(map);
	}

	/**
	 * 储罐报警默认页面
	 */
	@RequestMapping(value="cgbjindex")
	public String cgIndex(HttpServletRequest request, Model model) {
		ShiroRealm.ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		model.addAttribute("usertype", sessionuser.getUsertype());
		model.addAttribute("qyid", sessionuser.getQyid());

		if("1".equals(sessionuser.getUsertype())){
			BIS_EnterpriseEntity be = qyjbxxService.findInfoById(sessionuser.getQyid());
			if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
				model.addAttribute("usertype", "0");
		}
		return "zxjkyj/bjxx/cgbjindex";
	}

	/**
	 * 储罐报警list页面
	 */
	@RequestMapping(value="cgbjlist")
	@ResponseBody
	public Map<String, Object> getCgData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("qyname"));
		map.put("lx", request.getParameter("lx"));
		map.put("wh", request.getParameter("wh"));
		map.put("type", request.getParameter("type"));
		map.put("datetype", request.getParameter("datetype"));
		map.put("starttime", request.getParameter("starttime"));
		map.put("endtime", request.getParameter("endtime"));
		map.putAll(getAuthorityMap());
		return monitorZdwxyBjDataService.cgDataGrid(map);
	}

	/**
	 * 气体浓度报警默认页面
	 */
	@RequestMapping(value="ndbjindex")
	public String ndIndex(HttpServletRequest request, Model model) {
		ShiroRealm.ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		model.addAttribute("usertype", sessionuser.getUsertype());
		model.addAttribute("qyid", sessionuser.getQyid());

		if("1".equals(sessionuser.getUsertype())){
			BIS_EnterpriseEntity be = qyjbxxService.findInfoById(sessionuser.getQyid());
			if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
				model.addAttribute("usertype", "0");
		}
		return "zxjkyj/bjxx/ndbjindex";
	}

	/**
	 * 气体浓度报警list页面
	 */
	@RequestMapping(value="ndbjlist")
	@ResponseBody
	public Map<String, Object> getNdData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("qyname"));
		map.put("dwh", request.getParameter("dwh"));
		map.put("type", request.getParameter("type"));
		map.put("datetype", request.getParameter("datetype"));
		map.put("starttime", request.getParameter("starttime"));
		map.put("endtime", request.getParameter("endtime"));
		map.putAll(getAuthorityMap());
		return monitorZdwxyBjDataService.ndDataGrid(map);
	}

	/**
	 * 高危工艺报警默认页面
	 */
	@RequestMapping(value="gwgybjindex")
	public String gwgyIndex(HttpServletRequest request, Model model) {
		ShiroRealm.ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		model.addAttribute("usertype", sessionuser.getUsertype());
		model.addAttribute("qyid", sessionuser.getQyid());

		if("1".equals(sessionuser.getUsertype())){
			BIS_EnterpriseEntity be = qyjbxxService.findInfoById(sessionuser.getQyid());
			if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
				model.addAttribute("usertype", "0");
		}
		return "zxjkyj/bjxx/gwgybjindex";
	}

	/**
	 * 高危工艺报警list页面
	 */
	@RequestMapping(value="gwgybjlist")
	@ResponseBody
	public Map<String, Object> getGwgyData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("qyname"));
		map.put("dwh", request.getParameter("dwh"));
		map.put("gwgyname", request.getParameter("gwgyname"));
		map.put("type", request.getParameter("type"));
		map.put("datetype", request.getParameter("datetype"));
		map.put("starttime", request.getParameter("starttime"));
		map.put("endtime", request.getParameter("endtime"));
		map.putAll(getAuthorityMap());
		return monitorZdwxyBjDataService.gwgyDataGrid(map);
	}

}
