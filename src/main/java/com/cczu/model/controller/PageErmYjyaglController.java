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

import com.cczu.model.entity.AQJG_SafetyRecord;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.AqjgAqbaService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.IErmYjyaglService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 应急预案管理信息controller
 * 
 * @author jason
 * @date 2017年7月1日
 */
@Controller
@RequestMapping("erm/yjyagl")
public class PageErmYjyaglController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IErmYjyaglService ermYjyaglService;
	@Autowired
	private AqjgAqbaService aqjgAqbaService;
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
		if(sessionuser.getUsertype().equals("1")){
			//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null){
				model.addAttribute("usertype", sessionuser.getUsertype());
				return "erm/yjyagl/index";
			}else{
				//未添加企业基本信息错误提示页面
				return "../error/001";
			}
		}else {//非企业用户页面
			model.addAttribute("usertype", sessionuser.getUsertype());
			return "erm/yjyagl/index";
		}
	}

	/**
	 * 重大危险源监测预警-应急预案（只查看数据）
	 *
	 * @param model
	 */
	@RequestMapping(value = "viewindex")
	public String viewindex(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		model.addAttribute("usertype", sessionuser.getUsertype());
		return "erm/yjyagl/viewindex";
	}
	
	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("erm:yjyagl:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		String cxtype=request.getParameter("cx_type_con");
		map.put("number", request.getParameter("number"));
		map.put("cxtype", cxtype);
		map.putAll(getAuthorityMap());
		map.put("usertype", UserUtil.getCurrentShiroUser().getUsertype());//0安监 1 企业
		return ermYjyaglService.dataGrid(map);

	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("erm:yjyagl:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		//ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		//用户类型(1企业，0安监局，9系统管理员)		
//		if(sessionuser.getUsertype().equals("1")){
//			model.addAttribute("id1",sessionuser.getQyid());
//		}
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "erm/yjyagl/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequiresPermissions("erm:yjyagl:add")
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
		aqba.setUserid(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));
		aqba.setM3("应急预案");
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
		if(UserUtil.getCurrentShiroUser().getUsertype().equals("1")){
			model.addAttribute("id1", aqba.getID1());
		}
		model.addAttribute("aqbalist", aqba);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "erm/yjyagl/form";
	}

	/**
	 * 修改安全备案信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQJG_SafetyRecord aqba, Model model){
		String datasuccess="success";
		aqba.setUserid(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));	
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
	@RequiresPermissions("erm:yjyagl:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		AQJG_SafetyRecord obj = ermYjyaglService.findById((long)id);
		model.addAttribute("obj", obj);
		//返回页面
		model.addAttribute("action", "view");
		return "erm/yjyagl/view";
	}
	
	/**
	 * 导出Excel
	 * 
	 * @param request
	 */
	@RequiresPermissions("erm:yjyagl:export")
	@RequestMapping(value = "excel")
	@ResponseBody
	public void getExcel(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String cxtype=request.getParameter("cx_type_con");
		map.put("cxtype", cxtype);
		map.put("qyname", request.getParameter("qyname"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.put("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		map.putAll(getAuthorityMap());
		ermYjyaglService.exportExcel(request, response, map);
		
	}

	/**
	 * 显示所有列
	 * @param id,model
	 */
	@RequiresPermissions("erm:yjyagl:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","erm/yjyagl/excel");
		return "common/formexcel";
	}
}
