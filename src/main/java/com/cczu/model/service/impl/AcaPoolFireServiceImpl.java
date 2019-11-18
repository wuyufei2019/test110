package com.cczu.model.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IAcaPoolFireDao;
import com.cczu.model.entity.ACA_PoolFireEntity;
import com.cczu.model.service.IAcaPoolFireService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

@Transactional(readOnly=true)
@Service("AcaPoolFireService")
public class AcaPoolFireServiceImpl implements IAcaPoolFireService {
	@Resource
	private IAcaPoolFireDao acaPoolFire;

	@Override
	public String countSave(ACA_PoolFireEntity aca) throws Exception {
//		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
//		Timestamp t=DateUtils.getSysTimestamp();
//		aca.setS1(t);
//		aca.setS2(t);
//		aca.setS3(0);
//		aca.setID1(sessionuser.getId());
		return JsonMapper.getInstance().writeValueAsString(acaPoolFire.saveInfo(aca));
	}
	
	@Override
	public String jccountSave(ACA_PoolFireEntity aca) throws Exception {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Timestamp t=DateUtils.getSysTimestamp();
		aca.setS1(t);
		aca.setS2(t);
		aca.setS3(0);
		aca.setID1(sessionuser.getId());
		return JsonMapper.getInstance().writeValueAsString(acaPoolFire.jcsaveInfo(aca));
	}

	@Override
	public List<ACA_PoolFireEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ACA_PoolFireEntity> findAllByUserId(long id1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ACA_PoolFireEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> appPoolFire(String str1, String str2,
			String str3, String str4, String str5, String str6, String str7)
			throws Exception {
		// TODO Auto-generated method stub
		return acaPoolFire.appPoolFire(str1, str2, str3, str4, str5, str6, str7);
	}
}
