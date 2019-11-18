package com.cczu.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.FxgkFxpgSclActionDao;
import com.cczu.model.entity.FXGK_SclRiskAction;

@Transactional(readOnly=true)
@Service("FxgkFxpgSclActionService")
public class FxgkFxpgSclActionService {
	
	@Resource
	private FxgkFxpgSclActionDao fxgkFxpgDao;
	
	public void addInfo(FXGK_SclRiskAction entity) {
		// TODO Auto-generated method stub
		fxgkFxpgDao.save(entity);
	}
	
	public Long addInfoReID(FXGK_SclRiskAction entity) {
		// TODO Auto-generated method stub
		fxgkFxpgDao.save(entity);
		return entity.getID();
	}
	
	public void updateInfo(FXGK_SclRiskAction entity) {
		fxgkFxpgDao.save(entity);
	}
	@Transactional(readOnly=false)
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		fxgkFxpgDao.delete(id);
	}
	public List<FXGK_SclRiskAction> findAllById1(long id1) {
		// TODO Auto-generated method stub
		List<FXGK_SclRiskAction> list=fxgkFxpgDao.findBy("ID1", id1);
		return list;
	}

	public FXGK_SclRiskAction findById(Long id) {
		// TODO Auto-generated method stub
		FXGK_SclRiskAction entity = fxgkFxpgDao.find(id);
		fxgkFxpgDao.flush();fxgkFxpgDao.clear();
		return entity;
	}
	

}


