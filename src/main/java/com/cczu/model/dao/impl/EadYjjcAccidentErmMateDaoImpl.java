package com.cczu.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.EAD_AccidentEntity;
import com.cczu.model.entity.EAD_Accident_ERM_Mate;
import com.cczu.model.dao.IEadYjjcAccidentErmMateDao;
import com.cczu.util.dao.BaseDao;

@Repository("IEadYjjcAccidentErmMateDao")
public class EadYjjcAccidentErmMateDaoImpl extends BaseDao<EAD_Accident_ERM_Mate, Long> implements IEadYjjcAccidentErmMateDao {

	@Override
	public void saveAccidentErmMate(EAD_Accident_ERM_Mate ead_Accident_erm_Mate) {
		save(ead_Accident_erm_Mate);
	}

	@Override
	public List<EAD_Accident_ERM_Mate> getEADAccidentERMMateListByAccidentID(Long AccidentID) {
		return findBy("accident", new EAD_AccidentEntity(AccidentID));
				
	}
	
	@Override
	public void deleteERMMateListByAccidentID(Long accidentId) {
		String sql="delete from ead_accident_erm_mate where accident_id="+accidentId;
		updateBySql(sql);
	}
}
