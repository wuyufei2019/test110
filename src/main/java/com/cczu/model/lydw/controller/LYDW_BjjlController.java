package com.cczu.model.lydw.controller;

import com.cczu.model.lydw.service.LYDW_BjjlService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/lydw/bjjl")
public class LYDW_BjjlController extends BaseController {

    @Autowired
    LYDW_BjjlService lydw_bjjlService;


    /**
     * 首页
     * @param model
     */
    @RequestMapping(value="index")
    public String index(Model model) {
        return "lydw/bjgl/index";
    }

    /**
     * 获取所有报警记录
     * @return
     */
    @RequestMapping("/getallbjjl")
    public String getAllBjjl(){
        return lydw_bjjlService.getAllBjjl();
    }

    /**
     * 设置报警记录状态为已处理
     * @param request
     * @return
     */
    @RequestMapping("/setstatus")
    public String setStatus(HttpServletRequest request){
        long id = Long.parseLong(request.getParameter("id"));
        lydw_bjjlService.setStatus(id);
        return "success";
    }

    /**
     * 报警管理list
     * @param request
     */
    @RequestMapping(value="list")
    @ResponseBody
    public Map<String, Object> getRywzList(HttpServletRequest request) {
        Map<String, Object> map = getPageMap(request);
        ShiroRealm.ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
        map.put("qyid", sessionuser.getQyid());
        map.put("rq", request.getParameter("lydw_bjgl_cx_rq"));
        map.put("m1", request.getParameter("lydw_bjgl_cx_lb"));
        return lydw_bjjlService.dataGrid(map);
    }
}
