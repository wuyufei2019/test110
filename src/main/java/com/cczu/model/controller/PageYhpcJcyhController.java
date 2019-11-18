package com.cczu.model.controller;

import java.text.ParseException;
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

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.YHPC_DailyHiddenInfoEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.YhpcJcrwService;
import com.cczu.model.service.YhpcJcyhService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 隐患排查_日常检查记录controller
 */
@Controller
@RequestMapping("yhpc/jcyh")
public class PageYhpcJcyhController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private UserService userService;
	@Autowired
	private YhpcJcyhService yhpcJcyhService;
	@Autowired
	private YhpcJcrwService yhpcJcrwService;
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
					model.addAttribute("type", 2);
				else
					model.addAttribute("type", 1);
			}
		}else {//非企业用户页面
			model.addAttribute("type", 2);
		}	
		ShiroUser user = UserUtil.getCurrentShiroUser();
		List<Role> roleList=roleService.findRoleById(user.getId());

		model.addAttribute("userid", UserUtil.getCurrentUser().getId());
		return "yhpc/rcaqjc/jcyh/index";
	}

	/**
	 * 自查隐患list页面
	 * @param request
	 */
	@RequiresPermissions("yhpc:jcyh:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Map<String, Object> map = getPageMap(request);
		map.putAll(getAuthorityMap());
		map.put("userid", sessionuser.getId());
		map.put("qyname", request.getParameter("view_qyname"));
		map.put("starttime", request.getParameter("yhpc_jcyh_starttime"));//开始时间
		map.put("endtime", request.getParameter("yhpc_jcyh_finishtime"));//结束时间
		map.put("yhlb", request.getParameter("yhpc_jcyh_yhlb"));//隐患类别
		map.put("yhdj", request.getParameter("yhpc_jcyh_yhdj"));//隐患等级
		map.put("clzt", request.getParameter("yhpc_jcyh_clzt"));//处理状态
		map.putAll(getAuthorityMap());
		return yhpcJcyhService.dataGrid(map);
	}
	
	/**
	 * 我要处理list页面
	 * @param request
	 */
	@RequiresPermissions("yhpc:jcyh:view")
	@RequestMapping(value="mylist")
	@ResponseBody
	public Map<String, Object> myData(HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Map<String, Object> map = getPageMap(request);
		map.putAll(getAuthorityMap());
		map.put("userid", sessionuser.getId());
		map.put("qyname", request.getParameter("view_qyname"));
		map.put("starttime", request.getParameter("yhpc_jcyh_starttime"));//开始时间
		map.put("endtime", request.getParameter("yhpc_jcyh_finishtime"));//结束时间
		map.put("yhlb", request.getParameter("yhpc_jcyh_yhlb"));//隐患类别
		map.put("yhdj", request.getParameter("yhpc_jcyh_yhdj"));//隐患等级
		map.put("clzt", request.getParameter("yhpc_jcyh_clzt"));//处理状态
		map.putAll(getAuthorityMap());
		return yhpcJcyhService.myDataGrid(map);
	}
	
	/**
	 * 历史整改记录list页面
	 * @param request
	 */
	@RequiresPermissions("yhpc:jcyh:view")
	@RequestMapping(value="zglist")
	@ResponseBody
	public Map<String, Object> getData2(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.putAll(getAuthorityMap());
		map.put("yhid", request.getParameter("yhid"));//处理状态
		map.putAll(getAuthorityMap());
		return yhpcJcyhService.zgdataGrid(map);
	}
	
	/**
	 * 整改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("yhpc:jcyh:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的成员信息
		Map<String, Object> jcyh= yhpcJcyhService.findById(id);
		model.addAttribute("jcyh", jcyh);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("type", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "yhpc/rcaqjc/jcyh/form";
	}
	
	/**
	 * 添加隐患整改信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(YHPC_DailyHiddenInfoEntity jcyh, Model model){
		String datasuccess="success";	 
		yhpcJcyhService.updateInfo(jcyh);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 检查人复查页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("yhpc:jcyh:update")
	@RequestMapping(value = "review/{id}", method = RequestMethod.GET)
	public String review(@PathVariable("id") Long id, Model model) {
		//查询选择的成员信息
		Map<String, Object> jcyh= yhpcJcyhService.findById(id);
		model.addAttribute("jcyh", jcyh);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("type", "review");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "yhpc/rcaqjc/jcyh/form";
	}
	
	/**
	 * 修改检查隐患信息
	 * @param request,model
	 */
	@RequestMapping(value = "review")
	@ResponseBody
	public String review(YHPC_DailyHiddenInfoEntity jcyh, Model model){
		String datasuccess="success";	 
		yhpcJcyhService.review(jcyh);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除检查隐患信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequiresPermissions("yhpc:jcyh:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			yhpcJcyhService.deleteInfo(Long.parseLong(aids[i]));
		}
		
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		//查询选择的成员信息
		Map<String, Object> jcyh= yhpcJcyhService.findById(id);
		model.addAttribute("jcyh", jcyh);
		//返回页面
		return "yhpc/rcaqjc/jcyh/view";
	}

	//导入页面跳转
	@RequestMapping(value = "exinjump", method = RequestMethod.GET)
	public String exin(Model model) {
		model.addAttribute("action", "exin");
		return "common/importForm";
	}
	
	/**
	 * 导入
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequiresPermissions("yhpc:jcyh:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			resultmap = yhpcJcyhService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}
}
