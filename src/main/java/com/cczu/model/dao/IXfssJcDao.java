package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.XFSS_CheckEntity;

public interface IXfssJcDao {
	
	/**
	 * 添加消防设施检查信息
	 * @param id
	 */
	public Long addInfo(XFSS_CheckEntity obj);
	
	/**
	 * 删除消防设施检查信息
	 * @param id
	 */
	public void deleteInfo(Long id);
	
	/**
	 * 查询数据
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> dataGrid(Map<String,Object> mapData);
	
	/**
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData);
	
	/**
	 * 
	 * 通过id查找消防设施检查信息
	 * @param id
	 * @return
	 */
	public XFSS_CheckEntity findById(Long id);
	
	/**
	 * 
	 * 通过id查找消防设施检查信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> findByIdForView(Long id);
	
	/**
	 * 获得导出数据
	 * @return
	 */
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData);
	

}
