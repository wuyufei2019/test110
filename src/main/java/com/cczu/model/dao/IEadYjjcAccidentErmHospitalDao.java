package com.cczu.model.dao;

import java.util.List;

import com.cczu.model.entity.EAD_Accident_ERM_Hospital;
/**
 * 
 * @ClassName: IEadYjjcAccidentErmHospitalDao
 * @Description: 应急辅助决策_应急资源管理_应急医院
 * @author jason
 *
 */
public interface IEadYjjcAccidentErmHospitalDao {

	/**
	 * 保存
	 * @param EAD_Accident_ERM_Hospital erm_Hospital
	 */
	public void saveAccidentErmHospital(EAD_Accident_ERM_Hospital erm_Hospital);
	/**
	 * 根据事故后果id获取EAD_Accident_ERM_Hospital集合
	 * 
	 * @param AccidentID
	 * @return 结果集合
	 */
	public List<EAD_Accident_ERM_Hospital> getEADAccidentERMHospitalListByAccidentID(Long AccidentID);
	/**
	 * 根据事故后果id删除EAD_Accident_ERM_Hospital集合
	 * @param AccidentID
	 */
	public void deleteERMHospitalListByAccidentID(Long accidentId);

}
	
