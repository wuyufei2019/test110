package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.DW_WorkApprovalEntity;



public interface IDwZyspDao {
	
	/**
	 * 添加作业证
	 * @param dw
	 */
	public void addInfo(DW_WorkApprovalEntity dw);
	
	/**
	 * 修改作业证
	 * @param dw
	 */
	public void updateInfo(DW_WorkApprovalEntity dw);
	
	/**
	 * 删除作业证
	 * @param id
	 */
	public void deleteInfo(Long id);
	
	/**
	 * 查询数据
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String,Object> mapData);
	
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
	 * 通过id查找作业证
	 * @param id
	 * @return
	 */
	public DW_WorkApprovalEntity findInfoById(Long id);
	
	/**
	 * 
	 * 通过id查找作业证
	 * @param id
	 * @return
	 */
	public Map<String,Object> findInfoById2(Long id);

	/**
	 * 获得导出数据
	 * @return
	 */
	public List<Map<String, Object>> getExcelData(Map<String, Object> mapData);	
	
	/**
     * 企业list for app
     * @return
     */
	public List<Map<String, Object>> getqylistapp(Map<String, Object> mapData);
}
