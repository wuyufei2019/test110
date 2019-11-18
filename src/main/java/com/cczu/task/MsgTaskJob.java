package com.cczu.task;

import com.cczu.model.service.YhpcXjjdService;
import com.cczu.sys.system.service.ShiroRealm;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cczu.model.service.IMsgService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jason
 *
 */
@Component("msgTaskJob")  
public class MsgTaskJob {

	
	@Autowired
	private IMsgService msgService;
	@Autowired
	private YhpcXjjdService yhpcXjjdService;

//	@Scheduled(cron = "*/5 * * * * ?")  //每5秒执行一次

	/** 每天0点执行一次，定时插入巡检监督考核信息
	 *
	 */
	@Scheduled(cron = "0 0 0 * * ?")
    public void job1() {
		Map<String, Object> map = new HashMap<>();
		//刚进来时设置默认时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String end=dateFormat.format(date);//默认结束时间
		String start=end.substring(0,4)+"-01-01";//默认开始时间

		//计算年检的应查次数乘积
		int nj=(int) Math.ceil(Integer.parseInt(end.substring(0,3))-Integer.parseInt(start.substring(0,3)))+1;
		//计算月检的应查次数乘积
		try {
			Date beginDate = dateFormat.parse(start);
			Date endDate= dateFormat.parse(end);
			long day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);//相差天数
			int yj=(int)(day/31)+1;
			//计算周检的应查次数乘积
			int zj=(int)(day/7)+1;
			//计算日检的应查次数乘积
			int rj=(int) day;
			map.put("nj",nj);
			map.put("yj",yj);
			map.put("zj",zj);
			map.put("rj",rj);
			map.put("start",start);
			map.put("end",end);
			yhpcXjjdService.inserttimekh(map);
		}catch (ParseException e) {

		}
    }

}
