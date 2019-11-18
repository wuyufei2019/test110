package com.cczu.model.service;

import java.util.Map;

public interface IEadJclsjlService {

	/**
	 * dataGrid 集合
	 * @param map
	 * @return
	 */
	public Map<String, Object> findconsequenceResTeamDataGrid( Map<String, Object> map);

	/**
	 * 删除
	 * @param id
	 */
	public void delete(Long id);

}
