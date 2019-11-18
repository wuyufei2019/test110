package com.cczu.sys.system.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.system.entity.CompUserRole;

/**
 * @description 企业用户与内部角色关系DAO
 * @author jason
 * @date 2017年12月26日
 */
@Repository
public class CompUserRoleDao extends HibernateDao<CompUserRole, Integer>{

	/**
	 * 删除用户角色
	 * @param userId
	 */
	public void deleteCompUR(Integer userId){
		String hql="delete CompUserRole ur where ur.user.id=?0";
		batchExecute(hql, userId);
	}
	
	/**
	 * 删除用户角色
	 * @param userId
	 * @param roleId
	 */
	public void deleteCompUR(Integer userId,Integer comproleId){
		String hql="delete CompUserRole ur where ur.user.id=?0 and ur.compRole.id=?1";
		batchExecute(hql, userId,comproleId);
	}
	
	/**
	 * 查询用户拥有的角色id集合
	 * @param userId
	 * @return 结果集合
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> findCompRoleIds(Integer userId){
		String hql="select ur.compRole.id from CompUserRole ur where ur.user.id=?0";
		Query query= createQuery(hql, userId);
		return query.list();
	}
	
}
