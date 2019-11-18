package com.cczu.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.FxgkFxpgLecActionDao;
import com.cczu.model.entity.FXGK_LecRiskAction;

@Transactional(readOnly=true)
@Service("FxgkFxpgLecActionService")
public class FxgkFxpgLecActionService {
	
	@Resource
	private FxgkFxpgLecActionDao fxgkfxpglecactiondao;
	
	public void addInfo(FXGK_LecRiskAction entity) {
		// TODO Auto-generated method stub
		fxgkfxpglecactiondao.save(entity);
	}
	
	public Long addInfoReID(FXGK_LecRiskAction entity) {
		// TODO Auto-generated method stub
		fxgkfxpglecactiondao.save(entity);
		return entity.getID();
	}
	
	public void updateInfo(FXGK_LecRiskAction entity) {
		fxgkfxpglecactiondao.save(entity);
	}
	@Transactional(readOnly=false)
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		fxgkfxpglecactiondao.delete(id);
	}
	public List<FXGK_LecRiskAction> findAllById1(long id1) {
		// TODO Auto-generated method stub
		List<FXGK_LecRiskAction> list=fxgkfxpglecactiondao.findBy("ID1", id1);
		return list;
	}

	public FXGK_LecRiskAction findById(Long id) {
		// TODO Auto-generated method stub
		FXGK_LecRiskAction entity = fxgkfxpglecactiondao.find(id);
		fxgkfxpglecactiondao.flush();
		fxgkfxpglecactiondao.clear();
		return entity;
	}
	

}


