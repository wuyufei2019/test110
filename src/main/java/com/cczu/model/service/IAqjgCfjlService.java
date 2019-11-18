package com.cczu.model.service;

import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.cczu.model.entity.AQJG_DSFCfjlEntity;

public interface IAqjgCfjlService {

	/**
	 * 查询处罚记录list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData);

	/**
	 * 添加信息
	 * @param cfjl
	 */
	public void addInfo(AQJG_DSFCfjlEntity cfjl);

	/**
	 * 删除
	 * @param parseLong
	 */
	public void deleteInfo(long parseLong);
	
	/**
	 * 导出
	 * @param response
	 * @param map
	 */
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> map);

	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public AQJG_DSFCfjlEntity findById(Long id);

	/**
	 * 修改
	 * @param cfjllist
	 */
	public void updateInfo(AQJG_DSFCfjlEntity cfjllist);
}
