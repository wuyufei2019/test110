package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.TMESK_MsdsEntity;



public interface ISekbMsdsDao {
	
	/**
	 * 查询数据
	 * @param mapData
	 * @return
	 */
	public List<TMESK_MsdsEntity> dataGrid(Map<String,Object> mapData);
	
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
	 * 通过id查找MSDS信息
	 * @param id
	 * @return
	 */
	public TMESK_MsdsEntity findById(Long id);


	/**
	 * 获得导出数据
	 * @return
	 */
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData);

	
	/**
	 * 获得应急处置技术导出数据
	 * @return
	 */
	public List<Map<String, Object>> getExcelDataWhp(Map<String, Object> mapData);
	
	/**
	 * 根据M1查询list集合
	 * @param M1
	 * @return
	 */
	public List<TMESK_MsdsEntity> findByM1(String M1);
	
	public List<Map<String, Object>> findInfoForApp(Map<String, Object> mapData);
	List<Map<String,Object>> listAll();
}
