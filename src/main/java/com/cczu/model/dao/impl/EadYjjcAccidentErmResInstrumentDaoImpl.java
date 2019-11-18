package com.cczu.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.EAD_AccidentEntity;
import com.cczu.model.entity.EAD_Accident_ERM_ResInstrument;
import com.cczu.model.dao.IEadYjjcAccidentErmResInstrumentDao;
import com.cczu.util.dao.BaseDao;

@Repository("IEadYjjcAccidentErmResInstrumentDao")
public class EadYjjcAccidentErmResInstrumentDaoImpl extends BaseDao<EAD_Accident_ERM_ResInstrument, Long> implements IEadYjjcAccidentErmResInstrumentDao {

	@Override
	public void saveAccidentErmResInstrument( EAD_Accident_ERM_ResInstrument erm_ResInstrument) {
		save(erm_ResInstrument);
	}
	
	@Override
	public List<EAD_Accident_ERM_ResInstrument> getEADAccidentERMResInstrumentListByAccidentID(Long AccidentID) {
		return findBy("accident", new EAD_AccidentEntity(AccidentID));
				
	}
	
	@Override
	public void deleteERMResInstrumentListByAccidentID(Long accidentId) {
		String sql="delete from ead_accident_erm_resinstrument where accident_id="+accidentId;
		updateBySql(sql);
	}
}
