package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.AQJD_CheckRecordEntity;
/**
 * 安全文件发布service接口
 * @author jason
 *
 */
public interface IAqjgCheckRecordService {
	
	
	/**
	 * 添加信息
	 * @param sfr
	 * @return  返回影响条数
	 */
	public Long addInfoReturnID(AQJD_CheckRecordEntity cpe);
	

	
	/**
	 * 分页查询信息
	 * @return 
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) ;
	/**
	 * 安监分页查询信息
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
	public AQJD_CheckRecordEntity findInfoById(long id);
	
	/**
     * 根据id查找信息
     * @param id
     * @return AQJD_CheckPlanEntity
     */
	public Map<String, Object> findInfoById2(long id);
	
	/**
	 * 修改信息
	 * @param id
	 * @return int 影响条数
	 */
	public void updateInfo(AQJD_CheckRecordEntity c);
	/**
	 * 获取年份
	 * @param id
	 * @return int 影响条数
	 */
	/**
	 * 更新checkflag
	 * @param l
	 * @return int 影响条数
	 */
	public void updateCheckFlag(String flag,long l);
	
	/**
	 * 根据时间获取专项检查
	 * @param map
	 */
	public List<Map<String, Object>> getAjZxjcApp(Map<String, Object> map);
	
	/**
	 * 获取检查记录
	 * @param map
	 */
	public List<Map<String, Object>> getAjJcjlApp(Map<String, Object> map);
	
}
