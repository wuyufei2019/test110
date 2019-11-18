package com.cczu.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.EAD_AccidentEntity;
import com.cczu.model.entity.EAD_Accident_Leakage;
import com.cczu.model.dao.IEadYjjcAccidentLeakageDao;
import com.cczu.util.dao.BaseDao;

@Repository("IEadYjjcAccidentLeakageDao")
public class EadYjjcAccidentLeakageDaoImpl extends BaseDao<EAD_Accident_Leakage, Long> implements IEadYjjcAccidentLeakageDao {

	@Override
	public void saveAccidentLeakage(EAD_Accident_Leakage accidentLeakage) {
		save(accidentLeakage);
	}

	@Override
	public List<EAD_Accident_Leakage> getEADAccidentLeakageListByAccidentID(Long AccidentID) {
		return findBy("accident", new EAD_AccidentEntity(AccidentID));
				
	}
	
	@Override
	public void deleteAccidentLeakage(Long accidentId) {
		String sql="delete from ead_accident_leakage where accident_id="+accidentId;
		updateBySql(sql);
	}
}
