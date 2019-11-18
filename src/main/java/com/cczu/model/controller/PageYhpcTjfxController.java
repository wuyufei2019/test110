package com.cczu.model.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.YhpcTjfxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.StringUtils;

/**
 * 巡检记录
 * @author zpc
 * @date 2017/08/24
 */
@Controller
@RequestMapping("yhpc/tjfx")
public class PageYhpcTjfxController extends BaseController{

	@Autowired
	private YhpcTjfxService yhpcTjfxService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser user = UserUtil.getCurrentShiroUser();
		if("0".equals(user.getUsertype())||"9".equals(user.getUsertype())){
			return "yhpc/tjfx/index";
		}else{
			return "../error/403";
		}
	}
	/**
	 * 默认页面
	 */
	@RequestMapping(value="aqjc")
	public String Aqjcindex(Model model) {
		ShiroUser user = UserUtil.getCurrentShiroUser();
		if("1".equals(user.getUsertype())){
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(user.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1){//判断是否为集团公司
					model.addAttribute("qytype", "bloc");//集团标识
				}
			}
			return "yhpc/tjfx/aqjcindex";
		}else{
			return "../error/403";
		}
	}
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="aqxc")
	public String Aqxcindex(Model model) {
		ShiroUser user = UserUtil.getCurrentShiroUser();
		if("1".equals(user.getUsertype())){
			return "yhpc/tjfx/aqxcindex";
		}else{
			return "../error/403";
		}
	}
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="ssp")
	public String Sspindex(Model model) {
		ShiroUser user = UserUtil.getCurrentShiroUser();
		if("1".equals(user.getUsertype())){
			return "yhpc/tjfx/ssptj";
		}else{
			return "../error/403";
		}
	}
	
	
	/**
	 * 子集团名称下拉json数据
	 */
	@RequestMapping(value="qynamejson")
	@ResponseBody
	public String getQyname(HttpServletRequest request) {
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(UserUtil.getCurrentShiroUser().getQyid());
		Map<String, Object> map = new HashMap<>();
		map.put("fid", be.getID());
		//根据fid 获取子集团信息集合
		List<BIS_EnterpriseEntity> list = bisQyjbxxService.dataListE(map);
		List<Map<String, Object>> qynameList = new ArrayList<>();
		map.clear();
		for (BIS_EnterpriseEntity bis : list) {
			map = new HashMap<>();
			map.put("qyid", bis.getID());
			map.put("qyname", bis.getM1());
			qynameList.add(map);
		}
		return JsonMapper.getInstance().toJson(qynameList);
	}
	/**
	 * 统计汇总页面跳转
	 */
	@RequestMapping(value="tjhz")
	public String tjhzIndex(Model model) {
			Map<String, Object> map = getAuthorityMap();
			Map<String, Object> map2 =yhpcTjfxService.tjcount(map);
			//企业总数
			int getQyCount=Integer.parseInt(map2.get("qysum").toString());
			//已安装企业数
			int getAzQyCount=Integer.parseInt(map2.get("azsum").toString());
			//本月已巡检企业数
			int getXjQyCount=Integer.parseInt(map2.get("yxjsum").toString());
			//本月检查记录数
			int getJcCount=Integer.parseInt(map2.get("jcsum").toString());
			//本月检查隐患数
			int getYhCount=Integer.parseInt(map2.get("jcyhsum").toString());
			//本月未整改隐患数
			int getWzgYhCount=Integer.parseInt(map2.get("wzgsum").toString());
			//本月已整改隐患数
			int getYzgYhCount=Integer.parseInt(map2.get("yzgsum").toString());
			model.addAttribute("qycount", getQyCount);
			model.addAttribute("azqycount", getAzQyCount);
			model.addAttribute("xjqycount", getXjQyCount);
			model.addAttribute("wxjqycount", getQyCount-getXjQyCount);
			model.addAttribute("jccount", getJcCount);
			model.addAttribute("yhcount", getYhCount);
			model.addAttribute("wzgcount", getWzgYhCount);
			model.addAttribute("yzgcount", getYzgYhCount);
			return "yhpc/tjfx/tjhzindex"; 
	}

	/**
	 * 地区巡检比率
	 */
	@RequestMapping(value="dqxjbl")
	@ResponseBody
	public String dqbl(HttpServletRequest request) {
		Map<String, Object> map = getAuthorityMap();
		if(request.getParameter("flag").equals("xjbl")){
			return JsonMapper.getInstance().toJson(yhpcTjfxService.getDqCount(map));
		}else {
			return JsonMapper.getInstance().toJson(yhpcTjfxService.getDqzgCount(map));
		}
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="daycount")
	@ResponseBody
	public String getDayCount(HttpServletRequest request) {
		Map<String, Object> map = getAuthorityMap();
		if(request.getParameter("flag").equals("day")){
			return JsonMapper.getInstance().toJson(yhpcTjfxService.getDayCount(map));
		}
		else if(request.getParameter("flag").equals("month")){
			return JsonMapper.getInstance().toJson(yhpcTjfxService.getMonthCount(map));
		}
		else{
			return JsonMapper.getInstance().toJson(yhpcTjfxService.getYearCount(map));
		}
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.putAll(getAuthorityMap());
		map.put("qyname",request.getParameter("yhpc_tjfx_qyname"));
		
		//设置默认时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String end=dateFormat.format(date);//默认结束时间
		String start=end.substring(0,7)+"-01";//默认开始时间
		String start2 = request.getParameter("yhpc_tjfx_starttime");//页面搜索条件（开始时间）
		String end2 = request.getParameter("yhpc_tjfx_finishtime");//页面搜索条件（结束时间）
		if(!StringUtils.isBlank(start2)){
			start=start2;
		}
		if(!StringUtils.isBlank(end2)){
			end=end2;
		}
		map.put("starttime", start);//巡检开始时间
		map.put("finishtime", end);//巡检结束时间
		return yhpcTjfxService.dataGrid(map);
	}

	/**
	 * 数据总览跳转
	 * @param model
	 */
	@RequestMapping(value = "sjzl" , method = RequestMethod.GET)
	public String create(Model model,HttpServletRequest request) {
		model.addAttribute("type", request.getParameter("type"));
		return "yhpc/tjfx/index2";
	}
	
	/**
	 * 数据总览list页面
	 */
	@RequestMapping(value="list2")
	@ResponseBody
	public Map<String, Object> getData2(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("0")){//安监局
			map.put("xzqy",sessionuser.getXzqy());
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
				map.put("jglx",sessionuser.getUserroleflg());
		}
		map.put("type", request.getParameter("type"));
		return yhpcTjfxService.dataGrid2(map);
	}
	
	/**
	 * 显示所有列
	 * 
	 * @param id,model
	 */
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","yhpc/tjfx/export");
		return "common/formexcel";
	}
	
	/**
	 * 导出Excel
	 * 
	 * @param request
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public void getExcel(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = getAuthorityMap();
		map.put("qyname",request.getParameter("yhpc_tjfx_qyname"));
		
		//设置默认时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String end=dateFormat.format(date);//默认结束时间
		String start=end.substring(0,7)+"-01";//默认开始时间
		String start2 = request.getParameter("yhpc_tjfx_starttime");//页面搜索条件（开始时间）
		String end2 = request.getParameter("yhpc_tjfx_finishtime");//页面搜索条件（结束时间）
		if(!StringUtils.isBlank(start2)){
			start=start2;
		}
		if(!StringUtils.isBlank(end2)){
			end=end2;
		}
		map.put("starttime", start);//巡检开始时间
		map.put("finishtime", end);//巡检结束时间
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		yhpcTjfxService.exportExcel(response, map);
	}
	
	/**
	 * 安全巡查统计分析list页面(时间统计)
	 */
	@RequestMapping(value="aqxctj")
	@ResponseBody
	public String aqxctj(HttpServletRequest request) {
		Map<String, Object> map = getAuthorityMap();
		map.put("starttime", request.getParameter("starttime"));
		map.put("endtime", request.getParameter("endtime"));
		map.put("type", request.getParameter("flag"));
		return JsonMapper.getInstance().toJson(yhpcTjfxService.getCountByTime(map));
	}
	
	/**
	 * 安全巡查统计分析list页面(人员统计)
	 */
	@RequestMapping(value="aqxctj2")
	@ResponseBody
	public String aqxctj2(HttpServletRequest request) {
		Map<String, Object> map = getAuthorityMap();
		map.put("starttime", request.getParameter("starttime"));
		map.put("endtime", request.getParameter("endtime"));
		return JsonMapper.getInstance().toJson(yhpcTjfxService.getCountByUser(map));
	}
	
	/**
	 * 安全检查统计分析list页面(时间统计)
	 */
	@RequestMapping(value="aqjctj")
	@ResponseBody
	public String aqjctj(HttpServletRequest request) {
		Map<String, Object> map = getAuthorityMap();
		map = getIdMap(map, request);
		map.put("starttime", request.getParameter("starttime"));
		map.put("endtime", request.getParameter("endtime"));
		map.put("type", request.getParameter("flag"));
		return JsonMapper.getInstance().toJson(yhpcTjfxService.getCountByTime2(map));
	}
	
	/**
	 * 安全检查统计分析list页面(人员统计)
	 */
	@RequestMapping(value="aqjctj2")
	@ResponseBody
	public String aqjctj2(HttpServletRequest request) {
		Map<String, Object> map = getAuthorityMap();
		map = getIdMap(map, request);
		map.put("starttime", request.getParameter("starttime"));
		map.put("endtime", request.getParameter("endtime"));
		return JsonMapper.getInstance().toJson(yhpcTjfxService.getCountByUser2(map));
	}

	/**
	 * 随手拍统计分析list页面(时间统计)
	 */
	@RequestMapping(value="ssptj")
	@ResponseBody
	public String ssptj(HttpServletRequest request) {
		Map<String, Object> map = getAuthorityMap();
		map.put("starttime", request.getParameter("starttime"));
		map.put("endtime", request.getParameter("endtime"));
		map.put("type", request.getParameter("flag"));
		return JsonMapper.getInstance().toJson(yhpcTjfxService.getCountByTime3(map));
	}
	
	/**
	 * 随手拍统计分析list页面(人员统计)
	 */
	@RequestMapping(value="ssptj2")
	@ResponseBody
	public String ssptj2(HttpServletRequest request) {
		Map<String, Object> map = getAuthorityMap();
		map.put("starttime", request.getParameter("starttime"));
		map.put("endtime", request.getParameter("endtime"));
		return JsonMapper.getInstance().toJson(yhpcTjfxService.getCountByUser3(map));
	}
	
	
	/**
	 * 重要危险源整改汇总统计
	 * @throws ParseException 
	 */
	@RequestMapping(value="aqjctj3")
	@ResponseBody
	public String aqjctj3(HttpServletRequest request) throws ParseException {
		Map<String, Object> map = getAuthorityMap();
		map = getIdMap(map, request);
		map.put("starttime", request.getParameter("starttime"));
		map.put("endtime", request.getParameter("endtime"));
		return JsonMapper.getInstance().toJson(yhpcTjfxService.getDailyCheckCount(map));
	}
	
	/**
	 * 安全隐患类别占比统计
	 * @throws ParseException 
	 */
	@RequestMapping(value="aqjctj4")
	@ResponseBody
	public String aqjctj4(HttpServletRequest request) throws ParseException {
		Map<String, Object> map = getAuthorityMap();
		map = getIdMap(map, request);
		map.put("starttime", request.getParameter("starttime"));
		map.put("endtime", request.getParameter("endtime"));
		return JsonMapper.getInstance().toJson(yhpcTjfxService.getCountByYhkind(map));
	}
	
	/**
	 * 安全隐患类别占比统计
	 * @throws ParseException 
	 */
	@RequestMapping(value="aqjctj5")
	@ResponseBody
	public String aqjctj5(HttpServletRequest request) throws ParseException {
		Map<String, Object> map = getAuthorityMap();
		map = getIdMap(map, request);
		map.put("m2", request.getParameter("fxlb_name"));
		return JsonMapper.getInstance().toJson(yhpcTjfxService.getFxlbCount(map));
	}
	
	/**
	 * 根据企业类型确定查询条件
	 * @param map
	 * @param request
	 * @return
	 */
	public Map<String, Object> getIdMap(Map<String, Object> map, HttpServletRequest request) {
		if (map.get("fid") != null) {//如果是集团
			String qyid = request.getParameter("qyid");//判断页面是否传参
			if (StringUtils.isNotBlank(qyid)) {//如果传参
				if (map.get("qyid") != map.get("fid")) {//如果参数qyid和fid不同
					map.remove("fid");//移除集团id，使用参数qyid进行查询
					map.put("qyid", Long.parseLong(qyid));
				} else {
					map.put("fid", Long.parseLong(qyid));//相同，则使用fid进行查询
				}
			}
		}
		return map;
	}
}
