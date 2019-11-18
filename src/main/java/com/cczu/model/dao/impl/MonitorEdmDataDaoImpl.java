package com.cczu.model.dao.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.cczu.model.dao.IMonitorEdmDataDao;
import com.cczu.model.entity.Main_SignalStorageEntity;
import com.cczu.util.dao.BaseDao;

@Repository("MonitorEdmDataDao")
public class MonitorEdmDataDaoImpl extends BaseDao<Main_SignalStorageEntity, Long> implements IMonitorEdmDataDao {

	@Override
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
        String content=content(mapData);
		String sql = "SELECT TOP " + mapData.get("pageSize") + "* FROM ("+
		           "SELECT ROW_NUMBER() OVER(order by a.id1) rownum,b.m1 qyname,a.*,c.total FROM main_signal_people a LEFT JOIN bis_enterprise b ON a.id1 = b.id LEFT JOIN (select id1, sum (m6) total from main_signal_people GROUP BY id1) c on a.id1 = c.id1 Where b.s3=0 " + content + ") s "+
				   "WHERE rownum >" + mapData.get("pageSize") + "*("+mapData.get("pageNo")+"-1)" ;
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
			content = content + "AND b.m1 like'%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND b.id2 like'"+mapData.get("xzqy")+"%' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND b.id ='"+mapData.get("qyid")+"' "; 
		}
		if(mapData.get("qyids")!=null&&mapData.get("qyids")!=""){
			content = content + "AND b.id in ("+mapData.get("qyids")+") "; 
		}
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND b.m36='"+mapData.get("jglx")+"' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( b.fid='"+mapData.get("fid")+"' or b.id='"+mapData.get("fid")+"') "; 
		}
		return content;
	}

	@Override
	public List<Map<String, Object>> findMapList(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="";
		 List<Map<String, Object>> findMapList=null;
		try {
			//sql = "SELECT  DISTINCT b.id ,b.qyname,b.lng,b.lat , ( SELECT STUFF(CONVERT(VARCHAR ,name),1,1,'') from (select name= (SELECT ','+ m1+':'+CONVERT(VARCHAR, m6)+'<br>' from  main_signal_people where  id1= b.id for xml path(''))  ) as h )as s from (SELECT distinct b.id,b.m1 qyname,b.M16 as lng,b.M17 as lat FROM  main_signal_people a LEFT JOIN bis_enterprise b ON a.id1 = b.id "+ content+" ) b ";
			sql="SELECT  DISTINCT b.id ,b.qyname,b.lng,b.lat ,c.total, ( SELECT STUFF(CONVERT(VARCHAR(max) ,name),1,1,'') from (select name= (SELECT ','+ m1+':'+CONVERT(VARCHAR, m6)from  main_signal_people where  id1= b.id for xml path(''))  ) as h )as html from (SELECT distinct b.id,b.m1 qyname,b.M16 as lng,b.M17 as lat FROM  main_signal_people a LEFT JOIN bis_enterprise b ON a.id1 = b.id  "+content+" ) b LEFT JOIN (select id1, sum (m6) total from main_signal_people GROUP BY id1) c on b.id = c.id1 ";
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
		String sql = "SELECT ROW_NUMBER() OVER(order by a.id1) rownum,b.m1 qyname,a.* FROM main_signal_people a LEFT JOIN bis_enterprise b ON a.id1 = b.id  Where b.s3=0 " + content;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM main_signal_people a LEFT JOIN bis_enterprise b ON a.id1 = b.id  WHERE b.s3=0 " + content ;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public List<Map<String, Object>> qyList(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT id,m1 FROM bis_enterprise b WHERE id IN (SELECT DISTINCT id1 FROM main_signal_people) " + content;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> getqylistapp(Map<String, Object> tmap) {
		String content=content(tmap);
		String sql="SELECT TOP "+tmap.get("pageSize")+" * FROM ("+
				"select ROW_NUMBER() OVER (ORDER BY b.m1) AS RowNumber,b.id,b.m1,b.m11,b.m11_1,b.m11_2,b.m5,b.m6,(case M36 when '1' then '工贸' when '2' then '化工' end) as m36,c.total,c.sj "
				+ "FROM bis_enterprise b LEFT JOIN (SELECT id1,total,sj FROM (SELECT ROW_NUMBER() OVER (partition by id1 ORDER BY sj desc) as rn,* "
				+ "from (SELECT a.id1,a.total,b.m5 sj from (select id1, sum (m6) total from main_signal_people GROUP BY id1) a "
				+ "LEFT JOIN  main_signal_people b ON a.id1 = b.id1) a ) a where rn<=1) c on b.id = c.id1"
				+ " WHERE b.s3=0 and id IN (SELECT DISTINCT id1 FROM main_signal_people) "+content 
				+ " ) AS s WHERE RowNumber > "+tmap.get("pageSize")+"*("+tmap.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> findInfoById(long qyid) {
		String sql = "SELECT b.m1 qyname,a.*,c.total FROM main_signal_people a LEFT JOIN bis_enterprise b ON a.id1 = b.id "
				+ "LEFT JOIN (select id1, sum (m6) total from main_signal_people GROUP BY id1) c on a.id1 = c.id1 Where b.s3=0 and a.id1="+qyid;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
}
