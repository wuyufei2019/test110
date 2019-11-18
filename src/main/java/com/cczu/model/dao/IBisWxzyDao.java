package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.BIS_DangerOperationEntity;

public interface IBisWxzyDao {
	
	/**
	 * 通过企业编号查找所有危险作业的信息
	 * @param qyid
	 * @return
	 */
	public BIS_DangerOperationEntity findAll(Long qyid);
	
	/**
	 * 添加
	 * @param bis
	 */
	public void addInfo(BIS_DangerOperationEntity bis);
	
	/**
	 * 修改
	 * @param bis
	 */
	public void updateInfo(BIS_DangerOperationEntity bis);
	
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
    public List<BIS_DangerOperationEntity> dataGrid(Map<String,Object> mapData);
	
	/**
	 * 查询数据的条数
	 * @param mapData
	 * @return
	 */
	
	public int getTotalCount(Map<String, Object> mapData);

	/**查询条件
	 * @param mapData
	 * @return
	 */
	public String content(Map<String, Object> mapData);
	
	/**
	 * 
	 * 通过id查找危险作业信息
	 * @param id
	 * @return
	 */
	public BIS_DangerOperationEntity findById(Long id);
	
	
	/**
	 * 导出excel
	 * @param mapData
	 * @return
	 */
	public List<BIS_DangerOperationEntity> getExport(Map<String, Object> mapData);

}
