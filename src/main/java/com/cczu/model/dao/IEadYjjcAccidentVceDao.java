package com.cczu.model.dao;

import java.util.List;

import com.cczu.model.entity.EAD_Accident_Vce;
/**
 * 
 * @ClassName: IEadYjjcAccidentVceDao
 * @Description: 应急辅助决策_事故后果计算
 * @author jason
 *
 */
public interface IEadYjjcAccidentVceDao {

	/**
	 * 保存
	 * @param accidentVce
	 */
	public void saveAccidentVce(EAD_Accident_Vce accidentVce);
	/**
	 * 根据事故后果id获取EAD_Accident_Vce集合
	 * 
	 * @param AccidentID
	 * @return 结果集合
	 */
	public List<EAD_Accident_Vce> getEADAccidentVceListByAccidentID(Long AccidentID);
	/**
	 * 根据事故后果id删除EAD_Accident_Vce集合
	 * @param AccidentID
	 */
	public void deleteAccidentVce(Long accidentId);
}
	
