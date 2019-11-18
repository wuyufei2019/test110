package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.AQJD_BeiAnEntity;

public interface IAqjdXzbaDao {
	/**
     * 查询所有安全监督管理_企业行政备案信息
     * @return
     */
	public List<AQJD_BeiAnEntity> findAlllist();
	/**
     * 根据企业ID查询安全监督管理_企业行政备案信息
     * @return
     */
	public List<AQJD_BeiAnEntity> findAllByQyId(long id);

	/**
     * 查询安全监督管理_企业行政备案数据表格
     * @return
     */
	public List<AQJD_BeiAnEntity> dataGrid(Map<String, Object> mapData);
	/**
     * 查询安全监督管理_企业行政备案数据表格_总记录数
     * @return
     */
	public int getTotalCount(Map<String, Object> mapData);
	/**
     * 根据id查找安全监督管理_企业行政备案
     * @return
     */
	public AQJD_BeiAnEntity findInfoById(long id);
	/**
     * 添加安全监督管理_企业行政备案
     * @return
     */
	public void saveInfo(AQJD_BeiAnEntity aqjd);
	/**
     * 添加安全监督管理_企业行政备案返回ID
     * @return
     */
	public Long returnBySqlID(AQJD_BeiAnEntity aqjd);
	/**
     * 修改安全监督管理_企业行政备案
     * @return
     */
	public void updateInfo(AQJD_BeiAnEntity aqjd);
	/**
     * 删除安全监督管理_企业行政备案
     * @return
     */
	public void deleteInfo(long id);
	
	/**
     * 查询安全监督管理_企业行政许可数据表格(安监局显示界面)
     * @return
     */
	public List<Map<String,Object>> dataGrid2(Map<String, Object> mapData) ;
	
	/**
	 * 查询安全监督管理_企业行政许可数据表格(安监局显示界面)
	 * @return
	 */
	public List<Map<String,Object>> dataGridForApp(Map<String, Object> mapData);
	
	/**
	 * 根据企业查询安全监督管理_企业行政许可数据表格(安监局显示界面)
	 * @return
	 */
	public List<Map<String,Object>> findInfoByQyid(Long id) ;
	
	/**
     * 查询安全监督管理_企业行政备案数据表格_总记录数（非企业用户）
     * @return
     */
	public int getTotalCount2(Map<String, Object> mapData);
	
	public List<Map<String,Object>> getExport(Map<String, Object> mapData);
	
	/**
	 * 安监获取所有安全监督管理_企业行政备案信息
	 * @return
	 */
	public List<AQJD_BeiAnEntity> findAllaj();
	
	/**
	 * 企业获取安全监督管理_企业行政备案信息
	 * @return
	 */
	public List<AQJD_BeiAnEntity> findAllqy(Long qyid);
}
