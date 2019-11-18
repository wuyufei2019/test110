package com.cczu.sys.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.service.BaseService;
import com.cczu.sys.system.dao.RoleDao;
import com.cczu.sys.system.entity.Role;

/**
 * 角色service
 * @author jason
 * @date 2017年5月31日
 */
@Service
@Transactional(readOnly = true)
public class RoleService extends BaseService<Role, Integer> {

	@Autowired
	private RoleDao roleDao;

	@Override
	public HibernateDao<Role, Integer> getEntityDao() {
		return roleDao;
	}
	
	/**
	 * 查询用户的角色
	 * @param userId 用户id
	 * @return 结果集合
	 */
	public List<Role> findRoleById(Integer userId){
		return roleDao.findRoleById(userId);
	}
	
	/**
	 * 查询用户的角色
	 * @param roleCode
	 * @return 结果集合
	 */
	public Role findRoleByRoleCode(String roleCode){
		return roleDao.findRoleByRoleCode(roleCode);
	}
}
