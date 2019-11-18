package com.cczu.model.zdwxyssjc.dao.imp;

import com.cczu.model.zdwxyssjc.dao.IMonitorPressureDao;
import com.cczu.model.zdwxyssjc.entity.Main_SignalPressureEntity;
import com.cczu.util.dao.BaseDao;

import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Repository("MonitorPressureDao")
public class MonitorPressureDaoImpl extends BaseDao<Main_SignalPressureEntity, Long> implements IMonitorPressureDao {

	@Override
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);

		String sql=" SELECT TOP "+mapData.get("pageSize")+" * FROM ("+
				"select ROW_NUMBER() OVER (ORDER BY bis.m1) AS RowNumber,tank.id,bis.id qyid,bis.m1 qyname, tank.m1 cgname,tank.m2 lx,tank.m3 rj," +

				"tank.m7 wl,tank.m9 wh,tank.m10 gj,tank.m11 gh,sig.ylcgqbh,sig.bjyl,sig.ylbjsj,sig.ssyl,sig.cjsj " +

				"FROM (SELECT * FROM main_signal_pressure t1 WHERE id =(SELECT max(id) FROM main_signal_pressure t2 where t2.ylcgqbh=t1.ylcgqbh  )) sig " +

				"LEFT JOIN bis_tank tank on tank.m18=sig.ylcgqbh LEFT JOIN bis_enterprise bis on  bis.id=tank.ID1 where 1=1"+content+

				")  AS s WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;

		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);

		String sql="SELECT COUNT(*) SUM  FROM (SELECT * FROM main_signal_pressure t1 WHERE id =(SELECT max(id) FROM main_signal_pressure t2 where t2.ylcgqbh=t1.ylcgqbh  )) sig " +
				"LEFT JOIN bis_tank tank on tank.m18=sig.ylcgqbh LEFT JOIN bis_enterprise bis on  bis.id=tank.ID1 where 1=1 "+ content;
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
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + "AND bis.m1 like'%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND bis.id2 like'"+mapData.get("xzqy")+"%' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND bis.id ='"+mapData.get("qyid")+"' "; 
		}
		if(mapData.get("qyids")!=null&&mapData.get("qyids")!=""){
			content = content + "AND bis.id in ("+mapData.get("qyids")+") "; 
		}
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND bis.m36='"+mapData.get("jglx")+"' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( bis.fid='"+mapData.get("fid")+"' or bis.id='"+mapData.get("fid")+"') "; 
		}
		return content;
	}

	@Override
	public List<Map<String, Object>> findMapList(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="";
		 List<Map<String, Object>> findMapList=null;
		try {
			//sql = "SELECT  DISTINCT bis.id ,bis.qyname,bis.lng,bis.lat , ( SELECT STUFF(CONVERT(VARCHAR ,name),1,1,'') from (select name= (SELECT ','+ m1+':'+CONVERT(VARCHAR, m6)+'<br>' from  main_signal_pressure where  id1= bis.id for xml path(''))  ) as h )as s from (SELECT distinct bis.id,bis.m1 qyname,bis.M16 as lng,bis.M17 as lat FROM  main_signal_pressure a LEFT JOIN bis_enterprise b ON a.id1 = bis.id "+ content+" ) b ";
			sql="SELECT  DISTINCT bis.id ,bis.qyname,bis.lng,bis.lat ,c.total, ( SELECT STUFF(CONVERT(VARCHAR(max) ,name),1,1,'') from (select name= (SELECT ','+ m1+':'+CONVERT(VARCHAR, m6)from  main_signal_pressure where  id1= bis.id for xml path(''))  ) as h )as html from (SELECT distinct bis.id,bis.m1 qyname,bis.M16 as lng,bis.M17 as lat FROM  main_signal_pressure a LEFT JOIN bis_enterprise b ON a.id1 = bis.id  "+content+" ) b LEFT JOIN (select id1, sum (m6) total from main_signal_pressure GROUP BY id1) c on bis.id = c.id1 ";
			findMapList=findBySql(sql,null,Map.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return findMapList;
	}
	@Override
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = "SELECT ROW_NUMBER() OVER(order by a.id1) rownum,bis.m1 qyname,a.* FROM main_signal_pressure a LEFT JOIN bis_enterprise b ON a.id1 = bis.id  Where bis.s3=0 " + content;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> qyList(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT id,m1 FROM bis_enterprise b WHERE id IN (SELECT DISTINCT id1 FROM main_signal_pressure) " + content;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> getqylistapp(Map<String, Object> tmap) {
		String content=content(tmap);
		String sql="SELECT TOP "+tmap.get("pageSize")+" * FROM ("+
				"select ROW_NUMBER() OVER (ORDER BY bis.m1) AS RowNumber,bis.id,bis.m1,bis.m11,bis.m11_1,bis.m11_2,bis.m5,bis.m6,(case M36 when '1' then '工贸' when '2' then '化工' end) as m36,c.total,c.sj "
				+ "FROM bis_enterprise b LEFT JOIN (SELECT id1,total,sj FROM (SELECT ROW_NUMBER() OVER (partition by id1 ORDER BY sj desc) as rn,* "
				+ "from (SELECT a.id1,a.total,bis.m5 sj from (select id1, sum (m6) total from main_signal_pressure GROUP BY id1) a "
				+ "LEFT JOIN  main_signal_pressure b ON a.id1 = bis.id1) a ) a where rn<=1) c on bis.id = c.id1"
				+ " WHERE bis.s3=0 and id IN (SELECT DISTINCT id1 FROM main_signal_pressure) "+content 
				+ " ) AS s WHERE RowNumber > "+tmap.get("pageSize")+"*("+tmap.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> findInfoByQyid(long qyid) {
		String sql = "select tank.id,bis.id qyid,bis.m1 qyname, tank.m1 cgname,tank.m2 lx,tank.m3 rj," +

				"tank.m7 wl,tank.m9 wh,tank.m10 gj,tank.m11 gh,sig.ylcgqbh,sig.bjyl,sig.ylbjsj,sig.ssyl,sig.cjsj " +

				"FROM (SELECT * FROM main_signal_pressure t1 WHERE id =(SELECT max(id) FROM main_signal_pressure t2 where t2.ylcgqbh=t1.ylcgqbh  )) sig " +

				"LEFT JOIN bis_tank tank on tank.m18=sig.ylcgqbh LEFT JOIN bis_enterprise bis on  bis.id=tank.ID1 where bis.id = "+qyid;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public Long addInfo(Main_SignalPressureEntity entity) {
		save(entity);
		return entity.getID();
	}

}
