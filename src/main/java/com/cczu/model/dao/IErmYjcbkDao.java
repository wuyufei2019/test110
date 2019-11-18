package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.ERM_EmergencyRepositoryEntity;

public interface IErmYjcbkDao {
	
	/**
	 * 查询所有应急储备库信息
	 * @return
	 */
	public List<ERM_EmergencyRepositoryEntity> findAllInfo();
	
	/**
	 * 添加应急储备库信息
	 * @param erm
	 */
	public void addInfo(ERM_EmergencyRepositoryEntity erm);
	
	/**
	 * 修改应急储备库信息
	 * @param erm
	 */
	public void updateInfo(ERM_EmergencyRepositoryEntity erm);
	
	/**
	 * 删除应急储备库信息
	 * @param id
	 */
	public void deleteInfo(Long id);
	
	/**
	 * 查询数据
	 * @param mapData
	 * @return
	 */
	public List<ERM_EmergencyRepositoryEntity> dataGrid(Map<String,Object> mapData);
	
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
	 * 通过id查找应急储备库信息
	 * @param id
	 * @return
	 */
	public ERM_EmergencyRepositoryEntity findById(Long id);
	
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
