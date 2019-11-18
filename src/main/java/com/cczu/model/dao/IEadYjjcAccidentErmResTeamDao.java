package com.cczu.model.dao;

import java.util.List;

import com.cczu.model.entity.EAD_Accident_ERM_ResTeam;
/**
 * 
 * @ClassName: IEadYjjcAccidentERMResTeamDao
 * @Description: 应急辅助决策_应急资源管理_应急队伍
 * @author jason
 *
 */
public interface IEadYjjcAccidentErmResTeamDao {

	/**
	 * 保存
	 * @param ead_Accident_ERM_ResTeam
	 */
	public void saveAccidentErmResTeam(EAD_Accident_ERM_ResTeam ead_Accident_ERM_ResTeam);

	/**
	 * 根据事故后果id获取EAD_Accident_ERM_ResTeam集合
	 * 
	 * @param AccidentID
	 * @return 结果集合
	 */
	public List<EAD_Accident_ERM_ResTeam> getEADAccidentERMResTeamListByAccidentID(Long AccidentID);

	/**
	 * 根据事故后果id删除EAD_Accident_ERM_ResTeam集合
	 * @param AccidentID
	 */
	public void deleteERMResTeamListByAccidentID(Long accidentId);
}
	
