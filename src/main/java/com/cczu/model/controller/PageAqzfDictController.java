package com.cczu.model.controller;

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

import com.cczu.model.entity.AQZF_DictEntity;
import com.cczu.model.service.AqzfDictService;
import com.cczu.sys.comm.controller.BaseController;

/**
 * 安全生产执法--字典Controller
 * @author jason
 * @date 2017年8月3日
 */
@Controller
@RequestMapping("aqzf/dict")
public class PageAqzfDictController extends BaseController{

	@Autowired
	private AqzfDictService aqzfDictService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "aqzf/sjwh/dict/index";
	}
	
	/**
	 * list页面
	 */
	@RequiresPermissions("aqzf:dict:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("label", request.getParameter("label"));
		map.put("type", request.getParameter("type"));
		return aqzfDictService.dataGrid(map);
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 * @return
	 */
	@RequiresPermissions("aqzf:dict:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "aqzf/sjwh/dict/form";
	}
	
	/**
	 * 添加信息
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequiresPermissions("aqzf:dict:add")
	@RequestMapping(value = "create" )
	@ResponseBody
	public String create(AQZF_DictEntity entity, HttpServletRequest request) {
		String datasuccess="success";
		aqzfDictService.addInfo(entity);
		return datasuccess;
	}
	
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("aqzf:dict:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			aqzfDictService.deleteInfo(Long.parseLong(aids[i]));
		}
		
		return datasuccess;
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("aqzf:dict:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		AQZF_DictEntity dict  = aqzfDictService.findById(id);
		model.addAttribute("dict", dict);
		model.addAttribute("action", "update");
		return "aqzf/sjwh/dict/form";
	}
	
	/**
	 * 修改
	 * @param dict
	 * @param model
	 * @return
	 */
	@RequiresPermissions("aqzf:dict:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQZF_DictEntity dict, Model model){
		String datasuccess="success";	
		aqzfDictService.updateInfo(dict);
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("aqzf:dict:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		AQZF_DictEntity dict = aqzfDictService.findById(id);		
		model.addAttribute("dict", dict);
		return "aqzf/sjwh/dict/view";
	}
	

	/**
	 * 字典类别下拉填充
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "gettype")
	@ResponseBody
	public String getTypeJson(Model model) {
		return aqzfDictService.getTypeList();
	}
	
	
	/**
	 * 字典类别下拉填充
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "json/{type}")
	@ResponseBody
	public String getJson(@PathVariable("type") String type,Model model) {
		return aqzfDictService.findByType(type);
	}
	
}
