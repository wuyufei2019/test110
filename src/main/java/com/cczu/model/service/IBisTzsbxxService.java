package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.BIS_SpecialequipmentEntity;

public interface IBisTzsbxxService {
	
	/**
	 * 根据qyID查企业获取其特种设备信息
	 * @param userid
	 * @return
	 */
	public BIS_SpecialequipmentEntity findAll(long qyid);
	
	/**
	 * 通过id查特种设备信息
	 * @param id
	 * @return
	 */
	public Map<String, Object> findById(Long id);
	public BIS_SpecialequipmentEntity findById2(Long id);
	/**
	 * 添加信息
	 * @param bis
	 */
	public void addInfo(BIS_SpecialequipmentEntity bis);
	
	/**
	 * 修改
	 * @param bis
	 */
	public void updateInfo(BIS_SpecialequipmentEntity bis);
	
	/**
	 * 删除
	 * @param bis
	 */
	public void deleteInfo(long id);
	
	/**
	 * 条件查询
	 * @param mapData
	 * @return
	 */
	public String content(Map<String, Object> mapData);
	
	/**
	 * 列表
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData);
	
	public Map<String, Object> asd(Map<String, Object> mapData);
	
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
	
	public Map<String,Object> exinExcel(Map<String, Object> map);
	/**
	 * 统计分析获取数据
	 */
	public List<Map<String, Object>> statistics(Map<String,Object> map);
	
}
