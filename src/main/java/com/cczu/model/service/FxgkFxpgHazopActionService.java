package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.FxgkFxpgHazopStepDao;
import com.cczu.model.entity.FXGK_HazopRiskStep;

@Transactional(readOnly=true)
@Service("FxgkFxpgHazopActionService")
public class FxgkFxpgHazopActionService {
	
	@Resource
	private FxgkFxpgHazopStepDao fxgkFxpgHazopStepDao;
	
	public void addInfo(FXGK_HazopRiskStep entity) {
		// TODO Auto-generated method stub
		fxgkFxpgHazopStepDao.save(entity);
	}
	
	public Long addInfoReID(FXGK_HazopRiskStep entity) {
		// TODO Auto-generated method stub
		fxgkFxpgHazopStepDao.save(entity);
		return entity.getID();
	}
	
	public void updateInfo(FXGK_HazopRiskStep entity) {
		fxgkFxpgHazopStepDao.save(entity);
	}
	@Transactional(readOnly=false)
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		fxgkFxpgHazopStepDao.delete(id);
	}
	public List<FXGK_HazopRiskStep> findAllById1(long id1) {
		// TODO Auto-generated method stub
		List<FXGK_HazopRiskStep> list=fxgkFxpgHazopStepDao.findBy("ID1", id1);
		return list;
	}

	public FXGK_HazopRiskStep findById(Long id) {
		// TODO Auto-generated method stub
		FXGK_HazopRiskStep entity = fxgkFxpgHazopStepDao.find(id);
		fxgkFxpgHazopStepDao.flush();
		fxgkFxpgHazopStepDao.clear();
		return entity;
	}
	
	//根据id获得hazop风险评估word表数据
	public Map<String, Object> getExportWord(long id){
		Map<String, Object> map=new HashMap<String, Object>();
		FXGK_HazopRiskStep entity = fxgkFxpgHazopStepDao.find(id);
		fxgkFxpgHazopStepDao.flush();
		fxgkFxpgHazopStepDao.clear();

		return map;
	}
}


