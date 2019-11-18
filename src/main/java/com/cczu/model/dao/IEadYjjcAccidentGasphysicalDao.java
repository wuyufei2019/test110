package com.cczu.model.dao;

import java.util.List;

import com.cczu.model.entity.EAD_Accident_Gasphysical;
/**
 * 
 * @ClassName: IEadYjjcAccidentGasphysicalDao
 * @Description: 应急辅助决策_事故后果计算
 * @author jason
 *
 */
public interface IEadYjjcAccidentGasphysicalDao {

	/**
	 * 保存
	 * @param accidentGasphysical
	 */
	public void saveAccidentGasphysical(EAD_Accident_Gasphysical accidentGasphysical);

	/**
	 * 根据事故后果id获取EAD_Accident_Gasphysical集合
	 * 
	 * @param AccidentID
	 * @return 结果集合
	 */
	public List<EAD_Accident_Gasphysical> getEADAccidentGasphysicalListByAccidentID(Long AccidentID);

	/**
	 * 根据事故后果id删除EAD_Accident_Gasphysical集合
	 * 
	 * @param AccidentID
	 */
	public void deleteAccidentGasphysical(Long accidentId);
}
	
