package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.ERM_EmergencyResInstrumentEntity;

public interface IErmYjzbDao {
	
	/**
	 * 查询所有应急装备信息
	 * @return
	 */
	public List<ERM_EmergencyResInstrumentEntity> findAllInfo();
	
	/**
	 * 添加应急装备信息
	 * @param erm
	 */
	public void addInfo(ERM_EmergencyResInstrumentEntity erm);
	
	/**
	 * 修改应急装备信息
	 * @param erm
	 */
	public void updateInfo(ERM_EmergencyResInstrumentEntity erm);
	
	/**
	 * 删除应急装备信息
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
	 * 通过id查找应急装备信息
	 * @param id
	 * @return
	 */
	public ERM_EmergencyResInstrumentEntity findById(Long id);
	
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
