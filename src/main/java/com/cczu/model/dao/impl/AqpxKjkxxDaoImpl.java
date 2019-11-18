package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IAqpxKjkxxDao;
import com.cczu.model.entity.AQPX_CoursewareEntity;
import com.cczu.util.dao.BaseDao;
@Repository("AqpxKjkxxDao")
public class AqpxKjkxxDaoImpl extends BaseDao<AQPX_CoursewareEntity, Long> implements IAqpxKjkxxDao {

	@Override
	public  List<AQPX_CoursewareEntity>  findAllInfo(Long qyid) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM aqpx_courseware WHERE s3=0 AND ID1="+qyid;
		List<AQPX_CoursewareEntity> list = findBySql(sql, null,AQPX_CoursewareEntity.class);
		return list;
	}

	@Override
	public void addInfo(AQPX_CoursewareEntity ac) {
		save(ac);
	}

	@Override
	public void updateInfo(AQPX_CoursewareEntity ac) {

		String sql=" UPDATE aqpx_courseware SET "
				+ " S2=getdate(),M1='"+ac.getM1()+"',M2 ='"+ac.getM2()+"',M3="+ac.getM3()+" ";
		updateBySql(sql);
	}

	@Override
	public void deleteInfo(Long id) {
		// TODO Auto-generated method stub
		String sql = "UPDATE aqpx_courseware SET S3=0 WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public List<AQPX_CoursewareEntity> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,* FROM aqpx_courseware WHERE S3=0 "+content+" ) "
				+ "as a WHERE   RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<AQPX_CoursewareEntity> list=findBySql(sql, null,AQPX_CoursewareEntity.class);
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM aqpx_courseware WHERE s3=0  "+ content;
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
		if(mapData.get("kcid")!=null&&mapData.get("kcid")!=""){
			content = content +" AND ID2 ="+mapData.get("kcid")+" "; 
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND M1 LIKE'%"+mapData.get("m1")+"%'"; 
		}
		return content;
	}

	@Override
	public List<AQPX_CoursewareEntity> getExcel(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql=" SELECT * FROM aqpx_courseware  WHERE S3=0 "+ content ;
		List<AQPX_CoursewareEntity> list=findBySql(sql, null,AQPX_CoursewareEntity.class);
		return list;
	}

	@Override
	public List<AQPX_CoursewareEntity> getList() {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM aqpx_courseware WHERE S3=0";
		List<AQPX_CoursewareEntity> list = findBySql(sql, null,AQPX_CoursewareEntity.class);
		return list;
	}

	@Override
	public List<AQPX_CoursewareEntity> getListKcid(Long kcid) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM aqpx_courseware WHERE s3=0 AND ID2="+kcid;
		List<AQPX_CoursewareEntity> list = findBySql(sql, null,AQPX_CoursewareEntity.class);
		return list;
	}

	@Override
	public AQPX_CoursewareEntity findByID(Long id) {
		String sql ="SELECT * FROM aqpx_courseware WHERE ID="+id;
		List<AQPX_CoursewareEntity> list = findBySql(sql, null,AQPX_CoursewareEntity.class);
		return list.get(0);
	}

	@Override
	public void deleteByKcid(Long kcid) {
		String sql = "delete from aqpx_courseware WHERE ID2="+kcid;
		updateBySql(sql);
	}

}
