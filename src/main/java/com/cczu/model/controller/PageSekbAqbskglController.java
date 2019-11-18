package com.cczu.model.controller;

import java.sql.Timestamp;
import java.text.ParseException;
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

import com.cczu.model.entity.TMESK_AqbskglEntity;
import com.cczu.model.service.ISekbAqbskglService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 安全标示信息controller
 * 
 * @author jason
 * @date 2017年7月1日
 */
@Controller
@RequestMapping("sekb/aqbskgl")
public class PageSekbAqbskglController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private ISekbAqbskglService sekbAqbskglService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {

		return "sekb/aqbskgl/index";
	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("sekb:aqbskgl:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		map.put("btname", request.getParameter("sekb_aqbskgl_bt_name"));
		map.put("aqbskgllb", request.getParameter("aqbskgllb"));

		return sekbAqbskglService.dataGrid(map);

	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("sekb:aqbskgl:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "sekb/aqbskgl/form";
	}

	/**
	 * 添加安全标示
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("sekb:aqbskgl:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(TMESK_AqbskglEntity tm, HttpServletRequest request) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		tm.setS1(t);
		tm.setS2(t);
		tm.setS3(0);
		//因为用对象获取富文本会被转义，所以单独用request获取
		/*tm.setM3(request.getParameter("M3"));*/
		tm.setID1(UserUtil.getCurrentUser().getId());

		sekbAqbskglService.addInfo(tm);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("sekb:aqbskgl:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		// 查询选择的事故案例信息
		long id1 = id;
		TMESK_AqbskglEntity tm = sekbAqbskglService.findById(id1);
		model.addAttribute("res", tm);
		// 返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "sekb/aqbskgl/form";
	}

	/**
	 * 修改安全标示库管理
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("sekb:aqbskgl:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(TMESK_AqbskglEntity tm, HttpServletRequest request) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		tm.setS2(t);
		//因为用对象获取富文本会被转义，所以单独用request获取
		/*tm.setM3(request.getParameter("M3"));*/
		tm.setID1(UserUtil.getCurrentUser().getId());
		sekbAqbskglService.updateInfo(tm);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除安全标示
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("sekb:aqbskgl:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			sekbAqbskglService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

}
