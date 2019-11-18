package com.cczu.sys.system.dao;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.system.entity.User;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 用户DAO
 * @author jason
 * @date 2017年5月31日
 */
@SuppressWarnings("unchecked")
@Repository
public class UserDao extends HibernateDao<User, Integer>{

	public int saveback(User user) {
		save(user);
		return user.getId();
	}
	
	public List<User> findByuid(Integer uid) {
		StringBuffer sql=new StringBuffer();
		sql.append("select * from t_user  ");
		sql.append("where uid=?0 order by id");
		SQLQuery sqlQuery=createSQLQuery(sql.toString(), uid);
		sqlQuery.addEntity(User.class);
		return sqlQuery.list();
	}
	
	public List<Map<String, Object>> getUJsonById2(Map<String, Object> map) {
		StringBuffer sql=new StringBuffer();
		sql.append("select id,name text from t_user  ");
		sql.append("where id2=?0 order by id");
		SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
		sqlQuery.setParameter("0", map.get("qyid"));
		sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return sqlQuery.list();
	}

	public List<User> findUserByContent(String yqid,String depid,String comprolecode) {
		StringBuffer sql=new StringBuffer();
		sql.append("select u.* from t_user u LEFT JOIN t_comp_user_role ur on u.ID=ur.user_id LEFT JOIN t_comp_role r on ur.role_id=r.id where u.id2="+yqid );
		if(!depid.equals(""))
			sql.append(" and u.departmen= "+depid );
		if(!comprolecode.equals(""))
			sql.append(" and r.role_code= '"+comprolecode+"'" );
		SQLQuery sqlQuery=createSQLQuery(sql.toString());
		sqlQuery.addEntity(User.class);
		return sqlQuery.list();
	}

	public List<User> dataGrid(Map<String, Object> map) {
		String content=content(map);
		String sql=" SELECT TOP "+map.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.ID) AS RowNumber,a.* FROM t_user a"
				+ " left join t_department dep on a.departmen = dep.id "
				+ " WHERE 1=1 "+content+" ) "
				+ " AS a WHERE RowNumber > "+map.get("pageSize")+"*("+map.get("pageNo")+"-1) ";
		SQLQuery sqlQuery=createSQLQuery(sql);
		sqlQuery.addEntity(User.class);
		return sqlQuery.list();
	}
	
	public int getTotalCount(Map<String, Object> map) {
		String content=content(map);
		String sql=" SELECT COUNT(*) sum  FROM t_user a "
				+ " left join t_department dep on a.departmen = dep.id "
				+ " where 1=1 "+ content;
		SQLQuery sqlQuery=getSession().createSQLQuery(sql);
		return (int) sqlQuery.list().get(0);
	}
	
	/**
	 * 企业内部角色
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> nbjs(Map<String, Object> map) {
		String content=content(map);
		String sql=" SELECT TOP "+map.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.ID) AS RowNumber,a.*, cor.name nbjs FROM t_user a"
				+ " left join t_department dep on a.departmen = dep.id "
				+ " left join t_comp_user_role cou on a.id = cou.user_id "
				+ " left join t_comp_role cor on cou.role_id = cor.id "
				+ " WHERE 1=1 "+content+" ) "
				+ " AS a WHERE RowNumber > "+map.get("pageSize")+"*("+map.get("pageNo")+"-1) ";
		SQLQuery sqlQuery=createSQLQuery(sql);
		sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return sqlQuery.list();
	}
	
	public int getNbjsCount(Map<String, Object> map) {
		String content=content(map);
		String sql=" SELECT COUNT(*) sum  FROM t_user a "
				+ " left join t_department dep on a.departmen = dep.id "
				+ " left join t_comp_user_role cou on a.id = cou.user_id "
				+ " left join t_comp_role cor on cou.role_id = cor.id "
				+ " where 1=1 "+ content;
		SQLQuery sqlQuery=getSession().createSQLQuery(sql);
		return (int) sqlQuery.list().get(0);
	}
	
	/**
	 * 根据查询条件查询所有用户
	 * @param map
	 * @return
	 */
	public List<User> findByAllUserByContent(Map<String, Object> map) {
		String content=content(map);
		String sql="  SELECT a.* FROM t_user a"
				+ " left join t_department dep on a.departmen = dep.id "
				+ " WHERE (a.DEL_FLAG =0 or a.DEL_FLAG is null) "+content;
		SQLQuery sqlQuery=createSQLQuery(sql);
		sqlQuery.addEntity(User.class);
		return sqlQuery.list();
	}
	
	
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + " AND a.xzqy like'"+mapData.get("xzqy")+"%' "; 
		}
		if(mapData.get("eqxzqy")!=null&&mapData.get("eqxzqy")!=""){
			content = content + " AND a.xzqy like'"+mapData.get("eqxzqy")+"' "; 
		}
		if(mapData.get("notuserroleflg")!=null&&mapData.get("notuserroleflg")!=""){
			content = content + " AND a.userroleflg !="+mapData.get("notuserroleflg")+" "; 
		}
		if(mapData.get("name")!=null&&mapData.get("name")!=""){
			content = content + " AND a.name like'%"+mapData.get("name")+"%'"; 
		}
		if(mapData.get("loginName")!=null&&mapData.get("loginName")!=""){
			content = content + " AND a.login_Name like'%"+mapData.get("loginName")+"%'"; 
		}
		if(mapData.get("phone")!=null&&mapData.get("phone")!=""){
			content = content + " AND a.phone like'%"+mapData.get("phone")+"%' "; 
		}
		if(mapData.get("createDatestart")!=null&&mapData.get("createDatestart")!=""){
			content = content + " AND a.create_Date >='"+mapData.get("createDatestart")+" 00:00:00"+"' "; 
		}
		if(mapData.get("createDateEnd")!=null&&mapData.get("createDateEnd")!=""){
			content = content + " AND a.create_Date <='"+mapData.get("createDateEnd")+" 23:59:59"+"' "; 
		}
		if(mapData.get("usertype")!=null&&mapData.get("usertype")!=""){
			content = content + " AND a.usertype = '"+mapData.get("usertype")+"' "; 
		}
		if(mapData.get("departmen")!=null&&mapData.get("departmen")!=""){
			content = content + " AND a.departmen = '"+mapData.get("departmen")+"' "; 
		}
		if(mapData.get("departmens")!=null&&mapData.get("departmens")!=""){
			content = content + " AND a.departmen in ("+mapData.get("departmens")+")"; 
		}
		if(mapData.get("uid")!=null&&mapData.get("uid")!=""){
			content = content + " AND a.uid = '"+mapData.get("uid")+"' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + " AND a.id2 = '"+mapData.get("qyid")+"' "; 
		}
		if(mapData.get("qyids")!=null&&mapData.get("qyids")!=""){
			content = content + " AND a.id2 in ( "+mapData.get("qyids")+") "; 
		}
		if(mapData.get("role")!=null&&mapData.get("role")!=""){
			content = content + " AND a.role_code ='"+mapData.get("role")+"' "; 
		}
		if(mapData.get("role")!=null&&mapData.get("role")!=""){
			content = content + " AND a.role_code ='"+mapData.get("role")+"' "; 
		}
		if(mapData.get("depcode1")!=null&&mapData.get("depcode1")!=""){
			content = content + " and dep.code='"+mapData.get("depcode1")+"'";
		}
		if(mapData.get("depcode2")!=null&&mapData.get("depcode2")!=""){
			content = content + " and dep.code like'"+mapData.get("depcode2")+"%'";
		}
		//内部角色
		if(mapData.get("nbjs")!=null&&mapData.get("nbjs")!=""){
			content = content + " and cor.name like'"+mapData.get("nbjs")+"%'";
		}if(mapData.get("jcr")!=null&&mapData.get("jcr")!=""){
			content = content + " and a.name like'"+mapData.get("jcr")+"%'";
		}if(mapData.get("bm")!=null&&mapData.get("bm")!=""){
			content = content + " and b.id ='"+mapData.get("bm")+"' ";
		}if(mapData.get("depid")!=null&&mapData.get("depid")!=""){
			content = content + " and b.id in("+mapData.get("depid")+") ";
		}
		return content;
	}

	public User findById(Integer id) {
		String sqlString="select * from t_user where id="+id;
		SQLQuery sqlQuery=createSQLQuery(sqlString).addEntity(User.class);
		if(sqlQuery.list().size()>0){
			return (User) sqlQuery.list().get(0);
		}else{
			return null;
		}
	}
	
	//获取用户id
	public List<Object> findIDByContent(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT a.id FROM t_user a "
				+ " left join t_department dep on a.departmen = dep.id "
				+ " WHERE 1=1 "+content;
		SQLQuery sqlQuery=createSQLQuery(sql);
		return sqlQuery.list();
	}
	
	public List<Map<String, Object>> findMapByContent(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="  SELECT a.ID as id,a.LOGIN_NAME as loginName,a.NAME as name,a.BIRTHDAY as birthday,a.GENDER as gender,a.PHONE as phone FROM t_user a "
				+ " left join t_department dep on a.departmen = dep.id "
				+ " WHERE 1=1 "+content;
		SQLQuery sqlQuery=createSQLQuery(sql);
		return (List<Map<String, Object>>)sqlQuery.list();
	}
	
	//通过用户名获取数据，用于获取userroleflg 
	/**
	 * 通过用户名获取数据，用于获取userroleflg,对用户权限进行判断
	 * @param name 登录的用户名
	 * @return
	 */
	public User findByname(String name) {
		String sqlString="select * from t_user where loginName='"+name+"' ";
		SQLQuery sqlQuery=createSQLQuery(sqlString).addEntity(User.class);
		if(sqlQuery.list().size()>0){
			return (User) sqlQuery.list().get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 根据企业ID修改用户的网格区域
	 * @param qyid
	 * @return
	 */
	public boolean updateWGQY(long qyid,String wgqy){
		boolean flag=false;
		String hql="update User u set u.xzqy=?0  where u.id2=?1";
		int n=batchExecute(hql, wgqy,qyid);
		if(n>0)
			flag=true;
		return flag;
	}
	
	/**
	 * 根据网格编码查看网格成员，包括用户的角色
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getWgUser(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT a.id,a.name,a.login_name,a.gender,a.phone,r.name as rolename ,r.role_code FROM t_user a "
				+ " left join t_department dep on a.departmen = dep.id "
				+ " JOIN t_user_role ur on a.id=ur.USER_ID  JOIN t_role r on r.id=ur.ROLE_ID  where 0=0 "+content;
		SQLQuery sqlQuery=createSQLQuery(sql);
		sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return sqlQuery.list();
	}
	
	/**
	 * 获取员工部门和岗位信息
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> getGwAndBm(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("usertype")!=null&&mapData.get("usertype")!=""){
			content = content + " AND a.flag = '"+mapData.get("usertype")+"' "; 
		}
		if(mapData.get("userid")!=null&&mapData.get("userid")!=""){
			content = content + " AND b.id = "+mapData.get("userid")+" "; 
		}
		String sql="SELECT a.m1 depart,b.id AS userid, d.m1 AS qyname, b.name AS username, e.m1 gw"
				+ " FROM t_user b LEFT JOIN t_department a ON a.id = b.departmen LEFT JOIN bis_employee c ON c.id1 = b.id"
				+ " LEFT JOIN bis_enterprise d ON d.id = b.id2 left JOIN bis_jobpostentity e on e.id=c.m4"
				+ " WHERE 1 = 1 "+content;
		SQLQuery sqlQuery=createSQLQuery(sql);
		sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list = sqlQuery.list();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	//根据部门id查询人员json
	public List<Map<String, Object>> getUJsonByDep(Map<String, Object> map) {
		StringBuffer sql=new StringBuffer();
		sql.append("select b.m1 deptname,a.id,a.name text from t_user a left join t_department b ON b.id = a.departmen where 1=1 ");
		if(map.get("depid")!=null&&map.get("depid")!=""){
			sql.append(" AND  departmen in("+map.get("depid")+")"); 
		}
		if(map.get("qyid")!=null&&map.get("qyid")!=""){
			sql.append(" AND  a.id2 ="+map.get("qyid")+" "); 
		}
		if(map.get("jcr")!=null&&map.get("jcr")!=""){
			sql.append(" AND  a.name like '%"+map.get("jcr")+"%'"); 
		}
		if(map.get("bm")!=null&&map.get("bm")!=""){
			sql.append(" AND  b.id ="+map.get("bm")+" "); 
		}
		sql.append(" order by id");
		SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
		sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return sqlQuery.list();
	}
	
	public List<Map<String,Object>> dataGrid2(Map<String, Object> map) {
		String content=content(map);
		String sql=" SELECT TOP "+map.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.ID) AS RowNumber,b.m1 deptname,a.id,a.name text from t_user a"
				+ " left join t_department b ON b.id = a.departmen "
				+ " WHERE 1=1 "+content+" ) "
				+ " AS a WHERE RowNumber > "+map.get("pageSize")+"*("+map.get("pageNo")+"-1) ";
		SQLQuery sqlQuery=createSQLQuery(sql);
		sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return sqlQuery.list();
	}
	
	public int getTotalCount2(Map<String, Object> map) {
		String content=content(map);
		String sql=" SELECT COUNT(*) sum  FROM t_user a "
				+ " left join t_department b on b.id= a.departmen "
				+ " where 1=1 "+ content;
		SQLQuery sqlQuery=getSession().createSQLQuery(sql);
		return (int) sqlQuery.list().get(0);
	}
	
	/**
	 * 通过企业id，权限获取相应人员
	 * @return
	 */
	public List<User> findUserByPermission(Long qyid,String permission) {
		String sqlString="SELECT a.* FROM t_user a"
				+ " LEFT JOIN t_comp_user_role b ON a.ID = b.user_id"
				+ " LEFT JOIN t_comp_role c ON b.role_id = c.id"
				+ " LEFT JOIN t_comp_role_permission d ON d.COMPROLE_ID = c.id"
				+ " LEFT JOIN t_permission e ON e.ID = d.PERMISSION_ID"
				+ " WHERE a.id2 = "+qyid+" AND e.PERM_CODE = '"+permission+"'";
		SQLQuery sqlQuery=createSQLQuery(sqlString).addEntity(User.class);
		return sqlQuery.list();
	}
}
