package com.cczu.sys.system.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.cczu.sys.system.entity.*;
import com.cczu.sys.system.service.*;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
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

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 用户controller
 * @author jason
 * @date 2017年5月31日
 */
@Controller
@RequestMapping("system/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	
	@Autowired
	private RoleService roleService;

	@Autowired
	private CompUserRoleService compUserRoleService;

	@Autowired
	private CompUserDepService compUserDepService;

	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		String returnstr="system/userList";
		if("1".equals(sessionuser.getUsertype())){
			model.addAttribute("ygym", request.getParameter("ygym"));
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			returnstr = (be!=null? "system/comp/user/userList" : "../error/001");
		}
		return returnstr;
		
	}

	/**
	 * 获取用户json
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value="json",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();

		Page<User> page = getPage(request);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", page.getPageNo());
		map.put("pageSize", page.getPageSize());
		if(StringUtils.isNotEmpty(request.getParameter("filter_LIKES_name")))
			map.put("name", request.getParameter("filter_LIKES_name"));
		if(StringUtils.isNotEmpty(request.getParameter("filter_LIKES_loginName")))
			map.put("loginName", request.getParameter("filter_LIKES_loginName"));
		if(StringUtils.isNotEmpty(request.getParameter("filter_LIKES_phone")))
			map.put("phone", request.getParameter("filter_LIKES_phone"));
		if(StringUtils.isNotEmpty(request.getParameter("filter_GTD_createDate")))
			map.put("createDatestart", request.getParameter("filter_GTD_createDate"));
		if(StringUtils.isNotEmpty(request.getParameter("filter_LTD_createDate")))
			map.put("createDateEnd", request.getParameter("filter_LTD_createDate"));
		if(StringUtils.isNotEmpty(request.getParameter("filter_EQS_usertype")))
			map.put("usertype", request.getParameter("filter_EQS_usertype"));
		if(StringUtils.isNotEmpty(request.getParameter("filter_EQS_departmen")))
			map.put("departmen", request.getParameter("filter_EQS_departmen"));
		if(StringUtils.isNotEmpty(request.getParameter("filter_LIKES_nbjs")))
			map.put("nbjs", request.getParameter("filter_LIKES_nbjs"));
		
		//判断用户部门权限
		Department dep=sessionuser.getDep();
		if(dep!=null){
			String qxbs=dep.getM4();
			if(qxbs.equals("1"))//本级
				map.put("depcode1", dep.getCode());
			if(qxbs.equals("2"))//下级
				map.put("depcode2", dep.getCode());
		}
		
		if(sessionuser.getUsertype().equals("9")){//系统管理员
			List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
			page = userService.search(page, filters);
			return getEasyUIData(page);
		}else if(sessionuser.getUsertype().equals("0")){//安监
			map.put("xzqy", sessionuser.getXzqy());
			return userService.searchData(map);
		}else if(sessionuser.getUsertype().equals("1")){//企业
			map.put("qyid", sessionuser.getQyid());
			return userService.searchData(map);
		}else
			return null;
		
	}

	/**
	 * 添加用户跳转
	 *
	 * @param model
	 */
	@RequiresPermissions("sys:user:add")
	@RequestMapping(value = "create/{flg}", method = RequestMethod.GET)
	public String createForm(@PathVariable("flg") String flg,Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		User user=new User();
		switch(sessionuser.getUsertype()){
			case "0"://安监用户
				user.setXzqy(sessionuser.getXzqy());
				model.addAttribute("usertype", "0");//安监管理员操作
				break;
			case "1"://企业用户
				user.setId2(sessionuser.getQyid());
				model.addAttribute("usertype", "1");//企业管理员操作
				break;
			case "9"://系统管理员
				model.addAttribute("usertype", "9");//系统管理员操作
				break;
		}
		//判断用户部门权限
		Department dep=sessionuser.getDep();
		if(dep!=null)
			model.addAttribute("depid",dep.getId());

		model.addAttribute("user",user);
		model.addAttribute("action", "create");
		return "system/userForm";
	}

	/**
	 * 添加用户
	 * @param user
	 * @param roleid
	 * @return
	 */
	@RequiresPermissions("sys:user:add")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public String create(@Valid User user,@RequestParam("roleid") String roleid,String bmids) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		user.setUid((long)sessionuser.getId());
		user.setElecsignature(user.getSeal());//电子签章赋值，用于适配中天和禧玛诺的模块
		userService.save(user);

		if(!StringUtils.isEmpty(bmids)){//bmids不为空
			addUserDp(user.getId(),bmids);
		}

		userService.updateUser(user);

		switch(sessionuser.getUsertype()){
			case "1"://企业用户
				String[] roleids=roleid.split(",");
				for(int i=0;i<roleids.length;i++){
					CompUserRole comUserRole = compUserRoleService.getCompUserRole(user.getId(),Integer.parseInt(roleids[i]));
					compUserRoleService.save(comUserRole);
				}
				break;
			case "9"://系统管理员
				userRoleService.updateUserRole(user.getId(), userRoleService.getRoleIdList(user.getId()),Arrays.asList(Integer.parseInt(roleid)));
				break;
		}
		return "success";
	}

	/**
	 * 修改用户跳转
	 *
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:user:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		User user=userService.getUserById(id);
//		Set<UserRole> userRoles=user.getUserRoles();
//		for(UserRole ur:userRoles){
//			if(ur.getRole().getRoleCode().equals("ajcountyadmin")||ur.getRole().getRoleCode().equals("ajcounty")){
//				model.addAttribute("usertype", "1");
//				break;
//			}
//		}

//		//安监用户无权修改企业用户信息
//		if(sessionuser.getUsertype().equals("0")&&user.getUsertype().equals("1")){
//			return "../error/403";
//		}

		model.addAttribute("usertype", sessionuser.getUsertype());
		List<CompUserRole> list =compUserRoleService.getCompUserRoleListByUserID(user);
		if(list.size()>0) {
			String roleid="";
			for(CompUserRole ur:list){
				roleid+=ur.getCompRole().getId()+",";
			}
			roleid=roleid.substring(0,roleid.length()-1);
			model.addAttribute("roleid", roleid);
		}

		List<Long> userdplist = compUserDepService.getCompDepIdList(user.getId());
		String bmids = "";
		if(userdplist!=null&&userdplist.size()>0){
			for (Long udl : userdplist) {
				bmids+=udl+",";
			}
			bmids = bmids.substring(0, bmids.length()-1);
		}
		model.addAttribute("bmids", bmids);

		model.addAttribute("user", user);
		model.addAttribute("action", "update");
		return "system/userForm";
	}

	/**
	 * 修改用户
	 * @param user
	 * @param roleid
	 * @return
	 */
	@RequiresPermissions("sys:user:update")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(User user,@RequestParam("roleid") String roleid,String bmids) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		String[] roleids=roleid.split(",");
		List<Integer> roleidlist=new ArrayList<Integer>();
		for(int i=0;i<roleids.length;i++){
			roleidlist.add(Integer.parseInt(roleids[i]));
		}
		user.setElecsignature(user.getSeal());//电子签章赋值，用于适配中天和禧玛诺的模块
		userService.updateUser(user);

		//删除原有的用户部门信息
		compUserDepService.deleteByUserID(user.getId());
		if(!StringUtils.isEmpty(bmids)){//bmids不为空
			addUserDp(user.getId(),bmids);
		}


		log.info(sessionuser.getLoginName()+":  用户信息  【修改操作】，修改的用户名为:"+user.getLoginName()+".昵称为:"+user.getName());
		switch(sessionuser.getUsertype()){
			case "1"://企业用户
				compUserRoleService.updateCompUserRole(user.getId(), compUserRoleService.getCompRoleIdList(user.getId()),roleidlist);
				break;
			case "9"://系统管理员
				userRoleService.updateUserRole(user.getId(), userRoleService.getRoleIdList(user.getId()),roleidlist);
				break;
		}
		return "success";
	}

	/**
	 * 添加用户部门中间表
	 */
	public void addUserDp(long userid,String bmids){
		String[] bmids2 = bmids.split(",");
		for (String bmid : bmids2) {
			CompUserDep ud = new CompUserDep();
			ud.setID1(userid);
			ud.setID2(Long.valueOf(bmid));
			compUserDepService.save(ud);
		}
	}

	/**
	 * 删除用户
	 * 
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("sys:user:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		String str="删除失败！";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(String i:arrids){
			if(sessionuser.getId().toString().equals(i)){
				str="不能删除自己！";
				break;
			}
			//权限不足
			if( userService.isSupervisor(Integer.valueOf(i)) ){
				str= "该账号为系统管理员，你没有删除权限！";
				break;
			}else{
				userService.delete(Integer.valueOf(i) );
				str= "删除成功！";
			}
		}
		return str;
	}

	/**
	 * 禁用用户
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("sys:user:disable")
	@RequestMapping(value = "disable/{ids}")
	@ResponseBody
	public String disableUser(@PathVariable("ids") String ids) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		String str ="禁用成功！";
		try {
			String[] arrids = ids.split(",");
			for (String i : arrids) {
				int id = StringUtils.toInteger(i);
				if (sessionuser.getId().equals(id)) {
					str = "不能禁用自己！";
				} else if (userService.isSupervisor(id)) {
					str = "该账号为系统管理员，你没有禁用权限！";
				} else {
					userService.disable(id);
				}
			}
		}catch (Exception ex){
			ex.printStackTrace();
			str = "操作存在异常！";
		}
		return str;
	}
	/**
	 * 启用用户
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("sys:user:enable")
	@RequestMapping(value = "enable/{ids}")
	@ResponseBody
	public String enableUser(@PathVariable("ids") String ids) {
		String str="恢复成功！";
		String[] arrids = ids.split(",");
		for (String i : arrids) {
			userService.enable(Integer.valueOf(i) );
		}
		return str;
	}

	/**
	 * 弹窗页-用户拥有的角色
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:user:roleView")
	@RequestMapping(value = "{userId}/userRole")
	public String getUserRole(@PathVariable("userId") Integer id, Model model) {
		model.addAttribute("userId", id);
		return "system/userRoleList";
	}

	/**
	 * 获取用户拥有的角色ID集合
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("sys:user:roleView")
	@RequestMapping(value = "{id}/role")
	@ResponseBody
	public List<Integer> getRoleIdList(@PathVariable("id") Integer id) {
		return userRoleService.getRoleIdList(id);
	}

	/**
	 * 修改用户拥有的角色
	 * 
	 * @param id
	 * @param newRoleList
	 * @return
	 */
	@RequiresPermissions("sys:user:roleUpd")
	@RequestMapping(value = "{id}/updateRole")
	@ResponseBody
	public String updateUserRole(@PathVariable("id") Integer id,@RequestBody List<Integer> newRoleList) {
		userRoleService.updateUserRole(id, userRoleService.getRoleIdList(id),newRoleList);
		User user=userService.get(id);
		userService.update(user);
		return "success";
	}

	/**
	 * 修改密码跳转
	 */
	@RequestMapping(value = "updatePwd", method = RequestMethod.GET)
	public String updatePwdForm(Model model, HttpSession session) {
		model.addAttribute("user", (User) session.getAttribute("user"));
		return "system/updatePwd";
	}

	/**
	 * 修改密码
	 */
	@RequestMapping(value = "updatePwd", method = RequestMethod.POST)
	@ResponseBody
	public String updatePwd(String oldPassword,@Valid @ModelAttribute @RequestBody User user, HttpSession session) {
		if (userService.checkPassword((User) session.getAttribute("user"),oldPassword)) {
			userService.updatePwd(user);
			session.setAttribute("user", user);
			return "密码修改成功！";
		} else {
			return "密码修改失败！";
		}

	}
	
	/**
	 * 重置密码（000000）
	 */
	@RequestMapping(value = "resetPwd", method = RequestMethod.POST)
	@ResponseBody
	public String resetPwd(String ids) {
		try {
			String[] arrids = ids.split(",");
			for (String id : arrids) {
				User user=userService.get(Integer.valueOf(id));
				user.setPlainPassword("000000");
				userService.updatePwd(user);
			}
		}catch (Exception ex){
			log.info("批量重置密码出错！"+ex.getMessage());
			return "批量重置密码出错！";
		}

		return "密码重置成功！";
	}
	
	
	/**
	 * 用户个人信息修改页面
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "infor", method = RequestMethod.GET)
	public String updateInfor(Model model) {
		model.addAttribute("user", userService.get(UserUtil.getCurrentUser().getId()));
		return "system/userInfoEdit";
	}
	
	
	/**
	 * Ajax请求校验loginName是否唯一（添加时）。
	 */
	@RequestMapping(value = "checkLoginName")
	@ResponseBody
	public String checkLoginName(String loginName) {
		if (userService.getUser(loginName) == null) {
			return "true";
		} else {
			return "false";
		}
	}
	
	/**
	 * Ajax请求校验loginName是否唯一（修改时）。
	 */
	@RequestMapping(value = "checkLoginName2/{userId}")
	@ResponseBody
	public String checkLoginName2(@PathVariable("userId") Integer id, String loginName) {
		User u= userService.getUser(loginName);
		if (u == null) {
			return "true";
		} else if(u.getId().equals(id)) {
			return "true";
		}else{
			return "false";
		}
	}

	/**
	 * ajax请求校验原密码是否正确
	 * 
	 * @param oldPassword
	 * @param request
	 * @return
	 */
//	@RequiresPermissions("sys:user:update")
	@RequestMapping(value = "checkPwd")
	@ResponseBody
	public String checkPwd(String oldPassword, HttpSession session) {
		if (userService.checkPassword((User) session.getAttribute("user"),oldPassword)) {
			return "true";
		} else {
			return "false";
		}
	}
	
	/**
	 * 所有RequestMapping方法调用前的Model准备方法
	 * Preparable二次部分绑定的效果,先根据form的id从数据库查出Task对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void getUser(@RequestParam(value = "id", defaultValue = "-1") Integer id,Model model) {
		if (id != -1) {
			model.addAttribute("user", userService.get(id));
		}
	}

	
	/**
	 * 用户字典显示
	 * @param {json}
	 */
	@RequestMapping(value="/userjson" , method = RequestMethod.GET)
	@ResponseBody
	public String getJson() {
		return userService.getUserJson();
	}
	
	
	/**
	 * 设置普通网格员为管理员
	 * @param model
	 * @return
	 */
	@RequiresRoles(value={"admin","superadmin","ajcountyadmin","ajtownadmin"},logical=Logical.OR)
	@RequestMapping(value="settomanager/{id}",method = RequestMethod.GET)
	@ResponseBody
	public String setUserToManager(@PathVariable("id") Integer userid,Model model) {
		//获取该用户角色代码
		List<Role> rolelist=roleService.findRoleById(userid);
		String rolecode=rolelist.get(0).getRoleCode();
		List<Integer> newRoleList=new ArrayList<>();
		if(rolecode.equals("ajtown")){//镇安监普通网格员
			Role role= roleService.findRoleByRoleCode("ajtownadmin");
			newRoleList.add(role.getId());
			userRoleService.updateUserRole(userid, userRoleService.getRoleIdList(userid),newRoleList);
		}else if(rolecode.equals("ajcounty")){//市安监普通网格员}
			Role role= roleService.findRoleByRoleCode("ajcountyadmin");
			newRoleList.add(role.getId());
			userRoleService.updateUserRole(userid, userRoleService.getRoleIdList(userid),newRoleList);
		}else{
			return "error";
		}
		return "success";
	}
	
	/**
	 * 自定义菜单页面
	 * @param id,model 
	 */
	@RequestMapping(value = "selindex", method = RequestMethod.GET)
	public String selindex(Model model) {
		model.addAttribute("url","createsel");
		return "common/menuform";
	}
	
	/**
	 * 添加或修改所选菜单
	 * @param request,model
	 */
	@RequestMapping(value = "selmenu", method = RequestMethod.POST)
	@ResponseBody
	public String selmenu(@Valid User_Define ud, Model model) {
		String datasuccess="success";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		ud.setUserid(Long.parseLong(sessionuser.getId()+""));
		userService.addSelInfo(ud);
		return datasuccess;
		
	}

	/**
	 * 根据部门id,企业id，企业内部角色查询人员
	 * @param request
	 */
	@RequestMapping(value = "ryjson")
	@ResponseBody
	public String getUserJson(HttpServletRequest request,
							  @RequestParam(value="depid",defaultValue = "") String depid,
							  @RequestParam(value="qyid",defaultValue = "0") String qyid,
							  @RequestParam(value="comprolecode",defaultValue = "") String comprolecode) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if("1".equals(sessionuser.getUsertype())) {//企业
			qyid=sessionuser.getQyid()+"";
		}
		return userService.findUserByContent(qyid,depid,comprolecode);
	}
}
