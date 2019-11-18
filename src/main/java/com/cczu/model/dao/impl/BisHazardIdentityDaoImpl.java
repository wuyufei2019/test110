package com.cczu.model.dao.impl;

import com.cczu.model.dao.IBisHazardIdentityDao;
import com.cczu.model.entity.BIS_HazardIdentityEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("BisHazardIdentityDao")
public class BisHazardIdentityDaoImpl extends BaseDao<BIS_HazardIdentityEntity,Long> implements IBisHazardIdentityDao {

	@Override
	public BIS_HazardIdentityEntity findAll(Long wxid) {
		String sql = "SELECT * FROM bis_hazardidentity h WHERE h.s3=0 AND h.ID1="+wxid;
		List<BIS_HazardIdentityEntity> list=findBySql(sql, null, BIS_HazardIdentityEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public BIS_HazardIdentityEntity findById(Long id) {
		String sql = "SELECT * FROM bis_hazardidentity h WHERE h.s3=0 AND h.ID="+id;
		List<BIS_HazardIdentityEntity> list = findBySql(sql, null, BIS_HazardIdentityEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void addInfo(BIS_HazardIdentityEntity bis) {
		save(bis);
	}

	@Override
	public void updateInfo(BIS_HazardIdentityEntity bis) {
		save(bis);
	}

	@Override
	public void deleteInfo(Long id) {
		String sql = "UPDATE bis_hazardidentity SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public List<BIS_HazardIdentityEntity> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		List<BIS_HazardIdentityEntity> list = new ArrayList<>();
		if (content != null && content!=""){
			String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY h.id desc) AS RowNumber,h.* "
					+ " FROM bis_hazardidentity h  WHERE h.S3=0  "+content+" ) "
					+ "as a WHERE 0=0  AND RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
			list=findBySql(sql, null, BIS_HazardIdentityEntity.class);
		}
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM bis_hazardidentity h "
				+ "LEFT JOIN bis_enterprise bis on h.id1=bis.id  WHERE h.s3=0 AND 1=1"+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public String content(Map<String, Object> mapData) {
		String content="";
		if(mapData.get("hdyid")!=null&&mapData.get("hdyid")!=""){
			content = content +" AND h.ID1 ="+mapData.get("hdyid")+" "; 
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND bh.M1 ='"+mapData.get("m1")+"' ";
		}
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content +" AND bh.M2 ='"+mapData.get("m2")+"' ";
		}
		if(mapData.get("wuzhi")!=null&&mapData.get("wuzhi")!=""){
			content = content +" AND h.M2 like '%"+mapData.get("wuzhi")+"%' ";
		}
		if(mapData.get("wxydj")!=null&&mapData.get("wxydj")!=""){
			content = content +" AND h.M2 = "+mapData.get("wxydj")+" ";
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND bis.M1 like '%"+mapData.get("qyname")+"%' ";
		}
		
		return content;
	}

	@Override
	public int count(){
		String sql=" SELECT COUNT(*) sum  FROM bis_hazardidentity WHERE s3=0";
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public List<Map<String, Object>> Qzhi() {
		// TODO Auto-generated method stub
		String sql = "SELECT ID,M3,M4 FROM bis_hazardidentity h WHERE s3=0 ";
		List<Object> oblist=findBySql(sql);
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		
		for (int i = 0; i < oblist.size(); i++) {
			Object[] obj = (Object[])oblist.get(i);
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("ID", obj[0]);
			map.put("M3",obj[1]);
			map.put("M4",obj[2]);
			list.add(map);
		}
		System.out.println(list);
		return list;
	}

	@Override
	public List<Map<String,Object>> datafy(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" ID,S3,M1,M3,M4,M2 FROM ( SELECT ROW_NUMBER() OVER (ORDER BY bh.id) AS RowNumber,bh.ID,bh.S3,bh.M1,bh.M3,bh.M4,tbh.M2 FROM bis_hazardidentity bh,tdic_bis_hazardidentity tbh WHERE bh.M2=tbh.M4"
				+ " "+content+" ) "
				+ "as a WHERE s3=0  AND RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Object> oblist=findBySql(sql);
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
	
		for (int i = 0; i < oblist.size(); i++) {
			Object[] obj = (Object[])oblist.get(i);
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("ID", obj[0]);
			map.put("S3", obj[1]);
			map.put("M1", obj[2]);
			map.put("M3", obj[3]);
			map.put("M4", obj[4]);
			map.put("M2", obj[5]);
			list.add(map);
		}
		
		return list;
	}

	@Override
	public List<BIS_HazardIdentityEntity> findListHdid(Long id1) {
		String sql = "SELECT * FROM bis_hazardidentity WHERE s3=0 AND ID1="+id1;
		List<BIS_HazardIdentityEntity> list = findBySql(sql, null, BIS_HazardIdentityEntity.class);
		return list;
	}

	@Override
	public List<BIS_HazardIdentityEntity> getExport(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql=" SELECT * FROM bis_hazardidentity h WHERE S3=0 "+ content ;
		List<BIS_HazardIdentityEntity> list=findBySql(sql, null, BIS_HazardIdentityEntity.class);
		return list;
	}

	@Override
	public List<Map<String,Object>> dataGridApp(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY h.id desc) AS RowNumber,h.* "
				+ " FROM bis_hazardidentity h  WHERE h.S3=0  "+content+" ) "
				+ "as a WHERE 0=0  AND RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
}
