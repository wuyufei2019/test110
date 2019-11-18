package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.BIS_DirectorEntity;

public interface IBisAqzjglDao {
	/**
     * 查询所有
     * @return
     */
	public List<BIS_DirectorEntity> findAlllist();

	/**
     * 查询安全总监数据表格
     * @return
     */
	public List<BIS_DirectorEntity> dataGrid(Map<String, Object> mapData);
	/**
     * 查询安全总监数据表格_总记录数
     * @return
     */
	public int getTotalCount(Map<String, Object> mapData);
	/**
     * 根据id查找安全总监
     * @return
     */
	public BIS_DirectorEntity findInfoById(long id);
	/**
     * 添加安全总监返回ID
     * @return
     */
	public Long addInfore(BIS_DirectorEntity bis);
	/**
     * 修改安全总监
     * @return
     */
	public void updateInfo(BIS_DirectorEntity bis);
	/**
     * 删除安全总监
     * @return
     */
	public void deleteInfo(long id);
	
	/**
     * 查询安全总监数据表格
     * @return
     */
	public List<Map<String, Object>> dataGridAJ(Map<String, Object> mapData);
	
	/**
	 * 查询安全总监数据表格
	 * @return
	 */
	public List<Map<String, Object>> dataGridAJForApp(Map<String, Object> mapData);
	/**
     * 查询安全总监数据表格_总记录数
     * @return
     */
	public int getTotalCountAJ(Map<String, Object> mapData);

	public List<Map<String, Object>> dirHaveAllQyList(Map<String, Object> map);
	
	/**
     * 导出excel
     * @return
     */
	public List<Map<String,Object>> getExport(Map<String, Object> mapData);
}
