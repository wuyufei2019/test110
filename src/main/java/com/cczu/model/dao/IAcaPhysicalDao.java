package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.ACA_PhysicalEntity;
/**
 * 
 * @ClassName: IAcaPhysicalDao
 * @Description: 事故后果计算_物理爆炸（压力容器爆炸）
 * @author jason
 *
 */
public interface IAcaPhysicalDao {
	/**
     * 查询所有物理爆炸（压力容器爆炸）记录信息
     * @return
     */
	public List<ACA_PhysicalEntity> findAll();

	/**
     * 物理爆炸（压力容器爆炸）
     * @return
     */
	public Map<String, Object> dataGrid(Map<String, Object> map);
	
	/**
     * 根据id查找物理爆炸（压力容器爆炸）信息
     * @return
     */
	public ACA_PhysicalEntity findInfoById(long id);
	
	/**
     * 根据id1查找物理爆炸（压力容器爆炸）信息
     * @return
     */
	public List<ACA_PhysicalEntity> findListInfoByUserId(long id1);
	
	/**
     * 计算保存
     * @return
	 * @throws Exception 
     */
	public Map<String,Object> saveInfo(ACA_PhysicalEntity aca) throws Exception;

	/**
     * 应急辅助决策计算保存
     * @return
	 * @throws Exception 
     */
	public Map<String, Object> jcsaveInfo(ACA_PhysicalEntity aca) throws Exception;
	
	/**
	 * app接口获取事故计算后果的值--事故后果计算_物理爆炸（压力容器爆炸）
	 * @param str1 压力
	 * @param str2 容积
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> appPhysical(String str1,String str2) throws Exception;
}
