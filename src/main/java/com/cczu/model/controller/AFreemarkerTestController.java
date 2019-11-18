package com.cczu.model.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cczu.sys.comm.controller.BaseController;

/**
 * 
 * @ClassName: AFreemarkerTestController
 * @Description: 测试
 * @author jason
 *
 */
@Controller
public class AFreemarkerTestController extends BaseController  {
	@RequestMapping(value ="freemarker")  
    public String test(Model model) {  
        String word0 = "Freemarker ";  
        String word1 = "Test!";  
        
        model.addAttribute("word0",word0);  
        model.addAttribute("word1",word1);  
        return "/test";
    }  
}
