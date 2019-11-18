package com.cczu.model.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.FMEW_AlarmEntity;
import com.cczu.model.service.TsWarningDataService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.utils.UserUtil;


/**
 * 监控报警controller
 * @author jason
 *
 */
@Controller
@RequestMapping("fmew/bj")
public class PageFmewBjController extends BaseController {

	@Autowired
	private TsWarningDataService tsWarningDataService;
	@Autowired
	private RoleService roleService;

	
	/**
	 * 储罐报警默认页面
	 */
	@RequestMapping(value="cgindex")
	public String cgindex(Model model) {
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "zxjkyj/bjyj/cg/index";
	}

	/**
	 * 气体报警默认页面
	 */
	@RequestMapping(value="qtindex")
	public String qtindex(Model model) {
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "zxjkyj/bjyj/qt/index";
	}
	
	/**
	 * 高危工艺报警默认页面
	 */
	@RequestMapping(value="gyindex")
	public String gyindex(Model model) {
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "zxjkyj/bjyj/gwgy/index";
	}
	
	/**
	 * 储罐报警list页面(未处理)
	 */
	@RequestMapping(value="cglist")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("fmew_bj_cx_m1"));
		map.put("starttime", request.getParameter("bjyj_cgxx_cx_time1"));
		map.put("endtime", request.getParameter("bjyj_cgxx_cx_time2"));
		map.put("bjlx", request.getParameter("bjlx"));
		map.put("status", "0");
		map.put("type", "1");
		map.putAll(getAuthorityMap());
		return tsWarningDataService.dataGrid(map);
	}
	
	/**
	 * 储罐报警list页面(已处理)
	 */
	@RequestMapping(value="cglist2")
	@ResponseBody
	public Map<String, Object> getData12(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("fmew_bj_cx_m1"));
		map.put("starttime", request.getParameter("bjyj_cgxx_cx_time3"));
		map.put("endtime", request.getParameter("bjyj_cgxx_cx_time4"));
		map.put("bjlx", request.getParameter("bjlx2"));
		map.put("status", "1");
		map.put("type", "1");
		map.putAll(getAuthorityMap());
		return tsWarningDataService.dataGrid(map);
	}

	/**
	 * 气体报警list页面(未处理)
	 */
	@RequestMapping(value="qtlist")
	@ResponseBody
	public Map<String, Object> getData2(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("fmew_bj_cx_m1"));
		map.put("starttime", request.getParameter("bjyj_qtxx_cx_time1"));
		map.put("endtime", request.getParameter("bjyj_qtxx_cx_time2"));
		map.put("bjlx", request.getParameter("bjlx"));
		map.put("status", "0");
		map.put("type", "2");
		map.putAll(getAuthorityMap());
		return tsWarningDataService.dataGrid(map);
	}

	/**
	 * 气体报警list页面2(已处理)
	 */
	@RequestMapping(value="qtlist2")
	@ResponseBody
	public Map<String, Object> getData22(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("fmew_bj_cx_m1"));
		map.put("starttime", request.getParameter("bjyj_qtxx_cx_time3"));
		map.put("endtime", request.getParameter("bjyj_qtxx_cx_time4"));
		map.put("bjlx", request.getParameter("bjlx2"));
		map.put("status", "1");
		map.put("type", "2");
		map.putAll(getAuthorityMap());
		return tsWarningDataService.dataGrid(map);
	}
	
	/**
	 * 高危工艺报警list(未处理)
	 */
	@RequestMapping(value="gylist")
	@ResponseBody
	public Map<String, Object> getData3(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("fmew_bj_cx_m1"));
		map.put("starttime", request.getParameter("bjyj_gyxx_cx_time1"));
		map.put("endtime", request.getParameter("bjyj_gyxx_cx_time2"));
		map.put("bjlx", request.getParameter("bjlx"));
		map.put("status", "0");
		map.put("type", "3");
		map.putAll(getAuthorityMap());
		return tsWarningDataService.dataGrid(map);
	}	

	/**
	 * 高危工艺报警list页面(已处理)
	 */
	@RequestMapping(value="gylist2")
	@ResponseBody
	public Map<String, Object> getData32(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("fmew_bj_cx_m1"));
		map.put("starttime", request.getParameter("bjyj_gyxx_cx_time3"));
		map.put("endtime", request.getParameter("bjyj_gyxx_cx_time4"));
		map.put("bjlx", request.getParameter("bjlx2"));
		map.put("status", "1");
		map.put("type", "3");
		map.putAll(getAuthorityMap());
		return tsWarningDataService.dataGrid(map);
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		//查询选择的报警信息
		long id1 = id;
		FMEW_AlarmEntity bj = tsWarningDataService.findById(id1);
		model.addAttribute("bj", bj);
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		//返回页面
		model.addAttribute("action", "update");
		return "zxjkyj/bjyj/form";
	}
	
	/**
	 * 修改车间信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(FMEW_AlarmEntity bj, Model model){
		String datasuccess="success";
		tsWarningDataService.updateInfo(bj);
		//返回结果
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		//查询选择的安全备案信息
		Map<String,Object> bj = tsWarningDataService.findInforById(id);		
		model.addAttribute("bj", bj);
		//返回页面
		model.addAttribute("action", "view");
		return "zxjkyj/bjyj/view";
	}
	
	/**
	 * 导出储罐报警excel
	 * 
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("qyname", request.getParameter("fmew_bj_cx_m1"));
		if(request.getParameter("status").equals("0")){
			map.put("starttime", request.getParameter("bjyj_cgxx_cx_time1"));
			map.put("endtime", request.getParameter("bjyj_cgxx_cx_time2"));
			map.put("bjlx", request.getParameter("bjlx"));
		}else{
			map.put("starttime", request.getParameter("bjyj_cgxx_cx_time3"));
			map.put("endtime", request.getParameter("bjyj_cgxx_cx_time4"));
			map.put("bjlx", request.getParameter("bjlx2"));
		}
		map.put("status", request.getParameter("status"));
		map.put("type", "1");
		map.putAll(getAuthorityMap());
		map.put("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		tsWarningDataService.exportExcel(response, map);

	}
	
	/**
	 * 导出气体报警excel
	 * 
	 */
	@RequestMapping(value = "export2")
	@ResponseBody
	public void export2(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("qyname", request.getParameter("fmew_bj_cx_m1"));
		if(request.getParameter("status").equals("0")){
			map.put("starttime", request.getParameter("bjyj_qtxx_cx_time1"));
			map.put("endtime", request.getParameter("bjyj_qtxx_cx_time2"));
			map.put("bjlx", request.getParameter("bjlx"));
		}else{
			map.put("starttime", request.getParameter("bjyj_qtxx_cx_time3"));
			map.put("endtime", request.getParameter("bjyj_qtxx_cx_time4"));
			map.put("bjlx", request.getParameter("bjlx2"));
		}
		map.put("status", request.getParameter("status"));
		map.put("type", "2");
		map.putAll(getAuthorityMap());
		map.put("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		tsWarningDataService.exportExcel(response, map);

	}
	
	/**
	 * 导出高危工艺报警excel
	 * 
	 */
	@RequestMapping(value = "export3")
	@ResponseBody
	public void export3(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("qyname", request.getParameter("fmew_bj_cx_m1"));
		if(request.getParameter("status").equals("0")){
			map.put("starttime", request.getParameter("bjyj_gyxx_cx_time1"));
			map.put("endtime", request.getParameter("bjyj_gyxx_cx_time2"));
			map.put("bjlx", request.getParameter("bjlx"));
		}else{
			map.put("starttime", request.getParameter("bjyj_gyxx_cx_time3"));
			map.put("endtime", request.getParameter("bjyj_gyxx_cx_time4"));
			map.put("bjlx", request.getParameter("bjlx2"));
		}
		map.put("status", request.getParameter("status"));
		map.put("type", "3");
		map.putAll(getAuthorityMap());
		map.put("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		tsWarningDataService.exportExcel(response, map);

	}
	
	/**
	 * 储罐报警显示所有列
	 * @param id,model
	 */
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url", "fmew/bj/export");
		return "common/formexcel";
	}
	
	/**
	 * 气体报警显示所有列
	 * @param id,model
	 */
	@RequestMapping(value = "colindex2", method = RequestMethod.GET)
	public String colindex2(Model model) {
		model.addAttribute("url", "fmew/bj/export2");
		return "common/formexcel";
	}
	
	/**
	 * 高危工艺报警显示所有列
	 * @param id,model
	 */
	@RequestMapping(value = "colindex3", method = RequestMethod.GET)
	public String colindex3(Model model) {
		model.addAttribute("url", "fmew/bj/export3");
		return "common/formexcel";
	}
}
