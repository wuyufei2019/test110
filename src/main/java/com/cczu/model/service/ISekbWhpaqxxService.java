package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.TMESK_ChemicalsdirectoryEntity;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;


public interface ISekbWhpaqxxService {
	
	
	public Page<TMESK_ChemicalsdirectoryEntity> search(Page<TMESK_ChemicalsdirectoryEntity> page,
			List<PropertyFilter> filters);
	
	
	
	public String content(Map<String, Object> mapData);


	public Map<String, Object> dataGrid(Map<String, Object> map);
	
	/**
	 * 通过id查危化品安全信息
	 * @param id
	 * @return
	 */
	public TMESK_ChemicalsdirectoryEntity findById(Long id);



	/**
	 * 导出Excel
	 * @return
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);



}
