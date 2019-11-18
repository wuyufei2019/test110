package com.cczu.model.controller;

import java.text.ParseException;
import java.util.HashMap;
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

import com.cczu.model.entity.YHPC_DailyHiddenInfoEntity;
import com.cczu.model.entity.YHPC_InspectionTaskEntity;
import com.cczu.model.entity.dto.Tree_CheckContent;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.YhpcJcrwService;
import com.cczu.model.service.YhpcJcyhService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.StringUtils;

/**
 * 隐患排查_检查任务controller
 */
@Controller
@RequestMapping("yhpc/jcrw")
public class PageYhpcJcrwController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private YhpcJcrwService yhpcJcrwService;
	@Autowired
	private UserService userService;
	@Autowired
	private YhpcJcyhService yhpcJcyhService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "yhpc/rcaqjc/jcrw/index";
	}

	/**
	 * list集合(JSON)
	 */
	@RequiresPermissions("yhpc:jcrw:view")
	@RequestMapping(value="jcnr") 
	@ResponseBody
	public String DateList(Model model,HttpServletRequest request){
		Long qyid =UserUtil.getCurrentShiroUser().getQyid();
		List<Tree_CheckContent> list= yhpcJcrwService.getAllTreeList(request.getParameter("lx"),qyid);
		return JsonMapper.getInstance().toJson(list);
	}

	/**
	 * 根据部门id获取部门人员
	 * 
	 * @param request
	 */
	@RequestMapping(value = "bmrylist")
	@ResponseBody
	public String getUserJson(HttpServletRequest request) {
		Map<String, Object> map =new HashMap<String, Object>();
		map.putAll(getAuthorityMap());
		if(StringUtils.isNoneBlank(request.getParameter("depid")))
			map.put("depid", request.getParameter("depid").toString());
		return userService.getUJsonByDep(map);
	}
	
	/**
	 * list页面
	 * @param request
	 */
	@RequiresPermissions("yhpc:jcrw:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.putAll(getAuthorityMap());
		map.put("jcr", request.getParameter("yhpc_jcrw_jcr"));
		map.put("time", request.getParameter("yhpc_jcrw_time"));
		map.put("jclb", request.getParameter("yhpc_jcrw_jclb"));
		map.put("zt", request.getParameter("yhpc_jcrw_zt"));
		return yhpcJcrwService.dataGrid(map);
	}
	
	/**
	 * 我的任务list页面
	 * @param request
	 */
	@RequiresPermissions("yhpc:jcrw:view")
	@RequestMapping(value="mylist")
	@ResponseBody
	public Map<String, Object> getData2(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.putAll(getAuthorityMap());
		map.put("jcr", request.getParameter("yhpc_jcrw_jcr"));
		map.put("time", request.getParameter("yhpc_jcrw_time"));
		map.put("userid", UserUtil.getCurrentShiroUser().getId());
		map.put("jclb", request.getParameter("yhpc_jcrw_jclb2"));
		map.put("zt", request.getParameter("yhpc_jcrw_zt2"));
		return yhpcJcrwService.dataGrid(map);
	}

	/**
	 * 添加检查任务跳转
	 * @param model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "yhpc/rcaqjc/jcrw/form";	
	}
	
	/**
	 * 添加信息
	 * @param jcrw,model
	 */
	@RequiresPermissions("yhpc:jcrw:add")
	@RequestMapping(value = "create" , method = RequestMethod.POST)
	@ResponseBody
	public String create(YHPC_InspectionTaskEntity jcrw, Model model ) {
		String datasuccess="success";	
		if ("1".equals(UserUtil.getCurrentShiroUser().getUsertype())) {
			jcrw.setID1(UserUtil.getCurrentShiroUser().getQyid());
		}
		jcrw.setID2(UserUtil.getCurrentShiroUser().getId());//最后分配人
		jcrw.setM6("0");
		yhpcJcrwService.addInfo(jcrw);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 修改检查任务页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的成员信息
		Map<String, Object> jcrw= yhpcJcrwService.findById(id);
		model.addAttribute("jcrw", jcrw);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "yhpc/rcaqjc/jcrw/form";
	}
	
	/**
	 * 修改检查任务信息
	 * @param jcrw,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(YHPC_InspectionTaskEntity jcrw, Model model){
		String datasuccess="success";	 
		jcrw.setID2(UserUtil.getCurrentShiroUser().getId());//最后分配人
		yhpcJcrwService.updateInfo(jcrw);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除检查任务信息
	 * 
	 * @param ids
	 * @throws ParseException
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			yhpcJcrwService.deleteInfo(Long.parseLong(aids[i]));
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
		Map<String, Object> jcrw= yhpcJcrwService.findById(id);
		model.addAttribute("jcrw", jcrw);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "yhpc/rcaqjc/jcrw/view";
	}

	/**
	 * 获取检查内容list
	 * @param request
	 */
	@RequestMapping(value = "nrlist/{id}" , method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getDataNr(@PathVariable("id") String nrid, HttpServletRequest request) {
		int pageNo=1;	//当前页码
		int pageSize=50;	//每页行数

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);

		return yhpcJcrwService.dataGridNr(nrid);
		
	}
	
	/**
	 * 添加检查记录跳转
	 * @param id,model
	 */
	@RequestMapping(value = "addJl/{id}" , method = RequestMethod.GET)
	public String createFa(@PathVariable("id") Long id, Model model) {
		Map<String, Object> jcrw=yhpcJcrwService.findById(id);
		model.addAttribute("action", "create");
		model.addAttribute("jcrw", jcrw);//传检查方案id和企业信息过去
		return "yhpc/rcaqjc/jcrw/jlform";	
	}
	
	/**
	 * 添加检查内容页面跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "choosejcnr")
	public String choosejcnr(Model model) {
		model.addAttribute("action", "create");
		return "yhpc/rcaqjc/jcrw/nrForm";
	}
	
	/**
	 * 查看状态页面跳转
	 * @param rwid,model
	 */
	@RequestMapping(value = "viewzt/{rwid}", method = RequestMethod.GET)
	public String viewzt(@PathVariable("rwid") Long rwid, Model model) {
		YHPC_InspectionTaskEntity rw=yhpcJcrwService.find(rwid);
		if(rw.getM6().equals("0")){//判断是否已初次检查
			model.addAttribute("zt", "1");//开始检查
		}else{//若检查，判断到哪一步
			//查询任务是否有对应的隐患
			List<YHPC_DailyHiddenInfoEntity> list=yhpcJcyhService.findByRwid(rwid);
			if(list.size()==0){
				model.addAttribute("zt", "4");//没有隐患，处于检查完成状态
			}else{
				int wzg=0;
				int wc=0;
				int tg=0;
				for (YHPC_DailyHiddenInfoEntity yh : list){
					if(yh.getM11().equals("0"))
						wzg++;
					if(yh.getM11().equals("2"))
						wc++;
					if(yh.getM11().equals("4"))
						tg++;
				}
				if(wzg==list.size())//全部未整改，处于开始检查完成阶段
					model.addAttribute("zt", "1");
				else if(wc==list.size()||(wc+tg==list.size())&&wc!=0)//全部整改完成，待复查
					model.addAttribute("zt", "3");
				else if(tg==list.size())//全部复查通过
					model.addAttribute("zt", "4");
				else 
					model.addAttribute("zt", "2");
			}
		}
		return "yhpc/rcaqjc/jcrw/viewzt";
	}
	
	
	/**
	 * 添加检查人跳转
	 * @param request,model
	 */
	@RequestMapping(value = "addjcr" , method = RequestMethod.GET)
	public String createjcrtz(Model model, HttpServletRequest request) {
		model.addAttribute("depids", request.getParameter("depids"));
		model.addAttribute("jcrids",request.getParameter("jcrids"));
		return "yhpc/rcaqjc/jcrw/jcrform";	
	}
	
	
	/**
	 * 添加检查人
	 * @param id,model
	 */
	@RequestMapping(value = "jcrlist", method = RequestMethod.GET)
	@ResponseBody
	public  Map<String, Object> jcrlist(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("pageSize", 10000);
		map.put("depid", request.getParameter("depids").toString());
		map.put("jcr",request.getParameter("yhpc_jcrw_jcr"));
		map.put("bm", request.getParameter("yhpc_jcrw_bm"));
		map.putAll(getAuthorityMap());
		if(StringUtils.isNoneBlank(request.getParameter("depid")))
			map.put("depid", request.getParameter("depid").toString());
		return userService.dataGrid(map);
	}
	
}
