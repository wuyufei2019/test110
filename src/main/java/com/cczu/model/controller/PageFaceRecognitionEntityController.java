package com.cczu.model.controller;

import java.sql.Timestamp;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cczu.model.entity.FaceRecognitionEntity;
import com.cczu.model.service.FaceRecognitionEntityService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.sys.comm.utils.ServletUtils;

/**
 * 双向人脸识别controller
 * @author
 * @date 2019年11月18日
 */
@Controller
@RequestMapping("glsb/rysb")
public class PageFaceRecognitionEntityController extends BaseController {

    @Autowired
    private FaceRecognitionEntityService faceRecognitionEntityService;

    /**
     *页面跳转
     * @param model
     */
    @RequestMapping(value = "index")
    public String index(Model model) {
        return "glsb/rysb/index";
    }

    /**
     * list界面
     * @param request
     */
    @RequiresPermissions("glsb:rysb:view")
    @RequestMapping(value = "list")
    @ResponseBody
    public Map<String, Object> getData(HttpServletRequest request) {
        Map<String, Object> dataMap = getPageMap(request);
        dataMap.putAll(ServletUtils.getParametersStartingWith(request,  "view_"));
        return faceRecognitionEntityService.dataGrid(dataMap);
    }


    /**
     * 添加页面跳转
     * @param model
     */
    @RequiresPermissions("glsb:rysb:add")
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("action", "create");
        return "glsb/rysb/form";
    }

    /**
     * 添加信息
     * @param
     */
    @RequiresPermissions("glsb:rysb:add")
    @RequestMapping(value = "create")
    @ResponseBody
    public String create(FaceRecognitionEntity entity) {
        String datasuccess = "success";
        Timestamp t=DateUtils.getSysTimestamp();
        entity.setS1(t);
        entity.setS2(t);
        entity.setS3(0);
        faceRecognitionEntityService.addInfo(entity);
        return datasuccess;
    }

    /**
     * 修改页面跳转
     * @param id,model
     */
    @RequiresPermissions("glsb:rysb:update")
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") long id, Model model) {
        FaceRecognitionEntity entity = faceRecognitionEntityService.findById(id);
        model.addAttribute("entity", entity);
        model.addAttribute("action", "update");
        return "glsb/rysb/form";
    }

    /**
     * 修改信息
     *
     * @param request,model
     */
    @RequiresPermissions("glsb:rysb:update")
    @RequestMapping(value = "update")
    @ResponseBody
    public String update(FaceRecognitionEntity entity, Model model) {
        String datasuccess = "success";
        Timestamp t = DateUtils.getSysTimestamp();
        entity.setS2(t);
        faceRecognitionEntityService.updateInfo(entity);
        return datasuccess;
    }

    /**
     * 删除信息
     * @param ids
     */
    @RequiresPermissions("glsb:rysb:delete")
    @RequestMapping(value = "delete/{ids}")
    @ResponseBody
    public String delete(@PathVariable("ids") String ids) {
        String datasuccess = "success";
        String[] arrids = ids.split(",");
        for (int i = 0; i < arrids.length; i++) {
            faceRecognitionEntityService.deleteInfo(Long.parseLong(arrids[i]));
        }
        return datasuccess;
    }

    /**
     * 查看页面跳转
     * @param id
     */
    @RequiresPermissions("glsb:rysb:view")
    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String sview(@PathVariable("id") long id ,Model model) {
        FaceRecognitionEntity entity = faceRecognitionEntityService.findById(id);
        model.addAttribute("entity", entity);
        return "glsb/rysb/view";
    }

}
