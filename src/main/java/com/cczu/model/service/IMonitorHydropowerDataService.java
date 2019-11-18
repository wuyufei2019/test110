package com.cczu.model.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public interface IMonitorHydropowerDataService {

	/**
     * 水电实时用量
     * @return
     */
	public Map<String, Object> dataGrid(Map<String, Object> mapData);
	
	/**
	 * 导出excel
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);

	/**
     * 接入水电气采集数据的企业
     * @return
     */
	public String qyListJson(Map<String, Object> mapData);
}
