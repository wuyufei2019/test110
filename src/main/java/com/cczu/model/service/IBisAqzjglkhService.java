package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.BIS_DirectorAssessEntity;
/**
 * 
 * @ClassName: IBisAqzjglkhService
 * @Description: 企业基本信息-安全总监管理年度考核
 * @author jason
 *
 */
public interface IBisAqzjglkhService {
	/**
     * 查询所有
     * @return
     */
	public List<BIS_DirectorAssessEntity> findAll();
	
	/**
     * 安监局查询所有安全总监信息
     * @return
     */
	public List<BIS_DirectorAssessEntity> findAllByUserId(long userid);

	/**
     * dataGrid
     * @return
     */
	public Map<String, Object> dataGrid(Map<String, Object> mapData);
	/**
     * dataGrid
     * @return
     */
	public Map<String, Object> dataGridAJ(Map<String, Object> mapData);	
	/**
     * 根据id查找
     * @return
     */
	public BIS_DirectorAssessEntity findInfoById(long id);
	
	/**
     * 添加
     * @return
     */
	public Long addInfo(BIS_DirectorAssessEntity bis);
	/**
     * 修改
     * @return
     */
	public void updateInfo(BIS_DirectorAssessEntity bis);
	/**
     * 修改
     * @return
     */
	public void spupdateInfo(BIS_DirectorAssessEntity bis);
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
