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

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.ERM_EmergencyMateEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.IErmYjwzService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 应急物资信息controller
 * 
 * @author jason
 * @date 2017年7月1日
 */
@Controller
@RequestMapping("erm/yjwz")
public class PageErmYjwzController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IErmYjwzService ermYjwzService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private UserService userService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1){//判断是否为集团公司
					model.addAttribute("usertype", 5);
					return "erm/yjwz/index";
				}
				else{
					model.addAttribute("usertype", sessionuser.getUsertype());
					return "erm/yjwz/index";
				}
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			model.addAttribute("usertype", sessionuser.getUsertype());
			return "erm/yjwz/index";
		}
	}

	/**
	 * 重大危险源监测预警-应急物资（只查看数据）
	 *
	 * @param model
	 */
	@RequestMapping(value = "viewindex")
	public String viewindex(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		model.addAttribute("usertype", sessionuser.getUsertype());
		return "erm/yjwz/viewindex";
	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("erm:yjwz:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("erm_qyname"));
		map.put("wzname", request.getParameter("erm_yjwz_wz_name"));
		map.put("cxtype", request.getParameter("cx_type_con"));
		map.putAll(getAuthorityMap());
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
		map.put("usertype",usertype);
		return ermYjwzService.dataGrid(map);

	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("erm:yjwz:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			BIS_EnterpriseEntity be=bisQyjbxxService.findInfoById(UserUtil.getCurrentShiroUser().getQyid());
			model.addAttribute("locx", be.getM16());
			model.addAttribute("locy", be.getM17());
		}
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
		model.addAttribute("usertype", usertype);
		return "erm/yjwz/form";
	}

	/**
	 * 添加应急物资信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("erm:yjwz:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(ERM_EmergencyMateEntity ee, Model model) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		ee.setS1(t);
		ee.setS2(t);
		ee.setS3(0);
		ee.setID1(UserUtil.getCurrentUser().getId());
		if ("1".equals(UserUtil.getCurrentShiroUser().getUsertype())) {
			ee.setQyid(UserUtil.getCurrentShiroUser().getQyid());
		}else if(UserUtil.getCurrentShiroUser().getUsertype().equals("0")||UserUtil.getCurrentShiroUser().getUsertype().equals("9")){
			
		}else{
			return "请先完善企业基本信息";
		}
		ee.setUserid(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));
		ermYjwzService.addInfo(ee);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id
	 *            ,model
	 */
	@RequiresPermissions("erm:yjwz:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		// 查询选择的应急物资信息

		long id1 = id;
		ERM_EmergencyMateEntity ee = ermYjwzService.findById(id1);
		model.addAttribute("res", ee);
		model.addAttribute("ydlx", ee.getM12());
		// 返回页面
		model.addAttribute("action", "update");
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			BIS_EnterpriseEntity be=bisQyjbxxService.findInfoById(UserUtil.getCurrentShiroUser().getQyid());
			model.addAttribute("locx", be.getM16());
			model.addAttribute("locy", be.getM17());
		}
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
		model.addAttribute("usertype", usertype);
		return "erm/yjwz/form";
	}

	/**
	 * 修改应急物资信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("erm:yjwz:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(ERM_EmergencyMateEntity ee, Model model) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		ee.setS2(t);
		ee.setID1(UserUtil.getCurrentUser().getId());
		ermYjwzService.updateInfo(ee);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除应急物资信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("erm:yjwz:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			ermYjwzService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("erm:yjwz:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		//查询选择的产品信息
		long id1 = id;
		ERM_EmergencyMateEntity ee = ermYjwzService.findById(id1);
		model.addAttribute("res", ee);
		model.addAttribute("ydlx", ee.getM12());
		//返回页面
		model.addAttribute("action", "view");
		return "erm/yjwz/view";
	}
	
	/**
	 * 导出Excel
	 * 
	 * @param request
	 */
	@RequiresPermissions("erm:yjwz:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void getExcel(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		String cxtype=request.getParameter("cx_type_con");
		map.put("wzname", request.getParameter("erm_yjwz_wz_name"));
		map.put("cxtype", request.getParameter("cx_type_con"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.put("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		if ("1".equals(UserUtil.getCurrentShiroUser().getUsertype())) {// 企业
			map.put("qyid", (UserUtil.getCurrentShiroUser().getQyid()));
		}else if(!"9".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			User u=userService.getUserById(UserUtil.getCurrentUser().getId());
			map.put("xzqy", u.getXzqy());
			if("2".equals(cxtype)){
				if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0){
					map.put("jglx",sessionuser.getUserroleflg());
				}
			}
		}
		ermYjwzService.exportExcel(response, map);
		
	}

	/**
	 * 显示所有列
	 * @param id,model
	 */
	@RequiresPermissions("erm:yjwz:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","erm/yjwz/export");
		return "common/formexcel";
	}
	
	
	@RequiresPermissions("erm:yjwz:view")
	@RequestMapping(value="json")
	@ResponseBody
	public String getDataJson(Double lng,Double lat,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		int pageNo = 1; // 当前页码
		int pageSize = 50; // 每页行数
		if (StringUtils.isNotEmpty(request.getParameter("page")))
			pageNo = Integer.valueOf(request.getParameter("page"));
		if (StringUtils.isNotEmpty(request.getParameter("rows")))
			pageSize = Integer.valueOf(request.getParameter("rows"));

		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);

		map.put("lng",lng);
		map.put("lat",lat);
		map.put("juli",50000);
		map.putAll(getAuthorityMap());
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
		map.put("usertype",usertype);
		return ermYjwzService.findYjwz(map);
		
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
	@RequiresPermissions("erm:yjzb:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			if(UserUtil.getCurrentShiroUser().getUsertype().equals("1")){
				map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			}
			map.put("usertype", UserUtil.getCurrentShiroUser().getUsertype());
			resultmap = ermYjwzService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}
	
	//地图查看页面跳转
	@RequiresPermissions("erm:yjdw:map")
	@RequestMapping(value = "map/{cxtype}", method = RequestMethod.GET)
	public String Map(@PathVariable("cxtype") String cxtype,Model model) {
		model.addAttribute("action", "map");
		model.addAttribute("cxtype", cxtype);
		return "erm/yjwz/mapindex";
	}
	/**
	 * 地图坐标
	 */
	@RequiresPermissions("erm:yjdw:map")
	@RequestMapping(value="maplist/{cxtype}")
	@ResponseBody
	public Map<String, Object> list(@PathVariable("cxtype") String cxtype,HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Map<String, Object> mapdata = new HashMap<String, Object>();
		mapdata.put("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		mapdata.put("cxtype",cxtype);
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){//企业
			mapdata.put("qyid",(UserUtil.getCurrentShiroUser().getQyid()));
		}else if("0".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			User u=userService.getUserById(UserUtil.getCurrentUser().getId());
			mapdata.put("xzqy", u.getXzqy());
			if("2".equals(cxtype)){
				if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0){
					mapdata.put("jglx",sessionuser.getUserroleflg());
				}
			}
		}
		List<Map<String,Object>> list=ermYjwzService.findMapList(mapdata);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		return map;
	}
}
