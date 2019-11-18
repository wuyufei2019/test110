package com.cczu.model.controller;

import java.sql.Timestamp;
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

import com.cczu.model.entity.XZCF_CfjdInfoEntity;
import com.cczu.model.service.IMsgService;
import com.cczu.model.service.IXzcfCfjdService;
import com.cczu.model.service.IPunishSimpleGzService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.UserService;

/**
 * 行政处罚-简易处罚-处罚决定controller
 * 
 * @author jason
 * 
 */
@Controller
@RequestMapping("xzcf/jycf/jycf/cfjd")
public class PagePunishSimpleCfjdController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private IMsgService msgService;
	@Autowired
	private IXzcfCfjdService punishsimplecfjdservice;
	@Autowired
	private IPunishSimpleGzService punishsimplegzservice;

	/**
	 * 默认页面
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		return "aqzf/xzcf/jycf/cfjd/index";
	}

	/**
	 * list页面
	 */
	@RequiresPermissions("jycf:cfjd:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("name", request.getParameter("jycf_cfjd_name"));
		map.put("startdate", request.getParameter("jycf_cfjd_startdate"));
		map.put("enddate", request.getParameter("jycf_cfjd_enddate"));
		return punishsimplecfjdservice.dataGrid(map);
	}
	
	/**
	 * 首页提醒
	 * 
	 * @param request
	 */
	//@RequestMapping(value = "html")
	//@ResponseBody
//	public Map<String, Object>  getHtml(HttpServletRequest request, Model model) {
//		Map<String, Object> map = getPageMap(request);
//		Map<String, Object> remap = new HashMap<>();//返回值
//		ShiroUser user= UserUtil.getCurrentShiroUser();
//		map.put("year", DateUtils.getYear());
//		map.put("month", Integer.parseInt(DateUtils.getMonth()));
//		if(user.getUsertype().equals("1")){//0是企业，1为安监局
//		map.put("id", user.getQyid());
//		}
//		remap.put("rows",aqjgcheckplanservice.dataGrid(map).get("rows") );
//		remap.put("usertype",UserUtil.getCurrentShiroUser().getUsertype());
//		return remap;
//	}
	

	/**
	 * 添加处罚信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("jycf:cfjd:add")
	@RequestMapping(value = "create")
	public String create(Model model) {
		model.addAttribute("action", "createSub");
		return "aqzf/xzcf/jycf/cfjd/form";
	}

	/**
	 * 添加出处罚
	 * @param request
	 */
	@RequiresPermissions("jycf:cfjd:add")
	@RequestMapping(value = "createSub", method = RequestMethod.POST)
	@ResponseBody
	public String createSub(XZCF_CfjdInfoEntity jce,
			HttpServletRequest request, Model model){
		String datasuccess = "failed";
		Timestamp t = DateUtils.getSysTimestamp();
		jce.setS1(t);
		jce.setS2(t);
		jce.setS3(0);
		if (punishsimplecfjdservice.addInfoReturnID(jce) > 0) 
			datasuccess = "success";
		return datasuccess;
	}

	/**
	 * 修改处罚信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("jycf:cfjd:update")
	@RequestMapping(value = "update/{id}")
	public String update(@PathVariable("id") long id, Model model) {

		XZCF_CfjdInfoEntity jce = punishsimplecfjdservice.findInfoById(id);
		model.addAttribute("jce", jce);
		model.addAttribute("flag", jce.getPercomflag());
		model.addAttribute("action", "updateSub");
		return "aqzf/xzcf/jycf/cfjd/form";
	}

	/**
	 * 修改处罚信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("jycf:cfjd:update")
	@RequestMapping(value = "updateSub")
	@ResponseBody
	public String updateSub(XZCF_CfjdInfoEntity jce,
			HttpServletRequest request, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		jce.setS2(t);
		try {
			punishsimplecfjdservice.updateInfo(jce);
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="failed";
		}
		return datasuccess;
	}

	/**
	 * 查看处罚信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("jycf:cfjd:view")
	@RequestMapping(value = "view/{id}")
	public String view(@PathVariable("id") long id, Model model) {
		XZCF_CfjdInfoEntity jce = punishsimplecfjdservice.findInfoById(id);
		model.addAttribute("jce", jce);
		return "aqzf/xzcf/jycf/cfjd/view";
	}

	/**
	 * 删除处罚信息
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("jycf:cfjd:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "success";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			punishsimplecfjdservice.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 导出告知书记录word
	 * 
	 */
/*	@RequiresPermissions("jycf:cfjd:export")
	@RequestMapping(value = "exportcfjd/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		XZCF_JYCFCfjdEntity jce = punishsimplecfjdservice.findInfoById(id);
		XZCF_JYCFInfoEntity jie=punishsimplegzservice.findInfoById(jce.getId1());
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("name", jce.getName());
		map.put("startdate",jce.getStartdate());
		map.put("enddate", jce.getEnddate());
		map.put("address", jce.getAddress());
		map.put("name", jce.getName());
		map.put("sex","1".equals(jce.getSex())?"男":"女");
		map.put("duty", jce.getDuty());
		map.put("ybcode", jce.getYbcode());
		map.put("s1", DateUtils.formatDate(jce.getS1(), "yyyy年MM月dd日"));
	    map.put("workunit", jce.getWorkunit());
	    map.put("phone", jce.getPhone());
	    map.put("contactaddress", jce.getContactaddress());
	    map.put("director", jce.getDirector());
	    map.put("recorder", jce.getRecorder());
	    map.put("enforcer1", jce.getEnforcer1());
	    map.put("enforcer2", jce.getEnforcer2()==null?"":jce.getEnforcer2());
	    map.put("identity1", jce.getIdentity1());
	    map.put("identity2", jce.getIdentity2()==null?"":jce.getIdentity2());
	    map.put("casename", jie.getCasename());
	    map.put("cfjdrecord", jce.getcfjdrecord());
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "cfjdrecord.ftl", filePath, filename, request);
		return "/download/" + filename;
	}*/
	
}
