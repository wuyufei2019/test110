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
import com.cczu.model.entity.XZCF_CfjdInfoEntity;
import com.cczu.model.entity.XZCF_GzsInfoEntity;
import com.cczu.model.entity.XZCF_YbcfAjcpEntity;
import com.cczu.model.entity.XZCF_YbcfLaspEntity;
import com.cczu.model.service.AqzfSetBasicInfoService;
import com.cczu.model.service.AqzfSetNumberService;
import com.cczu.model.service.IPunishSimpleGzService;
import com.cczu.model.service.IXzcfCfjdService;
import com.cczu.model.service.IXzcfCommonAjcpService;
import com.cczu.model.service.IXzcfCommonLaspService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

/**
 * 行政处罚-简易处罚-处罚决定controller
 * 
 * @author jason
 * 
 */
@Controller
@RequestMapping("xzcf/ybcf/cfjd")
public class PageXzcfCommonCfjdController extends BaseController {

	@Autowired
	private IXzcfCfjdService punishsimplecfjdservice;
	@Autowired
	private IPunishSimpleGzService punishsimplegzservice;
	@Autowired
	private IXzcfCommonLaspService xzcfcommonlaspservice;
	@Autowired
	private AqzfSetNumberService setNumberService;
	@Autowired
	private AqzfSetBasicInfoService aqzfsetbasicinfoservice;
	@Autowired
	private IXzcfCommonAjcpService xzcfcommonajcpservice;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		return "aqzf/xzcf/ybcf/cfjd/index";
	}

	/**
	 * list页面
	 */
	@RequiresPermissions("ybcf:cfjd:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("number", request.getParameter("ybcf_cfjd_number"));
		map.put("type", request.getParameter("ybcf_cfjd_type"));
		map.put("startdate", request.getParameter("ybcf_cfjd_startdate"));
		map.put("enddate", request.getParameter("ybcf_cfjd_enddate"));
		map.put("cfdw", request.getParameter("xzcf_sxcg_name"));
		map.put("cftype", "1");
		map.put("xzqy",UserUtil.getCurrentShiroUser().getXzqy());
		return punishsimplecfjdservice.dataGrid(map);
	}
	
	/**
	 * 首页提醒
	 * 
	 * @param request
	 */
	//@RequestMapping(value = "html")
	//@ResponseBody
//	public Map<String, Object>  getHtml(HttpServletRequest request, Model model) {
//		Map<String, Object> map = getPageMap(request);
//		Map<String, Object> remap = new HashMap<>();//返回值
//		ShiroUser user= UserUtil.getCurrentShiroUser();
//		map.put("year", DateUtils.getYear());
//		map.put("month", Integer.parseInt(DateUtils.getMonth()));
//		if(user.getUsertype().equals("1")){//0是企业，1为安监局
//		map.put("id", user.getQyid());
//		}
//		remap.put("rows",aqjgcheckplanservice.dataGrid(map).get("rows") );
//		remap.put("usertype",UserUtil.getCurrentShiroUser().getUsertype());
//		return remap;
//	}
	

	/**
	 * 添加处罚信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("ybcf:cfjd:add")
	@RequestMapping(value = "create/{id}")
	public String create(@PathVariable("id") long id,Model model) {
		//XZCF_YbcfLaspEntity yle=xzcfcommonlaspservice.findInfoById(id);
		XZCF_GzsInfoEntity gie=punishsimplegzservice.findInfoByLaId(id);
		XZCF_YbcfAjcpEntity ajcp=xzcfcommonajcpservice.findInfoByLaId(id);
		model.addAttribute("id1", id);
		model.addAttribute("ajcp", ajcp);
		model.addAttribute("gie", gie);
		//model.addAttribute("yle", yle);
		model.addAttribute("action", "createSub");
		return "aqzf/xzcf/ybcf/cfjd/form";
	}

	/**
	 * 添加出处罚
	 * @param request
	 */
	@RequiresPermissions("ybcf:cfjd:add")
	@RequestMapping(value = "createSub", method = RequestMethod.POST)
	@ResponseBody
	public String createSub(XZCF_CfjdInfoEntity jce,
			HttpServletRequest request, Model model){
		String datasuccess = "error";
		Timestamp t = DateUtils.getSysTimestamp();
		jce.setS1(t);
		jce.setS2(t);
		jce.setS3(0);
		jce.setS3(0);
		jce.setPunishname(request.getParameter("punishname").toString());
		
		//设置编号
		AQZF_SetBasicInfoEntity bh =aqzfsetbasicinfoservice.findInfor();
		XZCF_YbcfLaspEntity ybcf = xzcfcommonlaspservice.findInfoById(jce.getId1());
		String m0 = ybcf.getNumber();
		jce.setNumber("（"+bh.getSsqjc()+"）安监罚〔"+DateUtils.getYear()+"〕"+m0.substring(m0.indexOf("〕")+1, m0.length()));
		
		if (punishsimplecfjdservice.addInfoReturnID(jce) > 0) {
			try {
				XZCF_YbcfLaspEntity yle= xzcfcommonlaspservice.findInfoById(jce.getId1());
				yle.setCfjdflag("1");
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
	 * 修改处罚信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("ybcf:cfjd:update")
	@RequestMapping(value = "update/{id}")
	public String update(@PathVariable("id") long id, Model model) {

		XZCF_CfjdInfoEntity jce = punishsimplecfjdservice.findInfoById(id);
		model.addAttribute("jce", jce);
		model.addAttribute("flag", jce.getPercomflag());
		model.addAttribute("action", "updateSub");
		return "aqzf/xzcf/ybcf/cfjd/form";
	}

	/**
	 * 修改处罚信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("ybcf:cfjd:update")
	@RequestMapping(value = "updateSub")
	@ResponseBody
	public String updateSub(XZCF_CfjdInfoEntity jce,
			HttpServletRequest request, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		String qyname=request.getParameter("punishname");
		jce.setPunishname(qyname);
		jce.setS2(t);
		try {
			punishsimplecfjdservice.updateInfo(jce);
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="error";
		}
		return datasuccess;
	}

	/**
	 * 查看处罚信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("ybcf:cfjd:view")
	@RequestMapping(value = "view/{id}")
	public String view(@PathVariable("id") long id, Model model) {
		XZCF_CfjdInfoEntity jce = punishsimplecfjdservice.findInfoById(id);
		model.addAttribute("jce", jce);
		return "aqzf/xzcf/ybcf/cfjd/view";
	}

	/**
	 * 删除处罚信息
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("ybcf:cfjd:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		try {
			for (int i = 0; i < arrids.length; i++) {
				punishsimplecfjdservice.deleteInfo(Long.parseLong(arrids[i]));
				punishsimplecfjdservice.updateLaspInfo(Long.parseLong(arrids[i]));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="删除失败";
		}
		return datasuccess;
	}
	
	/**
	 * 导出告知书记录word
	 * 
	 */
	@RequiresPermissions("ybcf:cfjd:export")
	@RequestMapping(value = "exportcfjd/{flag}/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("flag") String flag,@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		XZCF_CfjdInfoEntity jce ;
		AQZF_SetBasicInfoEntity sbe=aqzfsetbasicinfoservice.findInfor();
		if("la".equals(flag))
		jce=punishsimplecfjdservice.findInfoByLaId(id);
		else
		jce= punishsimplecfjdservice.findInfoById(id);
		//XZCF_JYCFInfoEntity jie=punishsimplegzservice.findInfoById(jce.getId1());
		Map<String, Object> map=new HashMap<String, Object>();
		//企业个人公有
		map.put("bankname", sbe.getBankname());
		map.put("account", sbe.getAccount());
		map.put("gov", sbe.getGov());
		map.put("highgov", sbe.getHighgov());
		map.put("court", sbe.getCourt());
		map.put("number",jce.getNumber());
		map.put("punishdate",DateUtils.formatDate(jce.getPunishdate(), "yyyy年MM月dd日"));
		map.put("punishname", jce.getPunishname());
		map.put("address", jce.getAddress());
		map.put("mobile", jce.getMobile());
		map.put("duty", jce.getDuty());
		map.put("illegalactandevidence", jce.getIllegalactandevidence()==null?"":jce.getIllegalactandevidence());
		map.put("unlaw", jce.getUnlaw()==null?"":jce.getUnlaw());
		map.put("enlaw",jce.getEnlaw()==null?"":jce.getEnlaw());
		map.put("punishresult", jce.getPunishresult()==null?"":jce.getPunishresult());
		map.put("ybcode", jce.getYbcode()==null?"":jce.getYbcode());
		map.put("ssqmc", sbe.getSsqmc());
		if("1".equals(jce.getPercomflag())){
			map.put("legal", jce.getLegal());
			WordUtil.ireportWord(map, "comcfjd.ftl", filePath, filename, request);
		}else{
			map.put("sex",jce.getSex());
			map.put("age", jce.getAge());
			map.put("identity1", jce.getIdentity1());
			map.put("dwname", jce.getDwname());
			map.put("dwaddress", jce.getDwaddress());
			WordUtil.ireportWord(map, "percfjd.ftl", filePath, filename, request);
		}
	
		return "/download/" + filename;
	}
	
}
