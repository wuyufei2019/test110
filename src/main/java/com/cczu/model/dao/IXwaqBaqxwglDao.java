package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.XWAQ_UnsafebehaviorEntity;

public interface IXwaqBaqxwglDao {
	/**
	 * 添加不安全行为管理信息
	 * @param xwaq
	 */
	public void addInfo(XWAQ_UnsafebehaviorEntity xwaq);
	
	/**
	 * 修改不安全行为管理信息
	 * @param xwaq
	 */
	public void updateInfo(XWAQ_UnsafebehaviorEntity xwaq);
	
	/**
	 * 删除不安全行为管理信息
	 * @param id
	 */
	public void deleteInfo(Long id);
	
	/**
	 * 查询数据
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> dataGrid(Map<String,Object> mapData);
	
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
	 * 通过id查找不安全行为管理信息
	 * @param id
	 * @return
	 */
	public XWAQ_UnsafebehaviorEntity findById(Long id);
	
	/**
	 * 
	 * 通过企业id查找不安全行为管理信息
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> findAllInfo(Long qyid);
	
	/**
	 * 
	 * 通过企业id查找不安全行为管理信息
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> findAllInfo1(Long qyid);
	
	/**
	 * 
	 * 通过企业id查找不安全行为管理信息
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> findAllInfo2(Long qyid, String xwlx);
	
	/**
	 * 获得导出数据
	 * @return
	 */
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData);
	

}
