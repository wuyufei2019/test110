package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IAqpxJhkcDao;
import com.cczu.model.entity.AQPX_PlancourseEntity;
import com.cczu.util.dao.BaseDao;

@Repository("AqpxJhkcDao")
public class AqpxJhkcDaoImpl extends BaseDao<AQPX_PlancourseEntity,Long> implements IAqpxJhkcDao {

	@Override
	public void addInfo(AQPX_PlancourseEntity ap) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO aqpx_plancourse (S1,S2,S3,ID1,ID2,ID3,ID4) "
				+ "VALUES (getdate() ,getdate(),0,"+ap.getID1()+","
						+ ""+ap.getID2()+","+ap.getID3()+","+ap.getID4()+" )";
		updateBySql(sql);
	}

	@Override
	public void updateInfo(AQPX_PlancourseEntity ap) {
		// TODO Auto-generated method stub
		String sql =" UPDATE aqpx_plancourse SET S2=getdate(),"
				+ "ID1="+ap.getID1()+",ID2="+ap.getID2()+" ";
		updateBySql(sql);
	}

	@Override
	public void deleteInfo(Long pxid) {
		// TODO Auto-generated method stub
		String sql ="UPDATE aqpx_plancourse SET S3=1 WHERE ID1="+pxid;
		updateBySql(sql);
	}

	@Override
	public AQPX_PlancourseEntity findAllInfo(Long ID) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM aqpx_plancourse WHERE s3=0 AND ID3 ="+ID;
		List<AQPX_PlancourseEntity> list=findBySql(sql, null,AQPX_PlancourseEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<AQPX_PlancourseEntity> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,* FROM aqpx_plancourse WHERE S3=0 "+content+" ) "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<AQPX_PlancourseEntity> list=findBySql(sql, null,AQPX_PlancourseEntity.class);
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM aqpx_plancourse WHERE s3=0  "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public String content(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content="";
		if(mapData.get("pxid")!=null&&mapData.get("pxid")!=""){
			content = content +" AND ID1 ="+mapData.get("pxid")+" "; 
		}
		if(mapData.get("kcid")!=null&&mapData.get("kcid")!=""){
			content = content +" AND ID2 ="+mapData.get("kcid")+" "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND ID3 ="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("ygid")!=null&&mapData.get("ygid")!=""){
			content = content +" AND ID4 ="+mapData.get("ygid")+" "; 
		}
		
		return content;
	}

	@Override
	public List<AQPX_PlancourseEntity> getExcel(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql=" SELECT * FROM aqpx_plancourse  WHERE S3=0 "+ content ;
		List<AQPX_PlancourseEntity> list=findBySql(sql, null,AQPX_PlancourseEntity.class);
		return list;
	}

	@Override
	public List<AQPX_PlancourseEntity> getList() {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM aqpx_plancourse WHERE S3=0";
		List<AQPX_PlancourseEntity> list = findBySql(sql, null,AQPX_PlancourseEntity.class);
		return list;
	}

	@Override
	public List<AQPX_PlancourseEntity> getlistpx(Long pxid) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM aqpx_plancourse WHERE S3=0 AND ID1="+pxid;
		List<AQPX_PlancourseEntity> list = findBySql(sql, null,AQPX_PlancourseEntity.class);
		return list;
	}

	@Override
	public List<AQPX_PlancourseEntity> getlistyg(Long ygid) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM aqpx_plancourse WHERE S3=0 AND ID4="+ygid;
		List<AQPX_PlancourseEntity> list = findBySql(sql, null,AQPX_PlancourseEntity.class);
		return list;
	}

	@Override
	public AQPX_PlancourseEntity findygid(Long ygid) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM aqpx_plancourse WHERE s3=0 AND ID4 ="+ygid;
		List<AQPX_PlancourseEntity> list=findBySql(sql, null,AQPX_PlancourseEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<AQPX_PlancourseEntity> findAllss(Long ygid) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM aqpx_plancourse WHERE s3=0 AND ID4 ="+ygid;
		List<AQPX_PlancourseEntity> list=findBySql(sql, null,AQPX_PlancourseEntity.class);
		return list;
	}

}
