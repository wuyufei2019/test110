package com.cczu.model.zyaqgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.zyaqgl.entity.AQGL_SaftyMeasure;
import com.cczu.util.dao.BaseDao;

/**
 * 安全管理-安全措施dao层
 */
@Repository("AqglAqcsDao")
public class AqglAqcsDao extends BaseDao<AQGL_SaftyMeasure, Long> {

	//动火作业安全措施datagrid
	public List<Map<String, Object>> aqcsList(Map<String, Object> mapData) {
		String sql  ="SELECT top  "+mapData.get("pageSize")+" * FROM ("
				+ "SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.* FROM aqgl_saftymeasure a where a.m2=1"
				+ " )as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	//动火作业安全措施数量统计
	public int aqcsCount(Map<String, Object> mapData) {
		String  sql= "select count(*) from aqgl_saftymeasure a WHERE a.m2=1 ";
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}
	
	//查询已编制动火作业安全措施datagrid
	public List<Map<String, Object>> bzaqcsList(Map<String, Object> mapData) {
		String sql  ="SELECT top  "+mapData.get("pageSize")+" * FROM ("
				+ "SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.* FROM aqgl_zyaq_aqcs a"
				+ " where a.id1 ="+mapData.get("id1")+" and a.m2=1"
				+ " )as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	//已编制动火作业安全措施数量统计
	public int bzaqcsCount(Map<String, Object> mapData) {
		String  sql= "select count(*) from aqgl_zyaq_aqcs a"
				+ " where a.id1 ="+mapData.get("id1")+" and a.m2=1";
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}
	
	public List<Map<String, Object>> bzaqcsAllList(Map<String, Object> mapData) {
		String sql  ="SELECT a.* FROM aqgl_zyaq_aqcs a"
				+ " where a.id1 ="+mapData.get("id1")+" and a.m2=1 order by id";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	//受限空间安全措施datagrid
	public List<Map<String, Object>> aqcsList2(Map<String, Object> mapData) {
		String sql  ="SELECT top  "+mapData.get("pageSize")+" * FROM ("
				+ "SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.* FROM aqgl_saftymeasure a where a.m2=2"
				+ " )as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	//受限空间安全措施数量统计
	public int aqcsCount2(Map<String, Object> mapData) {
		String  sql= "select count(*) from aqgl_saftymeasure a WHERE a.m2=2 ";
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}
	
	//查询已编制受限空间安全措施datagrid
	public List<Map<String, Object>> bzaqcsList2(Map<String, Object> mapData) {
		String sql  ="SELECT top  "+mapData.get("pageSize")+" * FROM ("
				+ "SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.* FROM aqgl_zyaq_aqcs a"
				+ " where a.id1 ="+mapData.get("id1")+" and a.m2=2"
				+ " )as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	public List<Map<String, Object>> bzaqcsAllList2(Map<String, Object> mapData) {
		String sql  ="SELECT a.* FROM aqgl_zyaq_aqcs a"
				+ " where a.id1 ="+mapData.get("id1")+" and a.m2=2 order by id";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	//已编制受限空间安全措施数量统计
	public int bzaqcsCount2(Map<String, Object> mapData) {
		String  sql= "select count(*) from aqgl_zyaq_aqcs a"
				+ " where a.id1 ="+mapData.get("id1")+" and a.m2=2";
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}
}
