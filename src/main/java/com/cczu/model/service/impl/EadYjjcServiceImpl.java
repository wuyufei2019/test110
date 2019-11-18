package com.cczu.model.service.impl;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cczu.model.dao.IAcaFireballDao;
import com.cczu.model.dao.IAcaGasphysicalDao;
import com.cczu.model.dao.IAcaInstantleakageDao;
import com.cczu.model.dao.IAcaJetFireDao;
import com.cczu.model.dao.IAcaLeakageDao;
import com.cczu.model.dao.IAcaPhysicalDao;
import com.cczu.model.dao.IAcaPoolFireDao;
import com.cczu.model.dao.IAcaVceDao;
import com.cczu.model.dao.IBisQyjbxxDao;
import com.cczu.model.dao.IEadYjjcAccidentErmContactsDao;
import com.cczu.model.dao.IEadYjjcAccidentErmDispTechnologyDao;
import com.cczu.model.dao.IEadYjjcAccidentErmHospitalDao;
import com.cczu.model.dao.IEadYjjcAccidentErmMateDao;
import com.cczu.model.dao.IEadYjjcAccidentErmResExpertDao;
import com.cczu.model.dao.IEadYjjcAccidentErmResInstrumentDao;
import com.cczu.model.dao.IEadYjjcAccidentErmResPlaceDao;
import com.cczu.model.dao.IEadYjjcAccidentErmResTeamDao;
import com.cczu.model.dao.IEadYjjcAccidentFireballDao;
import com.cczu.model.dao.IEadYjjcAccidentGasphysicalDao;
import com.cczu.model.dao.IEadYjjcAccidentInstantleakageDao;
import com.cczu.model.dao.IEadYjjcAccidentJetFireDao;
import com.cczu.model.dao.IEadYjjcAccidentLeakageDao;
import com.cczu.model.dao.IEadYjjcAccidentPhysicalDao;
import com.cczu.model.dao.IEadYjjcAccidentPoolFireDao;
import com.cczu.model.dao.IEadYjjcAccidentTmeskMsdsDao;
import com.cczu.model.dao.IEadYjjcAccidentVceDao;
import com.cczu.model.dao.IEadYjjcAccidentWordDao;
import com.cczu.model.dao.IEadYjjcDao;
import com.cczu.model.dao.IErmBncsDao;
import com.cczu.model.dao.IErmTxlDao;
import com.cczu.model.dao.IErmYjczjsDao;
import com.cczu.model.dao.IErmYjdwDao;
import com.cczu.model.dao.IErmYjwzDao;
import com.cczu.model.dao.IErmYjyyDao;
import com.cczu.model.dao.IErmYjzbDao;
import com.cczu.model.dao.IErmYjzjDao;
import com.cczu.model.dao.ISekbMsdsDao;
import com.cczu.model.dao.ITdicAcawxhxpDao;
import com.cczu.model.dao.SekbCshfhjlDao;
import com.cczu.model.entity.ACA_FireballEntity;
import com.cczu.model.entity.ACA_GasphysicalEntity;
import com.cczu.model.entity.ACA_InstantleakageEntity;
import com.cczu.model.entity.ACA_JetFireEntity;
import com.cczu.model.entity.ACA_LeakageEntity;
import com.cczu.model.entity.ACA_PhysicalEntity;
import com.cczu.model.entity.ACA_PoolFireEntity;
import com.cczu.model.entity.ACA_VceEntity;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.EAD_AccidentEntity;
import com.cczu.model.entity.EAD_AccidentWordEntity;
import com.cczu.model.entity.EAD_Accident_ERM_Contacts;
import com.cczu.model.entity.EAD_Accident_ERM_Hospital;
import com.cczu.model.entity.EAD_Accident_ERM_Mate;
import com.cczu.model.entity.EAD_Accident_ERM_ResExpert;
import com.cczu.model.entity.EAD_Accident_ERM_ResInstrument;
import com.cczu.model.entity.EAD_Accident_ERM_ResPlace;
import com.cczu.model.entity.EAD_Accident_ERM_ResTeam;
import com.cczu.model.entity.EAD_Accident_Fireball;
import com.cczu.model.entity.EAD_Accident_Gasphysical;
import com.cczu.model.entity.EAD_Accident_Instantleakage;
import com.cczu.model.entity.EAD_Accident_JetFire;
import com.cczu.model.entity.EAD_Accident_Leakage;
import com.cczu.model.entity.EAD_Accident_Physical;
import com.cczu.model.entity.EAD_Accident_PoolFire;
import com.cczu.model.entity.EAD_Accident_TMESK_Msds;
import com.cczu.model.entity.EAD_Accident_Vce;
import com.cczu.model.entity.ERM_EmergencyContactsEntity;
import com.cczu.model.entity.ERM_EmergencyHospitalEntity;
import com.cczu.model.entity.ERM_EmergencyMateEntity;
import com.cczu.model.entity.ERM_EmergencyResExpertEntity;
import com.cczu.model.entity.ERM_EmergencyResInstrumentEntity;
import com.cczu.model.entity.ERM_EmergencyResPlaceEntity;
import com.cczu.model.entity.ERM_EmergencyResTeamEntity;
import com.cczu.model.entity.TMESK_MsdsEntity;
import com.cczu.model.entity.TMESK_ProtectionDistanceEntity;
import com.cczu.model.entity.Tdic_AcaWxhxpmonitoringEntity;
import com.cczu.model.service.IEadYjjcService;
import com.cczu.sys.comm.mapper.BeanMapper;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.BaiDuMap;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.comm.utils.WebPagePicture;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

@SuppressWarnings("restriction")
@Service("IEadYjjcService")
public class EadYjjcServiceImpl implements IEadYjjcService {
	
	@Autowired
	private IAcaFireballDao acaFireball;
	@Autowired
	private IAcaGasphysicalDao acaGasphysical;
	@Autowired
	private IAcaInstantleakageDao acaInstantleakage;
	@Autowired
	private IAcaJetFireDao acaJetFire;
	@Autowired
	private IAcaLeakageDao acaLeakage;
	@Autowired
	private IAcaPhysicalDao acaPhysical;
	@Autowired
	private IAcaPoolFireDao acaPoolFire;
	@Autowired	
	private IAcaVceDao acaVce;
	
	@Autowired
	private IEadYjjcAccidentFireballDao  eadYjjcAccidentFireball; 
	@Autowired
	private IEadYjjcAccidentGasphysicalDao  eadYjjcAccidentGasphysical; 
	@Autowired
	private IEadYjjcAccidentInstantleakageDao  eadYjjcAccidentInstantleakage; 
	@Autowired
	private IEadYjjcAccidentJetFireDao  eadYjjcAccidentJetFire; 
	@Autowired
	private IEadYjjcAccidentLeakageDao  eadYjjcAccidentLeakage; 
	@Autowired
	private IEadYjjcAccidentPhysicalDao  eadYjjcAccidentPhysical; 
	@Autowired
	private IEadYjjcAccidentPoolFireDao  eadYjjcAccidentPoolFire; 
	@Autowired
	private IEadYjjcAccidentVceDao  eadYjjcAccidentVce; 
	
	@Autowired
	private IErmYjdwDao ermYjdwDao;
	@Autowired
	private IErmYjzbDao ermYjzbDao;
	@Autowired
	private IErmYjwzDao ermYjwzDao;
	@Autowired
	private IErmBncsDao ermBncsDao;
	@Autowired
	private IErmYjzjDao ermYjzjDao;
	@Autowired
	private IErmYjyyDao ermYjyyDao;
	@Autowired
	private IErmYjczjsDao ermYjczjsDao;
	@Autowired
	private IErmTxlDao ermTxlDao;
	
	@Autowired
	private ISekbMsdsDao sekbMsdsDao;
	
	@Autowired
	private IEadYjjcDao eadYjjcDao;
	
	@Autowired
	private IEadYjjcAccidentWordDao eadYjjcWordDao;
	
	@Autowired
	private IBisQyjbxxDao bisQyjbxx;
	@Autowired
	private IEadYjjcAccidentErmResTeamDao accidentERMResTeamDao;
	@Autowired
	private IEadYjjcAccidentErmResInstrumentDao accidentErmResInstrumentDao;
	@Autowired
	private IEadYjjcAccidentErmMateDao accidentErmMateDao;
	@Autowired
	private IEadYjjcAccidentErmResPlaceDao accidentErmResPlaceDao;
	@Autowired
	private IEadYjjcAccidentErmResExpertDao accidentErmResExpertDao;
	@Autowired
	private IEadYjjcAccidentErmHospitalDao accidentErmHospitalDao;
	@Autowired
	private IEadYjjcAccidentErmDispTechnologyDao accidentErmDispTechnologyDao;
	@Autowired
	private IEadYjjcAccidentTmeskMsdsDao accidentTmeskMsdsDao;
	@Autowired
	private IEadYjjcAccidentErmContactsDao accidentErmContactsDao;
	
	@Autowired
	private ITdicAcawxhxpDao iTdicAcawxhxpDao;
	
	@Resource
	private SekbCshfhjlDao sekbCshfhjlDao;	//疏散距离
	
	@Override
	public EAD_AccidentEntity commonSetEad(HttpServletRequest request) {
		EAD_AccidentEntity ead = new EAD_AccidentEntity();
		if(StringUtils.isNotEmpty( request.getParameter("ead_yjjc_qy") ) ){
			ead.setQyid(Long.valueOf(request.getParameter("ead_yjjc_qy") ) );
		}
		ead.setType(request.getParameter("ead_yjjc_type") );
		ead.setLng(request.getParameter("ead_yjjc_lng") );
		ead.setLat(request.getParameter("ead_yjjc_lat") );
		if(StringUtils.isNotEmpty( request.getParameter("ead_yjjc_type") ) ){
			if(!request.getParameter("ead_yjjc_type").toString().equals("4")){
				ead.setMatter( request.getParameter("M1") );
				try {
					Tdic_AcaWxhxpmonitoringEntity aca=iTdicAcawxhxpDao.findById( Long.parseLong(request.getParameter("M1")) );
					if(aca!=null){
						ead.setMatterstr( aca.getM1() );
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		ead.setTodayfx(request.getParameter("ead_yjjc_weathertoday_fx") );
		return ead;
	}
	
	/**
	 * EAD_AccidentEntity 插入数据之前的相关数据set
	 * @param sessionuser
	 * @param ead
	 * @param t
	 * @return
	 */
	private EAD_AccidentEntity beforeSetEad(ShiroUser sessionuser,EAD_AccidentEntity ead,Timestamp t) {
		if(ead.getQyid()!=0){
			BIS_EnterpriseEntity bis=bisQyjbxx.findInfoById(ead.getQyid());
			if(bis!=null){
				ead.setQyname(bis.getM1());
			}
		}
		ead.setS1(t);
		ead.setID1(sessionuser.getId());
		ead.setID2(sessionuser.getXzqy());
		ead.setID3(sessionuser.getQyid());
		return ead;
	}

	/**
	 * 构建EAD_Accident_Fireball
	 * 
	 * @param accidentId
	 * @param fireballId
	 * @return EAD_Accident_Fireball
	 */
	private EAD_Accident_Fireball getAccidentFireball(Long accidentId, Long fireballId) {
		EAD_Accident_Fireball ead = new EAD_Accident_Fireball();
		ead.setAccident( new EAD_AccidentEntity(accidentId) );
		ead.setFireball( new ACA_FireballEntity(fireballId) );
		return ead;
	}
	/**
	 * 构建EAD_Accident_Gasphysical
	 * @param accidentId
	 * @param gasphysicalId
	 * @return
	 */
	private EAD_Accident_Gasphysical getAccidentGasphysical(Long accidentId, Long gasphysicalId) {
		EAD_Accident_Gasphysical ead = new EAD_Accident_Gasphysical();
		ead.setAccident( new EAD_AccidentEntity(accidentId) );
		ead.setGasphysical( new ACA_GasphysicalEntity(gasphysicalId) );
		return ead;
	}
	/**
	 * 构建EAD_Accident_Instantleakage
	 * 
	 * @param accidentId
	 * @param instantleakageId
	 * @return EAD_Accident_Instantleakage
	 */
	private EAD_Accident_Instantleakage getAccidentInstantleakage(Long accidentId, Long instantleakageId) {
		EAD_Accident_Instantleakage ead = new EAD_Accident_Instantleakage();
		ead.setAccident( new EAD_AccidentEntity(accidentId) );
		ead.setInstantleakage( new ACA_InstantleakageEntity(instantleakageId) );
		return ead;
	}
	/**
	 * 构建EAD_Accident_JetFire
	 * 
	 * @param accidentId
	 * @param jetfireId
	 * @return EAD_Accident_JetFire
	 */
	private EAD_Accident_JetFire getAccidentJetFire(Long accidentId, Long jetfireId) {
		EAD_Accident_JetFire ead = new EAD_Accident_JetFire();
		ead.setAccident( new EAD_AccidentEntity(accidentId) );
		ead.setJetfire( new ACA_JetFireEntity(jetfireId) );
		return ead;
	}
	/**
	 * 构建EAD_Accident_Leakage
	 * 
	 * @param accidentId
	 * @param leakageId
	 * @return EAD_Accident_Leakage
	 */
	private EAD_Accident_Leakage getAccidentLeakage(Long accidentId, Long leakageId) {
		EAD_Accident_Leakage ead = new EAD_Accident_Leakage();
		ead.setAccident( new EAD_AccidentEntity(accidentId) );
		ead.setLeakage( new ACA_LeakageEntity(leakageId) );
		return ead;
	}
	/**
	 * 构建EAD_Accident_Physical
	 * 
	 * @param accidentId
	 * @param fireballId
	 * @return EAD_Accident_Physical
	 */
	private EAD_Accident_Physical getAccidentPhysical(Long accidentId, Long physicalId) {
		EAD_Accident_Physical ead = new EAD_Accident_Physical();
		ead.setAccident( new EAD_AccidentEntity(accidentId) );
		ead.setPhysical( new ACA_PhysicalEntity(physicalId) );
		return ead;
	}
	/**
	 * 构建EAD_Accident_PoolFire
	 * 
	 * @param accidentId
	 * @param poolfireId
	 * @return EAD_Accident_PoolFire
	 */
	private EAD_Accident_PoolFire getAccidentPoolFire(Long accidentId, Long poolfireId) {
		EAD_Accident_PoolFire ead = new EAD_Accident_PoolFire();
		ead.setAccident( new EAD_AccidentEntity(accidentId) );
		ead.setPoolfire( new ACA_PoolFireEntity(poolfireId) );
		return ead;
	}
	/**
	 * 构建EAD_Accident_Vce
	 * 
	 * @param accidentId
	 * @param vceId
	 * @return EAD_Accident_Vce
	 */
	private EAD_Accident_Vce getAccidentVce(Long accidentId, Long vceId) {
		EAD_Accident_Vce ead = new EAD_Accident_Vce();
		ead.setAccident( new EAD_AccidentEntity(accidentId) );
		ead.setVce( new ACA_VceEntity(vceId) );
		return ead;
	}
	
	
	@Override
	public String countSaveEadFireball(EAD_AccidentEntity ead,ACA_FireballEntity aca) throws Exception {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Timestamp t=DateUtils.getSysTimestamp();
		//返回accidentId
		Long accidentId=eadYjjcDao.saveAccidentRid(beforeSetEad(sessionuser,ead,t));

		aca.setS1(t);
		aca.setS2(t);
		aca.setS3(0);
		aca.setID1(sessionuser.getId());
		//计算
		Map<String, Object> map=acaFireball.jcsaveInfo(aca);
		
		// 是否删除
		eadYjjcAccidentFireball.deleteAccidentFireball(accidentId);
		
		//存数据库
		eadYjjcAccidentFireball.saveAccidentFireball( getAccidentFireball( accidentId,Long.parseLong(map.get("id").toString()) ) );
		
		map.put("id", accidentId);
		
		return JsonMapper.getInstance().toJson(map);
	}

	@Override
	public String countSaveEadGasphysical(EAD_AccidentEntity ead,
			ACA_GasphysicalEntity aca) throws Exception {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Timestamp t=DateUtils.getSysTimestamp();
		//返回accidentId
		Long accidentId=eadYjjcDao.saveAccidentRid(beforeSetEad(sessionuser,ead,t));
		
		aca.setS1(t);
		aca.setS2(t);
		aca.setS3(0);
		aca.setID1(sessionuser.getId());
		//计算
		Map<String, Object> map=acaGasphysical.jcsaveInfo(aca);
		// 是否删除
		eadYjjcAccidentGasphysical.deleteAccidentGasphysical(accidentId);
		//存数据库
		eadYjjcAccidentGasphysical.saveAccidentGasphysical( getAccidentGasphysical( accidentId,Long.parseLong(map.get("id").toString()) ) );
		
		map.put("id", accidentId);
		
		return JsonMapper.getInstance().toJson(map);
	}

	@Override
	public String countSaveEadJetFire(EAD_AccidentEntity ead,
			ACA_JetFireEntity aca) throws Exception {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Timestamp t=DateUtils.getSysTimestamp();
		//返回accidentId
		Long accidentId=eadYjjcDao.saveAccidentRid(beforeSetEad(sessionuser,ead,t));

		aca.setS1(t);
		aca.setS2(t);
		aca.setS3(0);
		aca.setID1(sessionuser.getId());
		
		//计算
		Map<String, Object> map=acaJetFire.jcsaveInfo(aca);
		// 是否删除
		eadYjjcAccidentJetFire.deleteAccidentJetFire(accidentId);
		//存数据库
		eadYjjcAccidentJetFire.saveAccidentJetFire( getAccidentJetFire( accidentId,Long.parseLong(map.get("id").toString()) ) );
		
		map.put("id", accidentId);
		
		return JsonMapper.getInstance().toJson(map);
	}

	@Override
	public String countSaveEadLeakage(EAD_AccidentEntity ead,
			ACA_LeakageEntity aca) throws Exception {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Timestamp t=DateUtils.getSysTimestamp();
		
		switch(aca.getM11()){
		case "1": ead.setTodayfx("东风"); break;//东
		case "2": ead.setTodayfx("东南风"); break;//东南
		case "3": ead.setTodayfx("南风"); break;//南
		case "4": ead.setTodayfx("西南风"); break;//西南
		case "5": ead.setTodayfx("西风"); break;//西
		case "6": ead.setTodayfx("西北风"); break;//西北
		case "7": ead.setTodayfx("北风"); break;//北
		case "8": ead.setTodayfx("东北风"); break;//东北
	}
		
		
		
		//返回accidentId
		Long accidentId=eadYjjcDao.saveAccidentRid(beforeSetEad(sessionuser,ead,t));
		
		aca.setS1(t);
		aca.setS2(t);
		aca.setS3(0);
		aca.setID1(sessionuser.getId());
		//计算
		Map<String, Object> map=acaLeakage.jcsaveInfo(aca);
		// 是否删除
		eadYjjcAccidentLeakage.deleteAccidentLeakage(accidentId);
		//存数据库
		eadYjjcAccidentLeakage.saveAccidentLeakage( getAccidentLeakage( accidentId,Long.parseLong(map.get("id").toString()) ) );
		
		map.put("id", accidentId);
		
		return JsonMapper.getInstance().toJson(map);
	}

	@Override
	public String countSaveEadPhysical(EAD_AccidentEntity ead,
			ACA_PhysicalEntity aca) throws Exception {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Timestamp t=DateUtils.getSysTimestamp();
		//返回accidentId
		Long accidentId=eadYjjcDao.saveAccidentRid(beforeSetEad(sessionuser,ead,t));

		aca.setS1(t);
		aca.setS2(t);
		aca.setS3(0);
		aca.setID1(sessionuser.getId());
		//计算
		Map<String, Object> map=acaPhysical.jcsaveInfo(aca);
		// 是否删除
		eadYjjcAccidentPhysical.deleteAccidentPhysical(accidentId);
		//存数据库
		eadYjjcAccidentPhysical.saveAccidentPhysical( getAccidentPhysical( accidentId,Long.parseLong(map.get("id").toString()) ) );
		
		map.put("id", accidentId);
		
		return JsonMapper.getInstance().toJson(map);
	}

	@Override
	public String countSaveEadPoolFire(EAD_AccidentEntity ead,
			ACA_PoolFireEntity aca) throws Exception {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Timestamp t=DateUtils.getSysTimestamp();
		//返回accidentId
		Long accidentId=eadYjjcDao.saveAccidentRid(beforeSetEad(sessionuser,ead,t));

		aca.setS1(t);
		aca.setS2(t);
		aca.setS3(0);
		aca.setID1(sessionuser.getId());
		//计算
		Map<String, Object> map=acaPoolFire.jcsaveInfo(aca);
		// 是否删除
		eadYjjcAccidentPoolFire.deleteAccidentPoolFire(accidentId);
		//存数据库
		eadYjjcAccidentPoolFire.saveAccidentPoolFire( getAccidentPoolFire( accidentId,Long.parseLong(map.get("id").toString()) ) );
		
		map.put("id", accidentId);
		
		return JsonMapper.getInstance().toJson(map);
	}

	@Override
	public String countSaveEadVce(EAD_AccidentEntity ead,
			ACA_VceEntity aca) throws Exception {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Timestamp t=DateUtils.getSysTimestamp();
		//返回accidentId
		Long accidentId=eadYjjcDao.saveAccidentRid(beforeSetEad(sessionuser,ead,t));

		aca.setS1(t);
		aca.setS2(t);
		aca.setS3(0);
		aca.setID1(sessionuser.getId());
		//计算
		Map<String, Object> map=acaVce.jcsaveInfo(aca);
		// 是否删除
		eadYjjcAccidentVce.deleteAccidentVce(accidentId);
		//存数据库
		eadYjjcAccidentVce.saveAccidentVce( getAccidentVce( accidentId,Long.parseLong(map.get("id").toString()) ) );
		
		map.put("id", accidentId);
		
		return JsonMapper.getInstance().toJson(map);
	}

	@Override
	public Map<String, Object> countSaveEadInstantleakage(EAD_AccidentEntity ead,
			ACA_InstantleakageEntity aca) throws Exception {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Timestamp t=DateUtils.getSysTimestamp();
		//返回accidentId
		Long accidentId=eadYjjcDao.saveAccidentRid(beforeSetEad(sessionuser,ead,t));

		aca.setS1(t);
		aca.setS2(t);
		aca.setS3(0);
		aca.setID1(sessionuser.getId());
		//计算
		List<Map<String,Object>> list=acaInstantleakage.jcsaveTimeAllInfo(aca);

		// 是否删除
		eadYjjcAccidentInstantleakage.deleteAccidentInstantleakage(accidentId);
		//存数据库
		if(list.size()>0){
			for(Map<String, Object> map : list){
				eadYjjcAccidentInstantleakage.saveAccidentInstantleakage( getAccidentInstantleakage( accidentId,Long.parseLong(map.get("id").toString()) ) );
			}
		}
		
		Map<String, Object> mapdata = new HashMap<String, Object>();
		mapdata.put("data", list);
		mapdata.put("id", accidentId);
		mapdata.put("time", list.size()*10-10);
		
		return mapdata;
	}

	@Override
	public void saveDistance(Long id) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();

		EAD_AccidentEntity ead=eadYjjcDao.findById(id);
		if(ead!=null){
			// 是否删除
			accidentERMResTeamDao.deleteERMResTeamListByAccidentID(id);

			//应急队伍  3/5/10KM
			List<ERM_EmergencyResTeamEntity> yjdwlist=ermYjdwDao.findAllInfo();
			if(yjdwlist.size()>0){
				for(ERM_EmergencyResTeamEntity yjdw:yjdwlist){
					if(StringUtils.isNotEmpty(yjdw.getM12())&&StringUtils.isNotEmpty(yjdw.getM13())){
						double distance = BaiDuMap.GetLongDistance(Double.valueOf(ead.getLng()),Double.valueOf(ead.getLat()),Double.valueOf(yjdw.getM12()),Double.valueOf(yjdw.getM13()) );

						if(distance<=3000 ){
							EAD_Accident_ERM_ResTeam resteam=new EAD_Accident_ERM_ResTeam();
							resteam.setDistance(3);
							resteam.setAccident(ead);
							resteam.setResteam(yjdw);
							accidentERMResTeamDao.saveAccidentErmResTeam(resteam);
						}else if(distance <= 5000 ){
							EAD_Accident_ERM_ResTeam resteam=new EAD_Accident_ERM_ResTeam();
							resteam.setDistance(5);
							resteam.setAccident(ead);
							resteam.setResteam(yjdw);
							accidentERMResTeamDao.saveAccidentErmResTeam(resteam);
						}else if(distance <= 10000 ){
							EAD_Accident_ERM_ResTeam resteam=new EAD_Accident_ERM_ResTeam();
							resteam.setDistance(10);
							resteam.setAccident(ead);
							resteam.setResteam(yjdw);
							accidentERMResTeamDao.saveAccidentErmResTeam(resteam);
						}
					}
				}
			}

			// 是否删除
			accidentErmResInstrumentDao.deleteERMResInstrumentListByAccidentID(id);

			//应急装备  3/5/10KM  ermYjzbDao         ERM_EmergencyResInstrumentEntity
			List<ERM_EmergencyResInstrumentEntity> yjzblist = ermYjzbDao.findAllInfo();
			if(yjzblist.size()>0){
				for(ERM_EmergencyResInstrumentEntity yjzb:yjzblist){
					if(StringUtils.isNotEmpty(yjzb.getM14())&&StringUtils.isNotEmpty(yjzb.getM15())){
						double distance = BaiDuMap.GetLongDistance(Double.valueOf(ead.getLng()),Double.valueOf(ead.getLat()),Double.valueOf(yjzb.getM14()),Double.valueOf(yjzb.getM15()) );
						if(distance<=3000 ){
							EAD_Accident_ERM_ResInstrument resinstru = new EAD_Accident_ERM_ResInstrument();
							resinstru.setDistance(3);
							resinstru.setAccident(ead);
							resinstru.setResInstrument(yjzb);
							accidentErmResInstrumentDao.saveAccidentErmResInstrument(resinstru);
						}else if(distance <= 5000 ){
							EAD_Accident_ERM_ResInstrument resinstru = new EAD_Accident_ERM_ResInstrument();
							resinstru.setDistance(5);
							resinstru.setAccident(ead);
							resinstru.setResInstrument(yjzb);
							accidentErmResInstrumentDao.saveAccidentErmResInstrument(resinstru);
						}else if(distance <= 10000 ){
							EAD_Accident_ERM_ResInstrument resinstru = new EAD_Accident_ERM_ResInstrument();
							resinstru.setDistance(10);
							resinstru.setAccident(ead);
							resinstru.setResInstrument(yjzb);
							accidentErmResInstrumentDao.saveAccidentErmResInstrument(resinstru);
						}
					}
				}
			}

			// 是否删除
			accidentErmMateDao.deleteERMMateListByAccidentID(id);
			
			//应急物资  3/5/10KM  ermYjwzDao         ERM_EmergencyMateEntity
			List<ERM_EmergencyMateEntity> yjwzlist = ermYjwzDao.findAllInfo();
			if(yjwzlist.size()>0){
				for(ERM_EmergencyMateEntity yjwz:yjwzlist){
					if(StringUtils.isNotEmpty(yjwz.getM14())&&StringUtils.isNotEmpty(yjwz.getM15())){
						double distance = BaiDuMap.GetLongDistance(Double.valueOf(ead.getLng()),Double.valueOf(ead.getLat()),Double.valueOf(yjwz.getM14()),Double.valueOf(yjwz.getM15()) );
						if(distance<=3000 ){
							EAD_Accident_ERM_Mate resinstru = new EAD_Accident_ERM_Mate();
							resinstru.setDistance(3);
							resinstru.setAccident(ead);
							resinstru.setMate(yjwz);
							accidentErmMateDao.saveAccidentErmMate(resinstru);
						}else if(distance <= 5000 ){
							EAD_Accident_ERM_Mate resinstru = new EAD_Accident_ERM_Mate();
							resinstru.setDistance(5);
							resinstru.setAccident(ead);
							resinstru.setMate(yjwz);
							accidentErmMateDao.saveAccidentErmMate(resinstru);
						}else if(distance <= 10000 ){
							EAD_Accident_ERM_Mate resinstru = new EAD_Accident_ERM_Mate();
							resinstru.setDistance(10);
							resinstru.setAccident(ead);
							resinstru.setMate(yjwz);
							accidentErmMateDao.saveAccidentErmMate(resinstru);
						}
					}
				}
			}

			// 是否删除
			accidentErmResPlaceDao.deleteERMResPlaceListByAccidentID(id);
			
			//应急避难场所  3/5/10KM   ermBncsDao      ERM_EmergencyResPlaceEntity    accidentErmResPlaceDao   ermBncsDao
			List<ERM_EmergencyResPlaceEntity> bncslist = ermBncsDao.findAllInfo();
			if(bncslist.size()>0){
				for(ERM_EmergencyResPlaceEntity bncs:bncslist){
					if(StringUtils.isNotEmpty(bncs.getM12())&&StringUtils.isNotEmpty(bncs.getM13())){
						double distance = BaiDuMap.GetLongDistance(Double.valueOf(ead.getLng()),Double.valueOf(ead.getLat()),Double.valueOf(bncs.getM12()),Double.valueOf(bncs.getM13()) );
						if(distance<=3000 ){
							EAD_Accident_ERM_ResPlace resplace = new EAD_Accident_ERM_ResPlace();
							resplace.setDistance(3);
							resplace.setAccident(ead);
							resplace.setResplace(bncs);
							accidentErmResPlaceDao.saveAccidentErmResPlace(resplace);
						}else if(distance <= 5000 ){
							EAD_Accident_ERM_ResPlace resplace = new EAD_Accident_ERM_ResPlace();
							resplace.setDistance(5);
							resplace.setAccident(ead);
							resplace.setResplace(bncs);
							accidentErmResPlaceDao.saveAccidentErmResPlace(resplace);
						}else if(distance <= 10000 ){
							EAD_Accident_ERM_ResPlace resplace = new EAD_Accident_ERM_ResPlace();
							resplace.setDistance(10);
							resplace.setAccident(ead);
							resplace.setResplace(bncs);
							accidentErmResPlaceDao.saveAccidentErmResPlace(resplace);
						}
					}
				}
			}

			// 是否删除
			accidentErmResExpertDao.deleteERMResExpertListByAccidentID(id);

			//应急专家   相关区域内部的   ermYjzjDao   		 ERM_EmergencyResExpertEntity    accidentErmResExpertDao  ermYjzjDao
			List<ERM_EmergencyResExpertEntity> yjzjlist = ermYjzjDao.findInfoByXzpy(sessionuser.getXzqy());
			if(yjzjlist.size()>0){
				for(ERM_EmergencyResExpertEntity yjzj:yjzjlist){
					String[] arrM19s = yjzj.getM19().split(",");
					
					int huozai=0,baoza=0,xielou=0;
					for(int i=0;i<arrM19s.length;i++){
						if( arrM19s[i].equals("8") ){
							huozai=huozai+1;
						}
						if( arrM19s[i].equals("15")||arrM19s[i].equals("16")||arrM19s[i].equals("17")||arrM19s[i].equals("18") ){
							baoza=baoza+1;
						}
						if( arrM19s[i].equals("19") ){
							xielou=xielou+1;
						}
					}
					
					EAD_Accident_ERM_ResExpert resexpert = new EAD_Accident_ERM_ResExpert();
					
					if( ead.getType().equals("1")||ead.getType().equals("2")||ead.getType().equals("3") ){
						if( huozai>0 ){
							resexpert.setType(1);//事故类型火灾
							resexpert.setAccident(ead);
							resexpert.setResexpert(yjzj);
							accidentErmResExpertDao.saveAccidentErmResExpert(resexpert);
						}
					}else if( ead.getType().equals("4")||ead.getType().equals("5")||ead.getType().equals("6") ){
						if( baoza>0 ){
							resexpert.setType(2);//事故类型爆炸
							resexpert.setAccident(ead);
							resexpert.setResexpert(yjzj);
							accidentErmResExpertDao.saveAccidentErmResExpert(resexpert);
						}
					}else if( ead.getType().equals("7")||ead.getType().equals("8") ){
						if( xielou>0 ){
							resexpert.setType(3);//事故类型泄漏
							resexpert.setAccident(ead);
							resexpert.setResexpert(yjzj);
							accidentErmResExpertDao.saveAccidentErmResExpert(resexpert);
						}
					}
					
				}
			}
			
			//应急专家  3/5/10KM  ermYjzjDao   		 ERM_EmergencyResExpertEntity    accidentErmResExpertDao  ermYjzjDao
			/*List<ERM_EmergencyResExpertEntity> yjzjlist = ermYjzjDao.findAllInfo();
			if(yjzjlist.size()>0){
				for(ERM_EmergencyResExpertEntity yjzj:yjzjlist){
					if( StringUtils.isNotEmpty(yjzj.getM21()) && StringUtils.isNotEmpty(yjzj.getM22()) ){
						double distance = BaiDuMap.GetLongDistance(Double.valueOf(ead.getLng()),Double.valueOf(ead.getLat()),Double.valueOf(yjzj.getM21()),Double.valueOf(yjzj.getM22()) );
						if(distance<=3000 ){
							EAD_Accident_ERM_ResExpert resexpert = new EAD_Accident_ERM_ResExpert();
							resexpert.setDistance(3);
							resexpert.setAccident(ead);
							resexpert.setResexpert(yjzj);
							accidentErmResExpertDao.saveAccidentErmResExpert(resexpert);
						}else if(distance <= 5000 ){
							EAD_Accident_ERM_ResExpert resexpert = new EAD_Accident_ERM_ResExpert();
							resexpert.setDistance(5);
							resexpert.setAccident(ead);
							resexpert.setResexpert(yjzj);
							accidentErmResExpertDao.saveAccidentErmResExpert(resexpert);
						}else if(distance <= 10000 ){
							EAD_Accident_ERM_ResExpert resexpert = new EAD_Accident_ERM_ResExpert();
							resexpert.setDistance(10);
							resexpert.setAccident(ead);
							resexpert.setResexpert(yjzj);
							accidentErmResExpertDao.saveAccidentErmResExpert(resexpert);
						}
					}
				}
			}
			*/
			// 是否删除
			accidentErmHospitalDao.deleteERMHospitalListByAccidentID(id);
			
			//应急医院  3/5/10KM  ermYjyyDao   		 ERM_EmergencyHospitalEntity     accidentErmHospitalDao   ermYjyyDao
			List<ERM_EmergencyHospitalEntity> yjyylist = ermYjyyDao.findAllInfo();
			if(yjyylist.size()>0){
				for(ERM_EmergencyHospitalEntity yjyy:yjyylist){
					if( StringUtils.isNotEmpty(yjyy.getM11()) && StringUtils.isNotEmpty(yjyy.getM12()) ){
						double distance = BaiDuMap.GetLongDistance(Double.valueOf(ead.getLng()),Double.valueOf(ead.getLat()),Double.valueOf(yjyy.getM11()),Double.valueOf(yjyy.getM12()) );
						if(distance<=3000 ){
							EAD_Accident_ERM_Hospital hospital = new EAD_Accident_ERM_Hospital();
							hospital.setDistance(3);
							hospital.setAccident(ead);
							hospital.setHospital(yjyy);
							accidentErmHospitalDao.saveAccidentErmHospital(hospital);
						}else if(distance <= 5000 ){
							EAD_Accident_ERM_Hospital hospital = new EAD_Accident_ERM_Hospital();
							hospital.setDistance(5);
							hospital.setAccident(ead);
							hospital.setHospital(yjyy);
							accidentErmHospitalDao.saveAccidentErmHospital(hospital);
						}else if(distance <= 10000 ){
							EAD_Accident_ERM_Hospital hospital = new EAD_Accident_ERM_Hospital();
							hospital.setDistance(10);
							hospital.setAccident(ead);
							hospital.setHospital(yjyy);
							accidentErmHospitalDao.saveAccidentErmHospital(hospital);
						}
					}
				}
			}
			// 是否删除
//			accidentErmDispTechnologyDao.deleteERMDispTechnologyListByAccidentID(id);
//			
//			//应急处置技术   sekbMsdsDao      ERM_EmergencyDispTechnologyEntity   accidentErmDispTechnologyDao   ermYjczjsDao
//			List<ERM_EmergencyDispTechnologyEntity> yjczjslist = ermYjczjsDao.findAllInfo();
//			if(yjczjslist.size()>0){
//				for(ERM_EmergencyDispTechnologyEntity yjczjs:yjczjslist){
//					EAD_Accident_ERM_DispTechnology disptechnology = new EAD_Accident_ERM_DispTechnology();
//					disptechnology.setAccident(ead);
//					disptechnology.setDispTechnology(yjczjs);
//					accidentErmDispTechnologyDao.saveAccidentErmDispTechnology(disptechnology);
//				}
//			}
			// 是否删除
			accidentTmeskMsdsDao.deleteTmeskMsdsListByAccidentID(id);
//			应急处置技术   sekbMsdsDao      TMESK_MsdsEntity msds  EAD_Accident_TMESK_Msds  accidentTmeskMsdsDao   ISekbMsdsDao sekbMsdsDao;
			List<TMESK_MsdsEntity> yjczjslist = sekbMsdsDao.findByM1(ead.getMatterstr());
			if(yjczjslist.size()>0){
				for(TMESK_MsdsEntity yjczjs:yjczjslist){
					EAD_Accident_TMESK_Msds msds = new EAD_Accident_TMESK_Msds();
					msds.setAccident(ead);
					msds.setMsds(yjczjs); 
					accidentTmeskMsdsDao.saveAccidentTmeskMsds(msds);
				}
			}
//			List<TMESK_MsdsEntity> yjczjslist = sekbMsdsDao.findByMsdsName();
//			if(yjczjslist.size()>0){
//				for(ERM_EmergencyDispTechnologyEntity yjczjs:yjczjslist){
//					EAD_Accident_ERM_DispTechnology disptechnology = new EAD_Accident_ERM_DispTechnology();
//					disptechnology.setAccident(ead);
//					disptechnology.setDispTechnology(yjczjs);
//					accidentErmDispTechnologyDao.saveAccidentErmDispTechnology(disptechnology);
//				}
//			}
			
			// 是否删除
			accidentErmContactsDao.deleteERMContactsListByAccidentID(id);
			
			//应急通讯录   ermTxlDao                   ERM_EmergencyContactsEntity     accidentErmContactsDao   ermTxlDao
			List<ERM_EmergencyContactsEntity> txllist = ermTxlDao.findAllInfo();
			if(txllist.size()>0){
				for(ERM_EmergencyContactsEntity txl:txllist){
					EAD_Accident_ERM_Contacts contacts = new EAD_Accident_ERM_Contacts();
					contacts.setAccident(ead);
					contacts.setContacts(txl);
					accidentErmContactsDao.saveAccidentErmContacts(contacts);
				}
			}

		}
	}

	@Override
	public String findconsequenceRoute(Map<String, Object> map) {
		// 计算结束_事故后果页面_救援路线
		Map<String, Object> mapjson = new HashMap<String, Object>();
		
		EAD_AccidentEntity ead=eadYjjcDao.findById( Long.valueOf( map.get("id").toString() ) );
		if(ead!=null){
			switch(ead.getType()){//事故类型
				case "1"://池火灾
					List<EAD_Accident_PoolFire>  listPoolFire= eadYjjcAccidentPoolFire.getEADAccidentPoolFireListByAccidentID(ead.getID());
					mapjson.put("sw", listPoolFire.get(0).getPoolfire().getM10());
					mapjson.put("zs", listPoolFire.get(0).getPoolfire().getM10_1());
					mapjson.put("qs", listPoolFire.get(0).getPoolfire().getM10_2());
					mapjson.put("type", ead.getType());
					break;
					
				case "2"://喷射火
					List<EAD_Accident_JetFire>  listJetFire= eadYjjcAccidentJetFire.getEADAccidentJetFireListByAccidentID(ead.getID());
					mapjson.put("sw", listJetFire.get(0).getJetfire().getM10());
					mapjson.put("zs", listJetFire.get(0).getJetfire().getM10_1());
					mapjson.put("qs", listJetFire.get(0).getJetfire().getM10_2());
					mapjson.put("type", ead.getType());
					break;
					
		        case "3"://火球
					List<EAD_Accident_Fireball>  listFireball= eadYjjcAccidentFireball.getEADAccidentFireballListByAccidentID(ead.getID());
					mapjson.put("sw", listFireball.get(0).getFireball().getM6());
					mapjson.put("zs", listFireball.get(0).getFireball().getM6_1());
					mapjson.put("qs", listFireball.get(0).getFireball().getM6_2());
					mapjson.put("type", ead.getType());
					break;
					
		        case "4"://物理爆炸（压力容器爆炸）
					List<EAD_Accident_Physical>  listPhysical= eadYjjcAccidentPhysical.getEADAccidentPhysicalListByAccidentID(ead.getID());
					mapjson.put("sw", listPhysical.get(0).getPhysical().getM4());
					mapjson.put("zs", listPhysical.get(0).getPhysical().getM4_1());
					mapjson.put("qs", listPhysical.get(0).getPhysical().getM4_2());
					mapjson.put("type", ead.getType());
					break;
					
		        case "5"://化学爆炸
					List<EAD_Accident_Vce>  listVce= eadYjjcAccidentVce.getEADAccidentVceListByAccidentID(ead.getID());
					mapjson.put("sw", listVce.get(0).getVce().getM5() );
					mapjson.put("zs", listVce.get(0).getVce().getM5_1() );
					mapjson.put("qs", listVce.get(0).getVce().getM5_2() );
					mapjson.put("type", ead.getType() );
					break;
					
		        case "6"://压缩气体物理爆炸
					List<EAD_Accident_Gasphysical>  listGasphysical= eadYjjcAccidentGasphysical.getEADAccidentGasphysicalListByAccidentID(ead.getID());
					mapjson.put("sw", listGasphysical.get(0).getGasphysical().getM6() );
					mapjson.put("zs", listGasphysical.get(0).getGasphysical().getM6_1() );
					mapjson.put("qs", listGasphysical.get(0).getGasphysical().getM6_2() );
					mapjson.put("type", ead.getType());
					break;
					
		        case "7"://持续泄漏
					List<EAD_Accident_Leakage>  listLeakage= eadYjjcAccidentLeakage.getEADAccidentLeakageListByAccidentID(ead.getID());
					mapjson.put("lng1", listLeakage.get(0).getLeakage().getM18());
					mapjson.put("lat1", listLeakage.get(0).getLeakage().getM19());
					mapjson.put("lng2", listLeakage.get(0).getLeakage().getM20());
					mapjson.put("lat2", listLeakage.get(0).getLeakage().getM21());
					mapjson.put("lng3", listLeakage.get(0).getLeakage().getM22());
					mapjson.put("lat3", listLeakage.get(0).getLeakage().getM23());
					mapjson.put("type", ead.getType());
					break;
					
		        case "8"://瞬时泄漏
					List<EAD_Accident_Instantleakage>  listInstantleakage= eadYjjcAccidentInstantleakage.getEADAccidentInstantleakageListByAccidentID(ead.getID());
					List<Map<String, Object>> listinsjson=new ArrayList<Map<String,Object>>();
					for(EAD_Accident_Instantleakage istleak:listInstantleakage){
						Map<String, Object> mapinsleak = new HashMap<String, Object>();
						mapinsleak.put("lng",istleak.getInstantleakage().getM12() );
						mapinsleak.put("lat",istleak.getInstantleakage().getM13() );
						mapinsleak.put("r",istleak.getInstantleakage().getM14() );
						listinsjson.add(mapinsleak);
					}
					mapjson.put("data", listinsjson);
					mapjson.put("type", ead.getType());
					break;
					
				default:
					break;
			}
			
			mapjson.put("point_lng", ead.getLng());
			mapjson.put("point_lat", ead.getLat());
			mapjson.put("fx", ead.getTodayfx());
		}
		
		return JsonMapper.getInstance().toJson(mapjson);
	}

	@Override
	public String findconsequenceResPlaceMap(Map<String, Object> map) {
		Map<String, Object> mapjson = eadYjjcDao.findMapResPlace(map);
		EAD_AccidentEntity ead=eadYjjcDao.findById( Long.valueOf( map.get("consequenceid").toString() ) );
		if(ead!=null){
			mapjson.put( "yjjclng", ead.getLng() );
			mapjson.put( "yjjclat", ead.getLat() );
		}
		return JsonMapper.getInstance().toJson(mapjson);
	}

	@Override
	public Map<String, Object> findconsequenceResPlaceDataGrid(Map<String, Object> map){
		Map<String,Object> mapl=eadYjjcDao.dataGridResPlace(map);
		int getTotalCount=eadYjjcDao.getTotaldataGridResPlace(map);

		Map<String, Object> mapjson = new HashMap<String, Object>();
		mapjson.put("rows", mapl.get("list") );
		mapjson.put("total", getTotalCount );

		return mapjson;
	}

	@Override
	public String findconsequenceResTeamMap(Map<String, Object> map) {
		Map<String, Object> mapjson = eadYjjcDao.findMapResTeam(map);
		EAD_AccidentEntity ead=eadYjjcDao.findById( Long.valueOf( map.get("consequenceid").toString() ) );
		if(ead!=null){
			mapjson.put( "yjjclng", ead.getLng() );
			mapjson.put( "yjjclat", ead.getLat() );
		}
		return JsonMapper.getInstance().toJson(mapjson);
	}

	@Override
	public Map<String, Object> findconsequenceResTeamDataGrid(Map<String, Object> map) {
		Map<String,Object> mapl=eadYjjcDao.dataGridResTeam(map);
		int getTotalCount=eadYjjcDao.getTotaldataGridResTeam(map);

		Map<String, Object> mapjson = new HashMap<String, Object>();
		mapjson.put("rows", mapl.get("list") );
		mapjson.put("total", getTotalCount );

		return mapjson;
	}

	@Override
	public String findconsequenceResInstrumentMap(Map<String, Object> map) {
		Map<String, Object> mapjson = eadYjjcDao.findMapResInstrument(map);
		EAD_AccidentEntity ead=eadYjjcDao.findById( Long.valueOf( map.get("consequenceid").toString() ) );
		if(ead!=null){
			mapjson.put( "yjjclng", ead.getLng() );
			mapjson.put( "yjjclat", ead.getLat() );
		}
		return JsonMapper.getInstance().toJson(mapjson);
	}
	@Override
	public Map<String, Object> findconsequenceResInstrumentDataGrid(Map<String, Object> map) {
		Map<String,Object> mapl=eadYjjcDao.dataGridResInstrument(map);
		int getTotalCount=eadYjjcDao.getTotaldataGridResInstrument(map);

		Map<String, Object> mapjson = new HashMap<String, Object>();
		mapjson.put("rows", mapl.get("list") );
		mapjson.put("total", getTotalCount );
		return mapjson;
	}

	@Override
	public String findconsequenceMateMap(Map<String, Object> map) {
		Map<String, Object> mapjson = eadYjjcDao.findMapMate(map);
		EAD_AccidentEntity ead=eadYjjcDao.findById( Long.valueOf( map.get("consequenceid").toString() ) );
		if(ead!=null){
			mapjson.put( "yjjclng", ead.getLng() );
			mapjson.put( "yjjclat", ead.getLat() );
		}
		return JsonMapper.getInstance().toJson(mapjson);
	}
	@Override
	public Map<String, Object> findconsequenceMateDataGrid(Map<String, Object> map) {
		Map<String,Object> mapl=eadYjjcDao.dataGridMate(map);
		int getTotalCount=eadYjjcDao.getTotaldataGridMate(map);

		Map<String, Object> mapjson = new HashMap<String, Object>();
		mapjson.put("rows", mapl.get("list") );
		mapjson.put("total", getTotalCount );
		return mapjson;
	}

	@Override
	public String findconsequenceResExpertMap(Map<String, Object> map) {
		Map<String, Object> mapjson = eadYjjcDao.findMapResExpert(map);
		EAD_AccidentEntity ead=eadYjjcDao.findById( Long.valueOf( map.get("consequenceid").toString() ) );
		if(ead!=null){
			mapjson.put( "yjjclng", ead.getLng() );
			mapjson.put( "yjjclat", ead.getLat() );
		}
		return JsonMapper.getInstance().toJson(mapjson);
	}
	@Override
	public Map<String, Object> findconsequenceResExpertDataGrid(Map<String, Object> map) {
		Map<String,Object> mapl=eadYjjcDao.dataGridResExpert(map);
		int getTotalCount=eadYjjcDao.getTotaldataGridResExpert(map);

		Map<String, Object> mapjson = new HashMap<String, Object>();
		mapjson.put("rows", mapl.get("list") );
		mapjson.put("total", getTotalCount );
		return mapjson;
	}

	@Override
	public String findconsequenceHospitalMap(Map<String, Object> map) {
		Map<String, Object> mapjson = eadYjjcDao.findMapHospital(map);
		EAD_AccidentEntity ead=eadYjjcDao.findById( Long.valueOf( map.get("consequenceid").toString() ) );
		if(ead!=null){
			mapjson.put( "yjjclng", ead.getLng() );
			mapjson.put( "yjjclat", ead.getLat() );
		}
		return JsonMapper.getInstance().toJson(mapjson);
	}
	@Override
	public Map<String, Object> findconsequenceHospitalDataGrid(Map<String, Object> map) {
		Map<String,Object> mapl=eadYjjcDao.dataGridHospital(map);
		int getTotalCount=eadYjjcDao.getTotaldataGridHospital(map);

		Map<String, Object> mapjson = new HashMap<String, Object>();
		mapjson.put("rows", mapl.get("list") );
		mapjson.put("total", getTotalCount );
		return mapjson;
	}
	
	
	@Override
	public Map<String, Object> findconsequenceDispTechnologyDataGrid(Map<String, Object> map) {
		Map<String,Object> mapl=eadYjjcDao.dataGridDispTechnology(map);
		int getTotalCount=eadYjjcDao.getTotaldataGridDispTechnology(map);

		Map<String, Object> mapjson = new HashMap<String, Object>();
		mapjson.put("rows", mapl.get("list") );
		mapjson.put("total", getTotalCount );
		return mapjson;
	}
	
	@Override
	public Map<String, Object> findconsequenceMsdsDataGrid(Map<String, Object> map) {
		Map<String,Object> mapl=eadYjjcDao.dataGridMsds(map);
		int getTotalCount=eadYjjcDao.getTotaldataGridMsds(map);

		Map<String, Object> mapjson = new HashMap<String, Object>();
		mapjson.put("rows", mapl.get("list") );
		mapjson.put("total", getTotalCount );
		return mapjson;
	}
	
	@Override
	public Map<String, Object> findconsequenceContactsDataGrid(Map<String, Object> map) {
		Map<String,Object> mapl=eadYjjcDao.dataGridContacts(map);
		int getTotalCount=eadYjjcDao.getTotaldataGridContacts(map);

		Map<String, Object> mapjson = new HashMap<String, Object>();
		mapjson.put("rows", mapl.get("list") );
		mapjson.put("total", getTotalCount );
		return mapjson;
	}

	@Override
	public String exportWord(HttpServletRequest request, Map<String, Object> map) {
		 /** 文件名称，唯一字符串 */
		String fileurl="";
		//查询文书是否已经生成
		EAD_AccidentWordEntity eadword = eadYjjcWordDao.findByAccidentId(Long.valueOf( map.get("consequenceid").toString() ) );
		if(eadword!=null){
			fileurl = eadword.getFileurl();
		}else{
			fileurl = createWord(request,map);
		}
		Map<String, Object> mapjson=new HashMap<String, Object>();
		mapjson.put("data", fileurl);
	    return JsonMapper.getInstance().toJson(mapjson);
	}
	
    @SuppressWarnings({ "deprecation" })
	public String createWord(HttpServletRequest request, Map<String, Object> map) {
		 /** 文件名称，唯一字符串 */
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
        StringBuffer sb=new StringBuffer();
        sb.append(sdf1.format(new Date()));
        String filename=sb+".doc";
		String filePath=request.getRealPath("/")+"/download/word/";
		String fileurl="/download/word/"+filename;
		List<Map<String, Object>> mapdlist=getConsequenceData(request,map);
		if(mapdlist.size()>0){
			WordUtil.ireportWord(mapdlist.get(0), "yjfzjc.ftl", filePath, filename, request);
			
			EAD_AccidentEntity ead = BeanMapper.map(mapdlist.get(1),EAD_AccidentEntity.class);

			saveEADAccidentWord(ead,fileurl);
		};
		
		return fileurl;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getConsequenceData(HttpServletRequest request, Map<String, Object> map ){
		ServletContext context=request.getSession().getServletContext();
		String pageCtx = context.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pageCtx+"/"; 
		String realPath = context.getRealPath("/upload/consequence/");
		DecimalFormat df = new DecimalFormat("#.##");
		
		List<Map<String, Object>> mapAlllist = new ArrayList<Map<String,Object>>();
		if(map.get("consequenceid")!=null&&map.get("consequenceid")!="" ){
			EAD_AccidentEntity ead=eadYjjcDao.findById( Long.valueOf( map.get("consequenceid").toString() ) );
			if(ead!=null){
				Map<String, Object> mapdata = new HashMap<String, Object>();
				
				mapdata.put("yjjc_createtime", ead.getS1());
				if(StringUtils.isNotEmpty(ead.getQyname())){
					mapdata.put("yjjc_qyname",ead.getQyname());
				}
				switch(ead.getType()){//事故类型
					case "1"://池火灾
						List<EAD_Accident_PoolFire>  listPoolFire= eadYjjcAccidentPoolFire.getEADAccidentPoolFireListByAccidentID(ead.getID());
						mapdata.put("yjjc_sw", df.format( Double.valueOf( listPoolFire.get(0).getPoolfire().getM10()) ) );
						mapdata.put("yjjc_zs", df.format( Double.valueOf( listPoolFire.get(0).getPoolfire().getM10_1() ) ) );
						mapdata.put("yjjc_qs", df.format( Double.valueOf( listPoolFire.get(0).getPoolfire().getM10_2() ) ) );
						mapdata.put("yjjc_hxpname",listPoolFire.get(0).getPoolfire().getM1_1());
						mapdata.put("yjjc_type", "池火灾");
						break;
						
					case "2"://喷射火
						List<EAD_Accident_JetFire>  listJetFire= eadYjjcAccidentJetFire.getEADAccidentJetFireListByAccidentID(ead.getID());
						mapdata.put("yjjc_sw", df.format( Double.valueOf( listJetFire.get(0).getJetfire().getM10() ) ) );
						mapdata.put("yjjc_zs", df.format( Double.valueOf( listJetFire.get(0).getJetfire().getM10_1() ) ) );
						mapdata.put("yjjc_qs", df.format( Double.valueOf( listJetFire.get(0).getJetfire().getM10_2() ) ) );
						mapdata.put("yjjc_hxpname",listJetFire.get(0).getJetfire().getM1_1());
						mapdata.put("yjjc_type", "喷射火");
						break;
						
			        case "3"://火球
						List<EAD_Accident_Fireball>  listFireball= eadYjjcAccidentFireball.getEADAccidentFireballListByAccidentID(ead.getID());
						mapdata.put("yjjc_sw", df.format( Double.valueOf( listFireball.get(0).getFireball().getM6() ) ) );
						mapdata.put("yjjc_zs", df.format( Double.valueOf( listFireball.get(0).getFireball().getM6_1() ) ) );
						mapdata.put("yjjc_qs", df.format( Double.valueOf( listFireball.get(0).getFireball().getM6_2() ) ) );
						mapdata.put("yjjc_hxpname",listFireball.get(0).getFireball().getM1_1());
						mapdata.put("yjjc_type", "火球");
						break;
						
			        case "4"://物理爆炸（压力容器爆炸）
						List<EAD_Accident_Physical>  listPhysical= eadYjjcAccidentPhysical.getEADAccidentPhysicalListByAccidentID(ead.getID());
						mapdata.put("yjjc_sw", df.format( Double.valueOf( listPhysical.get(0).getPhysical().getM4() ) ) );
						mapdata.put("yjjc_zs", df.format( Double.valueOf( listPhysical.get(0).getPhysical().getM4_1() ) ) );
						mapdata.put("yjjc_qs", df.format( Double.valueOf( listPhysical.get(0).getPhysical().getM4_2() ) ) );
						mapdata.put("yjjc_hxpname","-");
						mapdata.put("yjjc_type", "物理爆炸（压力容器爆炸）");
						break;
						
			        case "5"://化学爆炸
						List<EAD_Accident_Vce>  listVce= eadYjjcAccidentVce.getEADAccidentVceListByAccidentID(ead.getID());
						mapdata.put("yjjc_sw", df.format( Double.valueOf( listVce.get(0).getVce().getM5() ) ) );
						mapdata.put("yjjc_zs", df.format( Double.valueOf( listVce.get(0).getVce().getM5_1() ) ) );
						mapdata.put("yjjc_qs", df.format( Double.valueOf( listVce.get(0).getVce().getM5_2() ) ) );
						mapdata.put("yjjc_hxpname",listVce.get(0).getVce().getM1_1());
						mapdata.put("yjjc_type", "化学爆炸" );
						break;
						
			        case "6"://压缩气体物理爆炸
						List<EAD_Accident_Gasphysical>  listGasphysical= eadYjjcAccidentGasphysical.getEADAccidentGasphysicalListByAccidentID(ead.getID());
						mapdata.put("yjjc_sw", df.format( Double.valueOf( listGasphysical.get(0).getGasphysical().getM6() ) ) );
						mapdata.put("yjjc_zs", df.format( Double.valueOf( listGasphysical.get(0).getGasphysical().getM6_1() ) ) );
						mapdata.put("yjjc_qs", df.format( Double.valueOf( listGasphysical.get(0).getGasphysical().getM6_2() ) ) );
						mapdata.put("yjjc_hxpname",listGasphysical.get(0).getGasphysical().getM1_1());
						mapdata.put("yjjc_type", "压缩气体物理爆炸");
						break;
						
			        case "7"://持续泄漏
						List<EAD_Accident_Leakage>  listLeakage= eadYjjcAccidentLeakage.getEADAccidentLeakageListByAccidentID(ead.getID());
						mapdata.put("yjjc_sw", "-" );
						mapdata.put("yjjc_zs", "-" );
						mapdata.put("yjjc_qs", "-" );
						mapdata.put("yjjc_hxpname",listLeakage.get(0).getLeakage().getM1_1());
						mapdata.put("yjjc_type", "持续泄漏");
						break;
						
			        case "8"://瞬时泄漏
						List<EAD_Accident_Instantleakage>  listInstantleakage= eadYjjcAccidentInstantleakage.getEADAccidentInstantleakageListByAccidentID(ead.getID());
						mapdata.put("yjjc_sw", "-" );
						mapdata.put("yjjc_zs", "-" );
						mapdata.put("yjjc_qs", "-" );
						mapdata.put("yjjc_hxpname",listInstantleakage.get(0).getInstantleakage().getM1_1());
						mapdata.put("yjjc_type", "瞬时泄漏");
						break;
						
					default:
						break;
				}
				mapdata.put("yjjc_lng",ead.getLng());
				mapdata.put("yjjc_lat",ead.getLat());
				
		    	SimpleDateFormat fileNameFormat = new SimpleDateFormat("yyyyMMddkkmmss_S");
		    	String filePath = "/upload/consequence/";
		    	String filedir=fileNameFormat.format(new Date());
				//应急队伍
				Map<String, Object> mapResTeam = eadYjjcDao.findMapResTeam(map);
				mapdata.put("yjdwlist",mapResTeam.get("data"));
				
				//应急装备
				Map<String, Object> mapResInstrument = eadYjjcDao.findMapResInstrument(map);
				mapdata.put("yjzblist",mapResInstrument.get("data"));

				//应急物资
				Map<String, Object> mapMate = eadYjjcDao.findMapMate(map);
				mapdata.put("yjwzlist",mapMate.get("data"));
				
				//应急避难场所
				Map<String, Object> mapResPlace = eadYjjcDao.findMapResPlace(map);
				mapdata.put("yjbncslist",mapResPlace.get("data"));
				
				//应急专家
				Map<String, Object> mapResExpert = eadYjjcDao.findMapResExpert(map);
				mapdata.put("yjzjlist",mapResExpert.get("data"));
				
				//应急医院
				Map<String, Object> mapHospital = eadYjjcDao.findMapHospital(map);
				mapdata.put("yjyylist",mapHospital.get("data"));
				
				//应急处置技术
				Map<String, Object> mapMsds = eadYjjcDao.findAllMsds(map);
				mapdata.put("yjczjslist",mapMsds.get("data"));
				
				//疏散距离
				List<TMESK_ProtectionDistanceEntity> list=sekbCshfhjlDao.findBy( "M2", ead.getMatterstr() );
				mapdata.put("yjczssjllist", list);
				
		    	
				String webcountname="count_"+map.get("consequenceid").toString()+".png";
				Map<String,Object> webmapcount = WebPagePicture.getAjaxCotnent(request, filePath, filedir, "rasterize.js "+basePath+"ead/yjjc/consequencepage/pagecount/"+map.get("consequenceid"), webcountname);
				
				String webjylxname="jylx_"+map.get("consequenceid").toString()+".png";
				Map<String,Object> webmapjylx = WebPagePicture.getAjaxCotnent(request, filePath, filedir, "rasterize.js "+basePath+"ead/yjjc/consequencepage/pagejylx/"+map.get("consequenceid"), webjylxname);
						    	
				String webbncsname="bncs_"+map.get("consequenceid").toString()+".png";
				Map<String,Object> webmapbncs = WebPagePicture.getAjaxCotnent(request, filePath, filedir, "rasterize.js "+basePath+"ead/yjjc/consequencepage/pagebncs/"+map.get("consequenceid"), webbncsname);
				
				String webyjdwname="yjdw_"+map.get("consequenceid").toString()+".png";
				Map<String,Object> webmapyjdw = WebPagePicture.getAjaxCotnent(request, filePath, filedir, "rasterize.js "+basePath+"ead/yjjc/consequencepage/pageyjdw/"+map.get("consequenceid"), webyjdwname);
				
				String webyjwzname="yjwz_"+map.get("consequenceid").toString()+".png";
				Map<String,Object> webmapyjwz = WebPagePicture.getAjaxCotnent(request, filePath, filedir, "rasterize.js "+basePath+"ead/yjjc/consequencepage/pageyjwz/"+map.get("consequenceid"), webyjwzname);
				
				String webyjyyname="yjyy_"+map.get("consequenceid").toString()+".png";
				Map<String,Object> webmapyjyy = WebPagePicture.getAjaxCotnent(request, filePath, filedir, "rasterize.js "+basePath+"ead/yjjc/consequencepage/pageyjyy/"+map.get("consequenceid"), webyjyyname);
				
				String webyjzbname="yjzb_"+map.get("consequenceid").toString()+".png";
				Map<String,Object> webmapyjzb = WebPagePicture.getAjaxCotnent(request, filePath, filedir, "rasterize.js "+basePath+"ead/yjjc/consequencepage/pageyjzb/"+map.get("consequenceid"), webyjzbname);				
				
				//截图
				mapdata.put("yjjc_countimage", getImageStr( realPath + "\\" + webmapcount.get("fileDirName") ) );
				mapdata.put("yjjc_jylximage", getImageStr( realPath + "\\" + webmapjylx.get("fileDirName") ) );
				mapdata.put("yjjc_yjdwimage", getImageStr( realPath + "\\" + webmapyjdw.get("fileDirName") ) );
				mapdata.put("yjjc_yjzbimage", getImageStr( realPath + "\\" + webmapyjzb.get("fileDirName") ) );
				mapdata.put("yjjc_yjwzimage", getImageStr( realPath + "\\" + webmapyjwz.get("fileDirName") ) );
				mapdata.put("yjjc_bncsimage", getImageStr( realPath + "\\" + webmapbncs.get("fileDirName") ) );
				mapdata.put("yjjc_yjyyimage", getImageStr( realPath + "\\" + webmapyjyy.get("fileDirName") ) );
				
				Map<String,Object> mapead = BeanMapper.map(ead,Map.class);
				
				mapAlllist.add(mapdata);
				mapAlllist.add(mapead);
				return mapAlllist;
			}
		}
		
		return null;
		
	}

	/**
	 * 保存EAD_AccidentWordEntity
	 * @param ead
	 * @param fileurl
	 */
	private void saveEADAccidentWord(EAD_AccidentEntity ead,String fileurl){
		EAD_AccidentWordEntity eadword =  new EAD_AccidentWordEntity();
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Timestamp t=DateUtils.getSysTimestamp();
		eadword.setS1(t);
		eadword.setS2(t);
		eadword.setS3(0);
		eadword.setAccidentid(ead.getID());
		eadword.setQyid(ead.getQyid());
		eadword.setQyname(ead.getQyname());
		eadword.setLat(ead.getLat());
		eadword.setLng(ead.getLng());
		eadword.setMatter(ead.getMatter());
		eadword.setType(ead.getType());
		eadword.setFileurl(fileurl);
		eadword.setID1(sessionuser.getId());
		eadword.setID2(sessionuser.getXzqy());
		
		eadYjjcWordDao.saveInfo(eadword);
	}

	private String getImageStr(String url) {
        String imgFile = url;
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

}
