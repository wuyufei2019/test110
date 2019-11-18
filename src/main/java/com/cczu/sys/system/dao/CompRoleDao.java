package com.cczu.sys.system.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.system.entity.CompRole;
import com.cczu.sys.system.entity.User;

/**
 * @description 企业内部角色DAO
 * @author jason
 * @date 2017年12月26日
 */
@Repository
@SuppressWarnings("unchecked")
public class CompRoleDao extends HibernateDao<CompRole, Integer>{
	/**
	 * 查询用户的角色
	 * @param userId 用户id
	 * @return 结果集合
	 */
	public List<CompRole> findCompRoleById(Integer userId){		
		String sql=" select tr.* from t_comp_role tr "
				+ " inner join t_comp_user_role tur on tr.ID = tur.ROLE_ID "
				+ " inner join t_user tu on tur.USER_ID=tu.ID "
				+ " where tu.ID="+userId ;
		SQLQuery sqlQuery=createSQLQuery(sql);
		sqlQuery.addEntity(CompRole.class);
		return sqlQuery.list();
	} 
	/**
	 * 根据角色编码查询角色
	 * @param roleCode
	 * @return
	 */
	public CompRole findCompRoleByRoleCode(String roleCode) {
		String sql="select * from t_comp_role where role_code='"+roleCode+"' ";
		SQLQuery sqlQuery=createSQLQuery(sql).addEntity(CompRole.class);
		return (CompRole) sqlQuery.list().get(0);
	}
	
	/**
	 * 根据企业内部角色编码和企业id 查询企业用户
	 * @param roleCode
	 * @param qyid
	 * @return
	 */
	public List<User> findUserByCompRoleCode(String roleCode, Long qyid) {
		String sql="select * from t_user where id in (select a.USER_ID from t_comp_user_role a left join t_comp_role b on a.ROLE_ID=b.id where b.ROLE_CODE=?0 AND b.qyid=?1 group by a.user_id) ";
		SQLQuery sqlQuery=createSQLQuery(sql,roleCode,qyid).addEntity(User.class);
		return sqlQuery.list();
	}
	
	/**
	 * 根据企业查询企业内部角色集合
	 */
	public List<Map<String, Object>> getCompRole(Long qyid) {
		String sql =" select id,name from t_comp_role where qyid= "+qyid ;
		SQLQuery sqlQuery=createSQLQuery(sql);
		sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return sqlQuery.list();
	}
}
