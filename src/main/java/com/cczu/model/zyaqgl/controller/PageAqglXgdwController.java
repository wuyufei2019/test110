package com.cczu.model.zyaqgl.controller;

import com.cczu.model.zyaqgl.entity.AQGL_RelatedUnitsEntity;
import com.cczu.model.zyaqgl.service.AqglXgdwService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.utils.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 安全生产-相关单位Controller
 * @author YZH
 */
@Controller
@RequestMapping("zyaqgl/xgdw")
public class PageAqglXgdwController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private AqglXgdwService aqglxgdwService;
	 
	
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model,HttpServletRequest request) {
		model.addAttribute("qyid", request.getParameter("qyid"));
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
		model.addAttribute("usertype", usertype);
		return "zyaqgl/xggl/xgdw/index";
	}
	
	/**
	 * 数据list 
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("m1", request.getParameter("aqzf_xgdw_m1"));
		map.put("m3", request.getParameter("aqzf_xgdw_m3"));
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			map.put("qyid",(UserUtil.getCurrentShiroUser().getQyid()));
		}

		//安监条件
		String qyid = request.getParameter("qyid");
		if(StringUtils.isNotEmpty(qyid))
			map.put("qyid", qyid);
		return aqglxgdwService.dataGrid(map);	
		
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("zyaqgl:xgdw:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "zyaqgl/xggl/xgdw/form";
	}
	
	/**
	 * 添加关联信息页面跳转
	 * @param model
	 */
	@RequiresPermissions("zyaqgl:xgdw:add")
	@RequestMapping(value = "creategl/{id}" , method = RequestMethod.GET)
	public String creategl(@PathVariable("id") Long id,Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("dwid", id);
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "zyaqgl/xggl/xgdw/glindex";
	}
	
	/**
	 * 添加相关单位信息
	 * @param entity,model
	 */
	@RequiresPermissions("zyaqgl:xgdw:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(AQGL_RelatedUnitsEntity entity, Model model) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		entity.setType("0");
		//添加人
		entity.setM12(Long.parseLong(UserUtil.getCurrentShiroUser().getId().toString()));
		//企业id
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			entity.setID1(UserUtil.getCurrentShiroUser().getQyid());
		}
		//添加相关单位
		aqglxgdwService.addInfo(entity);
		// 返回结果
		return datasuccess;
	}
	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("zyaqgl:xgdw:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的相关单位信息
		Map<String,Object> xgdw = aqglxgdwService.findById(id);
		model.addAttribute("xgdw", xgdw);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "zyaqgl/xggl/xgdw/form";
	}
	
	/**
	 * 修改相关单位信息
	 * @param entity,model
	 */
	@RequiresPermissions("zyaqgl:xgdw:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQGL_RelatedUnitsEntity entity,  Model model){
		String datasuccess="success";	
		entity.setS1(new java.sql.Timestamp(new java.util.Date().getTime()));
		entity.setS2(new java.sql.Timestamp(new java.util.Date().getTime()));
		entity.setS3(0);
		
		aqglxgdwService.updateInfo(entity);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除相关单位信息
	 */
	@RequiresPermissions("zyaqgl:xgdw:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			aqglxgdwService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("zyaqgl:xgdw:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String,Object> xgdw = aqglxgdwService.findById(id);

		model.addAttribute("xgdw", xgdw);
		//返回页面
		model.addAttribute("action", "view");
		return "zyaqgl/xggl/xgdw/view";
	}

	/**
	 * 黑名单管理
	 * @param model
	 */
	@RequiresPermissions("zyaqgl:xgdw:blacklist")
	@RequestMapping(value = "blacklist/{id}/{type}")
	@ResponseBody
	public String blacklist(@PathVariable("id") Long id, @PathVariable("type") String type, Model model) {
		String datasuccess = "success";
		AQGL_RelatedUnitsEntity entity = aqglxgdwService.find(id);
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS2(t);
		entity.setType(type);

		aqglxgdwService.updateInfo(entity);

		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  黑名单管理 - 操作");
		// 返回结果
		return datasuccess;
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
	@RequiresPermissions("zyaqgl:xgdw:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			resultmap = aqglxgdwService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}

	/**
	 * 获取承包商的json数据
	 *
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getcbsjson")
	@ResponseBody
	public String getJson(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			map.put("qyid",(UserUtil.getCurrentShiroUser().getQyid()));
		}
		map.put("type", "承包商");
		List<Map<String, Object>> resultList = aqglxgdwService.getJson(map);
		return JsonMapper.toJsonString(resultList);
	}

}
