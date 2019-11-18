package com.cczu.sys.system.service;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.service.BaseService;
import com.cczu.sys.system.dao.CompUserDepDao;
import com.cczu.sys.system.entity.CompRole;
import com.cczu.sys.system.entity.CompUserDep;
import com.cczu.sys.system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description 企业用户和内部角色关系service
 * @author jason
 * @date 2017年12月26日
 */
@Service
@Transactional(readOnly = true)
public class CompUserDepService extends BaseService<CompUserDep, Integer> {

	@Autowired
	private CompUserDepDao compuserDepDao;

	@Override
	public HibernateDao<CompUserDep, Integer> getEntityDao() {
		return compuserDepDao;
	}

	/**
	 * 添加修改用户角色
	 * @param userId
	 * @param oldList
	 * @param newList
	 */
	@Transactional(readOnly = false)
	public void updateCompUserDep(Integer userId, List<Integer> oldList,List<Integer> newList) {
		// 是否删除
		for (int i = 0, j = oldList.size(); i < j; i++) {
			if (!newList.contains(oldList.get(i))) {
				compuserDepDao.deleteUD(userId, oldList.get(i));
			}
		}

		// 是否添加
		for (int m = 0, n = newList.size(); m < n; m++) {
			if (!oldList.contains(newList.get(m))) {
				compuserDepDao.save(getCompUserDep(userId, newList.get(m)));
			}
		}
	}

	/**
	 * 构建CompUserDep
	 *
	 * @param userId
	 * @param depId
	 * @return UserRole
	 */
	public CompUserDep getCompUserDep(long userId, long depId) {
		CompUserDep ud = new CompUserDep();
		ud.setID1(userId);
		ud.setID2(depId);
		return ud;
	}

	/**
	 * 获取用户拥有角色id集合
	 * @param userId
	 * @return 结果集合
	 */
	public List<Long> getCompDepIdList(Integer userId) {
		return compuserDepDao.findDepIds(userId);
	}
	
	/**
	 * 根据角色id获取UserRole集合
	 * 
	 * @param compRoleID
	 * @return 结果集合
	 */
	public List<CompUserDep> getCompUserDepListByroleID(Integer compRoleID) {
		return compuserDepDao.findBy("role", new CompRole(compRoleID));
				
	}
	/**
	 * 根据userid获取UserRole集合
	 *
	 * @param roleID
	 * @return 结果集合
	 */
	public List<CompUserDep> getCompUserDepListByUserID(User user) {
		return compuserDepDao.findBy("user", user);

	}

	/**
	 * 根据用户id删除用户与部门的关系表
	 * @param userId
	 */
	public void deleteByUserID(long userId){
		compuserDepDao.deleteUD(userId);
	}
}