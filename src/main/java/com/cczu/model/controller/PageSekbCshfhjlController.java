package com.cczu.model.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.TMESK_ProtectionDistanceEntity;
import com.cczu.model.service.SekbCshfhjlService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 初始化防护距离controller
 * @author jason
 *
 */
@Controller
@RequestMapping("sekb/csfhjl")
public class PageSekbCshfhjlController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private SekbCshfhjlService sekbCshfhjlService;

	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "model/sekb/csfhjl/index";	
	}
	
	/**
	 * list 
	 * @param request
	 */
	@RequiresPermissions("sekb:csfhjl:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData( HttpServletRequest request) {
		int pageNo=1;	//当前页码
		int pageSize=20;	//每页行数
		if(StringUtils.isNotEmpty(request.getParameter("page")))
			pageNo=Integer.valueOf(request.getParameter("page"));
		if(StringUtils.isNotEmpty(request.getParameter("rows")))
			pageSize=Integer.valueOf(request.getParameter("rows"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);
		map.put("name", request.getParameter("sekb_cx_m1"));
		return sekbCshfhjlService.dataGrid(map);
		
	}

	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("sekb:csfhjl:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "model/sekb/csfhjl/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequiresPermissions("sekb:csfhjl:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(TMESK_ProtectionDistanceEntity entity, Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		String datasuccess="保存成功！";	
		entity.setS1(new java.sql.Timestamp(new java.util.Date().getTime()));
		entity.setS2(new java.sql.Timestamp(new java.util.Date().getTime()));
		entity.setS3(0);
		entity.setID1(sessionuser.getId());
		sekbCshfhjlService.addInfo(entity);
		 
		//返回结果
		return datasuccess;
		
	}
	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("sekb:csfhjl:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		TMESK_ProtectionDistanceEntity csfhjl = sekbCshfhjlService.findById(id);
		model.addAttribute("csfhjl", csfhjl);
		//返回页面
		model.addAttribute("action", "update");
		return "model/sekb/csfhjl/form";
	}
	
	
	/**
	 * 修改信息
	 * @param request,model
	 */
	@RequiresPermissions("sekb:csfhjl:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(TMESK_ProtectionDistanceEntity entity,  Model model){
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		String datasuccess="更新成功";		
		entity.setS2(new java.sql.Timestamp(new java.util.Date().getTime()));
		entity.setS3(0);
		entity.setID1(sessionuser.getId());
		sekbCshfhjlService.updateInfo(entity);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除信息
	 */
	@RequiresPermissions("sekb:csfhjl:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			sekbCshfhjlService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("sekb:csfhjl:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		TMESK_ProtectionDistanceEntity csfhjl = sekbCshfhjlService.findById(id);
		
		model.addAttribute("csfhjl", csfhjl);
		//返回页面
		model.addAttribute("action", "view");
		return "model/sekb/csfhjl/view";
	}
	
	
	/**
	 * 导出Excel
	 * @param request
	 */
	@RequiresPermissions("sekb:csfhjl:export")
	@RequestMapping(value = "excel")
	@ResponseBody
	public void getExcel(HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", request.getParameter("excelcon1"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		sekbCshfhjlService.exportExcel(response, map);
		
	}
	
	/**
	 * 显示所有列
	 * @param id,model
	 */
	@RequiresPermissions("sekb:csfhjl:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		return "model/common/formexcel";
	}
	
	/**
	 * list 
	 * @param request
	 */
	@RequestMapping(value="json")
	@ResponseBody
	public String getDataJson( HttpServletRequest request) {
		List<TMESK_ProtectionDistanceEntity> list=sekbCshfhjlService.findByWzName(request.getParameter("name"));
		if(list!=null&&list.size()>0)
			return JsonMapper.getInstance().toJson(list.get(0));
		else{
			Map<String, Object> map=new HashMap<>();
			map.put("m2", "");
			map.put("m3", "");
			map.put("m4", "");
			map.put("m5", "");
			map.put("m6", "");
			map.put("m7", "");
			map.put("m8", "");
			return JsonMapper.getInstance().toJson(map);
		}

	}
}
