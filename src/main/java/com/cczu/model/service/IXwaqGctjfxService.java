package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;


public interface IXwaqGctjfxService {
	/**
	 * 查询列表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> dataGrid(Map<String, Object> map);

	/**
	 * 导出Excel
	 * @return
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);

	/**
	 * 查询企业部门
	 * @return
	 */
	public String findQybmList(Long qyid);

}
