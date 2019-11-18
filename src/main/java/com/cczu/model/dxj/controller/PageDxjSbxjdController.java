package com.cczu.model.dxj.controller;

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

import com.cczu.model.dxj.entity.DXJ_SbXjdEntity;
import com.cczu.model.dxj.service.DxjSbxjdService;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;


/**
 * 设备巡检点Controller
 * @author ZPC
 */
@Controller
@RequestMapping("dxj/sbxjd")
public class PageDxjSbxjdController extends BaseController {

	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	@Autowired
	private DxjSbxjdService dxjSbxjdService;
	
	/**
	 * 跳转页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
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
		}else {//非企业用户页面
			model.addAttribute("type","2");//其他
		}
		return "dxj/dxjxm/index";
	}
	
	/**
	 * list页面 
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request, Model model) {
		Map<String, Object> map = getPageMap(request);
		map.putAll(getAuthorityMap());
		map.put("qyname",request.getParameter("dxj_dxjxm_qyname"));
		map.put("sbname",request.getParameter("dxj_dxjxm_sbname"));
		map.put("sbxmname",request.getParameter("dxj_dxjxm_sbxmname"));
		return dxjSbxjdService.dataGrid(map);	
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "dxj/dxjxm/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(DXJ_SbXjdEntity sbxm, Model model) {
		String datasuccess="success";
		Timestamp t=DateUtils.getSysTimestamp();
		sbxm.setCreatetime(t);
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		sbxm.setID1(sessionuser.getQyid());
		dxjSbxjdService.addInfo(sbxm);
		return datasuccess;
	}	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		DXJ_SbXjdEntity sbxm = dxjSbxjdService.findById(id);
		model.addAttribute("sbxm", sbxm);
		//返回页面
		model.addAttribute("action", "update");
		return "dxj/dxjxm/form";
	}
	
	/**
	 * 修改信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(DXJ_SbXjdEntity sbxm,  Model model){
		String datasuccess="success";
		dxjSbxjdService.addInfo(sbxm);
		return datasuccess;
	}
	
	/**
	 * 删除信息
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			dxjSbxjdService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String, Object> sbxm = dxjSbxjdService.findInforById(id);
		model.addAttribute("sbxm", sbxm);
		return "dxj/dxjxm/view";
	}
	
	/**
	 * 显示所有列
	 */
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","dxj/sbxjd/export");
		return "/common/formexcel";
	}
	
	/**
	 * 导出excel
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.putAll(getAuthorityMap());
		map.put("qyname",request.getParameter("dxj_dxjxm_qyname"));
		map.put("sbname",request.getParameter("dxj_dxjxm_sbname"));
		map.put("sbxmname",request.getParameter("dxj_dxjxm_sbxmname"));
		dxjSbxjdService.exportExcel(response, map);
	}
}
