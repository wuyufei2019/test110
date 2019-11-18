package com.cczu.model.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.IMonitorEdmDataService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;


/**
 * 在线监控预警-二道门实时数据controller
 * @author jason
 * @date 2017年6月23日
 */
@Controller
@RequestMapping("zxjkyj/edmjc")
public class PageMonitorEdmController extends BaseController {

	@Autowired
	private IMonitorEdmDataService monitorEdmDataService;
	@Autowired
	private IBisQyjbxxService qyjbxxService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		model.addAttribute("usertype", sessionuser.getUsertype());
		model.addAttribute("qyid", sessionuser.getQyid());
		
		if("1".equals(sessionuser.getUsertype())){
			BIS_EnterpriseEntity be = qyjbxxService.findInfoById(sessionuser.getQyid());
			if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
				model.addAttribute("usertype", "0");
		}
		return "zxjkyj/ssjc/edm/index";
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("ssjc_edm_qy_name"));
		map.putAll(getAuthorityMap());
		return monitorEdmDataService.dataGrid(map);
	}
	
	/**
	 * 选择企业json
	 * @param model
	 */
	@RequestMapping(value="qyjson")
	@ResponseBody
	public String selectQyJson(Model model,HttpServletRequest request) {
		return monitorEdmDataService.qyListJson(getAuthorityMap());
	}
	
	/**
	 * 添加坐标
	 */
	@RequestMapping(value="maplist")
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request) {
		Map<String, Object> mapdata = getAuthorityMap();
		List<Map<String,Object>> list=monitorEdmDataService.findMapList(mapdata);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		return map;
	}
	
	/**
	 * 导出excel
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(String ids, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(!ids.equals(""))
			map.put("qyids", ids);
		else if(UserUtil.getCurrentShiroUser().getUsertype().equals("1"))
			map.put("qyid",UserUtil.getCurrentShiroUser().getQyid());
		monitorEdmDataService.exportExcel(response, map);
		
	}
	 
}
