package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.Tdic_AcaWxhxpmonitoringEntity;

public interface ITdicAcawxhxpDao {
	/**
	 * 获取所有
	 * @return 集合
	 */
	public List<Tdic_AcaWxhxpmonitoringEntity> getAllList();

	/**
	 * 根据id获取
	 * @return 对象
	 */
	public Tdic_AcaWxhxpmonitoringEntity findById(Long tid);
	/**
	 * 根据id获取
	 * @return 对象
	 */
	public Tdic_AcaWxhxpmonitoringEntity find(Long id);
	
	
	
	/**
	 * 用于app接口获取事故后果计算的信息
	 * @return
	 */
	public List<Map<String,Object>> getAllxx(String sum);
	
	/**
	 * 根据物料名称查询所有
	 * @return 集合
	 */
	public List<Tdic_AcaWxhxpmonitoringEntity> findByName(String name);
	
}
