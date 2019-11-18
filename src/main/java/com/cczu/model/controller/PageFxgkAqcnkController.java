package com.cczu.model.controller;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.FxgkFxxxService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 两单三卡管理---安全承诺卡
 * @author jason
 * @date 2017年8月8日
 */
@Controller
@RequestMapping("fxgk/aqcnk")
public class PageFxgkAqcnkController extends BaseController {

	@Autowired
	private FxgkFxxxService sxgkaqcnkService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;

	/**
	 * 列表显示页面
	 *
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model,HttpServletRequest request) {
		if(request.getParameter("sys")!=null &&"1".equals(request.getParameter("sys"))){
			model.addAttribute("sys", "sys");
			if(request.getParameter("mflag")!=null &&(request.getParameter("mflag"))!="")
				model.addAttribute("fxdj", request.getParameter("mflag"));
		}
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		model.addAttribute("usertype", sessionuser.usertype);
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					return "fxgk/aqcnk/index";
				else
					return "fxgk/aqcnk/qyindex";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "fxgk/aqcnk/index";
		}
	}

}
