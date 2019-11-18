package com.cczu.model.ztzr.controller;

import com.cczu.model.mbgl.service.TargetAqzzService;
import com.cczu.model.service.BisGzxxService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.zdgl.service.ZdglGlzdService;
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
@RequestMapping("ztzr/gzzd")
public class PageZtzrGzzdController extends BaseController {

	@Autowired
	private ZdglGlzdService zdglGlzdService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model,HttpServletRequest request) {
		return "ztzr/fzr/gzzd/index";
	}

	/**
	 * list页面
	 *
	 * @param request
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.putAll(getAuthorityMap());
		map.put("ajqyname", request.getParameter("ajqyname"));
		map.put("year", request.getParameter("ztzr_gzzd_year"));
		return zdglGlzdService.dataGrid2(map);
	}
}
