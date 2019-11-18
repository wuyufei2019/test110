package com.cczu.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cczu.model.service.IFmewWdytylService;
import com.cczu.model.service.IMonitorGasDataService;
import com.cczu.model.service.IMonitorGwgyDataService;
import com.cczu.model.service.IMonitorTankDataService;
import com.cczu.sys.comm.utils.DateUtils;

@Component("fmewYwTaskJob")  
public class FmewYwTaskJob {

	
	@Autowired
	private IFmewWdytylService fmewWdytylService;
	@Autowired
	private IMonitorGasDataService monitorGasDataService;
	@Autowired
	private IMonitorGwgyDataService monitorGwgyDataService;
	@Autowired
	private IMonitorTankDataService monitorTankDataService;	
	
	  		
//	@Scheduled(cron = "*/30 * * * * ?") //每30秒执行一次
	@Scheduled(cron = "0 0/3 * * * ?")  //每3分钟执行一次
    public void job1() {  
//		System.out.println(DateUtils.getDate()+": 检测报警数据！！！！");
//		monitorGasDataService.addbj();
//		monitorGwgyDataService.addbj();
//		monitorTankDataService.addbj();
    }   

}
