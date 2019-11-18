package com.cczu.model.ztzyaqgl.controller;

import com.alibaba.fastjson.JSON;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.model.ztzyaqgl.entity.ZTAQGL_Dtzy;
import com.cczu.model.ztzyaqgl.entity.ZTAQGL_Zyaq_Aqcs;
import com.cczu.model.ztzyaqgl.service.AqglAqcsService;
import com.cczu.model.ztzyaqgl.service.AqglDtzyService;
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
 * 安全生产-动土作业Controller
 * @author YZH
 */
@Controller
@RequestMapping("ztzyaqgl/dtzy")
public class AqglDtzyController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private AqglDtzyService aqgldtzyService;
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
		return "ztzyaqgl/zyaq/dtzy/index";
	}
	
	/**
	 * 数据list 
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("m1", request.getParameter("aqgl_dtzy_cx_m1"));//作业证编号
		map.put("m2", request.getParameter("aqgl_dtzy_cx_m2"));//作业单位
		map.put("m10", request.getParameter("aqgl_dtzy_cx_m10"));//动土作业级别
		map.put("zt", request.getParameter("aqgl_dtzy_cx_zt"));//动土作业状态
        map.putAll(getAuthorityMap());

		//如果选择了部门下拉，则覆盖前面的部门条件，但也仅限于该用户所能看到的部门范围
		String depid=request.getParameter("aqgl_dtzy_cx_depid");
		if(depid!=null&&(!depid.equals(""))){
			Department dep=departmentService.get(Long.parseLong(depid));
			map.put("depcode3", dep.getCode());
		}

		Subject subject = SecurityUtils.getSubject();
		if(!subject.isPermitted("zyaqgl:gczy:viewall")) {
			if (subject.isPermitted("zyaqgl:dhzy:nyzxsp")) {//是否拥有能控中心审批权限
				map.put("depcode2", "");
				map.put("zt2", "2");//临时用电作业状态
			}
			if (subject.isPermitted("zyaqgl:dtzy:sbcsp")) {//是否拥有设备处审批权限
				map.put("depcode2", "");
				map.put("zt2", "4");//临时用电作业状态
			}
			if (subject.isPermitted("zyaqgl:dhzy:bwcsp")) {//是否拥有保卫处审批权限
				map.put("depcode2", "");
				map.put("zt2", "5");//临时用电作业状态
			}
		}
		map.put("dspzt", request.getParameter("spzt"));//当前审批人待审批的状态
		return aqgldtzyService.dataGrid(map);
		
	}

	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("zyaqgl:dtzy:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model, HttpServletRequest request) {
		String code="DTZY-"+ DateUtils.getDateRandom();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("m1", code);
		map.put("bzr", UserUtil.getCurrentUser().getName());//编制人
		model.addAttribute("dtzy", map);
		model.addAttribute("action", "create");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "ztzyaqgl/zyaq/dtzy/form";
	}

	/**
	 * 添加动土作业信息
	 * @param request,model
	 */
	@RequiresPermissions("zyaqgl:dtzy:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(ZTAQGL_Dtzy entity, Model model) {
		String datasuccess = "success";
		Timestamp t= DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		entity.setZt("0");//添加初始状态
		entity.setID1(UserUtil.getCurrentShiroUser().getQyid());
		//申请人id
		entity.setM19_1(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));
		//添加动土作业
		aqgldtzyService.addInfo(entity);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  作业安全--动土作业  【添加操作】");
		// 返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的动土作业信息
		Map<String, Object> dtzy = aqgldtzyService.findInforById(id);
		model.addAttribute("dtzy", dtzy);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "ztzyaqgl/zyaq/dtzy/form";
	}
	
	/**
	 * 修改动土作业信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(ZTAQGL_Dtzy entity, Model model){
		String datasuccess="success";	
		ZTAQGL_Dtzy dtzy = aqgldtzyService.findById(entity.getID());
		dtzy.setM1(entity.getM1());
		dtzy.setM2(entity.getM2());
		dtzy.setM3(entity.getM3());
		dtzy.setM4(entity.getM4());
		dtzy.setM5(entity.getM5());
		dtzy.setM6(entity.getM6());
		dtzy.setM7(entity.getM7());
		dtzy.setM8(entity.getM8());
		dtzy.setM9(entity.getM9());
		dtzy.setM10(entity.getM10());
		dtzy.setM10_1(entity.getM10_1());
		dtzy.setM11(entity.getM11());
		dtzy.setM11_1(entity.getM11_1());
		dtzy.setM12(entity.getM12());
		dtzy.setM12_1(entity.getM12_1());
		dtzy.setZt("0");
		dtzy.setS2(new Timestamp(new java.util.Date().getTime()));

		aqgldtzyService.updateInfo(dtzy);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  作业安全--动土作业  【修改操作】");
		//返回结果
		return datasuccess;
	}

	/**
	 * 撤回动土作业
	 * @param request,model
	 */
	@RequestMapping(value = "revoke/{id}")
	@ResponseBody
	public String revoke(@PathVariable("id") Long id,  Model model){
		String datasuccess="success";
		ZTAQGL_Dtzy dtzy = aqgldtzyService.findById(id);
		dtzy.setZt("0");
		dtzy.setS2(new Timestamp(new java.util.Date().getTime()));

		aqgldtzyService.updateInfo(dtzy);
		//返回结果
		return datasuccess;
	}

	/**
	 * 作废动土作业
	 * @param request,model
	 */
	@RequestMapping(value = "cancel/{id}")
	@ResponseBody
	public String cancel(@PathVariable("id") Long id,  Model model){
		String datasuccess="操作成功";
		ZTAQGL_Dtzy dtzy = aqgldtzyService.findById(id);
		dtzy.setZt("-2");
		dtzy.setS2(new Timestamp(new java.util.Date().getTime()));
		
		aqgldtzyService.updateInfo(dtzy);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 动土作业安全措施列表显示页面
	 * @param model
	 */
	@RequestMapping(value="aqcsindex")
	public String aqcsindex(Model model,HttpServletRequest request) {
		model.addAttribute("id1", request.getParameter("id1"));
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		return "ztzyaqgl/zyaq/dtzy/aqcsindex";
	}

	/**
	 * 动土作业安全措施列表显示页面
	 * @param model
	 */
	@RequestMapping(value="updaqcsindex")
	public String updaqcsindex(Model model,HttpServletRequest request) {
		model.addAttribute("id1", request.getParameter("id1"));
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		return "ztzyaqgl/zyaq/dtzy/addaqcsindex";
	}
	
	/**
	 * 动土作业安全措施list 
	 * @param request
	 */
	@RequestMapping(value="aqcslist")
	@ResponseBody
	public Map<String, Object> aqcsData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		String type=request.getParameter("type");
		if(type!=null){
			map.put("id1", request.getParameter("id1"));
			map.put("m2", "7");
			return aqglaqcsService.ybzaqscdataGrid(map);	
		}else{
			map.put("m2", "7");
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
		return "ztzyaqgl/zyaq/dtzy/index_wlfchoose";
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
		String dtid=request.getParameter("dtid");
		String flag=request.getParameter("flag");
		List<ZTAQGL_Zyaq_Aqcs> list =JSON.parseArray(listjson, ZTAQGL_Zyaq_Aqcs.class);
        for(int i=0;i<list.size();i++){
	        try {
	        	ZTAQGL_Zyaq_Aqcs aqsc=list.get(i);
	        	aqgldtzyService.updateAqcs(aqsc);
			} catch (Exception e) {
				// TODO: handle exception
				datasuccess="failed";
			}
        }
        if(flag==null){
			//修改动土状态
	        ZTAQGL_Dtzy dtzy=aqgldtzyService.find(Long.parseLong(dtid));
	        dtzy.setM19(DateUtils.getSysTimestamp());
	        dtzy.setM19_4(request.getParameter("aqjd"));
	        dtzy.setM19_5(request.getParameter("sgfa"));
	        dtzy.setM19_6(request.getParameter("wlfids"));
	        dtzy.setZt("1");
	        aqgldtzyService.updateInfo(dtzy);
        }
		return datasuccess;
	}

	/**
	 * 作业单位确认页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "zydwqr/{id}", method = RequestMethod.GET)
	public String zydwqr(@PathVariable("id") Long id, Model model) {
		model.addAttribute("dtzyid", id);
		//返回页面
		model.addAttribute("action", "zydwqr");
		return "ztzyaqgl/zyaq/dtzy/zydwform";
	}	

	/**
	 *  作业单位确认
	 * @param request,model
	 */
	@RequestMapping(value = "zydwqr")
	@ResponseBody
	public String aqbm(long id, String M13, String M13_1, Model model, HttpServletRequest request){
		String datasuccess="success";	
		ZTAQGL_Dtzy dtzy = aqgldtzyService.find(id);
		
		//根据自定义流程查找下个审批部门的状态
		String process=dtzy.getProcess();//审批流程
		String[] lc=process.split(",");
		dtzy.setZt(lc[0]);
		dtzy.setM13(M13);
		dtzy.setM13_1(M13_1);
		dtzy.setM13_2(DateUtils.getSysTimestamp());
		dtzy.setM13_3(Long.parseLong(UserUtil.getCurrentShiroUser().getId().toString()));
		aqgldtzyService.updateInfo(dtzy);
		//返回结果
		return datasuccess;
	}

	/**
	 * 内部审批页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "nbsp/{id}", method = RequestMethod.GET)
	public String nbsp(@PathVariable("id") Long id, Model model) {
		model.addAttribute("dtzyid", id);
		//返回页面
		model.addAttribute("action", "nbsp");
		return "ztzyaqgl/zyaq/dtzy/nbspform";
	}	

	/**
	 *  内部审批
	 * @param request,model
	 */
	@RequestMapping(value = "nbsp")
	@ResponseBody
	public String nbsp(long id, String suggest, String spflag, Model model, HttpServletRequest request){
		String datasuccess="success";	
		ZTAQGL_Dtzy dtzy = aqgldtzyService.find(id);
		Timestamp t= DateUtils.getSysTimestamp();//当前时间
		String zt=dtzy.getZt();//当前状态
		String process=dtzy.getProcess();//审批流程
		Long userid=Long.parseLong(UserUtil.getCurrentShiroUser().getId()+"");//用户时间
		String signpic= UserUtil.getCurrentShiroUser().getElecsignature();//用户的电子签章

		//根据自定义流程查找下个审批部门的状态
		String[] lc=process.split(",");
		int index= StringUtils.getIndex(lc, zt);
		String nextzt="";
		if(index==(lc.length-1))
			nextzt="7";
		else
			nextzt=lc[index+1];
		
		if(zt.equals("2")){//能控中心审批
			if(spflag.equals("通过"))
				dtzy.setZt(nextzt);
			else
				dtzy.setZt("-1");//退回编制人修改
			dtzy.setM14(suggest);//能控中心意见
			dtzy.setM14_1(signpic);//签名图片
			dtzy.setM14_2(t);//审批时间
			dtzy.setM14_3(userid);//添加人id
			dtzy.setM14_4(spflag);//状态
		}
		
		if(zt.equals("3")){//分厂审批
			if(spflag.equals("通过"))
				dtzy.setZt(nextzt);
			else
				dtzy.setZt("-1");//退回编制人修改
			dtzy.setM15(request.getParameter("suggest").toString());//分厂意见
			dtzy.setM15_1(signpic);//签名图片
			dtzy.setM15_2(t);//审批时间
			dtzy.setM15_3(userid);//添加人id
			dtzy.setM15_4(spflag);//状态
		}			

		if(zt.equals("4")){//设备处审批
			if(spflag.equals("通过"))
				dtzy.setZt(nextzt);
			else
				dtzy.setZt("-1");//退回编制人修改
			dtzy.setM16(request.getParameter("suggest").toString());//设备处意见
			dtzy.setM16_1(signpic);//签名图片
			dtzy.setM16_2(t);//审批时间
			dtzy.setM16_3(userid);//添加人id
			dtzy.setM16_4(spflag);//状态
		}
		
		if(zt.equals("5")){//保卫处审批
			if(spflag.equals("通过"))
				dtzy.setZt(nextzt);
			else
				dtzy.setZt("-1");//退回编制人修改
			dtzy.setM17(request.getParameter("suggest").toString());//保卫处意见
			dtzy.setM17_1(signpic);//签名图片
			dtzy.setM17_2(t);//审批时间
			dtzy.setM17_3(userid);//添加人id
			dtzy.setM17_4(spflag);//状态
		}
		
		if(zt.equals("6")){//安全处审批
			if(spflag.equals("通过"))
				dtzy.setZt(nextzt);
			else
				dtzy.setZt("-1");//退回编制人修改
			dtzy.setM18(request.getParameter("suggest").toString());//安全处意见
			dtzy.setM18_1(signpic);//签名图片
			dtzy.setM18_2(t);//审批时间
			dtzy.setM18_3(userid);//添加人id
			dtzy.setM18_4(spflag);//状态
		}
		aqgldtzyService.updateInfo(dtzy);
		//返回结果
		return datasuccess;
	}

	/**
	 * 动土作业确认安全措施列表显示页面
	 * @param model
	 */
	@RequestMapping(value="qraqcsindex")
	public String qraqcsindex(Model model,HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("id1", request.getParameter("id1"));
		map.put("m2", 7);
		model.addAttribute("id1", request.getParameter("id1"));
		model.addAttribute("action", "csqr");
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		model.addAttribute("aqcslist", JsonMapper.getInstance().toJson(aqglaqcsService.ybzaqsc(map)));
		return "ztzyaqgl/zyaq/dtzy/qrcsform";
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
        	ZTAQGL_Zyaq_Aqcs aqcs=aqgldtzyService.findAqcs(Long.parseLong(csid[i]));
        	aqcs.setM3(m3);
        	aqcs.setM4(xcphoto);
        	aqgldtzyService.updateAqcs(aqcs);        	
		}
		//修改动土状态
		String dtid=request.getParameter("dtid");
        ZTAQGL_Dtzy dtzy=aqgldtzyService.find(Long.parseLong(dtid));
        dtzy.setM19_7(DateUtils.getSysTimestamp());
        dtzy.setM19_2(request.getParameter("signpic"));
        dtzy.setM19_3(UserUtil.getCurrentShiroUser().getElecsignature());//许可人电子签章
        dtzy.setZt("8");//待作业单位完工签字
        aqgldtzyService.updateInfo(dtzy);
		//返回结果
		return datasuccess;
	}

	/**
	 * 动土作业完工签字显示页面
	 * @param model
	 */
	@RequestMapping(value="wgqzindex")
	public String wgqzindex(Model model,HttpServletRequest request) {
		Timestamp t= DateUtils.getSysTimestamp();//确认时间
		model.addAttribute("wgsj", t);
		model.addAttribute("action", "wgqz");
		model.addAttribute("dtid", request.getParameter("id1"));
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		return "ztzyaqgl/zyaq/dtzy/wgqzform";
	}
	
	//完工签字
	@RequestMapping(value="wgqz")
	@ResponseBody
	public String wgqz(HttpServletRequest request){
		String datasuccess="success";
		ZTAQGL_Dtzy dtzy=aqgldtzyService.find(Long.parseLong(request.getParameter("dtid")));
		Timestamp t= DateUtils.getSysTimestamp();//确认时间
		String signpic=request.getParameter("signpic").toString();//签名图片
		Long userid=Long.parseLong(UserUtil.getCurrentShiroUser().getId()+"");
		if(dtzy.getZt().equals("8")){
			dtzy.setZt("9");//待分厂完工签字
			dtzy.setM20(t);//作业单位确认时间
			dtzy.setM20_1(signpic);//签名图片
			dtzy.setM20_2(userid);//添加人id
		}else if(dtzy.getZt().equals("9")){
			dtzy.setZt("10");//完工
			dtzy.setM21(t);//分厂完工确认时间
			dtzy.setM21_1(signpic);//分厂完工图片
			dtzy.setM21_2(userid);//添加人id
		}
		aqgldtzyService.updateInfo(dtzy);//修改动土作业
		return datasuccess;
	}
	
	/**
	 * 删除动土作业信息
	 */
	@RequiresPermissions("zyaqgl:dtzy:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  作业安全--动土作业  【删除操作】,删除的id是:"+arrids[i]);
			aqgldtzyService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String,Object> dtzy = aqgldtzyService.findallById(id);
		if(dtzy.get("m19_6")!=null){
			List<Map<String,Object>> wlf = aqgldtzyService.findallWlfIds(dtzy.get("m19_6").toString()+"0");
			model.addAttribute("wlflist", JsonMapper.getInstance().toJson(wlf));
		}
		model.addAttribute("dtzy", dtzy);
		return "ztzyaqgl/zyaq/dtzy/view";
	}
	
	/**
	 * 查看状态页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "viewzt/{id}", method = RequestMethod.GET)
	public String viewzt(@PathVariable("id") Long id, Model model) {
		Map<String,Object> dtzy = aqgldtzyService.findallById(id);
		model.addAttribute("dtzy", dtzy);
		return "ztzyaqgl/zyaq/dtzy/viewzt";
	}
	
	/**
	 * 显示所有列
	 * 
	 * @param id,model
	 */
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","ztzyaqgl/dtzy/export");
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
		aqgldtzyService.exportExcel(response, map);
	}
	
	/**
	 * 导出动土作业word
	 */
	@RequestMapping(value = "exportword/{id}")
	@ResponseBody
	public String getexportword(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		String webAddress= request.getSession().getServletContext().getRealPath("/").replace(request.getContextPath().replace("/","\\"),"");
		Map<String, Object> map = aqgldtzyService.getExportWord(id,webAddress);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "dtzy.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
}
