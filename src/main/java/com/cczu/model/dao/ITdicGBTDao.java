package com.cczu.model.dao;

import java.util.List;

import com.cczu.model.entity.Tdic_GbtbusinessEntity;

public interface ITdicGBTDao {

	/**
	 * 获取所有
	 * @return 集合
	 */
	public List<Tdic_GbtbusinessEntity> findListAll();
	
}
