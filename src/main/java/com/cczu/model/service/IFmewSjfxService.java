package com.cczu.model.service;

import java.util.List;
import java.util.Map;

/**
 * @author jason
 * 储罐实时监测-大数据分析Service
 */
public interface IFmewSjfxService {

	/**
	 * 根据物料类别统计所有储量实时信息
	 */
	public List<Map<String, Object>> findAllInforByLeibie(String xzqy);
	
	
	/**
	 * 根据物料名称统计所有储量实时信息
	 */
	public List<Map<String, Object>> findAllInforByName(String xzqy);
	
	/**
	 * 根据物料类别统计所有 储罐储量 信息
	 */
	public List<Map<String, Object>> findAllCGInforByLeibie(String xzqy);
	
	/**
	 * 根据物料名称统计所有 储罐储量 信息
	 */
	public List<Map<String, Object>> findAllCGInforByName(String xzqy);
	
	/**
	 * 根据物料类别统计所有 仓库库存 信息
	 */
	public List<Map<String, Object>> findAllCKInforByLeibie(String xzqy);
	
	/**
	 * 根据物料名称统计所有 仓库库存 信息
	 */
	public List<Map<String, Object>> findAllCKInforByName(String xzqy);	
	
	/**
	 * 根据公司ID查询公司所有储罐绑定的数据采集通道id集合
	 */
	public List<Map<String, Object>> findChanelsByQYID(long id);
	
	/**
	 *  根据企业id获取该企业储罐历史记录
	 * @param id 企业id
	 * @param strattime  开始日期
	 * @param endtime   结束日期
	 * @return
	 */
	public String findHistoryDataByQyID(long id, String strattime,String endtime);
	
	
	/**
	 * 查询统计最近一天热力图数据
	 */
	public List<Map<String, Object>> selectDatesToHeatmap(Map<String,Object> map);
	
	/**
	 * 获取某天的热力图数据
	 * @param date 日期(yyyy-MM-dd)
	 * @param xzqy 行政区域
	 * @return
	 */
	public List<Map<String, Object>> selectDatesToHeatmap(String date,Map<String,Object> map);
	
	
	/**
	 * 根据物料类别统计储罐储量
	 * @param date 日期(yyyy-MM-dd hh:mm:ss)
	 * @param xzqy 行政区域
	 * @return
	 */
	public List<Object> findHistoryData(String date,String xzqy);
	
	/**
	 * 根据物料类别统计储罐某个时间间隔历史记录
	 * @param strattime 开始时间
	 * @param endtime 结束时间
	 * @return
	 */
	public String findHistoryDatesByWllb(Map<String, Object> mapData);

	/**
	 * 查询统计某个日期之后的所有热力图数据 
	 * @param date 日期(yyyy-MM-dd)
	 * @param xzqy 行政区域
	 * @return
	 */
	public String selectHeatmapData(String date,Map<String,Object> map);
}
