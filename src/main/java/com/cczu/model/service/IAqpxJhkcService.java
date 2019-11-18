package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.AQPX_PlancourseEntity;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;

public interface IAqpxJhkcService {
	
	public Page<AQPX_PlancourseEntity> search(Page<AQPX_PlancourseEntity> page,
			List<PropertyFilter> filters);
	
	
	
	/**
	 * 添加
	 * @param ac
	 */
	public void addInfo(AQPX_PlancourseEntity ap);
	
	/**
	 * 修改
	 * @param ap
	 */
	public void updateInfo(AQPX_PlancourseEntity ap);
	
	/**
	 * 通过培训id来进行假删除
	 * @param pxid
	 */
	public void deleteInfo(Long pxid);
	
	/**
	 * 根据企业id查询信息
	 * @param qyid
	 * @return
	 */
	public AQPX_PlancourseEntity findAllInfo(Long qyid);
	
	/**
	 * 查询出来的数据
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
	 * 根据员工id查询某条数据
	 * @param ygid
	 * @return
	 */
	public AQPX_PlancourseEntity findygid(Long ygid);
	
	/**
	 * 查询员工所有的课程信息
	 * @param ygid
	 * @return
	 */
	public List<AQPX_PlancourseEntity> find(Long ygid);
	
	
}
