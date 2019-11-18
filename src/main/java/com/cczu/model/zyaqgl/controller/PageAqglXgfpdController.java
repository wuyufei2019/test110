package com.cczu.model.zyaqgl.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.zyaqgl.entity.AQGL_RelevantEvaluation;
import com.cczu.model.zyaqgl.entity.AQGL_RelevantEvaluation_content;
import com.cczu.model.zyaqgl.service.AqglXgfpdRecordService;
import com.cczu.model.zyaqgl.service.AqglXgfpdService;
import com.cczu.model.zyaqgl.service.AqglXgfpdjhService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 安全管理-相关方评定Controller
 * @author YZH
 */
@Controller
@RequestMapping("zyaqgl/xgfpd")
public class PageAqglXgfpdController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private AqglXgfpdService aqglxgfpdService;
	@Autowired
	private AqglXgfpdjhService aqglxgfpdjhService;
	@Autowired
	private AqglXgfpdRecordService aqglXgfpdRecordService;
	 
	
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model,HttpServletRequest request) {
		model.addAttribute("qyid", request.getParameter("qyid"));
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
		model.addAttribute("usertype", usertype);
		return "zyaqgl/xggl/xgfpd/index";
	}
	
	/**
	 * 数据list 
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xgfname", request.getParameter("zyaqgl_xgfpd_xgfname"));//相关方名称
		map.put("m12", request.getParameter("zyaqgl_xgfpd_m12"));//评定等级
		map.put("pdryid", UserUtil.getCurrentShiroUser().getId());//评定人id
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			map.put("qyid",(UserUtil.getCurrentShiroUser().getQyid()));
		}

		//安监条件
		String qyid = request.getParameter("qyid");
		if(StringUtils.isNotEmpty(qyid))
			map.put("qyid", qyid);
		return aqglxgfpdService.dataGrid(map);	
		
	}
	
	/**
	 * 数据list2
	 * @param request
	 */
	@RequiresPermissions("zyaqgl:xgfpd:view")
	@RequestMapping(value="list2")
	@ResponseBody
	public Map<String, Object> getData2(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("m12", request.getParameter("zyaqgl_xgfpd_m12"));//评定等级
		map.put("ID1", request.getParameter("id1"));//评定计划id
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			map.put("qyid",(UserUtil.getCurrentShiroUser().getQyid()));
		}
		return aqglxgfpdService.dataGrid2(map);	
		
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("zyaqgl:xgfpd:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model, HttpServletRequest request) {
		model.addAttribute("action", "create");
		model.addAttribute("dwid", request.getParameter("dwid"));
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "zyaqgl/xggl/xgfpd/form";
	}
	
	/**
	 * 添加相关方评定信息
	 * @param request,model
	 */
	@RequiresPermissions("zyaqgl:xgfpd:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(AQGL_RelevantEvaluation_content entity, Model model, HttpServletRequest request) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		//企业id
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			entity.setID1(UserUtil.getCurrentShiroUser().getQyid());
		}
		//添加相关方评定
		aqglxgfpdService.addInfo(entity);
		
		//修改评定计划平均分,评定等级
		Map<String, Object> map=aqglxgfpdjhService.findcount(entity.getID1());
		AQGL_RelevantEvaluation pdjh=aqglxgfpdjhService.findById(entity.getID1());
		pdjh.setM4(Integer.parseInt(map.get("cj").toString()));
		pdjh.setM5(map.get("level").toString());
		aqglxgfpdjhService.addInfo(pdjh);
		// 返回结果
		return datasuccess;
	}
	
	/**
	 * 评定人员选择页面跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "pdrychoose")
	public String pdrychoosechoose(Model model) {
		model.addAttribute("action", "pdrychoose");
		return "zyaqgl/xggl/xgfpd/index_pdrychoose";
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("zyaqgl:xgfpd:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的相关方评定信息
		Map<String, Object> xgfpd = aqglxgfpdService.findInforById(id);
		List<Map<String, Object>> pdcont = aqglXgfpdRecordService.findPdRecordById(id);
		model.addAttribute("xgfpd", xgfpd);
		model.addAttribute("pdcont", pdcont);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "zyaqgl/xggl/xgfpd/form";
	}
	
	/**
	 * 修改相关方评定信息
	 * @param request,model
	 */
	@RequiresPermissions("zyaqgl:xgfpd:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQGL_RelevantEvaluation_content entity,  Model model, HttpServletRequest request){
		String datasuccess="success";	
		String[] setids=request.getParameterValues("set_id");
		String[] points=request.getParameterValues("radio_sorce");
		String[] rids=request.getParameterValues("record_id");
		try {
			aqglxgfpdService.updateInfoTransactional(entity,setids,points,rids);
		} catch (Exception e) {
			datasuccess="failed";
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datasuccess;
	}
	 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("zyaqgl:xgfpd:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String, Object> xgfpd = aqglxgfpdService.findInforById(id);
		List<Map<String, Object>> pdcont = aqglXgfpdRecordService.findPdRecordById(id);
		model.addAttribute("xgfpd", xgfpd);
		model.addAttribute("pdcont", pdcont);
		//返回页面
		model.addAttribute("action", "view");
		return "zyaqgl/xggl/xgfpd/view";
	}
	
}
