package com.cczu.model.service;

import java.util.Map;
/**
 * 
 * @ClassName: ICommDictService
 * @Description: 自定义字典
 * @author jason
 * @date 2017年6月8日
 *
 */
public interface ICommDictService {

	/**
     * 查询数据
     * @return
     */
	public String dataList(Map<String, Object> mapData);

	/**
	 * 查询数据2
	 * @return
	 */
	public String dataList2(Map<String, Object> mapData);

	/**
	 * 物料的查询数据的
     * 查询数据
     * @return
     */
	public String datawlList(Map<String, Object> mapData);
	
}
