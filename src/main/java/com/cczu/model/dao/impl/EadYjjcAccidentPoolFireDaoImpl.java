package com.cczu.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.EAD_AccidentEntity;
import com.cczu.model.entity.EAD_Accident_PoolFire;
import com.cczu.model.dao.IEadYjjcAccidentPoolFireDao;
import com.cczu.util.dao.BaseDao;

@Repository("IEadYjjcAccidentPoolFireDao")
public class EadYjjcAccidentPoolFireDaoImpl extends BaseDao<EAD_Accident_PoolFire, Long> implements IEadYjjcAccidentPoolFireDao {

	@Override
	public void saveAccidentPoolFire(EAD_Accident_PoolFire accidentPoolFire) {
		save(accidentPoolFire);
	}

	@Override
	public List<EAD_Accident_PoolFire> getEADAccidentPoolFireListByAccidentID(Long AccidentID) {
		return findBy("accident", new EAD_AccidentEntity(AccidentID));
				
	}
	
	@Override
	public void deleteAccidentPoolFire(Long accidentId) {
		String sql="delete from ead_accident_poolfire where accident_id="+accidentId;
		updateBySql(sql);
	}
}
