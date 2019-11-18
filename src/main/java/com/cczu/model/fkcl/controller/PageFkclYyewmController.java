package com.cczu.model.fkcl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.fkcl.entity.FKCL_YyewmEntity;
import com.cczu.model.fkcl.service.FkclYyermService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.QRCode;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 预约二维码controller
 */
@Controller
@RequestMapping("fkcl/yyewm")
public class PageFkclYyewmController extends BaseController {

	@Autowired
	private FkclYyermService fkclYyermService;
	
	/**
	 * 跳转页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		Map<String,Object> map = new HashMap<>();
		map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		map.put("type", "1");
		List<FKCL_YyewmEntity> list = fkclYyermService.findAllList(map);
		if(list.size()>0){
			model.addAttribute("fkyyewm",list.get(0));
		}
		return "fkcl/yyewm/index";
	}
	
	/**
	 * 保存
	 * @param model
	 */
	@RequestMapping(value = "bc")
	@ResponseBody
	public String bc(String type, String ewm, Model model){
		String datasuccess="success";
		Map<String,Object> map = new HashMap<>();
		map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		map.put("type", type);
		List<FKCL_YyewmEntity> list = fkclYyermService.findAllList(map);
		FKCL_YyewmEntity fkyyewm;
		if(list.size()>0){
			fkyyewm = list.get(0);
			fkyyewm.setEwm(ewm);
		}else{
			fkyyewm = new FKCL_YyewmEntity();
			fkyyewm.setQyid(UserUtil.getCurrentShiroUser().getQyid());
			fkyyewm.setType(type);
			fkyyewm.setEwm(ewm);
		}
		fkclYyermService.save(fkyyewm);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 生成二维码图片
	 */
	@RequestMapping(value="erm")
	@ResponseBody
	public String erweima(String ewm,HttpServletResponse response,HttpServletRequest request) {
		// 取得输出流        
		String dowmloadPath = request.getSession().getServletContext().getRealPath("/")+"/download";
		String url="/download/";
		try {
			url =url+ QRCode.encode(ewm, null, dowmloadPath, true,"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}
}
