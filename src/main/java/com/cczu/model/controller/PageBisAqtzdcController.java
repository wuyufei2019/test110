package com.cczu.model.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.service.BisAqtzdcService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 安全台账导出controller
 */
@Controller
@RequestMapping("bis/aqtzdc")
public class PageBisAqtzdcController extends BaseController {

	@Autowired
	private BisAqtzdcService bisAqtzdcService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model,HttpServletRequest request) {
		return "qyxx/aqtzdc/index";	
	}
	
	/**
	 * 导出
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(String checkval, String starttime, String finishtime, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(!StringUtils.isEmpty(starttime)){
			map.put("aqtzstartnf", starttime.substring(0, 4));
			map.put("aqtzstarttime", starttime);
		}
		if(!StringUtils.isEmpty(finishtime)){
			map.put("aqtzfinishnf", finishtime.substring(0, 4));
			map.put("aqtzfinishtime", finishtime);
		}
		map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		bisAqtzdcService.exportExcel(response, map , checkval);
	}
}
