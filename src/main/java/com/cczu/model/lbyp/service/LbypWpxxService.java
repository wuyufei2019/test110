package com.cczu.model.lbyp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.lbyp.dao.LbypWpxxDao;
import com.cczu.model.lbyp.entity.Lbyp_Goods;

@Transactional(readOnly=true)
@Service("LbypWpxxService")
public class LbypWpxxService {
	
	@Resource
	private LbypWpxxDao lbypWpxxDao;
	
	public void addInfo(Lbyp_Goods entity) {
		// TODO Auto-generated method stub
		lbypWpxxDao.save(entity);
		
	}
	
	public void updateInfo(Lbyp_Goods entity) {
		lbypWpxxDao.save(entity);
	}
	
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		lbypWpxxDao.deleteInfo(id);
	}
	
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=lbypWpxxDao.dataGrid(mapData);
		int getTotalCount=lbypWpxxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	
	public Lbyp_Goods findById(Long id) {
		// TODO Auto-generated method stub
		return lbypWpxxDao.find(id);
	}
	public List<Map<String, Object>> getIdJson(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return lbypWpxxDao.getIdJson(mapData);
	}

}


