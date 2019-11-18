package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.ERM_EmergencyResPlaceEntity;

public interface IErmBncsDao {
	
	/**
	 * 查询所有避难场所信息
	 * @return
	 */
	public List<ERM_EmergencyResPlaceEntity> findAllInfo();
	
	/**
	 * 添加避难场所信息
	 * @param erm
	 */
	public void addInfo(ERM_EmergencyResPlaceEntity erm);
	
	/**
	 * 修改避难场所信息
	 * @param erm
	 */
	public void updateInfo(ERM_EmergencyResPlaceEntity erm);
	
	/**
	 * 删除避难场所信息
	 * @param id
	 */
	public void deleteInfo(Long id);
	
	/**
	 * 查询数据
	 * @param mapData
	 * @return
	 */
	public List<ERM_EmergencyResPlaceEntity> dataGrid(Map<String,Object> mapData);
	
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
	 * 通过id查找避难场所信息
	 * @param id
	 * @return
	 */
	public ERM_EmergencyResPlaceEntity findById(Long id);


	/**
	 * 获得导出数据
	 * @return
	 */
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData);
	
	/**
	 * 查询所有信息(APP)
	 * @return
	 */
	public List<Map<String, Object>> findInfoForApp(Map<String, Object> mapData);
	/**
	 * 在地图中显示
	 * @return
	 */
	public List<Map<String, Object>> findMapList(Map<String, Object> mapdata);
}
