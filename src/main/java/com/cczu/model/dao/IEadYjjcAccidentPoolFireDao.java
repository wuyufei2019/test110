package com.cczu.model.dao;

import java.util.List;

import com.cczu.model.entity.EAD_Accident_PoolFire;
/**
 * 
 * @ClassName: IEadYjjcAccidentPoolFireDao
 * @Description: 应急辅助决策_事故后果计算
 * @author jason
 *
 */
public interface IEadYjjcAccidentPoolFireDao {

	/**
	 * 保存
	 * @param accidentPoolFire
	 */
	public void saveAccidentPoolFire(EAD_Accident_PoolFire accidentPoolFire);

	/**
	 * 根据事故后果id获取EAD_Accident_PoolFire集合
	 * 
	 * @param AccidentID
	 * @return 结果集合
	 */
	public List<EAD_Accident_PoolFire> getEADAccidentPoolFireListByAccidentID(
			Long AccidentID);

	/**
	 * 根据事故后果id获取EAD_Accident_PoolFire集合
	 * @param AccidentID
	 */
	public void deleteAccidentPoolFire(Long accidentId);
}
	
