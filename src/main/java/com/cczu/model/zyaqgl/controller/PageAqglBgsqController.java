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

import com.cczu.model.zyaqgl.entity.AQGL_ChangeRequest;
import com.cczu.model.zyaqgl.service.AqglBgsqService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.Department;
import com.cczu.sys.system.service.DepartmentService;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.StringUtils;

/**
 * 作业安全管理-变更申请Controller
 * @author YZH
 */
@Controller
@RequestMapping("zyaqgl/bgsq")
public class PageAqglBgsqController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private AqglBgsqService aqglBgsqService;
	@Autowired
	private DepartmentService departmentService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model,HttpServletRequest request) {
		model.addAttribute("qyid", request.getParameter("qyid"));
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
		model.addAttribute("usertype", usertype);
		return "zyaqgl/bggl/bgsq/index";
	}
	
	/**
	 * 企业端list 
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("s1", request.getParameter("aqzf_bgsq_s1"));
		map.put("m2", request.getParameter("aqzf_bgsq_m2"));
		
		String qyid = request.getParameter("qyid");
		if(StringUtils.isEmpty(qyid)){
			if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
				map.put("qyid",(UserUtil.getCurrentShiroUser().getQyid()));
			}
		}else{
			map.put("qyid", qyid);
		}
		return aqglBgsqService.dataGrid(map);	
		
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("zyaqgl:bgsq:add")
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
		return "zyaqgl/bggl/bgsq/form";
	}
	
	/**
	 * 添加变更申请信息
	 * @param request,model
	 */
	@RequiresPermissions("zyaqgl:bgsq:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(AQGL_ChangeRequest entity, Model model) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		//企业id
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			entity.setID1(UserUtil.getCurrentShiroUser().getQyid());
		}
		//申请人id
		entity.setID2(Long.parseLong(UserUtil.getCurrentShiroUser().getId().toString()));
		//添加变更申请
		aqglBgsqService.addInfo(entity);
		// 返回结果
		return datasuccess;
	}
	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{type}/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("type") String type,@PathVariable("id") Long id, Model model) {
		//查询选择的变更申请信息
		Map<String, Object> bgsq = aqglBgsqService.findById(id);
		model.addAttribute("bgsq", bgsq);
		//操作人
		model.addAttribute("czr", UserUtil.getCurrentShiroUser().getName());
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("type", type);
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "zyaqgl/bggl/bgsq/form";
	}
	
	/**
	 * 修改变更申请信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQGL_ChangeRequest entity,  Model model, HttpServletRequest request){
		String datasuccess="success";	
		entity.setS2(new java.sql.Timestamp(new java.util.Date().getTime()));
		String role=request.getParameter("role");
		if(!StringUtils.isEmpty(role)){
			if(role.equals("2")){
				//审核
				entity.setM8(UserUtil.getCurrentUser().getName());
				Timestamp t=DateUtils.getSysTimestamp();
				entity.setM9(t);
			}else if(role.equals("3")){
				//批准
				entity.setM11(UserUtil.getCurrentUser().getName());
				Timestamp t=DateUtils.getSysTimestamp();
				entity.setM12(t);
			}
		}
		aqglBgsqService.updateInfo(entity);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除变更申请信息
	 */
	@RequiresPermissions("zyaqgl:bgsq:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			aqglBgsqService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String, Object> bgsq = aqglBgsqService.findInforById(id);
		
		model.addAttribute("bgsq", bgsq);
		//返回页面
		model.addAttribute("action", "view");
		return "zyaqgl/bggl/bgsq/view";
	}
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("zyaqgl:bgsq:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.put("s1", request.getParameter("aqzf_bgsq_s1"));
		map.put("m2", request.getParameter("aqzf_bgsq_m2"));
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			map.put("qyid",(UserUtil.getCurrentShiroUser().getQyid()));
		}
		aqglBgsqService.exportExcel(response, map);
	}
	
	/**
	 * 显示所有列
	 */
	@RequiresPermissions("zyaqgl:bgsq:export")
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
