package com.cczu.model.dao;

import java.util.List;

import com.cczu.model.entity.EAD_Accident_Physical;
/**
 * 
 * @ClassName: IEadYjjcAccidentPhysicalDao
 * @Description: 应急辅助决策_事故后果计算
 * @author jason
 *
 */
public interface IEadYjjcAccidentPhysicalDao {

	/**
	 * 保存
	 * @param accidentPhysical
	 */
	public void saveAccidentPhysical(EAD_Accident_Physical accidentPhysical);

	/**
	 * 根据事故后果id获取EAD_Accident_Physical集合
	 * 
	 * @param AccidentID
	 * @return 结果集合
	 */
	public List<EAD_Accident_Physical> getEADAccidentPhysicalListByAccidentID(
			Long AccidentID);

	/**
	 * 根据事故后果id删除EAD_Accident_Physical集合
	 * @param AccidentID
	 */
	public void deleteAccidentPhysical(Long accidentId);
}
	
