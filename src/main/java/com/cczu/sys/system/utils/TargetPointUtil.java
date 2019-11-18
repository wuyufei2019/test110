package com.cczu.sys.system.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cczu.model.mbgl.entity.Target_Points;
import com.cczu.model.mbgl.service.TargetPointService;
import com.cczu.sys.comm.utils.DateUtils;

/**
 * 积分添加工具类
 * @author XY
 * 
 */
@Component
public class TargetPointUtil {

	private static TargetPointService targetPointService;

	@Autowired
	public void setTargetPointService(TargetPointService targetPointService) {
		TargetPointUtil.targetPointService = targetPointService;
	}

	public static void sendTp(long ID1,long ID2,String M1,String M3) {
		Target_Points tp = new Target_Points(ID1,ID2,M1,M3);
		tp.setS1(DateUtils.getSystemTime());
		tp.setS2(DateUtils.getSystemTime());
		tp.setM4(DateUtils.getSystemTime());
		tp.setS3(0);
		targetPointService.addInfo(tp);
	}
}
