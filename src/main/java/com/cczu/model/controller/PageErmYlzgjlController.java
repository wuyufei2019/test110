package com.cczu.model.controller;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.ERM_ExerciseReformRecordEntity;
import com.cczu.model.service.ErmYlzgjlService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("erm/ylzgjl")
public class PageErmYlzgjlController extends BaseController {


    @Autowired
    private IBisQyjbxxService bisQyjbxxService;
    @Autowired
    private ErmYlzgjlService ermYlzgjlService;

    /**
     * 列表显示页面
     *
     * @param model
     */
    @RequestMapping(value = "index")
    public String index(Model model) {
        ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
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
        return "erm/yjyl/ylzgjl/index";
    }

    /**
     * list页面
     *
     * @param request
     */
    @RequiresPermissions("erm:ylzgjl:view")
    @RequestMapping(value = "list")
    @ResponseBody
    public Map<String, Object> getData(HttpServletRequest request) {

        Map<String, Object> map = getPageMap(request);
        map.putAll(getAuthorityMap());
        map.put("qynm", request.getParameter("view_qyname"));
        map.put("m1", request.getParameter("m1"));
        map.put("m3", request.getParameter("m3"));
        return ermYlzgjlService.dataGrid(map);

    }

   

    

    /**
     * 添加页面跳转
     *
     * @param model
     */
    @RequiresPermissions("erm:ylzgjl:add")
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("action", "create");
        model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        return "erm/yjyl/ylzgjl/form";
    }

    /**
     * 添加演练记录信息
     *
     * @param request
     *            ,model
     */
    @RequiresPermissions("erm:ylzgjl:add")
    @RequestMapping(value = "create")
    @ResponseBody
    public String create(ERM_ExerciseReformRecordEntity ee, Model model) {
        String datasuccess = "success";
        if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
            ee.setID1(UserUtil.getCurrentShiroUser().getQyid());
        }
        ermYlzgjlService.addInfo(ee);
        // 返回结果
        return datasuccess;
    }

    /**
     * 修改页面跳转
     *
     * @param id
     *            ,model
     */
    @RequiresPermissions("erm:ylzgjl:update")
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Integer id, Model model) {
        // 查询选择的演练记录信息

        long id1 = id;
        ERM_ExerciseReformRecordEntity ee = ermYlzgjlService.findById(id1);
        model.addAttribute("res", ee);
        // 返回页面
        model.addAttribute("action", "update");
        model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        return "erm/yjyl/ylzgjl/form";
    }

    /**
     * 修改演练记录信息
     *
     * @param
     *            ,model
     */
    @RequiresPermissions("erm:ylzgjl:update")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public String update(ERM_ExerciseReformRecordEntity ee, Model model) {
        String datasuccess = "success";

        ermYlzgjlService.updateInfo(ee);
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
    @RequiresPermissions("erm:ylzgjl:delete")
    @RequestMapping(value = "delete/{ids}")
    @ResponseBody
    public String delete(@PathVariable("ids") String ids) {
        String datasuccess = "删除成功";
        // 可以批量删除
        String[] arrids = ids.split(",");
        for (int i = 0; i < arrids.length; i++) {
            ermYlzgjlService.deleteInfo(Long.parseLong(arrids[i]));
        }
        return datasuccess;
    }

    /**
     * 查看页面跳转
     * @param id,model
     */
    @RequiresPermissions("erm:ylzgjl:view")
    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Integer id, Model model) {
        //查询选择的产品信息

        long id1 = id;
        ERM_ExerciseReformRecordEntity ee = ermYlzgjlService.findById(id1);
        model.addAttribute("res", ee);
        //返回页面
        model.addAttribute("action", "view");
        model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        return "erm/yjyl/ylzgjl/view";
    }

    //导入页面跳转
    @RequestMapping(value = "exinjump", method = RequestMethod.GET)
    public String exin(Model model) {
        model.addAttribute("action", "exin");
        return "common/importForm";
    }

}
