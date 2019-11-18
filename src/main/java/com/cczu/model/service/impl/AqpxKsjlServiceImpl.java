package com.cczu.model.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IAqpxKsjlDao;
import com.cczu.model.entity.AQPX_TestpaperEntity;
import com.cczu.model.service.IAqpxKsjlService;

@Transactional(readOnly=true)
@Service("AqpxKsjlService")
public class AqpxKsjlServiceImpl implements IAqpxKsjlService {
	
	@Resource
	private IAqpxKsjlDao aqpxksjldao;

	@Override
	public void addInfo(AQPX_TestpaperEntity at) {
		// TODO Auto-generated method stub
		aqpxksjldao.addInfo(at);
	}

	@Override
	public List<AQPX_TestpaperEntity> getlist(Long ygid, String bs) {
		// TODO Auto-generated method stub
		return aqpxksjldao.getlist(ygid, bs);
	}

	@Override
	public void updateInfo(AQPX_TestpaperEntity at) {
		// TODO Auto-generated method stub
		aqpxksjldao.updateInfo(at);
	}

	@Override
	public List<AQPX_TestpaperEntity> getall(Long ygid) {
		// TODO Auto-generated method stub
		return aqpxksjldao.getall(ygid);
	}

	@Override
	public List<AQPX_TestpaperEntity> getkcsj(Long ygid, Long kcid) {
		// TODO Auto-generated method stub
		return aqpxksjldao.getkcsj(ygid, kcid);
	}

	@Override
	public List<Map<String, Object>> getsjxx(String bs) {
		// TODO Auto-generated method stub
		return aqpxksjldao.getsjxx(bs);
	}

    @Override
    public List<Map<String, Object>> getctxx(String bs) {
        return aqpxksjldao.getctxx(bs);
    }


}
