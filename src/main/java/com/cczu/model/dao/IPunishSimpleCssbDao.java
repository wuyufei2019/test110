package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.XZCF_JycfCssbEntity;

public interface IPunishSimpleCssbDao {
	/**
	 * 添加信息
	 * @param sfr
	 * @return 返回影响记录条数
	 */
	public Long addInfoReturnID(XZCF_JycfCssbEntity jce);
	
	/**
	 * 分页查询信息
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
     * @return XZCF_JYCFCssbEntity
     */
	public XZCF_JycfCssbEntity findInfoById(long id);
	
	
	/**
	 * 修改信息
	 * @param id
	 * @return int 影响条数
	 */
	public void updateInfo(XZCF_JycfCssbEntity jce);
	
}



