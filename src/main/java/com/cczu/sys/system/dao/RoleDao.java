package com.cczu.sys.system.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.system.entity.Role;
/**
 * 角色DAO
 * @author jason
 * @date 2017年5月31日
 */
@Repository
@SuppressWarnings("unchecked")
public class RoleDao extends HibernateDao<Role, Integer>{
	/**
	 * 查询用户的角色
	 * @param userId 用户id
	 * @return 结果集合
	 */
	public List<Role> findRoleById(Integer userId){
		
		String sql=" select tr.* from t_role tr "
				+ " inner join t_user_role tur on tr.ID = tur.ROLE_ID "
				+ " inner join t_user tu on tur.USER_ID=tu.ID "
				+ " where tu.ID="+userId ;
		SQLQuery sqlQuery=createSQLQuery(sql);
		sqlQuery.addEntity(Role.class);
		return sqlQuery.list();
	}

	public Role findRoleByRoleCode(String roleCode) {
		String sql="select * from t_role where role_code='"+roleCode+"' ";
		SQLQuery sqlQuery=createSQLQuery(sql).addEntity(Role.class);
		return (Role) sqlQuery.list().get(0);
	}
}
