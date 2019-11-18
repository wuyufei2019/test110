package com.cczu.model.zdgl.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cczu.model.zdgl.entity.ZDGL_AQPSEntity;
import com.cczu.model.zdgl.entity.ZDGL_CZGCEntity;
import com.cczu.model.zdgl.entity.ZDGL_GLZDEntity;
import com.cczu.model.zdgl.service.ZdglAqpsService;
import com.cczu.model.zdgl.service.ZdglCzgcService;
import com.cczu.model.zdgl.service.ZdglGlzdService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.Message;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.utils.MessageUtil;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.StringUtils;
import com.cczu.util.common.WordUtil;

/**
 * 制度管理-安全评审controller
 */
@Controller
@RequestMapping("zdgl/aqps")
public class PageZdglAqpsController extends BaseController {

	@Autowired
	private ZdglAqpsService zdglAqpsService;
	@Autowired
	private ZdglGlzdService zdglGlzdService;
	@Autowired
	private ZdglCzgcService zdglCzgcService;
	@Autowired
	private UserService userService;
	
	/**
	 * 制度评审列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index1")
	public String index(Model model,HttpServletRequest request) {
		model.addAttribute("qyid", request.getParameter("qyid"));
		return "zdgl/aqglzd/zdps/index";
	}
	
	/**
	 * 制度评审list页面
	 * @param request
	 */
	@RequestMapping(value="list1")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		String qyid = request.getParameter("qyid");
		if(StringUtils.isEmpty(qyid)){
			map.put("qyid", UserUtil.getCurrentUser().getId2());
		}else{
			map.put("qyid", qyid);
		}
		map.put("m2", request.getParameter("zdgl_zdps_m2"));
		return zdglAqpsService.dataGrid(map);
	}
	
	/**
	 * 获取安全制度名称和id
	 * zdidjson  {"id":11,"text":"安全制度名称"}
	 * return String
	 */
	@RequestMapping(value="zdidjson")
	@ResponseBody
	public String zdIdjson() {
		return zdglAqpsService.getzdIdjson(UserUtil.getCurrentUser().getId2());
	}
	
	/**
	 * 添加制度评审页面跳转
	 * @param model
	 */
	@RequestMapping(value = "create1" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create1");
		return "zdgl/aqglzd/zdps/form";
	}
	
	/**
	 * 添加制度评审信息
	 * @param request,model
	 */
	@RequestMapping(value = "create1" , method = RequestMethod.POST)
	@ResponseBody
	public String create(ZDGL_AQPSEntity aqps, Model model) {
		String datasuccess="success";
		aqps.setM18("1");
		zdglAqpsService.addInfo(aqps);
		
		Map<String,Object>  msgmap = new HashMap<String,Object>();
		msgmap.put(Message.MSGTARGET_H5,"weixin/enterprise/zdgl/zdglmain.jsp");
		msgmap.put(Message.MSGTARGET_PC,"zdgl/aqps/update/sh/"+aqps.getID());
		List<User> userlist = userService.findUserByPermission(UserUtil.getCurrentShiroUser().getQyid(), "zdgl:zdps:sh");
		for (User user : userlist) {
			MessageUtil.sendMsg(UserUtil.getCurrentShiroUser().getId()+"", user.getId()+"", "安全制度评审审核", Message.MessageType.DSH.getMsgType(),JSON.toJSONString(msgmap),"安全制度评审审核");
		}
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 查看制度评审页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view1/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String,Object> aqps = zdglAqpsService.findById(id);
		String[] aids = aqps.get("m1").toString().split(",");
		String m1 = "";
		for(int i=0;i<aids.length;i++){
			ZDGL_GLZDEntity glzd = zdglGlzdService.find(Long.parseLong(aids[i]));
			if(glzd != null){
				m1 += glzd.getM1()+"，";
			}
		}
		if(m1.length()>0){
			model.addAttribute("zd", m1.substring(0, m1.length()-1));
		}else{
			model.addAttribute("zd", m1);
		}
		model.addAttribute("aqps", aqps);
		return "zdgl/aqglzd/zdps/view";
	}

	/**
	 * 制度操作规程显示页面
	 * @param model
	 */
	@RequestMapping(value="index2")
	public String index2(Model model,HttpServletRequest request) {
		model.addAttribute("qyid", request.getParameter("qyid"));
		return "zdgl/aqczgc/gcps/index";
	}
	
	/**
	 * 操作规程list页面
	 * @param request
	 */
	@RequestMapping(value="list2")
	@ResponseBody
	public Map<String, Object> getData2(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		String qyid = request.getParameter("qyid");
		if(StringUtils.isEmpty(qyid)){
			map.put("qyid", UserUtil.getCurrentUser().getId2());
		}else{
			map.put("qyid", qyid);
		}
		map.put("m2", request.getParameter("zdgl_czgc_m2"));
		return zdglAqpsService.dataGrid2(map);
	}
	
	/**
	 * 获取操作规程名称和id
	 * gcidjson  {"id":11,"text":"操作规程名称"}
	 * return String
	 */
	@RequestMapping(value="gcidjson")
	@ResponseBody
	public String gcIdjson() {
		return zdglAqpsService.getgcIdjson(UserUtil.getCurrentUser().getId2());
	}
	
	/**
	 * 添加操作规程页面跳转
	 * @param model
	 */
	@RequestMapping(value = "create2" , method = RequestMethod.GET)
	public String create2(Model model) {
		model.addAttribute("action", "create2");
		return "zdgl/aqczgc/gcps/form";
	}
	
	/**
	 * 添加操作规程信息
	 * @param request,model
	 */
	@RequestMapping(value = "create2" , method = RequestMethod.POST)
	@ResponseBody
	public String create2(ZDGL_AQPSEntity aqps, Model model) {
		String datasuccess="success";
		aqps.setM18("2");
		zdglAqpsService.addInfo(aqps);

		Map<String,Object>  msgmap = new HashMap<String,Object>();
		msgmap.put(Message.MSGTARGET_H5,"weixin/enterprise/zdgl/zdglmain.jsp");
		msgmap.put(Message.MSGTARGET_PC,"zdgl/aqps/update2/sh/"+aqps.getID());
		List<User> userlist = userService.findUserByPermission(UserUtil.getCurrentShiroUser().getQyid(), "zdgl:gcps:sh");
		for (User user : userlist) {
			MessageUtil.sendMsg(UserUtil.getCurrentShiroUser().getId()+"", user.getId()+"", "安全操作规程评审审核", Message.MessageType.DSH.getMsgType(),JSON.toJSONString(msgmap),"安全操作规程评审审核");
		}
		//返回结果
		return datasuccess;
	}

	/**
	 * 查看操作规程页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view2/{id}", method = RequestMethod.GET)
	public String view2(@PathVariable("id") Long id, Model model) {
		Map<String,Object> aqps = zdglAqpsService.findById(id);
		String[] aids = aqps.get("m1").toString().split(",");
		String m1 = "";
		for(int i=0;i<aids.length;i++){
			ZDGL_CZGCEntity czgc = zdglCzgcService.find(Long.parseLong(aids[i]));
			if(czgc != null){
				m1 += czgc.getM1()+"，";
			}
		}
		if(m1.length()>0){
			model.addAttribute("gc", m1.substring(0, m1.length()-1));
		}else{
			model.addAttribute("gc", m1);
		}
		model.addAttribute("aqps", aqps);
		return "zdgl/aqczgc/gcps/view";
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{type}/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, @PathVariable("type") String type, Model model) {
		ZDGL_AQPSEntity aqps = zdglAqpsService.find(id);
		model.addAttribute("aqps", aqps);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("type", type);
		return "zdgl/aqglzd/zdps/form";
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update2/{type}/{id}", method = RequestMethod.GET)
	public String update2(@PathVariable("id") Long id, @PathVariable("type") String type, Model model) {
		ZDGL_AQPSEntity aqps = zdglAqpsService.find(id);
		model.addAttribute("aqps", aqps);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("type", type);
		return "zdgl/aqczgc/gcps/form";
	}
	
	/**
	 * 修改信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(String role,ZDGL_AQPSEntity aqps, Model model){
		String datasuccess="success";	
		if(!StringUtils.isEmpty(role)){
			if(role.equals("2")){
				//审核
				aqps.setM12(UserUtil.getCurrentUser().getId().toString());
				Timestamp t=DateUtils.getSysTimestamp();
				aqps.setM14(t);
				
				List<User> userlist = new ArrayList<>();
				Map<String,Object>  msgmap = new HashMap<String,Object>();
				msgmap.put(Message.MSGTARGET_H5,"weixin/enterprise/zdgl/zdglmain.jsp");
				if(aqps.getM18().equals("1")){
					msgmap.put(Message.MSGTARGET_PC,"zdgl/aqps/update/pz/"+aqps.getID());
					userlist = userService.findUserByPermission(UserUtil.getCurrentShiroUser().getQyid(), "zdgl:zdps:sp");
				}else{
					msgmap.put(Message.MSGTARGET_PC,"zdgl/aqps/update2/pz/"+aqps.getID());
					userlist = userService.findUserByPermission(UserUtil.getCurrentShiroUser().getQyid(), "zdgl:gcps:sp");
				}
				for (User user : userlist) {
					//暂时发给自己
					MessageUtil.sendMsg(UserUtil.getCurrentShiroUser().getId()+"", user.getId()+"", "安全评审批准", Message.MessageType.DSP.getMsgType(),JSON.toJSONString(msgmap),"安全评审批准");
				}
			}else if(role.equals("3")){
				//审批
				aqps.setM15(UserUtil.getCurrentUser().getId().toString());
				Timestamp t=DateUtils.getSysTimestamp();
				aqps.setM17(t);
			}
		}
		zdglAqpsService.updateInfo(aqps);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除安全评审
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			zdglAqpsService.deleteInfo(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 安全制度页面添加评审跳转
	 * @param id,model
	 */
	@RequestMapping(value = "createps1/{id}")
	public String createps1(@PathVariable("id") Long id, Model model) {
		Map<String,Object> map = new HashMap<>();
		map.put("m1", id);
		map.put("m18", "1");
		model.addAttribute("aqps", map);
		//返回页面
		model.addAttribute("action", "createps");
		return "zdgl/aqglzd/zdps/form";
	}
	
	/**
	 * 规程管理页面添加评审跳转
	 * @param id,model
	 */
	@RequestMapping(value = "createps2/{id}")
	public String createps2(@PathVariable("id") Long id, Model model) {
		Map<String,Object> map = new HashMap<>();
		map.put("m1", id);
		map.put("m18", "2");
		model.addAttribute("aqps", map);
		//返回页面
		model.addAttribute("action", "createps");
		return "zdgl/aqglzd/zdps/form";
	}
	
	/**
	 * 安全制度页面添加评审
	 * @param request,model
	 */
	@RequestMapping(value = "createps")
	@ResponseBody
	public String createps(ZDGL_AQPSEntity aqps, Model model){
		String datasuccess="success";	
		zdglAqpsService.addInfo(aqps);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 导出评审表word
	 */
	@RequestMapping(value = "export/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = zdglAqpsService.getWord(id);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "zdgcps.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
}
