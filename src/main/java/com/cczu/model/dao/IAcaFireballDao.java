package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.ACA_FireballEntity;
/**
 * 
 * @ClassName: IAcaFireballDao
 * @Description: 事故后果计算_火球
 * @author jason
 *
 */
public interface IAcaFireballDao {
	/**
     * 查询所有火球记录信息
     * @return
     */
	public List<ACA_FireballEntity> findAll();

	/**
     * 火球
     * @return
     */
	public Map<String, Object> dataGrid(Map<String, Object> map);
	
	/**
     * 根据id查找火球信息
     * @return
     */
	public ACA_FireballEntity findInfoById(long id);
	
	/**
     * 根据id1查找火球信息
     * @return
     */
	public List<ACA_FireballEntity> findListInfoByUserId(long id1);
	
	/**
     * 计算保存
     * @return
	 * @throws Exception 
     */
	public Map<String,Object> saveInfo(ACA_FireballEntity aca) throws Exception;
	/**
     * 应急辅助决策计算保存
     * @return
	 * @throws Exception 
     */
	public Map<String, Object> jcsaveInfo(ACA_FireballEntity aca) throws Exception;
	
	/**
	 * 用于app端获取火球计算后果的数据获取
	 * @param str1 泄露量str1
	 * @param str2 燃烧热str2
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> appFire(String str1,String str2) throws Exception;
}
