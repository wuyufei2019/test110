package com.cczu.model.zdwxyssjc.controller;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.BIS_TankEntity;
import com.cczu.model.service.BisCgjcwhsjService;
import com.cczu.model.service.IBisGwgyService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.impl.BisGwgyServiceImpl;
import com.cczu.model.zdwxyssjc.service.MonitorZdwxyCgService;
import com.cczu.model.zdwxyssjc.service.MonitorZdwxyGwgyService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
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
 * 在线监控预警-高危工艺数据controller
 */
@Controller
@RequestMapping("zxjkyj/zdwxygwgy")
public class PageMonitorZdwxyGwgyController extends BaseController {

	@Autowired
	private MonitorZdwxyGwgyService monitorZdwxyGwgyService;
	@Autowired
	private IBisQyjbxxService qyjbxxService;
	@Autowired
	private BisCgjcwhsjService bisCgjcwhsjService;
	@Autowired
	private IBisGwgyService bisGwgyService;
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
		return "zxjkyj/zdwxy/gwgy/index";
	}

	/**
	 * 首页高危工艺实时数据list页面
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
		map.put("cg", "1");
        map.put("qyname", request.getParameter("ssjc_wl_qy_name"));
		map.put("gwgyname", request.getParameter("gwgyname"));
		map.put("dwh", request.getParameter("dwh"));
		map.put("type", request.getParameter("type"));
		map.put("starttime", request.getParameter("starttime"));
		map.put("endtime", request.getParameter("endtime"));
		return monitorZdwxyGwgyService.dataGrid(map);
	}
	 
	/**
	 * 图形显示页面
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		/*List<Map<String, Object>> cllist=monitorZdwxyGwgyService.findInfoByQyid(id);
		BIS_EnterpriseEntity ent= qyjbxxService.findInfoById(id);
		List<Map<String, Object>> gwgyInfoList=monitorZdwxyGwgyService.getGwgyInfoByQyid(ent.getID());
		model.addAttribute("cllist", cllist);
		model.addAttribute("gwgy", gwgyInfoList);
		model.addAttribute("qiye", ent);*/
		return "zxjkyj/zdwxy/gwgy/view";
	}
	
	/**
	 * 查看高危工艺实时监测详情信息
	 * @param gwgyid,model
	 */
	@RequestMapping(value = "viewxq/{gwgyid}/{jctype}", method = RequestMethod.GET)
	public String viewxq(@PathVariable("gwgyid") Long gwgyid,@PathVariable("jctype") String jctype, String pointid, Model model) {
		if ("0".equals(jctype)) {
			model.addAttribute("cg", monitorZdwxyGwgyService.findListByGwgyid(gwgyid));
		} else {
			model.addAttribute("cg", monitorZdwxyGwgyService.findInfoByGwgyid(gwgyid,jctype));
		}
		model.addAttribute("jctype", jctype);
		model.addAttribute("pointid", pointid);
		return "zxjkyj/zdwxy/gwgy/viewxq";
	} 
	
	/**
	 * 高危工艺报警信息页面
	 */
	@RequestMapping(value="viewbjxx/{id}/{jctype}")
	public String viewbjxx(@PathVariable("id") Long id,@PathVariable("jctype") String jctype, Model model) {
		model.addAttribute("pointid", id);
		model.addAttribute("jctype", jctype);
		return "zxjkyj/zdwxy/gwgy/bjxxview";
	}
	
	/**
	 * 高危工艺波动图页面
	 */
	@RequestMapping(value="viewbdtindex/{id}/{jctype}")
	public String viewbdtindex(@PathVariable("id") Long id,@PathVariable("jctype") String jctype, Model model) {
		Map<String, Object> map = bisCgjcwhsjService.findMapById(id);
		model.addAttribute("pointid", id);
		model.addAttribute("jctype", jctype);
		model.addAttribute("unit", map.get("unit"));
		model.addAttribute("label", map.get("label"));
		return "zxjkyj/zdwxy/gwgy/bdtview";
	}

	/**
	 * 获取实时监测点详情信息
	 */
	@RequestMapping(value="getjcdxq")
	@ResponseBody
	public String getJcdXqInfo(HttpServletRequest request, Model model) {
		Long qyid = UserUtil.getCurrentShiroUser().getQyid();
		String type = request.getParameter("type");
		return JsonMapper.toJsonString(monitorZdwxyGwgyService.getJcdxqInfo(qyid, type));
	}

	/**
	 * 获取高危工艺实时监测企业名称下拉框内容（安监端）
	 * @param model
	 * @return
	 */
	@RequestMapping(value="qyjson")
	@ResponseBody
	public String qyJson(Model model) {
		Map<String, Object> map = new HashMap<>();
		map.putAll(getAuthorityMap());
		return monitorZdwxyGwgyService.getQyJson(map);
	}

	/**
	 * 查看高危工艺温度、压力、液位实时监测详情信息
	 * @param gwgyid,model
	 */
	@RequestMapping(value = "viewgwgysssjxq/{gwgyid}")
	public String viewCgsssjXq(@PathVariable("gwgyid") Long gwgyid, Model model) {
		Map<String, Object> gwgy = bisGwgyService.findById(gwgyid);// 高危工艺信息
		List<Map<String, Object>> list = monitorZdwxyGwgyService.findListByGwgyid(gwgyid);
		model.addAttribute("gwgy", gwgy);
		model.addAttribute("list", list);
		return "zxjkyj/zdwxy/gwgy/viewgwgysssjxq";
	}

	/**
	 * 根据企业id获取有监测指标的高危工艺id和名称
	 * @param request,model
	 */
	@RequestMapping(value = "getgwgyinfo")
	@ResponseBody
	public String getGwgyInfo(HttpServletRequest request, Model model) {
		return JsonMapper.toJsonString(monitorZdwxyGwgyService.getGwgyInfoByQyid(UserUtil.getCurrentShiroUser().getQyid()));
	}

	/**
	 * 根据高危工艺id获取温度、压力、液位实时数据
	 * @param request,model
	 */
	@RequestMapping(value = "getgwgysssj/{gwgyid}")
	@ResponseBody
	public String getGwgySssj(@PathVariable("gwgyid") Long gwgyid, Model model) {
		return JsonMapper.toJsonString(monitorZdwxyGwgyService.findListByGwgyid(gwgyid));
	}

}
