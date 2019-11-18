package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.BIS_WorkshopEntity;


public interface IBisCjxxDao {
	
	/**
	 * 查询当前企业的所有车间信息
	 * @param ID1
	 * @return
	 */
	public List<BIS_WorkshopEntity> findAll(long qyid);
	
	/**
	 * 删除车间信息
	 * @param bis
	 */
	public void deleteInfo(Long id);
	
	/**
	 * 查询数据
	 * @param mapData
	 * @param qyid
	 * @return
	 */
	public List<BIS_WorkshopEntity> dataGrid(Map<String,Object> mapData);
	
	/**
	 * @param mapData
	 * @return
	 */
	
	public int getTotalCount(Map<String, Object> mapData);

	/**
	 * 
	 * 通过id查找车间信息
	 * @param id
	 * @return
	 */
	public BIS_WorkshopEntity findById(Long id);
	
	/**
	 * 
	 * 通过id查找车间信息2
	 * @param id
	 * @return
	 */
	public BIS_WorkshopEntity findById2(Long id);
	
	/**
	 * 通过m1查询
	 * @param M1
	 * @return
	 */
	public BIS_WorkshopEntity findByM(String M1);
	
	/**
	 * 通过企业id进行查询
	 * @param id1
	 * @return
	 */
	public List<BIS_WorkshopEntity> findByQyID(Long id1);
	
	/**
	 * excel获取导出数据
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getExport(Map<String, Object> mapData);
	
	/**
	 * 查询数据
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> ajdataGrid(Map<String,Object> mapData);
	
	/**
	 * @param mapData
	 * @return
	 */
	
	public int ajgetTotalCount(Map<String, Object> mapData);
	/**
	 * 统计分析获取数据
	 */
	public Map<String, Object> statistics(Map<String, Object> map);

}
