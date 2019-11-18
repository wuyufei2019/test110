package com.cczu.model.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.FXGK_RiskPerEntity;
import com.cczu.model.service.FxgkFxzpzService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;

/**
 * 风险管控_风险值配置Controller
 * @author zpc
 * @date 2017年8月10日
 */
@Controller
@RequestMapping("fxgk/fxzpz")
public class PageFxgkFxzpzController extends BaseController{

	@Autowired
	private FxgkFxzpzService fxgkFxzpzService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index",method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("fxz", fxgkFxzpzService.findInfor());
		return "fxgk/fxzpz/index";
	}
	
	
	/**
	 * 添加或修改操作
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update" , method = RequestMethod.POST)
	@ResponseBody
	public String create(FXGK_RiskPerEntity fxz, Model model) {
		fxgkFxzpzService.updateInfo(fxz);
		return "success";
	}
	
	/**
	 * 获取风险值信息
	 * @param request,model
	 */
	@RequestMapping(value = "getjson" )
	@ResponseBody
	public String getInforJson() {
		FXGK_RiskPerEntity fxz =fxgkFxzpzService.findInfor();
		return JsonMapper.getInstance().toJson(fxz);
	}

}
