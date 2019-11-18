package com.cczu.model.dao;

import java.util.List;

import com.cczu.model.entity.BIS_T_Safetynetenterprise;

public interface IBisAqwlglDao {

	/**
	 * 获取所有
	 * @return 集合
	 */
	public List<BIS_T_Safetynetenterprise> findAll(Long uid);
	/**
	 * 根据id获取对象
	 * @return 对象
	 */
	public BIS_T_Safetynetenterprise findById(Long id);
	/**
	 * 保存所有
	 */
	public void save(BIS_T_Safetynetenterprise bis);
	/**
	 * 删除对象
	 */
	public void delete(Long id);
	
}
