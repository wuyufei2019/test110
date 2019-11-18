package com.cczu.model.mbgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.mbgl.entity.Target_SafetyAdvice;
import com.cczu.util.dao.BaseDao;


/**
 * 目标管理-建言献策DAO
 * @author YZH
 */
@Repository("TargetHlhjyDao")
public class TargetHlhjyDao extends BaseDao<Target_SafetyAdvice, Long>{
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY bis.m1 ,a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS "
				+ "RowNumber,a.*,bis.m1 qyname,u.name username ,v.result FROM target_safetyadvice a left join bis_enterprise bis on a.id1=bis.id "
				+ " left join t_user u on u.id=a.id2 left join target_safetyadvicereview v on v.id1=a.id"
				+ " WHERE a.S3=0  and bis.s3=0 "+ content+" )as a WHERE RowNumber > "
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
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if (mapData.get("fid") != null && mapData.get("fid") != "") {
			content = content + " AND ( bis.fid='" + mapData.get("fid")
					+ "' or bis.id='" + mapData.get("fid") + "') ";
		}
		if (mapData.get("qyname") != null && mapData.get("qyname") != "") {
			content = content + " AND bis.m1 like'%" + mapData.get("qyname")+ "%' ";
		}
		if(mapData.get("name")!=null&&mapData.get("name")!=""){
			content = content +" AND a.name like'%"+mapData.get("name")+"%' "; 
		}
		if(mapData.get("theme")!=null&&mapData.get("theme")!=""){
			content = content +" AND a.theme like'%"+mapData.get("theme")+"%' "; 
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
		String sql="SELECT COUNT(a.id) sum FROM target_safetyadvice a left join bis_enterprise bis on a.id1=bis.id "
				+ "WHERE a.S3=0 and bis.s3=0"+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE target_safetyadvice SET S3=1 WHERE ID="+id;
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
		String sql=" SELECT bis.m1 qyname,a.* FROM target_safetyadvice a left join bis_enterprise bis on a.id1=bis.id "
				+ "WHERE a.S3=0 and bis.s3=0 "+ content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
}
