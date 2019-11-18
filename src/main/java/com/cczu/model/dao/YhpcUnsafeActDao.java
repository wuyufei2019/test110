package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_UnsafeActEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 不安全行为DAO
 *
 */
@Repository("YhpcUnsafeActDao")
public class YhpcUnsafeActDao extends BaseDao<YHPC_UnsafeActEntity, Long> {

	/**
	 * 分页查询（企业端）
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY a.m1 desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.* FROM yhpc_unsafeact a"
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
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND a.m1 like'%"+mapData.get("m1")+"%' "; 
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
		String sql=" UPDATE yhpc_unsafeact SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

    /**
     * 查询不安全行为类型list
     * @param mapData
     * @return
     */
	public List<Map<String, Object>> findXwlbList(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT distinct a.m1 as text FROM yhpc_unsafeact a where a.s3=0 "+ content; 
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
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
		String sql=" SELECT b.m1 qyname,a.* FROM yhpc_unsafeact a "
				+ " left join bis_enterprise b on b.id=a.id1 "
				+ " WHERE a.S3=0 AND b.S3=0 "+ content +"ORDER BY a.id desc ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		 
		return list;
	}

	/**
	 * app查询接口
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGridXwlxApp(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT a.* FROM yhpc_unsafeact a"
				+ " left join bis_enterprise b on a.id1=b.id"
				+ " where a.S3=0 and b.S3=0"+content+" ORDER BY a.id desc";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}

}
