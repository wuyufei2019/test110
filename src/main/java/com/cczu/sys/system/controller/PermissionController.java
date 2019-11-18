package com.cczu.sys.system.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cczu.model.entity.dto.Menu;
import com.cczu.model.entity.dto.Tdic_NoteTreeDto;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.entity.Permission;
import com.cczu.sys.system.service.PermissionService;
import com.cczu.sys.system.service.RolePermissionService;
import com.cczu.sys.system.utils.UserUtil;


/**
 * 权限controller
 * @author jason
 * @date 2017年5月31日
 */
@Controller
@RequestMapping("system/permission")
public class PermissionController extends BaseController{
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private RolePermissionService rolePermissionService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(){
		return "system/permissionList";
	}
	
	/**
	 * 菜单页面
	 */
	@RequestMapping(value="menu",method = RequestMethod.GET)
	public String menuList(){
		return "system/menuList";
	}
	
	/**
	 * 菜单集合(JSON) Treegrid
	 */
	@RequiresPermissions("sys:perm:menu:view")
	@RequestMapping(value="menu/treejson")//,method = RequestMethod.GET)
	@ResponseBody
	public String menuTreeDate(){
		List<Permission> permissionList=permissionService.getMenus();
		List<Menu> nodeList =getMenuTree(permissionList);
		
		return JsonMapper.getInstance().toJson(nodeList);
	}
	/**
	 * 菜单集合(JSON) Combotree 
	 */
	@RequiresPermissions("sys:perm:menu:view")
	@RequestMapping(value="menu/json2")//,method = RequestMethod.GET)
	@ResponseBody
	public String menuTreeDate2(){
		List<Permission> permissionList=permissionService.getMenus();
		List<Tdic_NoteTreeDto> list=new ArrayList<>();
		if(permissionList.size()>0){
			for(Permission p:permissionList){
				Tdic_NoteTreeDto m=new Tdic_NoteTreeDto();
				m.setId(p.getId()+"");
				m.setText(p.getName());
				m.setPid(p.getPid()+"");
				m.setSort(p.getSort());
				list.add(m);
			}
		}
		
		List<Tdic_NoteTreeDto> nodeList = new ArrayList<>();  
		for(Tdic_NoteTreeDto dto1 : list){  
		    boolean mark = false;  
		    for(Tdic_NoteTreeDto dto2 : list){  
		        if(dto1.getPid()!=null && dto1.getPid().equals(dto2.getId())){  
		            mark = true;  
		            if(dto2.getChildren() == null)  
		            	dto2.setChildren(new ArrayList<Tdic_NoteTreeDto>());  
		            dto2.getChildren().add(dto1);   
		            break;  
		        }  
		    }  
		    if(!mark){  
		        nodeList.add(dto1);   
		    }  
		}  
		
		return JsonMapper.getInstance().toJson(nodeList);
	}
	/**
	 * 菜单集合(JSON)
	 */
	@RequiresPermissions("sys:perm:menu:view")
	@RequestMapping(value="menu/json")//,method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> menuDate(){
		List<Permission> permissionList=permissionService.getMenus();
		List<Map<String, Object>> list =new ArrayList<>();
		for(Permission p:permissionList){
			Map<String, Object> map=new HashMap<>();
			map.put("id", p.getId());
			map.put("name", p.getName());
			map.put("pId", p.getPid());
			list.add(map);
		}
		
		return list;
	}
	
	/**
	 * 权限集合(JSON)
	 */
	@RequiresPermissions("sys:perm:view")
	@RequestMapping(value="json",method = RequestMethod.GET)
	@ResponseBody
	public List<Permission> getData() {
		List<Permission> permissionList=permissionService.getAll();
		return permissionList;
	}
	
	
	
	/**
	 * 获取菜单下的操作
	 */
	@RequiresPermissions("sys:perm:view")
	@RequestMapping("ope/json")
	@ResponseBody
	public Map<String, Object> menuOperationDate(Integer pid){
		List<Permission> menuOperList=permissionService.getMenuOperation(pid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", menuOperList);
		map.put("total",menuOperList.size());
		return map;
	}
	
	/**
	 * 当前登录用户的权限集合
	 */
	@RequestMapping("i/json")
	@ResponseBody
	public List<Permission> myPermissionDate() {
//		List<Permission> permissionList=permissionService.getPermissions(UserUtil.getCurrentUser().getId());
		List<Permission> permissionList=permissionService.getPermissionsnew(UserUtil.getCurrentUser().getId());
		
		return permissionList;
	}
	
	/**
	 * 获取当前用户授权的一级菜单
	 * @return
	 */
	@RequestMapping("i/topmenu")
	@ResponseBody
	public String getTopMenu(){
		List<Permission> permissionList=permissionService.getFirstMenuPermissions(UserUtil.getCurrentUser().getId());
		List<Menu> nodeList =getMenuTree(permissionList);
		return JsonMapper.getInstance().toJson(nodeList);
	}
	
	/**
	 * 根据菜单id获取该菜单下所有子菜单（当前用户授权的）
	 * @return
	 */
	@RequestMapping("i/childmenu")
	@ResponseBody
	public String getChildMenu(@RequestParam(value = "pid") int pid){
		List<Permission> permissionList=permissionService.getChildMenusById(UserUtil.getCurrentUser().getId(),pid);
		List<Menu> nodeList =getMenuTree(permissionList);
		return JsonMapper.getInstance().toJson(nodeList);
	}
	
	
	/**
	 * 获取当前用户授权的所有菜单
	 * @return
	 */
	@RequestMapping("i/menujson")
	@ResponseBody
	public String getMenu(){
		List<Permission> permissionList=permissionService.getPermissionsnew(UserUtil.getCurrentUser().getId());
		List<Menu> nodeList =getMenuTree(permissionList);
		return JsonMapper.getInstance().toJson(nodeList);
	}
	
	/**
	 * 获取当前用户授权的所有菜单（化工企业版）
	 * @return
	 */
	@RequestMapping("hgqy/menujson")
	@ResponseBody
	public String getHgqyMenu(@RequestParam(value = "pid") int pid){
		List<Permission> permissionList=permissionService.getHgqyPermissions(UserUtil.getCurrentUser().getId(),pid);
		List<Menu> nodeList =getMenuTree(permissionList);
		return JsonMapper.getInstance().toJson(nodeList);
	}
	
	/**
	 * 某用户的权限集合
	 */
	@RequiresPermissions("sys:perm:view")
	@RequestMapping("{userId}/json")
	@ResponseBody
	public List<Permission> otherPermissionDate(@PathVariable("userId") Integer userId) {
		List<Permission> permissionList=permissionService.getPermissions(userId);
		return permissionList;
	}
	
	/**
	 * 添加权限跳转
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model,Integer pid) {
		Permission parent=permissionService.get(pid);
		Permission per=new Permission();
		per.setPid(pid);
		if(parent.getType().equals("AF"))//判断是否为APP端菜单
			per.setType("AO");
		else
			per.setType("O");
			
		model.addAttribute("permission", per);
		model.addAttribute("pname",parent.getName());
		model.addAttribute("action", "create");
		return "system/permissionForm";
	}
	
	/**
	 * 添加菜单跳转
	 */
	@RequestMapping(value = "menu/create", method = RequestMethod.GET)
	public String menuCreateForm(Model model,String type) {
		Permission per=new Permission();
		per.setType(type);
		model.addAttribute("permission", per);
		model.addAttribute("action", "create");
		return "system/menuForm";
	}

	/**
	 * 添加权限/菜单
	 */
	@RequiresPermissions("sys:perm:add")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public String create(@Valid Permission permission,Model model) {
		permissionService.save(permission);
		return "success";
	}
	

	/**
	 * 初始化权限页面跳转
	 */
	@RequiresPermissions("sys:perm:add")
	@RequestMapping(value = "initpermission", method = RequestMethod.GET)
	public String initPermission(@RequestParam(value = "id")Integer id,Model model) {
		model.addAttribute("action", "init");
		Permission parent=permissionService.get(id);
		if(parent.getType().equals("AF"))//判断是否为APP端菜单
			model.addAttribute("type", "AO");
		else
			model.addAttribute("type", "O");
		model.addAttribute("pid", id);
		return "system/permissionInit";
	}
	/**
	 * 初始化权限
	 */
	@RequiresPermissions("sys:perm:add")
	@RequestMapping(value = "init", method = RequestMethod.POST)
	@ResponseBody
	public String initPer(@Valid Permission permission) {
		String datasuccess ="success";
		try {
			permissionService.initPer(permission);
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="failed";
		}
		return datasuccess;
	}
	
	
	/**
	 * 添加菜单基础操作
	 * @param pid
	 * @return
	 */
	@RequiresPermissions("sys:perm:add")
	@RequestMapping(value = "createBase/{pname}/{pid}", method = RequestMethod.GET)
	@ResponseBody
	public String create(@PathVariable("pname") String pname,@PathVariable("pid") Integer pid){
		permissionService.addBaseOpe(pid, pname);
		return "success";
	}

	/**
	 * 修改权限跳转
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		Permission per=permissionService.get(id);
		model.addAttribute("permission", per);
		model.addAttribute("pname", permissionService.get(per.getPid()).getName());
		model.addAttribute("action", "update");
		return "system/permissionForm";
	}
	
	/**
	 * 修改菜单跳转
	 */
	@RequestMapping(value = "menu/update/{id}", method = RequestMethod.GET)
	public String updateMenuForm(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("permission", permissionService.get(id));
		model.addAttribute("action", "update");
		return "system/menuForm";
	}

	/**
	 * 修改权限/菜单
	 */
	@RequiresPermissions("sys:perm:update")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(@Valid @ModelAttribute("permission") Permission permission,Model model) {
		permissionService.save(permission);
		return "success";
	}

	/**
	 * 删除权限
	 */
	@RequiresPermissions("sys:perm:delete")
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") Integer id) {
		permissionService.delete(id);
		return "success";
	}
	
	/**
	 * 删除菜单以及对应的权限
	 */
	@RequiresPermissions("sys:perm:delete")
	@RequestMapping(value = "deletemenu/{id}")
	@ResponseBody
	public String deleteMenu(@PathVariable("id") Integer id) {
		permissionService.deleteMenuPermission(id);
		permissionService.delete(id);
		return "success";
	}
	
	@ModelAttribute
	public void getPermission(@RequestParam(value = "id", defaultValue = "-1") Integer id, Model model) {
		if (id != -1) {
			model.addAttribute("permission", permissionService.get(id));
		}
	}
	
	/**
	 * 将菜单列表转换成树形列表
	 * @param permissionList
	 * @return
	 */
	public List<Menu> getMenuTree(List<Permission> permissionList){
		List<Menu> list=new ArrayList<>();
		if(permissionList.size()>0){
			for(Permission p:permissionList){
				Menu m=new Menu();
				m.setId(p.getId()+"");
				m.setHref(p.getUrl());
				m.setIcon(p.getIcon());
				m.setName(p.getName());
				m.setEnname(p.getEnname());
				m.setParentId(p.getPid()+"");
				m.setDescription(p.getDescription());
				m.setSort(p.getSort());
				list.add(m);
			}
		}
		
		List<Menu> nodeList = new ArrayList<Menu>();  
		for(Menu dto1 : list){  
		    boolean mark = false;  
		    for(Menu dto2 : list){  
		        if(dto1.getParentId()!=null && dto1.getParentId().equals(dto2.getId())){  
		            mark = true;  
		            if(dto2.getChildren() == null)  
		            	dto2.setChildren(new ArrayList<Menu>());  
		            dto2.getChildren().add(dto1);   
		            break;  
		        }  
		    }  
		    if(!mark){  
		        nodeList.add(dto1);   
		    }  
		}  
		return nodeList;
	}
	/**
	 * 一级菜单主页
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="subindex",method = RequestMethod.GET)
	public String subindex(@RequestParam(value = "id", defaultValue = "") String id, Model model){
		model.addAttribute("id", id);
		return "system/newframe/subindex";
	}
	
	/**
	 * 二级菜单主页
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="subpmenuList",method = RequestMethod.GET)
	public String subpmenuList(@RequestParam(value = "id", defaultValue = "") String id, Model model){
		model.addAttribute("pid", id);
		return "system/newframe/subpmenuList";
	}
	

	/**
	 * 菜单图标下拉选择
	 */
	@RequestMapping("getBigIcons")
	@ResponseBody
	public String getBigIcons(HttpServletRequest request) {
		
		JSONArray  alist = new JSONArray();
		
		String bathpath = request.getServletContext().getRealPath("/");
		
		String imagedir = bathpath+"\\static\\model\\main\\images";
		
		File filedir = new File(imagedir);
		
		if(filedir.exists() && filedir.isDirectory())
		{
			File[] filelist = filedir.listFiles();
			if(filelist.length > 0)
			{
				for(File f : filelist)
				{
					JSONObject  result = new JSONObject();
					result.put("text", f.getName());
					result.put("value", f.getName());
					alist.add(result);
				}
			}
		}
		return alist.toJSONString();
	}
}
