package com.cczu.model.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cczu.model.entity.AQJG_AccidentInforEntity;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IAqjgsjglService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 安全监管-事件管理controller
 * @author jason
 *
 */
@Controller
@RequestMapping("aqjg/tjfx")
public class PageAqjgTjfxController extends BaseController {
	
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private IAqjgsjglService aqjgsjglservice ;
/**
 * 跳转列表页面
 * @param model
 */
	@RequestMapping(value="index")
	String index(Model model){
		ShiroUser sessionuser=UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity bis= bisQyjbxxService.findInfoById(sessionuser.getQyid());
		model.addAttribute("UserType", sessionuser.getUsertype());
		if(bis!=null){
			return "aqjg/sjgl/tjfx/index";
		}else{
			// 获取用户角色
			List<Role> list = roleService.findRoleById(sessionuser.getId());
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					//判断用户角色，如果包含有企业用户角色，则转到企业用户显示的界面
					if (list.get(i).getRoleCode().equals("company")||list.get(i).getRoleCode().equals("companyadmin"))
						return "../error/001";
				}
			}
			return "aqjg/sjgl/tjfx/index";
		}	
	}
	
	/**
	 * 列表显示页面
	 * @param request 
	 */
	@RequiresPermissions("aqjg:tjfx:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String ,Object> getData(HttpServletRequest request){
		Map<String,Object> map=this.getPageMap(request);
		map.put("sgtype",request.getParameter("aqjg_tjfx_sgtype"));
		map.put("sglevel", request.getParameter("aqjg_tjfx_sglevel"));
		map.put("dwname", request.getParameter("aqjg_tjfx_dwname"));
		map.put("year", request.getParameter("aqjg_tjfx_year"));
		return aqjgsjglservice.dataGrid(map);
	}
	

	/**
	 * 查看行事件信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("aqjg:tjfx:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		// 查询选择的行政备案信息
		AQJG_AccidentInforEntity aie = aqjgsjglservice.findInfoById(id);
		model.addAttribute("aie", aie);
		// 返回页面
		model.addAttribute("action", "view");
		return "aqjg/sjgl/tjfx/view";
	}
	
	/**
	 * Echarts图返回数据
	 */
	@RequiresPermissions("aqjg:tjfx:view")
	@RequestMapping(value="jsondate")
	@ResponseBody
	public Map<String,Object> getJsonDate(HttpServletRequest request){
		//返回map
		Map<String ,Object> remap = new HashMap<String,Object>();
		//查询map
		Map<String ,Object> querymap= new HashMap<String,Object>();
		String[] month={"01月","02月","03月","04月","05月","06月","07月","08月","09月","10月","11月","12月"};
		List<Object> listcount=new ArrayList<Object>();
		List<Object> listmonth=new ArrayList<Object>();
		
		String year= request.getParameter("year");
		querymap.put("sgtype",request.getParameter("sgtype"));
		querymap.put("sglevel",request.getParameter("sglevel"));
		
		year=(StringUtils.isNotBlank(year)?year:DateUtils.getYear());
		//String year=DateUtils.getYear();
		int  count=0;
		for(int i=0; i<month.length;i++){
			querymap.put("date",year+"-"+(month[i].replace("月", "")));
		    count=aqjgsjglservice.getCountEveryMonth(querymap);
		    querymap.remove("date");
			listcount.add(count);
			listmonth.add(month[i]);
		}
		remap.put("xdate",JSON.toJSON(listmonth).toString());
		
		remap.put("ydate",JSON.toJSON(listcount).toString());
		return remap;
	}
	
	/**
	 * 获取年份
	 * @param id,model
	 */
	@RequiresPermissions("aqjg:tjfx:view")
	@RequestMapping(value = "year")
	@ResponseBody
	public String getyear() {
		Object[] objs=aqjgsjglservice.getMaxYearAndMinYear();
		int maxyear= Integer.parseInt(objs[0].toString());
		int minyear= Integer.parseInt(objs[1].toString());
		List<Map<String, Object>> yearlist=new ArrayList<Map<String, Object>>();  
		Map<String, Object> map;
		for(int i=minyear;i<=maxyear;i++){
			map=new HashMap<String,Object>();
			map.put("id", i);
			map.put("text", i);
			yearlist.add(map);
		}
		return JsonMapper.getInstance().toJson(yearlist);
	}
}
