package com.cczu.model.controller;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.model.entity.XZCF_CssbblEntity;
import com.cczu.model.entity.XZCF_JttlEntity;
import com.cczu.model.entity.XZCF_YbcfLaspEntity;
import com.cczu.model.service.AqzfSetBasicInfoService;
import com.cczu.model.service.IXzcfCommonLaspService;
import com.cczu.model.service.XzcfCssbblService;
import com.cczu.model.service.XzcfJttlService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

/**
 * 陈述申辩笔录
 * @author zpc
 * @date 2017/08/02
 */
@Controller
@RequestMapping("xzcf/ybcf/cssbbl")
public class PageXzcfCssbblController extends BaseController{

	@Autowired
	private XzcfCssbblService xzcfCssbblService;
	@Autowired
	private IXzcfCommonLaspService xzcfcommonlaspservice;
	@Autowired
	private AqzfSetBasicInfoService aqzfsetbasicinfoservice;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "aqzf/xzcf/ybcf/cssbbl/index";
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
		map.put("m11", request.getParameter("aqzf_cssbbl_M11"));
		return xzcfCssbblService.dataGrid(map);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		try {
			for(int i=0;i<aids.length;i++){
				xzcfCssbblService.deleteInfo(Long.parseLong(aids[i]));
				xzcfCssbblService.updateLaspInfo(Long.parseLong(aids[i]));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			datasuccess="删除失败";
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		XZCF_CssbblEntity cssbbl = xzcfCssbblService.findById(id);
		model.addAttribute("cssbbl", cssbbl);
		return "aqzf/xzcf/ybcf/cssbbl/view";
	}
	
	/**
	 * 添加跳转
	 * id 立案id
	 * @param model
	 */
	@RequestMapping(value = "create/{id}" , method = RequestMethod.GET)
	public String create(@PathVariable("id") Long id,Model model) {
		XZCF_CssbblEntity cssbbl = new XZCF_CssbblEntity();
		XZCF_YbcfLaspEntity ybcf = xzcfcommonlaspservice.findInfoById(id);
		cssbbl.setM11(ybcf.getCasename());
		cssbbl.setID1(id);
		cssbbl.setM7(ybcf.getDsperson());
		cssbbl.setM8(ybcf.getContact());
		cssbbl.setM9(ybcf.getDsaddress());
		cssbbl.setM10(ybcf.getYbcode());
		model.addAttribute("cssbbl", cssbbl);
		model.addAttribute("action", "create");
		return "aqzf/xzcf/ybcf/cssbbl/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "create" )
	@ResponseBody
	public String create(XZCF_CssbblEntity cssbbl, HttpServletRequest request) {
		String datasuccess="success";
		XZCF_YbcfLaspEntity ybcf = xzcfcommonlaspservice.findInfoById(cssbbl.getID1());
		//添加集体讨论并修改立案标记
		xzcfCssbblService.addInfo(cssbbl);
		ybcf.setSbflag("1");
		xzcfcommonlaspservice.updateInfo(ybcf);
		return datasuccess;
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		XZCF_CssbblEntity cssbbl = xzcfCssbblService.findById(id);
		model.addAttribute("cssbbl", cssbbl);
		model.addAttribute("action", "update");
		return "aqzf/xzcf/ybcf/cssbbl/form";
	}
	
	/**
	 * 修改
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(XZCF_CssbblEntity cssbbl){
		String datasuccess="success";
		xzcfCssbblService.updateInfo(cssbbl);
		return datasuccess;
	}

	/**
	 * 导出word
	 */
	@RequestMapping(value = "export/{flag}/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("flag") String flag,@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		XZCF_CssbblEntity jttl = new XZCF_CssbblEntity();
		if("la".equals(flag)){
			jttl= xzcfCssbblService.findWordByLaId(id);
		}else{
			jttl= xzcfCssbblService.findById(id);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("m3", StringUtils.isEmpty(jttl.getM3())?"":jttl.getM3());
		map.put("m4", StringUtils.isEmpty(jttl.getM4())?"         ":jttl.getM4());
		map.put("m5", StringUtils.isEmpty(jttl.getM5())?"  ":jttl.getM5());
		map.put("m6", StringUtils.isEmpty(jttl.getM6())?"      ":jttl.getM6());
		map.put("m7", StringUtils.isEmpty(jttl.getM7())?"                             ":jttl.getM7());
		map.put("m8", StringUtils.isEmpty(jttl.getM8())?"":jttl.getM8());
		map.put("m9", StringUtils.isEmpty(jttl.getM9())?"                             ":jttl.getM9());
		map.put("m10", StringUtils.isEmpty(jttl.getM10())?"":jttl.getM10());
		map.put("m11", StringUtils.isEmpty(jttl.getM11())?"                   ":jttl.getM11());
		map.put("m12", StringUtils.isEmpty(jttl.getM12())?"         ":jttl.getM12());
		map.put("m13", StringUtils.isEmpty(jttl.getM13())?"         ":jttl.getM13());
		map.put("m14", StringUtils.isEmpty(jttl.getM14())?"         ":jttl.getM14());
		map.put("m15", StringUtils.isEmpty(jttl.getM15())?"         ":jttl.getM15());
		map.put("m16", StringUtils.isEmpty(jttl.getM16())?"":jttl.getM16());
		map.put("m17", StringUtils.isEmpty(jttl.getM17())?"         ":jttl.getM17());
		//解析时间
		if(jttl.getM1()!=null){
			map.put("year", (jttl.getM1().getYear()+1900)+"");
			map.put("month1", jttl.getM1().getMonth() + 1);
			map.put("day1", jttl.getM1().getDay());
			map.put("hour1", jttl.getM1().getHours());
			map.put("min1", jttl.getM1().getMinutes());
		}else{
			map.put("year", "   ");
			map.put("month1", "  ");
			map.put("day1", "  ");
			map.put("hour1", "  ");
			map.put("min1", "  ");
		}
		if(jttl.getM2()!=null){
			map.put("month2", jttl.getM2().getMonth() + 1);
			map.put("day2", jttl.getM2().getDay());
			map.put("hour2", jttl.getM2().getHours());
			map.put("min2", jttl.getM2().getMinutes());
		}else{
			map.put("month2", "  ");
			map.put("day2", "  ");
			map.put("hour2", "  ");
			map.put("min2", "  ");
		}

		 AQZF_SetBasicInfoEntity bh =aqzfsetbasicinfoservice.findInfor();
		 map.put("ssqmc", bh.getSsqmc());
		
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "xzcssbbl.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
}
