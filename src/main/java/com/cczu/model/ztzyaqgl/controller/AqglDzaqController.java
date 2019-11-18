package com.cczu.model.ztzyaqgl.controller;

import com.alibaba.fastjson.JSON;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.model.ztzyaqgl.entity.ZTAQGL_Dzaqzy;
import com.cczu.model.ztzyaqgl.entity.ZTAQGL_Zyaq_Aqcs;
import com.cczu.model.ztzyaqgl.service.AqglAqcsService;
import com.cczu.model.ztzyaqgl.service.AqglDzaqService;
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
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
 * 安全生产-吊装安全作业Controller
 * @author YZH
 */
@Controller
@RequestMapping("ztzyaqgl/dzaq")
public class AqglDzaqController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private AqglDzaqService aqgldzaqService;
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
		return "ztzyaqgl/zyaq/dzaq/index";
	}
	
	/**
	 * 数据list 
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("m1", request.getParameter("aqgl_dzaq_cx_m1"));//作业证编号
		map.put("m7", request.getParameter("aqgl_dzaq_cx_m7"));//吊装安全作业级别
		map.put("zt", request.getParameter("aqgl_dzaq_cx_zt"));//吊装安全作业状态
		map.putAll(getAuthorityMap());

		//如果选择了部门下拉，则覆盖前面的部门条件，但也仅限于该用户所能看到的部门范围
		String depid=request.getParameter("aqgl_dzaq_cx_depid");
		if(depid!=null&&(!depid.equals(""))){
			Department dep=departmentService.get(Long.parseLong(depid));
			map.put("depcode3", dep.getCode());
		}
		map.put("dspzt", request.getParameter("spzt"));//当前审批人待审批的状态
		return aqgldzaqService.dataGrid(map);	
		
	}

	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("zyaqgl:dzaq:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model, HttpServletRequest request) {
		String code="DZAQ-"+ DateUtils.getDateRandom();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("m1", code);
		map.put("bzr", UserUtil.getCurrentUser().getName());//编制人
		model.addAttribute("dzaq", map);
		model.addAttribute("action", "create");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "ztzyaqgl/zyaq/dzaq/form";
	}

	/**
	 * 添加吊装安全作业信息
	 * @param request,model
	 */
	@RequiresPermissions("zyaqgl:dzaq:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(ZTAQGL_Dzaqzy entity, Model model) {
		String datasuccess = "success";
		Timestamp t= DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		entity.setZt("0");//添加初始状态
		entity.setID1(UserUtil.getCurrentShiroUser().getQyid());
		//申请人id
		entity.setM20_1(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));
		//添加吊装安全作业
		aqgldzaqService.addInfo(entity);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  作业安全--吊装安全作业  【添加操作】");
		// 返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的吊装安全作业信息
		Map<String, Object> dzaq = aqgldzaqService.findInforById(id);
		model.addAttribute("dzaq", dzaq);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "ztzyaqgl/zyaq/dzaq/form";
	}
	
	/**
	 * 修改吊装安全作业信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(ZTAQGL_Dzaqzy entity, Model model){
		String datasuccess="success";	
		ZTAQGL_Dzaqzy dzaq = aqgldzaqService.findById(entity.getID());
		dzaq.setM1(entity.getM1());
		dzaq.setM2(entity.getM2());
		dzaq.setM3(entity.getM3());
		dzaq.setM4(entity.getM4());
		dzaq.setM4_1(entity.getM4_1());
		dzaq.setM5(entity.getM5());
		dzaq.setM5_1(entity.getM5_1());
		dzaq.setM6(entity.getM6());
		dzaq.setM7(entity.getM7());
		dzaq.setM8(entity.getM8());
		dzaq.setM9(entity.getM9());
		dzaq.setM10(entity.getM10());
		dzaq.setM11(entity.getM11());
		dzaq.setM12(entity.getM12());
		dzaq.setM13(entity.getM13());
		dzaq.setM13_1(entity.getM13_1());
		dzaq.setZt("0");
		dzaq.setS2(new Timestamp(new java.util.Date().getTime()));

		aqgldzaqService.updateInfo(dzaq);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  作业安全--吊装安全作业  【修改操作】");
		//返回结果
		return datasuccess;
	}

	/**
	 * 撤回吊装安全作业
	 * @param request,model
	 */
	@RequestMapping(value = "revoke/{id}")
	@ResponseBody
	public String revoke(@PathVariable("id") Long id,  Model model){
		String datasuccess="success";
		ZTAQGL_Dzaqzy dzaq = aqgldzaqService.findById(id);
		dzaq.setZt("0");
		dzaq.setS2(new Timestamp(new java.util.Date().getTime()));

		aqgldzaqService.updateInfo(dzaq);
		//返回结果
		return datasuccess;
	}

	/**
	 * 作废吊装安全作业
	 * @param request,model
	 */
	@RequestMapping(value = "cancel/{id}")
	@ResponseBody
	public String cancel(@PathVariable("id") Long id,  Model model){
		String datasuccess="操作成功";
		ZTAQGL_Dzaqzy dzaq = aqgldzaqService.findById(id);
		dzaq.setZt("-2");
		dzaq.setS2(new Timestamp(new java.util.Date().getTime()));
		
		aqgldzaqService.updateInfo(dzaq);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 吊装安全作业安全措施列表显示页面
	 * @param model
	 */
	@RequestMapping(value="aqcsindex")
	public String aqcsindex(Model model,HttpServletRequest request) {
		model.addAttribute("id1", request.getParameter("id1"));
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		return "ztzyaqgl/zyaq/dzaq/aqcsindex";
	}

	/**
	 * 吊装安全作业安全措施列表显示页面
	 * @param model
	 */
	@RequestMapping(value="updaqcsindex")
	public String updaqcsindex(Model model,HttpServletRequest request) {
		model.addAttribute("id1", request.getParameter("id1"));
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		return "ztzyaqgl/zyaq/dzaq/addaqcsindex";
	}
	
	/**
	 * 吊装安全作业安全措施list 
	 * @param request
	 */
	@RequestMapping(value="aqcslist")
	@ResponseBody
	public Map<String, Object> aqcsData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		String type=request.getParameter("type");
		if(type!=null){
			map.put("id1", request.getParameter("id1"));
			map.put("m2", "5");
			return aqglaqcsService.ybzaqscdataGrid(map);	
		}else{
			map.put("m2", "5");
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
		return "ztzyaqgl/zyaq/dzaq/index_wlfchoose";
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
		String dzid=request.getParameter("dzid");
		String flag=request.getParameter("flag");
		List<ZTAQGL_Zyaq_Aqcs> list =JSON.parseArray(listjson, ZTAQGL_Zyaq_Aqcs.class);
        for(int i=0;i<list.size();i++){
	        try {
	        	ZTAQGL_Zyaq_Aqcs aqsc=list.get(i);
	        	aqgldzaqService.updateAqcs(aqsc);
			} catch (Exception e) {
				// TODO: handle exception
				datasuccess="failed";
			}
        }
        if(flag==null){
			//修改吊装安全状态
	        ZTAQGL_Dzaqzy dzaq=aqgldzaqService.find(Long.parseLong(dzid));
	        dzaq.setM20(DateUtils.getSysTimestamp());
	        dzaq.setM20_4(request.getParameter("aqjd"));
	        dzaq.setM20_5(request.getParameter("sgfa"));
	        dzaq.setM20_6(request.getParameter("wlfids"));
	        dzaq.setZt("1");
	        aqgldzaqService.updateInfo(dzaq);
        }
		return datasuccess;
	}

	/**
	 * 作业单位确认页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "zydwqr/{id}", method = RequestMethod.GET)
	public String zydwqr(@PathVariable("id") Long id, Model model) {
		model.addAttribute("dzaqid", id);
		//返回页面
		model.addAttribute("action", "zydwqr");
		return "ztzyaqgl/zyaq/dzaq/zydwform";
	}	

	/**
	 *  作业单位确认
	 * @param request,model
	 */
	@RequestMapping(value = "zydwqr")
	@ResponseBody
	public String aqbm(long id, String M14, String M14_1, Model model, HttpServletRequest request){
		String datasuccess="success";	
		ZTAQGL_Dzaqzy dzaq = aqgldzaqService.find(id);
		dzaq.setZt("2");//待安技员审批
		dzaq.setM14(M14);
		dzaq.setM14_1(M14_1);
		dzaq.setM14_2(DateUtils.getSysTimestamp());
		dzaq.setM14_3(Long.parseLong(UserUtil.getCurrentShiroUser().getId().toString()));
		aqgldzaqService.updateInfo(dzaq);
		//返回结果
		return datasuccess;
	}

	/**
	 * 内部审批页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "nbsp/{id}", method = RequestMethod.GET)
	public String nbsp(@PathVariable("id") Long id, Model model) {
		model.addAttribute("dzaqid", id);
		//返回页面
		model.addAttribute("action", "nbsp");
		return "ztzyaqgl/zyaq/dzaq/nbspform";
	}	

	/**
	 *  内部审批
	 * @param request,model
	 */
	@RequestMapping(value = "nbsp")
	@ResponseBody
	public String nbsp(long id, String suggest, String spflag,Model model, HttpServletRequest request){
		String datasuccess="success";	
		ZTAQGL_Dzaqzy dzaq = aqgldzaqService.find(id);
		Timestamp t= DateUtils.getSysTimestamp();//当前时间
		String zt=dzaq.getZt();//当前状态
		Long userid=Long.parseLong(UserUtil.getCurrentShiroUser().getId()+"");//用户时间
		String signpic= UserUtil.getCurrentShiroUser().getElecsignature();//用户的电子签章
		
			if(zt.equals("2")){//安技员审批
				if(spflag.equals("通过")){
					if(dzaq.getM7().equals("一级吊装"))
						dzaq.setZt("3");//待部门一把手审批
					else
						dzaq.setZt("4");//待安全科长审批
				}
				else
					dzaq.setZt("-1");//退回编制人修改
				dzaq.setM15(suggest);//安技员意见
				dzaq.setM15_1(signpic);//签名图片
				dzaq.setM15_2(t);//审批时间
				dzaq.setM15_3(userid);//添加人id
				dzaq.setM15_4(spflag);//状态
			}
			
			if(zt.equals("3")){//部门一把手审批
				if(spflag.equals("通过"))
					dzaq.setZt("6");//待安全处分管领导
				else
					dzaq.setZt("-1");//退回编制人修改
				dzaq.setM16(request.getParameter("suggest").toString());//安全科长意见
				dzaq.setM16_1(signpic);//签名图片
				dzaq.setM16_2(t);//审批时间
				dzaq.setM16_3(userid);//添加人id
				dzaq.setM16_4(spflag);//状态
			}			

			if(zt.equals("4")){//安全科长审批
				if(spflag.equals("通过")){
					if(dzaq.getM7().equals("三级吊装"))
						dzaq.setZt("7");//待确认措施
					else //二级吊装
						dzaq.setZt("5");//待安全处分管人员
				}
				else
					dzaq.setZt("-1");//退回编制人修改
				dzaq.setM17(suggest);//安技员意见
				dzaq.setM17_1(signpic);//签名图片
				dzaq.setM17_2(t);//审批时间
				dzaq.setM17_3(userid);//添加人id
				dzaq.setM17_4(spflag);//状态
			}
			
			if(zt.equals("5")){//安全处分管人员审批
				if(spflag.equals("通过"))
					dzaq.setZt("7");//待确认措施
				else
					dzaq.setZt("-1");//退回编制人修改
				dzaq.setM18(suggest);//安技员意见
				dzaq.setM18_1(signpic);//签名图片
				dzaq.setM18_2(t);//审批时间
				dzaq.setM18_3(userid);//添加人id
				dzaq.setM18_4(spflag);//状态
			}
			
			if(zt.equals("6")){//安全处分管领导审批
				if(spflag.equals("通过"))
					dzaq.setZt("7");//待确认措施
				else
					dzaq.setZt("-1");//退回编制人修改
				dzaq.setM19(suggest);//安技员意见
				dzaq.setM19_1(signpic);//签名图片
				dzaq.setM19_2(t);//审批时间
				dzaq.setM19_3(userid);//添加人id
				dzaq.setM19_4(spflag);//状态
			}			
		aqgldzaqService.updateInfo(dzaq);
		//返回结果
		return datasuccess;
	}

	/**
	 * 吊装安全作业确认安全措施列表显示页面
	 * @param model
	 */
	@RequestMapping(value="qraqcsindex")
	public String qraqcsindex(Model model,HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("id1", request.getParameter("id1"));
		map.put("m2", 5);
		model.addAttribute("id1", request.getParameter("id1"));
		model.addAttribute("action", "csqr");
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		model.addAttribute("aqcslist", JsonMapper.getInstance().toJson(aqglaqcsService.ybzaqsc(map)));
		return "ztzyaqgl/zyaq/dzaq/qrcsform";
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
        	ZTAQGL_Zyaq_Aqcs aqcs=aqgldzaqService.findAqcs(Long.parseLong(csid[i]));
        	aqcs.setM3(m3);
        	aqcs.setM4(xcphoto);
        	aqgldzaqService.updateAqcs(aqcs);        	
		}
		//修改吊装安全状态
		String dzid=request.getParameter("dzid");
        ZTAQGL_Dzaqzy dzaq=aqgldzaqService.find(Long.parseLong(dzid));
        dzaq.setM20_7(DateUtils.getSysTimestamp());
        dzaq.setM20_2(request.getParameter("signpic"));
        dzaq.setM20_3(UserUtil.getCurrentShiroUser().getElecsignature());//许可人电子签章
        dzaq.setZt("8");//待作业单位完工签字
        aqgldzaqService.updateInfo(dzaq);
		//返回结果
		return datasuccess;
	}

	/**
	 * 吊装安全作业完工签字显示页面
	 * @param model
	 */
	@RequestMapping(value="wgqzindex")
	public String wgqzindex(Model model,HttpServletRequest request) {
		Timestamp t= DateUtils.getSysTimestamp();//确认时间
		model.addAttribute("wgsj", t);
		model.addAttribute("action", "wgqz");
		model.addAttribute("dzid", request.getParameter("id1"));
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		return "ztzyaqgl/zyaq/dzaq/wgqzform";
	}
	
	//完工签字
	@RequestMapping(value="wgqz")
	@ResponseBody
	public String wgqz(HttpServletRequest request){
		String datasuccess="success";
		ZTAQGL_Dzaqzy dzaq=aqgldzaqService.find(Long.parseLong(request.getParameter("dzid")));
		Timestamp t= DateUtils.getSysTimestamp();//确认时间
		String signpic=request.getParameter("signpic").toString();//签名图片
		Long userid=Long.parseLong(UserUtil.getCurrentShiroUser().getId()+"");
		if(dzaq.getZt().equals("8")){
			dzaq.setZt("9");//待分厂完工签字
			dzaq.setM21(t);//作业单位确认时间
			dzaq.setM21_1(signpic);//签名图片
			dzaq.setM21_2(userid);//添加人id
		}else if(dzaq.getZt().equals("9")){
			dzaq.setZt("10");//完工
			dzaq.setM22(t);//分厂完工确认时间
			dzaq.setM22_1(signpic);//分厂完工图片
			dzaq.setM22_2(userid);//添加人id
		}
		aqgldzaqService.updateInfo(dzaq);//修改吊装安全作业
		return datasuccess;
	}
	
	/**
	 * 删除吊装安全作业信息
	 */
	@RequiresPermissions("zyaqgl:dzaq:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  作业安全--吊装安全作业  【删除操作】 ,删除的id是:"+arrids[i]);
			aqgldzaqService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String,Object> dzaq = aqgldzaqService.findallById(id);
		if(dzaq.get("m20_6")!=null){
			List<Map<String,Object>> wlf = aqgldzaqService.findallWlfIds(dzaq.get("m20_6").toString()+"0");
			model.addAttribute("wlflist", JsonMapper.getInstance().toJson(wlf));
		}
		model.addAttribute("dzaq", dzaq);
		return "ztzyaqgl/zyaq/dzaq/view";
	}
	
	/**
	 * 查看状态页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "viewzt/{id}", method = RequestMethod.GET)
	public String viewzt(@PathVariable("id") Long id, Model model) {
		Map<String,Object> dzaq = aqgldzaqService.findallById(id);
		model.addAttribute("dzaq", dzaq);
		return "ztzyaqgl/zyaq/dzaq/viewzt";
	}
	
	/**
	 * 显示所有列
	 * 
	 * @param id,model
	 */
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","ztzyaqgl/dzaq/export");
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
		aqgldzaqService.exportExcel(response, map);
	}
	
	/**
	 * 导出吊装安全作业word
	 */
	@RequestMapping(value = "exportword/{id}")
	@ResponseBody
	public String getexportword(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		String webAddress= request.getSession().getServletContext().getRealPath("/").replace(request.getContextPath().replace("/","\\"),"");
		Map<String, Object> map = aqgldzaqService.getExportWord(id,webAddress);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "dzaq.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
}
