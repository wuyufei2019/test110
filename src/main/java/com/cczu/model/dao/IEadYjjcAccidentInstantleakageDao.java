package com.cczu.model.dao;

import java.util.List;

import com.cczu.model.entity.EAD_Accident_Instantleakage;
/**
 * 
 * @ClassName: IEadYjjcAccidentInstantleakageDao
 * @Description: 应急辅助决策_事故后果计算
 * @author jason
 *
 */
public interface IEadYjjcAccidentInstantleakageDao {

	/**
	 * 保存
	 * @param accidentInstantleakage
	 */
	public void saveAccidentInstantleakage(EAD_Accident_Instantleakage accidentInstantleakage);

	/**
	 * 根据事故后果id获取EAD_Accident_Instantleakage集合
	 * 
	 * @param AccidentID
	 * @return 结果集合
	 */
	public List<EAD_Accident_Instantleakage> getEADAccidentInstantleakageListByAccidentID(Long AccidentID);
	/**
	 * 根据事故后果id删除EAD_Accident_Instantleakage集合
	 * 
	 * @param AccidentID
	 */
	void deleteAccidentInstantleakage(Long accidentId);
}
	
