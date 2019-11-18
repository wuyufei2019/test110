package com.cczu.model.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.XWAQ_ObservationsEntity;


public interface IXwaqGcjlService {
	/**
	 * 添加观察记录
	 * @param xwaq
	 */
	public void addInfo(XWAQ_ObservationsEntity xwaq);
	
	/**
	 * 修改
	 * @param xwaq
	 */
	public void updateInfo(XWAQ_ObservationsEntity xwaq);
	
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
	 * 通过id查观察记录
	 * @param id
	 * @return
	 */
	public XWAQ_ObservationsEntity findById(Long id);
	
	/**
	 * 通过id查观察记录
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
