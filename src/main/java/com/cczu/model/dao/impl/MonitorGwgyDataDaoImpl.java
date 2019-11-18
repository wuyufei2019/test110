package com.cczu.model.dao.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IMonitorGwgyDataDao;
import com.cczu.model.entity.Main_SignalTechniqueEntity;
import com.cczu.util.dao.BaseDao;

@Repository("MonitorGwgyDataDao")
public class MonitorGwgyDataDaoImpl extends BaseDao<Main_SignalTechniqueEntity, Long> implements IMonitorGwgyDataDao{

	@Override
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql=" SELECT TOP "+mapData.get("pageSize")+" * FROM ("+
				"select ROW_NUMBER() OVER (ORDER BY bis.m1) AS RowNumber,bis.m1 qyname,bis.ID qyid,tank.m1 name,tank.m2 wl,tank.m3 rj,sig.* FROM (SELECT * FROM main_signal_tsechnique t1 WHERE id =(SELECT max(id) FROM main_signal_tsechnique t2 where t2.ID1=t1.id1  )) sig LEFT JOIN bis_technique tank on tank.ID=sig.id1 LEFT JOIN bis_enterprise bis on  bis.id=tank.ID1 where 1=1 "+content+
				")  AS s WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> dataGrid2(String id1) {
		String sql=" SELECT * FROM ("+
				"select ROW_NUMBER() OVER (ORDER BY bis.m1) AS RowNumber,gwgy.id,bis.id qyid,gwgy.m1 gyname,gwgy.m2 wlname,gwgy.m5 wh,gwgy.level2 ywyj,gwgy.temperature2 wdyj1,gwgy.temperature4 wdyj2,gwgy.pressure2 ylyj,gwgy.flux2 llyj,sig.[percent] yw,sig.innerTemp wd1,sig.ourterTemp wd2,sig.pressure yl,sig.flux ll,sig.colltime sj FROM (SELECT * FROM main_signal_tsechnique t1 WHERE id =(SELECT max(id) FROM main_signal_tsechnique t2 where t2.ID1=t1.id1  )) sig LEFT JOIN bis_technique gwgy on gwgy.ID=sig.id1 LEFT JOIN bis_enterprise bis on  bis.id=gwgy.ID1 where 1=1 and datediff(ss,sig.colltime,GETDATE())<180 "+
				")  AS s where s.id not in("+id1+")" ;		
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> dataGrid3() {
		String sql=" SELECT id2 FROM fmew_alarm where type=3 and status=0 " ;	
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> findbj(Map<String, Object> mapData) {
		String sql=" SELECT * FROM fmew_alarm where id2='"+mapData.get("gyid")+"' and type='3' and status='0' " ;	
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="select COUNT(*) SUM  FROM (SELECT * FROM main_signal_tsechnique t1 WHERE id =(SELECT max(id) FROM main_signal_tsechnique t2 where t2.ID1=t1.id1  )) sig LEFT JOIN bis_technique tank on tank.ID=sig.id1 LEFT JOIN bis_enterprise bis on  bis.id=tank.ID1 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public List<Map<String, Object>> findInfoById(long id) {
		String sql="SELECT t1.*,tank.m1,tank.m2,tank.m8 FROM main_signal_tsechnique t1 left join bis_technique tank on tank.id=t1.id1 WHERE t1.id =(SELECT max(id) FROM main_signal_tsechnique t2 where t2.ID1=t1.id1 ) and tank.ID1= '"+id+"'" ;
		List<Map<String,Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="select ROW_NUMBER() OVER (ORDER BY bis.m1) AS RowNumber,bis.m1 qyname,bis.ID qyid,tank.m1 name,tank.m2 wl,tank.m3 rj,sig.* FROM (SELECT * FROM main_signal_tsechnique t1 WHERE id =(SELECT max(id) FROM main_signal_tsechnique t2 where t2.ID1=t1.id1  )) sig LEFT JOIN bis_technique tank on tank.ID=sig.id1 LEFT JOIN bis_enterprise bis on  bis.id=tank.ID1 where 1=1 "+content;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> findLastOverData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> qyList(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT id,m1 from bis_enterprise bis right join (select DISTINCT tec.id1 from main_signal_tsechnique sig left join bis_technique tec  on sig.id1=tec.ID) s on bis.id=s.id1 WHERE 1=1 "+content;
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
			content = content + "AND bis.m1 like'%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND bis.m36='"+mapData.get("jglx")+"' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + " AND CHARINDEX('"+mapData.get("xzqy")+"', bis.id2 )=1  "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND bis.id ="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("qyids")!=null&&mapData.get("qyids")!=""){
			content = content + "AND bis.id in ("+mapData.get("qyids")+") "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( bis.fid='"+mapData.get("fid")+"' or bis.id='"+mapData.get("fid")+"') "; 
		}
		return content;
	}

	@Override
	public List<Map<String, Object>> getqyList(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT TOP "+mapData.get("pageSize")+" * FROM ("+
				"select ROW_NUMBER() OVER (ORDER BY m1) AS RowNumber,id,m1,m11,m11_1,m11_2,m11_3,m5,m6,(case M36 when '1' then '工贸' when '2' then '化工' end) as m36,(case when (M39= '1') then '是' else '否'end) m39,m19,m44 from bis_enterprise bis right join (select DISTINCT tec.id1 from main_signal_tsechnique sig left join bis_technique tec  on sig.id1=tec.ID) s on bis.id=s.id1 WHERE bis.s3=0 "+content
				+" )  AS s WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
}
