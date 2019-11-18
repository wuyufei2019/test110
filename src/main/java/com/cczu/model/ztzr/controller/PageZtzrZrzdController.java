package com.cczu.model.ztzr.controller;

import com.cczu.model.mbgl.service.TargetAqzzService;
import com.cczu.model.service.BisGzxxService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 目标管理-安全职责controller
 * 
 * @author jason
 * 
 */

@Controller
@RequestMapping("ztzr/zrzd")
public class PageZtzrZrzdController extends BaseController {

	@Autowired
	private TargetAqzzService targetAqzzService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model,HttpServletRequest request) {
		return "ztzr/fzr/aqsczr/index";
	}

	/**
	 * list页面 企业端 
	 * 
	 * @param request
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getQyData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		String ajqyid = request.getParameter("ajqyid");
		map.put("name", request.getParameter("target_aqzz_gwname"));
		return targetAqzzService.dataGrid2(map);
	}

}
