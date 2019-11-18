package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.AQPX_PlancourseEntity;

public interface IAqpxJhkcDao {
	
	/**
	 * 添加
	 * @param ap
	 */
	public void addInfo(AQPX_PlancourseEntity ap);
	
	/**
	 * 修改
	 * @param ap
	 */
	public void updateInfo(AQPX_PlancourseEntity ap);
	
	/**
	 * 通过培训计划ID来删除培训信息
	 * @param pxid
	 */
	public void deleteInfo(Long pxid);
	
	/**
	 * 通过企业id来查询企业培训计划课程
	 * @param ID
	 * @return
	 */
	public AQPX_PlancourseEntity findAllInfo(Long ID);
	
	/**
	 * 查询出来的数据
	 * @param mapData
	 * @return
	 */
	public List<AQPX_PlancourseEntity> dataGrid(Map<String,Object> mapData);
	
	/**
	 * 总数
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
	 * 获得导出数据
	 * @return
	 */
	public List<AQPX_PlancourseEntity> getExcel(Map<String, Object> mapData);

	/**
	 * 查询所有信息
	 * @return
	 */
	public List<AQPX_PlancourseEntity> getList();
	
	/**
	 * 根据培训id查所有信息
	 * @param kcid
	 * @return
	 */
	public List<AQPX_PlancourseEntity> getlistpx(Long pxid);
	
	/**
	 * 根据员工id查员工的培训计划的信息
	 * @param ygid
	 * @return
	 */
	public List<AQPX_PlancourseEntity> getlistyg(Long ygid);
	
	/**
	 * 通过员工id来查询数据
	 * @param ygid
	 * @return
	 */
	public AQPX_PlancourseEntity findygid(Long ygid);
	
	public List<AQPX_PlancourseEntity> findAllss(Long ygid);

}
