package com.cczu.model.lbyp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.lbyp.dao.LbypFfbzDao;

@Transactional(readOnly=true)
@Service("LbypFfglService")
public class LbypFfglService {
	
	@Resource
	private LbypFfbzDao lbypFfbzDao;
	

	public int getTotalCount2(Map<String, Object> mapData){
		return lbypFfbzDao.getTotalCount2(mapData);
	}
	
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=lbypFfbzDao.dataGrid2(mapData);
		int getTotalCount=lbypFfbzDao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
}


