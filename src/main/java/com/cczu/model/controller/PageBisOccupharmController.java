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
import com.cczu.model.entity.BIS_OccupharmExamEntity;
import com.cczu.model.service.IBisOccupharmService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.ITZybysService;
import com.cczu.model.service.ITdicZybflService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.Department;
import com.cczu.sys.system.service.DepartmentService;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 职业病危害因素controller
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("bis/occupharm")
public class PageBisOccupharmController extends BaseController {
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private IBisOccupharmService bisOccupharmService;
	@Autowired
	private ITZybysService  tzybysservice;//职业病危害因素字典
	@Autowired
	private ITdicZybflService tdiczybflservice;//职业病分类的字典

	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model,HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					return "qyxx/occupharm/ajindex";
				else
					return "qyxx/occupharm/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			model.addAttribute("qyid", request.getParameter("qyid"));
			return "qyxx/occupharm/ajindex";
		}	
	}

	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="ajlist")
	@ResponseBody
	public Map<String, Object> ajgetData(HttpServletRequest request) {
		
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("bis_occupharm_qy_name"));
		map.put("m1", request.getParameter("bis_zyb_cx_m1"));//职业病危害因素分类
		map.put("m2", request.getParameter("bis_zyb_cx_m2"));//职业病危害因素名称
		map.put("m3", request.getParameter("bis_zyb_cx_m3"));//严重程度
		map.put("m5", request.getParameter("bis_zyb_cx_m5"));//可能导致的职业病
		map.put("m6", request.getParameter("bis_zyb_cx_m6"));//存在部门
		map.put("m7", request.getParameter("bis_zyb_cx_m7"));//存在岗位		
		map.putAll(getAuthorityMap());

		//安监条件
		String qyid = request.getParameter("qyid");
		if(StringUtils.isNotEmpty(qyid))
			map.put("qyid", qyid);
		return bisOccupharmService.ajdataGrid(map);
	}	
	
	/**
	 * list页面
	 * @param request
	 * @throws ParseException 
	 */
	@RequiresPermissions("bis:occupharm:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) throws ParseException {
		
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());		
		Map<String, Object> map = getPageMap(request);
		if(be!=null&&be.getM1()!=null){
			map.put("qyid", be.getID());
		}
		
		map.put("m1", request.getParameter("bis_zyb_cx_m1"));//职业病危害因素分类
		map.put("m2", request.getParameter("bis_zyb_cx_m2"));//职业病危害因素名称
		map.put("m3", request.getParameter("bis_zyb_cx_m3"));//严重程度
		map.put("m5", request.getParameter("bis_zyb_cx_m5"));//可能导致的职业病
		map.put("m6", request.getParameter("bis_zyb_cx_m6"));//存在部门
		map.put("m7", request.getParameter("bis_zyb_cx_m7"));//存在岗位
		return bisOccupharmService.dataGrid(map);
	}
	
	/**
	 * list页面
	 * @param request
	 * @throws ParseException 
	 */
	@RequestMapping(value="tablist/{qyid}")
	@ResponseBody
	public Map<String, Object> getDataTab(@PathVariable("qyid") Integer qyid, HttpServletRequest request) throws ParseException {
		
		Map<String, Object> map = getPageMap(request);
		map.put("qyid", qyid);
		return bisOccupharmService.dataGrid(map);
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("bis:occupharm:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
			return "qyxx/occupharm/form";	
	}
	
	/**
	 * 选择接触名单页面跳转
	 * @param model
	 */
	@RequiresPermissions("bis:occupharm:add")
	@RequestMapping(value = "jcmd" , method = RequestMethod.GET)
	public String jcmd(Model model, HttpServletRequest request) {
		model.addAttribute("action", "create");
		model.addAttribute("bmid",request.getParameter("bmid"));
		model.addAttribute("gwname",request.getParameter("gwname"));
		model.addAttribute("ygid",request.getParameter("ygid"));
		return "qyxx/occupharm/jcmd";	
	}
	
	/**
	 * 接触人员名单数据
	 * @param request
	 * @throws ParseException 
	 */
	@RequiresPermissions("bis:occupharm:view")
	@RequestMapping(value="jcmd/json")
	@ResponseBody
	public Map<String, Object> jcmd(HttpServletRequest request) throws ParseException {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Map<String, Object> map = getPageMap(request);
		map.put("qyid", sessionuser.getQyid());
		map.put("bmid", request.getParameter("bmid"));
		map.put("gwname", request.getParameter("gwname"));
		map.put("ygname", request.getParameter("keyword"));//要搜索的关键词
		
		return bisOccupharmService.getJcmdDatagrid(map);
	}
	
	/**
	 * 添加
	 * @param request,model
	 */
	@RequiresPermissions("bis:occupharm:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(BIS_OccupharmExamEntity bo, Model model) {
		String datasuccess="success";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		//用户类型(1企业，0安监局，9系统管理员)
		if(sessionuser.getUsertype().equals("1")){
			bo.setID1(sessionuser.getQyid());
		}
		Timestamp t=DateUtils.getSysTimestamp();
		bo.setS1(t);
		bo.setS2(t);
		bo.setS3(0);
		String m9 = bo.getM9();
		if(StringUtils.isBlank(m9)) {
			bo.setM8(0 + "");
		}else{
			String[] arr_m9 = m9.split(",");
			bo.setM8(arr_m9.length+"");
		}
		bisOccupharmService.addInfo(bo);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--职业病危害因素信息  【添加操作】");

		//返回结果
		return datasuccess;
		
	}
	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("bis:occupharm:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		//查询选择的作业班次
		long id1 = id;
		BIS_OccupharmExamEntity bo = bisOccupharmService.findById(id1);
		String m6 = bo.getM6();//部门名称
		if (StringUtils.isNotBlank(m6)) {
			Department dept = departmentService.findInfoByName(m6);//根据部门名称获取部门信息
			model.addAttribute("deptid", dept.getId());//将部门id传到修改页面
		} else {
			model.addAttribute("deptid", "");
		}
		model.addAttribute("whyslist", bo);
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		//返回页面
		model.addAttribute("action", "update");
		return "qyxx/occupharm/form";
	}
	
	/**
	 * 修改作业班次
	 * @param request,model
	 */
	@RequiresPermissions("bis:occupharm:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(BIS_OccupharmExamEntity bo, Model model){
		String datasuccess="success";
		Timestamp t=DateUtils.getSysTimestamp();
		bo.setS2(t);
		String m9 = bo.getM9();
		if(StringUtils.isBlank(m9)) {
			bo.setM8(0 + "");
		}else{
			String[] arr_m9 = m9.split(",");
			bo.setM8(arr_m9.length+"");
		}
		bisOccupharmService.updateInfo(bo);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--职业病危害因素信息  【修改操作】");

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
	@RequiresPermissions("bis:occupharm:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			bisOccupharmService.deleteInfo(Long.parseLong(aids[i]));
		}
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--职业病危害因素信息  【删除操作】");

		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("bis:qyjbxx:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		//查询选择的产品信息
		long id1 = id;
		BIS_OccupharmExamEntity bo = bisOccupharmService.findById2(id1);	
		model.addAttribute("whyslist", bo);
		//返回页面
		return "qyxx/occupharm/view";
	}
	
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("bis:occupharm:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		}else if("0".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());	
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
				map.put("jglx",sessionuser.getUserroleflg());
		}else if( UserUtil.getCurrentShiroUser().getUsertype().equals("5"))
			map.put("cjz", UserUtil.getCurrentShiroUser().getId());
		map.put("qyname", request.getParameter("bis_occupharm_qy_name"));
		map.put("m1", request.getParameter("bis_zyb_cx_m1"));//职业病危害因素分类
		map.put("m2", request.getParameter("bis_zyb_cx_m2"));//职业病危害因素名称
		map.put("m3", request.getParameter("bis_zyb_cx_m3"));//严重程度
		map.put("m5", request.getParameter("bis_zyb_cx_m5"));//可能导致的职业病
		map.put("m6", request.getParameter("bis_zyb_cx_m6"));//存在部门
		map.put("m7", request.getParameter("bis_zyb_cx_m7"));//存在岗位
		map.put("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		bisOccupharmService.exportExcel(response, map);
		
	}
	
	/**
	 * 显示所有列
	 * @param id,model
	 */
	@RequiresPermissions("bis:occupharm:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","bis/occupharm/export");
		return "common/formexcel";
	}
	
	
	/**
	 * 字典显示
	 *(没用)
	 * @param 
	 */
	@RequestMapping(value="/zybzd")
	@ResponseBody
	public String jsons(HttpServletRequest request) {
		return tzybysservice.dataList();
	}
	
	/**
	 * 字典显示
	 * @param 
	 */
	@RequestMapping(value="/zybzd2/{text}")
	@ResponseBody
	public String jsons2(@PathVariable("text") String text,HttpServletRequest request) {
		return tzybysservice.dataList2(text);
	}
	
	
	/**
	 * 字典显示
	 * @param {json}
	 */
	@RequestMapping(value="/zybzd3")
	@ResponseBody
	public String json2(HttpServletRequest request) {
		String m1 =request.getParameter("t1");
		return tzybysservice.findliat(m1);
	}
	
	/**
	 * 字典显示
	 * @param {json}
	 */
	@RequestMapping(value="/zybfl/json" , method = RequestMethod.GET)
	@ResponseBody
	public String gbtjson() {
		return tdiczybflservice.gbtjson();
	}

	/**
	 * 字典显示
	 * @param {json}
	 */
	@RequestMapping(value="/izy")
	@ResponseBody
	public String js(HttpServletRequest request) {
		return tzybysservice.data();
	}
	/**
	 * 统计页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "statistics", method = RequestMethod.GET)
	public String statistics(Model model) {
		
		Map<String, Object>map= bisOccupharmService.statistics(getAuthorityMap());
		model.addAttribute("data", JsonMapper.getInstance().toJson(map));
		return "qyxx/occupharm/statistics";
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
	@RequiresPermissions("bis:occupharm:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			resultmap = bisOccupharmService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}
	
}
