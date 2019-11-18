package com.cczu.model.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IErmYjcbkDao;
import com.cczu.model.entity.ERM_EmergencyRepositoryEntity;
import com.cczu.util.dao.BaseDao;

@Repository("ErmYjcbkDao")
public class ErmYjcbkDaoImpl extends BaseDao<ERM_EmergencyRepositoryEntity,Long> implements IErmYjcbkDao {

	
	@Override
	public List<ERM_EmergencyRepositoryEntity> findAllInfo() {
		
		String sql ="SELECT * FROM erm_emergencyrepository WHERE s3=0";
		List<ERM_EmergencyRepositoryEntity> list=findBySql(sql, null,ERM_EmergencyRepositoryEntity.class);
		return list;
		
	}

	@Override
	public void addInfo(ERM_EmergencyRepositoryEntity erm) {
		save(erm);
	}

	@Override
	public void updateInfo(ERM_EmergencyRepositoryEntity erm) {
		save(erm);
	}

	@Override
	public List<ERM_EmergencyRepositoryEntity> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,* FROM erm_emergencyrepository WHERE s3=0 " + content + " ) "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<ERM_EmergencyRepositoryEntity> list=findBySql(sql, null,ERM_EmergencyRepositoryEntity.class);
		
		return list;
	}

	

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM erm_emergencyrepository WHERE s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	@Override
	public List<Map<String, Object>> findMapList(Map<String, Object> mapdata) {
		String content=content(mapdata);
		String sql=" SELECT  * FROM  erm_emergencyrepository WHERE S3=0 "+content ;
		List<ERM_EmergencyRepositoryEntity> list=findBySql(sql, null,ERM_EmergencyRepositoryEntity.class);
		
		List<Map<String,Object>> maplist=new ArrayList<Map<String, Object>>();
		for (ERM_EmergencyRepositoryEntity ere : list) {
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("id", ere.getID());
				map.put("title", ere.getM1());
				map.put("address", ere.getM2());
				if(ere.getM11()!=null) map.put("pointx", ere.getM11());
				if(ere.getM12()!=null) map.put("pointy", ere.getM12());
				map.put("isOpen", 0);
				map.put("icon", "/static/model/images/ead/yjjc/i_yjwz.png");
				maplist.add(map);
			}
		return maplist;
	}
    /**
     * 查询条件
     * @param mapData
     * @return
     */
    public String content(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("cbkname")!=null&&mapData.get("cbkname")!=""){
			content = content +"AND M1 LIKE'%"+mapData.get("cbkname")+"%'"; 
		}
		if(mapData.get("qyid")!=null && mapData.get("qyid")!=""){
			content = content +"AND qyid ="+ mapData.get("qyid"); 
		}
		return content;
	}

	@Override
	public void deleteInfo(Long id) {
		String sql=" UPDATE erm_emergencyrepository SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public ERM_EmergencyRepositoryEntity findById(Long id) {
		String sql ="SELECT * FROM erm_emergencyrepository WHERE s3=0 AND ID="+id;
		List<ERM_EmergencyRepositoryEntity> list=findBySql(sql, null,ERM_EmergencyRepositoryEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11,m12 FROM erm_emergencyrepository WHERE s3=0 " + content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return setValue2Name(list);
		
	}
	
	@Override
	public List<Map<String, Object>> findInfoForApp(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber, id,m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11,m12 FROM erm_emergencyrepository WHERE s3=0 " + content
				+ ")as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return setValue2Name(list);
	}
	private List<Map<String, Object>> setValue2Name(List<Map<String, Object>> list){
		for(Map<String, Object> map : list){
			StringBuffer ydlx = new StringBuffer();
			String[] arrYdlx = map.get("m9").toString().split(",");
			if(arrYdlx != null){
				for(int j=0;j<arrYdlx.length;j++){
					if(j>0){
						ydlx.append(",");
					}
					if ("1".equals(arrYdlx[j])){
						ydlx.append("物体打击");
					}else if ("2".equals(arrYdlx[j])){
						ydlx.append("车辆伤害");
					}else if ("3".equals(arrYdlx[j])){
						ydlx.append("机械伤害");
					}else if ("4".equals(arrYdlx[j])){
						ydlx.append("起重伤害");
					}else if ("5".equals(arrYdlx[j])){
						ydlx.append("触电");
					}else if ("6".equals(arrYdlx[j])){
						ydlx.append("淹溺");
					}else if ("7".equals(arrYdlx[j])){
						ydlx.append("灼烫");
					}else if ("8".equals(arrYdlx[j])){
						ydlx.append("火灾");
					}else if ("9".equals(arrYdlx[j])){
						ydlx.append("高处坠落");
					}else if ("10".equals(arrYdlx[j])){
						ydlx.append("坍塌");
					}else if ("11".equals(arrYdlx[j])){
						ydlx.append("冒顶片帮");
					}else if ("12".equals(arrYdlx[j])){
						ydlx.append("透水");
					}else if ("13".equals(arrYdlx[j])){
						ydlx.append("放炮");
					}else if ("14".equals(arrYdlx[j])){
						ydlx.append("火药爆炸");
					}else if ("15".equals(arrYdlx[j])){
						ydlx.append("瓦斯爆炸");
					}else if ("16".equals(arrYdlx[j])){
						ydlx.append("锅炉爆炸");
					}else if ("17".equals(arrYdlx[j])){
						ydlx.append("容器爆炸");
					}else if ("18".equals(arrYdlx[j])){
						ydlx.append("其它爆炸");
					}else if ("19".equals(arrYdlx[j])){
						ydlx.append("中毒和窒息");
					}else if ("20".equals(arrYdlx[j])){
						ydlx.append("其它伤害");
					}
				}
				map.put("m9", ydlx);
			}
		}
		return list;
	}
}
