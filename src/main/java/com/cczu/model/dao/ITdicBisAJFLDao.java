package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.Tdic_BisjgflEntity;

public interface ITdicBisAJFLDao {
	/**
	 * 获取所有
	 * @return 集合
	 */
	public List<Tdic_BisjgflEntity> findAJFLList();

	/**
	 * 获取大类
	 * @return 集合
	 */
	public List<Map<String,Object>> findDlLList();

}
