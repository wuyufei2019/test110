package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.XWAQ_ObservationsEntity;


public interface IXwaqGcjlDao {
	/**
	 * 添加观察记录信息
	 * @param xwaq
	 */
	public Long addInfo(XWAQ_ObservationsEntity xwaq);
	
	/**
	 * 修改观察记录信息
	 * @param xwaq
	 */
	public void updateInfo(XWAQ_ObservationsEntity xwaq);
	
	/**
	 * 删除观察记录信息
	 * @param id
	 */
	public void deleteInfo(Long id);
	
	/**
	 * 查询数据
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> dataGrid(Map<String,Object> mapData);
	
	/**
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData);

	/**
	 * @param mapData
	 * @return
	 */
	public String content(Map<String, Object> mapData);
	
	/**
	 * 
	 * 通过id查找观察记录信息
	 * @param id
	 * @return
	 */
	public XWAQ_ObservationsEntity findById(Long id);
	
	/**
	 * 
	 * 通过id查找观察记录信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> findByIdForView(Long id);
	
	/**
	 * 获得导出数据
	 * @return
	 */
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData);
	

}
