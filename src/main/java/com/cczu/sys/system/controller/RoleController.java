package com.cczu.sys.system.controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.cczu.sys.comm.mapper.JsonMapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.entity.Permission;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.service.PermissionService;
import com.cczu.sys.system.service.RolePermissionService;
import com.cczu.sys.system.service.RoleService;

/**
 * 角色controller
 * @author jason
 * @date 2017年5月31日
 */
@Controller
@RequestMapping("system/role")
public class RoleController extends BaseController{
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RolePermissionService rolePermissionService;
	
	@Autowired
	private PermissionService permissionService;
	
	/**
	 * 默认页面
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(){
		return "system/roleList";
	}
	
	/**
	 * 角色集合(JSON)
	 */
	@RequiresPermissions("sys:role:view")
	@RequestMapping(value="json")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Page<Role> page=getPage(request);
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		page = roleService.search(page, filters);
		return getEasyUIData(page);
	}

	/**
	 * 获取角色拥有的权限ID集合
	 * @param id
	 * @return
	 */
	@RequiresPermissions("sys:role:permView")
	@RequestMapping("{id}/json")
	@ResponseBody
	public List<Integer> getRolePermissions(@PathVariable("id") Integer id){
		List<Integer> permissionIdList=rolePermissionService.getPermissionIds(id);
		return permissionIdList;
	}
	
	/**
	 * 修改角色拥有的权限ID集合
	 * @param id
	 * @return
	 */
	@RequiresPermissions("sys:role:permView")
	@RequestMapping("{id}/setrole")
	public String setRolePermissions(@PathVariable("id") Integer id,Model model){
		List<Integer> permissionIdList=rolePermissionService.getPermissionIds(id);
		List<Permission> permissionList=permissionService.getAll();
		model.addAttribute("menuIds", permissionIdList);
		model.addAttribute("menuList",permissionList);
		model.addAttribute("roleid", id);
		return "system/roleAuth";
	}
	
	/**
	 * 修改角色权限
	 * @param id
	 * @param newRoleList
	 * @return
	 */
	@RequiresPermissions("sys:role:permUpd")
	@RequestMapping(value = "{id}/updatePermission")
	@ResponseBody
	public String updateRolePermission(@PathVariable("id") Integer id,@RequestBody List<Integer> newRoleIdList,HttpSession session){
		List<Integer> oldRoleIdList=rolePermissionService.getPermissionIds(id);
		
		try {
		//获取application中的sessions
		@SuppressWarnings("rawtypes")
		HashSet sessions=(HashSet) session.getServletContext().getAttribute("sessions");
		@SuppressWarnings("unchecked")
		Iterator<Session> iterator= sessions.iterator();
		PrincipalCollection pc=null;
		
		//遍历sessions
		while(iterator.hasNext()){
			HttpSession s=(HttpSession) iterator.next();
			User user=(User) s.getAttribute("user");
			if(user==null)
				continue;
			List<Role> rolelist=roleService.findRoleById(user.getId());
			if(rolelist.size()>0&&rolelist.get(0).getId()==id){
				pc= (PrincipalCollection) s.getAttribute(String.valueOf(user.getId()));
				//清空该用户权限缓存
				rolePermissionService.clearUserPermCache(pc);
//				s.removeAttribute(String.valueOf(user.getId()));
				
			}
		}
		} catch (Exception e) {
			System.out.println("角色权限分配异常："+e.toString());
		}
		rolePermissionService.updateRolePermission(id,oldRoleIdList ,newRoleIdList);
		
		return "success";
	}
	
	/**
	 * 添加角色跳转
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:role:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("role", new Role());
		model.addAttribute("action", "create");
		return "system/roleForm";
	}

	/**
	 * 添加角色
	 * @param role
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:role:add")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public String create(@Valid Role role) {
		roleService.save(role);
		return "success";
	}

	/**
	 * 修改角色跳转
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:role:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("role", roleService.get(id));
		model.addAttribute("action", "update");
		return "system/roleForm";
	}

	/**
	 * 修改角色
	 * @param role
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:role:update")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(@Valid @ModelAttribute("role") Role role,Model model) {
		roleService.save(role);
		return "success";
	}

	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	@RequiresPermissions("sys:role:delete")
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") Integer id) {
		roleService.delete(id);
		return "删除成功！";
	}
	
	@ModelAttribute
	public void getRole(@RequestParam(value = "id", defaultValue = "-1") Integer id, Model model) {
		if (id != -1) {
			model.addAttribute("role", roleService.get(id));
		}
	}
}