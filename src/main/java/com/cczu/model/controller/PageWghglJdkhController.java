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

import com.cczu.model.service.WghglJdkhService;
import com.cczu.model.service.WghglWgyxjjlService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.StringUtils;

/**
 * 监督考核Controller
 * @author ZPC
 */
@Controller
@RequestMapping("wghgl/jdkh")
public class PageWghglJdkhController extends BaseController {
	
	@Autowired
	private WghglJdkhService wghglJdkhService;
	@Autowired
	private WghglWgyxjjlService wghglWgyxjjlService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String xjryindex(Model model) {
		return "wghgl/jdkh/index";
	}
	
	/**
	 * 月检list页面 
	 * @param request
	 */
	@RequestMapping(value="yjlist")
	@ResponseBody
	public Map<String, Object> getyjData(HttpServletRequest request, Model model) {
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
			map.put("xzqy",user.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(user.getUserroleflg()!=null&&user.getUserroleflg()!=0)
				map.put("jglx",user.getUserroleflg());
		}
		return wghglJdkhService.yjdataGrid(map);	
	}
	
	/**
	 * 年检list页面 
	 * @param request
	 */
	@RequestMapping(value="njlist")
	@ResponseBody
	public Map<String, Object> getnjData(HttpServletRequest request, Model model) {
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
			map.put("xzqy",user.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(user.getUserroleflg()!=null&&user.getUserroleflg()!=0)
				map.put("jglx",user.getUserroleflg());
		}
		return wghglJdkhService.njdataGrid(map);	
	}
	
	/**
	 * 查看巡检记录跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="view")
	public String getView(HttpServletRequest request, Model model) {
		model.addAttribute("wgdid", request.getParameter("wgdid"));
		model.addAttribute("userid", request.getParameter("userid"));
		model.addAttribute("yf", request.getParameter("yf"));
		model.addAttribute("nf", request.getParameter("nf"));
		return "wghgl/jdkh/view";
	}
	
	/**
	 * 查看巡检记录
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="viewlist")
	@ResponseBody
	public Map<String, Object> getViewList(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		String wgdid = request.getParameter("wgdid");
		if(!wgdid.equals("null")){
			map.put("xjdid", request.getParameter("wgdid"));
		}
		map.put("xjryid", request.getParameter("userid"));
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
