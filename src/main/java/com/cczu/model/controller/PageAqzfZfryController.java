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

import com.cczu.model.entity.AQZF_TipstaffEntity;
import com.cczu.model.service.AqzfZfryService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 执法人员
 * @author zpc
 * @date 2017/08/02
 */
@Controller
@RequestMapping("aqzf/zfry")
public class PageAqzfZfryController extends BaseController{

	@Autowired
	private AqzfZfryService aqzfZfryService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "aqzf/sjwh/zfry/index";
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		map.put("xzqy", sessionuser.getXzqy());
		if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
			map.put("jglx",sessionuser.getUserroleflg());
		map.put("xm", request.getParameter("aqzf_zfry_M1"));
		map.put("zc", request.getParameter("aqzf_zfry_M4"));
		return aqzfZfryService.dataGrid(map);
	}
	
	/**
	 * 执法人员list下拉填充 id，text（姓名）
	 * @param model
	 * @return
	 */
	@RequestMapping(value="idlist")
	@ResponseBody
	public List<Map<String, Object>> getZfryIdlist(Model model) {
		Map<String, Object> mapData = new HashMap<String, Object>();
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		mapData.put("xzqy", sessionuser.getXzqy());
		if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
			mapData.put("jglx",sessionuser.getUserroleflg());
		return aqzfZfryService.getZfryIdlist(mapData);
	}
	
	/**
	 * 执法人员list下拉填充 id，text（姓名-职务）
	 * @param model
	 * @return
	 */
	@RequestMapping(value="xmzwlist")
	@ResponseBody
	public List<Map<String, Object>> getZfryXmzwlist(Model model) {
		Map<String, Object> mapData = new HashMap<String, Object>();
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		mapData.put("xzqy", sessionuser.getXzqy());
		if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
			mapData.put("jglx",sessionuser.getUserroleflg());
		return aqzfZfryService.getZfryXmzwlist(mapData);
	}

	/**
	 * 根据id查找执行人员idcard
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequestMapping(value = "findidcard/{id}")
	@ResponseBody
	public String findidcard(@PathVariable("id") Long id) {
		AQZF_TipstaffEntity xzry=aqzfZfryService.findById(id);
		return xzry.getM3();
	}
	
	/**
	 * 删除执行人员记录
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			aqzfZfryService.deleteInfo(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 添加执法人员页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("aqzf:zfry:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "aqzf/sjwh/zfry/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequiresPermissions("aqzf:zfry:add")
	@RequestMapping(value = "create" )
	@ResponseBody
	public String create(AQZF_TipstaffEntity zfry, HttpServletRequest request) {
		String datasuccess="success";
		ShiroUser su = UserUtil.getCurrentShiroUser();
		zfry.setID1(su.getId());
		zfry.setM6(su.getXzqy());
		aqzfZfryService.addInfo(zfry);
		return datasuccess;
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		AQZF_TipstaffEntity zfry  = aqzfZfryService.findById(id);
		model.addAttribute("zfry", zfry);
		model.addAttribute("action", "update");
		return "aqzf/sjwh/zfry/form";
	}
	
	/**
	 * 修改
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQZF_TipstaffEntity zfry, Model model){
		String datasuccess="success";	
		aqzfZfryService.updateInfo(zfry);
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		//查询选择的执法人员信息
		AQZF_TipstaffEntity zfry = aqzfZfryService.findById(id);		
		model.addAttribute("zfry", zfry);
		return "aqzf/sjwh/zfry/view";
	}
	
	/**
	 * 显示所有列
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("aqzf:zfry:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","aqzf/zfry/export");
		return "common/formexcel";
	}
	
	/**
	 * 导出Excel
	 * 
	 * @param request
	 */
	@RequiresPermissions("aqzf:zfry:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void getExcel(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		map.put("xzqy", sessionuser.getXzqy());
		if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
			map.put("jglx",sessionuser.getUserroleflg());
		map.put("xm", request.getParameter("aqzf_zfry_M1"));
		map.put("zc", request.getParameter("aqzf_zfry_M4"));
		aqzfZfryService.exportExcel(response, map);
	}
}
