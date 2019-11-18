package com.cczu.model.dao;

import java.util.List;

import com.cczu.model.entity.EAD_Accident_ERM_DispTechnology;
/**
 * 
 * @ClassName: IEadYjjcAccidentErmDispTechnologyDao
 * @Description: 应急辅助决策_应急资源管理_应急处置技术
 * @author jason
 *
 */
public interface IEadYjjcAccidentErmDispTechnologyDao {

	/**
	 * 保存
	 * @param EAD_Accident_ERM_DispTechnology erm_DispTechnology
	 */
	public void saveAccidentErmDispTechnology(EAD_Accident_ERM_DispTechnology erm_DispTechnology);

	/**
	 * 根据事故后果id获取EAD_Accident_ERM_DispTechnology集合
	 * 
	 * @param AccidentID
	 * @return 结果集合
	 */
	public List<EAD_Accident_ERM_DispTechnology> getEADAccidentERMDispTechnologyListByAccidentID(Long AccidentID);

	/**
	 * 根据事故后果id删除EAD_Accident_ERM_DispTechnology集合
	 * @param AccidentID
	 */
	public void deleteERMDispTechnologyListByAccidentID(Long accidentId);

}
	
