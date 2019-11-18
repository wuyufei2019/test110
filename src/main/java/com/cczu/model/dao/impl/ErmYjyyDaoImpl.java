package com.cczu.model.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IErmYjyyDao;
import com.cczu.model.entity.ERM_EmergencyHospitalEntity;
import com.cczu.util.dao.BaseDao;

@Repository("ErmYjyyDao")
public class ErmYjyyDaoImpl extends BaseDao<ERM_EmergencyHospitalEntity,Long> implements IErmYjyyDao {

	
	@Override
	public List<ERM_EmergencyHospitalEntity> findAllInfo() {
		String sql ="SELECT * FROM erm_emergencyhospital WHERE s3=0";
		List<ERM_EmergencyHospitalEntity> list=findBySql(sql, null,ERM_EmergencyHospitalEntity.class);
		return list;
		
	}

	@Override
	public void addInfo(ERM_EmergencyHospitalEntity erm) {
		save(erm);
	}

	@Override
	public void updateInfo(ERM_EmergencyHospitalEntity erm) {
		save(erm);
	}

	@Override
	public List<ERM_EmergencyHospitalEntity> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,* FROM erm_emergencyhospital WHERE s3=0 " + content + " ) "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<ERM_EmergencyHospitalEntity> list=findBySql(sql, null,ERM_EmergencyHospitalEntity.class);
		
		return list;
	}

	

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM erm_emergencyhospital WHERE s3=0 "+ content;
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
		if(mapData.get("yyname")!=null&&mapData.get("yyname")!=""){
			content = content +"AND M1 LIKE'%"+mapData.get("yyname")+"%'"; 
		}
		return content;
	}

	@Override
	public void deleteInfo(Long id) {
		String sql=" UPDATE erm_emergencyhospital SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public ERM_EmergencyHospitalEntity findById(Long id) {
		String sql ="SELECT * FROM erm_emergencyhospital WHERE s3=0 AND ID="+id;
		List<ERM_EmergencyHospitalEntity> list=findBySql(sql, null,ERM_EmergencyHospitalEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT m1,m2 "
		+ ",m3,m4,m5,m6,m7,m8,m9,m10,m11,m12 FROM erm_emergencyhospital WHERE s3=0 " + content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
		
	}
	
	@Override
	public List<Map<String, Object>> findInfoForApp(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber, id,m1,m2 "
				+ ",m3,m4,m5,m6,m7,m8,m9,m10,m11,m12 FROM erm_emergencyhospital WHERE s3=0 " + content
				+ ")as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
		
	}
	
	@Override
	public List<Map<String, Object>> findMapList(Map<String, Object> mapdata) {
		String content=content(mapdata);
		String sql=" SELECT  * FROM  erm_emergencyhospital WHERE S3=0 "+content ;
		List<ERM_EmergencyHospitalEntity> list=findBySql(sql, null,ERM_EmergencyHospitalEntity.class);
		
		List<Map<String,Object>> maplist=new ArrayList<Map<String, Object>>();
		for (ERM_EmergencyHospitalEntity eme : list) {
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("id", eme.getID());
				map.put("title", eme.getM1());
				map.put("address", eme.getM3());
				if(eme.getM11()!=null) map.put("pointx", eme.getM11());
				if(eme.getM12()!=null) map.put("pointy", eme.getM12());
				map.put("isOpen", 0);
				map.put("icon", "/static/model/images/ead/yjjc/i_yjyy.png");
				maplist.add(map);
			}
		return maplist;
	}
}
