package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.BIS_JobPostEntity;
import com.cczu.util.dao.BaseDao;


/**
 * 
 * @Description: 一企一档---工种、岗位信息DAO
 * @author: YZH
 * @date: 2017年12月27日
 */
@Repository("BisGzxxDao")
public class BisGzxxDao extends BaseDao<BIS_JobPostEntity, Long> {
	
    /**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<BIS_JobPostEntity> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")
				+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.* "
				+ " FROM bis_jobpostentity a WHERE 1=1 "+content+") "+ " as s WHERE  RowNumber > "
				+ mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<BIS_JobPostEntity> list=findBySql(sql, null,BIS_JobPostEntity.class);
  		return list;
	}
	
	/**
     * 查询条件
     * @param mapData
     * @return
     */
    public String content(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND a.ID1 ="+mapData.get("qyid"); 
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND a.m1 ='"+mapData.get("m1")+"' "; 
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
		String sql=" SELECT COUNT(*) sum  FROM bis_jobpostentity a  WHERE 1=1"+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
    public List<Map<String, Object>> getJobTextJson(Map<String, Object> mapData) {
    	String sql="select id,m1 text from bis_jobpostentity a where 1=1 "+content(mapData);
    	return findBySql(sql, null,Map.class);
    }
	
}
