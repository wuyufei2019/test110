package com.cczu.model.controller;


import java.util.Date;
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
import com.cczu.model.entity.YHPC_GridKpiMonth;
import com.cczu.model.entity.YHPC_GridKpiMonthOverview;
import com.cczu.model.service.YhpcGridKpiMonthOverviewService;
import com.cczu.model.service.YhpcGridKpiMonthService;
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
@RequestMapping("yhpc/wgkpi/month")
public class PageYhpcGridKpiMonthController extends BaseController{

	@Autowired
	private YhpcGridKpiMonthService yhpcGridKpiMonthService;
	@Autowired
	private YhpcGridKpiMonthOverviewService gridKpiMonthOverviewService;
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
			return "yhpc/wgkpi/month/index";
		}
	}
	
	/**
	 * list页面
	 */
	@RequiresPermissions("yhpc:wgkpimon:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		String wgcode=request.getParameter("wgcode");
		map.put("time", request.getParameter("time"));
		map.put("wgcode",StringUtils.isBlank(wgcode)?UserUtil.getCurrentShiroUser().getXzqy():wgcode);
		return yhpcGridKpiMonthService.dataGrid(map);
	}
	
	/**
	 * 评分list页面
	 */
	@RequiresPermissions("yhpc:wgkpimon:view")
	@RequestMapping(value="examinelist")
	@ResponseBody
	public Map<String, Object> getExamineData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		String code=request.getParameter("wgcode");
		String time=request.getParameter("time");
		map.put("wgcode",StringUtils.isBlank(code)?UserUtil.getCurrentShiroUser().getXzqy():code);
		map.put("examtime",StringUtils.isBlank(time)?DateUtils.formatDate(new Date(), "yyyy-MM"):time);
		return yhpcGridKpiMonthService.dataGridExamine(map);
	}
	
	/**
	 * 总览list页面
	 */
	@RequiresPermissions("yhpc:wgkpimon:view")
	@RequestMapping(value="overviewlist")
	@ResponseBody
	public Map<String, Object> getOverviewData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		String code=request.getParameter("wgcode");
		map.put("time",request.getParameter("time"));
		map.put("wgcode",StringUtils.isBlank(code)?UserUtil.getCurrentShiroUser().getXzqy():code);
		return gridKpiMonthOverviewService.dataGrid(map);
	}
	
	@RequiresPermissions("yhpc:wgkpimon:view")
	@RequestMapping(value = "viewdetail/{id}")
	@ResponseBody
	public Map<String, Object> view(@PathVariable("id") Long id, HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("id1", id);
		return yhpcGridKpiMonthService.dataGridDetail(map);
	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("yhpc:wgkpimon:add")
	@RequestMapping(value = "create")
	public String create(Model model) {
		model.addAttribute("action", "createSub");
		return "yhpc/wgkpi/month/form";
	}
	/**
	 *初始化
	 * @param model
	 */
	@RequiresPermissions("yhpc:wgkpimon:add")
	@RequestMapping(value = "init",method = RequestMethod.POST)
	@ResponseBody
	public String initMonthKpi() {
		String datasuccess="success";
		try {
			yhpcGridKpiMonthService.initMonthKpi();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			datasuccess="failed";
		}
		return datasuccess;
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequiresPermissions("yhpc:wgkpimon:add")
	@RequestMapping(value = "createSub" )
	@ResponseBody
	public String create(YHPC_GridKpiMonth mon, HttpServletRequest request) {
		String datasuccess = "success";
		try {
		} catch (Exception e) {
			// TODO: handle exception
			 datasuccess = "failed";
		}
		return datasuccess;
	}
	/**
	 * 全部保存信息
	 * @param request,model
	 */
	@RequiresPermissions("yhpc:wgkpimon:add")
	@RequestMapping(value = "createAllSub" )
	@ResponseBody
	public String createAll(HttpServletRequest request) {
		String datasuccess = "success";
		String listjson = request.getParameter("list");
		String entity = request.getParameter("entity");
		YHPC_GridKpiMonthOverview ov = JSON.parseObject(entity,YHPC_GridKpiMonthOverview.class);
		List<YHPC_GridKpiMonth> list = JSON.parseArray(listjson,YHPC_GridKpiMonth.class);
		try {
			yhpcGridKpiMonthService.addAllInfo(list, ov);
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess = "failed";
		}
		return datasuccess;
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("yhpc:wgkpimon:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		Map<String, Object> mon = yhpcGridKpiMonthService.findInfoById(id);
		model.addAttribute("mon",mon);
		model.addAttribute("action", "update");
		return "yhpc/wgkpi/month/form";
	}
	
	/**
	 * 修改
	 * @param request,model
	 */
	@RequiresPermissions("yhpc:wgkpimon:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(YHPC_GridKpiMonth mon, HttpServletRequest request, Model model){
		String datasuccess="success";
		yhpcGridKpiMonthService.updateInfo(mon);
		return datasuccess;
	}
	
	/**
	 * 删除
	 * @param user
	 * @param model
	 * 
	 */
	@RequiresPermissions("yhpc:wgkpimon:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			yhpcGridKpiMonthService.deleteInfo(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
}
