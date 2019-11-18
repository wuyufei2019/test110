package com.cczu.model.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cczu.model.dao.YhpcCheckPlanDao;
import com.cczu.model.dao.YhpcCheckPlanPointDao;
import com.cczu.model.dao.YhpcCheckPlanTimeDao;
import com.cczu.model.dao.YhpcCheckPlanUserDao;
import com.cczu.model.entity.YHPC_CheckPlanEntity;
import com.cczu.model.entity.YHPC_CheckPlan_Point;
import com.cczu.model.entity.YHPC_CheckPlan_Time;
import com.cczu.model.entity.YHPC_CheckPlan_User;
import com.cczu.sys.system.entity.Message;
import com.cczu.sys.system.utils.MessageUtil;

/**
 * 巡检班次任务设置
 * @author zpc
 */
@Service("YhpcCheckPlanService")
public class YhpcCheckPlanService {

	@Resource
	private YhpcCheckPlanDao yhpcCheckPlanDao;
	@Resource
	private YhpcCheckPlanPointDao yhpcCheckPlanPointDao;
	@Resource
	private YhpcCheckPlanUserDao yhpcCheckPlanUserDao;
	@Resource
	private YhpcCheckPlanTimeDao yhpcCheckPlanTimeDao;
	
	/**
	 * 查询巡检班次任务list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<YHPC_CheckPlanEntity> list=yhpcCheckPlanDao.dataGrid(mapData);
		int getTotalCount=yhpcCheckPlanDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 添加巡检班次任务
	 * @param zfry
	 */
	public long addInfo(YHPC_CheckPlanEntity bcrw) {
		return yhpcCheckPlanDao.addInfo(bcrw);
	}

	/**
	 * 查询检查点list
	 * @param map
	 * @return
	 */
	public Map<String, Object> jcddataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=yhpcCheckPlanDao.jcddataGrid(mapData);
		int getTotalCount=yhpcCheckPlanDao.getjcdTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 添加巡检班次任务时间中间表
	 * @param zfry
	 */
	public void addxjbcsjInfo(YHPC_CheckPlan_Time bcrw) {
		yhpcCheckPlanTimeDao.save(bcrw);
	}
	
	/**
	 * 查询班次任务时间list
	 * @param id1
	 * @return
	 */
	public List<YHPC_CheckPlan_Time> rwsjList(Long id1) {
		return yhpcCheckPlanDao.rwsjList(id1);
	}
	
	/**
	 * 添加巡检班次人员中间表
	 * @param zfry
	 */
	public void addxjbcryInfo(YHPC_CheckPlan_User bcrw) {
		yhpcCheckPlanUserDao.save(bcrw);
	}
	
	/**
	 * 添加巡检班次检查点中间表
	 * @param zfry
	 */
	public void addxjbcjcdInfo(YHPC_CheckPlan_Point bcrw) {
		yhpcCheckPlanPointDao.save(bcrw);
	}
	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		yhpcCheckPlanDao.deleteInfo(id);
	}

	/**
	 * 根据id1删除时间中间表
	 * @param parseLong
	 */
	public void deletexjbcsjInfo(long id1) {
		yhpcCheckPlanDao.deletexjbcsjInfo(id1);
	}
	
	/**
	 * 根据id1删除人员中间表
	 * @param parseLong
	 */
	public void deletexjbcryInfo(long id1) {
		yhpcCheckPlanDao.deletexjbcryInfo(id1);
	}

	/**
	 * 根据id1删除检查点中间表
	 * @param parseLong
	 */
	public void deletexjbcjcdInfo(long id1) {
		yhpcCheckPlanDao.deletexjbcjcdInfo(id1);
	}
	
	/**
	 * 根据id查找巡检班次任务
	 * @param id
	 * @return
	 */
	public YHPC_CheckPlanEntity findById(Long id) {
		return yhpcCheckPlanDao.find(id);
	}
	
	public List<Map<String,Object>> getidname(long id1){
		return yhpcCheckPlanDao.getidname(id1);
	}
	
	public List<Map<String,Object>> getidname2(long id1){
		List<YHPC_CheckPlan_Point> list = yhpcCheckPlanDao.getlistbyid1(id1);
		List<Map<String,Object>> listall = new ArrayList<>();
		if(list.size()>0){
			for (YHPC_CheckPlan_Point y : list) {
				if((y.getCheckpointtype().trim()).equals("1")){
					Map<String,Object> map = yhpcCheckPlanDao.getidname2(y.getID());
					if(map!=null && map.get("id")!=null){
						listall.add(map);
					}
				}else if((y.getCheckpointtype().trim()).equals("2")){
					Map<String,Object> map = yhpcCheckPlanDao.getidname3(y.getID());
					if(map!=null && map.get("id")!=null){
						listall.add(map);
					}
				}
			}
		}
		return listall;
	}
	
	/**
	 * 修改
	 * @param zfry
	 */
	public void updateInfo(YHPC_CheckPlanEntity bcrw) {
		yhpcCheckPlanDao.save(bcrw);
	}

	public List<Map<String, Object>> findbclist(Long qyid) {
		return yhpcCheckPlanDao.findbclist(qyid);
	}

	/**
	 * 我的任务list
	 * @param map
	 * @return
	 */
	public Map<String, Object> myrwdataGrid(Map<String, Object> mapData) {
		List<YHPC_CheckPlanEntity> list=yhpcCheckPlanDao.myrwdataGrid(mapData);
		int getTotalCount=yhpcCheckPlanDao.getmyrwTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 查看企业安全员的班次 app
	 * @return
	 */
	public List<Map<String,Object>> qyxjbcForApp(Map<String, Object> mapData) {
		return yhpcCheckPlanDao.qyxjbcForApp(mapData);
	}

	/**
	 * 班次消息
	 */
	public void BcMsg(){
		Map<String, Object> map = new HashMap<>();
		//刚进来时设置默认时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String start = dateFormat.format(getThisWeekMonday(new Date()));
		String end = dateFormat.format(getNextWeekMonday(new Date()));
		try {
			//计算年检的应查次数乘积
			int nj=(int) Math.ceil(Integer.parseInt(end.substring(0,3))-Integer.parseInt(start.substring(0,3)))+1;
			//计算月检的应查次数乘积
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
			
			List<Map<String,String>> qyids = yhpcCheckPlanDao.qyidslist();
			for (Map<String,String> qyid : qyids) {
				map.put("qyid",qyid.get("qyid"));
				List<Map<String, Object>> msglist = yhpcCheckPlanDao.bctx(map);
				for (Map<String, Object> msg : msglist) {
					//发送msg消息
					Map<String,Object>  msgmap = new HashMap<String,Object>();
					msgmap.put(Message.MSGTARGET_H5,"weixin/enterprise/danger/checkplan/dangerbanci.jsp");
					msgmap.put(Message.MSGTARGET_PC,"yhpc/bcrw/index");
					MessageUtil.sendMsg(msg.get("userid").toString(), msg.get("userid").toString(), "你有班次未巡检，请立即巡检！",Message.MessageType.DXJ.getMsgType(),JSON.toJSONString(msgmap), "你有班次未巡检，请立即巡检！");
				}
			}
		} catch (Exception e) {
			System.out.println("班次消息提醒出错");
		}
	}
	
	public Date getThisWeekMonday(Date date) {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        // 获得当前日期是一个星期的第几天  
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);  
        if (1 == dayWeek) {  
            cal.add(Calendar.DAY_OF_MONTH, -1);  
        }  
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
        cal.setFirstDayOfWeek(Calendar.MONDAY);  
        // 获得当前日期是一个星期的第几天  
        int day = cal.get(Calendar.DAY_OF_WEEK);  
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值  
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);  
        return cal.getTime();  
    }  
  
    public Date getNextWeekMonday(Date date) {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(getThisWeekMonday(date));  
        cal.add(Calendar.DATE, 7);  
        return cal.getTime();  
    }  
}
