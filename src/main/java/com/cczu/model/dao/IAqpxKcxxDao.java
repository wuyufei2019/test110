package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.AQPX_CourseEntity;

public interface IAqpxKcxxDao {
	
	/**
	 * 通过企业id查询改企业的课程信息表
	 * @param qyid
	 * @return
	 */
	public List<AQPX_CourseEntity> findAllInfoByQyID(Long qyid);
	
	/**
	 * 删除课程信息（假删除）
	 * @param id
	 */
	public void deleteInfo(Long id);
	
	/**
	 * 分页查询数据
	 * @param mapData
	 * @return
	 */
	public List<AQPX_CourseEntity> dataGrid(Map<String,Object> mapData);
	
	/**
	 * 分页查询数据
	 * @param mapData
	 * @return
	 */
	public List<AQPX_CourseEntity> findByName(Map<String,Object> mapData);
	
	/**
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData);

	/**
	 * @param mapData
	 * @return
	 */
	public String content(Map<String, Object> mapData);
	
	
	/**
	 * 获得导出数据
	 * @return
	 */
	public List<Object> getExcel(Map<String, Object> mapData);

	/**
	 * 查询所有的课程信息
	 * @return
	 */
	public List<AQPX_CourseEntity> getList();
	
	/**通过kcid查询所有的课程信息
	 * @param kcid
	 * @return
	 */
	public List<AQPX_CourseEntity> getkcid(Long kcid);
	
	
	/**
	 * 通过id查询课程信息
	 * @param id
	 * @return
	 */
	public AQPX_CourseEntity findbyid(Long id);
	
	/**
	 * 通过课程id查询
	 * @param ygid
	 * @param kcid
	 * @return
	 */
	public AQPX_CourseEntity findygidandkcid(Long kcid);
	
	/**
	 * 通过企业id和课程名称查询信息
	 * @param id1
	 * @param m1
	 * @return
	 */
	public AQPX_CourseEntity findid(Long id1,String m1);
	
	/**
	 * 根据课程id集合查询课程信息
	 * @param kcids
	 * @return
	 */
	public List<AQPX_CourseEntity> getlistByKcids(String kcids);
	
	/**
	 * 根据课程id集合查询课程信息
	 * @param kcids
	 * @return
	 */
	public List<Map<String, Object>> getlistByKcids2(String kcids,String planname,Long planid);

	/**
	 * 根据企业,部门id查询课程信息
	 * @param kcids
	 * @return
	 */
	public List<Map<String, Object>> getKclist(Long qyid, Long bmid);
	
	
	/**
	 * 根据条件查询课程信息
	 * @return
	 */
	public List<AQPX_CourseEntity> findByContent(Map<String, Object> mapData);
	
	/**
	 * app端根据条件查询课程信息
	 * @return
	 */
	public List<AQPX_CourseEntity> findByContentForApp(Map<String, Object> mapData);
	
	/**
	 * app根据课程id集合查询课程信息
	 * @return
	 */
	public List<Map<String, Object>> getlistByKcids2ForApp(String kcids,String planname,Long planid,String starttime,String endtime);

}
