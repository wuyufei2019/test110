package com.cczu.model.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.ERM_EmergencyContactsEntity;


public interface IErmTxlService {
	/**
	 * 添加通讯录信息
	 * @param erm
	 */
	public void addInfo(ERM_EmergencyContactsEntity erm);
	
	/**
	 * 修改
	 * @param erm
	 */
	public void updateInfo(ERM_EmergencyContactsEntity erm);
	
	/**
	 * 删除
	 * @param erm
	 */
	public void deleteInfo(long id);
	
	/**
	 * 查询列表
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> map);
	
	/**
	 * 通过id查通讯录信息
	 * @param id
	 * @return
	 */
	public ERM_EmergencyContactsEntity findById(Long id);

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
