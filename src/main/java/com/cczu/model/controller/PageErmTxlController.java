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
import com.cczu.model.entity.ERM_EmergencyContactsEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.IErmTxlService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 通讯录信息controller
 * 
 * @author jason
 * @date 2017年7月1日
 */
@Controller
@RequestMapping("erm/txl")
public class PageErmTxlController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IErmTxlService ermTxlService;
	@Autowired
	private UserService userService;
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
				if(be.getIsbloc()!=null&&be.getIsbloc()==1){//判断是否为集团公司
					model.addAttribute("usertype", 5);
					return "erm/txl/index";
				}
				else{
					model.addAttribute("usertype", sessionuser.getUsertype());
					return "erm/txl/index";
				}
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			model.addAttribute("usertype", sessionuser.getUsertype());
			return "erm/txl/index";
		}
	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("erm:txl:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		String cxtype=request.getParameter("cx_type_con");
		map.put("qyname", request.getParameter("erm_qyname"));
		map.put("xm", request.getParameter("erm_xm_con"));
		map.put("cxtype", cxtype);
		map.putAll(getAuthorityMap());
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
		map.put("usertype", usertype);
		return ermTxlService.dataGrid(map);

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
		@RequiresPermissions("erm:txl:exin")
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
				resultmap = ermTxlService.exinExcel(map);
			} else {
				resultmap.put("returncode", -2);
			}
			return JsonMapper.toJsonString(resultmap);
		}
	
	/**
	 * 导出Excel
	 * 
	 * @param request
	 */
	@RequiresPermissions("erm:txl:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void getExcel(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String cxtype=request.getParameter("cx_type_con");
		map.put("xm", request.getParameter("erm_xm_con"));
		map.put("cxtype", cxtype);
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.put("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		map.putAll(getAuthorityMap());
		ermTxlService.exportExcel(response, map);

	}

	/**
	 * 显示所有列
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("erm:txl:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","erm/txl/export");
		return "common/formexcel";
	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("erm:txl:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "erm/txl/form";
	}

	/**
	 * 添加通讯录信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("erm:txl:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(ERM_EmergencyContactsEntity ee, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
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
		ermTxlService.addInfo(ee);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id
	 *            ,model
	 */
	@RequiresPermissions("erm:txl:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		// 查询选择的通讯录信息
		long id1 = id;
		ERM_EmergencyContactsEntity ee = ermTxlService.findById(id1);
		model.addAttribute("txl", ee);
		// 返回页面
		model.addAttribute("action", "update");
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
		model.addAttribute("usertype", usertype);
		return "erm/txl/form";
	}

	/**
	 * 修改通讯录信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("erm:txl:update")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(ERM_EmergencyContactsEntity ee, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		ee.setS2(t);
		ee.setID1(UserUtil.getCurrentUser().getId());
		ermTxlService.updateInfo(ee);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除通讯录信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("erm:txl:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			ermTxlService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("erm:txl:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		// 查询选择的产品信息

		long id1 = id;
		ERM_EmergencyContactsEntity ee = ermTxlService.findById(id1);
		model.addAttribute("txl", ee);
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
		model.addAttribute("usertype", usertype);
		// 返回页面
		model.addAttribute("action", "view");
		return "erm/txl/view";
	}
}
