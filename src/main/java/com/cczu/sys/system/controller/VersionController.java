package com.cczu.sys.system.controller;

import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.VersionEntity;
import com.cczu.sys.system.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 项目版本记录controller
 * @author jason
 */
@Controller
@RequestMapping("system/xmversion")
public class VersionController extends BaseController{
	
	@Autowired
	private VersionService versionService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "system/version/index";
	}
	
	/**
	 * 菜单集合(list)
	 */
	@RequestMapping(value="list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Page<VersionEntity> page = getPage(request);
        page.setOrderBy("id");
        page.setOrder(page.DESC);
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		page = versionService.search(page, filters);
		return getEasyUIData(page);
	}

	/**
	 * 添加跳转
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String menuCreateForm(Model model) {
		model.addAttribute("infor", new VersionEntity());
		model.addAttribute("action", "create");
		return "system/version/form";
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public String create(@Valid VersionEntity app,Model model) {
		String restring="success";		
		try {
			versionService.save(app);
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
		model.addAttribute("infor", versionService.get(id));
		model.addAttribute("action", "update");
		return "system/version/form";
	}

	/**
	 * 修改
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(@Valid @ModelAttribute @RequestBody VersionEntity app,Model model) {
		String restring="success";
		versionService.save(app);
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
			versionService.delete(Long.parseLong(arrids[i]));
		}
		
		return restring;
	}

    @RequestMapping(value = "maxversion")
    @ResponseBody
    public String getMaxVersion(Model model) {
        return versionService.getMaxVersion();
    }

    @RequestMapping(value = "showhis")
    public String showHis(Model model){
        model.addAttribute("list", versionService.findAll());
        return "system/version/show";
    }
}
