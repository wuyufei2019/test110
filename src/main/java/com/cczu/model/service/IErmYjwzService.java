package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.ERM_EmergencyMateEntity;

public interface IErmYjwzService {
	
	/**
	 * 根据userID查应急物资信息
	 * @param userid
	 * @return
	 */
	public List<ERM_EmergencyMateEntity> findAll();
	
	/**
	 * 添加应急物资信息
	 * @param erm
	 */
	public void addInfo(ERM_EmergencyMateEntity erm);
	
	/**
	 * 修改
	 * @param erm
	 */
	public void updateInfo(ERM_EmergencyMateEntity erm);
	
	/**
	 * 删除
	 * @param erm
	 */
	public void deleteInfo(long id);
	
	public String content(Map<String, Object> mapData);

	public Map<String, Object> dataGrid(Map<String, Object> map);
	
	/**
	 * 通过id查应急物资信息
	 * @param id
	 * @return
	 */
	public ERM_EmergencyMateEntity findById(Long id);




	/**
	 * 导出Excel
	 * @return
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);
	
	/**
	 * 事故计算关联应急物资查询
	 * @param mapData
	 * @return
	 */
	public String findYjwz(Map<String, Object> mapData);

	/**
	 * 导入
	 */
	public Map<String,Object> exinExcel(Map<String, Object> map);
	
	/**
	 * 在地图中显示
	 * @return
	 */
	public List<Map<String, Object>> findMapList(Map<String, Object> mapdata);
	
	/**
	 * 获取接入企业数
	 * @param map
	 * @return
	 */
	public String getqylistapp(Map<String, Object> map);
}
