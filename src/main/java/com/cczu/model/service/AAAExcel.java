package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public interface AAAExcel {
	
	/**
	 * excel导出
	 * @param fileName
	 * @param Title
	 * @param listContent
	 * @param response
	 */
	public void export(int num, String fileName,String[] Title, List<Map<String, Object>> listContent, HttpServletResponse response);

}
