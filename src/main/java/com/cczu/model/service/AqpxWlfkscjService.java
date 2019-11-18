package com.cczu.model.service;

import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.AqpxWlfkscjDao;
import com.cczu.model.entity.AQPX_ExamresultsEntity;
import com.cczu.model.entity.AQPX_WlfExamResultsEntity;
import com.cczu.sys.comm.utils.DateUtils;

/**
 * @description 安全培训外来方考试成绩Service
 * @author jason
 * @date 2018年1月27日
 */
@Transactional(readOnly=true)
@Service("AqpxWlfkscjService")
public class AqpxWlfkscjService {
	@Resource
	private AqpxWlfkscjDao aqpxWlfkscjDao;
	 
	public void addInfo(AQPX_ExamresultsEntity kscj,HttpServletRequest request) {
		HttpSession session=request.getSession();
		AQPX_WlfExamResultsEntity wlfkscj =new AQPX_WlfExamResultsEntity();
		Timestamp t = DateUtils.getSystemTime();
		wlfkscj.setS1(t);
		wlfkscj.setS2(t);
		wlfkscj.setID1(kscj.getID1());
		wlfkscj.setID3(kscj.getID3());
		wlfkscj.setID4(kscj.getID4());
		wlfkscj.setH(kscj.getH());
		wlfkscj.setM1(kscj.getM1());
		wlfkscj.setM2(kscj.getM2());
		wlfkscj.setM3(kscj.getM3());	
		wlfkscj.setM4((String)session.getAttribute("unit"));	
		wlfkscj.setM5((String)session.getAttribute("idcard"));	
		aqpxWlfkscjDao.save(wlfkscj);
	}
	 
}
