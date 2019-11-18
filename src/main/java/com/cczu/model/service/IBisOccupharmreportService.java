package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.BIS_OccupharmExamReportEntity;

public interface IBisOccupharmreportService {

	public Map<String, Object> dataGrid(Map<String, Object> mapData);
	
	/**
	 * @param qyid
	 * @return
	 */
	public List<BIS_OccupharmExamReportEntity> findAll(Long qyid);
	
	/**
	 * @param bis
	 */
	public void addInfo(BIS_OccupharmExamReportEntity bis);
	
	/**
	 * @param id
	 * @return
	 */
	public BIS_OccupharmExamReportEntity findById(Long id);
	
	/**
	 * @param bis
	 */
	public void updateInfo(BIS_OccupharmExamReportEntity bis);
	
	/**
	 * @param id
	 */
	public void deleteInfo(Long id);
	
	/**
	 * 导出excel
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);
	
	/**
	 * 行政区域-所有企业检测报告信息
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGridAJ(Map<String,Object> mapData);

	/**
	 * 导入
	 */
	public Map<String,Object> exinExcel(Map<String, Object> map);
}
