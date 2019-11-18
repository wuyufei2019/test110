package com.cczu.model.service;

import java.util.Map;

public interface IXfssPmtService {
	/**
	 * 添加平面图
	 * @param xwaq
	 */
	public void addInfo(Map<String,Object> xwaq);
	
	/**
	 * 删除
	 * @param xwaq
	 */
	public void deleteInfo(long id);
	
	/**
	 * 通过id查平面图
	 * @param id
	 * @return
	 */
	public Map<String,Object> findById(Long id);

}
