package com.cczu.model.controller;


import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.model.entity.AQZF_SetNumberEntity;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.XZCF_EnquiryNoteEntity;
import com.cczu.model.entity.XZCF_YbcfLaspEntity;
import com.cczu.model.service.AqzfSetBasicInfoService;
import com.cczu.model.service.AqzfSetNumberService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.IXzcfCommonLaspService;
import com.cczu.model.service.XzcfXwtzService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

/**
 * 询问通知
 * @author zpc
 * @date 2017/08/02
 */
@Controller
@RequestMapping("xzcf/ybcf/xwtz")
public class PageXzcfXwtzController extends BaseController{

	@Autowired
	private XzcfXwtzService xzcfXwtzService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService; 
	@Autowired
	private AqzfSetNumberService setNumberService;
	@Autowired
	private IXzcfCommonLaspService xzcfcommonlaspservice;
	@Autowired
	private AqzfSetBasicInfoService aqzfsetbasicinfoservice;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "aqzf/xzcf/ybcf/xwtz/index";
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
		map.put("qyname", request.getParameter("aqzf_xwtz_qyname"));
		map.put("m2", request.getParameter("aqzf_xwtz_M2"));
		return xzcfXwtzService.dataGrid(map);
	}
	
	/**
	 * 删除询问通知记录
	 * 
	 * @param user
	 * @param model
	 * 
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		try {
			for(int i=0;i<aids.length;i++){
				xzcfXwtzService.deleteInfo(Long.parseLong(aids[i]));
				xzcfXwtzService.updateLaspInfo(Long.parseLong(aids[i]));
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			datasuccess="删除失败";
		}
		return datasuccess;
	}

	/**
	 * 添加询问通知页面跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "create/{id}" , method = RequestMethod.GET)
	public String create(@PathVariable("id") Long id,Model model) {
		model.addAttribute("action", "create");
		XZCF_EnquiryNoteEntity xwtz = new XZCF_EnquiryNoteEntity();
		XZCF_YbcfLaspEntity ybcf = xzcfcommonlaspservice.findInfoById(id);
		//企业id
		xwtz.setID2(ybcf.getId1());
		//立案处罚id
		xwtz.setID3(id);
		model.addAttribute("qyname",bisQyjbxxService.findInfoById(ybcf.getId1()).getM1());
		model.addAttribute("xwtz", xwtz);
		return "aqzf/xzcf/ybcf/xwtz/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "create" )
	@ResponseBody
	public String create(XZCF_EnquiryNoteEntity xwtz, HttpServletRequest request) {
		String datasuccess="success";
		ShiroUser su = UserUtil.getCurrentShiroUser();
		//设置编号
		AQZF_SetBasicInfoEntity bh =aqzfsetbasicinfoservice.findInfor();
		XZCF_YbcfLaspEntity ybcf = xzcfcommonlaspservice.findInfoById(xwtz.getID3());
		String m0 = ybcf.getNumber();
		xwtz.setM0("（"+bh.getSsqjc()+"）安监询〔"+DateUtils.getYear()+"〕"+m0.substring(m0.indexOf("〕")+1, m0.length()));
		
		xwtz.setID1(su.getId());
		try {
			xzcfXwtzService.addInfo(xwtz);
			XZCF_YbcfLaspEntity yle= xzcfcommonlaspservice.findInfoById(xwtz.getID3());
			yle.setXwflag("1");
			xzcfcommonlaspservice.updateInfo(yle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			datasuccess="error";
		}
		return datasuccess;
	}
	
	
	/**
	 * 添加临时询问通知页面跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "createtemp" , method = RequestMethod.GET)
	public String createtem(Model model) {
		model.addAttribute("action", "createtemp");
		return "aqzf/xzcf/ybcf/xwtz/form";
	}
	
	/**
	 * 添加临时询问信息
	 * @param request,model
	 */
	@RequestMapping(value = "createtemp" )
	@ResponseBody
	public String createtem(XZCF_EnquiryNoteEntity xwtz, HttpServletRequest request) {
		String datasuccess="success";
		ShiroUser su = UserUtil.getCurrentShiroUser();
		XZCF_YbcfLaspEntity lasp = new XZCF_YbcfLaspEntity();
		AQZF_SetNumberEntity bh =setNumberService.findInfor();
		//lasp.setAyname(xwtz.getM1());
		lasp.setID2(0);//与检查记录无关
		lasp.setXwflag("1");
		lasp.setTempflag("1");
		lasp.setId1(xwtz.getID2());
		BIS_EnterpriseEntity bis=bisQyjbxxService.findInfoById(xwtz.getID2());
		lasp.setDsperson(bis.getM1());
		lasp.setContact(bis.getM6());
		lasp.setYbcode(bis.getM9());
		lasp.setDsaddress(bis.getM8());
		lasp.setLegalperson(bis.getM5());
		Timestamp t = DateUtils.getSysTimestamp();
		lasp.setS1(t);
		lasp.setS2(t);
		lasp.setS3(0);
		lasp.setUserid(su.getId());
		AQZF_SetBasicInfoEntity asb =aqzfsetbasicinfoservice.findInfor();
		lasp.setNumber("（"+asb.getSsqjc()+"）安监立〔"+DateUtils.getYear()+"〕"+(bh.M6)+" 号");
		lasp.setSdhznumber("（"+asb.getSsqjc()+"）安监回〔"+DateUtils.getYear()+"〕"+(bh.M6)+" 号");
		long laspid=xzcfcommonlaspservice.addInfoReturnID(lasp);
		if(laspid>0){
			
			xwtz.setM0("（"+asb.getSsqjc()+"）安监询〔"+DateUtils.getYear()+"〕"+(bh.M6)+"号");
			bh.setM6(bh.M6+1);
			setNumberService.updateInfo(bh);
			xwtz.setID1(su.getId());
			xwtz.setID3(laspid);
			try {
				xzcfXwtzService.addInfo(xwtz);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				datasuccess="error";
			}
		}else{
			datasuccess="error";
		}
		return datasuccess;
	}
	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		XZCF_EnquiryNoteEntity xwtz  = xzcfXwtzService.findById(id);
		//BIS_EnterpriseEntity a = bisQyjbxxService.findInfoById(xwtz.getID2());
		//model.addAttribute("qyname",a.getM1());
		model.addAttribute("xwtz", xwtz);
		model.addAttribute("action", "update");
		return "aqzf/xzcf/ybcf/xwtz/form";
	}
	
	/**
	 * 修改
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(XZCF_EnquiryNoteEntity xwtz){
		
		return xzcfXwtzService.updateTempTzs(xwtz);
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		//查询选择的询问通知信息
		Map<String,Object> xwtz = xzcfXwtzService.findWord(id);	
		model.addAttribute("qyname",xwtz.get("qyname").toString());
		model.addAttribute("xwtz", xwtz);
		return "aqzf/xzcf/ybcf/xwtz/view";
	}
	
	/**
	 * 导出询问通知书word
	 * 
	 */
	@RequiresPermissions("aqzf:xwtz:export")
	@RequestMapping(value = "export/{flag}/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("flag") String flag,@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> mapret;
		if("la".equals(flag))
		mapret= xzcfXwtzService.findWordByLaId(id);
		else
		mapret= xzcfXwtzService.findWord(id);
		Map<String, Object> map=new HashMap<String, Object>();
		//解析询问时间
		if(mapret.get("m2")!=null&&!mapret.get("m2").toString().equals("")){
			String a = mapret.get("m2").toString();
			String[] as1 = a.substring(0,10).split("-");
			map.put("year", as1[0]);
			map.put("month", as1[1]);
			map.put("day", as1[2]);
			String[] bs1 = a.substring(11,16).split(":");
			map.put("hour", bs1[0]);
		}else{
			map.put("year", "   ");
			map.put("month", "   ");
			map.put("day", "   ");
			map.put("hour", "   ");
		}
		map.put("m0", mapret.get("m0")==null||mapret.get("m0").toString().equals("")?"":mapret.get("m0").toString());
		map.put("address", aqzfsetbasicinfoservice.findInfor().getAddress());
		map.put("qyname",mapret.get("qyname")==null||mapret.get("qyname").toString().equals("")?"         ":mapret.get("qyname").toString());
		map.put("m1", mapret.get("m1")==null||mapret.get("m1").toString().equals("")?"":mapret.get("m1").toString());
		map.put("m3", mapret.get("m3")==null||mapret.get("m3").toString().equals("")?"                 ":mapret.get("m3").toString());
		map.put("m4", mapret.get("m4")==null||mapret.get("m4").toString().equals("")?"":mapret.get("m4").toString());
		map.put("m6", aqzfsetbasicinfoservice.findInfor().getContact());
		map.put("m7", aqzfsetbasicinfoservice.findInfor().getPhone());
		map.put("ssqmc", aqzfsetbasicinfoservice.findInfor().getSsqmc());
		String html="<w:sym w:font=\"Wingdings 2\" w:char=\"0052\"/>";
		String html2="<w:t>□</w:t>";
		if(mapret.get("m8")!=null&&!mapret.get("m8").toString().equals("")){
			String m8=mapret.get("m8").toString();
			//,,法定代表人身份证明或者
			String [] s= m8.split(",");
			map.put("sfz",html2);
			for(String str:s){
				if(str.equals("身份证"))
					map.put("sfz",html);
			}
			if(m8.contains("营业执照"))
				map.put("yyzz",html);
			else
				map.put("yyzz",html2);
			if(m8.contains("委托书"))
				map.put("wts",html);
			else
				map.put("wts",html2);
			if(StringUtils.isNotBlank(mapret.get("m4").toString()))
				map.put("qt",html);
			else
				map.put("qt",html2);
		}else{
			map.put("sfz",html2);
			map.put("yyzz",html2);
			map.put("wts",html2);
			map.put("qt",html2);
		}
		
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "xwtzs.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
}
