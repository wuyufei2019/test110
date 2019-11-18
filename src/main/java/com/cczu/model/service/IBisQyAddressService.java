package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.BIS_EnterpriseFactoryEntity;
/**
 * 
 * @ClassName: IBisQyAddressService
 * @Description: 企业基本信息厂区接口
 * @author jason
 * @date 2017年6月8日
 *
 */
public interface IBisQyAddressService {
	/**
     * 查询企业厂区数据表格
     * @return
     */
	public Map<String, Object> dataGrid(Map<String, Object> mapData);
	
	/**
     * 根据id查找企业厂区
     * @return
     */
	public BIS_EnterpriseFactoryEntity findInfoById(long id);
	
	/**
     * 根据qyid查找企业厂区
     * @return
     */
	public List<BIS_EnterpriseFactoryEntity> findInfoByQyId(long qyid);
	
	/**
     * 添加企业厂区
     * @return
     */
	public void addInfo(BIS_EnterpriseFactoryEntity bis);
	/**
     * 添加企业厂区返回ID
     * @return
     */
	public Long addInforeturnID(BIS_EnterpriseFactoryEntity bis);
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
}
