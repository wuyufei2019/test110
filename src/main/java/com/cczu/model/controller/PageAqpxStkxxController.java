package com.cczu.model.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.AQPX_CourseEntity;
import com.cczu.model.entity.AQPX_ItembankEntity;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IAqpxKCxxService;
import com.cczu.model.service.IAqpxStkxxService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 安全培训管理——试题库管理Controller
 * @author jason
 *
 */
@Controller
@RequestMapping("aqpx/stkxx")
public class PageAqpxStkxxController extends BaseController {
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private IAqpxKCxxService aqpxkcxxservice;
	@Autowired
	private IAqpxStkxxService aqpxstkxxservice;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be==null){//判断是否存在企业基本信息
				return "../error/001";
			}
			return "aqpx/stkxx/index";
		}else//非企业用户无法使用该模块
			return "../error/403";
		
	}
	

	/**
	 * list页面
	 * @param request
	 */
	@RequiresPermissions("aqpx:stkxx:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		
		Map<String, Object> map = getPageMap(request);
		
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		map.put("qyid", be.getID());	
		map.put("kc", request.getParameter("aqpx_stkxx_cx_kc"));
		map.put("m1", request.getParameter("aqpx_stkxx_cx_m1"));
		return aqpxstkxxservice.dataGrid(map);
		
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("aqpx:stkxx:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
			return "aqpx/stkxx/form";
		
	}
	
	/**
	 * 添加
	 * @param request,model
	 */
	@RequiresPermissions("aqpx:stkxx:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(@Valid AQPX_ItembankEntity ai, Model model) {
		String datasuccess="success";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		ai.setID1(sessionuser.getQyid());	
		aqpxstkxxservice.addInfo(ai);
		return datasuccess;
	}	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("aqpx:stkxx:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		//查询选择的信息
		long id1 = id;
		AQPX_ItembankEntity ai = aqpxstkxxservice.findByid(id1);
		model.addAttribute("aqpxlist",ai);
		AQPX_CourseEntity ac = aqpxkcxxservice.findbyid(ai.getID2());
		model.addAttribute("kclist",ac);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "aqpx/stkxx/form";
	}
	
	/**
	 * 修改试题库信息
	 * @param request,model
	 */
	@RequiresPermissions("aqpx:stkxx:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQPX_ItembankEntity ai, Model model){
		String datasuccess="success";
		aqpxstkxxservice.updateInfo(ai);
		return datasuccess;
	}
	
	/**
	 * 删除试题库信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequiresPermissions("aqpx:stkxx:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			aqpxstkxxservice.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("aqpx:stkxx:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		AQPX_ItembankEntity ai = aqpxstkxxservice.findByid(id);
		String type=ai.getM1();
		String lx="";
		if(type.equals("1")){
			lx="单选题";
		}else if(type.equals("2")){
			lx="多选题";
		}else if(type.equals("3")){
			lx="填空题";
		}else if(type.equals("4")){
			lx="判断题";
		}
		model.addAttribute("aqpxlist",ai);
		model.addAttribute("lx",lx);
		AQPX_CourseEntity ac = aqpxkcxxservice.findbyid(ai.getID2());
		model.addAttribute("kcmc",ac.getM1());
		//返回页面
		model.addAttribute("action", "view");
		return "aqpx/stkxx/view";
	}
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("aqpx:stkxx:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyid", sessionuser.getQyid());
		map.put("kc", request.getParameter("aqpx_stkxx_cx_kc"));
		map.put("m1", request.getParameter("aqpx_stkxx_cx_m1"));
		aqpxstkxxservice.exportExcel(response, map);
		
	}
	
	/**
	 * 导入页面跳转
	 * @param model
	 * @return
	 */
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
	@RequiresPermissions("aqpx:stkxx:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String exinExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			resultmap = aqpxstkxxservice.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}
}
