package com.cczu.model.sbssgl.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_SBGZEntity;
import com.cczu.model.sbssgl.service.SBSSGLSbgzService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

/**
 * 设备设施管理-设备故障controller
 */
@Controller
@RequestMapping("sbssgl/sbgz")
public class PageSbssglSbgzController extends BaseController {

	@Autowired
	private SBSSGLSbgzService sBSSGLSbgzService;
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
		return "sbssgl/sbgz/index";
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
		map.put("m1", request.getParameter("m1"	));
		map.put("m2", request.getParameter("m2"));
		map.put("sbname", request.getParameter("sbname"));
		map.put("m9", request.getParameter("m9"));
		map.put("m10", request.getParameter("m10"));
		map.putAll(getAuthorityMap());
		return sBSSGLSbgzService.dataGrid(map);
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
			//删除点巡检信息
			sBSSGLSbgzService.deleteInfoById(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type","2");//集团公司
				else
					model.addAttribute("type","1");//子公司
			}
		}
		//获取点巡检信息
		Map<String,Object> sbgz = sBSSGLSbgzService.findById(id);	
		model.addAttribute("sbgz", sbgz);
		return "sbssgl/sbgz/view";
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model, HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type","2");//集团公司
				else
					model.addAttribute("type","1");//子公司
			}
		}
		model.addAttribute("action", "create");
		model.addAttribute("sbtype", request.getParameter("sbtype"));
		return "sbssgl/sbgz/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.POST)
	@ResponseBody
	public String create(SBSSGL_SBGZEntity entity, Model model,HttpServletRequest request) {
		String datasuccess="success";
		/*entity.setQyid(UserUtil.getCurrentShiroUser().getQyid());*/
		sBSSGLSbgzService.addInfo(entity);
		//返回结果
		return datasuccess;
	}
	

	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type","2");//集团公司
				else
					model.addAttribute("type","1");//子公司
			}
		}
		SBSSGL_SBGZEntity sbgz = sBSSGLSbgzService.find(id);	
		model.addAttribute("sbgz", sbgz);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("sbtype", request.getParameter("sbtype"));
		return "sbssgl/sbgz/form";
	}
	
	/**
	 * 修改信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(SBSSGL_SBGZEntity entity, Model model,HttpServletRequest request){
		String datasuccess="success";
		sBSSGLSbgzService.updateInfo(entity);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 显示所有列
	 * @param id,model 
	 */
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","sbssgl/sbgz/export");
		return "common/formexcel";
	}
	
	/**
	 * 导出普通设备故障数据
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyname", request.getParameter("qyname"));
		map.put("sbtype", request.getParameter("sbtype"));
		map.put("qyname", request.getParameter("qyname"));
		map.put("m1", request.getParameter("m1"));
		map.put("m2", request.getParameter("m2"));
		map.put("sbname", request.getParameter("sbname"));
		map.put("m9", request.getParameter("m9"));
		map.put("m10", request.getParameter("m10"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.putAll(getAuthorityMap());
		sBSSGLSbgzService.exportExcel(response, map, request);
	}
	
	/**
	 * 显示所有列
	 * @param id,model 
	 */
	@RequestMapping(value = "colindex2", method = RequestMethod.GET)
	public String colindex2(Model model) {
		model.addAttribute("url","sbssgl/sbgz/exporttzsb");
		return "common/formexcel";
	}
	
	/**
	 * 导出特种设备故障数据
	 */
	@RequestMapping(value = "exporttzsb")
	@ResponseBody
	public void exportTzsb(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyname", request.getParameter("qyname"));
		map.put("sbtype", request.getParameter("sbtype"));
		map.put("qyname", request.getParameter("qyname"));
		map.put("m1", request.getParameter("m1"));
		map.put("m2", request.getParameter("m2"));
		map.put("sbname", request.getParameter("sbname"));
		map.put("m9", request.getParameter("m9"));
		map.put("m10", request.getParameter("m10"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.putAll(getAuthorityMap());
		sBSSGLSbgzService.exportExcel2(response, map, request);
	}
	
	/**
	 * 导出设备维修需求单word
	 * @throws ParseException 
	 */
	@RequestMapping(value = "exportword")
	@ResponseBody
	public String getWord(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		//设置导出的文件名
		String filename = "设备维修需求单_" + DateUtils.getDateRandom() + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "sbwxxqd.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
}
