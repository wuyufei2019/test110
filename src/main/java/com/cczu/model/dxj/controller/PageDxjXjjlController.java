package com.cczu.model.dxj.controller;

import java.text.ParseException;
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

import com.cczu.model.dxj.service.DxjXjjlService;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 点巡检记录
 * @author zpc
 */
@Controller
@RequestMapping("dxj/dxjjl")
public class PageDxjXjjlController extends BaseController{

	@Autowired
	private DxjXjjlService dxjXjjlService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
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
		return "dxj/dxjjl/index";
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.putAll(getAuthorityMap());
		map.put("qyname",request.getParameter("dxj_dxjjl_qyname"));
		map.put("sbname",request.getParameter("dxj_dxjjl_sbname"));
		//map.put("sbxmname",request.getParameter("dxj_dxjjl_sbxmname"));
		map.put("checkresult",request.getParameter("dxj_dxjjl_checkresult"));
		return dxjXjjlService.dataGrid(map);
	}

	/**
	 * 删除巡检记录信息
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			dxjXjjlService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * 
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") long id, Model model) {
		Map<String,Object> dxjjl = dxjXjjlService.findInforById(id);
		model.addAttribute("dxjjl", dxjjl);
		return "dxj/dxjjl/view";
	}
	
	/**
	 * 显示所有列
	 * @param id,model
	 */
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","dxj/dxjjl/export");
		return "common/formexcel";
	}
	
	/**
	 * 导出Excel
	 * @param request
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public void getExcel(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(getAuthorityMap());
		map.put("qyname",request.getParameter("dxj_dxjjl_qyname"));
		map.put("sbname",request.getParameter("dxj_dxjjl_sbname"));
		//map.put("sbxmname",request.getParameter("dxj_dxjjl_sbxmname"));
		map.put("checkresult",request.getParameter("dxj_dxjjl_checkresult"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		dxjXjjlService.exportExcel(response, map);
	}
	
	/**
	 * 巡检记录隐患页面
	 */
	@RequestMapping(value="yhlist")
	@ResponseBody
	public Map<String, Object> getyhData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xjjlid",request.getParameter("xjjlid"));
		return dxjXjjlService.yhdataGrid(map);
	}
}
