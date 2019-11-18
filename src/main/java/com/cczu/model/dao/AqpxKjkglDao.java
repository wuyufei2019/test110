package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.AQPX_Courseware2Entity;
import com.cczu.util.dao.BaseDao;
/**
 * @description 培训设置-课件库管理DAO
 * @author jason
 */
@Repository("AqpxKjkglDao")
public class AqpxKjkglDao extends BaseDao<AQPX_Courseware2Entity, Long>{

	public  List<AQPX_Courseware2Entity>  findAllInfo(Long qyid) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM aqpx_courseware2 WHERE s3=0 AND ID1="+qyid;
		List<AQPX_Courseware2Entity> list = findBySql(sql, null,AQPX_Courseware2Entity.class);
		return list;
	}

	public void addInfo(AQPX_Courseware2Entity ac) {
		save(ac);
	}

	public void updateInfo(AQPX_Courseware2Entity ac) {

		String sql=" UPDATE aqpx_courseware2 SET "
				+ " S2=getdate(),M1='"+ac.getM1()+"',M2 ='"+ac.getM2()+"',M3="+ac.getM3()+" ";
		updateBySql(sql);
	}

	public void deleteInfo(Long id) {
		// TODO Auto-generated method stub
		String sql = "UPDATE aqpx_courseware2 SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	public List<AQPX_Courseware2Entity> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY S1 desc) AS RowNumber,* FROM aqpx_courseware2 WHERE S3=0 "+content+" ) "
				+ "as a WHERE   RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<AQPX_Courseware2Entity> list=findBySql(sql, null,AQPX_Courseware2Entity.class);
		return list;
	}

	public int getTotalCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM aqpx_courseware2 WHERE s3=0  "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

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

	public List<AQPX_Courseware2Entity> getExcel(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql=" SELECT * FROM aqpx_courseware2  WHERE S3=0 "+ content ;
		List<AQPX_Courseware2Entity> list=findBySql(sql, null,AQPX_Courseware2Entity.class);
		return list;
	}

	public List<AQPX_Courseware2Entity> getList() {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM aqpx_courseware2 WHERE S3=0";
		List<AQPX_Courseware2Entity> list = findBySql(sql, null,AQPX_Courseware2Entity.class);
		return list;
	}

	public List<AQPX_Courseware2Entity> getListKcid(Long kcid) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM aqpx_courseware2 WHERE s3=0 AND ID2="+kcid;
		List<AQPX_Courseware2Entity> list = findBySql(sql, null,AQPX_Courseware2Entity.class);
		return list;
	}

	public void deleteByKcid(Long kcid) {
		String sql = "delete from aqpx_courseware2 WHERE ID2="+kcid;
		updateBySql(sql);
	}

}
