package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.BIS_StorageEntity;

public interface IBisCkxxService {
	/**
	 * 根据qyid查企业获取其仓库信息
	 * @param userid
	 * @return
	 */
	public List<BIS_StorageEntity> findAll(long qyid);
	
	/**
	 * 添加仓库信息
	 * @param bis
	 */
	public void addInfo(BIS_StorageEntity bis);
	
	/**
	 * 修改
	 * @param bis
	 */
	public void updateInfo(BIS_StorageEntity bis);
	
	/**
	 * 删除
	 * @param bis
	 */
	public void deleteInfo(long id);
	
	public Map<String, Object> dataGrid(Map<String, Object> mapData);
	
	public BIS_StorageEntity findById(Long id);
	
	public Map<String, Object> findById2(Long id);
	
	/**
	 * 导出excel
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);
	
	/**
	 * 安监局查看所有企业仓库信息
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid2(Map<String,Object> mapData);

	/**
	 * 行政区域—所有仓库信息
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGridAJ(Map<String, Object> map);


	/**
	 * 导入
	 */
	public Map<String,Object> exinExcel(Map<String, Object> map);
	/**
	 * 统计分析
	 * @param xzqy
	 * @return
	 */
	public Map<String, Object> statistics(Map<String, Object> map);

	/**
	 * 根据多个条件查询
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> findListByMap(Map<String, Object> map);
}
