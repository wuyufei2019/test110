package com.cczu.model.ztzyaqgl.controller;

import com.alibaba.fastjson.JSON;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.model.ztzyaqgl.entity.ZTAQGL_HighOperation;
import com.cczu.model.ztzyaqgl.entity.ZTAQGL_Zyaq_Aqcs;
import com.cczu.model.ztzyaqgl.service.AqglAqcsService;
import com.cczu.model.ztzyaqgl.service.AqglGczyService;
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
 * 安全生产-高处作业Controller
 * @author YZH
 */
@Controller
@RequestMapping("ztzyaqgl/gczy")
public class PageAqglGczyController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private AqglGczyService aqglGczyService;
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
		return "ztzyaqgl/zyaq/gczy/index";
	}
	
	/**
	 * 数据list 
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("m1", request.getParameter("aqgl_gczy_cx_m1"));//作业证编号
		map.put("m8", request.getParameter("aqgl_gczy_cx_m8"));//作业单位
		map.put("m10", request.getParameter("aqgl_gczy_cx_m10"));//高处作业级别
		map.put("zt", request.getParameter("aqgl_gczy_cx_zt"));//高处作业状态
		map.put("qyname", request.getParameter("zyaqgl_bgsq_qy_name"));//企业名
		map.putAll(getAuthorityMap());

		//如果选择了部门下拉，则覆盖前面的部门条件，但也仅限于该用户所能看到的部门范围
		String depid=request.getParameter("aqgl_gczy_cx_depid");
		if(depid!=null&&(!depid.equals(""))){
			Department dep=departmentService.get(Long.parseLong(depid));
			map.put("depcode3", dep.getCode());
		}

		Subject subject = SecurityUtils.getSubject();
//		if(subject.isPermitted("zyaqgl:gczy:gsldsp")) {//是否拥有公司分管领导审批权限
//			map.put("depcode2", "");
//			map.put("zt2", "7");//动火作业状态
//		}
		map.put("dspzt", request.getParameter("spzt"));//当前审批人待审批的状态
		return aqglGczyService.dataGrid(map);	
		
	}

	/**
	 * 特殊情况list
	 * @param {json}
	 */
	@RequestMapping(value="tsqklist")
	@ResponseBody
	public String tsqklist() {
		return aqglGczyService.tsqklist();
	
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("zyaqgl:gczy:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model, HttpServletRequest request) {
		String code="GCZY-"+ DateUtils.getDateRandom();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("m1", code);
		map.put("bzr", UserUtil.getCurrentUser().getName());//编制人
		model.addAttribute("gczy", map);
		model.addAttribute("action", "create");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "ztzyaqgl/zyaq/gczy/form";
	}

	/**
	 * 添加高处作业信息
	 * @param request,model
	 */
	@RequiresPermissions("zyaqgl:gczy:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(ZTAQGL_HighOperation entity, Model model) {
		String datasuccess = "success";
		Timestamp t= DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		entity.setZt("0");//添加初始状态
		entity.setID1(UserUtil.getCurrentShiroUser().getQyid());
		//申请人id
		entity.setM21_1(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));
		//添加高处作业
		aqglGczyService.addInfo(entity);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  作业安全--高处作业  【添加操作】");
		// 返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的高处作业信息
		Map<String, Object> gczy = aqglGczyService.findInforById(id);
		model.addAttribute("gczy", gczy);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "ztzyaqgl/zyaq/gczy/form";
	}
	
	/**
	 * 修改高处作业信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(ZTAQGL_HighOperation entity, Model model){
		String datasuccess="success";	
		ZTAQGL_HighOperation gczy = aqglGczyService.findById(entity.getID());
		gczy.setM1(entity.getM1());
		gczy.setM2(entity.getM2());
		gczy.setM3(entity.getM3());
		gczy.setM4(entity.getM4());
		gczy.setM5(entity.getM5());
		gczy.setM6(entity.getM6());
		gczy.setM7(entity.getM7());
		gczy.setM8(entity.getM8());
		gczy.setM9(entity.getM9());
		gczy.setM9_1(entity.getM9_1());
		gczy.setM10(entity.getM10());
		gczy.setM11(entity.getM11());
		gczy.setM12(entity.getM12());
		gczy.setM13(entity.getM13());
		gczy.setM14(entity.getM14());
		gczy.setM15(entity.getM15());
		gczy.setZt("0");
		gczy.setS2(new Timestamp(new java.util.Date().getTime()));

		aqglGczyService.updateInfo(gczy);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  作业安全--高处作业  【修改操作】");
		//返回结果
		return datasuccess;
	}

	/**
	 * 撤回高处作业
	 * @param request,model
	 */
	@RequestMapping(value = "revoke/{id}")
	@ResponseBody
	public String revoke(@PathVariable("id") Long id,  Model model){
		String datasuccess="success";
		ZTAQGL_HighOperation gczy = aqglGczyService.findById(id);
		gczy.setZt("0");
		gczy.setS2(new Timestamp(new java.util.Date().getTime()));

		aqglGczyService.updateInfo(gczy);
		//返回结果
		return datasuccess;
	}

	/**
	 * 作废高处作业
	 * @param request,model
	 */
	@RequestMapping(value = "cancel/{id}")
	@ResponseBody
	public String cancel(@PathVariable("id") Long id,  Model model){
		String datasuccess="操作成功";
		ZTAQGL_HighOperation gczy = aqglGczyService.findById(id);
		gczy.setZt("-2");
		gczy.setS2(new Timestamp(new java.util.Date().getTime()));
		
		aqglGczyService.updateInfo(gczy);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 高处作业安全措施列表显示页面
	 * @param model
	 */
	@RequestMapping(value="aqcsindex")
	public String aqcsindex(Model model,HttpServletRequest request) {
		model.addAttribute("id1", request.getParameter("id1"));
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		return "ztzyaqgl/zyaq/gczy/aqcsindex";
	}

	/**
	 * 高处作业安全措施列表显示页面
	 * @param model
	 */
	@RequestMapping(value="updaqcsindex")
	public String updaqcsindex(Model model,HttpServletRequest request) {
		model.addAttribute("id1", request.getParameter("id1"));
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		return "ztzyaqgl/zyaq/gczy/addaqcsindex";
	}
	
	/**
	 * 高处作业安全措施list 
	 * @param request
	 */
	@RequestMapping(value="aqcslist")
	@ResponseBody
	public Map<String, Object> aqcsData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		String type=request.getParameter("type");
		if(type!=null){
			map.put("id1", request.getParameter("id1"));
			map.put("m2", "3");
			return aqglaqcsService.ybzaqscdataGrid(map);	
		}else{
			map.put("m2", "3");
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
		return "ztzyaqgl/zyaq/gczy/index_wlfchoose";
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
		String gcid=request.getParameter("gcid");
		String flag=request.getParameter("flag");
		List<ZTAQGL_Zyaq_Aqcs> list =JSON.parseArray(listjson, ZTAQGL_Zyaq_Aqcs.class);
        for(int i=0;i<list.size();i++){
	        try {
	        	ZTAQGL_Zyaq_Aqcs aqsc=list.get(i);
	        	aqglGczyService.updateAqcs(aqsc);
			} catch (Exception e) {
				// TODO: handle exception
				datasuccess="failed";
			}
        }
        if(flag==null){
			//修改高处状态
	        ZTAQGL_HighOperation gczy=aqglGczyService.find(Long.parseLong(gcid));
	        gczy.setM21(DateUtils.getSysTimestamp());
	        gczy.setM21_4(request.getParameter("aqjd"));
	        gczy.setM21_5(request.getParameter("sgfa"));
	        gczy.setM21_6(request.getParameter("wlfids"));
	        gczy.setZt("1");
	        aqglGczyService.updateInfo(gczy);
        }
		return datasuccess;
	}

	/**
	 * 作业单位确认页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "zydwqr/{id}", method = RequestMethod.GET)
	public String zydwqr(@PathVariable("id") Long id, Model model) {
		model.addAttribute("gczyid", id);
		//返回页面
		model.addAttribute("action", "zydwqr");
		return "ztzyaqgl/zyaq/gczy/zydwform";
	}	

	/**
	 *  作业单位确认
	 * @param request,model
	 */
	@RequestMapping(value = "zydwqr")
	@ResponseBody
	public String aqbm(long id, String M16, String M16_1, Model model, HttpServletRequest request){
		String datasuccess="success";	
		ZTAQGL_HighOperation gczy = aqglGczyService.find(id);
		gczy.setZt("2");//待安技员审批
		gczy.setM16(M16);
		gczy.setM16_1(M16_1);//电子签章
		gczy.setM16_2(DateUtils.getSysTimestamp());
		gczy.setM16_3(Long.parseLong(UserUtil.getCurrentShiroUser().getId().toString()));
		aqglGczyService.updateInfo(gczy);
		//返回结果
		return datasuccess;
	}

	/**
	 * 内部审批页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "nbsp/{id}", method = RequestMethod.GET)
	public String nbsp(@PathVariable("id") Long id, Model model) {
		model.addAttribute("gczyid", id);
		//返回页面
		model.addAttribute("action", "nbsp");
		return "ztzyaqgl/zyaq/gczy/nbspform";
	}	

	/**
	 *  内部审批
	 * @param request,model
	 */
	@RequestMapping(value = "nbsp")
	@ResponseBody
	public String nbsp(long id, String suggest, String spflag,Model model, HttpServletRequest request){
		String datasuccess="success";	
		ZTAQGL_HighOperation gczy = aqglGczyService.find(id);
		Timestamp t= DateUtils.getSysTimestamp();//当前时间
		String zt=gczy.getZt();//当前状态
		Long userid=Long.parseLong(UserUtil.getCurrentShiroUser().getId()+"");//用户时间
		String signpic= UserUtil.getCurrentShiroUser().getElecsignature();//用户的电子签章
		
		if(!gczy.getM10().equals("四级")){
			if(zt.equals("2")){//安技员审批
				if(spflag.equals("通过"))
					gczy.setZt("4");//待安全科长审批
				else
					gczy.setZt("-1");//退回编制人修改
				gczy.setM17(suggest);//安技员意见
				gczy.setM17_1(signpic);//签名图片
				gczy.setM17_2(t);//审批时间
				gczy.setM17_3(userid);//添加人id
				gczy.setM17_4(spflag);//状态
			}
			
			if(zt.equals("4")){
				if(spflag.equals("通过")){
					if(gczy.getM10().equals("一级"))
						gczy.setZt("8");//待确认安全措施
					else if(gczy.getM10().equals("二级"))
						gczy.setZt("5");//待安全处分管人员签字
					else if(gczy.getM10().equals("三级"))
						gczy.setZt("6");//待安全处分管领导签字
				}
				else
					gczy.setZt("-1");//退回编制人修改
				gczy.setM18(request.getParameter("suggest").toString());//安全科长意见
				gczy.setM18_1(signpic);//签名图片
				gczy.setM18_2(t);//审批时间
				gczy.setM18_3(userid);//添加人id
				gczy.setM18_4(spflag);//状态
			}			

			if(zt.equals("5")){//安全处分管人员签字
				if(spflag.equals("通过")){
						gczy.setZt("8");//待确认安全措施
				}
				else
					gczy.setZt("-1");//退回编制人修改
				gczy.setM19(suggest);//安全科长意见
				gczy.setM19_1(signpic);//签名图片
				gczy.setM19_2(t);//审批时间
				gczy.setM19_3(userid);//添加人id
				gczy.setM19_4(spflag);//状态
			}

			if(zt.equals("6")){//安全处分管领导签字
				if(spflag.equals("通过")){
					gczy.setZt("8");//待确认安全措施
				}
				else
					gczy.setZt("-1");//退回编制人修改
				gczy.setM20(suggest);//安全科长意见
				gczy.setM20_1(signpic);//签名图片
				gczy.setM20_2(t);//审批时间
				gczy.setM20_3(userid);//添加人id
				gczy.setM20_4(spflag);//状态
			}
			
		}else{//四级风险
			if(zt.equals("2")){
				if(spflag.equals("通过"))
					gczy.setZt("3");//待部门一把手审批
				else
					gczy.setZt("-1");//退回编制人修改
				gczy.setM17(suggest);//安技员意见
				gczy.setM17_1(signpic);//签名图片
				gczy.setM17_2(t);//审批时间
				gczy.setM17_3(userid);//添加人id
				gczy.setM17_4(spflag);//状态
			}	
			
			if(zt.equals("3")){
				if(spflag.equals("通过"))
					gczy.setZt("6");//待安全处分管领导审批
				else
					gczy.setZt("-1");//退回编制人修改
				gczy.setM24(suggest);//安全科长意见
				gczy.setM24_1(signpic);//签名图片
				gczy.setM24_2(t);//审批时间
				gczy.setM24_3(userid);//添加人id
				gczy.setM24_4(spflag);//状态
			}	
			
			if(zt.equals("6")){
				if(spflag.equals("通过"))
					gczy.setZt("7");//待公司分管领导审批
				else
					gczy.setZt("-1");//退回编制人修改
				gczy.setM20(suggest);//安全科长意见
				gczy.setM20_1(signpic);//签名图片
				gczy.setM20_2(t);//审批时间
				gczy.setM20_3(userid);//添加人id
				gczy.setM20_4(spflag);//状态
			}	
			
			if(zt.equals("7")){
				if(spflag.equals("通过"))
					gczy.setZt("8");//待确认安全措施
				else
					gczy.setZt("-1");//退回编制人修改
				gczy.setM25(suggest);//安全科长意见
				gczy.setM25_1(signpic);//签名图片
				gczy.setM25_2(t);//审批时间
				gczy.setM25_3(userid);//添加人id
				gczy.setM25_4(spflag);//状态
			}			
		}
		
		aqglGczyService.updateInfo(gczy);
		//返回结果
		return datasuccess;
	}

	/**
	 * 高处作业确认安全措施列表显示页面
	 * @param model
	 */
	@RequestMapping(value="qraqcsindex")
	public String qraqcsindex(Model model,HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("id1", request.getParameter("id1"));
		map.put("m2", 3);
		model.addAttribute("id1", request.getParameter("id1"));
		model.addAttribute("action", "csqr");
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		model.addAttribute("aqcslist", JsonMapper.getInstance().toJson(aqglaqcsService.ybzaqsc(map)));
		return "ztzyaqgl/zyaq/gczy/qrcsform";
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
        	ZTAQGL_Zyaq_Aqcs aqcs=aqglGczyService.findAqcs(Long.parseLong(csid[i]));
        	aqcs.setM3(m3);
        	aqcs.setM4(xcphoto);
        	aqglGczyService.updateAqcs(aqcs);
		}
		//修改高处状态
		String gcid=request.getParameter("gcid");
        ZTAQGL_HighOperation gczy=aqglGczyService.find(Long.parseLong(gcid));
        gczy.setM21_7(DateUtils.getSysTimestamp());
        gczy.setM21_2(request.getParameter("signpic"));
        gczy.setM21_3(UserUtil.getCurrentShiroUser().getElecsignature());//许可人电子签章
        gczy.setZt("9");
        aqglGczyService.updateInfo(gczy);
		//返回结果
		return datasuccess;
	}

	/**
	 * 高处作业完工签字显示页面
	 * @param model
	 */
	@RequestMapping(value="wgqzindex")
	public String wgqzindex(Model model,HttpServletRequest request) {
		Timestamp t= DateUtils.getSysTimestamp();//确认时间
		model.addAttribute("wgsj", t);
		model.addAttribute("action", "wgqz");
		model.addAttribute("gcid", request.getParameter("id1"));
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		return "ztzyaqgl/zyaq/gczy/wgqzform";
	}
	
	//完工签字
	@RequestMapping(value="wgqz")
	@ResponseBody
	public String wgqz(HttpServletRequest request){
		String datasuccess="success";
		ZTAQGL_HighOperation gczy=aqglGczyService.find(Long.parseLong(request.getParameter("gcid")));
		Timestamp t= DateUtils.getSysTimestamp();//确认时间
		String signpic=request.getParameter("signpic").toString();//签名图片
		Long userid=Long.parseLong(UserUtil.getCurrentShiroUser().getId()+"");
		if(gczy.getZt().equals("9")){
			gczy.setZt("10");//待分厂完工签字
			gczy.setM22(t);//作业单位确认时间
			gczy.setM22_1(signpic);//签名图片
			gczy.setM22_2(userid);//添加人id
		}else if(gczy.getZt().equals("10")){
			gczy.setZt("11");//完工
			gczy.setM23(t);//分厂完工确认时间
			gczy.setM23_1(signpic);//分厂完工图片
			gczy.setM23_2(userid);//添加人id
		}
		aqglGczyService.updateInfo(gczy);//修改高处作业
		return datasuccess;
	}
	
	/**
	 * 删除高处作业信息
	 */
	@RequiresPermissions("zyaqgl:gczy:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  作业安全--高处作业  【删除操作】,删除的id是:"+arrids[i]);
			aqglGczyService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String,Object> gczy = aqglGczyService.findallById(id);
		if(gczy.get("m21_6")!=null){
			List<Map<String,Object>> wlf = aqglGczyService.findallWlfIds(gczy.get("m21_6").toString()+"0");
			model.addAttribute("wlflist", JsonMapper.getInstance().toJson(wlf));
		}
		model.addAttribute("gczy", gczy);
		return "ztzyaqgl/zyaq/gczy/view";
	}
	
	/**
	 * 查看状态页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "viewzt/{id}", method = RequestMethod.GET)
	public String viewzt(@PathVariable("id") Long id, Model model) {
		Map<String,Object> gczy = aqglGczyService.findallById(id);
		model.addAttribute("gczy", gczy);
		return "ztzyaqgl/zyaq/gczy/viewzt";
	}
	
	/**
	 * 显示所有列
	 * 
	 * @param id,model
	 */
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","ztzyaqgl/gczy/export");
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
		aqglGczyService.exportExcel(response, map);
	}
	
	/**
	 * 导出高处作业word
	 */
	@RequestMapping(value = "exportword/{id}")
	@ResponseBody
	public String getexportword(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		String webAddress= request.getSession().getServletContext().getRealPath("/").replace(request.getContextPath().replace("/","\\"),"");
		Map<String, Object> map = aqglGczyService.getExportWord(id,webAddress);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "gczy.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
}
