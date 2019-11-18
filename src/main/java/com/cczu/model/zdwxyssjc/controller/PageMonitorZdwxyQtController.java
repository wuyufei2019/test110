package com.cczu.model.zdwxyssjc.controller;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.BIS_TankEntity;
import com.cczu.model.service.BisCgjcwhsjService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.zdwxyssjc.service.MonitorZdwxyQtService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
 * 在线监控预警-气体数据controller
 */
@Controller
@RequestMapping("zxjkyj/zdwxyqt")
public class PageMonitorZdwxyQtController extends BaseController {

	@Autowired
	private MonitorZdwxyQtService monitorZdwxyQtService;
	@Autowired
	private IBisQyjbxxService qyjbxxService;
	@Autowired
	private BisCgjcwhsjService bisCgjcwhsjService;
	
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
		return "zxjkyj/zdwxy/qt/index";
	}

	/**
	 * 首页气体实时数据list页面
	 *
	 * @param request
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		// 获取企业id
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = qyjbxxService.findInfoById(sessionuser.getQyid());

		Map<String, Object> map = getPageMap(request);
		if (be != null) {
			map.put("qyid", be.getID());
		}
		map.put("qt", "1");
		map.put("qyname", request.getParameter("ssjc_qtnd_qy_name"));
		map.put("cgqname", request.getParameter("cgqname"));
		map.put("dwh", request.getParameter("dwh"));
		map.put("type", request.getParameter("type"));
		map.put("starttime", request.getParameter("starttime"));
		map.put("endtime", request.getParameter("endtime"));
		return monitorZdwxyQtService.dataGrid(map);
	}
	 
	/**
	 * 查看气体实时数据
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		List<Map<String, Object>> cllist=monitorZdwxyQtService.findInfoByQyid(id);
		BIS_EnterpriseEntity ent= qyjbxxService.findInfoById(id);
		model.addAttribute("cllist", cllist);
		model.addAttribute("qiye", ent);
		return "zxjkyj/zdwxy/qt/view";
	}

	/**
	 * 查看气体实时监测详情信息
	 * @param areaid,model
	 */
	@RequestMapping(value = "viewxq/{pointid}/{jctype}", method = RequestMethod.GET)
	public String viewxq(@PathVariable("pointid") Long pointid,@PathVariable("jctype") String jctype, Model model) {
		Map<String, Object> qt = monitorZdwxyQtService.findInfoByPointid(pointid);
		model.addAttribute("qt", qt);
		model.addAttribute("jctype", jctype);
		model.addAttribute("pointid", pointid);
		return "zxjkyj/zdwxy/qt/viewxq";
	}

	/**
	 * 气体报警信息页面
	 */
	@RequestMapping(value="viewbjxx/{id}/{jctype}")
	public String viewbjxx(@PathVariable("id") Long id,@PathVariable("jctype") String jctype, Model model) {
		model.addAttribute("pointid", id);
		model.addAttribute("jctype", jctype);
		return "zxjkyj/zdwxy/qt/bjxxview";
	}

	/**
	 * 气体波动图页面
	 */
	@RequestMapping(value="viewbdtindex/{id}/{jctype}")
	public String viewbdtindex(@PathVariable("id") Long id,@PathVariable("jctype") String jctype, Model model) {
		Map<String, Object> map = bisCgjcwhsjService.findMapById(id);
		model.addAttribute("pointid", id);
		model.addAttribute("jctype", jctype);
		model.addAttribute("unit", map.get("unit"));
		model.addAttribute("label", map.get("label"));
		return "zxjkyj/zdwxy/qt/bdtview";
	}

	/**
	 * 获取气体实时监测企业名称下拉框内容（安监端）
	 * @param mapData
	 * @return
	 */
	@RequestMapping(value="qyjson")
	@ResponseBody
	public String qyJson(Model model) {
		Map<String, Object> map = new HashMap<>();
		map.putAll(getAuthorityMap());
		return monitorZdwxyQtService.getQyJson(map);
	}

}
