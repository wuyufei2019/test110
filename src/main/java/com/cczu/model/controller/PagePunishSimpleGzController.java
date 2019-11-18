package com.cczu.model.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.XZCF_GzsInfoEntity;
import com.cczu.model.service.IMsgService;
import com.cczu.model.service.IPunishSimpleGzService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.UserService;
import com.cczu.util.common.WordUtil;

/**
 * 行政处罚-简易处罚-告知controller
 * 
 * @author jason
 * 
 */
@Controller
@RequestMapping("xzcf/jycf/jycf/cfgz")
public class PagePunishSimpleGzController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private IMsgService msgService;
	@Autowired
	private IPunishSimpleGzService punishsimplegzservice;

	/**
	 * 默认页面
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		return "aqzf/xzcf/jycf/cfgz/index";
	}

	/**
	 * list页面
	 */
	@RequiresPermissions("jycf:cfgz:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("name", request.getParameter("jycf_cfgz_name"));
		map.put("startdate", request.getParameter("jycf_cfgz_startdate"));
		map.put("enddate", request.getParameter("jycf_cfgz_enddate"));
		return punishsimplegzservice.dataGrid(map);
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
	 * 添加告知信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("jycf:cfgz:add")
	@RequestMapping(value = "create")
	public String create(Model model) {
		model.addAttribute("action", "createSub");
		return "aqzf/xzcf/jycf/cfgz/form";
	}

	/**
	 * 添加计划
	 * @param request
	 */
	@RequiresPermissions("jycf:cfgz:add")
	@RequestMapping(value = "createSub", method = RequestMethod.POST)
	@ResponseBody
	public String createSub(XZCF_GzsInfoEntity jie,
			HttpServletRequest request, Model model) throws IOException {
		String datasuccess = "failed";
		Timestamp t = DateUtils.getSysTimestamp();
		jie.setS1(t);
		jie.setS2(t);
		jie.setS3(0);
		jie.setNumber("11");
		jie.setExplainflag("0");//申辩标志
		jie.setPunishflag("0");//是否已处罚标志
		if (punishsimplegzservice.addInfoReturnID(jie) > 0) 
			datasuccess = "success";
		return datasuccess;
	}

	/**
	 * 修改计划信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("jycf:cfgz:update")
	@RequestMapping(value = "update/{id}")
	public String update(@PathVariable("id") long id, Model model) {

		XZCF_GzsInfoEntity jie = punishsimplegzservice.findInfoById(id);
		model.addAttribute("jie", jie);
		model.addAttribute("jid", id);
		model.addAttribute("action", "updateSub");
		return "aqzf/xzcf/jycf/cfgz/form";
	}

	/**
	 * 修改告知信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("jycf:cfgz:update")
	@RequestMapping(value = "updateSub")
	@ResponseBody
	public String updateSub(XZCF_GzsInfoEntity jie,
			HttpServletRequest request, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		jie.setS2(t);
		try {
			punishsimplegzservice.updateInfo(jie);
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="failed";
		}
		return datasuccess;
	}

	/**
	 * 查看告知信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("jycf:cfgz:view")
	@RequestMapping(value = "view/{id}")
	public String view(@PathVariable("id") long id, Model model) {
		XZCF_GzsInfoEntity jie = punishsimplegzservice.findInfoById(id);
		model.addAttribute("jie", jie);
		return "aqzf/xzcf/jycf/cfgz/view";
	}

	/**
	 * 删除告知信息
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("jycf:cfgz:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "success";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			punishsimplegzservice.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	
//	/**
//	 * 获取案件下拉菜单的内容
//	 * @param id,model
//	 */
//	@RequestMapping(value = "casenamelist")
//	@ResponseBody
//	public String getGzCaseNameList() {
//		List<Map<String,Object>> list=punishsimplegzservice.findGzCaseNameList("");
//		
//		return JsonMapper.getInstance().toJson(list);
//	}
	
	/**
	 * 导出告知书记录word
	 * 
	 */
	@RequiresPermissions("jycf:cfgz:export")
	@RequestMapping(value = "exportgzs/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		XZCF_GzsInfoEntity jie = punishsimplegzservice.findInfoById(id);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("name", jie.getName());
		map.put("punishdate",jie.getPunishdate());
		map.put("illegalact", jie.getIllegalact());
		map.put("evidence", jie.getEvidence());
		map.put("unlaw", jie.getUnlaw());
		map.put("enlaw",jie.getEnlaw());
		map.put("punishresult", jie.getPunishresult());
	    map.put("contactname", jie.getContactname());
	    map.put("number", jie.getNumber());
	    map.put("phone", jie.getPhone());

		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "gzsrecord.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
}
