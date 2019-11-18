package com.cczu.model.controller;

import java.text.ParseException;
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

import com.cczu.model.service.IXwaqGcjlService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 观察记录controller
 * 
 * @author jason
 * @date 2017年7月1日
 */
@Controller
@RequestMapping("xwaq/gcjl")
public class PageXwaqGcjlController extends BaseController {
	@Autowired
	private IXwaqGcjlService xwaqGcjlService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		return "model/xwaq/gcjl/index";
	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("xwaq:gcjl:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		int pageNo = 1; // 当前页码
		int pageSize = 50; // 每页行数
		if (StringUtils.isNotEmpty(request.getParameter("page")))
			pageNo = Integer.valueOf(request.getParameter("page"));
		if (StringUtils.isNotEmpty(request.getParameter("rows")))
			pageSize = Integer.valueOf(request.getParameter("rows"));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);
		map.put("cxtype", request.getParameter("cx_type_con"));
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){//企业
			map.put("qyid",(UserUtil.getCurrentShiroUser().getQyid()));
		}
		if(!"9".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			map.put("xzqy",UserUtil.getCurrentShiroUser().getXzqy());
		}
		return xwaqGcjlService.dataGrid(map);

	}
	
	/**
	 * 导出Excel
	 * 
	 * @param request
	 */
	@RequiresPermissions("xwaq:gcjl:excel")
	@RequestMapping(value = "excel")
	@ResponseBody
	public void getExcel(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cxtype", request.getParameter("excelcon1"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){//企业
			map.put("qyid",(UserUtil.getCurrentShiroUser().getQyid()));
		}
		if(!"9".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			map.put("xzqy",UserUtil.getCurrentShiroUser().getXzqy());
		}
		xwaqGcjlService.exportExcel(response, map);
		
	}
	
	/**
	 * 显示所有列
	 * @param id,model
	 */
	@RequiresPermissions("xwaq:gcjl:excel")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		return "model/common/formexcel";
	}
	
	/**
	 * 删除观察记录
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("xwaq:gcjl:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			xwaqGcjlService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("xwaq:gcjl:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		//查询选择的产品信息
		 
		long id1 = id;
		Map<String,Object> ee = xwaqGcjlService.findByIdForView(id1);
		model.addAttribute("res", ee);
		//返回页面
		model.addAttribute("action", "view");
		return "model/xwaq/gcjl/view";
	}
}
