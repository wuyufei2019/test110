package com.cczu.model.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cczu.model.dao.IEadYjjcAccidentWordDao;
import com.cczu.model.entity.EAD_AccidentWordEntity;
import com.cczu.model.service.IEadJclsjlService;

@Service("IEadJclsjlService")
public class EadJclsjlServiceImpl implements IEadJclsjlService {
	
	@Autowired
	private IEadYjjcAccidentWordDao eadYjjcWordDao;

	@Override
	public Map<String, Object> findconsequenceResTeamDataGrid(Map<String, Object> map) {
		List<EAD_AccidentWordEntity> list= eadYjjcWordDao.dataGridAccidentWord(map);
		int getTotalCount=eadYjjcWordDao.getTotaldataGridAccidentWord(map);
				
		Map<String, Object> mapjson = new HashMap<String, Object>();
		mapjson.put("rows", list );
		mapjson.put("total", getTotalCount );
		return mapjson;
	}

	@Override
	public void delete(Long id) {
		eadYjjcWordDao.deleteInfo(id);
	}
	
	

}
