package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.impl.ClWxxsDao;
import com.cczu.model.entity.WxxsCL;

@Service("ClWxxsService")
@Transactional(readOnly=true)
public class ClWxxsService {

	@Autowired
	private ClWxxsDao clWxxsDao;
	
	
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<WxxsCL> list=clWxxsDao.dataGrid(mapData);
		int getTotalCount=clWxxsDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public void addInfo(WxxsCL xs){
		clWxxsDao.saveIn(xs);
	}
	
	public void updateInfo(WxxsCL xs){
		clWxxsDao.save(xs);
	}

	public void deleteInfo(int id){
		clWxxsDao.deleteInfro(id);
	}
	
	public WxxsCL findByContent(Map<String, Object> mapData){
		return clWxxsDao.findByContent(mapData);	
	}
}
