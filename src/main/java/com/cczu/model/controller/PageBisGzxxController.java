package com.cczu.model.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_JobPostEntity;
import com.cczu.model.service.BisGzxxService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 一企一档---工种、岗位信息controller
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("bis/gzxx")
public class PageBisGzxxController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private BisGzxxService bisGzxxService;
	@Autowired
	private BisQyjbxxServiceImpl qyService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;

	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("0")){
			return "../error/403";
		}else {
			return "qyxx/gzxx/index";
		}	
	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("bis:gzxx:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype()))
		map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		return bisGzxxService.dataGrid(map);
	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("bis:gzxx:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "qyxx/gzxx/form";

	}

	/**
	 * 添加
	 * 
	 * @param request,model
	 * @throws ParseException
	 */
	@RequiresPermissions("bis:gzxx:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(BIS_JobPostEntity entity, Model model) {
		String datasuccess = "success";
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		entity.setID1(sessionuser.getQyid());
		int result=bisGzxxService.addInfo(entity);
		if(result==0)
			datasuccess = "error";
		return datasuccess;

	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("bis:gzxx:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") long id, Model model) {
		BIS_JobPostEntity entity = bisGzxxService.findById(id);
		model.addAttribute("entity", entity);
		model.addAttribute("action", "update");
		return "qyxx/gzxx/form";
	}

	/**
	 * 修改
	 * 
	 * @param request,model
	 * @throws ParseException
	 */
	@RequiresPermissions("bis:gzxx:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(BIS_JobPostEntity entity, Model model) {
		String datasuccess="success";		
 		bisGzxxService.updateInfo(entity);
		//返回结果
		return datasuccess;
	}

	/**
	 * 删除
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("bis:gzxx:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		String[] aids = ids.split(",");
		for (int i = 0; i < aids.length; i++) {
			bisGzxxService.deleteInfo(Long.parseLong(aids[i]));
		}

		return datasuccess;
	}
	
	/**
	 * 
	 * @param id
	 *  ,model
	 */
	@RequestMapping(value = "textjson", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>>getJobTextJson() {
		Map<String, Object> mapdata = new HashMap<String, Object>();
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype()))
			mapdata.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		List<Map<String, Object>> list = bisGzxxService.getJobtextJson(mapdata);
		return list;
	}

}
