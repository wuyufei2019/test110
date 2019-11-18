package com.cczu.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cczu.model.service.IFmewWdytylService;

/**
 * @author jason
 *
 */
@Component("taskJob")  
public class TaskJob {  
	@Autowired
	private IFmewWdytylService fmewWdytylService;
	
//    @Scheduled(cron = "*/1 * * * * ?")  //每10秒执行一次
//    public void job1() {  
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒"); 
////        System.out.println("系统时间：" + dateFormat.format(new Date())+" 时运行"); 
//    }  
}  