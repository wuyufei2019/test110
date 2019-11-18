package com.cczu.model.controller;

import java.sql.Timestamp;
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

import com.cczu.model.entity.BIS_DangerOperationEntity;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.IBisWxzyService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 危险作业controller
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("bis/wxzy")
public class PageBisWxzyController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private IBisWxzyService biswxzyService;

	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		if(be!=null&& be.getM1() != null){
			return "model/bis/wxzy/index";
		}else{
			// 获取用户角色
			List<Role> list = roleService.findRoleById(sessionuser.getId());
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					//判断用户角色，如果包含有企业用户角色，则转到企业用户显示的界面
					if (list.get(i).getRoleCode().equals("company")||list.get(i).getRoleCode().equals("companyadmin"))
						return "../error/001";
				}
			}
			return "model/bis/wxzy/index";
		}
	}
	
	
	/**
	 * list页面
	 * @param request
	 */
	@RequiresPermissions("bis:wxzy:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		
		Map<String, Object> map = getPageMap(request);
		if(be!=null){
			map.put("qyid", be.getID());
		}
		map.put("m1", request.getParameter("bis_wxzy_cx_m1"));
		
		return biswxzyService.dataGrid(map);
	}
	
	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="tablist/{qyid}")
	@ResponseBody
	public Map<String, Object> getDataTab(@PathVariable("qyid") Integer qyid, HttpServletRequest request) {
		
		Map<String, Object> map = getPageMap(request);
		map.put("qyid", qyid);
		
		return biswxzyService.dataGrid(map);
	}

	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("bis:wxzy:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "model/bis/wxzy/form";
		
	}
	
	/**
	 * 添加危险作业
	 * @param request,model
	 * @throws ParseException 
	 */
	@RequiresPermissions("bis:wxzy:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(BIS_DangerOperationEntity bd, Model model) throws ParseException {
		String datasuccess="保存成功！";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--危险作业信息  【增加操作】");

		if(be!=null){
			long ID = be.getID();//获取到企业id
			Timestamp t=DateUtils.getSysTimestamp();
			bd.setS1(t);
			bd.setS2(t);
			bd.setS3(0);
			bd.setID1(ID);
			biswxzyService.addInfo(bd);
			//返回结果
			return datasuccess;
		}else{
			return "请先完善企业基本信息";
		}
		
	}
	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("bis:wxzy:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		//查询选择的危险作业
		long id1 = id;
		BIS_DangerOperationEntity bd = biswxzyService.findById(id1);
		
		model.addAttribute("qylist", bd);
		//返回页面
		model.addAttribute("action", "update");
		return "model/bis/wxzy/form";
	}
	
	/**
	 * 修改危险作业
	 * @param request,model
	 * @throws ParseException 
	 */
	@RequiresPermissions("bis:wxzy:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(BIS_DangerOperationEntity bd, Model model) throws ParseException{
		String datasuccess="更新成功";
		
		Timestamp t=DateUtils.getSysTimestamp();
		bd.setS2(t);
		biswxzyService.updateInfo(bd);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--危险作业信息  【修改操作】");

		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除危险作业
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequiresPermissions("bis:wxzy:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			biswxzyService.deleteInfo(Long.parseLong(aids[i]));
		}
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--危险作业信息  【删除操作】");

		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("bis:qyjbxx:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		//查询选择的产品信息
		long id1 = id;
		BIS_DangerOperationEntity bd = biswxzyService.findById(id1);
		model.addAttribute("qylist", bd);
		//返回页面
		model.addAttribute("action", "view");
		return "model/bis/wxzy/view";
	}
	
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("bis:wxzy:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyid", be.getID());
		biswxzyService.exportExcel(response, map);
		
	}
}
