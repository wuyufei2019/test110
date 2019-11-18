package com.cczu.model.controller;

import java.sql.Timestamp;
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
import com.cczu.model.entity.XZCF_YbcfAjcpEntity;
import com.cczu.model.entity.XZCF_YbcfJaspEntity;
import com.cczu.model.entity.XZCF_YbcfLaspEntity;
import com.cczu.model.service.AqzfSetBasicInfoService;
import com.cczu.model.service.AqzfSetNumberService;
import com.cczu.model.service.IXzcfCfjdService;
import com.cczu.model.service.IXzcfCommonAjcpService;
import com.cczu.model.service.IXzcfCommonLaspService;
import com.cczu.model.service.XzcfCommonJaspService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

/**
 * 行政结案审批-一般结案审批-结案审批controller
 * 
 * @author jason
 * 
 */
@Controller
@RequestMapping("xzcf/ybcf/jasp")
public class PageXzcfCommonJaspController extends BaseController {

	@Autowired
	private XzcfCommonJaspService XzcfCommonJaspService;
	@Autowired
	private IXzcfCfjdService punishsimplecfjdservice;
	@Autowired
	private IXzcfCommonAjcpService xzcfcommonajcpservice;
	@Autowired
	private IXzcfCommonLaspService xzcfcommonlaspservice;
	@Autowired
	private AqzfSetNumberService setNumberService;
	@Autowired
	private AqzfSetBasicInfoService setbasicservice;

	/**
	 * 默认页面
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		return "aqzf/xzcf/ybcf/jasp/index";
	}

	/**
	 * list页面
	 */
	@RequiresPermissions("xzcf:jasp:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("name", request.getParameter("xzcf_qzzx_name"));
		map.put("number", request.getParameter("xzcf_qzzx_number"));
//		map.put("startdate", request.getParameter("jycf_ajcp_startdate"));
//		map.put("enddate", request.getParameter("jycf_ajcp_enddate"));
		map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
		return XzcfCommonJaspService.dataGrid(map);
	}

	/**
	 * 首页提醒
	 * 
	 * @param request
	 */
	// @RequestMapping(value = "html")
	// @ResponseBody
	// public Map<String, Object> getHtml(HttpServletRequest request, Model
	// model) {
	// Map<String, Object> map = getPageMap(request);
	// Map<String, Object> remap = new HashMap<>();//返回值
	// ShiroUser user= UserUtil.getCurrentShiroUser();
	// map.put("year", DateUtils.getYear());
	// map.put("month", Integer.parseInt(DateUtils.getMonth()));
	// if(user.getUsertype().equals("1")){//0是企业，1为安监局
	// map.put("id", user.getQyid());
	// }
	// remap.put("rows",aqjgcheckplanservice.dataGrid(map).get("rows") );
	// remap.put("usertype",UserUtil.getCurrentShiroUser().getUsertype());
	// return remap;
	// }

	/**
	 * 添加结案审批信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("xzcf:jasp:add")
	@RequestMapping(value = "create/{id}")
	public String create(@PathVariable("id") long id, Model model) {
		XZCF_YbcfAjcpEntity yae = xzcfcommonajcpservice.findInfoByLaId(id);
		XZCF_YbcfJaspEntity yje= new XZCF_YbcfJaspEntity();
		yje.setCasename(yae.getCasename());
		yje.setPunishname(yae.getPunishname());
		if("1".equals(yae.getPercomflag())){
			yje.setQyaddress(yae.getQyaddress());
			yje.setLegal(yae.getLegal());
			yje.setDuty(yae.getDuty());
			yje.setQyyb(yae.getQyyb());
			yje.setPercomflag("1");
		}else{
			yje.setAge(yae.getAge());
			yje.setSex(yae.getSex());
			yje.setMobile(yae.getMobile());
			yje.setDwname(yae.getDwname());
			yje.setDwaddress(yae.getDwaddress());
			yje.setAddress(yae.getAddress());
			yje.setJtyb(yae.getJtyb());
			yje.setPercomflag("0");
		}
		model.addAttribute("id1", id);
		model.addAttribute("yje", yje);
		model.addAttribute("action", "createSub");
		return "aqzf/xzcf/ybcf/jasp/form";
	}

	/**
	 * 添加出结案审批
	 * 
	 * @param request
	 */
	@RequiresPermissions("xzcf:jasp:add")
	@RequestMapping(value = "createSub", method = RequestMethod.POST)
	@ResponseBody
	public String createSub(XZCF_YbcfJaspEntity yje,
			HttpServletRequest request, Model model) {
		String datasuccess = "error";
		Timestamp t = DateUtils.getSysTimestamp();
		yje.setS1(t);
		yje.setS2(t);
		yje.setS3(0);
		yje.setS3(0);
		
		//设置编号
		AQZF_SetBasicInfoEntity bh =setbasicservice.findInfor();
		XZCF_YbcfLaspEntity ybcf = xzcfcommonlaspservice.findInfoById(yje.getId1());
		String m0 = ybcf.getNumber();
		yje.setNumber("（"+bh.getSsqjc()+"）安监结〔" + DateUtils.getYear() + "〕"+m0.substring(m0.indexOf("〕")+1, m0.length()));
		
		if (XzcfCommonJaspService.addInfoReturnID(yje) > 0) {
			try {
				XZCF_YbcfLaspEntity yle = xzcfcommonlaspservice.findInfoById(yje.getId1());
				yle.setJaflag("1");
				xzcfcommonlaspservice.updateInfo(yle);
				datasuccess = "success";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return datasuccess;
	}

	/**
	 * 修改结案审批信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("xzcf:jasp:update")
	@RequestMapping(value = "update/{id}")
	public String update(@PathVariable("id") long id, Model model) {

		XZCF_YbcfJaspEntity yje = XzcfCommonJaspService.findInfoById(id);
		model.addAttribute("yje", yje);
		model.addAttribute("flag", yje.getPercomflag());
		model.addAttribute("action", "updateSub");
		return "aqzf/xzcf/ybcf/jasp/form";
	}

	/**
	 * 修改结案审批信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("xzcf:jasp:update")
	@RequestMapping(value = "updateSub")
	@ResponseBody
	public String updateSub(XZCF_YbcfJaspEntity yje,
			HttpServletRequest request, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		String qyname = request.getParameter("punishname");
		yje.setPunishname(qyname);
		yje.setS2(t);
		try {
			XzcfCommonJaspService.updateInfo(yje);
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess = "error";
		}
		return datasuccess;
	}

	/**
	 * 查看结案审批信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("xzcf:jasp:view")
	@RequestMapping(value = "view/{id}")
	public String view(@PathVariable("id") long id, Model model) {
		XZCF_YbcfJaspEntity yje = XzcfCommonJaspService.findInfoById(id);
		model.addAttribute("yje", yje);
		return "aqzf/xzcf/ybcf/jasp/view";
	}

	/**
	 * 删除结案审批信息
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("xzcf:jasp:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		try {
			for (int i = 0; i < arrids.length; i++) {
				XzcfCommonJaspService.deleteInfo(Long.parseLong(arrids[i]));
				XzcfCommonJaspService.updateLaspInfo(Long.parseLong(arrids[i]));
			}

		} catch (Exception e) {
			// TODO: handle exception
			datasuccess = "删除失败";
		}
		return datasuccess;
	}

	/**
	 * 导出案件呈批记录word
	 * 
	 */
	@RequiresPermissions("xzcf:jasp:export")
	@RequestMapping(value = "exportjasp/{flag}/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("flag") String flag,
			@PathVariable("id") Long id, HttpServletRequest request,
			HttpServletResponse response) {
		// 设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_"+ new Random().nextInt(100) + ".doc";
		// 设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/")+ "download/";
		XZCF_YbcfJaspEntity yje;
		if ("la".equals(flag))
			yje = XzcfCommonJaspService.findInfoByLaId(id);
		else
			yje = XzcfCommonJaspService.findInfoById(id);
		// XZCF_JYCFInfoEntity
		// jie=punishsimplegzservice.findInfoById(yje.getId1());
		Map<String, Object> map = new HashMap<String, Object>();
		//企业个人公有
		if("1".equals(yje.getPercomflag())){
			map.put("punishname", "");
			map.put("punishdwname", yje.getPunishname());
		}else{
			map.put("punishname",yje.getPunishname());
			map.put("punishdwname","");
			}
		map.put("number", yje.getNumber());
		map.put("casename", yje.getCasename());
		map.put("qyaddress", yje.getQyaddress()==null?"":yje.getQyaddress());
		map.put("qyyb", yje.getQyyb()==null?"":yje.getQyyb());
		map.put("jtyb", yje.getJtyb()==null?"":yje.getJtyb());
		map.put("legal", yje.getLegal()==null?"":yje.getLegal());
		map.put("sex", yje.getSex()==null?"":yje.getSex());
		map.put("age", yje.getAge() == null ? "" : yje.getAge());
		map.put("dwname", yje.getDwname()==null?"":yje.getDwname());
		map.put("dwaddress", yje.getDwaddress()==null?"":yje.getDwaddress());
		map.put("duty", yje.getDuty() == null ? "" : yje.getDuty());
		map.put("addresss", yje.getAddress() == null ? "" : yje.getAddress());
		map.put("result", yje.getResult()==null?"":yje.getResult());
		map.put("mobile", yje.getMobile() == null ? "" : yje.getMobile());
		map.put("exeucondition", yje.getExeucondition());
		WordUtil.ireportWord(map, "xzcfjasp.ftl", filePath, filename, request);

		return "/download/" + filename;
	}

}
