package com.cczu.model.mbgl.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
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
import com.cczu.model.mbgl.entity.Target_SafetyAdvice;
import com.cczu.model.mbgl.service.TargetHlhjyService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 目标管理-安全文化-建言献策controller
 * 
 * @author jason
 */

@Controller
@RequestMapping("target/hlhjy")
public class PageTargetHlhjyController extends BaseController {

	@Autowired
	private TargetHlhjyService targetJyxcService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model, HttpServletRequest request) {
		// 获取用户角色
		List<Role> list = roleService.findRoleById(UserUtil.getCurrentShiroUser().getId());
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				// 判断用户角色，如果包含有企业用户角色，则转到企业用户显示的界面
				if (list.get(i).getRoleCode().equals("authorizedepartment")|| list.get(i).getRoleCode().equals("companyadmin")){
					model.addAttribute("authorize", "authorizedepartment");
					break;
				}
			}
		}
		String qyid = request.getParameter("qyid");
		if(StringUtils.isNotEmpty(qyid))
			model.addAttribute("qyid", request.getParameter("qyid"));
		else {
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(UserUtil.getCurrentShiroUser().getQyid());
			if (be.getIsbloc() != null && be.getIsbloc() == 1) {
				model.addAttribute("usertype", "isbloc");
			}
		}
		return "targetmanger/safetyculture/hlhjy/index";
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
		ShiroUser user =UserUtil.getCurrentShiroUser();
		//安监端条件
		String qyid = request.getParameter("qyid");
		if(StringUtils.isNotEmpty(qyid))
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
		map.put("name", request.getParameter("target_hlhjy_name"));
		map.put("theme", request.getParameter("target_hlhjy_theme"));
		return targetJyxcService.dataGrid(map);
	}

	
	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("target:hlhjy:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("username", UserUtil.getCurrentShiroUser().getName());
		return "targetmanger/safetyculture/hlhjy/form";
	}

	/**
	 * 添加目标信息
	 * 
	 * @param request
	 *            model
	 */
	@RequiresPermissions("target:hlhjy:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(Target_SafetyAdvice target, Model model) {
		String datasuccess = "success";
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		if ("1".equals(sessionuser.getUsertype())) {
			target.setID1(sessionuser.getQyid());
		}
		target.setID2(sessionuser.getId());
		Timestamp t = DateUtils.getSysTimestamp();
		target.setS1(t);
		target.setS2(t);
		target.setS3(0);
		try {
			targetJyxcService.addInfo(target);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			datasuccess = "failed";
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
	@RequiresPermissions("target:hlhjy:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		Target_SafetyAdvice target = targetJyxcService.findInfoById(id);
		model.addAttribute("target", target);
		model.addAttribute("action", "update");
		model.addAttribute("username", UserUtil.getCurrentShiroUser().getName());
		return "targetmanger/safetyculture/hlhjy/form";
	}

	/**
	 * 修改目标信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("target:hlhjy:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(Target_SafetyAdvice target, Model model) {
		String datasuccess = "success";
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		if ("1".equals(sessionuser.getUsertype())) {
			target.setID1(sessionuser.getQyid());
		}
		Timestamp t = DateUtils.getSysTimestamp();
		target.setS2(t);
		targetJyxcService.updateInfo(target);
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
	@RequiresPermissions("target:hlhjy:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			targetJyxcService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * 
	 * @param id
	 *            ,model
	 */
	@RequiresPermissions("target:hlhjy:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Target_SafetyAdvice target = targetJyxcService.findInfoById(id);
		model.addAttribute("target", target);
		return "targetmanger/safetyculture/hlhjy/view";
	}

	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("target:hlhjy:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("targetname", request.getParameter("target_hlhjy_mbname"));
		map.put("m1", request.getParameter("target_hlhjy_m1"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		targetJyxcService.exportExcel(response, map);

	}

	/**
	 * 显示所有列
	 * 
	 * @param id
	 *            model
	 */
	@RequiresPermissions("target:hlhjy:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url", "target/safetyculture/hlhjy/export");
		return "common/formexcel";
	}

}
