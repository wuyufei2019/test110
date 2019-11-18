package ${code_classpack }.controller;

import java.sql.Timestamp;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ${code_classpack }.entity.${code_classname };
import ${code_classpack }.service.${code_classname?replace('_', '' ) }Service;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.sys.comm.utils.ServletUtils;

/**
 * ${code_description }controller
 * @author 
 * @date ${.now?string["yyyy年MM月dd日"] }
 */
@Controller
@RequestMapping("${code_mapurl!'' }")
public class Page${code_classname?replace('_', '' ) }Controller extends BaseController {

	@Autowired
	private ${code_classname?replace('_', '' ) }Service ${code_classname?replace('_', '' )?uncap_first }Service;

	/**
     *页面跳转 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
         return "${code_viewpath!'' }/index";
	}

	/**
	 * list界面
	 * @param request
	 */
    <#if code_permisson=="true">
	@RequiresPermissions("${permisson.permCode }view")
    </#if>
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> dataMap = getPageMap(request);
		dataMap.putAll(ServletUtils.getParametersStartingWith(request,  "view_"));
		return ${code_classname?replace('_', '' )?uncap_first }Service.dataGrid(dataMap);
	}


	/**
	 * 添加页面跳转
	 * @param model
	 */
	   <#if code_permisson=='true'>
	@RequiresPermissions("${permisson.permCode }add")
    </#if>
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "${code_viewpath!'' }/form";
	}

	/**
	 * 添加信息
	 * @param ${code_classname }
	 */
	<#if code_permisson=='true'>
	@RequiresPermissions("${permisson.permCode }add")
    </#if>
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(${code_classname } entity) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		${code_classname?replace('_', '' )?uncap_first }Service.addInfo(entity);
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * @param id,model
	 */
    <#if code_permisson=='true'>
   @RequiresPermissions("${permisson.permCode }update")
    </#if>
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") long id, Model model) {
		${code_classname } entity = ${code_classname?replace('_', '' )?uncap_first }Service.findById(id);
		model.addAttribute("entity", entity);
		model.addAttribute("action", "update");
		return "${code_viewpath!'' }/form";
	}

	/**
	 * 修改信息
	 * 
	 * @param request,model
	 */
	<#if code_permisson=='true'>
   @RequiresPermissions("${permisson.permCode }update")
    </#if>
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(${code_classname } entity, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		entity.setS2(t);
		${code_classname?replace('_', '' )?uncap_first }Service.updateInfo(entity);
		return datasuccess;
	}

	/**
	 * 删除信息
     * @param ids
	 */
    <#if code_permisson=='true'>
   @RequiresPermissions("${permisson.permCode }delete")
    </#if>
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "success";
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			${code_classname?replace('_', '' )?uncap_first }Service.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * @param id
	 */
	<#if code_permisson=='true'>
   @RequiresPermissions("${permisson.permCode }view")
    </#if>
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String sview(@PathVariable("id") long id ,Model model) {
		${code_classname } entity = ${code_classname?replace('_', '' )?uncap_first }Service.findById(id);
		model.addAttribute("entity", entity);
		return "${code_viewpath!'' }/view";
	}
	
}
