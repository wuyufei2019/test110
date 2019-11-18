package com.cczu.model.controller;


import java.sql.Timestamp;
import java.util.HashMap;
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

import com.cczu.model.entity.YHPC_GridKpiContent;
import com.cczu.model.service.YhpcGridKpiContentService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 *网格化管理-网格绩效考核规则
 * @author zpc
 * @date 2017/08/19
 */
@Controller
@RequestMapping("yhpc/wgkpi/content")
public class PageYhpcGridKpiContentController extends BaseController{

	@Autowired
	private YhpcGridKpiContentService yhpcGridKpiContentService;
	@Autowired
	private RoleService roleService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if("1".equals(sessionuser.getUsertype())){
			return "../error/403";
		}else{
			return "yhpc/wgkpi/content/index";
		}
	}
	
	/**
	 * list页面
	 */
	@RequiresPermissions("yhpc:wgkpicont:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		String code = request.getParameter("wgcode");
		map.put("wgcode",StringUtils.isBlank(code)?UserUtil.getCurrentShiroUser().getXzqy():code);
		return yhpcGridKpiContentService.dataGrid(map);
	}

	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("yhpc:wgkpicont:add")
	@RequestMapping(value = "create")
	public String create(Model model,HttpServletRequest request) {
		model.addAttribute("action", "createSub");
		return "yhpc/wgkpi/content/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequiresPermissions("yhpc:wgkpicont:add")
	@RequestMapping(value = "createSub" )
	@ResponseBody
	public String create(YHPC_GridKpiContent cont, HttpServletRequest request) {
		String datasuccess="failed";
		Timestamp t=DateUtils.getSysTimestamp();
		cont.setS1(t);
		cont.setS2(t);
		cont.setS3(0);
		if(StringUtils.isNoneBlank(cont.getStandard())){
			String[] standards=cont.getStandard().split(",");
			for(String s : standards){
				cont.setID(null);
				cont.setStandard(s);
				yhpcGridKpiContentService.addInfoReturnId(cont);
			}
		}
		datasuccess="success";
		return datasuccess;
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("yhpc:wgkpicont:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		YHPC_GridKpiContent cont = yhpcGridKpiContentService.findById(id);
		model.addAttribute("cont", cont);
		model.addAttribute("action", "update");
		return "yhpc/wgkpi/content/form";
	}
	
	/**
	 * 修改
	 * @param request,model
	 */
	@RequiresPermissions("yhpc:wgkpicont:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(YHPC_GridKpiContent cont, HttpServletRequest request, Model model){
		String datasuccess="success";
		yhpcGridKpiContentService.updateInfo(cont);
		return datasuccess;
	}
	
	/**
	 * 删除
	 * @param user
	 * @param model
	 * 
	 */
	@RequiresPermissions("yhpc:wgkpicont:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			yhpcGridKpiContentService.deleteInfo(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("yhpc:wgkpicont:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		YHPC_GridKpiContent cont = yhpcGridKpiContentService.findById(id);
		model.addAttribute("cont", cont);
		return "yhpc/wgkpi/content/view";
	}
	
	/**
	 * 获取 项目评分和考核内容
	 * @param user
	 * @param model
	 */
	@RequestMapping(value = "namejson")
	@ResponseBody
	public String getNameJson(HttpServletRequest request) {
		Map<String,Object> mapData= new HashMap<String, Object>();
		String code = request.getParameter("code");
		mapData.put("wgcode", code);
		List<Map<String,Object>> list=yhpcGridKpiContentService.getNameJson(mapData);
		return JsonMapper.getInstance().toJson(list);
	}
	/**
	 * 获取 项目评分和考核内容
	 * @param user
	 * @param model
	 */
	@RequestMapping(value = "contentjson")
	@ResponseBody
	public String getContentJson(HttpServletRequest request) {
		Map<String,Object> mapData= new HashMap<String, Object>();
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		mapData.put("wgcode", code);
		mapData.put("name", name);
		List<Map<String,Object>> list=yhpcGridKpiContentService.getContentJson(mapData);
		return JsonMapper.getInstance().toJson(list);
	}
	
}
