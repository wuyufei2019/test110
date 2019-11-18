package com.cczu.model.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_CheckResultEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 巡检监督与考核dao层
 * ll
 */
@Repository("YhpcXjjdDao")
public class YhpcXjjdDao extends BaseDao<YHPC_CheckResultEntity, Long>{

	//总检list(安监端)
	public List<Map<String, Object>> khdataGrid(Map<String, Object> mapData) {
		String timecontent="AND a.createtime between '"+mapData.get("start")+"' and '"+mapData.get("end")+"' ";
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM(SELECT ROW_NUMBER() OVER ( ORDER BY Round(CAST(ISNULL(a.yxcs, 0) AS float) / CAST(nullif(a.yccs,0) AS float) * 100, 2) desc) AS RowNumber, a.qyid, a.qyname, a.yxcs, a.yccs, ISNULL(Round(CAST(ISNULL(a.yxcs, 0) AS float) / CAST(nullif(a.yccs,0) AS float) * 100, 2),0) bl"
				+ " from( SELECT bis.id qyid,bis.m1 qyname,(a.yccs1*"+mapData.get("rj")+"+b.yccs2*"+mapData.get("zj")+"+c.yccs3*"+mapData.get("yj")+"+d.yccs4*"+mapData.get("nj")+") yccs,(a.yxcs1+b.yxcs2+c.yxcs3+d.yxcs4) yxcs FROM bis_enterprise bis"
				+ " left join (SELECT b.ID qyid, b.m1 qyname, (ISNULL(d.yhcs,0)+ISNULL(c.fxcs,0)) yxcs1, ISNULL(e.yccs,0) yccs1 FROM bis_enterprise b"
				+ " LEFT JOIN ( SELECT a.qyid, d.m1 qyname, COUNT(a.qyid) yhcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(10), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.*"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 WHERE b.id1 != 0 AND b.type = '1' AND CONVERT(varchar(100), a.createtime, 8) >= c.starttime AND CONVERT(varchar(100), a.createtime, 8) <= c.endtime) a where a.rn = 1) a"
				+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id LEFT JOIN bis_enterprise d ON d.ID = a.qyid WHERE d.s3=0 AND a.checkpointtype = '2' AND b.usetype = '2' "+timecontent+" GROUP BY a.qyid, d.m1) d ON d.qyid = b.ID"
				+ " LEFT JOIN ( SELECT a.qyid, d.m1 qyname, COUNT(a.qyid) fxcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(10), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.* FROM yhpc_checkresult a"
				+ " LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 WHERE a.checkpointtype = '1' AND b.id1 != 0 AND b.type = '1' AND CONVERT(varchar(100), a.createtime, 8) >= c.starttime AND CONVERT(varchar(100), a.createtime, 8) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id LEFT JOIN bis_enterprise d ON d.ID = a.qyid WHERE d.s3=0 AND a.checkpointtype = '1' "+timecontent+" GROUP BY d.m1, a.qyid) c ON c.qyid = b.ID"
				+ " LEFT JOIN ( SELECT bis.id qyid, bis.m1 qyname, COUNT(*) yccs FROM yhpc_checkplan a LEFT JOIN t_user b ON a.userid = b.ID LEFT JOIN bis_enterprise bis ON bis.id = a.id1 LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id LEFT JOIN yhpc_checkplan_point e ON e.id1 = a.id WHERE a.id1 != 0 AND a.type = '1' AND bis.s3 =0 GROUP BY bis.id, bis.m1 ) e ON e.qyid = b.id ) a on bis.id=a.qyid"
				+ " left join( SELECT b.ID qyid, b.m1 qyname, (ISNULL(d.yhcs,0)+ISNULL(c.fxcs,0)) yxcs2, ISNULL(e.yccs,0) yccs2 FROM bis_enterprise b"
				+ " LEFT JOIN ( SELECT a.qyid, d.m1 qyname, COUNT(a.qyid) yhcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(7), a.createtime, 120), DATEPART(week,a.createtime) ORDER BY a.createtime DESC) AS rn, a.*"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 WHERE a.checkpointtype = '2' AND b.id1 != 0 AND b.type = '2' AND Datepart(weekday, a.createtime) >= c.starttime AND Datepart(weekday, a.createtime) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id LEFT JOIN bis_enterprise d ON d.ID = a.qyid WHERE d.s3=0 AND a.checkpointtype = '2' AND b.usetype = '2' "+timecontent+" GROUP BY a.qyid, d.m1, b.id) d ON d.qyid = b.ID"
				+ " LEFT JOIN ( SELECT a.qyid, d.m1 qyname, COUNT(a.qyid) fxcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(7), a.createtime, 120), DATEPART(week,a.createtime) ORDER BY a.createtime DESC) AS rn, a.*"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 WHERE a.checkpointtype = '1' AND b.id1 != 0 AND b.type = '2' AND Datepart(weekday, a.createtime) >= c.starttime AND Datepart(weekday, a.createtime) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id LEFT JOIN bis_enterprise d ON d.ID = a.qyid WHERE d.s3=0 AND a.checkpointtype = '1' "+timecontent+" GROUP BY d.m1, a.qyid) c ON c.qyid = b.ID"
				+ " LEFT JOIN ( SELECT bis.id qyid, bis.m1 qyname, COUNT(*) yccs FROM yhpc_checkplan a LEFT JOIN t_user b ON a.userid = b.ID LEFT JOIN bis_enterprise bis ON bis.id = a.id1 LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id LEFT JOIN yhpc_checkplan_point e ON e.id1 = a.id WHERE a.id1 != 0 AND a.type = '2' AND bis.s3 =0 GROUP BY bis.id, bis.m1 ) e ON e.qyid = b.id )b on bis.id=b.qyid"
				+ " left join( SELECT b.ID qyid, b.m1 qyname, (ISNULL(d.yhcs,0)+ISNULL(c.fxcs,0)) yxcs3, ISNULL(e.yccs,0) yccs3 FROM bis_enterprise b"
				+ " LEFT JOIN ( SELECT a.qyid, d.m1 qyname, COUNT(a.qyid) yhcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(7), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.*"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 WHERE a.checkpointtype = '2' AND b.id1 != 0 AND b.type = '3' AND Day(a.createtime) >= c.starttime AND Day(a.createtime) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id LEFT JOIN bis_enterprise d ON d.ID = a.qyid WHERE d.s3=0 AND a.checkpointtype = '2' AND b.usetype = '2' "+timecontent+" GROUP BY a.qyid, d.m1) d ON d.qyid = b.ID"
				+ " LEFT JOIN ( SELECT a.qyid, d.m1 qyname, COUNT(a.qyid) fxcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(7), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.*"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 WHERE a.checkpointtype = '1' AND b.id1 != 0 AND b.type = '3' AND Day(a.createtime) >= c.starttime AND Day(a.createtime) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id LEFT JOIN bis_enterprise d ON d.ID = a.qyid WHERE d.s3=0 AND a.checkpointtype = '1' "+timecontent+" GROUP BY d.m1, a.qyid) c ON c.qyid = b.ID"
				+ " LEFT JOIN ( SELECT bis.id qyid, bis.m1 qyname, COUNT(*) yccs FROM yhpc_checkplan a LEFT JOIN t_user b ON a.userid = b.ID LEFT JOIN bis_enterprise bis ON bis.id = a.id1 LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id LEFT JOIN yhpc_checkplan_point e ON e.id1 = a.id WHERE a.id1 != 0 AND a.type = '3' AND bis.s3 =0 GROUP BY bis.id, bis.m1 ) e ON e.qyid = b.id )c on bis.id=c.qyid"
				+ " left join( SELECT b.ID qyid, b.m1 qyname, (ISNULL(d.yhcs,0)+ISNULL(c.fxcs,0)) yxcs4, ISNULL(e.yccs,0) yccs4 FROM bis_enterprise b"
				+ " LEFT JOIN ( SELECT a.qyid, d.m1 qyname, COUNT(a.qyid) yhcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(4), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.*"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 WHERE a.checkpointtype = '2' AND b.id1 != 0 AND b.type = '4' AND MONTH(a.createtime) >= c.starttime AND MONTH(a.createtime) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id LEFT JOIN bis_enterprise d ON d.ID = a.qyid WHERE d.s3=0 AND a.checkpointtype = '2' AND b.usetype = '2' "+timecontent+" GROUP BY a.qyid, d.m1) d ON d.qyid = b.ID"
				+ " LEFT JOIN ( SELECT a.qyid, d.m1 qyname, COUNT(a.qyid) fxcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(4), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.*"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 WHERE a.checkpointtype = '1' AND b.id1 != 0 AND b.type = '4' AND MONTH(a.createtime) >= c.starttime AND MONTH(a.createtime) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id LEFT JOIN bis_enterprise d ON d.ID = a.qyid WHERE d.s3=0 AND a.checkpointtype = '1' "+timecontent+" GROUP BY d.m1, a.qyid) c ON c.qyid = b.ID"
				+ " LEFT JOIN ( SELECT bis.id qyid, bis.m1 qyname, COUNT(*) yccs FROM yhpc_checkplan a LEFT JOIN t_user b ON a.userid = b.ID LEFT JOIN bis_enterprise bis ON bis.id = a.id1 LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id LEFT JOIN yhpc_checkplan_point e ON e.id1 = a.id WHERE a.id1 != 0 AND a.type = '4' AND bis.s3 =0 GROUP BY bis.id, bis.m1 ) e ON e.qyid = b.id where 1=1 )d on bis.id=d.qyid"
				+ " where 1=1 "+content+" )a )as s WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)" ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	//总检list数量(安监端)
	public int getzjTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="select COUNT(*)  from bis_enterprise bis"
				+ " where bis.s3=0 " +content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	//巡检轨迹
	public List<Map<String,Object>> getXjgjData(String time,String xjry,String xjbc) {
		String sql="SELECT id, userid,lat,lng,createtime,checkplan_id from yhpc_checkresult where 1=1 AND userid="+xjry+" and CONVERT(VARCHAR(10),createtime,120)='"+time+"' and checkplan_id="+xjbc+" ORDER BY checkplan_id,createtime ";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	//总检list(企业端-按人员)
	public List<Map<String, Object>> khdataGrid2(Map<String, Object> mapData) {
		String timecontent="AND a.createtime between '"+mapData.get("start")+"' and '"+mapData.get("end")+"' ";
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM(SELECT ROW_NUMBER() OVER ( ORDER BY Round(CAST(ISNULL(a.yxcs, 0) AS float) / CAST(nullif(a.yccs, 0) AS float) * 100, 2) desc) AS RowNumber, a.userid, a.username, a.yxcs, a.yccs, ISNULL(Round(CAST(ISNULL(a.yxcs, 0) AS float) / CAST(nullif(a.yccs, 0) AS float) * 100, 2), 0) bl,b.wts,b.zgs"
				+ " from ( SELECT t.id userid, t.name username, (a.yccs1*"+mapData.get("rj")+"+b.yccs2*"+mapData.get("zj")+"+c.yccs3*"+mapData.get("yj")+"+d.yccs4*"+mapData.get("nj")+") yccs, (a.yxcs1+b.yxcs2+c.yxcs3+d.yxcs4) yxcs FROM t_user t left join ( SELECT b.ID userid, b.name username, (ISNULL(d.yhcs, 0)+ISNULL(c.fxcs, 0)) yxcs1, ISNULL(e.yccs, 0) yccs1 FROM t_user b"
				+ " LEFT JOIN ( SELECT a.userid, a.name username, COUNT(a.userid) yhcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(10), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.*,d.name"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 LEFT JOIN t_user d ON d.id = a.userid WHERE a.checkpointtype = '2' AND b.id1 != 0 AND b.type = '1' AND CONVERT(varchar(100), a.createtime, 8) >= c.starttime AND CONVERT(varchar(100), a.createtime, 8) <= c.endtime) a where a.rn = 1) a"
				+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' AND b.usetype = '2' "+timecontent+" GROUP BY a.userid, a.name) d ON d.userid = b.ID LEFT JOIN ( SELECT a.userid, a.name username, COUNT(a.userid) fxcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(10), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.* ,d.name"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 LEFT JOIN t_user d ON d.id = a.userid WHERE a.checkpointtype = '1' AND b.id1 != 0 AND b.type = '1' AND CONVERT(varchar(100), a.createtime, 8) >= c.starttime AND CONVERT(varchar(100), a.createtime, 8) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' "+timecontent+" GROUP BY a.userid, a.name) c ON c.userid = b.ID LEFT JOIN ( SELECT f.id2 userid, g.name, COUNT(*) yccs FROM yhpc_checkplan a LEFT JOIN t_user b ON a.userid = b.ID LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id"
				+ " LEFT JOIN yhpc_checkplan_point e ON e.id1 = a.id LEFT JOIN yhpc_checkplan_user f ON f.id1 = a.id LEFT JOIN t_user g on g.id=f.id2 WHERE a.id1 != 0 AND a.type = '1' GROUP BY f.id2, g.name ) e ON e.userid = b.id ) a on t.id=a.userid left join ( SELECT b.ID userid, b.name username, (ISNULL(d.yhcs, 0)+ISNULL(c.fxcs, 0)) yxcs2, ISNULL(e.yccs, 0) yccs2 FROM t_user b"
				+ " LEFT JOIN ( SELECT a.userid, a.name username, COUNT(a.qyid) yhcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(7), a.createtime, 120), DATEPART(week, a.createtime) ORDER BY a.createtime DESC) AS rn, a.* ,d.name"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 LEFT JOIN t_user d ON d.id = a.userid WHERE a.checkpointtype = '2' AND b.id1 != 0 AND b.type = '2' AND Datepart(weekday, a.createtime) >= c.starttime AND Datepart(weekday, a.createtime) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' AND b.usetype = '2' "+timecontent+" GROUP BY a.userid, a.name) d ON d.userid = b.ID LEFT JOIN ( SELECT a.userid, a.name username, COUNT(a.userid) fxcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, d.name, convert(varchar(7), a.createtime, 120), DATEPART(week, a.createtime) ORDER BY a.createtime DESC) AS rn, a.* ,d.name"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 LEFT JOIN t_user d ON d.id = a.userid WHERE a.checkpointtype = '1' AND b.id1 != 0 AND b.type = '2' AND Datepart(weekday, a.createtime) >= c.starttime AND Datepart(weekday, a.createtime) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' "+timecontent+" GROUP BY a.userid, a.name) c ON c.userid = b.ID"
				+ " LEFT JOIN ( SELECT f.id2 userid, g.name, COUNT(*) yccs FROM yhpc_checkplan a LEFT JOIN t_user b ON a.userid = b.ID LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id LEFT JOIN yhpc_checkplan_point e ON e.id1 = a.id LEFT JOIN yhpc_checkplan_user f ON f.id1 = a.id LEFT JOIN t_user g on g.id=f.id2 WHERE a.id1 != 0 AND a.type = '2' GROUP BY f.id2, g.name ) e ON e.userid = b.id )b on t.id=b.userid left join ( SELECT b.ID userid, b.name username, (ISNULL(d.yhcs, 0)+ISNULL(c.fxcs, 0)) yxcs3, ISNULL(e.yccs, 0) yccs3 FROM t_user b"
				+ " LEFT JOIN ( SELECT a.userid, a.name username, COUNT(a.userid) yhcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(7), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.* ,d.name"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 LEFT JOIN t_user d ON d.id = a.userid WHERE a.checkpointtype = '2' AND b.id1 != 0 AND b.type = '3' AND Day(a.createtime) >= c.starttime AND Day(a.createtime) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' AND b.usetype = '2' "+timecontent+" GROUP BY a.userid, a.name) d ON d.userid = b.ID LEFT JOIN ( SELECT a.userid, a.name username, COUNT(a.userid) fxcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(7), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.* ,d.name"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 LEFT JOIN t_user d ON d.id = a.userid WHERE a.checkpointtype = '1' AND b.id1 != 0 AND b.type = '3' AND Day(a.createtime) >= c.starttime AND Day(a.createtime) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' "+timecontent+" GROUP BY a.userid, a.name) c ON c.userid = b.ID LEFT JOIN ( SELECT f.id2 userid, g.name, COUNT(*) yccs FROM yhpc_checkplan a LEFT JOIN t_user b ON a.userid = b.ID"
				+ " LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id LEFT JOIN yhpc_checkplan_point e ON e.id1 = a.id LEFT JOIN yhpc_checkplan_user f ON f.id1 = a.id LEFT JOIN t_user g on g.id=f.id2 WHERE a.id1 != 0 AND a.type = '3' GROUP BY f.id2, g.name ) e ON e.userid = b.id )c on t.id=c.userid left join ( SELECT b.ID userid, b.name username, (ISNULL(d.yhcs, 0)+ISNULL(c.fxcs, 0)) yxcs4, ISNULL(e.yccs, 0) yccs4 FROM t_user b"
				+ " LEFT JOIN ( SELECT a.userid, a.name username, COUNT(a.userid) yhcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(4), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.* ,d.name"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 LEFT JOIN t_user d ON d.id = a.userid WHERE a.checkpointtype = '2' AND b.id1 != 0 AND b.type = '4' AND MONTH(a.createtime) >= c.starttime AND MONTH(a.createtime) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' AND b.usetype = '2' "+timecontent+" GROUP BY a.userid, a.name) d ON d.userid = b.ID LEFT JOIN ( SELECT a.userid, a.name username, COUNT(a.userid) fxcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(4), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.*,d.name"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 LEFT JOIN t_user d ON d.id = a.userid WHERE a.checkpointtype = '1' AND b.id1 != 0 AND b.type = '4' AND MONTH(a.createtime) >= c.starttime AND MONTH(a.createtime) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' "+timecontent+" GROUP BY a.userid, a.name) c ON c.userid = b.ID"
				+ " LEFT JOIN ( SELECT f.id2 userid, g.name, COUNT(*) yccs FROM yhpc_checkplan a LEFT JOIN t_user b ON a.userid = b.ID LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id LEFT JOIN yhpc_checkplan_point e ON e.id1 = a.id LEFT JOIN yhpc_checkplan_user f ON f.id1 = a.id LEFT JOIN t_user g on g.id=f.id2 WHERE a.id1 != 0 AND a.type = '4' GROUP BY f.id2, g.name ) e ON e.userid = b.id )d on t.id=d.userid"
				+ " where 1=1 "+content+" )a LEFT JOIN (select u.id,COUNT(a.id)wts,sum(case when a.dangerstatus = '3' then 1 else 0 end)zgs from t_user u LEFT JOIN yhpc_checkhiddeninfo a ON a.userid = u.ID AND a.dangerorigin = '1' "+timecontent
				+ " GROUP BY u.ID) b ON a.userid = b.id)as s WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)" ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	//总检list(企业端-按人员)
	public List<Map<String, Object>> timekhdataGrid(Map<String, Object> mapData) {
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM(SELECT ROW_NUMBER() OVER ( ORDER BY Round(CAST(ISNULL(a.yxcs, 0) AS float) / CAST(nullif(a.yccs, 0) AS float) * 100, 2) desc) AS RowNumber, " +
				" a.* from yhpc_checkcount_qy a where a.qyid="+mapData.get("qyid")+")as s WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)" ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	//定时任务删除巡检考核副表信息
	public void deletetimekh() {
		String sql =" delete from yhpc_checkcount_qy " ;
		updateBySql(sql);
	}

	//定时任务插入巡检考核信息
	public void inserttimekh(Map<String, Object> mapData) {
		String timecontent="AND a.createtime between '"+mapData.get("start")+"' and '"+mapData.get("end")+"' ";
		String content=content(mapData);
		String sql ="INSERT INTO yhpc_checkcount_qy (qyid,userid,username,yxcs,yccs,bl,wts,zgs)"
				+ " (SELECT a.qyid, a.userid, a.username, a.yxcs, a.yccs, ISNULL(Round(CAST(ISNULL(a.yxcs, 0) AS float) / CAST(nullif(a.yccs, 0) AS float) * 100, 2), 0) bl,b.wts,b.zgs"
				+ " from ( SELECT t.id2 qyid,t.id userid, t.name username, (a.yccs1*"+mapData.get("rj")+"+b.yccs2*"+mapData.get("zj")+"+c.yccs3*"+mapData.get("yj")+"+d.yccs4*"+mapData.get("nj")+") yccs, (a.yxcs1+b.yxcs2+c.yxcs3+d.yxcs4) yxcs FROM t_user t left join ( SELECT b.ID userid, b.name username, (ISNULL(d.yhcs, 0)+ISNULL(c.fxcs, 0)) yxcs1, ISNULL(e.yccs, 0) yccs1 FROM t_user b"
				+ " LEFT JOIN ( SELECT a.userid, a.name username, COUNT(a.userid) yhcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(10), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.*,d.name"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 LEFT JOIN t_user d ON d.id = a.userid WHERE a.checkpointtype = '2' AND b.id1 != 0 AND b.type = '1' AND CONVERT(varchar(100), a.createtime, 8) >= c.starttime AND CONVERT(varchar(100), a.createtime, 8) <= c.endtime) a where a.rn = 1) a"
				+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' AND b.usetype = '2' "+timecontent+" GROUP BY a.userid, a.name) d ON d.userid = b.ID LEFT JOIN ( SELECT a.userid, a.name username, COUNT(a.userid) fxcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(10), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.* ,d.name"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 LEFT JOIN t_user d ON d.id = a.userid WHERE a.checkpointtype = '1' AND b.id1 != 0 AND b.type = '1' AND CONVERT(varchar(100), a.createtime, 8) >= c.starttime AND CONVERT(varchar(100), a.createtime, 8) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' "+timecontent+" GROUP BY a.userid, a.name) c ON c.userid = b.ID LEFT JOIN ( SELECT f.id2 userid, g.name, COUNT(*) yccs FROM yhpc_checkplan a LEFT JOIN t_user b ON a.userid = b.ID LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id"
				+ " LEFT JOIN yhpc_checkplan_point e ON e.id1 = a.id LEFT JOIN yhpc_checkplan_user f ON f.id1 = a.id LEFT JOIN t_user g on g.id=f.id2 WHERE a.id1 != 0 AND a.type = '1' GROUP BY f.id2, g.name ) e ON e.userid = b.id ) a on t.id=a.userid left join ( SELECT b.ID userid, b.name username, (ISNULL(d.yhcs, 0)+ISNULL(c.fxcs, 0)) yxcs2, ISNULL(e.yccs, 0) yccs2 FROM t_user b"
				+ " LEFT JOIN ( SELECT a.userid, a.name username, COUNT(a.qyid) yhcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(7), a.createtime, 120), DATEPART(week, a.createtime) ORDER BY a.createtime DESC) AS rn, a.* ,d.name"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 LEFT JOIN t_user d ON d.id = a.userid WHERE a.checkpointtype = '2' AND b.id1 != 0 AND b.type = '2' AND Datepart(weekday, a.createtime) >= c.starttime AND Datepart(weekday, a.createtime) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' AND b.usetype = '2' "+timecontent+" GROUP BY a.userid, a.name) d ON d.userid = b.ID LEFT JOIN ( SELECT a.userid, a.name username, COUNT(a.userid) fxcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, d.name, convert(varchar(7), a.createtime, 120), DATEPART(week, a.createtime) ORDER BY a.createtime DESC) AS rn, a.* ,d.name"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 LEFT JOIN t_user d ON d.id = a.userid WHERE a.checkpointtype = '1' AND b.id1 != 0 AND b.type = '2' AND Datepart(weekday, a.createtime) >= c.starttime AND Datepart(weekday, a.createtime) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' "+timecontent+" GROUP BY a.userid, a.name) c ON c.userid = b.ID"
				+ " LEFT JOIN ( SELECT f.id2 userid, g.name, COUNT(*) yccs FROM yhpc_checkplan a LEFT JOIN t_user b ON a.userid = b.ID LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id LEFT JOIN yhpc_checkplan_point e ON e.id1 = a.id LEFT JOIN yhpc_checkplan_user f ON f.id1 = a.id LEFT JOIN t_user g on g.id=f.id2 WHERE a.id1 != 0 AND a.type = '2' GROUP BY f.id2, g.name ) e ON e.userid = b.id )b on t.id=b.userid left join ( SELECT b.ID userid, b.name username, (ISNULL(d.yhcs, 0)+ISNULL(c.fxcs, 0)) yxcs3, ISNULL(e.yccs, 0) yccs3 FROM t_user b"
				+ " LEFT JOIN ( SELECT a.userid, a.name username, COUNT(a.userid) yhcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(7), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.* ,d.name"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 LEFT JOIN t_user d ON d.id = a.userid WHERE a.checkpointtype = '2' AND b.id1 != 0 AND b.type = '3' AND Day(a.createtime) >= c.starttime AND Day(a.createtime) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' AND b.usetype = '2' "+timecontent+" GROUP BY a.userid, a.name) d ON d.userid = b.ID LEFT JOIN ( SELECT a.userid, a.name username, COUNT(a.userid) fxcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(7), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.* ,d.name"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 LEFT JOIN t_user d ON d.id = a.userid WHERE a.checkpointtype = '1' AND b.id1 != 0 AND b.type = '3' AND Day(a.createtime) >= c.starttime AND Day(a.createtime) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' "+timecontent+" GROUP BY a.userid, a.name) c ON c.userid = b.ID LEFT JOIN ( SELECT f.id2 userid, g.name, COUNT(*) yccs FROM yhpc_checkplan a LEFT JOIN t_user b ON a.userid = b.ID"
				+ " LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id LEFT JOIN yhpc_checkplan_point e ON e.id1 = a.id LEFT JOIN yhpc_checkplan_user f ON f.id1 = a.id LEFT JOIN t_user g on g.id=f.id2 WHERE a.id1 != 0 AND a.type = '3' GROUP BY f.id2, g.name ) e ON e.userid = b.id )c on t.id=c.userid left join ( SELECT b.ID userid, b.name username, (ISNULL(d.yhcs, 0)+ISNULL(c.fxcs, 0)) yxcs4, ISNULL(e.yccs, 0) yccs4 FROM t_user b"
				+ " LEFT JOIN ( SELECT a.userid, a.name username, COUNT(a.userid) yhcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(4), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.* ,d.name"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 LEFT JOIN t_user d ON d.id = a.userid WHERE a.checkpointtype = '2' AND b.id1 != 0 AND b.type = '4' AND MONTH(a.createtime) >= c.starttime AND MONTH(a.createtime) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' AND b.usetype = '2' "+timecontent+" GROUP BY a.userid, a.name) d ON d.userid = b.ID LEFT JOIN ( SELECT a.userid, a.name username, COUNT(a.userid) fxcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(4), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.*,d.name"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 LEFT JOIN t_user d ON d.id = a.userid WHERE a.checkpointtype = '1' AND b.id1 != 0 AND b.type = '4' AND MONTH(a.createtime) >= c.starttime AND MONTH(a.createtime) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' "+timecontent+" GROUP BY a.userid, a.name) c ON c.userid = b.ID"
				+ " LEFT JOIN ( SELECT f.id2 userid, g.name, COUNT(*) yccs FROM yhpc_checkplan a LEFT JOIN t_user b ON a.userid = b.ID LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id LEFT JOIN yhpc_checkplan_point e ON e.id1 = a.id LEFT JOIN yhpc_checkplan_user f ON f.id1 = a.id LEFT JOIN t_user g on g.id=f.id2 WHERE a.id1 != 0 AND a.type = '4' GROUP BY f.id2, g.name ) e ON e.userid = b.id )d on t.id=d.userid"
				+ " where 1=1 and t.usertype='1' "+content+" )a LEFT JOIN (select u.id,COUNT(a.id)wts,sum(case when a.dangerstatus = '3' then 1 else 0 end)zgs from t_user u LEFT JOIN yhpc_checkhiddeninfo a ON a.userid = u.ID AND a.dangerorigin = '1' "+timecontent
				+ " GROUP BY u.ID) b ON a.userid = b.id) " ;
		updateBySql(sql);
	}

	//总检list数量(企业端)
	public int getzjTotalCount2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="select COUNT(*)  from t_user t where 1=1 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	//按隐患点统计list(隐患点)
	public List<Map<String, Object>> khdataGrid3(Map<String, Object> mapData) {
		String timecontent="AND a.createtime between '"+mapData.get("start")+"' and '"+mapData.get("end")+"' ";
		String content=content3(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER ( ORDER BY Round(CAST(ISNULL(a.yxcs, 0) AS float) / CAST(nullif(a.yccs, 0) AS float) * 100, 2) desc) AS RowNumber, a.pointid, a.pointname, a.yxcs, a.yccs, ISNULL(Round(CAST(ISNULL(a.yxcs, 0) AS float) / CAST(nullif(a.yccs, 0) AS float) * 100, 2), 0) bl, '隐患点' flag,b.wts,b.zgs"
				+ " from ( SELECT t.id pointid, t.name pointname, (a.yccs1*"+mapData.get("rj")+"+b.yccs2*"+mapData.get("zj")+"+c.yccs3*"+mapData.get("yj")+"+d.yccs4*"+mapData.get("nj")+") yccs, (a.yxcs1+b.yxcs2+c.yxcs3+d.yxcs4) yxcs FROM yhpc_checkpoint t left join ( SELECT b.ID pointid, b.name pointname, (ISNULL(d.yhcs, 0)) yxcs1, ISNULL(e.yccs, 0) yccs1 FROM yhpc_checkpoint b"
				+ " LEFT JOIN ( SELECT b.id pointid, b.name pointname, COUNT(b.id) yhcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(10), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.*"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 WHERE a.checkpointtype = '2' AND b.id1 != 0 AND b.type = '1' AND CONVERT(varchar(100), a.createtime, 8) >= c.starttime AND CONVERT(varchar(100), a.createtime, 8) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' AND b.usetype = '2' "+timecontent+" GROUP BY b.name, b.id) d ON d.pointid = b.ID"
				+ " LEFT JOIN ( SELECT h.id pointid, h.name pointname, COUNT(*) yccs FROM yhpc_checkplan a LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id LEFT JOIN yhpc_checkplan_point e ON e.id1 = a.id LEFT JOIN yhpc_checkpoint h on e.id2=h.id WHERE a.id1 != 0 AND a.type = '1' AND e.checkpointtype=2 GROUP BY h.id, h.name ) e ON e.pointid = b.id ) a on t.id=a.pointid"
				+ " left join ( SELECT b.ID pointid, b.name pointname, (ISNULL(d.yhcs, 0)) yxcs2, ISNULL(e.yccs, 0) yccs2 FROM yhpc_checkpoint b"
				+ " LEFT JOIN ( SELECT b.id pointid, b.name pointname, COUNT(b.id) yhcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(7), a.createtime, 120), DATEPART(week, a.createtime) ORDER BY a.createtime DESC) AS rn, a.*"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 WHERE a.checkpointtype = '2' AND b.id1 != 0 AND b.type = '2' AND Datepart(weekday, a.createtime) >= c.starttime AND Datepart(weekday, a.createtime) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' AND b.usetype = '2' "+timecontent+" GROUP BY b.id, b.name) d ON d.pointid = b.ID"
				+ " LEFT JOIN ( SELECT h.id pointid, h.name pointname, COUNT(*) yccs FROM yhpc_checkplan a LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id LEFT JOIN yhpc_checkplan_point e ON e.id1 = a.id LEFT JOIN yhpc_checkpoint h on e.id2=h.id WHERE a.id1 != 0 AND a.type = '2' AND e.checkpointtype=2 GROUP BY h.id, h.name ) e ON e.pointid = b.id )b on t.id=b.pointid"
				+ " left join ( SELECT b.ID pointid, b.name pointname, (ISNULL(d.yhcs, 0)) yxcs3, ISNULL(e.yccs, 0) yccs3 FROM yhpc_checkpoint b"
				+ " LEFT JOIN ( SELECT b.id pointid, b.name pointname, COUNT(b.id) yhcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(7), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.*"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 WHERE a.checkpointtype = '2' AND b.id1 != 0 AND b.type = '3' AND Day(a.createtime) >= c.starttime AND Day(a.createtime) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' AND b.usetype = '2' "+timecontent+" GROUP BY b.name, b.id) d ON d.pointid = b.ID"
				+ " LEFT JOIN ( SELECT h.id pointid, h.name pointname, COUNT(*) yccs FROM yhpc_checkplan a LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id LEFT JOIN yhpc_checkplan_point e ON e.id1 = a.id LEFT JOIN yhpc_checkpoint h on e.id2=h.id WHERE a.id1 != 0 AND a.type = '3' and e.checkpointtype=2 GROUP BY h.id, h.name ) e ON e.pointid = b.id )c on t.id=c.pointid"
				+ " left join ( SELECT b.ID pointid, b.name pointname, (ISNULL(d.yhcs, 0)) yxcs4, ISNULL(e.yccs, 0) yccs4 FROM yhpc_checkpoint b"
				+ " LEFT JOIN ( SELECT b.id pointid, b.name pointname, COUNT(b.id) yhcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(4), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.*"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 WHERE a.checkpointtype = '2' AND b.id1 != 0 AND b.type = '4' AND MONTH(a.createtime) >= c.starttime AND MONTH(a.createtime) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' AND b.usetype = '2' "+timecontent+" GROUP BY b.name, b.id) d ON d.pointid = b.ID"
				+ " LEFT JOIN ( SELECT h.id pointid, h.name pointname, COUNT(*) yccs FROM yhpc_checkplan a LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id LEFT JOIN yhpc_checkplan_point e ON e.id1 = a.id LEFT JOIN yhpc_checkpoint h on e.id2=h.id and e.checkpointtype=2 GROUP BY h.id, h.name ) e ON e.pointid = b.id )d on t.id=d.pointid where t.usetype = '2' "+content+" )a"
				+ " LEFT JOIN (select u.id,COUNT(a.id)wts,sum(case when a.dangerstatus = '3' then 1 else 0 end)zgs from yhpc_checkpoint u LEFT JOIN yhpc_checkhiddeninfo a ON a.pointid = u.id AND a.dangerorigin = '1' AND a.checkpointtype = 2 "+timecontent+" WHERE u.usetype = '2'"
				+ " GROUP BY u.id) b ON a.pointid = b.id)as s WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)" ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	//按隐患点统计list(风险点)
	public List<Map<String, Object>> khdataGrid4(Map<String, Object> mapData) {
		String timecontent="AND a.createtime between '"+mapData.get("start")+"' and '"+mapData.get("end")+"' ";
		String content=content3(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER ( ORDER BY Round(CAST(ISNULL(a.yxcs, 0) AS float) / CAST(nullif(a.yccs, 0) AS float) * 100, 2) desc) AS RowNumber, a.pointid, a.pointname, a.yxcs, a.yccs, ISNULL(Round(CAST(ISNULL(a.yxcs, 0) AS float) / CAST(nullif(a.yccs, 0) AS float) * 100, 2), 0) bl, '风险点' flag,b.wts,b.zgs"
				+ " from ( SELECT t.id pointid, t.m1 pointname, (a.yccs1*"+mapData.get("rj")+"+b.yccs2*"+mapData.get("zj")+"+c.yccs3*"+mapData.get("yj")+"+d.yccs4*"+mapData.get("nj")+") yccs, (a.yxcs1+b.yxcs2+c.yxcs3+d.yxcs4) yxcs FROM fxgk_accidentrisk t left join ( SELECT b.ID pointid, b.m1 pointname, (ISNULL(d.yhcs, 0)) yxcs1, ISNULL(e.yccs, 0) yccs1 FROM fxgk_accidentrisk b"
				+ " LEFT JOIN ( SELECT b.id pointid, b.m1 pointname, COUNT(b.id) yhcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(10), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.*"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 WHERE a.checkpointtype = '1' AND b.id1 != 0 AND b.type = '1' AND CONVERT(varchar(100), a.createtime, 8) >= c.starttime AND CONVERT(varchar(100), a.createtime, 8) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' "+timecontent+" GROUP BY b.m1, b.id) d ON d.pointid = b.ID"
				+ " LEFT JOIN ( SELECT h.id pointid, h.m1 pointname, COUNT(*) yccs FROM yhpc_checkplan a LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id LEFT JOIN yhpc_checkplan_point e ON e.id1 = a.id LEFT JOIN fxgk_accidentrisk h on e.id2=h.id WHERE a.id1 != 0 AND a.type = '1' AND e.checkpointtype=1 GROUP BY h.id, h.m1 ) e ON e.pointid = b.id ) a on t.id=a.pointid"
				+ " left join ( SELECT b.ID pointid, b.m1 pointname, (ISNULL(d.yhcs, 0)) yxcs2, ISNULL(e.yccs, 0) yccs2 FROM fxgk_accidentrisk b"
				+ " LEFT JOIN ( SELECT b.id pointid, b.m1 pointname, COUNT(a.qyid) yhcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(7), a.createtime, 120), DATEPART(week, a.createtime) ORDER BY a.createtime DESC) AS rn, a.*"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 WHERE a.checkpointtype = '1' AND b.id1 != 0 AND b.type = '2' AND Datepart(weekday, a.createtime) >= c.starttime AND Datepart(weekday, a.createtime) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' "+timecontent+" GROUP BY b.id, b.m1) d ON d.pointid = b.ID"
				+ " LEFT JOIN ( SELECT h.id pointid, h.m1 pointname, COUNT(*) yccs FROM yhpc_checkplan a LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id LEFT JOIN yhpc_checkplan_point e ON e.id1 = a.id LEFT JOIN fxgk_accidentrisk h on e.id2=h.id WHERE a.id1 != 0 AND a.type = '2' AND e.checkpointtype=1 GROUP BY h.id, h.m1 ) e ON e.pointid = b.id )b on t.id=b.pointid"
				+ " left join ( SELECT b.ID pointid, b.m1 pointname, (ISNULL(d.yhcs, 0)) yxcs3, ISNULL(e.yccs, 0) yccs3 FROM fxgk_accidentrisk b"
				+ " LEFT JOIN ( SELECT b.id pointid, b.m1 pointname, COUNT(b.id) yhcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(7), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.*"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 WHERE a.checkpointtype = '1' AND b.id1 != 0 AND b.type = '3' AND Day(a.createtime) >= c.starttime AND Day(a.createtime) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' "+timecontent+" GROUP BY b.m1, b.id) d ON d.pointid = b.ID"
				+ " LEFT JOIN ( SELECT h.id pointid, h.m1 pointname, COUNT(*) yccs FROM yhpc_checkplan a LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id LEFT JOIN yhpc_checkplan_point e ON e.id1 = a.id LEFT JOIN fxgk_accidentrisk h on e.id2=h.id WHERE a.id1 != 0 AND a.type = '3' and e.checkpointtype=1 GROUP BY h.id, h.m1 ) e ON e.pointid = b.id )c on t.id=c.pointid"
				+ " left join ( SELECT b.ID pointid, b.m1 pointname, (ISNULL(d.yhcs, 0)) yxcs4, ISNULL(e.yccs, 0) yccs4 FROM fxgk_accidentrisk b"
				+ " LEFT JOIN ( SELECT b.id pointid, b.m1 pointname, COUNT(b.id) yhcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(4), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.*"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 WHERE a.checkpointtype = '1' AND b.id1 != 0 AND b.type = '4' AND MONTH(a.createtime) >= c.starttime AND MONTH(a.createtime) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' "+timecontent+" GROUP BY b.m1, b.id) d ON d.pointid = b.ID"
				+ " LEFT JOIN ( SELECT h.id pointid, h.m1 pointname, COUNT(*) yccs FROM yhpc_checkplan a LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id LEFT JOIN yhpc_checkplan_point e ON e.id1 = a.id LEFT JOIN fxgk_accidentrisk h on e.id2=h.id WHERE a.id1 != 0 AND a.type = '4' and e.checkpointtype=1 GROUP BY h.id, h.m1 ) e ON e.pointid = b.id )d on t.id=d.pointid where t.s3 = 0 "+content+" )a"
				+ " LEFT JOIN (select u.id,COUNT(a.id)wts,sum(case when a.dangerstatus = '3' then 1 else 0 end)zgs from fxgk_accidentrisk u LEFT JOIN yhpc_checkhiddeninfo a ON a.pointid = u.id AND a.dangerorigin = '1' AND a.checkpointtype = 1 "+timecontent+" WHERE u.s3 = 0"
				+ " GROUP BY u.id) b ON a.pointid = b.id)as s WHERE RowNumber  > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)" ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	//总检list数量(隐患点)
	public int getzjTotalCount3(Map<String, Object> mapData) {
		String content=content3(mapData);
		String sql="select COUNT(*)  from yhpc_checkpoint t where usetype = '2' "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	//总检list数量(风险点)
	public int getzjTotalCount4(Map<String, Object> mapData) {
		String content=content3(mapData);
		String sql="select COUNT(*)  from fxgk_accidentrisk t where s3 = 0 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	//按任务名称统计list(企业端)
	public List<Map<String, Object>> khdataGrid5(Map<String, Object> mapData) {
		String timecontent="AND a.createtime between '"+mapData.get("start")+"' and '"+mapData.get("end")+"' ";
		String content=content3(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER ( ORDER BY Round(CAST(ISNULL(a.yxcs, 0) AS float) / CAST(nullif(a.yccs, 0) AS float) * 100, 2) desc) AS RowNumber, a.planid, a.planname, a.yxcs, a.yccs, ISNULL(Round(CAST(ISNULL(a.yxcs, 0) AS float) / CAST(nullif(a.yccs, 0) AS float) * 100, 2), 0) bl, '隐患点' flag,b.wts,b.zgs"
				+ " from ( SELECT t.id planid, t.name planname, (a.yccs1*"+mapData.get("rj")+"+b.yccs2*"+mapData.get("zj")+"+c.yccs3*"+mapData.get("yj")+"+d.yccs4*"+mapData.get("nj")+") yccs, (a.yxcs1+b.yxcs2+c.yxcs3+d.yxcs4) yxcs FROM yhpc_checkplan t left join ( SELECT b.ID planid, b.name planname, (ISNULL(d.yhcs, 0)) yxcs1, ISNULL(e.yccs, 0) yccs1 FROM yhpc_checkplan b"
				+ " LEFT JOIN ( SELECT c.id planid, c.name planname, COUNT(c.id) yhcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(10), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.*"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 WHERE b.id1 != 0 AND b.type = '1' AND CONVERT(varchar(100), a.createtime, 8) >= c.starttime AND CONVERT(varchar(100), a.createtime, 8) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id LEFT JOIN yhpc_checkplan c ON c.id = a.checkplan_id WHERE b.usetype = '2' "+timecontent+" GROUP BY c.name, c.id) d ON d.planid = b.ID"
				+ " LEFT JOIN ( SELECT a.id planid, a.name planname, COUNT(*) yccs FROM yhpc_checkplan a LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id LEFT JOIN yhpc_checkplan_point e ON e.id1 = a.id LEFT JOIN yhpc_checkpoint h on e.id2=h.id WHERE a.id1 != 0 AND a.type = '1' GROUP BY a.id, a.name ) e ON e.planid = b.id ) a on t.id=a.planid"
				+ " left join ( SELECT b.ID planid, b.name planname, (ISNULL(d.yhcs, 0)) yxcs2, ISNULL(e.yccs, 0) yccs2 FROM yhpc_checkplan b"
				+ " LEFT JOIN ( SELECT c.id planid, c.name planname, COUNT(c.id) yhcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(7), a.createtime, 120), DATEPART(week, a.createtime) ORDER BY a.createtime DESC) AS rn, a.*"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 WHERE b.id1 != 0 AND b.type = '2' AND Datepart(weekday, a.createtime) >= c.starttime AND Datepart(weekday, a.createtime) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id LEFT JOIN yhpc_checkplan c ON c.id = a.checkplan_id WHERE b.usetype = '2' "+timecontent+" GROUP BY c.id, c.name) d ON d.planid = b.ID"
				+ " LEFT JOIN ( SELECT a.id planid, a.name planname, COUNT(*) yccs FROM yhpc_checkplan a LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id LEFT JOIN yhpc_checkplan_point e ON e.id1 = a.id LEFT JOIN yhpc_checkpoint h on e.id2=h.id WHERE a.id1 != 0 AND a.type = '2' GROUP BY a.id, a.name ) e ON e.planid = b.id )b on t.id=b.planid"
				+ " left join ( SELECT b.ID planid, b.name planname, (ISNULL(d.yhcs, 0)) yxcs3, ISNULL(e.yccs, 0) yccs3 FROM yhpc_checkplan b"
				+ " LEFT JOIN ( SELECT c.id planid, c.name planname, COUNT(b.id) yhcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(7), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.*"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 WHERE b.id1 != 0 AND b.type = '3' AND Day(a.createtime) >= c.starttime AND Day(a.createtime) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id LEFT JOIN yhpc_checkplan c ON c.id = a.checkplan_id WHERE b.usetype = '2' "+timecontent+" GROUP BY c.name, c.id) d ON d.planid = b.ID"
				+ " LEFT JOIN ( SELECT a.id planid, a.name planname, COUNT(*) yccs FROM yhpc_checkplan a LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id LEFT JOIN yhpc_checkplan_point e ON e.id1 = a.id LEFT JOIN yhpc_checkpoint h on e.id2=h.id WHERE a.id1 != 0 AND a.type = '3' GROUP BY a.id, a.name ) e ON e.planid = b.id )c on t.id=c.planid"
				+ " left join ( SELECT b.ID planid, b.name planname, (ISNULL(d.yhcs, 0)) yxcs4, ISNULL(e.yccs, 0) yccs4 FROM yhpc_checkplan b"
				+ " LEFT JOIN ( SELECT c.id planid, c.name planname, COUNT(b.id) yhcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(4), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.*"
				+ " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 WHERE b.id1 != 0 AND b.type = '4' AND MONTH(a.createtime) >= c.starttime AND MONTH(a.createtime) <= c.endtime) a where a.rn = 1 ) a"
				+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id LEFT JOIN yhpc_checkplan c ON c.id = a.checkplan_id WHERE b.usetype = '2' "+timecontent+" GROUP BY c.name, c.id) d ON d.planid = b.ID"
				+ " LEFT JOIN ( SELECT a.id planid, a.name planname, COUNT(*) yccs FROM yhpc_checkplan a LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id LEFT JOIN yhpc_checkplan_point e ON e.id1 = a.id LEFT JOIN yhpc_checkpoint h on e.id2=h.id  GROUP BY a.id, a.name ) e ON e.planid = b.id )d on t.id=d.planid where 1=1  "+content+" )a"
				+ " LEFT JOIN (SELECT u.id,COUNT(a.id)wts,sum(case when a.dangerstatus = '3' then 1 else 0 end)zgs FROM yhpc_checkplan u LEFT JOIN yhpc_checkresult t ON u.id = t.checkplan_id"
				+ " LEFT JOIN yhpc_checkhiddeninfo a ON t.id = a.checkresult_id AND a.dangerorigin = '1' "+timecontent
				+ " GROUP BY u.id) b ON a.planid = b.id)as s WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)" ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	//按任务名称统计list数量(企业端)
	public int getzjTotalCount5(Map<String, Object> mapData) {
		//String content=content(mapData);
		String sql="select COUNT(*)  from yhpc_checkplan a where a.id1= "+mapData.get("qyid");
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}	
	
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND bis.id2 like '"+mapData.get("xzqy")+"%' "; 
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND bis.m36='"+mapData.get("jglx")+"' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + "AND bis.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND t.id2 = '"+mapData.get("qyid")+"' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( bis.fid='"+mapData.get("fid")+"' or bis.id='"+mapData.get("fid")+"') "; 
		}
		return content;
	}

	/**
	 * 企业巡检监督与考核list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String content2=content2(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM (select ROW_NUMBER() OVER (ORDER BY isnull(d.num,0) desc ) AS RowNumber,h.qyname,isnull(d.num,0) num from (SELECT DISTINCT c.m1 qyname,c.id  from bis_enterprise  c "
				+ " LEFT JOIN yhpc_checkpoint b on b.ID1=c.id  "
				+ " left join yhpc_checkresult a on a.checkpoint_id=b.id where b.usetype='2' "+content2+" "
				+ " UNION SELECT DISTINCT c.m1 qyname,c.id "
				+ " from bis_enterprise  c LEFT JOIN fxgk_accidentrisk b on b.ID1=c.id "
				+ " left join yhpc_checkresult a on a.checkpoint_id=b.id where 1=1 and c.s3=0 "+content2+") h "
				+ " left join (select s.id,count(*) num from(select c.id from yhpc_checkresult a "
				+ " left join yhpc_checkpoint b on a.checkpoint_id=b.id "
				+ " left join bis_enterprise c on b.ID1=c.id "
				+ " where b.usetype=2 "+content+" "
				+ " UNION ALL select c.id from yhpc_checkresult a left join fxgk_accidentrisk b on a.checkpoint_id=b.id "
				+ " left join bis_enterprise c on b.ID1=c.id "
				+ " where c.s3=0 "+content+")s GROUP BY s.id) d on d.id=h.id) "
				+ " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 查询企业巡检监督与考核list的个数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String content2=content2(mapData);
		String sql=" SELECT COUNT(*) from bis_enterprise c   "
				+ " where c.s3=0 "+content2;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	

	
	/**
     * 企业查询条件判断2
     * @return
	 * @throws ParseException 
     */
	public String content2(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + " AND c.m1 like '%"+mapData.get("qyname")+"%'"; 
		}
		
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND c.id2 like '"+mapData.get("xzqy")+"%' "; 
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND c.m36='"+mapData.get("jglx")+"' "; 
		}
		return content;
	}
	
	/**
     * 企业查询条件判断3
     * @return
	 * @throws ParseException 
     */
	public String content3(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + " AND t.id1='"+mapData.get("qyid")+"'"; 
		}
		return content;
	}
}
