package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

/**
 * 安全生产动态信息DAO接口
 * @author jason
 * 
 */


import com.cczu.model.entity.ISSUE_SafetyProductionDynamicEntity;

public interface IIssueAqscdtDao {
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
	 * 分页查询安全生产动态信息(安监局可以看到自己和上级和下级发的，同级发的看不到；企业同安监局)
	 * @return 
	 */
	public List<ISSUE_SafetyProductionDynamicEntity> dataGrid(Map<String, Object> mapData) ;
	
	/**
     * 查询安全生产动态信息数据表格_总记录数
     * @return
     */
	public int getTotalCount(Map<String, Object> mapData);
	
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


