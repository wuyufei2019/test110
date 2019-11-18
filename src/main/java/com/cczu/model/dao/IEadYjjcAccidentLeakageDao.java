package com.cczu.model.dao;

import java.util.List;

import com.cczu.model.entity.EAD_Accident_Leakage;
/**
 * 
 * @ClassName: IEadYjjcAccidentLeakageDao
 * @Description: 应急辅助决策_事故后果计算
 * @author jason
 *
 */
public interface IEadYjjcAccidentLeakageDao {

	/**
	 * 保存
	 * @param accidentLeakage
	 */
	public void saveAccidentLeakage(EAD_Accident_Leakage accidentLeakage);

	/**
	 * 根据事故后果id获取EAD_Accident_Leakage集合
	 * 
	 * @param AccidentID
	 * @return 结果集合
	 */
	public List<EAD_Accident_Leakage> getEADAccidentLeakageListByAccidentID(
			Long AccidentID);

	/**
	 * 根据事故后果id删除EAD_Accident_Leakage集合
	 * 
	 * @param AccidentID
	 */
	public void deleteAccidentLeakage(Long accidentId);
}
	
