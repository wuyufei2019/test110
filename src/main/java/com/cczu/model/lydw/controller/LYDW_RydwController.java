package com.cczu.model.lydw.controller;

import com.cczu.model.lydw.service.LYDW_RydwService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 蓝牙定位----人员定位
 */
@Controller
@RequestMapping("lydw/rydw")
public class LYDW_RydwController extends BaseController {

	@Autowired
	private LYDW_RydwService lydw_rydwService;

	/**
     * 首页
     * @param model
     */
    @RequestMapping(value="index")
    public String index(Model model) {
        //wbindex：websocket传输数据， index：ajax传输
//        return "lydw/rydw/cap-index";//ThingJs主页面
        return "lydw/rydw/fncapindex";
    }

    /**
     * 人员位置listjson
     * @param request
     */
    @RequestMapping(value="listjson")
    @ResponseBody
    public String getRywzList(HttpServletRequest request) {
    	Map<String, Object> map = new HashMap<>();
    	map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
        return lydw_rydwService.rydwData(map);
    }

    /**
     * 根据部门统计在线人数
     * @param request
     */
    @RequestMapping(value="bmtotaljson")
    @ResponseBody
    public String getTotalByDepartment(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
        return lydw_rydwService.totalOnlinePoeple(map);
    }

    /**
     * 轨迹回放首页
     * @param model
     * @return
     */
    @RequestMapping(value="gjcx/index")
    public String gjindexman(Model model) {
        model.addAttribute("qyid", UserUtil.getCurrentShiroUser().getQyid());
//      return "lydw/gjcx/index";
        return "lydw/gjcx/index2";
    }

    @RequestMapping(value="gjcx/index2")
    public String gjindex(Model model) {
        model.addAttribute("qyid", UserUtil.getCurrentShiroUser().getQyid());
//      return "lydw/gjcx/index";
        return "lydw/gjcx/index_cl";
    }

    /**
     * 查询人员历史轨迹
     * @param request
     */
    @RequestMapping(value="hisgjlistman")
    @ResponseBody
    public String getHisGjListMan(HttpServletRequest request) {

        Map<String, Object> map = new HashMap<>();
        map.put("starttime",request.getParameter("starttime"));
        map.put("endtime",request.getParameter("endtime"));
        map.put("tagid",request.getParameter("tagid"));
        return lydw_rydwService.getHisGjListMan(map);
    }

    /**
     * 查询车辆历史轨迹
     * @param request
     */
    @RequestMapping(value="hisgjlistcar")
    @ResponseBody
    public String getHisGjListCar(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("starttime",request.getParameter("starttime"));
        map.put("endtime",request.getParameter("endtime"));
        map.put("tagid",request.getParameter("tagid"));
        return lydw_rydwService.getHisGjListCar(map);
    }

    /**
     * 人员追踪
     * @param model
     * @return
     */
    @RequestMapping(value="ryzz")
    public String track(Model model) {

        return "lydw/ryzz/index";
    }
    /**
     * 人员追踪位置刷新
     * @param request
     */
    @RequestMapping(value="realtimezb")
    @ResponseBody
    public String getRealTimeList(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String ygid=request.getParameter("ygid");
        if(ygid==null||ygid.equals(""))
            return "";
        else{
            map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
            map.put("ygid", ygid);
        }
        return lydw_rydwService.rydwData(map);
    }

    /**
     * 查询绑定工卡的人员
     * @param request
     */
    @RequestMapping(value="yglist")
    @ResponseBody
    public String getYgList(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
        return lydw_rydwService.getYGList(map);
    }

    @RequestMapping(value = "carlist")
    @ResponseBody
    public String getCarList(HttpServletRequest request) {
        return lydw_rydwService.getCarList();
    }
}
