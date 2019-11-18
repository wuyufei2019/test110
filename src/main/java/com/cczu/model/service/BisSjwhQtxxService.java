package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.BisSjwhQtxxDao;
import com.cczu.model.entity.Bis_SensorEntity;

/**
 *  气体信息Service
 *
 */
@Transactional(readOnly=true)
@Service("BisSjwhQtxxService")
public class BisSjwhQtxxService {
	
	@Resource
	private BisSjwhQtxxDao bisSjwhQtxxDao;
	
	/**
	 * tab页分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list = bisSjwhQtxxDao.dataGrid(mapData);
		int getTotalCount = bisSjwhQtxxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	
	
	//添加信息
	public void addInfo(Bis_SensorEntity aqjg) {
		bisSjwhQtxxDao.save(aqjg);
	}
	
	//更新信息
	public void updateInfo(Bis_SensorEntity aqjg) {
		bisSjwhQtxxDao.save(aqjg);
	}
	
	//删除信息
	public void deleteInfo(long id) {
		bisSjwhQtxxDao.deleteInfo(id);
	}

	//根据id查找详细信息
	public Bis_SensorEntity findById(Long id) {
		return bisSjwhQtxxDao.find(id);
	}
	
	//根据id查询详细信息
	public Map<String, Object> findInfoById(long id) {
		return bisSjwhQtxxDao.findInfoById(id);
	}
	
}
