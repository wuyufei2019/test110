package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.TMESK_AccidentEntity;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;


public interface ISekbSgalService {
	
	
	public Page<TMESK_AccidentEntity> search(Page<TMESK_AccidentEntity> page,
			List<PropertyFilter> filters);
	
	
	/**
	 * 根据userID查事故案例信息
	 * @param userid
	 * @return
	 */
	public TMESK_AccidentEntity findAll();
	
	/**
	 * 添加事故案例信息
	 * @param sekb
	 */
	public void addInfo(TMESK_AccidentEntity sekb);
	
	/**
	 * 修改
	 * @param sekb
	 */
	public void updateInfo(TMESK_AccidentEntity sekb);
	
	/**
	 * 删除
	 * @param sekb
	 */
	public void deleteInfo(long id);
	
	public String content(Map<String, Object> mapData);


	public Map<String, Object> dataGrid(Map<String, Object> map);
	
	/**
	 * 通过id查事故案例信息
	 * @param id
	 * @return
	 */
	public TMESK_AccidentEntity findById(Long id);



	/**
	 * 导出Excel
	 * @return
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);



}
