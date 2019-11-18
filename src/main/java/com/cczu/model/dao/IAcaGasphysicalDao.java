package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.ACA_GasphysicalEntity;
/**
 * 
 * @ClassName: IAcaGasphysicalDao
 * @Description: 事故后果计算_压缩气体物理爆炸
 * @author jason
 *
 */
public interface IAcaGasphysicalDao {
	/**
     * 查询所有压缩气体物理爆炸记录信息
     * @return
     */
	public List<ACA_GasphysicalEntity> findAll();

	/**
     * 压缩气体物理爆炸
     * @return
     */
	public Map<String, Object> dataGrid(Map<String, Object> map);
	
	/**
     * 根据id查找压缩气体物理爆炸信息
     * @return
     */
	public ACA_GasphysicalEntity findInfoById(long id);
	
	/**
     * 根据id1查找压缩气体物理爆炸信息
     * @return
     */
	public List<ACA_GasphysicalEntity> findListInfoByUserId(long id1);
	
	/**
     * 计算保存
     * @return
	 * @throws Exception 
     */
	public Map<String,Object> saveInfo(ACA_GasphysicalEntity aca) throws Exception;

	/**
     * 应急辅助决策计算保存
     * @return
	 * @throws Exception 
     */
	public Map<String, Object> jcsaveInfo(ACA_GasphysicalEntity aca) throws Exception;
	
	/**
	 * app计算压缩气体物理爆炸后果
	 * @param str1
	 * @param str2
	 * @param str3
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> appGasphysical(String str1,String str2,String str3) throws Exception;
}
