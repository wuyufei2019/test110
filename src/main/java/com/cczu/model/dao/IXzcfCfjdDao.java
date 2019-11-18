package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.XZCF_CfjdInfoEntity;

public interface IXzcfCfjdDao {
	/**
	 * 添加信息
	 * @param sfr
	 * @return 返回影响记录条数
	 */
	public Long addInfoReturnID(XZCF_CfjdInfoEntity jce);
	
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
     * @return XZCF_JYCFCfjdEntity
     */
	public XZCF_CfjdInfoEntity findInfoById(long id);
	/**
     * 根据立案id查找件信息
     * @param id
     * @return XZCF_JYCFCfjdEntity
     */
	public XZCF_CfjdInfoEntity findInfoByLaId(long laid);
	
	
	/**
	 * 修改信息
	 * @param id
	 * @return int 影响条数
	 */
	public void updateInfo(XZCF_CfjdInfoEntity jce);
	public void updateLaspInfo(long id);
	
}



