package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.BIS_TankEntity;

public interface IBisCgxxDao {
	
	/**
	 * 通过id查找企业所有的储罐信息
	 * @param id
	 * @return
	 */
	public List<BIS_TankEntity> findAll(Long qyid);
	
	
	/**
	 * 添加储罐信息
	 * @param bis
	 */
	public void addInfo(BIS_TankEntity bis);
	
	/**
	 * 修改储罐信息
	 * @param bis
	 */
	public void updateInfo(BIS_TankEntity bis);
	
	/**
	 * 删除储罐信息
	 * @param bis
	 */
	public void deleteInfo(Long id);
	
	/**
	 * 分页查询
	 * @param mapData
	 * @param 
	 * @return
	 */
	public List<BIS_TankEntity> dataGrid(Map<String,Object> mapData);
	
	/**
	 * 查数据条数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData);

	/**
	 * 查询条件
	 * @param mapData
	 * @return
	 */
	public String content(Map<String, Object> mapData);
	
	/**
	 * 
	 * 通过id查找储罐信息
	 * @param id
	 * @return
	 */
	public BIS_TankEntity findById(Long id);
	
	/**
	 * 
	 * 通过id查找储罐信息
	 * @param id
	 * @return
	 */
	public BIS_TankEntity findById2(Long id);
	
	/**
	 * 导出excel
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getExport(Map<String, Object> mapData);

	/**
	 * 行政区域-所有企业储罐信息  count
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGridAJ(Map<String, Object> map);

	/**
	 * 行政区域-所有企业储罐信息  count
	 * @param mapData
	 * @return
	 */
	public int getTotalCountAJ(Map<String, Object> map);

    /**
     * 数据维护-储罐信息分页查询
     * @param mapData
     * @return
     */
	public List<Map<String, Object>> dataGrid2(Map<String, Object> mapData);
	/**
	 * 首页地图-储罐信息map
	 * @param mapData
	 * @return 
	 */
	public List<Map<String, Object>> getCgMapJson(Map<String, Object> mapData);
	/**
	 * 统计分析获取数据
	 */
	public Map<String, Object> statistics(Map<String,Object> map);

	/**
	 * 根据多个条件查询
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> findListByMap(Map<String, Object> map);


}
