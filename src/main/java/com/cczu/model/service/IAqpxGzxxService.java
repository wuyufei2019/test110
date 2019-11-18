package com.cczu.model.service;

import java.util.Map;
import com.cczu.model.entity.AQPX_TestguizeEntity;

public interface IAqpxGzxxService {
	
	/**
	 * 添加考试规则
	 * @param at
	 */
	public long addInfo(AQPX_TestguizeEntity at);
	
	/**
	 * 通过企业id和课程id查看该企业考试制定的题目规则
	 * @param qyid
	 * @param kcid
	 * @return
	 */
	public AQPX_TestguizeEntity findkc(Long qyid, Long kcid);

	/**
	 * 查询条件
	 * @param mapData
	 * @return
	 */
	public String content(Map<String, Object> mapData);

	/**
	 * 查询数据
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData);
	
	/**
	 * 通过id查询信息
	 * @param id
	 * @return
	 */
	public AQPX_TestguizeEntity findbyid(Long id);
	
	/**
	 * 修改考试规则
	 * @param gz
	 */
	public void updateInfo(AQPX_TestguizeEntity gz);
	
	/**
	 * 删除考试规则
	 * @param gz
	 */
	public void deleteInfo(long id);
	
	/**
	 * 查询没有制定考试规则的课程json
	 * @param qyid 企业id
	 * @return
	 */
	public String findKCByNoGz(Long qyid);

}
