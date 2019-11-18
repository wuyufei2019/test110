package com.cczu.model.mbgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.mbgl.entity.Target_Basic;
import com.cczu.util.dao.BaseDao;


/**
 * 目标管理-目标设置DAO
 * @author YZH
 *
 */
@Repository("TargetMbszDao")
public class TargetZbszDao extends BaseDao<Target_Basic, Long>{
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT top "+mapData.get("pageSize")+" * FROM ( "
				+ "SELECT ROW_NUMBER() OVER (ORDER BY bis.id desc,a.id desc) AS RowNumber,a.*,bis.m1 qyname FROM "
				+ "target_basic a left join bis_enterprise bis on a.id1=bis.id WHERE a.S3=0 and bis.S3=0 "
				+content+")as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
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
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND a.M1 like '%"+mapData.get("m1")+"%' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND bis.id ='"+mapData.get("qyid")+"' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND bis.m1 like'%"+mapData.get("qyname")+"%' "; 
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
		String sql=" SELECT COUNT(*) sum  FROM target_basic a left join bis_enterprise bis on a.id1=bis.id "
				+ "WHERE a.S3=0 and bis.s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE target_basic SET S3=1 WHERE ID="+id;
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
		String sql=" SELECT a.m1,a.m2,a.m3,bis.m1 qyname FROM target_basic a left join bis_enterprise bis "
				+ "on a.id1=bis.id WHERE a.S3=0 and bis.s3=0  "+ content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
    /**
     * 获取指标名称和id
     * @return
     */
    public List<Map<String, Object>> getTargetIDAndName(Map<String, Object> mapData) {
    	String content=content(mapData);
    	String sql=" SELECT a.id,a.m1 text,a.m2 val FROM target_basic a left join bis_enterprise bis on a.id1=bis.id "
    			+ "WHERE a.S3=0 and bis.S3=0 and a.id not in (select ID1 from target_distribute where s3=0 and m1='"+mapData.get("year")+"' and id3="+mapData.get("deptid")+")"+ content +" ORDER BY a.ID";
    	List<Map<String, Object>> list=findBySql(sql, null,Map.class);
    	return list;
    }

}
