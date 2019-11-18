package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.AQJG_SafetyRecord;

public interface IErmYjyaglDao {
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
	 * 通过id查找应急预案管理信息
	 * @param id
	 * @return
	 */
	public AQJG_SafetyRecord findById(Long id);

	/**
	 * 应急预案管理
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData);
	
	/**
	 * 应急预案管理
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> findInfoForApp(Map<String, Object> mapData);
	
	/**
	 * app查询接入企业数
	 * @param tmap
	 * @return
	 */
	public List<Map<String, Object>> getqylistapp(Map<String, Object> tmap);

}
