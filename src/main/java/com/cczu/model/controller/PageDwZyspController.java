package com.cczu.model.controller;

import java.sql.Timestamp;
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

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.DW_WorkApprovalEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.IDwZyspService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 危险作业审批controller
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("dw/zysp")
public class PageDwZyspController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IDwZyspService dwDhzyService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model,HttpServletRequest request) {
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		if(request.getParameter("sys")!=null &&"1".equals(request.getParameter("sys"))){
			model.addAttribute("sys", "sys");
			if(request.getParameter("mflag")!=null &&(request.getParameter("mflag"))!="")
				model.addAttribute("zydw", request.getParameter("mflag"));
		}
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		model.addAttribute("qyid", sessionuser.getQyid());
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("usertype","0");//集团公司
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}	
		return "dw/index";
	}

	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> ajgetData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.putAll(getAuthorityMap());
		map.put("zylx", request.getParameter("dw_zysp_cx_m1"));//危险作业类型
		map.put("zyjb", request.getParameter("dw_zysp_cx_m2"));//危险作业级别
		map.put("zydw", request.getParameter("dw_zysp_cx_m17"));//危险作业队伍
		map.put("starttime", request.getParameter("starttime"));//开始时间
		map.put("qyname", request.getParameter("dw_zysp_cx_qyname"));
		map.put("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					map.put("usertype","0");//集团公司
			}
		}
		return dwDhzyService.dataGrid(map);
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("dw:zysp:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "dw/form";
	}
	
	/**
	 * 添加危险作业审批
	 * @param request,model
	 */
	@RequiresPermissions("dw:zysp:add")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public String create(@Valid DW_WorkApprovalEntity obj, Model model) {
		String datasuccess="success";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		//用户类型(1企业，0安监局，9系统管理员)
		if(sessionuser.getUsertype().equals("1")){
			obj.setID1(sessionuser.getQyid());
		}else if(sessionuser.getUsertype().equals("0")||sessionuser.getUsertype().equals("9")){
			
		}else{
			return "请先完善企业基本信息";
		}
		Timestamp t=DateUtils.getSysTimestamp();
		obj.setS1(t);
		obj.setS2(t);
		obj.setS3(0);
		dwDhzyService.addInfo(obj);
		return datasuccess;
		
	}
	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("dw:zysp:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		//查询选择的危险作业审批
		 
		long id1 = id;
		DW_WorkApprovalEntity dw = dwDhzyService.findInfoById(id1);
		model.addAttribute("zysp", dw);
		model.addAttribute("wxys", dw.getM9());
		model.addAttribute("sglx", dw.getM11());
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		//返回页面
		model.addAttribute("action", "update");
		return "dw/form";
	}
	
	/**
	 * 修改危险作业审批
	 * @param request,model
	 */
	@RequiresPermissions("dw:zysp:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(DW_WorkApprovalEntity dw, Model model){
		String datasuccess="success";
		Timestamp t=DateUtils.getSysTimestamp();
		dw.setS2(t);
		dwDhzyService.updateInfo(dw);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除危险作业审批
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequiresPermissions("dw:zysp:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功！";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			dwDhzyService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("dw:zysp:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		//查询选择的产品信息
		 
		long id1 = id;
		Map<String,Object> dw = dwDhzyService.findInfoById2(id1);
		model.addAttribute("zysp", dw);
		//返回页面
		return "dw/view";
	}
	
	//导入页面跳转
	@RequestMapping(value = "exinjump", method = RequestMethod.GET)
	public String exin(Model model) {
		model.addAttribute("action", "exin");
		return "common/importForm";
	}
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("dw:zysp:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(getAuthorityMap());
		map.put("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		map.put("qyname", request.getParameter("dw_zysp_cx_qyname"));
		map.put("zylx", request.getParameter("dw_zysp_cx_m1"));//危险作业类型
		map.put("zyjb", request.getParameter("dw_zysp_cx_m2"));//危险作业级别
		map.put("zydw", request.getParameter("dw_zysp_cx_m17"));//危险作业队伍
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					map.put("usertype","0");//集团公司
			}
		}
		dwDhzyService.exportExcel(response, map);
	}
	
	/**
	 * 显示所有列
	 * @param id,model 
	 */
	@RequiresPermissions("dw:zysp:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","dw/zysp/export");
		return "common/formexcel";
	}
	
}
