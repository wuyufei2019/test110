package com.cczu.model.service;


import com.cczu.model.entity.AQJD_AjCheckRecordEntity;
/**
 * 安全监督安监检查service接口
 * @author jason
 *
 */
public interface IAqjgAjCheckRecordService {
	
	
	/**
	 * 添加安监初检信息
	 * @param sfr
	 * @return  返回影响条数
	 */
	public void addAjRecordInfo(AQJD_AjCheckRecordEntity acre);
	
	/**
     * 根据id查找信息
     * @param id
     * @return AQJD_CheckPlanEntity
     */
	public AQJD_AjCheckRecordEntity findInfoById(long id);
	
	/**
     * 根据企业检查id查找信息
     * @param id
     * @return AQJD_CheckPlanEntity
     */
	public AQJD_AjCheckRecordEntity findInfoByQyId(long qyid);
	/**
	 * 修改初检信息
	 * @param id
	 * @return int 影响条数
	 */
	public void updateInfo(AQJD_AjCheckRecordEntity acre);
	/**
	 * 修改复检信息
	 * @param id
	 * @return int 影响条数
	 */
	public int updateFjInfo(AQJD_AjCheckRecordEntity acre);

	
}
