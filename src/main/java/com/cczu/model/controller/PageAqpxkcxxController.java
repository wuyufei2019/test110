package com.cczu.model.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.AQPX_CourseEntity;
import com.cczu.model.entity.AQPX_CoursewareEntity;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IAqpxKCxxService;
import com.cczu.model.service.IAqpxKjkxxService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.entity.Department;
import com.cczu.sys.system.service.DepartmentService;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;


/**
 * 安全培训管理——课程信息管理Controller
 * @author jason
 */
@Controller
@RequestMapping("aqpx/kcxx")
public class PageAqpxkcxxController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private IAqpxKCxxService aqpxkcxxservice;
	@Autowired
	private IAqpxKjkxxService aqpxKjkxxService;
	@Autowired
	private DepartmentService departmentService;
	
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
			return "aqpx/kcxx/index";
		}else//非企业用户无法使用该模块
			return "../error/403";
	}
	

	/**
	 * list页面
	 * @param request
	 */
	@RequiresPermissions("aqpx:kcxx:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		
		Map<String, Object> map = getPageMap(request);
		
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		map.put("qyid", be.getID());
		map.put("m1", request.getParameter("aqpx_kcxx_cx_m1"));
		
		return aqpxkcxxservice.dataGrid(map);	
	}

	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("aqpx:kcxx:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "aqpx/kcxx/form";
		
	}
	
	/**
	 * 添加课程信息
	 * @param request,model
	 */
	@RequiresPermissions("aqpx:kcxx:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(AQPX_CourseEntity ac, HttpServletRequest request) {
		String datasuccess="success";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		ac.setID1(sessionuser.getQyid());
		long kcid=aqpxkcxxservice.addInforReturnID(ac);
		
		if(ac.getM4()!=null&&(!ac.getM4().equals(""))){
			String lx[]=request.getParameterValues("lx");
			String url=ac.getM4();
			String file[]=url.split(",");
			int i=0;
			for(String s:file){
				AQPX_CoursewareEntity kj=new AQPX_CoursewareEntity();
				kj.setID1(sessionuser.getQyid());
				kj.setID2(kcid);
				String str[]=s.split("\\|\\|");
				//判断是否为非视频格式，如果非视频，则保存转过的pdf文件地址给h5端使用
				String fileurl[]=str[0].toString().split("\\.");
				if(!fileurl[1].equals("mp4")){
					String pdfurl=fileurl[0]+".pdf";
					kj.setM4(pdfurl);
				}
				kj.setM1(str[1]);
				kj.setM2(str[0]);
				kj.setM3(lx[i]);
				aqpxKjkxxService.addInfo(kj);
				i++;
			}
		}					
		return datasuccess;
	}	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("aqpx:kcxx:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		AQPX_CourseEntity ac = aqpxkcxxservice.findbyid(id);
		model.addAttribute("aqpxlist",ac);
		List<AQPX_CoursewareEntity> list=aqpxKjkxxService.getListKcid(id);
		String url="";
		String lx="";
		for(AQPX_CoursewareEntity cw:list){
			if(url.equals("")){
				url+=cw.getM2()+"||"+cw.getM1();
				lx+=cw.getM3();
			}
			else{
				url+=","+cw.getM2()+"||"+cw.getM1();
				lx+=","+cw.getM3();
			}
		}
		model.addAttribute("url",url);
		model.addAttribute("lx",lx);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "aqpx/kcxx/form";
	}
	
	/**
	 * 修改课程信息
	 * @param request,model
	 */
	@RequiresPermissions("aqpx:kcxx:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQPX_CourseEntity ac,Model model, HttpServletRequest request){
		String datasuccess="success";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		aqpxkcxxservice.updateInfo(ac);
		//删除课程的课件信息，重新添加课件信息
		aqpxKjkxxService.deleteByKcid(ac.getID());
		if(ac.getM4()!=null&&(!ac.getM4().equals(""))){
			String url=ac.getM4();
			String lx[]=request.getParameterValues("lx");
			String file[]=url.split(",");
			int i=0;
			for(String s:file){
				AQPX_CoursewareEntity kj=new AQPX_CoursewareEntity();
				kj.setID1(sessionuser.getQyid());
				kj.setID2(ac.getID());
				String str[]=s.split("\\|\\|");
				//判断是否为非视频格式，如果非视频，则保存转过的pdf文件地址给h5端使用
				String fileurl[]=str[0].toString().split("\\.");
				if(!fileurl[1].equals("mp4")){
					String pdfurl=fileurl[0]+".pdf";
					kj.setM4(pdfurl);
				}	
				kj.setM1(str[1]);
				kj.setM2(str[0]);
				kj.setM3(lx[i]);
				aqpxKjkxxService.addInfo(kj);
				i++;
			}
		}
		return datasuccess;
	}
	
	/**
	 * 删除课程信息
	 */
	@RequiresPermissions("aqpx:kcxx:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			aqpxkcxxservice.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("aqpx:kcxx:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		//查询选择的产品信息
		long id1 = id;
		AQPX_CourseEntity ac = aqpxkcxxservice.findbyid(id1);
		if(ac.getM5().equals("2")&&ac.getID2()!=null){
			String[] depid = ac.getID2().split(",");
			String depname = "";
			for (int i = 0; i < depid.length; i++) {
				Department dep=departmentService.getDepartmentById(Long.valueOf(depid[i]));
				if(i == 0) {
					depname = dep.getM1();
				}else {
					depname = depname + ',' + dep.getM1();
				}
			}
			model.addAttribute("dep",depname);
		}
		model.addAttribute("aqpxlist",ac);
		//返回页面
		model.addAttribute("action", "view");
		List<AQPX_CoursewareEntity> list=aqpxKjkxxService.getListKcid(id1);
		String url="";
		for(AQPX_CoursewareEntity cw:list){
			if(url.equals(""))
				url+=cw.getM2()+"||"+cw.getM1();
			else
				url+=","+cw.getM2()+"||"+cw.getM1();
		}
		model.addAttribute("url",url);
		return "aqpx/kcxx/view";
	}
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("aqpx:kcxx:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyid", sessionuser.getQyid());
		aqpxkcxxservice.exportExcel(response, map);
		
	}
	
	/**
	 * 根据条件获取课程选择json
	 * {"id":11,"text":"课程名称"}
	 * @param request
	 */
	@RequestMapping(value="json")
	@ResponseBody
	public String getKCListJson(String type,HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyid", sessionuser.getQyid());
		map.put("type", type);
		return aqpxkcxxservice.findCKByContent(map);
	}
	
}
