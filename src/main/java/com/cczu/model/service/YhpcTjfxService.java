package com.cczu.model.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cczu.model.dao.YhpcTjfxDao;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 隐患排查统计分析
 * @author zpc
 */
@Service("YhpcTjfxService")
public class YhpcTjfxService {

	@Resource
	private YhpcTjfxDao yhpcTjfxDao;
	
	/**
	 * 统计分析list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=yhpcTjfxDao.dataGrid(mapData);
		int getTotalCount=yhpcTjfxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 数据总览list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {
		List<Map<String,Object>> list=yhpcTjfxDao.sjzl(mapData);
		Map<String, Object> countmap=yhpcTjfxDao.tjcount(mapData);
		int getTotalCount=0;
		if(mapData.get("type").equals("1")){
			//企业总数
			getTotalCount=Integer.parseInt(countmap.get("qysum").toString());
		}
		if(mapData.get("type").equals("2")){
			//已安装企业数
			getTotalCount=Integer.parseInt(countmap.get("azsum").toString());
		}
		if(mapData.get("type").equals("3")){
			//本月已巡检企业数
			getTotalCount=Integer.parseInt(countmap.get("yxjsum").toString());
		}
		if(mapData.get("type").equals("4")){
			//本月未巡检企业数
			getTotalCount=Integer.parseInt(countmap.get("qysum").toString())-Integer.parseInt(countmap.get("yxjsum").toString());
		}
		if(mapData.get("type").equals("5")){
			//本月检查记录数
			getTotalCount=Integer.parseInt(countmap.get("jcsum").toString());
		}
		if(mapData.get("type").equals("6")){
			//本月检查隐患数
			getTotalCount=Integer.parseInt(countmap.get("jcyhsum").toString());
		}
		if(mapData.get("type").equals("7")){
			//本月未整改隐患数
			getTotalCount=Integer.parseInt(countmap.get("wzgsum").toString());
		}
		if(mapData.get("type").equals("8")){
			//本月已整改隐患数
			getTotalCount=Integer.parseInt(countmap.get("yzgsum").toString());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 导出 
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="企业巡检点统计分析表.xls";
		List<Map<String, Object>> list=yhpcTjfxDao.getExport(mapData);
		String[] title={"企业名称","正常巡检点数","异常巡检点数","未巡检点数","已巡检点数","应巡检点数"};  
		String[] keys={"qyname","count_a","count_b","count_c","count_d","count_e"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			 title = (mapData.get("coltext").toString()).split(",") ;
			 keys = (mapData.get("colval").toString()).split(",") ;
			}
		new ExportExcel(fileName, title, keys, list, response);
	}
	
	/**
	 * 企业总数
	 * @param mapData
	 * @return
	 */
	public int getQyCount(Map<String, Object> mapData) {
		int getTotalCount = yhpcTjfxDao.getQyCount(mapData);
		return getTotalCount;
	}
	
	/**
	 * 已安装企业数
	 * @param mapData
	 * @return
	 */
	public int getAzQyCount(Map<String, Object> mapData) {
		int getTotalCount = yhpcTjfxDao.getAzQyCount(mapData);
		return getTotalCount;
	}
	
	/**
	 * 本月已巡检企业数
	 * @param mapData
	 * @return
	 */
	public int getXjQyCount(Map<String, Object> mapData) {
		int getTotalCount = yhpcTjfxDao.getXjQyCount(mapData);
		return getTotalCount;
	}
	
	/**
	 * 本月检查记录数
	 * @param mapData
	 * @return
	 */
	public int getJcCount(Map<String, Object> mapData) {
		int getTotalCount = yhpcTjfxDao.getJcCount(mapData);
		return getTotalCount;
	}
	
	/**
	 * 本月检查隐患数
	 * @param mapData
	 * @return
	 */
	public int getYhCount(Map<String, Object> mapData) {
		int getTotalCount = yhpcTjfxDao.getYhCount(mapData);
		return getTotalCount;
	}
	
	/**
	 * 本月未整改隐患数
	 * @param mapData
	 * @return
	 */
	public int getWzgYhCount(Map<String, Object> mapData) {
		int getTotalCount = yhpcTjfxDao.getWzgYhCount(mapData);
		return getTotalCount;
	}
	
	/**
	 * 本月已整改隐患数
	 * @param mapData
	 * @return
	 */
	public int getYzgYhCount(Map<String, Object> mapData) {
		int getTotalCount = yhpcTjfxDao.getYzgYhCount(mapData);
		return getTotalCount;
	}
	
	/**
	 * 统计汇总
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> tjcount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return yhpcTjfxDao.tjcount(map);
	}
	
	/**
	 * 数据总览
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> sjzl(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return yhpcTjfxDao.tjcount(map);
	}
	
	/**
	 * 统计前三天隐患数目
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> getDayCount(Map<String, Object> mapData) {
		Map<String, Object> map=new HashMap<>();
		mapData.put("status", "");//隐患总数
		List<Integer> list=yhpcTjfxDao.getDayCount(mapData);
		map.put("yhzs", list);
		mapData.put("status", "1");//未整改数
		List<Integer> list1= yhpcTjfxDao.getDayCount(mapData);
		map.put("wzgs", list1);
		mapData.put("status", "2");//整改数
		List<Integer> list2= yhpcTjfxDao.getDayCount(mapData);
		map.put("yzgs", list2);
		return map;
	}
	
	/**
	 * 统计前三月隐患数目
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> getMonthCount(Map<String, Object> mapData) {
		Map<String, Object> map=new HashMap<>();
		mapData.put("status", "");//隐患总数
		List<Integer> list=yhpcTjfxDao.getMonthCount(mapData);
		map.put("yhzs", list);
		mapData.put("status", "1");//未整改数
		List<Integer> list1= yhpcTjfxDao.getMonthCount(mapData);
		map.put("wzgs", list1);
		mapData.put("status", "2");//整改数
		List<Integer> list2= yhpcTjfxDao.getMonthCount(mapData);
		map.put("yzgs", list2);
		return map;
	}
	
	/**
	 * 统计前三年隐患数目
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> getYearCount(Map<String, Object> mapData) {
		Map<String, Object> map=new HashMap<>();
		mapData.put("status", "");//隐患总数
		List<Integer> list=yhpcTjfxDao.getYearCount(mapData);
		map.put("yhzs", list);
		mapData.put("status", "1");//未整改数
		List<Integer> list1= yhpcTjfxDao.getYearCount(mapData);
		map.put("wzgs", list1);
		mapData.put("status", "2");//整改数
		List<Integer> list2= yhpcTjfxDao.getYearCount(mapData);
		map.put("yzgs", list2);
		return map;
	}
	
	/**
	 * 统计地区巡检比率
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getDqCount(Map<String, Object> mapData) {
		ShiroUser  sessionuser=UserUtil.getCurrentShiroUser();
		switch(sessionuser.getRoles().get(0).getRoleCode()){
		case "admin":
		case "superadmin":
		case "ajcountyadmin":
		case "ajcounty":
			return yhpcTjfxDao.getDqCount2();//显示各个镇
		case "ajtownadmin":
		case "ajtown":
			return yhpcTjfxDao.getDqCount(mapData);//显示各个村
		default:
			return yhpcTjfxDao.getDqCount(mapData);//显示各个村
		}
	}
	
	/**
	 * 统计地区整改完成比率
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getDqzgCount(Map<String, Object> mapData) {
		ShiroUser  sessionuser=UserUtil.getCurrentShiroUser();
		switch(sessionuser.getRoles().get(0).getRoleCode()){
		case "admin":
		case "superadmin":
		case "ajcountyadmin":
		case "ajcounty":
			return yhpcTjfxDao.getDqzgCount2();//显示各个镇
		case "ajtownadmin":
		case "ajtown":
			return yhpcTjfxDao.getDqzgCount(mapData);//显示各个村
		default:
			return yhpcTjfxDao.getDqzgCount(mapData);//显示各个村
		}
	}
	
	//集团公司首页数据
	public List<Map<String, Object>> getJtData(Map<String, Object> mapData) {
		return yhpcTjfxDao.getJtData(mapData);
	}
	
	/**
	 * 按时间统计巡检数和隐患数(安全巡查)
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> getCountByTime(Map<String, Object> mapData) {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		if(mapData.get("type").equals("1"))
			list=yhpcTjfxDao.getCountByDay(mapData);
		if(mapData.get("type").equals("2"))
			list=yhpcTjfxDao.getCountByMonth(mapData);
		if(mapData.get("type").equals("3"))
			list=yhpcTjfxDao.getCountByYear(mapData);
		List<String> timelist=new ArrayList<>();
		List<String> xjlist=new ArrayList<>();
		List<String> yhlist=new ArrayList<>();
		for(Map<String, Object> m:list){
			timelist.add(m.get("date").toString());
			xjlist.add(m.get("xjcount").toString());
			yhlist.add(m.get("yhcount").toString());
		}
		Map<String, Object> map=new HashMap<>();
		map.put("date", timelist);
		map.put("xjcount", xjlist);
		map.put("yhcount", yhlist);
		return map;
	}
	
	/**
	 * 按人员统计巡检数和隐患数(安全巡查)
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> getCountByUser(Map<String, Object> mapData) {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		list=yhpcTjfxDao.getCountByUser(mapData);
		List<String> namelist=new ArrayList<>();
		List<String> xjlist=new ArrayList<>();
		List<String> yhlist=new ArrayList<>();
		for(Map<String, Object> m:list){
			namelist.add(m.get("name").toString());
			xjlist.add(m.get("xjcount").toString());
			yhlist.add(m.get("yhcount").toString());
		}
		Map<String, Object> map=new HashMap<>();
		map.put("name", namelist);
		map.put("xjcount", xjlist);
		map.put("yhcount", yhlist);
		return map;
	}
	
	/**
	 * 按时间统计巡检数和隐患数(安全检查)
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> getCountByTime2(Map<String, Object> mapData) {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		if(mapData.get("type").equals("1")){
			if(mapData.get("starttime")==""&&mapData.get("endtime")==""){
				c.add(Calendar.MONTH, -1);
				mapData.put("endtime",df.format(new Date()));
				mapData.put("starttime",df.format(c.getTime()));
			}
			list=yhpcTjfxDao.getCountByDay2(mapData);
		}
		if(mapData.get("type").equals("2")){
			if(mapData.get("starttime")==""&&mapData.get("endtime")==""){
				c.add(Calendar.YEAR, -1);
				mapData.put("endtime",df.format(new Date()));
				mapData.put("starttime",df.format(c.getTime()));
			}
			list=yhpcTjfxDao.getCountByMonth2(mapData);
		}
		if(mapData.get("type").equals("3")){
			if(mapData.get("starttime")==""&&mapData.get("endtime")==""){
				c.add(Calendar.YEAR, -5);
				mapData.put("endtime",df.format(new Date()));
				mapData.put("starttime",df.format(c.getTime()));
			}
			list=yhpcTjfxDao.getCountByYear2(mapData);
		}
		List<String> timelist=new ArrayList<>();
		List<String> xjlist=new ArrayList<>();
		List<String> yhlist=new ArrayList<>();
		for(Map<String, Object> m:list){
			timelist.add(m.get("date").toString());
			xjlist.add(m.get("xjcount").toString());
			yhlist.add(m.get("yhcount").toString());
		}
		Map<String, Object> map=new HashMap<>();
		map.put("date", timelist);
		map.put("xjcount", xjlist);
		map.put("yhcount", yhlist);
		return map;
	}
	
	/**
	 * 按人员统计巡检数和隐患数(安全检查)
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> getCountByUser2(Map<String, Object> mapData) {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		list=yhpcTjfxDao.getCountByUser2(mapData);
		List<String> namelist=new ArrayList<>();
		List<String> xjlist=new ArrayList<>();
		List<String> yhlist=new ArrayList<>();
		for(Map<String, Object> m:list){
			namelist.add(m.get("name").toString());
			xjlist.add(m.get("xjcount").toString());
			yhlist.add(m.get("yhcount").toString());
		}
		Map<String, Object> map=new HashMap<>();
		map.put("name", namelist);
		map.put("xjcount", xjlist);
		map.put("yhcount", yhlist);
		return map;
	}

	/**
	 * 按时间统计巡检数和隐患数(随手拍)
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> getCountByTime3(Map<String, Object> mapData) {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		if(mapData.get("type").equals("1"))
			list=yhpcTjfxDao.getCountByDay3(mapData);
		if(mapData.get("type").equals("2"))
			list=yhpcTjfxDao.getCountByMonth3(mapData);
		if(mapData.get("type").equals("3"))
			list=yhpcTjfxDao.getCountByYear3(mapData);
		List<String> timelist=new ArrayList<>();
		List<String> yhlist=new ArrayList<>();
		for(Map<String, Object> m:list){
			timelist.add(m.get("date").toString());
			yhlist.add(m.get("yhcount").toString());
		}
		Map<String, Object> map=new HashMap<>();
		map.put("date", timelist);
		map.put("yhcount", yhlist);
		return map;
	}
	
	/**
	 * 按人员统计巡检数和隐患数(随手拍)
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> getCountByUser3(Map<String, Object> mapData) {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		list=yhpcTjfxDao.getCountByUser3(mapData);
		List<String> namelist=new ArrayList<>();
		List<String> yhlist=new ArrayList<>();
		for(Map<String, Object> m:list){
			namelist.add(m.get("name").toString());
			yhlist.add(m.get("yhcount").toString());
		}
		Map<String, Object> map=new HashMap<>();
		map.put("name", namelist);
		map.put("yhcount", yhlist);
		return map;
	}
	
	/**
	 * 统计重要危险源整改情况
	 * @param mapData
	 * @return
	 * @throws ParseException 
	 */
	public Map<String, Object> getDailyCheckCount(Map<String, Object> mapData) throws ParseException {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		list=yhpcTjfxDao.getDailyCheckCount(mapData);
		List<String> deptnameList=new ArrayList<>();
		List<String> currentMonCountList=new ArrayList<>();
		List<String> lastMonCountList=new ArrayList<>();
		List<String> solutedCountList=new ArrayList<>();
		List<String> noSolutCountList=new ArrayList<>();
		List<String> noExpireCountList=new ArrayList<>();
		for(Map<String, Object> m:list){
			deptnameList.add(m.get("deptname").toString());                 //部门名称
			currentMonCountList.add(m.get("current_mon_count").toString()); //本期发生数量
			lastMonCountList.add(m.get("last_mon_count").toString());       //上期未完成项
			solutedCountList.add(m.get("soluted_count").toString());        //整改
			noSolutCountList.add(m.get("no_solut_count").toString());       //未到期
			noExpireCountList.add(m.get("no_expire_count").toString());     //未完成
		}
		Map<String, Object> map=new HashMap<>();
		map.put("deptname", deptnameList);
		map.put("current_mon_count", currentMonCountList);
		map.put("last_mon_count", lastMonCountList);
		map.put("soluted_count", solutedCountList);
		map.put("no_solut_count", noSolutCountList);
		map.put("no_expire_count", noExpireCountList);
		return map;
	}
	
	/**
	 * 安全了隐患类别占比 统计
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> getCountByYhkind(Map<String, Object> mapData) {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		list=yhpcTjfxDao.getCountByYhkind(mapData);
		List<String> namelist=new ArrayList<>();
		List<Map<String, Object>> yhlist=new ArrayList<>();
		for(Map<String, Object> m:list){
			Map<String, Object> yhMap=new HashMap<>();
			namelist.add(m.get("name").toString());    //饼状图左侧的安全类别      
			String yhcount = m.get("yhcount").toString();
			//饼状图的data要求是[{name: xx, value: xx}]格式
			String name = (m.get("name").toString());
			yhMap.put("name", name);
			yhMap.put("value", yhcount);
			yhlist.add(yhMap);
		}
		Map<String, Object> map=new HashMap<>();
		map.put("name", namelist);
		map.put("yhcount", yhlist);
		return map;
	}
	
	/**
	 * 风险类别统计图
	 * @param mapData
	 * @return
	 * @throws ParseException 
	 */
	public Map<String, Object> getFxlbCount(Map<String, Object> mapData) throws ParseException {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		list=yhpcTjfxDao.getCountByFxlb(mapData);
		List<String> nameList=new ArrayList<>();
		List<String> redCountList=new ArrayList<>();
		List<String> orangeCountList=new ArrayList<>();
		List<String> yellowCountList=new ArrayList<>();
		List<String> buleCountList=new ArrayList<>();
		List<String> m2SumCountList=new ArrayList<>();
		for(Map<String, Object> m:list){
			nameList.add(m.get("name").toString());                 //风险类别名称
			redCountList.add(m.get("red_count").toString());        //红色的数量
			orangeCountList.add(m.get("orange_count").toString());  //橙色的数量
			yellowCountList.add(m.get("yellow_count").toString());  //黄色的数量
			buleCountList.add(m.get("blue_count").toString());      //蓝色的数量
			m2SumCountList.add(m.get("m2_sum_count").toString());   //总的数量
		}
		Map<String, Object> map=new HashMap<>();
		map.put("name", nameList);
		map.put("red_count", redCountList);
		map.put("orange_count", orangeCountList);
		map.put("yellow_count", yellowCountList);
		map.put("blue_count", buleCountList);
		map.put("m2_sum_count", m2SumCountList);
		return map;
	}
	
}

