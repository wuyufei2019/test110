package com.cczu.model.service;

import java.util.List;

import com.cczu.model.entity.BIS_T_Safetynetenterprise;
import com.cczu.model.entity.dto.Tree_SafetyNetEnterprise;

/**
 * 
 * @ClassName: IBisAqwlglService
 * @Description: 企业基本信息_安全网络管理
 * @author jason
 * @date 2017年6月8日
 *
 */
public interface IBisAqwlglService {

	/**
	 * 获取所有
	 * @return 集合
	 */
	List<BIS_T_Safetynetenterprise> getSafetynets(Long uid);

	/**
	 * 根据ID获取对象
	 * @return 对象
	 */
	public BIS_T_Safetynetenterprise get(Long id);

	/**
	 * 保存
	 */
	public void save(BIS_T_Safetynetenterprise bis);

	/**
	 * 删除
	 */
	public void delete(Long id);
	
	/**
	 * 根据企业id获取安全网络图数据（echart）
	 * @param qyid
	 * @return
	 */
	public String getWltson(Long qyid);
	
	
	/**
	 * 根据企业ID获取treelist集合
	 * @param qyid
	 * @return
	 */
	List<Tree_SafetyNetEnterprise> getAllTreeList(Long qyid);
	
}
