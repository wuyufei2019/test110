package com.cczu.model.mbgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.mbgl.entity.Target_SafetyPromiseAgreement;
import com.cczu.util.dao.BaseDao;


/**
 * 目标管理-安全ch承诺书DAO
 * @author YZH
 */
@Repository("TargetAqcnsDao")
public class TargetAqcnsDao extends BaseDao<Target_SafetyPromiseAgreement, Long>{
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY bis.m1 ,a.year desc,a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS "
				+ "RowNumber,a.*,bis.m1 qyname FROM target_safetypromiseagreement a left join bis_enterprise bis on a.id1=bis.id "
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
		if(mapData.get("post")!=null&&mapData.get("post")!=""){
			content = content +" AND a.post like'%"+mapData.get("post")+"%' "; 
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
		String sql="SELECT COUNT(a.id) sum FROM target_safetypromiseagreement a left join bis_enterprise bis on a.id1=bis.id "
				+ "WHERE a.S3=0 and bis.s3=0"+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE target_safetypromiseagreement SET S3=1 WHERE ID="+id;
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
		String sql=" SELECT bis.m1 qyname,a.* FROM target_safetypromiseagreement a left join bis_enterprise bis on a.id1=bis.id "
				+ "WHERE a.S3=0 and bis.s3=0 "+ content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
  
    
    /**
     * 获取指标名称和id
     * @return
     */
    public List<Map<String, Object>> getTargetDisIdJson(Map<String, Object> mapData) {
    	String content=content(mapData);
    	String sql=" SELECT a.id,a.m1+a.m2+c.m1 as text,a.m1 year,a.m2 department,c.m1 targetname,a.targetval "
    			+ "FROM target_safetypromiseagreement a left join target_basic c on a.id1=c.id left join bis_enterprise "
    			+ "bis on a.id2=bis.id WHERE a.S3=0 and bis.S3=0 and c.s3=0 "+ content ;
    	List<Map<String, Object>> list=findBySql(sql, null,Map.class);
    	return list;
    }

}
