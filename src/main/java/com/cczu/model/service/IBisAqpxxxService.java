package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.BIS_SafetyEducationEntity;

public interface IBisAqpxxxService {
	/**
	 * 通过id查安全培训信息
	 * @param id
	 * @return
	 */
	public BIS_SafetyEducationEntity findById(Long id);
	public BIS_SafetyEducationEntity findById2(Long id);
	
	/**
	 * 根据qyID查企业获取其安全培训信息
	 * @param userid
	 * @return
	 */
	public List<BIS_SafetyEducationEntity> findAll(Long qyid);
	
	/**
	 * 添加信息
	 * @param bis
	 */
	public void addInfo(BIS_SafetyEducationEntity bis);
	
	/**
	 * 修改
	 * @param bis
	 */
	public void updateInfo(BIS_SafetyEducationEntity bis);
	
	/**
	 * 删除
	 * @param bis
	 */
	public void deleteInfo(long id);
	
	public Map<String, Object> dataGrid(Map<String, Object> mapData);
	
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
	 * 用于安监局获取所有企业的安全培训信息
	 * @return
	 */
	public List<BIS_SafetyEducationEntity> findAllaj();

	public Map<String,Object> exinExcel(Map<String, Object> map);
}
