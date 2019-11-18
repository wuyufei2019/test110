package com.cczu.model.hjbh.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.hjbh.entity.HJBH_OutStorage;
import com.cczu.model.hjbh.service.HjbhOutStorageService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 环境保护-入库管理controller
 * @author jason
 */

@Controller
@RequestMapping("hjbh/ckgl")
public class PageOutStorageController extends BaseController {

	@Autowired
	private HjbhOutStorageService hjbhOutStorageService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;

	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequiresPermissions("hjbh:ckgl:view")
	@RequestMapping(value = "index")
	public String index2(Model model) {
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(UserUtil.getCurrentShiroUser().getQyid());
		if (be.getIsbloc() != null && be.getIsbloc() == 1) {
			return "hjbh/ckgl/index";
		}else{
			return "hjbh/ckgl/index";
		}
	}

	/**
	 * list页面 企业端 
	 * @param request
	 */
	@RequiresPermissions("hjbh:ckgl:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getQyData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		ShiroUser user =UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(user.getQyid());
		if (be.getIsbloc() != null && be.getIsbloc() == 1) {// 集团公司
			map.put("fid", user.getQyid());
		} else {
			map.put("qyid", user.getQyid());
		}
		map.put("name", request.getParameter("view_ckgl_name"));
		return hjbhOutStorageService.dataGrid(map);
	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("hjbh:ckgl:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "hjbh/ckgl/form";
	}

	/**
	 * 添加目标信息
	 * 
	 * @param request
	 * model
	 */
	@RequiresPermissions("hjbh:ckgl:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(HJBH_OutStorage entity, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		try {
			hjbhOutStorageService.addInfo(entity);
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
	@RequiresPermissions("hjbh:ckgl:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		HJBH_OutStorage entity = hjbhOutStorageService.findInfoById(id);
		model.addAttribute("entity", entity);
		model.addAttribute("action", "update");
		return "hjbh/ckgl/form";
	}

	/**
	 * 修改目标信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("hjbh:ckgl:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(HJBH_OutStorage entity, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		entity.setS2(t);
		hjbhOutStorageService.updateInfo(entity);
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
	@RequiresPermissions("hjbh:ckgl:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			hjbhOutStorageService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * 
	 * @param id
	 *            ,model
	 */
	@RequiresPermissions("hjbh:ckgl:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		HJBH_OutStorage entity = hjbhOutStorageService.findInfoById(id);
		model.addAttribute("entity", entity);
		return "hjbh/ckgl/view";
	}
	
}
