package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.YhpcXjdztDao;

/**
 * 巡检点状态
 * @author zpc
 */
@Service("YhpcXjdztService")
public class YhpcXjdztService {

	@Resource
	private YhpcXjdztDao yhpcXjdztDao;
	
	/**
	 * 巡检点状态list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=yhpcXjdztDao.dataGrid(mapData);
		return list;
	}
	
	/**
	 * 获取班次最新巡检记录
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataXjGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=yhpcXjdztDao.dataXjGrid(mapData);
		return list;
	}
}
