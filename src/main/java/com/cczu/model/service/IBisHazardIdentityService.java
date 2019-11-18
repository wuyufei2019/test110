package com.cczu.model.service;

import com.cczu.model.entity.BIS_HazardIdentityEntity;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface IBisHazardIdentityService {

	/**
	 * 查条数
	 * @param mapData
	 * @return
	 */
	public String content(Map<String, Object> mapData);

	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData);
	
	/**
	 * 通过wxid来查数据
	 * @param wxid
	 * @return
	 */
	public BIS_HazardIdentityEntity findAll(Long wxid);
	
	/**
	 * 添加
	 * @param bis
	 */
	public void addInfo(BIS_HazardIdentityEntity bis);
	
	/**
	 * 通过id来查数据
	 * @param id
	 * @return
	 */
	public BIS_HazardIdentityEntity findById(Long id);
	
	/**
	 * @param bis
	 */
	public void updateInfo(BIS_HazardIdentityEntity bis);
	
	/**
	 * @param id
	 */
	public void deleteInfo(Long id);
	
	/**
	 * 通过qyid来查数据
	 * @param qyid
	 * @return list
	 */
	public List<BIS_HazardIdentityEntity> findListHdid(Long qyid);
	
	/**
	 * 查条数
	 * @return
	 */
	public int count(); 
	
	/**
	 * 算q值
	 * @return
	 */
	public List<Map<String, Object>> Qzhi();
	
	/**
	 * 分页
	 * @param mapData
	 * @return
	 */
	public Map<String,Object> datafy(Map<String, Object> mapData);
	
	/**
	 * 导出excel
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);

	
	/**
	 * 分页查询app
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGridApp(Map<String, Object> mapData);
}
