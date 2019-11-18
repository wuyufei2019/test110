package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.ACA_PoolFireEntity;
/**
 * 
 * @ClassName: IAcaPoolFireDao
 * @Description: 事故后果计算_池火灾
 * @author jason
 *
 */
public interface IAcaPoolFireDao {
	/**
     * 查询所有池火灾记录信息
     * @return
     */
	public List<ACA_PoolFireEntity> findAll();

	/**
     * 池火灾
     * @return
     */
	public Map<String, Object> dataGrid(Map<String, Object> map);
	
	/**
     * 根据id查找池火灾信息
     * @return
     */
	public ACA_PoolFireEntity findInfoById(long id);
	
	/**
     * 根据id1查找池火灾信息
     * @return
     */
	public List<ACA_PoolFireEntity> findListInfoByUserId(long id1);
	
	/**
     * 计算保存
     * @return
	 * @throws Exception 
     */
	public Map<String,Object> saveInfo(ACA_PoolFireEntity aca) throws Exception;

	/**
     * 应急辅助决策计算保存
     * @return
	 * @throws Exception 
     */
	public Map<String, Object> jcsaveInfo(ACA_PoolFireEntity aca) throws Exception;
	
	
	/**
	 * app事故后果计算_池火灾
	 * @param str1 比热容：Cp
	 * @param str2 沸点：Tf
	 * @param str3 密度：Ds
	 * @param str4 燃烧热：Er
	 * @param str5 相对湿度RH
	 * @param str6 蒸发热：Hv
	 * @param str7 液池半径：D
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> appPoolFire(String str1,String str2,String str3,
			String str4,String str5,String str6,String str7) throws Exception;
}
