package com.cczu.model.dao;

import com.cczu.model.entity.BIS_HazardEntity;

import java.util.List;
import java.util.Map;

public interface IBisHazardDao {

	/**
	 * 通过id
	 * @param id
	 * @return
	 */
	public BIS_HazardEntity findById(Long id);
	
    /**通过企业id进行查询
     * @param qyid
     * @return
     */
	public BIS_HazardEntity findQyId(Long id);
	
	/**通过企业id进行查询(主要危险性名称)
	 * @param qyid
	 * @return
	 */
	public Map<String,Object> findMapQyId(Long qyid);
	
	/**添加
	 * @param bis
	 */
	public void addInfo(BIS_HazardEntity bis);
	
	/**修改
	 * @param bis
	 */
	public void updateInfo(BIS_HazardEntity bis);
	
	/**
	 * 删除
	 * @param id
	 */
	public void deleteInfo(Long id);
	
    /**
     * 分页查询
     * @param mapData
     * @return
     */
    public List<Map<String,Object>> dataGrid(Map<String, Object> mapData);
	
	/**
	 * 查询条数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData);
	
	public List<Map<String,Object>> getExport(Map<String, Object> mapData);

	/**
	 * 根据企业id获取结果集
	 * @param qyid
	 * @return
	 */
	public List<Map<String, Object>> findByQyID(Long qyid);
	/**
	 * 查询最新流水号
	 * @param qyid
	 * @return
	 */
	public String FindWaterCode(Long qyid);
	
	/**
	 * 重大危险源编码Json
	 * @param qyid
	 * @return
	 */
	public List<Map<String,Object>> findhazardCode(Long qyid);
}
