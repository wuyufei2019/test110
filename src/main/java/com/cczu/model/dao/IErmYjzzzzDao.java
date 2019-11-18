package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.ERM_EmergencyOrgEntity;

public interface IErmYjzzzzDao {
	
	/**
	 * 查询所有应急组织职责信息
	 * @return
	 */
	public List<ERM_EmergencyOrgEntity> findAllInfo();
	
	/**
	 * 添加应急组织职责信息
	 * @param erm
	 */
	public void addInfo(ERM_EmergencyOrgEntity erm);
	
	/**
	 * 修改应急组织职责信息
	 * @param erm
	 */
	public void updateInfo(ERM_EmergencyOrgEntity erm);
	
	/**
	 * 删除应急组织职责信息
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
	 * 通过id查找应急组织职责信息
	 * @param id
	 * @return
	 */
	public ERM_EmergencyOrgEntity findById(Long id);
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
	 * app查询接入企业数
	 * @param tmap
	 * @return
	 */
	public List<Map<String, Object>> getqylistapp(Map<String, Object> tmap);
}
