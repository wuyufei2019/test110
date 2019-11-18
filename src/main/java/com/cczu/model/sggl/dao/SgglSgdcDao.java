package com.cczu.model.sggl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.sggl.entity.SGGL_AccidentSurveyEntity;
import com.cczu.util.dao.BaseDao;

@Repository("SgglSgdcDao")
public class SgglSgdcDao extends BaseDao<SGGL_AccidentSurveyEntity,Long>  {
	
	public List<SGGL_AccidentSurveyEntity> findAllInfo() {
		String sql ="SELECT * FROM sggl_accidentsurvey WHERE s3=0";
		List<SGGL_AccidentSurveyEntity> list=findBySql(sql, null,SGGL_AccidentSurveyEntity.class);
		return list;
		
	}

	public void addInfo(SGGL_AccidentSurveyEntity erm) {
		save(erm);
	}
	
	public void updateInfo(SGGL_AccidentSurveyEntity erm) {
		save(erm);
	}

	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.qyid,a.id) AS RowNumber,b.m1 qynm,a.* "
				+ " FROM sggl_accidentsurvey a "
				+ " left join bis_enterprise b on a.qyid=b.id "
				+ " WHERE b.s3=0 and a.s3=0 and a.qyid is not null " + content + " ) "
				+ " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}

	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM sggl_accidentsurvey a"
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
		if(mapData.get("dwname")!=null&&mapData.get("dwname")!=""){
			content = content +"AND a.M1 LIKE'%"+mapData.get("dwname")+"%'"; 
		}
		if(mapData.get("depart")!=null&&mapData.get("depart")!=""){
			content = content +"AND a.M3 ='"+mapData.get("depart")+"'"; 
		}
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content +"AND CONVERT(varchar(100), a.M2, 23) ='"+mapData.get("m2")+"' "; 
		}
		if(mapData.get("qyid")!=null && mapData.get("qyid")!=""){
			content = content +"AND a.qyid ="+ mapData.get("qyid"); 
		}
		if(mapData.get("qynm")!=null && mapData.get("qynm")!=""){
			content = content +"AND b.M1 LIKE'%"+mapData.get("qynm")+"%'"; 
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
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content +"AND CONVERT(varchar(100), M2, 23) ='"+mapData.get("m2")+"' "; 
		}
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content +"AND CONVERT(varchar(100), M2, 23) ='"+mapData.get("m2")+"' "; 
		}
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content +"AND CONVERT(varchar(100), M2, 23) ='"+mapData.get("m2")+"' "; 
		}
		if(mapData.get("qyid")!=null && mapData.get("qyid")!=""){
			content = content +"AND qyid ="+ mapData.get("qyid"); 
		}
		return content;
	}

	public void deleteInfo(Long id) {
		String sql=" UPDATE sggl_accidentsurvey SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}
	
	public SGGL_AccidentSurveyEntity findById(Long id) {
		String sql ="SELECT * FROM sggl_accidentsurvey WHERE s3=0 AND ID="+id;
		List<SGGL_AccidentSurveyEntity> list=findBySql(sql, null,SGGL_AccidentSurveyEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	//绑定巡检内容list
	public List<Map<String, Object>> swrydataGrid(Map<String, Object> mapData) {
		String sql  ="SELECT top  "+mapData.get("pageSize")+" * FROM ("
				+ "SELECT ROW_NUMBER() OVER (ORDER BY b.dangerlevel) AS RowNumber,a.id id,a.m1 name,a.m3 sex,DATEDIFF (day,a.m10,getDate()) age,a.m4 gw,a.m5 xl,a.m7 zw,b.m1 shcd FROM bis_employee a LEFT JOIN sggl_casualty b ON a.id = b.id2 where a.S3=0 and b.id1 = "+ mapData.get("sgid")+") "
				+ " as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	public int getswryTotalCount(Map<String, Object> mapData) {
		String sql="SELECT COUNT(*) FROM bis_employee a LEFT JOIN sggl_casualty b ON a.id = b.id2 where a.S3=0 and b.id1 ="+ mapData.get("sgid");
	    List<Object>list = findBySql(sql);
		return (int) list.get(0);
	}

	//删除绑定巡检内容
	public void deleteSwry(long id) {
		String sql=" delete sggl_casualty WHERE id="+id;
		updateBySql(sql);
	}

	//巡检内容list
	public List<Map<String, Object>> xjnralldataGrid(Map<String, Object> mapData) {
		String content = "";
		if(mapData.get("checktitle")!=null&&mapData.get("checktitle")!=""){
			content = content + "AND checktitle LIKE '%"+mapData.get("checktitle")+"%' "; 
		}
		if(mapData.get("yhjb")!=null&&mapData.get("yhjb")!=""){
			content = content + "AND dangerlevel = '"+mapData.get("yhjb")+"' "; 
		}
		String sql  ="SELECT top  "+mapData.get("pageSize")+" * FROM ("
				+ "SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,* FROM bis_employee "
				+ "WHERE (id3 = "+mapData.get("qyid")+") AND id not IN (SELECT id FROM yhpc_riskpoint_content WHERE id1 = '"+mapData.get("id1")+"') "+content+" ) "
				+ " as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT b.m1 qynm,a.* "
		+ " FROM sggl_accidentsurvey a "
		+ " left join bis_enterprise b on a.qyid=b.id "
		+ " WHERE b.s3=0 and a.s3=0 and a.qyid is not null " + content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	public List<Map<String, Object>> findInfoForApp(Map<String, Object> mapData) {
		String content=content2(mapData);
		String sql ="SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber, id,m1,m2"
				+ ",m3,m4,m5,m6,m7,m8,m9,m10,m11,m12,m13 FROM sggl_accidentsurvey WHERE s3=0 " + content
				+ ")as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
		
	}
	
}
