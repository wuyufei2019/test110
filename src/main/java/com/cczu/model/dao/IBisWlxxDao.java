package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.BIS_MatEntity;
import com.cczu.sys.system.entity.Dict;

public interface IBisWlxxDao {
	/**
	 * 通过id查找企业所有的物料信息
	 * @param id
	 * @return
	 */
	public List<BIS_MatEntity> findAllWL(Long qyid);
	/**
	 * 通过id查找企业所有的物料信息
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> findwlByqyid(long qyid);
	
	
	/**
	 * 通过企业id查找所有的物料和产品信息
	 * @param id
	 * @return
	 */
	public List<BIS_MatEntity> findAll(Long qyid);
	
	
	/**
	 * 添加信息
	 * @param bis
	 */
	public void addInfo(BIS_MatEntity bis);
	
	/**
	 * 修改信息
	 * @param bis
	 */
	public void updateInfo(BIS_MatEntity bis);
	
	/**
	 * 删除物料信息
	 * @param bis
	 */
	public void deleteInfo(Long id);
	
	/**
	 * 分页查询数据
	 * @param mapData
	 * @param 
	 * @return
	 */
	public List<BIS_MatEntity> dataGrid(Map<String,Object> mapData);
	
	/**
	 * 分页查询数据(重点监管危化品)
	 * @param mapData
	 * @param 
	 * @return
	 */
	public List<Map<String, Object>> dataGrid2(Map<String,Object> mapData);
	
	/**查询条数
	 * @param mapData
	 * @return
	 */
	
	public int getTotalCount(Map<String, Object> mapData);

	/**
	 * 
	 * 通过id查找物料信息
	 * @param id
	 * @return
	 */
	public BIS_MatEntity findById(Long id);
	
	/**
	 * 
	 * 通过id查找物料信息2
	 * @param id
	 * @return
	 */
	public Map<String, Object> findById2(Long id);
	
	/**
	 * 
	 * 物料名称验证
	 * @param id
	 * @return
	 */
	public String wlnmck(Map<String, Object> mapData);
	
	
	/**
	 * 导出excel
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getExport(Map<String, Object> mapData);
	
	/**
	 * 查字典值
	 * @param Value
	 * @return
	 */
	public Dict findval(String Value);

	/**
	 * String str查询
	 * @param str
	 * @return
	 */
	public List<BIS_MatEntity> findByNameList(String str,Long id);

	/**
	 * 通过id查询转成Object
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> findObById(Long id);
	/**
	 * 行政区域-物料信息list
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> dataGridAJ(Map<String, Object> map);
	/**
	 * 行政区域-物料信息list 统计数量
	 * @param map
	 * @return
	 */
	public int getTotalCountAJ(Map<String, Object> map);
	
	/**
	 * 行政区域-重点监管危化品信息list 统计数量
	 * @param map
	 * @return
	 */
	public int getTotalCount2(Map<String, Object> map);

}
