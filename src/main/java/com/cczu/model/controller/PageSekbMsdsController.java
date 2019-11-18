package com.cczu.model.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.cczu.model.entity.TMESK_MsdsEntity;
import com.cczu.model.service.ISekbMsdsService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.service.RoleService;

/**
 * Msds信息controller
 * 
 * @author jason
 * @date 2017年7月1日
 */
@Controller
@RequestMapping("sekb/msds")
public class PageSekbMsdsController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private ISekbMsdsService sekbMsdsService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {

		return "sekb/msds/index";
	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("sekb:msds:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		map.put("zwname", request.getParameter("sekb_msds_zw_name"));
		map.put("zwname2", request.getParameter("sekb_msds_zw_name2"));

		return sekbMsdsService.dataGrid(map);

	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("sekb:msds:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		//查询选择的产品信息
		long id1 = id;
		TMESK_MsdsEntity tm = sekbMsdsService.findById(id1);
		model.addAttribute("res", tm);
		//返回页面
		model.addAttribute("action", "view");
		return "sekb/msds/view";
	}
	
	
	/**
	 * 导出Excel
	 * 
	 * @param request
	 */
	@RequiresPermissions("sekb:msds:excel")
	@RequestMapping(value = "excel")
	@ResponseBody
	public void getExcel(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("zwname", request.getParameter("sekb_msds_zw_name"));
		map.put("zwname2", request.getParameter("sekb_msds_zw_name2"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		sekbMsdsService.exportExcel(response, map);
		
	}
	
	/**
	 * 显示所有列
	 * @param id,model
	 */
	@RequiresPermissions("sekb:msds:excel")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","sekb/msds/excel");
		return "common/formexcel";
	}
	
	
	@RequestMapping(value="json")
	@ResponseBody
	public String getDataJson( HttpServletRequest request) {
		List<TMESK_MsdsEntity> list=sekbMsdsService.findByWzName(request.getParameter("name"));
		if(list!=null&&list.size()>0)
			return JsonMapper.getInstance().toJson(list.get(0));
		else{
			Map<String, Object> map=new HashMap<>();
			map.put("m1", "");
			map.put("m10", "");
			map.put("m20", "");
			return JsonMapper.getInstance().toJson(map);
		}
		
	}
	
	@RequestMapping(value="listjson")
	@ResponseBody
	public String getAllDataJson( HttpServletRequest request) {
		List<Map<String,Object>> list=sekbMsdsService.listAll();
		return  JsonMapper.getInstance().toJson(list);
	}
}
