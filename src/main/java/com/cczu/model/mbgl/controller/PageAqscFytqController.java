package com.cczu.model.mbgl.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.cczu.model.mbgl.entity.AQSC_ExpenseExtraction;
import com.cczu.model.mbgl.service.AqscFyjsService;
import com.cczu.model.mbgl.service.AqscFytqService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 安全生产-费用提取Controller
 * @author YZH
 */
@Controller
@RequestMapping("aqsctr/fytq")
public class PageAqscFytqController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private AqscFytqService aqscFytqService;
	@Autowired
	private AqscFyjsService aqscFyjsService;
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
						return "targetmanger/aqsctr/fytq/ajindex";
					else
						return "targetmanger/aqsctr/fytq/index";
				}else//未添加企业基本信息错误提示页面
					return "../error/001";
			}else {//非企业用户页面
				return "targetmanger/aqsctr/fytq/ajindex";
			}
		}else{
			model.addAttribute("qyid", qyid);
			return "targetmanger/aqsctr/fytq/index";
		}
	}
	
	/**
	 *查询list 
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("aqsc_fytq_qyname"));
		map.put("m1", request.getParameter("aqsc_fytq_cs_m1"));
		map.put("m3", request.getParameter("aqzf_fytq_m3"));
		map.putAll(getAuthorityMap());
		String qyid = request.getParameter("qyid");
		if(!StringUtils.isEmpty(qyid)){
			map.put("qyid", qyid);
		}
		return aqscFytqService.dataGrid(map);	
		
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("aqsc:fytq:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "targetmanger/aqsctr/fytq/form";
	}
	
	/**
	 * 添加费用提取信息
	 * @param request,model
	 */
	@RequiresPermissions("aqsc:fytq:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(AQSC_ExpenseExtraction entity, Model model) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			entity.setQyid(UserUtil.getCurrentShiroUser().getQyid());
		}
		//添加费用提取
		aqscFytqService.addInfo(entity);
		// 返回结果
		return datasuccess;
	}
	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("aqsc:fytq:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的费用提取信息
		AQSC_ExpenseExtraction fytq = aqscFytqService.findById(id);
		model.addAttribute("fytq", fytq);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "targetmanger/aqsctr/fytq/form";
	}
	
	/**
	 * 修改费用提取信息
	 * @param request,model
	 */
	@RequiresPermissions("aqsc:fytq:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQSC_ExpenseExtraction entity,  Model model){
		String datasuccess="success";	
		entity.setS1(new java.sql.Timestamp(new java.util.Date().getTime()));
		entity.setS2(new java.sql.Timestamp(new java.util.Date().getTime()));
		entity.setS3(0);
		
		aqscFytqService.updateInfo(entity);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除费用提取信息
	 */
	@RequiresPermissions("aqsc:fytq:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			aqscFytqService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		AQSC_ExpenseExtraction bis = aqscFytqService.findById(id);
		
		model.addAttribute("fytq", bis);
		//返回页面
		model.addAttribute("action", "view");
		return "targetmanger/aqsctr/fytq/view";
	}
	
	/**
	 * 获取行业类型list
	 * @param id,model
	 */
	@RequestMapping(value = "lxlist")
	@ResponseBody
	public String getTypeJson() {
		List<Map<String, Object>> typeList=new ArrayList<Map<String, Object>>();
		typeList = aqscFyjsService.findLxList();
		return JsonMapper.getInstance().toJson(typeList);
	}
	
	/**
	 * 根据行业类型，销售收入获取提取标准，计算提取数
	 */
	@RequestMapping(value = "count")
	@ResponseBody
	public Map<String, Object> count(String type, Float m2) {
		return	aqscFyjsService.count(type, m2);
	}
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("aqsc:fytq:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.put("qyname", request.getParameter("aqsc_fytq_qyname"));
		map.put("m1", request.getParameter("aqsc_fytq_cs_m1"));
		map.put("m3", request.getParameter("aqzf_fytq_m3"));
		map.putAll(getAuthorityMap());
		aqscFytqService.exportExcel(response, map);
	}
	
	/**
	 * 显示所有列
	 */
	@RequiresPermissions("aqsc:fytq:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","aqsctr/fytq/export");
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
	@RequiresPermissions("aqsc:fytq:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			resultmap = aqscFytqService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}	
}
