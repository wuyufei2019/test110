package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.ACA_LeakageEntity;
/**
 * 
 * @ClassName: IAcaLeakageDao
 * @Description: 事故后果计算_持续泄漏
 * @author jason
 *
 */
public interface IAcaLeakageDao {
	/**
     * 查询所有持续泄漏记录信息
     * @return
     */
	public List<ACA_LeakageEntity> findAll();

	/**
     * 持续泄漏
     * @return
     */
	public Map<String, Object> dataGrid(Map<String, Object> map);
	
	/**
     * 根据id查找持续泄漏信息
     * @return
     */
	public ACA_LeakageEntity findInfoById(long id);
	
	/**
     * 根据id1查找持续泄漏信息
     * @return
     */
	public List<ACA_LeakageEntity> findListInfoByUserId(long id1);
	
	/**
     * 计算保存
     * @return
	 * @throws Exception 
     */
	public Map<String,Object> saveInfo(ACA_LeakageEntity aca) throws Exception;

	/**
     * 应急辅助决策计算保存
     * @return
	 * @throws Exception 
     */
	public Map<String, Object> jcsaveInfo(ACA_LeakageEntity aca) throws Exception;
	
	/**
	 * app接口要的
	 * @param aca
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> savesInfo(ACA_LeakageEntity aca) throws Exception;
}
