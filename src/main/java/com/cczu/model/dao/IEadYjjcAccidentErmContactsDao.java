package com.cczu.model.dao;

import java.util.List;

import com.cczu.model.entity.EAD_Accident_ERM_Contacts;
/**
 * 
 * @ClassName: IEadYjjcAccidentErmContactsDao
 * @Description: 应急辅助决策_应急资源管理_应急通讯录
 * @author jason
 *
 */
public interface IEadYjjcAccidentErmContactsDao {

	/**
	 * 保存
	 * @param EAD_Accident_ERM_Contacts erm_Contacts
	 */
	public void saveAccidentErmContacts(EAD_Accident_ERM_Contacts erm_Contacts);
	/**
	 * 根据事故后果id获取EAD_Accident_ERM_Contacts集合
	 * 
	 * @param AccidentID
	 * @return 结果集合
	 */
	public List<EAD_Accident_ERM_Contacts> getEADAccidentERMContactsListByAccidentID(Long AccidentID);
	/**
	 * 根据事故后果id删除EAD_Accident_ERM_Contacts集合
	 * @param AccidentID
	 */
	public void deleteERMContactsListByAccidentID(Long accidentId);

}
	
