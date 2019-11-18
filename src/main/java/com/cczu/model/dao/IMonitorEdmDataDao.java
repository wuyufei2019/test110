package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

/**
 * 在线监控预警----二道门人员数据DAO
 * @author zpc
 * @date 2017年7月3日
 */
public interface IMonitorEdmDataDao {

	/**
	 * 查询二道门人员数据
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
     * 统计二道门人员数据记录数
     * @return
     */
	public int getTotalCount(Map<String, Object> mapData);
	
	/**
     * 接入二道门人员数据的企业
     * @return
     */
	public List<Map<String, Object>> qyList(Map<String, Object> mapData);
	/**
	 * 获取地图json
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> findMapList(Map<String, Object> mapData);
	
	/**
	 * 接入二道门人员数据的企业app
	 * @param tmap
	 * @return
	 */
	public List<Map<String, Object>> getqylistapp(Map<String, Object> tmap);

	/**
	 * 根据企业id查看app
	 * @param parseLong
	 * @return
	 */
	public List<Map<String, Object>> findInfoById(long qyid);
}
