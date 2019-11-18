package com.cczu.model.controller;


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

import com.cczu.model.entity.AQZF_WfxwEntity;
import com.cczu.model.service.AqzfWfxwService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 违法行为
 * @author zpc
 * @date 2017/012/09
 */
@Controller
@RequestMapping("aqzf/wfxw")
public class PageAqzfWfxwController extends BaseController{

	@Autowired
	private AqzfWfxwService aqzfWfxwService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "aqzf/sjwh/wfxw/index";
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("m1", request.getParameter("aqzf_wfxw_M1"));
		return aqzfWfxwService.dataGrid(map);
	}
	
	/**
	 * 删除违法行为记录
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			aqzfWfxwService.deleteInfo(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}

	/**
	 * 添加违法行为页面跳转
	 * @param model
	 */
	@RequiresPermissions("aqzf:wfxw:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "aqzf/sjwh/wfxw/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequiresPermissions("aqzf:wfxw:add")
	@RequestMapping(value = "create" )
	@ResponseBody
	public String create(AQZF_WfxwEntity wfxw, HttpServletRequest request) {
		String datasuccess="success";
		wfxw.setID1(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));
		aqzfWfxwService.addInfo(wfxw);
		return datasuccess;
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		AQZF_WfxwEntity wfxw = aqzfWfxwService.findById(id);
		model.addAttribute("wfxw", wfxw);
		model.addAttribute("action", "update");
		return "aqzf/sjwh/wfxw/form";
	}
	
	/**
	 * 修改
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQZF_WfxwEntity wfxw, Model model){
		String datasuccess="success";	
		aqzfWfxwService.updateInfo(wfxw);
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		AQZF_WfxwEntity wfxw = aqzfWfxwService.findById(id);		
		model.addAttribute("wfxw", wfxw);
		return "aqzf/sjwh/wfxw/view";
	}
	
	/**
	 * 违法行为list下拉填充 id，text（违法行为）
	 * @param model
	 * @return
	 */
	@RequestMapping(value="idlist")
	@ResponseBody
	public List<Map<String, Object>> getWfxwIdlist(Model model) {
		return aqzfWfxwService.getWfxwIdlist();
	}
	
	/**
	 * 显示所有列
	 * @param id,model
	 */
	@RequiresPermissions("aqzf:wfxw:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","aqzf/wfxw/export");
		return "common/formexcel";
	}
	
	/**
	 * 导出Excel
	 * 
	 * @param request
	 */
	@RequiresPermissions("aqzf:wfxw:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void getExcel(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.put("m1", request.getParameter("aqzf_wfxw_M1"));
		aqzfWfxwService.exportExcel(response, map);
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
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		resultmap = aqzfWfxwService.exinExcel(map);
		return JsonMapper.toJsonString(resultmap);
	}	
	
	/**
	 * 根据ids查找违反条款
	 */
	@RequestMapping(value = "wftk/{wfids}")
	@ResponseBody
	public String findidcard(@PathVariable("wfids") String wfids) {
		String cbyj = "";
		if(!StringUtils.isEmpty(wfids)){
			String[] aids = wfids.split(",");
			for(int i=0;i<aids.length;i++){
				try {
					AQZF_WfxwEntity wfxw = aqzfWfxwService.findById(Long.parseLong(aids[i]));
					if(i==0){
						cbyj += wfxw.getM2();
					}else{
						cbyj += ","+wfxw.getM2();
					}
				} catch (Exception e) {

				}
			}
		}
		return cbyj;
	}
}
