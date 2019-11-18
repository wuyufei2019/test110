package com.cczu.model.fkcl.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.fkcl.entity.FKCL_FkyyEntity;
import com.cczu.model.fkcl.service.FkclFkyyService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 访客预约controller
 */
@Controller
@RequestMapping("fkcl/fkyy")
public class PageFkclFkyyController extends BaseController {

	@Autowired
	private FkclFkyyService fkclFkyyService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "fkcl/fkyy/index";
	}
	
	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyid", UserUtil.getCurrentUser().getId2());
		map.put("status", request.getParameter("fkcl_fkyy_status"));
		map.put("m1", request.getParameter("fkcl_fkyy_m1"));
		map.put("m2", request.getParameter("fkcl_fkyy_m2"));
		return fkclFkyyService.dataGrid(map);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			fkclFkyyService.deleteInfo(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "fkcl/fkyy/addform";
	}
	
	/**
	 * pc添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.POST)
	@ResponseBody
	public String create(FKCL_FkyyEntity entity, Model model,HttpServletRequest request) {
		String datasuccess="success";
		entity.setQyid(UserUtil.getCurrentUser().getId2());
		entity.setM6(UserUtil.getCurrentUser().getName());
		entity.setM7(DateUtils.getSysTimestamp());
		entity.setStatus("3");
		fkclFkyyService.addInfo(entity);	
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		FKCL_FkyyEntity entity = fkclFkyyService.find(id);
		model.addAttribute("entity", entity);
		//返回页面
		model.addAttribute("action", "update");
		return "fkcl/fkyy/updateform";
	}
	
	/**
	 * pc修改信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(FKCL_FkyyEntity entity, Model model,HttpServletRequest request){
		String datasuccess="success";	
		fkclFkyyService.updateInfo(entity);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 预约确认
	 * @param request,model
	 */
	@RequestMapping(value = "yyqr/{id}/{status}")
	@ResponseBody
	public String yyqr(@PathVariable("id") Long id,@PathVariable("status") String status, Model model,HttpServletRequest request){
		String datasuccess="操作成功";	
		FKCL_FkyyEntity entity = fkclFkyyService.find(id);
		entity.setM6(UserUtil.getCurrentUser().getName());
		entity.setM7(DateUtils.getSysTimestamp());
		entity.setStatus(status);
		fkclFkyyService.updateInfo(entity);
		return datasuccess;
	}
	
	/**
	 * 进厂页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "jc/{id}")
	public String jcindex(@PathVariable("id") Long id, Model model) {
		model.addAttribute("id", id);
		//返回页面
		model.addAttribute("action", "jcupdate");
		return "fkcl/fkyy/jccform";
	}
	
	/**
	 * 进厂信息修改
	 * @param request,model
	 */
	@RequestMapping(value = "jcupdate")
	@ResponseBody
	public String jcupdate(Long id,String rynum, Model model,HttpServletRequest request){
		String datasuccess="success";	
		FKCL_FkyyEntity entity = fkclFkyyService.find(id);
		entity.setM8(UserUtil.getCurrentUser().getName());
		entity.setM9(DateUtils.getSysTimestamp());
		entity.setM10(rynum);
		entity.setStatus("4");
		fkclFkyyService.updateInfo(entity);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 出厂页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "cc/{id}")
	public String ccindex(@PathVariable("id") Long id, Model model) {
		model.addAttribute("id", id);
		//返回页面
		model.addAttribute("action", "ccupdate");
		return "fkcl/fkyy/jccform";
	}
	
	/**
	 * 出厂信息修改
	 * @param request,model
	 */
	@RequestMapping(value = "ccupdate")
	@ResponseBody
	public String ccupdate(Long id,String rynum, Model model,HttpServletRequest request){
		String datasuccess="success";	
		FKCL_FkyyEntity entity = fkclFkyyService.find(id);
		entity.setM11(UserUtil.getCurrentUser().getName());
		entity.setM12(DateUtils.getSysTimestamp());
		entity.setM13(rynum);
		entity.setStatus("5");
		fkclFkyyService.updateInfo(entity);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String,Object> entity = fkclFkyyService.findInfoById(id);
		model.addAttribute("entity", entity);
		return "fkcl/fkyy/view";
	}
	
	/**
	 * 显示所有列
	 * @param id,model
	 */
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","fkcl/fkyy/export");
		return "/common/formexcel";
	}

	/**
	 * 导出excel
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyid", UserUtil.getCurrentUser().getId2());
		map.put("status", request.getParameter("fkcl_fkyy_status"));
		map.put("m1", request.getParameter("fkcl_fkyy_m1"));
		map.put("m2", request.getParameter("fkcl_fkyy_m2"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		fkclFkyyService.exportExcel(response, map);
	}
	
	/**
	 * h5预约
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "h5yy")
	@ResponseBody
	public String h5yy(Long qyid, String M1, String M2, String M3, String M4, String M5,HttpServletRequest request) {
		String callback = request.getParameter("callback");
		Map<String, Object> map = new HashMap<String, Object>();
		String datasuccess="success";	
		try {
			FKCL_FkyyEntity entity = new FKCL_FkyyEntity();
			entity.setM1(Timestamp.valueOf(M1+":00"));
			entity.setM2(M2);
			entity.setM3(M3);
			entity.setM4(M4);
			entity.setM5(M5);
			entity.setQyid(qyid);
			entity.setStatus("1");
			fkclFkyyService.addInfo(entity);	
		} catch (Exception e) {
			datasuccess="error";
		}
		map.put("result", datasuccess);
		return callback+"("+JsonMapper.getInstance().toJson(map)+")";
	}
}
