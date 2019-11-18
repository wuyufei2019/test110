package com.cczu.model.mbgl.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.mbgl.entity.Target_SafetyPromiseAgreement;
import com.cczu.model.mbgl.service.TargetAqcnsService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 目标管理-安全承诺书controller
 * 
 * @author jason
 * 
 */

@Controller
@RequestMapping("target/aqcns")
public class PageTargetAqcnsController extends BaseController {

	@Autowired
	private TargetAqcnsService targetAqcnsService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model,HttpServletRequest request)  {
		model.addAttribute("qyid", request.getParameter("qyid"));
		return "targetmanger/safetyduty/aqcns/index";
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
		String qyid = request.getParameter("qyid");
		if(StringUtils.isEmpty(qyid)){
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		}else{
			map.put("qyid", qyid);
		}
		map.put("post", request.getParameter("target_aqcns_post"));
		return targetAqcnsService.dataGrid(map);
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
		map.put("qyname", request.getParameter("target_aqcns_qyname"));
		map.put("post", request.getParameter("target_aqcns_post"));
		return targetAqcnsService.dataGrid(map);
	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("target:aqcns:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		User user= UserUtil.getCurrentUser();
		model.addAttribute("username", user.getName());
		model.addAttribute("phone", user.getPhone());
		return "targetmanger/safetyduty/aqcns/form";
	}

	/**
	 * 添加目标信息
	 * 
	 * @param request
	 * model
	 */
	@RequiresPermissions("target:aqcns:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(Target_SafetyPromiseAgreement target, Model model) {
		String datasuccess = "success";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if("1".equals(sessionuser.getUsertype())){
			target.setID1(sessionuser.getQyid());
		}
		Timestamp t = DateUtils.getSysTimestamp();
		target.setS1(t);
		target.setS2(t);
		target.setS3(0);
		try {
			targetAqcnsService.addInfo(target);
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
	@RequiresPermissions("target:aqcns:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		Target_SafetyPromiseAgreement target = targetAqcnsService.findInfoById(id);
		model.addAttribute("target", target);
		model.addAttribute("action", "update");
		return "targetmanger/safetyduty/aqcns/form";
	}

	/**
	 * 修改目标信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("target:aqcns:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(Target_SafetyPromiseAgreement target, Model model) {
		String datasuccess = "success";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if("1".equals(sessionuser.getUsertype())){
			target.setID1(sessionuser.getQyid());
		}
		Timestamp t = DateUtils.getSysTimestamp();
		target.setS2(t);
		targetAqcnsService.updateInfo(target);
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
	@RequiresPermissions("target:aqcns:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			targetAqcnsService.deleteInfo(Long.parseLong(arrids[i]));
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
		Target_SafetyPromiseAgreement target = targetAqcnsService.findInfoById(id);
		model.addAttribute("target", target);
		return "targetmanger/safetyduty/aqcns/view";
	}


	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("target:aqcns:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("targetname", request.getParameter("target_aqcns_mbname"));
		map.put("m1", request.getParameter("target_aqcns_m1"));	
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		targetAqcnsService.exportExcel(response, map);

	}

	/**
	 * 显示所有列
	 * @param id
	 * model
	 */
	@RequiresPermissions("target:aqcns:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) { 
		model.addAttribute("url", "target/safetyduty/aqcns/export");
		return "common/formexcel";
	}
	
	
	/**
	 * 显示各个企业的指标分配 的id和名称
	 * @param id
	 * model
	 */
	@RequestMapping(value = "idjson")
	@ResponseBody
	public String getTargetIDAndName(HttpServletRequest request) {
		String tid= request.getParameter("tid");
		Map<String, Object> map = getAuthorityMap();
		if(StringUtils.isNoneBlank(tid))
			map.put("tid", tid);
		return JsonMapper.getInstance().toJson(targetAqcnsService.getTargetDisIdJson(map));
	}

}
