package com.cczu.model.dao;

import com.cczu.model.entity.AQJD_AjCheckRecordEntity;

public interface IAqjgAjCheckRecordDao {
	/**
	 * 添加信息
	 * @param sfr
	 * @return 返回影响记录条数
	 */
	public void addAjCheckInfo(AQJD_AjCheckRecordEntity acre);

	/**
     * 根据id查找件信息
     * @param id
     */
	public AQJD_AjCheckRecordEntity findInfoById(long id);
	/**
     * 根据id查找件信息
     * @param id
     */
	public AQJD_AjCheckRecordEntity findInfoByQyId(long qyid);
	
	/**
	 * 更新安监复检信息
	 */
	public int updateAjFjRecordInfo(AQJD_AjCheckRecordEntity acre);
	/**
	 * 更新安监初检信息
	 */
	public void updateAjRecordInfo(AQJD_AjCheckRecordEntity acre);
	
	}


