package com.cczu.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.EAD_AccidentEntity;
import com.cczu.model.entity.EAD_Accident_JetFire;
import com.cczu.model.dao.IEadYjjcAccidentJetFireDao;
import com.cczu.util.dao.BaseDao;

@Repository("IEadYjjcAccidentJetFireDao")
public class EadYjjcAccidentJetFireDaoImpl extends BaseDao<EAD_Accident_JetFire, Long> implements IEadYjjcAccidentJetFireDao {

	@Override
	public void saveAccidentJetFire(EAD_Accident_JetFire accidentJetFire) {
		save(accidentJetFire);
	}

	@Override
	public List<EAD_Accident_JetFire> getEADAccidentJetFireListByAccidentID(Long AccidentID) {
		return findBy("accident", new EAD_AccidentEntity(AccidentID));
				
	}

	@Override
	public void deleteAccidentJetFire(Long accidentId) {
		String sql="delete from ead_accident_jetfire where accident_id="+accidentId;
		updateBySql(sql);
	}
	
}
