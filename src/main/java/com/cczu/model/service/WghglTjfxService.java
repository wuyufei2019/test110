package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.WghglTjfxDao;

/**
 * 
 * @Description: 网格统计分析Service
 * @author: zpc
 * @date: 2017年9月29日
 */
@Service("WghglTjfxService")
public class WghglTjfxService {

	@Resource
	private WghglTjfxDao wghglTjfxDao;
	
	/**
	 * 巡检点月检list
	 * @param map
	 * @return
	 */
	public Map<String, Object> xjdyjdataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=wghglTjfxDao.xjdyjdataGrid(mapData);
		int getTotalCount=wghglTjfxDao.getxjdyjTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 巡检点年检list
	 * @param map
	 * @return
	 */
	public Map<String, Object> xjdnjdataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=wghglTjfxDao.xjdnjdataGrid(mapData);
		int getTotalCount=wghglTjfxDao.getxjdnjTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 班次月检list
	 * @param map
	 * @return
	 */
	public Map<String, Object> bcyjdataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=wghglTjfxDao.bcyjdataGrid(mapData);
		int getTotalCount=wghglTjfxDao.getbcyjTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 班次年检list
	 * @param map
	 * @return
	 */
	public Map<String, Object> bcnjdataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=wghglTjfxDao.bcnjdataGrid(mapData);
		int getTotalCount=wghglTjfxDao.getbcnjTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 巡检人员月检list
	 * @param map
	 * @return
	 */
	public Map<String, Object> xjryyjdataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=wghglTjfxDao.xjryyjdataGrid(mapData);
		int getTotalCount=wghglTjfxDao.getxjryyjTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 巡检人员年检list
	 * @param map
	 * @return
	 */
	public Map<String, Object> xjrynjdataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=wghglTjfxDao.xjrynjdataGrid(mapData);
		int getTotalCount=wghglTjfxDao.getxjrynjTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
}
