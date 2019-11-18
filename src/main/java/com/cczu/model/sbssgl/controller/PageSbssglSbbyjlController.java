package com.cczu.model.sbssgl.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_SBBYTASKEntity;
import com.cczu.model.sbssgl.service.SBSSGLSbbyTaskFirService;
import com.cczu.model.sbssgl.service.SBSSGLSbbyTaskSecService;
import com.cczu.model.sbssgl.service.SBSSGLSbbyTaskService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 设备设施管理-设备保养记录controller
 */
@Controller
@RequestMapping("sbssgl/sbbyjl")
public class PageSbssglSbbyjlController extends BaseController {

	@Autowired
	private SBSSGLSbbyTaskService sBSSGLSbbyTaskService;
	@Autowired
	private SBSSGLSbbyTaskFirService sBSSGLSbbyTaskFirService;
	@Autowired
	private SBSSGLSbbyTaskSecService sBSSGLSbbyTaskSecService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model, HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type","2");//集团公司
				else
					model.addAttribute("type","1");//子公司
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else{
			return "../error/403";
		}
		
		if ("tzsb".equals(request.getParameter("sbtype"))) {
			model.addAttribute("sbtype","1");//特种设备
		} else {
			model.addAttribute("sbtype","0");//普通设备
		}
		return "sbssgl/sbbyjl/index";
	}
	
	/**
	 * 一级保养记录list
	 * @param request
	 */
	@RequestMapping(value="firlist")
	@ResponseBody
	public Map<String, Object> getFirData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("sbtype", request.getParameter("sbtype"));
		map.put("type", "0");
		map.put("qyname", request.getParameter("qynamefir"));
		map.put("year", request.getParameter("yearfir"));
		map.put("month", request.getParameter("month"));
		map.put("deptid", request.getParameter("deptidfir"));
		map.put("bztime", request.getParameter("bztimefir"));
		map.put("bzrname", request.getParameter("bzrnamefir"));
		map.putAll(getAuthorityMap());
		return sBSSGLSbbyTaskService.dataFirGrid(map);
	}
	
	/**
	 * 二级保养记录list
	 * @param request
	 */
	@RequestMapping(value="seclist")
	@ResponseBody
	public Map<String, Object> getSecData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("sbtype", request.getParameter("sbtype"));
		map.put("type", "1");
		map.put("qyname", request.getParameter("qynamesec"));
		map.put("year", request.getParameter("yearsec"));
		map.put("deptid", request.getParameter("deptidsec"));
		map.put("bztime", request.getParameter("bztimesec"));
		map.put("bzrname", request.getParameter("bzrnamesec"));
		map.putAll(getAuthorityMap());
		return sBSSGLSbbyTaskService.dataSecGrid(map);
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids, HttpServletRequest request) {
		String datasuccess="删除成功";
		String type = request.getParameter("type"); 
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			//删除设备保养信息
			sBSSGLSbbyTaskService.deleteInfoById(Long.parseLong(aids[i]));
			if ("0".equals(type)) {
				// 删除与taskid关联的一级保养计划记录
				sBSSGLSbbyTaskFirService.deleteInfoByTaskid(Long.parseLong(aids[i]));
			} else if ("1".equals(type)) {
				// 删除与taskid关联的二级保养计划记录
				sBSSGLSbbyTaskSecService.deleteInfoByTaskid(Long.parseLong(aids[i]));
			}
			
		}
		return datasuccess;
	}
	
	/**
	 * 上传附件页面跳转
	 */
	@RequestMapping(value = "uploadindex/{id}/{type}")
	public String uploadindex(@PathVariable("id") Long id, @PathVariable("type") String type, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("type", type);// 保养计划类别，根据该类别判断上传结束后刷新哪个datagrid
		return "sbssgl/sbbyjl/upload";
	}
	
	/**
	 * 上传附件
	 */
	@RequestMapping(value = "uploadfj")
	@ResponseBody
	public String uploadfj(long id,String fj) {
		String datasuccess="success";
		SBSSGL_SBBYTASKEntity sbbyjl = sBSSGLSbbyTaskService.find(id);
		sbbyjl.setFj(fj);
		sBSSGLSbbyTaskService.updateInfo(sbbyjl);
		return datasuccess;
	}
}
