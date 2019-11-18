package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.AQPX_TestpaperEntity;

public interface IAqpxKsjlService {
	
	/**
	 * 添加
	 * @param at
	 */
	public void addInfo(AQPX_TestpaperEntity at);
	
	/**
	 * 修改
	 * @param at
	 */
	public void updateInfo(AQPX_TestpaperEntity at);
	
	/**
	 * 查找获取考试记录
	 * @param ygid
	 * @param bs
	 * @return
	 */
	public List<AQPX_TestpaperEntity> getlist(Long ygid, String bs);

	/**
	 * 通过员工id查询所有的考试记录
	 * @param ygid
	 * @return
	 */
	public List<AQPX_TestpaperEntity> getall(Long ygid);
	
	/**
	 * 通过员工id与课程的id查询
	 * @param ygid
	 * @param gzid
	 * @return
	 */
	public List<AQPX_TestpaperEntity> getkcsj(Long ygid, Long kcid);
	
	
	/**
	 * 根据试卷标识查询试卷信息
	 * @param bs 试卷标识
	 * @return
	 */
	public List<Map<String, Object>> getsjxx(String bs);

    /**
     * 根据试卷标识查询试卷错题信息
     * @param bs 试卷标识
     * @return
     */
    List<Map<String, Object>> getctxx(String bs);

    /**
     * 根据试卷标识查询试卷错题题号
     * @param bs 试卷标识
     * @return
     */


}
