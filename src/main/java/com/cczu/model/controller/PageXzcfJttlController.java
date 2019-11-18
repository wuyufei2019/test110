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
import com.cczu.model.entity.XZCF_JttlEntity;
import com.cczu.model.entity.XZCF_YbcfLaspEntity;
import com.cczu.model.service.AqzfSetBasicInfoService;
import com.cczu.model.service.IXzcfCommonLaspService;
import com.cczu.model.service.XzcfJttlService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

/**
 * 集体讨论
 * @author zpc
 * @date 2017/08/02
 */
@Controller
@RequestMapping("xzcf/ybcf/jttl")
public class PageXzcfJttlController extends BaseController{

	@Autowired
	private XzcfJttlService xzcfJttlService;
	@Autowired
	private IXzcfCommonLaspService xzcfcommonlaspservice;
	@Autowired
	private AqzfSetBasicInfoService aqzfsetbasicinfoservice;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "aqzf/xzcf/ybcf/jttl/index";
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
		map.put("m1", request.getParameter("aqzf_jttl_M1"));
		map.put("m2", request.getParameter("aqzf_jttl_M2"));
		return xzcfJttlService.dataGrid(map);
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
				xzcfJttlService.deleteInfo(Long.parseLong(aids[i]));
				xzcfJttlService.updateLaspInfo(Long.parseLong(aids[i]));
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
		XZCF_JttlEntity jttl = xzcfJttlService.findById(id);
		model.addAttribute("jttl", jttl);
		return "aqzf/xzcf/ybcf/jttl/view";
	}
	
	/**
	 * 添加集体讨论跳转
	 * id 立案id
	 * @param model
	 */
	@RequestMapping(value = "create/{id}" , method = RequestMethod.GET)
	public String create(@PathVariable("id") Long id,Model model) {
		XZCF_JttlEntity jttl = new XZCF_JttlEntity();
		XZCF_YbcfLaspEntity ybcf = xzcfcommonlaspservice.findInfoById(id);
		jttl.setM2(ybcf.getCasename());
		jttl.setID1(id);
		model.addAttribute("jttl", jttl);
		model.addAttribute("action", "create");
		return "aqzf/xzcf/ybcf/jttl/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "create" )
	@ResponseBody
	public String create(XZCF_JttlEntity jttl, HttpServletRequest request) {
		String datasuccess="success";
		//设置编号
		AQZF_SetBasicInfoEntity bh =aqzfsetbasicinfoservice.findInfor();
		XZCF_YbcfLaspEntity ybcf = xzcfcommonlaspservice.findInfoById(jttl.getID1());
		String m1 = ybcf.getNumber();
		jttl.setM1("（"+bh.getSsqjc()+"）安监罚集〔"+DateUtils.getYear()+"〕"+m1.substring(m1.indexOf("〕")+1, m1.length()));
		//添加集体讨论并修改立案标记
		xzcfJttlService.addInfo(jttl);
		ybcf.setTlflag("1");
		xzcfcommonlaspservice.updateInfo(ybcf);
		return datasuccess;
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		XZCF_JttlEntity jttl = xzcfJttlService.findById(id);
		model.addAttribute("jttl", jttl);
		model.addAttribute("action", "update");
		return "aqzf/xzcf/ybcf/jttl/form";
	}
	
	/**
	 * 修改
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(XZCF_JttlEntity jttl){
		String datasuccess="success";
		xzcfJttlService.updateInfo(jttl);
		return datasuccess;
	}

	/**
	 * 导出集体讨论word
	 */
	@RequestMapping(value = "export/{flag}/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("flag") String flag,@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		XZCF_JttlEntity jttl = new XZCF_JttlEntity();
		if("la".equals(flag)){
			jttl= xzcfJttlService.findWordByLaId(id);
		}else{
			jttl= xzcfJttlService.findById(id);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("m1", StringUtils.isEmpty(jttl.getM1())?"":jttl.getM1());
		map.put("m2", StringUtils.isEmpty(jttl.getM2())?"":jttl.getM2());
		map.put("m5", StringUtils.isEmpty(jttl.getM5())?"":jttl.getM5());
		map.put("m6", StringUtils.isEmpty(jttl.getM6())?"           ":jttl.getM6());
		map.put("m7", StringUtils.isEmpty(jttl.getM7())?"           ":jttl.getM7());
		map.put("m8", StringUtils.isEmpty(jttl.getM8())?"           ":jttl.getM8());
		map.put("m9", StringUtils.isEmpty(jttl.getM5())?"":jttl.getM9());
		map.put("m10", StringUtils.isEmpty(jttl.getM5())?"":jttl.getM10());
		map.put("m11", StringUtils.isEmpty(jttl.getM5())?"":jttl.getM11());
		map.put("m12", StringUtils.isEmpty(jttl.getM5())?"":jttl.getM12());
		//解析时间
		if(jttl.getM3()!=null){
			map.put("year1", (jttl.getM3().getYear()+1900)+"");
			map.put("month1", jttl.getM3().getMonth() + 1);
			map.put("day1", jttl.getM3().getDay());
			map.put("hour1", jttl.getM3().getHours());
			map.put("min1", jttl.getM3().getMinutes());
		}else{
			map.put("year1", "   ");
			map.put("month1", "  ");
			map.put("day1", "  ");
			map.put("hour1", "  ");
			map.put("min1", "  ");
		}
		if(jttl.getM4()!=null){
			map.put("year2", (jttl.getM4().getYear()+1900)+"");
			map.put("month2", jttl.getM4().getMonth() + 1);
			map.put("day2", jttl.getM4().getDay());
			map.put("hour2", jttl.getM4().getHours());
			map.put("min2", jttl.getM4().getMinutes());
		}else{
			map.put("year2", "   ");
			map.put("month2", "  ");
			map.put("day2", "  ");
			map.put("hour2", "  ");
			map.put("min2", "  ");
		}

		
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "xzjttl.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
}
