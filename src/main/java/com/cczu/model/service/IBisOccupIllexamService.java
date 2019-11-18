package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.BIS_OccupIllexamEntity;

public interface IBisOccupIllexamService {

	public Map<String, Object> dataGrid(Map<String, Object> mapData);
	
	public List<BIS_OccupIllexamEntity> findAll(Long qyid);
	
	public void addInfo(BIS_OccupIllexamEntity bis);
	
	public BIS_OccupIllexamEntity findById(Long id);
	
	public void updateInfo(BIS_OccupIllexamEntity bis);
	
	public void deleteInfo(Long id);
	
	/**
	 * 导出excel
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);
	
	
	/**
	 * @param map
	 * @return
	 */
	public Map<String, Object> ajdataGrid(Map<String, Object> map);
	
	/**
	 * 导入
	 */
	public Map<String,Object> exinExcel(Map<String, Object> map);
	
}
