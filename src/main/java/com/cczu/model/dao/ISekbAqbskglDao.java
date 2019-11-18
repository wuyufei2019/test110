package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.TMESK_AqbskglEntity;

public interface ISekbAqbskglDao {
	
	/**
	 * 查询所有安全标示信息
	 * @return
	 */
	public TMESK_AqbskglEntity findAllInfo();
	
	/**
	 * 添加安全标示信息
	 * @param sekb
	 */
	public void addInfo(TMESK_AqbskglEntity sekb);
	
	/**
	 * 修改安全标示信息
	 * @param sekb
	 */
	public void updateInfo(TMESK_AqbskglEntity sekb);
	
	/**
	 * 删除安全标示信息
	 * @param id
	 */
	public void deleteInfo(Long id);
	
	/**
	 * 查询数据
	 * @param mapData
	 * @return
	 */
	public List<TMESK_AqbskglEntity> dataGrid(Map<String,Object> mapData);
	
	/**
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData);

	/**
	 * @param mapData
	 * @return
	 */
	public String content(Map<String, Object> mapData);
	
	/**
	 * 
	 * 通过id查找安全标示信息
	 * @param id
	 * @return
	 */
	public TMESK_AqbskglEntity findById(Long id);


}
