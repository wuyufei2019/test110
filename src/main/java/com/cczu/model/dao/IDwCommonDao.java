package com.cczu.model.dao;

import java.util.List;
import java.util.Map;



public interface IDwCommonDao {
	/**
	 * 
	 * 通过企业id查找作业证
	 * @param id
	 * @return
	 */
	public Map<String, Object> getCurrentTask(Long qyid, Integer userId);
	/**
	 * 
	 * 通过企业id查找作业证
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> getWorkList(Long qyid, String searchCon);
	/**
	 * 
	 * 通过企业id查找分配任务
	 * @param id
	 * @return
	 */
	public Map<String, Object> getUserList(Long qyid);
	
	/**
	 * 
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData);
	
	/**
	 * 
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getWorkByRfid(Long qyid, String rfid);
	
	/**
	 * 
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> getCurrentTaskDhzyCnt(Long qyid, Integer userId, String rfid);
	
	/**
	 * 
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> getCurrentTaskSxkjCnt(Long qyid, Integer userId, String rfid);
	
	/**
	 * 
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> getCurrentTaskMbcdCnt(Long qyid, Integer userId, String rfid);
	
	/**
	 * 
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> getCurrentTaskGcaqCnt(Long qyid, Integer userId, String rfid);
	
	/**
	 * 
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> getCurrentTaskDzaqCnt(Long qyid, Integer userId, String rfid);
	
	/**
	 * 
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> getCurrentTaskLsydCnt(Long qyid, Integer userId, String rfid);
	
	/**
	 * 
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> getCurrentTaskDtaqCnt(Long qyid, Integer userId, String rfid);
	
	/**
	 * 
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> getCurrentTaskDlaqCnt(Long qyid, Integer userId, String rfid);
	

	/**
	 * 安监局查看的所有企业
	 * @return
	 */
	public List<Map<String, Object>> getQyListForAjDhzy(Map<String, Object> mapData);
	
	
	/**
	 * 安监局查看列表
	 * @return
	 */
	public List<Map<String, Object>> getWkListForAjDhzy(Map<String, Object> mapData);
	
	
	/**
	 * 安监局查看作业证
	 * @return
	 */
	public Map<String, Object> getWkInfoForAjDhzy(Long id);
	
	/**
	 * 受限空间
	 * @return
	 */
	public List<Map<String, Object>> getQyListForAjSxkj(Map<String, Object> mapData);
	public List<Map<String, Object>> getWkListForAjSxkj(Map<String, Object> mapData);
	public Map<String, Object> getWkInfoForAjSxkj(Long id);
	
	/**
	 * 盲板抽堵
	 * @return
	 */
	public List<Map<String, Object>> getQyListForAjMbcd(Map<String, Object> mapData);
	public List<Map<String, Object>> getWkListForAjMbcd(Map<String, Object> mapData);
	public Map<String, Object> getWkInfoForAjMbcd(Long id);
	
	/**
	 * 高出安全
	 * @return
	 */
	public List<Map<String, Object>> getQyListForAjGcaq(Map<String, Object> mapData);
	public List<Map<String, Object>> getWkListForAjGcaq(Map<String, Object> mapData);
	public Map<String, Object> getWkInfoForAjGcaq(Long id);
	
	/**
	 * 吊装安全
	 * @return
	 */
	public List<Map<String, Object>> getQyListForAjDzaq(Map<String, Object> mapData);
	public List<Map<String, Object>> getWkListForAjDzaq(Map<String, Object> mapData);
	public Map<String, Object> getWkInfoForAjDzaq(Long id);
	
	/**
	 * 临时用电
	 * @return
	 */
	public List<Map<String, Object>> getQyListForAjLsyd(Map<String, Object> mapData);
	public List<Map<String, Object>> getWkListForAjLsyd(Map<String, Object> mapData);
	public Map<String, Object> getWkInfoForAjLsyd(Long id);
	
	/**
	 * 动土安全
	 * @return
	 */
	public List<Map<String, Object>> getQyListForAjDtaq(Map<String, Object> mapData);
	public List<Map<String, Object>> getWkListForAjDtaq(Map<String, Object> mapData);
	public Map<String, Object> getWkInfoForAjDtaq(Long id);
	
	/**
	 * 断路安全
	 * @return
	 */
	public List<Map<String, Object>> getQyListForAjDlaq(Map<String, Object> mapData);
	public List<Map<String, Object>> getWkListForAjDlaq(Map<String, Object> mapData);
	public Map<String, Object> getWkInfoForAjDlaq(Long id);
}
