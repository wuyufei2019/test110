package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.XZCF_GzsInfoEntity;

public interface IPunishSimpleGzDao {
	/**
	 * 添加信息
	 * @param sfr
	 * @return 返回影响记录条数
	 */
	public Long addInfoReturnID(XZCF_GzsInfoEntity jie);
	
	/**
	 * 分页查询信息
	 * @return 
	 */
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) ;
	/**
	 * 分页查询信息2(用于统计分析)
	 * @return 
	 */
	//public List<Map<String,Object>> dataGrid2(Map<String, Object> mapData) ;
	
	/**
     * 查询数据表格_总记录数
     * @return
     */
	public int getTotalCount(Map<String, Object> mapData);
	/**
	 * 删除信息
	 * @param id
	 * @return int 影响条数
	 */
	public void deleteInfo(long id);
	/**
     * 根据id查找件信息
     * @param id
     * @return AQJD_CheckPlanEntity
     */
	public XZCF_GzsInfoEntity findInfoById(long id);
	/**
     * 根据立案审批表id查找件信息
     * @param id
     * @return AQJD_CheckPlanEntity
     */
	public XZCF_GzsInfoEntity findInfoByLaId(long laid);
	
	
	/**
	 * 修改信息
	 * @param id
	 * @return int 影响条数
	 */
	public void updateInfo(XZCF_GzsInfoEntity jie);
	/**
	 * 删除告知信息时根据id重置立案审批的gzflag=0
	 * @param id
	 * @return int 影响条数
	 */
	public void updateLaspInfo(long id);
	
	}


