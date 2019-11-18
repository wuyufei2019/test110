/**
 * @ClassName: PageAqjgDSFManageController
 * @Description: 第三方技术服务管理——管理操作
 * @author iDoctor
 * @date 2017年4月18日
 */
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
import com.cczu.model.entity.AQJG_DSFTzzyryEntity;
import com.cczu.model.entity.AQJG_DSFZzEntity;
import com.cczu.model.service.AqjgDSFManageService;
import com.cczu.model.service.AqjgDSFTzzyryService;
import com.cczu.model.service.AqjgDSFZzService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

@Controller
@RequestMapping("dsffw/dsf")
public class PageAqjgDSFManageController extends BaseController {

	@Autowired
	private AqjgDSFManageService manageService;

	@Autowired
	private AqjgDSFZzService zzService;

	@Autowired
	private AqjgDSFTzzyryService tzzyService;

	/**
	 * 字典显示
	 * 
	 * @param {json}
	 */
	@RequestMapping(value = "/jsonlist")
	@ResponseBody
	public String jsonlist(HttpServletRequest request) {
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		AQJG_DSFZzEntity be = zzService.findById(sessionuser.getQyid());
		long id1 = be.getID();
		return JsonMapper.getInstance().toJson(zzService.findByDwID(id1));
	}

	/**
	 * 
	 * @description 列表显示页面
	 * 
	 */
	@RequestMapping(value = "/index")
	public String Index() {

		return "aqjg/dsf/index";
	}

	/**
	 * 
	 * @description 列表显示页面
	 * @param 行数，页数
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Map<String, Object> dataGrid(HttpServletRequest request) {
		Map<String, Object> mapData = getPageMap(request);
		mapData.put("dwname", request.getParameter("manage_M1"));
		mapData.put("dwlx", request.getParameter("manage_M2"));
		return manageService.dataGrid(mapData);
	}

	@RequestMapping(value = "/view/{id}")
	public String View(@PathVariable("id") Integer id, Model model) {
		AQJG_DSFManageEntity manage = manageService.findById(id);
		AQJG_DSFZzEntity zz = zzService.findByDwID(id);
		AQJG_DSFTzzyryEntity tz = tzzyService.findByDwID(id);
		model.addAttribute("manage", manage);
		if (zz != null)
			model.addAttribute("zz", zz);
		if (tz != null)
			model.addAttribute("tz", tz);
		// 返回页面
		model.addAttribute("action", "view");
		return "aqjg/dsf/view";
	}

	/**
	 * 资质list页面
	 * 
	 * @param request
	 */
	@RequestMapping(value = "tablistzz/{dwid}")
	@ResponseBody
	public Map<String, Object> getzzDataTab(@PathVariable("dwid") Integer dwid, HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("dwid", dwid);
		return zzService.dataGrid(map);
	}

	/**
	 * 特种list页面
	 * 
	 * @param request
	 */
	@RequestMapping(value = "tablisttz/{dwid}")
	@ResponseBody
	public Map<String, Object> gettzDataTab(@PathVariable("dwid") Integer dwid, HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("dwid", dwid);
		return tzzyService.dataGrid(map);
	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("dsffw:dsf:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "aqjg/dsf/form";
	}

	/**
	 * 添加单位信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(AQJG_DSFManageEntity bt, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		bt.setS1(t);
		bt.setS2(t);
		bt.setS3(0);
		manageService.addInfo(bt);
		// 返回结果
		return datasuccess;

	}

	/**
	 * 添加资质页面跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "zzcreate/{id}", method = RequestMethod.GET)
	public String createZz(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("ID", id); //第三方单位id
		model.addAttribute("action", "zzcreate");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "aqjg/dsf/zz/form";
	}

	/**
	 * 添加资质信息
	 * 
	 * @param request,model
	 */
	@RequestMapping(value = "zzcreate")
	@ResponseBody
	public String createZz(AQJG_DSFZzEntity bw, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		bw.setS1(t);
		bw.setS2(t);
		bw.setS3(0);
		zzService.addInfo(bw);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 添加特种人员页面跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "tzcreate/{id}", method = RequestMethod.GET)
	public String createTz(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("ID", id); //第三方单位id
		model.addAttribute("action", "tzcreate");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "aqjg/dsf/tzzyry/form";
	}

	/**
	 * 添加特种人员信息
	 * 
	 * @param request,model
	 */
	@RequestMapping(value = "tzcreate")
	@ResponseBody
	public String createTz(AQJG_DSFTzzyryEntity bw, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		bw.setS1(t);
		bw.setS2(t);
		bw.setS3(0);
		tzzyService.addInfo(bw);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id
	 *            ,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		// 查询选择的储罐信息
		AQJG_DSFManageEntity manage = manageService.findById(id);
		model.addAttribute("manage", manage);
		// 返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "aqjg/dsf/form";
	}

	/**
	 * 修改单位信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQJG_DSFManageEntity bt, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		bt.setS2(t);
		manageService.updateInfo(bt);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 修改资质页面跳转
	 * 
	 * @param id,model
	 */
	@RequestMapping(value = "zzupdate/{id}", method = RequestMethod.GET)
	public String updateZz(@PathVariable("id") Integer id, Model model) {
		// 查询选择的第三方服务信息
		AQJG_DSFZzEntity bw = zzService.findById(Long.parseLong(id + ""));
		model.addAttribute("zz", bw);
		// 返回页面
		model.addAttribute("action", "zzupdate");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "aqjg/dsf/zz/form";
	}

	/**
	 * 修改资质信息
	 * 
	 * @param request,model
	 */
	@RequestMapping(value = "zzupdate")
	@ResponseBody
	public String updateZz(AQJG_DSFZzEntity bw, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		bw.setS2(t);
		zzService.updateInfo(bw);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 修改特种页面跳转
	 * 
	 * @param id,model
	 */
	@RequestMapping(value = "tzupdate/{id}", method = RequestMethod.GET)
	public String updateTz(@PathVariable("id") Integer id, Model model) {
		long id1 = id;
		AQJG_DSFTzzyryEntity bw = tzzyService.findById(id1);
		model.addAttribute("tz", bw);
		// 返回页面
		model.addAttribute("action", "tzupdate");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "aqjg/dsf/tzzyry/form";
	}

	/**
	 * 修改特种信息
	 * 
	 * @param request,model
	 */
	@RequestMapping(value = "tzupdate")
	@ResponseBody
	public String updateTz(AQJG_DSFTzzyryEntity bw, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		bw.setS2(t);
		tzzyService.updateInfo(bw);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除单位信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] aids = ids.split(",");
		for (int i = 0; i < aids.length; i++) {
			manageService.deleteInfo(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}

	/**
	 * 删除资质信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequestMapping(value = "zzdelete/{ids}")
	@ResponseBody
	public String deleteZz(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			zzService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 删除特种信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequestMapping(value = "tzdelete/{ids}")
	@ResponseBody
	public String deleteTz(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			tzzyService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 导出excel
	 * 
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dwname", request.getParameter("manage_M1"));
		map.put("dwlx", request.getParameter("manage_M2"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		manageService.exportExcel(response, map);

	}

	/**
	 * 显示所有列
	 * 
	 * @param id
	 *            ,model
	 */
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","dsffw/dsf/export");
		return "common/formexcel";
	}

	/**
	 * 获取单位名称字典
	 * 
	 * @param id,model
	 */
	@RequestMapping(value = "dwnmlist")
	@ResponseBody
	public String getUserJson() {
		List<Map<String, Object>> qynmList = manageService.findDwnmList();

		return JsonMapper.getInstance().toJson(qynmList);
	}
}
