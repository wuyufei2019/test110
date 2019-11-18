package com.cczu.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.EAD_AccidentEntity;
import com.cczu.model.entity.EAD_Accident_Vce;
import com.cczu.model.dao.IEadYjjcAccidentVceDao;
import com.cczu.util.dao.BaseDao;

@Repository("IEadYjjcAccidentVceDao")
public class EadYjjcAccidentVceDaoImpl extends BaseDao<EAD_Accident_Vce, Long> implements IEadYjjcAccidentVceDao {

	@Override
	public void saveAccidentVce(EAD_Accident_Vce accidentVce) {
		save(accidentVce);
	}

	@Override
	public List<EAD_Accident_Vce> getEADAccidentVceListByAccidentID(Long AccidentID) {
		return findBy("accident", new EAD_AccidentEntity(AccidentID));
				
	}
	
	@Override
	public void deleteAccidentVce(Long accidentId) {
		String sql="delete from ead_accident_vce where accident_id="+accidentId;
		updateBySql(sql);
	}
}
