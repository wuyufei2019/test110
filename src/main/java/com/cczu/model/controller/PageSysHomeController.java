package com.cczu.model.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cczu.sys.comm.mapper.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.ISSUE_SafetyProductionDynamicEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.SysHomeService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.entity.Barrio;
import com.cczu.sys.system.service.BarrioService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 安监端首页 sysHome:controller
 * 
 * @author jason
 * 
 */
@Controller
@RequestMapping("syshome")
public class PageSysHomeController extends BaseController {

	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private SysHomeService syshomeservice;
	@Autowired
	private BarrioService barrioservice;

	/**
	 * 首页页面跳转
	 */
	@RequestMapping(value="map")
	public String index(Model model) {
		Barrio bro=barrioservice.findPointBycode(UserUtil.getCurrentShiroUser().getXzqy());
		if(bro!=null)
		model.addAttribute("mappoint", bro.getMappoint());
		model.addAttribute("forbidScroll", "1");//禁止地图使用滚轮标志位
		return "system/sysHome";
	}
	/**
	 *安监端 获取展示数据json（高港专用）
	 * @param model
	 */
	@RequestMapping(value = "sjzs2")
	@ResponseBody
	String getDateJson2(Model model) {

		List<Map<String, Object>> obs;
		Map<String, Object> map = getAuthorityMap();	
		try {
			obs = syshomeservice.getDate2(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			obs=null;
		}
		 return JsonMapper.getInstance().toJson(obs);
	}

	/**
	 *安监端 获取展示数据json
	 * @param model
	 */
	@RequestMapping(value = "sjzs")
	@ResponseBody
	String getDateJson(Model model) {

		List<Object> obs;
		Map<String, Object> map = getAuthorityMap();
		try {
			obs = syshomeservice.getDate(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			obs=null;
		}
		return obs.toString();
	}


	/**
	 * 企业端首页获取展示数据json
	 * @param model
	 */
	@RequestMapping(value = "qysjzs")
	@ResponseBody
	public Map<String, Object> getQyDateJson(Model model) {
		Map<String, Object> map;
		try {
			ShiroUser user = UserUtil.getCurrentShiroUser();
			Map<String,Object> mapData = new HashMap<>();
			mapData.put("qyid", user.getQyid());
			mapData.put("uid", user.getId());
			map = syshomeservice.getQyDate(mapData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map=null;
		}
		return map;
	}
	/**
	 * 最新文件 展示10个
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "issue")
	@ResponseBody
	public List<Map<String,Object>>  getIssueInfo(Model model) {
		Map<String, Object> map = new HashMap<String,Object>();
		ShiroUser user = UserUtil.getCurrentShiroUser();
		map.put("pageSize",10);
		map.put("pageNo",1);
		if("0".equals(user.getUsertype())){
			map.put("xzqy",user.getXzqy());
		}else if("1".equals(user.getUsertype())){
			map.put("qyid",user.getQyid());
		}
		List<Map<String,Object>> list= syshomeservice.getIssueInfo(map);
		return list;
	}
	/**
	 * 最新动态 展示10个
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "zxdt")
	@ResponseBody
	public List<ISSUE_SafetyProductionDynamicEntity>  getZxdtInfo(Model model) {
		Map<String, Object> map = getAuthorityMap();
		map.put("pageSize",10);
		map.put("pageNo",1);
		List<ISSUE_SafetyProductionDynamicEntity> list= syshomeservice.getZxdtInfo(map);
		return list;
	}
	/**
	 * 报警信息 展示10个
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "bjxx")
	@ResponseBody
	public List<Map<String,Object>> getBjxxInfo(Model model) {
		Map<String, Object> map =getAuthorityMap();
		List<Map<String,Object>> list= syshomeservice.getBjxxInfo(map);
		return list;
	}
	
}
