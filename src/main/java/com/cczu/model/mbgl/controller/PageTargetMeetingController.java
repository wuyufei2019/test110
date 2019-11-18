package com.cczu.model.mbgl.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.mbgl.entity.Target_SafetyMeeting;
import com.cczu.model.mbgl.service.TargetMeetingService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 会议管理-安全文化-安全会有controller
 * @author jason
 */

@Controller
@RequestMapping("target/aqhy")
public class PageTargetMeetingController extends BaseController {

	@Autowired
	private TargetMeetingService targetMeetingService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;

	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model, HttpServletRequest request) {
		String qyid = request.getParameter("qyid");
		if(org.apache.commons.lang3.StringUtils.isNotEmpty(qyid))
			model.addAttribute("qyid", request.getParameter("qyid"));
		else {
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(UserUtil.getCurrentShiroUser().getQyid());
			if (be.getIsbloc() != null && be.getIsbloc() == 1) {
				model.addAttribute("usertype", "isbloc");
			}
		}
		return "targetmanger/safetyculture/aqhy/index";
	}

	/**
	 * list页面 企业端 
	 * @param request
	 */
	@RequestMapping(value = "qylist")
	@ResponseBody
	public Map<String, Object> getQyData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		ShiroUser user =UserUtil.getCurrentShiroUser();
		//安监端条件
		String qyid = request.getParameter("qyid");
		if(org.apache.commons.lang3.StringUtils.isNotEmpty(qyid))
			map.put("qyid", qyid);
		else {
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(user.getQyid());
			if (be.getIsbloc() != null && be.getIsbloc() == 1) {// 集团公司
				map.put("fid", user.getQyid());
				map.put("qyname", request.getParameter("list_qyname"));
			} else {
				map.put("qyid", user.getQyid());
			}
		}
		map.put("theme", request.getParameter("view_theme"));
		map.put("type", request.getParameter("view_type"));
		return targetMeetingService.dataGrid(map);
	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("target:aqhy:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		User user=UserUtil.getCurrentUser();
		model.addAttribute("id2", user.getDepartmen());
		model.addAttribute("username", user.getName());
		model.addAttribute("action", "create");
		return "targetmanger/safetyculture/aqhy/form";
	}

	/**
	 * 添加会议信息
	 * 
	 * @param request
	 * model
	 */
	@RequiresPermissions("target:aqhy:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(Target_SafetyMeeting target, Model model) {
		String datasuccess = "success";
		try {
			targetMeetingService.addInfo(target);
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
	@RequiresPermissions("target:aqhy:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		Target_SafetyMeeting target = targetMeetingService.findInfoById(id);
		model.addAttribute("target", target);
		model.addAttribute("username", UserUtil.getCurrentShiroUser().getName());
		model.addAttribute("action", "update");
		return "targetmanger/safetyculture/aqhy/form";
	}
	/**
	 * 修改页面跳转
	 * 
	 * @param id
	 *            ,model
	 */
	@RequiresPermissions("target:aqhy:update")
	@RequestMapping(value = "addcont/{id}", method = RequestMethod.GET)
	public String addCont(@PathVariable("id") Long id, Model model) {
		Target_SafetyMeeting target = targetMeetingService.findInfoById(id);
		model.addAttribute("target", target);
		model.addAttribute("flg", "addcont");
		model.addAttribute("username", UserUtil.getCurrentShiroUser().getName());
		model.addAttribute("action", "update");
		return "targetmanger/safetyculture/aqhy/form";
	}

	/**
	 * 修改会议信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("target:aqhy:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(Target_SafetyMeeting target,HttpServletRequest request) {
		String datasuccess = "success";
		String flg=request.getParameter("flg");
		if(StringUtils.isNoneBlank(flg)&&flg.equals("addcont")){
			if(!"3".equals(target.getState())){
				target.setState("3");
			}
		}
		Timestamp t = DateUtils.getSysTimestamp();
		target.setS2(t);
		targetMeetingService.updateInfo(target);
		// 返回结果
		return datasuccess;
	}
	/**
	 * 推迟会议
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("target:aqhy:update")
	@RequestMapping(value = "delay/{id}")
	@ResponseBody
	public String updateDelay(@PathVariable("id")long id,@RequestParam("time")String time,@RequestParam("reason")String  reason) {
		String datasuccess = "success";
		targetMeetingService.delayMeeting(id,time,reason);
		// 返回结果
		return datasuccess;
	}
	/**
	 * 事项反馈
	 * @param request
	 */
	@RequiresPermissions("target:aqhy:update")
	@RequestMapping(value = "feedback/{id}")
	@ResponseBody
	public String updateFeedback(@PathVariable("id")long id,@RequestParam("feedback")String  feedback) {
		String datasuccess = "success";
		targetMeetingService.feedbackMeeting(id,feedback);
		// 返回结果
		return datasuccess;
	}
	
	/**
	 * 删除会议信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("target:aqhy:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			targetMeetingService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * 
	 * @param id
	 *            ,model
	 */
	@RequiresPermissions("target:aqhy:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Target_SafetyMeeting target = targetMeetingService.findInfoById(id);
		model.addAttribute("target", target);
		return "targetmanger/safetyculture/aqhy/view";
	}


	
}
