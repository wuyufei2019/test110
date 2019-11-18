package com.cczu.sys.system.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.Department;
import com.cczu.sys.system.service.DepartmentService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 部门controller
 * @author jason
 */
@Controller
@RequestMapping("system/department")
public class DepartmentController extends BaseController{
	
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		String returnstr="";
		switch(sessionuser.getRoles().get(0).getRoleCode()){
			case "companyadmin":
				BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
				if(be!=null&&be.getM1()!=null){
					returnstr="system/departList";
				}else{
					returnstr="../error/001";
				}
				break;
			default:
				returnstr="system/departList";
				break;
		}
		
		return returnstr;
	}

	/**
	 * 部门网格list
	 */
	@RequestMapping(value="json")
	@ResponseBody
	public String  list(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(request.getParameter("filter_LIKES_m1")))
			map.put("m1", request.getParameter("filter_LIKES_m1"));
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		map.put("flag", sessionuser.getUsertype());
		if(sessionuser.getUsertype().equals("1")){
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			map.put("id2", be.getID());
		}else if(sessionuser.getUsertype().equals("0")){
			map.put("id3", sessionuser.getXzqy());
		}
		return departmentService.getJson_list(map);
	}

	/**
	 * 部门字典显示 {部门id   ： 部门名称}
	 * @param
	 */
	@RequestMapping(value="idjson")
	@ResponseBody
	public String depjson2() {
		Map<String, Object> map = new HashMap<String, Object>();
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		map.put("flag", sessionuser.getUsertype());
		if(sessionuser.getUsertype().equals("1")){
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			map.put("id2", be.getID());
		}else if(sessionuser.getUsertype().equals("0")){
			map.put("id3", sessionuser.getXzqy());
		}
		map.putAll(getAuthorityMap());
		return departmentService.jsonListBycode(map);
	}
	
	/**
	 * 菜单集合(list)
	 */
	@RequestMapping(value="list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Department> page = getPage(request);
		map.put("pageNo", page.getPageNo());
		map.put("pageSize", page.getPageSize());
		if(StringUtils.isNotEmpty(request.getParameter("filter_LIKES_m1")))
			map.put("m1", request.getParameter("filter_LIKES_m1"));
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		map.put("flag", sessionuser.getUsertype());
		if(sessionuser.getUsertype().equals("1")){
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			map.put("id2", be.getID());
		}else if(sessionuser.getUsertype().equals("0")){
			map.put("id3", sessionuser.getXzqy());
		}
		
		return departmentService.searchData(map);
	}
	
	
	/**
	 * 菜单集合(JSON)
	 */
	@RequestMapping(value="deptjson")
	@ResponseBody
	public String  deptlist(){
		return departmentService.getAlllist();
	}

	/**
	 * 企业任务分配模块专用部门集合(JSON)
	 */
	@RequestMapping(value="deptjson2")
	@ResponseBody
	public String  deptlist2(){
		return departmentService.getAlllist2();
	}
	
	/**
	 * 添加跳转
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String menuCreateForm(Model model) {
		model.addAttribute("list", new Department());
		model.addAttribute("action", "create");
		return "system/departForm";
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public String create(Department dt,Model model) {
		String restring="success";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		dt.setFlag(sessionuser.getUsertype());
		dt.setId1(Long.valueOf(sessionuser.getId()));
		dt.setId3(sessionuser.getXzqy());
		if(sessionuser.getUsertype().equals("1")){
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null){
				dt.setId2(be.getID());
			}
		}else{
			dt.setId2(0);
		}
		
		departmentService.save(dt);
		return restring;
	}
	
	/**
	 * 修改跳转
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateMenuForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("list", departmentService.get(id));
		model.addAttribute("action", "update");
		return "system/departForm";
	}

	/**
	 * 修改
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(Department dt,Model model) {
		String restring="success";
		departmentService.update(dt);
		return restring;
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "delete/{ids}", method = RequestMethod.GET)
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String restring="success";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			departmentService.delete(Long.parseLong(arrids[i]));
		}
		
		return restring;
	}
	
}
