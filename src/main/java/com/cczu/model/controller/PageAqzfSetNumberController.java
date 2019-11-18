package com.cczu.model.controller;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.AQZF_SetNumberEntity;
import com.cczu.model.service.AqzfSetNumberService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;

/**
 * 安全执法_设置文书编号Controller
 * @author jason
 * @date 2017年8月3日
 */
@Controller
@RequestMapping("aqzf/wsbh")
public class PageAqzfSetNumberController extends BaseController{

	@Autowired
	private AqzfSetNumberService setNumberService;
	
	/**
	 * 默认页面
	 */
	@RequiresPermissions("aqzf:wsbh:view")
	@RequestMapping(value="index",method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("bh", setNumberService.findInfor());
		return "aqzf/sjwh/szbh/index";
	}
	
	
	/**
	 * 添加或修改操作
	 * @param model
	 * @return
	 */
	@RequiresPermissions("aqzf:wsbh:update")
	@RequestMapping(value = "update" , method = RequestMethod.POST)
	@ResponseBody
	public String create(AQZF_SetNumberEntity bh, Model model) {
		setNumberService.updateInfo(bh);
		return "success";
	}
	
	/**
	 * 获取文书编号信息
	 * @param request,model
	 */
	@RequiresPermissions("aqzf:wsbh:view")
	@RequestMapping(value = "getjson" )
	@ResponseBody
	public String getInforJson() {
		AQZF_SetNumberEntity bh =setNumberService.findInfor();
		return JsonMapper.getInstance().toJson(bh);
	}

}
