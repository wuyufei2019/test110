package com.cczu.model.lydw.controller;

/**
 * @Author: luxincai
 * @CreateTime:2019-11-08 15:21
 * @Description: todo
 */

import com.cczu.sys.system.utils.UserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 蓝牙定位----电子围栏
 */
@Controller
@RequestMapping("lydw/dzwlfn")
public class LYDW_FNController {

    /**
     * 蜂鸟人员定位 页面
     * @param model
     */
    @RequestMapping(value="fncapindex")
    public String fncapindex(Model model) {
        return "lydw/rydw/fncapindex";
    }


    /**
     * 蜂鸟围栏
     * @param model
     */
    @RequestMapping(value="wlindex")
    public String wlindex(Model model) {
        return "lydw/dzwl/index";
    }

    /**
     *蜂鸟 轨迹回放首页
     * @param model
     * @return
     */
    @RequestMapping(value="gjindex")
    public String gjindex(Model model) {
        model.addAttribute("qyid", UserUtil.getCurrentShiroUser().getQyid());
      return "lydw/gjcx/index";
    }

    @RequestMapping(value = "clgjindex")
    public String clgjindex(Model model){
        model.addAttribute("qyid", UserUtil.getCurrentShiroUser().getQyid());
        return "lydw/gjcx/index2";
    }
}
