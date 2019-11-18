package com.cczu.model.mbgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.mbgl.entity.Target_SafetyDutyAgreement;
import com.cczu.util.dao.BaseDao;


/**
 * 目标管理-安全责任书DAO
 * @author YZH
 */
@Repository("TargetAqzrsDao")
public class TargetAqzrsDao extends BaseDao<Target_SafetyDutyAgreement, Long>{
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY bis.m1 ,a.year desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS "
				+ "RowNumber,a.*,bis.m1 qyname FROM target_safetydutyagreement a left join bis_enterprise bis on a.id1=bis.id "
				+ " WHERE a.S3=0 and bis.s3=0"+ content+" )as a WHERE RowNumber > "
				+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
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
			content = content +" AND bis.id ='"+mapData.get("qyid")+"' "; 
		}
		if(mapData.get("year")!=null&&mapData.get("year")!=""){
			content = content +" AND a.year ='"+mapData.get("year")+"' "; 
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
		String sql="SELECT COUNT(a.id) sum FROM target_safetydutyagreement a left join bis_enterprise bis on a.id1=bis.id "
				+ "WHERE a.S3=0 and bis.s3=0"+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE target_safetydutyagreement SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}
    
    /**
     * 删除接收信息---根据部门id
     * @param id
     */
    public void deleteRecInfoByDepId(Long id) {
    	String sql=" UPDATE target_safetydutyagreement SET S3=1 WHERE ID="+id;
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
		String sql=" SELECT bis.m1 qyname,a.* FROM target_safetydutyagreement a left join bis_enterprise bis on a.id1=bis.id "
				+ "WHERE a.S3=0 and bis.s3=0 "+ content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
  
    
}
