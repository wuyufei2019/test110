package com.cczu.model.ztzyaqgl.controller;

import com.alibaba.fastjson.JSON;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.model.ztzyaqgl.entity.ZTAQGL_SxkjzyEntity;
import com.cczu.model.ztzyaqgl.entity.ZTAQGL_SxkjzyFxEntity;
import com.cczu.model.ztzyaqgl.entity.ZTAQGL_Zyaq_Aqcs;
import com.cczu.model.ztzyaqgl.service.AqglAqcsService;
import com.cczu.model.ztzyaqgl.service.AqglSxzyFxService;
import com.cczu.model.ztzyaqgl.service.AqglSxzyService;
import com.cczu.model.zyaqgl.service.AqglXgdwService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.Department;
import com.cczu.sys.system.service.DepartmentService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
 * 安全生产-受限空间作业Controller
 * @author zpc
 */
@Controller
@RequestMapping("ztzyaqgl/sxzy")
public class PageZtAqglSxzyController extends BaseController {

	@Autowired
	private AqglSxzyService aqglSxzyService;
	@Autowired
	private AqglSxzyFxService aqglSxzyFxService;
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
		return "ztzyaqgl/zyaq/sxzy/index";
	}
	
	/**
	 * 数据list 
	 * @param request
	 */
	@RequiresPermissions("zyaqgl:sxzy:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("m1", request.getParameter("aqgl_sxzy_cx_m1"));//作业证编号
		map.put("m4", request.getParameter("aqgl_sxzy_cx_m4"));//受限空间名称
		map.put("m13", request.getParameter("aqgl_sxzy_cx_m13"));//受限空间等级
		map.put("zt", request.getParameter("aqgl_sxzy_cx_zt"));//受限空间作业状态
		map.put("qyname", request.getParameter("zyaqgl_bgsq_qy_name"));//企业名
		map.putAll(getAuthorityMap());

		//如果选择了部门下拉，则覆盖前面的部门条件，但也仅限于该用户所能看到的部门范围
		String depid=request.getParameter("aqgl_sxzy_cx_depid");
		if(depid!=null&&(!depid.equals(""))){
			Department dep=departmentService.get(Long.parseLong(depid));
			map.put("depcode3", dep.getCode());
		}
		map.put("dspzt", request.getParameter("spzt"));//当前审批人待审批的状态
		return aqglSxzyService.dataGrid(map);	
	}

	/**
	 * 特殊情况list
	 * @param {json}
	 */
	@RequestMapping(value="tsqklist")
	@ResponseBody
	public String tsqklist() {
		return aqglSxzyService.tsqklist();
	}

	/**
	 * 延期页面跳转
	 * @param model
	 */
	@RequestMapping(value = "delay" , method = RequestMethod.GET)
	public String delay(Model model, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", request.getParameter("sxid"));
		model.addAttribute("sxzy", map);
		return "ztzyaqgl/zyaq/sxzy/delayform";
	}

	/**
	 * 延期受限作业信息
	 * @param request,model
	 */
	@RequestMapping(value = "delay")
	@ResponseBody
	public String delay(ZTAQGL_SxkjzyEntity entity, Model model) {
		String datasuccess = "success";
		ZTAQGL_SxkjzyEntity sxzy=aqglSxzyService.find(entity.getID());

		//判断该作业是否已延期
		Map<String,Object> map=new HashMap<>();
		map.put("m1",sxzy.getM1());
		int delaycount=aqglSxzyService.finddelaycount(map);
		if(delaycount>1){
			datasuccess="has";
			return datasuccess;
		}
		ZTAQGL_SxkjzyEntity sxzy2=new ZTAQGL_SxkjzyEntity();
		BeanUtils.copyProperties(sxzy,sxzy2);
		sxzy2.setID(null);
		sxzy2.setM1(sxzy.getM1()+"-A");
		sxzy2.setM7(entity.getM7());
		sxzy2.setM8(entity.getM8());
		sxzy2.setM21_7(entity.getM7());
		sxzy2.setZt("8");
		//添加受限作业
		aqglSxzyService.addInfo(sxzy2);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  作业安全--受限作业  【延期操作】");
		// 返回结果
		return datasuccess;
	}

	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("zyaqgl:sxzy:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model, HttpServletRequest request) {
		String code="SXZY-"+ DateUtils.getDateRandom();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("m1", code);
		map.put("bzr", UserUtil.getCurrentUser().getName());//编制人
		model.addAttribute("sxzy", map);
		model.addAttribute("action", "create");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "ztzyaqgl/zyaq/sxzy/form";
	}
	
	/**
	 * 添加受限空间作业信息
	 * @param request,model
	 */
	@RequiresPermissions("zyaqgl:sxzy:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(ZTAQGL_SxkjzyEntity entity, Model model) {
		String datasuccess = "success";
		Timestamp t= DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		entity.setZt("0");//添加初始状态
		entity.setID1(UserUtil.getCurrentShiroUser().getQyid());
		//申请人id(即编制人)
		entity.setM21_1(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));
		//添加受限空间作业
		aqglSxzyService.addInfo(entity);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  作业安全--受限作业  【添加操作】");
		// 返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		ZTAQGL_SxkjzyEntity sxzy = aqglSxzyService.findById(id);
		model.addAttribute("sxzy", sxzy);
		//返回页面
		model.addAttribute("action", "update");
		return "ztzyaqgl/zyaq/sxzy/form";
	}
	
	/**
	 * 修改
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(ZTAQGL_SxkjzyEntity sxzy, Model model){
		String datasuccess="success";	
		ZTAQGL_SxkjzyEntity sxzy2 = aqglSxzyService.findById(sxzy.getID());
		sxzy2.setM1(sxzy.getM1());
		sxzy2.setM2(sxzy.getM2());
		sxzy2.setM3(sxzy.getM3());
		sxzy2.setM4(sxzy.getM4());
		sxzy2.setM5(sxzy.getM5());
		sxzy2.setM6(sxzy.getM6());
		sxzy2.setM7(sxzy.getM7());
		sxzy2.setM8(sxzy.getM8());
		sxzy2.setM9(sxzy.getM9());
		sxzy2.setM10(sxzy.getM10());
		sxzy2.setM11(sxzy.getM11());
		sxzy2.setM12(sxzy.getM12());
		sxzy2.setM13(sxzy.getM13());
		sxzy2.setM14(sxzy.getM14());
		sxzy2.setM14_1(sxzy.getM14_1());
		sxzy2.setM15(sxzy.getM15());
		sxzy2.setM15_1(sxzy.getM15_1());
		sxzy2.setZt("0");
		sxzy2.setS2(new Timestamp(new java.util.Date().getTime()));
		aqglSxzyService.updateInfo(sxzy2);
		//返回结果
		return datasuccess;
	}

	/**
	 * 撤回受限空间作业
	 * @param request,model
	 */
	@RequestMapping(value = "revoke/{id}")
	@ResponseBody
	public String revoke(@PathVariable("id") Long id,  Model model){
		String datasuccess="success";
		ZTAQGL_SxkjzyEntity sxzy = aqglSxzyService.findById(id);
		sxzy.setZt("0");
		sxzy.setS2(new Timestamp(new java.util.Date().getTime()));

		aqglSxzyService.updateInfo(sxzy);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  作业安全--临时用电作业  【修改操作】");
		//返回结果
		return datasuccess;
	}

	/**
	 * 作废受限空间作业
	 * @param request,model
	 */
	@RequestMapping(value = "cancel/{id}")
	@ResponseBody
	public String cancel(@PathVariable("id") Long id,  Model model){
		String datasuccess="操作成功";
		ZTAQGL_SxkjzyEntity sxzy = aqglSxzyService.findById(id);
		sxzy.setZt("-2");
		sxzy.setS2(new Timestamp(new java.util.Date().getTime()));
		
		aqglSxzyService.updateInfo(sxzy);
		//返回结果
		return datasuccess;
	}

	/**
	 * 受限空间作业安全措施列表显示页面
	 * @param model
	 */
	@RequestMapping(value="updaqcsindex")
	public String updaqcsindex(Model model,HttpServletRequest request) {
		model.addAttribute("id1", request.getParameter("id1"));
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		return "ztzyaqgl/zyaq/sxzy/addaqcsindex";
	}

	/**
	 * 受限空间作业安全措施list 
	 * @param request
	 */
	@RequestMapping(value="aqcslist")
	@ResponseBody
	public Map<String, Object> aqcsData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		String type=request.getParameter("type");
		if(type!=null){
			map.put("id1", request.getParameter("id1"));
			map.put("m2", "2");
			return aqglaqcsService.ybzaqscdataGrid(map);	
		}else{
			map.put("m2", "2");
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
		return "ztzyaqgl/zyaq/sxzy/index_wlfchoose";
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
		String sxid=request.getParameter("sxid");
		String flag=request.getParameter("flag");
		List<ZTAQGL_Zyaq_Aqcs> list =JSON.parseArray(listjson, ZTAQGL_Zyaq_Aqcs.class);
        for(int i=0;i<list.size();i++){
	        try {
	        	ZTAQGL_Zyaq_Aqcs aqsc=list.get(i);
	        	aqglSxzyService.updateAqcs(aqsc);
			} catch (Exception e) {
				// TODO: handle exception
				datasuccess="failed";
			}
        }
        if(flag==null){
			//修改作业状态
        	ZTAQGL_SxkjzyEntity sxzy=aqglSxzyService.find(Long.parseLong(sxid));
	        sxzy.setM21(DateUtils.getSysTimestamp());
	        sxzy.setM21_4(request.getParameter("aqjd"));
	        sxzy.setM21_5(request.getParameter("sgfa"));
	        sxzy.setM21_6(request.getParameter("wlfids"));
	        sxzy.setZt("1");
	        aqglSxzyService.updateInfo(sxzy);
        }
		return datasuccess;
	}

	/**
	 * 作业单位确认页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "zydwqr/{id}", method = RequestMethod.GET)
	public String zydwqr(@PathVariable("id") Long id, Model model) {
		model.addAttribute("sxzyid", id);
		//返回页面
		model.addAttribute("action", "zydwqr");
		return "ztzyaqgl/zyaq/sxzy/zydwform";
	}	

	/**
	 *  作业单位确认
	 * @param request,model
	 */
	@RequestMapping(value = "zydwqr")
	@ResponseBody
	public String aqbm(long id, String M16, String M16_1, Model model, HttpServletRequest request){
		String datasuccess="success";	
		ZTAQGL_SxkjzyEntity sxzy = aqglSxzyService.find(id);
		sxzy.setZt("2");//待安技员审批
		sxzy.setM16(M16);
		sxzy.setM16_1(M16_1);
		sxzy.setM16_2(DateUtils.getSysTimestamp());
		sxzy.setM16_3(Long.parseLong(UserUtil.getCurrentShiroUser().getId().toString()));
		aqglSxzyService.updateInfo(sxzy);
		//返回结果
		return datasuccess;
	}

	/**
	 * 内部审批页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "nbsp/{id}", method = RequestMethod.GET)
	public String nbsp(@PathVariable("id") Long id, Model model) {
		model.addAttribute("sxzyid", id);
		//返回页面
		model.addAttribute("action", "nbsp");
		return "ztzyaqgl/zyaq/sxzy/nbspform";
	}

	/**
	 *  内部审批
	 * @param request,model
	 */
	@RequestMapping(value = "nbsp")
	@ResponseBody
	public String nbsp(long id, String suggest, String spflag,Model model, HttpServletRequest request){
		String datasuccess="success";	
		ZTAQGL_SxkjzyEntity sxzy = aqglSxzyService.find(id);
		Timestamp t= DateUtils.getSysTimestamp();//当前时间
		String zt=sxzy.getZt();//当前状态
		Long userid=Long.parseLong(UserUtil.getCurrentShiroUser().getId()+"");//用户时间
		String signpic= UserUtil.getCurrentShiroUser().getElecsignature();//用户的电子签章

		if(!sxzy.getM13().equals("特殊")){
			if(zt.equals("2")){//安技员审批
				if(spflag.equals("通过"))
					sxzy.setZt("4");//待安全科长审批
				else
					sxzy.setZt("-1");//退回编制人修改
				sxzy.setM17(suggest);//安技员意见
				sxzy.setM17_1(signpic);//签名图片
				sxzy.setM17_2(t);//审批时间
				sxzy.setM17_3(userid);//添加人id
				sxzy.setM17_4(spflag);//状态
			}
			
			if(zt.equals("4")){//安全科长审批
				if(spflag.equals("通过"))
					sxzy.setZt("6");//待确认安全措施
				else
					sxzy.setZt("-1");//退回编制人修改
				sxzy.setM18(request.getParameter("suggest").toString());//安全科长意见
				sxzy.setM18_1(signpic);//签名图片
				sxzy.setM18_2(t);//审批时间
				sxzy.setM18_3(userid);//添加人id
				sxzy.setM18_4(spflag);//状态
			}			

		}else{//特殊有限空间作业
			if(zt.equals("2")){
				if(spflag.equals("通过"))
					sxzy.setZt("3");//待部门一把手审批
				else
					sxzy.setZt("-1");//退回编制人修改
				sxzy.setM17(suggest);//安技员意见
				sxzy.setM17_1(signpic);//签名图片
				sxzy.setM17_2(t);//审批时间
				sxzy.setM17_3(userid);//添加人id
				sxzy.setM17_4(spflag);//状态
			}	
			
			if(zt.equals("3")){//部门一把手审批
				if(spflag.equals("通过"))
					sxzy.setZt("5");//待安全处分管领导审批
				else
					sxzy.setZt("-1");//退回编制人修改
				sxzy.setM19(suggest);//部门一把手意见
				sxzy.setM19_1(signpic);//签名图片
				sxzy.setM19_2(t);//审批时间
				sxzy.setM19_3(userid);//添加人id
				sxzy.setM19_4(spflag);//状态
			}

			if(zt.equals("5")){//安全处分管人员审批
				if(spflag.equals("通过"))
					sxzy.setZt("6");//待确认安全措施
				else
					sxzy.setZt("-1");//退回编制人修改
				sxzy.setM20(suggest);//安全处分管人员意见
				sxzy.setM20_1(signpic);//签名图片
				sxzy.setM20_2(t);//审批时间
				sxzy.setM20_3(userid);//添加人id
				sxzy.setM20_4(spflag);//状态
			}
		}
		aqglSxzyService.updateInfo(sxzy);
		//返回结果
		return datasuccess;
	}

	/**
	 * 受限空间作业确认安全措施列表显示页面
	 * @param model
	 */
	@RequestMapping(value="qraqcsindex")
	public String qraqcsindex(Model model,HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("id1", request.getParameter("id1"));
		map.put("m2", 2);
		model.addAttribute("id1", request.getParameter("id1"));
		model.addAttribute("action", "csqr");
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		model.addAttribute("aqcslist", JsonMapper.getInstance().toJson(aqglaqcsService.ybzaqsc(map)));
		return "ztzyaqgl/zyaq/sxzy/qrcsform";
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
        	ZTAQGL_Zyaq_Aqcs aqcs=aqglSxzyService.findAqcs(Long.parseLong(csid[i]));
        	aqcs.setM3(m3);
        	aqcs.setM4(xcphoto);
        	aqglSxzyService.updateAqcs(aqcs);
		}
		//修改受限空间作业状态
		String sxid=request.getParameter("sxid");
		ZTAQGL_SxkjzyEntity sxzy=aqglSxzyService.find(Long.parseLong(sxid));
		sxzy.setM21_7(DateUtils.getSysTimestamp());
		sxzy.setM21_2(request.getParameter("signpic"));
		sxzy.setM21_3(UserUtil.getCurrentShiroUser().getElecsignature());//许可人电子签章
		sxzy.setZt("7");//待分析
        aqglSxzyService.updateInfo(sxzy);
		//返回结果
		return datasuccess;
	}	

	/**
	 * 受限作业完工签字显示页面
	 * @param model
	 */
	@RequestMapping(value="wgqzindex")
	public String wgqzindex(Model model,HttpServletRequest request) {
		Timestamp t= DateUtils.getSysTimestamp();//确认时间
		model.addAttribute("wgsj", t);
		model.addAttribute("action", "wgqz");
		model.addAttribute("sxid", request.getParameter("id1"));
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		return "ztzyaqgl/zyaq/sxzy/wgqzform";
	}
	
	//完工签字
	@RequestMapping(value="wgqz")
	@ResponseBody
	public String wgqz(HttpServletRequest request){
		String datasuccess="success";
		ZTAQGL_SxkjzyEntity sxzy=aqglSxzyService.find(Long.parseLong(request.getParameter("sxid")));
		Timestamp t= DateUtils.getSysTimestamp();//确认时间
		String signpic=request.getParameter("signpic").toString();//签名图片
		Long userid=Long.parseLong(UserUtil.getCurrentShiroUser().getId()+"");
		if(sxzy.getZt().equals("8")){
			sxzy.setZt("9");//待分厂完工签字
			sxzy.setM22(t);//作业单位确认时间
			sxzy.setM22_1(signpic);//签名图片
			sxzy.setM22_2(userid);//添加人id
		}else if(sxzy.getZt().equals("9")){
			sxzy.setZt("10");//完工
			sxzy.setM23(t);//分厂完工确认时间
			sxzy.setM23_1(signpic);//分厂完工图片
			sxzy.setM23_2(userid);//添加人id
		}
		aqglSxzyService.updateInfo(sxzy);//修改受限空间作业
		return datasuccess;
	}
	
	/**
	 * 删除
	 */
	@RequiresPermissions("zyaqgl:sxzy:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  作业安全--临时用电作业  【删除操作】,删除的id是:"+arrids[i]);
			aqglSxzyService.deleteInfo(Long.parseLong(arrids[i]));
			//删除相应分析数据
			aqglSxzyFxService.deleteInfoByid1(Long.parseLong(arrids[i]));
		}
		
		return datasuccess;
	}
	
	
	/**
	 * 分析页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "fx/{id}", method = RequestMethod.GET)
	public String fx(@PathVariable("id") Long id, Model model) {
		model.addAttribute("sxzyid", id);
		model.addAttribute("username", UserUtil.getCurrentUser().getName());
		//返回页面
		model.addAttribute("action", "fx");
		return "ztzyaqgl/zyaq/sxzy/fxform";
	}
	
	/**
	 * 分析
	 * @param request,model
	 */
	@RequestMapping(value = "fx")
	@ResponseBody
	public String fx(Long id,String M24, String M25, String M26, String M27,Model model, HttpServletRequest request){
		String datasuccess="success";	
		Timestamp t= DateUtils.getSysTimestamp();
		String type[] = request.getParameterValues("type");//类型
		String media[] = request.getParameterValues("media");//介质
		String number[] = request.getParameterValues("number");//数值
		String unit[] = request.getParameterValues("unit");//单位
		String part[] = request.getParameterValues("part");//部位
		String person[] = request.getParameterValues("person");//分析人
		String xczp[] = request.getParameterValues("xczp");//现场照片
		//循环插入分析表
		if(type != null){
			for(int i=0;i<type.length;i++){
				ZTAQGL_SxkjzyFxEntity sxfx = new ZTAQGL_SxkjzyFxEntity();
				sxfx.setID1(id);
				sxfx.setM1(type[i]);
				sxfx.setM2(media[i]);
				sxfx.setM3(number[i]);
				sxfx.setM4(unit[i]);
			    sxfx.setM5(t);
				sxfx.setM6(part[i]);
				sxfx.setM7(person[i]);
				sxfx.setM8(xczp[i]);
				aqglSxzyFxService.addInfo(sxfx);
			}
		}
		
		//修改作业证状态
		ZTAQGL_SxkjzyEntity sxzy = aqglSxzyService.findById(id);
		sxzy.setM21_8(t);//分析时间
		sxzy.setM24(M24);//分析时间
		sxzy.setM25(M25);//分析时间
		sxzy.setM26(M26);//分析时间
		sxzy.setM27(M27);//分析时间
		sxzy.setZt("8");//待作业单位完工签字
		aqglSxzyService.updateInfo(sxzy);
		//返回结果
		return datasuccess;
	}

	/**
	 * 受限作业安全措施列表显示页面
	 * @param model
	 */
	@RequestMapping(value="aqcsindex")
	public String aqcsindex(Model model,HttpServletRequest request) {
		model.addAttribute("id1", request.getParameter("id1"));
		model.addAttribute("type", request.getParameter("type"));
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		return "ztzyaqgl/zyaq/sxzy/aqcsindex";
	}

	/**
	 * 分析数据list 
	 * @param request
	 */
	@RequestMapping(value="sxzyfxlist")
	@ResponseBody
	public Map<String, Object> getFxsjData(HttpServletRequest request) {
		String sxzyid = request.getParameter("sxzyid");
		return aqglSxzyFxService.dataGrid(sxzyid);	
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String,Object> sxzy = aqglSxzyService.findallById(id);
		if(sxzy.get("m21_6")!=null){
			List<Map<String,Object>> wlf = aqglSxzyService.findallWlfIds(sxzy.get("m21_6").toString()+"0");
			model.addAttribute("wlflist", JsonMapper.getInstance().toJson(wlf));
		}
		model.addAttribute("sxzy", sxzy);
		return "ztzyaqgl/zyaq/sxzy/view";
	}
	
	/**
	 * 查看状态页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "viewzt/{id}", method = RequestMethod.GET)
	public String viewzt(@PathVariable("id") Long id, Model model) {
		Map<String,Object> sxzy = aqglSxzyService.findallById(id);
		model.addAttribute("sxzy", sxzy);
		//根据作业证id查找分析数据详情
		List<Map<String,Object>> sxfxs = aqglSxzyFxService.findAllByid1(id);
		model.addAttribute("sxfxs", sxfxs);
		return "ztzyaqgl/zyaq/sxzy/viewzt";
	}
	
	/**
	 * 显示所有列
	 * 
	 * @param id,model
	 */
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","ztzyaqgl/sxzy/export");
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
		map.put("m0", request.getParameter("zyaq_sxzy_m0"));
		map.put("m3", request.getParameter("zyaq_sxzy_m3"));
		map.put("zt", request.getParameter("zyaq_sxzy_zt"));
		map.put("qyname", request.getParameter("zyaq_sxzy_qyname"));//企业名
		map.putAll(getAuthorityMap());
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		aqglSxzyService.exportExcel(response, map);
	}
	
	/**
	 * 导出受限空间作业word
	 */
	@RequestMapping(value = "exportword/{id}")
	@ResponseBody
	public String getexportword(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		String webAddress= request.getSession().getServletContext().getRealPath("/").replace(request.getContextPath().replace("/","\\"),"");
		Map<String, Object> map = aqglSxzyService.getExportWord(id,webAddress);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "sxzy.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
}
