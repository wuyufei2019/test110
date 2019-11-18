package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.BIS_WorkshopEntity;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;


public interface IBisCjxxService {
	
	
	public Page<BIS_WorkshopEntity> search(Page<BIS_WorkshopEntity> page,
			List<PropertyFilter> filters);
	
	
	/**
	 * 根据userID查企业获取其车间信息
	 * @param userid
	 * @return
	 */
	public List<BIS_WorkshopEntity> findAll(long userid);
	
	/**
	 * 添加车间信息
	 * @param bis
	 */
	public void addInfo(BIS_WorkshopEntity bis);
	
	/**
	 * 修改
	 * @param bis
	 */
	public void updateInfo(BIS_WorkshopEntity bis);
	
	/**
	 * 删除
	 * @param bis
	 */
	public void deleteInfo(long id);

	/**
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> map);
	
	/**
	 * @param map
	 * @return
	 */
	public Map<String, Object> ajdataGrid(Map<String, Object> map);
	
	/**
	 * 通过id查车间信息
	 * @param id
	 * @return
	 */
	public BIS_WorkshopEntity findById(Long id);
	
	/**
	 * 通过id查车间信息2
	 * @param id
	 * @return
	 */
	public BIS_WorkshopEntity findById2(Long id);
	
	/**
	 * @param M1
	 * @return
	 */
	public BIS_WorkshopEntity findByM(String M1);
	
	/**
	 * @param id1
	 * @return
	 */
	public String findByQyID(Long id1);
	
	/**
	 * 导出excel
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);
	
	/**
	 * 导入
	 */
	public Map<String,Object> exinExcel(Map<String, Object> map);
	/**
	 * 统计分析获取数据
	 */
	public Map<String, Object> statistics(Map<String, Object> map);
}
