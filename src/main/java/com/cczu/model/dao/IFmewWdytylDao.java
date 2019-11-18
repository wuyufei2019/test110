package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author jason
 * 现场监控预警-实时数据监测DAO
 */
public interface IFmewWdytylDao {

	/**
     * 查询实时数据监测表格
     * @return
     */
	public List<Map<String , Object>> dataGrid(Map<String, Object> mapData);
	
	/**
     * 查询实时数据监测数据表格_总记录数
     * @return
     */
	public int getTotalCount(Map<String, Object> mapData);
	
	/**
     * 根据企业id查询储罐实时数据监测数据
     * @return
     */
	public List<Map<String , Object>> findInfoById(long id);
	
	/**
     * 根据企业id查询仓库实时库存数据
     * @return
     */
	public List<Map<String , Object>> findCangKuInfoByQyid(long qyid);
	
	/**
     * 根据仓库id查询仓库物料库存数据
     * @return
     */
	public List<Map<String , Object>> findKcInfoByCkid(long ckid);
	
	/**
	 * 获得导出数据
	 * @return
	 */
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData);
	
	
	/**
     * 查询所有储罐最新数据(液位超标数据)
     * @return
     */
	public List<Map<String, Object>> findLastOverData();
}
