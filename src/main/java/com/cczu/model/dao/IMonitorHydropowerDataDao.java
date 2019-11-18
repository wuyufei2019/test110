package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

public interface IMonitorHydropowerDataDao {

	/**
	 * 气电水采集
	 * @param mapData
	 * @return
	 */
	public List<Map<String , Object>> dataGrid(Map<String, Object> mapData);
	
	/**
	 * 获得导出数据
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getExport(Map<String, Object> mapData);
	
	/**
     * 气电水数据记录数
     * @return
     */
	public int getTotalCount(Map<String, Object> mapData);
	
	/**
     * 接入气电水数据的企业
     * @return
     */
	public List<Map<String, Object>> qyList(Map<String, Object> mapData);
}
