package com.cczu.sys.system.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.MDC;

import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.entity.User;

import eu.bitwalker.useragentutils.UserAgent;

public class AjaxFilter implements Filter{

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	public void doFilter(ServletRequest servletRequestt, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		 HttpServletRequest request=(HttpServletRequest) servletRequestt;
		 HttpServletResponse response=(HttpServletResponse) servletResponse;
		 
		 //String currentURL = request.getRequestURI();//取得根目录所对应的绝对路径:   
		 //String targetURL = currentURL.substring(currentURL.indexOf("/", 1), currentURL.length());  //截取到当前文件名用于比较
		 
		 String ajaxSubmit = request.getHeader("X-Requested-With");
		 if(ajaxSubmit != null && ajaxSubmit.equals("XMLHttpRequest")){
			 if (request.getSession(false) == null) {
				 response.setHeader("sessionstatus", "timeout");
				 response.getWriter().print("sessionstatus");
				 return;
			 }
		 }
		 
		 HttpSession session= request.getSession();
		 UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent")); 
		 MDC.put("agent", userAgent.getBrowser().getName()); //请求浏览器
		 MDC.put("ip", IPUtil.getIpAddress(request));  //请求者ip
		 MDC.put("requestUri", request.getRequestURI()); //请求地址
		 MDC.put("param", JsonMapper.toJsonString(request.getParameterMap()));  //请求参数
		
		if (session==null){
		    MDC.put("userid",0);  
		}
		else{
		    User user=(User)session.getAttribute("user");
		    if (user==null)
		        MDC.put("userid",0);
		    else
		        MDC.put("userid",user.getId());
		}
		 
		 
		 
//		 HttpSession session = ((HttpServletRequest) servletRequestt).getSession(true);
		 chain.doFilter(servletRequestt, servletResponse);
	}

	public void destroy() {
		
	}
}
