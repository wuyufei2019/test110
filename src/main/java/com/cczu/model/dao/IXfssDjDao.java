package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.XFSS_RegisterEntity;

public interface IXfssDjDao {
	/**
	 * 添加消防设施登记信息
	 * @param xwaq
	 */
	public void addInfo(XFSS_RegisterEntity xwaq);
	
	/**
	 * 修改消防设施登记信息
	 * @param xwaq
	 */
	public void updateInfo(XFSS_RegisterEntity xwaq);
	
	/**
	 * 删除消防设施登记信息
	 * @param id
	 */
	public void deleteInfo(Long id);
	
	/**
	 * 查询数据
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> dataGrid(Map<String,Object> mapData);
	
	/**
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData);
	
	/**
	 * 
	 * 通过id查找消防设施登记信息
	 * @param id
	 * @return
	 */
	public XFSS_RegisterEntity findById(Long id);
	
	/**
	 * 
	 * 通过企业id查找消防设施登记信息
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> findAllInfo(Long qyid);
	
	/**
	 * 
	 * 通过企业id查找消防设施登记信息
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> findAllInfo(Long qyid, Long pmtId);
	
	/**
	 * 
	 * 通过企业id查找消防设施坐标
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> findAllZb(Long qyid);
	
	/**
	 * 获得导出数据
	 * @return
	 */
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData);
	

}
