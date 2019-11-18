package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.BIS_DangerProcessEntity;
import com.cczu.model.entity.Tdic_BIS_DangerProcess;
import com.cczu.sys.system.entity.Dict;

public interface IBisGwgyDao {
	
	/**
	 * 通过企业编号查找所有高危工艺的信息
	 * @param qyid
	 * @return
	 */
	public List<BIS_DangerProcessEntity> findAll(Long qyid);
	
	/**
	 * 添加
	 * @param bis
	 */
	public void addInfo(BIS_DangerProcessEntity bis);
	
	/**
	 * 修改
	 * @param bis
	 */
	public void updateInfo(BIS_DangerProcessEntity bis);
	
	/**
	 * 通过id进行假删除
	 * @param id
	 */
	public void deleteInfo(Long id);
	
	
    /**
     * 分页查询
     * @param mapData
     * @return
     */
    public List<BIS_DangerProcessEntity> dataGrid(Map<String,Object> mapData);
	
	/**查询条数
	 * @param mapData
	 * @return
	 */
	
	public int getTotalCount(Map<String, Object> mapData);
	
	/**
	 * 
	 * 通过id查找高危工艺信息
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
	 * @param name
	 * @return
	 */
	public Tdic_BIS_DangerProcess findByGwgyName(String name);
	
	public List<Map<String,Object>> gwgy(Map<String,Object> mapData);

	/**
	 * 导出excel
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> getExport(Map<String, Object> mapData);

	/**
	 * 根据多个条件查询
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> findListByMap(Map<String, Object> map);

	/**
	 * 查字典
	 * @param value
	 * @return
	 */
	public Dict findvalue(String value);
	
    /**
     * 分页查询
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> ajdataGrid(Map<String,Object> mapData);
	
	/**查询条数
	 * @param mapData
	 * @return
	 */
	
	public int ajgetTotalCount(Map<String, Object> mapData);

	/**查询条件
	 * @param mapData
	 * @return
	 */
	public String ajcontent(Map<String, Object> mapData);
	
	/**
	 * 统计分析获取数据
	 */
	public List<Map<String, Object>> statistics(Map<String,Object> map);
}
