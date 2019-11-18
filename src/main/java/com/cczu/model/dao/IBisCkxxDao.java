package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.BIS_StorageEntity;

public interface IBisCkxxDao {
	/**
	 * 查询当前企业的所有仓库信息
	 * @param qyid
	 * @return
	 */
	public List<BIS_StorageEntity> findAll(long qyid);
	
	/**
	 * 分页
	 * @param mapData
	 * @param qyid
	 * @return
	 */
	public List<BIS_StorageEntity> dataGrid(Map<String,Object> mapData);
	
	/**
	 * 查询数据条数
	 * @param mapData
	 * @return
	 */
	
	public int getTotalCount(Map<String, Object> mapData);
	
	/**
	 * 
	 * 通过id查找仓库信息
	 * @param id
	 * @return
	 */
	public BIS_StorageEntity findById(Long id);
	
	/**
	 * 
	 * 通过id查找仓库信息2
	 * @param id
	 * @return
	 */
	public Map<String, Object> findById2(Long id);
	
	/**
	 * 添加
	 * @param bis
	 */
	public void addInfo(BIS_StorageEntity bis);
	
	/**
	 * 删除
	 * @param id
	 */
	public void deleteInfo(Long id);
	
	/**
	 * 改
	 * @param bis
	 */
	public void updateInfo(BIS_StorageEntity bis);

	
	/**
	 * 导出excel
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getExport(Map<String, Object> mapData);
	
	/**
	 * 安监局查看所有企业仓库信息
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid2(Map<String,Object> mapData);
	/**
	 * 行政区域-所有企业仓库信息
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGridAJ(Map<String, Object> map);
	/**
	 * 行政区域-所有企业仓库信息 count
	 * @param mapData
	 * @return
	 */
	public int getTotalCountAJ(Map<String, Object> map);
	/**
	 * 统计分析
	 * @param xzqy
	 * @return
	 */
	public Map<String, Object> statistics(Map<String, Object> map);

	/**
	 * 根据多个条件查询
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> findListByMap(Map<String, Object> map);

}
