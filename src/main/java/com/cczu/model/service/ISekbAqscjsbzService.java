package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.TMESK_TechnologystandardEntity;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;


public interface ISekbAqscjsbzService {
	
	
	public Page<TMESK_TechnologystandardEntity> search(Page<TMESK_TechnologystandardEntity> page,
			List<PropertyFilter> filters);
	
	
	/**
	 * 根据userID查安全生产技术标准信息
	 * @param userid
	 * @return
	 */
	public TMESK_TechnologystandardEntity findAll();
	
	/**
	 * 添加安全生产技术标准 信息
	 * @param sekb
	 */
	public void addInfo(TMESK_TechnologystandardEntity sekb,String filePath);
	
	/**
	 * 修改
	 * @param sekb
	 */
	public void updateInfo(TMESK_TechnologystandardEntity sekb,String filePath);
	
	/**
	 * 删除
	 * @param sekb
	 */
	public void deleteInfo(long id);
	
	public String content(Map<String, Object> mapData);


	public Map<String, Object> dataGrid(Map<String, Object> map);
	
	/**
	 * 通过id查安全生产技术标准 信息
	 * @param id
	 * @return
	 */
	public TMESK_TechnologystandardEntity findById(Long id);



	/**
	 * 导出Excel
	 * @return
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);



}
