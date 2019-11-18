package com.cczu.sys.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.xpath.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.system.entity.CompRole;
import com.cczu.sys.system.entity.Permission;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.service.CompRolePermissionService;
import com.cczu.sys.system.service.CompRoleService;
import com.cczu.sys.system.service.CompUserRoleService;
import com.cczu.sys.system.service.PermissionService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.utils.UserUtil;


/**
 * @description 企业用户、内部角色controller
 * @author jason
 * @date 2017年12月26日
 */
@Controller
@RequestMapping("system/compuser")
public class CompUserRoleController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private CompUserRoleService compUserRoleService;
	
	@Autowired
	private CompRoleService compRoleService;
	
	@Autowired
	private CompRolePermissionService compRolePermissionService;
	
	@Autowired
	private PermissionService permissionService;
	
	/**
	 * 企业内部角色页面跳转
	 */
	@RequiresPermissions("system:compuser:view")
	@RequestMapping(value="comprole/index",method = RequestMethod.GET)
	public String comproleindex(HttpServletRequest request) {
		return "system/comp/role/index";
	}
	/**
	 * 企业内部角色集合(JSON)
	 */
	@RequiresPermissions("system:compuser:view")
	@RequestMapping(value="comprole/json",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCompRoleData(HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Page<CompRole> page=getPage(request);
		page.setOrderBy("sort,id");
		page.setOrder("asc,asc");
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		filters.add(new PropertyFilter("EQL_qyid", sessionuser.getQyid()+""));
		page = compRoleService.search(page, filters);
		return getEasyUIData(page);
	}
	
	/**
	 * 添加企业内部角色跳转
	 * @param model
	 * @return
	 */
	@RequiresPermissions("system:compuser:add")
	@RequestMapping(value = "comprole/create", method = RequestMethod.GET)
	public String createForm(Model model) {
		CompRole role=new CompRole();
		role.setQyid( UserUtil.getCurrentShiroUser().getQyid());
		model.addAttribute("role", role);
		model.addAttribute("action", "create");
		return "system/comp/role/roleForm";
	}

	/**
	 * 添加企业内部角色
	 * @param role
	 * @param model
	 * @return
	 */
	@RequiresPermissions("system:compuser:add")
	@RequestMapping(value = "comprole/create", method = RequestMethod.POST)
	@ResponseBody
	public String create(@Valid CompRole role,Model model) {
		compRoleService.save(role);
		return "success:"+role.getId();
	}

	/**
	 * 修改企业内部角色跳转
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("system:compuser:update")
	@RequestMapping(value = "comprole/update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("role", compRoleService.get(id));
		model.addAttribute("action", "update");
		return "system/comp/role/updateForm";
	}

	/**
	 * 修改企业内部角色
	 * @param role
	 * @param model
	 * @return
	 */
	@RequiresPermissions("system:compuser:update")
	@RequestMapping(value = "comprole/update", method = RequestMethod.POST)
	@ResponseBody
	public String update(@Valid @ModelAttribute("role") CompRole role,Model model) {
		compRoleService.save(role);
		return "success";
	}

	/**
	 * 删除企业内部角色
	 * @param idList
	 * @return
	 */
	@RequiresPermissions("system:compuser:delete")
	@RequestMapping(value = "comprole/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestBody List<Integer> idList) {
		for(int id:idList){
			compRolePermissionService.deleteALlPerByRole(id);
			compRoleService.delete(id);
		}
		return "删除成功！";
	}
	
	/**
	 * 设置角色拥有的权限ID集合
	 * @param id
	 * @return
	 */
	@RequiresPermissions("system:compuser:permUpd")
	@RequestMapping("{id}/setrole")
	public String setCompRolePermissions(@PathVariable("id") Integer id,Model model){
		//获取企业管理员拥有的权限信息（企业管理员只能对自己所拥有的权限进行分配）
		List<Permission> permissionList=permissionService.getPermissions(UserUtil.getCurrentShiroUser().getId());
		
		List<Map<String, Object>> list=new ArrayList<>();
		for(Permission  permission:permissionList){
			Map<String, Object> map =new HashMap<>();
			map.put("id", permission.getId());
			map.put("pid", permission.getPid());
			if(permission.getDescription().trim().equals(""))
				map.put("name", permission.getName());
			else
				map.put("name", permission.getName()+"······"+permission.getDescription());
			list.add(map);
		}
		
		//内部角色拥有的权限id集合
		List<Integer> permissionIdList=compRolePermissionService.getPermissionIds(id);
		
		model.addAttribute("menuIds", permissionIdList);
		model.addAttribute("menuList",list);
		model.addAttribute("roleid", id);
		return "system/comp/role/roleAuth";
	}
	
	/**
	 * 保存角色拥有的权限信息
	 * @param id
	 * @return
	 */
	@RequiresPermissions("system:compuser:permUpd")
	@RequestMapping(value = "{id}/updatePermission")
	@ResponseBody
	public String updateCompRolePermission(@PathVariable("id") Integer id,@RequestBody List<Integer> newRoleIdList,HttpSession session){
		//获取数据库中保存的企业内部角色的权限id集合
		List<Integer> oldRoleIdList=compRolePermissionService.getPermissionIds(id);
		//执行修改
		compRolePermissionService.updateRolePermission(id,oldRoleIdList ,newRoleIdList);
	
		//清除拥有该角色的，并正在使用系统的用户的权限信息
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
			List<CompRole> rolelist=compRoleService.findCompRoleById(user.getId());
			if(rolelist.size()>0&&rolelist.get(0).getId()==id){
				pc= (PrincipalCollection) s.getAttribute(String.valueOf(user.getId()));
				//清空该用户权限缓存
				compRolePermissionService.clearUserPermCache(pc);
			}
		}
		} catch (Exception e) {
			System.out.println("角色权限分配异常："+e.toString());
		}
	
		return "success";
	}
	
	/**
	 * 弹窗页-企业用户拥有的角色
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "{userId}/compuserrole")
	public String getUserRole(@PathVariable("userId") Integer id, Model model) {
		model.addAttribute("userId", id);
		return "system/comp/user/userRoleList";
	}

	/**
	 * 获取用户拥有的角色ID集合
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "{id}/comprole")
	@ResponseBody
	public List<Integer> getCompRoleIdList(@PathVariable("id") Integer id) {
		return compUserRoleService.getCompRoleIdList(id);
	}

	/**
	 * 修改用户拥有的角色
	 * 
	 * @param id
	 * @param newRoleList
	 * @return
	 */
	@RequestMapping(value = "{id}/updatecompur")
	@ResponseBody
	public String updateCompUserRole(@PathVariable("id") Integer id,@RequestBody List<Integer> newRoleList) {
		compUserRoleService.updateCompUserRole(id, compUserRoleService.getCompRoleIdList(id),newRoleList);
		return "操作成功！";
	}
	
	
	/**
	 * 企业用户下拉字典
	 * @param {json}
	 */
	@RequestMapping(value="/userjson" , method = RequestMethod.POST)
	@ResponseBody
	public String getCompUserJson(HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		List<Map<String, Object>> reslist = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> datamap =new HashMap<>();
		datamap.put("qyid", sessionuser.getQyid());
		datamap.put("usertype", "1");
		
		//根据部门id查询
		String attribute = request.getParameter("bmid");
		datamap.put("departmen", attribute);
		
		List<User> list = userService.findByAllUserByContent(datamap);
		for(User user:list){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", user.getId());
			map.put("text", user.getName());
			reslist.add(map);
		}
		
		return JsonMapper.getInstance().toJson(reslist);
	}

    /**
     * 企业用户下拉字典
     * @param {json}
     */
    @RequestMapping(value="/userloginnamejson" , method = RequestMethod.POST)
    @ResponseBody
    public String getUserLoginNameJson(HttpServletRequest request) {
        ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
        List<Map<String, Object>> reslist = new ArrayList<Map<String, Object>>();

        Map<String, Object> datamap =new HashMap<>();
        datamap.put("qyid", sessionuser.getQyid());
        datamap.put("usertype", "1");

        //根据部门id查询
        String attribute = request.getParameter("bmid");
        datamap.put("departmen", attribute);

        List<User> list = userService.findByAllUserByContent(datamap);
        for(User user:list){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", user.getId());
            map.put("text", user.getLoginName());
            reslist.add(map);
        }

        return JsonMapper.getInstance().toJson(reslist);
    }

	/**
	 * 跳转人员多选列表页面
	 */
	@RequestMapping(value = "rydx", method = RequestMethod.GET)
	public String rydx(HttpServletRequest request, Model model) {
		String content = request.getParameter("content");
		model.addAttribute("content",content);
		return "common/multipleRyChoose";
	}

	/**
	 * 企业用户下拉字典多选列表
	 * @param {json}
	 */
	@RequestMapping(value="/userjsonlist" , method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCompUserJsonList(HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Map<String, Object> maplist = new HashMap<String, Object>();
		List<Map<String, Object>> reslist = new ArrayList<Map<String, Object>>();

		Map<String, Object> datamap =new HashMap<>();
		datamap.put("qyid", sessionuser.getQyid());
		datamap.put("usertype", "1");

		//根据人员姓名模糊查询
		String name = request.getParameter("ry_name");
		datamap.put("name",name);
		//根据部门id查询
		String attribute = request.getParameter("bmid");
		datamap.put("departmen", attribute);

		List<User> list = userService.findByAllUserByContent(datamap);
		for(User user:list){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", user.getId());
			map.put("text", user.getName());
			reslist.add(map);
		}
		maplist.put("rows", reslist);
		return maplist;
	}
	
	/**
	 * 企业用户内部角色
	 * @return
	 */
	@RequestMapping(value="/compRoleJson" , method = RequestMethod.POST)
	@ResponseBody
	public String getCompRole() {
		Long qyid = UserUtil.getCurrentShiroUser().getQyid();
		return JsonMapper.getInstance().toJson(compRoleService.getCompRole(qyid));
	}
}
