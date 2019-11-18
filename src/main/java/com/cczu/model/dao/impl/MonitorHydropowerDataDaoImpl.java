package com.cczu.model.dao.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IMonitorHydropowerDataDao;
import com.cczu.model.entity.Main_SignalWaterEntity;
import com.cczu.util.dao.BaseDao;

@Repository("MonitorHydropowerDataDao")
public class MonitorHydropowerDataDaoImpl extends BaseDao<Main_SignalWaterEntity, Long> implements IMonitorHydropowerDataDao {

	String sqlty = "SELECT ROW_NUMBER() OVER(order by s.qyname) rownum,s.qyname,"+
			   "SUM(CASE s.metername WHEN '水表' THEN s.number ELSE 0 END ) sb,"+
	           "SUM(CASE s.metername WHEN '电表' THEN s.number ELSE 0 END ) db,"+
			   "SUM(CASE s.metername WHEN '气表' THEN s.number ELSE 0 END )zq "+
               "FROM (SELECT e.m1 qyname,m.metername,sw.number "+
               "FROM (SELECT depno,MAX(colltime) sj FROM main_signal_water GROUP BY depno) de "+
               "LEFT JOIN main_signal_water sw ON sw.depno = de.depno AND sw.colltime = de.sj "+
               "LEFT JOIN basic_meter m ON de.depno = m.[no] LEFT JOIN bis_enterprise e ON e.id = sw.id1 WHERE 1=1 ";
	
	@Override
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = "SELECT TOP " + mapData.get("pageSize") + "* FROM ("+
		           sqlty + content + " ) s GROUP BY qyname ) ss "+
				   "WHERE rownum >" + mapData.get("pageSize") + "*("+mapData.get("pageNo")+"-1) order by qyname" ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = sqlty + content + " ) s GROUP BY qyname ORDER BY s.qyname";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM (SELECT DISTINCT sw.id1 FROM main_signal_water sw LEFT JOIN bis_enterprise e ON e.id = sw.id1 WHERE 1=1 "+ content + " ) s";
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public List<Map<String, Object>> qyList(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT id,m1 FROM bis_enterprise e WHERE id IN (SELECT DISTINCT id1 FROM main_signal_water) " + content;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + "AND e.m1 like'%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND e.id2 like'"+mapData.get("xzqy")+"%' "; 
		}
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND e.m36='"+mapData.get("jglx")+"' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND e.id like'"+mapData.get("qyid")+"' "; 
		}
		if(mapData.get("qyids")!=null&&mapData.get("qyids")!=""){
			content = content + "AND e.id in ("+mapData.get("qyids")+") "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( e.fid='"+mapData.get("fid")+"' or e.id='"+mapData.get("fid")+"') "; 
		}
		return content;
	}

}
