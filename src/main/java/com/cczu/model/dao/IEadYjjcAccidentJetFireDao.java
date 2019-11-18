package com.cczu.model.dao;

import java.util.List;

import com.cczu.model.entity.EAD_Accident_JetFire;
/**
 * 
 * @ClassName: IEadYjjcAccidentJetFireDao
 * @Description: 应急辅助决策_事故后果计算
 * @author jason
 *
 */
public interface IEadYjjcAccidentJetFireDao {

	/**
	 * 保存
	 * @param accidentJetFire
	 */
	public void saveAccidentJetFire(EAD_Accident_JetFire accidentJetFire);

	/**
	 * 根据事故后果id获取EAD_Accident_JetFire集合
	 * 
	 * @param AccidentID
	 * @return 结果集合
	 */
	public List<EAD_Accident_JetFire> getEADAccidentJetFireListByAccidentID(
			Long AccidentID);

	/**
	 * 根据事故后果id删除EAD_Accident_JetFire集合
	 * @param AccidentID
	 */
	public void deleteAccidentJetFire(Long accidentId);
}
	
