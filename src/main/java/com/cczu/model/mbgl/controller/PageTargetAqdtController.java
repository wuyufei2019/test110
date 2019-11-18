package com.cczu.model.mbgl.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.mbgl.entity.Target_SecurityFileRelease;
import com.cczu.model.mbgl.service.TargetAqdtService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 目标管理-安全文化-安全动态controller
 * 
 * @author jason
 * 
 */
@Controller
@RequestMapping("target/aqdt")
public class PageTargetAqdtController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private TargetAqdtService targetAqdtService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;

	/**
	 * 默认页面
	 */
	@RequestMapping(value = "index")
	public String index(Model model, HttpServletRequest request) {
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		model.addAttribute("qyid", request.getParameter("qyid"));
		if ("1".equals(sessionuser.getUsertype()) || "9".equals(sessionuser.getUsertype())) {// 企业
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(UserUtil.getCurrentShiroUser().getQyid());
			if (be.getIsbloc() != null && be.getIsbloc() == 1) {
				model.addAttribute("usertype", "isbloc");
			}
			return "targetmanger/safetyculture/aqdt/index";
		} else {
			return "targetmanger/safetyculture/aqdt/index";
		}
	}

	/**
	 * list页面
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		Map<String, Object> map = getPageMap(request);

		//安监端条件
		String qyid = request.getParameter("qyid");
		if(StringUtils.isNotEmpty(qyid))
			map.put("qyid", qyid);
		else {
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if (be.getIsbloc() != null && be.getIsbloc() == 1) {// 集团公司
				map.put("fid", sessionuser.getQyid());
				map.put("qyname", request.getParameter("list_qyname"));
			} else {
				map.put("qyid", sessionuser.getQyid());
			}
		}
		map.put("name", request.getParameter("list_m1"));
		map.put("datestart", request.getParameter("list_starttime"));
		map.put("dateend", request.getParameter("list_endtime"));
		return targetAqdtService.dataGrid(map);
	}

	/**
	 * 添加安全动态信息页面跳转
	 * @param model
	 */
	@RequiresPermissions("target:aqdt:add")
	@RequestMapping(value = "create")
	public String create(Model model) {
		model.addAttribute("action", "createSub");
		return "targetmanger/safetyculture/aqdt/form";
	}

	/**
	 * 添加安全动态信息
	 * 
	 * @param request
	 *            model
	 */
	@RequiresPermissions("target:aqdt:add")
	@RequestMapping(value = "createSub", method = RequestMethod.POST)
	@ResponseBody
	public String createSub(Target_SecurityFileRelease entity, HttpServletRequest request, Model model)
			throws IOException {
		String datasuccess = "success";
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		// String paths=UPload(request, model);//动态保存路径
		Timestamp t = DateUtils.getSysTimestamp();
		entity.setID2(sessionuser.getQyid());// D1用户id
		entity.setID1(sessionuser.getId());// D1用户id
		entity.setS1(t);// s1发布时间
		entity.setS2(t);
		entity.setS3(0);// s3删除标识
		entity.setM2(request.getParameter("M2"));
		String filePath = request.getSession().getServletContext().getRealPath("/");
		targetAqdtService.addInforReturnID(entity, filePath);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 修改安全动态信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("target:aqdt:update")
	@RequestMapping(value = "update/{id}")
	public String update(@PathVariable("id") long id, Model model) {

		Target_SecurityFileRelease entity = targetAqdtService.findInfoById(id);
		model.addAttribute("entity", entity);
		model.addAttribute("action", "updateSub");
		return "targetmanger/safetyculture/aqdt/form";
	}

	/**
	 * 修改安全动态信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("target:aqdt:update")
	@RequestMapping(value = "updateSub")
	@ResponseBody
	public String updateSub(Target_SecurityFileRelease entity, HttpServletRequest request, Model model) {
		String datasuccess = "failed";
		Timestamp t = DateUtils.getSysTimestamp();
		entity.setS2(t);
		entity.setM2(request.getParameter("M2"));
		// 更新发布动态
		targetAqdtService.updateInfoByID(entity.getID(), entity, request.getSession().getServletContext().getRealPath("/"));
		datasuccess = "success";
		return datasuccess;
	}

	/**
	 * 查看安全动态信息
	 * 
	 * @param model
	 */
	@RequestMapping(value = "view/{id}")
	public String view(@PathVariable("id") long id, Model model) {
		Target_SecurityFileRelease entity = targetAqdtService.findInfoById(id);
		model.addAttribute("entity", entity);
		model.addAttribute("action", "view");
		//更新entity浏览次数字段
		targetAqdtService.updateViewCount(id);
		return "targetmanger/safetyculture/aqdt/view";
	}

	/**
	 * 删除安全动态信息
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("target:aqdt:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			// 删除安全动态信息
			targetAqdtService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 查询安全动态信息
	 * 
	 * @param model
	 */
	@RequestMapping(value = "search/{id}")
	public String search(@PathVariable("id") long id, Model model) {

		Target_SecurityFileRelease entity = targetAqdtService.findInfoById(id);
		model.addAttribute("entity", entity);
		model.addAttribute("action", "view");
		return "targetmanger/safetyculture/aqdt/form";
	}
	/**
	 * 查询前四条安全动态信息
	 * 
	 * @param model
	 */
	@RequestMapping(value = "topfour")
	@ResponseBody
	public String searchTop4(Model model) {
		Map<String,Object> map=new HashMap<>();
		map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		List<Target_SecurityFileRelease> list = targetAqdtService.findTop4(map);
		return JsonMapper.getInstance().toJson(list);
	}

}
