package com.cczu.sys.system.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.SafetyNetwork;
import com.cczu.sys.system.service.SafetyNetworkService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 安全管理网络controller
 * @author jason
 * @date 2017年11月24日
 */
@Controller
@RequestMapping("system/aqwl")
public class SafetyNetworkController extends BaseController{
	
	@Autowired
	private SafetyNetworkService networkService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "system/aqwl/list";
	}
	
	/**
	 * 列表页(list)
	 */
	@RequestMapping(value="list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Map<String, Object> map = getPageMap(request);
		if(StringUtils.isNotEmpty(request.getParameter("filter_LIKES_m1")))
			map.put("m1", request.getParameter("filter_LIKES_m1"));
		if(sessionuser.getUsertype().equals("1")){
			map.put("qyid", sessionuser.getQyid());
		}
		return networkService.searchData(map);
	}
	
	
	/**
	 * 安全管理网络下拉json
	 */
	@RequestMapping(value="textjson")
	@ResponseBody
	public String  deptlist(@RequestParam(value="qyid", defaultValue="0") int id){
		long qyid=id;
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1"))
			qyid=sessionuser.getQyid();
		return networkService.getlist(qyid);
	}

	/**
	 * 添加跳转
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String menuCreateForm(Model model) {
		model.addAttribute("model", new SafetyNetwork());
		model.addAttribute("action", "create");
		return "system/aqwl/form";
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public String create(@Valid SafetyNetwork dt,Model model) {
		String restring="success";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		dt.setID1(Long.valueOf(sessionuser.getQyid()));		
		networkService.save(dt);
		return restring;
	}
	
	/**
	 * 修改跳转
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateMenuForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("model", networkService.get(id));
		model.addAttribute("action", "update");
		return "system/aqwl/form";
	}

	/**
	 * 修改
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(@Valid @ModelAttribute @RequestBody SafetyNetwork dt,Model model) {
		String restring="success";
		networkService.save(dt);
		return restring;
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "delete/{ids}", method = RequestMethod.GET)
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String restring="success";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			networkService.delete(Long.parseLong(arrids[i]));
		}
		return restring;
	}
	
}
