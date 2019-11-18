package com.cczu.model.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.service.WghglTjfxService;
import com.cczu.model.service.WghglWgyxjjlService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.StringUtils;

/**
 * 网格点Controller
 * @author YZH
 */
@Controller
@RequestMapping("wghgl/tjfx")
public class PageWghglTjfxController extends BaseController {
	
	@Autowired
	private WghglTjfxService wghglTjfxService;
	@Autowired
	private WghglWgyxjjlService wghglWgyxjjlService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "wghgl/tjfx/index";
	}
	
	/**
	 * 巡检点列表显示页面
	 * @param model
	 */
	@RequestMapping(value="xjdindex")
	public String xjdindex(Model model) {
		return "wghgl/tjfx/xjdindex";
	}
	
	/**
	 * 网格点月检list页面 
	 * @param request
	 */
	@RequestMapping(value="xjdyjlist")
	@ResponseBody
	public Map<String, Object> getxjdyjData(HttpServletRequest request, Model model) {
		Map<String, Object> map = getPageMap(request);
		String yf = request.getParameter("wghgl_yjxjd_yf");
		if(!StringUtils.isBlank(yf)){
			if(yf.length()==6){
				yf = yf.substring(0,5) +"0"+yf.substring(5);
			}
		}
		else{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
			Date date = new Date();
			yf = dateFormat.format(date);
		}
		map.put("yf",yf);
		map.put("qyname", request.getParameter("wghgl_yjxjd_qyname"));
		map.put("xjdxzqy", request.getParameter("wghgl_yjxjd_xzqy"));
		ShiroUser user = UserUtil.getCurrentShiroUser();
		if("0".equals(user.getUsertype())){//安监
			map.put("xzqy",user.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(user.getUserroleflg()!=null&&user.getUserroleflg()!=0)
				map.put("jglx",user.getUserroleflg());
		}
		return wghglTjfxService.xjdyjdataGrid(map);	
	}
	
	/**
	 * 查看巡检点巡检记录跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="xjdview")
	public String getxjdView(HttpServletRequest request, Model model) {
		model.addAttribute("xjdid", request.getParameter("xjdid"));
		model.addAttribute("yf", request.getParameter("yf"));
		model.addAttribute("nf", request.getParameter("nf"));
		return "wghgl/tjfx/xjdview";
	}
	
	/**
	 * 查看巡检点巡检记录
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="xjdviewlist")
	@ResponseBody
	public Map<String, Object> getxjdViewList(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xjdid", request.getParameter("xjdid"));
		String yf = request.getParameter("yf");
		if(!StringUtils.isBlank(yf)){
			if(yf.length()==6){
				yf = yf.substring(0,5) +"0"+yf.substring(5);
			}
			map.put("yf", yf);
		}	
		String nf = request.getParameter("nf");
		if(!StringUtils.isBlank(nf)){	
			map.put("nf",nf);
		}
		return wghglWgyxjjlService.dataGrid(map);
	}
	
	/**
	 * 网格点年检list页面 
	 * @param request
	 */
	@RequestMapping(value="xjdnjlist")
	@ResponseBody
	public Map<String, Object> getxjdnjData(HttpServletRequest request, Model model) {
		Map<String, Object> map = getPageMap(request);
		String nf = request.getParameter("wghgl_njxjd_nf");
		if(StringUtils.isBlank(nf)){	
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
			Date date = new Date();
			nf = dateFormat.format(date);
		}
		map.put("nf",nf);
		map.put("qyname", request.getParameter("wghgl_njxjd_qyname"));
		map.put("xjdxzqy", request.getParameter("wghgl_njxjd_xzqy"));
		ShiroUser user = UserUtil.getCurrentShiroUser();
		if("0".equals(user.getUsertype())){//安监
			map.put("xzqy",user.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(user.getUserroleflg()!=null&&user.getUserroleflg()!=0)
				map.put("jglx",user.getUserroleflg());
		}
		return wghglTjfxService.xjdnjdataGrid(map);	
	}
	
	/**
	 * 班次列表显示页面
	 * @param model
	 */
	@RequestMapping(value="bcindex")
	public String bcindex(Model model) {
		return "wghgl/tjfx/bcindex";
	}
	
	/**
	 * 班次月检list页面 
	 * @param request
	 */
	@RequestMapping(value="bcyjlist")
	@ResponseBody
	public Map<String, Object> getbcyjData(HttpServletRequest request, Model model) {
		Map<String, Object> map = getPageMap(request);
		String yf = request.getParameter("wghgl_yjbc_yf");
		if(!StringUtils.isBlank(yf)){
			if(yf.length()==6){
				yf = yf.substring(0,5) +"0"+yf.substring(5);
			}
		}
		else{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
			Date date = new Date();
			yf = dateFormat.format(date);
		}
		map.put("yf",yf);
		map.put("bcname", request.getParameter("wghgl_yjbc_bcname"));
		map.put("bcxzqy", request.getParameter("wghgl_yjbc_xzqy"));
		ShiroUser user = UserUtil.getCurrentShiroUser();
		if("0".equals(user.getUsertype())){//安监
			map.put("wgxzqy",user.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
//			if(user.getUserroleflg()!=null&&user.getUserroleflg()!=0)
//				map.put("userjglx",user.getUserroleflg());
		}
		return wghglTjfxService.bcyjdataGrid(map);	
	}
	
	/**
	 * 班次年检list页面 
	 * @param request
	 */
	@RequestMapping(value="bcnjlist")
	@ResponseBody
	public Map<String, Object> getbcnjData(HttpServletRequest request, Model model) {
		Map<String, Object> map = getPageMap(request);
		String nf = request.getParameter("wghgl_njbc_nf");
		if(StringUtils.isBlank(nf)){	
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
			Date date = new Date();
			nf = dateFormat.format(date);
		}
		map.put("nf",nf);
		map.put("bcname", request.getParameter("wghgl_njbc_bcname"));
		map.put("bcxzqy", request.getParameter("wghgl_njbc_xzqy"));
		ShiroUser user = UserUtil.getCurrentShiroUser();
		if("0".equals(user.getUsertype())){//安监
			map.put("wgcode",user.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
//			if(user.getUserroleflg()!=null&&user.getUserroleflg()!=0)
//				map.put("userjglx",user.getUserroleflg());
		}
		return wghglTjfxService.bcnjdataGrid(map);	
	}
	
	/**
	 * 查看巡检班次巡检记录跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="bcview")
	public String getbcView(HttpServletRequest request, Model model) {
		model.addAttribute("bcid", request.getParameter("bcid"));
		model.addAttribute("yf", request.getParameter("yf"));
		model.addAttribute("nf", request.getParameter("nf"));
		return "wghgl/tjfx/bcview";
	}
	
	/**
	 * 查看巡检班次巡检记录
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="bcviewlist")
	@ResponseBody
	public Map<String, Object> getbcViewList(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("bcid", request.getParameter("bcid"));
		String yf = request.getParameter("yf");
		if(!StringUtils.isBlank(yf)){
			if(yf.length()==6){
				yf = yf.substring(0,5) +"0"+yf.substring(5);
			}
			map.put("yf", yf);
		}	
		String nf = request.getParameter("nf");
		if(!StringUtils.isBlank(nf)){	
			map.put("nf",nf);
		}
		return wghglWgyxjjlService.dataGrid(map);
	}
	
	/**
	 * 巡检人员列表显示页面
	 * @param model
	 */
	@RequestMapping(value="xjryindex")
	public String xjryindex(Model model) {
		return "wghgl/tjfx/xjryindex";
	}
	
	/**
	 * 巡检人员月检list页面 
	 * @param request
	 */
	@RequestMapping(value="xjryyjlist")
	@ResponseBody
	public Map<String, Object> getyjxjryData(HttpServletRequest request, Model model) {
		Map<String, Object> map = getPageMap(request);
		String yf = request.getParameter("wghgl_yjxjry_yf");
		if(!StringUtils.isBlank(yf)){
			if(yf.length()==6){
				yf = yf.substring(0,5) +"0"+yf.substring(5);
			}
		}
		else{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
			Date date = new Date();
			yf = dateFormat.format(date);
		}
		map.put("yf",yf);
		map.put("ryname", request.getParameter("wghgl_yjxjry_ryname"));
		map.put("ryxzqy", request.getParameter("wghgl_yjxjry_xzqy"));
		ShiroUser user = UserUtil.getCurrentShiroUser();
		if("0".equals(user.getUsertype())){//安监
			map.put("userxzqy",user.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(user.getUserroleflg()!=null&&user.getUserroleflg()!=0)
				map.put("userjglx",user.getUserroleflg());
		}
		return wghglTjfxService.xjryyjdataGrid(map);	
	}
	
	/**
	 * 巡检人员年检list页面 
	 * @param request
	 */
	@RequestMapping(value="xjrynjlist")
	@ResponseBody
	public Map<String, Object> getnjxjryData(HttpServletRequest request, Model model) {
		Map<String, Object> map = getPageMap(request);
		String nf = request.getParameter("wghgl_njxjry_nf");
		if(StringUtils.isBlank(nf)){	
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
			Date date = new Date();
			nf = dateFormat.format(date);
		}
		map.put("nf",nf);
		map.put("ryname", request.getParameter("wghgl_njxjry_ryname"));
		map.put("ryxzqy", request.getParameter("wghgl_njxjry_xzqy"));
		ShiroUser user = UserUtil.getCurrentShiroUser();
		if("0".equals(user.getUsertype())){//安监
			map.put("userxzqy",user.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(user.getUserroleflg()!=null&&user.getUserroleflg()!=0)
				map.put("userjglx",user.getUserroleflg());
		}
		return wghglTjfxService.xjrynjdataGrid(map);	
	}
	
	/**
	 * 查看巡检人员巡检记录跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="xjryview")
	public String getxjryView(HttpServletRequest request, Model model) {
		model.addAttribute("xjryid", request.getParameter("xjryid"));
		model.addAttribute("yf", request.getParameter("yf"));
		model.addAttribute("nf", request.getParameter("nf"));
		return "wghgl/tjfx/xjryview";
	}
	
	/**
	 * 查看巡检人员巡检记录
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="xjryviewlist")
	@ResponseBody
	public Map<String, Object> getxjryViewList(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xjryid", request.getParameter("xjryid"));
		String yf = request.getParameter("yf");
		if(!StringUtils.isBlank(yf)){
			if(yf.length()==6){
				yf = yf.substring(0,5) +"0"+yf.substring(5);
			}
			map.put("yf", yf);
		}	
		String nf = request.getParameter("nf");
		if(!StringUtils.isBlank(nf)){	
			map.put("nf",nf);
		}
		return wghglWgyxjjlService.dataGrid(map);
	}
}
