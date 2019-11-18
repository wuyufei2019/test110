package com.cczu.model.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.AQZF_SafetyCheckItemEntity;

public interface IAqzfJcbkService {

	/**
	 * 查询检查表库list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData);

	/**
	 * 添加信息
	 * @param jcbk
	 */
	public void addInfo(AQZF_SafetyCheckItemEntity jcbk);

	/**
	 * 删除
	 * @param parseLong
	 */
	public void deleteInfo(long parseLong);
	
	/**
	 * 导出
	 * @param response
	 * @param map
	 */
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> map);

	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public AQZF_SafetyCheckItemEntity findById(Long id);

	/**
	 * 修改
	 * @param jcbk
	 */
	public void updateInfo(AQZF_SafetyCheckItemEntity jcbk);
}
