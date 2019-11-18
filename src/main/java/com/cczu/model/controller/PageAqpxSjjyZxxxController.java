package com.cczu.model.controller;

import java.io.IOException;
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

import com.alibaba.fastjson.JSON;
import com.cczu.model.entity.AQPX_CourseEntity;
import com.cczu.model.entity.AQPX_ThreeLevelEducationHistoryEntity;
import com.cczu.model.entity.BIS_EmployeeEntity;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.AqpxSjjyHistoryService;
import com.cczu.model.service.IAqpxKCxxService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.impl.BisYgxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.Department;
import com.cczu.sys.system.service.DepartmentService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;
import com.itextpdf.text.pdf.qrcode.WriterException;


/**
 * @description 安全培训管理——三级教育培训Controller
 * @author jason
 * @date 2018年1月20日
 */
@Controller
@RequestMapping("aqpx/sjjy")
public class PageAqpxSjjyZxxxController extends BaseController {
	
	 
	@Autowired
	private IAqpxKCxxService aqpxKCxxService;
	@Autowired
	private AqpxSjjyHistoryService sjjyHistoryService;
	@Autowired
	private BisYgxxServiceImpl ygxxService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	
	/**
	 * 首页显示三级教育学习课程
	 * @param model	
	 */
	@RequestMapping(value="zxxx")
	public String ZXXX(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyid", sessionuser.getQyid());
		map.put("type", 2);//三级安全教育
		map.put("dep", UserUtil.getCurrentUser().getDepartmen());//三级安全教育
		List<AQPX_CourseEntity> list=aqpxKCxxService.getList(map);
		model.addAttribute("kclist", list);
		return "aqpx/sjjy/zxxx/index";
	}

	
	/**
	 * 三级教育学习时长记录
	 * @param model	
	 */
	@RequestMapping(value="ksjlindex")
	public String StudyLengthindex(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					return "aqpx/sjjy/ksjl/ajindex";
				else
					return "aqpx/sjjy/ksjl/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "aqpx/sjjy/ksjl/ajindex";
		}
	}
	
	/**
	 * 三级教育考试首页显示三级教育学习课程
	 * @param model	
	 */
	@RequestMapping(value="zxks")
	public String ZXKS(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyid", sessionuser.getQyid());
		map.put("type", 2);//三级安全教育
		map.put("dep", UserUtil.getCurrentUser().getDepartmen());//三级安全教育
		List<AQPX_CourseEntity> list=aqpxKCxxService.getList(map);
		model.addAttribute("kclist", list);
		
		return "aqpx/sjjy/zxks/index";
	}
	
	
	
	/**
	 * 培训记录页面
	 * @param model
	 */
	@RequestMapping(value="pxjl/index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					return "aqpx/sjjy/pxjl/ajindex";
				else
					return "aqpx/sjjy/pxjl/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "aqpx/sjjy/pxjl/ajindex";
		}
	}
	
	/**
	 * 培训记录list页面
	 * @param request
	 */
	@RequestMapping(value="pxjl/list")
	@ResponseBody
	public Map<String, Object> getPxjlData(HttpServletRequest request) {
		
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("aqpx_sjjy_qyname"));
		map.put("name", request.getParameter("name"));
		map.put("idcard", request.getParameter("idcard"));
		map.putAll(getAuthorityMap());
		return sjjyHistoryService.dataGrid(map);	
	}
	
	/**
	 * 考试时长list页面
	 * @param request
	 */
	@RequestMapping(value="kssclist")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		
		Map<String, Object> map = getPageMap(request);
		map.put("starttime", request.getParameter("view_aqpx_kssc_m1"));
		map.put("endtime", request.getParameter("view_aqpx_kssc_m2"));
		map.put("uname", request.getParameter("uname"));
		map.putAll(getAuthorityMap());
		return sjjyHistoryService.dataGridStudyLength(map);	
	}
	
	/**
	 * 培训记录添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("sjjy:pxjl:add")
	@RequestMapping(value = "pxjl/create" , method = RequestMethod.GET)
	public String create(Model model) {
		AQPX_ThreeLevelEducationHistoryEntity sjjy=new AQPX_ThreeLevelEducationHistoryEntity();
		sjjy.setID1(UserUtil.getCurrentShiroUser().getQyid());
		sjjy.setM2(DateUtils.getSystemTime());
		model.addAttribute("action", "create");
		model.addAttribute("sjjy",sjjy);
		return "aqpx/sjjy/pxjl/form";
		
	}
	
	/**
	 * 培训记录添加
	 * @param model
	 */
	@RequiresPermissions("sjjy:pxjl:add")
	@RequestMapping(value = "pxjl/create" , method = RequestMethod.POST)
	@ResponseBody
	public String create(AQPX_ThreeLevelEducationHistoryEntity jl,Model model) {
		String result="success";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		AQPX_ThreeLevelEducationHistoryEntity  temp=sjjyHistoryService.findByYgID(sessionuser.getQyid(),jl.getID2());
		if(temp==null){
			jl.setID1(sessionuser.getQyid());
			jl.setState("2");//线下培训
			sjjyHistoryService.addInfo(jl);
		}else{
			/*
			jl.setID(temp.getID());
			jl.setID1(temp.getID1());
			jl.setID2(temp.getID2());
			jl.setS1(temp.getS1());
			jl.setS3(0);
			sjjyHistoryService.updateInfo(jl);
			*/
			result="isexist";
		}
		return result;
		
	}
	
	/**
	 * 培训记录修改页面跳转
	 * @param model
	 */
	@RequiresPermissions("sjjy:pxjl:update")
	@RequestMapping(value = "pxjl/update/{id}" , method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id,Model model) {
		AQPX_ThreeLevelEducationHistoryEntity sjjy = sjjyHistoryService.findById(id);
		model.addAttribute("action", "update");
		model.addAttribute("sjjy",sjjy);
		return "aqpx/sjjy/pxjl/form";
	}
	
	/**
	 * 培训记录修改
	 * @param model
	 */
	@RequiresPermissions("sjjy:pxjl:update")
	@RequestMapping(value = "pxjl/update" , method = RequestMethod.POST)
	@ResponseBody
	public String update(AQPX_ThreeLevelEducationHistoryEntity jl,Model model) {
		sjjyHistoryService.updateInfo(jl);
		return "success";
	}
	
	
	/**
	 * 培训记录查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("sjjy:pxjl:view")
	@RequestMapping(value = "pxjl/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		AQPX_ThreeLevelEducationHistoryEntity sjjy = sjjyHistoryService.findById(id);
		BIS_EmployeeEntity yg= ygxxService.findInfoByID(sjjy.getID2());
		Department dep=new Department();
		if(yg.ID4!=null&&yg.ID4!=0)
			dep= departmentService.getDepartmentById(yg.ID4);
		model.addAttribute("sjjy",sjjy);
		model.addAttribute("yg",yg);
		model.addAttribute("dep",dep);
		return "aqpx/sjjy/pxjl/view";
	}
	
	/**
	 * 删除培训记录
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("sjjy:pxjl:delete")
	@RequestMapping(value = "pxjl/delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			sjjyHistoryService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("sjjy:pxjl:export")
	@RequestMapping(value = "pxjl/export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String param=request.getParameter("param");
		Map<String,Object> map2 = JSON.parseObject(param);
		map.put("qyname", map2.get("aqpx_sjjy_qyname"));
		map.put("name", map2.get("name"));
		map.put("idcard", map2.get("idcard"));
		map.putAll(getAuthorityMap());
		sjjyHistoryService.exportExcel(response, map);
	}
	
	/**
	 * 导出excel(集团)
	 * 
	 */
	@RequiresPermissions("sjjy:pxjl:export")
	@RequestMapping(value = "pxjl/export2")
	@ResponseBody
	public void export2(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String param=request.getParameter("param");
		Map<String,Object> map2 = JSON.parseObject(param);
		map.put("qyname", map2.get("aqpx_sjjy_qyname"));
		map.put("name", map2.get("name"));
		map.put("idcard", map2.get("idcard"));
		map.putAll(getAuthorityMap());
		sjjyHistoryService.exportExcel2(response, map);
	}
	
	
	/**
	 * 统计页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("sjjy:pxjl:statistics")
	@RequestMapping(value = "statistics", method = RequestMethod.GET)
	public String statistics(Model model) {
		Map<String, Object> tmap = new HashMap<>();
		tmap.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		Map<String, Object> map = sjjyHistoryService.statistics(tmap);
		model.addAttribute("mapdata", JsonMapper.getInstance().toJson(map));
		return "aqpx/sjjy/pxjl/statistics";
	}
	

	/**
	 * 导出三级教育卡
	 * 
	 * @param id
	 *            ,model
	 * @throws IOException
	 * @throws WriterException
	 */
	@RequestMapping(value = "exportcard/{id}")
	@ResponseBody
	public String viewka(@PathVariable("id") Long id, Model model,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		Map<String,Object> map = sjjyHistoryService.exportCard(id);
		String dowmloadPath = request.getSession().getServletContext().getRealPath("/")+"/download";
		//设置导出的文件名
		String filename =  map.get("qyname")+ "_" + map.get("m1") + "_三级教育卡.doc";
		WordUtil.ireportWord(map, "sjjy.ftl", dowmloadPath, filename, request);
		return "/download/" + filename;
	}
	
}
