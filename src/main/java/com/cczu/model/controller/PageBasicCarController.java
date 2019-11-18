package com.cczu.model.controller;

import com.cczu.model.entity.BASIC_CarEntity;
import com.cczu.model.service.BasicCarService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * 园区车辆管理Controller
 * @author wbth
 */
@Controller
@RequestMapping("basic/car")
public class PageBasicCarController extends BaseController {

	@Autowired
	private BasicCarService basicCarService;
	
	/**
	 * 跳转到列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "basic/car/index";
	}

	/**
	 * 车辆名称list页面 
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request, Model model) {
		Map<String, Object> map = getPageMap(request);
		map.put("category", request.getParameter("basic_car_m1"));//车型
		map.put("number", request.getParameter("basic_car_m3"));//车牌号码
		map.put("name", request.getParameter("basic_car_m9"));//车辆负责人
		
		map.put("type", request.getParameter("type"));//黑名单
		return basicCarService.dataGrid(map);	
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model, HttpServletRequest request) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "basic/car/form";
	}

	/**
	 * 添加车辆信息
	 * @param request,model
	 */
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(BASIC_CarEntity entity, HttpServletRequest request) {
		setBaseInfo(entity);
		return basicCarService.addInfo(entity);
		
	}	
	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//根据id获取车辆信息
		BASIC_CarEntity entity = basicCarService.findInforById(id);
		model.addAttribute("entity", entity);
		//返回页面
		model.addAttribute("action", "update");
		return "basic/car/form";
	}
	
	/**
	 * 修改车辆信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String xfssupdate(BASIC_CarEntity entity){
		String result = "success";
		entity.setS2(DateUtils.getSysTimestamp());
		basicCarService.updateInfo(entity);
		return result;
	}

	/**
	 * 删除车辆信息
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="success";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			//删除信息
			basicCarService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		BASIC_CarEntity entity = basicCarService.findInforById(id);
		model.addAttribute("entity", entity);
		return "basic/car/view";
	}
	
	
	/**
	 * 显示所有列
	 * @param id,model 
	 */
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","basic/car/export");
		return "common/formexcel";
	}
	
	/**
	 * 导出excel
	 * 
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("category", request.getParameter("basic_car_m1"));//车型
		map.put("number", request.getParameter("basic_car_m3"));//车牌号码
		map.put("name", request.getParameter("basic_car_m9"));//车辆负责人
		
		map.put("type", request.getParameter("type"));//黑名单
		
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.putAll(getAuthorityMap());
		basicCarService.exportExcel(response, map);
	}
	
	/**
	 * 获取json数据
	 * 
	 */
	@RequestMapping(value = "json")
	@ResponseBody
	public String getInfoFromJson(HttpServletRequest request) {
		String filter = request.getParameter("filter");
		return JsonMapper.toJsonString(basicCarService.carInfoJson(filter));
	}

	/**
	 * 获取json数据
	 *
	 */
	@RequestMapping(value = "listjson")
	@ResponseBody
	public String getCarsJson(HttpServletRequest request) {
		return JsonMapper.toJsonString(basicCarService.listAllCars());
	}
	/**
	 * 获取json数据
	 *
	 */
	@RequestMapping(value = "tracejson")
	@ResponseBody
	public String getTraceJson(HttpServletRequest request) {
		String filter = request.getParameter("filter");
		return JsonMapper.toJsonString(basicCarService.getTraceJson(filter));
	}


	/**
	 * 检验是否有重复数据
	 */
	@RequestMapping(value = "valid/unique/{plateNum}")
	@ResponseBody
	public boolean validUnique(@PathVariable String plateNum) {
		return basicCarService.validPlateNum(plateNum);
	}

	/**
	 * 查看运单记录信息跳转
	 * @param id,model
	 */
	@RequestMapping(value = "viewydjl", method = RequestMethod.GET)
	public String viewydjl(String m3, Model model) {
		model.addAttribute("cphm", m3);//车牌号码
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("carview","carview");
        return "yszy/kkzysb/index";
	}
	
	/**
	 * 查看车辆违章记录信息跳转
	 * @param id,model
	 */
	@RequestMapping(value = "viewclwzjl", method = RequestMethod.GET)
	public String viewclwzjl(String m3, Model model) {
		model.addAttribute("cphm", m3);//车牌号码
		model.addAttribute("carview","carview");
		return "black/trafficviolation/index";
	}
}
