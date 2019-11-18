package com.cczu.model.service;

import java.util.Map;

import com.cczu.model.entity.XZCF_YbcfAjcpEntity;
/**
 * 行政处罚-简单处罚-处罚决定接口
 * @author jason
 *
 */
public interface IXzcfCommonAjcpService {
	
	/**
	 * 添加信息
	 * @param sfr
	 * @return  返回影响条数
	 */
	public Long addInfoReturnID(XZCF_YbcfAjcpEntity yae);
	
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
     * @return XZCF_YbcfAjcpEntity
     */
	public XZCF_YbcfAjcpEntity findInfoById(long id);
	/**
     * 根据立案id查找信息
     * @param id
     * @return XZCF_YbcfAjcpEntity
     */
	public XZCF_YbcfAjcpEntity findInfoByLaId(long id);
	
	/**
	 * 修改信息
	 * @param id
	 * @return int 影响条数
	 */
	public void updateInfo(XZCF_YbcfAjcpEntity yae);
	/**
	 * 删除处罚决定时根据id重置立案审批的cbflag=0
	 * @param id
	 * @return int 影响条数
	 */
	public void updateLaspInfo(long id);
	
}
