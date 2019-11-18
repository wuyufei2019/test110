package com.cczu.model.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IFmewSjfxDao;
import com.cczu.model.entity.TS_DeviceData;
import com.cczu.util.dao.BaseDao;

@Repository("fmewSjfxDao")
public class FmewSjfxDaoImpl extends BaseDao<TS_DeviceData,Long> implements IFmewSjfxDao {

	@Override
	public List<Map<String, Object>> findAllInforByLeibie(String xzqy) {
		String content="";
		if((!xzqy.equals(""))&&(!xzqy.equals("0"))){
			content = content + "AND bis.id2 like'"+xzqy+"%' "; 
		}
		StringBuffer sql=new StringBuffer();
		sql.append("select m1,sum(m2) m2 from(");
		sql.append("select c.LABEL m1,ROUND(ISNULL(SUM(b.data1),0),2) m2 from (select * from ts_devicedata where id in (select max(id) from ts_devicedata group by ID1))b left join bis_tank a on a.id2=b.ID1 left join t_dict c on a.ID3=c.VALUE left join bis_enterprise bis on a.ID1=bis.id  where a.S3=0 "+content+" group by c.LABEL");
		sql.append(" UNION ");
		sql.append("select b.m8 m1,sum(a.m1) m2 from ck_kucun a left join bis_mat b on a.id1=b.ID left join bis_enterprise bis on a.ID2=bis.id  where 1=1 "+content+" group by b.m8");
		sql.append(") as s group by m1");
		
		List<Map<String, Object>> list=findBySql(sql.toString(),null,Map.class);	
		
		return list;
	}

	@Override
	public List<Map<String, Object>> findAllInforByName(String xzqy) {
		String content="";
		if((!xzqy.equals(""))&&(!xzqy.equals("0"))){
			content = content + "AND bis.id2 like'"+xzqy+"%' "; 
		}
		
		StringBuffer sql=new StringBuffer();
		sql.append("select m1,sum(m2) m2 from(");
		sql.append("select a.m7 m1,ROUND(ISNULL(SUM(b.data1),0),2) m2 from (select * from ts_devicedata where id in (select max(id) from ts_devicedata group by ID1))b left join bis_tank a on a.id2=b.ID1 left join bis_enterprise bis on a.ID1=bis.id  where a.S3=0 "+content+" group by a.m7");
		sql.append(" UNION ");
		sql.append("select b.m1 m1,sum(a.m1) m2 from ck_kucun a left join bis_mat b on a.id1=b.ID left join bis_enterprise bis on a.ID2=bis.id  where 1=1 "+content+" group by b.m1");
		sql.append(") as s group by m1");
		
		List<Map<String, Object>> list=findBySql(sql.toString(),null,Map.class);	
		
		return list;
	}

	@Override
	public List<Map<String, Object>> findAllCKInforByLeibie(String xzqy) {
		String content="";
		if((!xzqy.equals(""))&&(!xzqy.equals("0"))){
			content = content + "AND bis.id2 like'"+xzqy+"%' "; 
		}
		String sql="select b.m8 m1,sum(a.m1) m2 from ck_kucun a left join bis_mat b on a.id1=b.ID left join bis_enterprise bis on a.ID2=bis.id  where 1=1 "+content+" group by b.m8";
		//String oldsql="select b.m8 m1,sum(a.m1) m2 from ck_kucun a,bis_mat b where a.id1=b.ID  group by b.m8";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> findAllCKInforByName(String xzqy) {
		//String oldsql="select b.m1,sum(a.m1) m2 from ck_kucun a,bis_mat b where a.id1=b.ID  group by b.m1";
		String content="";
		if((!xzqy.equals(""))&&(!xzqy.equals("0"))){
			content = content + "AND bis.id2 like'"+xzqy+"%' "; 
		}
		String sql="select b.m1 m1,sum(a.m1) m2 from ck_kucun a left join bis_mat b on a.id1=b.ID left join bis_enterprise bis on a.ID2=bis.id  where 1=1 "+content+" group by b.m1";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> findAllCGInforByLeibie(String xzqy) {
		String content="";
		if((!xzqy.equals(""))&&(!xzqy.equals("0"))){
			content = content + "AND bis.id2 like'"+xzqy+"%' "; 
		}
		String sql="select c.LABEL m1,ROUND(ISNULL(SUM(b.data1),0),2) m2 ,a.ID3 m3 from (select * from ts_devicedata where id in (select max(id) from ts_devicedata group by ID1))b left join bis_tank a on a.id2=b.ID1 left join t_dict c on a.ID3=c.VALUE left join bis_enterprise bis on a.ID1=bis.id  where a.S3=0 "+content+" group by c.LABEL,a.ID3";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> findAllCGInforByName(String xzqy) {
		String content="";
		if((!xzqy.equals(""))&&(!xzqy.equals("0"))){
			content = content + "AND bis.id2 like'"+xzqy+"%' "; 
		}
		//String sql="select a.m7 m1,ROUND(ISNULL(SUM(b.data1),0),2) m2 from bis_tank a left join (select * from ts_devicedata where colltime in (select max(colltime) from ts_devicedata group by ID1))  b on a.id2=b.ID1 AND a.S3=0 group by a.m7";
		String sql="select a.m7 m1,ROUND(ISNULL(SUM(b.data1),0),2) m2 from (select * from ts_devicedata where id in (select max(id) from ts_devicedata group by ID1))b left join bis_tank a on a.id2=b.ID1 left join bis_enterprise bis on a.ID1=bis.id  where a.S3=0 "+content+" group by a.m7";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	@Override
	public List<Map<String,Object>> findChanelsByQYID(long id) {
		String sql="SELECT  ID2 ,M1,M7 FROM bis_tank  WHERE ID2<>0 AND S3=0 AND ID1="+id;
		List<Object> list=findBySql(sql);
		
		List<Map<String,Object>> newlist=new ArrayList<Map<String,Object>>();
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[])list.get(i);
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("m1", obj[0]);
			map.put("m2", obj[1]);
			map.put("m3", obj[2]);
			newlist.add(map);
		}
		return newlist;
	}

	@Override
	public List<Map<String,Object>> findHistoryDataByQyID(long id, String strattime,String endtime) {
		String sql="select avg(a.data1)data,a.ID1,b.m7,b.m9, CONVERT(varchar(100), colltime, 23) colltime from ts_devicedata a left join bis_tank b on a.ID1=b.ID2 left join bis_enterprise bis on b.ID1=bis.id  where colltime >='"+strattime+"' and colltime <='"+endtime+"' and  bis.id="+id+" group by a.ID1,b.m7,b.m9,CONVERT(varchar(100), colltime, 23)order by a.ID1,b.m7,b.m9, CONVERT(varchar(100), colltime, 23)";
		List<Map<String,Object>> list=findBySql(sql.toString(),null,Map.class);

		return list;
	}



	@Override
	public List<Map<String, Object>> selectDatesToHeatmap(Map<String,Object> mapData) {	
		String content="";
		if (mapData.get("xzqy") != null && mapData.get("xzqy") != "") {
			content = content + " AND bis.id2 like'" + mapData.get("xzqy") + "%' ";
		}
		// 添加监管类型查询条件
		if (mapData.get("jglx") != null && mapData.get("jglx") != "") {
			content = content + "AND bis.m36='" + mapData.get("jglx") + "' ";
		}
		StringBuffer sql=new StringBuffer();
		sql.append("select bis.m16 lng, bis.m17 lat, SUM(xs.m3*xslb.m2) count ");
		sql.append("from (SELECT * FROM main_signal_storage t1 WHERE id =(SELECT max(id) FROM main_signal_storage t2 where  t2.ID1=t1.id1 )) a ");
		sql.append("left join bis_tank b on a.ID1=b.ID ");
		sql.append("left join bis_enterprise bis on b.ID1=bis.id ");
		sql.append("left join wxxs_wlcl xs on a.reserves between xs.m1 and xs.m2 ");
		sql.append("left join wxxs_wllb xslb on b.id3=xslb.m1 ");
		sql.append("WHERE  1=1 "+content+" ");
		sql.append("group by bis.m16, bis.m17  ");
		
		List<Map<String,Object>> list=findBySql(sql.toString(),null,Map.class);
		
		return list;
	}
	@Override
	public List<Map<String, Object>> selectDatesToHeatmap(String date,Map<String, Object> mapData) {
		String content="";
		if (mapData.get("xzqy") != null && mapData.get("xzqy") != "") {
			content = content + " AND bis.id2 like'" + mapData.get("xzqy") + "%' ";
		}
		// 添加监管类型查询条件
		if (mapData.get("jglx") != null && mapData.get("jglx") != "") {
			content = content + "AND bis.m36='" + mapData.get("jglx") + "' ";
		}
		//根据日期统计每个企业所有储罐储量和
		String sql="select  bis.m16 lng, bis.m17 lat,SUM(xs.m3*xslb.m2) count from (select * from main_signal_storage where id in (select max(id) from main_signal_storage  where DATEDIFF(DAY,colltime ,'"+date+"')=0  group by ID1)) a left join bis_tank b on a.ID1=b.ID left join bis_enterprise bis on b.ID1=bis.id left join wxxs_wlcl xs on a.data1 between xs.m1 and xs.m2 left join wxxs_wllb xslb on b.id3=xslb.m1 WHERE 1=1  "+content+"  group by bis.m16,bis.m17";
		List<Map<String,Object>> list=findBySql(sql,null,Map.class);
	
		return list;
	}

	@Override
	public List<Object> findHistoryData(String date, String xzqy) {
		String content="";
		if((!xzqy.equals(""))&&(!xzqy.equals("0"))){
			content = content + "AND bis.id2 like'"+xzqy+"%' "; 
		}
		//根据日期统计每个企业所有储罐储量和
		String sql="select SUM( a.data1) count from (select * from ts_devicedata where id in (select max(id) from ts_devicedata where DATEDIFF(hour,colltime ,'"+date+"')=0 group by ID1)) a left join bis_tank b on a.ID1=b.ID2 left join bis_enterprise bis on b.ID1=bis.id left join t_dict c on b.ID3=c.VALUE WHERE 1=1 "+content+" group by c.LABEL";
		
		List<Object> list=findBySql(sql);
	
		return list;
	}

	@Override
	public List<Map<String,Object>> findHistoryDatesByWllb(Map<String, Object> map) {
		String content="";
		if(map.get("datestart")!=null&&map.get("datestart")!=""){
			content = content + "AND a.colltime >='"+map.get("datestart")+"' "; 
		}
		if(map.get("dateend")!=null&&map.get("dateend")!=""){
			content = content + "AND a.colltime <='"+map.get("dateend")+"' "; 
		}
		if(map.get("xzqy")!=null&&map.get("xzqy")!=""){
			content = content + "AND bis.id2 like '%"+map.get("xzqy")+"%' "; 
		}
		
		StringBuffer sql=new StringBuffer();
		
		sql.append("select  sum(a.data1)data ,a.colltime,c.label from( ");
		sql.append("select ID1,data1, CONVERT(varchar(100), colltime, 23) colltime from ");
		sql.append("ts_devicedata where id in ( select  max(id) from ts_devicedata  group by ID1, CONVERT(CHAR(8),colltime,112))) a ");
		sql.append("left join bis_tank b on a.ID1=b.ID2 left join t_dict c on b.ID3=c.value left join bis_enterprise bis on b.ID1=bis.id ");
		sql.append("where b.ID3 is not null "+ content+" group by a.colltime,c.label order by label ,a.colltime");
		
		List<Map<String,Object>> list=findBySql(sql.toString(),null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> selectHeatmapData(String date, Map<String, Object> mapData) {
		String content="";
		if (mapData.get("xzqy") != null && mapData.get("xzqy") != "") {
			content = content + " AND bis.id2 like'" + mapData.get("xzqy") + "%' ";
		}
		// 添加监管类型查询条件
		if (mapData.get("jglx") != null && mapData.get("jglx") != "") {
			content = content + "AND bis.m36='" + mapData.get("jglx") + "' ";
		}
		StringBuffer sql=new StringBuffer();
		sql.append("select bis.m16 lng, bis.m17 lat, SUM(xs.m3*xslb.m2) count,CONVERT(varchar(20), a.colltime, 23) acceptdate ");
		sql.append("from (select * from main_signal_storage where id in ( select max(id) from main_signal_storage group by ID1,CONVERT(CHAR(8),colltime,112))) a ");
		sql.append("left join bis_tank b on a.ID1=b.ID ");
		sql.append("left join bis_enterprise bis on b.ID1=bis.id ");
		sql.append("left join wxxs_wlcl xs on a.reserves between xs.m1 and xs.m2 ");
		sql.append("left join wxxs_wllb xslb on b.id3=xslb.m1 ");
		sql.append("WHERE a.colltime >'"+date+"' "+content+" ");
		sql.append("group by bis.m16, bis.m17, a.colltime ");
		List<Map<String,Object>> list=findBySql(sql.toString(),null,Map.class);
		
		return list;
	}

	
}
