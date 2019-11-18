package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.Tdic_BIS_HazardIdentityEntity;

public interface ITdicBisHazardIdentityDao {
	public List<Tdic_BIS_HazardIdentityEntity> dataList(String m1);
	
	public Tdic_BIS_HazardIdentityEntity findAll(Long id);

	public Tdic_BIS_HazardIdentityEntity findBymap(Map<String,Object> map);
	
	public List<Map<String,Object>> findWzList();
	
	public List<Map<String,Object>> findWzlbList();
	/**
	 * 根据化学品名称查询临界量
	 * @param M2
	 * @return
	 */
	public Tdic_BIS_HazardIdentityEntity findM3ByM2(String m2);
	/**
	 * 根据化学品类别查询分类说明
	 * @param M1
	 * @return
	 */
	public List<Tdic_BIS_HazardIdentityEntity> flsmList2(String m1);
	/**
	 * 根据化学品类别和分类说明查询临界量
	 * @param map
	 * @return
	 */
	public Tdic_BIS_HazardIdentityEntity findBymap2(Map<String, Object> map);
}
