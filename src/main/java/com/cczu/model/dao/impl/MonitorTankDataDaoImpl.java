package com.cczu.model.dao.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IMonitorTankDataDao;
import com.cczu.model.entity.Main_SignalStorageEntity;
import com.cczu.util.dao.BaseDao;

@Repository("MonitorTankDataDao")
public class MonitorTankDataDaoImpl extends BaseDao<Main_SignalStorageEntity, Long> implements IMonitorTankDataDao{

	@Override
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql=" SELECT TOP "+mapData.get("pageSize")+" * FROM ("+
				"select ROW_NUMBER() OVER (ORDER BY bis.m1) AS RowNumber,tank.id,bis.id qyid,bis.m1 qyname, tank.m1 cgname,tank.m2 lx,tank.m3 rj," +

				"tank.m7 wl,tank.m9 wh,tank.m10 gj,tank.m11 gh,sig.level yw,sig.reserves cl,sig.temperature wd,sig.pressure yl,sig.[percent] bl," +

				"sig.colltime sj FROM (SELECT * FROM main_signal_storage t1 WHERE id =(SELECT max(id) FROM main_signal_storage t2 where t2.ID1=t1.id1  )) sig " +

				"LEFT JOIN bis_tank tank on tank.ID=sig.id1 LEFT JOIN bis_enterprise bis on  bis.id=tank.ID1 where 1=1"+content+
				
				")  AS s WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> dataGrid2(String id1) {
		String sql=" SELECT * FROM ("+
				"select ROW_NUMBER() OVER (ORDER BY bis.m1) AS RowNumber,tank.id,bis.id qyid,tank.m1 cgname,tank.level2 ywyj,tank.temperature2 wdyj,tank.pressure2 ylyj,tank.m9 wh,sig.colltime sj,sig.level yw,sig.pressure yl,sig.temperature wd FROM (SELECT * FROM main_signal_storage t1 WHERE id =(SELECT max(id) FROM main_signal_storage t2 where t2.ID1=t1.id1  )) sig LEFT JOIN bis_tank tank on tank.ID=sig.id1 LEFT JOIN bis_enterprise bis on  bis.id=tank.ID1 where 1=1 and datediff(ss,sig.colltime,GETDATE())<180 "+
				")  AS s where s.id not in("+id1+")" ;	
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> dataGrid3() {
		String sql=" SELECT id2 FROM fmew_alarm where type=1 and status=0 " ;	
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> findbj(Map<String, Object> mapData) {
		String sql=" SELECT * FROM fmew_alarm where id2='"+mapData.get("cgid")+"' and type='1' and status='0' " ;	
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);

		String sql="SELECT COUNT(*) SUM  FROM (SELECT * FROM main_signal_storage t1 WHERE id =(SELECT max(id) FROM main_signal_storage t2 where t2.ID1=t1.id1  )) sig LEFT JOIN bis_tank tank on tank.ID=sig.id1 LEFT JOIN bis_enterprise bis on  bis.id=tank.ID1 where 1=1 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public List<Map<String, Object>> findInfoById(long id) {
		String sql="SELECT tank.m2,tank.m7,tank.m9,sig.* FROM bis_tank tank  INNER JOIN (SELECT * FROM main_signal_storage t1 WHERE id =(SELECT max(id) FROM main_signal_storage t2 where t2.ID1=t1.id1   )) sig on tank.ID=sig.id1 WHERE tank.ID1= '"+id+"'" ;
		List<Map<String,Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql="select ROW_NUMBER() OVER (ORDER BY bis.m1) AS RowNumber,tank.id,bis.id qyid,bis.m1 qyname, tank.m1 cgname,(case tank.m2 when '1' then '立式圆筒形储罐' "
				+" when '2' then '卧式圆筒形储罐' "
				+" when '3' then '球形储罐' "
				+" when '4' then '其他储罐' "
				+" else '' end) lx,tank.m3 rj,tank.m7 wl,tank.m9 wh,tank.m10 gj,tank.m11 gh,sig.level yw,sig.reserves cl,sig.temperature wd,sig.pressure yl,sig.[percent] bl FROM (SELECT * FROM main_signal_storage t1 WHERE id =(SELECT max(id) FROM main_signal_storage t2 where t2.ID1=t1.id1  )) sig LEFT JOIN bis_tank tank on tank.ID=sig.id1 LEFT JOIN bis_enterprise bis on  bis.id=tank.ID1 where 1=1 "+content;
		
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> findLastOverData() {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + "AND  CHARINDEX('"+mapData.get("qyname")+"', bis.m1 )!=0  "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + " AND CHARINDEX('"+mapData.get("xzqy")+"', bis.id2 )=1  "; 
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND bis.m36='"+mapData.get("jglx")+"'"; 
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
	/**
	 * 查询条件判断
	 * @return
	 * @throws ParseException 
	 */
	public String content2(Map<String, Object> mapData) {
		String content="";
		if(mapData.get("qyid")!=null && mapData.get("qyid")!="")
			content= " AND  bis.id="+mapData.get("qyid");
	 	else{
	 		if(mapData.get("xzqy")!=null && mapData.get("xzqy")!=""){
	 			content= " AND CHARINDEX('"+mapData.get("xzqy")+"', bis.id2 )=1";
	 			if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
	 				content = content + "AND bis.m36='"+mapData.get("jglx")+"' "; 
	 			}
	 			if(mapData.get("qyname")!=null && mapData.get("qyname")!="")
	 				content+=" AND bis.m1 like '%"+mapData.get("qyname")+"%'";
	 		}
		}
		if(mapData.get("tankid")!=null && mapData.get("tankid")!="")
			content= " AND tank.id =" + mapData.get("tankid");
		return content;
	}

	@Override
	public List<Map<String, Object>> qyList(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT id,m1 from bis_enterprise bis  right join (select  DISTINCT tank.id1  from main_signal_storage sig left join bis_tank tank on sig.id1=tank.ID) s on bis.id=s.id1 WHERE 1=1 "+content;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> dataGridByQyid(Long qyid) {
		String sql="select ROW_NUMBER() OVER (ORDER BY tank.ID) AS RowNumber,tank.id, tank.m1 cgname,tank.m2 lx,tank.m3 rj,tank.m7 wl,tank.m9 wh,tank.m10 gj,tank.m11 gh,sig.level yw,sig.reserves cl,sig.temperature wd,sig.pressure yl,sig.[percent] bl FROM (SELECT * FROM main_signal_storage t1 WHERE id =(SELECT max(id) FROM main_signal_storage t2 where t2.ID1=t1.id1  )) sig LEFT JOIN bis_tank tank on tank.ID=sig.id1 where tank.ID1="+qyid;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		
		return list;
	}


	@Override
	public List<Map<String, Object>> getMatSsDate(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String sql;
		String content=content2(mapData);
		if("pie".equals(mapData.get("type"))){
		 sql="select dic.label name, sum(round(sig.reserves,2)) value FROM (SELECT *  FROM main_signal_storage t1 WHERE id =(SELECT max(id)  FROM  main_signal_storage t2 where t2.ID1=t1.id1 )) sig  LEFT JOIN bis_tank tank  on tank.ID=sig.id1 LEFT JOIN bis_enterprise bis  on  bis.id=tank.ID1 left join t_dict dic on  dic.value= tank.id3 and dic.type='wxhxpfl' where 1=1 "+content+"  GROUP BY label HAVING dic.label is not null order by value desc ";
		}else{
		 sql="select tank.m7 name, sum(round(sig.reserves,2)) value FROM (SELECT *  FROM main_signal_storage t1 WHERE id =(SELECT max(id)  FROM  main_signal_storage t2 where t2.ID1=t1.id1 )) sig  LEFT JOIN bis_tank tank  on tank.ID=sig.id1 LEFT JOIN bis_enterprise bis  on  bis.id=tank.ID1  where 1=1 " + content+"  GROUP BY tank.m7 HAVING tank.m7 is not null ORDER BY VALUE desc";
		}
		 List<Map<String, Object>> list = findBySql(sql,null,Map.class);
		 return list;
	}

	@Override
	public List<Map<String, Object>> getMatLsbdDate(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content2(mapData);
		String sql="SELECT a.* FROM (SELECT a.pointid,  round(AVG (a.ssdata),2) count, CONVERT (VARCHAR (10),a.updatetime,120) ct,b.target_type label,b.equip_code " +
				"FROM main_monitoring_historydata a " +
				"LEFT JOIN bis_monitor_point_maintain b ON a.pointid = b.id "+
				"where a.updatetime>='"+mapData.get("datestart")+" 00:00:00.000' and a.updatetime<='"+mapData.get("dateend")+" 23:59:59.999'  " +
				"GROUP BY a.pointid,CONVERT (VARCHAR (10),a.updatetime,120),b.target_type,b.equip_code)a " +
				"LEFT JOIN bis_tank tank ON a.equip_code = tank.equipcode " +
				"LEFT JOIN bis_enterprise bis on tank.id1= bis.id where 1=1 "+content;
		List<Map<String, Object>> list = findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> getMatTtlDate(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		//速度较慢
		//SELECT dic.value, sum(case  when a.change>0 then a.change else 0 end )count1 ,sum(case  when a.change<0 then a.change else 0 end )count2 from main_signal_storage a left join bis_tank tank on tank.id=a.id1  left join bis_enterprise bis on bis.id=tank.id1 LEFT JOIN T_dict dic on dic.value= tank.id3 and type='wxhxpfl' where a.change is not null and a.change!=0 and bis.id2 like'1%' and bis.m1 like'德纳滨海化工有限公司' and  a.colltime>'2017-08-04 00:00:00.000' and a.colltime<'2017-09-04 23:59:59.999' group by dic.value
		String sql;
		if("type".equals(mapData.get("type")))
			sql="SELECT dic.label,sum(tmp.count1) count1,"
				+ "sum(tmp.count2)count2 from ( SELECT id1,sum(case  when a.change>0 then a.change else 0 end )count1 ,"
				+ "sum(case  when a.change<0 then a.change else 0 end )count2 "
				+ "from main_signal_storage a WHERE a.change is not null and a.change!=0 "
				+ "and a.colltime>='"+mapData.get("datestart")+" 00:00:00.000' "
				+ "and a.colltime<='"+mapData.get("dateend")+" 23:59:59.999' group by id1) as tmp "
				+ "LEFT JOIN bis_tank tank on tmp.id1=tank.ID "
				+ "left join bis_enterprise bis on bis.id=tank.id1 "
				+ "LEFT JOIN t_dict  dic on dic.value= tank.id3 and type='wxhxpfl'  where 1=1"+content2(mapData)
				+ " group by label";
		else
			sql="SELECT tank.m7 as label,sum(tmp.count1) count1,"
					+ "sum(tmp.count2)count2 from ( SELECT id1,sum(case  when a.change>0 then a.change else 0 end )count1 ,"
					+ "sum(case  when a.change<0 then a.change else 0 end )count2 "
					+ "from main_signal_storage a WHERE a.change is not null and a.change!=0 "
					+ "and a.colltime>='"+mapData.get("datestart")+" 00:00:00.000' "
					+ "and a.colltime<='"+mapData.get("dateend")+" 23:59:59.999' group by id1) as tmp "
					+ "LEFT JOIN bis_tank tank on tmp.id1=tank.ID "
					+ "left join bis_enterprise bis on bis.id=tank.id1 "
					+ " where 1=1 "+content2(mapData)+" group by tank.m7";
			
			
		// String sql="SELECT CONVERT(VARCHAR(10),a.colltime,120) ct, sum(case  when a.change>0 then a.change else 0 end )count1 ,sum(case  when a.change<0 then a.change else 0 end )count2 from main_signal_storage a left join bis_tank tank on tank.id=a.id1 left join bis_enterprise bis on bis.id=tank.id1 where a.change is not null and a.change!=0 and bis.id2 like'"+mapData.get("xzqy")+"%'"+(StringUtils.isNotBlank(mapData.get("qyid").toString())?(" and bis.id="+mapData.get("qyid")):"")+" group by CONVERT(VARCHAR(10),a.colltime,120)";
		 List<Map<String, Object>> list = findBySql(sql,null,Map.class);
		 return list;
	}

	@Override
	public List<Map<String, Object>> getMatTtlDateByTime(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String sql;
		if(mapData.get("type")!=null&&"month".equals(mapData.get("type"))){
			 sql="SELECT sum(case  when a.change>0 then a.change else 0 end)count1 ,"
					+ "sum(case  when a.change<0 then a.change else 0 end )count2,CONVERT(VARCHAR(7), a.colltime,120) ct from main_signal_storage a"
					+ " left join bis_tank tank on tank.id=a.id1 LEFT JOIN "
					+ " bis_enterprise bis on bis.id=tank.id1 "
					+ "where CONVERT(VARCHAR(7), a.colltime,120)>='"+mapData.get("datestart").toString().substring(0,7)+"' and CONVERT(VARCHAR(7), a.colltime,120)<='"
					+mapData.get("dateend").toString().substring(0,7)+"'"
					+content2(mapData)+" AND change!=0 and change is not null "
					+ "GROUP BY CONVERT(VARCHAR(7), a.colltime,120)";
		}else{
			 sql="SELECT sum(case  when a.change>0 then a.change else 0 end)count1 ,"
					+ "sum(case  when a.change<0 then a.change else 0 end )count2,CONVERT(VARCHAR(10), a.colltime,120) ct from main_signal_storage a"
					+ " left join bis_tank tank on tank.id=a.id1 LEFT JOIN "
					+ " bis_enterprise bis on bis.id=tank.id1 "
					+ "where a.colltime>='"+mapData.get("datestart")+" 00:00:00.000' and a.colltime<='"
					+mapData.get("dateend")+" 23:59:59.999' "
					+content2(mapData)+" AND change!=0 and change is not null "
					+ "GROUP BY CONVERT(VARCHAR(10), a.colltime,120)";
		}
		//String  sql="SELECT sum(case  when a.change>0 then a.change else 0 end)count1 ,sum(case  when a.change<0 then a.change else 0 end )count2,CONVERT(VARCHAR(10), a.colltime,120) ct from main_signal_storage a where a.colltime>'2017-08-04 00:00:00.000' and a.colltime<'2017-09-04 23:59:59.999'  AND change!=0 and change is not null GROUP BY CONVERT(VARCHAR(10), a.colltime,120)";
		return  findBySql(sql,null,Map.class);
	}


	@Override
	public List<Map<String, Object>> getqylist(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT TOP "+mapData.get("pageSize")+" * FROM ("+
				"select ROW_NUMBER() OVER (ORDER BY m1) AS RowNumber,id,m1,m11,m11_1,m11_2,m11_3,m5,m6,(case M36 when '1' then '工贸' when '2' then '化工' end) as m36,(case when (M39= '1') then '是' else '否'end) m39,m19,m44 from bis_enterprise bis  right join (select  DISTINCT tank.id1  from main_signal_storage sig left join bis_tank tank on sig.id1=tank.ID) s on bis.id=s.id1 WHERE bis.s3=0 "+content
				+" )  AS s WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
}
