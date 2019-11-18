package com.cczu.model.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.service.IXwaqGctjfxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 观察统计分析controller
 * 
 * @author jason
 * @date 2017年7月1日
 */
@Controller
@RequestMapping("xwaq/gctjfx")
public class PageXwaqGctjfxController extends BaseController {
	@Autowired
	private IXwaqGctjfxService xwaqGctjfxService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		Timestamp t=DateUtils.getSysTimestamp();
		model.addAttribute("con_dt1", new Timestamp(t.getTime()-7*24 * 60 * 60 * 1000));
		model.addAttribute("con_dt2", t);
		return "model/xwaq/gctjfx/index";
	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("xwaq:gctjfx:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public List<Map<String,Object>> getData(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("con_type", request.getParameter("con_type"));
		map.put("con_bm", request.getParameter("con_bm"));
		Timestamp t=DateUtils.getSysTimestamp();
		if(request.getParameter("con_dt1")==null){
			map.put("con_dt1", new Timestamp(t.getTime()-7*24 * 60 * 60 * 1000));
		}else{
			map.put("con_dt1", request.getParameter("con_dt1"));
		}
		if(request.getParameter("con_dt2")==null){
			map.put("con_dt2", t);
		}else{
			map.put("con_dt2", request.getParameter("con_dt2"));
		}
		map.put("qyname", request.getParameter("yhpc_observe_qyname"));
		map.putAll(getAuthorityMap());
		return xwaqGctjfxService.dataGrid(map);

	}
	
	/**
	 * 导出Excel
	 * 
	 * @param request
	 */
	@RequiresPermissions("xwaq:gctjfx:excel")
	@RequestMapping(value = "excel")
	@ResponseBody
	public void getExcel(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("con_type", request.getParameter("excelcon1"));
		map.put("con_bm", request.getParameter("excelcon2"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		Timestamp t=DateUtils.getSysTimestamp();
		if("".equals(request.getParameter("excelcon3"))){
			map.put("con_dt1", new Timestamp(t.getTime()-7*24 * 60 * 60 * 1000));
		}else{
			map.put("con_dt1", request.getParameter("excelcon3"));
		}
		if("".equals(request.getParameter("excelcon4"))){
			map.put("con_dt2", t);
		}else{
			map.put("con_dt2", request.getParameter("excelcon4"));
		}
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){//企业
			map.put("qyid",(UserUtil.getCurrentShiroUser().getQyid()));
		}
		if(!"9".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			map.put("xzqy",UserUtil.getCurrentShiroUser().getXzqy());
		}
		xwaqGctjfxService.exportExcel(response, map);
		
	}
	
	/**
	 * 显示所有列
	 * @param id,model
	 */
	@RequiresPermissions("xwaq:gctjfx:excel")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model, HttpServletRequest request) {
		return "model/common/formexcel";
	}
	
	/**
	 * 查询部门
	 * 
	 * @param request
	 */
	@RequestMapping(value = "bmjson")
	@ResponseBody
	public String getBmList(HttpServletRequest request) {
		return xwaqGctjfxService.findQybmList(UserUtil.getCurrentShiroUser().getQyid());
		
	}
}
