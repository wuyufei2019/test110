package com.cczu.model.dao;

import java.util.List;

import com.cczu.model.entity.Tdic_ZybAndFlEntity;

public interface ITdicZybflDao {
	
	/**
	 * 获取所有
	 * @return 集合
	 */
	public List<Tdic_ZybAndFlEntity> findListAll();
	
	
	/**
	 * 通过cid来查询信息
	 * @param cid
	 * @return
	 */
	public Tdic_ZybAndFlEntity findbycid(String cid);
	
}
