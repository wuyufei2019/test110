package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.BisTzsbSecDao;
import com.cczu.model.entity.BIS_Specequipment_Second;

/**
 *  特种设备副表Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("BisTzsbSecService")
public class BisTzsbSecService {
	@Resource
	private BisTzsbSecDao bisTzsbSecDao;
	
	/**
	 * tab页分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<BIS_Specequipment_Second> list=bisTzsbSecDao.dataGrid(mapData);
		int getTotalCount=bisTzsbSecDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 添加
	 * @param bis
	 */
	public void addInfo(BIS_Specequipment_Second bis) {
		bisTzsbSecDao.save(bis);
	}
}
