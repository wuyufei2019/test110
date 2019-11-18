package com.cczu.model.zdwxyssjc.controller;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.zdwxyssjc.service.MonitorZdwxyAjService;
import com.cczu.model.zdwxyssjc.service.MonitorZdwxyCgService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 在线监控预警-储罐数据controller
 */
@Controller
@RequestMapping("zxjkyj/zdwxyaj")
public class PageMonitorZdwxyAjController extends BaseController {

	@Autowired
	private MonitorZdwxyAjService monitorZdwxyAjService;
	@Autowired
	private IBisQyjbxxService qyjbxxService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		model.addAttribute("usertype", sessionuser.getUsertype());
		model.addAttribute("qyid", sessionuser.getQyid());
		
		if("1".equals(sessionuser.getUsertype())){
			BIS_EnterpriseEntity be = qyjbxxService.findInfoById(sessionuser.getQyid());
			if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
				model.addAttribute("usertype", "0");
		}
		//获取接入企业数
		Map<String, Object> mapData=new HashMap<String, Object>();
		mapData.putAll(getAuthorityMap());
		model.addAttribute("qysum", monitorZdwxyAjService.getQyCount(mapData));
		return "zxjkyj/zdwxy/aj/index";
	}

	/**
	 * 企业视频树状list
	 */
	@RequestMapping(value="json")
	@ResponseBody
	public String  list(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(getAuthorityMap());
		map.put("qyid2",request.getParameter("qyid"));
		map.put("qyname", request.getParameter("qyname"));
		return monitorZdwxyAjService.getJson_list(map);
	}

	/**
	 * 查询重大危险源一级、二级的企业json
	 * @return
	 */
	@RequestMapping(value="zdwxydjjson")
	@ResponseBody
	public String zdwxydjJson() {
		return monitorZdwxyAjService.getZdwxydjJson();
	}
	 
}
