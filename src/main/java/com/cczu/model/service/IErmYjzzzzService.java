package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.ERM_EmergencyOrgEntity;


public interface IErmYjzzzzService {
	
	/**
	 * 根据userID查应急组织职责信息
	 * @param userid
	 * @return
	 */
	public List<ERM_EmergencyOrgEntity> findAll();
	
	/**
	 * 添加应急组织职责信息
	 * @param erm
	 */
	public void addInfo(ERM_EmergencyOrgEntity erm);
	
	/**
	 * 修改
	 * @param erm
	 */
	public void updateInfo(ERM_EmergencyOrgEntity erm);
	
	/**
	 * 删除
	 * @param erm
	 */
	public void deleteInfo(long id);
	
	public String content(Map<String, Object> mapData);

	public Map<String, Object> dataGrid(Map<String, Object> map);
	
	/**
	 * 通过id查应急组织职责信息
	 * @param id
	 * @return
	 */
	public ERM_EmergencyOrgEntity findById(Long id);


	/**
	 * 导出Excel
	 * @return
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);

	/**
	 * 导入
	 */
	public Map<String,Object> exinExcel(Map<String, Object> map);
	
	/**
	 * 获取接入企业数
	 * @param map
	 * @return
	 */
	public String getqylistapp(Map<String, Object> map);
}
