package com.cczu.model.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.service.TsVideoService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;


/**
 * 实时视频监测controller
 * @author jason
 *
 */
@Controller
@RequestMapping("fmew/sp")
public class PageFmewSpController extends BaseController {
	@Autowired 
	TsVideoService tsVideoService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Map<String, Object> mapData=new HashMap<String, Object>();
		if(!"9".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			mapData.put("xzqy", sessionuser.getXzqy());
		}
		List<Map<String, Object>> list=tsVideoService.findQyList(mapData);
		model.addAttribute("qylist", list);

		return "model/fmew/sp/index";
	}

	
	@RequestMapping(value="showsp/{qyid}")
	public String showSP(@PathVariable Long qyid, Model model) {
		model.addAttribute("qyid", qyid);
		return "model/fmew/sp/show";
	}
	
	
	@RequestMapping(value="listjson")
	@ResponseBody
	public String videoJson( Long qyid, Model model){
		return tsVideoService.findByQyid(qyid);
	} 
	
	
	
}
