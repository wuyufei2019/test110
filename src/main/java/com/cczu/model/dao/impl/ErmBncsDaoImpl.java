package com.cczu.model.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IErmBncsDao;
import com.cczu.model.entity.ERM_EmergencyResPlaceEntity;
import com.cczu.util.dao.BaseDao;

@Repository("ErmBncsDao")
public class ErmBncsDaoImpl extends BaseDao<ERM_EmergencyResPlaceEntity,Long> implements IErmBncsDao {

	
	@Override
	public List<ERM_EmergencyResPlaceEntity> findAllInfo() {
		String sql ="SELECT * FROM erm_emergencyresplace WHERE s3=0";
		List<ERM_EmergencyResPlaceEntity> list=findBySql(sql, null,ERM_EmergencyResPlaceEntity.class);
		return list;
		
	}

	@Override
	public void addInfo(ERM_EmergencyResPlaceEntity erm) {
		save(erm);
	}

	@Override
	public void updateInfo(ERM_EmergencyResPlaceEntity erm) {
		save(erm);
	}

	@Override
	public List<ERM_EmergencyResPlaceEntity> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,* FROM erm_emergencyresplace WHERE s3=0 " + content + " ) "
				+ "as a WHERE s3=0 " + content + " AND RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<ERM_EmergencyResPlaceEntity> list=findBySql(sql, null,ERM_EmergencyResPlaceEntity.class);
		
		return list;
	}

	

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM erm_emergencyresplace WHERE s3=0 "+ content;
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
		if(mapData.get("csname")!=null &&mapData.get("csname")!=""){
			content = content +"AND M1 LIKE'%"+mapData.get("csname")+"%'"; 
		}
		if(mapData.get("cstype")!=null && mapData.get("cstype")!=""){
			content = content +"AND M2 LIKE'%"+mapData.get("cstype")+"%'"; 
		}
		if(mapData.get("qyid")!=null && mapData.get("qyid")!=""){
			content = content +"AND qyid ="+ mapData.get("qyid"); 
		}
		return content;
	}

	@Override
	public void deleteInfo(Long id) {
		String sql=" UPDATE erm_emergencyresplace SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public ERM_EmergencyResPlaceEntity findById(Long id) {
		String sql ="SELECT * FROM erm_emergencyresplace WHERE s3=0 AND ID="+id;
		List<ERM_EmergencyResPlaceEntity> list=findBySql(sql, null,ERM_EmergencyResPlaceEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11,m12,m13 FROM erm_emergencyresplace WHERE s3=0 " + content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> findInfoForApp(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber, id as id,m1,m2,"
				+ " m3,m4,m5,m6,m7,m8,m9,m10,m11,m12,m13 FROM erm_emergencyresplace WHERE s3=0 " + content
				+ ")as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> findMapList(Map<String, Object> mapdata) {
		String content=content(mapdata);
		String sql=" SELECT  * FROM  erm_emergencyresplace WHERE S3=0 "+content ;
		List<ERM_EmergencyResPlaceEntity> list=findBySql(sql, null,ERM_EmergencyResPlaceEntity.class);
		
		List<Map<String,Object>> maplist=new ArrayList<Map<String, Object>>();
		for (ERM_EmergencyResPlaceEntity erp : list) {
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("id", erp.getID());
				map.put("title", erp.getM1());
				map.put("address", erp.getM3());
				if(erp.getM12()!=null) map.put("pointx", erp.getM12());
				if(erp.getM13()!=null) map.put("pointy", erp.getM13());
				map.put("isOpen", 0);
				map.put("icon", "/static/model/images/ead/yjjc/i_bncs.png");
				maplist.add(map);
			}
		return maplist;
	}
//	private List<Map<String, Object>> setValue2Name(List<Map<String, Object>> list){
//		for(Map<String, Object> map : list){
//			StringBuffer m10 = new StringBuffer();
//			if(map.get("m10") != null){
//				String[] arrM10 = map.get("m10").toString().split(",");
//				for(int j=0;j<arrM10.length;j++){
//					if(j>0){
//						m10.append(",");
//					}
//					if ("1".equals(arrM10[j])){
//						m10.append("物体打击");
//					}else if ("2".equals(arrM10[j])){
//						m10.append("车辆伤害");
//					}else if ("3".equals(arrM10[j])){
//						m10.append("机械伤害");
//					}else if ("4".equals(arrM10[j])){
//						m10.append("起重伤害");
//					}else if ("5".equals(arrM10[j])){
//						m10.append("触电");
//					}else if ("6".equals(arrM10[j])){
//						m10.append("淹溺");
//					}else if ("7".equals(arrM10[j])){
//						m10.append("灼烫");
//					}else if ("8".equals(arrM10[j])){
//						m10.append("火灾");
//					}else if ("9".equals(arrM10[j])){
//						m10.append("高处坠落");
//					}else if ("10".equals(arrM10[j])){
//						m10.append("坍塌");
//					}else if ("11".equals(arrM10[j])){
//						m10.append("冒顶片帮");
//					}else if ("12".equals(arrM10[j])){
//						m10.append("透水");
//					}else if ("13".equals(arrM10[j])){
//						m10.append("放炮");
//					}else if ("14".equals(arrM10[j])){
//						m10.append("火药爆炸");
//					}else if ("15".equals(arrM10[j])){
//						m10.append("瓦斯爆炸");
//					}else if ("16".equals(arrM10[j])){
//						m10.append("锅炉爆炸");
//					}else if ("17".equals(arrM10[j])){
//						m10.append("容器爆炸");
//					}else if ("18".equals(arrM10[j])){
//						m10.append("其它爆炸");
//					}else if ("19".equals(arrM10[j])){
//						m10.append("中毒和窒息");
//					}else if ("20".equals(arrM10[j])){
//						m10.append("其它伤害");
//					}
//				}
//				map.put("m10", m10);
//			}
//		}
//		return list;
//	}
}
