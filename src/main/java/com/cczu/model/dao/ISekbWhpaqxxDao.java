package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.TMESK_ChemicalsdirectoryEntity;

public interface ISekbWhpaqxxDao {
	
	/**
	 * 查询数据
	 * @param mapData
	 * @return
	 */
	public List<TMESK_ChemicalsdirectoryEntity> dataGrid(Map<String,Object> mapData);
	
	/**
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData);

	/**
	 * @param mapData
	 * @return
	 */
	public String content(Map<String, Object> mapData);
	
	/**
	 * 
	 * 通过id查找危化品安全信息信息
	 * @param id
	 * @return
	 */
	public TMESK_ChemicalsdirectoryEntity findById(Long id);
	
	/**
	 * 获得导出数据
	 * @return
	 */
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData);

}
