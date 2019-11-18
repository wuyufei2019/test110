package com.cczu.model.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.dao.IBisQyjbxxDao;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.IFmewSjfxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 大数据分析controller
 * @author jason
 *
 */
@Controller
@RequestMapping("fmew/sjfx")
public class PageFmewSjfxController extends BaseController {
	@Autowired
	private IFmewSjfxService fmewSjfxService;
	@Autowired
	private IBisQyjbxxService qyjbxxService;
	@Autowired
	private IBisQyjbxxDao bisQyjbxxDao;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "model/fmew/sjfx/index";
	}

	/**
	 * 储罐实时统计数据页面
	 */
	@RequestMapping(value="cgindex")
	public String cgindex(Model model) {
		return "model/fmew/sjfx/index_cg";
	}

	/**
	 * 仓库实时统计数据页面
	 */
	@RequestMapping(value="ckindex")
	public String ckindex(Model model) {
		return "model/fmew/sjfx/index_ck";
	}
	
	/**
	 * 储罐和仓库合计统计
	 */
	@RequestMapping(value="totalindex")
	public String totalindex(Model model) {
		return "model/fmew/sjfx/index_all";
	}
	
	
	/**
	 * 根据物料类别实时显示所有储罐储量
	 * @param request
	 */
	@RequiresPermissions("fmew:sjfx:view")
	@RequestMapping(value="cglistbylb")
	@ResponseBody
	public List<Map<String, Object>> getCGListByLB(HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		return fmewSjfxService.findAllCGInforByLeibie(sessionuser.getXzqy());
	}
	
	/**
	 * 根据物料名称实时显示所有储罐储量
	 * @param request
	 */
	@RequiresPermissions("fmew:sjfx:view")
	@RequestMapping(value="cglistbymc")
	@ResponseBody
	public List<Map<String, Object>> getCGListByMC(HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		return fmewSjfxService.findAllCGInforByName(sessionuser.getXzqy());
	}
	
	
	/**
	 * 根据物料类别实时显示所有仓库储量
	 * @param request
	 */
	@RequiresPermissions("fmew:sjfx:view")
	@RequestMapping(value="cklistbylb")
	@ResponseBody
	public List<Map<String, Object>> getCKListByLB(HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		return fmewSjfxService.findAllCKInforByLeibie(sessionuser.getXzqy());
	}
	
	/**
	 * 根据物料名称实时显示所有仓库储量
	 * @param request
	 */
	@RequiresPermissions("fmew:sjfx:view")
	@RequestMapping(value="cklistbymc")
	@ResponseBody
	public List<Map<String, Object>> getCKListByMC(HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		return fmewSjfxService.findAllCKInforByName(sessionuser.getXzqy());
	}
	
	
	/**
	 * 根据物料类别实时显示所有   储罐和仓库  储量
	 * @param request
	 */
	@RequiresPermissions("fmew:sjfx:view")
	@RequestMapping(value="alllistbylb")
	@ResponseBody
	public List<Map<String, Object>> getAllListByLB(HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		return fmewSjfxService.findAllInforByLeibie(sessionuser.getXzqy());
	}
	
	/**
	 * 根据物料名称实时显示所有  储罐和仓库  储量
	 * @param request
	 */
	@RequiresPermissions("fmew:sjfx:view")
	@RequestMapping(value="alllistbymc")
	@ResponseBody
	public List<Map<String, Object>> getAllListByMC(HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		return fmewSjfxService.findAllInforByName(sessionuser.getXzqy());
	}
	
 
	
	
	/**
	 * 页面跳转
	 * @param model
	 */
	@RequiresPermissions("fmew:sjfx:view")
	@RequestMapping(value = "showcharts" , method = RequestMethod.GET)
	public String create(Model model) {
		return "model/fmew/sjfx/charts";
	}
	
	
	/**
	 * 根据企业id查看企业储罐历史记录曲线图
	 * @param request
	 */
	@RequiresPermissions("fmew:sjfx:view")
	@RequestMapping(value="qyhistorydata")
	@ResponseBody
	public String getChuGuanHistoryDatas(Long qyid,String strattime,String endtime) {
		if(endtime==null||endtime.equals(""))
			endtime=DateUtils.getDate();
		if(strattime==null||strattime.equals(""))
			strattime=DateUtils.getNextDay(endtime, "-30");
	
		return fmewSjfxService.findHistoryDataByQyID(qyid, strattime, endtime);
	}
	
	
	/**
	 * 根据物料类别统计储罐历史记录曲线图
	 * @param request
	 */
	@RequiresPermissions("fmew:sjfx:view")
	@RequestMapping(value="historydata")
	@ResponseBody
	public String getWuliaoHistoryDatas(String datestart,String dateend) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Map<String, Object> mapData = new HashMap<String, Object>();
		if(dateend==null||dateend.equals(""))
			dateend=DateUtils.getDate();
		if(datestart==null||datestart.equals(""))
			datestart=DateUtils.getNextDay(DateUtils.getDate(), "-30");
		mapData.put("datestart", datestart);
		mapData.put("dateend", dateend);
		mapData.put("xzqy",sessionuser.getXzqy());
		
		
		return fmewSjfxService.findHistoryDatesByWllb(mapData);
	}
	
	
	/**
	 * 热力图页面跳转
	 * @param model
	 */
	@RequiresPermissions("fmew:heatmap:view")
	@RequestMapping(value = "showheatmap" , method = RequestMethod.GET)
	public String createheatmap(Model model) {
		return "model/fmew/sjfx/heatmap";
	}
	
	
	/**
	 * 获取热力图数据
	 * @param request
	 */
	@RequestMapping(value="heatmapjson")
	@ResponseBody
	public String getHeatmapJson2(HttpServletRequest request) {
		Map<String, Object> map= getAuthorityMap();
		//最近7天数据
		String stratdate = DateUtils.getNextDay(DateUtils.getDate(), "-"+7);
		return fmewSjfxService.selectHeatmapData(stratdate, map);
	}
	
	
	
}
