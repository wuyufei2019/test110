package com.cczu.model.mbgl.controller;

import java.sql.Timestamp;
import java.text.ParseException;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.mbgl.entity.Target_SafetyAdvice;
import com.cczu.model.mbgl.entity.Target_SafetyAdviceReview;
import com.cczu.model.mbgl.service.TargetHlhjyReviewService;
import com.cczu.model.mbgl.service.TargetHlhjyService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 目标管理-安全文化-建言献策-审核controller
 * @author jason
 */

@Controller
@RequestMapping("target/hlhjysh")
public class PageTargetHlhjyReviewController extends BaseController {
	@Autowired
	private TargetHlhjyService targetJyxcService;
	@Autowired
	private TargetHlhjyReviewService targetHlhjyReviewService;

	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequiresPermissions("target:hlhjysh:view")
	@RequestMapping(value = "index")
	public String index(Model model) {
		return "targetmanger/safetyculture/hlhjysh/index";
	}


	/**
	 * 添加页面跳转
	 * id 合理化建议id
	 * @param model
	 */
	@RequiresPermissions("target:hlhjysh:add")
	@RequestMapping(value = "create/{id}", method = RequestMethod.GET)
	public String create(@PathVariable("id") Long id,Model model) {
		Target_SafetyAdvice t = targetJyxcService.findInfoById(id);
		model.addAttribute("t", t);
		model.addAttribute("action", "create");
		model.addAttribute("username", UserUtil.getCurrentShiroUser().getName());
		return "targetmanger/safetyculture/hlhjysh/form";
	}

	/**
	 * 添加信息
	 * 
	 * @param request
	 * model
	 */
	@RequiresPermissions("target:hlhjysh:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(Target_SafetyAdviceReview target, Model model) {
		String datasuccess = "success";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		target.setID2(sessionuser.getId());
		Timestamp t = DateUtils.getSysTimestamp();
		target.setS1(t);
		target.setS2(t);
		target.setS3(0);
		try {
			targetHlhjyReviewService.addInfo(target);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			datasuccess="failed";
		}
		// 返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id
	 *            ,model
	 */
	@RequiresPermissions("target:hlhjysh:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		Target_SafetyAdvice t = targetJyxcService.findInfoById(id);
		model.addAttribute("t", t);
		Target_SafetyAdviceReview target = targetHlhjyReviewService.findInfoById1(id);
		model.addAttribute("target", target);
		model.addAttribute("action", "update");
		model.addAttribute("username", UserUtil.getCurrentShiroUser().getName());
		return "targetmanger/safetyculture/hlhjysh/form";
	}

	/**
	 * 修改目标信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("target:hlhjysh:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(Target_SafetyAdviceReview target, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		target.setS2(t);
		targetHlhjyReviewService.updateInfo(target);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除目标信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("target:hlhjysh:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			targetHlhjyReviewService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * 
	 * @param id
	 *  ,model
	 */
	@RequiresPermissions("target:hlhjysh:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Target_SafetyAdvice t = targetJyxcService.findInfoById(id);
		model.addAttribute("t", t);
		Target_SafetyAdviceReview target = targetHlhjyReviewService.findInfoById1(id);
		model.addAttribute("target", target);
		return "targetmanger/safetyculture/hlhjysh/view";
	}
}
