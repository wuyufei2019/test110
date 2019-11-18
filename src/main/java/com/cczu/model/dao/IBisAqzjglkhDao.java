package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.BIS_DirectorAssessEntity;

public interface IBisAqzjglkhDao {
	/**
     * 查询所有
     * @return
     */
	public List<BIS_DirectorAssessEntity> findAlllist();
	/**
     * 根据安监局ID查询所
     * @return
     */
	public List<BIS_DirectorAssessEntity> findAllByUserId(long id);
	/**
     * 根据安监局ID查询
     * @return
     */
	public List<Map<String, Object>> findAllEFlistByUserId(long id);

	/**
     * 查询安全总监数据表格
     * @return
     */
	public List<Map<String, Object>> dataGridAJ(Map<String, Object> mapData);
	
	/**
	 * 查询安全总监数据表格
	 * @return
	 */
	public List<Map<String, Object>> dataGridAJForApp(Map<String, Object> mapData);
	
	/**
	 * 根据企业id查询安全总监数据表格
	 * @return
	 */
	public List<Map<String, Object>> findInfoByQyid(Long qyid);
	/**
     * 查询安全总监数据表格_总记录数
     * @return
     */
	public int getTotalCountAJ(Map<String, Object> mapData);
	
	/**
     * 查询安全总监数据表格
     * @return
     */
	public List<BIS_DirectorAssessEntity> dataGrid(Map<String, Object> mapData);
	/**
     * 查询安全总监数据表格_总记录数
     * @return
     */
	public int getTotalCount(Map<String, Object> mapData);
	/**
     * 根据id查找安全总监
     * @return
     */
	public BIS_DirectorAssessEntity findInfoById(long id);
	/**
     * 添加安全总监返回ID
     * @return
     */
	public Long addInfore(BIS_DirectorAssessEntity bis);
	/**
     * 修改安全总监
     * @return
     */
	public void updateInfo(BIS_DirectorAssessEntity bis);
	/**
     * 删除安全总监
     * @return
     */
	public void deleteInfo(long id);
	
	public List<Map<String, Object>> dirHaveAllQyList(Map<String, Object> map);
	public List<Map<String,Object>> getExport(Map<String, Object> mapData);
}
