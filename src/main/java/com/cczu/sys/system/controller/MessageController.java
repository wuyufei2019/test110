package com.cczu.sys.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.entity.Message;
import com.cczu.sys.system.service.MessageService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 
 * @description 消息提醒Controller
 * @author jason
 * @date 2018年1月16日
 */
@Controller
@RequestMapping("system/message")
public class MessageController extends BaseController{

	@Autowired
	private MessageService messageService;
	
	
	/**
	 * 用户信息提醒页面跳转
	 */
	@RequestMapping("index")
	public String index(HttpServletRequest request, Model model) {
		model.addAttribute("msgtype", request.getParameter("msgtype"));
		return "system/messageList";
	}
	
	
	/**
	 * 获取用户的提醒信息
	 */
	@RequestMapping("list")
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Page<Message> page=getPage(request);
		page.setOrder("ASC,DESC");
		page.setOrderBy("SENDSTATUE,CREATETIME");
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		filters.add(new PropertyFilter("LIKES_TOUSER", sessionuser.getId().toString()));
		filters.add(new PropertyFilter("NEQS_MSGTYPE", "wx"));
		page = messageService.search(page, filters);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("rows", page.getResult());
		map.put("total", page.getTotalCount());
		return map;
	}
	
	
	/**
	 * 根据查询条件获取用户的提醒信息
	 */
	@RequestMapping(value ="json" )
	@ResponseBody
	public String json(HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		filters.add(new PropertyFilter("EQS_TOUSER", sessionuser.getId().toString()));
		List<Message> mlist= messageService.search(filters);
		return JsonMapper.getInstance().toJson(mlist);
	}
	
	/**
	 * 根据查询条件获取用户的提醒信息
	 */
	@RequestMapping(value ="msgjson" )
	@ResponseBody
	public List<Map<String, Object>> getTypeCount(HttpServletRequest request) {
		return messageService.findTypeCount(UserUtil.getCurrentShiroUser().getId());
	}
	
	
	/**
	 * 批量删除信息提醒
	 * @param idList
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestBody List<Integer> idList) {
		for (Integer id : idList) {
			messageService.delete(id);
		}
		return "success";
	}

	/**
	 * 修改消息状态
	 */
	@RequestMapping(value = "uptmsg/{id}")
	@ResponseBody
	public String uptmsg(@PathVariable("id") String id) {
		String datasuccess = "success";
		messageService.uptmsg(id);
		return datasuccess;
	}
}
