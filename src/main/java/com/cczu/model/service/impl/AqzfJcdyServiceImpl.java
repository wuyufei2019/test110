package com.cczu.model.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.IAqzfJcdyDao;
import com.cczu.model.entity.AQZF_SafetyCheckUnitEntity;
import com.cczu.model.service.IAqzfJcdyService;

@Service("AqzfJcdyService")
public class AqzfJcdyServiceImpl implements IAqzfJcdyService {

	@Resource
	private IAqzfJcdyDao AqzfJcdyDao;
	
	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=AqzfJcdyDao.dataGrid(mapData);
		int getTotalCount=AqzfJcdyDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	@Override
	public List<Map<String, Object>> getjcdylist() {
		return AqzfJcdyDao.getjcdylist();
	}

	@Override
	public void addInfo(AQZF_SafetyCheckUnitEntity jcdy) {
		jcdy.setS1(new Timestamp(System.currentTimeMillis()));
		jcdy.setS2(new Timestamp(System.currentTimeMillis()));
		jcdy.setS3(0);
		AqzfJcdyDao.addInfo(jcdy);
	}

	@Override
	public void deleteInfo(long id) {
		AqzfJcdyDao.deleteInfo(id);
	}
	
	@Override
	public void updateInfo(AQZF_SafetyCheckUnitEntity jcdy) {
		AQZF_SafetyCheckUnitEntity a = findById(jcdy.getID());
		jcdy.setS1(a.getS1());
		jcdy.setS2(new Timestamp(System.currentTimeMillis()));
		jcdy.setS3(0);
		AqzfJcdyDao.updateInfo(jcdy);
	}

	@Override
	public AQZF_SafetyCheckUnitEntity findById(Long id) {
		return AqzfJcdyDao.findInfoById(id);
	}
	
}
