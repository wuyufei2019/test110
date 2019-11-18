package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.AQPX_TestpaperEntity;

/**
 * 	企业-安全培训-员工考试记录DAO
 * @author jason
 *
 */
public interface IAqpxKsjlDao {

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
	 * 查看
	 * @param ygid
	 * @return
	 */
	public List<AQPX_TestpaperEntity> getlist(Long ygid,String bs);
	
	/**
	 * 查询员工所有的考试记录
	 * @param ygid
	 * @return
	 */
	public List<AQPX_TestpaperEntity> getall(Long ygid);
	
	/**
	 * 查询员工某一课程的所有考试记录
	 * @param ygid
	 * @param kcid
	 * @return
	 */
	public List<AQPX_TestpaperEntity> getkcsj(Long ygid,Long kcid);
	
	
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
    public List<Map<String, Object>> getctxx(String bs);


	

}
