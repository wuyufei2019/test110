package com.cczu.model.controller;

import java.text.ParseException;
import java.util.HashMap;
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

import com.cczu.model.entity.BIS_DirectorAssessEntity;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IBisAqzjglkhService;
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
@RequestMapping("bis/aqzjglkh")
public class PageBisAqzjglkhController extends BaseController {

	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private IBisAqzjglkhService bisAqzjglkhService;
	
	/**
	 * 安全总监年度考核index页面
	 */
	@RequestMapping(value="index")
	public String khindex(Model model) {
		String indexstr="model/bis/aqzjglkh/index";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		switch(sessionuser.getRoles().get(0).getRoleCode()){
			case "company":
			case "companyadmin":
				if(be!=null&&be.getM1()!=null){
					indexstr = "model/bis/aqzjglkh/index";
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
				indexstr = "model/bis/aqzjglkh/ajindex";
				break;
			default:
				break;
		}
		return indexstr;
	}
	
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> dataGridkh(HttpServletRequest request) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		int pageNo=1;	//当前页码
		int pageSize=20;	//每页行数
		if(StringUtils.isNotEmpty(request.getParameter("page")))
			pageNo=Integer.valueOf(request.getParameter("page"));
		if(StringUtils.isNotEmpty(request.getParameter("rows")))
			pageSize=Integer.valueOf(request.getParameter("rows"));
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);
		if(StringUtils.isNotEmpty(request.getParameter("bis_aqzjglkh_yearChoose")))
			map.put("year", request.getParameter("bis_aqzjglkh_yearChoose"));
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

		return bisAqzjglkhService.dataGrid(map);
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
		if(StringUtils.isNotEmpty(request.getParameter("bis_aqzjglkh_yearChoose")))
			map.put("year", request.getParameter("bis_aqzjglkh_yearChoose"));
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
		
		return bisAqzjglkhService.dataGridAJ(map);
	}
	
	/**
	 * qyjson
	 * return String
	 */
	@RequestMapping(value="qyjson")
	@ResponseBody
	public String gbtjson() {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Map<String, Object> map = new HashMap<String, Object>();
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
		return bisAqzjglkhService.getQyJson(map);
	}
	
	/**
	 * 添加页面
	 * 
	 * @param user
	 * @param model
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createkh(Model model){
		BIS_DirectorAssessEntity be=new BIS_DirectorAssessEntity();
		model.addAttribute("list", be);
		model.addAttribute("action", "create");
		return "model/bis/aqzjglkh/form";
	}
	
	/**
	 * 添加
	 * 
	 * @param 
	 */
	@RequestMapping(value = "create")
	@ResponseBody
	public String createkh(@Valid BIS_DirectorAssessEntity bisdire,Model model){
		String retstring="提交失败！";
		
		long l = bisAqzjglkhService.addInfo(bisdire);
		if(l>0){
			retstring="提交成功！";
		}
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--安全总监kh信息  【添加操作】");

		return retstring;
	}

	/**
	 * 更新页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}")
	public String updatekh(@PathVariable("id") Long id, Model model) {
		//查询选择的车间信息
		BIS_DirectorAssessEntity bis = bisAqzjglkhService.findInfoById(id);
		model.addAttribute("list", bis);
		//返回页面
		model.addAttribute("action", "update");
		return "model/bis/aqzjglkh/form";
	}
	
	/**
	 * 更新
	 * 
	 * @param model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String updatekh(@Valid BIS_DirectorAssessEntity bis,Model model){
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		switch(sessionuser.getRoles().get(0).getRoleCode()){
			case "company":
			case "companyadmin":
				bisAqzjglkhService.updateInfo(bis);
				break;
				
			default:
				bisAqzjglkhService.spupdateInfo(bis);
				break;
		}
		return "更新成功！";
	}
	
	/**
	 * 审批页面
	 * @param request,model
	 */
	@RequestMapping(value = "approve/{id}", method = RequestMethod.GET)
	public String khapprove(@PathVariable("id") Long id, Model model){
		//查询选择的车间信息
		BIS_DirectorAssessEntity bis = bisAqzjglkhService.findInfoById(id);
		model.addAttribute("list", bis);
		//返回页面
		model.addAttribute("action", "approve");
		return "model/bis/aqzjglkh/spform";
	}
	
	/**
	 * 审批
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequestMapping(value = "approve")
	@ResponseBody
	public String khapprove(@Valid BIS_DirectorAssessEntity bisdire,Model model){
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--安全总监kh信息  【添加操作】");
		BIS_DirectorAssessEntity bis=bisAqzjglkhService.findInfoById(bisdire.getID());
		if( bis!=null ){
			bis.setM4(bisdire.getM4());
			bis.setM7(bisdire.getM7());
			bisAqzjglkhService.spupdateInfo(bis);
			
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
		BIS_DirectorAssessEntity bis = bisAqzjglkhService.findInfoById(id);
		model.addAttribute("list", bis);
		//返回页面
		model.addAttribute("action", "view");
		return "model/bis/aqzjglkh/spform";
	}
	
	/**
	 * 删除
	 * 
	 * @param ids
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String deletekh(@PathVariable("ids") String ids) {
		String datasuccess="删除成功！";
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--安全总监kh信息  【删除操作】");
		//可以批量删除
		String[] arrids = ids.split(",");
		for(String id:arrids){
			bisAqzjglkhService.deleteInfo(Long.parseLong(id));
		}
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
			map.put("year", request.getParameter("excelcon1"));
			//map.put("check_sptype", request.getParameter("excelcon2"));
		}else if("0".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
			map.put("qyid", request.getParameter("excelcon1"));
			map.put("year", request.getParameter("excelcon2"));
			map.put("check_sptype", request.getParameter("excelcon3"));
		}else{
			map.put("qyid", request.getParameter("excelcon1"));
			map.put("year", request.getParameter("excelcon2"));
			map.put("check_sptype", request.getParameter("excelcon3"));
		}
		map.put("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		bisAqzjglkhService.exportExcel(response, map);
		
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
