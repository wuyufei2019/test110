package com.cczu.model.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.impl.AqjgAjCheckRecordDaoImpl;
import com.cczu.model.entity.AQJD_AjCheckRecordEntity;
import com.cczu.model.service.IAqjgAjCheckRecordService;

/**
 * 安全文件发布service接口实现类
 * @author jason
 */

@Service("AqjgAjCheckRecordService")
public class AqjgAjCheckRecordServiceImpl implements IAqjgAjCheckRecordService{
	@Resource
	private AqjgAjCheckRecordDaoImpl aqjgajcheckrecorddao;

	@Override
	public void addAjRecordInfo(AQJD_AjCheckRecordEntity acre) {
		// TODO Auto-generated method stub
		aqjgajcheckrecorddao.addAjCheckInfo(acre);
		
	}

	@Override
	public AQJD_AjCheckRecordEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return aqjgajcheckrecorddao.findInfoById(id);
	}

	@Override
	public void updateInfo(AQJD_AjCheckRecordEntity acre) {
		// TODO Auto-generated method stub
		 aqjgajcheckrecorddao.updateAjRecordInfo(acre);
	}

	@Override
	public AQJD_AjCheckRecordEntity findInfoByQyId(long qyid) {
		// TODO Auto-generated method stub
		
		return aqjgajcheckrecorddao.findInfoByQyId(qyid);
	}

	@Override
	public int updateFjInfo(AQJD_AjCheckRecordEntity acre) {
		// TODO Auto-generated method stub
	   return aqjgajcheckrecorddao.updateAjFjRecordInfo(acre);
	}

}
