package com.cczu.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.EAD_AccidentEntity;
import com.cczu.model.entity.EAD_Accident_TMESK_Msds;
import com.cczu.model.dao.IEadYjjcAccidentTmeskMsdsDao;
import com.cczu.util.dao.BaseDao;

@Repository("IEadYjjcAccidentTmeskMsdsDao")
public class EadYjjcAccidentTmeskMsdsDaoImpl extends BaseDao<EAD_Accident_TMESK_Msds, Long> implements IEadYjjcAccidentTmeskMsdsDao {

	@Override
	public void saveAccidentTmeskMsds(EAD_Accident_TMESK_Msds msds) {
		save(msds);
	}

	@Override
	public List<EAD_Accident_TMESK_Msds> getEADAccidentTmeskMsdsListByAccidentID(Long AccidentID) {
		return findBy("accident", new EAD_AccidentEntity(AccidentID));
	}

	@Override
	public void deleteTmeskMsdsListByAccidentID(Long accidentId) {
		String sql="delete from ead_accident_tmesk_msds where accident_id="+accidentId;
		updateBySql(sql);
	}
	
}
