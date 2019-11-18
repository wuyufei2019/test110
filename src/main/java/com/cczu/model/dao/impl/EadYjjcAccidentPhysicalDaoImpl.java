package com.cczu.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.EAD_AccidentEntity;
import com.cczu.model.entity.EAD_Accident_Physical;
import com.cczu.model.dao.IEadYjjcAccidentPhysicalDao;
import com.cczu.util.dao.BaseDao;

@Repository("IEadYjjcAccidentPhysicalDao")
public class EadYjjcAccidentPhysicalDaoImpl extends BaseDao<EAD_Accident_Physical, Long> implements IEadYjjcAccidentPhysicalDao {

	@Override
	public void saveAccidentPhysical(EAD_Accident_Physical accidentPhysical) {
		save(accidentPhysical);
	}

	@Override
	public List<EAD_Accident_Physical> getEADAccidentPhysicalListByAccidentID(Long AccidentID) {
		return findBy("accident", new EAD_AccidentEntity(AccidentID));
				
	}
	
	@Override
	public void deleteAccidentPhysical(Long accidentId) {
		String sql="delete from ead_accident_physical where accident_id="+accidentId;
		updateBySql(sql);
	}
}
