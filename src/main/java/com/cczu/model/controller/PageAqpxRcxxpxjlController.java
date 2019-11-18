package com.cczu.model.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cczu.sys.comm.mapper.JsonMapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cczu.model.entity.AQPX_RcxxpxjlEntity;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.AqpxRcxxpxjlService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
/**
 * 日常培训线下培训记录
 * @author Administrator
 *
 */
@Controller
@RequestMapping("aqpx/rcxxpxjl")
public class PageAqpxRcxxpxjlController extends BaseController {
	
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	
	@Autowired
	private AqpxRcxxpxjlService aqpxRcxxpxjlService;
	
	/**
	 * 线下培训记录页面
	 * @param model
	 */
	@RequestMapping(value="pxjl/index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					return "aqpx/rcxxpxjl/pxjl/ajindex";
				else
					return "aqpx/rcxxpxjl/pxjl/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "aqpx/rcxxpxjl/pxjl/ajindex";
		}
	}
	
	
	/**
	 * 线下培训记录list页面
	 * @param request
	 */
	@RequestMapping(value="pxjl/list")
	@ResponseBody
	public Map<String, Object> getData(Model model, HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("aqpx_rcxxpxjl_qyname"));
		map.put("name", request.getParameter("aqpx_rcxxpxjl_name"));
		map.putAll(getAuthorityMap());
		return aqpxRcxxpxjlService.dataGrid(map);	
	}
	
	
	
	/**
	 * 线下培训记录添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("rcxxpxjl:pxjl:add")
	@RequestMapping(value = "pxjl/create" , method = RequestMethod.GET)
	public String create(Model model) {
		AQPX_RcxxpxjlEntity pxjl=new AQPX_RcxxpxjlEntity();
		pxjl.setID1(UserUtil.getCurrentShiroUser().getQyid());
		pxjl.setM2(DateUtils.getSystemTime());
		model.addAttribute("action", "create");
		model.addAttribute("pxjl",pxjl);
		return "aqpx/rcxxpxjl/pxjl/form";
		
	}
	
	
	/**
	 * 线下培训记录添加
	 * @param model
	 */
	@RequiresPermissions("rcxxpxjl:pxjl:add")
	@RequestMapping(value = "pxjl/create" , method = RequestMethod.POST)
	@ResponseBody
	public String create(AQPX_RcxxpxjlEntity jl,Model model) {
		String result="success";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		jl.setID1(sessionuser.getQyid());
		jl.setState("2");
		aqpxRcxxpxjlService.addInfo(jl);
		return result;
	}
	
	/**
	 * 删除培训记录
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("rcxxpxjl:pxjl:delete")
	@RequestMapping(value = "pxjl/delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			aqpxRcxxpxjlService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	
	
	
	/**
	 * 线下培训记录修改页面跳转
	 * @param model
	 */
	@RequiresPermissions("rcxxpxjl:pxjl:delete")
	@RequestMapping(value = "pxjl/update/{id}" , method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id,Model model) {
		AQPX_RcxxpxjlEntity pxjl = aqpxRcxxpxjlService.findById(id);
		model.addAttribute("action", "update");
		model.addAttribute("pxjl",pxjl);
		return "aqpx/rcxxpxjl/pxjl/form";
	}
	
	/**
	 * 线下培训记录修改
	 * @param model
	 */
	@RequiresPermissions("rcxxpxjl:pxjl:delete")
	@RequestMapping(value = "pxjl/update" , method = RequestMethod.POST)
	@ResponseBody
	public String update(AQPX_RcxxpxjlEntity jl,Model model) {
		aqpxRcxxpxjlService.updateInfo(jl);
		return "success";
	}
	
	
	
	/**
	 * 线下培训记录查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("rcxxpxjl:pxjl:view")
	@RequestMapping(value = "pxjl/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		AQPX_RcxxpxjlEntity pxjl = aqpxRcxxpxjlService.findById(id);
		model.addAttribute("pxjl",pxjl);
		return "aqpx/rcxxpxjl/pxjl/view";
	}
	
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("rcxxpxjl:pxjl:export")
	@RequestMapping(value = "pxjl/export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String param=request.getParameter("param");
		Map<String,Object> map2 = JSON.parseObject(param);
		map.put("qyname", map2.get("aqpx_rcxxpxjl_qyname"));
		map.put("name", map2.get("aqpx_rcxxpxjl_name"));
		map.put("idcard", request.getParameter("idcard"));
		map.putAll(getAuthorityMap());
		aqpxRcxxpxjlService.exportExcel(response, map);
	}

	/**
	 * 导出excel(集团)
	 * 
	 */
	@RequiresPermissions("rcxxpxjl:pxjl:export")
	@RequestMapping(value = "pxjl/export2")
	@ResponseBody
	public void export2(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String param=request.getParameter("param");
		Map<String,Object> map2 = JSON.parseObject(param);
		map.put("qyname", map2.get("aqpx_rcxxpxjl_qyname"));
		map.put("name", map2.get("aaqpx_rcxxpxjl_name"));
		map.put("idcard", request.getParameter("idcard"));
		map.putAll(getAuthorityMap());
		aqpxRcxxpxjlService.exportExcel2(response, map);
	}

	//导入页面跳转
	@RequiresPermissions("rcxxpxjl:pxjl:exin")
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
	@RequiresPermissions("rcxxpxjl:pxjl:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			resultmap = aqpxRcxxpxjlService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}











}
