package com.cczu.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.EAD_AccidentEntity;
import com.cczu.model.entity.EAD_Accident_Fireball;
import com.cczu.model.dao.IEadYjjcAccidentFireballDao;
import com.cczu.util.dao.BaseDao;

@Repository("IEadYjjcAccidentFireballDao")
public class EadYjjcAccidentFireballDaoImpl extends BaseDao<EAD_Accident_Fireball, Long> implements IEadYjjcAccidentFireballDao {

	@Override
	public void saveAccidentFireball(EAD_Accident_Fireball accidentFireball) {
		save(accidentFireball);
	}

	@Override
	public List<EAD_Accident_Fireball> getEADAccidentFireballListByAccidentID(Long AccidentID) {
		return findBy("accident", new EAD_AccidentEntity(AccidentID));
				
	}
	
	@Override
	public void deleteAccidentFireball(Long accidentId) {
		String sql="delete from ead_accident_fireball where accident_id="+accidentId;
		updateBySql(sql);
	}
}
