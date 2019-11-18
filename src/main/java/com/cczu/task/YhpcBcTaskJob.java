package com.cczu.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cczu.model.service.YhpcCheckPlanService;

@Component("yhpcBcTaskJob")  
public class YhpcBcTaskJob {

	
	@Autowired
	private YhpcCheckPlanService yhpcCheckPlanService;
	  		
	@Scheduled(cron = "0 0 5 ? * MON")  //每个星期一早上5点
    public void job1() {  
		yhpcCheckPlanService.BcMsg();
    }   

}
