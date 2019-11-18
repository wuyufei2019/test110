package com.cczu.model.mbgl.controller;

import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.mbgl.entity.Target_SafetyDutyAgreement;
import com.cczu.model.mbgl.service.TargetAqzrsService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 目标管理-安全责任书controller
 * 
 * @author jason
 * 
 */

@Controller
@RequestMapping("target/aqzrs")
public class PageTargetAqzrsController extends BaseController {

	@Autowired
	private TargetAqzrsService targetAqzrsService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model,HttpServletRequest request)  {
		model.addAttribute("qyid", request.getParameter("qyid"));
		return "targetmanger/safetyduty/aqzrs/index";

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
		map.put("year", request.getParameter("target_aqzrs_year"));
		String qyid = request.getParameter("qyid");
		if(StringUtils.isEmpty(qyid)){
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		}else{
			map.put("qyid", qyid);
		}	
		map.put("name", request.getParameter("target_aqzrs_gwname"));
		return targetAqzrsService.dataGrid(map);
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
		map.put("qyname", request.getParameter("target_aqzrs_qyname"));
		map.put("name", request.getParameter("target_aqzrs_gwname"));
		return targetAqzrsService.dataGrid(map);
	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("target:aqzrs:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "targetmanger/safetyduty/aqzrs/form";
	}

	/**
	 * 添加目标信息
	 * 
	 * @param request
	 * model
	 */
	@RequiresPermissions("target:aqzrs:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(Target_SafetyDutyAgreement target,HttpServletRequest request) {
		String datasuccess = "success";
		String[] deps=request.getParameterValues("departments");
		try {
			targetAqzrsService.addInfo(target,deps);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			datasuccess="failed";
		}
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id
	 *            ,model
	 */
	@RequiresPermissions("target:aqzrs:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		Target_SafetyDutyAgreement target = targetAqzrsService.findInfoById(id);
		model.addAttribute("target", target);
		model.addAttribute("action", "update");
		return "targetmanger/safetyduty/aqzrs/form";
	}

	/**
	 * 修改目标信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("target:aqzrs:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(Target_SafetyDutyAgreement target,HttpServletRequest request) {
		String datasuccess = "success";
		String deleteid= request.getParameter("deleteid");
		String insertid= request.getParameter("insertid");
		targetAqzrsService.updateInfo(target,deleteid,insertid);
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
	@RequiresPermissions("target:aqzrs:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			targetAqzrsService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * 
	 * @param id
	 *            ,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Target_SafetyDutyAgreement target = targetAqzrsService.findInfoById(id);
		model.addAttribute("target", target);
		return "targetmanger/safetyduty/aqzrs/view";
	}

}
