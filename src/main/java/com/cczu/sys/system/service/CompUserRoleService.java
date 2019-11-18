package com.cczu.sys.system.service;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.service.BaseService;
import com.cczu.sys.system.dao.CompUserRoleDao;
import com.cczu.sys.system.entity.CompRole;
import com.cczu.sys.system.entity.CompUserRole;
import com.cczu.sys.system.entity.Permission;
import com.cczu.sys.system.entity.User;

/**
 * @description 企业用户和内部角色关系service
 * @author jason
 * @date 2017年12月26日
 */
@Service
@Transactional(readOnly = true)
public class CompUserRoleService extends BaseService<CompUserRole, Integer> {

	@Autowired
	private CompUserRoleDao compuserRoleDao;

	@Override
	public HibernateDao<CompUserRole, Integer> getEntityDao() {
		return compuserRoleDao;
	}

	/**
	 * 添加修改用户角色
	 * 
	 * @param id
	 * @param oldList
	 * @param newList
	 */
	@Transactional(readOnly = false)
	public void updateCompUserRole(Integer userId, List<Integer> oldList,List<Integer> newList) {
		// 是否删除
		for (int i = 0, j = oldList.size(); i < j; i++) {
			if (!newList.contains(oldList.get(i))) {
				compuserRoleDao.deleteCompUR(userId, oldList.get(i));
			}
		}

		// 是否添加
		for (int m = 0, n = newList.size(); m < n; m++) {
			if (!oldList.contains(newList.get(m))) {
				compuserRoleDao.save(getCompUserRole(userId, newList.get(m)));
			}
		}
	}

	/**
	 * 构建CompUserRole
	 * 
	 * @param userId
	 * @param roleId
	 * @return UserRole
	 */
	public CompUserRole getCompUserRole(Integer userId, Integer compRoleId) {
		CompUserRole ur = new CompUserRole();
		ur.setUser(new User(userId));
		ur.setCompRole(new CompRole(compRoleId));
		return ur;
	}

	/**
	 * 获取用户拥有角色id集合
	 * @param userId
	 * @return 结果集合
	 */
	public List<Integer> getCompRoleIdList(Integer userId) {
		return compuserRoleDao.findCompRoleIds(userId);
	}
	
	/**
	 * 根据角色id获取UserRole集合
	 * 
	 * @param roleID
	 * @return 结果集合
	 */
	public List<CompUserRole> getCompUserRoleListByroleID(Integer compRoleID) {
		return compuserRoleDao.findBy("role", new CompRole(compRoleID));
				
	}
	/**
	 * 根据userid获取CompUserRole集合
	 *
	 * @param roleID
	 * @return 结果集合
	 */
	public List<CompUserRole> getCompUserRoleListByUserID(User user) {
		return compuserRoleDao.findBy("user", user);

	}
	/**
	 * 根据userid获取CompRole集合
	 *
	 * @param roleID
	 * @return 结果集合
	 */
	public List<CompRole> getCompRoleListByUserID(Integer userId) {
		StringBuffer sql=new StringBuffer();
		sql.append("select r.* from t_user u left join t_comp_user_role ur on u.id =ur.user_id left join t_comp_role r on ur.role_id =r.id  ");
		sql.append("where u.id=?0  order by id");
		SQLQuery sqlQuery=compuserRoleDao.createSQLQuery(sql.toString(), userId );
		sqlQuery.addEntity(CompRole.class);
		return sqlQuery.list();

	}

}