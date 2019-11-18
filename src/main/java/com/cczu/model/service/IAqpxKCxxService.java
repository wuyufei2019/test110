package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.AQPX_CourseEntity;

public interface IAqpxKCxxService {
	
	
	/**
	 * 根据企业id查询课程信息
	 * @param qyid
	 * @return
	 */
	public List<AQPX_CourseEntity> findByQyID(Long qyid);
	
	/**
	 * 添加
	 * @param ac
	 */
	public void addInfo(AQPX_CourseEntity ac);
	
	/**
	 * 添加课程信息返回课程id
	 * @param ac
	 */
	public long addInforReturnID(AQPX_CourseEntity ac);
	
	/**
	 * 对上传的word，ppt文件转换并返回地址
	 * @param as
	 */
	public String uploadReturnName(String url,String filePath);
	
	/**
	 * 修改课程信息
	 * @param ac
	 */
	public void updateInfo(AQPX_CourseEntity ac);
	
	/**
	 * 删除课程信息（假删除）
	 * @param id
	 */
	public void deleteInfo(Long id);
	
	/**
	 * list数据
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData);
	

	/**
	 * 查询条件
	 * @param mapData
	 * @return
	 */
	public String content(Map<String, Object> mapData);
	
	
	/**
	 * 获得导出数据
	 * @return
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);

	
	public List<AQPX_CourseEntity> getkcid(Long kcid);
	
	/**
	 * 通过课程id查询课程信息
	 * @param id
	 * @return
	 */
	public AQPX_CourseEntity findbyid(Long id);
	
	/**
	 * 通过课程id和员工id查询
	 * @param ygid
	 * @param kcid
	 * @return
	 */
	public AQPX_CourseEntity findygidandkcid(Long kcid);
	
	/**
	 * 通过企业id和课程名称进行查询
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
	 * @param 
	 * @return
	 */
	public List<Map<String, Object>> getKclist(Long qyid, Long bmid);
	
	
	/**
	 * 根据条件查询课程名称json
	 * @return
	 */
	public String findCKByContent(Map<String, Object> mapData);
	
	/**
	 * 根据条件查询课程
	 * @return
	 */
	public List<AQPX_CourseEntity> getList(Map<String, Object> mapData);
	
}
