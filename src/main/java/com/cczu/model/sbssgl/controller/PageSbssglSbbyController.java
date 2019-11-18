package com.cczu.model.sbssgl.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_SBBYEntity;
import com.cczu.model.sbssgl.service.SBSSGLSbbyService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 设备设施管理-设备保养controller
 */
@Controller
@RequestMapping("sbssgl/sbby")
public class PageSbssglSbbyController extends BaseController {

	@Autowired
	private SBSSGLSbbyService sBSSGLSbbyService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model, HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type","2");//集团公司
				else
					model.addAttribute("type","1");//子公司
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else{
			return "../error/403";
		}
		
		if ("tzsb".equals(request.getParameter("sbtype"))) {
			model.addAttribute("sbtype","1");//特种设备
		} else {
			model.addAttribute("sbtype","0");//普通设备
		}
		return "sbssgl/sbby/index";
	}
	
	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("sbtype", request.getParameter("sbtype"));
		map.put("qyname", request.getParameter("qyname"));
		map.put("jhnf", request.getParameter("jhnf"));
		map.put("jhlx", request.getParameter("jhlx"));
		map.put("byqx", request.getParameter("byqx"));
		map.put("sbbh", request.getParameter("sbbh"));
		map.put("sbname", request.getParameter("sbname"));
		map.put("zt", request.getParameter("zt"));
		map.put("taskid", request.getParameter("taskid"));
		map.putAll(getAuthorityMap());
		return sBSSGLSbbyService.dataGrid(map);
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
			//删除设备保养信息
			sBSSGLSbbyService.deleteInfoById(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		//获取设备保养任务信息
		Map<String,Object> sbby = sBSSGLSbbyService.findById(id);	
		model.addAttribute("sbby", sbby);
		return "sbssgl/sbby/view";
	}

	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		SBSSGL_SBBYEntity sbby = sBSSGLSbbyService.find(id);	
		model.addAttribute("sbby", sbby);
		//返回页面
		model.addAttribute("action", "update");
		return "sbssgl/sbby/form";
	}
	
	/**
	 * 修改信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(SBSSGL_SBBYEntity entity, Model model,HttpServletRequest request){
		String datasuccess="success";
		entity.setUserid((long)UserUtil.getCurrentUser().getId());
		sBSSGLSbbyService.updateInfo(entity);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 上传附件页面跳转
	 */
	@RequestMapping(value = "uploadindex/{id}")
	public String uploadindex(@PathVariable("id") Long id, Model model) {
		model.addAttribute("id", id);
		return "sbssgl/sbby/upload";
	}
	
	/**
	 * 上传附件
	 */
	@RequestMapping(value = "uploadfj")
	@ResponseBody
	public String uploadfj(long id,String m3) {
		String datasuccess="success";
		SBSSGL_SBBYEntity sbby = sBSSGLSbbyService.find(id);
		sbby.setM2("1");
		sbby.setM3(m3);
		sbby.setUserid((long)UserUtil.getCurrentUser().getId());
		sBSSGLSbbyService.updateInfo(sbby);
		return datasuccess;
	}
	
	/**
	 * 根据设备保养任务id获得设备保养记录
	 * @param request
	 */
	@RequestMapping(value="sbbylist/{taskid}")
	@ResponseBody
	public Map<String, Object> getData(@PathVariable("taskid") Long taskid,HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("taskid", taskid);
		return sBSSGLSbbyService.dataGrid(map);
	}
}
