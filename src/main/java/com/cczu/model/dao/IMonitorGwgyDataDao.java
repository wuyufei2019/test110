package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

/**
 * 在线监控预警-高危工艺实时数据DAO
 * @author jason
 * @date 2017年6月15日
 */
public interface IMonitorGwgyDataDao {

	/**
     * 查询高危工艺实时数据监测
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
     * 根据高危工艺id查询是否存在以前未处理的高危工艺报警信息
     * @return
     */
	public List<Map<String , Object>> findbj(Map<String, Object> mapData);
	
	/**
     * 统计高危工艺实时数据记录数
     * @return
     */
	public int getTotalCount(Map<String, Object> mapData);
	
	/**
     * 根据企业id查询高危工艺实时数据监测数据
     * @return
     */
	public List<Map<String , Object>> findInfoById(long id);
	
	/**
	 * 获得导出数据
	 * @return
	 */
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData);
	
	
	/**
     * 查询所有高危工艺最新数据(液位超标数据)
     * @return
     */
	public List<Map<String, Object>> findLastOverData();
	
	
	/**
     * 接入高危工艺实时数据的企业
     * @return
     */
	public List<Map<String, Object>> qyList(Map<String, Object> mapData);

	/**
	 * 接入高危工艺实时数据的企业app
	 * @param str1
	 * @return
	 */
	public List<Map<String, Object>> getqyList(Map<String, Object> mapData);
}
