package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.AQPX_PlanEntity;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.system.entity.User;

public interface IAqpxJhxxService {
	
	public Page<AQPX_PlanEntity> search(Page<AQPX_PlanEntity> page,
			List<PropertyFilter> filters);
	
	/**
	 * 根据企业id查询培训信息
	 * @param qyid
	 * @return
	 */
	public AQPX_PlanEntity findAllInfo(Long qyid);
	
	/**
	 * 添加
	 * @param ac
	 */
	public void addInfo(AQPX_PlanEntity ac);
	
	/**
	 * 修改课程信息
	 * @param ac
	 */
	public void updateInfo(AQPX_PlanEntity ac);
	
	/**
	 * 删除课程信息（假删除）
	 * @param id
	 */
	public void deleteInfo(Long id);
	
	/**
	 * list数据
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData);
	

	/**
	 * 查询条件
	 * @param mapData
	 * @return
	 */
	public String content(Map<String, Object> mapData);
	
	
	/**
	 * 导出excel
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);

	/**
	 * 查询所有的课程信息
	 * @return
	 */
	public List<AQPX_PlanEntity> getList(Long id);
	
	/**
	 * 通过id查询相关信息
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
	 * 根据所选部门id查询部门所有员工的id
	 * @param depid 部门id集合
	 * @return
	 */
	public List<User> findUseridByDep(String depid);

	/**
	 * list数据
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid2(Map<String, Object> mapData);
}
