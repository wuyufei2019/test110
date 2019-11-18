package com.cczu.model.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.XWAQ_UnsafebehaviorEntity;


public interface IXwaqBaqxwglService {
	/**
	 * 添加不安全行为管理
	 * @param xwaq
	 */
	public void addInfo(XWAQ_UnsafebehaviorEntity xwaq);
	
	/**
	 * 修改
	 * @param xwaq
	 */
	public void updateInfo(XWAQ_UnsafebehaviorEntity xwaq);
	
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
	 * 通过id查不安全行为管理
	 * @param id
	 * @return
	 */
	public XWAQ_UnsafebehaviorEntity findById(Long id);

	/**
	 * 导出Excel
	 * @return
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);




}
