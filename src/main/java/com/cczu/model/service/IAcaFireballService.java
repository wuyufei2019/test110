package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.ACA_FireballEntity;
/**
 * 
 * @ClassName: IAcaFireballService
 * @Description: 事故后果计算_火球
 * @author jason
 *
 */
public interface IAcaFireballService {
	/**
     * 查询所有火球信息
     * @return
     */
	public List<ACA_FireballEntity> findAll();
	
	/**
     * 根据id1查找火球信息
     * @return
     */
	public List<ACA_FireballEntity> findAllByUserId(long id1);

	/**
     * 火球
     * @return
     */
	public Map<String, Object> dataGrid(Map<String, Object> mapData);
	
	/**
     * 根据id查找火球信息
     * @return
     */
	public ACA_FireballEntity findInfoById(long id);
	
	/**
     * 计算保存
     * @return
	 * @throws Exception 
     */
	public String countSave(ACA_FireballEntity aca) throws Exception;
	/**
     * 应急辅助决策计算保存
     * @return
	 * @throws Exception 
     */
	public String jccountSave(ACA_FireballEntity aca) throws Exception;
	
	
	/**
	 * app获取火球事故计算后果所需的值
	 * @param str1 泄露量str1
	 * @param str2 燃烧热str2
	 * @return
	 */
	public List<Map<String,Object>> appFire(String str1,String str2) throws Exception;
	
}
