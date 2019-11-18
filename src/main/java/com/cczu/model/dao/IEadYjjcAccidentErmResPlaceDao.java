package com.cczu.model.dao;

import java.util.List;

import com.cczu.model.entity.EAD_Accident_ERM_ResPlace;
/**
 * 
 * @ClassName: IEadYjjcAccidentErmResPlaceDao
 * @Description: 应急辅助决策_应急资源管理_应急装备
 * @author jason
 *
 */
public interface IEadYjjcAccidentErmResPlaceDao {

	/**
	 * 保存
	 * @param EAD_Accident_ERM_ResPlace erm_ResPlace
	 */
	public void saveAccidentErmResPlace(EAD_Accident_ERM_ResPlace erm_ResPlace);

	/**
	 * 根据事故后果id获取EAD_Accident_ERM_ResPlace集合
	 * 
	 * @param AccidentID
	 * @return 结果集合
	 */
	public List<EAD_Accident_ERM_ResPlace> getEADAccidentERMResPlaceListByAccidentID(Long AccidentID);

	/**
	 * 根据事故后果id删除EAD_Accident_ERM_ResPlace集合
	 * @param AccidentID
	 */
	public void deleteERMResPlaceListByAccidentID(Long accidentId);
}
	
