package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.WghglJdkhDao;

/**
 * 
 * @Description: 监督考核Service
 * @author: zpc
 * @date: 2017年9月30日
 */
@Service("WghglJdkhService")
public class WghglJdkhService {

	@Resource
	private WghglJdkhDao wghglJdkhDao;

	/**
	 * 月检list
	 * @param map
	 * @return
	 */
	public Map<String, Object> yjdataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=wghglJdkhDao.yjdataGrid(mapData);
		int getTotalCount=wghglJdkhDao.getyjTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 年检list
	 * @param map
	 * @return
	 */
	public Map<String, Object> njdataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=wghglJdkhDao.njdataGrid(mapData);
		int getTotalCount=wghglJdkhDao.getnjTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
}
