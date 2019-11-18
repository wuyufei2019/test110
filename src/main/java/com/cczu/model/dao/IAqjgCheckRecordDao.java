package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.AQJD_CheckRecordEntity;

public interface IAqjgCheckRecordDao {
	/**
	 * 添加信息
	 * @param sfr
	 * @return 返回影响记录条数
	 */
	public Long addInfoReturnID(AQJD_CheckRecordEntity cre);
	

	/**
	 * 企业分页查询信息
	 * @return 
	 */
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) ;
	
	/**
     * 查询数据表格_总记录数
     * @return
     */
	public int getTotalCount(Map<String, Object> mapData);
	
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
	public AQJD_CheckRecordEntity findInfoById(long id);
	
	/**
     * 根据id查找件信息
     * @param id
     * @return AQJD_CheckPlanEntity
     */
	public Map<String,Object> findInfoById2(long id);	
	
	/**
	 * 修改信息
	 * @param id
	 * @return int 影响条数
	 */
	public void updateInfo(AQJD_CheckRecordEntity cre);
	
	/**
	 * 安监检查更改checkflag
	 */
	public void updateCheckFlag(String flag,long id);
	
	}


