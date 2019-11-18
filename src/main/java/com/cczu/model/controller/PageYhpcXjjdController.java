package com.cczu.model.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.YhpcXjjdService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.StringUtils;

/**
 * 巡检监督与考核
 * @author ll
 * @date 2017/08/24
 */
@Controller
@RequestMapping("yhpc/xjjd")
public class PageYhpcXjjdController extends BaseController{

	@Autowired
	private YhpcXjjdService yhpcXjjdService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					return "yhpc/xjjd/index";
				else
					return "yhpc/xjjd/qyindex";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "yhpc/xjjd/index";
		}	
	}

	/**
	 * 考核list页面 (安监端)
	 * @param request
	 * @throws ParseException 
	 */
	@RequestMapping(value="xjgjhf",method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getXjgjData(
			@RequestParam("view_time")String time,
			@RequestParam("view_xjry")String xjry,
			@RequestParam("view_xjbc")String xjbc){
		return yhpcXjjdService.getXjgjData(time,xjry,xjbc);	
	}
	/**
	 * 考核list页面 (安监端)
	 * @param request
	 * @throws ParseException 
	 */
	@RequestMapping(value="zjlist")
	@ResponseBody
	public Map<String, Object> getzjData(HttpServletRequest request, Model model) throws ParseException {
		Map<String, Object> map = getPageMap(request);
		//刚进来时设置默认时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String end=dateFormat.format(date);//默认结束时间
		String start=end.substring(0,4)+"-01-01";//默认开始时间
		
		String start2 = request.getParameter("yhpc_xjsj_start");//页面搜索条件（开始时间）
		String end2 = request.getParameter("yhpc_xjsj_end");//页面搜索条件（结束时间）
		if(!StringUtils.isBlank(start2)){
			start=start2;
		}
		if(!StringUtils.isBlank(end2)){
			end=end2;
		}
		
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
		map.putAll(getAuthorityMap());
		return yhpcXjjdService.khdataGrid(map);	
	}

	/**
	 * 考核list页面 (企业端)
	 * @param request
	 * @throws ParseException 
	 */
	@RequestMapping(value="zjlist2")
	@ResponseBody
	public Map<String, Object> getzjData2(HttpServletRequest request, Model model) throws ParseException {
		Map<String, Object> map = getPageMap(request);
		//刚进来时设置默认时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String end=dateFormat.format(date);//默认结束时间
		String start=end.substring(0,4)+"-01-01";//默认开始时间
		
		String start2 = request.getParameter("yhpc_xjsj_start");//页面搜索条件（开始时间）
		String end2 = request.getParameter("yhpc_xjsj_end");//页面搜索条件（结束时间）
		String tjlx = request.getParameter("tjlx");//页面搜索条件（统计类型）
		if(!StringUtils.isBlank(start2)){
			start=start2;
		}
		if(!StringUtils.isBlank(end2)){
			end=end2;
		}
		
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
		ShiroUser user = UserUtil.getCurrentShiroUser();
		map.put("qyid",user.getQyid());
		if(tjlx.equals("1")){
			return yhpcXjjdService.timekhdataGrid(map);
		}
		if(tjlx.equals("2")){
			return yhpcXjjdService.khdataGrid3(map);	
		}
		if(tjlx.equals("3")){
			return yhpcXjjdService.khdataGrid4(map);	
		}
		if(tjlx.equals("4")){
			return yhpcXjjdService.khdataGrid5(map);	
		}
		return null;
	}
}
