package com.cczu.model.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IAcaVceDao;
import com.cczu.model.entity.ACA_VceEntity;
import com.cczu.model.service.IAcaVceService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

@Transactional(readOnly=true)
@Service("AcaVceService")
public class AcaVceServiceImpl implements IAcaVceService {
	@Resource
	private IAcaVceDao acaVce;

	@Override
	public String countSave(ACA_VceEntity aca) throws Exception {
//		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
//		Timestamp t=DateUtils.getSysTimestamp();
//		aca.setS1(t);
//		aca.setS2(t);
//		aca.setS3(0);
//		aca.setID1(sessionuser.getId());
		return JsonMapper.getInstance().writeValueAsString(acaVce.saveInfo(aca));
	}
	
	@Override
	public String jccountSave(ACA_VceEntity aca) throws Exception {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Timestamp t=DateUtils.getSysTimestamp();
		aca.setS1(t);
		aca.setS2(t);
		aca.setS3(0);
		aca.setID1(sessionuser.getId());
		return JsonMapper.getInstance().writeValueAsString(acaVce.jcsaveInfo(aca));
	}

	@Override
	public List<ACA_VceEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ACA_VceEntity> findAllByUserId(long id1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ACA_VceEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> appVce(String str1, String str2)
			throws Exception {
		// TODO Auto-generated method stub
		return acaVce.appVce(str1, str2);
	}
}
