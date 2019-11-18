package com.cczu.model.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_DirectorEntity;
import com.cczu.model.entity.BIS_DirectorResumeEntity;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IBisAqzjglResumeService;
import com.cczu.model.service.IBisAqzjglService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 安全总监管理controller
 * @author jason
 */
@Controller
@RequestMapping("bis/aqzjgl")
public class PageBisAqzjglController extends BaseController {

	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private IBisAqzjglService bisAqzjglService;
	@Autowired
	private IBisAqzjglResumeService bisAqzjglResumeService;
	
	/**
	 * 安全总监管理页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		String indexstr = "model/bis/aqzjgl/index";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		switch(sessionuser.getRoles().get(0).getRoleCode()){
			case "company":
			case "companyadmin":
				if(be!=null&&be.getM1()!=null){
					indexstr = "model/bis/aqzjgl/index";
				}else{
					indexstr = "../error/001";
				}
				break;
			case "ajcountryadmin":
			case "ajprovinceadmin":
			case "ajcityadmin":
			case "ajcountyadmin":
			case "ajtownadmin":
			case "admin":
			case "superadmin":
				indexstr = "model/bis/aqzjgl/ajindex";
				break;
			default:
				break;
		}
		
		
		return indexstr;

	}

	/**
	 * list页面
	 */
//	@RequiresPermissions("bis:aqzjgl:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		
		int pageNo=1;	//当前页码
		int pageSize=20;	//每页行数
		if(StringUtils.isNotEmpty(request.getParameter("page")))
			pageNo=Integer.valueOf(request.getParameter("page"));
		if(StringUtils.isNotEmpty(request.getParameter("rows")))
			pageSize=Integer.valueOf(request.getParameter("rows"));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);
		if(StringUtils.isNotEmpty(request.getParameter("check_name")))
			map.put("aqzjname", request.getParameter("check_name"));
		if(StringUtils.isNotEmpty(request.getParameter("check_sptype")))
			map.put("check_sptype", request.getParameter("check_sptype"));

		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		switch(sessionuser.getRoles().get(0).getRoleCode()){
			case "company":
			case "companyadmin":
				if(sessionuser.getQyid()!=0){
					map.put("qyid", sessionuser.getQyid());
				}
				break;
			case "ajcountryadmin":
				break;
			case "ajprovinceadmin":
				break;
			case "ajcityadmin":
				break;
			case "ajcountyadmin":
				break;
			case "ajtownadmin":
				break;
			default:
				break;
		}
		
		return bisAqzjglService.dataGrid(map);
	}

	/**
	 * 安监list页面
	 */
	@RequestMapping(value="list_aj")
	@ResponseBody
	public Map<String, Object> getDataAJ(HttpServletRequest request) {
		
		int pageNo=1;	//当前页码
		int pageSize=20;	//每页行数
		if(StringUtils.isNotEmpty(request.getParameter("page")))
			pageNo=Integer.valueOf(request.getParameter("page"));
		if(StringUtils.isNotEmpty(request.getParameter("rows")))
			pageSize=Integer.valueOf(request.getParameter("rows"));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);
		if(StringUtils.isNotEmpty(request.getParameter("check_qyname")))
			map.put("qynm", request.getParameter("check_qyname"));
		if(StringUtils.isNotEmpty(request.getParameter("check_name")))
			map.put("aqzjname", request.getParameter("check_name"));
		if(StringUtils.isNotEmpty(request.getParameter("check_sptype")))
			map.put("check_sptype", request.getParameter("check_sptype"));

		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		switch(sessionuser.getRoles().get(0).getRoleCode()){
			case "ajcountryadmin":
			case "ajprovinceadmin":
			case "ajcityadmin":
			case "ajcountyadmin":
			case "ajtownadmin":
				map.put("xzqy",sessionuser.getXzqy());
				break;
			default:
				break;
		}
		
		return bisAqzjglService.dataGridAJ(map);
	}
	
	/**
	 * qyjson
	 * return String
	 */
	@RequestMapping(value="qyjson")
	@ResponseBody
	public String gbtjson() {
		Map<String, Object> map = new HashMap<String, Object>();
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		switch(sessionuser.getRoles().get(0).getRoleCode()){
			case "ajcountryadmin":
			case "ajprovinceadmin":
			case "ajcityadmin":
			case "ajcountyadmin":
			case "ajtownadmin":
				map.put("xzqy",sessionuser.getXzqy());
				break;
			default:
				break;
		}
		return bisAqzjglService.getQyJson(map);
	}
	
	/**
	 * 添加页面
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
//	@RequiresPermissions("bis:aqzjgl:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model){
		BIS_DirectorEntity be=new BIS_DirectorEntity();
		model.addAttribute("list", be);
		model.addAttribute("action", "create");
		return "model/bis/aqzjgl/form";
	}
	
	/**
	 * 添加
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
//	@RequiresPermissions("bis:aqzjgl:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(@Valid BIS_DirectorEntity bisdire,Timestamp[] jl_M1,Timestamp[] jl_M2,String[] jl_M3,String[] jl_M4,String[] jl_M5,Model model){
		String retstring="提交成功！";
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--安全总监信息  【添加操作】");

		long l = bisAqzjglService.addInfo(bisdire);
		if(jl_M1!=null && jl_M1.length>0){
			for(int i=0;i<jl_M1.length;i++){
				 BIS_DirectorResumeEntity bis =new BIS_DirectorResumeEntity();
				 bis.setM1(jl_M1[i]);
				 bis.setM2(jl_M2[i]);
				 bis.setM3(jl_M3[i]);
				 bis.setM4(jl_M4[i]);
				 bis.setM5(jl_M5[i]);
				 bis.setID1(l);
				 long resumel = bisAqzjglResumeService.addInfo(bis);
				 System.out.println(resumel);
			}
		}
		return retstring;
	}
	
	
	/**
	 * 更新页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}")
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的车间信息
		BIS_DirectorEntity bis = bisAqzjglService.findInfoById(id);
		List<BIS_DirectorResumeEntity> bisList = bisAqzjglResumeService.findAllById1(id);
		model.addAttribute("list", bis);
		model.addAttribute("listRes", bisList);
		//返回页面
		model.addAttribute("action", "update");
		return "model/bis/aqzjgl/form";
	}
	
	/**
	 * 更新
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
//	@RequiresPermissions("bis:aqzjgl:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(@Valid BIS_DirectorEntity bisdire,Long[] jl_ID,Timestamp[] jl_S1, Timestamp[] jl_M1,Timestamp[] jl_M2,String[] jl_M3,String[] jl_M4,String[] jl_M5,Model model){
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--安全总监信息  【修改操作】");
		bisAqzjglService.updateInfo(bisdire);
		if(jl_ID!=null && jl_ID.length >= 0){
			for(int i=0;i<jl_ID.length;i++){
				 BIS_DirectorResumeEntity bis =new BIS_DirectorResumeEntity();
				 bis.setM1(jl_M1[i]);
				 bis.setM2(jl_M2[i]);
				 bis.setM3(jl_M3[i]);
				 bis.setM4(jl_M4[i]);
				 bis.setM5(jl_M5[i]);
				 bis.setID1(bisdire.getID());
				 if(jl_ID[i]>0){
					 //更新
					 bis.setID(jl_ID[i]);
					 bis.setS1(jl_S1[i]);
					 bisAqzjglResumeService.updateInfo(bis);
				 }else{
					 //新增
					 long resumel = bisAqzjglResumeService.addInfo(bis);
					 System.out.println(resumel);
				 }
			}
		}
		
		return "更新成功！";
	}

	/**
	 * 审批页面
	 * @param request,model
	 */
	@RequestMapping(value = "approve/{id}", method = RequestMethod.GET)
	public String approve(@PathVariable("id") Long id, Model model){
		//查询选择的车间信息
		BIS_DirectorEntity bis = bisAqzjglService.findInfoById(id);
		List<BIS_DirectorResumeEntity> bisList = bisAqzjglResumeService.findAllById1(id);
		model.addAttribute("list", bis);
		model.addAttribute("listRes", bisList);
		//返回页面
		model.addAttribute("action", "approve");
		return "model/bis/aqzjgl/spform";
	}
	
	/**
	 * 审批
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
//	@RequiresPermissions("bis:aqzjgl:update")
	@RequestMapping(value = "approve")
	@ResponseBody
	public String approve(@Valid BIS_DirectorEntity bisdire,Model model){
		BIS_DirectorEntity bis=bisAqzjglService.findInfoById(bisdire.getID());
		if( bis!=null ){
			bis.setM10(bisdire.getM10());
			bis.setM13(bisdire.getM13());
			bisAqzjglService.spupdateInfo(bis);
			
			return "提交成功！";
		}else{
			return "提交失败！";
		}
	}
	
	/**
	 * 查看页面
	 * @param request,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model){
		//查询选择的车间信息
		BIS_DirectorEntity bis = bisAqzjglService.findInfoById(id);
		List<BIS_DirectorResumeEntity> bisList = bisAqzjglResumeService.findAllById1(id);
		model.addAttribute("list", bis);
		model.addAttribute("listRes", bisList);
		//返回页面
		model.addAttribute("action", "view");
		return "model/bis/aqzjgl/spform";
	}
	
	/**
	 * 删除
	 * 
	 * @param ids
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功！";
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--安全总监信息  【删除操作】");

		//可以批量删除
		String[] arrids = ids.split(",");
		for(String id:arrids){
			bisAqzjglService.deleteInfo(Long.parseLong(id));
		}
		return datasuccess;
	}
	
	/**
	 * 删除
	 * 
	 * @param ids
	 */
	@RequestMapping(value = "deletere/{id}")
	@ResponseBody
	public String deleteResume(@PathVariable("id") String id) {
		String datasuccess="删除成功！";
		//可以批量删除
		bisAqzjglResumeService.deleteInfo(Long.parseLong(id));
		return datasuccess;
	}
	/**
	 * 导出excel
	 * 
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			map.put("aqzjname", request.getParameter("excelcon1"));
		}else if("0".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
			map.put("qyid", request.getParameter("excelcon1"));
			map.put("aqzjname", request.getParameter("excelcon2"));
			map.put("check_sptype", request.getParameter("excelcon3"));
		}else{
			map.put("qyid", request.getParameter("excelcon1"));
		}
		map.put("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		bisAqzjglService.exportExcel(response, map);
		
	}
	
	/**
	 * 显示所有列
	 * @param id,model
	 */
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		return "model/common/formexcel";
	}
}
