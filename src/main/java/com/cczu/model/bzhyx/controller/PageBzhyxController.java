package com.cczu.model.bzhyx.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.bzhyx.service.BzhyxService;
import com.cczu.sys.comm.controller.BaseController;

/**
 * 标准化运行controller
 */
@Controller
@RequestMapping("bzhyx")
public class PageBzhyxController extends BaseController {

	@Autowired
	private BzhyxService bzhyxService;
	
	/**
	 * 目标指标列表显示页面
	 * @param model
	 */
	@RequestMapping(value="mbzb/index")
	public String mbzbindex(HttpServletRequest request,Model model) {
		return "bzhyx/mbzbindex";
	}
	
	/**
	 * 目标指标list页面
	 * @param request
	 */
	@RequestMapping(value="mbzb/list")
	@ResponseBody
	public Map<String, Object> getmbzbData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("qyname"));
		
		map.put("starttime", request.getParameter("starttime"));
		map.put("finishtime", request.getParameter("finishtime"));
		
		map.putAll(getAuthorityMap());	
		return bzhyxService.mbzbdataGrid(map);
	}

	/**
	 * 设备管理列表显示页面
	 * @param model
	 */
	@RequestMapping(value="sbgl/index")
	public String sbglindex(HttpServletRequest request,Model model) {
		return "bzhyx/sbglindex";
	}

	/**
	 * 设备管理list页面
	 * @param request
	 */
	@RequestMapping(value="sbgl/list")
	@ResponseBody
	public Map<String, Object> sbglData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("qyname"));
		
		map.put("starttime", request.getParameter("starttime"));
		map.put("finishtime", request.getParameter("finishtime"));

		map.putAll(getAuthorityMap());
		return bzhyxService.sbgldataGrid(map);
	}

	/**
	 * 危险作业列表显示页面
	 * @param model
	 */
	@RequestMapping(value="zyaq/index")
	public String zyaqindex(HttpServletRequest request,Model model) {
		return "bzhyx/zyaqindex";
	}

	/**
	 * 危险作业list页面
	 * @param request
	 */
	@RequestMapping(value="zyaq/list")
	@ResponseBody
	public Map<String, Object> zyaqData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("qyname"));
		
		map.put("starttime", request.getParameter("starttime"));
		map.put("finishtime", request.getParameter("finishtime"));

		map.putAll(getAuthorityMap());
		return bzhyxService.zyaqdataGrid(map);
	}

	/**
	 * 安全文化列表显示页面
	 * @param model
	 */
	@RequestMapping(value="aqwh/index")
	public String aqwhindex(HttpServletRequest request,Model model) {
		return "bzhyx/aqwhindex";
	}

	/**
	 * 安全文化list页面
	 * @param request
	 */
	@RequestMapping(value="aqwh/list")
	@ResponseBody
	public Map<String, Object> aqwhData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("qyname"));
		
		map.put("starttime", request.getParameter("starttime"));
		map.put("finishtime", request.getParameter("finishtime"));

		map.putAll(getAuthorityMap());
		return bzhyxService.aqwhdataGrid(map);
	}

	/**
	 * 职业病列表显示页面
	 * @param model
	 */
	@RequestMapping(value="zyb/index")
	public String zybindex(HttpServletRequest request,Model model) {
		return "bzhyx/zybindex";
	}

	/**
	 * 职业病list页面
	 * @param request
	 */
	@RequestMapping(value="zyb/list")
	@ResponseBody
	public Map<String, Object> zybData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("qyname"));
		
		map.put("starttime", request.getParameter("starttime"));
		map.put("finishtime", request.getParameter("finishtime"));

		map.putAll(getAuthorityMap());
		return bzhyxService.zybdataGrid(map);
	}

	/**
	 * 相关方列表显示页面
	 * @param model
	 */
	@RequestMapping(value="xgf/index")
	public String xgfindex(HttpServletRequest request,Model model) {
		return "bzhyx/xgfindex";
	}

	/**
	 * 相关方list页面
	 * @param request
	 */
	@RequestMapping(value="xgf/list")
	@ResponseBody
	public Map<String, Object> xgfData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("qyname"));
		
		map.put("starttime", request.getParameter("starttime"));
		map.put("finishtime", request.getParameter("finishtime"));

		map.putAll(getAuthorityMap());
		return bzhyxService.xgfdataGrid(map);
	}
	
	/**
	 * 安全职责列表显示页面
	 * @param model
	 */
	@RequestMapping(value="aqzz/index")
	public String aqzzindex(HttpServletRequest request,Model model) {
		return "bzhyx/aqzzindex";
	}
	
	/**
	 * 安全职责list页面
	 * @param request
	 */
	@RequestMapping(value="aqzz/list")
	@ResponseBody
	public Map<String, Object> getaqzzData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("qyname"));
		
		map.put("starttime", request.getParameter("starttime"));
		map.put("finishtime", request.getParameter("finishtime"));
		
		map.putAll(getAuthorityMap());	
		return bzhyxService.aqzzdataGrid(map);
	}
	
	/**
	 * 安全生产投入列表显示页面
	 * @param model
	 */
	@RequestMapping(value="aqsctr/index")
	public String aqsctrindex(HttpServletRequest request,Model model) {
		return "bzhyx/aqsctrindex";
	}
	
	/**
	 * 安全生产投入list页面
	 * @param request
	 */
	@RequestMapping(value="aqsctr/list")
	@ResponseBody
	public Map<String, Object> getaqsctrData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("qyname"));
		
		map.put("starttime", request.getParameter("starttime"));
		map.put("finishtime", request.getParameter("finishtime"));
		
		map.putAll(getAuthorityMap());	
		return bzhyxService.aqsctrdataGrid(map);
	}
	
	/**
	 * 安全管理制度列表显示页面
	 * @param model
	 */
	@RequestMapping(value="aqglzd/index")
	public String aqglzdindex(HttpServletRequest request,Model model) {
		return "bzhyx/aqglzdindex";
	}
	
	/**
	 * 安全管理制度list页面
	 * @param request
	 */
	@RequestMapping(value="aqglzd/list")
	@ResponseBody
	public Map<String, Object> getaqglzdData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("qyname"));
		
		map.put("starttime", request.getParameter("starttime"));
		map.put("finishtime", request.getParameter("finishtime"));
		
		map.putAll(getAuthorityMap());	
		return bzhyxService.aqglzddataGrid(map);
	}
	
	/**
	 * 安全操作规程列表显示页面
	 * @param model
	 */
	@RequestMapping(value="aqczgc/index")
	public String aqczgcindex(HttpServletRequest request,Model model) {
		return "bzhyx/aqczgcindex";
	}
	
	/**
	 * 安全操作规程list页面
	 * @param request
	 */
	@RequestMapping(value="aqczgc/list")
	@ResponseBody
	public Map<String, Object> getaqczgcData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("qyname"));
		
		map.put("starttime", request.getParameter("starttime"));
		map.put("finishtime", request.getParameter("finishtime"));
		
		map.putAll(getAuthorityMap());	
		return bzhyxService.aqczgcdataGrid(map);
	}
	
	/**
	 * 劳保用品管理列表显示页面
	 * @param model
	 */
	@RequestMapping(value="lbypgl/index")
	public String lbypglindex(HttpServletRequest request,Model model) {
		return "bzhyx/lbypglindex";
	}
	
	/**
	 * 劳保用品管理list页面
	 * @param request
	 */
	@RequestMapping(value="lbypgl/list")
	@ResponseBody
	public Map<String, Object> getlbypglData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("qyname"));
		
		map.put("starttime", request.getParameter("starttime"));
		map.put("finishtime", request.getParameter("finishtime"));
		
		map.putAll(getAuthorityMap());	
		return bzhyxService.lbypgldataGrid(map);
	}
	
	/**
	 * 法律法规识别列表显示页面
	 * @param model
	 */
	@RequestMapping(value="flfgsb/index")
	public String flfgsbindex(HttpServletRequest request,Model model) {
		return "bzhyx/flfgsbindex";
	}
	
	/**
	 * 法律法规识别list页面
	 * @param request
	 */
	@RequestMapping(value="flfgsb/list")
	@ResponseBody
	public Map<String, Object> getflfgsbData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("qyname"));
		
		map.put("starttime", request.getParameter("starttime"));
		map.put("finishtime", request.getParameter("finishtime"));
		
		map.putAll(getAuthorityMap());	
		return bzhyxService.flfgsbdataGrid(map);
	}
	
	/**
	 * 变更管理列表显示页面
	 * @param model
	 */
	@RequestMapping(value="bggl/index")
	public String bgglindex(HttpServletRequest request,Model model) {
		return "bzhyx/bgglindex";
	}

	/**
	 * 变更管理list页面
	 * @param request
	 */
	@RequestMapping(value="bggl/list")
	@ResponseBody
	public Map<String, Object> getbgglData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("qyname"));
		
		map.put("starttime", request.getParameter("starttime"));
		map.put("finishtime", request.getParameter("finishtime"));
		
		map.putAll(getAuthorityMap());	
		return bzhyxService.bggldataGrid(map);
	}
}
