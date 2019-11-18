package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.AQJG_DSFPjEntity;

public interface IAqjgPjDao {

	/**
	 * 查询评价记录list
	 * @param mapData
	 * @return
	 */
	public List<Map<String , Object>> dataGrid(Map<String, Object> mapData);

	/**
     * 统计评价记录数
     * @return
     */
	public int getTotalCount(Map<String, Object> mapData);

	
	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id);

	/**
     * 导出
     * @param mapData
     * @return
     */
	public List<Map<String, Object>> getExport(Map<String, Object> mapData);

	/**
	 * 获取未评价第三方
	 * @return
	 */
	public List<Map<String, Object>> getdname();

	/**
	 * 添加评价
	 * @param pjlist
	 */
	public void addInfo(AQJG_DSFPjEntity pjlist);

	/**
	 * 通过id查找
	 * @param id
	 * @return
	 */
	public AQJG_DSFPjEntity findById(Long id);

	/**
	 * 修改
	 * @param pjlist
	 */
	public void updateInfo(AQJG_DSFPjEntity pjlist);

	/**
	 * 获得年份
	 * @return
	 */
	public List<Map<String, Object>> findNdList();

	public List<Map<String, Object>> getdname2();

	
}
