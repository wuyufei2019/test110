package com.cczu.model.mbgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.mbgl.entity.Target_Distribute;
import com.cczu.util.dao.BaseDao;


/**
 * 目标管理-目标分配DAO
 * @author YZH
 *
 */
@Repository("TargetMbfpDao")
public class TargetZbfpDao extends BaseDao<Target_Distribute, Long>{
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY bis.id desc,a.m1 desc,a.m3 desc,a.id3 ");
		if(mapData.get("orderBy").equals("deptname")){
			ordercont=" ORDER BY td.id "+mapData.get("order")+" ";
		}
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS "
				+ "RowNumber,a.*,b.m1 targetname,bis.m1 qynm,td.m1 deptname FROM target_distribute a left join target_basic b on "
				+ "a.id1=b.id left join bis_enterprise bis on a.id2=bis.id left join t_department td on a.id3= td.id "
				+ "WHERE a.S3=0 and b.s3=0 and bis.s3=0 "+ content+" )as a WHERE RowNumber > "
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
		if(mapData.get("id")!=null&&mapData.get("id")!=""){
			content = content +" AND a.id ='"+mapData.get("id")+"' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND bis.id ='"+mapData.get("qyid")+"' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + " AND ( bis.fid='"+mapData.get("fid")+"' or bis.id='"+mapData.get("fid")+"') "; 
		}
		if(mapData.get("tid")!=null&&mapData.get("tid")!=""){
			content = content +" AND a.id ='"+mapData.get("tid")+"' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND bis.m1 like'%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("deptname")!=null&&mapData.get("deptname")!=""){
			content = content +" AND td.m1 like'%"+mapData.get("deptname")+"%' "; 
		}
		if(mapData.get("id2")!=null&&mapData.get("id2")!=""){
			content = content +" AND a.id2 ='"+mapData.get("id2")+"' "; 
		}
		if(mapData.get("deptid")!=null&&mapData.get("deptid")!=""){
			content = content +" AND a.id3 ='"+mapData.get("deptid")+"' "; 
		}
		if(mapData.get("m3")!=null&&mapData.get("m3")!=""){
			content = content +" AND a.M3 ='"+mapData.get("m3")+"' "; 
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND a.M1 ='"+mapData.get("m1")+"' "; 
		}
		if(mapData.get("targetname")!=null&&mapData.get("targetname")!=""){
			content = content +" AND b.M1 like'%"+mapData.get("targetname")+"%' "; 
		}
		
		/*安全台账条件*/
		if(mapData.get("aqtzstartnf")!=null&&mapData.get("aqtzstartnf")!=""){
			content = content +" AND a.M1 >='"+mapData.get("aqtzstartnf")+"' "; 
		}
		if(mapData.get("aqtzfinishnf")!=null&&mapData.get("aqtzfinishnf")!=""){
			content = content +" AND a.M1 <='"+mapData.get("aqtzfinishnf")+"' "; 
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
		String sql="SELECT COUNT(a.id) sum FROM target_distribute a left join target_basic b on a.id1=b.id"
				+ " left join bis_enterprise bis on a.id2=bis.id  left join t_department td on a.id3= td.id"
				+ " WHERE a.S3=0 and b.s3=0 and bis.s3=0"+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE target_distribute SET S3=1 WHERE ID="+id;
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
		String sql=" SELECT bis.m1 qynm,a.m1,td.m1 deptname,"
				+ "case a.m3 when '1' then '公司' when '2' then '部门' when '3' then '班组' else '' end m3,"
				+ "CONVERT(varchar(100), a.m5, 23)m5,a.m6,a.m7,a.m8,a.m9,a.m11,a.m12,a.m13,a.url,a.targetval,b.m1 targetname FROM target_distribute a left join target_basic b "
				+ "on a.id1=b.id left join bis_enterprise bis on a.id2=bis.id left join t_department td on td.id=a.id3"
				+ " WHERE a.S3=0 and b.s3=0 and bis.s3=0 "+ content +" ORDER BY bis.id desc,a.m1 desc,a.m3 desc,a.id3";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
  
    
    /**
     * 获取指标名称和id
     * @return
     */
    public List<Map<String, Object>> getTargetDisIdJson(Map<String, Object> mapData) {
    	String content=content(mapData);
    	String sql=" SELECT a.id,a.m1+' '+td.m1+' '+c.m1 as text,a.m1 year,td.m1 department,c.m1 targetname,a.targetval "
    			+ "FROM target_distribute a left join target_basic c on a.id1=c.id left join bis_enterprise "
    			+ "bis on a.id2=bis.id left join t_department td on td.id= a.id3 WHERE a.S3=0 and bis.S3=0 and c.s3=0 "
    			+ content +" order by a.m1 desc,a.m3 desc";
    	List<Map<String, Object>> list=findBySql(sql, null,Map.class);
    	return list;
    }
    
    public List<Map<String, Object>> getView(Map<String, Object> map) {
    	String content=content(map);
    	String sql = "select a.*, b.m1 zbnm from target_distribute a left join target_basic b on a.id1=b.id left join t_department td on a.id3= td.id "+ 
					"where a.s3=0 and b.s3=0 " + content;
    	List<Map<String, Object>> list=findBySql(sql, null,Map.class);
    	return list;
    }
}
