package com.cczu.model.zyaqgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.zyaqgl.entity.AQGL_RelevantEvaluation_content;
import com.cczu.util.dao.BaseDao;

/**
 * 安全管理-相关方评定DAO
 * @author YZH
 *
 */
@Repository("AqglXgfpdDao")
public class AqglXgfpdDao extends BaseDao<AQGL_RelevantEvaluation_content, Long>{
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.*,b.m3 zcr,c.m2 pddw,d.m1 qyname FROM aqgl_relevantevaluation_content a "
				+ " left join aqgl_relevantevaluation b on a.id1=b.id "
				+ " left join aqgl_relatedunits c on b.id2=c.id "
				+ " left join bis_enterprise d on b.id1=d.id "
				+ " WHERE a.S3=0 and b.S3=0 and c.S3=0 and d.S3=0 "+content+" ) "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid2(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.*,b.m3 zcr,c.m2 pddw,d.m1 qyname,e.name pdr FROM aqgl_relevantevaluation_content a "
				+ " left join aqgl_relevantevaluation b on a.id1=b.id "
				+ " left join aqgl_relatedunits c on b.id2=c.id "
				+ " left join bis_enterprise d on b.id1=d.id "
				+ " left join t_user e on a.id2=e.id "
				+ " WHERE a.S3=0 and b.S3=0 and c.S3=0 and d.S3=0 "+content+" ) "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
	/**
	 * 根据id查询评定详细信息
	 * @return
	 */
	public Map<String, Object> findInforById(Long id) {
		String sql="SELECT a.*,b.m3 zcr,c.m2 pddw FROM aqgl_relevantevaluation_content a"
				+ " left join aqgl_relevantevaluation b on a.id1=b.id "
				+ " left join aqgl_relatedunits c on b.id2=c.id "
				+ " left join bis_enterprise d on b.id1=d.id "
				+ " WHERE a.S3=0 and b.S3=0 and c.S3=0 and d.S3=0 AND a.id="+id;
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
			content = content +" AND b.ID1 ='"+mapData.get("qyid")+"' "; 
		}
		if(mapData.get("xgfname")!=null&&mapData.get("xgfname")!=""){
			content = content +" AND c.M2 like'%"+mapData.get("xgfname")+"%' "; 
		}
		if(mapData.get("m12")!=null&&mapData.get("m12")!=""){
			content = content +" AND a.m12 ='"+mapData.get("m12")+"' "; 
		}
		if(mapData.get("pdryid")!=null&&mapData.get("pdryid")!=""){
			content = content +" AND a.ID2 ='"+mapData.get("pdryid")+"' "; 
		}
		if(mapData.get("ID1")!=null&&mapData.get("ID1")!=""){
			content = content +" AND a.ID1 ='"+mapData.get("ID1")+"' "; 
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
		String sql=" SELECT COUNT(*) sum  FROM aqgl_relevantevaluation_content a"
				+ " left join aqgl_relevantevaluation b on a.id1=b.id "
				+ " left join aqgl_relatedunits c on b.id2=c.id "
				+ " left join bis_enterprise d on b.id1=d.id "
				+ " WHERE a.S3=0 and b.S3=0 and c.S3=0 and d.S3=0 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" delete aqgl_relevantevaluation_content WHERE ID="+id;
		updateBySql(sql);
	}
    
    /**
     * 根据计划id和评定人员id删除
     * @param id
     */
    public void deleteInfor(Map<String, Object> mapData) {
		String sql=" UPDATE aqgl_relevantevaluation_content SET S3=1 WHERE ID1="+mapData.get("ID1")+" and ID2="+mapData.get("ID2");
		updateBySql(sql);
	}
}
