package com.cczu.model.ztzyaqgl.controller;

import com.alibaba.fastjson.JSON;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.model.ztzyaqgl.entity.ZTAQGL_Dlzy;
import com.cczu.model.ztzyaqgl.entity.ZTAQGL_Zyaq_Aqcs;
import com.cczu.model.ztzyaqgl.service.AqglAqcsService;
import com.cczu.model.ztzyaqgl.service.AqglDlzyService;
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
 * 安全生产-断路作业Controller
 * @author YZH
 */
@Controller
@RequestMapping("ztzyaqgl/dlzy")
public class AqglDlzyController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private AqglDlzyService aqgldlzyService;
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
		return "ztzyaqgl/zyaq/dlzy/index";
	}
	
	/**
	 * 数据list 
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("m1", request.getParameter("aqgl_dlzy_cx_m1"));//作业证编号
		map.put("m2", request.getParameter("aqgl_dlzy_cx_m2"));//作业单位
		map.put("m10", request.getParameter("aqgl_dlzy_cx_m10"));//断路作业级别
		map.put("zt", request.getParameter("aqgl_dlzy_cx_zt"));//断路作业状态
		map.putAll(getAuthorityMap());

		//如果选择了部门下拉，则覆盖前面的部门条件，但也仅限于该用户所能看到的部门范围
		String depid=request.getParameter("aqgl_dlzy_cx_depid");
		if(depid!=null&&(!depid.equals(""))){
			Department dep=departmentService.get(Long.parseLong(depid));
			map.put("depcode3", dep.getCode());
		}

		Subject subject = SecurityUtils.getSubject();
		if(!subject.isPermitted("zyaqgl:gczy:viewall")) {
			if (subject.isPermitted("zyaqgl:dhzy:nyzxsp")) {//是否拥有能控中心审批权限
				map.put("depcode2", "");
				map.put("zt2", "2");//断路作业状态
			}
			if (subject.isPermitted("zyaqgl:dtzy:sbcsp")) {//是否拥有设备处审批权限
				map.put("depcode2", "");
				map.put("zt2", "4");//断路作业状态
			}
			if (subject.isPermitted("zyaqgl:dhzy:bwcsp")) {//是否拥有保卫处审批权限
				map.put("depcode2", "");
				map.put("zt2", "5");//断路作业状态
			}
		}
		map.put("dspzt", request.getParameter("spzt"));//当前审批人待审批的状态
		return aqgldlzyService.dataGrid(map);	
		
	}

	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("zyaqgl:dlzy:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model, HttpServletRequest request) {
		String code="DLZY-"+ DateUtils.getDateRandom();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("m1", code);
		map.put("bzr", UserUtil.getCurrentUser().getName());//编制人
		model.addAttribute("dlzy", map);
		model.addAttribute("action", "create");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "ztzyaqgl/zyaq/dlzy/form";
	}

	/**
	 * 添加断路作业信息
	 * @param request,model
	 */
	@RequiresPermissions("zyaqgl:dlzy:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(ZTAQGL_Dlzy entity, Model model) {
		String datasuccess = "success";
		Timestamp t= DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		entity.setZt("0");//添加初始状态
		entity.setID1(UserUtil.getCurrentShiroUser().getQyid());
		//申请人id
		entity.setM18_1(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));
		//添加断路作业
		aqgldlzyService.addInfo(entity);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  作业安全--断路作业  【添加操作】");
		// 返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的断路作业信息
		Map<String, Object> dlzy = aqgldlzyService.findInforById(id);
		model.addAttribute("dlzy", dlzy);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "ztzyaqgl/zyaq/dlzy/form";
	}
	
	/**
	 * 修改断路作业信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(ZTAQGL_Dlzy entity, Model model){
		String datasuccess="success";	
		ZTAQGL_Dlzy dlzy = aqgldlzyService.findById(entity.getID());
		dlzy.setM1(entity.getM1());
		dlzy.setM2(entity.getM2());
		dlzy.setM3(entity.getM3());
		dlzy.setM4(entity.getM4());
		dlzy.setM5(entity.getM5());
		dlzy.setM6(entity.getM6());
		dlzy.setM7(entity.getM7());
		dlzy.setM8(entity.getM8());
		dlzy.setM9(entity.getM9());
		dlzy.setM10(entity.getM10());
		dlzy.setM10_1(entity.getM10_1());
		dlzy.setM11(entity.getM11());
		dlzy.setM11_1(entity.getM11_1());
		dlzy.setZt("0");
		dlzy.setS2(new Timestamp(new java.util.Date().getTime()));

		aqgldlzyService.updateInfo(dlzy);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  作业安全--断路作业  【修改操作】");
		//返回结果
		return datasuccess;
	}

	/**
	 * 撤回断路作业
	 * @param request,model
	 */
	@RequestMapping(value = "revoke/{id}")
	@ResponseBody
	public String revoke(@PathVariable("id") Long id,  Model model){
		String datasuccess="success";
		ZTAQGL_Dlzy dlzy = aqgldlzyService.findById(id);
		dlzy.setZt("0");
		dlzy.setS2(new Timestamp(new java.util.Date().getTime()));

		aqgldlzyService.updateInfo(dlzy);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  作业安全--断路作业  【撤回操作】");
		//返回结果
		return datasuccess;
	}

	/**
	 * 作废断路作业
	 * @param request,model
	 */
	@RequestMapping(value = "cancel/{id}")
	@ResponseBody
	public String cancel(@PathVariable("id") Long id,  Model model){
		String datasuccess="操作成功";
		ZTAQGL_Dlzy dlzy = aqgldlzyService.findById(id);
		dlzy.setZt("-2");
		dlzy.setS2(new Timestamp(new java.util.Date().getTime()));
		
		aqgldlzyService.updateInfo(dlzy);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 断路作业安全措施列表显示页面
	 * @param model
	 */
	@RequestMapping(value="aqcsindex")
	public String aqcsindex(Model model,HttpServletRequest request) {
		model.addAttribute("id1", request.getParameter("id1"));
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		return "ztzyaqgl/zyaq/dlzy/aqcsindex";
	}

	/**
	 * 断路作业安全措施列表显示页面
	 * @param model
	 */
	@RequestMapping(value="updaqcsindex")
	public String updaqcsindex(Model model,HttpServletRequest request) {
		model.addAttribute("id1", request.getParameter("id1"));
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		return "ztzyaqgl/zyaq/dlzy/addaqcsindex";
	}
	
	/**
	 * 断路作业安全措施list 
	 * @param request
	 */
	@RequestMapping(value="aqcslist")
	@ResponseBody
	public Map<String, Object> aqcsData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		String type=request.getParameter("type");
		if(type!=null){
			map.put("id1", request.getParameter("id1"));
			map.put("m2", "8");
			return aqglaqcsService.ybzaqscdataGrid(map);	
		}else{
			map.put("m2", "8");
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
		return "ztzyaqgl/zyaq/dlzy/index_wlfchoose";
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
		String dlid=request.getParameter("dlid");
		String flag=request.getParameter("flag");
		List<ZTAQGL_Zyaq_Aqcs> list =JSON.parseArray(listjson, ZTAQGL_Zyaq_Aqcs.class);
        for(int i=0;i<list.size();i++){
	        try {
	        	ZTAQGL_Zyaq_Aqcs aqsc=list.get(i);
	        	aqgldlzyService.updateAqcs(aqsc);
			} catch (Exception e) {
				// TODO: handle exception
				datasuccess="failed";
			}
        }
        if(flag==null){
			//修改断路状态
	        ZTAQGL_Dlzy dlzy=aqgldlzyService.find(Long.parseLong(dlid));
	        dlzy.setM18(DateUtils.getSysTimestamp());
	        dlzy.setM18_4(request.getParameter("aqjd"));
	        dlzy.setM18_5(request.getParameter("sgfa"));
	        dlzy.setM18_6(request.getParameter("wlfids"));
	        dlzy.setZt("1");
	        aqgldlzyService.updateInfo(dlzy);
        }
        log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  作业安全--断路作业  【编制措施操作】");
		return datasuccess;
	}

	/**
	 * 作业单位确认页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "zydwqr/{id}", method = RequestMethod.GET)
	public String zydwqr(@PathVariable("id") Long id, Model model) {
		model.addAttribute("dlzyid", id);
		//返回页面
		model.addAttribute("action", "zydwqr");
		return "ztzyaqgl/zyaq/dlzy/zydwform";
	}	

	/**
	 *  作业单位确认
	 * @param request,model
	 */
	@RequestMapping(value = "zydwqr")
	@ResponseBody
	public String aqbm(long id, String M12, String M12_1, Model model, HttpServletRequest request){
		String datasuccess="success";	
		ZTAQGL_Dlzy dlzy = aqgldlzyService.find(id);
		
		//根据自定义流程查找下个审批部门的状态
		String process=dlzy.getProcess();//审批流程
		String[] lc=process.split(",");
		dlzy.setZt(lc[0]);
		dlzy.setM12(M12);
		dlzy.setM12_1(M12_1);
		dlzy.setM12_2(DateUtils.getSysTimestamp());
		dlzy.setM12_3(Long.parseLong(UserUtil.getCurrentShiroUser().getId().toString()));
		aqgldlzyService.updateInfo(dlzy);
		//返回结果
		return datasuccess;
	}

	/**
	 * 内部审批页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "nbsp/{id}", method = RequestMethod.GET)
	public String nbsp(@PathVariable("id") Long id, Model model) {
		model.addAttribute("dlzyid", id);
		//返回页面
		model.addAttribute("action", "nbsp");
		return "ztzyaqgl/zyaq/dlzy/nbspform";
	}	

	/**
	 *  内部审批
	 * @param request,model
	 */
	@RequestMapping(value = "nbsp")
	@ResponseBody
	public String nbsp(long id, String suggest, String spflag,Model model, HttpServletRequest request){
		String datasuccess="success";	
		ZTAQGL_Dlzy dlzy = aqgldlzyService.find(id);
		Timestamp t= DateUtils.getSysTimestamp();//当前时间
		String zt=dlzy.getZt();//当前状态
		String process=dlzy.getProcess();//审批流程
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
				dlzy.setZt(nextzt);
			else
				dlzy.setZt("-1");//退回编制人修改
			dlzy.setM13(suggest);//能控中心意见
			dlzy.setM13_1(signpic);//签名图片
			dlzy.setM13_2(t);//审批时间
			dlzy.setM13_3(userid);//添加人id
			dlzy.setM13_4(spflag);//状态
		}
		
		if(zt.equals("3")){//分厂审批
			if(spflag.equals("通过"))
				dlzy.setZt(nextzt);
			else
				dlzy.setZt("-1");//退回编制人修改
			dlzy.setM14(request.getParameter("suggest").toString());//分厂意见
			dlzy.setM14_1(signpic);//签名图片
			dlzy.setM14_2(t);//审批时间
			dlzy.setM14_3(userid);//添加人id
			dlzy.setM14_4(spflag);//状态
		}			

		if(zt.equals("4")){//设备处审批
			if(spflag.equals("通过"))
				dlzy.setZt(nextzt);
			else
				dlzy.setZt("-1");//退回编制人修改
			dlzy.setM15(request.getParameter("suggest").toString());//设备处意见
			dlzy.setM15_1(signpic);//签名图片
			dlzy.setM15_2(t);//审批时间
			dlzy.setM15_3(userid);//添加人id
			dlzy.setM15_4(spflag);//状态
		}
		
		if(zt.equals("5")){//保卫处审批
			if(spflag.equals("通过"))
				dlzy.setZt(nextzt);
			else
				dlzy.setZt("-1");//退回编制人修改
			dlzy.setM16(request.getParameter("suggest").toString());//保卫处意见
			dlzy.setM16_1(signpic);//签名图片
			dlzy.setM16_2(t);//审批时间
			dlzy.setM16_3(userid);//添加人id
			dlzy.setM16_4(spflag);//状态
		}
		
		if(zt.equals("6")){//安全处审批
			if(spflag.equals("通过"))
				dlzy.setZt(nextzt);
			else
				dlzy.setZt("-1");//退回编制人修改
			dlzy.setM17(request.getParameter("suggest").toString());//安全处意见
			dlzy.setM17_1(signpic);//签名图片
			dlzy.setM17_2(t);//审批时间
			dlzy.setM17_3(userid);//添加人id
			dlzy.setM17_4(spflag);//状态
		}
		aqgldlzyService.updateInfo(dlzy);
		//返回结果
		return datasuccess;
	}

	/**
	 * 断路作业确认安全措施列表显示页面
	 * @param model
	 */
	@RequestMapping(value="qraqcsindex")
	public String qraqcsindex(Model model,HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("id1", request.getParameter("id1"));
		map.put("m2", 8);
		model.addAttribute("id1", request.getParameter("id1"));
		model.addAttribute("action", "csqr");
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		model.addAttribute("aqcslist", JsonMapper.getInstance().toJson(aqglaqcsService.ybzaqsc(map)));
		return "ztzyaqgl/zyaq/dlzy/qrcsform";
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
        	ZTAQGL_Zyaq_Aqcs aqcs=aqgldlzyService.findAqcs(Long.parseLong(csid[i]));
        	aqcs.setM3(m3);
        	aqcs.setM4(xcphoto);
        	aqgldlzyService.updateAqcs(aqcs);        	
		}
		//修改断路状态
		String dlid=request.getParameter("dlid");
        ZTAQGL_Dlzy dlzy=aqgldlzyService.find(Long.parseLong(dlid));
        dlzy.setM18_7(DateUtils.getSysTimestamp());
        dlzy.setM18_2(request.getParameter("signpic"));
        dlzy.setM18_3(UserUtil.getCurrentShiroUser().getElecsignature());//许可人电子签章
        dlzy.setZt("8");//待作业单位完工签字
        aqgldlzyService.updateInfo(dlzy);
		//返回结果
		return datasuccess;
	}

	/**
	 * 断路作业完工签字显示页面
	 * @param model
	 */
	@RequestMapping(value="wgqzindex")
	public String wgqzindex(Model model,HttpServletRequest request) {
		Timestamp t= DateUtils.getSysTimestamp();//确认时间
		model.addAttribute("wgsj", t);
		model.addAttribute("action", "wgqz");
		model.addAttribute("dlid", request.getParameter("id1"));
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		return "ztzyaqgl/zyaq/dlzy/wgqzform";
	}
	
	//完工签字
	@RequestMapping(value="wgqz")
	@ResponseBody
	public String wgqz(HttpServletRequest request){
		String datasuccess="success";
		ZTAQGL_Dlzy dlzy=aqgldlzyService.find(Long.parseLong(request.getParameter("dlid")));
		Timestamp t= DateUtils.getSysTimestamp();//确认时间
		String signpic=request.getParameter("signpic").toString();//签名图片
		Long userid=Long.parseLong(UserUtil.getCurrentShiroUser().getId()+"");
		if(dlzy.getZt().equals("8")){
			dlzy.setZt("9");//待分厂完工签字
			dlzy.setM19(t);//作业单位确认时间
			dlzy.setM19_1(signpic);//签名图片
			dlzy.setM19_2(userid);//添加人id
		}else if(dlzy.getZt().equals("9")){
			dlzy.setZt("10");//完工
			dlzy.setM20(t);//分厂完工确认时间
			dlzy.setM20_1(signpic);//分厂完工图片
			dlzy.setM20_2(userid);//添加人id
		}
		aqgldlzyService.updateInfo(dlzy);//修改断路作业
		return datasuccess;
	}
	
	/**
	 * 删除断路作业信息
	 */
	@RequiresPermissions("zyaqgl:dlzy:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  作业安全--断路作业  【删除操作】,删除的id是:"+arrids[i]);
			aqgldlzyService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String,Object> dlzy = aqgldlzyService.findallById(id);
		if(dlzy.get("m18_6")!=null){
			List<Map<String,Object>> wlf = aqgldlzyService.findallWlfIds(dlzy.get("m18_6").toString()+"0");
			model.addAttribute("wlflist", JsonMapper.getInstance().toJson(wlf));
		}
		model.addAttribute("dlzy", dlzy);
		return "ztzyaqgl/zyaq/dlzy/view";
	}
	
	/**
	 * 查看状态页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "viewzt/{id}", method = RequestMethod.GET)
	public String viewzt(@PathVariable("id") Long id, Model model) {
		Map<String,Object> dlzy = aqgldlzyService.findallById(id);
		model.addAttribute("dlzy", dlzy);
		return "ztzyaqgl/zyaq/dlzy/viewzt";
	}
	
	/**
	 * 显示所有列
	 * 
	 * @param id,model
	 */
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","ztzyaqgl/dlzy/export");
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
		aqgldlzyService.exportExcel(response, map);
	}
	
	/**
	 * 导出断路作业word
	 */
	@RequestMapping(value = "exportword/{id}")
	@ResponseBody
	public String getexportword(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		String webAddress= request.getSession().getServletContext().getRealPath("/").replace(request.getContextPath().replace("/","\\"),"");
		Map<String, Object> map = aqgldlzyService.getExportWord(id,webAddress);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "dlzy.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
}
