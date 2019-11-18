package com.cczu.model.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cczu.model.entity.BIS_EnterpriseEntity;

public interface IBisQyjbxxDao {
	/**
     * 查询所有企业信息
     * @return
     */
	public List<BIS_EnterpriseEntity> findAlllist();
	/**
	 * 查询所有企业信息
	 * @return
	 */
	public List<BIS_EnterpriseEntity> findAlllist(Map<String, Object> map);
	/**
     * 根据安监局ID查询所关联的所有企业信息
     * @return
     */
	public List<BIS_EnterpriseEntity> findAllByUserId(long id);
	/**
     * 查询所有企业信息、关联厂区信息
     * @return
     */
	public List<Map<String, Object>> findAllEFlist();
	/**
     * 根据安监局ID查询所有企业信息、关联厂区信息
     * @return
     */
	public List<Map<String, Object>> findAllEFlistByUserId(long id);

	/**
     * 查询企业数据表格
     * @return
     */
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData);
	
	/**
	 * 查询企业数据表格
	 * @return
	 */
	public List<Map<String,Object>> dataGridForApp(Map<String, Object> mapData);
	/**
     * 查询企业数据表格_总记录数
     * @return
     */
	public int getTotalCount(Map<String, Object> mapData);
	/**
     * 根据id查找企业
     * @return
     */
	public BIS_EnterpriseEntity findInfoById(long id);
	/**
	 * 根据id查找企业及名称
	 * @return
	 */
	public Map<String,Object> findInfoNameById(long id);
	/**
     * 添加企业
     * @return
     */
	public void addInfo(BIS_EnterpriseEntity bis);
	/**
     * 添加企业返回ID
     * @return
     */
	public Long addInfore(BIS_EnterpriseEntity bis);
	/**
     * 修改企业
     * @return
     */
	public void updateInfo(BIS_EnterpriseEntity bis);
	/**
     * 删除企业
     * @return
     */
	public void deleteInfo(long id);
	/**
     * 根据userid查找企业
     * @return
     */
	public BIS_EnterpriseEntity findInfoByUserId(long userid);
	
	/**
     * 根据企业ID范围查询企业名称
     * @return
     */
	public List<Object> findQYNamelistIds(String ids);
	/**
     * 根据条件查询全部企业数据表格
     * @return
     */
	public List<BIS_EnterpriseEntity> dataList(Map<String, Object> map);
	/**
     * 根据条件查询全部企业数据表格
     * @return List<BIS_EnterpriseEntity>
     */
	public List<BIS_EnterpriseEntity> dataListE(Map<String, Object> map);
	/**
     * 根据条件查询全部企业数据表格
     * @return List<Object>
     */
	public List<Map<String, Object>> dataObjectList(Map<String, Object> map);
	
	/**
	 * 行政区域—企业地图显示
	 * @param mapdata
	 * @return
	 */
	public List<Map<String, Object>> findMapList(Map<String, Object> mapdata);
	/**
	 * 两重点一重大地图marker点显示 （风险分布图用）
	 * @param mapdata
	 * @return
	 */
	public List<Map<String, Object>> findZdzdMapList(Map<String, Object> mapdata);
	/**
	 * @param map
	 * @return id、text
	 */
	public List<Map<String, Object>> findQyIdTextList(Map<String, Object> map);
	/**
	 * @param map
	 * @return text、text
	 */
	public List<Map<String, Object>> findQyTextList(Map<String, Object> map);
	/**
	 * @param map
	 * @return text、text
	 */
	public List<Map<String, Object>> findQynmList(String xzqy);
	
	/**根据创建者id查找
	 * @param map
	 * @return text、text
	 */
	public List<Map<String, Object>> findQynmList2(String cjz);
	
	/**
	 * @param 行政区域  
	 * @return map
	 */
	public Map<String, Object> statistics(Map<String, Object> map);
	
	
	/**
	 * 统计所有企业的基本信息录入数量
	 * @return
	 */
	public List<Map<String, Object>> qyxxTotalAll(Map<String, Object> map);
	
	/**
	 * 导出所有企业的基本信息录入数量
	 * @return
	 */
	public List<Map<String, Object>> ExportQyxxTotalAll(Map<String, Object> map);
	/**
	 * 查询多个符合ids的企业的信息
	 * @return
	 */
	 public List<BIS_EnterpriseEntity> findAllQy(String ids);
	 
	 /**
	  * 根据行政区域查看该区域企业风险等级
	  * @param mapData
	  * @return
	  */
	public Map<String, Object> FxdjForApp(Map<String, Object> mapData);
	
	/**
	 * 根据行政区域查看该区域乡镇企业风险等级
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> XZFxdjForApp(Map<String, Object> mapData);
	
	/**
	 * app根据企业id查询
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> findByQyIDForApp(Map<String, Object> mapData);
	/**
	 * 第三方无数据模块确认 后添加无数据信息
	 * @param mapData
	 * @return
	 */
	public void addDataForModule(Set<String> tables,long id);
	
	/**
	 * 第三方无数据模块确认功能 （左侧企业列表）
	 * @return
	 */
	public List<Map<String, Object>> qyListForDsfNoDataModule(Map<String, Object> map);
	
	/**
	 * 地图模块---根据map点的经纬度查询企业相关信息
	 * @return
	 */
	public List<Map<String, Object>> findQyInfoByPoint(Map<String, Object> map);

	/**
	 * 查询企业一企一档完成度
	 * @param qyid
	 * @return
	 */
	public Map<String, Object> findwcd(Long qyid);
}
