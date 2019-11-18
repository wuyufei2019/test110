package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.AQPX_CourseEntity;
import com.cczu.model.entity.AQPX_TestguizeEntity;

public interface IAqpxGzxxDao {
	 
	/**
	 * 通过企业id和课程id来查询试题规定
	 * @param qyid
	 * @param kcid
	 * @return
	 */
	public AQPX_TestguizeEntity findkc(Long qyid,Long kcid);
	
	/**
     * 查询试卷的出题要求，获取每种题目出多少道
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> dataGrid(Map<String, Object> mapData);
	
	/**
	 * 分页总数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData);

	/**
	 * 查询条件
	 * @param mapData
	 * @return
	 */
	public String content(Map<String, Object> mapData);
 
	/**
	 * 删除考试规则
	 * @param gz
	 */
	public void deleteInfo(long id);
	
	/**
	 * 查询没有制定考试规则的课程list
	 * @param qyid 企业id
	 * @return
	 */
	public List<AQPX_CourseEntity> findKCByNoGz(Long qyid);
}
