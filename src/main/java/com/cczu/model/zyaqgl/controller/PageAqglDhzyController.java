package com.cczu.model.zyaqgl.controller;

import com.alibaba.fastjson.JSON;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.model.zyaqgl.entity.*;
import com.cczu.model.zyaqgl.service.*;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.Message;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.MessageUtil;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;
import org.apache.commons.lang3.StringUtils;
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
import java.util.*;

/**
 * 安全生产-动火作业Controller
 * @author YZH
 */
@Controller
@RequestMapping("zyaqgl/dhzy")
public class PageAqglDhzyController extends BaseController {

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
    private IBisQyjbxxService bisQyjbxxService;
    @Autowired
    private AqglTzzyryService aqgltzzyryService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model,HttpServletRequest request) {
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		model.addAttribute("qyid", request.getParameter("qyid"));
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type", "2");
				else
					model.addAttribute("type", "1");
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			model.addAttribute("type", "2");
		}	
		return "zyaqgl/zyaq/dhzy/index";
	}
	
	/**
	 * 数据list 
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("m1", request.getParameter("aqgl_dhzy_cx_m1"));//作业证编号
		map.put("m3", request.getParameter("aqgl_dhzy_cx_m3"));//动火作业级别
		map.put("zt", request.getParameter("aqgl_dhzy_cx_zt"));//动火作业级别
		map.put("qyname", request.getParameter("zyaqgl_bgsq_qy_name"));//企业名
		map.putAll(getAuthorityMap());

		//安监端条件
		String qyid = request.getParameter("qyid");
		if(StringUtils.isNotEmpty(qyid))
			map.put("qyid", qyid);
		return aqgldhzyService.dataGrid(map);	
		
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("zyaqgl:dhzy:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model, HttpServletRequest request) {
		String code="DHZY-"+DateUtils.getDateRandom();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("m1", code);
		map.put("sqr", UserUtil.getCurrentUser().getName());//申请人
		model.addAttribute("dhzy", map);
		model.addAttribute("action", "create");
		model.addAttribute("sqr", UserUtil.getCurrentUser().getName());//申请人
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        model.addAttribute("qyid", UserUtil.getCurrentShiroUser().getQyid());
		return "zyaqgl/zyaq/dhzy/form";
	}
	
	/**
	 * 添加动火作业信息
	 * @param entity,model
	 */
	@RequiresPermissions("zyaqgl:dhzy:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(AQGL_FireWork entity, Model model) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		entity.setZt("0");//添加初始状态
		//企业id
//		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
//			entity.setID1(UserUtil.getCurrentShiroUser().getQyid());
//		}
		//申请人id
		entity.setID2(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));
		//添加动火作业
		aqgldhzyService.addInfo(entity);
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
		model.addAttribute("sqr", dhzy.get("sqr").toString());//申请人
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        model.addAttribute("qyid", UserUtil.getCurrentShiroUser().getQyid());
        return "zyaqgl/zyaq/dhzy/form";
	}
	
	/**
	 * 修改动火作业信息
	 * @param entity,model
	 */
	@RequiresPermissions("zyaqgl:dhzy:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQGL_FireWork entity,  Model model){
		String datasuccess="success";	
		AQGL_FireWork dhzy = aqgldhzyService.findById(entity.getID());
		dhzy.setM1(entity.getM1());
		dhzy.setM2(entity.getM2());
		dhzy.setM3(entity.getM3());
		dhzy.setM4(entity.getM4());
		dhzy.setM5(entity.getM5());
		dhzy.setM6(entity.getM6());
		dhzy.setM7(entity.getM7());
		dhzy.setM8(entity.getM8());
//		dhzy.setM8_id(entity.getM8_id());
		dhzy.setM9(entity.getM9());
//		dhzy.setM9_id(entity.getM9_id());
		dhzy.setM10(entity.getM10());
		dhzy.setM11(entity.getM11());
		dhzy.setM11_1(entity.getM11_1());
		dhzy.setM12(entity.getM12());
		dhzy.setM29(entity.getM29());
		dhzy.setID3(entity.getID3());
		entity.setS2(new java.sql.Timestamp(new java.util.Date().getTime()));
		
		aqgldhzyService.updateInfo(dhzy);
		//返回结果
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
		model.addAttribute("dhzy", dhzy);
		return "zyaqgl/zyaq/dhzy/view";
	}
	
	/**
	 * 分配任务页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "fprw/{id}", method = RequestMethod.GET)
	public String fprw(@PathVariable("id") Long id, Model model) {
		Map<String,Object> dhzy = aqgldhzyService.findallById(id);
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
		model.addAttribute("usertype", usertype);
		model.addAttribute("dhzy", dhzy);
		//返回页面
		model.addAttribute("action", "fprw");
		return "zyaqgl/zyaq/dhzy/fprwform";
	}
	
	/**
	 * 分配任务
	 * @param dhzy,model
	 */
	@RequestMapping(value = "fprw")
	@ResponseBody
	public String fprw(AQGL_FireWork dhzy,  Model model){
		String datasuccess="success";	
		//修改状态
		dhzy.setZt("1");
		dhzy.setM27(UserUtil.getCurrentUser().getId()+"");
		dhzy.setM27_1(DateUtils.getSysTimestamp());
		aqgldhzyService.updateInfo(dhzy);

		//返回结果
		return datasuccess;
	}
	
	/**
	 * 分析页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "fx/{id}", method = RequestMethod.GET)
	public String fx(@PathVariable("id") Long id, Model model) {
		model.addAttribute("dhzyid", id);
		//返回页面
		model.addAttribute("action", "fx");
		return "zyaqgl/zyaq/dhzy/fxform";
	}
	
	/**
	 * 分析
	 * @param request,model
	 */
	@RequestMapping(value = "fx")
	@ResponseBody
	public String fx(Long id, Model model, HttpServletRequest request){
		String datasuccess="success";	
		String name[] = request.getParameterValues("name");
		String data[] = request.getParameterValues("data");
		String time[] = request.getParameterValues("time");
		String person[] = request.getParameterValues("person");
		if(name!=null){
			//循环插入分析表
			for(int i=0;i<name.length;i++){
				AQGL_DhzyFxEntity sxfx = new AQGL_DhzyFxEntity();
				sxfx.setID1(id);
				sxfx.setM1(name[i]);
				sxfx.setM2(data[i]);
				sxfx.setM3(StringUtils.isBlank(time[i])?null:Timestamp.valueOf(time[i]));
				sxfx.setM4(person[i]);
				aqgldhzyfxService.addInfo(sxfx);
			}
		}
		//修改作业证状态
		AQGL_FireWork dhzy = aqgldhzyService.findById(id);
		dhzy.setZt("2");
		dhzy.setM14(UserUtil.getCurrentUser().getId()+"");
		aqgldhzyService.updateInfo(dhzy);

		Map<String,Object>  msgmap = new HashMap<String,Object>();
		msgmap.put(Message.MSGTARGET_H5,"weixin/enterprise/zyaqgl/dhzy/donghuo.jsp");
		msgmap.put(Message.MSGTARGET_PC,"zyaqgl/dhzy/index");
		MessageUtil.sendMsg(dhzy.getM15(), UserUtil.getCurrentShiroUser().getId()+"", "您有一条动火作业需要审批！", Message.MessageType.DSP.getMsgType(),JSON.toJSONString(msgmap),"编号为\""+dhzy.getM1()+"\"的动火作业需要审批");

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
		return "zyaqgl/zyaq/dhzy/aqcsindex";
	}
	
	/**
	 * 动火作业安全措施list 
	 * @param request
	 */
	@RequiresPermissions("zyaqgl:dhzy:view")
	@RequestMapping(value="aqcslist")
	@ResponseBody
	public Map<String, Object> aqcsData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		String type=request.getParameter("type");
		if(type!=null){
			map.put("id1", request.getParameter("id1"));
			return aqglaqcsService.bzaqscdataGrid(map);	
		}else{
			return aqglaqcsService.aqscdataGrid(map);
		}
		
	}
	
	/**
	 * 编制安全措施信息
	 * @param id1,model
	 */
	@RequiresPermissions("zyaqgl:dhzy:add")
	@RequestMapping(value = "createAqcs/{id1}", method = RequestMethod.GET)
	@ResponseBody
	public String createAqcs(@PathVariable("id1") Long id1,HttpServletRequest request) {
		String datasuccess="编制成功";
		//批量关联安全措施
        String listjson=request.getParameter("list");
        List<AQGL_Zyaq_Aqcs> list =JSON.parseArray(listjson, AQGL_Zyaq_Aqcs.class);
		for(int i=0;i<list.size();i++){
			AQGL_Zyaq_Aqcs aqcs=list.get(i);
			aqcs.setID1(id1);
			aqcs.setM2("1");
			aqgldhzyService.addAqcs(aqcs);
		}
		//修改动火状态
		AQGL_FireWork dhzy=aqgldhzyService.find(id1);
		dhzy.setM15_1(DateUtils.getSysTimestamp());
		dhzy.setZt("3");
		aqgldhzyService.updateInfo(dhzy);

		Map<String,Object>  msgmap = new HashMap<String,Object>();
		msgmap.put(Message.MSGTARGET_H5,"weixin/enterprise/zyaqgl/dhzy/donghuo.jsp");
		msgmap.put(Message.MSGTARGET_PC,"zyaqgl/dhzy/index");
		MessageUtil.sendMsg(dhzy.getM16(), UserUtil.getCurrentShiroUser().getId()+"", "您有一条动火作业需要审批！", Message.MessageType.DSP.getMsgType(),JSON.toJSONString(msgmap),"编号为\""+dhzy.getM1()+"\"的动火作业需要审批");

		return datasuccess;
	}
	
	/**
	 * 确认安全措施信息
	 * @param request,model
	 */
	@RequiresPermissions("zyaqgl:dhzy:add")
	@RequestMapping(value = "confirmAqcs")
	@ResponseBody
	public String confirmAqcs(HttpServletRequest request) {
		String datasuccess="已确认安全措施";
		String listjson=request.getParameter("list");
		long id=0l;
        List<AQGL_Zyaq_Aqcs> list =JSON.parseArray(listjson, AQGL_Zyaq_Aqcs.class);
        for(int i=0;i<list.size();i++){
	        try {
	        	AQGL_Zyaq_Aqcs aqsc=list.get(i);
	        	id=aqsc.getID1();
	        	aqgldhzyService.updateAqcs(aqsc);
			} catch (Exception e) {
				// TODO: handle exception
				datasuccess="failed";
			}
        }
		//修改动火状态
		AQGL_FireWork dhzy=aqgldhzyService.find(id);
		dhzy.setM16_1(DateUtils.getSysTimestamp());
		dhzy.setZt("4");
		aqgldhzyService.updateInfo(dhzy);

		Map<String,Object>  msgmap = new HashMap<String,Object>();
		msgmap.put(Message.MSGTARGET_H5,"weixin/enterprise/zyaqgl/dhzy/donghuo.jsp");
		msgmap.put(Message.MSGTARGET_PC,"zyaqgl/dhzy/index");
		MessageUtil.sendMsg(dhzy.getM13(), UserUtil.getCurrentShiroUser().getId()+"", "您有一条动火作业需要审批！", Message.MessageType.DSP.getMsgType(),JSON.toJSONString(msgmap),"编号为\""+dhzy.getM1()+"\"的动火作业需要审批");

		return datasuccess;
	}
	
	/**
	 * 生产单位确认
	 */
	@RequestMapping(value = "scdwqr/{id}")
	@ResponseBody
	public String scdw(@PathVariable("id") long id) {
		String datasuccess="确认成功";
		AQGL_FireWork dhzy = aqgldhzyService.findById(id);
		dhzy.setM13_1(DateUtils.getSysTimestamp());
		dhzy.setZt("5");
		aqgldhzyService.updateInfo(dhzy);

		Map<String,Object>  msgmap = new HashMap<String,Object>();
		msgmap.put(Message.MSGTARGET_H5,"weixin/enterprise/zyaqgl/dhzy/donghuo.jsp");
		msgmap.put(Message.MSGTARGET_PC,"zyaqgl/dhzy/index");
		MessageUtil.sendMsg(dhzy.getM17(), UserUtil.getCurrentShiroUser().getId()+"", "您有一条动火作业需要审批！", Message.MessageType.DSP.getMsgType(),JSON.toJSONString(msgmap),"编号为\""+dhzy.getM1()+"\"的动火作业需要审批");

		return datasuccess;
	}
	
	/**
	 * 监火人确认
	 */
	@RequestMapping(value = "jhrqr/{id}")
	@ResponseBody
	public String jhr(@PathVariable("id") long id) {
		String datasuccess="确认成功";
		AQGL_FireWork dhzy = aqgldhzyService.findById(id);
		dhzy.setM18_1(DateUtils.getSysTimestamp());
		dhzy.setZt("6");
		aqgldhzyService.updateInfo(dhzy);

		Map<String,Object>  msgmap = new HashMap<String,Object>();
		msgmap.put(Message.MSGTARGET_H5,"weixin/enterprise/zyaqgl/dhzy/donghuo.jsp");
		msgmap.put(Message.MSGTARGET_PC,"zyaqgl/dhzy/index");
		MessageUtil.sendMsg(dhzy.getM19(), UserUtil.getCurrentShiroUser().getId()+"", "您有一条动火作业需要审批！", Message.MessageType.DSP.getMsgType(),JSON.toJSONString(msgmap),"编号为\""+dhzy.getM1()+"\"的动火作业需要审批");

		return datasuccess;
	}
	
	/**
	 * 动火初审人确认
	 */
	@RequestMapping(value = "csrqr/{id}")
	@ResponseBody
	public String csr(@PathVariable("id") long id) {
		String datasuccess="确认成功";
		AQGL_FireWork dhzy = aqgldhzyService.findById(id);
		dhzy.setM20_1(DateUtils.getSysTimestamp());
		dhzy.setZt("7");
		aqgldhzyService.updateInfo(dhzy);

		Map<String,Object>  msgmap = new HashMap<String,Object>();
		msgmap.put(Message.MSGTARGET_H5,"weixin/enterprise/zyaqgl/dhzy/donghuo.jsp");
		msgmap.put(Message.MSGTARGET_PC,"zyaqgl/dhzy/index");
		MessageUtil.sendMsg(dhzy.getM21(), UserUtil.getCurrentShiroUser().getId()+"", "您有一条动火作业需要审批！", Message.MessageType.DSP.getMsgType(),JSON.toJSONString(msgmap),"编号为\""+dhzy.getM1()+"\"的动火作业需要审批");

		return datasuccess;
	}
	
	/**
	 * 实施安全教育
	 */
	@RequestMapping(value = "aqjy/{id}")
	@ResponseBody
	public String aqjy(@PathVariable("id") long id) {
		String datasuccess="确认成功";
		AQGL_FireWork dhzy = aqgldhzyService.findById(id);
		dhzy.setM21_1(DateUtils.getSysTimestamp());
		dhzy.setZt("8");
		aqgldhzyService.updateInfo(dhzy);

		Map<String,Object>  msgmap = new HashMap<String,Object>();
		msgmap.put(Message.MSGTARGET_H5,"weixin/enterprise/zyaqgl/dhzy/donghuo.jsp");
		msgmap.put(Message.MSGTARGET_PC,"zyaqgl/dhzy/index");
		MessageUtil.sendMsg(dhzy.getM22(), UserUtil.getCurrentShiroUser().getId()+"", "您有一条动火作业需要审批！", Message.MessageType.DSP.getMsgType(),JSON.toJSONString(msgmap),"编号为\""+dhzy.getM1()+"\"的动火作业需要审批");

		return datasuccess;
	}
	
	/**
	 * 申请单位确认页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "dwqr/{id}", method = RequestMethod.GET)
	public String dwqr(@PathVariable("id") Long id, Model model) {
		model.addAttribute("dhzyid", id);
		//返回页面
		model.addAttribute("action", "dwqr");
		return "zyaqgl/zyaq/dhzy/dwqrform";
	}
	
	/**
	 * 申请单位确认
	 * @param M22_1,model
	 */
	@RequestMapping(value = "dwqr")
	@ResponseBody
	public String dwqr(long id, String M22_1, Model model){
		String datasuccess="success";	
		AQGL_FireWork dhzy = aqgldhzyService.findById(id);
		dhzy.setM22_1(M22_1);
		dhzy.setM22_2(DateUtils.getSysTimestamp());
		dhzy.setZt("9");
		aqgldhzyService.updateInfo(dhzy);
		
		Map<String,Object>  msgmap = new HashMap<String,Object>();
		msgmap.put(Message.MSGTARGET_H5,"weixin/enterprise/zyaqgl/dhzy/donghuo.jsp");
		msgmap.put(Message.MSGTARGET_PC,"zyaqgl/dhzy/index");
		MessageUtil.sendMsg(dhzy.getM23(), UserUtil.getCurrentShiroUser().getId()+"", "您有一条动火作业需要审批！", Message.MessageType.DSP.getMsgType(),JSON.toJSONString(msgmap),"编号为\""+dhzy.getM1()+"\"的动火作业需要审批");
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 安全部门确认页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "aqbm/{id}", method = RequestMethod.GET)
	public String aqbm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("dhzyid", id);
		//返回页面
		model.addAttribute("action", "aqbm");
		return "zyaqgl/zyaq/dhzy/aqbmform";
	}
	
	/**
	 * 安全部门确认
	 * @param M23_1,model
	 */
	@RequestMapping(value = "aqbm")
	@ResponseBody
	public String aqbm(long id, String M23_1, Model model){
		String datasuccess="success";	
		AQGL_FireWork dhzy = aqgldhzyService.findById(id);
		dhzy.setM23_1(M23_1);
		dhzy.setM23_2(DateUtils.getSysTimestamp());
		dhzy.setZt("10");
		aqgldhzyService.updateInfo(dhzy);

		Map<String,Object>  msgmap = new HashMap<String,Object>();
		msgmap.put(Message.MSGTARGET_H5,"weixin/enterprise/zyaqgl/dhzy/donghuo.jsp");
		msgmap.put(Message.MSGTARGET_PC,"zyaqgl/dhzy/index");
		MessageUtil.sendMsg(dhzy.getM24(), UserUtil.getCurrentShiroUser().getId()+"", "您有一条动火作业需要审批！", Message.MessageType.DSP.getMsgType(),JSON.toJSONString(msgmap),"编号为\""+dhzy.getM1()+"\"的动火作业需要审批");

		//返回结果
		return datasuccess;
	}
	
	/**
	 * 动火审批人审批页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "spr/{id}", method = RequestMethod.GET)
	public String spr(@PathVariable("id") Long id, Model model) {
		model.addAttribute("dhzyid", id);
		//返回页面
		model.addAttribute("action", "spr");
		return "zyaqgl/zyaq/dhzy/sprform";
	}
	
	/**
	 * 动火审批人确认
	 * @param M24_1,model
	 */
	@RequestMapping(value = "spr")
	@ResponseBody
	public String spr(long id, String M24_1, Model model){
		String datasuccess="success";	
		AQGL_FireWork dhzy = aqgldhzyService.findById(id);
		dhzy.setM24_1(M24_1);
		dhzy.setM24_2(DateUtils.getSysTimestamp());
		dhzy.setZt("11");
		aqgldhzyService.updateInfo(dhzy);

		Map<String,Object>  msgmap = new HashMap<String,Object>();
		msgmap.put(Message.MSGTARGET_H5,"weixin/enterprise/zyaqgl/dhzy/donghuo.jsp");
		msgmap.put(Message.MSGTARGET_PC,"zyaqgl/dhzy/index");
		MessageUtil.sendMsg(dhzy.getM25(), UserUtil.getCurrentShiroUser().getId()+"", "您有一条动火作业需要审批！", Message.MessageType.DSP.getMsgType(),JSON.toJSONString(msgmap),"编号为\""+dhzy.getM1()+"\"的动火作业需要审批");

		//返回结果
		return datasuccess;
	}
	
	/**
	 * 验收
	 */
	@RequestMapping(value = "ys/{id}")
	@ResponseBody
	public String ys(@PathVariable("id") long id) {
		String datasuccess="验收成功";
		AQGL_FireWork dhzy = aqgldhzyService.findById(id);
		dhzy.setM25_1(DateUtils.getSysTimestamp());
		dhzy.setZt("12");
		aqgldhzyService.updateInfo(dhzy);
		return datasuccess;
	}
	
	/**
	 * 关闭
	 */
	@RequestMapping(value = "gb/{id}")
	@ResponseBody
	public String gb(@PathVariable("id") long id) {
		String datasuccess="关闭成功";
		AQGL_FireWork dhzy = aqgldhzyService.findById(id);
		dhzy.setM28(UserUtil.getCurrentUser().getId()+"");
		dhzy.setM28_1(DateUtils.getSysTimestamp());
		dhzy.setZt("13");
		aqgldhzyService.updateInfo(dhzy);
		return datasuccess;
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
		return "zyaqgl/zyaq/dhzy/viewzt";
	}
	
	/**
	 * 显示所有列
	 * 
	 * @param model
	 */
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","zyaqgl/dhzy/export");
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
		Map<String, Object> map = aqgldhzyService.getExportWord(id);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "zyaqdhzy.ftl", filePath, filename, request);
		return "/download/" + filename;
	}


    /**
     * 获取企业和承包商的json数据
     *
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "getcbsjson")
    @ResponseBody
    public String getJson(HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> resultList = new LinkedList<>();
        BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(UserUtil.getCurrentShiroUser().getQyid());
        map1.put("id", be.getID());
        map1.put("text", be.getM1());
        resultList.add(map1);
        if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
            map.put("qyid",(UserUtil.getCurrentShiroUser().getQyid()));
        }
        map.put("type", "承包商");
        resultList.addAll(aqglxgdwService.getJson(map));
        return JsonMapper.toJsonString(resultList);
    }

    /**
     * 获取承包商人员数据{json}
     * @param request
     */
    @RequestMapping(value="getCBSRlist")
    @ResponseBody
    public Map<String, Object> getCBSRData(HttpServletRequest request) {
        Map<String, Object> maplist = new HashMap<String, Object>();
        List<Map<String, Object>> reslist = new ArrayList<Map<String, Object>>();
        List<AQGL_TzzyryEntity> list = aqgltzzyryService.findActive(Long.parseLong(request.getParameter("dwid")));
        for(AQGL_TzzyryEntity aqgl:list){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", aqgl.getID());
            map.put("text", aqgl.getM1());
            reslist.add(map);
        }
        maplist.put("rows", reslist);
        return maplist;

    }
}
