package com.cczu.model.ztzr.controller;

import com.cczu.model.mbgl.entity.AQSC_ExpenseExtraction;
import com.cczu.model.mbgl.service.AqscFytqService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.utils.UserUtil;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 企业主体责任-安全生产投入Controller
 * @author ll
 */
@Controller
@RequestMapping("ztzr/aqsctr")
public class PageZtzrSctrController extends BaseController {

	@Autowired
	private AqscFytqService aqscFytqService;

	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model,HttpServletRequest request) {
		return "ztzr/fzr/aqsctr/index";
	}
	
	/**
	 *查询list 
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("m1", request.getParameter("ztzr_sctr_m1"));
		map.put("qyname", request.getParameter("qyname"));
		map.put("m3", request.getParameter("aqzf_fytq_m3"));
		map.putAll(getAuthorityMap());
		return aqscFytqService.dataGrid2(map);
		
	}
	
}
