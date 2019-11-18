package com.cczu.model.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.AQJG_SafetyRecord;

public interface IErmYjyaglService {

	public Map<String, Object> dataGrid(Map<String, Object> map);
	
	/**
	 * 通过id查应急预案管理
	 * @param id
	 * @return
	 */
	public AQJG_SafetyRecord findById(Long id);

	/**
	 * 导出Excel
	 * @return
	 */
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, Map<String, Object> mapData);

	/**
	 * 获取接入企业数
	 * @param map
	 * @return
	 */
	public String getqylistapp(Map<String, Object> map);
}
