package com.cczu.sys.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.service.BaseService;
import com.cczu.sys.system.dao.UserRoleDao;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.entity.UserRole;

/**
 * 用户角色service
 * @author jason
 * @date 2017年5月31日
 */
@Service
@Transactional(readOnly = true)
public class UserRoleService extends BaseService<UserRole, Integer> {

	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	public HibernateDao<UserRole, Integer> getEntityDao() {
		return userRoleDao;
	}

	/**
	 * 添加修改用户角色
	 * 
	 * @param id
	 * @param oldList
	 * @param newList
	 */
	@Transactional(readOnly = false)
	public void updateUserRole(Integer userId, List<Integer> oldList,List<Integer> newList) {
		// 是否删除
		for (int i = 0, j = oldList.size(); i < j; i++) {
			if (!newList.contains(oldList.get(i))) {
				userRoleDao.deleteUR(userId, oldList.get(i));
			}
		}

		// 是否添加
		for (int m = 0, n = newList.size(); m < n; m++) {
			if (!oldList.contains(newList.get(m))) {
				userRoleDao.save(getUserRole(userId, newList.get(m)));
			}
		}
	}

	/**
	 * 构建UserRole
	 * 
	 * @param userId
	 * @param roleId
	 * @return UserRole
	 */
	private UserRole getUserRole(Integer userId, Integer roleId) {
		UserRole ur = new UserRole();
		ur.setUser(new User(userId));
		ur.setRole(new Role(roleId));
		return ur;
	}

	/**
	 * 获取用户拥有角色id集合
	 * 
	 * @param userId
	 * @return 结果集合
	 */
	public List<Integer> getRoleIdList(Integer userId) {
		return userRoleDao.findRoleIds(userId);
	}
	
	/**
	 * 根据角色id获取UserRole集合
	 * 
	 * @param roleID
	 * @return 结果集合
	 */
	public List<UserRole> getUserRoleListByroleID(Integer roleID) {
		return userRoleDao.findBy("role", new Role(roleID));
				
	}
	

}