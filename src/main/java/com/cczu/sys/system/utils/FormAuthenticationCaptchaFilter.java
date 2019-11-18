package com.cczu.sys.system.utils;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 扩展认证默认过滤
 * @author jason
 * @date 2017年5月31日
 */
public class FormAuthenticationCaptchaFilter extends FormAuthenticationFilter {

	public static final String DEFAULT_CAPTCHA_PARAM = "captcha";
	private String captchaParam = DEFAULT_CAPTCHA_PARAM;

	public String getCaptchaParam() {
		return captchaParam;
	}

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}

	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		String captcha = getCaptcha(request);
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);
		System.out.println(username);
		System.out.println(password);
		System.out.println(captcha);
		System.out.println(rememberMe);
		System.out.println(host);
		return new UsernamePasswordCaptchaToken(username, password.toCharArray(), rememberMe, host, captcha);
	}


	@Override
	protected boolean onLoginSuccess(AuthenticationToken token,
									 Subject subject, ServletRequest request, ServletResponse response)
			throws Exception {
		String resurl = "/a";
		HttpServletRequest httprequest = (HttpServletRequest) request;
		Object loginurl = httprequest.getSession().getAttribute("loginurl");
		if (loginurl != null) {
			if (loginurl.toString().equals("/login/en"))
				resurl = "/en";
		}
		WebUtils.getAndClearSavedRequest(request);
		WebUtils.redirectToSavedRequest(request, response, resurl);
		return false;
	}


/*	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e,
									 ServletRequest request, ServletResponse response) {
		HttpServletRequest httprequest = (HttpServletRequest) request;
		Object loginurl = httprequest.getSession().getAttribute("loginurl");
		if(loginurl!=null){
			try {
				setFailureAttribute(request, e);
				WebUtils.redirectToSavedRequest(request, response, loginurl.toString());
				System.out.println(request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return  false;
	}*/

}
	 

