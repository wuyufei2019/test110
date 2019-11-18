package com.cczu.model.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

/**
 * 
 * @ClassName: CaptchaController
 * @Description: 验证码
 * @author jason
 * @date 2017年5月26日
 *
 */
@Controller
public class CaptchaController { 
	
    private Producer captchaProducer = null;  
  
    @Autowired
    public void setCaptchaProducer(Producer captchaProducer){  
        this.captchaProducer = captchaProducer;  
    }  
  
    @RequestMapping(value="/static/images/kaptcha.jpg")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{  
        response.setDateHeader("Expires", 0);  
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
        response.setHeader("Pragma", "no-cache");  
        response.setContentType("image/jpeg");  
        String capText=captchaProducer.createText();
        // 存session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out); 
        try {  
            out.flush();  
        } finally {  
            out.close();  
        }  
        return null;
    }  
    
 
}  