package com.cczu.model.dao;

import java.util.List;

import com.cczu.model.entity.EAD_Accident_Fireball;
/**
 * 
 * @ClassName: IEadYjjcAccidentFireballDao
 * @Description: 应急辅助决策_事故后果计算
 * @author jason
 *
 */
public interface IEadYjjcAccidentFireballDao {

	/**
	 * 保存
	 * @param accidentFireball
	 */
	public void saveAccidentFireball(EAD_Accident_Fireball accidentFireball);

	/**
	 * 根据事故后果id获取EAD_Accident_Instantleakage集合
	 * 
	 * @param AccidentID
	 * @return 结果集合
	 */
	public List<EAD_Accident_Fireball> getEADAccidentFireballListByAccidentID(Long AccidentID);
	/**
	 * 根据事故后果id删除EAD_Accident_Instantleakage集合
	 * @param AccidentID
	 */
	public void deleteAccidentFireball(Long accidentId);
}
	
