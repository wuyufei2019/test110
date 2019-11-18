package com.cczu.model.fkcl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.fkcl.entity.FKCL_FkyyEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 访客预约
 * @author zpc
 *
 */
@Repository("FkclFkyyDao")
public class FkclFkyyDao extends BaseDao<FKCL_FkyyEntity, Long>{

	/**
	 * 分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.m1 desc) AS RowNumber,"
				+ "a.* FROM fkcl_fkyy a WHERE a.s3 = 0 "+content+" ) "
				+ "as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
     * 分页统计
     * @param mapData
     * @return
     */
    public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum FROM fkcl_fkyy a WHERE a.s3 = 0 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
     * 查询条件
     * @param mapData
     * @return
     */
    public String content(Map<String, Object> mapData) {
		String content="";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND a.qyid = "+mapData.get("qyid")+" "; 
		}
		if(mapData.get("status")!=null&&mapData.get("status")!=""){
			content = content +" AND a.status = '"+mapData.get("status")+"' "; 
		}
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content +" AND a.m2 like '%"+mapData.get("m2")+"%' "; 
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND CONVERT(varchar(100), a.m1, 23) = CONVERT(varchar(100),'"+mapData.get("m1")+"', 23) "; 
		}
		return content;
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
    	String sql=" UPDATE fkcl_fkyy SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}
    
    /**
     * 根据id查找详情
     * @param id
     * @return
     */
  	public Map<String,Object> findInfoById(long id) {
  		String sql = "SELECT a.*,(CASE a.status WHEN '1' THEN '预约确认中' WHEN '2' THEN '拒绝预约' WHEN '3' THEN '预约通过待进厂' WHEN '4' THEN '进厂待出厂' WHEN '5' THEN '已出厂' ELSE '' END) zt"
    			+ " FROM fkcl_fkyy a WHERE a.id =" + id;
  		List<Map<String,Object>> list = findBySql(sql, null, Map.class);
  		return list.get(0);
  	}
    
    /**
     * 导出
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
    	String content=content(mapData);
    	String sql ="SELECT a.*,(CASE a.status WHEN '1' THEN '预约确认中' WHEN '2' THEN '拒绝预约' WHEN '3' THEN '预约通过待进厂' WHEN '4' THEN '进厂待出厂' WHEN '5' THEN '已出厂' ELSE '' END) zt"
    			+ " FROM fkcl_fkyy a WHERE a.s3 = 0 "+content+" ORDER BY a.m1 desc";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
}
