package com.cczu.model.dao.impl;

import com.cczu.model.dao.IBisHazardDao;
import com.cczu.model.entity.BIS_HazardEntity;
import com.cczu.util.dao.BaseDao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("BisHazardDao")
public class BisHazardDaoImpl extends BaseDao<BIS_HazardEntity,Long> implements IBisHazardDao {

	@Override
	public void addInfo(BIS_HazardEntity bis) {
		// TODO Auto-generated method stub
		save(bis);
	}

	@Override
	public void updateInfo(BIS_HazardEntity bis) {
		save(bis);
	}

	@Override
	public BIS_HazardEntity findById(Long id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM bis_hazard WHERE s3=0 and ID="+id;
		List<BIS_HazardEntity> list=findBySql(sql, null, BIS_HazardEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public BIS_HazardEntity findQyId(Long qyid) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM bis_hazard WHERE s3=0 and ID1="+qyid;
		List<BIS_HazardEntity> list=findBySql(sql, null, BIS_HazardEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	@Override
	public Map<String,Object> findMapQyId(Long qyid) {
		String sql = "SELECT a.id1,a.m1,a.m2,a.m3,a.m4,a.m5,a.m6,a.m7,a.m8,a.m9,a.m9_1,a.m10,a.m11,b.label FROM bis_hazard a LEFT JOIN t_dict b ON a.m5=b.value WHERE a.s3=0 AND a.ID1="+qyid;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void deleteInfo(Long id) {
		// TODO Auto-generated method stub
		String sql = "UPDATE bis_hazard SET S3=1 WHERE ID="+id;
		String sql1 = "UPDATE bis_hazardidentity SET S3=1 WHERE ID1 in (select id from  bis_hazard where id1="+id+")";
		updateBySql(sql);
		updateBySql(sql1);
	}

	@Override
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql =" SELECT DISTINCT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY h.id desc) AS RowNumber,h.*,bis.m1 qyname,bis.id qyid"
				+ " FROM  bis_hazard h"
				+ " LEFT JOIN bis_enterprise bis on h.id1=bis.id "
				+ " LEFT JOIN bis_hazardidentity hd on hd.id1=h.id  "
				+ "WHERE bis.M1 is not null and h.S3=0 and bis.S3=0 "+content+" ) "
				+ "as a WHERE 0=0  AND RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql=" SELECT COUNT(DISTINCT h.id) sum  FROM bis_hazard h "
				+ "LEFT JOIN bis_enterprise bis on h.id1=bis.id "
				+ "LEFT JOIN bis_hazardidentity hd on hd.id1=h.id "
				+ " WHERE h.s3=0 AND 1=1"+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	public String content(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content="";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND ID1 ="+mapData.get("qyid")+" ";
		}
		if(mapData.get("qyid2")!=null&&mapData.get("qyid2")!=""){
			content = content +" AND h.ID1 ="+mapData.get("qyid2")+" "; 
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND M1 LIKE'%"+mapData.get("m1")+"%'";
		}
		if(mapData.get("wuzhi")!=null&&mapData.get("wuzhi")!=""){
			content = content +" AND hd.M2 like '%"+mapData.get("wuzhi")+"%' ";
		}
		if(mapData.get("wxydj")!=null&&mapData.get("wxydj")!=""){
			content = content +" AND h.M1 = '"+mapData.get("wxydj")+"'";
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND bis.M1 like '%"+mapData.get("qyname")+"%' ";
		}
		if (mapData.get("xzqy") != null && mapData.get("xzqy") != "") {
			content = content + " AND bis.id2 like'" + mapData.get("xzqy") + "%' ";
		}
		if (mapData.get("cjz") != null && mapData.get("cjz") != "") {
			content = content + " AND bis.cjz=" + mapData.get("cjz");
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND bis.m36='"+mapData.get("jglx")+"' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( bis.fid='"+mapData.get("fid")+"' or bis.id='"+mapData.get("fid")+"') "; 
		}
		return content;
	}

	@Override
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql=" SELECT (CASE h.M1 WHEN '1' THEN '一级' WHEN '2' THEN '二级' WHEN '3' THEN '三级' WHEN '4' THEN '四级' ELSE '' END)M1,h.M2,h.M3,(CASE h.M4 WHEN 1 THEN '否' WHEN 0 THEN '是' ELSE '' END)M4,h.M5,h.M6,h.M7,h.M8,h.M9,h.M10,h.M9_1,bis.m1 qyname,bis.id qyid FROM  bis_hazard h"
				+ " LEFT JOIN bis_enterprise bis on h.id1=bis.id "
				+ " LEFT JOIN bis_hazardidentity hd on hd.id1=h.id  "
				+ "WHERE bis.M1 is not null and h.S3=0 and bis.S3=0 "+content
				+ " ORDER BY h.id ";
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> findByQyID(Long qyid) {
		String sql=" SELECT (CASE h.m1 WHEN '1' THEN '一级' WHEN '2' THEN '二级' WHEN '3' THEN '三级' WHEN '4' THEN '四级' ELSE '' END)m1,h.m2,h.m3,(CASE h.m4 WHEN 1 THEN '否' WHEN 0 THEN '是' ELSE '' END)m4,h.m5,h.m6,h.m7,h.m8,h.m9,h.m10,h.m9_1,h.id,bis.m1 qyname,bis.id qyid FROM  bis_hazard h"
				+ " LEFT JOIN bis_enterprise bis on h.id1=bis.id "
				+ " LEFT JOIN bis_hazardidentity hd on hd.id1=h.id  "
				+ "WHERE bis.M1 is not null and h.S3=0 and bis.S3=0 and h.id1="+qyid+" ORDER BY h.id ";
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
	 * 查询最新流水号
	 */
	@Override
	public String FindWaterCode(Long qyid) {
		String sql="select * from bis_hazard a where ID1="+qyid+" ORDER BY a.id desc";
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		if(list.size()>0){
			if(list.get(0).get("watercode")!=null){
				return list.get(0).get("watercode").toString();
			}else{
				return "";
			}
			
		}
		return "";
	}

	
	/**
	 * 重大危险源编码Json
	 */
	@Override
	public List<Map<String,Object>> findhazardCode(Long qyid) {
		String sql="select a.hazardcode id,a.m12 text from bis_hazard a where a.s3=0 and ID1="+qyid+"";
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	
	
	
	
	
	
	
	
	
}
