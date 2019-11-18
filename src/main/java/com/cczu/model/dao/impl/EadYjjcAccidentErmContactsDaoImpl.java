package com.cczu.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.EAD_AccidentEntity;
import com.cczu.model.entity.EAD_Accident_ERM_Contacts;
import com.cczu.model.dao.IEadYjjcAccidentErmContactsDao;
import com.cczu.util.dao.BaseDao;

@Repository("IEadYjjcAccidentErmContactsDao")
public class EadYjjcAccidentErmContactsDaoImpl extends BaseDao<EAD_Accident_ERM_Contacts, Long> implements IEadYjjcAccidentErmContactsDao {

	@Override
	public void saveAccidentErmContacts(EAD_Accident_ERM_Contacts ead_Accident_erm_Contacts) {
		save(ead_Accident_erm_Contacts);
	}

	@Override
	public List<EAD_Accident_ERM_Contacts> getEADAccidentERMContactsListByAccidentID(
			Long AccidentID) {
		return findBy("accident", new EAD_AccidentEntity(AccidentID));
	}

	@Override
	public void deleteERMContactsListByAccidentID(Long accidentId) {
		String sql="delete from ead_accident_erm_contacts where accident_id="+accidentId;
		updateBySql(sql);
	}
	
}
