package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

 
/**
 * @author jason
 * 现场监控预警-实时数据监测Service
 */
public interface IFmewWdytylService {
	/**
     * 查询实时数据监测表格
     * @return
     */
	public  Map<String, Object> dataGrid(Map<String, Object> mapData);
	
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
     * 根据企业id查询每个仓库每种物料的实时库存数据
     * @return
     */
	public List<Map<String , Object>> findCangKuInfoByQyid(long qyid);
	
	 
	
	/**
	 * 获得导出数据
	 * @return
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);
	
//	/**
//     * 查询所有储罐最新数据(液位超标数据),插入到报警信息表
//     * @return
//     */
//	public void findLastOverData();
}
