package com.cczu.model.zyaqgl.controller;

import java.sql.Timestamp;
import java.util.HashMap;
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

import com.cczu.model.zyaqgl.entity.AQGL_RelevantEvaluation_Setting;
import com.cczu.model.zyaqgl.service.AqglXgfpdSettingService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 安全管理-相关方评定内容设置Controller
 * @author YZH
 */
@Controller
@RequestMapping("zyaqgl/xgfpd/setting")
public class PageAqglXgfpdSettingController extends BaseController {

	@Autowired
	private AqglXgfpdSettingService aqglXgfpdSettingService;
	
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
		model.addAttribute("usertype", usertype);
		return "zyaqgl/xggl/xgfpd/setting/index";
	}
	
	/**
	 * 数据list 
	 * @param request
	 */
	@RequiresPermissions("zyaqgl:xgfpd:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		return aqglXgfpdSettingService.dataGrid(map);	
		
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("zyaqgl:xgfpd:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model, HttpServletRequest request) {
		model.addAttribute("action", "create");
		return "zyaqgl/xggl/xgfpd/setting/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequiresPermissions("zyaqgl:xgfpd:add")
	@RequestMapping(value = "create" ,method = RequestMethod.POST)
	@ResponseBody
	public String create(AQGL_RelevantEvaluation_Setting entity) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		entity.setID1(UserUtil.getCurrentShiroUser().getQyid());
		aqglXgfpdSettingService.addInfo(entity);
		// 返回结果
		return datasuccess;
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("zyaqgl:xgfpd:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		AQGL_RelevantEvaluation_Setting entity = aqglXgfpdSettingService.findInforById(id);
		model.addAttribute("entity", entity);
		model.addAttribute("action", "update");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "zyaqgl/xggl/xgfpd/setting/form";
	}
	
	/**
	 * 修改信息
	 * @param request,model
	 */
	@RequiresPermissions("zyaqgl:xgfpd:update")
	@RequestMapping(value = "update",method = RequestMethod.POST)
	@ResponseBody
	public String update(AQGL_RelevantEvaluation_Setting entity){
		String datasuccess="success";	
		entity.setS2(DateUtils.getSysTimestamp());
		aqglXgfpdSettingService.updateInfo(entity);
		return datasuccess;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			aqglXgfpdSettingService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	 
}
