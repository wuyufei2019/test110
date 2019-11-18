package com.cczu.model.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_CheckPointEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 监督考核dao层
 */
@Repository("WghglJdkhDao")
public class WghglJdkhDao extends BaseDao<YHPC_CheckPointEntity, Long>{

	//月检list
	public List<Map<String, Object>> yjdataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT top "+mapData.get("pageSize")+" * FROM ("+
		        "SELECT ROW_NUMBER() OVER (ORDER BY c.m1,b.ID) AS RowNumber,d.wgdid,b.ID userid,c.m1 wgname,b.NAME wgyname,d.wgdname,d.xccs,e.yxcs FROM t_user b "
		        + "LEFT JOIN t_barrio c ON b.xzqy = c.code LEFT JOIN (SELECT a.userid,b.id wgdid,b.name wgdname,COUNT(a.userid) xccs FROM (SELECT a.* "
		        + "FROM(SELECT ROW_NUMBER() OVER (partition by a.checkplan_id,a.checkpoint_id,c.starttime,c.endtime,convert(varchar(7),a.createtime,120) ORDER BY a.createtime DESC) AS rn,a.* "
		        + "FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 "
		        + "WHERE a.checkpointtype = '2' AND b.id1 = 0 AND b.type = '3' AND Day(a.createtime) >= c.starttime AND Day(a.createtime) <= c.endtime) a where a.rn = 1) a "
		        + "LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' AND b.usetype = '1' AND convert(varchar(7),a.createtime,120)= '"+mapData.get("yf")+"' "
		        + "GROUP BY a.userid,b.name,b.id) d ON d.userid = b.ID LEFT JOIN (SELECT tb.code xzqy,COUNT(*) yxcs FROM yhpc_checkplan a LEFT JOIN t_user b ON a.userid = b.ID "
		        + "LEFT JOIN t_barrio tb ON tb.id = a.wgid LEFT JOIN bis_enterprise bis ON bis.id2 = tb.code LEFT JOIN yhpc_checkpoint yc ON yc.id1 = bis.id "
		        + "LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id WHERE yc.usetype = '1' and a.id1 = 0 AND a.type = '3' AND bis.s3 =0 GROUP BY tb.code) e ON e.xzqy = c.code "
		        + "WHERE b.usertype = 0 "+content+")as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	//月检list数量
	public int getyjTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="select count(*) FROM t_user b LEFT JOIN t_barrio c ON b.xzqy = c.code LEFT JOIN (SELECT a.userid,b.id wgdid,b.name wgdname,COUNT(a.userid) xccs FROM (SELECT a.* "
				+ "FROM(SELECT ROW_NUMBER() OVER (partition by a.checkplan_id,a.checkpoint_id,c.starttime,c.endtime,convert(varchar(7),a.createtime,120) ORDER BY a.createtime DESC) AS rn,a.* "
				+ "FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 "
				+ "WHERE a.checkpointtype = '2' AND b.id1 = 0 AND b.type = '3' AND Day(a.createtime) >= c.starttime AND Day(a.createtime) <= c.endtime) a where a.rn = 1) a "
		        + "LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id "
		        + "WHERE a.checkpointtype = '2' AND b.usetype = '1' AND convert(varchar(7),a.createtime,120)= '"+mapData.get("yf")+"' "
		        + "GROUP BY a.userid,b.name,b.id) d ON d.userid = b.ID LEFT JOIN (SELECT tb.code xzqy,COUNT(*) yxcs FROM yhpc_checkplan a LEFT JOIN t_user b ON a.userid = b.ID "
		        + "LEFT JOIN t_barrio tb ON tb.id = a.wgid LEFT JOIN bis_enterprise bis ON bis.id2 = tb.code LEFT JOIN yhpc_checkpoint yc ON yc.id1 = bis.id LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id "
		        + "WHERE yc.usetype = '1' and a.id1 = 0 AND a.type = '3' AND bis.s3 =0 GROUP BY tb.code) e ON e.xzqy = c.code WHERE b.usertype = 0 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	//年检list
	public List<Map<String, Object>> njdataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT top "+mapData.get("pageSize")+" * FROM ("+
		        "SELECT ROW_NUMBER() OVER (ORDER BY c.m1,b.ID) AS RowNumber,d.wgdid,b.ID userid,c.m1 wgname,b.NAME wgyname,d.wgdname,d.xccs,e.yxcs FROM t_user b "
		        + "LEFT JOIN t_barrio c ON b.xzqy = c.code LEFT JOIN (SELECT a.userid,b.id wgdid,b.name wgdname,COUNT(a.userid) xccs FROM (SELECT a.* FROM("
		        + "SELECT ROW_NUMBER() OVER (partition by a.checkplan_id,a.checkpoint_id,c.starttime,c.endtime,convert(varchar(4),a.createtime,120) ORDER BY a.createtime DESC) AS rn,a.* "
		        + "FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 WHERE a.checkpointtype = '2' AND b.id1 = 0 AND b.type = '4' "
		        + "AND MONTH(a.createtime) >= c.starttime AND MONTH(a.createtime) <= c.endtime) a where a.rn = 1) a "
		        + "LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id "
		        + "WHERE a.checkpointtype = '2' AND b.usetype = '1' AND convert(varchar(4),a.createtime,120)= '"+mapData.get("nf")+"' "
		        + "GROUP BY a.userid,b.name,b.id) d ON d.userid = b.ID LEFT JOIN (SELECT tb.code xzqy,COUNT(*) yxcs FROM yhpc_checkplan a LEFT JOIN t_user b ON a.userid = b.ID "
		        + "LEFT JOIN t_barrio tb ON tb.id = a.wgid LEFT JOIN bis_enterprise bis ON bis.id2 = tb.code LEFT JOIN yhpc_checkpoint yc ON yc.id1 = bis.id "
		        + "LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id WHERE yc.usetype = '1' and a.id1 = 0 AND a.type = '4' AND bis.s3 =0 GROUP BY tb.code) e ON e.xzqy = c.code "
		        + "WHERE b.usertype = 0 "+content+")as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	//年检list数量
	public int getnjTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="select count(*) FROM t_user b LEFT JOIN t_barrio c ON b.xzqy = c.code LEFT JOIN (SELECT a.userid,b.id wgdid,b.name wgdname,COUNT(a.userid) xccs FROM (SELECT a.* "
				+ "FROM(SELECT ROW_NUMBER() OVER (partition by a.checkplan_id,a.checkpoint_id,c.starttime,c.endtime,convert(varchar(4),a.createtime,120) ORDER BY a.createtime DESC) AS rn,a.* "
				+ "FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 WHERE a.checkpointtype = '2' AND b.id1 = 0 AND b.type = '4' "
				+ "AND MONTH(a.createtime) >= c.starttime AND MONTH(a.createtime) <= c.endtime) a where a.rn = 1) a "
		        + "LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id "
		        + "WHERE a.checkpointtype = '2' AND b.usetype = '1' AND convert(varchar(4),a.createtime,120)= '"+mapData.get("nf")+"' "
		        + "GROUP BY a.userid,b.name,b.id) d ON d.userid = b.ID LEFT JOIN (SELECT tb.code xzqy,COUNT(*) yxcs FROM yhpc_checkplan a LEFT JOIN t_user b ON a.userid = b.ID "
		        + "LEFT JOIN t_barrio tb ON tb.id = a.wgid LEFT JOIN bis_enterprise bis ON bis.id2 = tb.code LEFT JOIN yhpc_checkpoint yc ON yc.id1 = bis.id "
		        + "LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id WHERE yc.usetype = '1' and a.id1 = 0 AND a.type = '4' AND bis.s3 =0 GROUP BY tb.code) e ON e.xzqy = c.code WHERE b.usertype = 0 "+content;
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
		//user xzqy和jglx
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + " AND b.xzqy like '"+mapData.get("xzqy")+"%' "; 
		}
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + " AND b.userroleflg='"+mapData.get("jglx")+"' "; 
		}
		
		if(mapData.get("ryxzqy")!=null&&mapData.get("ryxzqy")!=""){
			content = content + " AND b.xzqy = '"+mapData.get("ryxzqy")+"' "; 
		}
		if(mapData.get("ryname")!=null&&mapData.get("ryname")!=""){
			content = content + " AND b.NAME like '%"+mapData.get("ryname")+"%' "; 
		}
		return content;
	}
}
