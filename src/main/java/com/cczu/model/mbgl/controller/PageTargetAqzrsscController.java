package com.cczu.model.mbgl.controller;

import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cczu.model.entity.BIS_EmployeeEntity;
import com.cczu.model.service.impl.BisYgxxServiceImpl;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.mbgl.entity.Target_SafetyDutyAgreementRec;
import com.cczu.model.mbgl.service.TargetAqzrsscService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 目标管理-安全责任书上传controller
 * 
 * @author jason
 * 
 */

@Controller
@RequestMapping("target/aqzrssc")
public class PageTargetAqzrsscController extends BaseController {

	@Autowired
	private TargetAqzrsscService targetAqzrsscService;
	@Autowired
	private BisYgxxServiceImpl bisygxxservice;
	

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model,HttpServletRequest request) {
		String ajqyid = request.getParameter("qyid");
		if(StringUtils.isEmpty(ajqyid)){
			model.addAttribute("uid",UserUtil.getCurrentShiroUser().getId());
		}else{
			model.addAttribute("ajqyid", ajqyid);
		}
		return "targetmanger/safetyduty/aqzrssc/index";
	}

	/**
	 * list页面 企业端 
	 * 
	 * @param request
	 */
	@RequestMapping(value = "qylist")
	@ResponseBody
	public Map<String, Object> getQyData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		String ajqyid = request.getParameter("ajqyid");
		if(StringUtils.isEmpty(ajqyid)){
			User user= UserUtil.getCurrentUser();
			map.put("qyid", user.getId2());	
			Subject subject = SecurityUtils.getSubject();
			if(!subject.hasRole("companyadmin")){
				map.put("deptid", user.getDepartmen());
				map.put("uid", user.getId());
			}
		}else{
			map.put("qyid", ajqyid);
		}
		map.put("year", request.getParameter("target_aqzrssc_year"));
		return targetAqzrsscService.dataGrid(map);
	}
	/**
	 * list页面 admin端
	 * 
	 * @param request
	 */
	@RequestMapping(value = "adminlist")
	@ResponseBody
	public Map<String, Object> getAdminData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("target_aqzrssc_qyname"));
		map.put("name", request.getParameter("target_aqzrssc_gwname"));
		return targetAqzrsscService.dataGrid(map);
	}

	/**
	 * 添加页面跳转
	 * 
	 * @param id
	 * model
	 */
	@RequiresPermissions("target:aqzrssc:add")
	@RequestMapping(value = "create/{aid}/{uid}", method = RequestMethod.GET)
	public String create(@PathVariable("aid") Long aid,@PathVariable("uid") Long uid, Model model) {
		model.addAttribute("id1", aid);
		model.addAttribute("action", "create");
		BIS_EmployeeEntity em  = bisygxxservice.findInfoByID(uid);
		if (em != null && !"".equals(em)) {
			model.addAttribute("jobname", em.getM4());
			model.addAttribute("phone", em.getM9());
			model.addAttribute("name", em.getM1());
		}
		model.addAttribute("id3", uid);
		return "targetmanger/safetyduty/aqzrssc/form";
	}
	/**
	 * 添加信息
	 * @param model
	 * @param target
	 */
	@RequiresPermissions("target:aqzrssc:add")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public String create(Target_SafetyDutyAgreementRec target,Model model) {
		String datasuccess = "success";
		targetAqzrsscService.addInfo(target);
		// 返回结果
		return datasuccess;
	}
	
	
	/**
	 * 修改页面跳转
	 * 
	 * @param id
	 */
	@RequiresPermissions("target:aqzrssc:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		Target_SafetyDutyAgreementRec target = targetAqzrsscService.findInfoById(id);
		model.addAttribute("target", target);
		model.addAttribute("action", "update");
		return "targetmanger/safetyduty/aqzrssc/form";
	}

	/**
	 * 修改目标信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("target:aqzrssc:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(Target_SafetyDutyAgreementRec target,HttpServletRequest request) {
		String datasuccess = "success";
		targetAqzrsscService.updateInfo(target);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除目标信息
	 * 
	 * @param ids
	 * @throws ParseException
	 */
	@RequiresPermissions("target:aqzrssc:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			targetAqzrsscService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * 
	 * @param id
	 *  @param model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Target_SafetyDutyAgreementRec target = targetAqzrsscService.findInfoById(id);
		model.addAttribute("target", target);
		return "targetmanger/safetyduty/aqzrssc/view";
	}

}
