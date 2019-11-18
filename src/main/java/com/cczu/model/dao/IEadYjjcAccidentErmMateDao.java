package com.cczu.model.dao;

import java.util.List;

import com.cczu.model.entity.EAD_Accident_ERM_Mate;
/**
 * 
 * @ClassName: IEadYjjcAccidentErmMateDao
 * @Description: 应急辅助决策_应急资源管理_应急装备
 * @author jason
 *
 */
public interface IEadYjjcAccidentErmMateDao {

	/**
	 * 保存
	 * @param EAD_Accident_ERM_Mate erm_Mate
	 */
	public void saveAccidentErmMate(EAD_Accident_ERM_Mate erm_Mate);

	/**
	 * 根据事故后果id获取EAD_Accident_ERM_Mate集合
	 * 
	 * @param AccidentID
	 * @return 结果集合
	 */
	public List<EAD_Accident_ERM_Mate> getEADAccidentERMMateListByAccidentID(Long AccidentID);

	/**
	 * 根据事故后果id删除EAD_Accident_ERM_Mate集合
	 * @param AccidentID
	 */
	public void deleteERMMateListByAccidentID(Long accidentId);
}
	
