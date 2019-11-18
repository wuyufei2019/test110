package com.cczu.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.EAD_AccidentEntity;
import com.cczu.model.entity.EAD_Accident_ERM_ResTeam;
import com.cczu.model.dao.IEadYjjcAccidentErmResTeamDao;
import com.cczu.util.dao.BaseDao;

@Repository("IEadYjjcAccidentErmResTeamDao")
public class EadYjjcAccidentErmResteamDaoImpl extends BaseDao<EAD_Accident_ERM_ResTeam, Long> implements IEadYjjcAccidentErmResTeamDao {

	@Override
	public void saveAccidentErmResTeam(EAD_Accident_ERM_ResTeam ead_Accident_ERM_ResTeam) {
		save(ead_Accident_ERM_ResTeam);
	}

	@Override
	public List<EAD_Accident_ERM_ResTeam> getEADAccidentERMResTeamListByAccidentID(Long AccidentID) {
		return findBy("accident", new EAD_AccidentEntity(AccidentID));
				
	}
	
	@Override
	public void deleteERMResTeamListByAccidentID(Long accidentId) {
		String sql="delete from ead_accident_erm_resteam where accident_id="+accidentId;
		updateBySql(sql);
	}
}
