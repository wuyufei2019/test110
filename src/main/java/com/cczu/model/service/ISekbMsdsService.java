package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.TMESK_MsdsEntity;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;


public interface ISekbMsdsService {
	
	
	public Page<TMESK_MsdsEntity> search(Page<TMESK_MsdsEntity> page,
			List<PropertyFilter> filters);
	
	
	public String content(Map<String, Object> mapData);


	public Map<String, Object> dataGrid(Map<String, Object> map);
	
	/**
	 * 通过id查MSDS信息
	 * @param id
	 * @return
	 */
	public TMESK_MsdsEntity findById(Long id);


	/**
	 * 导出Excel
	 * @return
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);


	public List<TMESK_MsdsEntity> findByWzName(String name);
	
	public List<Map<String,Object>> listAll();

}
