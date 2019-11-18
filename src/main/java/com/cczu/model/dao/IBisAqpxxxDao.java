package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.BIS_SafetyEducationEntity;
import com.cczu.sys.system.entity.Dict;

/**
 * @author jason
 *
 */
public interface IBisAqpxxxDao {
	
	/**
	 * 通过企业编号查找所有安全培训的信息
	 * @param qyid
	 * @return
	 */
	public List<BIS_SafetyEducationEntity> findAll(Long qyid);
	
	/**
	 * 添加
	 * @param bis
	 */
	public void addInfo(BIS_SafetyEducationEntity bis);
	
	/**
	 * 修改
	 * @param bis
	 */
	public void updateInfo(BIS_SafetyEducationEntity bis);
	
	/**
	 * 通过id进行假删除
	 * @param id
	 */
	public void deleteInfo(Long id);
	
	
    /**分页查询
     * @param mapData
     * @return
     */
    public List<Map<String,Object>> dataGrid(Map<String,Object> mapData);
	
	/**查数据条数
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
	 * 通过id查找安全培训信息
	 * @param id
	 * @return
	 */
	public BIS_SafetyEducationEntity findById(Long id);
	public BIS_SafetyEducationEntity findById2(Long id);
	
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
	 * 安监局获取所有企业的安全培训的信息
	 * @param qyid
	 * @return
	 */
	public List<BIS_SafetyEducationEntity> findAllaj();

}
