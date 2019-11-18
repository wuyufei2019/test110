package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.AQZF_SafetyCheckPlanEntity;

/**
 * 检查计划记录dao
 * @author zpc
 * @date 2017/07/27
 */
public interface IAqzfJcjhDao {

	/**
	 * 查询检查计划list
	 * @param mapData
	 * @return
	 */
	public List<Map<String , Object>> dataGrid(Map<String, Object> mapData);
	
	/**
     * 统计检查计划记录数
     * @return
     */
	public int getTotalCount(Map<String, Object> mapData);
	
    /**
     * 添加
     * @param cfjl
     */
    public void addInfo(AQZF_SafetyCheckPlanEntity jcjh);

    /**
     * 删除
     * @param id
     */
	public void deleteInfo(long id);
	
    /**
     * 导出
     * @param mapData
     * @return
     */
	public List<Map<String, Object>> getExport(Map<String, Object> mapData);

	/**
	 * 执行添加操作获得返回的id
	 * @return
	 */
	public long addjcjh(AQZF_SafetyCheckPlanEntity jcjh);

	/**
	 * 根据id查看
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> findInfoById(long id);
	
	/**
	 * 根据所属行业id查找对应的字段
	 * @param id
	 * @return
	 */
	public Map<String, Object> findHy(String id);

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public AQZF_SafetyCheckPlanEntity findById(long id);

	/**
	 * 根据计划id1删除方案表中数据
	 * @param id1
	 * @param id2
	 */
	public void deletefa(long id1);

	/**
	 * 根据检查方案id删除检查记录
	 * @param id1
	 * @param id2
	 */
	public void deletejl(String faids);
	
	/**
	 * 根据id1
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> findJcfaById(long id);
	
	/**
	 * 根据计划id查找方案条数
	 * @param id1
	 * @return
	 */
	public int getfasl(long id1);
}
