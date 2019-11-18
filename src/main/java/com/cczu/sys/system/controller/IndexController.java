package com.cczu.sys.system.controller;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.cczu.sys.system.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录controller
 * @author jason
 * @date 2017年5月31日
 */
@Controller
public class IndexController {
	protected final Log log = LogFactory.getLog(getClass());

	/**
	 * 默认页面
	 * @return
	 */
	@RequestMapping(value="",method = RequestMethod.GET)
	public String index(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()||subject.isRemembered()){
			User user = (User)subject.getSession().getAttribute("user");
			if(user==null)
				return "system/login";
			else if(user.getUsertype().equals("1"))
				return "system/hgqy/index_hgqy";
			else
				return "system/index";
		}
		return "system/login";
	}

}
