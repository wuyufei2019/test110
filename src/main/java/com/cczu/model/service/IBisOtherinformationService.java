package com.cczu.model.service;

import com.cczu.model.entity.BIS_OtherinformationEntity;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface IBisOtherinformationService {
	/**
	 * 通过id查储罐信息
	 * @param id
	 * @return
	 */
	public BIS_OtherinformationEntity findById(Long id);

	/**
	 * 通过id查储罐信息
	 * @param id
	 * @return
	 */
	public BIS_OtherinformationEntity findById2(Long id);

	/**
	 * 根据qyID查企业获取其储罐信息
	 * @param userid
	 * @return
	 */
	public List<BIS_OtherinformationEntity> findAll(long qyid);

	/**
	 * 添加信息
	 * @param bis
	 */
	public void addInfo(BIS_OtherinformationEntity bis);

	/**
	 * 修改
	 * @param bis
	 */
	public void updateInfo(BIS_OtherinformationEntity bis);

	/**
	 * 删除
	 * @param bis
	 */
	public void deleteInfo(long id);

	public Map<String, Object> dataGrid(Map<String, Object> mapData);

	public Map<String, Object> dataGrid2(Map<String, Object> mapData);
	/**
	 * 导出excel
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);

	/**
	 * 行政区域-所有企业储罐信息
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGridAJ(Map<String, Object> map);

	/**
	 * 导入
	 */
	public Map<String,Object> exinExcel(Map<String, Object> map);

	/**
	 * 企业修改
	 * @param bt
	 */
	public void updateInfo2(BIS_OtherinformationEntity bt);

	/**
	 * admin修改
	 * @param bt
	 */
	public void updateInfo3(BIS_OtherinformationEntity bt);
	/**
	 * 首页地图-储罐信息map
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getCgMapJson(Map<String, Object> mapData);
		/**
	 * 统计分析获取数据
	 */
	public Map<String, Object> statistics(Map<String, Object> map);
	
}
