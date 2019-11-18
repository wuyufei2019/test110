package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.TMESK_ProtectionDistanceEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 初始化防护距离DAO
 * @author jason
 *
 */
@Repository("SekbCshfhjlDao")
public class SekbCshfhjlDao extends BaseDao<TMESK_ProtectionDistanceEntity, Long>{
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<TMESK_ProtectionDistanceEntity> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,* FROM tmesk_protectiondistance WHERE S3=0 "+content+" ) "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<TMESK_ProtectionDistanceEntity> list=findBySql(sql, null,TMESK_ProtectionDistanceEntity.class);
		
		return list;
	}
	
	/**
     * 查询条件
     * @param mapData
     * @return
     */
    public String content(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("name")!=null&&mapData.get("name")!=""){
			content = content +" AND M2 LIKE'%"+mapData.get("name")+"%' "; 
		}
		 
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND xzqy LIKE '"+mapData.get("xzqy")+"%' "; 
		}
		return content;
		
	}
    /**
     * 分页统计
     * @param mapData
     * @return
     */
    public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM tmesk_protectiondistance WHERE s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE tmesk_protectiondistance SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}
    
    /**
     * 导出Excel
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getExcel(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT * FROM tmesk_protectiondistance WHERE s3=0" + content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
		
	}
    
}
