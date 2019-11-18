package com.cczu.model.mbgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.mbgl.entity.Target_Points;
import com.cczu.util.dao.BaseDao;


/**
 * 目标管理-积分管理DAO
 * @author YZH
 *
 */
@Repository("TargetPointDao")
public class TargetPointDao extends BaseDao<Target_Points, Long>{
	
	/**
	 * 分页查询overview
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> dataGridOverview(Map<String, Object> mapData) {
		String contemt=content2(mapData);
		String sql ="SELECT top "+mapData.get("pageSize")+" * FROM ( "
		 + "SELECT ROW_NUMBER() OVER (ORDER BY z.year desc,z.qyid,(yhpc+ssp+aqpx+jyxc+qt) desc) AS RowNumber,z.*,(yhpc+ssp+aqpx+jyxc+qt) zjf from(select a.*,u.name,"
		 + "( SELECT isnull(SUM(m2), 0) FROM target_points WHERE id1 = a.id1 AND id2 = a.id2 AND DATENAME(year, m4) = a.year AND m1 = 1 ) AS yhpc ,"
		 + "( SELECT isnull(SUM(m2), 0) FROM target_points WHERE id1 = a.id1 AND id2 = a.id2 AND DATENAME(year, m4) = a.year AND m1 = 2 ) AS ssp ,"
		 + "( SELECT isnull(SUM(m2), 0) FROM target_points WHERE id1 = a.id1 AND id2 = a.id2 AND DATENAME(year, m4) = a.year AND m1 = 3 ) AS aqpx ,"
		 + "( SELECT isnull(SUM(m2), 0) FROM target_points WHERE id1 = a.id1 AND id2 = a.id2 AND DATENAME(year, m4) = a.year AND m1 = 4 ) AS jyxc ,"
		 + "( SELECT isnull(SUM(m2), 0) FROM target_points WHERE id1 = a.id1 AND id2 = a.id2 AND DATENAME(year, m4) = a.year AND m1 = 5 ) AS qt"
		 + " FROM ( SELECT bis.id qyid,bis.m1 qyname , a.id1, a.id2, cast(DATENAME(year, a.m4) as varchar) AS year FROM target_points a left join bis_enterprise bis on bis.id=a.id1 WHERE bis.s3=0 and a.s3 = 0 "+contemt
		 + " GROUP BY bis.id,bis.m1, a.id1, a.id2, DATENAME(year, a.m4) ) a left join t_user u on u.id=a.id2 ) z)as a WHERE RowNumber > "
		 + mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=null;
		list = findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
     * 分页统计overview
     * @param mapData
     * @return
     */
    public int getTotalCountOverview(Map<String, Object> mapData) {
    	String sql=" select count(1) from (SELECT a.id2 from target_points a left join bis_enterprise bis on bis.id=a.id1 where bis.s3=0 and a.s3=0 "+content2(mapData)+"  group by bis.id ,a.id2,DATENAME(year, a.m4)) a";
    	List<Object> list=findBySql(sql);
    	return (int) list.get(0);
    }
	
	/**
	 * 查询条件
	 * @param mapData
	 * @return
	 */
	public String content2(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND bis.id ="+mapData.get("qyid"); 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND bis.m1 like'%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("uid")!=null&&mapData.get("uid")!=""){
			content = content +" AND a.id2 ="+mapData.get("uid"); 
		}
		if(mapData.get("year")!=null&&mapData.get("year")!=""){
			content = content +" AND  DATENAME(year, a.m4) ='"+mapData.get("year")+"'"; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( bis.fid='"+mapData.get("fid")+"' or bis.id='"+mapData.get("fid")+"') "; 
		}
		return content;
		
	}
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT top "+mapData.get("pageSize")+" * FROM ( "
				+ " SELECT ROW_NUMBER() OVER (ORDER BY bis.id desc,a.id desc) AS RowNumber,a.*,bis.m1 qyname,u.name FROM "
				+ " Target_Points a left join bis_enterprise bis on a.id1=bis.id left join t_user u on u.id=a.id2"
				+ " WHERE a.S3=0 and bis.S3=0 "
				+content+")as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>>list = findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
     * 分页统计
     * @param mapData
     * @return
     */
    public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM Target_Points a left join bis_enterprise bis on a.id1=bis.id left join t_user u on u.id=a.id2 "
				+ " WHERE a.S3=0 and bis.s3=0 "+ content;
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
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND a.M1 like '%"+mapData.get("m1")+"%' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND a.id1 ='"+mapData.get("qyid")+"' "; 
		}
		if(mapData.get("year")!=null&&mapData.get("year")!=""){
			content = content +" AND  DATENAME(year, a.m4) ='"+mapData.get("year")+"'"; 
		}
		if(mapData.get("uid")!=null&&mapData.get("uid")!=""){
    		content = content +" AND u.id ="+mapData.get("uid"); 
    	}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND bis.m1 like'%"+mapData.get("qyname")+"%' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( b.fid='"+mapData.get("fid")+"' or b.id='"+mapData.get("fid")+"') "; 
		}
		return content;
		
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE Target_Points SET S3=1 WHERE ID="+id;
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
		String sql=" SELECT a.m1,a.m2,a.m3,bis.m1 qyname FROM Target_Points a left join bis_enterprise bis "
				+ "on a.id1=bis.id WHERE a.S3=0 and bis.s3=0  "+ content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
}
