package com.cczu.model.controller;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.AQZF_ReformEntity;
import com.cczu.model.entity.AQZF_ReviewEntity;
import com.cczu.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.model.entity.AQZF_WfxwEntity;
import com.cczu.model.service.AqzfFcyjService;
import com.cczu.model.service.AqzfJcfaService;
import com.cczu.model.service.AqzfJcjlService;
import com.cczu.model.service.AqzfSetBasicInfoService;
import com.cczu.model.service.AqzfWfxwService;
import com.cczu.model.service.AqzfZlzgService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;


/**
 * 复查意见Controller
 * @author YZH
 */
@Controller
@RequestMapping("aqzf/fcyj")
public class PageAqzfFcyjController extends BaseController {

	@Autowired
	private AqzfJcjlService aqzfJcjlService;
	@Autowired
	private AqzfJcfaService aqzfJcfaService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private AqzfFcyjService aqzfFcyjService;
	@Autowired
	private AqzfZlzgService aqzfZlzgService;
	@Autowired
	private AqzfWfxwService aqzfWfxwService;
	@Autowired
	private AqzfSetBasicInfoService setbasicservice;
	 
	
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "aqzf/fcyj/index";
	}
	
	/**
	 * list页面 
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("0")){//安监局
			map.put("xzqy",sessionuser.getXzqy());
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
				map.put("jglx",sessionuser.getUserroleflg());
		}
		map.put("qyname", request.getParameter("aqzf_fcyj_qyname"));
		map.put("M1", request.getParameter("aqzf_fcyj_M1"));
		return aqzfFcyjService.dataGrid(map);	
		
	}

	
	/**
	 * 添加页面跳转
	 * @param model
	 * id 责令整改id
	 */
	@RequestMapping(value = "create/{id}" , method = RequestMethod.GET)
	public String create(@PathVariable("id") Long id, Model model) {
		AQZF_ReformEntity zlzg = aqzfZlzgService.findById(id);
		
		AQZF_ReviewEntity fcyj = new AQZF_ReviewEntity();
		fcyj.setID1(zlzg.getID());//责令整改id
		fcyj.setID2(zlzg.getID2());//被检查单位id
		fcyj.setM5(zlzg.getM8());//负责人
		
		//存在问题
		String czwt = "";
		if(!StringUtils.isEmpty(zlzg.getM2())){
			String[] wfxwids = zlzg.getM2().split(",");
			for(int i=0;i<wfxwids.length;i++){
				AQZF_WfxwEntity wfxw = new AQZF_WfxwEntity();
				 try {
			    	wfxw = aqzfWfxwService.findById(Long.parseLong(wfxwids[i]));
			    	czwt += (i+1)+". "+wfxw.getM1()+" ";
				} catch (Exception e) {
					czwt += (i+1)+". "+wfxwids[i]+" ";
				}
			}
		}
		fcyj.setM2(czwt);
		
		fcyj.setM3_1(zlzg.getM6_1());//执法人员1
		fcyj.setM3_2(zlzg.getM6_2());//执法人员2
		fcyj.setM4_1(zlzg.getM7_1());//执法人员证号1
		fcyj.setM4_2(zlzg.getM7_2());//执法人员证号2
		model.addAttribute("action", "create");
		model.addAttribute("fcyj", fcyj);
		return "aqzf/fcyj/form";
	}
	
	/**
	 * 添加复查意见信息
	 * @param request,model
	 */
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(AQZF_ReviewEntity entity, Model model) {
		String datasuccess="success";
		//添加整改复查
		AQZF_SetBasicInfoEntity bh =setbasicservice.findInfor();
		AQZF_ReformEntity zlzg = aqzfZlzgService.findById(entity.getID1());
		Calendar a=Calendar.getInstance();
		String year=a.get(Calendar.YEAR)+"";
		String m0 = zlzg.getM0();
		entity.setM0("（"+bh.getSsqjc()+"）安监复查〔"+year+"〕"+m0.substring(m0.indexOf("〕")+1, m0.length()));
		
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		aqzfFcyjService.addInfo(entity);
		return datasuccess;
		
	}	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("aqzf:fcyj:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的复查意见信息
		AQZF_ReviewEntity fcyj = aqzfFcyjService.findById(id);
		model.addAttribute("fcyj", fcyj);
		//返回页面
		model.addAttribute("action", "update");
		return "aqzf/fcyj/form";
	}
	
	/**
	 * 修改复查意见信息
	 * @param request,model
	 */
	@RequiresPermissions("aqzf:fcyj:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQZF_ReviewEntity entity,  Model model){
		String datasuccess="success";	
		entity.setS1(new java.sql.Timestamp(new java.util.Date().getTime()));
		entity.setS2(new java.sql.Timestamp(new java.util.Date().getTime()));
		entity.setS3(0);
		
		aqzfFcyjService.updateInfo(entity);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除复查意见信息
	 */
	@RequiresPermissions("aqzf:fcyj:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			aqzfFcyjService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("aqzf:fcyj:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String, Object> fcyj = aqzfFcyjService.findInforById(id);
		model.addAttribute("fcyj", fcyj);
		return "aqzf/fcyj/view";
	}

	/**
	 * 导出复查意见书word
	 * 
	 */
	@RequiresPermissions("aqzf:fcyj:exportword")
	@RequestMapping(value = "exportword/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = aqzfFcyjService.getAjWord(id);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "zgfcyj.ftl", filePath, filename, request);
		return "/download/" + filename;
	}

}
