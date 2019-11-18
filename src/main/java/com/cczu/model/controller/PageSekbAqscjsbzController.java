package com.cczu.model.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

import com.cczu.model.entity.TMESK_TechnologystandardEntity;
import com.cczu.model.service.ISekbAqscjsbzService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

/**
 * 安全生产技术标准信息controller
 * 
 * @author jason
 * @date 2017年7月1日
 */
@Controller
@RequestMapping("sekb/aqscjsbz")
public class PageSekbAqscjsbzController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private ISekbAqscjsbzService sekbAqscjsbzService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {

		return "sekb/aqscjsbz/index";
	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("sekb:aqscjsbz:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		map.put("btname", request.getParameter("sekb_aqscjsbz_bt_name"));
		map.putAll(getAuthorityMap());
		
		return sekbAqscjsbzService.dataGrid(map);

	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("sekb:aqscjsbz:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "sekb/aqscjsbz/form";
	}

	/**
	 * 添加安全生产技术标准信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("sekb:aqscjsbz:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(TMESK_TechnologystandardEntity tm, HttpServletRequest request) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		tm.setS1(t);
		tm.setS2(t);
		tm.setS3(0);
		tm.setID1(UserUtil.getCurrentUser().getId());

		String filePath = request.getSession().getServletContext().getRealPath("/");
		sekbAqscjsbzService.addInfo(tm,filePath);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("sekb:aqscjsbz:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		// 查询选择的安全生产技术标准信息
		long id1 = id;
		TMESK_TechnologystandardEntity tm = sekbAqscjsbzService.findById(id1);
		model.addAttribute("res", tm);
		// 返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "sekb/aqscjsbz/form";
	}

	/**
	 * 修改安全生产技术标准信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("sekb:aqscjsbz:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(TMESK_TechnologystandardEntity ee, HttpServletRequest request) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		ee.setS2(t);
		ee.setID1(UserUtil.getCurrentUser().getId());
		
		String filePath = request.getSession().getServletContext().getRealPath("/");
		sekbAqscjsbzService.updateInfo(ee,filePath);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除安全生产技术标准信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("sekb:aqscjsbz:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			sekbAqscjsbzService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("sekb:aqscjsbz:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		//查询选择的产品信息
		long id1 = id;
		TMESK_TechnologystandardEntity ee = sekbAqscjsbzService.findById(id1);
		model.addAttribute("res", ee);
		//返回页面
		model.addAttribute("action", "view");
		return "sekb/aqscjsbz/view";
	}
	
	/**
	 * 导出word
	 * 
	 */
	@RequiresPermissions("sekb:aqscjsbz:excel")
	@RequestMapping(value = "export/{id}")
	@ResponseBody
	public String getWord(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		TMESK_TechnologystandardEntity tm = sekbAqscjsbzService.findById(id);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("title", tm.getM1());
		map.put("content", tm.getM2());
		//当值为null时，导出的word内容不正确，设置默认值
		if(tm.getM3()==null){
		map.put("remark","");	
		}else{
		map.put("remark", tm.getM3());
		}
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "/download/";
		WordUtil.ireportWord(map, "aqjs.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
}
