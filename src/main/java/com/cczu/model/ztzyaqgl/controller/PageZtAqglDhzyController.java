package com.cczu.model.ztzyaqgl.controller;

import com.alibaba.fastjson.JSON;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.model.ztzyaqgl.entity.ZTAQGL_DhzyFxEntity;
import com.cczu.model.ztzyaqgl.entity.ZTAQGL_FireWork;
import com.cczu.model.ztzyaqgl.entity.ZTAQGL_Zyaq_Aqcs;
import com.cczu.model.ztzyaqgl.service.AqglAqcsService;
import com.cczu.model.ztzyaqgl.service.AqglDhzyFxService;
import com.cczu.model.ztzyaqgl.service.AqglDhzyService;
import com.cczu.model.zyaqgl.service.AqglXgdwService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.Department;
import com.cczu.sys.system.entity.Message;
import com.cczu.sys.system.service.DepartmentService;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.utils.MessageUtil;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
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
 * 安全生产-动火作业Controller
 * @author YZH
 */
@Controller
@RequestMapping("ztzyaqgl/dhzy")
public class PageZtAqglDhzyController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private AqglDhzyService aqgldhzyService;
	@Autowired
	private AqglAqcsService aqglaqcsService;
	@Autowired
	private AqglDhzyFxService aqgldhzyfxService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	@Autowired
	private AqglXgdwService aqglxgdwService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private UserService userService;

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
		return "ztzyaqgl/zyaq/dhzy/index";
	}
	
	/**
	 * 数据list 
	 * @param request
	 */
	@RequiresPermissions("zyaqgl:dhzy:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("m1", request.getParameter("aqgl_dhzy_cx_m1"));//作业证编号
		map.put("m4", request.getParameter("aqgl_dhzy_cx_m4"));//动火作业级别
		map.put("zt", request.getParameter("aqgl_dhzy_cx_zt"));//动火作业状态
		map.put("qyname", request.getParameter("zyaqgl_bgsq_qy_name"));//企业名
		map.putAll(getAuthorityMap());

		//如果选择了部门下拉，则覆盖前面的部门条件，但也仅限于该用户所能看到的部门范围
		String depid=request.getParameter("aqgl_dhzy_cx_depid");
		if(depid!=null&&(!depid.equals(""))){
			Department dep=departmentService.get(Long.parseLong(depid));
			map.put("depcode3", dep.getCode());
		}

		Subject subject = SecurityUtils.getSubject();
		if(!subject.isPermitted("zyaqgl:gczy:viewall")) {
			if (subject.isPermitted("zyaqgl:dhzy:bwcsp")) {//是否拥有保卫处审批权限
				map.put("depcode2", "");
				map.put("zt2", "7");//动火作业状态
			}

//		if(subject.isPermitted("zyaqgl:gczy:gsldsp")) {//是否拥有公司分管领导审批权限
//			map.put("depcode2", "");
//			map.put("zt2", "8");//动火作业状态
//		}
			if(subject.isPermitted("zyaqgl:dhzy:nyzxsp")) {//是否拥有能控中心审批权限
				map.put("depcode2", "");
				map.put("zt2", "9");//动火作业状态
			}
		}
        map.put("dspzt", request.getParameter("spzt"));//当前审批人待审批的状态
		return aqgldhzyService.dataGrid(map);
	}

	/**
	 * 特殊情况list
	 * @param {json}
	 */
	@RequestMapping(value="tsqklist")
	@ResponseBody
	public String tsqklist() {
		return aqgldhzyService.tsqklist();
	}

	/**
	 * 延期页面跳转
	 * @param model
	 */
	@RequestMapping(value = "delay" , method = RequestMethod.GET)
	public String delay(Model model, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", request.getParameter("dhid"));
		model.addAttribute("dhzy", map);
		return "ztzyaqgl/zyaq/dhzy/delayform";
	}

	/**
	 * 延期动火作业信息
	 * @param request,model
	 */
	@RequestMapping(value = "delay")
	@ResponseBody
	public String delay(ZTAQGL_FireWork entity, Model model) {
		String datasuccess = "success";
		ZTAQGL_FireWork dhzy=aqgldhzyService.find(entity.getID());

		//判断该作业是否已延期
		Map<String,Object> map=new HashMap<>();
		map.put("m1",dhzy.getM1());
		int delaycount=aqgldhzyService.finddelaycount(map);
		if(delaycount>1){
			datasuccess="has";
			return datasuccess;
		}
		ZTAQGL_FireWork dhzy2=new ZTAQGL_FireWork();
		BeanUtils.copyProperties(dhzy,dhzy2);
		dhzy2.setID(null);
		dhzy2.setM1(dhzy.getM1()+"-A");
		dhzy2.setM7(entity.getM7());
		dhzy2.setM8(entity.getM8());
		dhzy2.setM23_7(entity.getM7());
		dhzy2.setZt("12");
		//添加动火作业
		aqgldhzyService.addInfo(dhzy2);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  作业安全--动火作业  【延期操作】");
		// 返回结果
		return datasuccess;
	}

	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("zyaqgl:dhzy:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model, HttpServletRequest request) {
		String code="DHZY-"+ DateUtils.getDateRandom();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("m1", code);
		map.put("bzr", UserUtil.getCurrentUser().getName());//编制人
		model.addAttribute("dhzy", map);
		model.addAttribute("action", "create");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "ztzyaqgl/zyaq/dhzy/form";
	}
	
	/**
	 * 添加动火作业信息
	 * @param request,model
	 */
	@RequiresPermissions("zyaqgl:dhzy:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(ZTAQGL_FireWork entity, Model model) {
		String datasuccess = "success";
		Timestamp t= DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		entity.setZt("0");//添加初始状态
		entity.setID1(UserUtil.getCurrentShiroUser().getQyid());
		//申请人id(即编制人)
		entity.setM23_1(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));
		//添加动火作业
		aqgldhzyService.addInfo(entity);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  作业安全--动火作业  【添加操作】");
		// 返回结果
		return datasuccess;
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("zyaqgl:dhzy:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的动火作业信息
		Map<String, Object> dhzy = aqgldhzyService.findInforById(id);
		model.addAttribute("dhzy", dhzy);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "ztzyaqgl/zyaq/dhzy/form";
	}
	
	/**
	 * 修改动火作业信息
	 * @param request,model
	 */
	@RequiresPermissions("zyaqgl:dhzy:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(ZTAQGL_FireWork entity, Model model){
		String datasuccess="success";	
		ZTAQGL_FireWork dhzy = aqgldhzyService.findById(entity.getID());
		dhzy.setM1(entity.getM1());
		dhzy.setM2(entity.getM2());
		dhzy.setM3(entity.getM3());
		dhzy.setM4(entity.getM4());
		dhzy.setM5(entity.getM5());
		dhzy.setM6(entity.getM6());
		dhzy.setM7(entity.getM7());
		dhzy.setM8(entity.getM8());
		dhzy.setM9(entity.getM9());
		dhzy.setM10(entity.getM10());
		dhzy.setM11(entity.getM11());
		dhzy.setM12(entity.getM12());
		dhzy.setM12_1(entity.getM12_1());
		dhzy.setM13(entity.getM13());
		dhzy.setM13_1(entity.getM13_1());
		dhzy.setZt("0");
		dhzy.setS2(new Timestamp(new java.util.Date().getTime()));

		aqgldhzyService.updateInfo(dhzy);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  作业安全--动火作业  【修改操作】");
		//返回结果
		return datasuccess;
	}

	/**
	 * 撤回动火作业
	 * @param request,model
	 */
	@RequestMapping(value = "revoke/{id}")
	@ResponseBody
	public String revoke(@PathVariable("id") Long id,  Model model){
		String datasuccess="success";
		ZTAQGL_FireWork dhzy = aqgldhzyService.findById(id);
		dhzy.setZt("0");
		dhzy.setS2(new Timestamp(new java.util.Date().getTime()));

		aqgldhzyService.updateInfo(dhzy);
		//返回结果
		return datasuccess;
	}

	/**
	 * 作废动火作业
	 * @param request,model
	 */
	@RequestMapping(value = "cancel/{id}")
	@ResponseBody
	public String cancel(@PathVariable("id") Long id,  Model model){
		String datasuccess="操作成功";
		ZTAQGL_FireWork dhzy = aqgldhzyService.findById(id);
		dhzy.setZt("-2");
		dhzy.setS2(new Timestamp(new java.util.Date().getTime()));
		
		aqgldhzyService.updateInfo(dhzy);
		//返回结果
		return datasuccess;
	}

	/**
	 * 动火作业安全措施列表显示页面
	 * @param model
	 */
	@RequestMapping(value="updaqcsindex")
	public String updaqcsindex(Model model,HttpServletRequest request) {
		model.addAttribute("id1", request.getParameter("id1"));
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		return "ztzyaqgl/zyaq/dhzy/addaqcsindex";
	}
	
	/**
	 * 动火作业安全措施list 
	 * @param request
	 */
	@RequestMapping(value="aqcslist")
	@ResponseBody
	public Map<String, Object> aqcsData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		String type=request.getParameter("type");
		if(type!=null){
			map.put("id1", request.getParameter("id1"));
			map.put("m2", "1");
			return aqglaqcsService.ybzaqscdataGrid(map);	
		}else{
			map.put("m2", "1");
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
		return "ztzyaqgl/zyaq/dhzy/index_wlfchoose";
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
		String dhid=request.getParameter("dhid");
		String flag=request.getParameter("flag");
		List<ZTAQGL_Zyaq_Aqcs> list =JSON.parseArray(listjson, ZTAQGL_Zyaq_Aqcs.class);
        for(int i=0;i<list.size();i++){
	        try {
	        	ZTAQGL_Zyaq_Aqcs aqsc=list.get(i);
	        	aqgldhzyService.updateAqcs(aqsc);
			} catch (Exception e) {
				// TODO: handle exception
				datasuccess="failed";
			}
        }
        if(flag==null){
			//修改动火状态
        	ZTAQGL_FireWork dhzy=aqgldhzyService.find(Long.parseLong(dhid));
	        dhzy.setM23(DateUtils.getSysTimestamp());
	        dhzy.setM23_4(request.getParameter("aqjd"));
	        dhzy.setM23_5(request.getParameter("sgfa"));
	        dhzy.setM23_6(request.getParameter("wlfids"));
	        dhzy.setZt("1");
	        aqgldhzyService.updateInfo(dhzy);
        }
		return datasuccess;
	}

	/**
	 * 作业单位确认页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "zydwqr/{id}", method = RequestMethod.GET)
	public String zydwqr(@PathVariable("id") Long id, Model model) {
		model.addAttribute("dhzyid", id);
		//返回页面
		model.addAttribute("action", "zydwqr");
		return "ztzyaqgl/zyaq/dhzy/zydwform";
	}	

	/**
	 *  作业单位确认
	 * @param request,model
	 */
	@RequestMapping(value = "zydwqr")
	@ResponseBody
	public String aqbm(long id, String M14, String M14_1, Model model, HttpServletRequest request){
		String datasuccess="success";	
		ZTAQGL_FireWork dhzy = aqgldhzyService.find(id);
		dhzy.setZt("2");//待安技员审批
		dhzy.setM14(M14);
		dhzy.setM14_1(M14_1);//电子签章
		dhzy.setM14_2(DateUtils.getSysTimestamp());
		dhzy.setM14_3(Long.parseLong(UserUtil.getCurrentShiroUser().getId().toString()));
		aqgldhzyService.updateInfo(dhzy);
		//返回结果
		return datasuccess;
	}

	/**
	 * 内部审批页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "nbsp/{id}", method = RequestMethod.GET)
	public String nbsp(@PathVariable("id") Long id, Model model) {
		model.addAttribute("dhzyid", id);
		//返回页面
		model.addAttribute("action", "nbsp");
		return "ztzyaqgl/zyaq/dhzy/nbspform";
	}	

	/**
	 *  内部审批
	 * @param request,model
	 */
	@RequestMapping(value = "nbsp")
	@ResponseBody
	public String nbsp(long id, String suggest, String spflag,Model model, HttpServletRequest request){
		String datasuccess="success";	
		ZTAQGL_FireWork dhzy = aqgldhzyService.find(id);
		Timestamp t= DateUtils.getSysTimestamp();//当前时间
		String zt=dhzy.getZt();//当前状态
		Long userid=Long.parseLong(UserUtil.getCurrentShiroUser().getId()+"");//用户时间
		String signpic= UserUtil.getCurrentShiroUser().getElecsignature();//用户的电子签章

/*		//发送消息
		ShiroUser shirouser = UserUtil.getCurrentShiroUser();
		Department dep=shirouser.getDep();
		Map<String,Object>  msgmap = new HashMap<String,Object>();
		msgmap.put(Message.MSGTARGET_H5,"zyaqgl/dhzy/index.jsp");
		msgmap.put(Message.MSGTARGET_PC,"zdgl/wjfb/update/pz/");*/

		if(!dhzy.getM4().equals("特殊动火")){
			if(zt.equals("2")){//安技员审批
				if(spflag.equals("通过")){
					if(dhzy.getM4().equals("二级动火")){
						dhzy.setZt("4");//待分厂安全分管领导审批
/*						//消息推送
						String ryids=userService.findUserByPermission3(dep.getCode(),dep.getId(),"zyaqgl:gczy:aqkzsp");
						sendmsg(msgmap,dhzy,ryids);*/
					}else{
						dhzy.setZt("3");//待部门一把手审批
					}
				}else{
					dhzy.setZt("-1");//退回编制人修改
				}
				dhzy.setM15(suggest);//安技员意见
				dhzy.setM15_1(signpic);//签名图片
				dhzy.setM15_2(t);//审批时间
				dhzy.setM15_3(userid);//添加人id
				dhzy.setM15_4(spflag);//状态
			}
			
			if(zt.equals("3")){//部门一把手审批
				if(spflag.equals("通过"))
					dhzy.setZt("6");//待安全处分管领导审批
				else
					dhzy.setZt("-1");//退回编制人修改
				dhzy.setM20(suggest);//部门一把手意见
				dhzy.setM20_1(signpic);//签名图片
				dhzy.setM20_2(t);//审批时间
				dhzy.setM20_3(userid);//添加人id
				dhzy.setM20_4(spflag);//状态
			}	
			
			if(zt.equals("4")){//安全科长审批
				if(spflag.equals("通过")){
					if(dhzy.getM4().equals("二级动火"))
						dhzy.setZt("10");//待确认安全措施
					else//一级动火
						dhzy.setZt("5");//待安全处分管人员审批
				}
				else
					dhzy.setZt("-1");//退回编制人修改
				dhzy.setM16(request.getParameter("suggest").toString());//安全科长意见
				dhzy.setM16_1(signpic);//签名图片
				dhzy.setM16_2(t);//审批时间
				dhzy.setM16_3(userid);//添加人id
				dhzy.setM16_4(spflag);//状态
			}			

			if(zt.equals("5")){//安全处分管人员签字
				if(spflag.equals("通过")){
						dhzy.setZt("7");//待保卫部
				}
				else
					dhzy.setZt("-1");//退回编制人修改
				dhzy.setM17(suggest);//安全科长意见
				dhzy.setM17_1(signpic);//签名图片
				dhzy.setM17_2(t);//审批时间
				dhzy.setM17_3(userid);//添加人id
				dhzy.setM17_4(spflag);//状态
			}

			if(zt.equals("6")){//安全处分管领导签字
				if(spflag.equals("通过")){
					dhzy.setZt("7");//待保卫部
				}
				else
					dhzy.setZt("-1");//退回编制人修改
				dhzy.setM18(suggest);//安全处分管领导意见
				dhzy.setM18_1(signpic);//签名图片
				dhzy.setM18_2(t);//审批时间
				dhzy.setM18_3(userid);//添加人id
				dhzy.setM18_4(spflag);//状态
			}
			
			if(zt.equals("7")){//保卫部签字
				if(spflag.equals("通过")){
					if(dhzy.getM4().equals("二级动火")){
						dhzy.setZt("10");//待确认措施
					}else{//一级动火
						dhzy.setZt("9");//待能源管控中心审批
					}
				}
				else
					dhzy.setZt("-1");//退回编制人修改
				dhzy.setM19(suggest);//保卫部意见
				dhzy.setM19_1(signpic);//签名图片
				dhzy.setM19_2(t);//审批时间
				dhzy.setM19_3(userid);//添加人id
				dhzy.setM19_4(spflag);//状态
			}
			
			if(zt.equals("9")){//能源管控中心
				if(spflag.equals("通过"))
					dhzy.setZt("10");//待确认措施
				else
					dhzy.setZt("-1");//退回编制人修改
				dhzy.setM22(suggest);//安全科长意见
				dhzy.setM22_1(signpic);//签名图片
				dhzy.setM22_2(t);//审批时间
				dhzy.setM22_3(userid);//添加人id
				dhzy.setM22_4(spflag);//状态
			}
			
		}else{//特殊动火
			if(zt.equals("2")){
				if(spflag.equals("通过"))
					dhzy.setZt("3");//待部门一把手审批
				else
					dhzy.setZt("-1");//退回编制人修改
				dhzy.setM15(suggest);//安技员意见
				dhzy.setM15_1(signpic);//签名图片
				dhzy.setM15_2(t);//审批时间
				dhzy.setM15_3(userid);//添加人id
				dhzy.setM15_4(spflag);//状态
			}	
			
			if(zt.equals("3")){//部门一把手审批
				if(spflag.equals("通过"))
					dhzy.setZt("6");//待安全处分管领导审批
				else
					dhzy.setZt("-1");//退回编制人修改
				dhzy.setM20(suggest);//部门一把手意见
				dhzy.setM20_1(signpic);//签名图片
				dhzy.setM20_2(t);//审批时间
				dhzy.setM20_3(userid);//添加人id
				dhzy.setM20_4(spflag);//状态
			}	
			
			if(zt.equals("6")){//安全处分管领导审批
				if(spflag.equals("通过"))
					dhzy.setZt("7");//待保卫部审批
				else
					dhzy.setZt("-1");//退回编制人修改
				dhzy.setM18(suggest);//安全处分管领导意见
				dhzy.setM18_1(signpic);//签名图片
				dhzy.setM18_2(t);//审批时间
				dhzy.setM18_3(userid);//添加人id
				dhzy.setM18_4(spflag);//状态
			}	
			
			if(zt.equals("7")){
				if(spflag.equals("通过"))
					dhzy.setZt("8");//待公司分管领导审批
				else
					dhzy.setZt("-1");//退回编制人修改
				dhzy.setM19(suggest);//安全科长意见
				dhzy.setM19_1(signpic);//签名图片
				dhzy.setM19_2(t);//审批时间
				dhzy.setM19_3(userid);//添加人id
				dhzy.setM19_4(spflag);//状态
			}
			
			if(zt.equals("8")){//公司分管领导
				if(spflag.equals("通过"))
					dhzy.setZt("9");//待能源管控中心审批
				else
					dhzy.setZt("-1");//退回编制人修改
				dhzy.setM21(suggest);//安全科长意见
				dhzy.setM21_1(signpic);//签名图片
				dhzy.setM21_2(t);//审批时间
				dhzy.setM21_3(userid);//添加人id
				dhzy.setM21_4(spflag);//状态
			}	
			
			if(zt.equals("9")){//能源管控中心
				if(spflag.equals("通过"))
					dhzy.setZt("10");//待确认措施
				else
					dhzy.setZt("-1");//退回编制人修改
				dhzy.setM22(suggest);//安全科长意见
				dhzy.setM22_1(signpic);//签名图片
				dhzy.setM22_2(t);//审批时间
				dhzy.setM22_3(userid);//添加人id
				dhzy.setM22_4(spflag);//状态
			}			
		}
		
		aqgldhzyService.updateInfo(dhzy);
		//返回结果
		return datasuccess;
	}

	/**
	 * 动火作业确认安全措施列表显示页面
	 * @param model
	 */
	@RequestMapping(value="qraqcsindex")
	public String qraqcsindex(Model model,HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("id1", request.getParameter("id1"));
		map.put("m2", 1);
		model.addAttribute("id1", request.getParameter("id1"));
		model.addAttribute("action", "csqr");
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		model.addAttribute("aqcslist", JsonMapper.getInstance().toJson(aqglaqcsService.ybzaqsc(map)));
		return "ztzyaqgl/zyaq/dhzy/qrcsform";
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
        	ZTAQGL_Zyaq_Aqcs aqcs=aqgldhzyService.findAqcs(Long.parseLong(csid[i]));
        	aqcs.setM3(m3);
        	aqcs.setM4(xcphoto);
        	aqgldhzyService.updateAqcs(aqcs);
		}
		//修改动火状态
		String dhid=request.getParameter("dhid");
		ZTAQGL_FireWork dhzy=aqgldhzyService.find(Long.parseLong(dhid));
		dhzy.setM23_7(DateUtils.getSysTimestamp());
		dhzy.setM23_2(request.getParameter("signpic"));
		dhzy.setM23_3(UserUtil.getCurrentShiroUser().getElecsignature());//许可人电子签章
		dhzy.setZt("11");//待分析
        aqgldhzyService.updateInfo(dhzy);
		//返回结果
		return datasuccess;
	}

	/**
	 * 动火作业完工签字显示页面
	 * @param model
	 */
	@RequestMapping(value="wgqzindex")
	public String wgqzindex(Model model,HttpServletRequest request) {
		Timestamp t= DateUtils.getSysTimestamp();//确认时间
		model.addAttribute("wgsj", t);
		model.addAttribute("action", "wgqz");
		model.addAttribute("dhid", request.getParameter("id1"));
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		return "ztzyaqgl/zyaq/dhzy/wgqzform";
	}
	
	//完工签字
	@RequestMapping(value="wgqz")
	@ResponseBody
	public String wgqz(HttpServletRequest request){
		String datasuccess="success";
		ZTAQGL_FireWork dhzy=aqgldhzyService.find(Long.parseLong(request.getParameter("dhid")));
		Timestamp t= DateUtils.getSysTimestamp();//确认时间
		String signpic=request.getParameter("signpic").toString();//签名图片
		Long userid=Long.parseLong(UserUtil.getCurrentShiroUser().getId()+"");
		if(dhzy.getZt().equals("12")){
			dhzy.setZt("13");//待分厂完工签字
			dhzy.setM24(t);//作业单位确认时间
			dhzy.setM24_1(signpic);//签名图片
			dhzy.setM24_2(userid);//添加人id
		}else if(dhzy.getZt().equals("13")){
			dhzy.setZt("14");//完工
			dhzy.setM25(t);//分厂完工确认时间
			dhzy.setM25_1(signpic);//分厂完工图片
			dhzy.setM25_2(userid);//添加人id
		}
		aqgldhzyService.updateInfo(dhzy);//修改动火作业
		return datasuccess;
	}
	
	/**
	 * 删除动火作业信息
	 */
	@RequiresPermissions("zyaqgl:dhzy:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  作业安全--动火作业  【删除操作】,删除的id是:"+arrids[i]);
			aqgldhzyService.deleteInfo(Long.parseLong(arrids[i]));
			//删除相应分析数据
			aqgldhzyfxService.deleteInfoByid1(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("zyaqgl:dhzy:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String,Object> dhzy = aqgldhzyService.findallById(id);
		if(dhzy.get("m23_6")!=null){
			List<Map<String,Object>> wlf = aqgldhzyService.findallWlfIds(dhzy.get("m23_6").toString()+"0");
			model.addAttribute("wlflist", JsonMapper.getInstance().toJson(wlf));
		}
		model.addAttribute("dhzy", dhzy);
		return "ztzyaqgl/zyaq/dhzy/view";
	}
	
	/**
	 * 分析页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "fx/{id}", method = RequestMethod.GET)
	public String fx(@PathVariable("id") Long id, Model model) {
		model.addAttribute("dhzyid", id);
		model.addAttribute("username", UserUtil.getCurrentUser().getName());
		//返回页面
		model.addAttribute("action", "fx");
		return "ztzyaqgl/zyaq/dhzy/fxform";
	}
	
	/**
	 * 分析
	 * @param request,model
	 */
	@RequestMapping(value = "fx")
	@ResponseBody
	public String fx(Long id, Model model, HttpServletRequest request){
		String datasuccess="success";	
		Timestamp t= DateUtils.getSysTimestamp();
		String name[] = request.getParameterValues("name");//分析点名称
		String media[] = request.getParameterValues("media");//介质
		String number[] = request.getParameterValues("number");//数量
		String unit[] = request.getParameterValues("unit");//单位
		String person[] = request.getParameterValues("person");//分析人
		String xczp[] = request.getParameterValues("xczp");//现场照片
		if(name!=null){
			//循环插入分析表
			for(int i=0;i<name.length;i++){
				ZTAQGL_DhzyFxEntity sxfx = new ZTAQGL_DhzyFxEntity();
				sxfx.setID1(id);
				sxfx.setM1(name[i]);
				sxfx.setM2(media[i]);
				sxfx.setM2_1(number[i]);
				sxfx.setM2_2(unit[i]);
				sxfx.setM3(t);
				sxfx.setM4(person[i]);
				sxfx.setM5(xczp[i]);
				aqgldhzyfxService.addInfo(sxfx);
			}
		}
		//修改作业证状态
		ZTAQGL_FireWork dhzy = aqgldhzyService.findById(id);
		dhzy.setZt("12");//待作业单位完工签字
		dhzy.setM23_8(t);//分析时间
		dhzy.setID2(Long.parseLong(UserUtil.getCurrentUser().getId()+""));
		aqgldhzyService.updateInfo(dhzy);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 动火作业安全措施列表显示页面
	 * @param model
	 */
	@RequestMapping(value="aqcsindex")
	public String aqcsindex(Model model,HttpServletRequest request) {
		model.addAttribute("id1", request.getParameter("id1"));
		model.addAttribute("type", request.getParameter("type"));
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		return "ztzyaqgl/zyaq/dhzy/aqcsindex";
	}
		
	/**
	 * 分析数据list 
	 * @param request
	 */
	@RequestMapping(value="dhzyfxlist")
	@ResponseBody
	public Map<String, Object> getFxsjData(HttpServletRequest request) {
		String dhzyid = request.getParameter("dhzyid");
		return aqgldhzyfxService.dataGrid(dhzyid);	
	}
	
	/**
	 * 查看状态页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "viewzt/{id}", method = RequestMethod.GET)
	public String viewzt(@PathVariable("id") Long id, Model model) {
		Map<String,Object> dhzy = aqgldhzyService.findallById(id);
		model.addAttribute("dhzy", dhzy);
		//根据作业证id查找分析数据详情
		List<Map<String,Object>> dhfxs = aqgldhzyfxService.findAllByid1(id);
		model.addAttribute("dhfxs", dhfxs);
		return "ztzyaqgl/zyaq/dhzy/viewzt";
	}
	
	/**
	 * 显示所有列
	 * 
	 * @param id,model
	 */
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","ztzyaqgl/dhzy/export");
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
		aqgldhzyService.exportExcel(response, map);
	}
	
	/**
	 * 导出动火作业word
	 */
	@RequestMapping(value = "exportword/{id}")
	@ResponseBody
	public String getexportword(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		String webAddress= request.getSession().getServletContext().getRealPath("/").replace(request.getContextPath().replace("/","\\"),"");
		Map<String, Object> map = aqgldhzyService.getExportWord(id,webAddress);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "dhzy.ftl", filePath, filename, request);
		return "/download/" + filename;
	}

	/**
	 * 消息提醒
	 * @param list
	 * @return
	 */
	public void sendmsg(Map<String,Object> map, ZTAQGL_FireWork dhzy, String ryids) {
		String ryid[]=ryids.split(",");
		Integer userid= UserUtil.getCurrentShiroUser().getId();
		for (int i=0;i<ryid.length;i++){
			MessageUtil.sendMsg(ryid[i]+"", userid+"", "劳保用品临时申请", Message.MessageType.DSH.getMsgType(),JSON.toJSONString(map),"劳保用品临时申请");
		}
	}
}
