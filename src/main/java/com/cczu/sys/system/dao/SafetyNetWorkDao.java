package com.cczu.sys.system.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.system.entity.SafetyNetwork;

/**
 * 安全管理网络DAO
 * @author jason
 * @date 2017年11月24日
 */
@SuppressWarnings("unchecked")
@Repository
public class SafetyNetWorkDao extends HibernateDao<SafetyNetwork, Long>{
	
	public List<SafetyNetwork> dataGrid(Map<String, Object> map) {
		String content=content(map);
		String orderStr=orderStr(map, "a.", "ORDER BY a.ID");
		String sql=" SELECT TOP "+map.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+orderStr+") AS RowNumber,a.* FROM t_SafetyNetwork a WHERE 1=1 "+content+" ) "
				+ " AS a WHERE RowNumber > "+map.get("pageSize")+"*("+map.get("pageNo")+"-1) ";
		SQLQuery sqlQuery=createSQLQuery(sql);
		sqlQuery.addEntity(SafetyNetwork.class);
		return sqlQuery.list();
	}

	public int getTotalCount(Map<String, Object> map) {
		String content=content(map);
		String sql=" SELECT COUNT(a.ID) sum  FROM t_SafetyNetwork a  where 1=1 "+ content;
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
			content = content + "AND a.m1 like'%"+mapData.get("m1")+"%' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND a.ID1 ="+mapData.get("qyid")+" "; 
		}
		return content;
	}
	/**
     * 排序条件判断
     * @return
	 * @throws ParseException 
     */
	public String orderStr(Map<String,Object> mapData,String alias,String orderBy) {
		if(mapData.get("order")!=""&&mapData.get("orderBy")!="")
			orderBy=" ORDER BY "+alias+mapData.get("orderBy")+" "+mapData.get("order")+" ";
		return orderBy;
	}

	public List<SafetyNetwork> findJson(Map<String, Object> mapd) {
		String content=content(mapd);
		String sql=" SELECT  * FROM t_SafetyNetwork a left join t_user b on a.id1=b.id WHERE 1=1 "+content;
		SQLQuery sqlQuery=createSQLQuery(sql);
		sqlQuery.addEntity(SafetyNetwork.class);
		return sqlQuery.list();
	}
	
	public List<SafetyNetwork> findListByBmids(String ids) {
		String sql=" SELECT  * FROM t_SafetyNetwork WHERE ID IN ("+ids+")";
		SQLQuery sqlQuery=createSQLQuery(sql);
		sqlQuery.addEntity(SafetyNetwork.class);
		return sqlQuery.list();
	}

}
