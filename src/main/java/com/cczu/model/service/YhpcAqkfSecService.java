package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.YhpcAqkfSecDao;
import com.cczu.model.entity.YHPC_SafetyDeduction_Second;

/**
 *  安全十二分副表Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("YhpcAqkfSecService")
public class YhpcAqkfSecService {
	@Resource
	private YhpcAqkfSecDao yhpcAqkfSecDao;
	
	/**
	 * tab页分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<YHPC_SafetyDeduction_Second> list=yhpcAqkfSecDao.dataGrid(mapData);
		int getTotalCount=yhpcAqkfSecDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 添加
	 * @param bis
	 */
	public void addInfo(YHPC_SafetyDeduction_Second bis) {
		yhpcAqkfSecDao.save(bis);
	}

	/**
	 * 修改
	 * @param bis
	 */
	public void upd(YHPC_SafetyDeduction_Second bis) {
		yhpcAqkfSecDao.save(bis);
	}
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public YHPC_SafetyDeduction_Second findById(Long id) {
		// TODO Auto-generated method stub
		return yhpcAqkfSecDao.find(id);
	}
}
