package com.cczu.model.dao;

import java.util.List;

import com.cczu.model.entity.EAD_Accident_ERM_ResExpert;
/**
 * 
 * @ClassName: IEadYjjcAccidentErmResExpertDao
 * @Description: 应急辅助决策_应急资源管理_应急专家
 * @author jason
 *
 */
public interface IEadYjjcAccidentErmResExpertDao {

	/**
	 * 保存
	 * @param EAD_Accident_ERM_ResExpert erm_ResExpert
	 */
	public void saveAccidentErmResExpert(EAD_Accident_ERM_ResExpert erm_ResExpert);

	/**
	 * 根据事故后果id获取EAD_Accident_ERM_ResExpert集合
	 * 
	 * @param AccidentID
	 * @return 结果集合
	 */
	public List<EAD_Accident_ERM_ResExpert> getEADAccidentERMResExpertListByAccidentID(Long AccidentID);

	/**
	 * 根据事故后果id删除EAD_Accident_ERM_ResExpert集合
	 * @param AccidentID
	 */
	public void deleteERMResExpertListByAccidentID(Long accidentId);
}
	
