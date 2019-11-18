package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public interface IMonitorGwgyDataService {
	/**
     * 查询高危工艺实时数据监测
     * @return
     */
	public Map<String, Object> dataGrid(Map<String, Object> mapData);
	
	/**
     * 添加高危工艺报警信息
     * @return
     */
	public void addbj(); 	 
	
	/**
     * 根据企业id查询高危工艺实时数据监测数据
     * @return
     */
	public List<Map<String , Object>> findInfoById(long id);
	
	/**
	 * 获得导出数据
	 * @return
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);
	
	
	/**
     * 查询所有高危工艺最新数据(液位超标数据),插入到报警信息表
     * @return
     */
	public void findLastOverData();
	
	
	/**
     * 接入高危工艺实时数据的企业
     * @return
     */
	public String qyListJson(Map<String, Object> mapData);

	/**
	 * 接入高危工艺实时数据的企业app
	 * @param str1
	 * @return
	 */
	public String getqylist(Map<String, Object> mapData);
}
