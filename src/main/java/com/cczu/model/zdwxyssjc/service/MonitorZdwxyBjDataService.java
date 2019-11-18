package com.cczu.model.zdwxyssjc.service;

import com.cczu.model.zdwxyssjc.dao.MonitorZdwxyBjDataDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  在线监控预警-报警信息Service
 *
 */
@Service("MonitorZdwxyBjDataService")
public class MonitorZdwxyBjDataService {

	@Resource
	private MonitorZdwxyBjDataDao monitorZdwxyBjDataDao;

	/**
	 * 储罐报警信息分页查询
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=monitorZdwxyBjDataDao.dataGrid(mapData);
		int getTotalCount=monitorZdwxyBjDataDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 储罐报警信息分页查询
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> cgDataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=monitorZdwxyBjDataDao.cgDataGrid(mapData);
		int getTotalCount=monitorZdwxyBjDataDao.getCgTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 气体浓度报警信息分页查询
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> ndDataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=monitorZdwxyBjDataDao.ndDataGrid(mapData);
		int getTotalCount=monitorZdwxyBjDataDao.getNdTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 高危工艺报警信息分页查询
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> gwgyDataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=monitorZdwxyBjDataDao.gwgyDataGrid(mapData);
		int getTotalCount=monitorZdwxyBjDataDao.getGwgyTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

}
