package com.cczu.model.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IAcaGasphysicalDao;
import com.cczu.model.entity.ACA_GasphysicalEntity;
import com.cczu.model.service.IAcaGasphysicalService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

@Transactional(readOnly=true)
@Service("AcaGasphysicalService")
public class AcaGasphysicalServiceImpl implements IAcaGasphysicalService {
	@Resource
	private IAcaGasphysicalDao acaGasphysical;

	@Override
	public String countSave(ACA_GasphysicalEntity aca) throws Exception {
//		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
//		Timestamp t=DateUtils.getSysTimestamp();
//		aca.setS1(t);
//		aca.setS2(t);
//		aca.setS3(0);
//		aca.setID1(sessionuser.getId());
		return JsonMapper.getInstance().writeValueAsString(acaGasphysical.saveInfo(aca));
	}

	@Override
	public String jccountSave(ACA_GasphysicalEntity aca) throws Exception {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Timestamp t=DateUtils.getSysTimestamp();
		aca.setS1(t);
		aca.setS2(t);
		aca.setS3(0);
		aca.setID1(sessionuser.getId());
		return JsonMapper.getInstance().writeValueAsString(acaGasphysical.jcsaveInfo(aca));
	}
	
	@Override
	public List<ACA_GasphysicalEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ACA_GasphysicalEntity> findAllByUserId(long id1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ACA_GasphysicalEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> appGasphysical(String str1, String str2,String str3)
			throws Exception {
		// TODO Auto-generated method stub
		return acaGasphysical.appGasphysical(str1, str2, str3);
	}
}
