package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.BIS_OccupIllexamEntity;

public interface IBisOccupIllexamDao {
	
	/**
	 * 通过企业id来查询
	 * @param qyid
	 * @return
	 */
	public List<BIS_OccupIllexamEntity> findAll(Long qyid);
	
	/**
	 * 通过id来查询
	 * @param id
	 * @return
	 */
	public BIS_OccupIllexamEntity findById(Long id);
	
	/**
	 * 添加
	 * @param bis
	 */
	public void addInfo(BIS_OccupIllexamEntity bis);
	
	/**
	 * 修改
	 * @param bis
	 */
	public void updateInfo(BIS_OccupIllexamEntity bis);
	
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
    public List<BIS_OccupIllexamEntity> dataGrid(Map<String,Object> mapData);
	
	/**
	 * 查询条数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData);
	
	/**
	 * 导出excel
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> getExport(Map<String, Object> mapData);
	
	/**
	 * 查询数据
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> ajdataGrid(Map<String,Object> mapData);
	
	/**
	 * @param mapData
	 * @return
	 */
	
	public int ajgetTotalCount(Map<String, Object> mapData);

}
