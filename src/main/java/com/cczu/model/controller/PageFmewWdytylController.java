package com.cczu.model.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.IFmewWdytylService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;


/**
 * 实时数据监测controller
 * @author jason
 *
 */
@Controller
@RequestMapping("fmew/wdytyl")
public class PageFmewWdytylController extends BaseController {

	@Autowired
	private IFmewWdytylService iFmewWdytylService;
	@Autowired
	private IBisQyjbxxService qyjbxxService;

	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		/*企业列表tab页
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		List<BIS_EnterpriseEntity> list=iFmewWdytylService.findQYInforInfoByXzqy(sessionuser.getXzqy());
		List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();
		for(BIS_EnterpriseEntity en:list){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", en.getID());
			map.put("qyname", en.getM1());
			result.add(map);
		}
		model.addAttribute("qylist", result);
		*/
		return "model/fmew/wdytyl/index";
	}

	
	
	/**
	 * list页面
	 */
	@RequiresPermissions("fmew:wd:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("fmew_wdytyl_cx_m1"));
		if(!sessionuser.getXzqy().equals("0"))
			map.put("xzqy",sessionuser.getXzqy());
		return iFmewWdytylService.dataGrid(map);
	}
		
 
	/**
	 * 查看企业物料储存实时储量
	 * @param id,model
	 */
	@RequiresPermissions("fmew:wd:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		List<Map<String, Object>> cllist=iFmewWdytylService.findInfoById(id);
		List<Map<String, Object>> cklist=iFmewWdytylService.findCangKuInfoByQyid(id);
		BIS_EnterpriseEntity ent= qyjbxxService.findInfoById(id);
		model.addAttribute("cllist", cllist);
		model.addAttribute("cklist", cklist);
		model.addAttribute("qiye", ent);
		return "model/fmew/wdytyl/view";
	}
	
	
	/**
	 * 导出实时数据选择企业页面
	 */
	@RequestMapping(value="selectqy")
	public String showqyselect(Model model) {
		return "model/fmew/wdytyl/qylist";
	}
	
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("fmew:wd:view")
	@RequestMapping(value = "export/{ids}")
	@ResponseBody
	public void export(@PathVariable("ids") String ids, HttpServletResponse response) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyids", ids);
		iFmewWdytylService.exportExcel(response, map);
		
	}
	
}
