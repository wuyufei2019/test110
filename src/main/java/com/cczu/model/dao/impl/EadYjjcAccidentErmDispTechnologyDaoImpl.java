package com.cczu.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.EAD_AccidentEntity;
import com.cczu.model.entity.EAD_Accident_ERM_DispTechnology;
import com.cczu.model.dao.IEadYjjcAccidentErmDispTechnologyDao;
import com.cczu.util.dao.BaseDao;

@Repository("IEadYjjcAccidentErmDispTechnologyDao")
public class EadYjjcAccidentErmDispTechnologyDaoImpl extends BaseDao<EAD_Accident_ERM_DispTechnology, Long> implements IEadYjjcAccidentErmDispTechnologyDao {

	@Override
	public void saveAccidentErmDispTechnology(EAD_Accident_ERM_DispTechnology ead_Accident_erm_DispTechnology) {
		save(ead_Accident_erm_DispTechnology);
	}

	@Override
	public List<EAD_Accident_ERM_DispTechnology> getEADAccidentERMDispTechnologyListByAccidentID(
			Long AccidentID) {
		return findBy("accident", new EAD_AccidentEntity(AccidentID));
	}

	@Override
	public void deleteERMDispTechnologyListByAccidentID(Long accidentId) {
		String sql="delete from ead_accident_erm_disptechnology where accident_id="+accidentId;
		updateBySql(sql);
	}
	
}
