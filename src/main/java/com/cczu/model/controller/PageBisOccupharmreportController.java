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
import com.cczu.model.entity.BIS_OccupharmExamReportEntity;
import com.cczu.model.service.IBisOccupharmreportService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 检测报告管理controller
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("bis/occupharmreport")
public class PageBisOccupharmreportController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private IBisOccupharmreportService bisOccupharmreportService;

	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model,HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		//首页弹窗条件
		if(request.getParameter("sys")!=null &&"1".equals(request.getParameter("sys")))
			model.addAttribute("sys", "sys");
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					return "qyxx/occupharmreport/ajindex";
				else
					return "qyxx/occupharmreport/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			model.addAttribute("qyid", request.getParameter("qyid"));
			return "qyxx/occupharmreport/ajindex";
		}	
	}
	
	
	/**
	 * list页面
	 * @param request
	 * @throws ParseException 
	 */
	@RequiresPermissions("bis:occupharmreport:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) throws ParseException {
		
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		
		Map<String, Object> map = getPageMap(request);
		if(be!=null){
			map.put("qyid", be.getID());
		}

		map.put("m10", request.getParameter("bis_zybjcbg_cx_m10"));
		map.put("m11", request.getParameter("bis_zybjcbg_cx_m11"));
		map.put("m1", request.getParameter("bis_zybjcbg_cx_m1"));
		map.put("m2", request.getParameter("bis_zybjcbg_cx_m2"));
		map.put("time", request.getParameter("bis_zybjcbg_cx_time"));
		map.put("time1", request.getParameter("bis_zybjcbg_cx_time1"));
		return bisOccupharmreportService.dataGrid(map);
	}
	

	/**
	 * list页面
	 * @param request
	 * @throws ParseException 
	 */
	@RequestMapping(value="tablist/{qyid}")
	@ResponseBody
	public Map<String, Object> getData(@PathVariable("qyid") Integer qyid, HttpServletRequest request) throws ParseException {
		
		Map<String, Object> map = getPageMap(request);
		map.put("qyid", qyid);
		return bisOccupharmreportService.dataGrid(map);
	}

	
	/**
	 * list页面(安监局查看所有企业检测报告信息)
	 * @param request
	 */
	@RequestMapping(value="ajlist")
	@ResponseBody
	public Map<String, Object> getAllData(HttpServletRequest request) {
		
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("bis_zybjcbg_cx_qyname"));
		map.put("m10", request.getParameter("bis_zybjcbg_cx_m10"));
		map.put("m11", request.getParameter("bis_zybjcbg_cx_m11"));
		map.put("m1", request.getParameter("bis_zybjcbg_cx_m1"));
		map.put("m2", request.getParameter("bis_zybjcbg_cx_m2"));
		map.put("time", request.getParameter("bis_zybjcbg_cx_time"));
		map.put("time1", request.getParameter("bis_zybjcbg_cx_time1"));
		map.putAll(getAuthorityMap());

		//安监条件
		String qyid = request.getParameter("qyid");
		if(StringUtils.isNotEmpty(qyid))
			map.put("qyid", qyid);
		return bisOccupharmreportService.dataGridAJ(map);
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
	@RequiresPermissions("bis:occupharmreport:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			resultmap = bisOccupharmreportService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("bis:occupharmreport:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		// 如果是企业，获取企业平面图
		if (sessionuser.getUsertype().equals("1")) {
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());

			model.addAttribute("pmt", StringUtils.defaultString(be.getM33_3()));
		}
		model.addAttribute("usertype",sessionuser.getUsertype());
		return "qyxx/occupharmreport/form";
		
	}
	
	/**
	 * 添加检测报告
	 * @param request,model
	 * @throws ParseException 
	 */
	@RequiresPermissions("bis:occupharmreport:add")
	@RequestMapping(value = "create" , method = RequestMethod.POST)
	@ResponseBody
	public String create(BIS_OccupharmExamReportEntity bo, Model model) throws ParseException {
		String datasuccess="success";
		
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){
			bo.setID1(sessionuser.getQyid());
		}
		Timestamp t=DateUtils.getSysTimestamp();
		bo.setS1(t);
		bo.setS2(t);
		bo.setS3(0);
		bo.setID2((long)sessionuser.getId());
		bisOccupharmreportService.addInfo(bo);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--检测报告信息  【添加操作】");

		//返回结果
		return datasuccess;
		
	}
	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("bis:occupharmreport:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的检测
		BIS_OccupharmExamReportEntity bo = bisOccupharmreportService.findById(id);
		
		// 根据企业id获取企业对象
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(StringUtils.toLong(bo.getID1()));
		// 获取企业平面图
		model.addAttribute("pmt", StringUtils.defaultString(be.getM33_3()));
		
		model.addAttribute("jcbg", bo);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "qyxx/occupharmreport/form";
	}
	
	/**
	 * 修改检测报告信息
	 * @param request,model
	 * @throws ParseException 
	 */
	@RequiresPermissions("bis:occupharmreport:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(BIS_OccupharmExamReportEntity bo, Model model) throws ParseException{
		String datasuccess="success";
		Timestamp t=DateUtils.getSysTimestamp();
		bo.setS2(t);
		bisOccupharmreportService.updateInfo(bo);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--检测报告信息  【修改操作】");

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
	@RequiresPermissions("bis:occupharmreport:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			bisOccupharmreportService.deleteInfo(Long.parseLong(aids[i]));
		}
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--检测报告信息  【删除操作】");

		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("bis:qyjbxx:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		//查询选择的产品信息
		BIS_OccupharmExamReportEntity bo = bisOccupharmreportService.findById(id);
		BIS_EnterpriseEntity entity = bisQyjbxxService.findInfoById(bo.getID1());
		model.addAttribute("pmt", entity.getM33_3());
		model.addAttribute("jcbg", bo);
		//返回页面
		return "qyxx/occupharmreport/view";
	}
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("bis:occupharmreport:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(getAuthorityMap());
		map.put("qyname", request.getParameter("bis_zybjcbg_cx_qyname"));
		map.put("m10", request.getParameter("bis_zybjcbg_cx_m10"));
		map.put("m11", request.getParameter("bis_zybjcbg_cx_m11"));
		map.put("m1", request.getParameter("bis_zybjcbg_cx_m1"));
		map.put("m2", request.getParameter("bis_zybjcbg_cx_m2"));
		map.put("time", request.getParameter("bis_zybjcbg_cx_time"));
		map.put("time1", request.getParameter("bis_zybjcbg_cx_time1"));
		
		map.put("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		bisOccupharmreportService.exportExcel(response, map);
		
	}
	
	/**
	 * 显示所有列
	 * @param id,model
	 */
	@RequiresPermissions("bis:occupharmreport:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","bis/occupharmreport/export");
		return "common/formexcel";
	}
}
