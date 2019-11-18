package com.cczu.model.dao;

import java.util.List;

import com.cczu.model.entity.EAD_Accident_ERM_ResInstrument;
/**
 * 
 * @ClassName: IEadYjjcAccidentErmResInstrumentDao
 * @Description: 应急辅助决策_应急资源管理_应急装备
 * @author jason
 *
 */
public interface IEadYjjcAccidentErmResInstrumentDao {

	/**
	 * 保存
	 * @param EAD_Accident_ERM_ResInstrument erm_ResInstrument
	 */
	public void saveAccidentErmResInstrument(EAD_Accident_ERM_ResInstrument erm_ResInstrument);

	/**
	 * 根据事故后果id获取EAD_Accident_ERM_ResInstrument集合
	 * 
	 * @param AccidentID
	 * @return 结果集合
	 */
	public List<EAD_Accident_ERM_ResInstrument> getEADAccidentERMResInstrumentListByAccidentID(Long AccidentID);

	/**
	 * 根据事故后果id删除EAD_Accident_ERM_ResInstrument集合
	 * @param AccidentID
	 */
	public void deleteERMResInstrumentListByAccidentID(Long accidentId);
}
	
