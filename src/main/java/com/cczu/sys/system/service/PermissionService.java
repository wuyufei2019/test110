package com.cczu.sys.system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.service.BaseService;
import com.cczu.sys.system.dao.CompRolePermissionDao;
import com.cczu.sys.system.dao.PermissionDao;
import com.cczu.sys.system.entity.Permission;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 权限service
 * @author jason
 * @date 2017年5月31日
 */
@Service
@Transactional(readOnly=true)
public class PermissionService extends BaseService<Permission, Integer>{
	
	@Autowired
	private PermissionDao permissionDao;
	
	@Autowired
	private CompRolePermissionDao compRolePermissionDao;
	
	@Override
	public HibernateDao<Permission, Integer> getEntityDao() {
		return permissionDao;
	}
	
	/**
	 * 添加菜单基础操作
	 * @param pid 菜单id
	 * @param pName 菜单权限标识名
	 */
	@Transactional(readOnly = false)
	public void addBaseOpe(Integer pid,String pClassName){
		List<Permission> pList=new ArrayList<Permission>();
		pList.add(new Permission(pid, "添加", "O", "", "sys:"+pClassName+":add"));
		pList.add(new Permission(pid, "删除", "O", "", "sys:"+pClassName+":delete"));
		pList.add(new Permission(pid, "修改", "O", "", "sys:"+pClassName+":update"));
		pList.add(new Permission(pid, "查看", "O", "", "sys:"+pClassName+":view"));
		
		//添加没有的基本操作
		List<Permission> existPList=getMenuOperation(pid);
		for(Permission permission:pList){
			boolean exist=false;
			for(Permission existPermission:existPList){
				if(permission.getPermCode().equals(existPermission.getPermCode())){
					exist=true;
					break;
				}else{
					exist=false;
				}
			}
			if(!exist)
				save(permission);
		}
	}
	
	/**
	 * 获取角色拥有的权限集合
	 * @param userId
	 * @return 结果集合
	 */
	public List<Permission> getPermissions(Integer userId){
		return permissionDao.findPermissions(userId);
	}
	
	/**
	 * 根据用户获取菜单集合
	 * @param userId
	 * @return
	 */
	public List<Permission> getPermissionsnew(Integer userId){
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		
		if(sessionuser.getUsertype().equals("1")){
			//如果是企业普通用户  则查询所在企业内部的权限信息
			List<Role> rolelit=sessionuser.getRoles();
			for(Role role:rolelit){
			 if (role.getRoleCode().equals("company")) {
				 return compRolePermissionDao.findMenuByUid(userId);
			 }
			}
			return permissionDao.findPermissionsnew(userId);
		}
		else
			return permissionDao.findPermissionsnew(userId);
	}
	
	/**
	 * 根据用户获取菜单集合（化工企业版）
	 * @param userId
	 * @param pid
	 * @return
	 */
	public List<Permission> getHgqyPermissions(Integer userId,int pid){
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		
		if(sessionuser.getUsertype().equals("1")){
			//如果是企业普通用户  则查询所在企业内部的权限信息
			List<Role> rolelit=sessionuser.getRoles();
			for(Role role:rolelit){
			 if (role.getRoleCode().equals("company")) {
				 return compRolePermissionDao.findHgqyChildMenu(userId,pid);
			 }
			}
			return permissionDao.findChildMenusById(userId,pid);
		}
		else
			return null;
	}
	
	/**
	 * 获取角色拥有的一级菜单的权限集合
	 * @return 结果集合
	 */
	public List<Permission> getFirstMenuPermissions(Integer userId){
		return permissionDao.findFirstMenuPermissions(userId);
	}
	/**
	 * 根据菜单id获取该菜单下所有子菜单（根据当前用户角色）
	 * @return 结果集合
	 */
	public List<Permission> getChildMenusById(Integer userId,int pid){
		return permissionDao.findChildMenusById(userId,pid);
	}
	
	/**
	 * 获取角色拥有的菜单
	 * @param userId
	 * @return 菜单集合
	 */
	public List<Permission> getMenus(Integer userId){
		return permissionDao.findMenus(userId);
	}
	
	/**
	 * 获取所有菜单
	 * @return 菜单集合
	 */
	public List<Permission> getMenus(){
		return permissionDao.findMenus();
	}
	
	/**
	 * 获取菜单下的操作
	 * @param pid
	 * @return 操作集合
	 */
	public List<Permission> getMenuOperation(Integer pid){
		return permissionDao.findMenuOperation(pid);
	}

	/**
	 * 删除菜单下对应的权限
	 * @param id Permission
	 */
	public void deleteMenuPermission(int id){
		permissionDao.deleteMenuPermission(id);
	}
	
	
	/**
	 * 查询拥有该权限的用户
	 * @param pid
	 */
	public List<Map<String,Object>> findUsersHasThisPer(Map<String,Object> mapData){
		return permissionDao.findUsersHasThisPer(mapData);
	}
	
	/**
	 * 初始化权限
	 * @param pid
	 */
	public void initPer( Permission permission) {
		//增删改查
		String permcode=permission.getPermCode();
		String description=permission.getDescription();
		permission.setName("增加");
		permission.setDescription(description+"增加");
		permission.setPermCode(permcode+"add");
		permissionDao.save(permission);
		permission.setId(null);
		permission.setName("修改");
		permission.setDescription(description+"修改");
		permission.setPermCode(permcode+"update");
		permissionDao.save(permission);
		permission.setId(null);
		permission.setName("查看");
		permission.setDescription(description+"查看");
		permission.setPermCode(permcode+"view");
		permissionDao.save(permission);
		permission.setId(null);
		permission.setName("删除");
		permission.setDescription(description+"删除");
		permission.setPermCode(permcode+"delete");
		permissionDao.save(permission);
		permission.setId(null);
		permission.setName("导出");
		permission.setDescription(description+"导出");
		permission.setPermCode(permcode+"export");
		permissionDao.save(permission);
		permission.setId(null);
		permission.setName("导入");
		permission.setDescription(description+"导入");
		permission.setPermCode(permcode+"exin");
		permissionDao.save(permission);
	}
}
