package com.cczu.model.zyaqgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.zyaqgl.dao.AqglXgfwgDao;
import com.cczu.model.zyaqgl.entity.AQGL_RelevantViolation;

/**
 *  安全管理-相关违规管理Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("AqglXgfwgService")
public class AqglXgfwgService {

	@Resource
	private AqglXgfwgDao xgfwgDao;

	/**
	 * 分页查询（累计扣分统计）
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {

		List<Map<String, Object>> list = xgfwgDao.dataGrid(mapData);
		int getTotalCount = xgfwgDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 分页查询（扣分历史）
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {

		List<Map<String, Object>> list = xgfwgDao.dataGrid2(mapData);
		int getTotalCount = xgfwgDao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	//添加
	public void addInfo(AQGL_RelevantViolation entity) {
		xgfwgDao.save(entity);
	}

	//修改
	public void updateInfo(AQGL_RelevantViolation entity) {
		xgfwgDao.save(entity);
	}
	
	//根据相关单位id删除
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		xgfwgDao.deleteInfo(id);
	}

	//根据相关单位id查询
	public Map<String,Object> findById(Long id) {
		return xgfwgDao.findById(id);
	}
}
