package com.cczu.model.controller;

import java.text.ParseException;

import java.util.HashMap;
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

import com.cczu.model.entity.AQJG_DSFCfjlEntity;
import com.cczu.model.service.IAqjgCfjlService;
import com.cczu.sys.comm.controller.BaseController;

/**
 * 第三方处罚记录
 * @author zpc
 * @date 2017/07/06
 */
@Controller
@RequestMapping("dsffw/cfjl")
public class PageAqjgDSFCfjlController extends BaseController{

	@Autowired
	private IAqjgCfjlService AqjgCfjlService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "aqjg/dsf/cfjl/index";
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("dsfname", request.getParameter("aqjg_dsf_cfjl_M1"));
		return AqjgCfjlService.dataGrid(map);
	}
	
	/**
	 * 添加第三方处罚信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("dsffw:cfjl:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "aqjg/dsf/cfjl/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequiresPermissions("dsffw:cfjl:add")
	@RequestMapping(value = "create" )
	@ResponseBody
	public String create(AQJG_DSFCfjlEntity cfjllist, HttpServletRequest request) {
		String datasuccess="success";
		AqjgCfjlService.addInfo(cfjllist);
		return datasuccess;
	}
	
	/**
	 * 删除第三方处罚记录
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
			AqjgCfjlService.deleteInfo(Long.parseLong(aids[i]));
		}
		
		return datasuccess;
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的成员信息
		AQJG_DSFCfjlEntity cfjl  = AqjgCfjlService.findById(id);
		model.addAttribute("cfjllist", cfjl);
		//返回页面
		model.addAttribute("action", "update");
		return "aqjg/dsf/cfjl/form";
	}
	
	/**
	 * 修改
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQJG_DSFCfjlEntity cfjllist, Model model){
		String datasuccess="success";	
		AqjgCfjlService.updateInfo(cfjllist);
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		//查询选择的安全备案信息
		AQJG_DSFCfjlEntity cfjllist = AqjgCfjlService.findById(id);		
		model.addAttribute("cfjllist", cfjllist);
		return "aqjg/dsf/cfjl/view";
	}
	
	/**
	 * 显示所有列
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("dsffw:cfjl:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","dsffw/cfjl/export");
		return "common/formexcel";
	}
	
	/**
	 * 导出Excel
	 * 
	 * @param request
	 */
	@RequiresPermissions("dsffw:cfjl:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void getExcel(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		AqjgCfjlService.exportExcel(response, map);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
