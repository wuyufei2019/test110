package com.cczu.model.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.AQPX_CourseEntity;
import com.cczu.model.entity.AQPX_TestguizeEntity;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IAqpxGzxxService;
import com.cczu.model.service.IAqpxKCxxService;
import com.cczu.model.service.IAqpxStkxxService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 安全培训管理——试题规则Controller
 * @author jason
 */
@Controller
@RequestMapping("aqpx/stgz")
public class PageAqpxStgzController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private IAqpxKCxxService aqpxkcxxservice;
	@Autowired
	private IAqpxGzxxService aqpxgzxxservice;
	@Autowired
	private IAqpxStkxxService aqpxstkxxservice;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be==null){//判断是否存在企业基本信息
				return "../error/001";
			}
			return "aqpx/stgz/index";
		}else//非企业用户无法使用该模块
			return "../error/403";
	}
	

	/**
	 * list页面
	 * @param request
	 */
	@RequiresPermissions("aqpx:stgz:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		
		Map<String, Object> map = getPageMap(request);
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		map.put("qyid", sessionuser.getQyid());
		map.put("kc", request.getParameter("aqpx_stgz_cx_m1"));
		return aqpxgzxxservice.dataGrid(map);
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("aqpx:stgz:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
			return "aqpx/stgz/form";
		
	}
	
	/**
	 * 添加规则信息
	 * @param request,model
	 */
	@RequiresPermissions("aqpx:stgz:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(@Valid AQPX_TestguizeEntity entity, Model model) {
		String datasuccess="success";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		entity.setID1(sessionuser.getQyid());
		aqpxgzxxservice.addInfo(entity);
		return datasuccess;
	}	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("aqpx:stgz:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		
		AQPX_TestguizeEntity gz = aqpxgzxxservice.findbyid(id);
		model.addAttribute("stgz",gz);
		
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "aqpx/stgz/form";
	}
	
	/**
	 * 修改试题规则信息
	 * @param request,model
	 */
	@RequiresPermissions("aqpx:stgz:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(@Valid AQPX_TestguizeEntity entity, Model model){
		String datasuccess="success";
		aqpxgzxxservice.updateInfo(entity);
		
		return datasuccess;
	}
	
	/**
	 * 删除试题规则信息
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequiresPermissions("aqpx:stgz:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			aqpxgzxxservice.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("aqpx:stgz:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		AQPX_TestguizeEntity gz = aqpxgzxxservice.findbyid(id);
		model.addAttribute("stgz",gz);
		AQPX_CourseEntity kc= aqpxkcxxservice.findbyid(gz.getID2());
		model.addAttribute("kcmc",kc.getM1());
		return "aqpx/stgz/view";
	}
	

	/**
	 * 沒有制定考試規則的課程名稱json
	 * {"id":11,"text":"课程名称"}
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "kcjson")
	@ResponseBody
	public String getKCJson(HttpServletRequest request, HttpServletResponse response) {
		return aqpxgzxxservice.findKCByNoGz(UserUtil.getCurrentShiroUser().getQyid());
	}
	
	/**
	 * 判断是否可以生成出卷规则
	 * @param request
	 */
	@RequestMapping(value = "creategz")
	@ResponseBody
	public String isOrNotCreateSJ(Model model, HttpServletRequest request) {
		String datasuccess="success";
		Map<String, String> map = new HashMap<String, String>();
		map.put("id2", request.getParameter("kcid"));
		map.put("m1", request.getParameter("m1"));
		map.put("m2", request.getParameter("m2"));
		map.put("m3", request.getParameter("m3"));
		map.put("m4", request.getParameter("m4"));
		String stgz = aqpxstkxxservice.STgzIsOrNot(map);
		if(stgz == "1"){
			datasuccess="试题库单选题量不足请联系管理员添加！";
		}else if (stgz == "2") {
			datasuccess="试题库多选题量不足请联系管理员添加！";
		}else if (stgz == "3") {
			datasuccess="试题库填空题量不足请联系管理员添加！";
		}else if (stgz == "4") {
			datasuccess="试题库判断题量不足请联系管理员添加！";
		}
		return datasuccess;
	}
}