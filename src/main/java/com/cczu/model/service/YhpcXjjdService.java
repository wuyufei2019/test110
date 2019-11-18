package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.YhpcXjjdDao;
import org.springframework.transaction.annotation.Transactional;

/**
 * 巡检监督与考核
 * @author ll
 */
@Transactional(readOnly=true)
@Service("YhpcXjjdService")
public class YhpcXjjdService {

	@Resource
	private YhpcXjjdDao yhpcXjjdDao;

	/**
	 * 考核结果list(安监端)
	 * @param map
	 * @return
	 */
	public Map<String, Object> khdataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=yhpcXjjdDao.khdataGrid(mapData);
		int getTotalCount=yhpcXjjdDao.getzjTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 获取巡检轨迹数据
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getXjgjData(String time,String xjry,String xjbc) {
		List<Map<String,Object>> list=yhpcXjjdDao.getXjgjData(time, xjry, xjbc);
		return list;
	}
	
	/**
	 * 考核结果list(企业端)
	 * @param map
	 * @return
	 */
	public Map<String, Object> khdataGrid2(Map<String, Object> mapData) {
		List<Map<String,Object>> list=yhpcXjjdDao.khdataGrid2(mapData);
		int getTotalCount=yhpcXjjdDao.getzjTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 定时插入巡检监督考核信息
	 * @param map
	 * @return
	 */
	public void inserttimekh(Map<String, Object> mapData) {
		yhpcXjjdDao.deletetimekh();//先删除
		yhpcXjjdDao.inserttimekh(mapData);//再插入
	}

	/**
	 * 考核结果list(按人员)
	 * @param map
	 * @return
	 */
	public Map<String, Object> timekhdataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=yhpcXjjdDao.timekhdataGrid(mapData);
		int getTotalCount=yhpcXjjdDao.getzjTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 考核结果list(隐患点)
	 * @param map
	 * @return
	 */
	public Map<String, Object> khdataGrid3(Map<String, Object> mapData) {
		List<Map<String,Object>> list=yhpcXjjdDao.khdataGrid3(mapData);
		int getTotalCount=yhpcXjjdDao.getzjTotalCount3(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 考核结果list(风险点)
	 * @param map
	 * @return
	 */
	public Map<String, Object> khdataGrid4(Map<String, Object> mapData) {
		List<Map<String,Object>> list=yhpcXjjdDao.khdataGrid4(mapData);
		int getTotalCount=yhpcXjjdDao.getzjTotalCount4(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 考核结果list(任务名)
	 * @param map
	 * @return
	 */
	public Map<String, Object> khdataGrid5(Map<String, Object> mapData) {
		List<Map<String,Object>> list=yhpcXjjdDao.khdataGrid5(mapData);
		int getTotalCount=yhpcXjjdDao.getzjTotalCount5(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 巡检监督记录list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=yhpcXjjdDao.dataGrid(mapData);
		int getTotalCount=yhpcXjjdDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
}
