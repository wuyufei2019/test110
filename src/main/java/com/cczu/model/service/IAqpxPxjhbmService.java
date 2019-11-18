package com.cczu.model.service;

import java.util.List;

import com.cczu.model.entity.AQPX_PlanDepartmentEntity;

public interface IAqpxPxjhbmService {
	
	/**
	 * 添加
	 * @param ap
	 */
	public void addInfo(AQPX_PlanDepartmentEntity ap);
	
	/**
	 * 修改
	 * @param ap
	 */
	public void updateInfo(AQPX_PlanDepartmentEntity ap);
	
	/**
	 * 删除
	 * @param id
	 */
	public void deleteInfo(Long id);
	
	/**
	 * 通过培训计划id查数据，
	 * @param jhid
	 * @return
	 */
	public AQPX_PlanDepartmentEntity findpxjh(Long jhid);
	
	/**
	 * 通过培训部门id查数据
	 * @param bmid
	 * @return
	 */
	public AQPX_PlanDepartmentEntity findpxbm(Long bmid);
	
	/**
	 * 通过企业id来查培训计划部门的信息
	 * @param qyid
	 * @return
	 */
	public AQPX_PlanDepartmentEntity findAll(Long qyid);
	
	/**
	 * 通过企业id来查培训计划部门的信息
	 * @param qyid
	 * @return
	 */
	public List<AQPX_PlanDepartmentEntity> getlist(Long qyid);
	

}
