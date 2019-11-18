package com.cczu.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.ITdicZybflDao;
import com.cczu.model.entity.Tdic_ZybAndFlEntity;
import com.cczu.util.dao.BaseDao;

@Repository("TdicZybflDao")
public class TdicZybflDaoImpl extends BaseDao<Tdic_ZybAndFlEntity,Long> implements ITdicZybflDao {

	@Override
	public List<Tdic_ZybAndFlEntity> findListAll() {
		// TODO Auto-generated method stub
		return findAll();
	}

	@Override
	public Tdic_ZybAndFlEntity findbycid(String cid) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM tdic_zybandfl WHERE CID='"+cid+"'";
		List<Tdic_ZybAndFlEntity> list = findBySql(sql,null,Tdic_ZybAndFlEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
