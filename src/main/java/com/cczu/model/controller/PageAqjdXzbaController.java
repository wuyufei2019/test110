package com.cczu.model.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.cczu.model.entity.AQJD_BeiAnEntity;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IAqjdXzbaService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 安全监督管理-行政备案信息controller
 * 
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("aqjd/xzba")
public class PageAqjdXzbaController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private IAqjdXzbaService aqjdXzbaService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		if (be != null) {
			return "model/aqjd/xzba/index_q";
		} else {
			// 获取用户角色
			List<Role> list = roleService.findRoleById(sessionuser.getId());
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					// 判断用户角色，如果包含有企业用户角色，则转到企业用户显示的界面
					if (list.get(i).getRoleCode().equals("company") || list.get(i).getRoleCode().equals("companyadmin"))
						return "../error/001";
				}
			}
			return "model/aqjd/xzba/index";
		}

	}

	/**
	 * list页面（企业用户显示界面）
	 * 
	 * @param request
	 */
	@RequiresPermissions("aqjd:xzba:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());

		Map<String, Object> map = getPageMap(request);
		if (be != null) {
			map.put("qyid", be.getID());
		}
		map.put("baname", request.getParameter("aqjd_xzba_cx_name"));

		return aqjdXzbaService.dataGrid(map);

	}

	/**
	 * list页面（非企业用户显示界面）
	 * 
	 * @param request
	 */
	@RequiresPermissions("aqjd:xzba:view")
	@RequestMapping(value = "list2")
	@ResponseBody
	public Map<String, Object> getData2(HttpServletRequest request) {
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();

		Map<String, Object> map = getPageMap(request);
		map.put("baname", request.getParameter("aqjd_xzba_cx_name"));

		if (!sessionuser.getXzqy().equals("0"))
			map.put("xzqy", sessionuser.getXzqy());
		return aqjdXzbaService.dataGrid2(map);

	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("aqjd:xzba:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "model/aqjd/xzba/form";
	}

	/**
	 * 添加/修改信息
	 * 
	 * @param request,model
	 * @throws ParseException
	 */
	@RequiresPermissions("aqjd:xzba:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(AQJD_BeiAnEntity aqjd) throws ParseException {
		String datasuccess = "保存成功！";
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		if (be != null) {
			Timestamp t = DateUtils.getSysTimestamp();
			aqjd.setS1(t);
			aqjd.setS2(t);
			aqjd.setS3(0);
			// 添加
			aqjdXzbaService.addInfo(aqjd);
			datasuccess = "保存成功！";
		}
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("aqjd:xzba:update")
	@RequestMapping(value = "update/{id}")
	public String update(@PathVariable("id") Long id, Model model) {
		// 查询选择的车间信息
		AQJD_BeiAnEntity aqjd = aqjdXzbaService.findInfoById(id);
		model.addAttribute("aqjd", aqjd);
		// 返回页面
		model.addAttribute("action", "update");
		return "model/aqjd/xzba/form";
	}

	/**
	 * 修改
	 * 
	 */
	@RequiresPermissions("aqjd:xzba:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQJD_BeiAnEntity aqjd, Model model) {
		String datasuccess = "更新成功";
		Timestamp t = DateUtils.getSysTimestamp();
		aqjd.setS2(t);
		// 更新操作
		aqjdXzbaService.updateInfo(aqjd);
		datasuccess = "更新成功！";
		// 返回结果
		return datasuccess;
	}

	/**
	 * 查看行政备案信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("aqjd:xzba:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		// 查询选择的行政备案信息
		AQJD_BeiAnEntity aqjd = aqjdXzbaService.findInfoById(id);
		model.addAttribute("aqjd", aqjd);
		// 返回页面
		model.addAttribute("action", "view");
		return "model/aqjd/xzba/view";
	}

	/**
	 * 删除行政备案信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("aqjd:xzba:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (String id : arrids) {
			aqjdXzbaService.deleteInfo(Long.parseLong(id));
		}
		return datasuccess;
	}

	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("aqjd:xzba:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		if ("1".equals(UserUtil.getCurrentShiroUser().getUsertype())) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			map.put("baname", request.getParameter("excelcon1"));
		} else if ("0".equals(UserUtil.getCurrentShiroUser().getUsertype())) {
			map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
			map.put("baname", request.getParameter("excelcon1"));
		} else {
			map.put("baname", request.getParameter("excelcon1"));
		}
		map.put("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		aqjdXzbaService.exportExcel(response, map);

	}

	/**
	 * 显示所有列
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("aqjd:xzba:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		return "model/common/formexcel";
	}

	/**
	 * 行政备案到期提醒
	 * 
	 * @return
	 * @throws ParseException
	 */
	@RequiresPermissions("aqjd:xzba:view")
	@RequestMapping(value = "xzbaTimeEnd", method = RequestMethod.GET)
	@ResponseBody
	public String xzbaTimeEnd() throws ParseException {
		String datasuccess = "null";

		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.CHINA);
		String dqtime = format.format(date);
		java.text.SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date1 = formatter.parse(dqtime);

		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());

		if (be != null) {// 假如be不为空，则是企业
			List<AQJD_BeiAnEntity> list = aqjdXzbaService.findAllqy(be.getID());
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getM3().getTime() < date1.getTime()) {
					datasuccess = "部分行政备案有效期以过期，请注意查看！";
					break;
				}
			}
		} else {// 安监局查看是否有过期
			List<AQJD_BeiAnEntity> list = aqjdXzbaService.findAllaj();
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getM3().getTime() < date1.getTime()) {
					datasuccess = "部分企业中行政备案有效期以过期，请注意查看！";
					break;
				}
			}
		}

		return datasuccess;
	}

}
