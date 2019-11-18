package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.BIS_OccupharmExamEntity;

public interface IBisOccupharmDao {
	
	/**
	 * 通过企业id获取改企业所有的职业病危害因素
	 * @param qyid
	 * @return
	 */
	public List<BIS_OccupharmExamEntity> findAll(Long qyid);
	
	/**
	 * 通过id查询职业病危害因素
	 * @param id
	 * @return
	 */
	public BIS_OccupharmExamEntity findById(Long id);
	
	/**
	 * 
	 * 通过id查找职业病危害因素
	 * @param id
	 * @return
	 */
	public BIS_OccupharmExamEntity findById2(Long id);
	
	/**
	 * 添加
	 * @param bis
	 */
	public void addInfo(BIS_OccupharmExamEntity bis);
	
	/**
	 * 修改
	 * @param bis
	 */
	public void updateInfo(BIS_OccupharmExamEntity bis);
	
	/**假 删除
	 * @param id
	 */
	public void deleteInfo(Long id);
	
    /**
     * 分页查询
     * @param mapData
     * @return
     */
    public List<BIS_OccupharmExamEntity> dataGrid(Map<String,Object> mapData);
	
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
     * 分页查询
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> ajdataGrid(Map<String,Object> mapData);
	
	/**
	 * 查询条数
	 * @param mapData
	 * @return
	 */
	public int ajgetTotalCount(Map<String, Object> mapData);
	/**
	 * 统计分析
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> statistics(Map<String, Object> map);

	/**获取指定员工的职业病危害接触史
	 * @param id
	 */
	public String findOccuharmHis(Long id);
}
