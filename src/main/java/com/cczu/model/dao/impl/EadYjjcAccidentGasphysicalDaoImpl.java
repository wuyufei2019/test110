package com.cczu.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.EAD_AccidentEntity;
import com.cczu.model.entity.EAD_Accident_Gasphysical;
import com.cczu.model.dao.IEadYjjcAccidentGasphysicalDao;
import com.cczu.util.dao.BaseDao;

@Repository("IEadYjjcAccidentGasphysicalDao")
public class EadYjjcAccidentGasphysicalDaoImpl extends BaseDao<EAD_Accident_Gasphysical, Long> implements IEadYjjcAccidentGasphysicalDao {

	@Override
	public void saveAccidentGasphysical(EAD_Accident_Gasphysical accidentGasphysical) {
		save(accidentGasphysical);
	}

	@Override
	public List<EAD_Accident_Gasphysical> getEADAccidentGasphysicalListByAccidentID(Long AccidentID) {
		return findBy("accident", new EAD_AccidentEntity(AccidentID));
				
	}
	@Override
	public void deleteAccidentGasphysical(Long accidentId) {
		String sql="delete from ead_accident_gasphysical where accident_id="+accidentId;
		updateBySql(sql);
	}
}
