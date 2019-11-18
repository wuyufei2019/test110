package com.cczu.model.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.DW_WorkApprovalEntity;

public interface IDwZyspService {
	/**
	 * 添加作业证
	 * @param obj
	 */
	public Long addInfo(DW_WorkApprovalEntity obj);
	
	/**
	 * 修改
	 * @param obj
	 */
	public void updateInfo(DW_WorkApprovalEntity obj);
	
	/**
	 * 删除
	 * @param obj
	 */
	public void deleteInfo(long id);

	/**
	 * list数据
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> map);
	
	/**
	 * list数据
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGridApp(Map<String, Object> map);
	
	/**
	 * 通过id查作业证
	 * @param id
	 * @return
	 */
	public DW_WorkApprovalEntity findInfoById(Long id);
	
	/**
	 * 通过id查作业证2
	 * @param id
	 * @return
	 */
	public Map<String,Object> findInfoById2(Long id);

	/**
	 * 导出Excel
	 * @return
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);
	
	/**
     * 企业list for app
     * @return
     */
	public String getqylistapp(Map<String, Object> mapData);

}
