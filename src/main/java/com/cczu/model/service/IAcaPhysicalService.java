package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.ACA_PhysicalEntity;
/**
 * 
 * @ClassName: IAcaPhysicalService
 * @Description: 事故后果计算_物理爆炸（压力容器爆炸）
 * @author jason
 *
 */
public interface IAcaPhysicalService {
	/**
     * 查询所有物理爆炸（压力容器爆炸）信息
     * @return
     */
	public List<ACA_PhysicalEntity> findAll();
	
	/**
     * 根据id1查找物理爆炸（压力容器爆炸）信息
     * @return
     */
	public List<ACA_PhysicalEntity> findAllByUserId(long id1);

	/**
     * 物理爆炸（压力容器爆炸）
     * @return
     */
	public Map<String, Object> dataGrid(Map<String, Object> mapData);
	
	/**
     * 根据id查找物理爆炸（压力容器爆炸）信息
     * @return
     */
	public ACA_PhysicalEntity findInfoById(long id);
	
	/**
     * 计算保存
     * @return
	 * @throws Exception 
     */
	public String countSave(ACA_PhysicalEntity aca) throws Exception;

	/**
     * 应急辅助决策计算保存
     * @return
	 * @throws Exception 
     */
	public String jccountSave(ACA_PhysicalEntity aca) throws Exception;
	
	/**
	 * app接口获取事故计算后果的值--事故后果计算_物理爆炸（压力容器爆炸）
	 * @param str1 压力
	 * @param str2 容积
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> appPhysical(String str1,String str2) throws Exception;
}
