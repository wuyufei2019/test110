package com.cczu.model.controller;

import java.sql.Timestamp;
import java.text.ParseException;
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

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.BIS_TechniqueEntity;
import com.cczu.model.service.IBisSjwhgwgyService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

@Controller
@RequestMapping("zxjkyj/gwgy")
public class PageZxjkyjSjwhGwgyController extends BaseController{
	
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private IBisSjwhgwgyService bisgwgyService;
	
	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "zxjkyj/sjwh/gwgy/index";
	}
	
	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		// 获取企业id
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		Map<String, Object> map = getPageMap(request);
		if (be != null) {
			map.put("qyid", be.getID());
		}else{
			map.put("qyid", request.getParameter("zxjkyj_sjwh_gwgy_id1"));
		}
		map.put("m1", request.getParameter("zxjkyj_sjwh_gwgy_m1"));
		return bisgwgyService.dataGrid(map);
	}
	
	/**
	 * 查看页面跳转
	 * 
	 * @param id
	 *            ,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		long id1 = id;
		BIS_TechniqueEntity bs = bisgwgyService.findById(id1);
		String a ="";
	    if(bs.getM4()==1){
		     a = "反应釜";
		}else if(bs.getM4()==2){
		     a = "反应塔";
		}
		model.addAttribute("a",a);
		model.addAttribute("gwgylist", bs);
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "zxjkyj/sjwh/gwgy/view";
	}
	
	/**
	 * 删除高危工艺记录
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
			bisgwgyService.deleteInfo(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 添加高危工艺信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("sjwh:gwgy:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "zxjkyj/sjwh/gwgy/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequiresPermissions("sjwh:gwgy:add")
	@RequestMapping(value = "create" )
	@ResponseBody
	public String create(BIS_TechniqueEntity gwgy, HttpServletRequest request) {
		String datasuccess="success";
		if(!UserUtil.getCurrentShiroUser().getUsertype().equals("9")){
			gwgy.setID1(UserUtil.getCurrentShiroUser().getQyid());
		}
		gwgy.setS3(0);
		gwgy.setS1(new Timestamp(System.currentTimeMillis()));
		gwgy.setS2(gwgy.getS1());
		bisgwgyService.addInfo(gwgy);
		return datasuccess;
	}
	
	/**
	 * 修改页面跳转
	 * 
	 * @param id
	 *            ,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		// 查询选择的储罐信息
		long id1 = id;
		BIS_TechniqueEntity bs = bisgwgyService.findById(id1);
		model.addAttribute("gwgy", bs);
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "zxjkyj/sjwh/gwgy/form";
	}
	
	/**
	 * 修改高危工艺信息
	 * 
	 * @param request
	 *     
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(BIS_TechniqueEntity bt, Model model) {
		String datasuccess = "success";
		if(UserUtil.getCurrentShiroUser().getUsertype().equals("9")){
			bisgwgyService.updateInfo1(bt);
		}else{
			bt.setID1(UserUtil.getCurrentShiroUser().getQyid());
			bisgwgyService.updateInfo2(bt);
		}
		// 返回结果
		return datasuccess;
	}
}
