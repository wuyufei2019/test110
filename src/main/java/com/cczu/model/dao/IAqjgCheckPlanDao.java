package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.AQJD_CheckPlanEntity;

public interface IAqjgCheckPlanDao {
	/**
	 * 添加信息
	 * @param sfr
	 * @return 返回影响记录条数
	 */
	public Long addInfoReturnID(AQJD_CheckPlanEntity cpe);
	
	/**
	 * 分页查询信息
	 * @return 
	 */
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) ;
	/**
	 * 分页查询信息2(用于统计分析)
	 * @return 
	 */
	public List<Map<String,Object>> dataGrid2(Map<String, Object> mapData) ;
	
	/**
     * 查询数据表格_总记录数
     * @return
     */
	public int getTotalCount(Map<String, Object> mapData);
	/**
     * 查询数据表格_总记录数(echarts)
     * @return
     */
	public int getTotalCount2(Map<String, Object> mapData);
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
	public AQJD_CheckPlanEntity findInfoById(long id);
	
	
	/**
	 * 修改信息
	 * @param id
	 * @return int 影响条数
	 */
	public void updateInfo(AQJD_CheckPlanEntity cpe);
	
	/**
	 * 获取日期
	 */
	public Object[] getMaxYearAndMinYear();
	/**
	 * 获取计划列表
	 */
	public List<Map<String, Object>> findCheckPlanList(String xzqy);
	/**
	 * 获取年份和每年数据
	 */
	public  List<Map<String,Object>> getYearDate();
	/**
	 * 获取年份和每年数据
	 */
	public  List<Object[]> getYearDate2(Map<String, Object> map);
	/**
	 * 获取qyids字段
	 * @param id
	 */
	public String getqyids(long id);
	
	}


