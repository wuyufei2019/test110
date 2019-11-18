package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.ERM_ExercisePlanEntity;
import com.cczu.util.dao.BaseDao;

@Repository("ErmYljhDao")
public class ErmYljhDao extends BaseDao<ERM_ExercisePlanEntity,Long>  {
	
	public List<ERM_ExercisePlanEntity> findAllInfo() {
		String sql ="SELECT * FROM erm_exerciseplan WHERE s3=0";
		List<ERM_ExercisePlanEntity> list=findBySql(sql, null,ERM_ExercisePlanEntity.class);
		return list;
		
	}

	public void addInfo(ERM_ExercisePlanEntity erm) {
		save(erm);
	}
	
	public void updateInfo(ERM_ExercisePlanEntity erm) {
		save(erm);
	}

	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.qyid,a.id) AS RowNumber,b.m1 qynm,a.* "
				+ " FROM erm_exerciseplan a "
				+ " left join bis_enterprise b on a.qyid=b.id "
				+ " WHERE b.s3=0 and a.s3=0 and a.qyid is not null " + content + " ) "
				+ " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}

	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM erm_exerciseplan a"
				+ " left join bis_enterprise b on a.qyid=b.id "
				+ " WHERE a.s3=0 and b.s3=0 and a.qyid is not null " + content;
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
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content +" AND a.M2 ='"+mapData.get("m2")+"' "; 
		}
		if(mapData.get("m3")!=null&&mapData.get("m3")!=""){
			content = content +" AND a.M3 ='"+mapData.get("m3")+"' "; 
		}
		if(mapData.get("qyid")!=null && mapData.get("qyid")!=""){
			content = content +" AND a.qyid ="+ mapData.get("qyid")+" "; 
		}
		if(mapData.get("qynm")!=null && mapData.get("qynm")!=""){
			content = content +" AND b.M1 LIKE'%"+mapData.get("qynm")+"%' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + " AND ( b.fid='"+mapData.get("fid")+"' or b.id="+mapData.get("fid")+") "; 
		}		
		return content;
	}
    
    /**
     * 查询条件
     * @param mapData
     * @return
     */
    public String content2(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content +"AND M2 ="+mapData.get("m2")+"%'"; 
		}
		if(mapData.get("m3")!=null&&mapData.get("m3")!=""){
			content = content +"AND M3 ="+mapData.get("m3")+"%'"; 
		}
		if(mapData.get("qyid")!=null && mapData.get("qyid")!=""){
			content = content +"AND qyid ="+ mapData.get("qyid"); 
		}
		return content;
	}

	public void deleteInfo(Long id) {
		String sql=" UPDATE erm_exerciseplan SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}
	
	public ERM_ExercisePlanEntity findById(Long id) {
		String sql ="SELECT * FROM erm_exerciseplan WHERE s3=0 AND ID="+id;
		List<ERM_ExercisePlanEntity> list=findBySql(sql, null,ERM_ExercisePlanEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT b.m1 qynm,a.* "
		+ " FROM erm_exerciseplan a "
		+ " left join bis_enterprise b on a.qyid=b.id "
		+ " WHERE b.s3=0 and a.s3=0 and a.qyid is not null " + content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	public List<Map<String, Object>> findInfoForApp(Map<String, Object> mapData) {
		String content=content2(mapData);
		String sql ="SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber, id,m1,m2"
				+ ",m3,m4,m5,m6,m7,m8,m9,m10,m11,m12,m13 FROM erm_exerciseplan WHERE s3=0 " + content
				+ ")as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
		
	}
	
}
