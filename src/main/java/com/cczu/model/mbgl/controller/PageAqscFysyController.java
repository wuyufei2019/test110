package com.cczu.model.mbgl.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.mbgl.entity.AQSC_ExpenseUse;
import com.cczu.model.mbgl.service.AqscFysyService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 安全生产-费用使用Controller
 * @author YZH
 */
@Controller
@RequestMapping("aqsctr/fysy")
public class PageAqscFysyController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private AqscFysyService aqscfysyService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService; 
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model,HttpServletRequest request) {
		String qyid = request.getParameter("qyid");
		if(StringUtils.isEmpty(qyid)){
			ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
			if(sessionuser.getUsertype().equals("1")){//企业用户
				BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
				if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
					if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
						return "targetmanger/aqsctr/fysy/ajindex";
					else
						return "targetmanger/aqsctr/fysy/index";
				}else//未添加企业基本信息错误提示页面
					return "../error/001";
			}else {//非企业用户页面
				return "targetmanger/aqsctr/fysy/ajindex";
			}
		}else{
			model.addAttribute("qyid", qyid);
			return "targetmanger/aqsctr/fysy/index";
		}
	}
	
	/**
	 * 查询list 
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("aqsc_fysy_qyname"));
		map.put("m1", request.getParameter("aqsc_fysy_m1"));
		map.putAll(getAuthorityMap());
		String qyid = request.getParameter("qyid");
		if(!StringUtils.isEmpty(qyid)){
			map.put("qyid", qyid);
		}
		return aqscfysyService.dataGrid(map);	
		
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("aqsc:fysy:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "targetmanger/aqsctr/fysy/form";
	}
	
	/**
	 * 添加费用使用信息
	 * @param request,model
	 */
	@RequiresPermissions("aqsc:fysy:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(AQSC_ExpenseUse entity, Model model) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			entity.setID1(UserUtil.getCurrentShiroUser().getQyid());
		}
		//添加费用使用
		aqscfysyService.addInfo(entity);
		// 返回结果
		return datasuccess;
	}
	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("aqsc:fysy:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的费用使用信息
		AQSC_ExpenseUse fysy = aqscfysyService.findById(id);
		model.addAttribute("fysy", fysy);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "targetmanger/aqsctr/fysy/form";
	}
	
	/**
	 * 修改费用使用信息
	 * @param request,model
	 */
	@RequiresPermissions("aqsc:fysy:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQSC_ExpenseUse entity,  Model model){
		String datasuccess="success";	
		entity.setS2(new java.sql.Timestamp(new java.util.Date().getTime()));
		aqscfysyService.updateInfo(entity);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除费用使用信息
	 */
	@RequiresPermissions("aqsc:fysy:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			aqscfysyService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String, Object> fysy = aqscfysyService.findInforById(id);
		
		model.addAttribute("fysy", fysy);
		//返回页面
		model.addAttribute("action", "view");
		return "targetmanger/aqsctr/fysy/view";
	}
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("aqsc:fysy:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.put("m1", request.getParameter("aqsc_fysy_m1"));
		map.put("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		map.putAll(getAuthorityMap());
		aqscfysyService.exportExcel(response, map);
	}
	
	/**
	 * 显示所有列
	 */
	@RequiresPermissions("aqsc:fysy:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","aqsctr/fysy/export");
		return "/common/formexcel";
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
	@RequiresPermissions("aqsc:fysy:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			resultmap = aqscfysyService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}	
}
