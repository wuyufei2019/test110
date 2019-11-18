package com.cczu.model.dao;

import java.util.List;

import com.cczu.model.entity.TMESK_ChemicalsdirectoryEntity;

public interface ITdicDangerousChemicalsDao {
	
	/**
	 * 通过M1查询字典-危险化学品分类信息表中的信息
	 * @param M1
	 * @return
	 */
	public TMESK_ChemicalsdirectoryEntity findByM(String m1);
	
	/**
	 * 查询所有的危险化学品分类信息表中的信息
	 * @return
	 */
	public List<TMESK_ChemicalsdirectoryEntity> findlist();
	
	/**
	 * 通过M1模糊查询字典-危险化学品分类信息表中的信息
	 * @param M1
	 * @return
	 */
	public List<TMESK_ChemicalsdirectoryEntity> findByMs(String m1);

}
