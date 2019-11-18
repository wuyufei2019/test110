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

import com.cczu.model.dao.AqzfJcjhQyDao;
import com.cczu.model.entity.AQZF_Plan_EnterpriseEntity;
import com.cczu.model.entity.AQZF_SafetyCheckPlanEntity;
import com.cczu.model.service.AqzfJcfaService;
import com.cczu.model.service.AqzfJcjlService;
import com.cczu.model.service.IAqzfJcjhService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 检查计划
 * @author zpc
 * @date 2017/07/27
 */
@Controller
@RequestMapping("aqzf/jcjh")
public class PageAqzfJcjhController extends BaseController{

	@Autowired
	private AqzfJcjlService aqzfJcjlService;
	@Autowired
	private IAqzfJcjhService AqzfJcjhService;
	@Autowired
	private AqzfJcjhQyDao AqzfJcjhQyDao;
	@Autowired
	private AqzfJcfaService aqzfJcfaService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "aqzf/jcjh/index";
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		map.put("xzqy",sessionuser.getXzqy());
		if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
			map.put("jglx",sessionuser.getUserroleflg());
		map.put("year", request.getParameter("aqzf_jcjh_year"));
		map.put("month", request.getParameter("aqzf_jcjh_month"));
		map.put("M3", request.getParameter("aqzf_jcjh_M3"));
		map.put("M4", request.getParameter("aqzf_jcjh_M4"));
		map.put("M5", request.getParameter("aqzf_jcjh_M5"));
		return AqzfJcjhService.dataGrid(map);
	}
	
	/**
	 * 删除检查计划记录
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		AQZF_Plan_EnterpriseEntity pe = AqzfJcjhQyDao.findInfoById(Long.parseLong(ids));
		AqzfJcjhService.deleteInfo(pe.getID1());
		return datasuccess;
	}
	
	/**
	 * 添加检查计划页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("aqzf:jcjh:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "aqzf/jcjh/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequiresPermissions("aqzf:jcjh:add")
	@RequestMapping(value = "create" )
	@ResponseBody
	public String create(AQZF_SafetyCheckPlanEntity jcjh,String qyids, HttpServletRequest request) {
		String datasuccess="success";
		if(!qyids.equals("")){
			jcjh.setID1(UserUtil.getCurrentShiroUser().getId());
			long a = AqzfJcjhService.addjcjh(jcjh);
			//添加企业计划表
			String[] aids = qyids.split(",");
			for(int i=0;i<aids.length;i++){
				AQZF_Plan_EnterpriseEntity pe = new AQZF_Plan_EnterpriseEntity();
				pe.setID1(a);
				pe.setID2(Long.parseLong(aids[i]));
				pe.setM1("0");
				AqzfJcjhQyDao.addInfo(pe);
			}
		}else{
			datasuccess = "error";
		}
		return datasuccess;
	}

	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		AQZF_Plan_EnterpriseEntity pe = AqzfJcjhQyDao.findInfoById(id);
		AQZF_SafetyCheckPlanEntity jcjh  = AqzfJcjhService.findById2(pe.getID1());
		String[] a = jcjh.getM2().split("月");
		String b = jcjh.getM1()+"-"+a[0];
		jcjh.setM2(b);
		model.addAttribute("jcjh", jcjh);
		List<Map<String,Object>> list = AqzfJcjhQyDao.selectbyid1(pe.getID1());
		String qyids = "";
		for (Map<String,Object> map : list) {
			qyids= qyids+map.get("id").toString()+"||"+map.get("m1").toString()+",";
		}
		model.addAttribute("idname", qyids);
		model.addAttribute("action", "update");
		return "aqzf/jcjh/form";
	}
	
	/**
	 * 修改
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQZF_SafetyCheckPlanEntity jcjh,String qyids, HttpServletRequest request){
		String datasuccess="success";	
		jcjh.setID1(UserUtil.getCurrentShiroUser().getId());
		AqzfJcjhService.addInfo(jcjh);
		
		List<AQZF_Plan_EnterpriseEntity> list = AqzfJcjhQyDao.selectList(jcjh.getID());
		//拆解qyids
		String[] aids = qyids.split(",");
		int z = 0;
		int p = 0;
		for(int i=0;i<aids.length;i++){
			z=0;
			for (AQZF_Plan_EnterpriseEntity pe : list) {
				if(aids[i].equals(pe.getID2()+"")){
					z=1;
					break;
				}
			}
			if(z==0){
				//添加
				AQZF_Plan_EnterpriseEntity a = new AQZF_Plan_EnterpriseEntity();
				a.setID1(jcjh.getID());
				a.setID2(Long.parseLong(aids[i]));
				a.setM1("0");
				AqzfJcjhQyDao.save(a);
			}
		}
		for (AQZF_Plan_EnterpriseEntity pe : list) {
			p = 0;
			for(int i=0;i<aids.length;i++){
				if((pe.getID2()+"").equals(aids[i])){
					p=1;
					break;
				}
			}
			if(p==0){
				//删除
				AqzfJcjhQyDao.deleteex(pe);
			}
		}
		
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		List<Map<String, Object>> jcjh = AqzfJcjhService.findById(id);		
		model.addAttribute("jcjh", jcjh.get(0));
		return "aqzf/jcjh/view";
	}
	
	/**
	 * 显示所有列
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("aqzf:jcjh:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","aqzf/jcjh/export");
		return "common/formexcel";
	}
	
	/**
	 * 导出Excel
	 * 
	 * @param request
	 */
	@RequiresPermissions("aqzf:jcjh:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void getExcel(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		map.put("xzqy",sessionuser.getXzqy());
		if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
			map.put("jglx",sessionuser.getUserroleflg());
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.put("year", request.getParameter("aqzf_jcjh_year"));
		map.put("month", request.getParameter("aqzf_jcjh_month"));
		map.put("M3", request.getParameter("aqzf_jcjh_M3"));
		map.put("M4", request.getParameter("aqzf_jcjh_M4"));
		map.put("M5", request.getParameter("aqzf_jcjh_M5"));
		AqzfJcjhService.exportExcel(response, map);
	}
	
	/**
	 * 企业选择页面跳转
	 * @param model
	 */
	@RequestMapping(value = "choose")
	public String choose(Model model) {
		model.addAttribute("action", "choose");
		return "aqzf/jcjh/index_qychoose";
	}
	
	/**
	 * 选择企业list页面
	 */
	@RequestMapping(value = "qylist")
	@ResponseBody
	public Map<String, Object> getQyList(String sd,String hylx,HttpServletRequest request) {
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("xzqy", sessionuser.getXzqy());
		if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0){
			map.put("jglx",sessionuser.getUserroleflg());
		}
		map.put("qyname", request.getParameter("qynm"));
		//属地行业类型
		if(!sd.equals("flag")){
			map.put("xzqy", sd);
		}
		if(!hylx.equals("flag")){
			map.put("jglx", hylx);
		}
		return bisQyjbxxService.findAllQyList(map);

	}
	
	/**
	 * 判断该计划是否有企业添加检查发案
	 * @param model
	 * id 关联表id
	 */
	@RequestMapping(value = "pd/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String pd(@PathVariable("id") Long id,Model model) {
		AQZF_Plan_EnterpriseEntity pe = AqzfJcjhQyDao.findInfoById(id);
		AQZF_SafetyCheckPlanEntity jcjh  = AqzfJcjhService.findById2(pe.getID1());
		List<AQZF_Plan_EnterpriseEntity> list = AqzfJcjhQyDao.selectList(jcjh.getID());
		String a = "success";
		for (AQZF_Plan_EnterpriseEntity ape : list) {
			if(ape.getM1().equals("1")){
				a = "error";
				break;
			}
		}
		return a;
	}
	
}
