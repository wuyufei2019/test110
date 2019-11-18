package com.cczu.model.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
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

import com.cczu.model.entity.BIS_EmployeeTestEntity;
import com.cczu.model.entity.BIS_EmployeeTest_Second;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.AqjgAqbaService;
import com.cczu.model.service.BisYgtjSecService;
import com.cczu.model.service.BisYgtjService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 企业基本信息---员工体检信息controller
 */
@Controller
@RequestMapping("bis/ygtjxx")
public class PageBisYgtjxxController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private BisYgtjService bisYgtjService;
	@Autowired
	private BisYgtjSecService bisYgtjSecService;	
	@Autowired
	private AqjgAqbaService aqjgAqbaService;
	
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
					return "qyxx/ygtj/ajindex";
				else
					return "qyxx/ygtj/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			model.addAttribute("qyid", request.getParameter("qyid"));
			return "qyxx/ygtj/ajindex";
		}	
	}
	
	/**
	 * list页面（企业用户显示界面）
	 * @param request
	 */
	@RequiresPermissions("bis:ygtjxx:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		int pageNo=1;	//当前页码
		int pageSize=20;	//每页行数
		if(StringUtils.isNotEmpty(request.getParameter("page")))
			pageNo=Integer.valueOf(request.getParameter("page"));
		if(StringUtils.isNotEmpty(request.getParameter("rows")))
			pageSize=Integer.valueOf(request.getParameter("rows"));
		
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);
		if(be!=null){
			map.put("qyid", be.getID());
		}

		map.put("ygname", request.getParameter("bis_ygtj_cx_ygname"));//员工姓名
		map.put("m1", request.getParameter("bis_ygtj_cx_m1"));//身份证号
		
		return bisYgtjService.dataGrid(map);
		
	}

	/**
	 * list页面（非企业用户显示界面）
	 * @param request
	 */
	@RequestMapping(value="ajlist")
	@ResponseBody
	public Map<String, Object> getData2(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.putAll(getAuthorityMap());

		//安监条件
		String qyid = request.getParameter("qyid");
		if(StringUtils.isNotEmpty(qyid))
			map.put("qyid", qyid);
		map.put("m1", request.getParameter("bis_ygtj_cx_m1"));//身份证号
		map.put("ygname", request.getParameter("bis_ygtj_cx_ygname"));//员工姓名
		map.put("qyname", request.getParameter("bis_ygtj_cx_qyname"));//企业名称
		return bisYgtjService.dataGrid2(map);
		
	}
	
	/**
	 * tablist页面 
	 * @param request
	 */
	@RequestMapping(value="tablist/{qyid}")
	@ResponseBody
	public Map<String, Object> getDataTab(@PathVariable("qyid") Integer qyid, HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyid", qyid);
		return bisYgtjService.dataGrid(map);
	}
	
	/**
	 * 判断员工的体检记录是否存在
	 * @param request
	 */
	@RequestMapping(value="isexist")
	@ResponseBody
	public String getCountByName(HttpServletRequest request) {
		String dataStatus = "";
		Map<String, Object> map = getPageMap(request);
		map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		map.put("yg_name", request.getParameter("yg_name"));
		int count = bisYgtjService.getCountByName(map);
		if (count >= 1) {
			dataStatus = "yes";
		} else {
			dataStatus = "no";
		}
		return dataStatus;
	}
	
	/**
	 * ygtjlist页面 
	 */
	@RequestMapping(value="ygtjlist/{sfz}")
	@ResponseBody
	public Map<String, Object> getYgtjData(@PathVariable("sfz") String sfz, HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("sfz", sfz);
		return bisYgtjService.dataGrid(map);
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("bis:ygtjxx:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		BIS_EmployeeTestEntity ygtjlist = new BIS_EmployeeTestEntity();
		ygtjlist.setID1(UserUtil.getCurrentShiroUser().getQyid());
		model.addAttribute("ygtjlist", ygtjlist);
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());		
		return "qyxx/ygtj/form";
	}
	
	/**
	 * 添加/修改信息
	 * @param request,model
	 */
	@RequiresPermissions("bis:ygtjxx:add")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public String create(BIS_EmployeeTestEntity scsb, Model model) {
		String datasuccess="success";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		//用户类型(1企业，0安监局，9系统管理员)
		if(sessionuser.getUsertype().equals("1")){
			scsb.setID1(sessionuser.getQyid());
		}		
		bisYgtjService.addInfo(scsb);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--员工体检信息  【增加操作】");

		//返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的成员信息
		BIS_EmployeeTestEntity ygtj = bisYgtjService.findById(id);
		model.addAttribute("ygtjlist", ygtj);
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		//返回页面
		model.addAttribute("action", "update");
		return "qyxx/ygtj/form";
	}
	
	/**
	 * 修改员工体检信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(BIS_EmployeeTestEntity ygtj, Model model){
		String datasuccess="success";	
		bisYgtjService.updateInfo(ygtj);
		//返回结果
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--员工体检信息  【修改操作】");

		return datasuccess;
	}
	
	/**
	 * 删除公用工程信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			bisYgtjService.deleteInfo(Long.parseLong(aids[i]));
		}
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--员工体检信息  【删除操作】");

		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		//查询选择的安全备案信息
		BIS_EmployeeTestEntity ygtj = bisYgtjService.findById(id);		
		model.addAttribute("ygtjlist", ygtj);
		//返回页面
		model.addAttribute("action", "view");
		return "qyxx/ygtj/view";
	}
	
	/**
	 * 获取企业list
	 * @param id,model
	 */
	@RequestMapping(value = "qylist")
	@ResponseBody
	public String getQyJson() {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		List<Map<String, Object>> qynmList=new ArrayList<Map<String, Object>>();
		Integer jglx = 0;
		if(sessionuser.getUsertype().equals("0")){
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0){
				jglx = sessionuser.getUserroleflg();
			}
			 qynmList = aqjgAqbaService.findQynmList(sessionuser.getXzqy(),"aj",jglx);
		    
		} 
		else if(sessionuser.getUsertype().equals("5")){
			qynmList = aqjgAqbaService.findQynmList(sessionuser.getId().toString(),"dsf",jglx);
		}
		return JsonMapper.getInstance().toJson(qynmList);
	}
	
	/**
	 * 显示所有列
	 */
	@RequiresPermissions("bis:ygtjxx:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","bis/ygtjxx/export");
		return "common/formexcel";
	}
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("bis:ygtjxx:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(getAuthorityMap());
		map.put("ygname", request.getParameter("bis_ygtj_cx_ygname"));//员工姓名
		map.put("m1", request.getParameter("bis_ygtj_cx_m1"));//身份证号
		map.put("qyname", request.getParameter("bis_ygtj_cx_qyname"));
		
		map.put("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		map.put("colval", request.getParameter("colval"));	
		map.put("coltext", request.getParameter("coltext"));
		
		bisYgtjService.exportExcel(response, map);
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
	@RequiresPermissions("bis:ygtjxx:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			resultmap = bisYgtjService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}

	/**
	 * 更新检测日期(弃用)
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("bis:ygtjxx:update")
	@RequestMapping(value = "update2/{id}", method = RequestMethod.GET)
	public String update2(@PathVariable("id") Long id, Model model) {
		// 查询选择的特种设备信息
		BIS_EmployeeTestEntity ygtj = bisYgtjService.findById(id);
		model.addAttribute("ygtjlist", ygtj);
		// 返回页面
		model.addAttribute("action", "update2");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "qyxx/ygtj/form2";
	}
	
	/**
	 * 更新员工体检日期(弃用)
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("bis:ygtjxx:update")
	@RequestMapping(value = "update2")
	@ResponseBody
	public String update2(BIS_EmployeeTestEntity ygtj, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		BIS_EmployeeTestEntity ygtj2=bisYgtjService.findById(ygtj.getID());
		ygtj2.setM3(ygtj.getM3());
		ygtj2.setM8(ygtj.getM8());
		ygtj2.setS2(t);
		
		bisYgtjService.updateInfo(ygtj2);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--特种设备信息  【更新日期操作】");

		BIS_EmployeeTest_Second ygtjsec=new BIS_EmployeeTest_Second();
		ygtjsec.setID1(ygtj2.getID());//员工id
		ygtjsec.setQyid(ygtj2.getID1());//企业id
		ygtjsec.setM1(ygtj.getM3());
		ygtjsec.setM2(ygtj.getM8());
		ygtjsec.setS1(t);
		ygtjsec.setS2(t);
		ygtjsec.setS3(0);
		//添加体检历史信息
		bisYgtjSecService.addInfo(ygtjsec);

		// 返回结果
		return datasuccess;
	}
	
	/**
	 * 更新历史页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("bis:ygtjxx:add")
	@RequestMapping(value = "hisindex/{id}", method = RequestMethod.GET)
	public String showhistory(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("ID1",id);
		return "qyxx/ygtj/hisindex";
	}
	
	/**
	 * 更新历史list页面
	 * 
	 * @param request
	 */
	@RequestMapping(value = "hislist")
	@ResponseBody
	public Map<String, Object> getHisData(HttpServletRequest request) {

		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());

		Map<String, Object> map = getPageMap(request);
		map.put("qyid", be.getID());
		map.put("ID1", request.getParameter("ID1").toString());
		
		return bisYgtjSecService.dataGrid(map);
	}	
	
	/**
	 * 字典显示
	 * @param {json}
	 */
	@RequestMapping(value="/whys/json" , method = RequestMethod.GET)
	@ResponseBody
	public String gbtjson() {
		return bisYgtjService.gbtjson();
	}
	
	/**
	 * 添加最新的体检信息页面跳转
	 * @param model
	 */
	@RequiresPermissions("bis:ygtjxx:add")
	@RequestMapping(value = "createnew/{id}" , method = RequestMethod.GET)
	public String create2(@PathVariable("id") Long id,Model model) {
		model.addAttribute("action", "createnew");
		model.addAttribute("ID1", id);
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());		
		return "qyxx/ygtj/form3";
	}
	
	/**
	 * 添加员工最新的体检信息
	 * @param request,model
	 */
	@RequiresPermissions("bis:ygtjxx:add")
	@RequestMapping(value = "createnew", method = RequestMethod.POST)
	@ResponseBody
	public String create2(BIS_EmployeeTest_Second scsb, Model model) {
		String datasuccess="success";
		Timestamp t = DateUtils.getSystemTime();
		
		BIS_EmployeeTestEntity ygtj=bisYgtjService.findById(scsb.getID1());
		
		BIS_EmployeeTest_Second ygtjsec = new BIS_EmployeeTest_Second();
		ygtjsec.setID1(ygtj.getID());  //id
		ygtjsec.setQyid(ygtj.getID1());//企业id
		ygtjsec.setM1(ygtj.getM3());   //体检日期
		ygtjsec.setM2(ygtj.getM8());   //下次体检日期
		ygtjsec.setM3(ygtj.getM2());   //体检类型
		ygtjsec.setM4(ygtj.getM4());   //体检医院
		ygtjsec.setM5(ygtj.getM9());   //接触危害因素
		ygtjsec.setM6(ygtj.getM10());  //体检结果
		ygtjsec.setM7(ygtj.getM5());   //体检结论
		ygtjsec.setS1(t);
		ygtjsec.setS2(t);
		ygtjsec.setS3(0);
		//添加体检历史信息
		bisYgtjSecService.addInfo(ygtjsec);
		
		ygtj.setM2(scsb.getM3()); //体检类型
		ygtj.setM3(scsb.getM1()); //体检日期
		ygtj.setM4(scsb.getM4()); //体检医院
		ygtj.setM5(scsb.getM7()); //体检结论
		ygtj.setM8(scsb.getM2()); //下次体检日期
		ygtj.setM9(scsb.getM5()); //接触危害因素
		ygtj.setM10(scsb.getM6());//体检结果
		ygtj.setS2(t);            //修改时间
		
		bisYgtjService.updateInfo(ygtj);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--特种设备信息  【更新日期操作】");

		//返回结果
		return datasuccess;
	}
}
