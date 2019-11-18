package com.cczu.model.zdwxyssjc.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface IMonitorTemperatureService {
	
	/**
     * 查询温度传感器数据
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
     * 接入温度传感器数据的企业
     * @return
     */
	public String qyListJson(Map<String, Object> mapData);
	/**
	 * 获取地图json
	 */
	public List<Map<String,Object>> findMapList(Map<String, Object> mapData);

	/**
     * 接入温度传感器数据的企业app
     * @return
     */
	public String getqylist(Map<String, Object> tmap);

	/**
	 * 根据企业id查看app
	 * @param parseLong
	 * @return
	 */
	public List<Map<String , Object>> findInfoByQyid(long qyid);

	/**
	 * 添加实时数据
	 * @param request
	 * @return
	 */
	public String add(HttpServletRequest request);
}
