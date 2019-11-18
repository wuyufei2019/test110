package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.Tdic_BIS_HazardIdentityEntity;

public interface ITdicBisHazardIdentityService {
	
	public String dataList(String m1);
	public Tdic_BIS_HazardIdentityEntity findAll(Long id);
	/*
	 * 更加条件查询
	 */
	public String findBymap(Map<String, Object> map);
	
	public List<Map<String,Object>> findWzList();
	
	public List<Map<String,Object>> findWzlbList();
	/**
	 * 根据化学品名称查询临界量
	 * @param m2
	 * @return
	 */
	public String findM3ByM2(String M2);
	/**
	 * 根据化学品类别查询分类说明
	 * @param text
	 * @return
	 */
	public String findM5ByM1(String M1);
	/**
	 * 根据化学品类别和分类说明查询临界量
	 * @param map
	 * @return
	 */
	public String findBymap2(Map<String, Object> map);


}
