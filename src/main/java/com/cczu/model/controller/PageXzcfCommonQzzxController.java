package com.cczu.model.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.model.entity.XZCF_CfjdInfoEntity;
import com.cczu.model.entity.XZCF_YbcfLaspEntity;
import com.cczu.model.entity.XZCF_YbcfQzzxEntity;
import com.cczu.model.entity.XZCF_YbcfSxcgEntity;
import com.cczu.model.service.AqzfSetBasicInfoService;
import com.cczu.model.service.AqzfSetNumberService;
import com.cczu.model.service.IXzcfCfjdService;
import com.cczu.model.service.IXzcfCommonLaspService;
import com.cczu.model.service.XzcfCommonQzzxService;
import com.cczu.model.service.XzcfCommonSxcgService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

/**
 * 行政处罚--强制执行controller
 * 
 * @author jason
 * 
 */
@Controller
@RequestMapping("xzcf/ybcf/qzzx")
public class PageXzcfCommonQzzxController extends BaseController {

	@Autowired
	private XzcfCommonQzzxService  xzcfcommonqzzxservice;
	@Autowired
	private IXzcfCommonLaspService xzcfcommonlaspservice;
	@Autowired
	private IXzcfCfjdService xzcfcfjdservice;
	@Autowired
	private AqzfSetNumberService setNumberService;
	@Autowired
	private AqzfSetBasicInfoService aqzfsetbasicinfoservice;
	@Autowired
	private XzcfCommonSxcgService xzcfcommonsxcgservice;
	/**
	 * 默认页面
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		return "aqzf/xzcf/ybcf/qzzx/index";
	}

	/**
	 * list页面
	 */
	@RequiresPermissions("xzcf:qzzx:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("number", request.getParameter("xzcf_qzzx_number"));
		map.put("name", request.getParameter("xzcf_qzzx_name"));
		map.put("xzqy",UserUtil.getCurrentShiroUser().getXzqy());
		return xzcfcommonqzzxservice.dataGrid(map);
	}
	

	/**
	 * 添加强制执行信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("xzcf:qzzx:add")
	@RequestMapping(value = "create/{id}")
	public String create(@PathVariable("id") long id,Model model) {
		XZCF_YbcfSxcgEntity yse=xzcfcommonsxcgservice.findInfoById(id);
		XZCF_CfjdInfoEntity cfjd= xzcfcfjdservice.findInfoByLaId(yse.getId1());
		AQZF_SetBasicInfoEntity sbi=aqzfsetbasicinfoservice.findInfor();
		XZCF_YbcfQzzxEntity yqe =new XZCF_YbcfQzzxEntity();
		yqe.setDsname(cfjd.getPunishname());
		yqe.setCourt(sbi.getCourt());
		model.addAttribute("id1", yse.getId1());
		model.addAttribute("yqe", yqe);
		model.addAttribute("action", "createSub");
		return "aqzf/xzcf/ybcf/qzzx/form";
	}

	/**
	 * 添加强制执行
	 * @param request
	 */
	@RequiresPermissions("xzcf:qzzx:add")
	@RequestMapping(value = "createSub", method = RequestMethod.POST)
	@ResponseBody
	public String createSub(XZCF_YbcfQzzxEntity yqe,
			HttpServletRequest request, Model model) throws IOException {
		String datasuccess = "failed";
		Timestamp t = DateUtils.getSysTimestamp();
		yqe.setS1(t);
		yqe.setS2(t);
		yqe.setS3(0);
		
		//设置编号
		AQZF_SetBasicInfoEntity bh =aqzfsetbasicinfoservice.findInfor();
		XZCF_YbcfLaspEntity ybcf = xzcfcommonlaspservice.findInfoById(yqe.getId1());
		String m0 = ybcf.getNumber();
		yqe.setNumber("（"+bh.getSsqjc()+"）安监强执〔"+DateUtils.getYear()+"〕"+m0.substring(m0.indexOf("〕")+1, m0.length()));
		
		if (xzcfcommonqzzxservice.addInfoReturnID(yqe) > 0) {
			XZCF_YbcfLaspEntity yle= xzcfcommonlaspservice.findInfoById(yqe.getId1());
			yle.setQzflag("1");
			xzcfcommonlaspservice.updateInfo(yle);
			datasuccess = "success";
		}
		return datasuccess;
	}

	/**
	 * 修改强制执行信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("xzcf:qzzx:update")
	@RequestMapping(value = "update/{id}")
	public String update(@PathVariable("id") long id, Model model) {

		XZCF_YbcfQzzxEntity yqe = xzcfcommonqzzxservice.findInfoById(id);
		model.addAttribute("yqe", yqe);
		//model.addAttribute("jid", id);
		model.addAttribute("action", "updateSub");
		return "aqzf/xzcf/ybcf/qzzx/form";
	}

	/**
	 * 修改强制执行信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("xzcf:qzzx:update")
	@RequestMapping(value = "updateSub")
	@ResponseBody
	public String updateSub(XZCF_YbcfQzzxEntity yqe,
			HttpServletRequest request, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		yqe.setS2(t);
		try {
			xzcfcommonqzzxservice.updateInfo(yqe);
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="failed";
		}
		return datasuccess;
	}

	/**
	 * 查看强制执行信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("xzcf:qzzx:view")
	@RequestMapping(value = "view/{id}")
	public String view(@PathVariable("id") long id, Model model) {
		XZCF_YbcfQzzxEntity yqe = xzcfcommonqzzxservice.findInfoById(id);
		model.addAttribute("yqe", yqe);
		return "aqzf/xzcf/ybcf/qzzx/view";
	}

	/**
	 * 删除强制执行信息
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("xzcf:qzzx:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		try {
			for (int i = 0; i < arrids.length; i++) {
				xzcfcommonqzzxservice.deleteInfo(Long.parseLong(arrids[i]));
				xzcfcommonqzzxservice.updateLaspInfo(Long.parseLong(arrids[i]));
			}
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="删除失败";
		}
		
		return datasuccess;
	}
	
	/**
	 * 导出强制执行记录word
	 * 
	 */
	@RequiresPermissions("xzcf:qzzx:export")
	@RequestMapping(value = "exportqzzx/{flag}/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("flag") String  flag,@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		XZCF_YbcfQzzxEntity yqe;
		AQZF_SetBasicInfoEntity sbe=aqzfsetbasicinfoservice.findInfor();
		
		if("la".equals(flag)){
			yqe=xzcfcommonqzzxservice.findInfoByLaId(id);
		}
		else
			yqe= xzcfcommonqzzxservice.findInfoById(id);
		XZCF_CfjdInfoEntity cfjd=xzcfcfjdservice.findInfoByLaId(yqe.getId1());
	    Calendar cal = Calendar.getInstance();
		cal.setTime(cfjd.getPunishdate());
		Map<String, Object> map=new HashMap<String, Object>();
		 map.put("number", yqe.getNumber());
		 map.put("dsname", yqe.getDsname());
		 map.put("year",String.valueOf(cal.get(cal.YEAR)));
		 map.put("month",cal.get(cal.MONTH));
		 map.put("day",cal.get(cal.DAY_OF_MONTH));
		 map.put("clname", yqe.getClname());
		 map.put("court", sbe.getCourt());
		 map.put("result", cfjd.getPunishresult());
		 map.put("cfnumber", cfjd.getNumber());
		 map.put("contact", sbe.getContact());
		 map.put("phone", sbe.getPhone());
		 map.put("ssqmc", sbe.getSsqmc());

		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "xzcfqzzx.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
}
