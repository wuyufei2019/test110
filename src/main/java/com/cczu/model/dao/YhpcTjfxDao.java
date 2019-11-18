package com.cczu.model.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_CheckResultEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 统计分析dao层
 * zpc
 */
@Repository("YhpcTjfxDao")
public class YhpcTjfxDao extends BaseDao<YHPC_CheckResultEntity, Long>{

	/**
	 * 统计分析list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String content2=content2(mapData);
		String ordercontent = "count_e desc";
		if(mapData.get("orderBy")!=null&&mapData.get("orderBy")!=""){
			ordercontent = mapData.get("orderBy")+" "+mapData.get("order");
		}
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER (ORDER BY "+ordercontent+") AS RowNumber,* from (select SUM(CASE WHEN z.checkresult = '0' THEN 1 ELSE 0 END) count_a,SUM(CASE WHEN z.checkresult = '1' THEN 1 ELSE 0 END) count_b,COUNT(z.qyid)-SUM(CASE WHEN z.checkresult = '0' THEN 1 ELSE 0 END)-SUM(CASE WHEN z.checkresult = '1' THEN 1 ELSE 0 END) count_c,"
				+ "COUNT(z.qyid) count_d,SUM(CASE WHEN z.checkresult = '0' THEN 1 ELSE 0 END)+SUM(CASE WHEN z.checkresult = '1' THEN 1 ELSE 0 END) count_e,z.qyid,z.qyname "
				+ "FROM (SELECT a.*,b.m1 qyname FROM(SELECT b.checkresult,b.createtime,b.userid,b.id,a.id jcdid,a.m1 jcdname,a.id1 qyid,1 jcdtype FROM fxgk_accidentrisk a "
				+ "LEFT JOIN (SELECT h.* FROM (SELECT ROW_NUMBER() OVER (partition by checkpoint_id,checkpointtype ORDER BY createtime DESC) AS rn,* FROM yhpc_checkresult "
				+ "WHERE checkpointtype= '1' "+content2+") h WHERE h.rn<=1) b ON a.id = b.checkpoint_id WHERE a.s3 = 0 UNION SELECT b.checkresult,b.createtime,b.userid,b.id,a.id jcdid,a.name jcdname,a.id1 qyid,2 jcdtype "
				+ "FROM yhpc_checkpoint a LEFT JOIN (SELECT h.* FROM (SELECT ROW_NUMBER() OVER (partition by checkpoint_id,checkpointtype ORDER BY createtime DESC) AS rn,* FROM yhpc_checkresult "
				+ "WHERE checkpointtype= '2' "+content2+") h WHERE h.rn<=1) b ON a.id = b.checkpoint_id where a.usetype = '2') a LEFT JOIN bis_enterprise b ON a.qyid = b.id where b.s3=0 " + content + " ) z GROUP BY z.qyid,z.qyname) zz) "
				+ "as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)" ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 统计分析list的个数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String content2=content2(mapData);
		String sql="SELECT COUNT(*) FROM (SELECT z.qyid FROM (SELECT a.*,b.m1 qyname FROM(SELECT b.checkresult,b.createtime,b.userid,b.id,a.id jcdid,a.m1 jcdname,a.id1 qyid,1 jcdtype FROM fxgk_accidentrisk a "
				+ "LEFT JOIN (SELECT h.* FROM (SELECT ROW_NUMBER() OVER (partition by checkpoint_id,checkpointtype ORDER BY createtime DESC) AS rn,* FROM yhpc_checkresult "
				+ "WHERE checkpointtype= '1' "+content2+") h WHERE h.rn<=1) b ON a.id = b.checkpoint_id WHERE a.s3 = 0 UNION SELECT b.checkresult,b.createtime,b.userid,b.id,a.id jcdid,a.name jcdname,a.id1 qyid,2 jcdtype "
				+ "FROM yhpc_checkpoint a LEFT JOIN (SELECT h.* FROM (SELECT ROW_NUMBER() OVER (partition by checkpoint_id,checkpointtype ORDER BY createtime DESC) AS rn,* FROM yhpc_checkresult "
				+ "WHERE checkpointtype= '2' "+content2+") h WHERE h.rn<=1) b ON a.id = b.checkpoint_id  where a.usetype = '2') a LEFT JOIN bis_enterprise b ON a.qyid = b.id where b.s3=0 " + content + " ) z GROUP BY z.qyid) h";
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
			content = content + "AND b.id2 like '"+mapData.get("xzqy")+"%' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + "AND b.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND b.m36='"+mapData.get("jglx")+"' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( b.fid='"+mapData.get("fid")+"' or b.id='"+mapData.get("fid")+"') "; 
		}
		return content;
	}
	
	/**
     * 查询条件判断2
     * @return
	 * @throws ParseException 
     */
	public String content2(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			content = content + "AND createtime >= '"+mapData.get("starttime")+"' "; 
		}
		if(mapData.get("finishtime")!=null&&mapData.get("finishtime")!=""){
			content = content + "AND createtime <= '"+mapData.get("finishtime")+"' "; 
		}
		return content;
	}
	
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String content2=content2(mapData);
		String sql="SELECT SUM(CASE WHEN z.checkresult = '0' THEN 1 ELSE 0 END)+SUM(CASE WHEN z.checkresult = '1' THEN 1 ELSE 0 END) count_d,COUNT(z.qyid) count_e,SUM(CASE WHEN z.checkresult = '0' THEN 1 ELSE 0 END) count_a,SUM(CASE WHEN z.checkresult = '1' THEN 1 ELSE 0 END) count_b,COUNT(z.qyid)-SUM(CASE WHEN z.checkresult = '0' THEN 1 ELSE 0 END)-SUM(CASE WHEN z.checkresult = '1' THEN 1 ELSE 0 END) count_c,z.qyid,z.qyname "
				+ "FROM (SELECT a.*,b.m1 qyname FROM(SELECT b.checkresult,b.createtime,b.userid,b.id,a.id jcdid,a.m1 jcdname,a.id1 qyid,1 jcdtype FROM fxgk_accidentrisk a "
				+ "LEFT JOIN (SELECT h.* FROM (SELECT ROW_NUMBER() OVER (partition by checkpoint_id,checkpointtype ORDER BY createtime DESC) AS rn,* FROM yhpc_checkresult "
				+ "WHERE checkpointtype= '1' "+content2+") h WHERE h.rn<=1) b ON a.id = b.checkpoint_id WHERE a.s3 = 0 UNION SELECT b.checkresult,b.createtime,b.userid,b.id,a.id jcdid,a.name jcdname,a.id1 qyid,2 jcdtype "
				+ "FROM yhpc_checkpoint a LEFT JOIN (SELECT h.* FROM (SELECT ROW_NUMBER() OVER (partition by checkpoint_id,checkpointtype ORDER BY createtime DESC) AS rn,* FROM yhpc_checkresult "
				+ "WHERE checkpointtype= '2' "+content2+") h WHERE h.rn<=1) b ON a.id = b.checkpoint_id  where a.usetype = '2') a LEFT JOIN bis_enterprise b ON a.qyid = b.id where b.s3=0 " + content + " ) z GROUP BY z.qyid,z.qyname order by z.qyid";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}

	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String tjcontent(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND bis.id2 like '"+mapData.get("xzqy")+"%' "; 
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND bis.m36='"+mapData.get("jglx")+"' "; 
		}
		//添加监管类型查询条件
		if(mapData.get("day")!=null&&mapData.get("day")!=""){
			content = content + "AND bis.m36='"+mapData.get("jglx")+"' "; 
		}
		//添加监管类型查询条件
		if(mapData.get("month")!=null&&mapData.get("month")!=""){
			content = content + "AND bis.m36='"+mapData.get("jglx")+"' "; 
		}
		//添加监管类型查询条件
		if(mapData.get("year")!=null&&mapData.get("year")!=""){
			content = content + "AND bis.m36='"+mapData.get("jglx")+"' "; 
		}
		
		return content;
	}
	
	/**
	 * 企业总数
	 * @param mapData
	 * @return
	 */
	public int getQyCount(Map<String, Object> mapData) {
		String content=tjcontent(mapData);
		String sql=" SELECT COUNT(*) SUM  FROM bis_enterprise bis WHERE bis.S3=0 and bis.M1 is not null "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
	 * 已安装企业数
	 * @param mapData
	 * @return
	 */
	public int getAzQyCount(Map<String, Object> mapData) {
		String content=tjcontent(mapData);
		String sql=" select count(distinct s.id1) from("
				+ " (select a.id1 from fxgk_accidentrisk a where a.s3=0 and (a.rfid is not null or a.bindcontent is not null) )"
				+ " union (select b.id1 from yhpc_checkpoint b where b.usetype=2 and (b.rfid is not null or b.bindcontent is not null)) ) s "
				+ " left join bis_enterprise bis on s.id1=bis.id where bis.S3=0 and bis.M1 is not null "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
	 * 本月已巡检企业数
	 * @param mapData
	 * @return
	 */
	public int getXjQyCount(Map<String, Object> mapData) {
		String content=tjcontent(mapData);
		String sql=" SELECT COUNT(DISTINCT s.qyid) yxjqy FROM (SELECT b.id1 qyid,a.createtime FROM yhpc_checkresult a "
				+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' AND b.s3 = 0 "
				+ " UNION SELECT b.id1 qyid,a.createtime FROM yhpc_checkresult a "
				+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' and b.usetype = '2') s "
				+ " LEFT JOIN bis_enterprise bis ON bis.id = s.qyid "
				+ " WHERE bis.s3 = 0 AND convert(varchar(7),s.createtime,120)= convert(varchar(7),GETDATE(),120) "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
	 * 本月检查记录数
	 * @param mapData
	 * @return
	 */
	public int getJcCount(Map<String, Object> mapData) {
		String content=tjcontent(mapData);
		String sql=" SELECT COUNT(s.qyid) xjjls FROM (SELECT b.id1 qyid,a.createtime FROM yhpc_checkresult a "
				+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' AND b.s3 = 0 "
				+ " UNION SELECT b.id1 qyid,a.createtime FROM yhpc_checkresult a "
				+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' and b.usetype = '2') s "
				+ " LEFT JOIN bis_enterprise bis ON bis.id = s.qyid "
				+ " WHERE bis.s3 = 0 AND convert(varchar(7),s.createtime,120)= convert(varchar(7),GETDATE(),120) "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
	 * 本月检查隐患数
	 * @param mapData
	 * @return
	 */
	public int getYhCount(Map<String, Object> mapData) {
		String content=tjcontent(mapData);
		String sql=" SELECT count(s.id) jcyhs FROM yhpc_checkhiddeninfo s "
				+ " LEFT JOIN bis_enterprise bis on s.qyid=bis.id "
				+ " where s.dangerorigin='1' AND bis.s3 = 0 AND convert(varchar(7),s.createtime,120)= convert(varchar(7),GETDATE(),120) "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
	 * 本月未整改隐患数
	 * @param mapData
	 * @return
	 */
	public int getWzgYhCount(Map<String, Object> mapData) {
		String content=tjcontent(mapData);
		String sql=" SELECT isNull(count(s.id),0) jcyhs FROM yhpc_checkhiddeninfo s "
				+ " LEFT JOIN bis_enterprise bis on s.qyid=bis.id "
				+ " where s.dangerorigin='1' AND bis.s3 = 0 AND s.dangerstatus='0' AND convert(varchar(7),s.createtime,120)= convert(varchar(7),GETDATE(),120) "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
	 * 本月已整改隐患数
	 * @param mapData
	 * @return
	 */
	public int getYzgYhCount(Map<String, Object> mapData) {
		String content=tjcontent(mapData);
		String sql=" SELECT isNull(count(s.id),0) jcyhs FROM yhpc_checkhiddeninfo s "
				+ " LEFT JOIN bis_enterprise bis on s.qyid=bis.id "
				+ " where s.dangerorigin='1' AND bis.s3 = 0 AND s.dangerstatus!='0' AND convert(varchar(7),s.createtime,120)= convert(varchar(7),GETDATE(),120) "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	public Map<String, Object> tjcount(Map<String, Object> mapData) {
		String content=tjcontent(mapData);
		StringBuffer sql=new StringBuffer();
		sql.append("select * from ");
		//企业总数
		sql.append("(SELECT COUNT(*) qysum  FROM bis_enterprise bis WHERE bis.S3=0 and bis.M1 is not null "+ content+") a");
		//已安装企业数
		sql.append(",(select count(distinct s.id1) azsum from("
				+ " (select a.id1 from fxgk_accidentrisk a where a.s3=0 and (a.rfid is not null or a.bindcontent is not null) )"
				+ " union (select b.id1 from yhpc_checkpoint b where b.usetype=2 and (b.rfid is not null or b.bindcontent is not null)) ) s "
				+ " left join bis_enterprise bis on s.id1=bis.id where bis.S3=0 and bis.M1 is not null "+ content+") b");
		//本月已巡检企业数
		sql.append(",(SELECT COUNT(DISTINCT s.qyid) yxjsum FROM (SELECT b.id1 qyid,a.createtime FROM yhpc_checkresult a "
				+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' AND b.s3 = 0 "
				+ " UNION SELECT b.id1 qyid,a.createtime FROM yhpc_checkresult a "
				+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' and b.usetype = '2') s "
				+ " LEFT JOIN bis_enterprise bis ON bis.id = s.qyid "
				+ " WHERE bis.s3 = 0 AND convert(varchar(7),s.createtime,120)= convert(varchar(7),GETDATE(),120) "+ content+") c");
		//本月检查记录数
		sql.append(",(SELECT COUNT(s.qyid) jcsum FROM (SELECT b.id1 qyid,a.createtime FROM yhpc_checkresult a "
				+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' AND b.s3 = 0 "
				+ " UNION SELECT b.id1 qyid,a.createtime FROM yhpc_checkresult a "
				+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' and b.usetype = '2') s "
				+ " LEFT JOIN bis_enterprise bis ON bis.id = s.qyid "
				+ " WHERE bis.s3 = 0 AND convert(varchar(7),s.createtime,120)= convert(varchar(7),GETDATE(),120) "+ content+") d");
		//本月检查隐患数
				sql.append(",(SELECT count(s.id) jcyhsum FROM yhpc_checkhiddeninfo s "
				+ " LEFT JOIN bis_enterprise bis on s.qyid=bis.id "
				+ " where s.dangerorigin='1' AND bis.s3 = 0 AND convert(varchar(7),s.createtime,120)= convert(varchar(7),GETDATE(),120) "+ content+") e");
		//本月未整改隐患数
				sql.append(",(SELECT isNull(count(s.id),0) wzgsum FROM yhpc_checkhiddeninfo s "
				+ " LEFT JOIN bis_enterprise bis on s.qyid=bis.id "
				+ " where s.dangerorigin='1' AND bis.s3 = 0 AND s.dangerstatus='0' AND convert(varchar(7),s.createtime,120)= convert(varchar(7),GETDATE(),120) "+ content+") f");
		//本月已整改隐患数
				sql.append(",(SELECT isNull(count(s.id),0) yzgsum FROM yhpc_checkhiddeninfo s "
				+ " LEFT JOIN bis_enterprise bis on s.qyid=bis.id "
				+ " where s.dangerorigin='1' AND bis.s3 = 0 AND s.dangerstatus!='0' AND convert(varchar(7),s.createtime,120)= convert(varchar(7),GETDATE(),120) "+ content+") g");
		List<Map<String, Object>> list=findBySql(sql.toString(), null, Map.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	public List<Map<String, Object>> sjzl(Map<String, Object> mapData) {
		String content=tjcontent(mapData);
		String sql="";
		if(mapData.get("type").equals("1")){//企业总数
		sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER (ORDER BY bis.id) AS RowNumber,bis.id,bis.m1,bis.m19,bis.m21,"
				+ "bis.m23,bis.m25,bis.m39,bis.m40,bis.m47,bis.m48,bis.m44 FROM bis_enterprise bis WHERE bis.S3=0 and bis.M1 is not null " + content + " ) "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)" ;
		}else if(mapData.get("type").equals("2")){//已上线企业
			sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER (ORDER BY bis.id) AS RowNumber,bis.id,bis.m1,bis.m19,bis.m21,"
					+ "bis.m23,bis.m25,bis.m39,bis.m40,bis.m47,bis.m48,bis.m44 FROM ((select a.id1 from fxgk_accidentrisk a where a.s3=0 and ( a.rfid is not null or a.bindcontent is not null) ) union ( select b.id1 from yhpc_checkpoint b where b.usetype=2 and ( b.rfid is not null or b.bindcontent is not null ) ) ) s left join bis_enterprise bis on s.id1=bis.id"
					+ " WHERE bis.S3=0 and bis.M1 is not null "+ content + " ) "
					+ "as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)" ;
		}else if(mapData.get("type").equals("3")){//本月已巡检企业
			sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER (ORDER BY bis.id) AS RowNumber,bis.id,bis.m1,bis.m19,bis.m21,"
					+ " bis.m23,bis.m25,bis.m39,bis.m40,bis.m47,bis.m48,bis.m44 FROM (select distinct b.qyid from(SELECT b.id1 qyid, a.createtime FROM yhpc_checkresult a LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' AND convert(varchar(7),a.createtime,120)= convert(varchar(7),GETDATE(),120) AND b.s3 = 0 UNION SELECT b.id1 qyid, a.createtime FROM yhpc_checkresult a LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id"
					+ " WHERE a.checkpointtype = '2' and b.usetype = '2' AND convert(varchar(7),a.createtime,120)= convert(varchar(7),GETDATE(),120) )b) s LEFT JOIN bis_enterprise bis ON bis.id = s.qyid WHERE bis.s3 = 0 " + content + " ) "
					+ " as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)" ;
		}else if(mapData.get("type").equals("4")){//本月未巡检企业
			sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER (ORDER BY bis.id) AS RowNumber,bis.id,bis.m1,bis.m19,bis.m21,"
					+ " bis.m23,bis.m25,bis.m39,bis.m40,bis.m47,bis.m48,bis.m44 FROM bis_enterprise bis where id not in (select distinct s.qyid from (SELECT b.id1 qyid, a.createtime FROM yhpc_checkresult a "
					+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' AND b.s3 = 0 "
					+ " UNION SELECT b.id1 qyid, a.createtime FROM yhpc_checkresult a LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id"
					+ " WHERE a.checkpointtype = '2' and b.usetype = '2') s where convert(varchar(7),s.createtime,120)= convert(varchar(7),GETDATE(),120) )"
					+ " and bis.s3 = 0  " + content + " ) "
					+ " as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)" ;
		}else if(mapData.get("type").equals("5")){//本月检查记录数
			sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER (order by qyname) AS RowNumber,* FROM "
					+ "(SELECT ROW_NUMBER() OVER (partition by e.m1,d.jcdname ORDER BY a.createtime DESC) AS rn, a.id,e.m1 qyname,d.jcdname,d.type,d.jcdid,b.name,a.createtime,c.NAME uname,a.checkresult,a.note,a.checkphoto,a.checkpointtype,e.id2,e.m36,d.checkpoint_id "
					+ "FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN t_user c ON c.ID = a.userid LEFT JOIN (SELECT a.id,a.checkpoint_id,b.id jcdid,b.m1 jcdname,'fxd' type,b.id1 qyid FROM yhpc_checkresult a "
					+ "LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' AND b.s3 = 0 UNION SELECT a.id,a.checkpoint_id,b.id jcdid,b.name jcdname,'yhd' type,b.id1 qyid FROM yhpc_checkresult a "
					+ "LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' and b.usetype = '2') d ON d.id = a.id LEFT JOIN bis_enterprise e ON e.id = d.qyid "
					+ "WHERE e.s3 = 0 ) bis WHERE 1=1 AND convert(varchar(7),createtime,120)= convert(varchar(7),GETDATE(),120) "+content+" ) z where RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		}else if(mapData.get("type").equals("6")){//本月检查隐患
			sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY g.m1,bis.m1,x.createtime desc) AS RowNumber,g.m1 wgname,a.jcdname xjdname,bis.m1 qyname,d.content jcnr,d.dangerlevel yhjb,c.NAME yhfxr,x.* "
					+ " FROM ("
					+ " SELECT a.id,a.dangerstatus,b.m1 jcdname,'1' type "
					+ " FROM yhpc_checkhiddeninfo a  "
					+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.pointid"
					+ " WHERE a.checkpointtype = '1' and a.dangerorigin='1' AND b.s3 = 0"
					+ " UNION SELECT a.id,a.dangerstatus,b.name jcdname,'2' type"
					+ " FROM yhpc_checkhiddeninfo a  "
					+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.pointid"
					+ " WHERE a.checkpointtype = '2' and a.dangerorigin='1' and b.usetype='2' ) a "
					+ " LEFT JOIN yhpc_checkhiddeninfo x ON x.id = a.id"
					+ " LEFT JOIN yhpc_checkcontent d on d.id=x.checkcontent_id"
					+ " LEFT JOIN bis_enterprise bis on bis.id=x.qyid"
					+ " LEFT JOIN t_user c on x.userid=c.ID"
					+ " LEFT JOIN t_barrio g on g.code=bis.id2 "
					+ " where bis.s3=0 AND convert(varchar(7),createtime,120)= convert(varchar(7),GETDATE(),120)"
					+ " and a.dangerstatus!='0' " + content + " ) "
					+ " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		}else if(mapData.get("type").equals("7")){//本月未整改隐患
			sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY g.m1,bis.m1,x.createtime desc) AS RowNumber,g.m1 wgname,a.jcdname xjdname,bis.m1 qyname,d.content jcnr,d.dangerlevel yhjb,c.NAME yhfxr,x.* "
					+ " FROM ("
					+ " SELECT a.id,b.m1 jcdname,'1' type "
					+ " FROM yhpc_checkhiddeninfo a  "
					+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.pointid"
					+ " WHERE a.checkpointtype = '1' and a.dangerorigin='1' AND b.s3 = 0"
					+ " UNION SELECT a.id,b.name jcdname,'2' type"
					+ " FROM yhpc_checkhiddeninfo a  "
					+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.pointid"
					+ " WHERE a.checkpointtype = '2' and a.dangerorigin='1' and b.usetype='2' ) a "
					+ " LEFT JOIN yhpc_checkhiddeninfo x ON x.id = a.id"
					+ " LEFT JOIN yhpc_checkcontent d on d.id=x.checkcontent_id"
					+ " LEFT JOIN bis_enterprise bis on bis.id=x.qyid"
					+ " LEFT JOIN t_user c on x.userid=c.ID"
					+ " LEFT JOIN t_barrio g on g.code=bis.id2 "
					+ " where bis.s3=0 AND x.dangerstatus='0' AND convert(varchar(7),createtime,120)= convert(varchar(7),GETDATE(),120)"
					+ content + " ) "
					+ " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		}else if(mapData.get("type").equals("8")){//本月已整改隐患
			sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY g.m1,bis.m1,x.createtime desc) AS RowNumber,g.m1 wgname,a.jcdname xjdname,bis.m1 qyname,d.content jcnr,d.dangerlevel yhjb,c.NAME yhfxr,x.* "
					+ " FROM ("
					+ " SELECT a.id,b.m1 jcdname,'1' type "
					+ " FROM yhpc_checkhiddeninfo a  "
					+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.pointid"
					+ " WHERE a.checkpointtype = '1' and a.dangerorigin='1' AND b.s3 = 0"
					+ " UNION SELECT a.id,b.name jcdname,'2' type"
					+ " FROM yhpc_checkhiddeninfo a  "
					+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.pointid"
					+ " WHERE a.checkpointtype = '2' and a.dangerorigin='1' and b.usetype='2' ) a "
					+ " LEFT JOIN yhpc_checkhiddeninfo x ON x.id = a.id"
					+ " LEFT JOIN yhpc_checkcontent d on d.id=x.checkcontent_id"
					+ " LEFT JOIN bis_enterprise bis on bis.id=x.qyid"
					+ " LEFT JOIN t_user c on x.userid=c.ID"
					+ " LEFT JOIN t_barrio g on g.code=bis.id2 "
					+ " where bis.s3=0 AND x.dangerstatus!='0' AND convert(varchar(7),createtime,120)= convert(varchar(7),GETDATE(),120)"
					+ content + " ) "
					+ " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		}      
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	/**
	 * 统计隐患数目（日）
	 * @param mapData
	 * @return
	 */
	public List<Integer> getDayCount(Map<String, Object> mapData) {
		String content=tjcontent(mapData);
		if(mapData.get("status")!=null &&mapData.get("status")!="")
			if("1".equals(mapData.get("status")))
				content+= " AND s.dangerstatus = '0'";
			else if("2".equals(mapData.get("status")))
				content+= " AND s.dangerstatus != '0'";
		String sql=" SELECT COUNT(*) AS sum FROM yhpc_checkhiddeninfo s LEFT JOIN bis_enterprise bis ON s.qyid = bis.id WHERE s.dangerorigin = '1' AND bis.s3 = 0 AND convert(varchar(10), s.createtime, 120) = convert(varchar(10), GETDATE()-2, 120) "+content+"UNION ALL SELECT COUNT(*) AS sum FROM yhpc_checkhiddeninfo s LEFT JOIN bis_enterprise bis ON s.qyid = bis.id WHERE s.dangerorigin = '1' AND bis.s3 = 0 AND convert(varchar(10), s.createtime, 120) = convert(varchar(10), GETDATE()-1, 120) "+content+" UNION ALL SELECT COUNT(*) AS sum FROM yhpc_checkhiddeninfo s LEFT JOIN bis_enterprise bis ON s.qyid = bis.id WHERE s.dangerorigin = '1'  AND bis.s3 = 0 AND convert(varchar(10), s.createtime, 120) = convert(varchar(10), GETDATE(), 120) "+ content;
		List<Integer> list=findBySql(sql);
		return list;
	}
	
	/**
	 * 统计隐患数目（月）
	 * @param mapData
	 * @return
	 */
	public List<Integer> getMonthCount(Map<String, Object> mapData) {
		String content=tjcontent(mapData);
		if(mapData.get("status")!=null &&mapData.get("status")!="")
			if("1".equals(mapData.get("status")))
				content+= " AND s.dangerstatus = '0'";
			else if("2".equals(mapData.get("status")))
				content+= " AND s.dangerstatus != '0'";
		String sql=" SELECT COUNT(*) AS sum FROM yhpc_checkhiddeninfo s LEFT JOIN bis_enterprise bis ON s.qyid = bis.id WHERE s.dangerorigin = '1' AND bis.s3 = 0 AND convert(varchar(7), DATEADD(MONTH,-0,s.createtime), 120) = convert(varchar(7), DATEADD(MONTH,-2,GETDATE()) , 120) "+content+"UNION ALL SELECT COUNT(*) AS sum FROM yhpc_checkhiddeninfo s LEFT JOIN bis_enterprise bis ON s.qyid = bis.id WHERE s.dangerorigin = '1' AND bis.s3 = 0 AND convert(varchar(7), DATEADD(MONTH,-0,s.createtime) , 120) = convert(varchar(7), DATEADD(MONTH,-1,GETDATE()) , 120) "+content+" UNION ALL SELECT COUNT(*) AS sum FROM yhpc_checkhiddeninfo s LEFT JOIN bis_enterprise bis ON s.qyid = bis.id WHERE s.dangerorigin = '1'  AND bis.s3 = 0 AND convert(varchar(7), DATEADD(MONTH,-0,s.createtime) , 120) = convert(varchar(7), DATEADD(MONTH,-0,GETDATE()), 120) "+ content;
		List<Integer> list=findBySql(sql);
		return list;
	}
	
	/**
	 * 统计隐患数目（年）
	 * @param mapData
	 * @return
	 */
	public List<Integer> getYearCount(Map<String, Object> mapData) {
		String content=tjcontent(mapData);
		if(mapData.get("status")!=null &&mapData.get("status")!="")
			if("1".equals(mapData.get("status")))
				content+= " AND s.dangerstatus = '0'";
			else if("2".equals(mapData.get("status")))
				content+= " AND s.dangerstatus != '0'";
		String sql=" SELECT COUNT(*) AS sum FROM yhpc_checkhiddeninfo s LEFT JOIN bis_enterprise bis ON s.qyid = bis.id WHERE s.dangerorigin = '1' AND bis.s3 = 0 AND convert(varchar(4), DATEADD(Year,-0,s.createtime), 120) = convert(varchar(4), DATEADD(Year,-2,GETDATE()), 120) "+content+"UNION ALL SELECT COUNT(*) AS sum FROM yhpc_checkhiddeninfo s LEFT JOIN bis_enterprise bis ON s.qyid = bis.id WHERE s.dangerorigin = '1' AND bis.s3 = 0 AND convert(varchar(4), DATEADD(Year,-0,s.createtime), 120) = convert(varchar(4), DATEADD(Year,-1,GETDATE()), 120) "+content+" UNION ALL SELECT COUNT(*) AS sum FROM yhpc_checkhiddeninfo s LEFT JOIN bis_enterprise bis ON s.qyid = bis.id WHERE s.dangerorigin = '1'  AND bis.s3 = 0 AND convert(varchar(4), DATEADD(Year,-0,s.createtime), 120) = convert(varchar(4), DATEADD(Year,-0,GETDATE()), 120) "+ content;
		List<Integer> list=findBySql(sql);
		return list;
	}
	
	/**
	 * 统计地区巡检比率
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getDqCount(Map<String, Object> mapData) {
		String content=tjcontent(mapData);
		String sql=" SELECT t.m1 AS name, Round(CAST(ISNULL(b.xjjls, 0) AS FLOAT) / CAST(a.num AS float) * 100, 0) AS value FROM (SELECT id2 AS xzqy, COUNT(1) AS num FROM bis_enterprise bis WHERE bis.S3 = 0 AND bis.M1 IS NOT NULL "+content+" GROUP BY id2 ) a LEFT JOIN (SELECT bis.id2 AS xzqy, COUNT(DISTINCT s.qyid) AS xjjls FROM ( SELECT b.id1 AS qyid, a.createtime FROM yhpc_checkresult a LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' AND b.s3 = 0 UNION SELECT b.id1 AS qyid, a.createtime FROM yhpc_checkresult a LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' AND b.usetype = '2' ) s LEFT JOIN bis_enterprise bis ON bis.id = s.qyid WHERE bis.s3 = 0 AND convert(varchar(7), s.createtime, 120) = convert(varchar(7), GETDATE(), 120) "+content+" GROUP BY bis.id2 ) b ON a.xzqy = b.xzqy LEFT JOIN (SELECT * FROM t_barrio  WHERE id NOT IN (SELECT fid FROM t_barrio) and code like '"+mapData.get("xzqy")+"%' ) t ON t.code = a.xzqy WHERE t.m1 IS NOT NULL ";
		List<Map<String, Object>> list=findBySql(sql);
		return list;
	}
	
	/**
	 * 统计地区隐患整改完成比率
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getDqzgCount(Map<String, Object> mapData) {
		String content=tjcontent(mapData);
		String sql=" SELECT t.m1 AS name, Round(CAST(ISNULL(b.jcyhs, 0) AS FLOAT) / CAST(a.num AS float) * 100, 0) AS value FROM (SELECT id2 AS xzqy, COUNT(1) AS num FROM bis_enterprise bis WHERE bis.S3 = 0 AND bis.M1 IS NOT NULL "+content+" GROUP BY id2 ) a LEFT JOIN (SELECT bis.id2 AS xzqy, COUNT(s.id) AS jcyhs FROM yhpc_checkhiddeninfo s LEFT JOIN bis_enterprise bis ON s.qyid = bis.id WHERE s.dangerorigin = '1' AND bis.s3 = 0 AND s.dangerstatus != '0' AND convert(varchar(7), s.createtime, 120) = convert(varchar(7), GETDATE(), 120) "+content+" GROUP BY bis.id2 ) b ON a.xzqy = b.xzqy LEFT JOIN (SELECT * FROM t_barrio WHERE id NOT IN (SELECT fid FROM t_barrio) AND code LIKE '"+mapData.get("xzqy")+"%' ) t ON t.code = a.xzqy WHERE t.m1 IS NOT NULL";
		List<Map<String, Object>> list=findBySql(sql);
		return list;
	}
	
	/**
	 * 统计地区巡检比率（市级）
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getDqCount2() {
		String sql="SELECT a.name, isNull(Round(CAST(ISNULL(b.xjjls, 0) AS FLOAT) / CAST(a.num AS float) * 100, 2), 0) AS value FROM (SELECT a.code AS xzqy, a.m1 AS name, COUNT(1) AS num FROM (SELECT * FROM t_barrio WHERE fid = ( SELECT id FROM t_barrio WHERE code = ( SELECT code FROM t_barrio WHERE fid = 0 ) ) ) a LEFT JOIN (SELECT * FROM bis_enterprise bis WHERE bis.S3 = 0 AND bis.M1 IS NOT NULL ) b ON b.id2 LIKE a.code + '%' GROUP BY a.code, a.m1 ) a LEFT JOIN (SELECT bis.id2 AS xzqy, COUNT(s.qyid) AS xjjls FROM ( SELECT b.id1 AS qyid, a.createtime FROM yhpc_checkresult a LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' AND b.s3 = 0 UNION SELECT b.id1 AS qyid, a.createtime FROM yhpc_checkresult a LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' AND b.usetype = '2' ) s LEFT JOIN bis_enterprise bis ON bis.id = s.qyid WHERE bis.s3 = 0 AND convert(varchar(7), s.createtime, 120) = convert(varchar(7), GETDATE(), 120) GROUP BY bis.id2 ) b ON b.xzqy = a.xzqy ";
		List<Map<String, Object>> list=findBySql(sql);
		return list;
	}
	
	/**
	 * 统计地区隐患整改完成比率（市级）
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getDqzgCount2() {
		String sql=" SELECT a.name, isNull(Round(CAST(ISNULL(b.jcyhs, 0) AS FLOAT) / CAST(a.num AS float) * 100, 2), 0) AS value FROM (SELECT a.code AS xzqy, a.m1 AS name, COUNT(1) AS num FROM (SELECT * FROM t_barrio WHERE fid = ( SELECT id FROM t_barrio WHERE code = ( SELECT code FROM t_barrio WHERE fid = 0 ) ) ) a LEFT JOIN (SELECT bis.id2, COUNT(s.id) AS jcyhsum FROM yhpc_checkhiddeninfo s LEFT JOIN bis_enterprise bis ON s.qyid = bis.id WHERE s.dangerorigin = '1' AND bis.s3 = 0 AND convert(varchar(7), s.createtime, 120) = convert(varchar(7), GETDATE(), 120) GROUP BY bis.id2 ) b ON b.id2 LIKE a.code + '%' GROUP BY a.code, a.m1 ) a LEFT JOIN (SELECT bis.id2 AS xzqy, COUNT(s.id) AS jcyhs FROM yhpc_checkhiddeninfo s LEFT JOIN bis_enterprise bis ON s.qyid = bis.id WHERE s.dangerorigin = '1' AND bis.s3 = 0 AND s.dangerstatus != '0' AND convert(varchar(7), s.createtime, 120) = convert(varchar(7), GETDATE(), 120) GROUP BY bis.id2 ) b ON b.xzqy = a.xzqy";
		List<Map<String, Object>> list=findBySql(sql);
		return list;
	}
	
	//集团公司首页数据
	public List<Map<String, Object>> getJtData(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND a.fid = "+mapData.get("fid")+" "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND a.id = "+mapData.get("qyid")+" "; 
		}
		String sql="SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.id,a.m1,(ISNULL(b.fxds,0)+ISNULL(c.zcds,0))xjds,(ISNULL(d.xjfxds,0)+ISNULL(e.xjzcds,0))yxjds,(ISNULL(b.fxds,0)+ISNULL(c.zcds,0)-ISNULL(d.xjfxds,0)-ISNULL(e.xjzcds,0))wxjds,(ISNULL(f.fxyhs,0)+ISNULL(g.zcyhs,0))yhs,(ISNULL(h.zgfxyhs,0)+ISNULL(i.zgzcyhs,0))zgyhs "
				+ "FROM bis_enterprise a LEFT JOIN (SELECT id1 qyid,COUNT(*) fxds FROM fxgk_accidentrisk WHERE s3 = 0 GROUP BY id1) b ON b.qyid = a.id "
				+ "LEFT JOIN (SELECT id1 qyid,COUNT(*) zcds FROM yhpc_checkpoint WHERE usetype = 2 GROUP BY id1) c ON c.qyid = a.id "
				+ "LEFT JOIN (SELECT b.id1 qyid,COUNT(DISTINCT b.id) xjfxds FROM yhpc_checkresult a LEFT JOIN fxgk_accidentrisk b ON a.checkpoint_id = b.id WHERE b.s3 = 0 AND a.checkpointtype = '1' AND DateDiff(dd,a.createtime,getdate())=0 GROUP BY b.id1) d ON d.qyid = a.id "
				+ "LEFT JOIN (SELECT b.id1 qyid,COUNT(DISTINCT b.id) xjzcds FROM yhpc_checkresult a LEFT JOIN yhpc_checkpoint b ON a.checkpoint_id = b.id WHERE a.checkpointtype = '2' AND b.usetype = '2' AND DateDiff(dd,a.createtime,getdate())=0 GROUP BY b.id1) e ON e.qyid = a.id "
				+ "LEFT JOIN (SELECT b.id1 qyid,COUNT(*) fxyhs FROM yhpc_checkhiddeninfo a LEFT JOIN fxgk_accidentrisk b ON a.pointid = b.id WHERE b.s3 = 0 AND a.checkpointtype = 1 AND a.dangerorigin = '1' GROUP BY b.id1)f ON f.qyid = a.id "
				+ "LEFT JOIN (SELECT b.id1 qyid,COUNT(*) zcyhs FROM yhpc_checkhiddeninfo a LEFT JOIN yhpc_checkpoint b ON a.pointid = b.id WHERE b.usetype = '2' AND a.checkpointtype = 2 AND a.dangerorigin = '1' GROUP BY b.id1)g ON g.qyid = a.id "
				+ "LEFT JOIN (SELECT b.id1 qyid,COUNT(*) zgfxyhs FROM yhpc_checkhiddeninfo a LEFT JOIN fxgk_accidentrisk b ON a.pointid = b.id WHERE b.s3 = 0 AND a.checkpointtype = 1 AND a.dangerorigin = '1' AND a.dangerstatus = '3' GROUP BY b.id1)h ON h.qyid = a.id "
				+ "LEFT JOIN (SELECT b.id1 qyid,COUNT(*) zgzcyhs FROM yhpc_checkhiddeninfo a LEFT JOIN yhpc_checkpoint b ON a.pointid = b.id WHERE b.usetype = '2' AND a.checkpointtype = 2 AND a.dangerorigin = '1' AND a.dangerstatus = '3' GROUP BY b.id1)i ON i.qyid = a.id "
				+ "WHERE a.s3 = 0 "+content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}

	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String tjcontent2(Map<String, Object> mapData) {
		String content=" ";
		//企业id
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND t.qyid = '"+mapData.get("qyid")+"' "; 
		}
		//开始时间(安全巡查)
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			content = content + "AND t.createtime>='"+mapData.get("starttime")+"' "; 
		}else{
			content = content + "AND t.createtime>=dateadd(dd,-day(getdate())+1,getdate()) "; 
		}
		//结束时间(安全巡查)
		if(mapData.get("endtime")!=null&&mapData.get("endtime")!=""){
			content = content + "AND t.createtime<='"+mapData.get("endtime")+"' "; 
		}else{
			content = content + "AND t.createtime<=getdate() "; 
		}
		return content;
	}
	
	/**
	 * 按日统计巡检数和隐患数(安全巡查)
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getCountByDay(Map<String, Object> mapData) {
		String starttime="dateadd(dd,-day(getdate())+1,getdate())";
		String endtime="getdate()";
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			starttime="'"+mapData.get("starttime").toString()+"'";
		}
		if(mapData.get("endtime")!=null&&mapData.get("endtime")!=""){
			endtime="'"+mapData.get("endtime").toString()+"'";
		}
		String content=tjcontent2(mapData);
		String sql="select right(s.date, 5) date,isNull(a.num,0) xjcount,isNull(b.num,0) yhcount "
				+ " from (select convert(varchar(10),dateadd(day,number,"+starttime+" ),120) as [date] from "
				+ " master..spt_values where datediff(day,dateadd(day,number,"+starttime+" ), "+endtime+")>=0 "
				+ " and number>=0 and type='p' )s left join "
				+ " (select  CONVERT(varchar(100), t.createtime, 23) [date] ,count(id) num "
				+ " from  yhpc_checkresult t where 1=1 "+content+"group by CONVERT(varchar(100), t.createtime, 23)) a on a.date=s.date"
				+ " left join (select convert(varchar(100),t.createtime,23) [date] ,count(id) num "
				+ " from  yhpc_checkhiddeninfo t where t.dangerorigin !='3' "+content+"group by CONVERT(varchar(100), t.createtime, 23)) b"
				+ " on b.date=s.date";
		List<Map<String, Object>> list=findBySql(sql, null, Map.class);
		return list;
	}
	
	/**
	 * 按月统计巡检数和隐患数(安全巡查)
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getCountByMonth(Map<String, Object> mapData) {
		String starttime="dateadd(dd,-day(getdate())+1,getdate())";
		String endtime="getdate()";
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			starttime="'"+mapData.get("starttime").toString()+"'";
		}
		if(mapData.get("endtime")!=null&&mapData.get("endtime")!=""){
			endtime="'"+mapData.get("endtime").toString()+"'";
		}
		String content=tjcontent2(mapData);
		String sql="select s.date,isNull(a.num,0) xjcount,isNull(b.num,0) yhcount "
				+ " from (select convert(varchar(7),dateadd(mm,number,"+starttime+" ),120) as [date] from "
				+ " master..spt_values where datediff(month,dateadd(month,number,"+starttime+" ), "+endtime+")>=0 "
				+ " and number>=0 and type='p' )s left join "
				+ "(select convert(varchar(7),t.createtime,120) [date] ,count(id) num "
				+ " from  yhpc_checkresult t where 1=1 "+content+"group by convert(varchar(7),t.createtime,120)) a on a.date=s.date"
				+ " left join (select convert(varchar(7),t.createtime,120) [date] ,count(id) num "
				+ " from  yhpc_checkhiddeninfo t where t.dangerorigin !='3' "+content+"group by convert(varchar(7),t.createtime,120)) b"
				+ " on b.date=s.date";
		List<Map<String, Object>> list=findBySql(sql, null, Map.class);
		return list;
	}
	
	/**
	 * 按年统计巡检数和隐患数(安全巡查)
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getCountByYear(Map<String, Object> mapData) {
		String starttime="dateadd(dd,-day(getdate())+1,getdate())";
		String endtime="getdate()";
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			starttime="'"+mapData.get("starttime").toString()+"'";
		}
		if(mapData.get("endtime")!=null&&mapData.get("endtime")!=""){
			endtime="'"+mapData.get("endtime").toString()+"'";
		}
		String content=tjcontent2(mapData);
		String sql="select s.date date,isNull(a.num,0) xjcount,isNull(b.num,0) yhcount "
				+ " from (select year(dateadd(year,number,"+starttime+" )) as [date] from "
				+ " master..spt_values where datediff(year,dateadd(year,number,"+starttime+" ), "+endtime+")>=0 "
				+ " and number>=0 and type='p' )s left join "
				+ " (select year(t.createtime) [date] ,count(id) num "
				+ " from  yhpc_checkresult t where 1=1 "+content+"group by year(t.createtime)) a on a.date=s.date"
				+ " left join (select year(t.createtime) [date] ,count(id) num "
				+ " from  yhpc_checkhiddeninfo t where t.dangerorigin !='3' "+content+"group by year(t.createtime)) b"
				+ " on b.date=s.date";
		List<Map<String, Object>> list=findBySql(sql, null, Map.class);
		return list;
	}
	
	/**
	 * 按人员统计巡检数和隐患数(安全巡查)
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getCountByUser(Map<String, Object> mapData) {
		String content=tjcontent2(mapData);
		String sql="select a.name,a.num xjcount,isNull(b.num,0) yhcount "
				+ " from (select b.name,b.id,count(b.id) num "
				+ " from  yhpc_checkresult t left join t_user b on t.userid=b.id "
				+ " where 1=1 "+content+"group by b.name,b.id) a "
				+ " left join (select b.name,b.id,count(b.id) num "
				+ " from  yhpc_checkhiddeninfo t left join t_user b on t.userid=b.id "
				+ " where t.dangerorigin !='3' "+content+"group by b.name,b.id) b"
				+ " on a.id=b.id";
		List<Map<String, Object>> list=findBySql(sql, null, Map.class);
		return list;
	}
	
	/**
	 * 按日统计巡检数和隐患数(安全检查)
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getCountByDay2(Map<String, Object> mapData) {
		String starttime="dateadd(dd,-day(getdate())+1,getdate())";
		String endtime="getdate()";
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			starttime="'"+mapData.get("starttime").toString()+"'";
		}
		if(mapData.get("endtime")!=null&&mapData.get("endtime")!=""){
			endtime="'"+mapData.get("endtime").toString()+"'";
		}
		String content=tjcontent2(mapData);
		if (mapData.get("qyid") != null) {
			content= (" and t.qyid = "+ mapData.get("qyid").toString()+" ");//企业id
		} else if (mapData.get("fid") != null) {
			content= (" and t.qyid = "+ mapData.get("fid").toString()+" ");//集团id
		} 
		
		String sql="select right(s.date, 5) date,isNull(a.num,0) xjcount,isNull(b.num,0) yhcount "
				+ " from (select convert(varchar(10),dateadd(day,number,"+starttime+" ),120) as [date] from "
				+ " master..spt_values where datediff(day,dateadd(day,number,"+starttime+" ), "+endtime+")>=0 "
				+ " and number>=0 and type='p' )s left join "
				+ " (select  CONVERT(varchar(100), t.createtime, 23) [date] ,count(id) num "
				+ " from  yhpc_dailycheckresult t where t.s3 = 0 "+content+"group by CONVERT(varchar(100), t.createtime, 23)) a on a.date=s.date"
				+ " left join (select convert(varchar(100),t.createtime,23) [date] ,count(*) num "
				+ " from  yhpc_dailyhiddeninfo t left join yhpc_dailycheckresult a on a.id = t.id1 where a.s3 = 0 and t.s3 = 0 "+content+"group by CONVERT(varchar(100), t.createtime, 23)) b"
				+ " on b.date=s.date";
		List<Map<String, Object>> list=findBySql(sql, null, Map.class);
		return list;
	}
	
	/**
	 * 按月统计巡检数和隐患数(安全检查)
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getCountByMonth2(Map<String, Object> mapData) {
		String starttime="dateadd(dd,-day(getdate())+1,getdate())";
		String endtime="getdate()";
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			starttime="'"+mapData.get("starttime").toString()+"'";
		}
		if(mapData.get("endtime")!=null&&mapData.get("endtime")!=""){
			endtime="'"+mapData.get("endtime").toString()+"'";
		}
		String content=tjcontent2(mapData);
		if (mapData.get("qyid") != null) {
			content= (" and t.qyid = "+ mapData.get("qyid").toString()+" ");//企业id
		} else if (mapData.get("fid") != null) {
			content= (" and t.qyid = "+ mapData.get("fid").toString()+" ");//集团id
		} 
		
		String sql="select s.date,isNull(a.num,0) xjcount,isNull(b.num,0) yhcount "
				+ " from (select convert(varchar(7),dateadd(mm,number,"+starttime+" ),120) as [date] from "
				+ " master..spt_values where datediff(month,dateadd(month,number,"+starttime+" ), "+endtime+")>=0 "
				+ " and number>=0 and type='p' )s left join "
				+ "(select convert(varchar(7),t.createtime,120) [date] ,count(id) num "
				+ " from  yhpc_dailycheckresult t where t.s3 = 0 "+content+"group by convert(varchar(7),t.createtime,120)) a on a.date=s.date"
				+ " left join (select convert(varchar(7),t.createtime,120) [date] ,count(*) num "
				+ " from  yhpc_dailyhiddeninfo t left join yhpc_dailycheckresult a on a.id = t.id1 where a.s3 = 0 and t.s3 = 0 "+content+"group by convert(varchar(7),t.createtime,120)) b"
				+ " on b.date=s.date";
		List<Map<String, Object>> list=findBySql(sql, null, Map.class);
		return list;
	}
	
	/**
	 * 按年统计巡检数和隐患数(安全检查)
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getCountByYear2(Map<String, Object> mapData) {
		String starttime="dateadd(dd,-day(getdate())+1,getdate())";
		String endtime="getdate()";
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			starttime="'"+mapData.get("starttime").toString()+"'";
		}
		if(mapData.get("endtime")!=null&&mapData.get("endtime")!=""){
			endtime="'"+mapData.get("endtime").toString()+"'";
		}
		String content=tjcontent2(mapData);
		if (mapData.get("qyid") != null) {
			content= (" and t.qyid = "+ mapData.get("qyid").toString()+" ");//企业id
		} else if (mapData.get("fid") != null) {
			content= (" and t.qyid = "+ mapData.get("fid").toString()+" ");//集团id
		} 
		
		String sql="select s.date date,isNull(a.num,0) xjcount,isNull(b.num,0) yhcount "
				+ " from (select year(dateadd(year,number,"+starttime+" )) as [date] from "
				+ " master..spt_values where datediff(year,dateadd(year,number,"+starttime+" ), "+endtime+")>=0 "
				+ " and number>=0 and type='p' )s left join "
				+ " (select year(t.createtime) [date] ,count(id) num "
				+ " from  yhpc_dailycheckresult t where t.s3 = 0 "+content+"group by year(t.createtime)) a on a.date=s.date"
				+ " left join (select year(t.createtime) [date] ,count(*) num "
				+ " from  yhpc_dailyhiddeninfo t left join yhpc_dailycheckresult a on a.id = t.id1 where a.s3 = 0 and t.s3 = 0 "+content+"group by year(t.createtime)) b"
				+ " on b.date=s.date";
		List<Map<String, Object>> list=findBySql(sql, null, Map.class);
		return list;
	}
	
	/**
	 * 按人员统计巡检数和隐患数(安全检查)
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getCountByUser2(Map<String, Object> mapData) {
		String content=tjcontent2(mapData);
		if (mapData.get("qyid") != null) {
			content += (" and t.qyid = "+ mapData.get("qyid").toString()+" ");//企业id
		} else if (mapData.get("fid") != null) {
			content += (" and t.qyid = "+ mapData.get("fid").toString()+" ");//集团id
		} 
		
		String sql="select a.name,a.num xjcount,isNull(b.num,0) yhcount "
				+ " from (select b.name,b.id,count(b.id) num "
				+ " from  yhpc_dailycheckresult t left join t_user b on t.m7=b.id "
				+ " where t.s3 = 0 "+content+"group by b.name,b.id) a "
				+ " left join (select b.name,b.id,count(b.id) num "
				+ " from  yhpc_dailyhiddeninfo t left join yhpc_dailycheckresult a on a.id = t.id1 left join t_user b on t.m12=b.id "
				+ " where a.s3 = 0 and t.s3 = 0 "+content+"group by b.name,b.id) b"
				+ " on a.id=b.id";
		List<Map<String, Object>> list=findBySql(sql, null, Map.class);
		return list;
	}

	/**
	 * 按日统计巡检数和隐患数(随手拍)
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getCountByDay3(Map<String, Object> mapData) {
		String starttime="dateadd(dd,-day(getdate())+1,getdate())";
		String endtime="getdate()";
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			starttime="'"+mapData.get("starttime").toString()+"'";
		}
		if(mapData.get("endtime")!=null&&mapData.get("endtime")!=""){
			endtime="'"+mapData.get("endtime").toString()+"'";
		}
		String content=tjcontent2(mapData);
		String sql="select right(s.date, 5) date,isNull(b.num,0) yhcount "
				+ " from (select convert(varchar(10),dateadd(day,number,"+starttime+" ),120) as [date] from "
				+ " master..spt_values where datediff(day,dateadd(day,number,"+starttime+" ), "+endtime+")>=0 "
				+ " and number>=0 and type='p' )s  left join (select convert(varchar(100),t.createtime,23) [date] ,count(id) num "
				+ " from  yhpc_checkhiddeninfo t where t.dangerorigin='3' "+content+"group by CONVERT(varchar(100), t.createtime, 23)) b"
				+ " on b.date=s.date";
		List<Map<String, Object>> list=findBySql(sql, null, Map.class);
		return list;
	}
	
	/**
	 * 按月统计巡检数和隐患数(随手拍)
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getCountByMonth3(Map<String, Object> mapData) {
		String starttime="dateadd(dd,-day(getdate())+1,getdate())";
		String endtime="getdate()";
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			starttime="'"+mapData.get("starttime").toString()+"'";
		}
		if(mapData.get("endtime")!=null&&mapData.get("endtime")!=""){
			endtime="'"+mapData.get("endtime").toString()+"'";
		}
		String content=tjcontent2(mapData);
		String sql="select s.date,isNull(b.num,0) yhcount "
				+ " from (select convert(varchar(7),dateadd(mm,number,"+starttime+" ),120) as [date] from "
				+ " master..spt_values where datediff(month,dateadd(month,number,"+starttime+" ), "+endtime+")>=0 "
				+ " and number>=0 and type='p' )s left join (select convert(varchar(7),t.createtime,120) [date] ,count(id) num "
				+ " from  yhpc_checkhiddeninfo t where t.dangerorigin='3' "+content+"group by convert(varchar(7),t.createtime,120)) b"
				+ " on b.date=s.date";
		List<Map<String, Object>> list=findBySql(sql, null, Map.class);
		return list;
	}
	
	/**
	 * 按年统计巡检数和隐患数(随手拍)
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getCountByYear3(Map<String, Object> mapData) {
		String starttime="dateadd(dd,-day(getdate())+1,getdate())";
		String endtime="getdate()";
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			starttime="'"+mapData.get("starttime").toString()+"'";
		}
		if(mapData.get("endtime")!=null&&mapData.get("endtime")!=""){
			endtime="'"+mapData.get("endtime").toString()+"'";
		}
		String content=tjcontent2(mapData);
		String sql="select s.date date,isNull(b.num,0) yhcount "
				+ " from (select year(dateadd(year,number,"+starttime+" )) as [date] from "
				+ " master..spt_values where datediff(year,dateadd(year,number,"+starttime+" ), "+endtime+")>=0 "
				+ " and number>=0 and type='p' )s left join (select year(t.createtime) [date] ,count(id) num "
				+ " from  yhpc_checkhiddeninfo t where t.dangerorigin='3' "+content+"group by year(t.createtime)) b"
				+ " on b.date=s.date";
		List<Map<String, Object>> list=findBySql(sql, null, Map.class);
		return list;
	}
	
	/**
	 * 按人员统计巡检数和隐患数(随手拍)
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getCountByUser3(Map<String, Object> mapData) {
		String content=tjcontent2(mapData);
		String sql="select b.name,b.id,count(b.id) yhcount "
				+ " from  yhpc_checkhiddeninfo t left join t_user b on t.userid=b.id "
				+ " where t.dangerorigin='3' "+content+"group by b.name,b.id ";
		List<Map<String, Object>> list=findBySql(sql, null, Map.class);
		return list;
	}
	
	/**
	 * 统计重要危险源整改情况
	 * @param mapData
	 * @return
	 * @throws ParseException 
	 */
	public List<Map<String, Object>> getDailyCheckCount(Map<String, Object> mapData) throws ParseException {
		String content = "";
		if (mapData.get("qyid") != null) {
			content= (" qyid = "+ mapData.get("qyid").toString());//企业id
		} else if (mapData.get("fid") != null) {
			content= (" qyid = "+ mapData.get("fid").toString());//集团id
		} 
		
		String starttime=" (DATENAME(yy, m1) + '-' + DATENAME(MM, m1)) >= (DATENAME(yy, getdate()) + '-' + DATENAME(MM, getdate())) ";//当前时间的年和月
		String endtime="(DATENAME(yy, m1) + '-' + DATENAME(MM, m1)) <= (DATENAME(yy, getdate()) + '-' + DATENAME(MM, getdate())) ";//当前时间的年和月
		String lasttime = "(DATENAME(yy, m1) + '-' + DATENAME(MM, m1)) = (convert(varchar(7),DATEADD(month,-1,GETDATE()),120)) ";//上个月的年和月
		String wholetime = "CONVERT(varchar(10), GETDATE(), 120)";//当前时间的年月日
		
		
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			starttime=" CONVERT(VARCHAR(10),m1,120) >= '"+mapData.get("starttime").toString()+"' ";
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(mapData.get("starttime").toString()));
			calendar.add(Calendar.MONTH, -1);
			calendar.set(Calendar.DAY_OF_MONTH, 1); // 上个月的第一天
			String last_mon_first = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
			
			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(mapData.get("starttime").toString()));
			calendar1.set(Calendar.DAY_OF_MONTH, 1); 
			calendar1.add(Calendar.DATE, -1); // 上个月的最后一天
			String last_mon_final = new SimpleDateFormat("yyyy-MM-dd").format(calendar1.getTime());
			lasttime=" CONVERT(VARCHAR(10),m1,120) >= '"+last_mon_first+"' and  CONVERT(VARCHAR(10),m1,120) <= '"+last_mon_final+"'";
		}
		if(mapData.get("endtime")!=null&&mapData.get("endtime")!=""){
			endtime=" CONVERT(VARCHAR(10),m1,120) <= '"+mapData.get("endtime").toString()+"'";
			
			if (mapData.get("starttime")==null||mapData.get("starttime")=="") {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(mapData.get("endtime").toString()));
				calendar.add(Calendar.MONTH, -1);
				calendar.set(Calendar.DAY_OF_MONTH, 1); // 上个月的第一天
				String last_mon_first = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
				
				Calendar calendar1 = Calendar.getInstance();
				calendar1.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(mapData.get("endtime").toString()));
				calendar1.set(Calendar.DAY_OF_MONTH, 1); 
				calendar1.add(Calendar.DATE, -1); // 上个月的最后一天
				String last_mon_final = new SimpleDateFormat("yyyy-MM-dd").format(calendar1.getTime());
				
				lasttime=" CONVERT(VARCHAR(10),m1,120) >= '"+last_mon_first+"' and  CONVERT(VARCHAR(10),m1,120) <= '"+last_mon_final+"'";
			}
		}
		
		String sql="SELECT a.m2 deptname,ISNULL(b.current_mon_count, 0) current_mon_count,ISNULL(c.last_mon_count, 0) last_mon_count,ISNULL(d.soluted_count, 0) soluted_count,"
				  +" ISNULL(e.no_solut_count, 0) no_solut_count, ISNULL(f.no_expire_count , 0) no_expire_count  "
				  +" FROM(SELECT m2 FROM yhpc_dailyhiddencheck where "
				  + content
				  + " and s3 = 0 GROUP BY m2) a LEFT JOIN ( "
				  +" SELECT m2,COUNT(1) current_mon_count FROM yhpc_dailyhiddencheck WHERE"
				  + content
				  +" and  " + starttime +"and " + endtime
				  +" GROUP BY m2 ) b on a.m2 = b.m2 LEFT JOIN ("
				  +" SELECT m2,COUNT(1) as last_mon_count FROM yhpc_dailyhiddencheck WHERE"
				  + content
				  +" and  "+lasttime+" and S3 = 0 and m13 <> '2' GROUP BY m2 ) c on a.m2 = c.m2 LEFT JOIN ("
				  +" SELECT m2,count(1)as soluted_count FROM yhpc_dailyhiddencheck WHERE "
				  + content
				  +" and m13 = '2' and S3 = 0 and  " + starttime +"and " + endtime
				  +" GROUP BY m2) d on a.m2 = d.m2 LEFT JOIN ("
				  +" SELECT m2,count(1)as no_solut_count FROM yhpc_dailyhiddencheck WHERE"
				  + content
				  +" and m13 <> '2' and S3 = 0 and  " + starttime +"and " + endtime
				  +" GROUP BY m2) e on a.m2 = e.m2 LEFT JOIN ("
				  +" SELECT m2,count(1)as no_expire_count FROM yhpc_dailyhiddencheck WHERE"
				  + content
				  +"and m13 = '0' and S3 = 0 and CONVERT(varchar(10), m10, 120) > "+wholetime+" and  " + starttime +"and " + endtime
				  +"GROUP BY m2) f on a.m2 = f.m2";

		List<Map<String, Object>> list=findBySql(sql, null, Map.class);
		return list;
	}
	
	
	/**
	 * 按安全隐患类别查询出对应的整改数量
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getCountByYhkind(Map<String, Object> mapData) {
		String content = "";
		if (mapData.get("qyid") != null) {
			content= (" qyid = "+ mapData.get("qyid").toString());//企业id
		} else if (mapData.get("fid") != null) {
			content= (" qyid = "+ mapData.get("fid").toString());//集团id
		} 
		
		String starttime=" (CONVERT(varchar(7),m1,120)) >= (CONVERT(varchar(7),GETDATE(),120)) ";//当前时间的年和月
		String endtime="(CONVERT(varchar(7),m1,120)) <= (CONVERT(varchar(7),GETDATE(),120)) ";//当前时间的年和月
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			starttime=" CONVERT(VARCHAR(10),m1,120) >= '"+mapData.get("starttime").toString()+"' ";
		}
		if(mapData.get("endtime")!=null&&mapData.get("endtime")!=""){
			endtime=" CONVERT(VARCHAR(10),m1,120) <= '"+mapData.get("endtime").toString()+"'";
		}
		String sql="SELECT m6 name, COUNT(1) AS yhcount "
				+ " FROM  yhpc_dailyhiddencheck "
				+ " WHERE  "+content+" and S3 = 0 and "+starttime+" and "+endtime+"AND m6 IS NOT NULL  and m6 <> '' "
				+ " GROUP BY m6";
		List<Map<String, Object>> list=findBySql(sql, null, Map.class);
		
		return list;
	}
	
	/**
	 * 风险类别统计图
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getCountByFxlb(Map<String, Object> mapData) {
		String content = "";
		if (mapData.get("qyid") != null) {
			content += (" id1 = "+ mapData.get("qyid").toString());//企业id
		} else if (mapData.get("fid") != null) {
			content += (" id1 = "+ mapData.get("fid").toString());//集团id
		} 
		if (mapData.get("m2") != null && mapData.get("m2") !="") {
			content += (" and m2 = '"+ mapData.get("m2").toString())+"'";//集团id
		}	
		
		String sql = "SELECT a.m2 name,ISNULL(b.red_count, 0) red_count,ISNULL(c.orange_count, 0) orange_count,ISNULL(d.yellow_count, 0) yellow_count,ISNULL(e.blue_count, 0) blue_count,ISNULL(g.m2_sum_count, 0) m2_sum_count "
				   + "FROM( "
				   + " SELECT m2 FROM fxgk_accidentrisk WHERE s3=0 and "+content+" GROUP BY m2) a "
			   	   + "LEFT JOIN  "
			   	   + " (SELECT m2,COUNT(1) red_count FROM fxgk_accidentrisk WHERE s3=0 AND "+content+" AND m9='1' GROUP BY m2,m9 ) b on a.m2=b.m2 "
		   	   	   + "LEFT JOIN "
		   	   	   + " (SELECT m2,COUNT(1) orange_count FROM fxgk_accidentrisk WHERE s3=0 AND "+content+" AND m9='2' GROUP BY m2,m9 ) c on a.m2 = c.m2 "
	   	   	   	   + "LEFT JOIN "
	   	   	   	   + " (SELECT m2,COUNT(1) yellow_count FROM fxgk_accidentrisk WHERE s3=0 AND "+content+" AND m9='3' GROUP BY m2,m9 ) d on a.m2 = d.m2 "
	   	   	   	   + "LEFT JOIN "
	   	   	   	   + " (SELECT m2,COUNT(1) blue_count FROM fxgk_accidentrisk WHERE s3=0 AND "+content+" AND m9='4' GROUP BY m2,m9 ) e on a.m2 = e.m2 "
   	   	   	   	   + "LEFT JOIN "
   	   	   	   	   + " (SELECT m2,COUNT(1) m2_sum_count FROM fxgk_accidentrisk WHERE s3 = 0 and "+content+" GROUP BY m2 ) g ON a.m2 = g.m2 ";
		
		List<Map<String, Object>> list=findBySql(sql, null, Map.class);
		
		return list;
	}

}
