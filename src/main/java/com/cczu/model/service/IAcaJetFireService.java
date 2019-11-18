package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.ACA_JetFireEntity;
/**
 * 
 * @ClassName: IAcaJetFireService
 * @Description: 事故后果计算_池火灾
 * @author jason
 *
 */
public interface IAcaJetFireService {
	/**
     * 查询所有池火灾信息
     * @return
     */
	public List<ACA_JetFireEntity> findAll();
	
	/**
     * 根据id1查找池火灾信息
     * @return
     */
	public List<ACA_JetFireEntity> findAllByUserId(long id1);

	/**
     * 池火灾
     * @return
     */
	public Map<String, Object> dataGrid(Map<String, Object> mapData);
	
	/**
     * 根据id查找池火灾信息
     * @return
     */
	public ACA_JetFireEntity findInfoById(long id);
	
	/**
     * 计算保存
     * @return
	 * @throws Exception 
     */
	public String countSave(ACA_JetFireEntity aca) throws Exception;

	/**
     * 应急辅助决策计算保存
     * @return
	 * @throws Exception 
     */
	public String jccountSave(ACA_JetFireEntity aca) throws Exception;
	
	/**
	 * app事故计算后果的值_喷射火
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
