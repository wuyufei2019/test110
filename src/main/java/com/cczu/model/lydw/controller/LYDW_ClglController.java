package com.cczu.model.lydw.controller;

import com.cczu.model.lydw.entity.LYDW_Emp_Pubfile;
import com.cczu.model.lydw.entity.Pub_File;
import com.cczu.model.lydw.service.LYDW_ClglService;
import com.cczu.model.lydw.service.Pub_FileService;
import com.cczu.sys.comm.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 蓝牙定位---车辆绑卡管理
 */
@Controller
@RequestMapping("lydw/clgl")
public class LYDW_ClglController extends BaseController {

    @Autowired
    private LYDW_ClglService lydw_clglService;
	@Autowired
	private Pub_FileService pfService;

    /**
     * 首页
     * @param model
     */
    @RequestMapping(value="index")
    public String index(Model model) {
        return "lydw/clgl/index";
    }

    /**
     * 人员信息listjson
     * @param request
     */
    @RequestMapping(value="listjson")
    @ResponseBody
    public Map<String, Object> getData(HttpServletRequest request) {
        Map<String, Object> map = getPageMap(request);
        map.put("carNumber", request.getParameter("lydw_clgl_cx_m2"));
        map.put("driverName", request.getParameter("lydw_clgl_cx_m1"));
        map.put("fileCode", request.getParameter("lydw_clgl_cx_filecode"));
        map.putAll(getAuthorityMap());
        return lydw_clglService.dataGrid(map);
    }

	/**
	 * 跳转绑定工卡页面
	 */
	@RequestMapping(value="selectcard/{ygid}")
	public String selectcard(@PathVariable("ygid") Long ygid, Model model) {
		model.addAttribute("ygid",ygid);
		model.addAttribute("action","selectcard");
		return "lydw/clgl/cardform";
	}

	/**
	 * 绑定工卡
	 */
	@RequestMapping(value = "selectcard")
	@ResponseBody
	public String bdcard(LYDW_Emp_Pubfile entity) {
		String datasuccess = "success";
		Pub_File pub_file = pfService.findById(Long.valueOf(entity.getFileid()));
		if (pub_file != null) {
			lydw_clglService.addInfo(entity);
		}else {
			datasuccess = "error";
		}
		return datasuccess;
	}

	/**
	 * 解绑
	 */
	@RequestMapping(value = "undocard/{bdid}")
	@ResponseBody
	public String undocard(@PathVariable("bdid") Long bdid) {
		String datasuccess = "success";
		lydw_clglService.undocard(bdid);
		return datasuccess;
	}
}
