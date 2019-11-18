package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.ACA_LeakageEntity;
/**
 * 
 * @ClassName: IAcaLeakageService
 * @Description: 事故后果计算_持续泄漏
 * @author jason
 *
 */
public interface IAcaLeakageService {
	/**
     * 查询所有持续泄漏信息
     * @return
     */
	public List<ACA_LeakageEntity> findAll();
	
	/**
     * 根据id1查找持续泄漏信息
     * @return
     */
	public List<ACA_LeakageEntity> findAllByUserId(long id1);

	/**
     * 持续泄漏
     * @return
     */
	public Map<String, Object> dataGrid(Map<String, Object> mapData);
	
	/**
     * 根据id查找持续泄漏信息
     * @return
     */
	public ACA_LeakageEntity findInfoById(long id);
	
	/**
     * 计算保存
     * @return
	 * @throws Exception 
     */
	public String countSave(ACA_LeakageEntity aca) throws Exception;

	/**
     * 应急辅助决策计算保存
     * @return
	 * @throws Exception 
     */
	public String jccountSave(ACA_LeakageEntity aca) throws Exception;
	
	/**
	 * app获取持续泄漏信息
	 * @param aca
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> saveInfo(ACA_LeakageEntity aca) throws Exception;
}
