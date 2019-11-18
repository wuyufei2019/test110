package com.cczu.model.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public interface IErmYjczjsService {
	/**
	 * 查询列表数据
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> map);
	
	/**
	 * 通过id查应急处置技术信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> findById(Long id);

	/**
	 * 导出Excel
	 * @return
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);




}
