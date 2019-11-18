package com.cczu.model.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.XFSS_CheckEntity;


public interface IXfssJcService {
	/**
	 * 删除
	 * @param xwaq
	 */
	public void deleteInfo(long id);
	
	/**
	 * 查询列表
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> map);
	
	/**
	 * 通过id查消防设施检查
	 * @param id
	 * @return
	 */
	public XFSS_CheckEntity findById(Long id);
	
	/**
	 * 通过id查消防设施检查
	 * @param id
	 * @return
	 */
	public Map<String,Object> findByIdForView(Long id);

	/**
	 * 导出Excel
	 * @return
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);




}
