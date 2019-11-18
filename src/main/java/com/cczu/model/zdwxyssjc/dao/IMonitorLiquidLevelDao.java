package com.cczu.model.zdwxyssjc.dao;


import java.util.List;
import java.util.Map;

import com.cczu.model.zdwxyssjc.entity.Main_SignalLiquidLevelEntity;

/**
 * 在线监控预警----液位传感器数据DAO
 * @author zpc
 * @date 2017年7月3日
 */
public interface IMonitorLiquidLevelDao {

	/**
	 * 查询液位传感器数据
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
     * 统计液位传感器数据记录数
     * @return
     */
	public int getTotalCount(Map<String, Object> mapData);
	
	/**
     * 接入液位传感器数据的企业
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
	 * 接入液位传感器数据的企业app
	 * @param tmap
	 * @return
	 */
	public List<Map<String, Object>> getqylistapp(Map<String, Object> tmap);

	/**
	 * 根据企业id查看app
	 * @param qyid
	 * @return
	 */
	public List<Map<String, Object>> findInfoByQyid(long qyid);

	/**
	 * 添加信息
	 * @param entity
	 * @return
	 */
	public Long addInfo(Main_SignalLiquidLevelEntity entity);

}
