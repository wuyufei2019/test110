package com.cczu.model.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IAcaLeakageDao;
import com.cczu.model.entity.ACA_LeakageEntity;
import com.cczu.model.service.IAcaLeakageService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

@Transactional(readOnly=true)
@Service("AcaLeakageService")
public class AcaLeakageServiceImpl implements IAcaLeakageService {
	@Resource
	private IAcaLeakageDao acaLeakage;

	@Override
	public String countSave(ACA_LeakageEntity aca) throws Exception {
//		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
//		Timestamp t=DateUtils.getSysTimestamp();
//		aca.setS1(t);
//		aca.setS2(t);
//		aca.setS3(0);
//		aca.setID1(sessionuser.getId());
		return JsonMapper.getInstance().writeValueAsString(acaLeakage.saveInfo(aca));
	}
	
	@Override
	public String jccountSave(ACA_LeakageEntity aca) throws Exception {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Timestamp t=DateUtils.getSysTimestamp();
		aca.setS1(t);
		aca.setS2(t);
		aca.setS3(0);
		aca.setID1(sessionuser.getId());
		return JsonMapper.getInstance().writeValueAsString(acaLeakage.jcsaveInfo(aca));
	}

	@Override
	public List<ACA_LeakageEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ACA_LeakageEntity> findAllByUserId(long id1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ACA_LeakageEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> saveInfo(ACA_LeakageEntity aca) throws Exception {
		// TODO Auto-generated method stub
		return acaLeakage.savesInfo(aca);
	}
}
