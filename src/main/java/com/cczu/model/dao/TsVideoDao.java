package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.TS_Video;
import com.cczu.util.dao.BaseDao;

/**
 * 视频监控DAO
 * @author jason
 * @date 2017年9月7日
 */
@Repository("TsVideoDao")
public class TsVideoDao extends BaseDao<TS_Video, Long>{
	
	 public List<TS_Video> findByQyid(long qyid){
		 String sql ="SELECT *  FROM ts_video WHERE id1="+qyid;
		 List<TS_Video> list=findBySql(sql, null,TS_Video.class);
		 return list;
	 }
	 
	 /**
	  * 查询有视频的企业列表
	  * @return
	  */
	 public List<Map<String, Object>> findQyList(Map<String, Object> mapData){
		 String content=content(mapData);
		 String sql ="select id,m1 from bis_enterprise bis where bis.s3=0 and id in(SELECT DISTINCT (id1)  FROM ts_video)  "+content;
		 List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		 return list;
	 }
	 /**
	  * 查询有视频的企业列表
	  * @return
	  */
	 public List<Map<String, Object>> findQyMapList(Map<String, Object> mapData){
		 String content=content(mapData);
		 String sql ="select bis.id,bis.m1 title,bis.m33 address,bis.m16 pointx,bis.m17 pointy,'/static/model/images/map/video.png' icon,'spjk' type from bis_enterprise bis where id in(SELECT DISTINCT (id1)  FROM ts_video)  "+content;
		 List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		 return list;
	 }
	 
	 public List<Map<String, Object>> findSpList(Map<String, Object> mapData){
		 String content=content(mapData);
		 String sql ="SELECT ts.*,bis.m1 qyname FROM ts_video ts left join bis_enterprise bis on ts.id1=bis.id where bis.s3=0  "+content;
		 List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		 return list;
	 }
	 
	 
	 public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
			String content=content(mapData);
			String sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER (ORDER BY ts.id1 desc) AS RowNumber,ts.*,bis.m1 qyname FROM ts_video ts left join bis_enterprise bis on ts.id1=bis.id   WHERE 0=0 "+content +") "
					+ "as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
			List<Map<String, Object>> list=findBySql(sql, null,Map.class);
			return list;
		}
		
		
		public String content(Map<String, Object> mapData) {
			String content="";
			
			if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
				content = content +" AND ts.id1 ="+mapData.get("qyid")+" ";
			}
			if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
				content = content + "AND bis.ID2 like '"+mapData.get("xzqy")+"%' "; 
			}
			if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
				content = content + "AND bis.m36='"+mapData.get("jglx")+"' "; 
			}
			if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
				content = content + "AND bis.m1 like '%"+mapData.get("qyname")+"%' "; 
			}
			//添加集团公司查询条件(集团公司可以看到下属的企业信息)
			if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
				content = content + "AND ( bis.fid='"+mapData.get("fid")+"' or bis.id='"+mapData.get("fid")+"') "; 
			}
			return content;
		}
		
		public int getTotalCount(Map<String, Object> mapData) {
			String content=content(mapData);
			String sql=" SELECT COUNT(*) sum  FROM ts_video WHERE 0=0 "+ content;
			List<Object> list=findBySql(sql);
			return (int) list.get(0);
		}
	 
		public void deleteInfo(Long id) {
			String sql ="DELETE from  ts_video  WHERE ID="+id;
			updateBySql(sql);
		}
		
		
		public boolean isexist(String name,long id){
			 String sql ="SELECT * FROM ts_video where name='"+name+"' and id <>"+id;
			 List<TS_Video> list=findBySql(sql, null,TS_Video.class);
			 if(list!=null&&list.size()>0)
				 return true;
			 else
				 return false;
		 }
		
		public List<Map<String, Object>> getqylistapp(Map<String, Object> tmap) {
			String content=content(tmap);
			String sql="SELECT TOP "+tmap.get("pageSize")+" * FROM ("+
					"SELECT ROW_NUMBER() OVER (ORDER BY bis.id) AS RowNumber,bis.id,bis.m1,bis.m11,bis.m11_1,bis.m11_2,bis.m11_3,bis.m5,bis.m6,(case bis.M36 when '1' then '工贸' when '2' then '化工' end) as m36,(case when (bis.M39= '1') then '是' else '否'end) m39,bis.m19,bis.m44,a.spgs "
					+ "FROM (SELECT id1,COUNT(*) spgs FROM ts_video GROUP BY id1) a LEFT JOIN bis_enterprise bis ON bis.id = a.id1 WHERE bis.s3=0 "+content 
					+ " ) AS s WHERE RowNumber > "+tmap.get("pageSize")+"*("+tmap.get("pageNo")+"-1)";
			List<Map<String, Object>> list=findBySql(sql,null,Map.class);
			return list;
		}

	/**视频实时监控页面
	 *
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> dataGrid2(Map<String, Object> map) {
		String content=content(map);
		String sql=" select ''+CONVERT(varchar,ts.id) id,'qy'+CONVERT(varchar,ts.id1) fid,ts.name name from ts_video ts"
				+" left join bis_enterprise bis on ts.id1=bis.id WHERE 1=1 "+content
				/*+" UNION "
				+" select 'qy'+CONVERT(varchar,id) id,''+CONVERT(varchar,fid) fid,name from("
				+" select DISTINCT(bis.id),lx.type fid,bis.m1 name"
				+" from ts_video a"
				+" left join ts_bis_type lx on a.id1=lx.id1 "
				+" left join bis_enterprise bis on a.id1=bis.id  WHERE 1=1 and bis.id in(select distinct(a.id1) from ts_video) "+content +")a"
				+" UNION "
				+" select CONVERT(varchar,id) id,CONVERT(varchar,fid) fid,name from("
				+" select DISTINCT(a.type) id,0 fid,a.type name "
				+" from ts_bis_type a"
				+" left join bis_enterprise bis on a.id1=bis.id  WHERE 1=1 and bis.id in(select distinct(id1) from ts_video) "+content +")a"*/;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
}
