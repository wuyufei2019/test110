package com.cczu.model.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.service.IBisYgxxService;
import com.cczu.sys.comm.controller.BaseController;

/**
 * 应急辅助决策-人员清点controller
 * @author jason
 *
 */
@Controller
@RequestMapping("ead/ryqd")
public class PageEadRyqdController extends BaseController {
	@Autowired
	private IBisYgxxService bisYgxxService;

	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "ead/ryqd/index";
	}

	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("ead:ryqd:count")
	@RequestMapping(value = "export/{ids}")
	@ResponseBody
	public void export(@PathVariable String ids, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ygids", ids);
		map.put("colval","");
		bisYgxxService.exportExcel(response,map);
	}
}
