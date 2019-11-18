package com.cczu.model.mbgl.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
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
import com.cczu.model.mbgl.entity.Target_Examine;
import com.cczu.model.mbgl.service.TargetZbkhService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 目标管理-指标考核controller
 * 
 * @author jason
 * 
 */

@Controller
@RequestMapping("target/zbkh")
public class PageTargetZbkhController extends BaseController {

	@Autowired
	private TargetZbkhService targetZbkhService;
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
		return "targetmanger/zbkh/index";

	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequestMapping(value = "qylist")
	@ResponseBody
	public Map<String, Object> getQyData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		String ajqyid = request.getParameter("ajqyid");
		if(StringUtils.isEmpty(ajqyid)){
			User user =UserUtil.getCurrentUser();
			Subject subject = SecurityUtils.getSubject();
			if(!subject.isPermitted("target:zbkh:viewall")){//是否拥有viewall权限
				if(user.getDepartmen()!=null)
					map.put("deptid", user.getDepartmen());
			}
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(user.getId2());
			if (be.getIsbloc() != null && be.getIsbloc() == 1) {//集团公司
				map.put("fid", user.getId2());
				map.put("qyname", request.getParameter("target_zbkh_qyname"));		
			}else{
				map.put("qyid", user.getId2());
			}
		}else{
			map.put("qyid", ajqyid);
		}
		map.put("deptname", request.getParameter("target_zbkh_deptname"));		

		map.put("year", request.getParameter("target_mbkh_year"));		
		map.put("deptname", request.getParameter("target_zbkh_deptname"));		
		return targetZbkhService.dataGrid(map);
	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("target:zbkh:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "targetmanger/zbkh/form";
	}
	
	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("target:zbkh:add")
	@RequestMapping(value = "init", method = RequestMethod.POST)
	@ResponseBody
	public String init(HttpServletRequest request) {
		String datasuccess = "success";
		try {
			targetZbkhService.init(request.getParameter("year"), UserUtil.getCurrentShiroUser().getQyid());
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="failed";
		}
		return datasuccess;
	}

	/**
	 * 添加目标信息
	 * 
	 * @param request
	 * model
	 */
	@RequiresPermissions("target:zbkh:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(Target_Examine target, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		target.setS1(t);
		target.setS2(t);
		target.setS3(0);
		try {
			targetZbkhService.addInfo(target);
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
	@RequiresPermissions("target:zbkh:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		Target_Examine target = targetZbkhService.findInfoById(id);
		model.addAttribute("target", target);
		model.addAttribute("action", "update");
		model.addAttribute("username", UserUtil.getCurrentShiroUser().getName());
		return "targetmanger/zbkh/form";
	}

	/**
	 * 修改目标信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("target:zbkh:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(Target_Examine target, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		target.setS2(t);
		targetZbkhService.updateInfo(target);
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
	@RequiresPermissions("target:zbkh:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			targetZbkhService.deleteInfo(Long.parseLong(arrids[i]));
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
		Target_Examine target = targetZbkhService.findInfoById(id);
		model.addAttribute("target", target);
		return "targetmanger/zbkh/view";
	}


	/**
	 * 导入
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
//	@RequiresPermissions("target:zbkh:exin")
//	@RequestMapping(value = "exin")
//	@ResponseBody
//	public String expiExcel(HttpServletResponse response,
//			HttpServletRequest request) {
//		Map<String, Object> map = uploadExcel(request, response);
//		Map<String, Object> resultmap = new HashMap<String, Object>();
//		if (map.get("content") != null) {
//			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
//			resultmap = targetZbkhService.exinExcel(map);
//		} else {
//			resultmap.put("returncode", -2);
//		}
//		return JsonMapper.toJsonString(resultmap);
//	}

	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("target:zbkh:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("targetname", request.getParameter("target_zbkh_mbname"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		targetZbkhService.exportExcel(response, map);

	}

	/**
	 * 显示所有列
	 * @param id
	 * model
	 */
	@RequiresPermissions("target:zbkh:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url", "target/zbkh/export");
		return "common/formexcel";
	}

}
