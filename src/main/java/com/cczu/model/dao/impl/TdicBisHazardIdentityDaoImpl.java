package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.ITdicBisHazardIdentityDao;
import com.cczu.model.entity.Tdic_BIS_HazardIdentityEntity;
import com.cczu.util.dao.BaseDao;

@Repository("ITdicBisHazardIdentityDao")
public class TdicBisHazardIdentityDaoImpl extends BaseDao<Tdic_BIS_HazardIdentityEntity,Long> implements ITdicBisHazardIdentityDao {

	@Override
	public List<Tdic_BIS_HazardIdentityEntity> dataList(String m1) {
		String sql=" select * from tdic_bis_hazardidentity where m1='"+m1+"' " ;
		List<Tdic_BIS_HazardIdentityEntity> list=findBySql(sql, null,Tdic_BIS_HazardIdentityEntity.class);
		return list;
	}

	@Override
	public Tdic_BIS_HazardIdentityEntity findAll(Long id) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM tdic_bis_hazardidentity WHERE ID="+id;
		List<Tdic_BIS_HazardIdentityEntity> list=findBySql(sql, null,Tdic_BIS_HazardIdentityEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Tdic_BIS_HazardIdentityEntity findBymap(Map<String, Object> map) {
		String sql=" select * from tdic_bis_hazardidentity where m1='"+map.get("m1")+"' and m2='"+map.get("m2")+"' " ;
		List<Tdic_BIS_HazardIdentityEntity> list=findBySql(sql, null,Tdic_BIS_HazardIdentityEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	public List<Map<String,Object>> findWzList(){
		String sql="SELECT id as id,m2 as text FROM tdic_bis_hazardidentity WHERE S3=0 and M2 is not null"; 
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	public List<Map<String,Object>> findWzlbList(){
		String sql="SELECT DISTINCT m1 as id,m1 as text FROM tdic_bis_hazardidentity WHERE S3=0 and M1 is not null"; 
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
	 * 根据化学品名称查询临界量
	 */
	@Override
	public Tdic_BIS_HazardIdentityEntity findM3ByM2(String m2) {
		String sql=" select * from tdic_bis_hazardidentity where m2 like '%"+m2+"%' ";
		List<Tdic_BIS_HazardIdentityEntity> list=findBySql(sql, null,Tdic_BIS_HazardIdentityEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 根据化学品类别查询分类说明
	 */
	@Override
	public List<Tdic_BIS_HazardIdentityEntity> flsmList2(String m1) {
		String sql=" select * from tdic_bis_hazardidentity where m1='"+m1+"' " ;
		List<Tdic_BIS_HazardIdentityEntity> list=findBySql(sql, null,Tdic_BIS_HazardIdentityEntity.class);
		return list;
	}
	/**
	 * 根据化学品类别和分类说明查询临界量
	 */
	@Override
	public Tdic_BIS_HazardIdentityEntity findBymap2(Map<String, Object> map) {
		String sql=" select * from tdic_bis_hazardidentity where m1='"+map.get("m1")+"' and m5='"+map.get("m5")+"' " ;
		List<Tdic_BIS_HazardIdentityEntity> list=findBySql(sql, null,Tdic_BIS_HazardIdentityEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
}
