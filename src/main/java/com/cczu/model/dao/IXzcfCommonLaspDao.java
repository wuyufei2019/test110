package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.XZCF_YbcfLaspEntity;

public interface IXzcfCommonLaspDao {
	/**
	 * 添加信息
	 * @param sfr
	 * @return 返回影响记录条数
	 */
	public Long addInfoReturnID(XZCF_YbcfLaspEntity yle);
	
	/**
	 * 分页查询信息
	 * @return 
	 */
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) ;
	/**
	 * 分页查询信息2(用于统计分析)
	 * @return 
	 */
	//public List<Map<String,Object>> dataGrid2(Map<String, Object> mapData) ;
	
	/**
     * 查询数据表格_总记录数
     * @return
     */
	public int getTotalCount(Map<String, Object> mapData);
	/**
     * 查询数据表格_总记录数(echarts)
     * @return
     */
	//public int getTotalCount2(Map<String, Object> mapData);
	/**
	 * 删除信息
	 * @param id
	 * @return int 影响条数
	 */
	public void deleteInfo(long id);
	/**
     * 根据id查找件信息
     * @param id
     * @return AQJD_CheckPlanEntity
     */
	public XZCF_YbcfLaspEntity findInfoById(long id);
	
	
	/**
	 * 修改信息
	 * @param id
	 * @return int 影响条数
	 */
	public void updateInfo(XZCF_YbcfLaspEntity yle);
	
	/**
	 * 获取待补全的总数
	 */
	public int getTempCount(Map<String, Object> mapData) ;
	/**
	 * 获取一条待补全的数据
	 */
	public XZCF_YbcfLaspEntity findTempInfo(String xzqy);
	/**
	 *  *获取所有和立案审批相关的number记录
	 * @param xzqy
	 * @return
	 */
	public  List<Object> getNumberlist(long id,String xzqy);
	
	/**
	 * 获取年份和每年数据
	 */
	//public  List<Map<String,Object>> getYearDate();
	/**
	 * 获取年份和每年数据
	 */
	//public  List<Object[]> getYearDate2(String year);
	
	}


