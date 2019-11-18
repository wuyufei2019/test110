package com.cczu.model.service;

import com.cczu.model.entity.BIS_HazardEntity;

import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

public interface IBisHazardService {


	/**
	 *
	 * @param qyid
	 * @return
	 */
	public BIS_HazardEntity findById(Long qyid);
	/**
	 * @param qyid
	 * @return
	 */
	public BIS_HazardEntity findqyid(Long qyid);
	
	/**
	 * @param qyid
	 * @return
	 */
	public Map<String,Object> findMapByQyId(Long qyid);
	
	/**
	 * @param qyid
	 * @return
	 */
	public BIS_HazardEntity findByid(Long id);
	
	/**
	 * @param bis
	 */
	public void addInfo(BIS_HazardEntity bis);
	
	
	/**
	 * @param bis
	 */
	public void updateInfo(BIS_HazardEntity bis);
	
	public Map<String, Object> dataGrid(Map<String, Object> mapData);

	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);

	/**
	 * 删除
	 * @param bis
	 */
	public void deleteInfo(long id);
	/**
	 * 统计分析
	 * @param mapData
	 * @return
	 */
	public List<Integer> statistics(Map<String, Object> mapData) ;
	
	/**
	 * 查询最新流水号
	 * @param qyid
	 * @return
	 */
	public String FindWaterCode(Long qyid);
	
	/**
	 * 重大危险源编码Json
	 * @return
	 */
	public List<Map<String,Object>> findhazardCode(Long qyid);
	
	
}
