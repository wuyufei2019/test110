package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.AQPX_PlanEntity;
import com.cczu.util.dao.BaseDao;


/**
 * 日常培训统计DAO
 * @author YZH
 *
 */
@Repository("AqpxRcpxtjDao")
public class AqpxRcpxtjDao extends BaseDao<AQPX_PlanEntity, Long>{
	
	/**
	 * 培训计划统计分页查询
	 */
	public List<Map<String, Object>> dataGrid2(Map<String, Object> mapData) {
		String content=content2(mapData);
		String sql =" SELECT top 50 * from( SELECT ROW_NUMBER() OVER ( ORDER BY a.userid) AS RowNumber,* FROM"
				+ " ( select a.userid ,a.name,a.qyname,isnull(b.xxcount,0) xxcount,a.kscount,b.xxsj ,a.kssj,cast(a.jg*100.0/a.zs as decimal(9,2)) jgl"
				+ " from (select b.id userid,bis.m1 qyname,b.name,max(a.s1) kssj,count(a.id) kscount,count(CASE WHEN (a.m3='合格') THEN 1 END) jg,count(*) zs from aqpx_examresults a"
				+ " left join t_user b on a.id2=b.id"
				+ " left join bis_enterprise bis on bis.id=a.id1 where a.s3=0 and bis.s3=0 "+content+" group by b.id,bis.m1,b.name,a.id2) a"
				+ " left join (select a.id2 userid,bis.m1 qyname,nullif(max(a.s1), '') xxsj,sum(cast(a.m1 as int))/60 xxcount from aqpx_studyhistory a"
				+ " left join t_user b on a.id2=b.id"
				+ " left join bis_enterprise bis on bis.id=a.id1 where a.s3=0 and bis.s3=0 "+content+" group by a.id2,bis.m1)b on a.userid=b.userid)"
				+ " as a)s WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
     * 培训计划统计分页统计
     */
    public int getTotalCount2(Map<String, Object> mapData) {
		String content=content2(mapData);
		String sql=" SELECT COUNT(DISTINCT a.id2) sum  FROM aqpx_examresults a left join t_user b on b.id=a.id2"
				+ " left join bis_enterprise bis on bis.id=a.id1 WHERE a.s3=0 and bis.s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
     * 查询条件2
     */
    public String content2(Map<String, Object> mapData) {
		String content="";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND a.ID1 ="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("ygname")!=null&&mapData.get("ygname")!=""){
			content = content +" AND b.name like'%"+mapData.get("ygname")+"%'"; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( bis.fid='"+mapData.get("fid")+"' or bis.id='"+mapData.get("fid")+"') "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND bis.m1 LIKE'%"+mapData.get("qyname")+"%' "; 
		}
		return content;
	}
    
}
