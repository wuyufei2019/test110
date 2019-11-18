package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.impl.LbWxxsDao;
import com.cczu.model.entity.WxxsLB;

@Service("LbWxxsService")
@Transactional(readOnly=true)
public class LbWxxsService {

	@Autowired
	private LbWxxsDao lbWxxsDao;
	
	
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=lbWxxsDao.dataGrid(mapData);
		int getTotalCount=lbWxxsDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public void addInfo(WxxsLB xs){
		Map<String, Object> map=new HashMap<>();
		map.put("m1", xs.getM1());
		if(lbWxxsDao.findByContent(map)==null)
			lbWxxsDao.addInfro(xs);
	}
	
	public void updateInfo(WxxsLB xs){
		lbWxxsDao.updateInfro(xs);
	}

	public void deleteInfo(int id){
		lbWxxsDao.deleteInfro(id);
	}
	
	public WxxsLB findByContent(Map<String, Object> mapData){
		return lbWxxsDao.findByContent(mapData);	
	}
}
