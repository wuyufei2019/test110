package com.cczu.model.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.ACA_PhysicalEntity;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IAcaPhysicalService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 事故后果计算controller
 * @author jason
 */
@Controller
@RequestMapping("sghgjs/physical")
public class PageAcaPhysicalController extends BaseController {

	@Autowired
	private IAcaPhysicalService acaPhysicalService;
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
		return "sghgjs/physical/index";
	}
	
	/**
	 * @param model
	 */
//	@RequiresPermissions("aca:physical:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model){
//		model.addAttribute("list", new ACA_PhysicalEntity());
		return "sghgjs/physical/form";
	}
	
	/**
	 * @param model
	 */
//	@RequiresPermissions("aca:physical:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(@Valid ACA_PhysicalEntity aca ,Model model) throws Exception{
		return acaPhysicalService.countSave(aca);
	}
	
}
