package com.cczu.model.controller;

import com.cczu.model.entity.Sbgl_YfbyndjhEntity;
import com.cczu.model.service.SbglYfbyndjhService;
import com.cczu.sys.comm.controller.BaseController;
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
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设备管理-预防保养年度计划controller
 *
 */
@Controller
@RequestMapping("sbgl/yfbyndjh")
public class PageSbglYfbyndjhController extends BaseController {

    @Autowired
    private SbglYfbyndjhService sbglYfbyndjhService;

    /**
     * 列表显示页面
     * @param model
     */
    @RequestMapping(value="index")
    public String index(Model model, HttpServletRequest request) {
        model.addAttribute("qyid", request.getParameter("qyid"));
        return "sbgl/yfbyndjh/index";
    }

    /**
     * list页面
     * @param request
     */
    @RequestMapping(value="list")
    @ResponseBody
    public Map<String, Object> getData(HttpServletRequest request) {
        Map<String, Object> map = getPageMap(request);
        String qyid = request.getParameter("qyid");
        map.put("m1", request.getParameter("m1"));
        map.put("m8", request.getParameter("m8"));

        if(StringUtils.isEmpty(qyid)){
            map.put("qyid", UserUtil.getCurrentUser().getId2());
        }else{
            map.put("qyid", qyid);
        }
        return sbglYfbyndjhService.dataGrid(map);
    }

    /**
     * 添加页面跳转
     *
     * @param model
     */
    @RequiresPermissions("sbgl:yfbyndjh:add")
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("action", "create");
        return "sbgl/yfbyndjh/form";
    }


    /**
     * 添加信息
     *
     * @param
     */
    @RequiresPermissions("sbgl:yfbyndjh:add")
    @RequestMapping(value = "create")
    @ResponseBody
    public String create(Sbgl_YfbyndjhEntity e) {
        String datasuccess = "success";
        sbglYfbyndjhService.addInfo(e);
        return datasuccess;
    }

    /**
     * 修改页面跳转
     *
     * @param id,model
     */
    @RequiresPermissions("sbgl:yfbyndjh:update")
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Integer id, Model model) {
        List<Sbgl_YfbyndjhEntity> bs = sbglYfbyndjhService.findById(id);
        model.addAttribute("sbgl", bs.get(0));
        // 返回页面
        model.addAttribute("action", "update");
        return "sbgl/yfbyndjh/form";
    }

    /**
     * 修改信息
     *
     * @param request,model
     */
    @RequiresPermissions("sbgl:yfbyndjh:update")
    @RequestMapping(value = "update")
    @ResponseBody
    public String update(Sbgl_YfbyndjhEntity bs, Model model, HttpServletRequest request) {
        String datasuccess = "success";

        sbglYfbyndjhService.updateInfo(bs);
        // 返回结果
        return datasuccess;
    }

    /**
     * 删除信息
     *
     *
     * @throws ParseException
     */
    @RequiresPermissions("sbgl:yfbyndjh:delete")
    @RequestMapping(value = "delete/{ids}")
    @ResponseBody
    public String delete(@PathVariable("ids") String ids) {
        String datasuccess = "删除成功";
        // 可以批量删除
        String[] arrids = ids.split(",");
        for (int i = 0; i < arrids.length; i++) {
            sbglYfbyndjhService.deleteInfo(Long.parseLong(arrids[i]));
        }

        return datasuccess;
    }

    /**
     * 查看页面跳转
     *
     * @param id,model
     */
    @RequiresPermissions("sbgl:yfbyndjh:view")
    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, Model model) {
        List<Sbgl_YfbyndjhEntity> bs = sbglYfbyndjhService.findById(id);
        model.addAttribute("sbgl", bs.get(0));
        // 返回页面
        return "sbgl/yfbyndjh/view";
    }

    /**
     * 显示所有列
     * @param ,model
     */
    @RequiresPermissions("sbgl:yfbyndjh:export")
    @RequestMapping(value = "colindex", method = RequestMethod.GET)
    public String colindex(Model model) {
        model.addAttribute("url","sbgl/yfbyndjh/export");
        return "/common/formexcel";
    }


    /**
     * 导出excel
     */
    @RequiresPermissions("sbgl:yfbyndjh:export")
    @RequestMapping(value = "export")
    @ResponseBody
    public void export(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        String qyid = request.getParameter("qyid");
        map.put("m1", request.getParameter("m1"));
        map.put("m8", request.getParameter("m8"));
        if(StringUtils.isEmpty(qyid)){
            map.put("qyid", UserUtil.getCurrentUser().getId2());
        }else{
            map.put("qyid", qyid);
        }
        map.put("colval", request.getParameter("colval"));
        map.put("coltext", request.getParameter("coltext"));
        sbglYfbyndjhService.exportExcel(response, map);
    }
}
