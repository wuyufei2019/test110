package com.cczu.sys.system.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.system.entity.CompRolePermission;
import com.cczu.sys.system.entity.Permission;

/**
 * @description 企业内部角色与权限关系DAO
 * @author jason
 * @date 2017年12月26日
 */
@Repository
public class CompRolePermissionDao extends HibernateDao<CompRolePermission, Integer>{
	
	/**
	 * 查询角色拥有的权限id
	 * @param roleId
	 * @return 结果集合
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> findPermissionIds(Integer roleId){
		String hql="select rp.permission.id from CompRolePermission rp where rp.compRole.id=?0";
		Query query= createQuery(hql, roleId);
		return query.list();
	}

	/**
	 * 删除角色权限
	 * @param roleId
	 * @param permissionId
	 */
	public void deleteRP(Integer roleId,Integer permissionId){
		String hql="delete CompRolePermission rp where rp.compRole.id=?0 and rp.permission.id=?1";
		batchExecute(hql, roleId,permissionId);
	}
	/**
	 * 删除某一角色拥有的所有权限
	 * @param roleId
	 */
	public void deleteALlPerByRole(Integer roleId){
		String hql="delete CompRolePermission rp where rp.compRole.id=?0";
		batchExecute(hql, roleId);
	}

	
	
	
	
	
	public String getSqlContent(){
		StringBuffer sql=new StringBuffer();
		sql.append("select a.* from (select ROW_NUMBER () over(partition by p.id,p.ICON order by p.id) r,p.* from t_permission p ");
		sql.append("INNER JOIN t_comp_role_permission rp on p.ID=rp.PERMISSION_ID ");
		sql.append("INNER JOIN t_comp_role r ON r.id=rp.COMPROLE_ID ");
		sql.append("INNER JOIN t_comp_user_role ur ON ur.role_id =rp.COMPROLE_ID ");
		sql.append("INNER JOIN t_user u ON u.id = ur.USER_ID ");
		return sql.toString();
	}
	
	
	/**
	 * 查询企业用户拥有的企业内部角色对应权限集合
	 * @param userId
	 * @return 结果集合
	 */
	@SuppressWarnings("unchecked")
	public List<Permission> findPermissionByUid(Integer userId){
		String sql=getSqlContent();
		sql+= " where u.id=?0  )a where a.r=1 ORDER BY a.sort" ;
		SQLQuery sqlQuery=createSQLQuery(sql,userId);
		sqlQuery.addEntity(Permission.class);
		return sqlQuery.list();
	}
	
	/**
	 * 查询企业用户拥有的企业内部角色对应菜单集合
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Permission> findMenuByUid(Integer userId){
		String sql=getSqlContent();
		sql+="where p.TYPE='F' and u.id=?0 )a where a.r=1 ORDER BY a.sort";
		SQLQuery sqlQuery=createSQLQuery(sql.toString(), userId);
		sqlQuery.addEntity(Permission.class);
		return sqlQuery.list();
	}
	
	/**
	 * 根据菜单id查询该菜单下所有的子菜单（化工企业版）
	 * @param userId
	 * @return 菜单集合
	 */
	@SuppressWarnings("unchecked")
	public List<Permission> findHgqyChildMenu(int userid,int id){
		StringBuffer sb=new StringBuffer();
		sb.append("with tmpdb as( SELECT * from t_permission where id=?0 and type='f' UNION all SELECT b.* from tmpdb a,t_permission b where b.type='f' and a.id=b.pid)");
		sb.append("SELECT a.* FROM ( SELECT ROW_NUMBER () over(partition by p.id order by p.id) r,p.* from tmpdb p INNER JOIN t_comp_role_permission rp on p.ID=rp.PERMISSION_ID INNER JOIN t_comp_role r ON  r.id=rp.COMPROLE_ID");
		sb.append(" INNER JOIN t_comp_user_role  ur ON ur.ROLE_ID =rp.COMPROLE_ID INNER JOIN t_user u ON u.id = ur.USER_ID where p.TYPE='F' and u.id=?1 ) a WHERE a.r = 1 ORDER BY a.sort");
		SQLQuery sqlQuery=createSQLQuery(sb.toString(),id,userid);
		sqlQuery.addEntity(Permission.class);
		//sqlQuery.setCacheable(true);
		return sqlQuery.list();
	}
	
}
