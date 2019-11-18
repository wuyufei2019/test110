package com.cczu.model.controller;

import java.sql.Timestamp;
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

import com.cczu.model.entity.XWAQ_UnsafebehaviorEntity;
import com.cczu.model.service.IXwaqBaqxwglService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 不安全行为管理controller
 * 
 * @author jason
 * @date 2017年7月1日
 */
@Controller
@RequestMapping("xwaq/baqxwgl")
public class PageXwaqBaqxwglController extends BaseController {
	@Autowired
	private IXwaqBaqxwglService xwaqBaqxwglService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		return "model/xwaq/baqxwgl/index";
	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("xwaq:baqxwgl:view")
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
		return xwaqBaqxwglService.dataGrid(map);

	}
	
	/**
	 * 导出Excel
	 * 
	 * @param request
	 */
	@RequiresPermissions("xwaq:baqxwgl:excel")
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
		xwaqBaqxwglService.exportExcel(response, map);
		
	}
	
	
	/**
	 * 显示所有列
	 * @param id,model
	 */
	@RequiresPermissions("xwaq:baqxwgl:excel")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		return "model/common/formexcel";
	}
	
	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("xwaq:baqxwgl:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "model/xwaq/baqxwgl/form";
	}

	/**
	 * 添加不安全行为管理
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("xwaq:baqxwgl:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(HttpServletRequest request, Model model) {
		String datasuccess = "保存成功！";
		XWAQ_UnsafebehaviorEntity ee = new XWAQ_UnsafebehaviorEntity();
		Timestamp t=DateUtils.getSysTimestamp();
		ee.setS1(t);
		ee.setS2(t);
		ee.setS3(0);
		ee.setM1(request.getParameter("M1"));
		ee.setM2(request.getParameter("M2"));
		ee.setM3(request.getParameter("M3"));
		ee.setID1(UserUtil.getCurrentUser().getId());
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			ee.setQyid(UserUtil.getCurrentShiroUser().getQyid());
		}
		ee.setXzqy(UserUtil.getCurrentShiroUser().getXzqy());
		xwaqBaqxwglService.addInfo(ee);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id
	 *            ,model
	 */
	@RequiresPermissions("xwaq:baqxwgl:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		// 查询选择的不安全行为管理
		long id1 = id;
		XWAQ_UnsafebehaviorEntity ee = xwaqBaqxwglService.findById(id1);
		model.addAttribute("res", ee);
		// 返回页面
		model.addAttribute("action", "update");
		return "model/xwaq/baqxwgl/form";
	}

	/**
	 * 修改不安全行为管理
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("xwaq:baqxwgl:update")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(HttpServletRequest request, Model model) {
		String datasuccess = "更新成功";
		String M1 = request.getParameter("M1");
		String M2 = request.getParameter("M2");
		String M3 = request.getParameter("M3");
		Long ID = Long.parseLong(request.getParameter("ID"));

		XWAQ_UnsafebehaviorEntity ee = xwaqBaqxwglService.findById(ID);
		ee.setID(ID);
		ee.setM1(M1);
		ee.setM2(M2);
		ee.setM3(M3);
		ee.setID1(UserUtil.getCurrentUser().getId());
		xwaqBaqxwglService.updateInfo(ee);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除不安全行为管理
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("xwaq:baqxwgl:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			xwaqBaqxwglService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("xwaq:baqxwgl:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		//查询选择的产品信息
		 
		long id1 = id;
		XWAQ_UnsafebehaviorEntity ee = xwaqBaqxwglService.findById(id1);
		model.addAttribute("res", ee);
		//返回页面
		model.addAttribute("action", "view");
		return "model/xwaq/baqxwgl/view";
	}
}
