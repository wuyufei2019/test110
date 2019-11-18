package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.BIS_SpecialequipmentEntity;
import com.cczu.sys.system.entity.Dict;

public interface IBisTzsbxxDao {
	
	/**
	 * 查询当前企业的所有特种设备的信息
	 * @param qyid
	 * @return
	 */
	public BIS_SpecialequipmentEntity findAll(long qyid);
	
	/**
	 * 
	 * 通过id查找特种设备信息
	 * @param id
	 * @return
	 */
	public Map<String, Object> findById(Long id);
	public BIS_SpecialequipmentEntity findById2(Long id);
	
	/**
	 * 添加
	 * @param bis
	 */
	public void addInfo(BIS_SpecialequipmentEntity bis);
	
	/**
	 * 删除
	 * @param id
	 */
	public void deleteInfo(Long id);
	
	/**
	 * 修改
	 * @param bis
	 */
	public void updateInfo(BIS_SpecialequipmentEntity bis);

	
	/**
	 * 分页查询
	 * @param mapData
	 * @param qyid
	 * @return
	 */
	public List<BIS_SpecialequipmentEntity> dataGrid(Map<String,Object> mapData);
	
	/**
	 * 查条数
	 * @param mapData
	 * @return
	 */
	
	public int getTotalCount(Map<String, Object> mapData);

	/**
	 * 条件查询
	 * @param mapData
	 * @return
	 */
	public String content(Map<String, Object> mapData);
	
	public List<Map<String,Object>> asd(Map<String,Object> mapData);
	
	/**
	 * 导出excel
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> getExport(Map<String, Object> mapData);

	/**
	 * 查字典里面的名称
	 * @param Value
	 * @return
	 */
	public Dict findvalue(String value);
	
	
	/**
	 * 查询数据
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> ajdataGrid(Map<String,Object> mapData);
	
	/**
	 * @param mapData
	 * @return
	 */
	
	public int ajgetTotalCount(Map<String, Object> mapData);
	/**
	 * 统计分析获取数据
	 */
	public List<Map<String, Object>> statistics(Map<String,Object> map);
	
}
