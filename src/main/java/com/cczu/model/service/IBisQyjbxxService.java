package com.cczu.model.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.BIS_EnterpriseEntity;
/**
 * 
 * @ClassName: IBisQyjbxxService
 * @Description: 企业基本信息接口
 * @author jason
 * @date 2017年6月8日
 *
 */
public interface IBisQyjbxxService {
	/**
     * 查询所有企业基本信息+厂区信息
     * @return
     */
	public List<Map<String,Object>> findAllQyAddressList() ;

	/**
     * 查询所有企业信息
     * @return
     */
	public List<BIS_EnterpriseEntity> findAll();
	
	/**
	 * 查询所有企业信息
	 * @return
	 */
	public Map<String, Object> findAllQyList(Map<String, Object> map);
	

	/**
	 * 查询所有企业信息
	 * @return
	 */
	public List<BIS_EnterpriseEntity> findAllQy(String ids);
	
	/**
     * 根据安监局ID查询所有企业信息
     * @return
     */
	public List<BIS_EnterpriseEntity> findAllByUserId(long userid);

	/**
     * 查询企业数据表格
     * @return
     */
	public Map<String, Object> dataGrid(Map<String, Object> mapData);
	
	/**
	 * 查询企业数据表格
	 * @return
	 */
	public Map<String, Object> dataGridForApp(Map<String, Object> mapData);
	
//	public List<BIS_EnterpriseEntity> getList(Map<String, Object> map);
	
	/**
     * 根据id查找企业
     * @return
     */
	public BIS_EnterpriseEntity findInfoById(long id);
	
	/**
     * 根据userid查找企业
     * @return
     */
	public BIS_EnterpriseEntity findInfoByUserId(long userid);
	
	/**
     * 添加企业
     * @return
     */
	public String addInfo(BIS_EnterpriseEntity bis);
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
//	/**
//	 * 导出Excel
//	 * @return
//	 */
//	public void exportExcel(HttpServletResponse response, Map<String, Object> map);

	public void exportExcel(HttpServletResponse response,
			Map<String, Object> map);

	public void exinExcel(Map<String,Object> map);
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
     * 根据条件查询全部企业数据表格
     * @return List<BIS_EnterpriseEntity>
     */
	public List<BIS_EnterpriseEntity> dataListE(Map<String, Object> map);

	/**
	 * @param map
	 * @return id、text
	 */
	public String getQyIdjson(Map<String, Object> map);
	/**
	 * @param map
	 * @return text、text
	 */
	public String getQyTextjson(Map<String, Object> map);
	/**
	 * @param map
	 * @return
	 */
	public String findXYById(Map<String, Object> map);
	/**
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> findQynmList(String xzqy);
	/**根据创建者id查找
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> findQynmList2(String cjz);
	/**
	 * @param 行政区域
	 * @return
	 */
	public Map<String, Object> statistics(Map<String, Object> map);
	
	/**
	 * 统计所有企业的基本信息数量
	 * @return
	 */
	public String qyxxTotalAll(Map<String, Object> map);
	/**
	 * 统计第三方所有企业的基本信息数量（用于确认信息完整性）
	 * @return
	 */
	public String DsfnoDataModule(Map<String, Object> map);
	
	/**
	 * 导出所有企业的基本信息数量
	 * @return
	 */
	public void ExportQyxxTotalAll(HttpServletResponse response,
			Map<String, Object> map);

	/**
	 * 根据行政区域查看该区域企业风险等级
	 * @param tmap
	 * @return
	 */
	public Map<String, Object> FxdjForApp(Map<String, Object> tmap);

	/**
	 * 根据行政区域查看该区域乡镇企业风险等级
	 * @param tmap
	 * @return
	 */
	public List<Map<String,Object>> XZFxdjForApp(Map<String, Object> tmap);

	/**
	 * 根据qyid获得一企一档信息  填充word
	 * @param qyid
	 * @return
	 */
	public Map<String, Object> getWordAll(Long qyid);
	
	/**
	 * 判断企业名称是否存在
	 * @param qyname
	 * @param qyid
	 * @return
	 */
	public boolean checkQyname(Map<String,Object> map);
	/**
	 * 第三方无数据模块确认 后添加无数据信息
	 * @param mapData
	 * @return
	 */
	public void addDataForModule(Set<String> tables,long id);
	
	/**
	 * 地图模块---根据map点的经纬度查询企业相关信息
	 * @return
	 */
	public Map<String, Object> findQyInfoByPoint(Map<String, Object> map);
	
	/**
	 * 根据qyid获得统计信息 
	 * @param qyid
	 * @return
	 */
	public Map<String, Object> getTotalViewById(Long qyid);

	/**
	 * 查询企业一企一档完成度
	 * @param qyid
	 * @return
	 */
	public Map<String, Object> findwcd(Long qyid);

	/**
	 * 查询企业一企一档各模块数量
	 * @param qyid
	 * @return
	 */
	public Map<String, Object> findsl(Long qyid);
}
