package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.AQPX_PlanEntity;
import com.cczu.sys.system.entity.User;

public interface IAqpxJhxxDao {
	/**
	 * 通过企业id查询改企业的计划信息表
	 * @param qyid
	 * @return
	 */
	public AQPX_PlanEntity findAllInfo(Long qyid);
	
	/**
	 * 添加课程信息
	 * @param ap
	 */
	public void addInfo(AQPX_PlanEntity ap);
	
	/**
	 * 修改课程信息
	 * @param ap
	 */
	public void updateInfo(AQPX_PlanEntity ap);
	
	/**
	 * 删除课程信息（假删除）
	 * @param id
	 */
	public void deleteInfo(Long id);
	
	/**
	 * 分页查询数据
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
	 * 获得导出数据
	 * @return
	 */
	public List<Object> getExcel(Map<String, Object> mapData);

	/**
	 * 通过id查询培训计划
	 * @return
	 */
	public List<AQPX_PlanEntity> getList(Long id);
	
	/**
	 * 通过id查询数据
	 * @param id
	 * @return
	 */
	public AQPX_PlanEntity findByid(Long id);
	
	/**
	 * 根据用户所在企业id和部门id查询要学习的课程
	 * @param qyid 企业id
	 * @param bmid 部门id
	 * @return
	 */
	public List<AQPX_PlanEntity> findInfoByBmid(Long qyid,Long bmid);
	
	/**
	 * 查询所有过期培训计划
	 * @return
	 */
	public List<AQPX_PlanEntity> findAllInfo();

	/**
	 * 查询部门所有员工id
	 * @return
	 */
	public List<User> findUseridByDep(String depid);

	/**
	 * 分页查询数据
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> dataGrid2(Map<String,Object> mapData);

	/**
	 * @param mapData
	 * @return
	 */
	public int getTotalCount2(Map<String, Object> mapData);
}
