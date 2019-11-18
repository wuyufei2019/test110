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

import com.cczu.model.entity.ERM_EmergencyResTeamEntity;
import com.cczu.model.service.IErmYjdwService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 应急队伍信息controller
 * 
 * @author jason
 * @date 2017年7月1日
 */
@Controller
@RequestMapping("erm/yjdw")
public class PageErmYjdwController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IErmYjdwService ermYjdwService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {

		return "erm/yjdw/index";
	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("erm:yjdw:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		map.put("dwname", request.getParameter("erm_yjdw_cs_name"));
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			map.put("qyid",(UserUtil.getCurrentShiroUser().getQyid()));
		}
		/*if ("0".equals(user.getUsertype())){
			map.put("xzqy",user.getXzqy());
		}*/
		return ermYjdwService.dataGrid(map);

	}
	
	/**
	 * 导出Excel
	 * 
	 * @param request
	 */
	@RequiresPermissions("erm:yjdw:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void getExcel(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dwname", request.getParameter("erm_yjdw_cs_name"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			map.put("qyid",(UserUtil.getCurrentShiroUser().getQyid()));
		}
		ermYjdwService.exportExcel(response, map);
		
	}

	/**
	 * 显示所有列
	 * @param id,model
	 */
	@RequiresPermissions("erm:yjdw:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","erm/yjdw/export");
		return "common/formexcel";
	}
	
	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("erm:yjdw:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "erm/yjdw/form";
	}

	/**
	 * 添加应急队伍信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("erm:yjdw:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(ERM_EmergencyResTeamEntity ee, Model model) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		ee.setS1(t);
		ee.setS2(t);
		ee.setS3(0);
		ee.setM9(t);
		ee.setID1(UserUtil.getCurrentUser().getId());
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			ee.setQyid(UserUtil.getCurrentShiroUser().getQyid());
		}
		ermYjdwService.addInfo(ee);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id
	 *            ,model
	 */
	@RequiresPermissions("erm:yjdw:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		// 查询选择的应急队伍信息

		long id1 = id;
		ERM_EmergencyResTeamEntity ee = ermYjdwService.findById(id1);
		model.addAttribute("res", ee);
		model.addAttribute("ydlx", ee.getM10());
		// 返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "erm/yjdw/form";
	}

	/**
	 * 修改应急队伍信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("erm:yjdw:update")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(ERM_EmergencyResTeamEntity ee, Model model) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		ee.setS2(t);
		ee.setID1(UserUtil.getCurrentUser().getId());
		ermYjdwService.updateInfo(ee);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除应急队伍信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("erm:yjdw:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			ermYjdwService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("erm:yjdw:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		//查询选择的产品信息
		 
		long id1 = id;
		ERM_EmergencyResTeamEntity ee = ermYjdwService.findById(id1);
		model.addAttribute("res", ee);
		model.addAttribute("ydlx", ee.getM10());
		//返回页面
		model.addAttribute("action", "view");
		return "erm/yjdw/view";
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
	@RequiresPermissions("erm:yjdw:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			if(UserUtil.getCurrentShiroUser().getUsertype().equals("1")){
				map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			}
			map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
			map.put("usertype", UserUtil.getCurrentShiroUser().getUsertype());
			resultmap = ermYjdwService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}
	
	
	//地图查看页面跳转
	@RequiresPermissions("erm:yjdw:map")
	@RequestMapping(value = "map", method = RequestMethod.GET)
	public String Map(Model model) {
		model.addAttribute("action", "map");
		return "erm/yjdw/mapindex";
	}
	/**
	 * 地图坐标
	 */
	@RequiresPermissions("erm:yjdw:map")
	@RequestMapping(value="maplist")
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request) {
		Map<String, Object> mapdata = new HashMap<String, Object>();
		List<Map<String,Object>> list=ermYjdwService.findMapList(mapdata);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		return map;
	}
	
}
