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

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.mbgl.entity.Target_SafetyDuty;
import com.cczu.model.mbgl.service.TargetAqzzService;
import com.cczu.model.service.BisGzxxService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 目标管理-安全职责controller
 * 
 * @author jason
 * 
 */

@Controller
@RequestMapping("target/aqzz")
public class PageTargetAqzzController extends BaseController {

	@Autowired
	private TargetAqzzService targetAqzzService;
	@Autowired
	private BisGzxxService bisGzxxService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model,HttpServletRequest request) {
		String ajqyid = request.getParameter("qyid");
		if(StringUtils.isEmpty(ajqyid)){
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(UserUtil.getCurrentShiroUser().getQyid());
			if (be.getIsbloc() != null && be.getIsbloc() == 1) {
				model.addAttribute("usertype", "isbloc");
			}
		}else{
			model.addAttribute("ajqyid", ajqyid);
		}
		return "targetmanger/safetyduty/aqzz/index";

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
			ShiroUser user =UserUtil.getCurrentShiroUser();
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(user.getQyid());
			if (be.getIsbloc() != null && be.getIsbloc() == 1) {// 集团公司
				map.put("fid", user.getQyid());
				map.put("qyname", request.getParameter("target_aqzz_qyname"));
			} else {
				map.put("qyid", user.getQyid());
			}
		}else{
			map.put("qyid", ajqyid);
		}
		map.put("name", request.getParameter("target_aqzz_gwname"));
		return targetAqzzService.dataGrid(map);
	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("target:aqzz:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("username", UserUtil.getCurrentShiroUser().getName());
		return "targetmanger/safetyduty/aqzz/form";
	}

	/**
	 * 添加目标信息
	 * 
	 * @param request
	 * model
	 */
	@RequiresPermissions("target:aqzz:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(Target_SafetyDuty target, Model model) {
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
			targetAqzzService.addInfo(target);
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
	@RequiresPermissions("target:aqzz:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		Target_SafetyDuty target = targetAqzzService.findInfoById(id);
		model.addAttribute("target", target);
		model.addAttribute("action", "update");
		return "targetmanger/safetyduty/aqzz/form";
	}

	/**
	 * 修改目标信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("target:aqzz:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(Target_SafetyDuty target, Model model) {
		String datasuccess = "success";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if("1".equals(sessionuser.getUsertype())){
			target.setID1(sessionuser.getQyid());
		}
		Timestamp t = DateUtils.getSysTimestamp();
		target.setS2(t);
		targetAqzzService.updateInfo(target);
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
	@RequiresPermissions("target:aqzz:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			targetAqzzService.deleteInfo(Long.parseLong(arrids[i]));
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
		Target_SafetyDuty target = targetAqzzService.findInfoById(id);
		
		model.addAttribute("jobname", bisGzxxService.findById(target.getJobid()).getM1());
		model.addAttribute("entity", target);
		return "targetmanger/safetyduty/aqzz/view";
	}


	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("target:aqzz:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("targetname", request.getParameter("target_aqzz_mbname"));
		map.put("m1", request.getParameter("target_aqzz_m1"));	
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		targetAqzzService.exportExcel(response, map);

	}

	/**
	 * 显示所有列
	 * @param id
	 * model
	 */
	@RequiresPermissions("target:aqzz:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) { 
		model.addAttribute("url", "target/safetyduty/aqzz/export");
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
		return JsonMapper.getInstance().toJson(targetAqzzService.getTargetDisIdJson(map));
	}

}
