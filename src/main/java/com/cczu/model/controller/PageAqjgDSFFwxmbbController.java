package com.cczu.model.controller;

import java.sql.Timestamp;
import java.text.ParseException;
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

import com.cczu.model.entity.AQJG_DSFManageEntity;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.AQJG_DSFFwxmbbEntity;
import com.cczu.model.service.AQJGDSFFwxmbbService;
import com.cczu.model.service.AqjgDSFManageService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

@Controller
@RequestMapping("dsffw/fwxmbb")
public class PageAqjgDSFFwxmbbController extends BaseController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private AQJGDSFFwxmbbService bbService;
	@Autowired
	private AqjgDSFManageService manageService;
	@Autowired
	private BisQyjbxxServiceImpl qyService;

	/**
	 * 字典显示
	 * 
	 * @param {json}
	 */
	@RequestMapping(value = "/jsonlist")
	@ResponseBody
	public String jsonlist(HttpServletRequest request) {

		return JsonMapper.getInstance().toJson(manageService.findDwnmList());
	}

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = qyService.findInfoById(sessionuser.getQyid());
		if (be != null) {
			return "aqjg/dsffwxmbb/index";
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
			return "aqjg/dsffwxmbb/index_j";
		}
	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequestMapping(value = "aqlist")
	@ResponseBody
	public Map<String, Object> aqgetData(HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);

		if ("0".equals(UserUtil.getCurrentShiroUser().getUsertype())) {
			map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
			ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
				map.put("jglx",sessionuser.getUserroleflg());
		}
		map.put("qyname", request.getParameter("bb_M1"));
		map.put("dwname", request.getParameter("bb_M2"));
		map.put("xmname", request.getParameter("bb_M3"));
		return bbService.dataGridAQ(map);

	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("dsffw:dsfbb:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = qyService.findInfoById(sessionuser.getQyid());

		Map<String, Object> map = getPageMap(request);

		if (be != null) {
			map.put("qyid", be.getID());
		}
		map.put("dwname", request.getParameter("bb_M1"));
		map.put("xmname", request.getParameter("bb_M3"));
		return bbService.dataGrid(map);

	}

	@RequiresPermissions("dsffw:dsfbb:view")
	@RequestMapping(value = "dwnamelist")
	@ResponseBody
	public List<Map<String, Object>> getDwnameList(HttpServletRequest request) {
		return manageService.findDwnmList();
	}

	@RequestMapping(value = "xmnamelist")
	@ResponseBody
	public List<Map<String, Object>> getXmnameList(HttpServletRequest request) {
		return manageService.findXmnmList();
	}
	
	/**
	 * list页面(安监局使用)
	 * 
	 * @param request
	 */
	@RequestMapping(value = "tablist/{qyid}")
	@ResponseBody
	public Map<String, Object> getDataTab(@PathVariable("qyid") Integer qyid, HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		map.put("qyid", qyid);

		return bbService.dataGrid(map);
	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("dsffw:dsfbb:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "aqjg/dsffwxmbb/form";
	}

	/**
	 * 安监局添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("dsffw:dsfbb:add")
	@RequestMapping(value = "create/{id1}", method = RequestMethod.GET)
	public String createaq(@PathVariable("id1") Integer id1, Model model) {
		model.addAttribute("id1", id1);
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "aqjg/dsffwxmbb/form_j";
	}

	/**
	 * 添加第三方服务报备信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("dsffw:dsfbb:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(AQJG_DSFFwxmbbEntity bw, HttpServletRequest request, Model model) {
		String datasuccess = "success";
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		//用户类型(1企业，0安监局，9系统管理员)
		if(sessionuser.getUsertype().equals("1")){
			bw.setID1(sessionuser.getQyid());
		} else if(sessionuser.getUsertype().equals("0")||sessionuser.getUsertype().equals("9")){

		}else{
			return "请先完善企业基本信息";
		}
		Timestamp t = DateUtils.getSysTimestamp();
		bw.setS1(t);
		bw.setS2(t);
		bw.setS3(0);
		bbService.addInfo(bw);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("dsffw:dsfbb:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		// 查询选择的第三方服务报备信息
		AQJG_DSFFwxmbbEntity bw = bbService.findById(id);
		model.addAttribute("bb", bw);
	
		// 返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "aqjg/dsffwxmbb/form";
	}

	/**
	 * 修改第三方服务报备信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("dsffw:dsfbb:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQJG_DSFFwxmbbEntity bw, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();

		bw.setS2(t);
		bbService.updateInfo(bw);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除第三方服务报备信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("dsffw:dsfbb:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			bbService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("dsffw:dsfbb:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, HttpServletRequest request, Model model) {
		// 查询选择的产品信息
		long id1 = id;
		AQJG_DSFFwxmbbEntity bw = bbService.findById(id1);
		model.addAttribute("bb", bw);
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("qyname", qyService.findInfoById(bw.getID1()).getM1());
		AQJG_DSFManageEntity aqjg=manageService.findById(bw.getID2());
		if(aqjg!=null)
			model.addAttribute("dwname", aqjg.getM1());
		// 返回页面
		model.addAttribute("action", "view");
		return "aqjg/dsffwxmbb/view";
	}

	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("dsffw:dsfbb:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		if ("1".equals(UserUtil.getCurrentShiroUser().getUsertype())) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			map.put("dwname", request.getParameter("bb_M1"));
		} else if ("0".equals(UserUtil.getCurrentShiroUser().getUsertype())) {
			map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
			ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
				map.put("jglx",sessionuser.getUserroleflg());
			map.put("qyname", request.getParameter("bb_M1"));
			map.put("dwname", request.getParameter("bb_M2"));
		} else {
			map.put("qyname", request.getParameter("bb_M1"));
			map.put("dwname", request.getParameter("bb_M2"));
		}
		map.put("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		bbService.exportExcel(response, map);
	}

	/**
	 * 显示所有列
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("dsffw:dsfbb:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","dsffw/fwxmbb/export");
		return "common/formexcel";
	}
}
