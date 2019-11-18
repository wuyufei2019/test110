package com.cczu.model.dao.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IAqzfJcjhDao;
import com.cczu.model.entity.AQZF_SafetyCheckPlanEntity;
import com.cczu.util.dao.BaseDao;

@Repository("AqzfJcjhDao")
public class AqzfJcjhDaoImpl extends BaseDao<AQZF_SafetyCheckPlanEntity, Long> implements IAqzfJcjhDao {

	@Override
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = "SELECT TOP " + mapData.get("pageSize") + "a.* FROM ("+
		           "SELECT ROW_NUMBER() OVER(order by  b.m1 DESC,convert(int,replace(b.m2,'月','')) DESC) rownum,a.id,(CAST(b.m1 as varchar(10))+'年'+CAST(b.m2 as varchar(10)))m1,c.m1 qyname,e.m1 m3,b.m4,b.m5,a.m1 panduan "+
                   " FROM aqzf_plan_enterprise a LEFT JOIN aqzf_safetycheckplan b ON b.id = a.id1 "
                   + " LEFT JOIN bis_enterprise c ON c.id = a.id2 "
                   + " left join t_user d on d.id=b.id1 left join t_barrio e on e.code = b.m3 WHERE b.s3 = 0 and c.s3 = 0 "+ content+ ") a "+
				   "WHERE rownum >" + mapData.get("pageSize") + "*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM aqzf_plan_enterprise a"
				+ "  left join aqzf_safetycheckplan b on a.id1=b.id"
				+ "  LEFT JOIN bis_enterprise c ON c.id = a.id2"
				+ "  left join t_user d on d.id=b.id1 WHERE b.s3 = 0 and c.s3 = 0 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	@Override
	public int getfasl(long id1){
		String sql="SELECT COUNT(*) FROM aqzf_safetycheckscheme WHERE s3=0 and id1 = "+id1;
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
		if(mapData.get("year")!=null&&mapData.get("year")!=""){
			content = content + "AND b.m1 like'%"+mapData.get("year")+"%' "; 
		}
		if(mapData.get("month")!=null&&mapData.get("month")!=""){
			content = content + "AND b.m2 like'%"+mapData.get("month")+"%' "; 
		}
		if(mapData.get("M3")!=null&&mapData.get("M3")!=""){
			content = content + "AND b.m3 like'%"+mapData.get("M3")+"%' "; 
		}
		if(mapData.get("M4")!=null&&mapData.get("M4")!=""){
			content = content + "AND b.m4 like'%"+mapData.get("M4")+"%' "; 
		}
		if(mapData.get("M5")!=null&&mapData.get("M5")!=""){
			content = content + "AND b.m5 like'%"+mapData.get("M5")+"%' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND d.xzqy = '"+mapData.get("xzqy")+"' "; 
		}
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND d.userroleflg='"+mapData.get("jglx")+"' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + "AND c.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		return content;
	}

	@Override
	public void addInfo(AQZF_SafetyCheckPlanEntity jcjh) {
		save(jcjh);
	}

	@Override
	public void deleteInfo(long id) {
		String sql=" UPDATE aqzf_safetycheckplan SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT (CAST(b.m1 as varchar(10))+'年'+CAST(b.m2 as varchar(10)))m1,c.m1 qyname,e.m1 m3,(case b.m4 when '1' then '工贸' when '2' then '化工' else b.m4 end) m4,b.m5 FROM aqzf_plan_enterprise a "
				+ " LEFT JOIN aqzf_safetycheckplan b ON b.id = a.id1 "
				+ " LEFT JOIN bis_enterprise c ON c.id = a.id2 "
				+ " left join t_user d on d.id=b.id1 left join t_barrio e on e.code = b.m3 WHERE b.s3 = 0 and c.s3 = 0 "+content+" ORDER BY b.m1 DESC,convert(int,replace(b.m2,'月','')) DESC";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}

	@Override
	public long addjcjh(AQZF_SafetyCheckPlanEntity jcjh) {
		save(jcjh);
		return jcjh.getID();
	}

	//根据中间表id
	@Override
	public List<Map<String, Object>> findInfoById(long id) {	
		String sql="SELECT b.m1,b.m2,b.m3,b.m4,b.m5,c.m1 qyname,d.m1 sd,(case b.m4 when '1' then '工贸' when '2' then '化工' else b.m4 end) hylx FROM aqzf_plan_enterprise a LEFT JOIN aqzf_safetycheckplan b ON b.id = a.id1 "
				+ "LEFT JOIN bis_enterprise c ON c.id = a.id2 left join t_barrio d on d.code = b.m3 WHERE c.s3=0 and b.s3=0 and a.id  = "+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}

	@Override
	public Map<String, Object> findHy(String id){
		String sql="select * from tdic_gbtbusiness where cid='"+id+"'";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public AQZF_SafetyCheckPlanEntity findById(long id) {
		return find(id);
	}

	@Override
	public void deletefa(long id1) {
		String sql=" UPDATE aqzf_safetycheckscheme SET s3=1 WHERE id1="+id1;
		updateBySql(sql);
	}
	
	@Override
	public void deletejl(String faids) {
		String sql=" UPDATE aqzf_safetycheckrecord SET s3=1 WHERE id1 in("+faids+")";
		updateBySql(sql);
	}

	@Override
	public List<Map<String, Object>> findJcfaById(long id1) {
		String sql="SELECT * from  aqzf_safetycheckscheme where id1="+id1+" and s3=0";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
}
