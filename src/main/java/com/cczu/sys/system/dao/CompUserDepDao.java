package com.cczu.sys.system.dao;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.system.entity.CompUserDep;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户角色DAO
 * @author jason
 * @date 2017年5月31日
 */
@Repository
public class CompUserDepDao extends HibernateDao<CompUserDep, Integer> {

	/**
	 * 删除用户角色
	 * @param userId
	 */
	public void deleteUD(long userId){
		String hql="delete CompUserDep ud where ud.ID1=?0";
		batchExecute(hql, userId);
	}
	
	/**
	 * 删除用户角色
	 * @param userId
	 * @param roleId
	 */
	public void deleteUD(Integer userId,Integer depId){
		String hql="delete CompUserDep ud where ud.ID1=?0 and ud.ID2=?1";
		batchExecute(hql, userId,depId);
	}
	
	/**
	 * 查询用户拥有的角色id集合
	 * @param userId
	 * @return 结果集合
	 */
	@SuppressWarnings("unchecked")
	public List<Long> findDepIds(Integer userId){
		long Id=userId;
		String hql="select ud.ID2 from CompUserDep ud where ud.ID1=?0";
		Query query= createQuery(hql, Id);
		return query.list();
	}
	
}
