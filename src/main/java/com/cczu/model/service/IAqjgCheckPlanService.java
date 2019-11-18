package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.AQJD_CheckPlanEntity;
/**
 * 安全文件发布service接口
 * @author jason
 *
 */
public interface IAqjgCheckPlanService {
	
	
	/**
	 * 添加信息
	 * @param sfr
	 * @return  返回影响条数
	 */
	public Long addInfoReturnID(AQJD_CheckPlanEntity cpe);
	
	/**
	 * 分页查询信息
	 * @return 
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) ;
	/**
	 * 分页查询信息2
	 * @return 
	 */
	public Map<String, Object> dataGrid2(Map<String, Object> mapData) ;
	
	/**
	 * 删除信息
	 * @param id
	 * @return int 影响条数
	 */
	public void deleteInfo(long id);
	/**
     * 根据id查找信息
     * @param id
     * @return AQJD_CheckPlanEntity
     */
	public AQJD_CheckPlanEntity findInfoById(long id);
	/**
	 * 修改信息
	 * @param id
	 * @return int 影响条数
	 */
	public void updateInfo(AQJD_CheckPlanEntity c);
	/**
	 * 获取年份
	 * @param id
	 * @return int 影响条数
	 */
	public Object[] getMaxYearAndMinYear();
	/**
	 * 获取年份和检查数量
	 * @param id
	 * @return int 影响条数
	 */
	public List<Map<String,Object>> getYearDate();
	/**
	 * 获取年份和检查数量
	 * @param id
	 * @return int 影响条数
	 */
	public List<Object[]> getYearDate2(Map<String, Object> map);
	/**
	 * 获取计划列表
	 */
	public List<Map<String, Object>> findCheckPlanList(String xzqy);
	
	/**
	 * 获取qyids字段
	 * @param id
	 */
	public String getqyids(long id);
	
}
