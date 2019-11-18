package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.ACA_InstantleakageEntity;
/**
 * 
 * @ClassName: IAcaInstantleakageDao
 * @Description: 事故后果计算_瞬时泄漏
 * @author jason
 *
 */
public interface IAcaInstantleakageDao {
	/**
     * 查询所有瞬时泄漏记录信息
     * @return
     */
	public List<ACA_InstantleakageEntity> findAll();

	/**
     * 瞬时泄漏
     * @return
     */
	public Map<String, Object> dataGrid(Map<String, Object> map);
	
	/**
     * 根据id查找瞬时泄漏信息
     * @return
     */
	public ACA_InstantleakageEntity findInfoById(long id);
	
	/**
     * 根据id1查找瞬时泄漏信息
     * @return
     */
	public List<ACA_InstantleakageEntity> findListInfoByUserId(long id1);
	
	/**
     * 计算保存
     * @return
	 * @throws Exception 
     */
	public Map<String,Object> saveInfo(ACA_InstantleakageEntity aca) throws Exception;

	/**
     * 应急辅助决策计算保存
     * @return
	 * @throws Exception 
     */
	public Map<String, Object> jcsaveInfo(ACA_InstantleakageEntity aca)
			throws Exception;

	/**
     * 应急辅助决策计算保存
     * @return
	 * @throws Exception 
     */
	public List<Map<String, Object>> jcsaveTimeAllInfo(ACA_InstantleakageEntity aca)
			throws Exception;
	
	/**
	 * app事故后果计算_瞬时泄漏的接口
	 * @param stra1  C边界浓度
	 * @param stra2  泄漏质量
	 * @param stra3  地面风速
	 * @param stra4  风向
	 * @param stra5  天气条件
	 * @param stra6  经度
	 * @param stra7  纬度
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> appInstantleakage(String stra1,String stra2,
			String stra3,String stra4,String stra5,String stra6,String stra7) throws Exception;
}
