package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IErmYjczjsDao;
import com.cczu.model.entity.ERM_EmergencyDispTechnologyEntity;
import com.cczu.util.dao.BaseDao;

@Repository("ErmYjczjsDao")
public class ErmYjczjsDaoImpl extends BaseDao<ERM_EmergencyDispTechnologyEntity,Long> implements IErmYjczjsDao {

	
	@Override
	public List<ERM_EmergencyDispTechnologyEntity> findAllInfo() {
		String sql ="SELECT * FROM erm_emergencydisptechnology WHERE s3=0";
		List<ERM_EmergencyDispTechnologyEntity> list=findBySql(sql, null,ERM_EmergencyDispTechnologyEntity.class);
		return list;
		
	}

	@Override
	public void addInfo(ERM_EmergencyDispTechnologyEntity erm) {
		String sql = "INSERT INTO erm_emergencydisptechnology (S1,S2,S3,M1,M2,M3,ID1)"
				+ " VALUES ("+"getdate() ,getdate(),0,'"+erm.getM1()+"','"+erm.getM2()+"','"+erm.getM3()+"','"+erm.getID1()+"'  ) ";
		updateBySql(sql);
	}

	@Override
	public void updateInfo(ERM_EmergencyDispTechnologyEntity erm) {
		String sql=" UPDATE erm_emergencydisptechnology SET "
				+ " S2=getdate(),M1='"+erm.getM1()+"',M2 ='"+erm.getM2()+"',M3 ='"+erm.getM3()
				+"', ID1 ='"+erm.getID1()+"' WHERE id="+erm.getID()+" ";
		updateBySql(sql);
	}

	@Override
	public List<ERM_EmergencyDispTechnologyEntity> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,* FROM erm_emergencydisptechnology WHERE s3=0 " + content + " ) "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<ERM_EmergencyDispTechnologyEntity> list=findBySql(sql, null,ERM_EmergencyDispTechnologyEntity.class);
		
		return list;
	}

	

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM erm_emergencydisptechnology WHERE s3=0 "+ content;
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
		if(mapData.get("hxpname")!=null&&mapData.get("hxpname")!=""){
			content = content +"AND M1 LIKE'%"+mapData.get("hxpname")+"%'"; 
		}
		if(mapData.get("userid")!=null && mapData.get("userid")!=""){
			content = content +"AND ID1 ="+ mapData.get("userid"); 
		}
		
		return content;
	}

	@Override
	public void deleteInfo(Long id) {
		String sql=" UPDATE erm_emergencydisptechnology SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public ERM_EmergencyDispTechnologyEntity findById(Long id) {
		String sql ="SELECT * FROM erm_emergencydisptechnology WHERE s3=0 AND ID="+id;
		List<ERM_EmergencyDispTechnologyEntity> list=findBySql(sql, null,ERM_EmergencyDispTechnologyEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<ERM_EmergencyDispTechnologyEntity> getExcel(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT * FROM erm_emergencydisptechnology WHERE s3=0 " + content;
		List<ERM_EmergencyDispTechnologyEntity> list=findBySql(sql, null,ERM_EmergencyDispTechnologyEntity.class);
		return list;
		
	}
	
	@Override
	public List<Map<String, Object>> findInfoForApp(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,* FROM erm_emergencydisptechnology WHERE s3=0 " + content
				+ ")as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
		
	}
}
