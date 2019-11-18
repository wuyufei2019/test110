package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.BIS_OccupharmExamReportEntity;

public interface IBisOccupharmreportDao {
	
	/**
	 * 通过企业id获取改企业所有的职业病危害因素检测报告
	 * @param qyid
	 * @return
	 */
	public List<BIS_OccupharmExamReportEntity> findAll(Long qyid);
	
	/**
	 * 通过id查询职业病危害因素检测报告
	 * @param id
	 * @return
	 */
	public BIS_OccupharmExamReportEntity findById(Long id);
	
	/**
	 * 添加
	 * @param bis
	 */
	public void addInfo(BIS_OccupharmExamReportEntity bis);
	
	/**
	 * 修改
	 * @param bis
	 */
	public void updateInfo(BIS_OccupharmExamReportEntity bis);
	
	/**假 删除
	 * @param id
	 */
	public void deleteInfo(Long id);
	
    /**
     * 分页查询
     * @param mapData
     * @return
     */
    public List<BIS_OccupharmExamReportEntity> dataGrid(Map<String,Object> mapData);
	
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
	 * 行政区域 -所有企业检测报告信息
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGridAJ(Map<String, Object> mapData);
	/**
	 * 行政区域 -所有企业检测报告信息 count
	 * @param mapData
	 * @return
	 */
	public int getTotalCountAJ(Map<String, Object> mapData);
	/**
	 * 查询过期的检测报告
	 * @return
	 */
	public List<BIS_OccupharmExamReportEntity> findAll();
}
