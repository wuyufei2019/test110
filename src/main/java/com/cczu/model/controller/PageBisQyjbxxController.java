package com.cczu.model.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.bcel.generic.NEW;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.misc.BASE64Decoder;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IBisAqpxxxService;
import com.cczu.model.service.IBisCgxxService;
import com.cczu.model.service.IBisCjxxService;
import com.cczu.model.service.IBisCkxxService;
import com.cczu.model.service.IBisGwgyService;
import com.cczu.model.service.IBisHazardIdentityService;
import com.cczu.model.service.IBisHazardService;
import com.cczu.model.service.IBisOccupIllexamService;
import com.cczu.model.service.IBisOccupharmService;
import com.cczu.model.service.IBisOccupharmreportService;
import com.cczu.model.service.IBisQyAddressService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.IBisTzsbxxService;
import com.cczu.model.service.IBisWlxxService;
import com.cczu.model.service.IBisWxzyService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.Barrio;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.service.BarrioService;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

/**
 * 企业基本信息controller
 * @author jason
 * @date 2017年5月31日
 */
@Controller
@RequestMapping("bis/qyjbxx")
public class PageBisQyjbxxController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private BarrioService barrioservice;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private IBisQyAddressService bisQyAddressService;
	@Autowired
	private IBisCjxxService bisCjxxService;
	@Autowired
	private IBisWlxxService bisWlxxService;
	@Autowired
	private IBisCkxxService bisCkxxService;
	@Autowired
	private IBisCgxxService bisCgxxService;
	@Autowired
	private IBisTzsbxxService bisTzsbxxService;
	@Autowired
	private IBisAqpxxxService bisAqpxxxService;
	@Autowired
	private IBisGwgyService bisGwgyService;
	@Autowired
	private IBisWxzyService bisWxzyService;
	@Autowired
	private IBisOccupharmService bisOccupharmService;
	@Autowired
	private IBisOccupharmreportService bisOccupharmreportService;
	@Autowired
	private IBisOccupIllexamService bisOccupIllexamService;
	@Autowired
	private IBisHazardService bisHazardservice;
	@Autowired
	private IBisHazardIdentityService bisHazardIdentityService;
	
	public static String strflg="bis_qytable_newtrid";
	
	public static String Bank=null;
	
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(HttpServletRequest request, Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		List<Role> list=roleService.findRoleById(sessionuser.getId());
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		if(sessionuser.getUsertype().equals("1")){
			if(be!=null&& be.getM1() != null){
				model.addAttribute("qylist", be);
				model.addAttribute("action", "update");
				model.addAttribute("locx", be.getM16());
				model.addAttribute("locy", be.getM17());
			}else{
				BIS_EnterpriseEntity be2=new BIS_EnterpriseEntity();
				be2.setID2(sessionuser.getXzqy());
				model.addAttribute("qylist", be2);
				model.addAttribute("action", "create");
			}
			return "qyxx/qyjbxx/indexform";
		}
		model.addAttribute("userroleflg", sessionuser.getUserroleflg());
		model.addAttribute("state", request.getParameter("state"));
		model.addAttribute("jglx", request.getParameter("jglx"));
		return "qyxx/qyjbxx/index";
	}

	/**
	 * 企业信息 地图显示页面跳转
	 */
	@RequestMapping(value="map")
	public String map(Model model) {
		Barrio bro=barrioservice.findPointBycode(UserUtil.getCurrentShiroUser().getXzqy());
		if(bro!=null)
		model.addAttribute("mappoint", bro.getMappoint());
		return "qyxx/qyjbxx/qyfbmapindex";
	}
	/**
	 * 企业信息    一张图     地图显示页面跳转
	 */
	@RequestMapping(value="onemap")
	public String oneMap(Model model) {
		Barrio bro=barrioservice.findPointBycode(UserUtil.getCurrentShiroUser().getXzqy());
		if(bro!=null)
			model.addAttribute("mappoint", bro.getMappoint());
		return "qyxx/qyjbxx/mapindex";
	}
	/**
	 * 第三方机构  模块有无数据确认 页面 跳转
	 */
	@RequiresPermissions("bis:qyjbxx:comfirm")
	@RequestMapping(value="comfirmmudule")
	public String noDataModule(Model model) {
		return "qyxx/qyjbxx/comfirmmudule";
	}
	
	/**
	 * 第三方机构  模块有无数据确认 页面 左侧企业列表
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequestMapping(value = "nodatamoduleqys")
	@ResponseBody
	public String getQyListForNoDataModule(HttpServletRequest request){
		Map<String, Object> map =getAuthorityMap();
		return  bisQyjbxxService.DsfnoDataModule(map);
	}
	
	/**
	 * 第三方机构  模块有无数据确认 页面  数据添加
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequestMapping(value = "addnodatamodule/{id}")
	@ResponseBody
	public String addDataForModule(@PathVariable("id") long id,HttpServletRequest request){
		String result="success";
		Set<String> tables=request.getParameterMap().keySet();
		try {
			bisQyjbxxService.addDataForModule(tables,id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result="failed";
		}
		return  result;
	}
	
	/**
	 * 添加坐标
	 */
	@RequestMapping(value="maplist")
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request) {
		Map<String, Object> mapdata = getAuthorityMap();
		String type=request.getParameter("type");
		mapdata.put("type",type);
		List<Map<String,Object>> list=bisQyjbxxService.findMapList(mapdata);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		return map;
	}
	
	
	
	/**
	 * list页面
	 */
	@RequiresPermissions("bis:qyjbxx:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("check_company_name"));
		map.put("datestart", request.getParameter("check_company_starttime"));
		map.put("dateend", request.getParameter("check_company_endtime"));
		map.put("qygm", request.getParameter("qygm"));
		map.put("wxydj", request.getParameter("wxydj"));
		map.put("fxdj", request.getParameter("fxdj"));
		map.put("bzhdj", request.getParameter("bzhdj"));
		map.put("isbloc", request.getParameter("isbloc"));
		map.put("state", request.getParameter("state"));
		
		String jglx= request.getParameter("jglx");
		String xzqy= request.getParameter("xzqy");
		map.put("xzqy",xzqy);
		if(jglx!=null&&!jglx.equals("")&&!jglx.equals("0"))
			map.put("jglx",jglx);

		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("0")){//安监用户  添加网格区域编码查询条件
			if(xzqy==null||xzqy.equals("")||xzqy.length()<sessionuser.getXzqy().length())
			    map.put("xzqy",sessionuser.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0){
				map.put("jglx",sessionuser.getUserroleflg());
			} 
		}else if(sessionuser.getUsertype().equals("5")){
			map.put("cjz", UserUtil.getCurrentShiroUser().getId());
		}
		
		return bisQyjbxxService.dataGrid(map);
	}

	
	/**
	 * 添加企业基本信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequiresPermissions("bis:qyjbxx:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model){
		BIS_EnterpriseEntity be = new BIS_EnterpriseEntity();
		be.setID2(UserUtil.getCurrentShiroUser().getXzqy());
		be.setKtdate(DateUtils.getSysTimestamp());// 当前日期
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, 1);
		Date date = calendar.getTime();
		be.setDqdate(new Timestamp(date.getTime()));// 到期日期
		model.addAttribute("qylist", be);
		model.addAttribute("action", "create");
		return "qyxx/qyjbxx/form";
	}
	
	/**
	 * 添加企业基本信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequiresPermissions("bis:qyjbxx:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(@Valid BIS_EnterpriseEntity bis,Model model,HttpServletRequest request){
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--基本信息  【增加操作】");
		bis.setCreateby(UserUtil.getCurrentUser().getName());//创建人
		bis.setM44("4");//默认将企业风险等级设置为蓝色
		return bisQyjbxxService.addInfo(bis);
	}
	
	/**
	 * 更新企业基本信息跳转
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("bis:qyjbxx:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(id);
		model.addAttribute("qylist", be);
		model.addAttribute("action", "update");
		return "qyxx/qyjbxx/form";
	}
	
	/**
	 * 更新企业基本信息
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequiresPermissions("bis:qyjbxx:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(@Valid BIS_EnterpriseEntity bis,Model model,HttpServletRequest request){
		bis.setUpdateby(UserUtil.getCurrentUser().getName());//最后修改人
		bisQyjbxxService.updateInfo(bis);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--基本信息  【修改操作】");

		return "success";
	}
	
	
	/**
	 * 删除企业信息
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("bis:qyjbxx:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功！";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			bisQyjbxxService.deleteInfo(Long.parseLong(arrids[i]));
		}
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--基本信息  【删除操作】");

		return datasuccess;
	}
	
	
	/** 
     * 导出excel 
     * @return 
     */  
	@RequestMapping(value="exportE")
	public void exportExcel(HttpServletResponse response,HttpServletRequest request){ 
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyname", request.getParameter("check_company_name"));
		map.put("datestart", request.getParameter("check_company_starttime"));
		map.put("dateend", request.getParameter("check_company_endtime"));
		map.put("qygm", request.getParameter("qygm"));
		map.put("wxydj", request.getParameter("wxydj"));
		map.put("fxdj", request.getParameter("fxdj"));
		map.put("bzhdj", request.getParameter("bzhdj"));
		map.put("bis_ids", request.getParameter("bis_ids"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		if(sessionuser.getUsertype().equals("0")){
			map.put("xzqy",sessionuser.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
				map.put("jglx",sessionuser.getUserroleflg());
		}else if(sessionuser.getUsertype().equals("5")){
			map.put("cjz", UserUtil.getCurrentShiroUser().getId());
		}
		bisQyjbxxService.exportExcel(response ,map);
    }
	
	/**
	 * 显示所有列
	 * @param id,model
	 */
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","bis/qyjbxx/exportE");
		return "common/formexcel";
	}  
	
	
	/**
	 * 选项卡查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("bis:qyjbxx:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		//查询选择的产品信息
		long id1 = id;
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(id1);
		model.addAttribute("qyid", be.ID);
        //统计
		Map<String,Object> tj = bisQyjbxxService.getTotalViewById(id1);
		model.addAttribute("tj", tj);
		return "qyxx/qyjbxx/formtab";
	}
	/**
	 * 首页选项卡查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("bis:qyjbxx:view")
	@RequestMapping(value = "viewmain/{id}", method = RequestMethod.GET)
	public String viewMain(@PathVariable("id") Integer id, Model model) {
		//查询选择的产品信息
		long id1 = id;
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(id1);
		model.addAttribute("qyid", be.ID);
		return "qyxx/qyjbxx/formtabmain";
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("bis:qyjbxx:view")
	@RequestMapping(value = "tabview/{id}", method = RequestMethod.GET)
	public String tabView(@PathVariable("id") Long id, Model model) {
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(id);
		model.addAttribute("qylist", be);
		//返回页面
		model.addAttribute("action", "view");
		return "qyxx/qyjbxx/formview";
	}
	
	/**
	 * 统计页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "statistics", method = RequestMethod.GET)
	public String statistics(Model model) {
		Map<String, Object> map = bisQyjbxxService.statistics(getAuthorityMap());
		model.addAttribute("mapdata", JsonMapper.getInstance().toJson(map));
		return "qyxx/qyjbxx/statistics";
	}
	
	
	/**
	 * 获取企业名称和id
	 * idjson  {"id":11,"text":"企业名称"}
	 * return String
	 */
	@RequestMapping(value="idjson")
	@ResponseBody
	public String codeIdjson() {
		Map<String, Object> map = new HashMap<String, Object>();
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("0")){//安监用户
			map.put("xzqy",sessionuser.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
				map.put("jglx",sessionuser.getUserroleflg());
		}else if(sessionuser.getUsertype().equals("5")){//第三方机构
			map.put("cjz", UserUtil.getCurrentShiroUser().getId());
		}else if(sessionuser.getUsertype().equals("1")){
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			//判断是否为集团公司
			if(be.getIsbloc()!=null&&be.getIsbloc()==1)
				map.put("fid", sessionuser.getQyid());
			else
				map.put("id", sessionuser.getQyid());
		}
		return bisQyjbxxService.getQyIdjson(map);
	}
	
	/**
	 * 获取企业名称
	 * textjson {"id":"企业名称","text":"企业名称"}
	 * return String
	 */
	@RequestMapping(value="textjson")
	@ResponseBody
	public String codeTextjson() {
		Map<String, Object> map = new HashMap<String, Object>();
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("0")){//安监用户
			map.put("xzqy",sessionuser.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
				map.put("jglx",sessionuser.getUserroleflg());
		}else if(sessionuser.getUsertype().equals("5")){//第三方机构
			map.put("cjz", UserUtil.getCurrentShiroUser().getId());
		}
		return bisQyjbxxService.getQyTextjson(map);
	}
	
	/**
	 * 根据id查询经纬度
	 * return String
	 */
	@RequestMapping(value = "dict")
	@ResponseBody
	public String dict(HttpServletRequest request ,Model model){
		String id=request.getParameter("id");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return bisQyjbxxService.findXYById(map);
	}
	
	
	/**
	 * 获取企业信息
	 * @param id,model
	 */
	@RequestMapping(value = "qydetail/{id}")
	@ResponseBody
	public String getQyDetailJson(@PathVariable("id") Long id) {
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(id);
		return JsonMapper.getInstance().toJson(be);
	}
	
	
	
	/**
	 * 统计页面跳转
	 *  
	 */
	@RequiresPermissions("bis:qyjbxx:view")
	@RequestMapping(value = "total", method = RequestMethod.GET)
	public String toQyxxTotal(Model model) {
		return "qyxx/qyjbxx/totalAll";
	}
	
	/**
	 * 统计所有企业的基本信息数量
	 *  
	 */
	@RequiresPermissions("bis:qyjbxx:view")
	@RequestMapping(value = "totaljson")
	@ResponseBody
	public String qyxxTotalAll(HttpServletRequest request, Model model) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("check_company_name"));
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("0")){
			map.put("xzqy",sessionuser.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
				map.put("jglx",sessionuser.getUserroleflg());
		}else if(sessionuser.getUsertype().equals("5")){
			map.put("cjz", UserUtil.getCurrentShiroUser().getId());
		}else if(sessionuser.getUsertype().equals("1")){
			return "";
		}
		
		return bisQyjbxxService.qyxxTotalAll(map);
	}
	
	/** 
     * 导出统计信息excel 
     * @return 
     */  
	@RequestMapping(value="export")
	public void exportTotalExcel(HttpServletResponse response,HttpServletRequest request){ 
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyname", request.getParameter("qyname"));
		if(sessionuser.getUsertype().equals("0")){
			map.put("xzqy",sessionuser.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
				map.put("jglx",sessionuser.getUserroleflg());
		}else if(sessionuser.getUsertype().equals("5")){
			map.put("cjz", UserUtil.getCurrentShiroUser().getId());
		}
		bisQyjbxxService.ExportQyxxTotalAll(response ,map);
    }
	
	/**
	 * 导出安全档案word
	 * 
	 */
	@RequestMapping(value = "exportWordAll/{qyid}")
	@ResponseBody
	public String getWordAll(@PathVariable("qyid") Long qyid,HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> map = bisQyjbxxService.getWordAll(qyid);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "caiji.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	
	/**
	 * Ajax请求校验企业名称是否唯一。
	 */
	@RequestMapping(value = "checkName")
	@ResponseBody
	public String checkLoginName(String qyName,Long qyid) {
		Map<String,Object> map = new HashMap<>();
		map.put("qyid", qyid);
		map.put("equalqynm", qyName);
		boolean falg=bisQyjbxxService.checkQyname(map);
		return falg+"";
	}
	
	
	/**
	 * 根据经纬度查询企业
	 * @param id,model
	 */
	@RequestMapping(value = "findpoint")
	@ResponseBody
	public String tabView(Model model,HttpServletRequest request) {
		Map<String,Object> map = new HashMap<>();
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		String lng=request.getParameter("lng");
		String lat=request.getParameter("lat");
		map.put("lat",lat);
		map.put("lng",lng);
		if(sessionuser.getUsertype().equals("0")){
			map.put("xzqy",sessionuser.getXzqy());
		}
		return JsonMapper.getInstance().toJson(bisQyjbxxService.findQyInfoByPoint(map));
	}
	
	/**
	 * 停用企业
	 */
	@RequestMapping(value = "disablebis/{qyid}")
	@ResponseBody
	public String disableBis(@PathVariable("qyid") Long qyid) {
		String dataSuccess = "success";
		BIS_EnterpriseEntity bis = bisQyjbxxService.findInfoById(qyid);
		bis.setState("0");
		bisQyjbxxService.updateInfo(bis);
		return dataSuccess;
	}
	
	/**
	 * 启用企业
	 */
	@RequestMapping(value = "enablebis/{qyid}")
	@ResponseBody
	public String enableBis(@PathVariable("qyid") Long qyid) {
		String dataSuccess = "success";
		BIS_EnterpriseEntity bis = bisQyjbxxService.findInfoById(qyid);
		bis.setState(null);
		bisQyjbxxService.updateInfo(bis);
		return dataSuccess;
	}
	
	/**
	 * 获取企业logo
	 */
	@RequestMapping(value = "getlogo")
	@ResponseBody
	public String getBisLogo() {
		String logoUrl="";
		ShiroUser user = UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity bis = bisQyjbxxService.findInfoById(user.getQyid());
		if(bis!=null)
			logoUrl = bis.getQylogo();// 企业logo地址
		return logoUrl;
	}
	
	/**
	 * 跳转到修改企业logo页面
	 */
	@RequestMapping(value = "updlogo", method = RequestMethod.GET)
	public String updateBisLogo(Model model) {
		model.addAttribute("action", "updlogo");
		return "qyxx/qyjbxx/uploadlogo";
	}
	
	/**
	 * 修改企业logo
	 */
	@RequestMapping(value = "updlogo")
	@ResponseBody
	public String updateBisLogo(String imgfile) {
		String dataSuccess = "success";
		ShiroUser user = UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity bis = bisQyjbxxService.findInfoById(user.getQyid());
		bis.setQylogo(imgfile);
		bisQyjbxxService.updateInfo(bis);
		return dataSuccess;
	}
	
	/**
	 * base64字符串转换成图片
	 * @param imgStr		base64字符串
	 * @param imgFilePath	图片存放路径
	 * @return
	 *
	 * @author ZHANGJL
	 * @dateTime 2018-02-23 14:42:17
	 */
	public boolean Base64ToImage(String imgStr,String imgFilePath) { // 对字节数组字符串进行Base64解码并生成图片
 
		if (StringUtils.isEmpty(imgStr)) // 图像数据为空
			return false;
 
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
 
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
 
			return true;
		} catch (Exception e) {
			return false;
		}
 
	}
	/**
	 * 企业标准化运行页面跳转
	 */
	@RequestMapping(value="bzhyx")
	public String bzhyx(Model model,HttpServletRequest request) {
		String qyid=request.getParameter("qyid");
		model.addAttribute("qyid",request.getParameter("qyid"));
        model.addAttribute("wcd",bisQyjbxxService.findwcd(Long.parseLong(qyid)).get("wcd"));
		model.addAttribute("mksl",bisQyjbxxService.findsl(Long.parseLong(qyid)));
		return "qyxx/qyjbxx/bzhyx";
	}
}
