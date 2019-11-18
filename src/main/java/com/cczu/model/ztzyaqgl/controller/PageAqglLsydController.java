package com.cczu.model.ztzyaqgl.controller;

import com.alibaba.fastjson.JSON;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.model.ztzyaqgl.entity.ZTAQGL_Lsydzy;
import com.cczu.model.ztzyaqgl.service.AqglLsydService;
import com.cczu.model.ztzyaqgl.entity.ZTAQGL_Zyaq_Aqcs;
import com.cczu.model.ztzyaqgl.service.AqglAqcsService;
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
 * 安全生产-临时用电作业Controller
 * @author YZH
 */
@Controller
@RequestMapping("ztzyaqgl/lsyd")
public class PageAqglLsydController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private AqglLsydService aqgllsydService;
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
		return "ztzyaqgl/zyaq/lsyd/index";
	}
	
	/**
	 * 数据list 
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("m1", request.getParameter("aqgl_lsyd_cx_m1"));//作业证编号
		map.put("m2", request.getParameter("aqgl_lsyd_cx_m2"));//作业单位
		map.put("m10", request.getParameter("aqgl_lsyd_cx_m10"));//临时用电作业级别
		map.put("zt", request.getParameter("aqgl_lsyd_cx_zt"));//临时用电作业状态

		Subject subject = SecurityUtils.getSubject();
		if(!subject.isPermitted("zyaqgl:gczy:viewall")) {
			if (!subject.isPermitted("zyaqgl:dhzy:nyzxsp")) {//是否拥有能控中心审批权限
				map.putAll(getAuthorityMap());
			} else {
				map.put("zt2", "1");//临时用电作业状态
			}
		}

		//如果选择了部门下拉，则覆盖前面的部门条件，但也仅限于该用户所能看到的部门范围
		String depid=request.getParameter("aqgl_lsyd_cx_depid");
		if(depid!=null&&(!depid.equals(""))){
			Department dep=departmentService.get(Long.parseLong(depid));
			map.put("depcode3", dep.getCode());
		}
		map.put("dspzt", request.getParameter("spzt"));//当前审批人待审批的状态
		return aqgllsydService.dataGrid(map);	
		
	}

	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("zyaqgl:lsyd:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model, HttpServletRequest request) {
		String code="LSYD-"+ DateUtils.getDateRandom();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("m1", code);
		map.put("bzr", UserUtil.getCurrentUser().getName());//编制人
		model.addAttribute("lsyd", map);
		model.addAttribute("action", "create");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "ztzyaqgl/zyaq/lsyd/form";
	}

	/**
	 * 添加临时用电作业信息
	 * @param request,model
	 */
	@RequiresPermissions("zyaqgl:lsyd:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(ZTAQGL_Lsydzy entity, Model model) {
		String datasuccess = "success";
		Timestamp t= DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		entity.setZt("0");//添加初始状态
		entity.setID1(UserUtil.getCurrentShiroUser().getQyid());
		//申请人id
		entity.setM22_1(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));
		//添加临时用电作业
		aqgllsydService.addInfo(entity);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  作业安全--临时用电作业  【添加操作】");
		// 返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的临时用电作业信息
		Map<String, Object> lsyd = aqgllsydService.findInforById(id);
		model.addAttribute("lsyd", lsyd);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "ztzyaqgl/zyaq/lsyd/form";
	}
	
	/**
	 * 修改临时用电作业信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(ZTAQGL_Lsydzy entity, Model model){
		String datasuccess="success";	
		ZTAQGL_Lsydzy lsyd = aqgllsydService.findById(entity.getID());
		lsyd.setM1(entity.getM1());
		lsyd.setM2(entity.getM2());
		lsyd.setM3(entity.getM3());
		lsyd.setM4(entity.getM4());
		lsyd.setM5(entity.getM5());
		lsyd.setM6(entity.getM6());
		lsyd.setM7(entity.getM7());
		lsyd.setM8(entity.getM8());
		lsyd.setM9(entity.getM9());
		lsyd.setM10(entity.getM10());
		lsyd.setM11(entity.getM11());
		lsyd.setM12(entity.getM12());
		lsyd.setM13(entity.getM13());
		lsyd.setM14(entity.getM14());
		lsyd.setM15(entity.getM15());
		lsyd.setM15_1(entity.getM15_1());
		lsyd.setZt("0");
		lsyd.setS2(new Timestamp(new java.util.Date().getTime()));

		aqgllsydService.updateInfo(lsyd);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  作业安全--临时用电作业  【修改操作】");
		//返回结果
		return datasuccess;
	}

	/**
	 * 撤回临时用电作业
	 * @param request,model
	 */
	@RequestMapping(value = "revoke/{id}")
	@ResponseBody
	public String revoke(@PathVariable("id") Long id,  Model model){
		String datasuccess="success";
		ZTAQGL_Lsydzy lsyd = aqgllsydService.findById(id);
		lsyd.setZt("0");
		lsyd.setS2(new Timestamp(new java.util.Date().getTime()));

		aqgllsydService.updateInfo(lsyd);
		//返回结果
		return datasuccess;
	}

	/**
	 * 作废临时用电作业
	 * @param request,model
	 */
	@RequestMapping(value = "cancel/{id}")
	@ResponseBody
	public String cancel(@PathVariable("id") Long id,  Model model){
		String datasuccess="操作成功";
		ZTAQGL_Lsydzy lsyd = aqgllsydService.findById(id);
		lsyd.setZt("-2");
		lsyd.setS2(new Timestamp(new java.util.Date().getTime()));
		
		aqgllsydService.updateInfo(lsyd);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 临时用电作业安全措施列表显示页面
	 * @param model
	 */
	@RequestMapping(value="aqcsindex")
	public String aqcsindex(Model model,HttpServletRequest request) {
		model.addAttribute("id1", request.getParameter("id1"));
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		return "ztzyaqgl/zyaq/lsyd/aqcsindex";
	}

	/**
	 * 临时用电作业安全措施列表显示页面
	 * @param model
	 */
	@RequestMapping(value="updaqcsindex")
	public String updaqcsindex(Model model,HttpServletRequest request) {
		model.addAttribute("id1", request.getParameter("id1"));
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		return "ztzyaqgl/zyaq/lsyd/addaqcsindex";
	}
	
	/**
	 * 临时用电作业安全措施list 
	 * @param request
	 */
	@RequestMapping(value="aqcslist")
	@ResponseBody
	public Map<String, Object> aqcsData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		String type=request.getParameter("type");
		if(type!=null){
			map.put("id1", request.getParameter("id1"));
			map.put("m2", "4");
			return aqglaqcsService.ybzaqscdataGrid(map);	
		}else{
			map.put("m2", "4");
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
		return "ztzyaqgl/zyaq/lsyd/index_wlfchoose";
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
		String lsid=request.getParameter("lsid");
		String flag=request.getParameter("flag");
		List<ZTAQGL_Zyaq_Aqcs> list =JSON.parseArray(listjson, ZTAQGL_Zyaq_Aqcs.class);
        for(int i=0;i<list.size();i++){
	        try {
	        	ZTAQGL_Zyaq_Aqcs aqsc=list.get(i);
	        	aqgllsydService.updateAqcs(aqsc);
			} catch (Exception e) {
				// TODO: handle exception
				datasuccess="failed";
			}
        }
        if(flag==null){
			//修改临时用电状态
	        ZTAQGL_Lsydzy lsyd=aqgllsydService.find(Long.parseLong(lsid));
	        lsyd.setM22(DateUtils.getSysTimestamp());
	        lsyd.setM22_4(request.getParameter("aqjd"));
	        lsyd.setM22_5(request.getParameter("sgfa"));
	        lsyd.setM22_6(request.getParameter("wlfids"));
	        lsyd.setZt("1");
	        aqgllsydService.updateInfo(lsyd);
        }
		return datasuccess;
	}

	/**
	 * 作业单位确认页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "zydwqr/{id}", method = RequestMethod.GET)
	public String zydwqr(@PathVariable("id") Long id, Model model) {
		model.addAttribute("lsydid", id);
		//返回页面
		model.addAttribute("action", "zydwqr");
		return "ztzyaqgl/zyaq/lsyd/zydwform";
	}	

	/**
	 *  作业单位确认
	 * @param request,model
	 */
	@RequestMapping(value = "zydwqr")
	@ResponseBody
	public String aqbm(long id, String M16, String M16_1, Model model, HttpServletRequest request){
		String datasuccess="success";	
		ZTAQGL_Lsydzy lsyd = aqgllsydService.find(id);
		lsyd.setZt("2");//待安技员审批
		lsyd.setM16(M16);
		lsyd.setM16_1(M16_1);
		lsyd.setM16_2(DateUtils.getSysTimestamp());
		lsyd.setM16_3(Long.parseLong(UserUtil.getCurrentShiroUser().getId().toString()));
		aqgllsydService.updateInfo(lsyd);
		//返回结果
		return datasuccess;
	}

	/**
	 * 内部审批页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "nbsp/{id}", method = RequestMethod.GET)
	public String nbsp(@PathVariable("id") Long id, Model model) {
		model.addAttribute("lsydid", id);
		//返回页面
		model.addAttribute("action", "nbsp");
		return "ztzyaqgl/zyaq/lsyd/nbspform";
	}	

	/**
	 *  内部审批
	 * @param request,model
	 */
	@RequestMapping(value = "nbsp")
	@ResponseBody
	public String nbsp(long id, String suggest, String spflag,Model model, HttpServletRequest request){
		String datasuccess="success";	
		ZTAQGL_Lsydzy lsyd = aqgllsydService.find(id);
		Timestamp t= DateUtils.getSysTimestamp();//当前时间
		String zt=lsyd.getZt();//当前状态
		Long userid=Long.parseLong(UserUtil.getCurrentShiroUser().getId()+"");//用户时间
		String signpic= UserUtil.getCurrentShiroUser().getElecsignature();//用户的电子签章

			if(zt.equals("2")){//安技员审批
				if(spflag.equals("通过"))
					lsyd.setZt("3");//待安全科长审批
				else
					lsyd.setZt("-1");//退回编制人修改
				lsyd.setM18(suggest);//安技员意见
				lsyd.setM18_1(signpic);//签名图片
				lsyd.setM18_2(t);//审批时间
				lsyd.setM18_3(userid);//添加人id
				lsyd.setM18_4(spflag);//状态
			}
			
			if(zt.equals("3")){//安全科长审批
				if(spflag.equals("通过"))
					lsyd.setZt("4");//待条线分管领导
				else
					lsyd.setZt("-1");//退回编制人修改
				lsyd.setM19(request.getParameter("suggest").toString());//安全科长意见
				lsyd.setM19_1(signpic);//签名图片
				lsyd.setM19_2(t);//审批时间
				lsyd.setM19_3(userid);//添加人id
				lsyd.setM19_4(spflag);//状态
			}			

			if(!lsyd.getM10().equals("涉及变配电所")){
				if(zt.equals("4")){//条线分管领导签字
					if(spflag.equals("通过"))
						lsyd.setZt("6");//待确认安全措施
					else
						lsyd.setZt("-1");//退回编制人修改
					lsyd.setM20(suggest);//安全科长意见
					lsyd.setM20_1(signpic);//签名图片
					lsyd.setM20_2(t);//审批时间
					lsyd.setM20_3(userid);//添加人id
					lsyd.setM20_4(spflag);//状态
				}
			}else{//四级风险
				if(zt.equals("4")){//条线分管领导签字
					if(spflag.equals("通过"))
						lsyd.setZt("5");//待能源管控中心签字
					else
						lsyd.setZt("-1");//退回编制人修改
					lsyd.setM20(suggest);//安技员意见
					lsyd.setM20_1(signpic);//签名图片
					lsyd.setM20_2(t);//审批时间
					lsyd.setM20_3(userid);//添加人id
					lsyd.setM20_4(spflag);//状态
				}	
				
				if(zt.equals("5")){//能源管控中心签字
					if(spflag.equals("通过"))
						lsyd.setZt("6");//待确认安全措施
					else
						lsyd.setZt("-1");//退回编制人修改
					lsyd.setM21(suggest);//安全科长意见
					lsyd.setM21_1(signpic);//签名图片
					lsyd.setM21_2(t);//审批时间
					lsyd.setM21_3(userid);//添加人id
					lsyd.setM21_4(spflag);//状态
				}	
			}
		aqgllsydService.updateInfo(lsyd);
		//返回结果
		return datasuccess;
	}

	/**
	 * 临时用电作业确认安全措施列表显示页面
	 * @param model
	 */
	@RequestMapping(value="qraqcsindex")
	public String qraqcsindex(Model model,HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("id1", request.getParameter("id1"));
		map.put("m2", 4);
		model.addAttribute("id1", request.getParameter("id1"));
		model.addAttribute("action", "csqr");
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		model.addAttribute("aqcslist", JsonMapper.getInstance().toJson(aqglaqcsService.ybzaqsc(map)));
		return "ztzyaqgl/zyaq/lsyd/qrcsform";
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
        	ZTAQGL_Zyaq_Aqcs aqcs=aqgllsydService.findAqcs(Long.parseLong(csid[i]));
        	aqcs.setM3(m3);
        	aqcs.setM4(xcphoto);
        	aqgllsydService.updateAqcs(aqcs);        	
		}
		//修改临时用电状态
		String lsid=request.getParameter("lsid");
        ZTAQGL_Lsydzy lsyd=aqgllsydService.find(Long.parseLong(lsid));
        lsyd.setM22_7(DateUtils.getSysTimestamp());
        lsyd.setM22_2(request.getParameter("signpic"));
        lsyd.setM22_3(UserUtil.getCurrentShiroUser().getElecsignature());//许可人电子签章
        lsyd.setZt("7");//待作业单位完工签字
        aqgllsydService.updateInfo(lsyd);
		//返回结果
		return datasuccess;
	}

	/**
	 * 临时用电作业完工签字显示页面
	 * @param model
	 */
	@RequestMapping(value="wgqzindex")
	public String wgqzindex(Model model,HttpServletRequest request) {
		Timestamp t= DateUtils.getSysTimestamp();//确认时间
		model.addAttribute("wgsj", t);
		model.addAttribute("action", "wgqz");
		model.addAttribute("lsid", request.getParameter("id1"));
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		return "ztzyaqgl/zyaq/lsyd/wgqzform";
	}
	
	//完工签字
	@RequestMapping(value="wgqz")
	@ResponseBody
	public String wgqz(HttpServletRequest request){
		String datasuccess="success";
		ZTAQGL_Lsydzy lsyd=aqgllsydService.find(Long.parseLong(request.getParameter("lsid")));
		Timestamp t= DateUtils.getSysTimestamp();//确认时间
		String signpic=request.getParameter("signpic").toString();//签名图片
		Long userid=Long.parseLong(UserUtil.getCurrentShiroUser().getId()+"");
		if(lsyd.getZt().equals("7")){
			lsyd.setZt("8");//待分厂完工签字
			lsyd.setM23(t);//作业单位确认时间
			lsyd.setM23_1(signpic);//签名图片
			lsyd.setM23_2(userid);//添加人id
		}else if(lsyd.getZt().equals("8")){
			if(lsyd.getM10().equals("涉及变配电所"))
				lsyd.setZt("9");//待能控中心完工确认
			else
				lsyd.setZt("10");//完工
			lsyd.setM24(t);//分厂完工确认时间
			lsyd.setM24_1(signpic);//分厂完工图片
			lsyd.setM24_2(userid);//添加人id
		}else if(lsyd.getZt().equals("9")){
			lsyd.setZt("10");//完工
			lsyd.setM25(t);//能控中心完工确认时间
			lsyd.setM25_1(signpic);//看你最想完工图片
			lsyd.setM25_2(userid);//添加人id
		}
		aqgllsydService.updateInfo(lsyd);//修改临时用电作业
		return datasuccess;
	}
	
	/**
	 * 删除临时用电作业信息
	 */
	@RequiresPermissions("zyaqgl:lsyd:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  作业安全--临时用电作业  【删除操作】 ,删除的id是:"+arrids[i]);
			aqgllsydService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String,Object> lsyd = aqgllsydService.findallById(id);
		if(lsyd.get("m22_6")!=null){
			List<Map<String,Object>> wlf = aqgllsydService.findallWlfIds(lsyd.get("m22_6").toString()+"0");
			model.addAttribute("wlflist", JsonMapper.getInstance().toJson(wlf));
		}
		model.addAttribute("lsyd", lsyd);
		return "ztzyaqgl/zyaq/lsyd/view";
	}
	
	/**
	 * 查看状态页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "viewzt/{id}", method = RequestMethod.GET)
	public String viewzt(@PathVariable("id") Long id, Model model) {
		Map<String,Object> lsyd = aqgllsydService.findallById(id);
		model.addAttribute("lsyd", lsyd);
		return "ztzyaqgl/zyaq/lsyd/viewzt";
	}
	
	/**
	 * 显示所有列
	 * 
	 * @param id,model
	 */
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","ztzyaqgl/lsyd/export");
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
		aqgllsydService.exportExcel(response, map);
	}
	
	/**
	 * 导出临时用电作业word
	 */
	@RequestMapping(value = "exportword/{id}")
	@ResponseBody
	public String getexportword(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		String webAddress= request.getSession().getServletContext().getRealPath("/").replace(request.getContextPath().replace("/","\\"),"");
		Map<String, Object> map = aqgllsydService.getExportWord(id,webAddress);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "lsyd.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
}
