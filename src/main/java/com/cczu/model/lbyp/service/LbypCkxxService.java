package com.cczu.model.lbyp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.lbyp.dao.LbypCkxxDao;
import com.cczu.model.lbyp.entity.Lbyp_Storage;

@Transactional(readOnly=true)
@Service("LbypCkxxService")
public class LbypCkxxService {
	
	@Resource
	private LbypCkxxDao lbypCkxxDao;

	
	public List<Lbyp_Storage> findAll(long qyid) {
		// TODO Auto-generated method stub
		return lbypCkxxDao.findAll(qyid);
	}

	
	public void addInfo(Lbyp_Storage entity) {
		// TODO Auto-generated method stub
		lbypCkxxDao.save(entity);
		
	}

	
	public void updateInfo(Lbyp_Storage entity) {
		lbypCkxxDao.save(entity);
	}

	
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		lbypCkxxDao.deleteInfo(id);
	}

	
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=lbypCkxxDao.dataGrid(mapData);
		int getTotalCount=lbypCkxxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	
	public Lbyp_Storage findById(Long id) {
		// TODO Auto-generated method stub
		return lbypCkxxDao.find(id);
	}
	
	public List<Map<String, Object>> getIdJson(Map<String, Object> map) {
		return lbypCkxxDao.getIdJson(map);
	}

}


