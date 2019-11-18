package com.cczu.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.EAD_AccidentEntity;
import com.cczu.model.entity.EAD_Accident_Instantleakage;
import com.cczu.model.dao.IEadYjjcAccidentInstantleakageDao;
import com.cczu.util.dao.BaseDao;

@Repository("IEadYjjcAccidentInstantleakageDao")
public class EadYjjcAccidentInstantleakageDaoImpl extends BaseDao<EAD_Accident_Instantleakage, Long> implements IEadYjjcAccidentInstantleakageDao {

	@Override
	public void saveAccidentInstantleakage(EAD_Accident_Instantleakage accidentInstantleakage) {
		save(accidentInstantleakage);
	}

	@Override
	public List<EAD_Accident_Instantleakage> getEADAccidentInstantleakageListByAccidentID(Long AccidentID) {
		return findBy("accident", new EAD_AccidentEntity(AccidentID));
				
	}
	@Override
	public void deleteAccidentInstantleakage(Long accidentId) {
		String sql="delete from ead_accident_instantleakage where accident_id="+accidentId;
		updateBySql(sql);
	}
}
