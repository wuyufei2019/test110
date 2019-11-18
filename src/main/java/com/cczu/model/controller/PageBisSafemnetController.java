package com.cczu.model.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 安全企业网络controller
 * 
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("bis/safemnet")
public class PageBisSafemnetController extends BaseController {

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
	public String index(Model model) {
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		if (be != null&& be.getM1() != null) {
			return "model/bis/safemnet/index";
		} else {
			return "../error/001";
		}
	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("bis:safemnet:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		List<Object> list = new ArrayList<Object>();
		Map<String, Object> map = getPageMap(request);
		// map.put("qyname", request.getParameter("check_company_name"));
		// map.put("datestart",
		// request.getParameter("check_company_starttime"));
		// map.put("dateend", request.getParameter("check_company_endtime"));
		map.put("rows", list);
		map.put("total", 0);
		return map;
	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("bis:safemnet:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "model/bis/safemnet/form";
	}

	/**
	 * 添加作业班次
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("bis:safemnet:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(HttpServletRequest request, Model model) {
		String datasuccess = "保存成功！";
		System.out.println(request.getParameter("M1"));
		// ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		// System.out.println("ShiroUser:"+sessionuser.getId());
		// 返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("bis:safemnet:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		// 查询选择的作业班次
		// 返回页面
		model.addAttribute("action", "update");
		return "model/bis/safemnet/form";
	}

	/**
	 * 修改作业班次
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("bis:safemnet:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(HttpServletRequest request, Model model) {
		String datasuccess = "更新成功";
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除作业班次
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("bis:safemnet:delete")
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("bis:safemnet:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		// 查询选择的产品信息

		// 返回页面
		model.addAttribute("action", "view");
		return "model/bis/safemnet/form";
	}

}
