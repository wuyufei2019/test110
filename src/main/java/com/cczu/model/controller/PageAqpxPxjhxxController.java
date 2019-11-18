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

import com.cczu.model.entity.AQPX_CourseEntity;
import com.cczu.model.entity.AQPX_PlanEntity;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IAqpxJhxxService;
import com.cczu.model.service.IAqpxKCxxService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.Department;
import com.cczu.sys.system.entity.Message;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.service.DepartmentService;
import com.cczu.sys.system.service.MessageService;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 安全培训管理——培训计划Controller
 * @author jason
 */
@Controller
@RequestMapping("aqpx/aqpxjh")
public class PageAqpxPxjhxxController extends BaseController {
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private IAqpxJhxxService aqpxpjhxxService;
	@Autowired
	private IAqpxKCxxService aqpxKCxxService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private UserService userService;
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "aqpx/pxjhxx/index";
	}
	

	/**
	 * list页面
	 * @param request
	 */
	@RequiresPermissions("aqpx:aqpxjh:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		
		int pageNo=1;	//当前页码
		int pageSize=20;	//每页行数
		if(StringUtils.isNotEmpty(request.getParameter("page")))
			pageNo=Integer.valueOf(request.getParameter("page"));
		if(StringUtils.isNotEmpty(request.getParameter("rows")))
			pageSize=Integer.valueOf(request.getParameter("rows"));
		
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);
		map.put("qyid", sessionuser.getQyid());
		map.put("sort", request.getParameter("sort"));
		map.put("order", request.getParameter("order"));
		map.put("m1", request.getParameter("aqpx_pxjhxx_cx_m1"));

		return aqpxpjhxxService.dataGrid(map);
		
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("aqpx:aqpxjh:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
			return "aqpx/pxjhxx/form";
		
	}
	
	/**
	 * 添加培训计划
	 * @param request,model
	 * @throws ParseException 
	 */
	@RequiresPermissions("aqpx:aqpxjh:add")
	@RequestMapping(value = "create" )
	@ResponseBody
	public String create(AQPX_PlanEntity ap, Model model) {
		String datasuccess="success";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		 
		ap.setID1(UserUtil.getCurrentShiroUser().getQyid());
		ap.setID4(UserUtil.getCurrentUser().getId());
		aqpxpjhxxService.addInfo(ap);
		
		//计划添加成功后给相关用户推送消息
		
		//根据部门id获取部门人员id
		List<User> userlist=aqpxpjhxxService.findUseridByDep(ap.getID3());
		Message msg=new Message();
		msg.setFROMUSER(sessionuser.getId().toString());//发送人
		msg.setTITLE("培训通知");
		msg.setCREATETIME(DateUtils.getSysTimestamp());
		msg.setMSGTYPE(Message.MessageType.PXTZ.getMsgType());
		msg.setSENDSTATUE("0");
		for(User touser:userlist){
			msg.setTOUSER(touser.getId().toString());//接收人
			msg.setCONTENT(touser.getName()+"您好，您有'"+ap.getM1()+"'需要学习");
			msg.setURL("aqpx/zxxx/index");
			messageService.save(msg);
		}
		
		return datasuccess;
	}	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("aqpx:aqpxjh:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		//查询选择的培训计划
		long id1 = id;
		AQPX_PlanEntity ap = aqpxpjhxxService.findByid(id1);
		model.addAttribute("aqpxjh",ap);
		
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "aqpx/pxjhxx/form";
	}
	
	/**
	 * 修改培训计划
	 * @param request,model
	 * @throws ParseException 
	 */
	@RequiresPermissions("aqpx:aqpxjh:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQPX_PlanEntity ap, Model model) throws ParseException{
		String datasuccess="success";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		
		ap.setID4((long)sessionuser.getId());
		aqpxpjhxxService.updateInfo(ap);
		
		//根据部门id获取部门人员id
		List<User> userlist=aqpxpjhxxService.findUseridByDep(ap.getID3());
		Message msg=new Message();
		msg.setFROMUSER(sessionuser.getId().toString());//发送人
		msg.setTITLE("培训通知");
		msg.setCREATETIME(DateUtils.getSysTimestamp());
		msg.setMSGTYPE(Message.MessageType.PXTZ.getMsgType());
		msg.setSENDSTATUE("0");
		for(User touser:userlist){
			msg.setTOUSER(touser.getId().toString());//接收人
			msg.setCONTENT(touser.getName()+"您好，您有'"+ap.getM1()+"'需要学习");
			msg.setURL("aqpx/zxxx/index");
			messageService.save(msg);
		}
		
		return datasuccess;
	}
	
	/**
	 * 删除培训计划
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequiresPermissions("aqpx:aqpxjh:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			aqpxpjhxxService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("aqpx:aqpxjh:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		//查询选择的培训计划
		long id1 = id;
		AQPX_PlanEntity ap = aqpxpjhxxService.findByid(id1);
		model.addAttribute("aqpxjh",ap);
		//课程id集合转换成课程名称集合
		List<AQPX_CourseEntity> list=aqpxKCxxService.getlistByKcids(ap.getID2());
		String kclist="";
		for(AQPX_CourseEntity c:list){
			kclist+=c.getM1()+"<br/>";
		}
		model.addAttribute("kclist",kclist);
		//部门id集合转换成部门名称集合
		List<Department> list2=departmentService.findListByBmids(ap.getID3());
		String bmlist="";
		for(Department d:list2){
			bmlist+=d.getM1()+"<br/>";
		}
		model.addAttribute("bmlist",bmlist);
		
		//返回页面
		model.addAttribute("action", "view");
		return "aqpx/pxjhxx/view";
	}
	
	/**
	 * 考试记录显示页面
	 * @param model
	 */
	@RequestMapping(value="ztindex/{jhid}/{type}")
	public String ztindex(@PathVariable("jhid") Integer id, 
			              @PathVariable("type") Integer type, Model model) {
		
		if(type==2){

			model.addAttribute("jhid",id);
			model.addAttribute("type",type);
			return "aqpx/pxjhxx/index_yg2";
		}else{
			model.addAttribute("jhid",id);
			model.addAttribute("type",type);
			return "aqpx/pxjhxx/index_yg";
		}
	
		
		
		
		
		
		
	}
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("aqpx:aqpxjh:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be=bisQyjbxxService.findInfoById(sessionuser.getQyid());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyid", be.getID());
		aqpxpjhxxService.exportExcel(response, map);
		
	}
	

}
