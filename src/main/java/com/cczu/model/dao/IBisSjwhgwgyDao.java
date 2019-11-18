package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.BIS_TechniqueEntity;

public interface IBisSjwhgwgyDao {

	/**
	 * 获取list
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> dataGrid(Map<String,Object> mapData);
	
	/**
	 * 获取记录条数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData);

	/**
	 * 通过id查找
	 * @param id
	 * @return
	 */
	public BIS_TechniqueEntity findById(long id);

	/**
	 * 删除
	 * @param id
	 */
	public void deleteInfo(long id);

	/**
	 * 添加
	 * @param gwgy
	 */
	public void addInfo(BIS_TechniqueEntity gwgy);

	/**
	 * 企业修改
	 * @param bt
	 */
	public void updateInfo2(BIS_TechniqueEntity bt);

	/**
	 * admin修改
	 * @param bt
	 */
	public void updateInfo1(BIS_TechniqueEntity bt);
}
