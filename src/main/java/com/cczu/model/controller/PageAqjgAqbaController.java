package com.cczu.model.controller;

import java.text.ParseException;
import java.util.ArrayList;
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

import com.cczu.model.entity.AQJG_SafetyRecord;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.AqjgAqbaService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 安全备案controller
 */
@Controller
@RequestMapping("aqjg/aqba")
public class PageAqjgAqbaController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private AqjgAqbaService aqjgAqbaService;

	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		if(be==null){
			// 获取用户角色
			List<Role> list = roleService.findRoleById(sessionuser.getId());
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					//判断用户角色，如果包含有企业用户角色，则转到企业用户显示的界面
					if (list.get(i).getRoleCode().equals("company")||list.get(i).getRoleCode().equals("companyadmin"))
						return "../error/001";
				}
			}
		}
		return "aqjg/aqba/index";
	}

	/**
	 * 评价报告列表显示页面
	 * @param model
	 */
	@RequestMapping(value="pjindex")
	public String aqpjindex(Model model,HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		if(be==null){
			// 获取用户角色
			List<Role> list = roleService.findRoleById(sessionuser.getId());
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					//判断用户角色，如果包含有企业用户角色，则转到企业用户显示的界面
					if (list.get(i).getRoleCode().equals("company")||list.get(i).getRoleCode().equals("companyadmin"))
						return "../error/001";
				}
			}
		}
		if(request.getParameter("sys")!=null &&"1".equals(request.getParameter("sys")))
			model.addAttribute("sys", "sys");
		return "qyxx/appraisalreport/index";
	}

	/**
	 * list页面
	 * @param request
	 */
	@RequiresPermissions("aqjg:aqba:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		//获取企业id
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("aqjg_aqba_cx_qyname"));
		map.put("m3", request.getParameter("aqjg_aqba_cx_m3"));
		map.put("m4", request.getParameter("aqjg_aqba_cx_m4"));
		if ("1".equals(UserUtil.getCurrentShiroUser().getUsertype())) {// 企业
			map.put("qyid", (UserUtil.getCurrentShiroUser().getQyid()));
		}
		if ("0".equals(UserUtil.getCurrentShiroUser().getUsertype())) {
			map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
				map.put("jglx",sessionuser.getUserroleflg());
		}
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
		map.put("usertype", usertype);
		return aqjgAqbaService.dataGrid(map);

	}

	/**
	 * list页面
	 * @param request
	 */
	@RequiresPermissions("aqjg:aqba:view")
	@RequestMapping(value="pjlist")
	@ResponseBody
	public Map<String, Object> getData2(HttpServletRequest request) {

		//获取企业id
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("aqjg_aqba_cx_qyname"));
		map.put("m3", request.getParameter("aqjg_aqba_cx_m3"));
		map.put("m4", request.getParameter("aqjg_aqba_cx_m4"));
		map.put("expiration", request.getParameter("aqjg_aqba_expiration"));
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
		String xzqy= request.getParameter("xzqy");
		map.put("xzqy",xzqy);
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("0")){//安监局
			if(xzqy==null||xzqy.equals("")||xzqy.length()<sessionuser.getXzqy().length())
				map.put("xzqy",sessionuser.getXzqy());
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
				map.put("jglx",sessionuser.getUserroleflg());
		}
		else if(sessionuser.getUsertype().equals("5")){//第三方机构
			map.put("cjz",sessionuser.getId());
		}
		map.put("usertype", usertype);
		return aqjgAqbaService.dataGrid2(map);

	}

	/**
	 * 企业信息tab页list页面
	 * @param request
	 */
	@RequiresPermissions("aqjg:aqba:view")
	@RequestMapping(value="pjtablist/{qyid}")
	@ResponseBody
	public Map<String, Object> getPjTabList(@PathVariable("qyid") Integer qyid,HttpServletRequest request) {

		//获取企业id
		Map<String, Object> map = getPageMap(request);
		map.put("qyid", qyid);
		map.put("usertype", 1);
		return aqjgAqbaService.dataGrid2(map);
	}


	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("aqjg:aqba:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		//用户类型(1企业，0安监局，9系统管理员)
		if(sessionuser.getUsertype().equals("1")){
			model.addAttribute("id1",sessionuser.getQyid());
		}
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "aqjg/aqba/form";
	}

	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequiresPermissions("aqjg:aqba:add")
	@RequestMapping(value = "create" )
	@ResponseBody
	public String create(AQJG_SafetyRecord aqba, HttpServletRequest request) {
		String datasuccess="success";

		//用户类型(1企业，0安监局，9系统管理员)
		if(UserUtil.getCurrentShiroUser().getUsertype().equals("1")){
			aqba.setID1(UserUtil.getCurrentShiroUser().getQyid());
		}else if(UserUtil.getCurrentShiroUser().getUsertype().equals("0")||UserUtil.getCurrentShiroUser().getUsertype().equals("9")){

		}else{
			return "请先完善企业基本信息";
		}
		aqjgAqbaService.addInfo(aqba);
		//返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的成员信息
		AQJG_SafetyRecord aqba = aqjgAqbaService.findById(id);
		model.addAttribute("id1", aqba.getID1());
		model.addAttribute("aqbalist", aqba);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "aqjg/aqba/form";
	}

	/**
	 * 修改安全备案信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQJG_SafetyRecord aqba, Model model){
		String datasuccess="success";
//		Date d=new Date();//获取时间
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");//转换格式
//		aqba.setM1(aqba.getM3()+"-"+sdf.format(d)+"-"+aqba.getID());//生成备案编号
		aqjgAqbaService.updateInfo(aqba);
		//返回结果
		return datasuccess;
	}

	/**
	 * 删除安全备案信息
	 *
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";

		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			aqjgAqbaService.deleteInfo(Long.parseLong(aids[i]));
		}

		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		//查询选择的安全备案信息
		Map<String, Object> bt = aqjgAqbaService.findInfoById(id);
		model.addAttribute("aqbalist", bt);
		//返回页面
		model.addAttribute("action", "view");
		return "aqjg/aqba/view";
	}

	/**
	 * 获取企业list
	 * @param id,model
	 */
	@RequestMapping(value = "qylist")
	@ResponseBody
	public String getUserJson() {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		List<Map<String, Object>> qynmList=new ArrayList<Map<String, Object>>();
		Integer jglx = 0;
		if(sessionuser.getUsertype().equals("0")){
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0){
				jglx = sessionuser.getUserroleflg();
			}
			qynmList = aqjgAqbaService.findQynmList(sessionuser.getXzqy(),"aj",jglx);
		}
		else if(sessionuser.getUsertype().equals("5")){
			qynmList = aqjgAqbaService.findQynmList(sessionuser.getId().toString(),"dsf",jglx);
		}
		return JsonMapper.getInstance().toJson(qynmList);
	}

	/**
	 * 导出Excel
	 *
	 * @param request
	 */
	@RequiresPermissions("aqjg:aqba:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void getExcel(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyname", request.getParameter("aqjg_aqba_cx_qyname"));
		map.put("m3", request.getParameter("aqjg_aqba_cx_m3"));
		map.put("m4", request.getParameter("aqjg_aqba_cx_m4"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.put("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		if ("1".equals(UserUtil.getCurrentShiroUser().getUsertype())) {// 企业
			map.put("qyid", (UserUtil.getCurrentShiroUser().getQyid()));
		}
		if (!"9".equals(UserUtil.getCurrentShiroUser().getUsertype())) {
			map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
				map.put("jglx",sessionuser.getUserroleflg());
		}
		aqjgAqbaService.exportExcel(response, map);

	}

	/**
	 * 显示所有列
	 *
	 * @param id,model
	 */
	@RequiresPermissions("aqjg:aqba:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","aqjg/aqba/export");
		return "common/formexcel";
	}

	/**
	 * 评价报告导出Excel
	 *
	 * @param request
	 */
	@RequiresPermissions("aqjg:aqba:export")
	@RequestMapping(value = "export2")
	@ResponseBody
	public void getExcel2(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyname", request.getParameter("aqjg_aqba_cx_qyname"));
		map.put("m3", request.getParameter("aqjg_aqba_cx_m3"));
		map.put("m4", request.getParameter("aqjg_aqba_cx_m4"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.put("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		if ("1".equals(UserUtil.getCurrentShiroUser().getUsertype())) {// 企业
			map.put("qyid", (UserUtil.getCurrentShiroUser().getQyid()));
		}
		if (!"9".equals(UserUtil.getCurrentShiroUser().getUsertype())) {
			map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
				map.put("jglx",sessionuser.getUserroleflg());
		}
		aqjgAqbaService.exportExcel2(response, map);

	}

	/**
	 * 评价报告显示所有列
	 *
	 * @param id,model
	 */
	@RequiresPermissions("aqjg:aqba:export")
	@RequestMapping(value = "colindex2", method = RequestMethod.GET)
	public String colindex2(Model model) {
		model.addAttribute("url","aqjg/aqba/export2");
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
	@RequiresPermissions("bis:cjxx:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			resultmap = aqjgAqbaService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}
}
