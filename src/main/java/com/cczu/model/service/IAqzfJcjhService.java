package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.AQZF_SafetyCheckPlanEntity;

public interface IAqzfJcjhService {

	/**
	 * 查询检查计划list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData);
	
	/**
	 * 删除
	 * @param parseLong
	 */
	public void deleteInfo(long parseLong);
	
	/**
	 * 添加信息
	 * @param jcjh
	 */
	public void addInfo(AQZF_SafetyCheckPlanEntity jcjh);

	/**
	 * 导出
	 * @param response
	 * @param map
	 */
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> map);

	/**
	 * 执行添加操作获得返回的id
	 * @return
	 */
	public long addjcjh(AQZF_SafetyCheckPlanEntity jcjh);
	
	/**
	 * 根据ID查看
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> findById(Long id);
	
	/**
	 * 根据行业类别ID查找内容
	 * @param id
	 * @return
	 */
	public Map<String, Object> findHy(String id);
	
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public AQZF_SafetyCheckPlanEntity findById2(long id);

	/**
	 * 根据计划id1企业id2删除方案表中数据
	 * @param id1
	 * @param id2
	 */
	public void deletefa(long id1);

	/**
	 * 根据方案id删除记录id
	 * @param id1
	 * @param id2
	 */
	public void deletejl(String faids);
	
	/**
	 * 根据id1,id2查找检查方案
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> findJcfa(long id1);
	
	/**
	 * 根据计划id查找方案条数
	 * @param id1
	 * @return
	 */
	public int getfasl(long id1);
}
