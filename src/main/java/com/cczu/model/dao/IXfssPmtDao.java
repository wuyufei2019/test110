package com.cczu.model.dao;

import java.util.Map;

public interface IXfssPmtDao {
	/**
	 * 上传平面图
	 * @param xwaq
	 */
	public void addInfo(Map<String,Object> xwss);
	
	/**
	 * 删除平面图
	 * @param id
	 */
	public void deleteInfo(Long id);
	
	/**
	 * 
	 * 通过id查找平面图
	 * @param id
	 * @return
	 */
	public Map<String,Object> findById(Long id);
}
