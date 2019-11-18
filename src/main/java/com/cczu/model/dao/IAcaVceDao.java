package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.ACA_VceEntity;
/**
 * 
 * @ClassName: IAcaVceDao
 * @Description: 事故后果计算_化学爆炸（蒸气云爆炸）
 * @author jason
 *
 */
public interface IAcaVceDao {
	/**
     * 查询所有化学爆炸（蒸气云爆炸）记录信息
     * @return
     */
	public List<ACA_VceEntity> findAll();

	/**
     * 化学爆炸（蒸气云爆炸）
     * @return
     */
	public Map<String, Object> dataGrid(Map<String, Object> map);
	
	/**
     * 根据id查找化学爆炸（蒸气云爆炸）信息
     * @return
     */
	public ACA_VceEntity findInfoById(long id);
	
	/**
     * 根据id1查找化学爆炸（蒸气云爆炸）信息
     * @return
     */
	public List<ACA_VceEntity> findListInfoByUserId(long id1);
	
	/**
     * 计算保存
     * @return
	 * @throws Exception 
     */
	public Map<String,Object> saveInfo(ACA_VceEntity aca) throws Exception;

	/**
     * 应急辅助决策计算保存
     * @return
	 * @throws Exception 
     */
	public Map<String, Object> jcsaveInfo(ACA_VceEntity aca) throws Exception;
	
	/**
	 * app 事故后果计算_化学爆炸（蒸气云爆炸）
	 * @param str1 泄露量
	 * @param str2 燃烧热
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> appVce(String str1,String str2) throws Exception;
}
