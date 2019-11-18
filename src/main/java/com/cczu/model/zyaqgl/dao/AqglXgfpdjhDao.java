package com.cczu.model.zyaqgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.zyaqgl.entity.AQGL_RelevantEvaluation;
import com.cczu.util.dao.BaseDao;

/**
 * 安全管理-相关方评定计划DAO
 * @author YZH
 *
 */
@Repository("AqglXgfpdjhDao")
public class AqglXgfpdjhDao extends BaseDao<AQGL_RelevantEvaluation, Long>{
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM(SELECT ROW_NUMBER() OVER ( "+ordercont+") AS RowNumber,a.*, b.m2 pddw, c.m1 qyname FROM aqgl_relevantevaluation a"
				+ " left join aqgl_relatedunits b on a.id2=b.id"
				+ " left join bis_enterprise c on a.id1=c.id"
				+ " WHERE a.S3=0 and b.S3=0 and c.S3=0 "+content
				+ " ) as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
	/**
	 * 查询计划的平均成绩和等级
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> findcount(Long ID1) {
		String sql ="  select avg(m11) cj from aqgl_relevantevaluation_content a"
				+ " left join t_user b on a.id2=b.id where a.s3=0 and  a.m11!=0 and a.id1="+ID1;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list.get(0);
	}
	
	/**
	 * 相关方单位下拉框list
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> findXgfdwList(Map<String, Object> map) {
		String content=content(map);
		String sql="SELECT id,m2 text FROM aqgl_relatedunits a WHERE a.S3=0 and a.M2 is not null "+ content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	//评定人员
	public List<Map<String,Object>> getidname(long id1) {
		String sql = "SELECT b.ID,b.NAME,b.GENDER,b.PHONE FROM aqgl_relevantevaluation_content a LEFT JOIN t_user b ON a.id2 = b.ID "
				+ "WHERE a.S3=0 and a.id1 = "+id1;
		List<Map<String,Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	//根据id1删除评定内容表
	public void deletepdnrInfo(long id1) {
		String sql=" delete aqgl_relevantevaluation_content WHERE id1="+id1;
		updateBySql(sql);
	}
	
	/**
	 * 根据id查询评定计划详细信息
	 * @return
	 */
	public Map<String, Object> findInforById(Long id) {
		String sql="SELECT a.*,b.m1 pddw,c.m1 depart FROM aqgl_relevantevaluation a"
				+ " left join aqgl_relatedunits b on a.id2=b.id "
				+ " left join bis_enterprise c on a.id1=c.id "
				+ " WHERE a.S3=0 AND b.S3=0 and c.S3=0 AND a.id="+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list.get(0);
	}
	
	/**
     * 查询条件
     * @param mapData
     * @return
     */
    public String content(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND a.ID1 ='"+mapData.get("qyid")+"' "; 
		}
		if(mapData.get("xgfname")!=null&&mapData.get("xgfname")!=""){
			content = content +" AND b.M2 like'%"+mapData.get("xgfname")+"%' "; 
		}
		if(mapData.get("m5")!=null&&mapData.get("m5")!=""){
			content = content +" AND a.m5 ='"+mapData.get("m5")+"' "; 
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
		String sql=" SELECT COUNT(*) sum  FROM aqgl_relevantevaluation a"
				+ " left join aqgl_relatedunits b on a.id2=b.id "
				+ " left join bis_enterprise c on a.id1=c.id "
				+ " WHERE a.S3=0 and b.S3=0 and c.S3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" delete aqgl_relevantevaluation WHERE ID="+id;
		updateBySql(sql);
	}
}
