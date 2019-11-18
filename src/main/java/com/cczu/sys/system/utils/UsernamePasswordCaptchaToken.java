package com.cczu.sys.system.utils;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 扩展添加验证码-继承用户验证类
 * @author jason
 * @date 2017年5月31日
 */
public class UsernamePasswordCaptchaToken extends UsernamePasswordToken {

	private static final long serialVersionUID = 1L;

	private String captcha;

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public UsernamePasswordCaptchaToken() {
		super();
	}

	public UsernamePasswordCaptchaToken(String username, char[] password,boolean rememberMe, String host, String captcha) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
	}

}
