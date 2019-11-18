package com.cczu.model.sbssgl.controller;

import java.sql.Timestamp;
import java.text.ParseException;
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
import com.cczu.model.sbssgl.entity.SBSSGL_SBBFEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_SBGLEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_SHJLEntity;
import com.cczu.model.sbssgl.service.SBSSGLSbbfService;
import com.cczu.model.sbssgl.service.SBSSGLSbglService;
import com.cczu.model.sbssgl.service.SBSSGLShjlService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

/**
 * 设备设施管理-设备报废controller
 */
@Controller
@RequestMapping("sbssgl/sbbf")
public class PageSbssglSbbfController extends BaseController {

	@Autowired
	private SBSSGLSbbfService sBSSGLSbbfService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	@Autowired
	private SBSSGLSbglService sBSSGLSbglService;
	@Autowired
	private SBSSGLShjlService sBSSGLShjlService;
	
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
		return "sbssgl/sbbf/index";
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
		map.put("m6", request.getParameter("m6"));
		map.put("gdsynx", request.getParameter("gdsynx"));
		map.put("sjsynx", request.getParameter("sjsynx"));
		map.putAll(getAuthorityMap());
		return sBSSGLSbbfService.dataGrid(map);
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
			//删除设备报废信息
			sBSSGLSbbfService.deleteInfoById(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		//获取设备报废信息
		Map<String,Object> sbbf = sBSSGLSbbfService.findById(id);	
		model.addAttribute("sbbf", sbbf);
		return "sbssgl/sbbf/view";
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model, HttpServletRequest request) {
		model.addAttribute("action", "create");
		model.addAttribute("sbtype", request.getParameter("sbtype"));
		return "sbssgl/sbbf/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 * @throws ParseException 
	 */
	@RequestMapping(value = "create" , method = RequestMethod.POST)
	@ResponseBody
	public String create(SBSSGL_SBBFEntity entity, Model model,HttpServletRequest request) throws ParseException {
		String datasuccess="success";
		entity.setStatus("0");
		sBSSGLSbbfService.addInfo(entity);
		
		Long sbid = Long.parseLong(request.getParameter("sbid"));
		SBSSGL_SBGLEntity sbgl = sBSSGLSbglService.find(sbid);	
		sbgl.setM1(request.getParameter("m1"));
		sbgl.setM3(request.getParameter("m3"));
		sbgl.setM4(request.getParameter("m4"));
		sbgl.setM5(request.getParameter("m5"));
		long m6L = DateUtils.parseDate(request.getParameter("m6"), "yyyy-MM-dd").getTime();
		sbgl.setM6(new Timestamp(m6L));
		sbgl.setM23(Long.parseLong(request.getParameter("m23")));
		sBSSGLSbglService.updateInfo(sbgl);
		
		//返回结果
		return datasuccess;
	}
	

	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		Map<String, Object> sbbf = sBSSGLSbbfService.findById(id);	
		model.addAttribute("sbbf", sbbf);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("sbtype", request.getParameter("sbtype"));
		return "sbssgl/sbbf/form";
	}
	
	/**
	 * 修改信息
	 * @param request,model
	 * @throws ParseException 
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(SBSSGL_SBBFEntity entity, Model model,HttpServletRequest request) throws ParseException{
		String datasuccess="success";
		entity.setStatus("0");
		sBSSGLSbbfService.updateInfo(entity);
		
		Long sbid = Long.parseLong(request.getParameter("sbid"));
		SBSSGL_SBGLEntity sbgl = sBSSGLSbglService.find(sbid);	
		sbgl.setM1(request.getParameter("m1"));
		sbgl.setM3(request.getParameter("m3"));
		sbgl.setM4(request.getParameter("m4"));
		sbgl.setM5(request.getParameter("m5"));
		long m6L = DateUtils.parseDate(request.getParameter("m6"), "yyyy-MM-dd").getTime();
		sbgl.setM6(new Timestamp(m6L));
		sbgl.setM23(Long.parseLong(request.getParameter("m23")));
		sBSSGLSbglService.updateInfo(sbgl);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 上传附件页面跳转
	 */
	@RequestMapping(value = "uploadindex/{id}")
	public String uploadindex(@PathVariable("id") Long id, Model model) {
		model.addAttribute("id", id);
		return "sbssgl/sbbf/upload";
	}
	
	/**
	 * 上传附件
	 */
	@RequestMapping(value = "uploadfj")
	@ResponseBody
	public String uploadfj(long id,String fj) {
		String datasuccess="success";
		SBSSGL_SBBFEntity sbbf = sBSSGLSbbfService.find(id);
		sbbf.setStatus("1");
		sbbf.setFj(fj);
		sBSSGLSbbfService.updateInfo(sbbf);
		return datasuccess;
	}
	
	/**
	 * 改变状态
	 */
	@RequestMapping(value = "changezt/{id}/{type}")
	@ResponseBody
	public String changezt(@PathVariable("id") Long id,@PathVariable("type") String type) {
		String datasuccess="操作成功";
		SBSSGL_SBBFEntity sbbf = sBSSGLSbbfService.find(id);
		sbbf.setStatus(type);
		sBSSGLSbbfService.updateInfo(sbbf);
		return datasuccess;
	}
	
	/**
	 * 导出设备验收单word
	 * @throws ParseException 
	 */
	@RequestMapping(value = "export/{id}")
	@ResponseBody
	public String getWord(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) throws ParseException {
		Map<String, Object> map = sBSSGLSbbfService.getWord(id);
		//设置导出的文件名
		String filename = "设备报废单_" + DateUtils.getDateRandom() + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "sbbf.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	/**
	 * 审核结果页面跳转
	 */
	@RequestMapping(value = "shjg/{sbbfid}")
	public String shjg(@PathVariable("sbbfid") Long sbbfid, Model model, HttpServletRequest request) {
		model.addAttribute("sbid", Long.parseLong(request.getParameter("sbid")));
		model.addAttribute("id", sbbfid);
		return "sbssgl/sbbf/shjg";
	}
	
	/**
	 * 保存审核结果
	 */
	@RequestMapping(value = "shjg")
	@ResponseBody
	public String bhzt(HttpServletRequest request) {
		String datasuccess="success";
		Long id = Long.parseLong(request.getParameter("id")); 
		String shjg = request.getParameter("shjg");
		String btgyy = request.getParameter("btgyy");
		SBSSGL_SBBFEntity sbbf = sBSSGLSbbfService.find(id);
		if ("0".equals(shjg)) {//不通过
			sbbf.setStatus("3");  //将状态改为"不通过"
		} else if ("1".equals(shjg)) {//通过
			sbbf.setStatus("2");  //将状态改为"通过"
			SBSSGL_SBGLEntity sbgl = sBSSGLSbglService.find(Long.parseLong(request.getParameter("sbid")));	
			sbgl.setM19("2");//将设备状态改为"报废"
		}
		sBSSGLSbbfService.updateInfo(sbbf);//修改设备申请表的状态
		
		SBSSGL_SHJLEntity shjl = new SBSSGL_SHJLEntity();
		Timestamp t = DateUtils.getSystemTime();
		shjl.setS1(t);
		shjl.setS2(t);
		shjl.setS3(0);
		shjl.setM1(shjg);//审核结果
		shjl.setShrid(Long.parseLong(UserUtil.getCurrentShiroUser().getId()+""));//审核人id
		shjl.setM2(btgyy);//不通过原因
		shjl.setM3(sbbf.getStatus());//附件
		shjl.setId2(id);//设备验收id
		shjl.setM4("2");//类别，2代表设备报废的审核记录
		sBSSGLShjlService.addShJlInfo(shjl);//保存审核记录信息
		
		return datasuccess;
	}
	
	/**
	 * 查看审核记录页面跳转
	 */
	@RequestMapping(value = "viewshjl/{sbbfid}")
	public String viewshjl(@PathVariable("sbbfid") Long sbbfid, Model model) {
		model.addAttribute("sbbfid", sbbfid);
		return "sbssgl/sbbf/viewshjl";
	}
	
	/**
	 * 审核记录list
	 * @param request
	 * @return
	 */
	@RequestMapping(value="shjllist/{sbbfid}")
	@ResponseBody
	public Map<String, Object> shjlList(@PathVariable("sbbfid") Long sbbfid, HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("sbbfid",sbbfid);
		return sBSSGLShjlService.shjlDataGrid(map);
	}
}
