package com.cczu.model.service;

import java.util.Map;

import com.cczu.model.entity.XZCF_YbcfTzgzEntity;
/**
 * 行政处罚-告知接口
 * @author jason
 *
 */
public interface IXzcfCommonTzService {
	
	
	/**
	 * 添加信息
	 * @param sfr
	 * @return  返回影响条数
	 */
	public Long addInfoReturnID(XZCF_YbcfTzgzEntity yte);
	
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
     * @return XZCF_YbcfTzgzEntity
     */
	public XZCF_YbcfTzgzEntity findInfoById(long id);
	/**
     * 根据立案审批id查找信息
     * @param id
     * @return XZCF_YbcfTzgzEntity
     */
	public XZCF_YbcfTzgzEntity findInfoByLaId(long laid);
	/**
	 * 修改信息
	 * @param id
	 * @return int 影响条数
	 */
	public void updateInfo(XZCF_YbcfTzgzEntity yte);
	/**
	 * 删除听证信息时根据id重置立案审批的tzflag=0
	 * @param id
	 * @return int 影响条数
	 */
	public void updateLaspInfo(long id);

}
