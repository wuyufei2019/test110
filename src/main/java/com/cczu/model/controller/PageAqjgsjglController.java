package com.cczu.model.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
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

import com.cczu.model.entity.AQJG_AccidentInforEntity;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IAqjgsjglService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 安全监管-事件管理controller
 * @author jason
 *
 */
@Controller
@RequestMapping("aqjg/sjgl")
public class PageAqjgsjglController extends BaseController {
	
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private IAqjgsjglService aqjgsjglservice ;
/**
 * 跳转列表页面
 * @param model
 */
	@RequestMapping(value="index")
	String index(Model model){
		ShiroUser sessionuser=UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity bis= bisQyjbxxService.findInfoById(sessionuser.getQyid());
		model.addAttribute("UserType", sessionuser.getUsertype());
		if(bis!=null){
			return "aqjg/sjgl/index";
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
			return "aqjg/sjgl/index";
		}	
	}
	
	/**
	 * 列表显示页面
	 * @param request 
	 */
	@RequiresPermissions("aqjg:sjgl:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String ,Object> getData(HttpServletRequest request){
		Map<String,Object> map=this.getPageMap(request);
		map.put("sgtype",request.getParameter("aqjg_sjgl_sgtype"));
		map.put("sglevel", request.getParameter("aqjg_sjgl_sglevel"));
		map.put("dwname", request.getParameter("aqjg_sjgl_dwname"));
		
		return aqjgsjglservice.dataGrid(map);
		
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("aqjg:sjgl:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		//用户类型(1企业，0安监局，9系统管理员)		
//		if(sessionuser.getUsertype().equals("1")){
//			model.addAttribute("id1",sessionuser.getQyid());
//		}
		model.addAttribute("action", "create");
		model.addAttribute("usertype",sessionuser.getUsertype());
		return "aqjg/sjgl/form";
	}
	
	/**
	 * 添加信息
	 * @param model
	 */
	@RequiresPermissions("aqjg:sjgl:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(AQJG_AccidentInforEntity aie,HttpServletRequest request) {
		String datasuccess="success";
		Timestamp t = DateUtils.getSysTimestamp();
		aie.setS1(t);
		aie.setS2(t);
		aie.setS3(0);
		try {
			aqjgsjglservice.saveInfo(aie);
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="failed";
			e.printStackTrace();
		}
		return datasuccess;
	}
	
	/**
	 * 删除文件信息
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
			aqjgsjglservice.deleteInfo(Long.parseLong(aids[i]));
		}
		
		return datasuccess;
	}
	

	/**
	 * 查看行事件信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("aqjg:sjgl:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		// 查询选择的行政备案信息
		AQJG_AccidentInforEntity aie = aqjgsjglservice.findInfoById(id);
		model.addAttribute("aie", aie);
		// 返回页面
		model.addAttribute("action", "view");
		return "aqjg/sjgl/view";
	}
	/**
	 * 更新跳转
	 */
	@RequiresPermissions("aqjg:sjgl:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		//查询选择的车间信息
		AQJG_AccidentInforEntity aie = aqjgsjglservice.findInfoById2(id);
		model.addAttribute("aie", aie);
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		//返回页面
		model.addAttribute("action", "update");
		return "aqjg/sjgl/form";
	}
	/**
	 * 修改是啊金信息
	 * @param request,model
	 */
	@RequiresPermissions("aqjg:sjgl:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQJG_AccidentInforEntity aie , Model model){
		String datasuccess="success";
		Timestamp t=DateUtils.getSysTimestamp();
		aie.setS2(t);
		aqjgsjglservice.updateInfo(aie);
		//返回结果
		return datasuccess;
	}
	
	
	
}
