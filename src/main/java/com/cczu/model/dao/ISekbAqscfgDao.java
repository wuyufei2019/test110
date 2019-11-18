package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.TMESK_LawsEntity;

public interface ISekbAqscfgDao {
	
	/**
	 * 查询所有安全生产法律法规信息
	 * @return
	 */
	public TMESK_LawsEntity findAllInfo();
	
	/**
	 * 添加安全生产法律法规信息
	 * @param sekb
	 */
	public void addInfo(TMESK_LawsEntity sekb);
	
	/**
	 * 修改安全生产法律法规信息
	 * @param sekb
	 */
	public void updateInfo(TMESK_LawsEntity sekb);
	
	/**
	 * 删除安全生产法律法规信息
	 * @param id
	 */
	public void deleteInfo(Long id);
	
	/**
	 * 查询数据
	 * @param mapData
	 * @return
	 */
	public List<TMESK_LawsEntity> dataGrid(Map<String,Object> mapData);
	
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
	 * 通过id查找安全生产法律法规信息
	 * @param id
	 * @return
	 */
	public TMESK_LawsEntity findById(Long id);


	
	
	/**
	 * 获得导出数据
	 * @return
	 */
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData);
	

}
