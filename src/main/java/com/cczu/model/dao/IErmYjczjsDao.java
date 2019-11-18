package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.ERM_EmergencyDispTechnologyEntity;

public interface IErmYjczjsDao {
	
	/**
	 * 查询所有应急处置技术信息
	 * @return
	 */
	public List<ERM_EmergencyDispTechnologyEntity> findAllInfo();
	
	/**
	 * 添加应急处置技术信息
	 * @param erm
	 */
	public void addInfo(ERM_EmergencyDispTechnologyEntity erm);
	
	/**
	 * 修改应急处置技术信息
	 * @param erm
	 */
	public void updateInfo(ERM_EmergencyDispTechnologyEntity erm);
	
	/**
	 * 删除应急处置技术信息
	 * @param id
	 */
	public void deleteInfo(Long id);
	
	/**
	 * 查询数据
	 * @param mapData
	 * @return
	 */
	public List<ERM_EmergencyDispTechnologyEntity> dataGrid(Map<String,Object> mapData);
	
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
	 * 通过id查找应急处置技术信息
	 * @param id
	 * @return
	 */
	public ERM_EmergencyDispTechnologyEntity findById(Long id);
	
	/**
	 * 获得导出数据
	 * @return
	 */
	public List<ERM_EmergencyDispTechnologyEntity> getExcel(Map<String, Object> mapData);
	
	/**
	 * 查询所有信息(APP)
	 * @return
	 */
	public List<Map<String, Object>> findInfoForApp(Map<String, Object> mapData);
}
