package com.cczu.model.controller;

import java.sql.Timestamp;
import java.text.ParseException;
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

import com.cczu.model.entity.ERM_EmergencyResPlaceEntity;
import com.cczu.model.service.IErmBncsService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 避难场所信息controller
 * 
 * @author jason
 * @date 2017年7月1日
 */
@Controller
@RequestMapping("erm/bncs")
public class PageErmBncsController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IErmBncsService ermBncsService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {

		return "erm/bncs/index";
	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("erm:bncs:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		map.put("csname", request.getParameter("erm_bncs_cs_name"));

		return ermBncsService.dataGrid(map);

	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("erm:bncs:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
		model.addAttribute("usertype", usertype);
		return "erm/bncs/form";
	}

	/**
	 * 添加避难场所信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("erm:bncs:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(ERM_EmergencyResPlaceEntity ee, Model model) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		ee.setS1(t);
		ee.setS2(t);
		ee.setS3(0);
		ee.setID1(UserUtil.getCurrentUser().getId());

		ermBncsService.addInfo(ee);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id
	 *            ,model
	 */
	@RequiresPermissions("erm:bncs:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		// 查询选择的避难场所信息

		long id1 = id;
		ERM_EmergencyResPlaceEntity ee = ermBncsService.findById(id1);
		model.addAttribute("res", ee);
		model.addAttribute("ydlx", ee.getM10());
		// 返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "erm/bncs/form";
	}

	/**
	 * 修改避难场所信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("erm:bncs:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(ERM_EmergencyResPlaceEntity ee, Model model) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		ee.setS2(t);
		ee.setID1(UserUtil.getCurrentUser().getId());
		ermBncsService.updateInfo(ee);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除避难场所信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("erm:bncs:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			ermBncsService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("erm:bncs:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		//查询选择的产品信息
		 
		long id1 = id;
		ERM_EmergencyResPlaceEntity ee = ermBncsService.findById(id1);
		model.addAttribute("res", ee);
		model.addAttribute("ydlx", ee.getM10());
		//返回页面
		model.addAttribute("action", "view");
		return "erm/bncs/view";
	}
	
	/**
	 * 导出Excel
	 * 
	 * @param request
	 */
	@RequiresPermissions("erm:bncs:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void getExcel(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("csname", request.getParameter("erm_bncs_cs_name"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		ermBncsService.exportExcel(response, map);
	}
	
	/**
	 * 显示所有列
	 * @param id,model
	 */
	@RequiresPermissions("erm:bncs:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","erm/bncs/export");
		return "common/formexcel";
	}
	
	//导入页面跳转
	@RequestMapping(value = "exinjump", method = RequestMethod.GET)
	public String exin(Model model) {
		model.addAttribute("action", "exin");
		return "common/importForm";
	}
	
	/**
	 * 导入
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequiresPermissions("erm:bncs:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			resultmap = ermBncsService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}
	
	//地图查看页面跳转
	@RequiresPermissions("erm:bncs:map")
	@RequestMapping(value = "map", method = RequestMethod.GET)
	public String Map(Model model) {
		model.addAttribute("action", "map");
		return "erm/bncs/mapindex";
	}
	/**
	 * 地图坐标
	 */
	@RequiresPermissions("erm:bncs:map")
	@RequestMapping(value="maplist")
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request) {
		Map<String, Object> mapdata = new HashMap<String, Object>();
		List<Map<String,Object>> list=ermBncsService.findMapList(mapdata);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		return map;
	}
}
