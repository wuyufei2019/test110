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
import com.cczu.model.entity.XZCF_GzsInfoEntity;
import com.cczu.model.entity.XZCF_YbcfAjcpEntity;
import com.cczu.model.entity.XZCF_YbcfLaspEntity;
import com.cczu.model.service.AqzfSetBasicInfoService;
import com.cczu.model.service.AqzfSetNumberService;
import com.cczu.model.service.AqzfWfxwService;
import com.cczu.model.service.IXzcfCfjdService;
import com.cczu.model.service.IPunishSimpleGzService;
import com.cczu.model.service.IXzcfCommonAjcpService;
import com.cczu.model.service.IXzcfCommonLaspService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

/**
 * 行政案件呈批-一般案件呈批-案件呈批controller
 * 
 * @author jason
 * 
 */
@Controller
@RequestMapping("xzcf/ybcf/ajclcp")
public class PageXzcfCommonAjcpController extends BaseController {

	@Autowired
	private IXzcfCommonAjcpService xzcfcommonajcpservice;
	@Autowired
	private IXzcfCfjdService punishsimplecfjdservice;
	@Autowired
	private AqzfWfxwService aqzfWfxwService;
	@Autowired
	private IXzcfCommonLaspService xzcfcommonlaspservice;
	@Autowired
	private AqzfSetNumberService setNumberService;
	@Autowired
	private IPunishSimpleGzService punishsimplegzservice;
	@Autowired
	private AqzfSetBasicInfoService setbasicservice;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		return "aqzf/xzcf/ybcf/ajcp/index";
	}

	/**
	 * list页面
	 */
	@RequiresPermissions("ybcf:ajcp:view")
	@RequestMapping(value = "list1")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("name", request.getParameter("ybcf_ajcp_name"));
		map.put("number", request.getParameter("ybcf_ajcp_number"));
		map.put("startdate", request.getParameter("jycf_ajcp_startdate"));
		map.put("enddate", request.getParameter("jycf_ajcp_enddate"));
		map.put("xzqy",UserUtil.getCurrentShiroUser().getXzqy());
		return xzcfcommonajcpservice.dataGrid(map);
	}
	

	/**
	 * 添加案件呈批信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("ybcf:ajcp:add")
	@RequestMapping(value = "create/{id}")
	public String create(@PathVariable("id") long id,Model model) {
		XZCF_YbcfLaspEntity yle=xzcfcommonlaspservice.findInfoById(id);
		XZCF_GzsInfoEntity gie=punishsimplegzservice.findInfoByLaId(id);
		model.addAttribute("id1", id);
		String opinion = "依据"+gie.getEnlaw()+"，建议进行如下处罚。";
		yle.setCasecondition("违法行为："+gie.getIllegalact()+"    处罚依据："+gie.getEnlaw());//违法事实和依据
		yle.setOpinion(opinion);//承办人意见
		model.addAttribute("yle", yle);
		model.addAttribute("action", "createSub");
		return "aqzf/xzcf/ybcf/ajcp/form";
	}

	/**
	 * 添加出案件呈批
	 * @param request
	 */
	@RequiresPermissions("ybcf:ajcp:add")
	@RequestMapping(value = "createSub", method = RequestMethod.POST)
	@ResponseBody
	public String createSub(XZCF_YbcfAjcpEntity jce,
			HttpServletRequest request, Model model){
		String datasuccess = "error";
		Timestamp t = DateUtils.getSysTimestamp();
		jce.setS1(t);
		jce.setS2(t);
		jce.setS3(0);
		jce.setPunishname(request.getParameter("punishname"));
		
		//设置编号
		AQZF_SetBasicInfoEntity bh =setbasicservice.findInfor();
		XZCF_YbcfLaspEntity ybcf = xzcfcommonlaspservice.findInfoById(jce.getId1());
		String m0 = ybcf.getNumber();
		jce.setNumber("（"+bh.getSsqjc()+"）安监处呈〔"+DateUtils.getYear()+"〕"+m0.substring(m0.indexOf("〕")+1, m0.length()));
			
		if (xzcfcommonajcpservice.addInfoReturnID(jce) > 0) {
			try {
				XZCF_YbcfLaspEntity yle= xzcfcommonlaspservice.findInfoById(jce.getId1());
				yle.setCbflag("1");
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
	 * 修改案件呈批信息页面跳转
	 * @param model
	 */
	@RequiresPermissions("ybcf:ajcp:update")
	@RequestMapping(value = "update/{id}")
	public String update(@PathVariable("id") long id, Model model) {
		XZCF_YbcfAjcpEntity jce = xzcfcommonajcpservice.findInfoById(id);
		model.addAttribute("jce", jce);
		model.addAttribute("flag", jce.getPercomflag());
		model.addAttribute("action", "updateSub");
		return "aqzf/xzcf/ybcf/ajcp/form";
	}

	/**
	 * 修改案件呈批信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("ybcf:ajcp:update")
	@RequestMapping(value = "updateSub")
	@ResponseBody
	public String updateSub(XZCF_YbcfAjcpEntity jce,
			HttpServletRequest request, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		String qyname=request.getParameter("punishname");
		jce.setPunishname(qyname);
		jce.setS2(t);
		try {
			xzcfcommonajcpservice.updateInfo(jce);
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="error";
		}
		return datasuccess;
	}

	/**
	 * 查看案件呈批信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("ybcf:ajcp:view")
	@RequestMapping(value = "view/{id}")
	public String view(@PathVariable("id") long id, Model model) {
		XZCF_YbcfAjcpEntity jce = xzcfcommonajcpservice.findInfoById(id);
		model.addAttribute("jce", jce);
		return "aqzf/xzcf/ybcf/ajcp/view";
	}

	/**
	 * 删除案件呈批信息
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("ybcf:ajcp:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		try {
			for (int i = 0; i < arrids.length; i++) {
				xzcfcommonajcpservice.deleteInfo(Long.parseLong(arrids[i]));
				xzcfcommonajcpservice.updateLaspInfo(Long.parseLong(arrids[i]));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="删除失败";
		}
		return datasuccess;
	}
	
	/**
	 * 导出案件呈批记录word
	 * 
	 */
	@RequiresPermissions("ybcf:ajcp:export")
	@RequestMapping(value = "exportajcp/{flag}/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("flag") String flag,@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		XZCF_YbcfAjcpEntity jce ;
		if("la".equals(flag))
		jce=xzcfcommonajcpservice.findInfoByLaId(id);
		else
		jce= xzcfcommonajcpservice.findInfoById(id);
		//XZCF_JYCFInfoEntity jie=punishsimplegzservice.findInfoById(jce.getId1());
		Map<String, Object> map=new HashMap<String, Object>();
		//企业个人公有
		if("1".equals(jce.getPercomflag())){
			map.put("punishname", "");
			map.put("punishdwname", jce.getPunishname());
		}else{
			map.put("punishname",jce.getPunishname());
			map.put("punishdwname","");
		}
		map.put("number",jce.getNumber());
		map.put("casename",jce.getCasename());
		map.put("qyaddress", jce.getQyaddress()==null?"":jce.getQyaddress());
		map.put("qyyb", jce.getQyyb()==null?"":jce.getQyyb());
		map.put("jtyb", jce.getJtyb()==null?"":jce.getJtyb());
		map.put("legal", jce.getLegal()==null?"":jce.getLegal());
		map.put("sex",jce.getSex()==null?"":jce.getSex());
		map.put("age", jce.getAge()==null?"":jce.getAge());
		map.put("dwname", jce.getDwname()==null?"":jce.getDwname());
		map.put("dwaddress", jce.getDwaddress()==null?"":jce.getDwaddress());
		map.put("address", jce.getAddress()==null?"":jce.getAddress());
		map.put("casecondition", jce.getIllegalactandevidence()==null?"":jce.getIllegalactandevidence());
		map.put("mobile", jce.getMobile()==null?"":jce.getMobile());
		map.put("sbrecord", jce.getSbrecord()==null?"":jce.getSbrecord());
		map.put("opinion", jce.getOpinion()==null?"":jce.getOpinion());
		map.put("duty", jce.getDuty()==null?"":jce.getDuty());
		map.put("illegalactandevidence", jce.getIllegalactandevidence()==null?"":jce.getIllegalactandevidence());
		WordUtil.ireportWord(map, "ajcprecord.ftl", filePath, filename, request);
	
		return "/download/" + filename;
	}
	
}
