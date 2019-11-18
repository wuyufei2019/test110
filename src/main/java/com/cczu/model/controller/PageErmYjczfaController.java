package com.cczu.model.controller;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.ERM_EmergencyDisposalPlan;
import com.cczu.model.service.ErmYjczfaService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;
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
import java.util.*;

@Controller
@RequestMapping("erm/yjczfa")
public class PageErmYjczfaController extends BaseController {

    @Autowired
    private IBisQyjbxxService bisQyjbxxService;
    
    @Autowired
    private ErmYjczfaService ermYjczfaService;

    /**
     * 列表显示页面
     *
     * @param model
     */
    @RequestMapping(value = "index")
    public String index(Model model) {
        ShiroRealm.ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
        if(sessionuser.getUsertype().equals("1")){//企业用户
            BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
            if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
                if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
                    model.addAttribute("type", 2);
                else
                    model.addAttribute("type", 1);
            }
        }
        String usertype = UserUtil.getCurrentShiroUser().getUsertype();
        model.addAttribute("usertype", usertype);
        return "erm/yjczfa/index";
    }

    /**
     * list页面
     *
     * @param request
     */
    @RequiresPermissions("erm:yjczfa:view")
    @RequestMapping(value = "list")
    @ResponseBody
    public Map<String, Object> getData(HttpServletRequest request) {

        Map<String, Object> map = getPageMap(request);
        map.putAll(getAuthorityMap());
        map.put("qyname", request.getParameter("view_qyname"));
        map.put("m1", request.getParameter("m1"));
        map.put("m2", request.getParameter("m2"));
        return ermYjczfaService.dataGrid(map);

    }





    /**
     * 添加页面跳转
     *
     * @param model
     */
    @RequiresPermissions("erm:yjczfa:add")
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("action", "create");
        model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        return "erm/yjczfa/form";
    }

    /**
     * 添加演练记录信息
     *
     * @param request
     *            ,model
     */
    @RequiresPermissions("erm:yjczfa:add")
    @RequestMapping(value = "create")
    @ResponseBody
    public String create(ERM_EmergencyDisposalPlan ee, Model model) {
        String datasuccess = "success";
        if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
            ee.setID1(UserUtil.getCurrentShiroUser().getQyid());
        }
        ermYjczfaService.addInfo(ee);
        // 返回结果
        return datasuccess;
    }

    /**
     * 修改页面跳转
     *
     * @param id
     *            ,model
     */
    @RequiresPermissions("erm:yjczfa:update")
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Integer id, Model model) {
        // 查询选择的演练记录信息

        long id1 = id;
        ERM_EmergencyDisposalPlan ee = ermYjczfaService.findById(id1);
        model.addAttribute("res", ee);
        // 返回页面
        model.addAttribute("action", "update");
        model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        return "erm/yjczfa/form";
    }

    /**
     * 修改演练记录信息
     *
     * @param
     *            ,model
     */
    @RequiresPermissions("erm:yjczfa:update")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public String update(ERM_EmergencyDisposalPlan ee, Model model) {
        String datasuccess = "success";

        ermYjczfaService.updateInfo(ee);
        // 返回结果
        return datasuccess;
    }

    /**
     * 删除演练记录信息
     *
     * @param
     * @param
     * @throws
     */
    @RequiresPermissions("erm:yjczfa:delete")
    @RequestMapping(value = "delete/{ids}")
    @ResponseBody
    public String delete(@PathVariable("ids") String ids) {
        String datasuccess = "删除成功";
        // 可以批量删除
        String[] arrids = ids.split(",");
        for (int i = 0; i < arrids.length; i++) {
            ermYjczfaService.deleteInfo(Long.parseLong(arrids[i]));
        }
        return datasuccess;
    }

    /**
     * 查看页面跳转
     * @param id,model
     */
    @RequiresPermissions("erm:yjczfa:view")
    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Integer id, Model model) {
        //查询选择的产品信息

        long id1 = id;
        ERM_EmergencyDisposalPlan ee = ermYjczfaService.findById(id1);
        model.addAttribute("res", ee);
        //返回页面
        model.addAttribute("action", "view");
        model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        return "erm/yjczfa/view";
    }

    /**
     * 导出word
     *
     */
    @RequiresPermissions("erm:yjczfa:export")
    @RequestMapping(value = "exportword/{id}")
    @ResponseBody
    public String exportWord(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> map=ermYjczfaService.getExportWord(id);
        //设置导出的文件名
        String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
        //设置模板路径
        String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
        WordUtil.ireportWord(map, "yjczfa.ftl", filePath, filename, request);
        return "/download/" + filename;
    }
    //导入页面跳转
    @RequestMapping(value = "exinjump", method = RequestMethod.GET)
    public String exin(Model model) {
        model.addAttribute("action", "exin");
        return "common/importForm";
    }
}
