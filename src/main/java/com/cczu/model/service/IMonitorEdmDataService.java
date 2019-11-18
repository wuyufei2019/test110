package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public interface IMonitorEdmDataService {
	
	/**
     * 查询二道门人员数据
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
     * 接入二道门人员数据的企业
     * @return
     */
	public String qyListJson(Map<String, Object> mapData);
	/**
	 * 获取地图json
	 */
	public List<Map<String,Object>> findMapList(Map<String, Object> mapData);

	/**
     * 接入二道门人员数据的企业app
     * @return
     */
	public String getqylist(Map<String, Object> tmap);

	/**
	 * 根据企业id查看app
	 * @param parseLong
	 * @return
	 */
	public List<Map<String , Object>> findInfoById(long parseLong);
}
