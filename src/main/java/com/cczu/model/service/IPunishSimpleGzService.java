package com.cczu.model.service;

import java.util.Map;

import com.cczu.model.entity.XZCF_GzsInfoEntity;
/**
 * 行政处罚-告知接口
 * @author jason
 *
 */
public interface IPunishSimpleGzService {
	
	
	/**
	 * 添加信息
	 * @param sfr
	 * @return  返回影响条数
	 */
	public Long addInfoReturnID(XZCF_GzsInfoEntity jie);
	
	/**
	 * 告知分页查询信息
	 * @return 
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) ;
	/**
	 * 分页查询信息2
	 * @return 
	 */
	//public Map<String, Object> dataGrid2(Map<String, Object> mapData) ;
	
	/**
	 * 删除信息
	 * @param id
	 * @return int 影响条数
	 */
	public void deleteInfo(long id);
	/**
     * 根据id查找信息
     * @param id
     * @return XZCF_JYCFInfoEntity
     */
	public XZCF_GzsInfoEntity findInfoById(long id);
	/**
     * 根据立案审批id查找信息
     * @param id
     * @return XZCF_JYCFInfoEntity
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
