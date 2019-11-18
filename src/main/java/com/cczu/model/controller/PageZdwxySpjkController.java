package com.cczu.model.controller;


import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.TS_Video;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.TsVideoService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 重大危险源实时数据---重大危险源视频controller
 * @author wbth
 * @date 2019年9月4日
 */
@Controller
@RequestMapping("zdwxy/spjk")
public class PageZdwxySpjkController extends BaseController {

	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model,HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		model.addAttribute("qyid", sessionuser.getQyid());
		model.addAttribute("usertype", sessionuser.getUsertype());
		model.addAttribute("zdwxy", request.getParameter("zdwxy"));
		return "zdwxy/spjk/index";
	}

}

