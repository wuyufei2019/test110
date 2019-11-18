package com.cczu.model.service;

import java.util.Map;

import com.cczu.model.entity.XZCF_JycfCssbEntity;
/**
 * 行政处罚-简单处罚-告知接口
 * @author jason
 *
 */
public interface IPunishSimpleCssbService {
	
	/**
	 * 添加信息
	 * @param sfr
	 * @return  返回影响条数
	 */
	public Long addInfoReturnID(XZCF_JycfCssbEntity jce);
	
	/**
	 * 告知分页查询信息
	 * @return 
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) ;
	/**
	 * 删除信息
	 * @param id
	 * @return int 影响条数
	 */
	public void deleteInfo(long id);
	/**
     * 根据id查找信息
     * @param id
     * @return XZCF_JYCFCssbEntity
     */
	public XZCF_JycfCssbEntity findInfoById(long id);
	/**
	 * 修改信息
	 * @param id
	 * @return int 影响条数
	 */
	public void updateInfo(XZCF_JycfCssbEntity jce);
	
}
