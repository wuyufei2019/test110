package com.cczu.model.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.service.ICommDictService;
import com.cczu.model.service.ITdicAcawxhxpService;
import com.cczu.model.service.ITdicGBTService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.StringUtils;

/**
 * 字典信息controller
 * @author jason
 * @date 2017年5月31日
 */
@Controller
@RequestMapping("tcode")
public class CommDictController extends BaseController {

	@Autowired
	private ICommDictService dictService;
	@Autowired
	private ITdicGBTService  dictgbtService;
	@Autowired
	private ITdicAcawxhxpService  acawxhxpService;

	
	/**
	 * 字典显示
	 * @param {json}
	 */
	@RequestMapping(value="/dict/{json}")
	@ResponseBody
	public String json(@PathVariable("json") String type,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(type)){ 
			map.put("type", type);
		}
		return dictService.dataList(map);
	
	}

	/**
	 * 字典显示2
	 * @param {json}
	 */
	@RequestMapping(value="/dict2/{json}")
	@ResponseBody
	public String json2(@PathVariable("json") String type,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(type)){
			map.put("type", type);
		}
		return dictService.dataList(map);

	}

	/**
	 * 字典显示
	 * @param {json2}
	 */
	@RequestMapping(value="/dictvalue/{json}")
	@ResponseBody
	public String jsonValue(@PathVariable("json") String value,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(value)){ 
			map.put("value", value);
		}
		return dictService.dataList(map);
	}
	
	/**
	 * GBT4754字典显示
	 * @param {json}
	 */
	@RequestMapping(value="/gbt/json" , method = RequestMethod.GET)
	@ResponseBody
	public String gbtjson() {
		return dictgbtService.gbtjson();
	}

	/**
	 * GBT4754 大类字典显示
	 * @param {json}
	 */
	@RequestMapping(value="/gbt/dljson" , method = RequestMethod.GET)
	@ResponseBody
	public String gbtdljson() {
		return dictgbtService.gbtdljson();
	}

	/**
	 * 安监分类字典显示
	 * @param {json}
	 */
	@RequestMapping(value="/ajfl/json" , method = RequestMethod.GET)
	@ResponseBody
	public String ajfljson() {
		return dictgbtService.ajfljson();
	}
	
	/**
	 * 事故后果计算字典显示
	 * @param {json}
	 */
	@RequestMapping(value="/acawxhxp/json")
	@ResponseBody
	public String acawxhxpJson(String name) {
		return acawxhxpService.findByName(name);
	}
	
	/**
	 * 事故后果计算字典显示（根据id查询）
	 * @param {json}
	 */
	@RequestMapping(value="/acawxhxp/jsontid")
	@ResponseBody
	public String acawxhxpJson(String tid, HttpServletRequest request, HttpServletResponse response) {
		Long id=(long) 0;
		if(StringUtils.isNotEmpty(tid)) { id=Long.parseLong(tid); }
		return acawxhxpService.acawxhxpJsonByTid(id);
	}
	
	/**
	 * 字典显示
	 * @param {json}
	 */
	@RequestMapping(value="/wlwxxlbs/{json}")
	@ResponseBody
	public String wlwxxlbjson(@PathVariable("json") String type,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(type)){ 
			map.put("type", type);
		}
		return dictService.datawlList(map);
	
	}
}
