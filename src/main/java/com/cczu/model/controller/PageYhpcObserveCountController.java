package com.cczu.model.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.YhpcObserveCountService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 
 * @Description: 观察统计分析Controller
 * @author: YZH
 * @date: 2017年12月27日
 */
@Controller
@RequestMapping("yhpc/gctj")
public class PageYhpcObserveCountController extends BaseController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private YhpcObserveCountService yhpcObserveCountService;
	
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
				if(be.getIsbloc()!=null&&be.getIsbloc()==1){//判断是否为集团公司
					model.addAttribute("usertype", 5);
					return "yhpc/xwaq/observecount/index";
				}
				else{
					model.addAttribute("usertype", sessionuser.getUsertype());
					return "yhpc/xwaq/observecount/index";
				}
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			model.addAttribute("usertype", sessionuser.getUsertype());
			return "yhpc/xwaq/observecount/index";
		}
	}	
	
	/**
	 * list页面 
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		//获取企业id
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("yhpc_observe_qyname"));//企业名称
		map.put("depart", request.getParameter("depart"));//部门
		map.put("starttime", request.getParameter("starttime"));//开始时间
		map.put("endtime", request.getParameter("endtime"));//结束时间
		map.putAll(getAuthorityMap());
		return yhpcObserveCountService.dataGrid(map);		
	}

	/**
	 * 观察记录统计
	 */
	@RequestMapping(value="lxcount")
	@ResponseBody
	public String getCount(HttpServletRequest request) {
		Map<String, Object> map = getAuthorityMap();
		map.put("qyname", request.getParameter("qyname"));//企业名称
		map.put("depart", request.getParameter("depart"));//部门
		map.put("starttime", request.getParameter("starttime"));//开始时间
		map.put("endtime", request.getParameter("endtime"));//结束时间
		return JsonMapper.getInstance().toJson(yhpcObserveCountService.jlcount(map));
	}
}
