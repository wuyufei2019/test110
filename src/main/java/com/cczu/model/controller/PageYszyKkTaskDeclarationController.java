package com.cczu.model.controller;

import com.cczu.model.entity.*;
import com.cczu.model.service.*;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.Message;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.service.MessageService;
import com.cczu.sys.system.service.ShiroRealm;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 卡口作业申报Controller
 *
 * @author wbth
 */
@Controller
@RequestMapping("yszy/kkzysb")
public class PageYszyKkTaskDeclarationController extends BaseController {

    @Autowired
    private YszyKkzysbService yszyKkzysbService;
    @Autowired
    private YszyTransGoodsService goodsService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private YszyReviewOpinionService yszyReviewOpinionService;


    /**
     * 跳转到列表显示页面
     *
     * @param model
     */
    @RequestMapping(value = "index")
    public String index(Model model) {
        List<YSZY_KkTaskDeclarationEntity> list = yszyKkzysbService.findAllInfo();
        String json = "{\"data\":" + JsonMapper.toJsonString(list) + "}";
        model.addAttribute("jsonlist", json);
        model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        List<Role> userList = yszyKkzysbService.getUserRoleCode(UserUtil.getCurrentUser().getId());
        Role userRole = userList.get(0);
        model.addAttribute("userRoleCode",userRole.getRoleCode());//得到用户的角色编码，只有安监局管理员才能对运单管理进行审核
        return "yszy/kkzysb/index";
    }

    @RequestMapping(value = "search")
    public String search(Model model) {
        return "yszy/kkzysb/search";
    }

    /**
     * 卡口作业申报list页面
     *
     * @param request
     */
    @RequestMapping(value = "list")
    @ResponseBody
    public Map<String, Object> getData(HttpServletRequest request, Model model) {
        Map<String, Object> map = getPageMap(request);
        String usertype = UserUtil.getCurrentShiroUser().getUsertype();
        if (usertype.equals("1")) {
            map.put("userid", UserUtil.getCurrentShiroUser().getId());
        } else if (usertype.equals("0") || usertype.equals("3") || usertype.equals("4")) {
            map.put("type", usertype);
        }
        map.put("entrust_company", request.getParameter("entrust_company"));//托运方
        map.put("consignee_company", request.getParameter("consignee_company"));//收货方
        map.put("accept_company", request.getParameter("accept_company"));//承运方
        map.put("plate_num", request.getParameter("plate_num"));//车牌号码
        map.put("driver_name", request.getParameter("driver_name"));//驾驶员姓名

        map.put("cphm", request.getParameter("cphm"));//车辆管理模块查看条件
        //判断运单是否已经超过预计到达时间两天，如果是，则将运单显示成已完成
        List<Long> list = yszyKkzysbService.getYdId();
        for(int i = 0;i < list.size();i++){
            Long id = StringUtils.toLong(list.get(i));
            YSZY_KkTaskDeclarationEntity entity = yszyKkzysbService.findInforById(id);
            entity.setState(2);
            yszyKkzysbService.save(entity);
        }
        return yszyKkzysbService.dataGrid(map);
    }

    /**
     * 卡口作业申报list页面
     *
     * @param request
     */
    @RequestMapping(value = "unfinishlist")
    @ResponseBody
    public List<Map<String, Object>> unfinishlist(HttpServletRequest request) {
        String plateNum = request.getParameter("plateNum");
        String number = request.getParameter("number");
        List<Map<String, Object>> resultList = yszyKkzysbService.findByPlateNumOnline(plateNum, number);
        return resultList;
    }

    /**
     * 添加页面跳转
     *
     * @param model
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request) {
        ShiroRealm.ShiroUser user = UserUtil.getCurrentShiroUser();
        model.addAttribute("action", "create");
        model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        if ("1".equals(user.getUsertype())) {
            model.addAttribute("qyname", user.getLoginName());
        }
        return "yszy/kkzysb/form";
    }

    /**
     * 添加卡口作业申报信息
     *
     * @param entity
     */
    @RequestMapping(value = "create")
    @ResponseBody
    public String create(YSZY_KkTaskDeclarationEntity entity, HttpServletRequest request) {
        String[] uniteNationNums = request.getParameterValues("uniteNationNum");
        String[] whpNames = request.getParameterValues("whpName");
        String[] tonnages = request.getParameterValues("tonnage");
        String[] packageTypes = request.getParameterValues("packageType");
        String[] specifications = request.getParameterValues("specification");
        String[] counts = request.getParameterValues("count");
        String[] types = new String[counts.length];
        int num = 0;
        for (String count : counts) {
            types[num] = StringUtils.join(request.getParameterValues("type_" + count), ",");
            num++;
        }
        String[] hazardouswastenames = request.getParameterValues("hazardouswastename");
        String[] hazardouswastetypes = request.getParameterValues("hazardouswastetype");
        String[] hazardouswastenums = request.getParameterValues("hazardouswastenum");
        String[] transferNumbers = request.getParameterValues("transferNumber");

        setBaseInfo(entity);
        return yszyKkzysbService.addInfo(entity, whpNames, uniteNationNums, tonnages, types, packageTypes,
                specifications,hazardouswastenames,hazardouswastetypes,hazardouswastenums,transferNumbers);
    }

    /**
     * 审核不通过修改页面跳转
     *
     * @param id,model
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Long id, Model model) {
        //根据id获取卡口作业申报信息
        YSZY_KkTaskDeclarationEntity entity = yszyKkzysbService.findInforById(id);
        model.addAttribute("entity", entity);
        List<YSZY_TransportationGoods> list = goodsService.listByTransId(id);
        model.addAttribute("goodsList", list);
        model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        model.addAttribute("action", "update");
        return "yszy/kkzysb/form";
    }

    /**
     * 审核不通过修改卡口作业申报信息
     *
     * @param entity,model
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public String xfssupdate(YSZY_KkTaskDeclarationEntity entity, HttpServletRequest request) {
        String[] whpNames = request.getParameterValues("whpName");
        String[] uniteNationNums = request.getParameterValues("uniteNationNum");
        String[] tonnages = request.getParameterValues("tonnage");
        String[] packageTypes = request.getParameterValues("packageType");
        String[] specifications = request.getParameterValues("specification");
        String[] counts = request.getParameterValues("count");
        String[] types = new String[counts.length];
        int num = 0;
        for (String count : counts) {
            types[num] = StringUtils.join(request.getParameterValues("type_" + count), ",");
            num++;
        }
        String[] hazardouswastenames = request.getParameterValues("hazardouswastename");
        String[] hazardouswastetypes = request.getParameterValues("hazardouswastetype");
        String[] hazardouswastenums = request.getParameterValues("hazardouswastenum");
        String[] transferNumbers = request.getParameterValues("transferNumber");

        String result = yszyKkzysbService.updateInfo(entity, whpNames, uniteNationNums, tonnages, types,
                packageTypes, specifications, hazardouswastenames, hazardouswastetypes, hazardouswastenums,transferNumbers);
        return result;
    }

    /**
     * 修改页面跳转
     *
     * @param id,model
     */
    @RequestMapping(value = "update2/{id}", method = RequestMethod.GET)
    public String update2(@PathVariable("id") Long id, Model model) {
        //根据id获取卡口作业申报信息
        YSZY_KkTaskDeclarationEntity entity = yszyKkzysbService.findInforById(id);
        model.addAttribute("entity", entity);
        model.addAttribute("action", "update2");
        return "yszy/kkzysb/form2";
    }

    /**
     * 修改承包方信息
     *
     * @param entity,model
     */
    @RequestMapping(value = "update2")
    @ResponseBody
    public String update2(YSZY_KkTaskDeclarationEntity entity, HttpServletRequest request) {
        String datasuccess = "success";
        Timestamp t = DateUtils.getSysTimestamp();
        entity.setS2(t);
        entity.setS3(0);
        yszyKkzysbService.save(entity);
        return datasuccess;
    }

    /**
     * 删除卡口作业申报信息
     */
    @RequestMapping(value = "delete/{ids}")
    @ResponseBody
    public String delete(@PathVariable("ids") String ids) {
        String datasuccess = "success";
        //可以批量删除
        String[] arrids = ids.split(",");
        for (int i = 0; i < arrids.length; i++) {
            //删除信息
            yszyKkzysbService.deleteInfo(Long.parseLong(arrids[i]));
        }
        return datasuccess;
    }

    /**
     * 查看页面跳转
     *
     * @param id,model
     */
    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String view(@RequestHeader("User-Agent") String userAgent, @PathVariable("id") Long id, Model model) {
        YSZY_KkTaskDeclarationEntity entity = yszyKkzysbService.findInforById(id);
        String userType = UserUtil.getCurrentUser().getUsertype();
        model.addAttribute("entity", entity);
        model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        List<YSZY_TransportationGoods> list = goodsService.listByTransId(id);
        model.addAttribute("goodsList", list);
        model.addAttribute("userType",userType);
        
        return "yszy/kkzysb/view";
    }

    /**
     * 查看页面跳转
     *
     * @param orderNum,model
     */
    @RequestMapping(value = "view2/{orderNum}", method = RequestMethod.GET)
    public String view2(@PathVariable("orderNum") String orderNum, Model model,@RequestHeader("User-Agent") String userAgent) {
        try {
            YSZY_KkTaskDeclarationEntity entity = yszyKkzysbService.findInforByOrderNum(orderNum);
            model.addAttribute("entity", entity);
            List<YSZY_TransportationGoods> list = goodsService.listByTransId(entity.getID());
            model.addAttribute("goodsList", list);
        } catch (Exception e) {
            model.addAttribute("error", "error");
            e.printStackTrace();
        }
        
        return "yszy/kkzysb/view";
    }


    @RequestMapping(value = "getGoods/{orderNum}", method = RequestMethod.GET)
    @ResponseBody
    public List<YSZY_TransportationGoods> getGoods(@PathVariable("orderNum") String orderNum) {
        List<YSZY_TransportationGoods> list = yszyKkzysbService.listByOrderNum(orderNum);
        return list;
    }


    /**
     * 显示所有列
     *
     * @param ,model
     */
    @RequestMapping(value = "colindex", method = RequestMethod.GET)
    public String colindex(Model model) {
        model.addAttribute("url", "yszy/kkzysb/export");
        return "common/formexcel";
    }

    /**
     * 导出excel
     */
    @RequestMapping(value = "export")
    @ResponseBody
    public void export(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        String usertype = UserUtil.getCurrentShiroUser().getUsertype();
        if (usertype.equals("1")) {
            map.put("userid", UserUtil.getCurrentShiroUser().getId());
        } else if (usertype.equals("0") || usertype.equals("3") || usertype.equals("4")) {
            map.put("type", usertype);
        }
        map.put("entrust_company", request.getParameter("entrust_company"));//托运方
        map.put("consignee_company", request.getParameter("consignee_company"));//收货方
        map.put("accept_company", request.getParameter("accept_company"));//承运方
        map.put("plate_num", request.getParameter("plate_num"));//车牌号码
        map.put("driver_name", request.getParameter("driver_name"));//驾驶员姓名
        map.put("colval", request.getParameter("colval"));
        map.put("coltext", request.getParameter("coltext"));
        map.putAll(getAuthorityMap());
        yszyKkzysbService.exportExcel(response, map);
    }

    /**
     * 修改卡口作业申报状态
     *
     * @param request,model
     */
    @RequestMapping(value = "changestatus")
    @ResponseBody
    public String changeStatus(HttpServletRequest request) {
        String dataSuccess = "";
        int result = 0;
        if (result > 0) {
            dataSuccess = "success";
        }
        return dataSuccess;
    }

    /**
     * 修改车辆出入状态
     *
     * @param request,model
     */
    @RequestMapping(value = "changeclcrstatus/{id}/{type}")
    @ResponseBody
    public String changeClcrStatus(@PathVariable("id") Long id, @PathVariable("type") Integer type, HttpServletRequest request) {
        String result = "success";
        Timestamp t = DateUtils.getSysTimestamp();
        YSZY_KkTaskDeclarationEntity entity = yszyKkzysbService.findInforById(id);
        entity.setState(type);
        entity.setS2(t);
        try {
            yszyKkzysbService.save(entity);
        } catch (Exception e) {
            result = "error";
        }

        //审核不通过发送消息
        if (type == 5) {
            String yj = request.getParameter("yj");

            Message msg = new Message();
            msg.setFROMUSER(UserUtil.getCurrentShiroUser().getId().toString());//发送人
            msg.setTITLE("运单处理");
            msg.setCREATETIME(DateUtils.getSysTimestamp());
            msg.setSENDSTATUE("0");
            msg.setTOUSER(entity.getUserid() + "");//接收人
            msg.setCONTENT("您好，您的运单（" + entity.getPlateNum() + "）因" + yj + "审核不通过，请及时修改!");
            msg.setURL("yszy/kkzysb/index");
            messageService.save(msg);
            YSZY_ReviewOpinion shyj = new YSZY_ReviewOpinion();
            shyj.setTransId(id);
            shyj.setUserid(UserUtil.getCurrentShiroUser().getId());
            shyj.setOpinion(yj);
            yszyReviewOpinionService.addInfo(shyj);
        }

        return result;
    }

    /**
     * 修改车辆出入状态
     *
     * @param request,model
     */
    @RequestMapping(value = "order")
    public String order(Model model, HttpServletRequest request) {

        String outnumber = request.getParameter("outnumber");
        if (StringUtils.isNotEmpty(outnumber)) {
            model.addAttribute("number", outnumber);
            return "app/order";
        }
        String number = request.getParameter("number");
        model.addAttribute("number", number);
        return "app/order";
    }


    /**
     * 查询未审核的运单
     *
     * @param request
     */
    @RequestMapping(value = "expiration/unreview")
    @ResponseBody
    public List<YSZY_KkTaskDeclarationEntity> unreviewlistExpiration(HttpServletRequest request) {
        String duration = request.getParameter("duration");
        String name = request.getParameter("name");
        List<YSZY_KkTaskDeclarationEntity> resultList = yszyKkzysbService.listUnReviewExpiration(duration);
        return resultList;
    }

    /**
     * 查询未审核的运单
     *
     * @param request
     */
    @RequestMapping(value = "unreview")
    @ResponseBody
    public List<YSZY_KkTaskDeclarationEntity> unreviewlist(HttpServletRequest request) {
        String name = request.getParameter("name");
        List<YSZY_KkTaskDeclarationEntity> resultList = yszyKkzysbService.listUnReview(name);
        return resultList;
    }


}
