package com.cczu.model.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.IMonitorHydropowerDataService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;


/**
 * 在线监控预警-水电实时用量controller
 * @author zpc
 * @date 2017年7月6日
 */
@Controller
@RequestMapping("zxjkyj/sdyljc")
public class PageMonitorHydropowerController extends BaseController {

	@Autowired
	private IMonitorHydropowerDataService monitorHydropowerDataService;
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
		return "zxjkyj/ssjc/sdyl/index";
	}

	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("ssjc_sdyl_qy_name"));
		map.putAll(getAuthorityMap());
		return monitorHydropowerDataService.dataGrid(map);
	}
		
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("zxjkyj:sdyljc:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(String ids, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(!ids.equals(""))
			map.put("qyids", ids);
		else if(UserUtil.getCurrentShiroUser().getUsertype().equals("1"))
			map.put("qyid",UserUtil.getCurrentShiroUser().getQyid());
		monitorHydropowerDataService.exportExcel(response, map);
	}
	
	/**
	 * 选择企业json
	 * @param model
	 */
	@RequestMapping(value="qyjson")
	@ResponseBody
	public String selectQyJson(Model model,HttpServletRequest request) {
		return monitorHydropowerDataService.qyListJson(getAuthorityMap());
	}
	
}
