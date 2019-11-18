package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.AQPX_CoursewareEntity;

public interface IAqpxKjkxxDao {
	
	/**
	 * 通过企业id查询改企业的课件信息表
	 * @param qyid
	 * @return
	 */
	public List<AQPX_CoursewareEntity> findAllInfo(Long qyid);
	
	/**
	 * 添加课件信息
	 * @param ac
	 */
	public void addInfo(AQPX_CoursewareEntity ac);
	
	/**
	 * 修改课件信息
	 * @param ac
	 */
	public void updateInfo(AQPX_CoursewareEntity ac);
	
	/**
	 * 删除课件信息（假删除）
	 * @param id
	 */
	public void deleteInfo(Long id);
	
	/**
	 * 分页查询数据
	 * @param mapData
	 * @return
	 */
	public List<AQPX_CoursewareEntity> dataGrid(Map<String,Object> mapData);
	
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
	 * 通过课件id查询课件信息
	 * @param id 课件ID
	 * @return
	 */
	public AQPX_CoursewareEntity findByID(Long id);
	
	public List<AQPX_CoursewareEntity> getExcel(Map<String, Object> mapData);

	/**
	 * 查询所有的课件信息
	 * @return
	 */
	public List<AQPX_CoursewareEntity> getList();
	
	/**
	 * 根据课程id查课件
	 * @param kcid
	 * @return
	 */
	public List<AQPX_CoursewareEntity> getListKcid(Long kcid);
	

	/**
	 * 根据课程id删除课件信息 
	 * @param kcid 课程id
	 */
	public void deleteByKcid(Long kcid);
}
