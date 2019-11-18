package com.cczu.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.EAD_AccidentEntity;
import com.cczu.model.entity.EAD_Accident_ERM_ResExpert;
import com.cczu.model.dao.IEadYjjcAccidentErmResExpertDao;
import com.cczu.util.dao.BaseDao;

@Repository("IEadYjjcAccidentErmResExpertDao")
public class EadYjjcAccidentErmResExpertDaoImpl extends BaseDao<EAD_Accident_ERM_ResExpert, Long> implements IEadYjjcAccidentErmResExpertDao {

	@Override
	public void saveAccidentErmResExpert(EAD_Accident_ERM_ResExpert ead_Accident_erm_ResExpert) {
		save(ead_Accident_erm_ResExpert);
	}

	@Override
	public List<EAD_Accident_ERM_ResExpert> getEADAccidentERMResExpertListByAccidentID(Long AccidentID) {
		return findBy("accident", new EAD_AccidentEntity(AccidentID));
				
	}
	
	@Override
	public void deleteERMResExpertListByAccidentID(Long accidentId) {
		String sql="delete from ead_accident_erm_resexpert where accident_id="+accidentId;
		updateBySql(sql);
	}
}
