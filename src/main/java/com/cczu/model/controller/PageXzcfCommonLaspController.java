package com.cczu.model.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import com.cczu.model.entity.AQZF_SafetyCheckContentEntity;
import com.cczu.model.entity.AQZF_SafetyCheckRecordEntity;
import com.cczu.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.model.entity.AQZF_SetNumberEntity;
import com.cczu.model.entity.AQZF_WfxwEntity;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.XZCF_YbcfLaspEntity;
import com.cczu.model.service.AqzfJcjlService;
import com.cczu.model.service.AqzfJcnrService;
import com.cczu.model.service.AqzfSetBasicInfoService;
import com.cczu.model.service.AqzfSetNumberService;
import com.cczu.model.service.AqzfWfxwService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.IMsgService;
import com.cczu.model.service.IXzcfCommonLaspService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

/**
 * 行政处罚-一般处罚-立案审批controller
 * 
 * @author jason
 * 
 */
@Controller
@RequestMapping("xzcf/ybcf/lasp")
public class PageXzcfCommonLaspController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private IMsgService msgService;
	@Autowired
	private IXzcfCommonLaspService punishcommonlaspservice;
	@Autowired
	private AqzfSetNumberService setNumberService;
	@Autowired
	private AqzfJcjlService aqzfJcjlService;
	@Autowired
	private AqzfJcnrService aqzfJcnrService;
	@Autowired
	private AqzfWfxwService aqzfWfxwService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private AqzfSetBasicInfoService setbasicservice;

	/**
	 * 默认页面
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
		int count=punishcommonlaspservice.getTempCount(map);
		model.addAttribute("count",count);
		return "aqzf/xzcf/ybcf/lasp/index";
	}

	/**
	 * list页面
	 */
	@RequiresPermissions("ybcf:lasp:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("number", request.getParameter("ybcf_lasp_number"));
		map.put("name", request.getParameter("ybcf_lasp_name"));
		map.put("startdate", request.getParameter("ybcf_lasp_startdate"));
		map.put("enddate", request.getParameter("ybcf_lasp_enddate"));
		map.put("xzqy",UserUtil.getCurrentShiroUser().getXzqy());
		return punishcommonlaspservice.dataGrid(map);
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
	 * 添加信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("ybcf:lasp:add")
	@RequestMapping(value = "create")
	public String create(Model model) {
		XZCF_YbcfLaspEntity yle = new XZCF_YbcfLaspEntity();
		yle.setID2(0);//设置检查记录id
		model.addAttribute("yle", yle);
		model.addAttribute("action", "createSub");
		return "aqzf/xzcf/ybcf/lasp/form";
	}
	
	/**
	 * 检查记录添加立案审批跳转
	 * @param id 检查记录id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "create2/{id}")
	public String create2(@PathVariable("id") Long  id, Model model) {
		//根据检查记录id查找检查记录
		AQZF_SafetyCheckRecordEntity jcjl=aqzfJcjlService.findjl(id);
		
		//存在问题
		List<AQZF_WfxwEntity> wfxwlist = new ArrayList<>();
		List<AQZF_SafetyCheckContentEntity> list = aqzfJcnrService.findByJlid(id);
		for (AQZF_SafetyCheckContentEntity scc : list) {
			AQZF_WfxwEntity wfxw = new AQZF_WfxwEntity();
		    try{
				//根据违法id查找违法行为
				wfxw = aqzfWfxwService.findById(Long.parseLong(scc.getM2()));
		    }catch (Exception e) {
				wfxw.setM1(scc.getM2());
			}
			wfxwlist.add(wfxw);
		}
		model.addAttribute("wfxwlist", JsonMapper.getInstance().toJson(wfxwlist));
		
		//设置基本信息
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(jcjl.getID2());
		XZCF_YbcfLaspEntity yle = new XZCF_YbcfLaspEntity();
		yle.setID2(id);//设置检查记录id
		yle.setId1(jcjl.getID2());//设置企业id
		yle.setContact(jcjl.getM4());//设置联系电话
		yle.setDsaddress(jcjl.getM1());//设置地址
		yle.setLegalperson(jcjl.getM2());//设置法人
		yle.setDsperson(be.getM1());//设置企业名
		yle.setYbcode(be.getM9());//设置邮编
		model.addAttribute("yle", yle);
		
		model.addAttribute("action", "createSub");
		return "aqzf/xzcf/ybcf/lasp/form2";
	}

	/**
	 * 添加立案审批
	 * @param request
	 */
	@RequestMapping(value = "createSub", method = RequestMethod.POST)
	@ResponseBody
	public String createSub(XZCF_YbcfLaspEntity yle,
			HttpServletRequest request, Model model) throws IOException {
		String datasuccess = "failed";
		Timestamp t = DateUtils.getSysTimestamp();
		AQZF_SetNumberEntity bh =setNumberService.findInfor();
		AQZF_SetBasicInfoEntity asb =setbasicservice.findInfor();
		yle.setNumber("（"+asb.getSsqjc()+"）安监立〔"+DateUtils.getYear()+"〕"+(bh.M6)+" 号");
		yle.setSdhznumber("（"+asb.getSsqjc()+"）安监回〔"+DateUtils.getYear()+"〕"+(bh.M6)+" 号");
		bh.setM6(bh.M6+1);
		setNumberService.updateInfo(bh);
		yle.setS1(t);
		yle.setS2(t);
		yle.setS3(0);
		yle.setXwflag("0");
		yle.setCbflag("0");
		yle.setGzflag("0");
		yle.setTzflag("0");
		yle.setCfjdflag("0");
		yle.setTempflag("0");
		yle.setDcflag("0");
		yle.setCgflag("0");
		yle.setQzflag("0");
		yle.setJaflag("0");
		yle.setSbflag("0");
		yle.setTlflag("0");
		yle.setUserid(UserUtil.getCurrentUser().getId());
	
		if (punishcommonlaspservice.addInfoReturnID(yle) > 0){
			if(yle.getID2()!=0){
				//根据检查记录id查找检查记录
				AQZF_SafetyCheckRecordEntity jcjl=aqzfJcjlService.findjl(yle.getID2());
				jcjl.setM14("1");
			    aqzfJcjlService.updateInfo(jcjl);
			}
			datasuccess = "success";
		}
		return datasuccess;
	}

	/**
	 * 修改信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("ybcf:lasp:update")
	@RequestMapping(value = "update/{id}")
	public String update(@PathVariable("id") String  id, Model model) {
		XZCF_YbcfLaspEntity yle;
		if(!id.equals("temp")){
			 yle = punishcommonlaspservice.findInfoById(Long.parseLong(id));
			 if(yle.getID2()!=0){
				 //跟检查记录有关联
				 model.addAttribute("yle", yle);
				 model.addAttribute("action", "updateSub");
				 
				//存在问题
				List<AQZF_WfxwEntity> wfxwlist = new ArrayList<>();
				List<AQZF_SafetyCheckContentEntity> list = aqzfJcnrService.findByJlid(yle.getID2());
				for (AQZF_SafetyCheckContentEntity scc : list) {
					AQZF_WfxwEntity wfxw = new AQZF_WfxwEntity();
				    try{
						//根据违法id查找违法行为
						wfxw = aqzfWfxwService.findById(Long.parseLong(scc.getM2()));
				    }catch (Exception e) {
						wfxw.setM1(scc.getM2());
					}
					wfxwlist.add(wfxw);
				}
				model.addAttribute("wfxwlist", JsonMapper.getInstance().toJson(wfxwlist));
					
				return "aqzf/xzcf/ybcf/lasp/form2";
			 }
		}else{
			 yle =punishcommonlaspservice.findTempInfo(UserUtil.getCurrentShiroUser().getXzqy());
		}
		model.addAttribute("yle", yle);
		model.addAttribute("action", "updateSub");
		return "aqzf/xzcf/ybcf/lasp/form";
	}

	/**
	 * 修改信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("ybcf:lasp:update")
	@RequestMapping(value = "updateSub")
	@ResponseBody
	public String updateSub(XZCF_YbcfLaspEntity yle,
			HttpServletRequest request, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		if("1".equals(yle.getTempflag()))
			yle.setTempflag("2");
		yle.setS2(t);
		try {
			punishcommonlaspservice.updateInfo(yle);
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="failed";
		}
		return datasuccess;
	}

	/**
	 * 查看立案审批信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("ybcf:lasp:view")
	@RequestMapping(value = "view/{id}")
	public String view(@PathVariable("id") long id, Model model) {
		XZCF_YbcfLaspEntity yle = punishcommonlaspservice.findInfoById(id);
		//事故
		String ajqk = "";
		if(!StringUtils.isEmpty(yle.getCasecondition())){
			String[] wfxwids = yle.getCasecondition().split(",");
			for(int i=0;i<wfxwids.length;i++){
				try{
					AQZF_WfxwEntity wfxw = aqzfWfxwService.findById(Long.parseLong(wfxwids[i]));
					ajqk += (i+1)+". "+wfxw.getM1()+" ";
				}catch (Exception e){
					ajqk += (i+1)+". "+wfxwids[i]+" ";
				}
			}
		}
		model.addAttribute("ajqk", ajqk);
		
		model.addAttribute("yle", yle);
		return "aqzf/xzcf/ybcf/lasp/view";
	}

	/**
	 * 删除立案审批 信息
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("ybcf:lasp:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "success";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			XZCF_YbcfLaspEntity yle = punishcommonlaspservice.findInfoById(Long.parseLong(arrids[i]));
			punishcommonlaspservice.deleteInfo(Long.parseLong(arrids[i]));
			if(yle.getID2()!=0){
				//与检查记录有关,根据检查记录id查找检查记录
				AQZF_SafetyCheckRecordEntity jcjl=aqzfJcjlService.findjl(yle.getID2());
				jcjl.setM14("0");
			    aqzfJcjlService.updateInfo(jcjl);
			}
		}
		return datasuccess;
	}
	
	/**
	 * 获取所有和立案审批相关的number记录
	 * @param model
	 * @return
	 */
	@RequestMapping(value="numberlist/{id}")
	@ResponseBody
	public List<Map<String, Object>> getNumberlist(@PathVariable("id") Long id) {
		List<Map<String, Object>> list=null;
		try {
			list = punishcommonlaspservice.getNumberlist(id,UserUtil.getCurrentShiroUser().getXzqy());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * 导出立案审批记录word
	 * 
	 */
	@RequiresPermissions("ybcf:lasp:export")
	@RequestMapping(value = "exportlasp/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		XZCF_YbcfLaspEntity yle = punishcommonlaspservice.findInfoById(id);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("ayname", yle.getAyname());
		map.put("filldate", yle.getFilldate()==null?"":DateUtils.formatDate(yle.getFilldate(), "yyyy年MM月dd日"));
		map.put("casesource", yle.getCasesource());
	    map.put("casename", yle.getCasename());
	    map.put("dsperson", yle.getDsperson());
	    map.put("contact", yle.getContact());
	    map.put("legalperson", yle.getLegalperson());
	    map.put("dsaddress", yle.getDsaddress());
	    map.put("ybcode", yle.getYbcode());
		map.put("opinion",yle.getOpinion()==null?"":yle.getOpinion());
		map.put("identity1", yle.getIdentity1());
		map.put("identity2",yle.getIdentity2());
		map.put("number", yle.getNumber());
		
		//案件情况
		String ajqk = "";
		if(!StringUtils.isEmpty(yle.getCasecondition())){
			String[] wfxwids = yle.getCasecondition().split(",");
			for(int i=0;i<wfxwids.length;i++){
				try {
					AQZF_WfxwEntity wfxw = aqzfWfxwService.findById(Long.parseLong(wfxwids[i]));
					ajqk += (i+1)+". "+wfxw.getM1()+" ";
				} catch (Exception e) {
					ajqk += (i+1)+". "+wfxwids[i]+" ";
				}
			}
		}
		map.put("casecondition", ajqk);

		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "lasprecord.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	
	/**
	 * 导出送达回执word
	 * 
	 */
	@RequiresPermissions("ybcf:lasp:export")
	@RequestMapping(value = "exportsdhz/{id}")
	@ResponseBody
	public String getSdhzWord(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		XZCF_YbcfLaspEntity yle = punishcommonlaspservice.findInfoById(id);
		Map<String, Object> map=new HashMap<String, Object>();
		AQZF_SetBasicInfoEntity asb =setbasicservice.findInfor();
		map.put("ssqjc", asb.getSsqjc());
		map.put("number",yle.getSdhznumber());
		map.put("casename", yle.getCasename());
		map.put("qyname", yle.getDsperson());
		map.put("year", DateUtils.getYear());
		map.put("ssqmc", asb.getSsqmc());

		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "xzcfsdhz.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
}
