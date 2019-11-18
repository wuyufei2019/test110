package com.cczu.model.ztzyaqgl.controller;

import com.alibaba.fastjson.JSON;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.model.ztzyaqgl.entity.ZTAQGL_Mbcdzy;
import com.cczu.model.ztzyaqgl.entity.ZTAQGL_Zyaq_Aqcs;
import com.cczu.model.ztzyaqgl.service.AqglAqcsService;
import com.cczu.model.ztzyaqgl.service.AqglMbcdService;
import com.cczu.model.zyaqgl.service.AqglXgdwService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.Department;
import com.cczu.sys.system.service.DepartmentService;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 安全生产-盲板抽堵作业Controller
 * @author YZH
 */
@Controller
@RequestMapping("ztzyaqgl/mbcd")
public class AqglMbcdController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private AqglMbcdService aqglmbcdService;
	@Autowired
	private AqglAqcsService aqglaqcsService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	@Autowired
	private AqglXgdwService aqglxgdwService;
	@Autowired
	private DepartmentService departmentService;

	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model,HttpServletRequest request) {
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		model.addAttribute("qyid", sessionuser.getQyid());
		model.addAttribute("spzt", request.getParameter("spzt"));
		return "ztzyaqgl/zyaq/mbcd/index";
	}
	
	/**
	 * 数据list 
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("m1", request.getParameter("aqgl_mbcd_cx_m1"));//作业证编号
		map.put("m2", request.getParameter("aqgl_mbcd_cx_m2"));//作业单位
		map.put("m10", request.getParameter("aqgl_mbcd_cx_m10"));//盲板抽堵作业级别
		map.put("zt", request.getParameter("aqgl_mbcd_cx_zt"));//盲板抽堵作业状态

		map.putAll(getAuthorityMap());

		//如果选择了部门下拉，则覆盖前面的部门条件，但也仅限于该用户所能看到的部门范围
		String depid=request.getParameter("aqgl_mbcd_cx_depid");
		if(depid!=null&&(!depid.equals(""))){
			Department dep=departmentService.get(Long.parseLong(depid));
			map.put("depcode3", dep.getCode());
		}


		Subject subject = SecurityUtils.getSubject();
		if(!subject.isPermitted("zyaqgl:gczy:viewall")) {
			if (subject.isPermitted("zyaqgl:dhzy:bwcsp")) {//是否拥有保卫处审批权限
				map.put("depcode2", "");
				map.put("zt2", "5");//盲板抽堵作业状态
			}
		}
//		if(subject.isPermitted("zyaqgl:gczy:gsldsp")) {//是否拥有公司分管领导审批权限
//			map.put("depcode2", "");
//			map.put("zt2", "6");//盲板抽堵作业状态
//		}
		map.put("dspzt", request.getParameter("spzt"));//当前审批人待审批的状态
		return aqglmbcdService.dataGrid(map);
		
	}

	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("zyaqgl:mbcd:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model, HttpServletRequest request) {
		String code="MBCD-"+ DateUtils.getDateRandom();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("m1", code);
		map.put("bzr", UserUtil.getCurrentUser().getName());//编制人
		model.addAttribute("mbcd", map);
		model.addAttribute("action", "create");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "ztzyaqgl/zyaq/mbcd/form";
	}

	/**
	 * 添加盲板抽堵作业信息
	 * @param request,model
	 */
	@RequiresPermissions("zyaqgl:mbcd:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(ZTAQGL_Mbcdzy entity, Model model) {
		String datasuccess = "success";
		Timestamp t= DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		entity.setZt("0");//添加初始状态
		entity.setID1(UserUtil.getCurrentShiroUser().getQyid());
		//申请人id
		entity.setM29_1(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));
		//添加盲板抽堵作业
		aqglmbcdService.addInfo(entity);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  作业安全--盲板抽堵作业  【添加操作】");
		// 返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的盲板抽堵作业信息
		Map<String, Object> mbcd = aqglmbcdService.findInforById(id);
		model.addAttribute("mbcd", mbcd);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "ztzyaqgl/zyaq/mbcd/form";
	}
	
	/**
	 * 修改盲板抽堵作业信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(ZTAQGL_Mbcdzy entity, Model model){
		String datasuccess="success";	
		ZTAQGL_Mbcdzy mbcd = aqglmbcdService.findById(entity.getID());
		mbcd.setM1(entity.getM1());
		mbcd.setM2(entity.getM2());
		mbcd.setM3(entity.getM3());
		mbcd.setM4(entity.getM4());
		mbcd.setM5(entity.getM5());
		mbcd.setM6(entity.getM6());
		mbcd.setM7(entity.getM7());
		mbcd.setM8(entity.getM8());
		mbcd.setM9(entity.getM9());
		mbcd.setM10(entity.getM10());
		mbcd.setM11(entity.getM11());
		mbcd.setM12(entity.getM12());
		mbcd.setM13(entity.getM13());
		mbcd.setM14(entity.getM14());
		mbcd.setM15(entity.getM15());
		mbcd.setM16(entity.getM16());
		mbcd.setM17(entity.getM17());
		mbcd.setM18(entity.getM18());
		mbcd.setM19(entity.getM19());
		mbcd.setM20(entity.getM20());
		mbcd.setM21(entity.getM21());
		mbcd.setM21_1(entity.getM21_1());
		mbcd.setM22(entity.getM22());
		mbcd.setZt("0");
		mbcd.setS2(new Timestamp(new java.util.Date().getTime()));

		aqglmbcdService.updateInfo(mbcd);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  作业安全--盲板抽堵作业  【修改操作】");
		//返回结果
		return datasuccess;
	}

	/**
	 * 撤回盲板抽堵作业
	 * @param request,model
	 */
	@RequestMapping(value = "revoke/{id}")
	@ResponseBody
	public String revoke(@PathVariable("id") Long id,  Model model){
		String datasuccess="success";
		ZTAQGL_Mbcdzy mbcd = aqglmbcdService.findById(id);
		mbcd.setZt("0");
		mbcd.setS2(new Timestamp(new java.util.Date().getTime()));

		aqglmbcdService.updateInfo(mbcd);
		//返回结果
		return datasuccess;
	}

	/**
	 * 作废盲板抽堵作业
	 * @param request,model
	 */
	@RequestMapping(value = "cancel/{id}")
	@ResponseBody
	public String cancel(@PathVariable("id") Long id,  Model model){
		String datasuccess="操作成功";
		ZTAQGL_Mbcdzy mbcd = aqglmbcdService.findById(id);
		mbcd.setZt("-2");
		mbcd.setS2(new Timestamp(new java.util.Date().getTime()));
		
		aqglmbcdService.updateInfo(mbcd);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 盲板抽堵作业安全措施列表显示页面
	 * @param model
	 */
	@RequestMapping(value="aqcsindex")
	public String aqcsindex(Model model,HttpServletRequest request) {
		model.addAttribute("id1", request.getParameter("id1"));
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		return "ztzyaqgl/zyaq/mbcd/aqcsindex";
	}

	/**
	 * 盲板抽堵作业安全措施列表显示页面
	 * @param model
	 */
	@RequestMapping(value="updaqcsindex")
	public String updaqcsindex(Model model,HttpServletRequest request) {
		model.addAttribute("id1", request.getParameter("id1"));
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		return "ztzyaqgl/zyaq/mbcd/addaqcsindex";
	}
	
	/**
	 * 盲板抽堵作业安全措施list 
	 * @param request
	 */
	@RequestMapping(value="aqcslist")
	@ResponseBody
	public Map<String, Object> aqcsData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		String type=request.getParameter("type");
		if(type!=null){
			map.put("id1", request.getParameter("id1"));
			map.put("m2", "6");
			return aqglaqcsService.ybzaqscdataGrid(map);	
		}else{
			map.put("m2", "6");
			return aqglaqcsService.wxaqscdataGrid(map);
		}
		
	}

	/**
	 * 外来方选择页面跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "choose")
	public String choose(Model model) {
		return "ztzyaqgl/zyaq/mbcd/index_wlfchoose";
	}
	
	/**
	 * 选择外来方list页面
	 */
	@RequestMapping(value = "wlflist")
	@ResponseBody
	public Map<String, Object> getQyList(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.putAll(getAuthorityMap());
		map.put("m1", request.getParameter("aqzf_xgdw_m1"));
		map.put("m2", request.getParameter("aqzf_xgdw_m2"));
		map.put("m3", request.getParameter("aqzf_xgdw_m3"));
		return aqglxgdwService.dataGrid(map);

	}
	
	/**
	 * 编制安全措施信息
	 * @param request,model
	 */
	@RequestMapping(value="createAqcs", method = RequestMethod.GET)
	@ResponseBody
	public String createAqcs(HttpServletRequest request) {
		String datasuccess="编制成功";
		String listjson=request.getParameter("list");
		String mbid=request.getParameter("mbid");
		String flag=request.getParameter("flag");
		List<ZTAQGL_Zyaq_Aqcs> list =JSON.parseArray(listjson, ZTAQGL_Zyaq_Aqcs.class);
        for(int i=0;i<list.size();i++){
	        try {
	        	ZTAQGL_Zyaq_Aqcs aqsc=list.get(i);
	        	aqglmbcdService.updateAqcs(aqsc);
			} catch (Exception e) {
				// TODO: handle exception
				datasuccess="failed";
			}
        }
        if(flag==null){
			//修改盲板抽堵状态
	        ZTAQGL_Mbcdzy mbcd=aqglmbcdService.find(Long.parseLong(mbid));
	        mbcd.setM29(DateUtils.getSysTimestamp());
	        mbcd.setM29_4(request.getParameter("aqjd"));
	        mbcd.setM29_5(request.getParameter("sgfa"));
	        mbcd.setM29_6(request.getParameter("wlfids"));
	        mbcd.setZt("1");
	        aqglmbcdService.updateInfo(mbcd);
        }
		return datasuccess;
	}

	/**
	 * 作业单位确认页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "zydwqr/{id}", method = RequestMethod.GET)
	public String zydwqr(@PathVariable("id") Long id, Model model) {
		model.addAttribute("mbid", id);
		//返回页面
		model.addAttribute("action", "zydwqr");
		return "ztzyaqgl/zyaq/mbcd/zydwform";
	}	

	/**
	 *  作业单位确认
	 * @param request,model
	 */
	@RequestMapping(value = "zydwqr")
	@ResponseBody
	public String aqbm(long id, String M23, String M23_1, Model model, HttpServletRequest request){
		String datasuccess="success";	
		ZTAQGL_Mbcdzy mbcd = aqglmbcdService.find(id);
		mbcd.setZt("2");//待安技员审批
		mbcd.setM23(M23);
		mbcd.setM23_1(M23_1);
		mbcd.setM23_2(DateUtils.getSysTimestamp());
		mbcd.setM23_3(Long.parseLong(UserUtil.getCurrentShiroUser().getId().toString()));
		aqglmbcdService.updateInfo(mbcd);
		//返回结果
		return datasuccess;
	}

	/**
	 * 内部审批页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "nbsp/{id}", method = RequestMethod.GET)
	public String nbsp(@PathVariable("id") Long id, Model model) {
		model.addAttribute("mbid", id);
		//返回页面
		model.addAttribute("action", "nbsp");
		return "ztzyaqgl/zyaq/mbcd/nbspform";
	}	

	/**
	 *  内部审批
	 * @param request,model
	 */
	@RequestMapping(value = "nbsp")
	@ResponseBody
	public String nbsp(long id, String suggest, String spflag,Model model, HttpServletRequest request){
		String datasuccess="success";	
		ZTAQGL_Mbcdzy mbcd = aqglmbcdService.find(id);
		Timestamp t= DateUtils.getSysTimestamp();//当前时间
		String zt=mbcd.getZt();//当前状态
		Long userid=Long.parseLong(UserUtil.getCurrentShiroUser().getId()+"");//用户时间
		String signpic= UserUtil.getCurrentShiroUser().getElecsignature();//用户的电子签章
		
			if(zt.equals("2")){//安技员审批
				if(spflag.equals("通过"))
					mbcd.setZt("3");//待部门一把手审批
				else
					mbcd.setZt("-1");//退回编制人修改
				mbcd.setM24(suggest);//安技员意见
				mbcd.setM24_1(signpic);//签名图片
				mbcd.setM24_2(t);//审批时间
				mbcd.setM24_3(userid);//添加人id
				mbcd.setM24_4(spflag);//状态
			}
			
			if(zt.equals("3")){//部门一把手审批
				if(spflag.equals("通过"))
					mbcd.setZt("4");//安全处分管领导
				else
					mbcd.setZt("-1");//退回编制人修改
				mbcd.setM25(request.getParameter("suggest").toString());//部门一把手意见
				mbcd.setM25_1(signpic);//签名图片
				mbcd.setM25_2(t);//审批时间
				mbcd.setM25_3(userid);//添加人id
				mbcd.setM25_4(spflag);//状态
			}			

			if(zt.equals("4")){//安全处分管领导签字
				if(spflag.equals("通过"))
					mbcd.setZt("5");//待保卫部审批
				else
					mbcd.setZt("-1");//退回编制人修改
				mbcd.setM26(suggest);//安全处分管领导意见
				mbcd.setM26_1(signpic);//签名图片
				mbcd.setM26_2(t);//审批时间
				mbcd.setM26_3(userid);//添加人id
				mbcd.setM26_4(spflag);//状态
			}
			
			if(zt.equals("5")){//保卫部签字
				if(spflag.equals("通过"))
					mbcd.setZt("6");//待公司分管领导审批
				else
					mbcd.setZt("-1");//退回编制人修改
				mbcd.setM27(suggest);//保卫部意见
				mbcd.setM27_1(signpic);//签名图片
				mbcd.setM27_2(t);//审批时间
				mbcd.setM27_3(userid);//添加人id
				mbcd.setM27_4(spflag);//状态
			}	
			
			if(zt.equals("6")){//公司分管领导签字
				if(spflag.equals("通过"))
					mbcd.setZt("7");//待确认措施
				else
					mbcd.setZt("-1");//退回编制人修改
				mbcd.setM28(suggest);//公司分管领导意见
				mbcd.setM28_1(signpic);//签名图片
				mbcd.setM28_2(t);//审批时间
				mbcd.setM28_3(userid);//添加人id
				mbcd.setM28_4(spflag);//状态
			}				
		aqglmbcdService.updateInfo(mbcd);
		//返回结果
		return datasuccess;
	}

	/**
	 * 盲板抽堵作业确认安全措施列表显示页面
	 * @param model
	 */
	@RequestMapping(value="qraqcsindex")
	public String qraqcsindex(Model model,HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("id1", request.getParameter("id1"));
		map.put("m2", 6);
		model.addAttribute("id1", request.getParameter("id1"));
		model.addAttribute("action", "csqr");
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		model.addAttribute("aqcslist", JsonMapper.getInstance().toJson(aqglaqcsService.ybzaqsc(map)));
		return "ztzyaqgl/zyaq/mbcd/qrcsform";
	}

	/**
	 *  确认措施
	 * @param request,model
	 */
	@RequestMapping(value = "csqr")
	@ResponseBody
	public String csqr(HttpServletRequest request){
		String datasuccess="success";
		String[] csid=request.getParameterValues("csid");	
		for(int i=0;i<csid.length;i++){
			String m3=request.getParameter("cz_"+csid[i]);
			String[] m4=request.getParameterValues("xczp"+csid[i]);
			String xcphoto= StringUtils.join(m4,",");
        	ZTAQGL_Zyaq_Aqcs aqcs=aqglmbcdService.findAqcs(Long.parseLong(csid[i]));
        	aqcs.setM3(m3);
        	aqcs.setM4(xcphoto);
        	aqglmbcdService.updateAqcs(aqcs);        	
		}
		//修改盲板抽堵状态
		String mbid=request.getParameter("mbid");
        ZTAQGL_Mbcdzy mbcd=aqglmbcdService.find(Long.parseLong(mbid));
        mbcd.setM29_7(DateUtils.getSysTimestamp());
        mbcd.setM29_2(request.getParameter("signpic"));
        mbcd.setM29_3(UserUtil.getCurrentShiroUser().getElecsignature());//许可人电子签章
        mbcd.setZt("8");//待作业单位完工签字
        aqglmbcdService.updateInfo(mbcd);
		//返回结果
		return datasuccess;
	}

	/**
	 * 盲板抽堵作业完工签字显示页面
	 * @param model
	 */
	@RequestMapping(value="wgqzindex")
	public String wgqzindex(Model model,HttpServletRequest request) {
		Timestamp t= DateUtils.getSysTimestamp();//确认时间
		model.addAttribute("wgsj", t);
		model.addAttribute("action", "wgqz");
		model.addAttribute("mbid", request.getParameter("id1"));
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		return "ztzyaqgl/zyaq/mbcd/wgqzform";
	}
	
	//完工签字
	@RequestMapping(value="wgqz")
	@ResponseBody
	public String wgqz(HttpServletRequest request){
		String datasuccess="success";
		ZTAQGL_Mbcdzy mbcd=aqglmbcdService.find(Long.parseLong(request.getParameter("mbid")));
		Timestamp t= DateUtils.getSysTimestamp();//确认时间
		String signpic=request.getParameter("signpic").toString();//签名图片
		Long userid=Long.parseLong(UserUtil.getCurrentShiroUser().getId()+"");
		if(mbcd.getZt().equals("8")){
			mbcd.setZt("9");//待分厂完工签字
			mbcd.setM30(t);//作业单位确认时间
			mbcd.setM30_1(signpic);//签名图片
			mbcd.setM30_2(userid);//添加人id
		}else if(mbcd.getZt().equals("9")){
			mbcd.setZt("10");//完工
			mbcd.setM31(t);//分厂完工确认时间
			mbcd.setM31_1(signpic);//分厂完工图片
			mbcd.setM31_2(userid);//添加人id
		}
		aqglmbcdService.updateInfo(mbcd);//修改盲板抽堵作业
		return datasuccess;
	}
	
	/**
	 * 删除盲板抽堵作业信息
	 */
	@RequiresPermissions("zyaqgl:mbcd:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  作业安全--盲板抽堵作业  【删除操作】,删除的id是:"+arrids[i]);
			aqglmbcdService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String,Object> mbcd = aqglmbcdService.findallById(id);
		if(mbcd.get("m29_6")!=null){
			List<Map<String,Object>> wlf = aqglmbcdService.findallWlfIds(mbcd.get("m29_6").toString()+"0");
			model.addAttribute("wlflist", JsonMapper.getInstance().toJson(wlf));
		}
		model.addAttribute("mbcd", mbcd);
		return "ztzyaqgl/zyaq/mbcd/view";
	}
	
	/**
	 * 查看状态页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "viewzt/{id}", method = RequestMethod.GET)
	public String viewzt(@PathVariable("id") Long id, Model model) {
		Map<String,Object> mbcd = aqglmbcdService.findallById(id);
		model.addAttribute("mbcd", mbcd);
		return "ztzyaqgl/zyaq/mbcd/viewzt";
	}
	
	/**
	 * 显示所有列
	 * 
	 * @param id,model
	 */
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","ztzyaqgl/mbcd/export");
		return "/common/formexcel";
	}
	
	/**
	 * 导出excel
	 * 
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyname", request.getParameter("zyaqgl_bgsq_qy_name"));//企业名
		map.putAll(getAuthorityMap());
		map.put("m0", request.getParameter("zyaq_sxzy_m0"));
		map.put("m3", request.getParameter("zyaq_sxzy_m3"));
		map.put("zt", request.getParameter("zyaq_sxzy_zt"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		aqglmbcdService.exportExcel(response, map);
	}
	
	/**
	 * 导出盲板抽堵作业word
	 */
	@RequestMapping(value = "exportword/{id}")
	@ResponseBody
	public String getexportword(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		String webAddress= request.getSession().getServletContext().getRealPath("/").replace(request.getContextPath().replace("/","\\"),"");
		Map<String, Object> map = aqglmbcdService.getExportWord(id,webAddress);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "mbcd.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
}
