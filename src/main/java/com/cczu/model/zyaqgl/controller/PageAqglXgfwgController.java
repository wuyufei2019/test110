package com.cczu.model.zyaqgl.controller;

import java.sql.Timestamp;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.zyaqgl.entity.AQGL_RelevantViolation;
import com.cczu.model.zyaqgl.service.AqglKpbzService;
import com.cczu.model.zyaqgl.service.AqglXgdwService;
import com.cczu.model.zyaqgl.service.AqglXgfwgService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 安全生产-相关方违规Controller
 * @author YZH
 */
@Controller
@RequestMapping("zyaqgl/xgfwg")
public class PageAqglXgfwgController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private AqglXgfwgService aqglXgfwgService;
	@Autowired
	private AqglXgdwService aqglXgdwService;	 
	@Autowired
	private AqglKpbzService aqglKpbzService;
	
	
	/**
	 * 列表显示页面（累计扣分统计）
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model,HttpServletRequest request) {
		model.addAttribute("qyid", request.getParameter("qyid"));
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
		model.addAttribute("usertype", usertype);
		return "zyaqgl/xggl/xgfwg/index";
	}
	
	/**
	 * 数据list（累计扣分统计）
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xgfname", request.getParameter("zyaqgl_xgfwg_xgfname"));
		map.put("year", request.getParameter("zyaqgl_xgfwg_year"));
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			map.put("qyid",(UserUtil.getCurrentShiroUser().getQyid()));
		}

		//安监条件
		String qyid = request.getParameter("qyid");
		if(StringUtils.isNotEmpty(qyid))
			map.put("qyid", qyid);
		return aqglXgfwgService.dataGrid(map);	
		
	}

	/**
	 * 列表显示页面（累计扣分统计）
	 * @param model
	 */
	@RequestMapping(value="index2")
	public String index2(Model model, HttpServletRequest request) {
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
		model.addAttribute("usertype", usertype);
		model.addAttribute("dwid", request.getParameter("dwid"));
		model.addAttribute("pddw", request.getParameter("pddw"));
		return "zyaqgl/xggl/xgfwg/hisindex";
	}
	
	/**
	 * 数据list（累计扣分统计）
	 * @param request
	 */
	@RequiresPermissions("zyaqgl:xgfwg:view")
	@RequestMapping(value="list2")
	@ResponseBody
	public Map<String, Object> getData2(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xgfname", request.getParameter("zyaqgl_xgfwg_xgfname"));
		map.put("dwid", request.getParameter("dwid"));
		map.put("year2", request.getParameter("zyaqgl_xgfwg_year"));
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			map.put("qyid",(UserUtil.getCurrentShiroUser().getQyid()));
		}
		return aqglXgfwgService.dataGrid2(map);	
		
	}
	
	/**
	 * 添加违规信息页面跳转
	 * @param model
	 */
	@RequiresPermissions("zyaqgl:xgfwg:add")
	@RequestMapping(value = "createwg/{id}/{pddw}" , method = RequestMethod.GET)
	public String creategl(@PathVariable("id") Long id,@PathVariable("pddw") String pddw,Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("dwid", id);
		model.addAttribute("pddw", pddw);
		model.addAttribute("pdry", UserUtil.getCurrentShiroUser().getName());
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "zyaqgl/xggl/xgfwg/hisform";
	}
	
	/**
	 * 添加相关方违规信息
	 * @param request,model
	 */
	@RequiresPermissions("zyaqgl:xgfwg:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(AQGL_RelevantViolation entity, Model model) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		entity.setPdry(UserUtil.getCurrentShiroUser().getName());
		//企业id
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			entity.setID1(UserUtil.getCurrentShiroUser().getQyid());
		}
		//添加相关方违规
		aqglXgfwgService.addInfo(entity);
		// 返回结果
		return datasuccess;
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("zyaqgl:xgfwg:update")
	@RequestMapping(value = "update/{dwid}/{pddw}", method = RequestMethod.GET)
	public String update(@PathVariable("dwid") Long dwid, @PathVariable("pddw") String pddw, Model model) {
		//查询选择的相关方违规信息
		Map<String,Object> xgfwg = aqglXgfwgService.findById(dwid);
		model.addAttribute("xgfwg", xgfwg);
		//查询相关方单位信息
		model.addAttribute("pddw", pddw);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "zyaqgl/xggl/xgfwg/hisform";
	}
	
	/**
	 * 修改相关方违规信息
	 * @param request,model
	 */
	@RequiresPermissions("zyaqgl:xgfwg:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQGL_RelevantViolation entity,  Model model){
		String datasuccess="success";	
		entity.setS1(new java.sql.Timestamp(new java.util.Date().getTime()));
		entity.setS2(new java.sql.Timestamp(new java.util.Date().getTime()));
		entity.setS3(0);
		
		aqglXgfwgService.updateInfo(entity);
		//返回结果
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("zyaqgl:xgfwg:view")
	@RequestMapping(value = "view/{dwid}/{pddw}", method = RequestMethod.GET)
	public String view(@PathVariable("dwid") Long dwid, @PathVariable("pddw") String pddw, Model model) {
		//查询选择的相关方违规信息
		Map<String,Object> xgfwg = aqglXgfwgService.findById(dwid);
		model.addAttribute("xgfwg", xgfwg);
		//查询相关方单位信息
		model.addAttribute("pddw", pddw);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "zyaqgl/xggl/xgfwg/hisview";
	}
	
	/**
	 * 删除相关方违规信息
	 */
	@RequiresPermissions("zyaqgl:xgfwg:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			aqglXgfwgService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("zyaqgl:xgfwg:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String,Object> xgdw = aqglXgfwgService.findById(id);
		
		model.addAttribute("xgfwg", xgdw);
		//返回页面
		model.addAttribute("action", "view");
		return "zyaqgl/xggl/xgfwg/view";
	}

	/**
	 * 查询考评标准类别
	 * @param id,model
	 */
	@RequestMapping(value = "findType")
	@ResponseBody
	public String findType() {
		return aqglKpbzService.findType();
	}
	
	/**
	 * 查询考评类别查询考评内容
	 * @param id,model
	 */
	@RequestMapping(value = "findContent")
	@ResponseBody
	public String findType(HttpServletRequest request) {
		String type=request.getParameter("type");
		return aqglKpbzService.findContent(type);
	}
	
	/**
	 * 根据类别和内容查询详细信息
	 * @param id,model
	 */
	@RequestMapping(value = "findBzInfo")
	@ResponseBody
	public String findContent(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("type", request.getParameter("type"));
		map.put("content", request.getParameter("content"));
		return aqglKpbzService.findInfo(map);
	}
}
