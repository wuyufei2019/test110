package com.cczu.model.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IAqjgCheckPlanService;
import com.cczu.model.service.IAqjgCheckRecordService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 安全监管-安全监督检查-统计分析controller
 * 
 * @author jason
 * 
 */
@Controller
@RequestMapping("aqjd/tjfx")
public class PageCheckTjfxController extends BaseController {

	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private IAqjgCheckPlanService aqjgcheckplanservice;
	@Autowired
	private IAqjgCheckRecordService aqjgcheckrecordservice;

	/**
	 * 跳转列表页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	String index(Model model) {
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity bis = bisQyjbxxService.findInfoById(sessionuser
				.getQyid());
		model.addAttribute("UserType", sessionuser.getUsertype());
		if (bis != null) {
			return "aqjg/aqjdjc/tjfx/index";
		} else {
			// 获取用户角色
			List<Role> list = roleService.findRoleById(sessionuser.getId());
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					// 判断用户角色，如果包含有企业用户角色，则转到企业用户显示的界面
					if (list.get(i).getRoleCode().equals("company")
							|| list.get(i).getRoleCode().equals("companyadmin"))
						return "../error/001";
				}
			}
			return "aqjg/aqjdjc/tjfx/index";
		}
	}
	
	/**
	 * 点击echarts跳转列表页面（已废弃不用）
	 * 
	 * @param model
	 */
	@RequestMapping(value = "result/{year}/{month}")
	String result(@PathVariable("year")String year,@PathVariable("month") String month,Model model) {
		try {
			model.addAttribute("year",year);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "aqjg/aqjdjc/tjfx/view";
	}
	
	/**
	 * list页面(用于统计分析的页面的echarts图点击链接显示)计划检查次数（已废弃不用）
	 */
	@RequiresPermissions("aqjg:tjfx:view")
	@RequestMapping(value = "list/{year}/{month}")
	@ResponseBody
	public Map<String, Object> getData(@PathVariable("year")String year,@PathVariable("month") String month,HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("year", year);
		map.put("month", Integer.parseInt(month.substring(0, 2)));
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		map.put("xzqy", sessionuser.getXzqy());
		//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
		if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
			map.put("jglx",sessionuser.getUserroleflg());
		return aqjgcheckplanservice.dataGrid2(map);
	}
	
	/**
	 * list页面(用于统计分析的页面的echarts图点击链接显示)未完成/已完成（已废弃不用）
	 */
	@RequiresPermissions("aqjg:tjfx:view")
	@RequestMapping(value = "list2/{year}/{month}/{flag}")
	@ResponseBody
	public Map<String, Object> getData2(@PathVariable("year")String year,@PathVariable("month") String month,@PathVariable("flag")String flag,HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("eyear", year);
		map.put("flag", flag);
		map.put("month", Integer.parseInt(month.substring(0, 2)));
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		map.put("xzqy", sessionuser.getXzqy());
		//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
		if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
			map.put("jglx",sessionuser.getUserroleflg());
		return aqjgcheckrecordservice.dataGrid(map);
	}

	/**
	 * Echarts图返回数据(饼图年份统计)（已废弃不用）
	 */
	@RequiresPermissions("aqjg:tjfx:view")
	@RequestMapping(value = "jsondate")
	@ResponseBody
	public Map<String, Object> getJsonDate(HttpServletRequest request) {
		// 返回map
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> maplist = aqjgcheckplanservice.getYearDate();
		List<Object> listx = new ArrayList<>();
		for (Map<String, Object> map1 : maplist) {
			listx.add(map1.get("name"));
		}
		map.put("xdate", JSON.toJSON(listx).toString());
		map.put("ydate", JSON.toJSON(maplist).toString());
		return map;
	}

	/**
	 * Echarts图返回数据
	 */
	@RequiresPermissions("aqjg:tjfx:view")
	@RequestMapping(value = "jsondate2")
	@ResponseBody
	public Map<String, Object> getJsonDate2(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();// 返回map
		Map<String, Object> param = new HashMap<String, Object>();//参数map
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		String[] month = { "01月", "02月", "03月", "04月", "05月", "06月", "07月",
				"08月", "09月", "10月", "11月", "12月" };
		String year=StringUtils.isBlank(request.getParameter("year"))?DateUtils.getYear():request.getParameter("year");
		param.put("year", year);
		if(sessionuser.getUsertype().equals("0")){//安监用户  添加网格区域编码查询条件
			param.put("uxzqy",sessionuser.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
				param.put("ujglx",sessionuser.getUserroleflg());
		}
		List<Object[]> maplist=aqjgcheckplanservice.getYearDate2(param);
		List<Object> listx = new ArrayList<>();
		List<Object> listqy = new ArrayList<>();// 月计划检查次数
		List<Object> listfin = new ArrayList<>();// 完成次数，企业复检完即算完成
		List<Object> listunfin = new ArrayList<>();// 未完成次数，企业只完成初检或未检查。
		int qycount = 0;
		int count = 0;
		int uncount = 0;
		// 获取每月的各个数量
		try {
			for (int i = 0; i < month.length; i++) {
				listx.add(month[i]);
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("name", month[i]);
				for (Object[] obs : maplist) {
					if ((obs[0].toString().substring(
							obs[0].toString().indexOf("-") + 1, obs[0].toString()
									.length())).equals(String.valueOf(i + 1))) {
						qycount += obs[1].toString().split(",").length;// 获取当月的计划检查次数
						uncount += (int) obs[2];// 获取当月的未完成次数
						count += (int) obs[3];// 企业复检完成
					}
				}
				listqy.add(qycount);
				listfin.add(count);
				listunfin.add(uncount);
				qycount = 0;
				count = 0;
				uncount = 0;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("xdata", JSON.toJSON(listx).toString());// x轴
		map.put("qycount", JSON.toJSON(listqy).toString());// 计划检查次数
		map.put("fincount", JSON.toJSON(listfin).toString());// 完成次数
		map.put("unfincount", JSON.toJSON(listunfin).toString());
		return map;
	}

}
