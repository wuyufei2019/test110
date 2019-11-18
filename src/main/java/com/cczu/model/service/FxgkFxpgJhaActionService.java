package com.cczu.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.FxgkFxpgJhaActionDao;
import com.cczu.model.entity.FXGK_RiskAction;

@Transactional(readOnly=true)
@Service("FxgkFxpgJhaActionService")
public class FxgkFxpgJhaActionService {
	
	@Resource
	private FxgkFxpgJhaActionDao fxgkFxpgDao;
	
	public void addInfo(FXGK_RiskAction entity) {
		// TODO Auto-generated method stub
		fxgkFxpgDao.save(entity);
	}
	
	public Long addInfoReID(FXGK_RiskAction entity) {
		// TODO Auto-generated method stub
		fxgkFxpgDao.save(entity);
		return entity.getID();
	}
	
	public void updateInfo(FXGK_RiskAction entity) {
		fxgkFxpgDao.save(entity);
	}
	@Transactional(readOnly=false)
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		fxgkFxpgDao.delete(id);
	}
	public List<FXGK_RiskAction> findAllById1(long id1) {
		// TODO Auto-generated method stub
		List<FXGK_RiskAction> list=fxgkFxpgDao.findBy("ID1", id1);
		return list;
	}

	public FXGK_RiskAction findById(Long id) {
		// TODO Auto-generated method stub
		FXGK_RiskAction entity = fxgkFxpgDao.find(id);
		fxgkFxpgDao.flush();
		fxgkFxpgDao.clear();
		return entity;
	}
	

}


