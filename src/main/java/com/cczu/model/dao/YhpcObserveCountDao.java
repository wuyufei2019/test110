package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_Observations_Main;
import com.cczu.util.dao.BaseDao;

/**
 * 隐患排查-观察统计分析DAO
 *
 */
@Repository("YhpcObserveCountDao")
public class YhpcObserveCountDao extends BaseDao<YHPC_Observations_Main, Long> {

	/**
	 * 不安全统计（按部门）
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="select m1,sum(sl) sl,sum(case when xw='不安全行为' then sl else 0 end) baqxw,sum(case when xw='不安全状态' then sl else 0 end) baqzt,sum(qs) qs,sum(zs) zs,sum(sw) sw,sum(qt) qt from("
				+ " select a.*,b.m5 sl,b.m1 yw,b.m3 xw,ISNULL(b.qs, 0) qs,ISNULL(b.zs, 0) zs,ISNULL(b.sw, 0) sw ,ISNULL(b.qt, 0) qt from yhpc_unsafeact a left join"
				+ " (select a.*,(case when a.m2='A' then 1 else 0 end)*a.m5 qs,(case when a.m2='B' then 1 else 0 end)*a.m5 zs,(case when a.m2='C' then 1 else 0 end)*a.m5 sw,(case when a.m2='D' then 1 else 0 end)*a.m5 qt,(case a.m2 when 'A' then '轻伤事故' when 'B' then '重伤事故' when 'C' then '死亡事故' when 'D' then '其他事故'  end) sh"
				+ " from yhpc_observations_sec a"
				+ " LEFT JOIN yhpc_observations_main b on a.id1=b.id"
				+ " left join bis_enterprise bis on bis.id = b.ID1 where 1=1 "+content+" "
				+ " )b on a.id=b.id2 where a.s3=0 and b. s3=0 UNION("
				+ " select a. *,0 sl,'无' as yw,'' xw,0 as qs,0 as  zs,0 as sw,0 as qt from yhpc_unsafeact a where a.id not in "
				+ " (select a.id2 from yhpc_observations_sec a LEFT JOIN yhpc_observations_main b "
				+ " left join bis_enterprise bis on bis.id = b.ID1 on a.id1=b.id where a.s3=0 and b.s3=0 "+content+" ) and a.s3=0)"
				+ " )a group by m1 order by sl desc";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}

	/**
     * 查询条件（企业端）
     * @param mapData
     * @return
     */
    public String content(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND b.id1='"+mapData.get("qyid")+"' "; 
		}
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			content = content +" AND b.s1>'"+mapData.get("starttime")+"' "; 
		}
		if(mapData.get("endtime")!=null&&mapData.get("endtime")!=""){
			content = content +" AND b.s1<'"+mapData.get("endtime")+"' "; 
		}	
		if(mapData.get("depart")!=null&&mapData.get("depart")!=""){
			content = content +" AND b.m1='"+mapData.get("depart")+"' "; 
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
