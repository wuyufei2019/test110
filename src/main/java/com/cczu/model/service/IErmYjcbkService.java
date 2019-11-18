package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.ERM_EmergencyRepositoryEntity;

public interface IErmYjcbkService {
	
	/**
	 * 根据userID查应急储备库信息
	 * @param userid
	 * @return
	 */
	public List<ERM_EmergencyRepositoryEntity> findAll();
	
	/**
	 * 添加应急储备库信息
	 * @param erm
	 */
	public void addInfo(ERM_EmergencyRepositoryEntity erm);
	
	/**
	 * 修改
	 * @param erm
	 */
	public void updateInfo(ERM_EmergencyRepositoryEntity erm);
	
	/**
	 * 删除
	 * @param erm
	 */
	public void deleteInfo(long id);
	
	public String content(Map<String, Object> mapData);


	public Map<String, Object> dataGrid(Map<String, Object> map);
	
	/**
	 * 通过id查应急储备库信息
	 * @param id
	 * @return
	 */
	public ERM_EmergencyRepositoryEntity findById(Long id);

	/**
	 * 导出Excel
	 * @return
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);

	/**
	 * 导入
	 */
	public Map<String,Object> exinExcel(Map<String, Object> map);
	/**
	 * 在地图中显示
	 * @return
	 */
	public List<Map<String, Object>> findMapList(Map<String, Object> mapdata);
}
