package com.cczu.sys.system.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.AppVersionEntity;
import com.cczu.sys.system.service.AppVersionService;

/**
 * APP版本升级controller
 * @author jason
 */
@Controller
@RequestMapping("system/version")
public class AppVersionController extends BaseController{
	
	@Autowired
	private AppVersionService appVersionService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "system/appv/index";
	}
	
	/**
	 * 菜单集合(list)
	 */
	@RequestMapping(value="list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Page<AppVersionEntity> page = getPage(request);
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		page = appVersionService.search(page, filters);
		return getEasyUIData(page);
	}

	/**
	 * 添加跳转
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String menuCreateForm(Model model) {
		model.addAttribute("list", new AppVersionEntity());
		model.addAttribute("action", "create");
		return "system/appv/form";
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public String create(@Valid AppVersionEntity app,Model model) {
		String restring="success";		
		try {
			appVersionService.saveApp(app);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return restring;
	}
	
	/**
	 * 修改跳转
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateMenuForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("list", appVersionService.get(id));
		model.addAttribute("action", "update");
		return "system/appv/form";
	}

	/**
	 * 修改
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(@Valid @ModelAttribute @RequestBody AppVersionEntity app,Model model) {
		String restring="success";
		app.setVersionNo(app.getVersionNo()+1);
		appVersionService.save(app);
		return restring;
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "delete/{ids}", method = RequestMethod.GET)
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String restring="删除成功！";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			appVersionService.delete(Long.parseLong(arrids[i]));
		}
		
		return restring;
	}
	
	/**
	 * 下载APP
	 * @param type
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "download", method = RequestMethod.GET)
	public String downloadApp(String type,Model model) {
		if(StringUtils.isEmpty(type))
			type="1";
		AppVersionEntity app=appVersionService.findVersionNoBytype(type);
		return "redirect:"+app.getFileurl();
	}
}
