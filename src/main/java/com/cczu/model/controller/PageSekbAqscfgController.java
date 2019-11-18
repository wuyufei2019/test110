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

import com.cczu.model.entity.TMESK_LawsEntity;
import com.cczu.model.service.ISekbAqscfgService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

/**
 * 安全生产法律法规信息controller
 * 
 * @author jason
 * @date 2017年7月1日
 */
@Controller
@RequestMapping("sekb/aqscfg")
public class PageSekbAqscfgController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private ISekbAqscfgService sekbAqscfgService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {

		return "sekb/aqscfg/index";
	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("sekb:aqscfg:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		map.put("type", "2");//法规
		map.put("btname", request.getParameter("sekb_aqscfg_bt_name"));
		map.putAll(getAuthorityMap());
		return sekbAqscfgService.dataGrid(map);

	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("sekb:aqscfg:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "sekb/aqscfg/form";
	}

	/**
	 * 添加安全生产法律法规信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("sekb:aqscfg:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(TMESK_LawsEntity tm, HttpServletRequest request) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		tm.setS1(t);
		tm.setS2(t);
		tm.setS3(0);
		tm.setID1(UserUtil.getCurrentUser().getId());
		
		String filePath = request.getSession().getServletContext().getRealPath("/");
		sekbAqscfgService.addInfo(tm,filePath);
		sekbAqscfgService.addQrcode(tm);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("sekb:aqscfg:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		// 查询选择的安全生产法律法规信息
		long id1 = id;
		TMESK_LawsEntity tm = sekbAqscfgService.findById(id1);
		model.addAttribute("res", tm);
		// 返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "sekb/aqscfg/form";
	}

	/**
	 * 修改安全生产法律法规信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("sekb:aqscfg:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(TMESK_LawsEntity tm, HttpServletRequest request) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		tm.setS2(t);
		tm.setID1(UserUtil.getCurrentUser().getId());
		
		String filePath = request.getSession().getServletContext().getRealPath("/");
		sekbAqscfgService.updateInfo(tm,filePath);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除安全生产法律法规信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("sekb:aqscfg:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			sekbAqscfgService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("sekb:aqscfg:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		//查询选择的产品信息
		long id1 = id;
		TMESK_LawsEntity ee = sekbAqscfgService.findById(id1);
		model.addAttribute("res", ee);
		//返回页面
		model.addAttribute("action", "view");
		return "sekb/aqscfg/view";
	}
	
	/**
	 * 导出word
	 * 
	 */
	@RequiresPermissions("sekb:aqscfg:excel")
	@RequestMapping(value = "export/{id}")
	@ResponseBody
	public String getWord(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		TMESK_LawsEntity tm = sekbAqscfgService.findById(id);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("title", tm.getM2());
		map.put("content", tm.getM3());
		map.put("remark", tm.getM4());

		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "/download/";
		WordUtil.ireportWord(map, "aqfg.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
}
