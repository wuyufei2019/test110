package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.BIS_MatEntity;

public interface IBisWlxxService {
	
	/**
	 * 通过id查物料信息
	 * @param id
	 * @return
	 */
	public BIS_MatEntity findById(Long id);
	
	/**
	 * 通过id查物料信息2
	 * @param id
	 * @return
	 */
	public Map<String, Object> findById2(Long id);
	
	/**
	 * 根据qyID查企业获取其物料信息
	 * @param userid
	 * @return
	 */
	public List<BIS_MatEntity> findAllWL(long qyid);
	
	/**
	 * 通过企业id查找所有的物料和产品信息
	 * @param id
	 * @return
	 */
	public List<BIS_MatEntity> findAll(long qyid);
	
	/**
	 * 添加信息
	 * @param bis
	 */
	public void addInfo(BIS_MatEntity bis);
	
	/**
	 * 修改
	 * @param bis
	 */
	public void updateInfo(BIS_MatEntity bis);
	
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
	 * 重点监管分页查询
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid2(Map<String, Object> mapData);
	
	/**
	 * 
	 * @param mapData
	 * @return
	 */
	public String wlnmck(Map<String, Object> mapData);
	
	/**
	 * 导出excel
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);
	
	/**
	 * 查询物料信息
	 * @return
	 */
	public String findAllwlList();
	
	/**
	 * 查询物料信息
	 * @return
	 */
	public String findWlforck(String qyid);

	/**
	 * 根据名称查询
	 * @param name
	 * @return
	 */
	public List<BIS_MatEntity> findByName(String name);
	
	/**
	 * 通过id查物料信息
	 * Object的
	 * @param id
	 * @return
	 */
	public Map<String, Object> findObById(Long id);

	/**
	 * 安监端-物料信息list
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGridAJ(Map<String, Object> map);
	
	/**
	 * 导入
	 */
	public Map<String,Object> exinExcel(Map<String, Object> map);

	/**
	 * 安监局仓库模块根据企业id获取物料
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> findwlByqyid(long qyid);

}
