package com.cczu.model.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.service.IEadJclsjlService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 决策历史记录controller
 * @author jason
 */
@Controller
@RequestMapping("ead/jclsjl")
public class PageEadJclsjlController extends BaseController {

	@Autowired
	private IEadJclsjlService eadJclsjlService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "ead/jclsjl/index";
	}
	
	/**
	 * 计算结束_事故后果页面_应急队伍  列表显示
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> dataGrid(HttpServletRequest request, Model model) {
		Map<String, Object> map = getPageMap(request);
		if(StringUtils.isNotEmpty(request.getParameter("check_name")))
			map.put("qyname", request.getParameter("check_name"));
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		switch(sessionuser.getRoles().get(0).getRoleCode()){
			case "ajcountryadmin":
			case "ajcountry":
			case "ajprovinceadmin":
			case "ajprovince":
			case "ajcityadmin":
			case "ajcity":
			case "ajcountyadmin":
			case "ajcounty":
			case "ajtownadmin":
			case "ajtown":
				map.put("xzqy",sessionuser.getXzqy());
				break;
		}
		
		return eadJclsjlService.findconsequenceResTeamDataGrid(map);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String restring="删除成功！";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			eadJclsjlService.delete(Long.parseLong(arrids[i]));
		}
		
		return restring;
	}

	
}
