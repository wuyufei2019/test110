package com.cczu.model.zdwxyssjc.controller;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.BIS_TankEntity;
import com.cczu.model.service.BisCgjcwhsjService;
import com.cczu.model.service.IBisCgxxService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.zdwxyssjc.service.MonitorZdwxyCgService;
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
 * 在线监控预警-储罐数据controller
 */
@Controller
@RequestMapping("zxjkyj/zdwxycg")
public class PageMonitorZdwxyCgController extends BaseController {

	@Autowired
	private MonitorZdwxyCgService monitorZdwxyCgService;
	@Autowired
	private IBisQyjbxxService qyjbxxService;
	@Autowired
	private BisCgjcwhsjService bisCgjcwhsjService;
	@Autowired
	private IBisCgxxService bisCgxxService;

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
		return "zxjkyj/zdwxy/cg/index";
	}

	/**
	 * 首页储罐实时数据list页面
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
		map.put("cglx", request.getParameter("cglx"));
		map.put("wh", request.getParameter("wh"));
		map.put("type", request.getParameter("type"));
		map.put("starttime", request.getParameter("starttime"));
		map.put("endtime", request.getParameter("endtime"));
		return monitorZdwxyCgService.dataGrid(map);
	}
	 
	/**
	 * 图形显示页面
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		List<Map<String, Object>> cllist=monitorZdwxyCgService.findInfoByQyid(id);
		for (Map<String, Object> map : cllist) {
			if((map.get("gg")==null||map.get("gg").toString().equals(""))||(map.get("yw")==null||map.get("yw").toString().equals(""))){
				map.put("percent", 0);
			}else{
				map.put("percent", Double.parseDouble(map.get("yw").toString())/(Double.parseDouble(map.get("gg").toString())*100));
			}
		}
		BIS_EnterpriseEntity ent= qyjbxxService.findInfoById(id);
		model.addAttribute("cllist", cllist);
		model.addAttribute("qiye", ent);
		return "zxjkyj/zdwxy/cg/view";
	}
	
	/**
	 * 查看储罐实时监测详情信息
	 * @param tankid,model
	 */
	@RequestMapping(value = "viewxq/{tankid}/{jctype}", method = RequestMethod.GET)
	public String viewxq(@PathVariable("tankid") Long tankid,@PathVariable("jctype") String jctype, String pointid, Model model) {
		if ("0".equals(jctype)) {
			model.addAttribute("cg", monitorZdwxyCgService.findListByTankid(tankid));
		} else {
			model.addAttribute("cg", monitorZdwxyCgService.findInfoByTankid(tankid,jctype));
		}
		model.addAttribute("jctype", jctype);
		model.addAttribute("pointid",pointid);
		return "zxjkyj/zdwxy/cg/viewxq";
	} 
	
	/**
	 * 储罐报警信息页面
	 */
	@RequestMapping(value="viewbjxx/{id}/{jctype}")
	public String viewbjxx(@PathVariable("id") Long id,@PathVariable("jctype") String jctype, Model model) {
		model.addAttribute("pointid", id);
		model.addAttribute("jctype", jctype);
		return "zxjkyj/zdwxy/cg/bjxxview";
	}
	
	/**
	 * 储罐波动图页面
	 */
	@RequestMapping(value="viewbdtindex/{id}/{jctype}")
	public String viewbdtindex(@PathVariable("id") Long id,@PathVariable("jctype") String jctype, Model model) {
		Map<String, Object> map = bisCgjcwhsjService.findMapById(id);
		model.addAttribute("pointid", id);
		model.addAttribute("jctype", jctype);
		model.addAttribute("unit", map.get("unit"));
		model.addAttribute("label", map.get("label"));
		return "zxjkyj/zdwxy/cg/bdtview";
	}

	/**
	 * 获取实时监测点详情信息
	 */
	@RequestMapping(value="getjcdxq")
	@ResponseBody
	public String getJcdXqInfo(HttpServletRequest request, Model model) {
		Long qyid = UserUtil.getCurrentShiroUser().getQyid();
		String type = request.getParameter("type");
		return JsonMapper.toJsonString(monitorZdwxyCgService.getJcdxqInfo(qyid, type));
	}

	/**
	 * 获取储罐实时监测企业名称下拉框内容（安监端）
	 * @param model
	 * @return
	 */
	@RequestMapping(value="qyjson")
	@ResponseBody
	public String qyJson(Model model) {
		Map<String, Object> map = new HashMap<>();
		map.putAll(getAuthorityMap());
		return monitorZdwxyCgService.getQyJson(map);
	}

	/**
	 * 查看储罐温度、压力、液位实时监测详情信息
	 * @param tankid,model
	 */
	@RequestMapping(value = "viewcgsssjxq/{tankid}")
	public String viewCgsssjXq(@PathVariable("tankid") Long tankid, Model model) {
		BIS_TankEntity tank = bisCgxxService.findById(tankid);// 储罐信息
		List<Map<String, Object>> list = monitorZdwxyCgService.findListByTankid(tankid);// 储罐信息
		model.addAttribute("cg", tank);
		model.addAttribute("list", list);
		return "zxjkyj/zdwxy/cg/viewcgsssjxq";
	}

	/**
	 * 获取储罐温度、压力、液位实时监测详情信息
	 * @param tankid,model
	 */
	@RequestMapping(value = "getcgsssj/{tankid}")
	@ResponseBody
	public String getCgsssj(@PathVariable("tankid") Long tankid, Model model) {
		BIS_TankEntity tank = bisCgxxService.findById(tankid);// 储罐信息
		List<Map<String, Object>> list = monitorZdwxyCgService.getCgSssjByTankid(tankid);// 储罐信息
		return JsonMapper.toJsonString(list);
	}
	
}
