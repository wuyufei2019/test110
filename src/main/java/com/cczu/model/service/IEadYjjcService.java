package com.cczu.model.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cczu.model.entity.ACA_FireballEntity;
import com.cczu.model.entity.ACA_GasphysicalEntity;
import com.cczu.model.entity.ACA_InstantleakageEntity;
import com.cczu.model.entity.ACA_JetFireEntity;
import com.cczu.model.entity.ACA_LeakageEntity;
import com.cczu.model.entity.ACA_PhysicalEntity;
import com.cczu.model.entity.ACA_PoolFireEntity;
import com.cczu.model.entity.ACA_VceEntity;
import com.cczu.model.entity.EAD_AccidentEntity;
/**
 * 
 * @ClassName: IEadYjjcballService
 * @Description: 应急辅助决策_事故后果计算
 * @author jason
 *
 */
public interface IEadYjjcService {

	/**
	 * request EAD_AccidentEntity 共通设定
	 * @param request
	 * @return
	 */
	public EAD_AccidentEntity commonSetEad(HttpServletRequest request);

	/**
	 * 应急辅助决策计算保存_火球
	 * @param aca
	 * @return
	 * @throws Exception
	 */
	public String countSaveEadFireball(EAD_AccidentEntity ead,
			ACA_FireballEntity aca) throws Exception;

	/**
	 * 应急辅助决策计算保存_压缩气体物理爆炸
	 * @param aca
	 * @return
	 * @throws Exception
	 */
	public String countSaveEadGasphysical(EAD_AccidentEntity ead,
			ACA_GasphysicalEntity aca) throws Exception;

	/**
	 * 应急辅助决策计算保存_喷射火
	 * @param aca
	 * @return
	 * @throws Exception
	 */
	public String countSaveEadJetFire(EAD_AccidentEntity ead,
			ACA_JetFireEntity aca) throws Exception;

	/**
	 * 应急辅助决策计算保存_持续泄漏
	 * @param aca
	 * @return
	 * @throws Exception
	 */
	public String countSaveEadLeakage(EAD_AccidentEntity ead,
			ACA_LeakageEntity aca) throws Exception;

	/**
	 * 应急辅助决策计算保存_物理爆炸（压力容器爆炸）
	 * @param aca
	 * @return
	 * @throws Exception
	 */
	public String countSaveEadPhysical(EAD_AccidentEntity ead,
			ACA_PhysicalEntity aca) throws Exception;

	/**
	 * 应急辅助决策计算保存_化学爆炸（蒸气云爆炸）
	 * @param aca
	 * @return
	 * @throws Exception
	 */
	public String countSaveEadPoolFire(EAD_AccidentEntity ead,
			ACA_PoolFireEntity aca) throws Exception;

	/**
	 * 应急辅助决策计算保存_池火灾
	 * @param aca
	 * @return
	 * @throws Exception
	 */
	public String countSaveEadVce(EAD_AccidentEntity ead,
			ACA_VceEntity aca) throws Exception;

	/**
	 * 应急辅助决策计算保存_瞬时泄漏
	 * @param aca
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> countSaveEadInstantleakage(EAD_AccidentEntity ead,
			ACA_InstantleakageEntity aca) throws Exception;

	/**
	 * 应急辅助决策计算保存_3/5/10KM
	 * @param id
	 */
	public void saveDistance(Long id);

	/**
	 * 计算结束_事故后果页面_救援路线
	 * @param map
	 * @return
	 */
	public String findconsequenceRoute(Map<String, Object> map);
	/**
	 * 计算结束_事故后果页面_避难场所
	 * @param map
	 * @return
	 */
	public String findconsequenceResPlaceMap(Map<String, Object> map);
	/**
	 * 计算结束_事故后果页面_应急队伍
	 * @param map
	 * @return
	 */
	public String findconsequenceResTeamMap(Map<String, Object> map);
	/**
	 * 计算结束_事故后果页面_物资
	 * @param map
	 * @return
	 */
	public String findconsequenceMateMap(Map<String, Object> map);
	/**
	 * 计算结束_事故后果页面_医院
	 * @param map
	 * @return
	 */
	public String findconsequenceHospitalMap(Map<String, Object> map);
	/**
	 * 计算结束_事故后果页面_装备
	 * @param map
	 * @return
	 */
	public String findconsequenceResInstrumentMap(Map<String, Object> map);
	/**
	 * 计算结束_事故后果页面_专家
	 * @param map
	 * @return
	 */
	public String findconsequenceResExpertMap(Map<String, Object> map);

	/**
	 * 计算结束_事故后果页面_避难场所  列表数据
	 * @param map
	 * @return
	 */
	public Map<String, Object> findconsequenceResPlaceDataGrid(Map<String, Object> map);
	/**
	 * 计算结束_事故后果页面_应急队伍
	 * @param map
	 * @return
	 */
	public Map<String, Object> findconsequenceResTeamDataGrid(Map<String, Object> map);
	/**
	 * 计算结束_事故后果页面_物资
	 * @param map
	 * @return
	 */
	public Map<String, Object> findconsequenceMateDataGrid(Map<String, Object> map);
	/**
	 * 计算结束_事故后果页面_医院
	 * @param map
	 * @return
	 */
	public Map<String, Object> findconsequenceHospitalDataGrid(Map<String, Object> map);
	/**
	 * 计算结束_事故后果页面_装备
	 * @param map
	 * @return
	 */
	public Map<String, Object> findconsequenceResInstrumentDataGrid(Map<String, Object> map);
	/**
	 * 计算结束_事故后果页面_专家
	 * @param map
	 * @return
	 */
	public Map<String, Object> findconsequenceResExpertDataGrid(Map<String, Object> map);
	/**
	 * 计算结束_事故后果页面_应急处置技术
	 * @param map
	 * @return
	 */
	public Map<String, Object> findconsequenceDispTechnologyDataGrid(Map<String, Object> map);
	/**
	 * 计算结束_事故后果页面_应急处置技术
	 * @param map
	 * @return
	 */
	public Map<String, Object> findconsequenceMsdsDataGrid(Map<String, Object> map);
	/**
	 * 计算结束_事故后果页面_通讯录
	 * @param map
	 * @return
	 */
	public Map<String, Object> findconsequenceContactsDataGrid(Map<String, Object> map);

	/**
	 * 生成应急辅助决策文书
	 * @param request,map
	 * @return
	 */
	public String exportWord(HttpServletRequest request, Map<String, Object> map);

}
