package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.ERM_EmergencyMateEntity;

public interface IErmYjwzDao {
	
	/**
	 * 查询所有应急物资信息
	 * @return
	 */
	public List<ERM_EmergencyMateEntity> findAllInfo();
	
	/**
	 * 添加应急物资信息
	 * @param erm
	 */
	public void addInfo(ERM_EmergencyMateEntity erm);
	
	/**
	 * 修改应急物资信息
	 * @param erm
	 */
	public void updateInfo(ERM_EmergencyMateEntity erm);
	
	/**
	 * 删除应急物资信息
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
	 * @param mapData
	 * @return
	 */
	public String content(Map<String, Object> mapData);
	
	/**
	 * 
	 * 通过id查找应急物资信息
	 * @param id
	 * @return
	 */
	public ERM_EmergencyMateEntity findById(Long id);
	
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
	 * 查询企业信息
	 * @return
	 */
	public List<Map<String, Object>> findInfoByQyid(Long qyid);
	/**
	 * 在地图中显示
	 * @return
	 */
	public List<Map<String, Object>> findMapList(Map<String, Object> mapdata);
	
	/**
	 * app查询接入企业数
	 * @param tmap
	 * @return
	 */
	public List<Map<String, Object>> getqylistapp(Map<String, Object> tmap);
}
