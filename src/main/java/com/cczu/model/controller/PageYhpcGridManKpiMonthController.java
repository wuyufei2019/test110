package com.cczu.model.controller;


import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cczu.model.entity.YHPC_GridManKpiMonth;
import com.cczu.model.entity.YHPC_GridManKpiMonthOverview;
import com.cczu.model.service.YhpcGridManKpiMonthOverviewService;
import com.cczu.model.service.YhpcGridManKpiMonthService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 网格化管理-网格员月度绩效考核
 * @author zpc
 * @date 2017/08/19
 */
@Controller
@RequestMapping("yhpc/wgykpi/month")
public class PageYhpcGridManKpiMonthController extends BaseController{

	@Autowired
	private YhpcGridManKpiMonthService yhpcGridManKpiMonthService;
	@Autowired
	private YhpcGridManKpiMonthOverviewService gridManKpiMonthovService;
	@Autowired
	private RoleService roleService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if("1".equals(sessionuser.getUsertype())){
			return "../error/403";
		}else{
			return "yhpc/wgykpi/month/index";
		}
	}
	/**
	 * 默认页面
	 */
	@RequestMapping(value="initform")
	public String initform(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if("1".equals(sessionuser.getUsertype())){
			return "../error/403";
		}else{
			return "yhpc/wgykpi/month/initform";
		}
	}
	/**
	 * 评分list页面
	 */
	@RequiresPermissions("yhpc:wgykpimon:view")
	@RequestMapping(value="examinelist")
	@ResponseBody
	public Map<String, Object> getExamineData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		String code=request.getParameter("wgcode");
		String uid=request.getParameter("wgyname");
		String time=request.getParameter("time");
		String ovid=request.getParameter("ovid");
		if(StringUtils.isNoneBlank(ovid)){
			map.put("ovid",ovid);
		}
		map.put("wgyid",uid);
		map.put("wgcode",StringUtils.isBlank(code)?UserUtil.getCurrentShiroUser().getXzqy():code);
		map.put("examtime",StringUtils.isBlank(time)?DateUtils.formatDate(new Date(), "yyyy-MM"):time);
		return yhpcGridManKpiMonthService.dataGridExamine(map);
	}
	/**
	 * list页面
	 */
	@RequiresPermissions("yhpc:wgykpimon:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		String code=request.getParameter("wgcode");
		map.put("wgcode",StringUtils.isBlank(code)?UserUtil.getCurrentShiroUser().getXzqy():code);
		map.put("time",request.getParameter("time"));
		map.put("wgyid",request.getParameter("wgyid"));
		return yhpcGridManKpiMonthService.dataGrid(map);
	}
	/**
	 * list页面
	 */
	@RequiresPermissions("yhpc:wgykpimon:view")
	@RequestMapping(value="overviewlist")
	@ResponseBody
	public Map<String, Object> getOverviewData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		String code=request.getParameter("wgcode");
		map.put("wgcode",StringUtils.isBlank(code)?UserUtil.getCurrentShiroUser().getXzqy():code);
		map.put("time",request.getParameter("time"));
		map.put("wgyid",request.getParameter("wgyid"));
		return gridManKpiMonthovService.dataGrid(map);
	}
	/**
	 * 判断本月考核是否初始化过
	 */
	@RequestMapping(value="getcount")
	@ResponseBody
	public int getCount(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code=request.getParameter("wgcode");
		String time=request.getParameter("time");
		map.put("time",time);
		map.put("wgcode",StringUtils.isBlank(code)?UserUtil.getCurrentShiroUser().getXzqy():code);
		return yhpcGridManKpiMonthService.getCount(map);
	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("yhpc:wgykpimon:add")
	@RequestMapping(value = "create")
	public String create(Model model) {
		model.addAttribute("action", "createSub");
		return "yhpc/wgykpi/month/form";
	}
	
	@RequiresPermissions("yhpc:wgykpimon:add")
	@RequestMapping(value = "init")
	@ResponseBody
	public String createAll(HttpServletRequest request) {
		String datasuccess="success";
		String listjson=request.getParameter("list");
		String entity=request.getParameter("entity");
		YHPC_GridManKpiMonthOverview ov=JSON.parseObject(entity, YHPC_GridManKpiMonthOverview.class);
        List<YHPC_GridManKpiMonth> list =JSON.parseArray(listjson, YHPC_GridManKpiMonth.class);
        try {
        	yhpcGridManKpiMonthService.addAllInfo(list, ov);
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="failed";
		}
		return datasuccess;
	}
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("yhpc:wgykpimon:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		 Map<String,Object> mon=null;
		try {
			mon = yhpcGridManKpiMonthService.findById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("mon",mon);
		model.addAttribute("action", "update");
		return "yhpc/wgykpi/month/form";
	}
	
	/**
	 * 修改
	 * @param request,model
	 */
	@RequiresPermissions("yhpc:wgykpimon:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(YHPC_GridManKpiMonth mon, HttpServletRequest request, Model model){
		String datasuccess="success";
		yhpcGridManKpiMonthService.updateInfo(mon);
		return datasuccess;
	}
	
	/**
	 * 删除
	 * @param user
	 * @param model
	 * 
	 */
	@RequiresPermissions("yhpc:wgykpimon:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			yhpcGridManKpiMonthService.deleteInfo(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	

	@RequiresPermissions("yhpc:wgykpimon:view")
	@RequestMapping(value = "viewdetail/{id}")
	@ResponseBody
	public Map<String, Object> view(@PathVariable("id") Long id, HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("id1", id);
		return yhpcGridManKpiMonthService.dataGridDetail(map);
	}
	
}
