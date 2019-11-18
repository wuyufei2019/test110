package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.ACA_GasphysicalEntity;
/**
 * 
 * @ClassName: IAcaGasphysicalService
 * @Description: 事故后果计算_压缩气体物理爆炸
 * @author jason
 *
 */
public interface IAcaGasphysicalService {
	/**
     * 查询所有压缩气体物理爆炸信息
     * @return
     */
	public List<ACA_GasphysicalEntity> findAll();
	
	/**
     * 根据id1查找压缩气体物理爆炸信息
     * @return
     */
	public List<ACA_GasphysicalEntity> findAllByUserId(long id1);

	/**
     * 压缩气体物理爆炸
     * @return
     */
	public Map<String, Object> dataGrid(Map<String, Object> mapData);
	
	/**
     * 根据id查找压缩气体物理爆炸信息
     * @return
     */
	public ACA_GasphysicalEntity findInfoById(long id);
	
	/**
     * 计算保存
     * @return
	 * @throws Exception 
     */
	public String countSave(ACA_GasphysicalEntity aca) throws Exception;
	/**
     * 应急辅助决策计算保存
     * @return
	 * @throws Exception 
     */
	public String jccountSave(ACA_GasphysicalEntity aca) throws Exception;
	
	
	
	/**
	 * app计算事故后果的结果_压缩气体物理爆炸
	 * @param str1 热容比
	 * @param str2 压力
	 * @param str3 容积
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> appGasphysical(String str1,String str2,String str3) throws Exception;
}
