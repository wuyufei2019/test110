package com.cczu.model.service;

import java.util.List;
import java.util.Map;


import com.cczu.model.entity.TMESK_AqbskglEntity;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;


public interface ISekbAqbskglService {
	
	
	public Page<TMESK_AqbskglEntity> search(Page<TMESK_AqbskglEntity> page,
			List<PropertyFilter> filters);
	
	
	/**
	 * 根据userID查安全标示信息
	 * @param userid
	 * @return
	 */
	public TMESK_AqbskglEntity findAll();
	
	/**
	 * 添加安全标示信息
	 * @param sekb
	 */
	public void addInfo(TMESK_AqbskglEntity sekb);
	
	/**
	 * 修改
	 * @param sekb
	 */
	public void updateInfo(TMESK_AqbskglEntity sekb);
	
	/**
	 * 删除
	 * @param sekb
	 */
	public void deleteInfo(long id);
	
	public String content(Map<String, Object> mapData);


	public Map<String, Object> dataGrid(Map<String, Object> map);
	
	/**
	 * 通过id查安全标示信息
	 * @param id
	 * @return
	 */
	public TMESK_AqbskglEntity findById(Long id);


}
