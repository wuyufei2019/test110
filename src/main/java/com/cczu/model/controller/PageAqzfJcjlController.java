package com.cczu.model.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
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

import com.cczu.model.dao.AqzfFcyjDao;
import com.cczu.model.dao.AqzfJcjhQyDao;
import com.cczu.model.dao.AqzfZlzgDao;
import com.cczu.model.entity.AQZF_Plan_EnterpriseEntity;
import com.cczu.model.entity.AQZF_SafetyCheckContentEntity;
import com.cczu.model.entity.AQZF_SafetyCheckPlanEntity;
import com.cczu.model.entity.AQZF_SafetyCheckRecordEntity;
import com.cczu.model.entity.AQZF_SafetyCheckSchemeEntity;
import com.cczu.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.model.entity.AQZF_SetNumberEntity;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.AqjgDSFManageService;
import com.cczu.model.service.AqzfJcfaService;
import com.cczu.model.service.AqzfJcjlService;
import com.cczu.model.service.AqzfJcnrService;
import com.cczu.model.service.AqzfSetBasicInfoService;
import com.cczu.model.service.IAqzfJcbkService;
import com.cczu.model.service.IAqzfJcjhService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

/**
 * 安全执法_检查记录controller
 */
@Controller
@RequestMapping("aqzf/jcjl")
public class PageAqzfJcjlController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private AqjgDSFManageService manageService;
	@Autowired
	private AqzfJcjlService aqzfJcjlService;
	@Autowired
	private AqzfJcfaService aqzfJcfaService;
	@Autowired
	private AqzfJcnrService aqzfJcnrService;
	@Autowired
	private IAqzfJcbkService AqzfJcbkService;
	@Autowired
	private IAqzfJcjhService AqzfJcjhService;
	@Autowired
	private AqzfSetBasicInfoService setbasicservice;
	@Resource
	private AqzfZlzgDao aqzfZlzgDao;
	@Resource
	private AqzfFcyjDao aqzfFcyjDao;
	@Autowired
	private AqzfJcjhQyDao AqzfJcjhQyDao;

	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "aqzf/jcjl/index";
	}
	
	/**
	 * list页面 
	 * @param request
	 */
	@RequiresPermissions("aqzf:jcjl:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		int pageNo=1;	//当前页码
		int pageSize=20;	//每页行数
		if(StringUtils.isNotEmpty(request.getParameter("page")))
			pageNo=Integer.valueOf(request.getParameter("page"));
		if(StringUtils.isNotEmpty(request.getParameter("rows")))
			pageSize=Integer.valueOf(request.getParameter("rows"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("0")){//安监局
			map.put("xzqy",sessionuser.getXzqy());
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
				map.put("jglx",sessionuser.getUserroleflg());
		}
		map.put("qyname", request.getParameter("aqzf_jcjl_qyname"));
		map.put("M6", request.getParameter("aqzf_jcjl_M6"));
		map.put("M7", request.getParameter("aqzf_jcjl_M7"));
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);

		return aqzfJcjlService.dataGrid(map);
		
	}


	/**
	 * 添加检查记录跳转
	 * @param request,model
	 */
	@RequestMapping(value = "addJl/{id}" , method = RequestMethod.GET)
	public String createFa(@PathVariable("id") Long id, Model model) {
		Map<String, Object> jcfa=aqzfJcfaService.findById(id);
		model.addAttribute("action", "create");
		model.addAttribute("jcfa", jcfa);//传检查方案id和企业信息过去
		Map<String, Object> jcjl=new HashMap<>();
		jcjl.put("m8", jcfa.get("m5"));
		model.addAttribute("jcjl", jcjl);
		return "aqzf/jcjl/form";	
	}
	
	/**
	 * 添加检查记录跳转2
	 * @param request,model
	 */
	@RequestMapping(value = "addJl2" , method = RequestMethod.GET)
	public String createFa2(Model model) {
		model.addAttribute("action", "create2");
		return "aqzf/jcjl/addform";	
	}

	/**
	 * 获取检查内容list
	 * @param request
	 */
	@RequestMapping(value = "nrlist/{id}" , method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getDataNr(@PathVariable("id") String nrid, HttpServletRequest request) {
		return aqzfJcjlService.dataGridNr(nrid);
		
	}

	/**
	 * 获取修改页面获取检查内容list
	 * @param request
	 */
	@RequestMapping(value = "nrlist2/{id}" , method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getDataNr2(@PathVariable("id") String jcjlid, HttpServletRequest request) {
		int pageNo=1;	//当前页码
		int pageSize=50;	//每页行数

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);

		return aqzfJcjlService.dataGridNr2(jcjlid);
		
	}
	
	/**
	 * 添加检查记录信息
	 * @param request,model
	 */
	@RequiresPermissions("aqzf:jcjl:add")
	@RequestMapping(value = "create" , method = RequestMethod.POST)
	@ResponseBody
	public String create(AQZF_SafetyCheckRecordEntity jcjl,String czwt,String czwturl, HttpServletRequest request) {
		//获取内容id数组并split
		String nr=request.getParameter("nrid");
		String[] nrid=nr.split(",");
		//获取文书编号
		AQZF_SetBasicInfoEntity bh =setbasicservice.findInfor();
		Map<String, Object> jcfa=aqzfJcfaService.findById(jcjl.getID1());
		Calendar a=Calendar.getInstance();
		String year=a.get(Calendar.YEAR)+"";
		String m0 = jcfa.get("m0").toString();
		jcjl.setM0("（"+bh.getSsqjc()+"）安监检记〔"+year+"〕"+m0.substring(m0.indexOf("〕")+1, m0.length()));
		Long jcjlid=aqzfJcjlService.addInfoReturnID(jcjl);//添加检查记录并返回id
		aqzfJcjlService.updateState(jcjl.getID1());//修改检查方案的检查状态
		//循环获取检查内容及状态
		if(!StringUtils.isEmpty(nr)){
			for(int i=1;i<=nrid.length;i++){
				String jcnrid=request.getParameter("jcnrid_"+nrid[i-1]);//获取检查内容id
				String jcwt=request.getParameter("jcwt_"+nrid[i-1]);//获取检查问题
				String jcjg=request.getParameter("ra_"+nrid[i-1]);//获取检查结果（1是，0否）
				String url=request.getParameter("url_"+nrid[i-1]);//获取地址
				AQZF_SafetyCheckContentEntity jcnr=new AQZF_SafetyCheckContentEntity();
				jcnr.setID1(jcjlid);//检查记录id
				jcnr.setID2(Long.parseLong(jcnrid));//检查内容id
				if(jcjg.equals("1")){
					jcnr.setM1(1);//检查结果
				}else{
					jcnr.setM1(0);//检查结果
				}	
				jcnr.setM2(jcwt);//存在问题
				jcnr.setM4(url);//图片地址
				aqzfJcnrService.addInfo(jcnr);//检查检查内容
			}
		}
		
		//获取存在的其他问题
		if(!StringUtils.isEmpty(czwt)){
			String[] czwt2=czwt.split(",");//存在问题数组
			String[] czwturl2=czwturl.split(",");//存在问题对应的图片数组
			for(int i=0;i<czwt2.length;i++){
				AQZF_SafetyCheckContentEntity wt=new AQZF_SafetyCheckContentEntity();
				wt.setID1(jcjlid);//检查记录id
				wt.setID2(0);//检查内容id
				wt.setM1(0);//存在问题
				wt.setM2(czwt2[i]);//内容
				wt.setM4(czwturl2[i]);//图片地址
				aqzfJcnrService.addInfo(wt);//检查检查内容
			}
		}
		String datasuccess="success";	

		//返回结果
		return datasuccess;
	}

	/**
	 * 添加检查记录信息2
	 * @param request,model
	 */
	@RequiresPermissions("aqzf:jcjl:add")
	@RequestMapping(value = "create2" , method = RequestMethod.POST)
	@ResponseBody
	public String create2(AQZF_SafetyCheckRecordEntity jcjl,String czwt,String czwturl, HttpServletRequest request) {
		//先添加检查计划信息
		BIS_EnterpriseEntity bis = bisQyjbxxService.findInfoById(jcjl.getID2());
		AQZF_SafetyCheckPlanEntity jcjh=new AQZF_SafetyCheckPlanEntity();
		jcjh.setM3(bis.getID2());
		jcjh.setM4(bis.getM36());
		jcjh.setID1(UserUtil.getCurrentShiroUser().getId());
		jcjh.setM2(request.getParameter("M6"));
		long jhid=AqzfJcjhService.addjcjh(jcjh);
		
		//添加安全检查计划与企业关联中间表
		AQZF_Plan_EnterpriseEntity pe = new AQZF_Plan_EnterpriseEntity();
		pe.setID1(jhid);
		pe.setID2(jcjl.getID2());
		pe.setM1("1");
		AqzfJcjhQyDao.addInfo(pe);
		
		//添加检查方案
		AQZF_SetBasicInfoEntity bh =setbasicservice.findInfor();
		Timestamp t=DateUtils.getSysTimestamp();
		Calendar a=Calendar.getInstance();
		String year=a.get(Calendar.YEAR)+"";
		AQZF_SetNumberEntity whbh=aqzfJcfaService.findWsbh();//获取文书编号
		AQZF_SafetyCheckSchemeEntity jcfa=new AQZF_SafetyCheckSchemeEntity();
		jcfa.setM0("（"+bh.getSsqjc()+"）安监检记〔"+year+"〕"+whbh.getM1()+"号");
		jcfa.setID1(jhid);//计划id
		jcfa.setID2(jcjl.getID2());//企业id
		jcfa.setM1(jcjl.getM1());//地址
		jcfa.setM11("1");//操作状态
		jcfa.setM2(request.getParameter("lxr"));//联系人
		jcfa.setM4(t);//检查时间
		jcfa.setM5(jcjl.getM8());//执法人员
		jcfa.setM6(request.getParameter("nrid"));//存在问题
		//添加行业类型
		if(bis.getM36().equals("1")){
			jcfa.setM3("工贸");
		}else if(bis.getM36().equals("2")){
			jcfa.setM3("化工");
		}
		long jcfaid=aqzfJcfaService.addInfoReturnID(jcfa);
		
		//获取内容id数组并split
		String nr=request.getParameter("nrid");
		String[] nrid=nr.split(",");
		jcjl.setID1(jcfaid);
		jcjl.setM0("（"+bh.getSsqjc()+"）安监检记〔"+year+"〕"+whbh.getM1()+"号");
		Long jcjlid=aqzfJcjlService.addInfoReturnID(jcjl);//添加检查记录并返回id
		//循环获取检查内容及状态
		if(!StringUtils.isEmpty(nr)){
			for(int i=1;i<=nrid.length;i++){
				String jcnrid=request.getParameter("jcnrid_"+nrid[i-1]);//获取检查内容id
				String jcwt=request.getParameter("jcwt_"+nrid[i-1]);//获取检查问题
				String jcjg=request.getParameter("ra_"+nrid[i-1]);//获取检查结果（1是，0否）
				String url=request.getParameter("url_"+nrid[i-1]);//获取地址
				AQZF_SafetyCheckContentEntity jcnr=new AQZF_SafetyCheckContentEntity();
				jcnr.setID1(jcjlid);//检查记录id
				jcnr.setID2(Long.parseLong(jcnrid));//检查内容id
				if(jcjg.equals("1")){
					jcnr.setM1(1);//检查结果
				}else{
					jcnr.setM1(0);//检查结果
				}	
				jcnr.setM2(jcwt);//存在问题
				jcnr.setM4(url);//图片地址
				aqzfJcnrService.addInfo(jcnr);//检查检查内容
			}
		}
		
		//获取存在的其他问题
		if(!StringUtils.isEmpty(czwt)){
			String[] czwt2=czwt.split(",");//存在问题数组
			String[] czwturl2=czwturl.split(",");//存在问题对应的图片数组
			for(int i=0;i<czwt2.length;i++){
				AQZF_SafetyCheckContentEntity wt=new AQZF_SafetyCheckContentEntity();
				wt.setID1(jcjlid);//检查记录id
				wt.setID2(0);//检查内容id
				wt.setM1(0);//存在问题
				wt.setM2(czwt2[i]);//内容
				wt.setM4(czwturl2[i]);//图片地址
				aqzfJcnrService.addInfo(wt);//检查检查内容
			}
		}
		String datasuccess="success";	

		//返回结果
		return datasuccess;
	}
	
	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="tablist/{qyid}")
	@ResponseBody
	public Map<String, Object> getDataTab(@PathVariable("qyid") Integer qyid, HttpServletRequest request) {
		
		Map<String, Object> map = getPageMap(request);
		map.put("qyid", qyid);
		return aqzfJcjlService.dataGrid(map);
	}
	
	/**
	 * 修改检查记录页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的成员信息
		Map<String, Object> jcjl= aqzfJcjlService.findById(id);
		//查询记录的存在问题
		List<Map<String, Object>> czwt= aqzfJcjlService.dataGridCzwt(id);
		
		model.addAttribute("jcjl", jcjl);
		model.addAttribute("czwt", JsonMapper.getInstance().toJson(czwt));
		//返回页面
		model.addAttribute("action", "update");
		return "aqzf/jcjl/updateForm";
	}
	
	/**
	 * 修改检查记录信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQZF_SafetyCheckRecordEntity jcjl,String czwt,String czwturl, HttpServletRequest request){
		String datasuccess="success";	
		Map<String, Object> jcfa=aqzfJcjlService.findNrid(jcjl.getID());
		String nid=(String) jcfa.get("nrid");
		String[] nrid=nid.split(",");
		//修改检查记录信息
		aqzfJcjlService.updateInfo(jcjl);
		
		if(StringUtils.isNotEmpty(nid)){
		//循环获取检查内容及状态
		for(int i=0;i<nrid.length;i++){
			String jcwt=request.getParameter("jcwt_"+nrid[i]);//获取检查问题
			String jcjg=request.getParameter("ra_"+nrid[i]);//获取检查结果（1是，0否）
			String url=request.getParameter("url_"+nrid[i]);//获取地址
			if(url==null){
				url="";
			}
			AQZF_SafetyCheckContentEntity jcnr=aqzfJcnrService.findNr(jcjl.getID(), nrid[i]);
			if(jcjg.equals("1")){
				jcnr.setM1(1);//检查结果
			}else{
				jcnr.setM1(0);//检查结果
			}	
			jcnr.setM2(jcwt);//存在问题
			jcnr.setM4(url);//图片地址
			aqzfJcnrService.updateInfo(jcnr);//检查检查内容
		}
		}
		//先删除其他问题，再重新添加
		aqzfJcnrService.deleteCzwt(jcjl.getID());
		//获取存在的其他问题
		if(!StringUtils.isEmpty(czwt)){
			String[] czwt2=czwt.split(",");//存在问题数组
			String[] czwturl2=czwturl.split(",");//存在问题对应的图片数组
			for(int i=0;i<czwt2.length;i++){
				AQZF_SafetyCheckContentEntity wt=new AQZF_SafetyCheckContentEntity();
				wt.setID1(jcjl.getID());//检查记录id
				wt.setID2(0);//检查内容id
				wt.setM1(0);//存在问题
				wt.setM2(czwt2[i]);//内容
				wt.setM4(czwturl2[i]);//图片地址
				aqzfJcnrService.addInfo(wt);//检查检查内容
			}
		}
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除检查记录信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			AQZF_SafetyCheckRecordEntity jcjl = aqzfJcjlService.findjl(Long.parseLong(aids[i]));
			aqzfJcjlService.deleteInfo(Long.parseLong(aids[i]));
			aqzfJcjlService.updatefa(jcjl.getID1());//修改检查方案的检查状态
		}
		
		return datasuccess;
	}

	/**
	 * 导出现场检查记录word
	 * 
	 */
	@RequiresPermissions("aqzf:jcjl:exportword")
	@RequestMapping(value = "export/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> map = aqzfJcjlService.getAjWord(id);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "xcjcjl.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	/**
	 * 导出责令整改指令书word
	 * 
	 */
	@RequiresPermissions("aqzf:jcjl:exportword2")
	@RequestMapping(value = "export2/{id}")
	@ResponseBody
	public String getAjWord2(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
//		Map<String,Object> map = aqzfJcjlService.getAjWord(id);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(null, "zlzgzls.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	/**
	 * 导出整改复查意见书word
	 * 
	 */
	@RequiresPermissions("aqzf:jcjl:exportword3")
	@RequestMapping(value = "export3/{id}")
	@ResponseBody
	public String getAjWord3(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
//		Map<String,Object> map = aqzfJcjlService.getAjWord(id);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(null, "zgfcyj.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		//查询选择的成员信息
		Map<String, Object> jcjl= aqzfJcjlService.findById(id);
		model.addAttribute("jcjl", jcjl);
		//查询记录的存在问题
		List<Map<String, Object>> czwt= aqzfJcjlService.dataGridCzwt(id);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("czwt", JsonMapper.getInstance().toJson(czwt));
		return "aqzf/jcjl/view";
	}

	/**
	 * 图片选择页面跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "choosenr")
	public String choosepic(Model model) {
		return "aqzf/jcjl/nrForm";
	}
	
	/**
	 * 添加检查内容页面跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "choosejcnr")
	public String choosejcnr(Model model) {
		return "aqzf/jcjl/addnrForm";
	}
	
	/**
	 * 图片选择页面跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "choose")
	public String choosenr(Model model) {
		return "aqzf/jcjl/picForm";
	}
	
	
}
