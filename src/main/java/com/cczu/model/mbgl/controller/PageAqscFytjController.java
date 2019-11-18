package com.cczu.model.mbgl.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.mbgl.service.AqscFytjService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 安全生产-费用统计Controller
 * @author YZH
 */
@Controller
@RequestMapping("aqsctr/fytj")
public class PageAqscFytjController extends BaseController {

	@Autowired
	private AqscFytjService aqscFytjService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
		model.addAttribute("usertype", usertype);
		return "targetmanger/aqsctr/fytj/tjindex";
	}
	
	/**
	 * 按支出类型统计预算和支出
	 */
	@RequestMapping(value="lxcount")
	@ResponseBody
	public String getCount(HttpServletRequest request) {
		Map<String, Object> map = getAuthorityMap();
		return JsonMapper.getInstance().toJson(aqscFytjService.findlxlist(map));
	}
	
	/**
	 * 按部门统计支出
	 * 
	 * @param request
	 */
	@RequestMapping(value = "departcount")
	@ResponseBody
	public String departcount(HttpServletRequest request) {
		Map<String, Object> map = getAuthorityMap();
		return JsonMapper.getInstance().toJson(aqscFytjService.findDepartCount(map));
	}
}
