package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.TMESK_AccidentEntity;

public interface ISekbSgalDao {
	
	/**
	 * 查询所有事故案例信息
	 * @return
	 */
	public TMESK_AccidentEntity findAllInfo();
	
	/**
	 * 添加事故案例信息
	 * @param sekb
	 */
	public void addInfo(TMESK_AccidentEntity sekb);
	
	/**
	 * 修改事故案例信息
	 * @param sekb
	 */
	public void updateInfo(TMESK_AccidentEntity sekb);
	
	/**
	 * 删除事故案例信息
	 * @param id
	 */
	public void deleteInfo(Long id);
	
	/**
	 * 查询数据
	 * @param mapData
	 * @return
	 */
	public List<TMESK_AccidentEntity> dataGrid(Map<String,Object> mapData);
	
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
	 * 通过id查找事故案例信息
	 * @param id
	 * @return
	 */
	public TMESK_AccidentEntity findById(Long id);


	
	/**
	 * 获得导出数据
	 * @return
	 */
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData);
	

}
