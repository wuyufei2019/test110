package com.cczu.model.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import com.cczu.model.entity.AQJD_CheckRecordEntity;
import com.cczu.model.service.IAqjgCheckPlanService;
import com.cczu.model.service.IAqjgCheckRecordService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

/**
 * 安全文件信息发布controller
 * 
 * @author jason
 * 
 */
@Controller
@RequestMapping("aqjd/jcjl")
public class PageCheckRecordController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private IAqjgCheckRecordService aqjgcheckrecordservice;
	@Autowired
	private IAqjgCheckPlanService aqjgcheckplanservice;


	/**
	 * 默认页面
	 */
	@RequestMapping(value = "index")
	public String index(Model model,HttpServletRequest request) {
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		// 获取用户角色
		List<Role> list = roleService.findRoleById(sessionuser.getId());
		if(request.getParameter("sys")!=null &&"1".equals(request.getParameter("sys"))){
			model.addAttribute("sys", "sys");
			if(request.getParameter("mflag")!=null &&(request.getParameter("mflag"))!="")
				model.addAttribute("jcjd", request.getParameter("mflag"));
		}
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				// 判断用户角色，如果包含有企业用户角色，则转到企业用户显示的界面
				if (list.get(i).getRoleCode().equals("company")
						|| list.get(i).getRoleCode().equals("companyadmin"))
					return "aqjg/aqjdjc/jcjl/index_qy";
			}
		}
		return "aqjg/aqjdjc/jcjl/index";
	}

	/**
	 * 企业list页面
	 */
	@RequiresPermissions("aqjd:jcjl:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("name", request.getParameter("aqjg_jcjh_name"));
		map.put("year", request.getParameter("aqjg_jcjh_year"));
		map.put("userid", UserUtil.getCurrentShiroUser().getId());
		map.put("date", request.getParameter("aqjg_jcjj_date"));
		map.put("flag", request.getParameter("aqjg_jcjj_checkflag"));
		return aqjgcheckrecordservice.dataGrid(map);

	}


	/**
	 * 添加企业初检信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("aqjd:jcjl:add")
	@RequestMapping(value = "create")
	public String create(Model model) {
		model.addAttribute("action", "createSub");
		return "aqjg/aqjdjc/jcjl/form";
	}
	/**
	 * 添加企业初检信息
	 * 
	 * @param request
	 *  ,model
	 */
	@RequiresPermissions("aqjd:jcjl:add")
	@RequestMapping(value = "createSub", method = RequestMethod.POST)
	@ResponseBody
	public String createSub(AQJD_CheckRecordEntity cre,
			HttpServletRequest request, Model model) throws IOException {
		String datasuccess = "success";
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		Timestamp t = DateUtils.getSysTimestamp();
		cre.setCheckflag("0");
		cre.setS1(t);
		cre.setS2(t);
		// s3删除标识
		cre.setS3(0);
		cre.setID2(sessionuser.getQyid());//设置企业id，在安监局端显示企业名字
		cre.setID3(sessionuser.getId());//设置用户id，不同用户显示对应的记录
	    aqjgcheckrecordservice.addInfoReturnID(cre);
		// 返回结果
		return datasuccess;
	}
	
	/**
	 * 企业修改记录信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("aqjd:jcjl:update")
	@RequestMapping(value = "update/{id}")
	public String update(@PathVariable("id") long id, Model model) {
		AQJD_CheckRecordEntity cre = aqjgcheckrecordservice.findInfoById(id);
		model.addAttribute("cre", cre);
		model.addAttribute("action", "updateSub");
		return "aqjg/aqjdjc/jcjl/form";
	}
	

	/**
	 * 企业修改记录信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("aqjd:jcjl:update")
	@RequestMapping(value = "updateSub")
	@ResponseBody
	public String updateSub(AQJD_CheckRecordEntity cre,
		HttpServletRequest request, Model model) {
		String datasuccess = "success";
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		Timestamp t = DateUtils.getSysTimestamp();
		cre.setS2(t);
		cre.setID2(sessionuser.getQyid());//设置企业id，在安监局端显示企业名字
		cre.setID3(sessionuser.getId());//设置用户id，不同用户显示对应的记录
		cre.setCheckflag("0");
		// 更新发布文件
		try {
			aqjgcheckrecordservice.updateInfo(cre);
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="failed";
		}
		return datasuccess;
	}

	/**
	 * 企业复检记录页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("aqjd:jcjl:add")
	@RequestMapping(value = "createfj/{id}")
	public String createfj(@PathVariable("id") long id,Model model) {
		AQJD_CheckRecordEntity cre = aqjgcheckrecordservice.findInfoById(id);
		model.addAttribute("cre", cre);
		model.addAttribute("action", "createfjSub");
		return "aqjg/aqjdjc/jcjl/fjform";
	}
	
	/**
	 * 添加企业复检记录信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("aqjd:jcjl:update")
	@RequestMapping(value = "createfjSub")
	@ResponseBody
	public String AddFjInfo(AQJD_CheckRecordEntity cre,
			HttpServletRequest request, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		cre.setS2(t);
		// 更新发布文件
		try {
			aqjgcheckrecordservice.updateInfo(cre);
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="failed";
		}
		return datasuccess;
	}
	
	/**
	 * 查看记录信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("aqjd:jcjl:view")
	@RequestMapping(value = "view/{id}")
	public String view(@PathVariable("id") long id, Model model) {
		Map<String,Object> cre = aqjgcheckrecordservice.findInfoById2(id);
		model.addAttribute("cre", cre);
		model.addAttribute("action", "view");
		return "aqjg/aqjdjc/jcjl/view";
	}
	/**
	 * list页面(安监局首页使用)
	 * @param request
	 */
	@RequestMapping(value="tablist/{qyid}")
	@ResponseBody
	public Map<String, Object> getDataTab(@PathVariable("qyid") long qyid, HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyid", qyid);
		return aqjgcheckrecordservice.dataGrid2(map);
		
	}
	
	/**
	 * 安监list页面
	 */
	@RequiresPermissions("aqjd:jcjl:view")
	@RequestMapping(value = "list2")
	@ResponseBody
	public Map<String, Object> getData2(HttpServletRequest request) {
		ShiroUser user= UserUtil.getCurrentShiroUser();
		Map<String, Object> map = getPageMap(request);
		map.put("name", request.getParameter("aqjg_jcjh_name"));
		map.put("year", request.getParameter("aqjg_jcjh_year"));
		map.put("date", request.getParameter("aqjg_jcjj_date"));
		map.put("qyname", request.getParameter("aqjg_jcjj_qyname"));
		map.put("flag", request.getParameter("aqjg_jcjj_checkflag"));
		map.put("xzqy", user.getXzqy());
		//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
		if(user.getUserroleflg()!=null&&user.getUserroleflg()!=0)
			map.put("jglx",user.getUserroleflg());
		//map.put("sort", request.getParameter("aqjg_jcjh_year"));
		return aqjgcheckrecordservice.dataGrid2(map);

	}
	
	/**
	 * 导出企业记录word
	 * 
	 */
	@RequiresPermissions("aqjd:jcjl:export")
	@RequestMapping(value = "exportqy/{id}")
	@ResponseBody
	public String getQyWord(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> cre = aqjgcheckrecordservice.findInfoById2(id);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("mc", cre.get("mc")==null?"":cre.get("mc"));
		map.put("m2", cre.get("m2")==null?"":cre.get("m2").toString().substring(0, 10));
		map.put("m3", cre.get("m3")==null?"":cre.get("m3"));
		map.put("m4", cre.get("m4")==null?"":cre.get("m4"));
		map.put("m5", cre.get("m5")==null?"":cre.get("m5"));
		map.put("m6", cre.get("m6")==null?"":cre.get("m6").toString().substring(0, 10));
		map.put("m9", cre.get("m9")==null?"":cre.get("m9"));
		map.put("m10", cre.get("m10")==null?"":cre.get("m10").toString().substring(0, 10));
		map.put("m11", cre.get("m11")==null?"":cre.get("m11"));
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "qyrecord.ftl", filePath, filename, request);
		return "/download/" + filename;
	}


	/**
	 * 删除记录信息
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("aqjd:jcjl:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			aqjgcheckrecordservice.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 企业新增检查记录时获取专项检查名称
	 * @param id,model
	 */
	@RequestMapping(value = "addcheckplanlist/{action}")
	@ResponseBody
	public String getAddPlanJson(@PathVariable("action") String action) {
		List<Map<String, Object>> checkplanlist=new ArrayList<Map<String, Object>>();  
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if("createSub".equals(action)){
			checkplanlist=aqjgcheckplanservice.findCheckPlanList(sessionuser.getQyid().toString());
		}else{
			checkplanlist=aqjgcheckplanservice.findCheckPlanList("");
		}
		return JsonMapper.getInstance().toJson(checkplanlist);
	}
	
	/**
	 * 获取专项检查名称
	 * @param id,model
	 */
	@RequestMapping(value = "checkplanlist")
	@ResponseBody
	public String getPlanJson() {
		List<Map<String, Object>> checkplanlist=new ArrayList<Map<String, Object>>();  
		checkplanlist=aqjgcheckplanservice.findCheckPlanList("");
		return JsonMapper.getInstance().toJson(checkplanlist);
	}

	
	
}
