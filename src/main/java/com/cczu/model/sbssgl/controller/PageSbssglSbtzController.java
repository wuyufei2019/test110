package com.cczu.model.sbssgl.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.sbssgl.service.SBSSGLSbglService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 设备设施管理-设备台账controller
 */
@Controller
@RequestMapping("sbssgl/sbtz")
public class PageSbssglSbtzController extends BaseController{

	@Autowired
	private SBSSGLSbglService sBSSGLSbglService;
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
		return "sbssgl/sbtz/index";
	}
	
	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("sbtype", request.getParameter("sbtype"));
		map.put("qyname", request.getParameter("qyname"));
		map.put("m1", request.getParameter("m1"));
		map.put("m2", request.getParameter("m2"));
		map.put("m6", request.getParameter("m6"));
		map.put("m9", request.getParameter("m9"));
		map.put("m16", request.getParameter("m16"));
		map.put("m19", request.getParameter("m19"));
		map.putAll(getAuthorityMap());
		return sBSSGLSbglService.dataGrid2(map);
	}
}
