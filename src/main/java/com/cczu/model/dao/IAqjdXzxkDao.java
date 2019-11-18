package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.AQJD_AdministrativeEntity;
/**
 * 
 * @author jason
 * 安全监督管理_企业行政许可DAO
 */
public interface IAqjdXzxkDao {
	
	/**
     * 根据企业ID查询安全监督管理_企业行政许可信息
     * @return
     */
	public List<AQJD_AdministrativeEntity> findAllByQyId(long id);

	/**
     * 查询安全监督管理_企业行政许可数据表格
     * @return
     */
	public List<AQJD_AdministrativeEntity> dataGrid(Map<String, Object> mapData);
	
	/**
     * 查询安全监督管理_企业行政许可数据表格_总记录数(企业用户)
     * @return
     */
	public int getTotalCount(Map<String, Object> mapData);
	
	/**
     * 查询安全监督管理_企业行政许可数据表格_总记录数(非企业用户)
     * @return
     */
	public int getTotalCount2(Map<String, Object> mapData);
	
	/**
     * 根据id查找安全监督管理_企业行政许可
     * @return
     */
	public AQJD_AdministrativeEntity findInfoById(long id);
	
	/**
     * 添加安全监督管理_企业行政许可
     * @return
     */
	public void saveInfo(AQJD_AdministrativeEntity aqjd);
	
	/**
     * 添加安全监督管理_企业行政许可返回ID
     * @return
     */
	public Long returnBySqlID(AQJD_AdministrativeEntity aqjd);
	
	/**
     * 修改安全监督管理_企业行政许可
     * @return
     */
	public void updateInfo(AQJD_AdministrativeEntity aqjd);
	
	/**
     * 删除安全监督管理_企业行政许可
     * @return
     */
	public void deleteInfo(long id);
	
	
	/**
     * 查询安全监督管理_企业行政许可数据表格(安监局显示界面)
     * @return
     */
	public List<Map<String, Object>> dataGrid2(Map<String, Object> mapData);
	
	
	/**
	 * 查询安全监督管理_企业行政许可数据表格(安监局显示界面)
	 * @return
	 */
	public List<Map<String, Object>> dataGridForApp(Map<String, Object> mapData);
	
	
	/**
	 * 根据企业查询安全监督管理_企业行政许可数据表格(安监局显示界面)
	 * @return
	 */
	public List<Map<String, Object>> findInfoByQyid(Long id);
	
	public List<Map<String,Object>> getExport(Map<String, Object> mapData);
	
	
	/**
	 * 安监界面上查询数据的
	 * @return
	 */
	public List<AQJD_AdministrativeEntity> findAllaj();
}
