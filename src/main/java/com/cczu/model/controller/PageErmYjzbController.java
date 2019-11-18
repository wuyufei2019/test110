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
import com.cczu.model.entity.ERM_EmergencyResInstrumentEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.IErmYjzbService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 应急装备信息controller
 * 
 * @author jason
 * @date 2017年7月1日
 */
@Controller
@RequestMapping("erm/yjzb")
public class PageErmYjzbController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IErmYjzbService ermYjzbService;
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
					return "erm/yjzb/index";
				}
				else{
					model.addAttribute("usertype", sessionuser.getUsertype());
					return "erm/yjzb/index";
				}
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			model.addAttribute("usertype", sessionuser.getUsertype());
			return "erm/yjzb/index";
		}
	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("erm:yjzb:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		String cxtype=request.getParameter("cx_type_con");
		map.put("qyname", request.getParameter("erm_qyname"));
		map.put("zbname", request.getParameter("erm_yjzb_zb_name"));
		map.put("cxtype", cxtype);
		map.putAll(getAuthorityMap());
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
		map.put("usertype",usertype);
		return ermYjzbService.dataGrid(map);

	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("erm:yjzb:add")
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
		return "erm/yjzb/form";
	}

	/**
	 * 添加应急装备信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("erm:yjzb:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(ERM_EmergencyResInstrumentEntity ee, Model model) {
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
		ermYjzbService.addInfo(ee);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id
	 *            ,model
	 */
	@RequiresPermissions("erm:yjzb:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		// 查询选择的应急装备信息
		long id1 = id;
		ERM_EmergencyResInstrumentEntity ee = ermYjzbService.findById(id1);
		model.addAttribute("res", ee);
		model.addAttribute("ydlx", ee.getM12());
		// 返回页面
		model.addAttribute("action", "update");
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			BIS_EnterpriseEntity be=bisQyjbxxService.findInfoById(UserUtil.getCurrentShiroUser().getQyid());
			model.addAttribute("locx", be.getM16());
			model.addAttribute("locy", be.getM17());
		}
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "erm/yjzb/form";
	}

	/**
	 * 修改应急装备信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("erm:yjzb:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(ERM_EmergencyResInstrumentEntity ee, Model model) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		ee.setS2(t);
		ee.setID1(UserUtil.getCurrentUser().getId());
		ee.setUserid(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));
		ermYjzbService.updateInfo(ee);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除应急装备信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("erm:yjzb:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			ermYjzbService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("erm:yjzb:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		//查询选择的产品信息
		long id1 = id;
		ERM_EmergencyResInstrumentEntity ee = ermYjzbService.findById(id1);
		model.addAttribute("res", ee);
		model.addAttribute("ydlx", ee.getM12());
		//返回页面
		return "erm/yjzb/view";
	}
	
	/**
	 * 导出Excel
	 * 
	 * @param request
	 */
	@RequiresPermissions("erm:yjzb:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void getExcel(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("zbname", request.getParameter("erm_yjzb_zb_name"));
		map.put("cxtype", request.getParameter("cx_type_con"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.put("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		map.putAll(getAuthorityMap());
		ermYjzbService.exportExcel(response, map);
		
	}

	/**
	 * 显示所有列
	 * @param id,model
	 */
	@RequiresPermissions("erm:yjzb:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","erm/yjzb/export");
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
			resultmap = ermYjzbService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}
	
	
	//地图查看页面跳转
	@RequiresPermissions("erm:yjzb:map")
	@RequestMapping(value = "map/{cxtype}", method = RequestMethod.GET)
	public String Map(@PathVariable("cxtype") String cxtype,Model model) {
		model.addAttribute("action", "map");
		model.addAttribute("cxtype", cxtype);
		return "erm/yjzb/mapindex";
	}
	/**
	 * 地图坐标
	 */
	@RequiresPermissions("erm:yjzb:map")
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
		List<Map<String,Object>> list=ermYjzbService.findMapList(mapdata);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		return map;
	}
	
}
