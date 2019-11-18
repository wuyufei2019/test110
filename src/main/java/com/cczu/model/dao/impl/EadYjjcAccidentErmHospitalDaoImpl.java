package com.cczu.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.EAD_AccidentEntity;
import com.cczu.model.entity.EAD_Accident_ERM_Hospital;
import com.cczu.model.dao.IEadYjjcAccidentErmHospitalDao;
import com.cczu.util.dao.BaseDao;

@Repository("IEadYjjcAccidentErmHospitalDao")
public class EadYjjcAccidentErmHospitalDaoImpl extends BaseDao<EAD_Accident_ERM_Hospital, Long> implements IEadYjjcAccidentErmHospitalDao {

	@Override
	public void saveAccidentErmHospital(EAD_Accident_ERM_Hospital ead_Accident_erm_Hospital) {
		save(ead_Accident_erm_Hospital);
	}

	@Override
	public List<EAD_Accident_ERM_Hospital> getEADAccidentERMHospitalListByAccidentID(Long AccidentID) {
		return findBy("accident", new EAD_AccidentEntity(AccidentID));
	}

	@Override
	public void deleteERMHospitalListByAccidentID(Long accidentId) {
		String sql="delete from ead_accident_erm_hospital where accident_id="+accidentId;
		updateBySql(sql);
	}
	
}
