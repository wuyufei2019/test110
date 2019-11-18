package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_Observations_Main;
import com.cczu.util.dao.BaseDao;

/**
 * 隐患排查-观察记录DAO
 *
 */
@Repository("YhpcObserveDao")
public class YhpcObserveDao extends BaseDao<YHPC_Observations_Main, Long> {

	/**
	 * 分页查询（企业端）
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.*,b.m1 qyname,c.NAME username,d.m1 depart FROM yhpc_observations_main a"
				+ " left join bis_enterprise b on a.id1=b.id"
				+ " left join t_user c on a.id2=c.id"
				+ " left join t_department d on a.m1=d.id"
				+ " where a.S3=0 and b.S3=0"+content+" ) "
				+ " as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}

    /**
     * 分页统计（企业端）
     * @param mapData
     * @return
     */
    public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM yhpc_observations_main a"
				+ " left join bis_enterprise b on a.id1=b.id WHERE a.S3=0 and b.S3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	/**
	 * 查询选中观察记录的详细列表
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> findObserveList(Map<String, Object> mapData) {
 		String sql =" select a.*,b.id secid,b.m1 yw,b.m3 xw,b.m4 yg,b.m5 sl,sh,b.m6 ms,b.m7 fj from yhpc_unsafeact a left join("
				+ " select a.*,(case a.m2 when 'A' then '轻伤' when 'B' then '重伤' when 'C' then '死亡' when 'D' then '其他' end) sh"
				+ " from yhpc_observations_main b"
				+ " LEFT JOIN yhpc_observations_sec a on a.id1=b.id where a.s3=0 and b.s3=0 and a.id1='"+mapData.get("id")+"'"
				+ " )b on a.id=b.id2 where a.s3=0 and b. s3=0 ORDER BY m1,yw desc" ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}
	
    /**
     * 选中观察记录的详细列表分页统计
     * @param mapData
     * @return
     */
    public int getTotalCount2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM yhpc_unsafeact a WHERE a.S3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
	/**
     * 查询条件（企业端）
     * @param mapData
     * @return
     */
    public String content(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND a.id1='"+mapData.get("qyid")+"' "; 
		}
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			content = content +" AND a.s1>'"+mapData.get("starttime")+"' "; 
		}
		if(mapData.get("endtime")!=null&&mapData.get("endtime")!=""){
			content = content +" AND a.s1<'"+mapData.get("endtime")+"' "; 
		}		
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( b.fid='"+mapData.get("fid")+"' or b.id='"+mapData.get("fid")+"') "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND b.m1 LIKE'%"+mapData.get("qyname")+"%' "; 
		}
		return content;
		
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE yhpc_observations_main SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

    /**
     * 删除
     * @param id
     */
    public void deleteJl(Long id) {
		String sql=" UPDATE yhpc_observations_sec SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}
    
    /**
     * 导出
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
    	String content=content(mapData);
		String sql=" SELECT b.m1 qyname,a.* FROM yhpc_observations_main a "
				+ " left join bis_enterprise b on b.id=a.id1 "
				+ " WHERE a.S3=0 AND b.S3=0 "+ content +"ORDER BY a.id desc ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		 
		return list;
	}

    //app添加观察记录主表
	public void addInfoForApp(YHPC_Observations_Main entity) {
		// TODO Auto-generated method stub
		save(entity);
	}
	
	/**
	 * 查询选中观察记录的详细信息
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> findObserveViewForApp(String secid) {
 		String sql =" select a.m3 xw,a.m4 ry,a.m5 sl,a.m6 ms,a.m7 fj,(case a.m2 when 'A' then '轻伤' when 'B' then '重伤' when 'C' then '死亡' when 'D' then '其他' end) sh"
				+ " from yhpc_observations_main b"
				+ " LEFT JOIN yhpc_observations_sec a on a.id1=b.id where a.s3=0 and b.s3=0 and a.id='"+secid+"'";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
}
