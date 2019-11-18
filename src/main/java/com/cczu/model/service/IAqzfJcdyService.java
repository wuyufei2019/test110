package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.AQZF_SafetyCheckUnitEntity;

public interface IAqzfJcdyService {

	/**
	 * 查询检查单元记录list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData);

	/**
	 * 得到jcdylist
	 * @return
	 */
	public List<Map<String, Object>> getjcdylist();

	/**
	 * 添加信息
	 * @param jcdy
	 */
	public void addInfo(AQZF_SafetyCheckUnitEntity jcdy);
	
	/**
	 * 删除
	 * @param parseLong
	 */
	public void deleteInfo(long parseLong);

	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public AQZF_SafetyCheckUnitEntity findById(Long id);

	/**
	 * 修改
	 * @param jcdy
	 */
	public void updateInfo(AQZF_SafetyCheckUnitEntity jcdy);
}
