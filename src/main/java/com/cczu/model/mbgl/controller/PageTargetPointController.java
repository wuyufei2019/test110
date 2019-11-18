package com.cczu.model.mbgl.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.mbgl.entity.Target_Points;
import com.cczu.model.mbgl.service.TargetPointService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 目标管理-积分管理controller
 * 
 * @author jason
 * 
 */

@Controller
@RequestMapping("target/jfgl")
public class PageTargetPointController extends BaseController {

	@Autowired
	private TargetPointService targetPointService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model, HttpServletRequest request)  {
		String qyid = request.getParameter("qyid");
		if(StringUtils.isNotEmpty(qyid))
			model.addAttribute("qyid", request.getParameter("qyid"));
		else {
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(UserUtil.getCurrentShiroUser().getQyid());
			if (be.getIsbloc() != null && be.getIsbloc() == 1) {
				model.addAttribute("usertype", "isbloc");
			}
		}
		return "targetmanger/safetyculture/jfgl/index";
	}
	
	/**
	 * list页面 主界面
	 * @param request
	 */
	@RequestMapping(value = "listoverview")
	@ResponseBody
	public Map<String, Object> getQyDataOverview(HttpServletRequest request) {
		Map<String, Object> mapData = getPageMap(request);
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		Subject subject = SecurityUtils.getSubject();
		//安监端条件
		String qyid = request.getParameter("qyid");
		if(StringUtils.isNotEmpty(qyid))
			mapData.put("qyid", qyid);
		else {
			if (!subject.isPermitted("target:jfgl:viewall")) {//是否拥有viewall权限
				mapData.put("uid", sessionuser.getId());
			} else {
				BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
				if (be.getIsbloc() != null && be.getIsbloc() == 1) {// 集团公司
					mapData.put("fid", sessionuser.getQyid());
					mapData.put("qyname", request.getParameter("list_qyname"));
				} else {
					mapData.put("qyid", sessionuser.getQyid());
				}
			}
		}
		mapData.put("year",request.getParameter("page_year"));
		return targetPointService.dataGridOverview(mapData);
	}

	/**
	 * list页面 详情
	 * @param request
	 */
	@RequestMapping(value = "qylist")
	@ResponseBody
	public Map<String, Object> getQyData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		if (be.getIsbloc() != null && be.getIsbloc() == 1) {// 集团公司
			map.put("fid", sessionuser.getQyid());
			map.put("qyname", request.getParameter("target_jfgl_qyname"));
		} else {
			map.put("qyid", sessionuser.getQyid());
		}
		//安监端条件
		String qyid = request.getParameter("qyid");
		if(StringUtils.isNotEmpty(qyid))
			map.put("qyid", qyid);
		map.put("uid", request.getParameter("uid"));
		map.put("year", request.getParameter("year"));
		return targetPointService.dataGrid(map);
	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("target:jfgl:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "targetmanger/safetyculture/jfgl/form";
	}

	/**
	 * 添加目标信息
	 * 
	 * @param request
	 *            model
	 */
	@RequiresPermissions("target:jfgl:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(Target_Points target, Model model) {
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
			targetPointService.addInfo(target);
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
	@RequiresPermissions("target:jfgl:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		Target_Points target = targetPointService.findInfoById(id);
		model.addAttribute("target", target);
		model.addAttribute("action", "update");
		return "targetmanger/safetyculture/jfgl/form";
	}

	/**
	 * 修改目标信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("target:jfgl:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(Target_Points target, Model model) {
		String datasuccess = "success";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if("1".equals(sessionuser.getUsertype())){
			target.setID1(sessionuser.getQyid());
		}
		Timestamp t = DateUtils.getSysTimestamp();
		target.setS2(t);
		targetPointService.updateInfo(target);
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
	@RequiresPermissions("target:jfgl:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			targetPointService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * 
	 * @param id
	 *            ,model
	 */
	@RequiresPermissions("target:jfgl:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Target_Points target = targetPointService.findInfoById(id);
		model.addAttribute("target", target);
		return "targetmanger/safetyculture/jfgl/view";
	}

	/**
	 * 导入
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	// @RequiresPermissions("target:jfgl:exin")
	// @RequestMapping(value = "exin")
	// @ResponseBody
	// public String expiExcel(HttpServletResponse response,
	// HttpServletRequest request) {
	// Map<String, Object> map = uploadExcel(request, response);
	// Map<String, Object> resultmap = new HashMap<String, Object>();
	// if (map.get("content") != null) {
	// map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
	// resultmap = targetPointService.exinExcel(map);
	// } else {
	// resultmap.put("returncode", -2);
	// }
	// return JsonMapper.toJsonString(resultmap);
	// }

	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("target:jfgl:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = getAuthorityMap();
		map.put("m1", request.getParameter("target_jfgl_m1"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		targetPointService.exportExcel(response, map);

	}

	/**
	 * 显示所有列
	 * 
	 * @param id
	 *            model
	 */
	@RequiresPermissions("target:jfgl:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url", "target/jfgl/export");
		return "common/formexcel";
	}

}
