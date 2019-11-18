package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.ISSUE_SafetyProductionDynamicEntity;

/**
 * 安全生产动态信息service接口
 * @author jason
 *
 */
public interface IIssueAqscdtService {
	
	/**
	 * 添加安全生产动态信息
	 * @param spd
	 * @return int 影响条数
	 */
	public int addInfor(ISSUE_SafetyProductionDynamicEntity spd);
	
	/**
	 * 查询所有安全生产动态信息
	 * @return
	 */
	public List<ISSUE_SafetyProductionDynamicEntity> findAlllist();
	
	/**
	 * 分页查询安全生产动态信息
	 * @return 
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) ;
	

	
	/**
	 * 删除安全生产动态信息
	 * @param id
	 * @return int 影响条数
	 */
	public int deleteInfo(long id);
	/**
     * 根据id查找安全生产动态信息
     * @param id
     * @return ISSUE_SecurityFileReleaseEntity
     */
	public ISSUE_SafetyProductionDynamicEntity findInfoById(long id);
	
	
	/**
	 * 修改安全生产动态信息
	 * @param id,spd
	 * @return int 影响条数
	 */
	public int updateInfoByID(long id,ISSUE_SafetyProductionDynamicEntity spd);
	
}
