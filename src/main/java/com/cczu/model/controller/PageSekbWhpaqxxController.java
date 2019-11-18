package com.cczu.model.controller;

import java.util.HashMap;
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

import com.cczu.model.entity.TMESK_ChemicalsdirectoryEntity;
import com.cczu.model.service.ISekbWhpaqxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.RoleService;

/**
 * 危化品安全信息controller
 * 
 * @author jason
 * @date 2017年7月1日
 */
@Controller
@RequestMapping("sekb/whpaqxx")
public class PageSekbWhpaqxxController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private ISekbWhpaqxxService sekbWhpaqxxService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {

		return "sekb/whpaqxx/index";
	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("sekb:whpaqxx:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		map.put("pname", request.getParameter("sekb_whpaqxx_p_name"));
		map.put("cas", request.getParameter("cas"));
		map.put("whpm6", request.getParameter("whpm6"));
		map.put("jdp", request.getParameter("jdp"));

		return sekbWhpaqxxService.dataGrid(map);

	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("sekb:whpaqxx:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		//查询选择的产品信息
		long id1 = id;
		TMESK_ChemicalsdirectoryEntity tc = sekbWhpaqxxService.findById(id1);
		model.addAttribute("res", tc);
		//返回页面
		model.addAttribute("action", "view");
		return "sekb/whpaqxx/view";
	}
	
	/**
	 * 导出Excel
	 * 
	 * @param request
	 */
	@RequiresPermissions("sekb:whpaqxx:excel")
	@RequestMapping(value = "excel")
	@ResponseBody
	public void getExcel(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pname", request.getParameter("excelcon1"));
		map.put("cas", request.getParameter("excelcon2"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		sekbWhpaqxxService.exportExcel(response, map);
		
	}
	
	/**
	 * 显示所有列
	 * @param id,model
	 */
	@RequiresPermissions("sekb:whpaqxx:excel")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","sekb/whpaqxx/excel");
		return "common/formexcel";
	}
}
