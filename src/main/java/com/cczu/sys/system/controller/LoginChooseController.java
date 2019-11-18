package com.cczu.sys.system.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.cczu.sys.comm.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cczu.sys.comm.utils.Global;


/**
 * 登录选择页面controller
 * @author jason
 * @date 2017年5月31日
 */
@Controller
@RequestMapping(value = "login")
public class LoginChooseController{
	public static final String DEFAULT_SESSION_LOGINURL="loginurl";
	/**
	 * 登录页面
	 * @return
	 */
	@RequestMapping(value = "/{address}")
	public String ajLogin(@PathVariable("address") String address ) {

		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()||subject.isRemembered()){
			return "redirect:"+Global.getAdminPath();
		}
		Session session =subject.getSession();

		switch (address) {
		case "zjzcl":
			session.setAttribute("logotitle", "中储粮油脂镇江基地智慧安全一体化云平台");
			session.setAttribute(DEFAULT_SESSION_LOGINURL, "/login/zjzcl");
			return "system/login/zjzcl/login";
		case "en"://英文版界面
			session.setAttribute("logotitle", "");
			session.setAttribute("loginurl", "/login/en");
			return "system/login_en";
		case "zf"://政府版界面
			session.setAttribute("logotitle", "");
			session.setAttribute("loginurl", "/login/zf");
			return "system/login_zf";
		case "qy"://企业版界面
			session.setAttribute("logotitle", "");
			session.setAttribute("loginurl", "/login/qy");
			return "system/login_qy";
		default:
			return "system/login";
		}

	}
	
}
