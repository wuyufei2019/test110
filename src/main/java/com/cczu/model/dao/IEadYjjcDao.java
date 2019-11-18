package com.cczu.model.dao;

import java.util.Map;

import com.cczu.model.entity.EAD_AccidentEntity;
/**
 * 
 * @ClassName: IEadYjjcDao
 * @Description: 应急辅助决策_事故后果计算
 * @author jason
 *
 */
public interface IEadYjjcDao {
	
	/**
     * 保存并返回id
     * @return
	 * @throws Exception 
     */
	public long saveAccidentRid(EAD_AccidentEntity ead);

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public EAD_AccidentEntity findById(Long id);
	
	/**
	 * 查询避难场所坐标
	 * @param map
	 * @return
	 */
	public Map<String, Object> findMapResPlace(Map<String, Object> map);
	/**
	 * 查询避难场所集合
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGridResPlace(Map<String, Object> map);
	/**
	 * 查询避难场所总数
	 * @param map
	 * @return
	 */
	public int getTotaldataGridResPlace(Map<String, Object> map);

	/**
	 * 查询应急队伍坐标
	 * @param map
	 * @return
	 */
	public Map<String, Object> findMapResTeam(Map<String, Object> map);

	/**
	 * 查询应急队伍集合
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGridResTeam(Map<String, Object> map);

	/**
	 * 查询应急队伍总数
	 * @param map
	 * @return
	 */
	public int getTotaldataGridResTeam(Map<String, Object> map);

	/**
	 * 查询应急装备坐标
	 * @param map
	 * @return
	 */
	public Map<String, Object> findMapResInstrument(Map<String, Object> map);
	/**
	 * 查询应急装备集合
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGridResInstrument(Map<String, Object> map);
	/**
	 * 查询应急装备总数
	 * @param map
	 * @return
	 */
	public int getTotaldataGridResInstrument(Map<String, Object> map);

	/**
	 * 查询应急物资坐标
	 * @param map
	 * @return
	 */
	public Map<String, Object> findMapMate(Map<String, Object> map);
	/**
	 * 查询应急物资集合
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGridMate(Map<String, Object> map);
	/**
	 * 查询应急物资总数
	 * @param map
	 * @return
	 */
	public int getTotaldataGridMate(Map<String, Object> map);

	/**
	 * 查询应急专家坐标
	 * @param map
	 * @return
	 */
	public Map<String, Object> findMapResExpert(Map<String, Object> map);
	/**
	 * 查询应急专家集合
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGridResExpert(Map<String, Object> map);
	/**
	 * 查询应急专家总数
	 * @param map
	 * @return
	 */
	public int getTotaldataGridResExpert(Map<String, Object> map);

	/**
	 * 查询应急医院坐标
	 * @param map
	 * @return
	 */
	public Map<String, Object> findMapHospital(Map<String, Object> map);
	/**
	 * 查询应急医院集合
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGridHospital(Map<String, Object> map);
	/**
	 * 查询应急医院总数
	 * @param map
	 * @return
	 */
	public int getTotaldataGridHospital(Map<String, Object> map);

	/**
	 * 查询应急处置技术集合
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGridDispTechnology(Map<String, Object> map);
	/**
	 * 查询应急处置技术总数
	 * @param map
	 * @return
	 */
	public int getTotaldataGridDispTechnology(Map<String, Object> map);
	
	/**
	 * 查询应急处置技术集合
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGridMsds(Map<String, Object> map);
	/**
	 * 查询应急处置技术总数
	 * @param map
	 * @return
	 */
	public int getTotaldataGridMsds(Map<String, Object> map);

	/**
	 * 查询应急通讯录集合
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGridContacts(Map<String, Object> map);
	/**
	 * 查询应急通讯录总数
	 * @param map
	 * @return
	 */
	public int getTotaldataGridContacts(Map<String, Object> map);

	
	
	/**
	 * 根据consequenceid查询应急处置技术
	 * @param map
	 * @return
	 */
	public Map<String, Object> findAllDispTechnology(Map<String, Object> map);
	/**
	 * 根据consequenceid查询应急处置技术
	 * @param map
	 * @return
	 */
	public Map<String, Object> findAllMsds(Map<String, Object> map);

	/**
	 * 根据consequenceid查询应急通讯录
	 * @param map
	 * @return
	 */
	public Map<String, Object> findAllContacts(Map<String, Object> map);


}
	
