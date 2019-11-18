package com.cczu.model.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_CheckPointEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 网格统计分析dao层
 */
@Repository("WghglTjfxDao")
public class WghglTjfxDao extends BaseDao<YHPC_CheckPointEntity, Long>{

	//巡检点月检list
	public List<Map<String, Object>> xjdyjdataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT top "+mapData.get("pageSize")+" * FROM ("+
		        "SELECT ROW_NUMBER() OVER (ORDER BY c.m1,a.id) AS RowNumber,a.id,a.id1 qyid,a.name wgdname,c.m1 wgname,d.xccs,e.wccs,e.zccs,e.yccs "
		        + "FROM yhpc_checkpoint a "
		        + "LEFT JOIN bis_enterprise b ON a.id1 = b.id LEFT JOIN t_barrio c ON b.id2 = c.code "
		        + "LEFT JOIN (SELECT yc.id id2,COUNT(yc.id) xccs FROM yhpc_checkplan a LEFT JOIN t_barrio tb ON tb.id = a.wgid "
		        + "LEFT JOIN bis_enterprise bis ON bis.id2 = tb.code LEFT JOIN yhpc_checkpoint yc ON yc.id1 = bis.id LEFT JOIN yhpc_checkplan_time c ON c.id1 = a.id "
		        + "WHERE yc.usetype = '1' and a.type = '3' AND a.id1 = 0 AND bis.s3 = 0 GROUP BY yc.id) d ON d.id2 = a.id LEFT JOIN "
		        + "(SELECT a.checkpoint_id,COUNT(a.checkpoint_id) wccs,SUM(CASE a.checkresult WHEN '0' THEN 1 ELSE 0 END) zccs,SUM(CASE a.checkresult WHEN '1' THEN 1 ELSE 0 END) yccs "
		        + "FROM (SELECT a.* FROM(SELECT ROW_NUMBER() OVER (partition by a.checkplan_id,a.checkpoint_id,c.starttime,c.endtime,convert(varchar(7),a.createtime,120) ORDER BY a.createtime DESC) AS rn,a.* FROM yhpc_checkresult a "
		        + "LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 WHERE a.checkpointtype = '2' AND b.id1 = 0 AND b.type = '3' AND Day(a.createtime) >= c.starttime AND Day(a.createtime) <= c.endtime"
		        + ") a where a.rn = 1) a WHERE a.checkpointtype = '2' AND convert(varchar(7),a.createtime,120)= '"+mapData.get("yf")+"' GROUP BY a.checkpoint_id) e ON e.checkpoint_id = a.id "
		        + "WHERE a.usetype = '1' AND b.s3 = 0 "+content+")as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	//巡检点月检数量
	public int getxjdyjTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="select count(*) FROM yhpc_checkpoint a "
		        + "LEFT JOIN bis_enterprise b ON a.id1 = b.id LEFT JOIN t_barrio c ON b.id2 = c.code "
		        + "LEFT JOIN (SELECT yc.id id2,COUNT(yc.id) xccs FROM yhpc_checkplan a LEFT JOIN t_barrio tb ON tb.id = a.wgid "
		        + "LEFT JOIN bis_enterprise bis ON bis.id2 = tb.code LEFT JOIN yhpc_checkpoint yc ON yc.id1 = bis.id LEFT JOIN yhpc_checkplan_time c ON c.id1 = a.id "
		        + "WHERE yc.usetype = '1' and a.type = '3' AND a.id1 = 0 AND bis.s3 = 0 GROUP BY yc.id) d ON d.id2 = a.id LEFT JOIN "
		        + "(SELECT a.checkpoint_id,COUNT(a.checkpoint_id) wccs,SUM(CASE a.checkresult WHEN '0' THEN 1 ELSE 0 END) zccs,SUM(CASE a.checkresult WHEN '1' THEN 1 ELSE 0 END) yccs "
		        + "FROM (SELECT a.* FROM(SELECT ROW_NUMBER() OVER (partition by a.checkplan_id,a.checkpoint_id,c.starttime,c.endtime,convert(varchar(7),a.createtime,120) ORDER BY a.createtime DESC) AS rn,a.* "
		        + "FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 "
		        + "WHERE a.checkpointtype = '2' AND b.id1 = 0 AND b.type = '3' AND Day(a.createtime) >= c.starttime AND Day(a.createtime) <= c.endtime"
		        + ") a where a.rn = 1) a WHERE a.checkpointtype = '2' AND convert(varchar(7),a.createtime,120)= '"+mapData.get("yf")+"' GROUP BY a.checkpoint_id) e ON e.checkpoint_id = a.id WHERE a.usetype = '1' AND b.s3 = 0 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	//巡检点年检list
	public List<Map<String, Object>> xjdnjdataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT top "+mapData.get("pageSize")+" * FROM ("+
		        "SELECT ROW_NUMBER() OVER (ORDER BY c.m1,a.id) AS RowNumber,a.id,a.id1 qyid,a.name wgdname,c.m1 wgname,d.xccs,e.wccs,e.zccs,e.yccs "
		        + "FROM yhpc_checkpoint a "
		        + "LEFT JOIN bis_enterprise b ON a.id1 = b.id LEFT JOIN t_barrio c ON b.id2 = c.code "
		        + "LEFT JOIN (SELECT yc.id id2,COUNT(yc.id) xccs FROM yhpc_checkplan a LEFT JOIN t_barrio tb ON tb.id = a.wgid "
		        + "LEFT JOIN bis_enterprise bis ON bis.id2 = tb.code LEFT JOIN yhpc_checkpoint yc ON yc.id1 = bis.id "
		        + "LEFT JOIN yhpc_checkplan_time c ON c.id1 = a.id WHERE yc.usetype = '1' and a.type = '4' AND a.id1 = 0 AND bis.s3 = 0 "
		        + "GROUP BY yc.id) d ON d.id2 = a.id LEFT JOIN "
		        + "(SELECT a.checkpoint_id,COUNT(a.checkpoint_id) wccs,SUM(CASE a.checkresult WHEN '0' THEN 1 ELSE 0 END) zccs,SUM(CASE a.checkresult WHEN '1' THEN 1 ELSE 0 END) yccs "
		        + "FROM (SELECT a.* FROM(SELECT ROW_NUMBER() OVER (partition by a.checkplan_id,a.checkpoint_id,c.starttime,c.endtime,convert(varchar(4),a.createtime,120) ORDER BY a.createtime DESC) AS rn,a.* "
		        + "FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 "
		        + "WHERE a.checkpointtype = '2' AND b.id1 = 0 AND b.type = '4' AND MONTH(a.createtime) >= c.starttime AND MONTH(a.createtime) <= c.endtime"
		        + ") a where a.rn = 1) a WHERE a.checkpointtype = '2' AND convert(varchar(4),a.createtime,120)= '"+mapData.get("nf")+"' GROUP BY a.checkpoint_id) e ON e.checkpoint_id = a.id WHERE a.usetype = '1' AND b.s3 = 0 "+content+
				")as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	//巡检点年检数量
	public int getxjdnjTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="select count(*) FROM yhpc_checkpoint a "
		        + "LEFT JOIN bis_enterprise b ON a.id1 = b.id LEFT JOIN t_barrio c ON b.id2 = c.code "
		        + "LEFT JOIN (SELECT yc.id id2,COUNT(yc.id) xccs FROM yhpc_checkplan a LEFT JOIN t_barrio tb ON tb.id = a.wgid "
		        + "LEFT JOIN bis_enterprise bis ON bis.id2 = tb.code LEFT JOIN yhpc_checkpoint yc ON yc.id1 = bis.id LEFT JOIN yhpc_checkplan_time c ON c.id1 = a.id "
		        + "WHERE yc.usetype = '1' and a.type = '4' AND a.id1 = 0 AND bis.s3 = 0 GROUP BY yc.id) d ON d.id2 = a.id LEFT JOIN "
		        + "(SELECT a.checkpoint_id,COUNT(a.checkpoint_id) wccs,SUM(CASE a.checkresult WHEN '0' THEN 1 ELSE 0 END) zccs,SUM(CASE a.checkresult WHEN '1' THEN 1 ELSE 0 END) yccs "
		        + "FROM (SELECT a.* FROM(SELECT ROW_NUMBER() OVER (partition by a.checkplan_id,a.checkpoint_id,c.starttime,c.endtime,convert(varchar(4),a.createtime,120) ORDER BY a.createtime DESC) AS rn,a.* "
		        + "FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 "
		        + "WHERE a.checkpointtype = '2' AND b.id1 = 0 AND b.type = '4' AND MONTH(a.createtime) >= c.starttime AND MONTH(a.createtime) <= c.endtime"
		        + ") a where a.rn = 1) a WHERE a.checkpointtype = '2' AND convert(varchar(4),a.createtime,120)= '"+mapData.get("nf")+"' GROUP BY a.checkpoint_id) e ON e.checkpoint_id = a.id WHERE a.usetype = '1' AND b.s3 = 0 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	//班次月检list
	public List<Map<String, Object>> bcyjdataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT top "+mapData.get("pageSize")+" * FROM ("+
		        "SELECT ROW_NUMBER() OVER (ORDER BY c.m1,a.id) AS RowNumber,a.*,c.m1 wgname,d.xccs,e.wccs,e.zccs,e.yccs FROM yhpc_checkplan a "
		        + "LEFT JOIN t_user b ON b.id = a.userid LEFT JOIN t_barrio c ON a.wgid = c.id LEFT JOIN "
		        + "(SELECT a.id,COUNT(a.id) xccs FROM yhpc_checkplan a LEFT JOIN yhpc_checkplan_time b ON b.id1 = a.id LEFT JOIN t_barrio tb ON tb.id = a.wgid "
		        + "LEFT JOIN bis_enterprise bis ON bis.id2 = tb.code LEFT JOIN yhpc_checkpoint yc ON yc.id1 = bis.id "
		        + "WHERE yc.usetype = '1' and a.id1 = 0 AND a.type = '3' AND bis.s3 =0 "
		        + "GROUP BY a.id) d ON d.id = a.id LEFT JOIN (SELECT a.checkplan_id,COUNT(a.checkpoint_id) wccs,SUM(CASE a.checkresult WHEN '0' THEN 1 ELSE 0 END) zccs,SUM(CASE a.checkresult WHEN '1' THEN 1 ELSE 0 END) yccs "
		        + "FROM (SELECT a.* FROM(SELECT ROW_NUMBER() OVER (partition by a.checkplan_id,a.checkpoint_id,c.starttime,c.endtime,convert(varchar(7),a.createtime,120) ORDER BY a.createtime DESC) AS rn,a.* "
		        + "FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 "
		        + "WHERE a.checkpointtype = '2' AND b.id1 = 0 AND b.type = '3' AND Day(a.createtime) >= c.starttime AND Day(a.createtime) <= c.endtime"
		        + ") a where a.rn = 1) a WHERE a.checkpointtype = '2' AND convert(varchar(7),a.createtime,120)= '"+mapData.get("yf")+"' "
		        + "GROUP BY a.checkplan_id) e ON e.checkplan_id = a.id WHERE a.id1 = 0 AND a.type = '3' "+content+")as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	//班次月检数量
	public int getbcyjTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="select count(*) FROM yhpc_checkplan a LEFT JOIN t_user b ON b.id = a.userid LEFT JOIN t_barrio c ON a.wgid = c.id LEFT JOIN "
		        + "(SELECT a.id,COUNT(a.id) xccs FROM yhpc_checkplan a LEFT JOIN yhpc_checkplan_time b ON b.id1 = a.id LEFT JOIN t_barrio tb ON tb.id = a.wgid "
		        + "LEFT JOIN bis_enterprise bis ON bis.id2 = tb.code LEFT JOIN yhpc_checkpoint yc ON yc.id1 = bis.id WHERE yc.usetype = '1' and a.id1 = 0 AND a.type = '3' AND bis.s3 =0 "
		        + "GROUP BY a.id) d ON d.id = a.id LEFT JOIN (SELECT a.checkplan_id,COUNT(a.checkpoint_id) wccs,SUM(CASE a.checkresult WHEN '0' THEN 1 ELSE 0 END) zccs,SUM(CASE a.checkresult WHEN '1' THEN 1 ELSE 0 END) yccs "
		        + "FROM (SELECT a.* FROM(SELECT ROW_NUMBER() OVER (partition by a.checkplan_id,a.checkpoint_id,c.starttime,c.endtime,convert(varchar(7),a.createtime,120) ORDER BY a.createtime DESC) AS rn,a.* "
		        + "FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 "
		        + "WHERE a.checkpointtype = '2' AND b.id1 = 0 AND b.type = '3' AND Day(a.createtime) >= c.starttime AND Day(a.createtime) <= c.endtime"
		        + ") a where a.rn = 1) a WHERE a.checkpointtype = '2' AND convert(varchar(7),a.createtime,120)= '"+mapData.get("yf")+"' "
		        + "GROUP BY a.checkplan_id) e ON e.checkplan_id = a.id WHERE a.id1 = 0 AND a.type = '3' "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	//班次年检list
	public List<Map<String, Object>> bcnjdataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT top "+mapData.get("pageSize")+" * FROM ("+
		        "SELECT ROW_NUMBER() OVER (ORDER BY c.m1,a.id) AS RowNumber,a.*,c.m1 wgname,d.xccs,e.wccs,e.zccs,e.yccs FROM yhpc_checkplan a "
		        + "LEFT JOIN t_user b ON b.id = a.userid LEFT JOIN t_barrio c ON a.wgid = c.id LEFT JOIN "
		        + "(SELECT a.id,COUNT(a.id) xccs FROM yhpc_checkplan a LEFT JOIN yhpc_checkplan_time b ON b.id1 = a.id LEFT JOIN t_barrio tb ON tb.id = a.wgid "
		        + "LEFT JOIN bis_enterprise bis ON bis.id2 = tb.code LEFT JOIN yhpc_checkpoint yc ON yc.id1 = bis.id "
		        + "WHERE yc.usetype = '1' and a.id1 = 0 AND a.type = '4' AND bis.s3 =0 "
		        + "GROUP BY a.id) d ON d.id = a.id LEFT JOIN (SELECT a.checkplan_id,COUNT(a.checkpoint_id) wccs,SUM(CASE a.checkresult WHEN '0' THEN 1 ELSE 0 END) zccs,SUM(CASE a.checkresult WHEN '1' THEN 1 ELSE 0 END) yccs "
		        + "FROM (SELECT a.* FROM(SELECT ROW_NUMBER() OVER (partition by a.checkplan_id,a.checkpoint_id,c.starttime,c.endtime,convert(varchar(4),a.createtime,120) ORDER BY a.createtime DESC) AS rn,a.* "
		        + "FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 "
		        + "WHERE a.checkpointtype = '2' AND b.id1 = 0 AND b.type = '4' AND MONTH(a.createtime) >= c.starttime AND MONTH(a.createtime) <= c.endtime"
		        + ") a where a.rn = 1) a WHERE a.checkpointtype = '2' AND convert(varchar(4),a.createtime,120)= '"+mapData.get("nf")+"' "
		        + "GROUP BY a.checkplan_id) e ON e.checkplan_id = a.id WHERE a.id1 = 0 AND a.type = '4' "+content+")as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	//班次年检数量
	public int getbcnjTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="select count(*) FROM yhpc_checkplan a LEFT JOIN t_user b ON b.id = a.userid LEFT JOIN t_barrio c ON a.wgid = c.id LEFT JOIN "
		        + "(SELECT a.id,COUNT(a.id) xccs FROM yhpc_checkplan a LEFT JOIN yhpc_checkplan_time b ON b.id1 = a.id LEFT JOIN t_barrio tb ON tb.id = a.wgid "
		        + "LEFT JOIN bis_enterprise bis ON bis.id2 = tb.code LEFT JOIN yhpc_checkpoint yc ON yc.id1 = bis.id WHERE yc.usetype = '1' and a.id1 = 0 AND a.type = '4' AND bis.s3 =0 "
		        + "GROUP BY a.id) d ON d.id = a.id LEFT JOIN (SELECT a.checkplan_id,COUNT(a.checkpoint_id) wccs,SUM(CASE a.checkresult WHEN '0' THEN 1 ELSE 0 END) zccs,SUM(CASE a.checkresult WHEN '1' THEN 1 ELSE 0 END) yccs "
		        + "FROM (SELECT a.* FROM(SELECT ROW_NUMBER() OVER (partition by a.checkplan_id,a.checkpoint_id,c.starttime,c.endtime,convert(varchar(4),a.createtime,120) ORDER BY a.createtime DESC) AS rn,a.* "
		        + "FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 "
		        + "WHERE a.checkpointtype = '2' AND b.id1 = 0 AND b.type = '4' AND MONTH(a.createtime) >= c.starttime AND MONTH(a.createtime) <= c.endtime "
		        + ") a where a.rn = 1) a WHERE a.checkpointtype = '2' AND convert(varchar(4),a.createtime,120)= '"+mapData.get("nf")+"' "
		        + "GROUP BY a.checkplan_id) e ON e.checkplan_id = a.id WHERE a.id1 = 0 AND a.type = '4' "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	//巡检人员月检list
	public List<Map<String, Object>> xjryyjdataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT top "+mapData.get("pageSize")+" * FROM ("+
		        "SELECT ROW_NUMBER() OVER (ORDER BY c.m1,b.ID) AS RowNumber,b.ID id,b.NAME wgyname,c.m1 wgname,d.xccs,d.yccs FROM t_user b "
		        + "LEFT JOIN t_barrio c ON b.xzqy = c.code LEFT JOIN (SELECT a.userid,COUNT(a.userid) xccs,SUM(CASE a.checkresult WHEN '1' THEN 1 ELSE '0' END) yccs "
		        + "FROM (SELECT a.* FROM(SELECT ROW_NUMBER() OVER (partition by a.checkplan_id,a.checkpoint_id,c.starttime,c.endtime,convert(varchar(7),a.createtime,120) ORDER BY a.createtime DESC) AS rn,a.* "
		        + "FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 "
		        + "WHERE a.checkpointtype = '2' AND b.id1 = 0 AND b.type = '3' AND Day(a.createtime) >= c.starttime AND Day(a.createtime) <= c.endtime) a where a.rn = 1) a LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id "
		        + "WHERE a.checkpointtype = '2' AND b.usetype = '1' AND convert(varchar(7),a.createtime,120)= '"+mapData.get("yf")+"' GROUP BY a.userid) d ON b.ID = d.userid "
		        + "WHERE b.usertype = 0 "+content+")as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	//巡检人员月检list数量
	public int getxjryyjTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="select count(*) FROM t_user b "
		        + "LEFT JOIN t_barrio c ON b.xzqy = c.code LEFT JOIN (SELECT a.userid,COUNT(a.userid) xccs,SUM(CASE a.checkresult WHEN '1' THEN 1 ELSE '0' END) yccs "
		        + "FROM (SELECT a.* FROM(SELECT ROW_NUMBER() OVER (partition by a.checkplan_id,a.checkpoint_id,c.starttime,c.endtime,convert(varchar(7),a.createtime,120) ORDER BY a.createtime DESC) AS rn,a.* "
		        + "FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 "
		        + "WHERE a.checkpointtype = '2' AND b.id1 = 0 AND b.type = '3' AND Day(a.createtime) >= c.starttime AND Day(a.createtime) <= c.endtime) a where a.rn = 1) a LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id "
		        + "WHERE a.checkpointtype = '2' AND b.usetype = '1' AND convert(varchar(7),a.createtime,120)= '"+mapData.get("yf")+"' GROUP BY a.userid) d ON b.ID = d.userid "
		        + "WHERE b.usertype = 0 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	//巡检人员年检list
	public List<Map<String, Object>> xjrynjdataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT top "+mapData.get("pageSize")+" * FROM ("+
		        "SELECT ROW_NUMBER() OVER (ORDER BY c.m1,b.ID) AS RowNumber,b.ID id,b.NAME wgyname,c.m1 wgname,d.xccs,d.yccs FROM t_user b "
		        + "LEFT JOIN t_barrio c ON b.xzqy = c.code LEFT JOIN (SELECT a.userid,COUNT(a.userid) xccs,SUM(CASE a.checkresult WHEN '1' THEN 1 ELSE '0' END) yccs "
		        + "FROM (SELECT a.* FROM(SELECT ROW_NUMBER() OVER (partition by a.checkplan_id,a.checkpoint_id,c.starttime,c.endtime,convert(varchar(4),a.createtime,120) ORDER BY a.createtime DESC) AS rn,a.* "
		        + "FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 WHERE a.checkpointtype = '2' AND b.id1 = 0 AND b.type = '4' "
		        + "AND MONTH(a.createtime) >= c.starttime AND MONTH(a.createtime) <= c.endtime) a where a.rn = 1) a LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id "
		        + "WHERE a.checkpointtype = '2' AND b.usetype = '1' AND convert(varchar(4),a.createtime,120)= '"+mapData.get("nf")+"' GROUP BY a.userid) d ON b.ID = d.userid "
		        + "WHERE b.usertype = 0 "+content+")as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	//巡检人员年检list数量
	public int getxjrynjTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="select count(*) FROM t_user b "
		        + "LEFT JOIN t_barrio c ON b.xzqy = c.code LEFT JOIN (SELECT a.userid,COUNT(a.userid) xccs,SUM(CASE a.checkresult WHEN '1' THEN 1 ELSE '0' END) yccs "
		        + "FROM (SELECT a.* FROM(SELECT ROW_NUMBER() OVER (partition by a.checkplan_id,a.checkpoint_id,c.starttime,c.endtime,convert(varchar(4),a.createtime,120) ORDER BY a.createtime DESC) AS rn,a.* "
		        + "FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 WHERE a.checkpointtype = '2' AND b.id1 = 0 AND b.type = '4' "
		        + "AND MONTH(a.createtime) >= c.starttime AND MONTH(a.createtime) <= c.endtime) a where a.rn = 1) a LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id "
		        + "WHERE a.checkpointtype = '2' AND b.usetype = '1' AND convert(varchar(4),a.createtime,120)= '"+mapData.get("nf")+"' GROUP BY a.userid) d ON b.ID = d.userid "
		        + "WHERE b.usertype = 0 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content="";
		//企业  xzqy和jglx
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + " AND b.id2 like '"+mapData.get("xzqy")+"%' "; 
		}
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + " AND b.m36='"+mapData.get("jglx")+"' "; 
		}
		//user xzqy和jglx
		if(mapData.get("userxzqy")!=null&&mapData.get("userxzqy")!=""){
			content = content + " AND b.xzqy like '"+mapData.get("userxzqy")+"%' "; 
		}
		if(mapData.get("userjglx")!=null&&mapData.get("userjglx")!=""){
			content = content + " AND b.userroleflg='"+mapData.get("userjglx")+"' "; 
		}
		//网格 xzqy  jglx(暂时无)
		if(mapData.get("wgcode")!=null&&mapData.get("wgcode")!=""){
			content = content + " AND c.code like '"+mapData.get("wgcode")+"%' "; 
		}
		
		
		//巡检点条件
		if(mapData.get("xjdxzqy")!=null&&mapData.get("xjdxzqy")!=""){
			content = content + " AND b.id2 = '"+mapData.get("xjdxzqy")+"' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + " AND b.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		//班次条件
		if(mapData.get("bcxzqy")!=null&&mapData.get("bcxzqy")!=""){
			content = content + " AND c.code = '"+mapData.get("bcxzqy")+"' "; 
		}
		if(mapData.get("bcname")!=null&&mapData.get("bcname")!=""){
			content = content + " AND a.name like '%"+mapData.get("bcname")+"%' "; 
		}
		//巡检人员条件
		if(mapData.get("ryxzqy")!=null&&mapData.get("ryxzqy")!=""){
			content = content + " AND b.xzqy = '"+mapData.get("ryxzqy")+"' "; 
		}
		if(mapData.get("ryname")!=null&&mapData.get("ryname")!=""){
			content = content + " AND b.NAME like '%"+mapData.get("ryname")+"%' "; 
		}
		return content;
	}
}
