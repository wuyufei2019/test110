package com.cczu.model.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.YhpcObserveService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 
 * @Description: 行为观察信息Controller
 * @author: YZH
 * @date: 2017年12月27日
 */
@Controller
@RequestMapping("yhpc/observe")
public class PageYhpcObserveController extends BaseController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private YhpcObserveService yhpcObserveService;

	
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
		model.addAttribute("usertype", usertype);
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					return "yhpc/xwaq/observe/ajindex";
				else
					return "yhpc/xwaq/observe/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "yhpc/xwaq/observe/ajindex";
		}
	}	
	
	/**
	 * list页面 
	 * @param request
	 */
	@RequiresPermissions("yhpc:observe:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		//获取企业id
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("yhpc_observe_qyname"));
		map.put("starttime", request.getParameter("yhpc_observe_starttime"));
		map.put("endtime", request.getParameter("yhpc_observe_endtime"));
		map.putAll(getAuthorityMap());
		return yhpcObserveService.dataGrid(map);		
	}

	/**
	 * 观察详细记录list页面 
	 * @param request
	 */
	@RequestMapping(value="xxjl/{pid}")
	@ResponseBody
	public Map<String, Object> observelist(@PathVariable("pid") Long pid, HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("id", pid);
		return yhpcObserveService.findObserveList(map);		
	}
	
	/**
	 * list页面(安监局使用)
	 * @param request
	 */
	@RequestMapping(value="tablist/{qyid}")
	@ResponseBody
	public Map<String, Object> getDataTab(@PathVariable("qyid") Integer qyid, HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyid", qyid);
		map.put("qyname", request.getParameter("yhpc_observe_qyname"));
		return yhpcObserveService.dataGrid(map);		
	}
	
	/**
	 * 删除信息
	 */
	@RequiresPermissions("yhpc:observe:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			yhpcObserveService.deleteInfo(Long.parseLong(arrids[i]));
		}

		return datasuccess;
	}

	/**
	 * 详细记录页面跳转
	 * 
	 * @param id,model
	 */
	@RequestMapping(value = "xxjl")
	public String xxjl() {
		return "/yhpc/xwaq/observe/index2";
	}
	
	/**
	 * 删除详细记录信息
	 */
	@RequiresPermissions("yhpc:observe:delete")
	@RequestMapping(value = "deletejl/{ids}")
	@ResponseBody
	public String deletejl(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			yhpcObserveService.deleteJl(Long.parseLong(arrids[i]));
		}

		return datasuccess;
	}
//	/**
//	 * 查看页面跳转
//	 * @param id,model
//	 */
//	@RequiresPermissions("yhpc:observe:view")
//	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
//	public String view(@PathVariable("id") Long id, Model model) {
//		YHPC_UnsafeActEntity bis = yhpcObserveService.findById(id);
//		
//		model.addAttribute("sxkj", bis);
//		model.addAttribute("qyid", bis.getID1());
//		//返回页面
//		model.addAttribute("action", "view");
//		return "yhpc/xwaq/observe/view";
//	}

}
