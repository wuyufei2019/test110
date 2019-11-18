package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.AQJG_DSFPjEntity;

public interface IAqjgPjService {
	/**
	 * 查询第三方评价记录list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData);

	/**
	 * 删除第三方评价
	 * @param parseLong
	 */
	public void deleteInfo(long id);

	/**
	 * 导出
	 * @param response
	 * @param map
	 */
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData);

	/**
	 * 获取未评价第三方
	 * @return
	 */
	public List<Map<String, Object>> getdname();

	/**
	 * 添加第三方评价
	 * @param pjlist
	 */
	public void addInfo(AQJG_DSFPjEntity pjlist);

	/**
	 * 通过id查找
	 * @param id
	 * @return
	 */
	public AQJG_DSFPjEntity findById(Long id);

	/**
	 * 修改
	 * @param pjlist
	 */
	public void updateInfo(AQJG_DSFPjEntity pjlist);

	/**
	 * 获得年份
	 * @return
	 */
	public List<Map<String, Object>> findNdList();

	public List<Map<String, Object>> getdname2();

}
