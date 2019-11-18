package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_DaliyCheckEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 日常安全检查表库DAO
 *
 */
@Repository("YhpcRcjcbkDao")
public class YhpcRcjcbkDao extends BaseDao<YHPC_DaliyCheckEntity, Long> {

	/**
	 * 分页查询（企业端）
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY a.m1,a.m2 desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.* FROM yhpc_dailycheck a"
				+ " left join bis_enterprise b on a.id1=b.id"
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
		String sql=" SELECT COUNT(*) sum  FROM yhpc_dailycheck a WHERE a.S3=0 "+ content;
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
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND a.m1 like '%"+mapData.get("m1")+"%' "; 
		}
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content +" AND a.m2 like '%"+mapData.get("m2")+"%' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND a.id1='"+mapData.get("qyid")+"' "; 
		}
		return content;
		
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE yhpc_dailycheck SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

    /**
     * 查询不安全行为类型list
     * @param mapData
     * @return
     */
	public List<Map<String, Object>> findXwlbList(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT distinct a.m1 as text FROM yhpc_dailycheck a where a.m1 is not null and a.s3=0 "+ content; 
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

    /**
     * 根据类型查询单元list
     * @param mapData
     * @return
     */
	public List<Map<String, Object>> findUnitList(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT distinct a.m2 as text FROM yhpc_dailycheck a where a.m2 is not null and a.s3=0 "+ content; 
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
    /**
     * 根据类型和单元查询内容list
     * @param mapData
     * @return
     */
	public List<Map<String, Object>> findContentList(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT distinct a.m3 as text FROM yhpc_dailycheck a where a.m3 is not null and a.s3=0 "+ content; 
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

    /**
     * 根据类型查询单元list2
     * @param mapData
     * @return
     */
	public List<YHPC_DaliyCheckEntity> findContentList2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT * FROM yhpc_dailycheck a where a.m3 is not null and a.s3=0 "+ content; 
		List<YHPC_DaliyCheckEntity> list=findBySql(sql, null,YHPC_DaliyCheckEntity.class);
		return list;
	}
	
    /**
     * 导出
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
    	String content=content(mapData);
		String sql=" SELECT b.m1 qyname,a.* FROM yhpc_dailycheck a "
				+ " left join bis_enterprise b on b.id=a.id1 "
				+ " WHERE a.S3=0 AND b.S3=0 "+ content +"ORDER BY a.id desc ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		 
		return list;
	}

}
