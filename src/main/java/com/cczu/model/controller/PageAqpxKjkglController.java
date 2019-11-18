package com.cczu.model.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.AQPX_Courseware2Entity;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.AqpxKjkglService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 培训设置-课件库管理Controller
 * @author jason
 */
@Controller
@RequestMapping("aqpx/kjkgl")
public class PageAqpxKjkglController extends BaseController {

	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private AqpxKjkglService aqpxKjkglService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be==null){//判断是否存在企业基本信息
				return "../error/001";
			}
			return "aqpx/kjkgl/index";
		}else//非企业用户无法使用该模块
			return "../error/403";
	}
	
	/**
	 * list页面
	 * @param request
	 */
	@RequiresPermissions("aqpx:kjgl:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		
		Map<String, Object> map = getPageMap(request);
		
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		map.put("qyid", be.getID());
		map.put("m1", request.getParameter("aqpx_kjgl_cx_m1"));
		
		return aqpxKjkglService.dataGrid(map);	
	}

	/**
	 * 课件页面跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "kjchoose")
	public String jcdchoosechoose(Model model) {
		model.addAttribute("action", "kjchoose");
		return "aqpx/kjkgl/index_kjchoose";
	}

	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("aqpx:kjgl:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "aqpx/kjkgl/form";
		
	}
	
	/**
	 * 添加课程信息
	 * @param request,model
	 */
	@RequiresPermissions("aqpx:kjgl:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(AQPX_Courseware2Entity ac, HttpServletRequest request) {
		String datasuccess="success";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		ac.setID1(sessionuser.getQyid());//企业id
		String url=ac.getM2();//附件地址
		String str[]=url.split("\\|\\|");
		//判断是否为非视频格式，如果非视频，则保存转过的pdf文件地址给h5端使用
		String fileurl[]=str[0].toString().split("\\.");
		if(!fileurl[1].equals("mp4")){
			String pdfurl=fileurl[0]+".pdf";
			ac.setM4(pdfurl);
			ac.setM5(fileurl[0]+"."+ac.getM3());//附件原地址
		}else{
			ac.setM5(ac.getM2());//附件原地址
		}
		aqpxKjkglService.addInfo(ac);
		return datasuccess;
	}	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("aqpx:kjgl:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		AQPX_Courseware2Entity ac = aqpxKjkglService.findByID(id);
		model.addAttribute("kjlist",ac);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "aqpx/kjkgl/form";
	}
	
	/**
	 * 修改课程信息
	 * @param request,model
	 */
	@RequiresPermissions("aqpx:kjgl:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQPX_Courseware2Entity ac,Model model, HttpServletRequest request){
		String datasuccess="success";
		String url=ac.getM2();//附件地址
		String str[]=url.split("\\|\\|");
		//判断是否为非视频格式，如果非视频，则保存转过的pdf文件地址给h5端使用
		String fileurl[]=str[0].toString().split("\\.");
		if(!fileurl[1].equals("mp4")){
			String pdfurl=fileurl[0]+".pdf";
			ac.setM4(pdfurl);
			ac.setM5(fileurl[0]+"."+ac.getM3());//附件原地址
		}else{
			ac.setM5(ac.getM2());//附件原地址
		}
		aqpxKjkglService.addInfo(ac);
		return datasuccess;
	}
	
	/**
	 * 删除课程信息
	 */
	@RequiresPermissions("aqpx:kjgl:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			aqpxKjkglService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("aqpx:kjgl:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		AQPX_Courseware2Entity ac = aqpxKjkglService.findByID(id);
		model.addAttribute("kjlist",ac);
		return "aqpx/kjkgl/view";
	}
}
