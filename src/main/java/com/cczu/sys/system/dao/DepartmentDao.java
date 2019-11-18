package com.cczu.sys.system.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.system.entity.Department;

/**
 * 部门管理DAO
 * @author jason
 */
@SuppressWarnings("unchecked")
@Repository
public class DepartmentDao extends HibernateDao<Department, Long>{
	
	public List<Department> dataGrid(Map<String, Object> map) {
		String content=content(map);
		String sql=" SELECT ROW_NUMBER() OVER (ORDER BY a.m2) AS RowNumber,a.* FROM t_department a left join t_user b on a.id1=b.id  WHERE 1=1 "+content ;
		SQLQuery sqlQuery=createSQLQuery(sql);
		sqlQuery.addEntity(Department.class);
		return sqlQuery.list();
	}

	public int getTotalCount(Map<String, Object> map) {
		String content=content(map);
		String sql=" SELECT COUNT(a.ID) sum  FROM t_department a left join t_user b on a.id1=b.id where 1=1 "+ content;
		SQLQuery sqlQuery=createSQLQuery(sql);
		return (int) sqlQuery.list().get(0);
	}

	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content + "AND a.m1 like'%"+mapData.get("m1")+"%'"; 
		}
		if(mapData.get("flag")!=null&&mapData.get("flag")!=""){
			content = content + "AND a.flag ='"+mapData.get("flag")+"' "; 
		}
		if(mapData.get("id2")!=null&&mapData.get("id2")!=""){
			content = content + "AND a.id2 ='"+mapData.get("id2")+"' "; 
		}
		if(mapData.get("id3")!=null&&mapData.get("id3")!=""){
			content = content + "AND b.xzqy ='"+mapData.get("id3")+"' "; 
		}
		//所属部门权限
		if(mapData.get("depids")!=null&&mapData.get("depids")!=""){
			content = content + " and a.id in ("+mapData.get("depids")+")";
		}else{
			if(mapData.get("depcode1")!=null&&mapData.get("depcode1")!=""){
				content = content + " and a.code='"+mapData.get("depcode1")+"'";
			}
			if(mapData.get("depcode2")!=null&&mapData.get("depcode2")!=""){
				content = content + " and a.code like'"+mapData.get("depcode2")+"%'";
			}
		}
		if(mapData.get("depcode3")!=null&&mapData.get("depcode3")!=""){
			content = content + " and a.code like'"+mapData.get("depcode3")+"%'";
		}
		return content;
	}

	public List<Department> findJson(Map<String, Object> mapd) {
		String content=content(mapd);
		String sql=" SELECT  * FROM t_department a left join t_user b on a.id1=b.id WHERE 1=1 "+content;
		SQLQuery sqlQuery=createSQLQuery(sql);
		sqlQuery.addEntity(Department.class);
		return sqlQuery.list();
	}
	
	public List<Map<String,Object>> findJsonForApp(Map<String, Object> mapd) {
		String content=content(mapd);
		String sql=" SELECT  a.id,a.m1 FROM t_department a left join t_user b on a.id1=b.id WHERE 1=1 "+content;
		SQLQuery sqlQuery=createSQLQuery(sql);
		sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return sqlQuery.list();
	}
	
	public Department findInfoById(long id) {
		String sql=" SELECT  * FROM t_department WHERE id= "+id;
		SQLQuery sqlQuery=createSQLQuery(sql);
		sqlQuery.addEntity(Department.class);
		return (Department)sqlQuery.list().get(0);
	}
	//根据部门id集合查询部门
	public List<Department> findListByBmids(String ids) {
		String sql=" SELECT  * FROM t_department WHERE ID IN ("+ids+")";
		SQLQuery sqlQuery=createSQLQuery(sql);
		sqlQuery.addEntity(Department.class);
		return sqlQuery.list();
	}
	
	public List<Department> findInfoByQyId(Long qyid) {
		String sql=" SELECT * FROM t_department WHERE ID2=" + qyid;
		SQLQuery sqlQuery=createSQLQuery(sql);
		sqlQuery.addEntity(Department.class);
		return sqlQuery.list();
	}
	
	public Department findInfoByName(String deptname) {
		String sql=" SELECT * FROM t_department WHERE m1= '" + deptname+ "'";
		SQLQuery sqlQuery=createSQLQuery(sql);
		sqlQuery.addEntity(Department.class);
		return (Department)sqlQuery.list().get(0);
	}
	
	/**
	 * 修改部门网格所属父级，及该网格下所有子网格的编码都要修改
	 * @param oldcode
	 * @param newcode
	 */
	public void updateFcode(String oldpcode,String newpcode){
		String sql=" update t_department set code=replace(code,'"+oldpcode+"','"+newpcode+"')";
		createSQLQuery(sql).executeUpdate();
	}
}
