package com.cczu.sys.system.service;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.service.BaseService;
import com.cczu.sys.system.dao.CompRolePermissionDao;
import com.cczu.sys.system.entity.CompRole;
import com.cczu.sys.system.entity.CompRolePermission;
import com.cczu.sys.system.entity.Permission;
import com.cczu.sys.system.service.ShiroRealm;

/**
 * @description 企业内部角色权限service
 * @author jason
 * @date 2017年12月26日
 */
@Service
@Transactional(readOnly=true)
public class CompRolePermissionService extends BaseService<CompRolePermission, Integer> {
	
	@Autowired
	private CompRolePermissionDao rolePermissionDao;
	
	@Override
	public HibernateDao<CompRolePermission, Integer> getEntityDao() {
		return rolePermissionDao;
	}
	
	/**
	 * 获取角色权限id集合
	 * @param id
	 * @return List
	 */
	public List<Integer> getPermissionIds(Integer roleId){
		return rolePermissionDao.findPermissionIds(roleId);
	}
	
	/**
	 * 修改角色权限
	 * @param id
	 * @param oldList
	 * @param newList
	 */
	@Transactional(readOnly = false)
	public void updateRolePermission(Integer id,List<Integer> oldList,List<Integer> newList){
		//是否删除
		for(int i=0,j=oldList.size();i<j;i++){
			if(!newList.contains(oldList.get(i))){
				rolePermissionDao.deleteRP(id,oldList.get(i));
			}
		}
		
		//是否添加
		for(int i=0,j=newList.size();i<j;i++){
			if(!oldList.contains(newList.get(i))){
				rolePermissionDao.save(getRolePermission(id,newList.get(i)));
			}
		}
	}
	
	/**
	 * 清空该角色用户的权限缓存
	 */
	public void clearUserPermCache(PrincipalCollection pc){
		RealmSecurityManager securityManager =  (RealmSecurityManager) SecurityUtils.getSecurityManager();
		ShiroRealm userRealm = (ShiroRealm) securityManager.getRealms().iterator().next();
	    userRealm.clearCachedAuthorizationInfo(pc);
	}
	
	/**
	 * 构造角色权限对象
	 * @param roleId
	 * @param permissionId
	 * @return RolePermission
	 */
	private CompRolePermission getRolePermission(Integer roleId,Integer permissionId){
		CompRolePermission rp=new CompRolePermission();
		rp.setCompRole(new CompRole(roleId));
		rp.setPermission(new Permission(permissionId));
		return rp;
	}
	
	/**
	 * 获取企业用户拥有的权限集合
	 * @param userId
	 * @return 结果集合
	 */
	public List<Permission> getPermissionList(Integer userId) {
		return rolePermissionDao.findPermissionByUid(userId);
	}

	/**
	 * 删除角色拥有的权限
	 * @param roleId
	 */
	public void deleteALlPerByRole(Integer roleId) {
		rolePermissionDao.deleteALlPerByRole(roleId);
	}

}
