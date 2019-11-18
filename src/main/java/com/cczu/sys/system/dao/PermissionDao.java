package com.cczu.sys.system.dao;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.system.entity.Permission;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * 权限DAO
 * @author jason
 * @date 2017年5月31日
 */
@Repository
public class PermissionDao extends HibernateDao<Permission, Integer> {
	/**
	 * 查询用户拥有的权限
	 * @param userId 用户id
	 * @return 结果集合
	 */
	@SuppressWarnings("unchecked")
	public List<Permission> findPermissions(Integer userId){
		String sql=getSqlContent();
		sql+="where u.id=?0)a where a.r=1 order by a.sort";
		SQLQuery sqlQuery=createSQLQuery(sql, userId);
		sqlQuery.addEntity(Permission.class);
		//sqlQuery.setCacheable(true);
		return sqlQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Permission> findPermissionsnew(Integer userId){
		String sql=getSqlContent();
		sql+="where p.TYPE='F' and u.id=?0)a where a.r=1 order by a.sort";
		SQLQuery sqlQuery=createSQLQuery(sql.toString(), userId);
		sqlQuery.addEntity(Permission.class);
		//sqlQuery.setCacheable(true);
		return sqlQuery.list();
	}
	@SuppressWarnings("unchecked")
	public List<Permission> findFirstMenuPermissions(Integer userId){
		String sql=getSqlContent();
		sql+="where p.TYPE='F' and u.id=?0 and(pid='' or pid is null))a where a.r=1 order by a.sort";
		SQLQuery sqlQuery=createSQLQuery(sql.toString(), userId);
		sqlQuery.addEntity(Permission.class);
		//sqlQuery.setCacheable(true);
		return sqlQuery.list();
	}
	
	public String getSqlContent(){
		StringBuffer sql=new StringBuffer();
		sql.append("select a.* from (select ROW_NUMBER () over(partition by p.id,p.ICON order by p.id) r,p.* from t_permission p ");
		sql.append("INNER JOIN t_role_permission rp on p.ID=rp.PERMISSION_ID ");
		sql.append("INNER JOIN t_role r ON  r.id=rp.ROLE_ID ");
		sql.append("INNER JOIN t_user_role  ur ON ur.ROLE_ID =rp.ROLE_ID ");
		sql.append("INNER JOIN t_user u ON u.id = ur.USER_ID ");
		return sql.toString();
	}
	
	/**
	 * 查询所有的菜单
	 * @param userId
	 * @return 菜单集合
	 */
	@SuppressWarnings("unchecked")
	public List<Permission> findMenus(){
		StringBuffer sb=new StringBuffer();
		sb.append("select p.ID id,p.PID pid,p.NAME name,p.URL url,p.ICON icon,p.SORT sort,p.DESCRIPTION description from t_permission p ");
		sb.append("where p.TYPE='F' or p.TYPE='AF' order by p.sort");
		SQLQuery sqlQuery=createSQLQuery(sb.toString());
		sqlQuery.addScalar("id",StandardBasicTypes.INTEGER );
		sqlQuery.addScalar("pid", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("name",StandardBasicTypes.STRING);
		sqlQuery.addScalar("url",StandardBasicTypes.STRING);
		sqlQuery.addScalar("icon",StandardBasicTypes.STRING);
		sqlQuery.addScalar("sort",StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("description",StandardBasicTypes.STRING);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(Permission.class));
		//sqlQuery.setCacheable(true);
		return sqlQuery.list();
	}

	/**
	 * 根据菜单id查询该菜单下所有的子菜单
	 * @param userId
	 * @return 菜单集合
	 */
	@SuppressWarnings("unchecked")
	public List<Permission> findChildMenusById(int userid,int id){
		StringBuffer sb=new StringBuffer();
		sb.append("with tmpdb as( SELECT * from t_permission where id=?0 and type='f' UNION all SELECT b.* from tmpdb a,t_permission b where b.type='f' and a.id=b.pid)");
		sb.append(" SELECT p.* from tmpdb p INNER JOIN t_role_permission rp on p.ID=rp.PERMISSION_ID INNER JOIN t_role r ON  r.id=rp.ROLE_ID");
		sb.append(" INNER JOIN t_user_role  ur ON ur.ROLE_ID =rp.ROLE_ID INNER JOIN t_user u ON u.id = ur.USER_ID where p.TYPE='F' and u.id=?1  order by p.sort");
		SQLQuery sqlQuery=createSQLQuery(sb.toString(),id,userid);
		sqlQuery.addEntity(Permission.class);
		//sqlQuery.setCacheable(true);
		return sqlQuery.list();
	}
	
	
	/**
	 * 查询用户拥有的菜单权限
	 * @param userId
	 * @return 结果集合
	 */
	@SuppressWarnings("unchecked")
	public List<Permission> findMenus(Integer userId){
		StringBuffer sb=new StringBuffer();
		sb.append("select p.ID id,p.PID pid,p.NAME name,p.URL url,p.ICON icon,p.SORT sort,p.DESCRIPTION description from t_permission p ");
		sb.append("INNER JOIN t_role_permission rp on p.ID=rp.PERMISSION_ID ");
		sb.append("INNER JOIN t_role r ON r.id=rp.ROLE_ID ");
		sb.append("INNER JOIN t_user_role ur ON ur.ROLE_ID =rp.ROLE_ID ");
		sb.append("INNER JOIN t_user u ON u.id = ur.USER_ID ");
		sb.append("where p.TYPE='F' and u.id=?0 order by p.sort");
		SQLQuery sqlQuery=createSQLQuery(sb.toString(), userId);
		sqlQuery.addScalar("id",StandardBasicTypes.INTEGER );
		sqlQuery.addScalar("pid", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("name",StandardBasicTypes.STRING);
		sqlQuery.addScalar("url",StandardBasicTypes.STRING);
		sqlQuery.addScalar("icon",StandardBasicTypes.STRING);
		sqlQuery.addScalar("sort",StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("description",StandardBasicTypes.STRING);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(Permission.class));
		//sqlQuery.setCacheable(true);
		return sqlQuery.list();
	}
	
	/**
	 * 查询菜单下的操作权限
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Permission> findMenuOperation(Integer pid){
		StringBuffer sb=new StringBuffer();
		sb.append("select p.ID id,p.NAME name,p.URL url,p.PERM_CODE permCode,p.DESCRIPTION description from t_permission p ");
		sb.append("where (p.TYPE='O' or p.TYPE='AO') and p.PID=?0 order by p.SORT");
		SQLQuery sqlQuery=createSQLQuery(sb.toString(),pid);
		sqlQuery.addScalar("id",StandardBasicTypes.INTEGER );
		sqlQuery.addScalar("name",StandardBasicTypes.STRING);
		sqlQuery.addScalar("url",StandardBasicTypes.STRING);
		sqlQuery.addScalar("permCode",StandardBasicTypes.STRING);
		sqlQuery.addScalar("description",StandardBasicTypes.STRING);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(Permission.class));
		//sqlQuery.setCacheable(true);
		return sqlQuery.list();
	}
	
	/**
	 * 根据菜单id删除该菜单下的权限信息
	 * @param pid
	 */
	public int deleteMenuPermission(Integer pid){
		
		String hql="delete from Permission p where p.type='O' and p.pid=?0";
		int n = batchExecute(hql, pid);
		return n;
	}
	
	
	/**
	 * 查询拥有该权限的用户
	 * @param pid
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> findUsersHasThisPer(Map<String,Object> mapData){
		String sql="SELECT u.id FROM t_user u LEFT JOIN t_comp_user_role tcur ON tcur.user_id = u.id"
				+ " LEFT JOIN t_comp_role tcr ON tcr.id = tcur.role_id"
				+ " LEFT JOIN t_comp_role_permission tcrp ON tcrp.COMPROLE_ID = tcr.id"
				+ " LEFT JOIN t_permission tp ON tp.ID = tcrp.PERMISSION_ID"
				+ " WHERE u.id2 =?0 and TYPE='O' and tp.PERM_CODE=?1";
		SQLQuery sqlQuery=createSQLQuery(sql,mapData.get("qyid"),mapData.get("permission"));
		sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return sqlQuery.list();
	}
	
}
