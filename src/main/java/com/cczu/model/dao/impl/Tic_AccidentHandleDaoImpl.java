package com.cczu.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.ITic_AccidentHandleDao;
import com.cczu.model.entity.Tdic_AccidentHandle;
import com.cczu.util.dao.BaseDao;
@Repository("accidentHandleDao")
public class Tic_AccidentHandleDaoImpl extends BaseDao<Tdic_AccidentHandle,Long> implements ITic_AccidentHandleDao {

 

	@Override
	public List<Tdic_AccidentHandle> findAllList() {
		// TODO Auto-generated method stub
		String sql =" SELECT * FROM tdic_accidenthandle "   ;
		List<Tdic_AccidentHandle> list=findBySql(sql, null,Tdic_AccidentHandle.class);
		
		return list;
	}
	
}
