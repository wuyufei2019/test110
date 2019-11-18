package com.cczu.model.mbgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.mbgl.entity.Target_Evaluation;
import com.cczu.util.common.Parameter;
import com.cczu.util.dao.BaseDao;


/**
 * 目标管理-目标考评DAO
 * @author YZH
 *
 */
@Repository("TargetMbkpDao")
public class TargetMbkpDao extends BaseDao<Target_Evaluation, Long>{
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY bis.m1,b.m1,b.id3,a.quarter,c.id");
		if(mapData.get("orderBy").equals("department")){
			ordercont=" ORDER BY td.id "+mapData.get("order")+" ";
		}
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER ("+ordercont+") "
				+ "AS RowNumber,a.*,c.m1 targetname,bis.m1 qyname,b.m1 year,td.m1 department,b.targetval "
				+ " from target_evaluation a left join target_distribute b "
				+ " on a.id2=b.id left join target_basic c on c.id=b.id1 left join bis_enterprise bis on bis.id=a.id1"
				+ " left join t_department td on td.id=b.id3 WHERE bis.S3=0 and a.S3=0 and c.s3=0 and b.s3=0 "
				+ content+" )as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
    /**
     * 分页统计
     * @param mapData
     * @return
     */
    public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(a.id) sum from target_evaluation a left join target_distribute b "
				+ " on a.id2=b.id left join target_basic c on c.id=b.id1 "
				+ " left join bis_enterprise bis on bis.id=a.id1  WHERE a.s3=0 and bis.s3=0 "+ content;
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
			content = content +" AND bis.id ='"+mapData.get("qyid")+"' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + " AND ( bis.fid='"+mapData.get("fid")+"' or bis.id='"+mapData.get("fid")+"') "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND bis.m1 like'%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("tname")!=null&&mapData.get("tname")!=""){
			content = content +" AND c.M1 like '%"+mapData.get("tname")+"%' "; 
		}
		if(mapData.get("year")!=null&&mapData.get("year")!=""){
			content = content +" AND b.m1 = '"+mapData.get("year")+"' "; 
		}
		if(mapData.get("quarter")!=null&&mapData.get("quarter")!=""){
			content = content +" AND a.quarter="+mapData.get("quarter"); 
		}
		if(mapData.get("deptid")!=null&&mapData.get("deptid")!=""){
			content = content +" AND b.id3 = "+mapData.get("deptid"); 
		}
		if(mapData.get("deptname")!=null&&mapData.get("deptname")!=""){
			content = content +" AND b.m1 like '%"+mapData.get("deptname")+"%'"; 
		}
		if(mapData.get("db")!=null&&mapData.get("db")!=""){
			content = content +" AND a.m4= '"+mapData.get("db")+"' "; 
		}
		return content;
		
	}
    
    /**
     * 初始化数据
     * @param id
     */
    public void init(long qyid,String year,String quarter) {
    	String sql="{Call init_target_selfassessments(:p1,:p2,:p3)}";
    	Parameter  parameter=new Parameter(qyid,year,quarter);
		updateBySql(sql,parameter);
    }
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" delete from target_evaluation  WHERE ID="+id;
		updateBySql(sql);
	}
    //修改
    public void updateInfo(Target_Evaluation entity) {
    	String sql=" update target_evaluation set s2='"+entity.getS2()+"', m2='"+entity.getM2()+"',m4="+entity.getM4()+",m3='"+entity.getM3()+"' WHERE ID="+entity.getID();
    	updateBySql(sql);
    }
    /**
     * 查找
     * @param id
     */
    public Target_Evaluation findInfoById1(Long id) {
    	String sql=" select  * from target_evaluation WHERE ID1="+id;
    	List<Target_Evaluation> list=findBySql(sql, null,Target_Evaluation.class);
    	if(list.size()>0)
    		return list.get(0);
    	else
    		return null;
    }
    /**
     * 查找季度评分所需数据
     * @param id 目标分配id
     */
    public List<Map<String, Object>> findQuartById(Map<String, Object> mapData) {
    	String content=content(mapData);
    	if(mapData.get("quart")!=null&&mapData.get("quart")!=""){
			content = content +" AND (a.quarter="+mapData.get("quart")+" or a.quarter is null)"; 
		}
    	if(mapData.get("deptid")!=null&&mapData.get("deptid")!=""){
			content = content +" AND (b.id3 = "+mapData.get("deptid")+" or b.id3 is null)"; 
		}
    	String sql=" SELECT b.id, a.id zpid,a.m4 result,b.targetval,c.m1 tname from"
    			+ " target_distribute b LEFT JOIN target_basic c on c.id=b.id1"
    			+ " LEFT JOIN target_evaluation a on b.id=a.id2 and(a.quarter ="+mapData.get("quart")+") left join bis_enterprise bis on bis.id=b.id2"
    			+ " where b.s3=0 and (a.s3=0 or a. s3 is null)"+content;
    	List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return  list;
    }
 

}
