package com.cczu.model.service;

import java.util.List;
import java.util.Map;


/**
 * 
 * @ClassName: ITdicAcawxhxpService
 * @Description: 字典
 * @author jason
 *
 */
public interface ITdicAcawxhxpService {
	/**
	 * 字典json
	 * @return 集合
	 */
	public String acawxhxpJson();

	/**
	 * 根据id
	 * @return 集合
	 */
	public String acawxhxpJsonByTid(Long tid);
	
	
	/**
	 * app获取事故后果计算的数据
	 * @return
	 */
	public List<Map<String,Object>> getAllxx(String sum);
	
	/**
	 * 根据物料名称查询所有
	 * @return 集合
	 */
	public String findByName(String name);
	
}
