package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.AQJD_BeiAnEntity;
/**
 * 
 * @ClassName: IaqjdQyjbxxService
 * @Description: 安全监督管理_企业行政备案接口
 * @author jason
 * @date 2017年6月8日
 *
 */
public interface IAqjdXzbaService {
	/**
     * 查询所有企业行政备案信息
     * @return
     */
	public List<AQJD_BeiAnEntity> findAll();
	
	/**
     * 根据安监局ID查询所有企业行政备案信息
     * @return
     */
	public List<AQJD_BeiAnEntity> findAllByUserId(long userid);

	/**
     * 查询企业行政备案信息数据表格
     * @return
     */
	public Map<String, Object> dataGrid(Map<String, Object> mapData);
	
	/**
     * 根据id查找企业行政备案信息
     * @return
     */
	public AQJD_BeiAnEntity findInfoById(long id);
	
	/**
     * 根据企业id查找企业行政备案信息
     * @return
     */
	public List<AQJD_BeiAnEntity> findListInfoByQyId(long id1);
	
	/**
     * 添加企业行政备案信息
     * @return
     */
	public void addInfo(AQJD_BeiAnEntity aqjd);
	/**
     * 添加企业行政备案信息返回ID
     * @return
     */
	public Long addInforeturnID(AQJD_BeiAnEntity aqjd);
	/**
     * 修改企业行政备案信息
     * @return
     */
	public void updateInfo(AQJD_BeiAnEntity aqjd);
	/**
     * 删除企业行政备案信息
     * @return
     */
	public void deleteInfo(long id);
	/**
     * 查询安全监督管理_企业行政备案数据表格(安监局显示界面)
     * @return
     */
	public Map<String, Object> dataGrid2(Map<String, Object> mapData);
	
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);
	
	/**
     * 安监获取所有企业行政备案信息
     * @return
     */
	public List<AQJD_BeiAnEntity> findAllaj();
	
	/**
	 * 企业获取企业行政备案信息
	 * @return
	 */
	public List<AQJD_BeiAnEntity> findAllqy(Long qyid);
}
