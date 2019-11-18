package com.cczu.sys.system.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;

  
//全局异常处理器  
@Component
public class CustomExceptionResolver implements HandlerExceptionResolver{  
	protected final Log log = LogFactory.getLog(getClass());
    //系统抛出的异常  
    @Override  
    public ModelAndView resolveException(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception ex) {  
          
        ModelAndView modelAndView=new ModelAndView(); 
        ex.printStackTrace();
        log.error(ex.getMessage(),ex); 
        //指向到错误界面  
        if(ex instanceof UnauthorizedException){
        	modelAndView.setViewName("../error/403");  
        }else{
        	StackTraceElement[] eles= ex.getStackTrace();
        	String exmsg=ex+"</br>";
        	for(StackTraceElement ele:eles){
        		exmsg+=ele.toString()+"</br>";
        	}
        	exmsg +="Cause By"+ ex.getCause()+"</br>";
        	//将错误信息传到页面  
        	modelAndView.addObject("exception", exmsg);
        	modelAndView.setViewName("../error/500");  
        }
        return modelAndView;  
    }  
       
}  
