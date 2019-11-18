package com.cczu.model.zyaqgl.controller;

import java.sql.Timestamp;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.zyaqgl.entity.AQGL_ZzEntity;
import com.cczu.model.zyaqgl.service.AqglZzService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 安全生产-资质信息Controller
 * @author YZH
 */
@Controller
@RequestMapping("zyaqgl/zzxx")
public class PageAqglZzxxController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private AqglZzService aqglzzService;
	 
	
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
		model.addAttribute("usertype", usertype);
		return "zyaqgl/xggl/xgdw/zzxx/index";
	}
	
	/**
	 * 数据list 
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("m2", request.getParameter("jb"));
		map.put("dwid", request.getParameter("dwid"));
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			map.put("qyid",(UserUtil.getCurrentShiroUser().getQyid()));
		}
		return aqglzzService.dataGrid(map);	
		
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model, HttpServletRequest request) {
		model.addAttribute("action", "create");
		model.addAttribute("dwid", request.getParameter("dwid"));
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "zyaqgl/xggl/xgdw/zzxx/form";
	}
	
	/**
	 * 添加资质信息信息
	 * @param request,model
	 */
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(AQGL_ZzEntity entity, Model model) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		//添加资质信息
		aqglzzService.addInfo(entity);
		// 返回结果
		return datasuccess;
	}
	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的资质信息信息
		AQGL_ZzEntity zzxx = aqglzzService.findById(id);
		model.addAttribute("zz", zzxx);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "zyaqgl/xggl/xgdw/zzxx/form";
	}
	
	/**
	 * 修改资质信息信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQGL_ZzEntity entity,  Model model){
		String datasuccess="success";	
		entity.setS1(new java.sql.Timestamp(new java.util.Date().getTime()));
		entity.setS2(new java.sql.Timestamp(new java.util.Date().getTime()));
		entity.setS3(0);
		
		aqglzzService.updateInfo(entity);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除资质信息信息
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			aqglzzService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		AQGL_ZzEntity zzxx = aqglzzService.findById(id);
		
		model.addAttribute("zz", zzxx);
		//返回页面
		model.addAttribute("action", "view");
		return "zyaqgl/xggl/xgdw/zzxx/view";
	}
	
}
