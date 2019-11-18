package com.cczu.model.dao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.ITdicBisAJFLDao;
import com.cczu.model.entity.Tdic_BisjgflEntity;
import com.cczu.util.dao.BaseDao;

@Repository("ITdicBisAJFLDao")
public class TdicBisAJFLDaoImpl extends BaseDao<Tdic_BisjgflEntity,Long> implements ITdicBisAJFLDao {

	@Override
	public List<Tdic_BisjgflEntity> findAJFLList() {
		return findAll();
	}

	@Override
	public List<Map<String,Object>> findDlLList() {
		String sql=" select RIGHT('0'+CAST( cid  AS varchar(50)),2) id,cname as text from tdic_gbtbusiness where cid>0 and cid<100 " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);

		return list;
	}

}
