package com.cczu.model.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_CheckResultEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 巡检记录dao层
 * zpc
 */
@Repository("YhpcXjjlDao")
public class YhpcXjjlDao extends BaseDao<YHPC_CheckResultEntity, Long>{

	/**
	 * 企业巡检记录list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont="order by a.createtime DESC";
		if("checkresult".equals(mapData.get("orderBy")))
			ordercont="ORDER BY a.checkresult "+mapData.get("order");
		else if("createtime".equals(mapData.get("orderBy")))
			ordercont="ORDER BY a.createtime "+mapData.get("order");
		else if("jcdname".equals(mapData.get("orderBy")))
			ordercont="ORDER BY d.jcdname "+mapData.get("order");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.id,e.m1 qyname,d.jcdname,d.type,d.jcdid,b.name,a.createtime,c.NAME uname,a.checkresult,a.note,a.checkphoto "
				+ "FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN t_user c ON c.ID = a.userid LEFT JOIN "
				+ "(SELECT a.id,a.checkpoint_id,b.id jcdid,b.m1 jcdname,'fxd' type,b.id1 qyid,b.m23 iszdwxy FROM yhpc_checkresult a LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' AND b.s3 = 0 "
				+ "UNION SELECT a.id,a.checkpoint_id,b.id jcdid,b.name jcdname,'yhd' type,b.id1 qyid,b.iszdwxy FROM yhpc_checkresult a LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' and b.usetype = '2') d ON d.id = a.id "
				+ "LEFT JOIN bis_enterprise e ON e.id = d.qyid WHERE e.s3 = 0 " + content + " ) "
				+ "as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)" ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 查询企业巡检记录list的个数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN t_user c ON c.ID = a.userid LEFT JOIN "
				+ "(SELECT a.id,b.m1 jcdname,b.id1 qyid,b.m23 iszdwxy FROM yhpc_checkresult a LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' AND b.s3 = 0 "
				+ "UNION SELECT a.id,b.name jcdname,b.id1 qyid,b.iszdwxy FROM yhpc_checkresult a LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2'  and b.usetype = '2') d ON d.id = a.id "
				+ "LEFT JOIN bis_enterprise e ON e.id = d.qyid WHERE e.s3 = 0 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
     * 企业查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("iszdwxy")!=null&&mapData.get("iszdwxy")!=""&&mapData.get("iszdwxy").toString().equals("1")){
			content = content + "AND d.iszdwxy = '1' "; 
		}
		if(mapData.get("jcdname")!=null&&mapData.get("jcdname")!=""){
			content = content + "AND d.jcdname like '%"+mapData.get("jcdname")+"%' "; 
		}
		if(mapData.get("checkplan")!=null&&mapData.get("checkplan")!=""){
			content = content + "AND a.checkplan_id = "+mapData.get("checkplan")+" "; 
		}
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			content = content + "AND a.createtime >= '"+mapData.get("starttime")+"' "; 
		}
		if(mapData.get("finishtime")!=null&&mapData.get("finishtime")!=""){
			content = content + "AND a.createtime <= '"+mapData.get("finishtime")+"' "; 
		}
		if(mapData.get("checkresult")!=null&&mapData.get("checkresult")!=""){
			content = content + "AND a.checkresult = '"+mapData.get("checkresult")+"' "; 
		}
		if(mapData.get("jcdid")!=null&&mapData.get("jcdid")!=""){
			content = content + "AND a.checkpoint_id = '"+mapData.get("jcdid")+"' "; 
		}
		if(mapData.get("jcdtype")!=null&&mapData.get("jcdtype")!=""){
			content = content + "AND a.checkpointtype = '"+mapData.get("jcdtype")+"' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND d.qyid = "+mapData.get("qyid")+" "; 
		}
        if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
            content = content +" AND e.m1 LIKE'%"+mapData.get("qyname")+"%' ";
        }
        if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
            content = content +" AND e.id2 like'"+mapData.get("xzqy")+"%' ";
        }
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND e.m36='"+mapData.get("jglx")+"' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( e.fid='"+mapData.get("fid")+"' or e.id='"+mapData.get("fid")+"') "; 
		}
		
		/*安全台账条件*/
		if(mapData.get("aqtzstarttime")!=null&&mapData.get("aqtzstarttime")!=""){
			content = content +" AND CONVERT(varchar(100), a.createtime, 23) >='"+mapData.get("aqtzstarttime")+"' "; 
		}
		if(mapData.get("aqtzfinishtime")!=null&&mapData.get("aqtzfinishtime")!=""){
			content = content +" AND CONVERT(varchar(100), a.createtime, 23) <='"+mapData.get("aqtzfinishtime")+"' "; 
		}
		return content;
	}

	/**
	 * 安监巡检记录list
	 * @param mapData
	 * @return
	 * 如果要查看最新记录把最后where 1=1 改为 h.rn<=1
	 */
	public List<Map<String, Object>> ajdataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","order by createtime DESC");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,h.* FROM "
				+ "(SELECT ROW_NUMBER() OVER (partition by e.m1,d.jcdname ORDER BY a.createtime DESC) AS rn, a.id,e.m1 qyname,d.jcdname,d.type,d.jcdid,b.name,a.createtime,c.NAME uname,a.checkresult,a.note,a.checkphoto,a.checkpointtype,e.id2 xzqy,e.m36,d.checkpoint_id "
				+ "FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN t_user c ON c.ID = a.userid LEFT JOIN (SELECT a.id,a.checkpoint_id,b.id jcdid,b.m1 jcdname,'fxd' type,b.id1 qyid,b.m23 iszdwxy FROM yhpc_checkresult a "
				+ "LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' AND b.s3 = 0 UNION SELECT a.id,a.checkpoint_id,b.id jcdid,b.name jcdname,'yhd' type,b.id1 qyid,b.iszdwxy FROM yhpc_checkresult a "
				+ "LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' and b.usetype = '2') d ON d.id = a.id LEFT JOIN bis_enterprise e ON e.id = d.qyid "
				+ "WHERE e.s3 = 0 "+content+") h   ) z where RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 根据id查找巡检记录
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> findInforById(Long id) {
		String sql =" SELECT a.id,e.m1 qyname,d.jcdname,d.type,d.jcdid,b.name,a.createtime,c.NAME uname,(case a.checkresult when '0' then '无隐患' when '1' then '有隐患' end) checkresult,a.note,a.checkphoto,a.checkpoint_id,a.checkpointtype,e.id2 xzqy,e.m36 "
				+ "FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN t_user c ON c.ID = a.userid LEFT JOIN (SELECT a.id, b.id jcdid,b.m1 jcdname,'fxd' type,b.id1 qyid FROM yhpc_checkresult a "
				+ "LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' AND b.s3 = 0 UNION SELECT a.id, b.id jcdid,b.name jcdname,'yhd' type,b.id1 qyid FROM yhpc_checkresult a "
				+ "LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' and b.usetype = '2') d ON d.id = a.id LEFT JOIN bis_enterprise e ON e.id = d.qyid "
				+ "WHERE e.s3 = 0 and a.id="+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list.get(0);
	}
	
	/**
	 * 查询安监巡检记录list的个数
	 * @param mapData
	 * @return
	 */
	public int getajTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM (select * from (SELECT ROW_NUMBER() OVER (partition by e.m1,d.jcdname ORDER BY a.createtime DESC) AS rn, a.id,e.m1 qyname,e.fid,d.jcdname,b.name,a.createtime,c.NAME uname,a.checkresult,a.note,a.checkphoto,a.checkpoint_id,a.checkpointtype,e.id2 xzqy,e.m36 "
				+ "FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN t_user c ON c.ID = a.userid LEFT JOIN (SELECT a.id,a.checkpoint_id,b.m1 jcdname,b.id1 qyid,b.m23 iszdwxy FROM yhpc_checkresult a "
				+ "LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' AND b.s3 = 0 UNION SELECT a.id,a.checkpoint_id,b.name jcdname,b.id1 qyid,b.iszdwxy FROM yhpc_checkresult a "
				+ "LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' and b.usetype = '2') d ON d.id = a.id LEFT JOIN bis_enterprise e ON e.id = d.qyid "
				+ "WHERE e.s3 = 0 "+content+") h ) z ";
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
	 * 删除巡检记录信息
	 */
	public void deleteInfor(Long id){
		String sql="delete from yhpc_checkresult where id="+id;
		updateBySql(sql);
	}
	
	/**
	 * 安监巡检记录list app
	 */
	public List<Map<String, Object>> ajdataGridforapp(Map<String, Object> mapData) {
		String content=content2(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER (order by createtime DESC) AS RowNumber,h.* FROM "
				+ "(SELECT ROW_NUMBER() OVER (partition by e.m1,d.jcdname ORDER BY a.createtime DESC) AS rn, a.id,e.m1 qyname,d.jcdname,d.type,d.jcdid,b.name,a.createtime,c.NAME uname,a.checkresult,a.note,a.checkphoto,a.checkpointtype,e.id2 xzqy,e.m36,d.checkpoint_id "
				+ "FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN t_user c ON c.ID = a.userid LEFT JOIN (SELECT a.id,a.checkpoint_id,b.id jcdid,b.m1 jcdname,'fxd' type,b.id1 qyid FROM yhpc_checkresult a "
				+ "LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' AND b.s3 = 0 UNION SELECT a.id,a.checkpoint_id,b.id jcdid,b.name jcdname,'yhd' type,b.id1 qyid FROM yhpc_checkresult a "
				+ "LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' and b.usetype = '2') d ON d.id = a.id LEFT JOIN bis_enterprise e ON e.id = d.qyid "
				+ "WHERE e.s3 = 0 ) h WHERE 1=1 "+content+" ) z where RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	/**
     * 安监查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content2(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("iszdwxy")!=null&&mapData.get("iszdwxy")!=""&&mapData.get("iszdwxy").toString().equals("1")){
			content = content + "AND d.iszdwxy = '1' "; 
		}
		if(mapData.get("jcdname")!=null&&mapData.get("jcdname")!=""){
			content = content + "AND h.jcdname like '%"+mapData.get("jcdname")+"%' "; 
		}
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			content = content + "AND h.createtime >= '"+mapData.get("starttime")+"' "; 
		}
		if(mapData.get("finishtime")!=null&&mapData.get("finishtime")!=""){
			content = content + "AND h.createtime <= '"+mapData.get("finishtime")+"' "; 
		}
		if(mapData.get("checkresult")!=null&&mapData.get("checkresult")!=""){
			content = content + "AND h.checkresult = '"+mapData.get("checkresult")+"' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND h.xzqy like '"+mapData.get("xzqy")+"%' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + "AND h.qyname like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("jcdid")!=null&&mapData.get("jcdid")!=""){
			content = content + "AND h.checkpoint_id = "+mapData.get("jcdid")+" "; 
		}
		if(mapData.get("jcdtype")!=null&&mapData.get("jcdtype")!=""){
			content = content + "AND h.checkpointtype = "+mapData.get("jcdtype")+" "; 
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND h.m36='"+mapData.get("jglx")+"' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( h.fid='"+mapData.get("fid")+"' or h.id='"+mapData.get("fid")+"') "; 
		}
		return content;
	}
	
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT h.*,(CASE h.checkresult when 0 then '无隐患' when 1 then '有隐患' ELSE '' END) lx FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.id,e.m1 qyname,d.jcdname,(case when b.name is null then '该班次已被删除' else b.name end) name,CONVERT(varchar(100), a.createtime, 120)createtime,c.NAME uname,a.checkresult,a.note,a.checkphoto "
				+ "FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN t_user c ON c.ID = a.userid LEFT JOIN "
				+ "(SELECT a.id,b.m1 jcdname,b.id1 qyid,b.m23 iszdwxy FROM yhpc_checkresult a LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' AND b.s3 = 0 "
				+ "UNION SELECT a.id,b.name jcdname,b.id1 qyid,b.iszdwxy FROM yhpc_checkresult a LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' and b.usetype = '2') d ON d.id = a.id "
				+ "LEFT JOIN bis_enterprise e ON e.id = d.qyid WHERE e.s3 = 0 " + content + " ) "
				+ "as h WHERE 1=1 order by createtime desc";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
	public List<Map<String, Object>> getExport2(Map<String, Object> mapData) {
		String content=content2(mapData);
		String sql=" SELECT z.*,(CASE z.checkresult when 0 then '无隐患' when 1 then '有隐患' ELSE '' END) lx FROM (SELECT h.* FROM "
				+ "(SELECT ROW_NUMBER() OVER (partition by e.m1,d.jcdname ORDER BY a.createtime DESC) AS rn, a.id,e.m1 qyname,d.jcdname,(case when b.name is null then '该班次已被删除' else b.name end) name,a.createtime,c.NAME uname,a.checkresult,a.note,a.checkphoto,e.id2 xzqy,e.m36,d.checkpoint_id "
				+ "FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN t_user c ON c.ID = a.userid LEFT JOIN (SELECT a.id,b.m1 jcdname,b.id1 qyid,b.m23 iszdwxy FROM yhpc_checkresult a "
				+ "LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' AND b.s3 = 0 UNION SELECT a.id,b.name jcdname,b.id1 qyid,b.iszdwxy FROM yhpc_checkresult a "
				+ "LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' and b.usetype = '2') d ON d.id = a.id LEFT JOIN bis_enterprise e ON e.id = d.qyid "
				+ "WHERE e.s3 = 0 ) h WHERE 1=1 "+content+") z ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
	public List<Map<String, Object>> getqylistForApp(Map<String, Object> mapData) {
		String content=content3(mapData);
		String sql="SELECT TOP "+mapData.get("pageSize")+" * FROM ("+
				"select ROW_NUMBER() OVER (ORDER BY m1) AS RowNumber,id,m1,m11,m11_1,m11_2,m11_3,m5,m6,(case M36 when '1' then '工贸' when '2' then '化工' end) as m36,(case when (M39= '1') then '是' else '否'end) m39,m19,m44 from bis_enterprise bis  right join (SELECT DISTINCT(a.qyid) "
				+ "FROM (SELECT a.id,b.m1 jcdname,b.id1 qyid FROM yhpc_checkresult a LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' AND b.s3 = 0 "
				+ "UNION SELECT a.id,b.name jcdname,b.id1 qyid FROM yhpc_checkresult a LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' and b.usetype = '2') a) s on bis.id=s.qyid WHERE bis.s3=0 "+content
				+" )  AS s WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	/**
     * app查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content3(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + " AND bis.id2 like '"+mapData.get("xzqy")+"%' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + " AND bis.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + " AND bis.m36='"+mapData.get("jglx")+"' "; 
		}
		return content;
	}
	
	/**
	 * 根据网格id获取该巡查点最新的巡检记录 app
	 */
	public Map<String, Object> getnewXjjlForApp(Long jcdid,String type) {
		String sql ="SELECT top 1 a.id,e.m1 qyname,d.jcdname,d.type,d.jcdid,b.name,a.createtime,c.NAME uname,a.checkresult,a.note,a.checkphoto "
				+ "FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN t_user c ON c.ID = a.userid LEFT JOIN "
				+ "(SELECT a.id,a.checkpoint_id,b.id jcdid,b.m1 jcdname,'fxd' type,b.id1 qyid FROM yhpc_checkresult a LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' AND b.s3 = 0 "
				+ "UNION SELECT a.id,a.checkpoint_id,b.id jcdid,b.name jcdname,'yhd' type,b.id1 qyid FROM yhpc_checkresult a LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' and b.usetype = '2') d ON d.id = a.id "
				+ "LEFT JOIN bis_enterprise e ON e.id = d.qyid WHERE e.s3 = 0 and d.jcdid = "+jcdid+" and d.type = '"+type+"' order by a.createtime desc";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 查询条件判断
	 * @return
	 * @throws ParseException
	 */
	public String content4(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + " AND a.qyid = "+mapData.get("qyid")+" ";
		}
		if(mapData.get("fxdcolor")!=null&&mapData.get("fxdcolor")!=""){
			content = content + " AND b.m9 = '"+mapData.get("fxdcolor")+"' ";
		}
		if(mapData.get("checkpointid")!=null&&mapData.get("checkpointid")!=""){
			content = content + " AND a.checkpoint_id = '"+mapData.get("checkpointid")+"' ";
		}
		return content;
	}

	/**
	 * 根据风险点颜色获取巡查信息
	 */
	public List<Map<String, Object>> getXcinfoByFxdColor(Map<String, Object> map) {
		String content = content4(map);
		String sql ="SELECT CONVERT(varchar(10),a.createtime,120) xcsj, b.m1 dwmc, c.NAME xcry FROM yhpc_checkresult a  "
				+ "LEFT JOIN fxgk_accidentrisk b ON a.checkpoint_id = b.id "
				+ "LEFT JOIN t_user c ON a.userid = c.id "
				+ "WHERE a.createtime in ( "
				+ "SELECT MAX(a.createtime) FROM yhpc_checkresult a LEFT JOIN fxgk_accidentrisk b ON a.checkpoint_id = b.id "
				+ "WHERE a.checkpointtype = '1' and b.s3 = 0 " + content + " GROUP BY a.checkpoint_id )";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

}
