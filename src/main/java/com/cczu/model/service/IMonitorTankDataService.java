package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public interface IMonitorTankDataService {
	/**
     * 查询储罐实时数据监测
     * @return
     */
	public Map<String, Object> dataGrid(Map<String, Object> mapData);
	
	/**
     * 添加储罐报警信息
     * @return
     */
	public void addbj(); 
	
	/**
     * 根据企业id查询储罐实时数据监测数据
     * @return
     */
	public List<Map<String , Object>> findInfoById(long id);
	
	/**
	 * 获得导出数据
	 * @return
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);
	
	
	/**
     * 查询所有储罐最新数据(液位超标数据),插入到报警信息表
     * @return
     */
	public void findLastOverData();
	
	
	/**
     * 接入储罐实时数据的企业
     * @return
     */
	public String qyListJson(Map<String, Object> mapData);
	
	/**
	 * 根据企业id查询储罐实时数据监测
	 * @param qyid 企业id
	 * @return
	 */
	public List<Map<String , Object>> dataGridByQyid(Long qyid);
	/**
	 * 大数据分析 -实时监测物料信息
	 * @param qyid 企业id
	 * @return
	 */
	public List<Map<String,Object>> getMatSsDate(Map<String, Object> mapData);
	/**
	 * 大数据分析 -统计物料历史波动数据
	 * @paramn
	 */
	public String getMatLsbdDate(Map<String, Object> mapData);
	/**
	 * 大数据分析 -统计物料历史波动数据
	 * @paramn
	 */
	public String getTtlDateByTime(Map<String, Object> mapData);
	/**
	 * 大数据分析 -统计物料历史波动数据
	 * @paramn
	 */
	public List<Map<String, Object>> getMatTtlDate(Map<String, Object> mapData);

	/**
	 * 接入储罐实时数据的企业app
	 * @param str1
	 * @return
	 */
	public String getqylist(Map<String, Object> mapData);
}
