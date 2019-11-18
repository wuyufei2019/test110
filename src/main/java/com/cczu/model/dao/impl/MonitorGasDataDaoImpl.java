package com.cczu.model.dao.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IMonitorGasDataDao;
import com.cczu.model.entity.Main_SignalGasEntity;
import com.cczu.util.dao.BaseDao;

@Repository("MonitorGasDataDao")
public class MonitorGasDataDaoImpl extends BaseDao<Main_SignalGasEntity, Long> implements IMonitorGasDataDao {

	@Override
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
        String content=content(mapData);
	   	String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id desc) AS RowNumber,e.m1 qyname,e.id qyid,b.M2 qtname,b.M3 lx,b.level1 yj,b.level2 ej,a.colltime rq,a.level nd ,b.m1 wh "
	   			+ " FROM (SELECT * FROM main_signal_gas t1 WHERE id =(SELECT max(id) FROM main_signal_gas t2 where t2.ID1=t1.id1  )) a "
	   	 		+ " JOIN bis_sensor b on b.id=a.id1 "
			 	+ " JOIN bis_enterprise e on e.id=b.id1 "
	   	 		+ " WHERE e.S3=0 "+content+") "
	   			+ " as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> dataGrid2(String id1) {
		String sql=" SELECT * FROM ("+
				"select ROW_NUMBER() OVER (ORDER BY bis.m1) AS RowNumber,gas.id,bis.id qyid,gas.m1 wh,gas.m2 qtname,gas.level1 yj1,gas.level2 yj2,sig.level nd,sig.colltime sj FROM (SELECT * FROM main_signal_gas t1 WHERE id =(SELECT max(id) FROM main_signal_gas t2 where t2.ID1=t1.id1  )) sig LEFT JOIN bis_sensor gas on gas.ID=sig.id1 LEFT JOIN bis_enterprise bis on  bis.id=gas.ID1 where 1=1 and datediff(ss,sig.colltime,GETDATE())<180 "+
				")  AS s where s.id not in("+id1+")" ;	
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> dataGrid3() {
		String sql=" SELECT id2 FROM fmew_alarm where type=2 and status=0 " ;	
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> findbj(Map<String, Object> mapData) {
		String sql=" SELECT * FROM fmew_alarm where id2='"+mapData.get("qtid")+"' and type='2' and status='0' " ;	
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
			content = content + "AND e.id ="+mapData.get("qyid")+" "; 
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

	@Override
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
	   	String sql ="SELECT ROW_NUMBER() OVER (ORDER BY a.id desc) AS rownum,e.m1 qyname,e.id qyid,b.M2 qtname,b.M3 lx,b.level1 yj,b.level2 ej,a.colltime rq,a.level nd ,b.m1 wh "
	   			+ " FROM (SELECT * FROM main_signal_gas t1 WHERE id =(SELECT max(id) FROM main_signal_gas t2 where t2.ID1=t1.id1  )) a "
	   	 		+ " LEFT JOIN bis_sensor b on b.id=a.id1 "
			 	+ " LEFT JOIN bis_enterprise e on e.id=b.id1 "
	   	 		+ " WHERE e.S3=0 "+content ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM (SELECT * FROM main_signal_gas t1 WHERE id =(SELECT max(id) FROM main_signal_gas t2 where t2.ID1=t1.id1  )) a"
				+ " JOIN bis_sensor b on b.id=a.id1 "
			 	+ " JOIN bis_enterprise e on e.id=b.id1 "
	   	 		+ " WHERE e.S3=0 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public List<Map<String, Object>> qyList(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT id,m1 FROM bis_enterprise e WHERE id IN (SELECT DISTINCT bis.id1 FROM main_signal_gas sig left join bis_sensor bis on bis.ID=sig.id1) " + content;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> findInfoById(long id) {
		String sql="SELECT a.m2 qtname,b.level bfb,b.colltime sj,b.sensorno wh,a.level1 level1,a.level2 level2,(b.level*0.01*1000000) as ppm,a.M3 lx FROM bis_sensor a "
				+ " INNER JOIN (SELECT * FROM main_signal_gas t1 WHERE id =(SELECT max(id) FROM main_signal_gas t2 where t2.ID1=t1.id1)) b on a.ID=b.id1 WHERE a.ID1= '"+id+"'" ;
		List<Map<String,Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getqyList(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT TOP "+mapData.get("pageSize")+" * FROM ("+
				"select ROW_NUMBER() OVER (ORDER BY m1) AS RowNumber,id,m1,m11,m11_1,m11_2,m11_3,m5,m6,(case M36 when '1' then '工贸' when '2' then '化工' end) as m36,(case when (M39= '1') then '是' else '否'end) m39,m19,m44 "
				+ "FROM bis_enterprise e WHERE e.s3=0 and id IN (SELECT DISTINCT bis.id1 FROM main_signal_gas sig left join bis_sensor bis on bis.ID=sig.id1) "+content
				+" )  AS s WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

}
