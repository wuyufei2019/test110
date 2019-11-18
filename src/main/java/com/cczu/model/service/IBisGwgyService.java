package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.BIS_DangerProcessEntity;
import com.cczu.model.entity.Tdic_BIS_DangerProcess;

public interface IBisGwgyService {
	/**
	 * 通过id查信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> findById(Long id);
	/**
	 * 通过m0查找高危工艺数据信息
	 */
	public Tdic_BIS_DangerProcess findByM0(String M0);

	/**
	 * 通过高危工艺名称查找高危工艺数据信息
	 */
	public Tdic_BIS_DangerProcess findByGwgyName(String name);

	/**
	 * 根据qyID查企业获取其高危工艺信息
	 * @param userid
	 * @return
	 */
	public List<BIS_DangerProcessEntity> findAll(long qyid);
	
	/**
	 * 添加信息
	 * @param bis
	 */
	public void addInfo(BIS_DangerProcessEntity bis);
	
	/**
	 * 修改
	 * @param bis
	 */
	public void updateInfo(BIS_DangerProcessEntity bis);
	
	/**
	 * 删除
	 * @param bis
	 */
	public void deleteInfo(long id);

	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData);
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> gwgy(Map<String, Object> mapData);
	

	/**
	 * 导出excel
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> ajdataGrid(Map<String, Object> mapData);
	public Map<String,Object> exinExcel(Map<String, Object> map);
	/**
	 * 统计分析获取数据
	 */
	public List<Map<String, Object>> statistics(Map<String,Object> map);

	/**
	 * 获取高危工艺信息json字符串
	 * @param mapData
	 * @return
	 */
	public String getGwgyJson(Map<String, Object> mapData);
}
