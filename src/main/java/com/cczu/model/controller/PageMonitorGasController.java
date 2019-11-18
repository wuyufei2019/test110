package com.cczu.model.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.IMonitorGasDataService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;


/**
 * 在线监控预警-气体浓度实时监测controller
 * @author zpc
 * @date 2017年7月4日
 */
@Controller
@RequestMapping("zxjkyj/qtndjc")
public class PageMonitorGasController extends BaseController {

	@Autowired
	private IMonitorGasDataService monitorGasDataService;
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
		return "zxjkyj/ssjc/qt/index";
	}

	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("ssjc_qtnd_qy_name"));
		map.putAll(getAuthorityMap());
		return monitorGasDataService.dataGrid(map);
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
		monitorGasDataService.exportExcel(response, map);
	}
	
	/**
	 * 选择企业json
	 * @param model
	 */
	@RequestMapping(value="qyjson")
	@ResponseBody
	public String selectQyJson(Model model,HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.putAll(getAuthorityMap());
		return monitorGasDataService.qyListJson(map);
	}
	
	/**
	 * 查看企业气体浓度实时储量
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		List<Map<String, Object>> cllist=monitorGasDataService.findInfoById(id);
		BIS_EnterpriseEntity ent= qyjbxxService.findInfoById(id);
		model.addAttribute("cllist", cllist);
		model.addAttribute("qiye", ent);
		return "zxjkyj/ssjc/qt/view";
	}
}
