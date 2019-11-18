package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.TMESK_LawsEntity;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;


public interface ISekbAqscfgService {
	
	
	public Page<TMESK_LawsEntity> search(Page<TMESK_LawsEntity> page,
			List<PropertyFilter> filters);
	
	
	/**
	 * 根据userID查安全生产法律法规 信息
	 * @param userid
	 * @return
	 */
	public TMESK_LawsEntity findAll();
	
	/**
	 * 添加安全生产法律法规 信息
	 * @param sekb
	 */
	public void addInfo(TMESK_LawsEntity sekb,String filePath);
	
	/**
	 * 修改
	 * @param sekb
	 */
	public void updateInfo(TMESK_LawsEntity sekb,String filePath);
	
	/**
	 * 添加二维码
	 * @param sekb
	 */
	public void addQrcode(TMESK_LawsEntity sekb);
	
	/**
	 * 删除
	 * @param sekb
	 */
	public void deleteInfo(long id);
	
	public String content(Map<String, Object> mapData);


	public Map<String, Object> dataGrid(Map<String, Object> map);
	
	/**
	 * 通过id查安全生产法律法规 信息
	 * @param id
	 * @return
	 */
	public TMESK_LawsEntity findById(Long id);


	/**
	 * 导出Excel
	 * @return
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);




}
