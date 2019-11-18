package com.cczu.sys.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.Barrio;
import com.cczu.sys.system.service.BarrioService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 网格管理controller
 * @author jason
 */
@Controller
@RequestMapping("system/admin/xzqy")
public class BarrioController extends BaseController{
	
	@Autowired
	private BarrioService barrioService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "system/wgh/areaList";
	}
	/**
	 * 网格总览页面跳转
	 */
	@RequestMapping(value="allindex",method = RequestMethod.GET)
	public String indexall(Model model) {
		String code=UserUtil.getCurrentShiroUser().getXzqy();
		List<Map<String,Object>> list= barrioService.getChildlist(code);
		model.addAttribute("wglist", JsonMapper.getInstance().toJson(list));
		model.addAttribute("barrio", JsonMapper.getInstance().toJson(barrioService.findPointBycode(code)));
		return "system/wgh/allmapindex";
	}
	/**
	 * 网格总览页面统计数据填充(区分工贸化工)（只查询网格区域直属企业）
	 */
	@RequestMapping(value="tjjson",method = RequestMethod.GET)
	@ResponseBody
	public String getTjDataJson(HttpServletRequest request) {
		String code=request.getParameter("xzqy");
		Map<String,Object> map = getAuthorityMap();
		if(StringUtils.isNotBlank(code)){
			map.put("xzqy", code);
		}
		Map<String, Object> remap= barrioService.getTjDatalist(map);
		return JsonMapper.getInstance().toJson(remap);
	}
	/**
	 * 勾画地图页面跳转
	 */
	@RequestMapping(value="mindex/{id}",method = RequestMethod.GET)
	public String mapIndex(@PathVariable("id")long id,Model model) {
		Barrio bro=barrioService.getBarrioById(id);
		model.addAttribute("bro", bro);//网格对象
		//model.addAttribute("qylist", JsonMapper.getInstance().toJson(barrioService.findQyByXzqy(bro.getCode())));
		model.addAttribute("wglist", JsonMapper.getInstance().toJson(barrioService.getNotNonelist(bro.getCode())));
		return "system/wgh/mapindex";
	}
	/**
	 * 查看地图页面跳转
	 */
	@RequestMapping(value="view/{id}")
	public String viewIndex(@PathVariable("id")long id,Model model) {
		Barrio bro=barrioService.getBarrioById(id);
		//model.addAttribute("action", "view");
		model.addAttribute("mappoint", bro.getMappoint());
		model.addAttribute("qylist", JsonMapper.getInstance().toJson(barrioService.findQyByXzqy(bro.getCode())));
		return "system/wgh/viewmapindex";
	}
	/**
	 * 绑定企业地图页面跳转
	 */
	@RequestMapping(value="bindqy/{code}")
	public String BindIndex(@PathVariable("code")String code,Model model) {
		model.addAttribute("code", code);
		//model.addAttribute("mappoint", bro.getMappoint());
		return "system/wgh/qychoose";
	}
	
	/**
	 * 选择企业list页面
	 */
	@RequestMapping(value = "qylist")
	@ResponseBody
	public Map<String, Object> getQyList(HttpServletRequest request) {
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userxzqy", sessionuser.getXzqy());//用户行政区域
		map.put("wgxzqy", request.getParameter("code"));//网格、企业行政区域
		map.put("qynm", request.getParameter("qynm"));
		map.put("zhen", request.getParameter("zhen"));//镇或街道
		return barrioService.findQy(map);

	}
	/**
	 * 修改mappoint
	 */
	@RequestMapping(value="updmappoint",method = RequestMethod.POST)
	@ResponseBody
	public String updateMapPoint(HttpServletRequest request) {
		String result="success";
		try {
			barrioService.updateMapPoint(Long.parseLong(request.getParameter("id")), request.getParameter("mappoint"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result="error";
		}
		return result;
				
	}
	/**
	 * 修改企业行政区域
	 */
	@RequestMapping(value="updatexzqy",method = RequestMethod.POST)
	@ResponseBody
	public String updateQyXzqy(HttpServletRequest request) {
		return barrioService.updateQyAndUserXzqy(request.getParameter("ids"), request.getParameter("xzqy"),UserUtil.getCurrentShiroUser().getXzqy());
	}
	
	/**
	 * 网格设置list
	 */
	@RequestMapping(value="json")
	@ResponseBody
	public String  list(){
		return barrioService.getJson_list();
	}
	/**
	 * 获取网格和该网格下级网格json
	 */
	@RequestMapping(value="twolevjson/{xzqy}")
	@ResponseBody
	public String  twoLevlist(@PathVariable("xzqy")String xzqy){
		List<Map<String,Object>> list= barrioService.getChildlist(xzqy);
 		return JsonMapper.getInstance().toJson(list);
	}
	/**
	 * 获取该网格上级网格和上级网格的所有下级网格json
	 * param:xzqy：地图所处网格code
	 */
	@RequestMapping(value="parentjson/{xzqy}")
	@ResponseBody
	public String  list(@PathVariable("xzqy")String xzqy){
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		//当前用户行政区
		String curxzqy=sessionuser.getXzqy();
		List<Map<String,Object>> list= barrioService.getParentlist(xzqy,curxzqy);
		String fcode="";
		if(list.size()>1){
			fcode=list.get(1).get("code")+"";
		}else{
			fcode=list.get(0).get("code")+"";
		}
		List<Map<String,Object>> relist= barrioService.getChildlist(fcode);
		return JsonMapper.getInstance().toJson(relist);
	}
	
	/**
	 * 添加跳转
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String menuCreateForm(Model model) {
		model.addAttribute("bis", new Barrio());
		model.addAttribute("action", "create");
		return "system/wgh/areaForm";
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public String create(@Valid Barrio bis,Model model) {
		barrioService.save(bis);
		return "success";
	}
	
	/**
	 * 修改跳转
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateMenuForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("bis", barrioService.get(id));
		model.addAttribute("action", "update");
		return "system/wgh/areaForm";
	}

	/**
	 * 修改
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(@Valid @ModelAttribute("permission") Barrio bis,Model model) {
		barrioService.update(bis);
		return "success";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") Long id) {
		barrioService.delete(id);
		return "删除成功！";
	}
	
	/**
	 * 区域字典显示  {网格编码   ： 网格名称}
	 * @param
	 */
	@RequestMapping(value="xzqyjson")
	@ResponseBody
	public String xzqyjson(String code) {
		
		if(StringUtils.isBlank(code)){
			ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
			if(sessionuser.getUsertype().equals("0")){
				code=sessionuser.getXzqy();
			}else{
				code="";
			}
		}
		return barrioService.jsonListCode(code);
	}
	
	/**
	 * 区域字典显示 {网格id   ： 网格名称}
	 * @param
	 */
	@RequestMapping(value="idjson")
	@ResponseBody
	public String xzqyjson2(String code) {
		if(StringUtils.isBlank(code)){
			ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
			if(sessionuser.getUsertype().equals("0")){
				code=sessionuser.getXzqy();
			}else{
				code="";
			}
		}
		return barrioService.jsonListBycode(code);
	}
	
	/**
	 * 根据行政区域查询企业数据返回json（只查询网格区域直属企业）
	 * return String
	 */
	@RequestMapping(value="qyjson")
	@ResponseBody
	public String codeIdjson(String xzqy) {
		Map<String, Object> map = new HashMap<String, Object>();
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if("undefined".equals(xzqy)||StringUtils.isBlank(xzqy)){
			if(sessionuser.getUsertype().equals("0")){
				map.put("equalxzqy",sessionuser.getXzqy());
				//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
				if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
					map.put("jglx",sessionuser.getUserroleflg());
			} 	
		}else{
			map.put("equalxzqy",xzqy);
			if(sessionuser.getUsertype().equals("0")&&sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0){
				//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
				map.put("jglx",sessionuser.getUserroleflg());
			} 
		}
		return  bisQyjbxxService.getQyIdjson(map);
	}
	
	
	/**
	 * 查看网格员
	 * @param model
	 * @return
	 */
	@RequiresRoles(value={"admin","superadmin","ajcountyadmin","ajtownadmin"},logical=Logical.OR)
	@RequestMapping(value="viewWgUser",method = RequestMethod.GET)
	public String viewWgUser(String code,Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("usertype", 0);
		map.put("eqxzqy", code);
		List<Map<String, Object>> list=barrioService.getWgUser(map);
		model.addAttribute("userlist", JsonMapper.getInstance().toJson(list));
		return "system/wgh/userList";
	}
	/**
	 * 网格员下拉
	 * @param model
	 * @return
	 */
	@RequestMapping(value="wguserjson",method = RequestMethod.GET)
	@ResponseBody
	public String WgUserjson(String code,Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("usertype", 0);
		map.put("eqxzqy", StringUtils.isBlank(code)?UserUtil.getCurrentShiroUser().getXzqy():code);
		List<Map<String, Object>> list=barrioService.getWgUser(map);
		return JsonMapper.getInstance().toJson(list);
	}
	
}
