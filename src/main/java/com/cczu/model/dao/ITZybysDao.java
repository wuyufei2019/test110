package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.Tdic_BIS_OccupharmExamEntity;

public interface ITZybysDao {
	
	/**
	 * 获取所有的m1类别的信息
	 * @return
	 */
	public List<Map<String, Object>> getlistm1();
	
	/**
	 * 通过m1查询获取到m2职业病危害因素的名称
	 * 
	 * @param m1
	 * @return
	 */
	public List<Tdic_BIS_OccupharmExamEntity> getlistm2(String m1);
	/**
	 * 查询所有职业病危险因素名称
	 */
	public List<Tdic_BIS_OccupharmExamEntity> getAll();
	/**
	 * 通过名称获取到的id进行查询
	 * @param id
	 * @return
	 */
	public Tdic_BIS_OccupharmExamEntity findliat(String m1);
	

}
