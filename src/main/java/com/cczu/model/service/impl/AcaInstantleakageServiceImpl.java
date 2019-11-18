package com.cczu.model.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IAcaInstantleakageDao;
import com.cczu.model.entity.ACA_InstantleakageEntity;
import com.cczu.model.service.IAcaInstantleakageService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

@Transactional(readOnly=true)
@Service("AcaInstantleakageService")
public class AcaInstantleakageServiceImpl implements IAcaInstantleakageService {
	@Resource
	private IAcaInstantleakageDao acaInstantleakage;

	@Override
	public String countSave(ACA_InstantleakageEntity aca) throws Exception {
//		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
//		Timestamp t=DateUtils.getSysTimestamp();
//		aca.setS1(t);
//		aca.setS2(t);
//		aca.setS3(0);
//		aca.setID1(sessionuser.getId());
		return JsonMapper.getInstance().writeValueAsString(acaInstantleakage.saveInfo(aca));
	}
	
	@Override
	public String jccountSave(ACA_InstantleakageEntity aca) throws Exception {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Timestamp t=DateUtils.getSysTimestamp();
		aca.setS1(t);
		aca.setS2(t);
		aca.setS3(0);
		aca.setID1(sessionuser.getId());
		return JsonMapper.getInstance().writeValueAsString(acaInstantleakage.jcsaveInfo(aca));
	}

	@Override
	public List<ACA_InstantleakageEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ACA_InstantleakageEntity> findAllByUserId(long id1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ACA_InstantleakageEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> appInstantleakage(String stra1, String stra2,
			String stra3, String stra4, String stra5, String stra6, String stra7)
			throws Exception {
		// TODO Auto-generated method stub
		return acaInstantleakage.appInstantleakage(stra1, stra2, stra3, stra4, stra5, stra6, stra7);
	}
}
