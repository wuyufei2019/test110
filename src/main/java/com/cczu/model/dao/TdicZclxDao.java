package com.cczu.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.Tdic_ExpenseCategoryEntity;
import com.cczu.util.dao.BaseDao;

@Repository("TdicZclxDao")
public class TdicZclxDao extends BaseDao<Tdic_ExpenseCategoryEntity, Long>{

	/**
	 * 获取所有
	 * @return 集合
	 */
	public List<Tdic_ExpenseCategoryEntity> findListAll(){
		return findAll();
	}
}
