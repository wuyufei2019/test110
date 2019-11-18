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

import com.cczu.model.mbgl.entity.Target_Basic;
import com.cczu.model.mbgl.service.TargetZbszService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 目标管理-目标设置controller
 * 
 * @author jason
 * 
 */

@Controller
@RequestMapping("target/zbsz")
public class PageTargetZbszController extends BaseController {

	@Autowired
	private TargetZbszService targetMbszService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model,HttpServletRequest request)  {
		model.addAttribute("qyid", request.getParameter("qyid"));
		return "targetmanger/zbsz/index";
	}

	/**
	 * list页面 企业端
	 * @param request
	 */
	@RequestMapping(value = "qylist")
	@ResponseBody
	public Map<String, Object> getQyData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("m1", request.getParameter("target_zbsz_m1"));
		String qyid = request.getParameter("qyid");
		if(StringUtils.isEmpty(qyid)){
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		}else{
			map.put("qyid", qyid);
		}
		return targetMbszService.dataGrid(map);
	}
	
	/**
	 * list页面 admin端
	 * @param request
	 */
	@RequestMapping(value = "adminlist")
	@ResponseBody
	public Map<String, Object> getAdminData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("m1", request.getParameter("target_zbsz_m1"));
		map.put("qyname", request.getParameter("target_zbsz_qynm"));
		return targetMbszService.dataGrid(map);
	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("target:zbsz:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "targetmanger/zbsz/form";
	}

	/**
	 * 添加目标信息
	 * 
	 * @param request
	 *            model
	 */
	@RequiresPermissions("target:zbsz:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(Target_Basic target, Model model) {
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
			targetMbszService.addInfo(target);
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
	@RequiresPermissions("target:zbsz:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		Target_Basic target = targetMbszService.findInfoById(id);
		model.addAttribute("target", target);
		model.addAttribute("action", "update");
		return "targetmanger/zbsz/form";
	}

	/**
	 * 修改目标信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("target:zbsz:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(Target_Basic target, Model model) {
		String datasuccess = "success";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if("1".equals(sessionuser.getUsertype())){
			target.setID1(sessionuser.getQyid());
		}
		Timestamp t = DateUtils.getSysTimestamp();
		target.setS2(t);
		targetMbszService.updateInfo(target);
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
	@RequiresPermissions("target:zbsz:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			targetMbszService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Target_Basic target = targetMbszService.findInfoById(id);
		model.addAttribute("target", target);
		return "targetmanger/zbsz/view";
	}

	/**
	 * 导入
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	// @RequiresPermissions("target:zbsz:exin")
	// @RequestMapping(value = "exin")
	// @ResponseBody
	// public String expiExcel(HttpServletResponse response,
	// HttpServletRequest request) {
	// Map<String, Object> map = uploadExcel(request, response);
	// Map<String, Object> resultmap = new HashMap<String, Object>();
	// if (map.get("content") != null) {
	// map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
	// resultmap = targetMbszService.exinExcel(map);
	// } else {
	// resultmap.put("returncode", -2);
	// }
	// return JsonMapper.toJsonString(resultmap);
	// }

	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("target:zbsz:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = getAuthorityMap();
		map.put("m1", request.getParameter("target_zbsz_m1"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		targetMbszService.exportExcel(response, map);

	}

	/**
	 * 显示所有列
	 * 
	 * @param id
	 *            model
	 */
	@RequiresPermissions("target:zbsz:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url", "target/zbsz/export");
		return "common/formexcel";
	}

	/**
	 * 显示各个企业的指标库 的id和名称
	 * @param id
	 * model
	 */
	@RequestMapping(value = "idjson")
	@ResponseBody
	public String getTargetIDAndName(HttpServletRequest request) {
		String year=request.getParameter("year");
		String deptid=request.getParameter("deptid");
		ShiroUser user = UserUtil.getCurrentShiroUser();
		Map<String, Object> map = new HashMap<>();
		map.put("qyid", user.getQyid());
		if(StringUtils.isNoneBlank(year))
			map.put("year", year);
		if(StringUtils.isNoneBlank(deptid))
			map.put("deptid", deptid);
		return JsonMapper.getInstance().toJson(targetMbszService.getTargetIDAndName(map));
	}

}
