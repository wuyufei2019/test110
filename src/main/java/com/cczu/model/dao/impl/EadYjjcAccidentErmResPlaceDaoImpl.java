package com.cczu.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.EAD_AccidentEntity;
import com.cczu.model.entity.EAD_Accident_ERM_ResPlace;
import com.cczu.model.dao.IEadYjjcAccidentErmResPlaceDao;
import com.cczu.util.dao.BaseDao;

@Repository("IEadYjjcAccidentErmResPlaceDao")
public class EadYjjcAccidentErmResPlaceDaoImpl extends BaseDao<EAD_Accident_ERM_ResPlace, Long> implements IEadYjjcAccidentErmResPlaceDao {

	@Override
	public void saveAccidentErmResPlace(EAD_Accident_ERM_ResPlace ead_Accident_erm_ResPlace) {
		save(ead_Accident_erm_ResPlace);
	}

	@Override
	public List<EAD_Accident_ERM_ResPlace> getEADAccidentERMResPlaceListByAccidentID(Long AccidentID) {
		return findBy("accident", new EAD_AccidentEntity(AccidentID));
				
	}
	
	@Override
	public void deleteERMResPlaceListByAccidentID(Long accidentId) {
		String sql="delete from ead_accident_erm_resplace where accident_id="+accidentId;
		updateBySql(sql);
	}
}
