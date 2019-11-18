package com.cczu.model.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.AqpxRcpxtjService;
import com.cczu.model.service.IAqpxKscjService;
import com.cczu.model.service.IAqpxXxjlService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 安全培训管理——日常培训Controller
 * @author jason
 */
@Controller
@RequestMapping("aqpx/rcpxtj")
public class PageAqpxRcpxtjController extends BaseController {
	
	@Autowired
	private IAqpxKscjService aqpxKscjService;
	@Autowired
	private AqpxRcpxtjService aqpxRcpxtjService;
	@Autowired
	private IAqpxXxjlService aqpxXxjlService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					return "aqpx/rcpxtj/ajindex";
				else
					return "aqpx/rcpxtj/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "aqpx/rcpxtj/ajindex";
		}
	}
	
	/**
	 * 培训计划统计list页面
	 * @param request
	 */
	@RequestMapping(value="pxjhlist")
	@ResponseBody
	public Map<String, Object> getPxjhData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("m1", request.getParameter("rcpxtj_pxjh_m1"));
		map.putAll(getAuthorityMap());
		return aqpxRcpxtjService.dataPxjhGrid(map);
		
	}

	/**
	 * 员工学习统计list页面
	 * @param request
	 */
	@RequestMapping(value="ygxxlist")
	@ResponseBody
	public Map<String, Object> getPxjhData2(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("aqpx_pxtj_qyname"));
		map.put("ygname", request.getParameter("ygname"));
		map.putAll(getAuthorityMap());
		return aqpxRcpxtjService.dataPxjhGrid(map);
		
	}
	
	/**
	 * 列表显示页面(员工统计)
	 * @param model
	 */
	@RequestMapping(value="ygindex")
	public String ygindex(Model model) {
		return "aqpx/rcpxtj/index_yg";
	}

	/**
	 * 考试记录list页面(企业普通用户)
	 * @param request
	 */
	@RequestMapping(value="kslistyg")
	@ResponseBody
	public Map<String, Object> getksData(HttpServletRequest request) {
		
		int pageNo=1;	//当前页码
		int pageSize=20;	//每页行数
		if(StringUtils.isNotEmpty(request.getParameter("page")))
			pageNo=Integer.valueOf(request.getParameter("page"));
		if(StringUtils.isNotEmpty(request.getParameter("rows")))
			pageSize=Integer.valueOf(request.getParameter("rows"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);
		map.put("ygid", request.getParameter("aqpx_pxtj_cx_id"));
		map.put("sort", request.getParameter("sort"));
		map.put("order", request.getParameter("order"));
		map.put("kcname", request.getParameter("aqpx_pxjl_cx_m1"));
		map.put("ksjg", request.getParameter("aqpx_pxjl_cx_m3"));
		return aqpxKscjService.dataGrid(map);
		
	}
	
	/**
	 * 学习记录list页面(企业普通用户)
	 * @param request
	 */
	@RequestMapping(value="xxlistyg")
	@ResponseBody
	public Map<String, Object> getxxData(HttpServletRequest request) {
		
		int pageNo=1;	//当前页码
		int pageSize=20;	//每页行数
		if(StringUtils.isNotEmpty(request.getParameter("page")))
			pageNo=Integer.valueOf(request.getParameter("page"));
		if(StringUtils.isNotEmpty(request.getParameter("rows")))
			pageSize=Integer.valueOf(request.getParameter("rows"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);
		map.put("ygid", request.getParameter("aqpx_pxtj_cx_id"));
		map.put("sort", request.getParameter("sort"));
		map.put("order", request.getParameter("order"));
		map.put("kcname", request.getParameter("aqpx_pxjl_cx_m1"));

		return aqpxXxjlService.dataGrid(map);
		
	}
}
