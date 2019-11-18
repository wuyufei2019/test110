package com.cczu.model.dao;

import com.cczu.model.entity.BIS_HazardIdentityEntity;

import java.util.List;
import java.util.Map;

public interface IBisHazardIdentityDao {
	
	/**
	 * 通过危险源信息的id来进行查询
	 * @param wxid
	 * @return
	 */
	public BIS_HazardIdentityEntity findAll(Long wxid);
	
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	public BIS_HazardIdentityEntity findById(Long id);
	
	/**
	 * 添加
	 * @param bis
	 */
	public void addInfo(BIS_HazardIdentityEntity bis);
	
	/**
	 * 修改
	 * @param bis
	 */
	public void updateInfo(BIS_HazardIdentityEntity bis);
	
	/**
	 * 删除
	 * @param id
	 */
	public void deleteInfo(Long id);
	
    /**
     * 分页查询
     * @param mapData
     * @return
     */
    public List<BIS_HazardIdentityEntity> dataGrid(Map<String, Object> mapData);

	/**
	 * 查询条数
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
	 * 查次数
	 * @return
	 */
	public int count();

	/**
	 * 查询出数据计算q值的
	 * @return
	 */
	public List<Map<String,Object>> Qzhi();

	/**
	 * 分页查询的显示
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> datafy(Map<String, Object> mapData);

	/**
	 * 查询关联id1的所有对象
	 * @param id1
	 * @return
	 */
	public List<BIS_HazardIdentityEntity> findListHdid(Long id1);

	/**
	 * 导出excel
	 * @param mapData
	 * @return
	 */
	public List<BIS_HazardIdentityEntity> getExport(Map<String, Object> mapData);

	/**
     * 分页查询app
     * @param mapData
     * @return
     */
    public List<Map<String,Object>> dataGridApp(Map<String, Object> mapData);
}
