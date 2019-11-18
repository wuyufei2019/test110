package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.AQZF_Plan_EnterpriseEntity;
import com.cczu.model.entity.AQZF_SafetyCheckSchemeEntity;
import com.cczu.util.dao.BaseDao;

@Repository("AqzfJcjhQyDao")
public class AqzfJcjhQyDao extends BaseDao<AQZF_Plan_EnterpriseEntity, Long>{

	public AQZF_Plan_EnterpriseEntity findInfoById(long id) {	
		AQZF_Plan_EnterpriseEntity a = find(id);
		flush();
		clear();
		return a;
	}

	public void addInfo(AQZF_Plan_EnterpriseEntity pe) {
		save(pe);
	}
	
	public List<Map<String,Object>> selectbyid1(long id1){
		String sql="SELECT b.id,b.m1 from aqzf_plan_enterprise a LEFT JOIN bis_enterprise b ON b.id = a.id2 where a.id1 = "+id1;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
	public List<AQZF_Plan_EnterpriseEntity> selectList(long id1){
		String sql="SELECT * from aqzf_plan_enterprise where id1="+id1;
		List<AQZF_Plan_EnterpriseEntity> list=findBySql(sql, null,AQZF_Plan_EnterpriseEntity.class); 
		return list;
	}

	public AQZF_Plan_EnterpriseEntity find1(AQZF_SafetyCheckSchemeEntity jcfa){
		String sql="SELECT * from aqzf_plan_enterprise where id1="+jcfa.getID1()+" and id2= "+jcfa.getID2();
		List<AQZF_Plan_EnterpriseEntity> list=findBySql(sql, null,AQZF_Plan_EnterpriseEntity.class); 
		return list.get(0);
	}
	
	public void deleteex(AQZF_Plan_EnterpriseEntity pe){
		delete(pe);
		flush();
		clear();
	}
	
}
