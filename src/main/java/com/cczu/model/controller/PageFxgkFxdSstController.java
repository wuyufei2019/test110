package com.cczu.model.controller;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.FxgkFxxxService;
import com.cczu.model.service.YhpcStoppointService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.entity.Department;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 风险管控---风险点四色图
 * @author jason
 * @date 2017年8月8日
 */
@Controller
@RequestMapping("fxgk/fxdsst")
public class PageFxgkFxdSstController extends BaseController {

	@Autowired
	private FxgkFxxxService sxgkfxdsstService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	@Autowired
	private YhpcStoppointService yhpcStoppointService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model,HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		model.addAttribute("usertype", sessionuser.usertype);
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				model.addAttribute("qyfxsst", be.getM33_4());// 企业风险四色图
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					return "fxgk/fxdsst/index";
				else
					return "fxgk/fxdsst/qyindex";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "fxgk/fxdsst/index";
		}	
	}



}
