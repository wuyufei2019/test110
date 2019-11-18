package com.cczu.model.service;

import java.util.List;


/**
 *  检查记录 统计分析接口
 *
 */

public interface IAqjgCheckTjfxService {
	/**
	 * 获取每年检查记录数据
	 * @return
	 */
	public List<Object[]> getYearDate();
	
}
