package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.FxgkFxpgMajorRiskDao;
import com.cczu.model.dao.FxgkFxpgWxdDao;
import com.cczu.model.entity.FXGK_WxdRiskAssessment;

@Transactional(readOnly=true)
@Service("FxgkFxpgWxdService")
public class FxgkFxpgWxdService {
	
	@Resource
	private FxgkFxpgWxdDao fxgkfxpgWxddao;
	@Resource
	private FxgkFxpgMajorRiskDao fxgkfxpgmajorriskdao;
	
	public void addInfo(FXGK_WxdRiskAssessment entity) {
		// TODO Auto-generated method stub
		fxgkfxpgWxddao.save(entity);
	}
	public Long addInfoReID(FXGK_WxdRiskAssessment entity) {
		// TODO Auto-generated method stub
		fxgkfxpgWxddao.save(entity);
		return entity.getID();
	}

	
	public void updateInfo(FXGK_WxdRiskAssessment entity) {
		fxgkfxpgWxddao.save(entity);
	}

	
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		fxgkfxpgWxddao.deleteInfo(id);
		fxgkfxpgmajorriskdao.deleteInfoById1(id);
		
	}

	
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=fxgkfxpgWxddao.dataGrid(mapData);
		int getTotalCount=fxgkfxpgWxddao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	
	public FXGK_WxdRiskAssessment findById(Long id) {
		// TODO Auto-generated method stub
		FXGK_WxdRiskAssessment entity = fxgkfxpgWxddao.find(id);
		fxgkfxpgWxddao.flush();fxgkfxpgWxddao.clear();
		return entity;
	}
	
}


