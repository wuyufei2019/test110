package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.EAD_AccidentWordEntity;

public interface IEadYjjcAccidentWordDao {

	/**
	 * 根据id查询对象
	 * @param id
	 * @return
	 */
	public EAD_AccidentWordEntity findById(Long id);

	/**
	 * 根据AccidentId查询对象
	 * @param id
	 * @return
	 */
	public EAD_AccidentWordEntity findByAccidentId(Long id);

	/**
	 * dataGrid集合
	 * @param map
	 * @return
	 */
	public List<EAD_AccidentWordEntity> dataGridAccidentWord(Map<String, Object> map);

	/**
	 * dataGrid 总数
	 * @param map
	 * @return
	 */
	public int getTotaldataGridAccidentWord(Map<String, Object> map);

	/**
	 * 删除数据
	 * @param id
	 */
	public void deleteInfo(Long id);

	/**
	 * 保存
	 * @param id
	 */
	public void saveInfo(EAD_AccidentWordEntity ead);

}
