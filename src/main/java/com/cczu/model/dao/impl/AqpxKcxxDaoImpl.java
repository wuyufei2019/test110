package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IAqpxKcxxDao;
import com.cczu.model.entity.AQPX_CourseEntity;
import com.cczu.util.dao.BaseDao;

@Repository("AqpxKcxxDao")
public class AqpxKcxxDaoImpl extends BaseDao<AQPX_CourseEntity, Long> implements IAqpxKcxxDao {

	@Override
	public List<AQPX_CourseEntity> findAllInfoByQyID(Long qyid) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM aqpx_course WHERE s3=0 AND id1 ="+qyid;
		List<AQPX_CourseEntity> list=findBySql(sql, null,AQPX_CourseEntity.class);
		return list;
	}

	@Override
	public void deleteInfo(Long id) {
		// TODO Auto-generated method stub
		String sql ="UPDATE aqpx_course SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public List<AQPX_CourseEntity> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY m5 asc, id desc ");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,* FROM aqpx_course WHERE S3=0 "+content +") "
				+ "as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		List<AQPX_CourseEntity> list=findBySql(sql, null,AQPX_CourseEntity.class);
		return list;
	}
	
	@Override
	public List<AQPX_CourseEntity> findByName(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql =" SELECT * from  aqpx_course a where 1=1 and a.s3=0 "+content;
		List<AQPX_CourseEntity> list=findBySql(sql, null,AQPX_CourseEntity.class);
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM aqpx_course WHERE s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public String content(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content="";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND ID1 ="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND M1 LIKE'%"+mapData.get("m1")+"%'"; 
		}
		if(mapData.get("kcname")!=null&&mapData.get("kcname")!=""){
			content = content +" AND M1 ='"+mapData.get("kcname")+"'"; 
		}
		if(mapData.get("type")!=null&&mapData.get("type")!=""){
			content = content +" AND M5 ='"+mapData.get("type")+"'"; 
		}
		if(mapData.get("dep")!=null&&mapData.get("dep")!=""){
			content = content +" AND ','+ID2+',' like ',%"+mapData.get("dep")+",%'";
		}		
		return content;
	}

	@Override
	public List<Object> getExcel(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql=" SELECT M1,M2,M3 FROM aqpx_course  WHERE S3=0 "+ content ;
		List<Object> list=findBySql(sql);
		return list;
	}

	@Override
	public List<AQPX_CourseEntity> getList() {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM aqpx_course WHERE S3=0";
		List<AQPX_CourseEntity> list = findBySql(sql, null,AQPX_CourseEntity.class);
		return list;
	}

	@Override
	public List<AQPX_CourseEntity> getkcid(Long kcid) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM aqpx_course WHERE s3=0 AND ID ="+kcid;
		List<AQPX_CourseEntity> list=findBySql(sql, null,AQPX_CourseEntity.class);
		return list;
	}


	@Override
	public AQPX_CourseEntity findbyid(Long id) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM aqpx_course a WHERE s3=0 AND id ="+id;
		List<AQPX_CourseEntity> list=findBySql(sql, null,AQPX_CourseEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public AQPX_CourseEntity findygidandkcid(Long kcid) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM aqpx_course WHERE s3=0 AND id="+kcid;
		List<AQPX_CourseEntity> list=findBySql(sql, null,AQPX_CourseEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public AQPX_CourseEntity findid(Long id1, String m1) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM aqpx_course WHERE s3=0 AND id1 ="+id1+" AND M1 ='"+m1+"'";
		List<AQPX_CourseEntity> list=findBySql(sql, null,AQPX_CourseEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<AQPX_CourseEntity> getlistByKcids(String kcids) {
		String sql ="SELECT * FROM aqpx_course WHERE s3=0 AND ID IN ("+kcids+")";
		List<AQPX_CourseEntity> list = findBySql(sql, null,AQPX_CourseEntity.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> getlistByKcids2(String kcids,String planname,Long planid) {
		String sql ="SELECT id,m1+'-----"+planname+"' kcname,"+planid+" planid FROM aqpx_course WHERE s3=0 AND ID IN ("+kcids+")";
		List<Map<String, Object>> list = findBySql(sql, null,Map.class);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getKclist(Long qyid, Long bmid) {
		// TODO Auto-generated method stub
		String sql ="select a.id planid, a.m1 jh,a.m6,a.m5,b.id,b.m1,b.m4 from aqpx_plan a, aqpx_course b where a.s3=0 and b.s3=0 and ','+a.id2+',' like '%,'+cast(b.id as varchar(20))+',%' AND ','+a.ID3+',' like '%,"+bmid+",%' and a.id1="+qyid+" AND (getdate() between a.m5 AND a.m6) order by a.id desc";
		List<Map<String, Object>> list = findBySql(sql, null,Map.class);
		return list;
	}

	@Override
	public List<AQPX_CourseEntity> findByContent(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT * FROM aqpx_course WHERE s3=0 "+content;
		List<AQPX_CourseEntity> list=findBySql(sql, null,AQPX_CourseEntity.class);
		return list;
	}
	
	@Override
	public List<AQPX_CourseEntity> findByContentForApp(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT a.m1 AS kcname , ISNULL(convert(varchar, b.examcount), '请联系管理员设置出题规则') AS examcount"
				+ " FROM aqpx_course a LEFT JOIN ( SELECT a.*, b.m1 + b.m2 + b.m3 + b.m4 AS examcount FROM aqpx_course a LEFT JOIN aqpx_testguize b ON a.id = b.id2 WHERE a.s3 = 0 AND b.s3 = 0 )"
				+ " b ON a.id = b.id where 1=1 "+content;
		List<AQPX_CourseEntity> list=findBySql(sql, null,AQPX_CourseEntity.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> getlistByKcids2ForApp(String kcids,String planname,Long planid,String starttime,String endtime) {
		String sql ="SELECT a.id,a.m1+'-----"+planname+"' kcname,"+planid+" planid,"+starttime+" starttime,"+endtime+" endtime,(b.m1+b.m2+b.m3+b.m4) examcount,"
				+ " FROM aqpx_course a WHERE a.s3=0 AND b.s3=0 a.ID IN ("+kcids+")";
		List<Map<String, Object>> list = findBySql(sql, null,Map.class);
		return list;
	}
}
