package com.cczu.model.zyaqgl.controller;

import java.sql.Timestamp;
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

import com.cczu.model.zyaqgl.entity.AQGL_ChangeAcceptance;
import com.cczu.model.zyaqgl.service.AqglBgysService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.Department;
import com.cczu.sys.system.service.DepartmentService;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.StringUtils;

/**
 * 作业安全管理-变更验收Controller
 * @author YZH
 */
@Controller
@RequestMapping("zyaqgl/bgys")
public class PageAqglBgysController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private AqglBgysService aqglBgysService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private UserService userService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model,HttpServletRequest request) {
		model.addAttribute("qyid", request.getParameter("qyid"));
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
		model.addAttribute("usertype", usertype);
		return "zyaqgl/bggl/bgys/index";
	}
	
	/**
	 * 获取变更项目下拉框list
	 * bgxmjson  {"id":11,"text":"变更项目名称"}
	 * return String
	 */
	@RequestMapping(value="bgxmjson")
	@ResponseBody
	public String codeIdjson() {
		Map<String, Object> map = new HashMap<String, Object>();
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			map.put("qyid",(UserUtil.getCurrentShiroUser().getQyid()));
		}
		return aqglBgysService.getBgxmjson(map);
	}
	
	/**
	 * 获取企业人员下拉框list
	 * userjson  {"id":11,"text":"变更项目名称"}
	 * return String
	 */
	@RequestMapping(value="userjson")
	@ResponseBody
	public String userjson() {
		Map<String, Object> map = new HashMap<String, Object>();
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			map.put("qyid",(UserUtil.getCurrentShiroUser().getQyid()));
		}
		return userService.getUJsonById2(map);
	}
	
	/**
	 * 企业端list 
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("start", request.getParameter("aqzf_bgys_start"));
		map.put("end", request.getParameter("aqzf_bgys_end"));
		
		String qyid = request.getParameter("qyid");
		if(StringUtils.isEmpty(qyid)){
			if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
				map.put("qyid",(UserUtil.getCurrentShiroUser().getQyid()));
			}
		}else{
			map.put("qyid", qyid);
		}
		return aqglBgysService.dataGrid(map);	
		
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("zyaqgl:bgys:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		//申请人
		model.addAttribute("sqr", UserUtil.getCurrentShiroUser().getName());
		//部门
		Long departid=UserUtil.getCurrentUser().getDepartmen();
		if(departid!=null){
			Department depart=departmentService.findListByBmids(departid+"").get(0);
			model.addAttribute("depart", depart.getM1());
		}
		return "zyaqgl/bggl/bgys/form";
	}
	
	/**
	 * 添加变更验收信息
	 * @param request,model
	 */
	@RequiresPermissions("zyaqgl:bgys:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(AQGL_ChangeAcceptance entity, Model model) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		//企业id
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			entity.setID1(UserUtil.getCurrentShiroUser().getQyid());
		}
		//添加变更验收
		aqglBgysService.addInfo(entity);
		// 返回结果
		return datasuccess;
	}
	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的变更验收信息
		Map<String, Object> bgsq = aqglBgysService.findById(id);
		model.addAttribute("bgsq", bgsq);
		//操作人
		model.addAttribute("czr", UserUtil.getCurrentShiroUser().getName());
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "zyaqgl/bggl/bgys/form";
	}
	
	/**
	 * 修改变更验收信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQGL_ChangeAcceptance entity,  Model model, HttpServletRequest request){
		String datasuccess="success";	
		entity.setS2(new java.sql.Timestamp(new java.util.Date().getTime()));
		String role=request.getParameter("role");
		if(!StringUtils.isEmpty(role)){
			if(role.equals("2")){
				//审核
				entity.setM12(UserUtil.getCurrentUser().getName());//审核人
				Timestamp t=DateUtils.getSysTimestamp();
				entity.setM13(t);//审核日期
			}else if(role.equals("3")){
				//批准
				entity.setM15(UserUtil.getCurrentUser().getName());//批准人
				Timestamp t=DateUtils.getSysTimestamp();
				entity.setM16(t);//批准日期
			}
		}
		aqglBgysService.updateInfo(entity);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除变更验收信息
	 */
	@RequiresPermissions("zyaqgl:bgys:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			aqglBgysService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String, Object> bgsq = aqglBgysService.findInforById(id);
		
		model.addAttribute("bgsq", bgsq);
		//返回页面
		model.addAttribute("action", "view");
		return "zyaqgl/bggl/bgys/view";
	}
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("zyaqgl:bgys:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.put("m1", request.getParameter("bis_fcxx_cx_m1"));
		aqglBgysService.exportExcel(response, map);
	}
	
	/**
	 * 显示所有列
	 */
	@RequiresPermissions("zyaqgl:bgys:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","bis/fcxx/export");
		return "/common/formexcel";
	}
	
	//导入页面跳转
	@RequestMapping(value = "exinjump", method = RequestMethod.GET)
	public String exin(Model model) {
		model.addAttribute("action", "exin");
		return "common/importForm";
	}
	
}
