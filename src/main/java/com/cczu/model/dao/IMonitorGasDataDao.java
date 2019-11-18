package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

public interface IMonitorGasDataDao {
	
	/**
	 * 气体浓度信号采集
	 * @param mapData
	 * @return
	 */
	public List<Map<String , Object>> dataGrid(Map<String, Object> mapData);
	
	/**
     * 查询气体浓度实时数据报警
     * @return
     */
	public List<Map<String , Object>> dataGrid2(String id1);
	
	/**
     * 查询报警的未处理的气体浓度报警信息
     * @return
     */
	public List<Map<String , Object>> dataGrid3();

	/**
     * 根据气体id查询是否存在以前未解决的气体报警信息
     * @return
     */
	public List<Map<String , Object>> findbj(Map<String, Object> mapData);
	
	/**
	 * 获得导出数据
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getExport(Map<String, Object> mapData);
	
	/**
     * 统计气体浓度信号采集数据记录数
     * @return
     */
	public int getTotalCount(Map<String, Object> mapData);
	
	/**
     * 接入气体浓度信号采集数据的企业
     * @return
     */
	public List<Map<String, Object>> qyList(Map<String, Object> mapData);

	/**
     * 根据企业id查询气体浓度实时数据监测数据
     * @return
     */
	public List<Map<String , Object>> findInfoById(long id);
	
	/**
     * 接入气体浓度信号采集数据的企业app
     * @return
     */
	public List<Map<String, Object>> getqyList(Map<String, Object> mapData);
}
