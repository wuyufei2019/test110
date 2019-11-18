package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.ERM_ExerciseRecordEntity;
import com.cczu.util.dao.BaseDao;

@Repository("ErmYljlDao")
public class ErmYljlDao extends BaseDao<ERM_ExerciseRecordEntity,Long>  {
	
	public List<ERM_ExerciseRecordEntity> findAllInfo() {
		String sql ="SELECT * FROM erm_exerciserecord WHERE s3=0";
		List<ERM_ExerciseRecordEntity> list=findBySql(sql, null,ERM_ExerciseRecordEntity.class);
		return list;
		
	}

	public void addInfo(ERM_ExerciseRecordEntity erm) {
		save(erm);
	}
	
	public void updateInfo(ERM_ExerciseRecordEntity erm) {
		save(erm);
	}

	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.qyid,a.id) AS RowNumber,b.m1 qynm,a.* "
				+ " FROM erm_exerciserecord a "
				+ " left join bis_enterprise b on a.qyid=b.id "
				+ " WHERE b.s3=0 and a.s3=0 and a.qyid is not null " + content + " ) "
				+ " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}

	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM erm_exerciserecord a"
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
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +"AND a.M1 ='"+mapData.get("m1")+"' "; 
		}
		if(mapData.get("depart")!=null&&mapData.get("depart")!=""){
			content = content +"AND a.M3 ='"+mapData.get("depart")+"' "; 
		}
		if(mapData.get("qyid")!=null && mapData.get("qyid")!=""){
			content = content +"AND a.qyid ="+ mapData.get("qyid"); 
		}
		if(mapData.get("qynm")!=null && mapData.get("qynm")!=""){
			content = content +"AND b.M1 LIKE'%"+mapData.get("qynm")+"%' "; 
		}
		
		/*安全台账条件*/
		if(mapData.get("aqtzstarttime")!=null&&mapData.get("aqtzstarttime")!=""){
			content = content +" AND CONVERT(varchar(100), a.M1, 23) >='"+mapData.get("aqtzstarttime")+"' "; 
		}
		if(mapData.get("aqtzfinishtime")!=null&&mapData.get("aqtzfinishtime")!=""){
			content = content +" AND CONVERT(varchar(100), a.M1, 23) <='"+mapData.get("aqtzfinishtime")+"' "; 
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
		if(mapData.get("dwname")!=null&&mapData.get("dwname")!=""){
			content = content +"AND M1 LIKE'%"+mapData.get("dwname")+"%'"; 
		}
		if(mapData.get("depart")!=null&&mapData.get("depart")!=""){
			content = content +"AND M3 ='"+mapData.get("depart")+"'"; 
		}
		if(mapData.get("qyid")!=null && mapData.get("qyid")!=""){
			content = content +"AND qyid ="+ mapData.get("qyid"); 
		}
		return content;
	}

	public void deleteInfo(Long id) {
		String sql=" UPDATE erm_exerciserecord SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}
	
	public void deleteInfoById1(Long id1) {
		String sql=" UPDATE erm_exerciserecord SET S3=1 WHERE ID1="+id1;
		updateBySql(sql);
	}
	
	public ERM_ExerciseRecordEntity findById(Long id) {
		String sql ="SELECT * FROM erm_exerciserecord WHERE s3=0 AND ID="+id;
		List<ERM_ExerciseRecordEntity> list=findBySql(sql, null,ERM_ExerciseRecordEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT b.m1 qynm,a.*,CONVERT(varchar(100), a.M1, 23)ylsj "
		+ " FROM erm_exerciserecord a "
		+ " left join bis_enterprise b on a.qyid=b.id "
		+ " WHERE b.s3=0 and a.s3=0 and a.qyid is not null " + content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	public List<Map<String, Object>> findInfoForApp(Map<String, Object> mapData) {
		String content=content2(mapData);
		String sql ="SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber, id,m1,m2"
				+ ",m3,m4,m5,m6,m7,m8,m9,m10,m11,m12,m13 FROM erm_exerciserecord WHERE s3=0 " + content
				+ ")as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
		
	}
	
}
