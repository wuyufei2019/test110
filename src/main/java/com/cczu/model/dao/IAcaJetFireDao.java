package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.ACA_JetFireEntity;
/**
 * 
 * @ClassName: IAcaJetFireDao
 * @Description: 事故后果计算_喷射火
 * @author jason
 *
 */
public interface IAcaJetFireDao {
	/**
     * 查询所有喷射火记录信息
     * @return
     */
	public List<ACA_JetFireEntity> findAll();

	/**
     * 喷射火
     * @return
     */
	public Map<String, Object> dataGrid(Map<String, Object> map);
	
	/**
     * 根据id查找喷射火信息
     * @return
     */
	public ACA_JetFireEntity findInfoById(long id);
	
	/**
     * 根据id1查找喷射火信息
     * @return
     */
	public List<ACA_JetFireEntity> findListInfoByUserId(long id1);
	
	/**
     * 计算保存
     * @return
	 * @throws Exception 
     */
	public Map<String,Object> saveInfo(ACA_JetFireEntity aca) throws Exception;

	/**
     * 应急辅助决策计算保存
     * @return
	 * @throws Exception 
     */
	public Map<String, Object> jcsaveInfo(ACA_JetFireEntity aca) throws Exception;
	
	
	/**app事故计算后果的值_喷射火
	 * @param str1 比热容
	 * @param str2 压力：P
	 * @param str3 分子量：M
	 * @param str4 燃烧热：Er
	 * @param str5 孔径：D
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> appJetFire(String str1,String str2,String str3,String str4,String str5) throws Exception;
}
