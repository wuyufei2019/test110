package com.cczu.model.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.ITZybysDao;
import com.cczu.model.entity.Tdic_BIS_OccupharmExamEntity;
import com.cczu.util.dao.BaseDao;


@Repository("TZybysDao")
public class TZybysDaoImpl extends BaseDao<Tdic_BIS_OccupharmExamEntity,Long> implements ITZybysDao {

	@Override
	public List<Map<String, Object>> getlistm1() {
		// TODO Auto-generated method stub
		String sql = "select distinct m1 from tdic_bis_occupharmexam";
		List<Object> oblist = findBySql(sql);
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Object[] obj = (Object[])oblist.get(0);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("m1",obj[0]);
		list.add(map);
		
		return list;
	}

	@Override
	public List<Tdic_BIS_OccupharmExamEntity> getlistm2(String m1) {
		// TODO Auto-generated method stub
		String sql = "select distinct * from tdic_bis_occupharmexam where m1='"+m1+"'";
		List<Tdic_BIS_OccupharmExamEntity> list = findBySql(sql,null,Tdic_BIS_OccupharmExamEntity.class);
		return list;
	}

	@Override
	public Tdic_BIS_OccupharmExamEntity findliat(String m1) {
		// TODO Auto-generated method stub
		String sql = "select distinct * from tdic_bis_occupharmexam where m1='"+m1+"'";
		List<Tdic_BIS_OccupharmExamEntity> list = findBySql(sql,null,Tdic_BIS_OccupharmExamEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	/**
	 * 查询所有职业病危险因素名称
	 */
	@Override
	public List<Tdic_BIS_OccupharmExamEntity> getAll() {
		String sql = "select distinct * from tdic_bis_occupharmexam order by m1";
		List<Tdic_BIS_OccupharmExamEntity> list = findBySql(sql,null,Tdic_BIS_OccupharmExamEntity.class);
		return list;
	}


}
