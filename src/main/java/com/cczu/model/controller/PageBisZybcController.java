package com.cczu.model.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
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
import com.cczu.model.entity.BIS_WorkorderEntity;
import com.cczu.model.service.BisZybcService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 作业班次controller
 * 
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("bis/zybc")
public class PageBisZybcController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private BisZybcService bisZybcService;
	@Autowired
	private BisQyjbxxServiceImpl qyService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					return "qyxx/zybc/ajindex";
				else
					return "qyxx/zybc/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "qyxx/zybc/ajindex";
		}	
	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequestMapping(value = "ajlist")
	@ResponseBody
	public Map<String, Object> ajgetData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("bis_zybc_cx_qyname"));
		map.put("m1", request.getParameter("bis_zybc_m1"));
		map.putAll(getAuthorityMap());
		return bisZybcService.dataGrid2(map);

	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("bis:zybc:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		//获取企业id
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		
		Map<String, Object> map = getPageMap(request);
		if(be!=null){
			map.put("qyid", be.getID());
		}
		map.put("m1", request.getParameter("bis_zybc_m1"));
		return bisZybcService.dataGrid(map);
	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequestMapping(value = "tablist/{qyid}")
	@ResponseBody
	public Map<String, Object> getDataTab(@PathVariable("qyid") Integer qyid, HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		map.put("qyid", qyid);

		return bisZybcService.dataGrid(map);
	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("bis:zybc:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("action", "create");
		return "qyxx/zybc/form";

	}

	/**
	 * 添加作业班次
	 * 
	 * @param request,model
	 * @throws ParseException
	 */
	@RequiresPermissions("bis:zybc:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(BIS_WorkorderEntity entity, Model model) {
		String datasuccess = "success";

		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){
			entity.setID1(sessionuser.getQyid());
		}
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		bisZybcService.addInfo(entity);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--作业班次信息  【增加操作】");

		return datasuccess;

	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("bis:zybc:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		// 查询选择的作业班次
		long id1 = id;
		BIS_WorkorderEntity bw = bisZybcService.findById(id1);
		model.addAttribute("zybclist", bw);
		// 返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "qyxx/zybc/form";
	}

	/**
	 * 修改作业班次
	 * 
	 * @param request,model
	 * @throws ParseException
	 */
	@RequiresPermissions("bis:zybc:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(BIS_WorkorderEntity entity, Model model) {
		String datasuccess="success";		
 		entity.setS1(new java.sql.Timestamp(new java.util.Date().getTime()));
		entity.setS2(new java.sql.Timestamp(new java.util.Date().getTime()));
		entity.setS3(0);
 		bisZybcService.updateInfo(entity);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--作业班次信息  【修改操作】");

		//返回结果
		return datasuccess;
	}

	/**
	 * 删除作业班次
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("bis:zybc:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] aids = ids.split(",");
		for (int i = 0; i < aids.length; i++) {
			bisZybcService.deleteInfo(Long.parseLong(aids[i]));
		}
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--作业班次信息  【删除操作】");

		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("bis:qyjbxx:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		// 查询选择的产品信息
		long id1 = id;
		BIS_WorkorderEntity bw = bisZybcService.findById(id1);
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("qyname", qyService.findInfoById(bw.getID1()).getM1());
		model.addAttribute("zybclist", bw);
		// 返回页面
		model.addAttribute("action", "view");
		return "qyxx/zybc/view";
	}

	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("bis:zybc:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
	
		map.put("qyname", request.getParameter("bis_zybc_cx_qyname"));
		map.put("m1", request.getParameter("bis_zybc_m1"));
		map.putAll(getAuthorityMap());
		bisZybcService.exportExcel(response, map);

	}

	/**
	 * 显示所有列
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("bis:zybc:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","bis/zybc/export");
		return "common/formexcel";
	}

	//导入页面跳转
	@RequestMapping(value = "exinjump", method = RequestMethod.GET)
	public String exin(Model model) {
		model.addAttribute("action", "exin");
		return "common/importForm";
	}
	
	/**
	 * 导入
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequiresPermissions("bis:zybc:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			resultmap = bisZybcService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}
}
