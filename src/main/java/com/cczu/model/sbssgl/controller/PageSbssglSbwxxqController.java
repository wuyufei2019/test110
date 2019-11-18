package com.cczu.model.sbssgl.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_SBWXXQEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_SBWXXQ_ZFSFZGSCSEntity;
import com.cczu.model.sbssgl.service.SBSSGLSbwxxqService;
import com.cczu.model.sbssgl.service.SBSSGLSbwxxqZfsfzgscsService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.StringUtils;
import com.cczu.util.common.WordUtil;

/**
 * 设备设施管理-设备维修记录controller
 */
@Controller
@RequestMapping("sbssgl/sbwxxq")
public class PageSbssglSbwxxqController extends BaseController {

	@Autowired
	private SBSSGLSbwxxqService sBSSGLSbwxxqService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	@Autowired
	private SBSSGLSbwxxqZfsfzgscsService sBSSGLSbwxxqZfsfzgscsService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model, HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type","2");//集团公司
				else
					model.addAttribute("type","1");//子公司
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else{
			return "../error/403";
		}
		
		if ("tzsb".equals(request.getParameter("sbtype"))) {
			model.addAttribute("sbtype","1");//特种设备
		} else {
			model.addAttribute("sbtype","0");//普通设备
		}
		return "sbssgl/sbwxxq/index";
	}
	
	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("sbtype", request.getParameter("sbtype"));
		map.put("qyname", request.getParameter("qyname"));
		map.put("m1", request.getParameter("m1"));
		map.put("m2", request.getParameter("m2"));
		map.put("sbname", request.getParameter("sbname"));
		map.put("m23", request.getParameter("m23"));
		map.putAll(getAuthorityMap());
		return sBSSGLSbwxxqService.dataGrid(map);
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			//删除维修申请信息
			sBSSGLSbwxxqService.deleteInfoById(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type","2");//集团公司
				else
					model.addAttribute("type","1");//子公司
			}
		}
		//获取维修申请信息
		Map<String,Object> sbwxxq = sBSSGLSbwxxqService.findById(id);	
		if (!sbwxxq.isEmpty()) {
			if ("6".equals(sbwxxq.get("m23"))) {
				model.addAttribute("gslist", sBSSGLSbwxxqZfsfzgscsService.findByWxid(id));
			}
		}
		
		model.addAttribute("sbwxxq", sbwxxq);
		return "sbssgl/sbwxxq/view";
	}
	
	/**
	 * 添加维修需求申请页面跳转
	 * @param model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String sqcreate(Model model, HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type","2");//集团公司
				else
					model.addAttribute("type","1");//子公司
			}
		}
		model.addAttribute("action", "create");
		model.addAttribute("sbtype", request.getParameter("sbtype"));
		return "sbssgl/sbwxxq/form";
	}
	
	/**
	 * 添加维修需求申请信息
	 * @param request,model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.POST)
	@ResponseBody
	public String sqcreate(SBSSGL_SBWXXQEntity entity, Model model,HttpServletRequest request) {
		String datasuccess="success";
		String type = request.getParameter("type");
		ShiroUser user = UserUtil.getCurrentShiroUser();
		if ("1".equals(type)) {//子公司
			entity.setQyid(user.getQyid());
		} 
		entity.setM23("0");//状态
		entity.setSqrid(Long.parseLong(user.getId()+""));//申请人id
		sBSSGLSbwxxqService.addInfo(entity);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 添加维修记录页面跳转
	 * @param model
	 */
	@RequestMapping(value = "wxcreate/{id}" , method = RequestMethod.GET)
	public String wxcreate(@PathVariable("id") Long id, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("action", "wxcreate");
		return "sbssgl/sbwxxq/wxform";
	}
	
	/**
	 * 添加维修记录信息
	 * @param request,model
	 */
	@RequestMapping(value = "wxcreate" , method = RequestMethod.POST)
	@ResponseBody
	public String wxcreate(SBSSGL_SBWXXQEntity entity, Model model,HttpServletRequest request) {
		String datasuccess="success";
		SBSSGL_SBWXXQEntity sbwxxqEntity = sBSSGLSbwxxqService.find(entity.getID());
		sbwxxqEntity.setM6(entity.getM6());
		sbwxxqEntity.setM7(entity.getM7());
		sbwxxqEntity.setM8(entity.getM8());
		sbwxxqEntity.setM9(entity.getM9());
		sbwxxqEntity.setM24(entity.getM24());
		sbwxxqEntity.setM25(entity.getM25());
		sbwxxqEntity.setM26(entity.getM26());
		sbwxxqEntity.setM27(entity.getM27());
		sbwxxqEntity.setM23("3");//状态
		//添加维修记录信息
		sBSSGLSbwxxqService.updateInfo(sbwxxqEntity);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 添加维修验收页面跳转
	 * @param model
	 */
	@RequestMapping(value = "yscreate/{id}" , method = RequestMethod.GET)
	public String yscreate(@PathVariable("id") Long id, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("action", "yscreate");
		return "sbssgl/sbwxxq/ysform";
	}
	
	/**
	 * 添加维修验收信息
	 * @param request,model
	 */
	@RequestMapping(value = "yscreate" , method = RequestMethod.POST)
	@ResponseBody
	public String yscreate(SBSSGL_SBWXXQEntity entity, Model model,HttpServletRequest request) {
		String datasuccess="success";
		SBSSGL_SBWXXQEntity sbwxxqEntity = sBSSGLSbwxxqService.find(entity.getID());
		sbwxxqEntity.setM10(entity.getM10());
		sbwxxqEntity.setM11(entity.getM11());
		sbwxxqEntity.setM13(entity.getM13());
		sbwxxqEntity.setM14(entity.getM14());
		sbwxxqEntity.setM15(entity.getM15());
		sbwxxqEntity.setM16(entity.getM16());
		sbwxxqEntity.setM17(entity.getM17());
		sbwxxqEntity.setM18(entity.getM18());
		sbwxxqEntity.setM19(entity.getM19());
		sbwxxqEntity.setM23("4");//状态
		//添加维修验收信息
		sBSSGLSbwxxqService.updateInfo(sbwxxqEntity);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 添加维修验收页面跳转
	 * @param model
	 */
	@RequestMapping(value = "pdcreate/{id}" , method = RequestMethod.GET)
	public String pdcreate(@PathVariable("id") Long id, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("action", "pdcreate");
		return "sbssgl/sbwxxq/pdform";
	}
	
	/**
	 * 添加维修验收信息
	 * @param request,model
	 */
	@RequestMapping(value = "pdcreate" , method = RequestMethod.POST)
	@ResponseBody
	public String pdcreate(SBSSGL_SBWXXQEntity entity, Model model,HttpServletRequest request) {
		String datasuccess="success";
		SBSSGL_SBWXXQEntity sbwxxqEntity = sBSSGLSbwxxqService.find(entity.getID());
		sbwxxqEntity.setM20(entity.getM20());
		sbwxxqEntity.setM23("5");//状态
		//添加维修结果评定信息
		sBSSGLSbwxxqService.updateInfo(sbwxxqEntity);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 添加再发生防止改善措施页面跳转
	 * @param model
	 */
	@RequestMapping(value = "cscreate/{sbwxid}" , method = RequestMethod.GET)
	public String gscreate(@PathVariable("sbwxid") Long sbwxid, Model model) {
		model.addAttribute("sbwxid", sbwxid);
		model.addAttribute("action", "cscreate");
		return "sbssgl/sbwxxq/csform";
	}
	
	/**
	 * 添加再发生防止改善措施信息
	 * @param request,model
	 * @throws ParseException 
	 */
	@RequestMapping(value = "cscreate" , method = RequestMethod.POST)
	@ResponseBody
	public String gscreate(Model model,HttpServletRequest request) throws ParseException {
		String datasuccess="success";
		Long wxid = Long.parseLong(request.getParameter("wxid"));
		String[] m1 = request.getParameterValues("m1");
		String[] m2 = request.getParameterValues("m2");
		String[] m3 = request.getParameterValues("m3");
		String[] m4 = request.getParameterValues("m4");
		
		for (int i = 0; i < m1.length; i++) {
			SBSSGL_SBWXXQ_ZFSFZGSCSEntity entity = new SBSSGL_SBWXXQ_ZFSFZGSCSEntity();
			Timestamp t = DateUtils.getSystemTime();
			entity.setS1(t);
			entity.setS2(t);
			entity.setS3(0);
			entity.setM1(m1[i]);
			entity.setM2(new Timestamp(DateUtils.parseDate(m2[i], "yyyy-MM-dd").getTime()));
			entity.setM3(new Timestamp(DateUtils.parseDate(m3[i], "yyyy-MM-dd").getTime()));
			entity.setM4(m4[i]);
			entity.setWxid(wxid);
			//添加维修记录信息
			sBSSGLSbwxxqZfsfzgscsService.addInfo(entity);
		}
		
		SBSSGL_SBWXXQEntity sbwxxqEntity = sBSSGLSbwxxqService.find(wxid);
		sbwxxqEntity.setM23("6");//状态
		sBSSGLSbwxxqService.updateInfo(sbwxxqEntity);//修改状态信息
		//返回结果
		return datasuccess;
	}
	

	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type","2");//集团公司
				else
					model.addAttribute("type","1");//子公司
			}
		}
		SBSSGL_SBWXXQEntity sbwxxq = sBSSGLSbwxxqService.find(id);	
		model.addAttribute("sbwxxq", sbwxxq);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("sbtype", request.getParameter("sbtype"));
		return "sbssgl/sbwxxq/form";
	}
	
	/**
	 * 修改信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(SBSSGL_SBWXXQEntity entity, Model model,HttpServletRequest request){
		String datasuccess="success";
		String type = request.getParameter("type");
		ShiroUser user = UserUtil.getCurrentShiroUser();
		if ("1".equals(type)) {//子公司
			entity.setQyid(user.getQyid());
		}
		entity.setSqrid(Long.parseLong(user.getId()+""));//申请人id
		sBSSGLSbwxxqService.updateInfo(entity);
		//返回结果
		return datasuccess;
	}
	

	/**
	 * 上传附件页面跳转
	 */
	@RequestMapping(value = "tgindex/{id}")
	public String tgindex(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		model.addAttribute("type", request.getParameter("type"));//附件类型
		model.addAttribute("id", id);
		return "sbssgl/sbwxxq/upload";
	}
	
	/**
	 * 保存附件信息
	 */
	@RequestMapping(value = "tgzt")
	@ResponseBody
	public String tgzt(long id,String m21,String type) {
		String datasuccess="success";
		SBSSGL_SBWXXQEntity sbsq = sBSSGLSbwxxqService.find(id);
		if (StringUtils.isNotEmpty(type)) {
			if ("sq".equals(type)) {
				sbsq.setM23("1");
				sbsq.setM21(m21);
			} else if ("wx".equals(type)) {
				sbsq.setM23("4");
				sbsq.setM21(m21);
			}
		}
		
		
		sBSSGLSbwxxqService.updateInfo(sbsq);
		return datasuccess;
	}
	
	/**
	 * 通过
	 */
	@RequestMapping(value = "tgzt/{id}")
	@ResponseBody
	public String tgzt(@PathVariable("id") Long id) {
		String datasuccess="操作成功";
		SBSSGL_SBWXXQEntity sbsq = sBSSGLSbwxxqService.find(id);
		sbsq.setM23("1");
		sBSSGLSbwxxqService.updateInfo(sbsq);
		return datasuccess;
	}
	
	/**
	 * 驳回
	 */
	@RequestMapping(value = "bhzt/{id}")
	@ResponseBody
	public String bhzt(@PathVariable("id") Long id) {
		String datasuccess="操作成功";
		SBSSGL_SBWXXQEntity sbsq = sBSSGLSbwxxqService.find(id);
		sbsq.setM23("3");
		sBSSGLSbwxxqService.updateInfo(sbsq);
		return datasuccess;
	}
	
	/**
	 * 结束
	 */
	@RequestMapping(value = "end/{id}")
	@ResponseBody
	public String end(@PathVariable("id") Long id) {
		String datasuccess="操作成功";
		SBSSGL_SBWXXQEntity sbsq = sBSSGLSbwxxqService.find(id);
		sbsq.setM23("7");
		sBSSGLSbwxxqService.updateInfo(sbsq);
		return datasuccess;
	}
	
	/**
	 * 导出设备维修需求单word
	 * @throws ParseException 
	 */
	@RequestMapping(value = "exportword")
	@ResponseBody
	public String getWord(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		//设置导出的文件名
		String filename = "设备维修需求单_" + DateUtils.getDateRandom() + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "sbwx1.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	/**
	 * 导出设备维修需求单word
	 * @throws ParseException 
	 */
	@RequestMapping(value = "exportword/{id}")
	@ResponseBody
	public String getWord1(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) throws ParseException {
		Map<String, Object> map = sBSSGLSbwxxqService.getWord(id);
		//设置导出的文件名
		String filename = "设备维修需求单_" + DateUtils.getDateRandom() + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "sbwx.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
}
