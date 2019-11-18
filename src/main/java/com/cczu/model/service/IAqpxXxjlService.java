package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.AQPX_StudyhistoryEntity;

public interface IAqpxXxjlService {
	
	/**
	 * 用于计算学习时长
	 * @param as
	 */
	public void xueshi(AQPX_StudyhistoryEntity as);
	
	/**
	 * 添加
	 * @param as
	 */
	public void addInfo(AQPX_StudyhistoryEntity as);
	
	
	/**
	 * 通过id删除
	 * @param id
	 */
	public void deleteInfo(Long id);
	
	/**
	 * 通过企业id进行查看
	 * @param qyid
	 * @return
	 */
	public AQPX_StudyhistoryEntity findAll(Long qyid);
	
	
	/**
	 * 学员学习某个课程的记录的基本信息
	 *
	 * @param xyid
	 * @param kcid
	 * @return
	 */
	public List<AQPX_StudyhistoryEntity> getlist(Long xyid,Long kcid);
	
	/**
	 * 添加
	 * @param as
	 */
	public void addshic(AQPX_StudyhistoryEntity as);
	
	/**
	 * 修改时长
	 * @param as
	 */
	public void updateshic(AQPX_StudyhistoryEntity as);
	
	/**
	 * 查看时长
	 * @param xyid
	 * @param kcid
	 * @return
	 */
	public AQPX_StudyhistoryEntity findsc(Long xyid, Long kcid);
	
	/**
	 * 分页查询数据(企业普通员工查看)
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String,Object> mapData);
	
	/**
	 * 分页查询数据(企业管理员查看)
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGridCompanyAdmin(Map<String,Object> mapData);
	
	/**
	 * 考试记录数据导出
	 * @param mapData
	 * @return
	 */
	public void exportExcel(HttpServletResponse response,Map<String, Object> mapData);
}
