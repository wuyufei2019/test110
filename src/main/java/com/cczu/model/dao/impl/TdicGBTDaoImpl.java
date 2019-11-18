package com.cczu.model.dao.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.ITdicGBTDao;
import com.cczu.model.entity.Tdic_GbtbusinessEntity;
import com.cczu.util.dao.BaseDao;

@Repository("ITdicGBTDao")
public class TdicGBTDaoImpl extends BaseDao<Tdic_GbtbusinessEntity,Long> implements ITdicGBTDao {

	@Override
	public List<Tdic_GbtbusinessEntity> findListAll() {
//		String sql="select * from tdic_gbtbusiness order by id";
//		List<Tdic_GbtbusinessEntity> list=findBySql(sql,null,Tdic_GbtbusinessEntity.class);
		return findAll();
	}
	
}
