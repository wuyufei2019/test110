package com.cczu.model.controller;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.model.service.AqzfSetBasicInfoService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;

/**
 * 安全执法_设置基本信息Controller
 * @author jason
 * @date 2017年8月3日
 */
@Controller
@RequestMapping("aqzf/xxsz")
public class PageAqzfSetBasicInfoController extends BaseController{

	@Autowired
	private AqzfSetBasicInfoService setbasicservice;
	
	/**
	 * 默认页面
	 */
	@RequiresPermissions("aqzf:xxsz:view")
	@RequestMapping(value="index",method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("bh", setbasicservice.findInfor());
		return "aqzf/sjwh/xxsz/index";
	}
	
	
	/**
	 * 添加或修改操作
	 * @param model
	 * @return
	 */
	@RequiresPermissions("aqzf:xxsz:update")
	@RequestMapping(value = "update" , method = RequestMethod.POST)
	@ResponseBody
	public String create(AQZF_SetBasicInfoEntity bh, Model model) {
		setbasicservice.updateInfo(bh);
		return "success";
	}
	
	/**
	 * 获取文书编号信息
	 * @param request,model
	 */
	@RequiresPermissions("aqzf:xxsz:view")
	@RequestMapping(value = "getjson" )
	@ResponseBody
	public String getInforJson() {
		AQZF_SetBasicInfoEntity bh =setbasicservice.findInfor();
		return JsonMapper.getInstance().toJson(bh);
	}

}
