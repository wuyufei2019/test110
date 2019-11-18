package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public interface IMonitorGasDataService {
	
	/**
     * 气体浓度信号采集
     * @return
     */
	public Map<String, Object> dataGrid(Map<String, Object> mapData);

	
	/**
     * 添加气体浓度报警信息
     * @return
     */
	public void addbj(); 
	
	/**
	 * 导出excel
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);

	/**
     * 接入气体浓度信号采集数据的企业
     * @return
     */
	public String qyListJson(Map<String, Object> mapData);

	/**
     * 根据企业id查询气体浓度实时数据监测数据
     * @return
     */
	public List<Map<String , Object>> findInfoById(long id);
	
	/**
     * 接入气体浓度信号采集数据的企业app
     * @return
     */
	public String getqylist(Map<String, Object> mapData);
}
