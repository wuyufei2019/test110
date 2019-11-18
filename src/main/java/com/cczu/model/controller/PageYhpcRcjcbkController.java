package com.cczu.model.controller;

import java.sql.Timestamp;
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
import com.cczu.model.entity.YHPC_DaliyCheckEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.YhpcRcjcbkService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 
 * @Description: 日常检查表库Controller
 * @author: YZH
 * @date: 2017年12月27日
 */
@Controller
@RequestMapping("yhpc/rcjcbk")
public class PageYhpcRcjcbkController extends BaseController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private YhpcRcjcbkService yhpcRcjcbkService;

	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
		model.addAttribute("usertype", usertype);
		return "yhpc/rcaqjc/rcjcbk/index";
	}	
	
	/**
	 * list页面 
	 * @param request
	 */
	@RequiresPermissions("yhpc:rcjcbk:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		//获取企业id
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		
		Map<String, Object> map = getPageMap(request);
		if(be!=null){
			map.put("qyid", be.getID());
		}
		map.put("m1", request.getParameter("yhpc_rcjcbk_cx_m1"));
		return yhpcRcjcbkService.dataGrid(map);		
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("yhpc:rcjcbk:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "yhpc/rcaqjc/rcjcbk/form";
	}

	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequiresPermissions("yhpc:rcjcbk:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(YHPC_DaliyCheckEntity entity, Model model) {
		String datasuccess = "success";
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){
			entity.setID1(sessionuser.getQyid());
		}
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		try {
			yhpcRcjcbkService.addInfo(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datasuccess;
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("yhpc:rcjcbk:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		YHPC_DaliyCheckEntity rcjcbk = yhpcRcjcbkService.findById(id);
		model.addAttribute("rcjcbk", rcjcbk);
		model.addAttribute("qyid", rcjcbk.getID1());
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "yhpc/rcaqjc/rcjcbk/form";
	}
	
	/**
	 * 修改信息
	 * @param request,model
	 */
	@RequiresPermissions("yhpc:rcjcbk:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(YHPC_DaliyCheckEntity entity,  Model model){
		String datasuccess="success";	
		entity.setS2(new java.sql.Timestamp(new java.util.Date().getTime()));
		yhpcRcjcbkService.updateInfo(entity);

		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除信息
	 */
	@RequiresPermissions("yhpc:rcjcbk:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			yhpcRcjcbkService.deleteInfo(Long.parseLong(arrids[i]));
		}

		return datasuccess;
	}
	 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("yhpc:rcjcbk:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		YHPC_DaliyCheckEntity rcjcbk = yhpcRcjcbkService.findById(id);
		
		model.addAttribute("rcjcbk",rcjcbk);
		model.addAttribute("qyid", rcjcbk.getID1());
		//返回页面
		model.addAttribute("action", "view");
		return "yhpc/rcaqjc/rcjcbk/view";
	}

	/**
	 * 查询日常检查类型list
	 * @param id,model
	 */
	@RequestMapping(value = "xwlblist")
	@ResponseBody
	public String xwlbJson() {
		//获取企业id
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		Map<String, Object> map = new HashMap<String, Object>();
		if(be!=null){
			map.put("qyid", be.getID());
		}
		List<Map<String, Object>> sblbList= yhpcRcjcbkService.findXwlbList(map);
		return JsonMapper.getInstance().toJson(sblbList);
	}
	
	/**
	 * 查询日常检查单元list
	 * @param id,model
	 */
	@RequestMapping(value = "unitlist")
	@ResponseBody
	public String unitJson(HttpServletRequest request) {
		//获取企业id
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		Map<String, Object> map = new HashMap<String, Object>();
		if(be!=null){
			map.put("qyid", be.getID());
			map.put("m1", request.getParameter("lx"));
		}
		List<Map<String, Object>> unitList= yhpcRcjcbkService.findUnitList(map);
		return JsonMapper.getInstance().toJson(unitList);
	}
	
	/**
	 * 查询日常检查内容list
	 * @param id,model
	 */
	@RequestMapping(value = "contentlist")
	@ResponseBody
	public String contentJson(HttpServletRequest request) {
		//获取企业id
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		Map<String, Object> map = new HashMap<String, Object>();
		if(be!=null){
			map.put("qyid", be.getID());
			map.put("m1", request.getParameter("lx"));
			map.put("m2", request.getParameter("dw"));
		}
		List<Map<String, Object>> contentList= yhpcRcjcbkService.findContentList(map);
		return JsonMapper.getInstance().toJson(contentList.get(0));
	}
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("yhpc:rcjcbk:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			map.put("qyid",(UserUtil.getCurrentShiroUser().getQyid()));
		}
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.put("m1", request.getParameter("yhpc_rcjcbk_cx_m1"));
		map.putAll(getAuthorityMap());
		yhpcRcjcbkService.exportExcel(response, map);
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
	@RequiresPermissions("yhpc:rcjcbk:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			resultmap = yhpcRcjcbkService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}
	
	/**
	 * 显示所有列
	 */
	@RequiresPermissions("yhpc:rcjcbk:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","yhpc/rcaqjc/rcjcbk/export");
		return "common/formexcel";
	}
}
