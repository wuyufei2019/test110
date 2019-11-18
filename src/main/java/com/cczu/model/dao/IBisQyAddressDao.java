package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.BIS_EnterpriseFactoryEntity;

public interface IBisQyAddressDao {
	/**
     * 查询所有企业厂区
     * @return
     */
	public List<BIS_EnterpriseFactoryEntity> findAlllist();

	/**
     * 查询企业厂区数据表格
     * @return
     */
	public List<BIS_EnterpriseFactoryEntity> dataGrid(Map<String, Object> mapData);
	/**
     * 查询企业厂区数据表格_总记录数
     * @return
     */
	public int getTotalCount(Map<String, Object> mapData);
	/**
     * 根据id查找企业厂区
     * @return
     */
	public BIS_EnterpriseFactoryEntity findInfoById(long id);
	/**
     * 添加企业厂区
     * @return
     */
	public void addInfo(BIS_EnterpriseFactoryEntity bis);
	/**
     * 添加企业厂区返回ID
     * @return
     */
	public Long returnBySqlID(BIS_EnterpriseFactoryEntity bis);
	/**
     * 修改企业厂区
     * @return
     */
	public void updateInfo(BIS_EnterpriseFactoryEntity bis);
	/**
     * 删除企业厂区
     * @return
     */
	public void deleteInfo(long id);
	/**
     * 根据企业id查找厂区
     * @return
     */
	public List<BIS_EnterpriseFactoryEntity> findInfoByQyId(long qyid);
}
