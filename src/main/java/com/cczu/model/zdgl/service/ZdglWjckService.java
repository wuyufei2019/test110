package com.cczu.model.zdgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.zdgl.dao.ZdglWjckDao;

/**
 *  制度管理-安全文件查看Service
 *
 */
@Service("ZdglWjckService")
public class ZdglWjckService {

	@Resource
	private ZdglWjckDao zdglWjckDao;
	
	/**
	 * list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=zdglWjckDao.dataGrid(mapData);
		int getTotalCount=zdglWjckDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {
		List<Map<String,Object>> list=zdglWjckDao.dataGrid2(mapData);
		int getTotalCount=zdglWjckDao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public void updateztbyid(Map<String, Object> mapData){
		zdglWjckDao.updateztbyid(mapData);
	}
	
}
