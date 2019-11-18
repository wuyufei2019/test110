package com.cczu.model.ztzr.controller;

import com.cczu.model.sggl.service.SgglSgxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
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
 * 安全制度规程及预案编制Controller
 * @author jason
 */
@Controller
@RequestMapping("glr/gzzd")
public class PageGlrGzzdController extends BaseController {
	
	@Autowired
	private SgglSgxxService sgglSgxxService;
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "ztzr/glr/gzzd/index";
	}
	

	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		
		int pageNo=1;	//当前页码
		int pageSize=20;	//每页行数
		if(StringUtils.isNotEmpty(request.getParameter("page")))
			pageNo=Integer.valueOf(request.getParameter("page"));
		if(StringUtils.isNotEmpty(request.getParameter("rows")))
			pageSize=Integer.valueOf(request.getParameter("rows"));
		
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(getAuthorityMap());
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);
		map.put("sort", request.getParameter("sort"));
		map.put("order", request.getParameter("order"));
		map.put("ztzr_aqzd_cx_qyname", request.getParameter("ztzr_aqzd_cx_qyname"));
		map.put("xzqy",sessionuser.getXzqy());
		map.put("year2",request.getParameter("ztzr_aqzd_year"));
		return sgglSgxxService.dataGrid4(map);
		
	}
	
}
