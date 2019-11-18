package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.BIS_DirectorEntity;
/**
 * 
 * @ClassName: IBisAqzjglService
 * @Description: 企业基本信息-安全总监管理接口
 * @author jason
 *
 */
public interface IBisAqzjglService {
	/**
     * 查询所有
     * @return
     */
	public List<BIS_DirectorEntity> findAll();
	
	/**
     * 安监局查询所有安全总监信息
     * @return
     */
	public List<BIS_DirectorEntity> findAllByUserId(long userid);

	/**
     * 查询安全总监数据表格
     * @return
     */
	public Map<String, Object> dataGrid(Map<String, Object> mapData);

	/**
     * 查询安全总监数据表格
     * @return
     */
	public Map<String, Object> dataGridAJ(Map<String, Object> mapData);
	
	/**
     * 根据id查找安全总监
     * @return
     */
	public BIS_DirectorEntity findInfoById(long id);
	
	/**
     * 添加
     * @return
     */
	public Long addInfo(BIS_DirectorEntity bis);
	/**
     * 修改
     * @return
     */
	public void updateInfo(BIS_DirectorEntity bis);
	/**
     * 审批修改
     * @return
     */
	public void spupdateInfo(BIS_DirectorEntity bis);
	/**
     * 删除
     * @return
     */
	public void deleteInfo(long id);

	/**
	 * 导出
	 * @param response
	 * @param map
	 */
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> map);

	public String getQyJson(Map<String, Object> map);
}
