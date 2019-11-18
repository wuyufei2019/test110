package com.cczu.model.ztzyaqgl.dao;

import com.cczu.model.ztzyaqgl.entity.ZTAQGL_SaftyMeasure;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 安全管理-安全措施dao层
 */
@Repository("ztAqglAqcsDao")
public class AqglAqcsDao extends BaseDao<ZTAQGL_SaftyMeasure, Long> {

	//动火作业安全措施datagrid
	public List<Map<String, Object>> aqcsList(Map<String, Object> mapData) {
		String sql  ="SELECT top  "+mapData.get("pageSize")+" * FROM ("
				+ "SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.* FROM ztaqgl_saftymeasure a where a.m2=1"
				+ " )as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	//动火作业安全措施数量统计
	public int aqcsCount(Map<String, Object> mapData) {
		String  sql= "select count(*) from ztaqgl_saftymeasure a WHERE a.m2=1 ";
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}
	
	//查询已编制动火作业安全措施datagrid
	public List<Map<String, Object>> bzaqcsList(Map<String, Object> mapData) {
		String sql  ="SELECT top  "+mapData.get("pageSize")+" * FROM ("
				+ "SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.* FROM ztaqgl_zyaq_aqcs a"
				+ " where a.id1 ="+mapData.get("id1")+" and a.m2=1"
				+ " )as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	//已编制动火作业安全措施数量统计
	public int bzaqcsCount(Map<String, Object> mapData) {
		String  sql= "select count(*) from ztaqgl_zyaq_aqcs a"
				+ " where a.id1 ="+mapData.get("id1")+" and a.m2=1";
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}
	
	public List<Map<String, Object>> bzaqcsAllList(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql  ="SELECT a.* FROM ztaqgl_zyaq_aqcs a"
				+ " where 0=0 "+content+"order by id";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	//受限空间安全措施datagrid
	public List<Map<String, Object>> aqcsList2(Map<String, Object> mapData) {
		String sql  ="SELECT top  "+mapData.get("pageSize")+" * FROM ("
				+ "SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.* FROM ztaqgl_saftymeasure a where a.m2=2"
				+ " )as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	//受限空间安全措施数量统计
	public int aqcsCount2(Map<String, Object> mapData) {
		String  sql= "select count(*) from ztaqgl_saftymeasure a WHERE a.m2=2 ";
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}
	
	//查询已编制受限空间安全措施datagrid
	public List<Map<String, Object>> bzaqcsList2(Map<String, Object> mapData) {
		String sql  ="SELECT top  "+mapData.get("pageSize")+" * FROM ("
				+ "SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.* FROM ztaqgl_zyaq_aqcs a"
				+ " where a.id1 ="+mapData.get("id1")+" and a.m2=2"
				+ " )as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	public List<Map<String, Object>> bzaqcsAllList2(Map<String, Object> mapData) {
		String sql  ="SELECT a.* FROM ztaqgl_zyaq_aqcs a"
				+ " where a.id1 ="+mapData.get("id1")+" and a.m2=2 order by id";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	//已编制受限空间安全措施数量统计
	public int bzaqcsCount2(Map<String, Object> mapData) {
		String  sql= "select count(*) from ztaqgl_zyaq_aqcs a"
				+ " where a.id1 ="+mapData.get("id1")+" and a.m2=2";
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}

	//危险作业安全措施datagrid
	public List<Map<String, Object>> aqcsList1(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql  ="SELECT top  "+mapData.get("pageSize")+" * FROM ("
				+ "SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.* FROM ztaqgl_saftymeasure a where 0=0 "+content
				+ " )as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	//危险作业安全措施数量统计
	public int aqcsCount1(Map<String, Object> mapData) {
		String content = content(mapData);
		String  sql= "select count(*) from ztaqgl_saftymeasure a WHERE 0=0 "+content;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}
	
	//查询已编制危险作业安全措施datagrid
	public List<Map<String, Object>> ybzaqcsList(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql  ="SELECT top  "+mapData.get("pageSize")+" * FROM ("
				+ "SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.* FROM ztaqgl_zyaq_aqcs a"
				+ " where 0=0 "+content
				+ " )as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	//查询已编制危险作业安全措施list
	public List<Map<String, Object>> ybzaqcsList2(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql  ="select * FROM ztaqgl_zyaq_aqcs a where 0=0 "+content;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	//已编制危险作业安全措施数量统计
	public int ybzaqcsCount(Map<String, Object> mapData) {
		String content = content(mapData);
		String  sql= "select count(*) from ztaqgl_zyaq_aqcs a"
				+ " where 0=0 "+content;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}	

	public String content(Map<String,Object> mapData) {
		String content = "";
		if (mapData.get("id1") != null && mapData.get("id1") != "") {
			content += " and a.id1 =" + mapData.get("id1");
		}
		if (mapData.get("m2") != null && mapData.get("m2") != "") {
			content += " and a.m2 ='" + mapData.get("m2")+"' ";
		}		
		return content;
	}
}
