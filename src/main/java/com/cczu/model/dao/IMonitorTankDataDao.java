package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

/**
 * 在线监控预警-储罐实时数据DAO
 * @author jason
 * @date 2017年6月15日
 */
public interface IMonitorTankDataDao {

	/**
     * 查询储罐实时数据监测
     * @return
     */
	public List<Map<String , Object>> dataGrid(Map<String, Object> mapData);
	
	/**
     * 查询储罐实时数据报警
     * @return
     */
	public List<Map<String , Object>> dataGrid2(String id1);
	
	/**
     * 查询报警的未处理的气体浓度报警信息
     * @return
     */
	public List<Map<String , Object>> dataGrid3();

	/**
     * 根据储罐id查询是否存在以前未解决的储罐报警信息
     * @return
     */
	public List<Map<String , Object>> findbj(Map<String, Object> mapData);
	
	/**
	 * 根据企业id查询储罐实时数据监测
	 * @param qyid 企业id
	 * @return
	 */
	public List<Map<String , Object>> dataGridByQyid(Long qyid);
	
	/**
     * 统计储罐实时数据记录数
     * @return
     */
	public int getTotalCount(Map<String, Object> mapData);
	
	/**
     * 根据企业id查询储罐实时数据监测数据
     * @return
     */
	public List<Map<String , Object>> findInfoById(long id);
	
	/**
	 * 获得导出数据
	 * @return
	 */
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData);
	
	
	/**
     * 查询所有储罐最新数据(液位超标数据)
     * @return
     */
	public List<Map<String, Object>> findLastOverData();
	
	
	/**
     * 接入储罐实时数据的企业
     * @return
     */
	public List<Map<String, Object>> qyList(Map<String, Object> mapData);

	
	/**
	 * 大数据分析物料实时数据
	 * @return
	 */
	public List<Map<String,Object>> getMatSsDate(Map<String, Object> mapData);
	/**
	 * 大数据分析物料历史波动数据
	 * @return
	 */
	public List<Map<String,Object>> getMatLsbdDate(Map<String, Object> mapData);
	/**
	 * 大数据分析吞吐量数据
	 * @return
	 */
	public List<Map<String,Object>> getMatTtlDate(Map<String, Object> mapData);
	/**
	 * 大数据分析吞吐量数据(通过时间统计)
	 * @return
	 */
	public List<Map<String,Object>> getMatTtlDateByTime(Map<String, Object> mapData);

	/**
	 * 接入储罐实时数据的企业app
	 * @param str1
	 * @return
	 */
	public List<Map<String, Object>> getqylist(Map<String, Object> mapData);

}
