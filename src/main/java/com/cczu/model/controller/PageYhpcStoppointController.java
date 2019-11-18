package com.cczu.model.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.YHPC_StopPoint;
import com.cczu.model.service.YhpcStoppointService;
import com.cczu.sys.comm.controller.BaseController;

/**
 * 隐患排查风险隐患点停产Controller
 */
@Controller
@RequestMapping("yhpc/stoppoint")
public class PageYhpcStoppointController extends BaseController {

	@Autowired
	private YhpcStoppointService yhpcStoppointService;
	
	/**
	 * 恢复
	 */
	@RequestMapping(value = "enable/{id1}/{type}")
	@ResponseBody
	public String enable(@PathVariable("id1") Long id1,@PathVariable("type") String type) {
		String str="恢复成功！";
		yhpcStoppointService.deleteById1AndType(id1, type);
		return str;
	}
	
	/**
	 * 停产页面跳转
	 */
	@RequestMapping(value = "disableindex/{id1}/{type}")
	public String disableindex(@PathVariable("id1") Long id1,@PathVariable("type") String type,Model model) {
		model.addAttribute("id1",id1);
		model.addAttribute("type",type);
		return "yhpc/stoppoint/tcform";
	}
	
	/**
	 * 停产信息添加
	 */
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(YHPC_StopPoint entity,HttpServletRequest request) {
		String str="success";
		yhpcStoppointService.save(entity);
		return str;
	}
}
