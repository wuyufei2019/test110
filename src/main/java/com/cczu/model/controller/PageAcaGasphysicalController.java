package com.cczu.model.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.ACA_GasphysicalEntity;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IAcaGasphysicalService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 事故后果计算controller
 * @author jason
 */
@Controller
@RequestMapping("sghgjs/gasphysical")
public class PageAcaGasphysicalController extends BaseController {

	@Autowired
	private IAcaGasphysicalService acaGasphysicalService;
	@Autowired
	private BisQyjbxxServiceImpl bisQyjbxxService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(UserUtil.getCurrentShiroUser().getQyid());
		if(be!=null){
			String lng = be.getM16();//企业所在位置的经度
			String lat = be.getM17();//企业所在位置的纬度
			model.addAttribute("qylng", lng);
			model.addAttribute("qylat", lat);
		}
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "sghgjs/gasphysical/index";
	}
	
	/**
	 * @param model
	 */
//	@RequiresPermissions("aca:gasphysical:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model){
//		model.addAttribute("list", new ACA_GasphysicalEntity());
		return "sghgjs/gasphysical/form";
	}
	
	/**
	 * @param model
	 */
//	@RequiresPermissions("aca:gasphysical:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(@Valid ACA_GasphysicalEntity aca ,Model model) throws Exception{
		return acaGasphysicalService.countSave(aca);
	}
	
}
