package com.cczu.model.controller;

import com.cczu.model.entity.BEIAN_RoadTransportEntity;
import com.cczu.model.service.BeianRoadTransportService;
import com.cczu.model.service.YszyKkzysbService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.Message;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.service.MessageService;
import com.cczu.sys.system.service.ShiroRealm;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 道路运输证管理Controller
 *
 * @author wbth
 */
@Controller
@RequestMapping("beian/roadtransport")
public class PageBeianRoadTransportController extends BaseController {

    @Autowired
    private BeianRoadTransportService beianRoadTransportService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private YszyKkzysbService yszyKkzysbService;

    /**
     * 跳转到列表显示页面
     *
     * @param model
     */
    @RequestMapping(value = "index")
    public String index(Model model) {
        ShiroRealm.ShiroUser currentShiroUser = UserUtil.getCurrentShiroUser();
        model.addAttribute("usertype", currentShiroUser.getUsertype());
        model.addAttribute("userid", currentShiroUser.getId());

        //得到当前用户的用户类型
        List<Role> userList = yszyKkzysbService.getUserRoleCode(UserUtil.getCurrentUser().getId());
        Role userRole = userList.get(0);
        model.addAttribute("userRoleCode",userRole.getRoleCode());
        return "beian/roadtransport/index";
    }

    /**
     * 道路运输证list页面
     *
     * @param request
     */
    @RequiresPermissions("beian:roadtransport:view")
    @RequestMapping(value = "list")
    @ResponseBody
    public Map<String, Object> getData(HttpServletRequest request, Model model) {
        Map<String, Object> map = getPageMap(request);
        String usertype = UserUtil.getCurrentShiroUser().getUsertype();
       /* if (usertype.equals("1")) {
            map.put("userid", UserUtil.getCurrentShiroUser().getId());
        }*/
        map.put("name", request.getParameter("beian_rt_m1"));//业主名称
        map.put("plateNum", request.getParameter("beian_rt_m3"));//业主名称
        map.put("category", request.getParameter("beian_rt_m5"));//车辆类型
        map.put("jyfw", request.getParameter("beian_rt_m8"));//经营范围
        map.put("yxtime", request.getParameter("beian_rt_m10"));//有效期

        //判断道路运输证是否已经过期，如果过期将状态设置为待审核
        /*List<Long>  list = beianRoadTransportService.getExpiredId();
        for(int i = 0;i < list.size();i++){
            Long id = StringUtils.toLong(list.get(i));
            BEIAN_RoadTransportEntity entity = beianRoadTransportService.findInforById(id);
            entity.setState(2);
            beianRoadTransportService.updateInfo(entity);
        }*/
        return beianRoadTransportService.dataGrid(map);
    }

    /**
     * 添加页面跳转
     *
     * @param model
     */
    @RequiresPermissions("beian:roadtransport:add")
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request) {
        model.addAttribute("action", "create");
        model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        return "beian/roadtransport/form";
    }

    /**
     * 添加道路运输证信息
     *
     * @param request,model
     */
    @RequiresPermissions("beian:roadtransport:add")
    @RequestMapping(value = "create")
    @ResponseBody
    public String create(BEIAN_RoadTransportEntity entity, HttpServletRequest request) {
        entity.setState(1);
        entity.setUserid(UserUtil.getCurrentShiroUser().getId());
        setBaseInfo(entity);
        return beianRoadTransportService.addInfo(entity);

    }


    /**
     * 修改页面跳转
     *
     * @param id,model
     */
    @RequiresPermissions("beian:roadtransport:update")
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Long id, Model model) {
        //根据id获取道路运输证信息
        BEIAN_RoadTransportEntity entity = beianRoadTransportService.findInforById(id);
        model.addAttribute("entity", entity);
        //返回页面
        model.addAttribute("action", "update");
        return "beian/roadtransport/form";
    }

    /**
     * 修改道路运输证信息
     *
     * @param request,model
     */
    @RequiresPermissions("beian:roadtransport:update")
    @RequestMapping(value = "update")
    @ResponseBody
    public String xfssupdate(BEIAN_RoadTransportEntity entity) {
        Timestamp t = DateUtils.getSysTimestamp();
        //必须设置，否则会出错
        entity.setS2(t);
        entity.setS3(0);
        entity.setState(1);
        entity.setUserid(UserUtil.getCurrentShiroUser().getId());
        String result = beianRoadTransportService.updateInfo(entity);
        return result;
    }

    /**
     * 修改审核不通过信息跳转
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "update2/{id}", method = RequestMethod.GET)
    public String update2(@PathVariable("id") Long id, Model model) {
        BEIAN_RoadTransportEntity entity = beianRoadTransportService.findInforById(id);
        model.addAttribute("entity", entity);
        //返回页面
        model.addAttribute("action", "update2");
        return "beian/roadtransport/form";
    }

    /**
     * 修改审核不通过信息
     *
     * @param request,model
     */
    @RequestMapping(value = "update2")
    @ResponseBody
    public String xfssupdate2(BEIAN_RoadTransportEntity entity) {
        Timestamp t = DateUtils.getSysTimestamp();
        //必须设置，否则会出错
        entity.setS2(t);
        entity.setS3(0);
        entity.setState(0);
        String result = beianRoadTransportService.updateInfo(entity);
        return result;
    }

    /**
     * 修改状态
     *
     * @param request,model
     */
    @RequestMapping(value = "changeclcrstatus/{id}/{type}")
    @ResponseBody
    public String changeClcrStatus(@PathVariable("id") Long id, @PathVariable("type") Integer type, HttpServletRequest request) {

        BEIAN_RoadTransportEntity entity = beianRoadTransportService.findInforById(id);
        Timestamp t = DateUtils.getSysTimestamp();
        //必须设置，否则会出错
        entity.setS2(t);
        entity.setState(type);
        String result = beianRoadTransportService.updateInfo(entity);
        String opinion = request.getParameter("opinion");
        if (StringUtils.isNotBlank(opinion)) {
            Message msg = new Message(entity.getUserid() + "", UserUtil.getCurrentShiroUser().getId() + ""
                    , "您申报的道路运输证(" + entity.getM3() + ")审核不通过，请及时修改！",
                    null, "审核不通过原因：" + opinion);
            msg.setCREATETIME(DateUtils.getSysTimestamp());
            msg.setSENDSTATUE("0");
            msg.setURL("beian/roadoperationpermit/update2/" + id);
            messageService.save(msg);
        }
        return result;
    }

    /**
     * 删除道路运输证信息
     */
    @RequiresPermissions("beian:roadtransport:delete")
    @RequestMapping(value = "delete/{ids}")
    @ResponseBody
    public String delete(@PathVariable("ids") String ids) {
        String datasuccess = "success";
        //可以批量删除
        String[] arrids = ids.split(",");
        for (int i = 0; i < arrids.length; i++) {
            //删除信息
            beianRoadTransportService.deleteInfo(Long.parseLong(arrids[i]));
        }
        return datasuccess;
    }

    /**
     * 查看页面跳转
     *
     * @param id,model
     */
    @RequiresPermissions("beian:roadtransport:view")
    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, Model model) {
        BEIAN_RoadTransportEntity entity = beianRoadTransportService.findInforById(id);
        model.addAttribute("entity", entity);
        return "beian/roadtransport/view";
    }


    /**
     * 显示所有列
     *
     * @param id,model
     */
    @RequiresPermissions("beian:roadtransport:export")
    @RequestMapping(value = "colindex", method = RequestMethod.GET)
    public String colindex(Model model) {
        model.addAttribute("url", "beian/roadtransport/export");
        return "common/formexcel";
    }

    /**
     * 导出excel
     */
    @RequiresPermissions("beian:roadtransport:export")
    @RequestMapping(value = "export")
    @ResponseBody
    public void export(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        String usertype = UserUtil.getCurrentShiroUser().getUsertype();
        if (usertype.equals("1")) {
            map.put("userid", UserUtil.getCurrentShiroUser().getId());
        }
        map.put("name", request.getParameter("beian_rt_m1"));//业主名称
        map.put("category", request.getParameter("beian_rt_m5"));//车辆类型
        map.put("jyfw", request.getParameter("beian_rt_m8"));//经营范围
        map.put("yxtime", request.getParameter("beian_rt_m10"));//有效期
        map.put("colval", request.getParameter("colval"));
        map.put("coltext", request.getParameter("coltext"));
        map.putAll(getAuthorityMap());
        beianRoadTransportService.exportExcel(response, map);
    }


    /**
     * 检验是否有重复数据
     */
    @RequestMapping(value = "valid/unique/{plateNum}")
    @ResponseBody
    public boolean validUnique(@PathVariable String plateNum) {

        return beianRoadTransportService.validPlateNum(plateNum);
    }
    /**
     * 检验是否有重复数据
     */
    @RequestMapping(value = "valid/unique/review/{plateNum}")
    @ResponseBody
    public boolean validReviewUnique(@PathVariable String plateNum) {

        return beianRoadTransportService.validReviewPlateNum(plateNum);
    }


    //导入页面跳转
    @RequestMapping(value = "exinjump", method = RequestMethod.GET)
    public String exin(Model model) {
        model.addAttribute("action", "exin");
        return "common/importForm";
    }


    /**
     * 导入
     *
     * @param response
     * @param request
     * @return
     */
    @RequiresPermissions("beian:roadoperationpermit:exin")
    @RequestMapping(value = "exin")
    @ResponseBody
    public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> map = uploadExcel(request, response);
        Map<String, Object> resultmap = new HashMap<String, Object>();
        if (map.get("content") != null) {
            resultmap = beianRoadTransportService.exinExcel(map);
        } else {
            resultmap.put("returncode", -2);
        }
        return JsonMapper.toJsonString(resultmap);
    }


    @RequestMapping(value = "listjson")
    @ResponseBody
    public String getAllDataJson(HttpServletRequest request) {
        List<Map<String, Object>> list = beianRoadTransportService.listAll();
        return JsonMapper.getInstance().toJson(list);
    }


}
