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

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.ERM_ExercisePlanEntity;
import com.cczu.model.service.ErmYljhService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 演练计划信息controller
 * 
 * @author jason
 * @date 2017年7月1日
 */
@Controller
@RequestMapping("erm/yljh")
public class PageErmYljhController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private ErmYljhService ermYljhService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;

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
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type", 2);
				else
					model.addAttribute("type", 1);
			}
		}
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
		model.addAttribute("usertype", usertype);
		return "erm/yjyl/yljh/index";
	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("erm:yljh:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		map.putAll(getAuthorityMap());
		map.put("qynm", request.getParameter("view_qyname"));
		map.put("m2", request.getParameter("erm_yljh_cs_m2"));
		map.put("m3", request.getParameter("erm_yljh_cs_m3"));
		return ermYljhService.dataGrid(map);

	}
	
	/**
	 * 导出Excel
	 * 
	 * @param request
	 */
	@RequiresPermissions("erm:yljh:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void getExcel(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qynm", request.getParameter("erm_yljh_qy_name"));
		map.put("dwname", request.getParameter("erm_yljh_cs_name"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			map.put("qyid",(UserUtil.getCurrentShiroUser().getQyid()));
		}
		ermYljhService.exportExcel(response, map);
		
	}

	/**
	 * 显示所有列
	 * @param id,model
	 */
	@RequiresPermissions("erm:yljh:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","erm/yjyl/yljh/export");
		return "common/formexcel";
	}
	
	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("erm:yljh:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "erm/yjyl/yljh/form";
	}

	/**
	 * 添加演练计划信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("erm:yljh:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(ERM_ExercisePlanEntity ee, Model model) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		ee.setS1(t);
		ee.setS2(t);
		ee.setS3(0);
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			ee.setQyid(UserUtil.getCurrentShiroUser().getQyid());
		}
		ermYljhService.addInfo(ee);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id
	 *            ,model
	 */
	@RequiresPermissions("erm:yljh:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		// 查询选择的演练计划信息

		long id1 = id;
		ERM_ExercisePlanEntity ee = ermYljhService.findById(id1);
		model.addAttribute("res", ee);
		// 返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "erm/yjyl/yljh/form";
	}

	/**
	 * 修改演练计划信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("erm:yljh:update")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(ERM_ExercisePlanEntity ee, Model model) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		ee.setS2(t);
		ermYljhService.updateInfo(ee);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除演练计划信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("erm:yljh:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			ermYljhService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("erm:yljh:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		//查询选择的产品信息
		 
		long id1 = id;
		ERM_ExercisePlanEntity ee = ermYljhService.findById(id1);
		model.addAttribute("res", ee);
		//返回页面
		model.addAttribute("action", "view");
		return "erm/yjyl/yljh/view";
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
	@RequiresPermissions("erm:yljh:exin")
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
			resultmap = ermYljhService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}
	
}
