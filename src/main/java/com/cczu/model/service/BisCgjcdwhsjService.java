package com.cczu.model.service;

import com.cczu.model.dao.BisCgjcdwhsjDao;
import com.cczu.model.entity.BIS_Monitor_Point_ExtraEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 储罐监测点维护数据service
 */
@Service("BisCgjcdwhsjService")
public class BisCgjcdwhsjService {
	
	@Resource
	private BisCgjcdwhsjDao bisCgjcdwhsjDao;

	
	public void addInfo(BIS_Monitor_Point_ExtraEntity bis) {
		bisCgjcdwhsjDao.addInfo(bis);
	}

	
	public void updateInfo(BIS_Monitor_Point_ExtraEntity bt) {
		bisCgjcdwhsjDao.updateInfo(bt);
	}

	
	public void deleteInfo(long id) {
		bisCgjcdwhsjDao.delete(id);
	}

	
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list = bisCgjcdwhsjDao.dataGrid(mapData);
		int getTotalCount = bisCgjcdwhsjDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	public BIS_Monitor_Point_ExtraEntity findById(Long id) {
		return bisCgjcdwhsjDao.findById(id);
	}

	public Map<String, Object> findMapById(Long id) {
		return bisCgjcdwhsjDao.findMapById(id);
	}

}
