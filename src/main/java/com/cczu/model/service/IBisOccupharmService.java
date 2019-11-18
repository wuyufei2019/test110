package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.BIS_OccupharmExamEntity;

public interface IBisOccupharmService {
	public Map<String, Object> dataGrid(Map<String, Object> mapData);
	
	public List<BIS_OccupharmExamEntity> findAll(Long qyid);
	
	/**
	 * 通过qyid获取员工信息
	 * @param qyid
	 * @return
	 */
	public List<Map<String, Object>> getJcmd(Map<String, Object> map);
	
	public void addInfo(BIS_OccupharmExamEntity bis);
	
	public BIS_OccupharmExamEntity findById(Long id);
	
	public BIS_OccupharmExamEntity findById2(Long id);
	
	public void updateInfo(BIS_OccupharmExamEntity bis);
	
	public void deleteInfo(Long id);
	
	/**
	 * 导出excel
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);
	
	public Map<String, Object> ajdataGrid(Map<String, Object> mapData);
	/**
	 * 统计分析获取数据
	 */
	public Map<String, Object> statistics(Map<String, Object> mapData);
	
	public Map<String, Object> getJcmdDatagrid(Map<String, Object> map);
	/**
	 * 导入
	 * @param map
	 * @return
	 */
	public Map<String, Object> exinExcel(Map<String, Object> map);
	
}
