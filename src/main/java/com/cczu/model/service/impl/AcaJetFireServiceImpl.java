package com.cczu.model.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IAcaJetFireDao;
import com.cczu.model.entity.ACA_JetFireEntity;
import com.cczu.model.service.IAcaJetFireService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

@Transactional(readOnly=true)
@Service("AcaJetFireService")
public class AcaJetFireServiceImpl implements IAcaJetFireService {
	@Resource
	private IAcaJetFireDao acaJetFire;

	@Override
	public String countSave(ACA_JetFireEntity aca) throws Exception {
//		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
//		Timestamp t=DateUtils.getSysTimestamp();
//		aca.setS1(t);
//		aca.setS2(t);
//		aca.setS3(0);
//		aca.setID1(sessionuser.getId());
//		System.out.println(JsonMapper.getInstance().writeValueAsString(acaJetFire.saveInfo(aca)));
		return JsonMapper.getInstance().writeValueAsString(acaJetFire.saveInfo(aca));
	}
	
	@Override
	public String jccountSave(ACA_JetFireEntity aca) throws Exception {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Timestamp t=DateUtils.getSysTimestamp();
		aca.setS1(t);
		aca.setS2(t);
		aca.setS3(0);
		aca.setID1(sessionuser.getId());
		return JsonMapper.getInstance().writeValueAsString(acaJetFire.jcsaveInfo(aca));
	}

	@Override
	public List<ACA_JetFireEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ACA_JetFireEntity> findAllByUserId(long id1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ACA_JetFireEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> appJetFire(String str1, String str2,
			String str3, String str4, String str5) throws Exception {
		// TODO Auto-generated method stub
		return acaJetFire.appJetFire(str1, str2, str3, str4, str5);
	}
}
