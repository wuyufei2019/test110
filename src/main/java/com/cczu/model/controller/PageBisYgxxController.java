package com.cczu.model.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cczu.model.entity.AQPX_ThreeLevelEducationHistoryEntity;
import com.cczu.model.entity.BIS_EmployeeEntity;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.AqpxSjjyHistoryService;
import com.cczu.model.service.BisGzxxService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.IBisYgxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.QRCode;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.Department;
import com.cczu.sys.system.service.DepartmentService;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

/**
 * 员工信息controller
 * @author jason
 * 
 */

@Controller
@RequestMapping("bis/ygxx")
public class PageBisYgxxController extends BaseController {

	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private IBisYgxxService bisYgxxService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private BisGzxxService bisGzxxService;
	@Autowired
	private AqpxSjjyHistoryService sjjyHistoryService;
	@Autowired
	private UserService userService;


	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					return "qyxx/ygxx/ajindex";
				else
					return "qyxx/ygxx/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "qyxx/ygxx/ajindex";
		}
	}
	
	/**
	 * list页面
	 * @param request
	 */
	@RequiresPermissions("bis:ygxx:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		
		//获取企业id
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		
		Map<String, Object> map = getPageMap(request);
		if(be!=null){
			map.put("qyid", be.getID());
		}
		map.put("jobnum", request.getParameter("bis_ygxx_cx_m2"));
		map.put("ygname", request.getParameter("bis_ygxx_cx_m1"));
		map.put("gw", request.getParameter("bis_ygxx_cx_m4"));
		map.put("ewm", request.getParameter("bis_ygxx_cx_m17"));
		map.put("status", request.getParameter("bis_ygxx_cx_status"));
		return bisYgxxService.dataGrid(map);
	}
	
	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="tablist/{qyid}")
	@ResponseBody
	public Map<String, Object> getDataTab(@PathVariable("qyid") Integer qyid, HttpServletRequest request) {
		
		Map<String, Object> map = getPageMap(request);
		map.put("qyid", qyid);
		return bisYgxxService.dataGrid(map);
	}
	
	/**
	 * list页面(安监局查看所有企业员工信息)
	 * @param request
	 */
	@RequiresPermissions("bis:ygxx:view")
	@RequestMapping(value="ajlist")
	@ResponseBody
	public Map<String, Object> getAllData(HttpServletRequest request) {
		
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("bis_ygxx_cx_id3"));
		map.put("ygname", request.getParameter("bis_ygxx_cx_m1"));
		map.put("gw", request.getParameter("bis_ygxx_cx_m4"));
		map.put("ewm", request.getParameter("bis_ygxx_cx_m17"));
		map.put("status", request.getParameter("bis_ygxx_cx_status"));
		map.putAll(getAuthorityMap());
		return bisYgxxService.dataGridAJ(map);
	}
	
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("bis:ygxx:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		Map<String,Object> map = new HashMap<>();
		map.put("m17",UUID.randomUUID().toString().replaceAll("-", ""));
		model.addAttribute("yglist", map);
		return "qyxx/ygxx/form";
	}
	
	/**
	 * 添加员工信息
	 * @param em,model
	 */
	@RequiresPermissions("bis:ygxx:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(BIS_EmployeeEntity em, Model model) {
		log.info(UserUtil.getCurrentShiroUser().getLoginName() + ":  一企一档--员工信息  【增加操作】");
		String datasuccess = "success";
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		if(!StringUtils.isEmpty(em.getM17())&&!bisYgxxService.checkSameEwm(0,em.getM17(),sessionuser.getQyid())){
			// 用户类型(1企业，0安监局，9系统管理员)
			if (sessionuser.getUsertype().equals("1")) {
				em.setID3(sessionuser.getQyid());
			}
			Timestamp t = DateUtils.getSysTimestamp();
			em.setID2(sessionuser.getXzqy());
			em.setS1(t);
			em.setS2(t);
			em.setS3(0);
			em.setStatus(1);
			bisYgxxService.addInfo(em);
		}else{
			datasuccess = "ewmerror";
		}
		// 返回结果
		return datasuccess;
	}

	/**
	 * 已存在账号,添加账号的员工信息
	 * @param em,model
	 */
	@RequestMapping(value = "create2")
	@ResponseBody
	public String create2(BIS_EmployeeEntity em, Model model) {
		log.info(UserUtil.getCurrentShiroUser().getLoginName() + ":  一企一档--员工信息  【增加操作】");
		String datasuccess = "success";
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		if(!StringUtils.isEmpty(em.getM17())&&!bisYgxxService.checkSameEwm(0,em.getM17(),sessionuser.getQyid())){
			// 用户类型(1企业，0安监局，9系统管理员)
			if (sessionuser.getUsertype().equals("1")) {
				em.setID3(sessionuser.getQyid());
			}
			Timestamp t = DateUtils.getSysTimestamp();
			em.setID1(Long.parseLong(sessionuser.getId()+""));
			em.setID2(sessionuser.getXzqy());
			em.setS1(t);
			em.setS2(t);
			em.setS3(0);
			em.setStatus(1);
			bisYgxxService.addInfo2(em);
		}else{
			datasuccess = "ewmerror";
		}
		// 返回结果
		return datasuccess;
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("bis:ygxx:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的员工信息
		BIS_EmployeeEntity em = bisYgxxService.findInfoByID(id);		
		model.addAttribute("yglist",em);
		if(em.getID4()==null){
		}else{
			Department bm = departmentService.getDepartmentById(em.getID4());
			model.addAttribute("ygbm",bm);
		}
		//返回页面
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("action", "update");
		return "qyxx/ygxx/form";
	}
	
	/**
	 * 修改员工信息
	 * @param em,model
	 */
	@RequiresPermissions("bis:ygxx:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(BIS_EmployeeEntity em, Model model){
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--员工信息  【修改操作】");
		String datasuccess="success";
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		Timestamp t=DateUtils.getSysTimestamp();
		if(!StringUtils.isEmpty(em.getM17())) {
			if(!bisYgxxService.checkSameEwm(em.getID(),em.getM17(),sessionuser.getQyid())){
				em.setS2(t);
				bisYgxxService.updateInfo(em);
			}else{
				datasuccess = "ewmerror";
			}
		}else {
			em.setM17(UUID.randomUUID().toString().replaceAll("-", ""));//二维码
			em.setS2(t);
			bisYgxxService.updateInfo(em);
		}
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 修改员工个人信息
	 * @param em,model
	 */
	@RequestMapping(value = "updatePersonal")
	@ResponseBody
	public String updatePersonal(BIS_EmployeeEntity em, Model model){
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--员工信息  【修改操作】");
		String datasuccess="success";
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		if(!StringUtils.isEmpty(em.getM17())&&!bisYgxxService.checkSameEwm(em.getID(),em.getM17(),sessionuser.getQyid())){
			Timestamp t=DateUtils.getSysTimestamp();
			em.setS2(t);
			bisYgxxService.updateInfo(em);
		}else{
			datasuccess = "ewmerror";
		}
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除员工信息
	 * 
	 * @param ids
	 * @throws ParseException
	 */
	@RequiresPermissions("bis:ygxx:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			bisYgxxService.deleteInfo(Long.parseLong(arrids[i]));
		}
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--员工信息  【删除操作】");

		return datasuccess;
	}

	/**
	 * 绑定用户账号信息
	 * @param id 员工id
	 * @param userid 用户id
	 */
	@RequiresPermissions("bis:ygxx:bind")
	@RequestMapping(value = "connect")
	@ResponseBody
	public String connectUserAccount(@RequestParam("id")long id,@RequestParam("userid") long userid) {
		String datasuccess="绑定成功！";
		try {
			if(bisYgxxService.getBindCount(userid)>0){
				datasuccess="该账号已绑定，不可重复绑定！";
			}else{
				bisYgxxService.connectUserAccount(id,userid);
			}
		} catch (Exception e) {
			e.printStackTrace();
			datasuccess="绑定失败！";
		}
		return datasuccess;
	}
	
	/**
	 * 解除绑定用户账号信息
	 * @param id 员工id
	 */
	@RequiresPermissions("bis:ygxx:bind")
	@RequestMapping(value = "removebind/{id}")
	@ResponseBody
	public String removeUserAccount(@PathVariable("id") long id) {
		String datasuccess="解绑成功！";
		try {
			bisYgxxService.removeBind(id);
		} catch (Exception e) {
			e.printStackTrace();
			datasuccess="解绑失败！";
		}
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("bis:ygxx:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model, HttpServletResponse response, HttpServletRequest request) {
		Map<String,Object> em = bisYgxxService.findAllByID(id);		
		model.addAttribute("yglist",em);
		
		AQPX_ThreeLevelEducationHistoryEntity sjjy = sjjyHistoryService.findAllById2(id);
		BIS_EmployeeEntity yg= bisYgxxService.findInfoByID(id);
		Department dep=new Department();
		if(yg.ID4!=null&&yg.ID4!=0)
			dep= departmentService.getDepartmentById(yg.ID4);
		model.addAttribute("sjjy",sjjy);
		model.addAttribute("yg",yg);
		model.addAttribute("dep",dep);
		if(em.get("m17")!=null&&em.get("m17").toString()!=null){
			String url = ewmurl(em.get("m17").toString(),request);
			model.addAttribute("imgurl",url);
		}
		return "qyxx/ygxx/view";
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
	@RequiresPermissions("bis:ygxx:xycdexin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			resultmap = bisYgxxService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("bis:ygxx:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyname", request.getParameter("bis_ygxx_cx_id3"));
		map.put("ygname", request.getParameter("bis_ygxx_cx_m1"));
		map.put("jobnum", request.getParameter("bis_ygxx_cx_m2"));
		map.put("bm", request.getParameter("bis_ygxx_cx_bm"));
		map.put("ewm", request.getParameter("bis_ygxx_cx_m17"));
		map.put("status", request.getParameter("bis_ygxx_cx_status"));
		
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.putAll(getAuthorityMap());
		bisYgxxService.exportExcel(response,map);
	
	}
	
	/**
	 * 显示所有列
	 * @param model
	 */
	@RequiresPermissions("bis:ygxx:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","bis/ygxx/export");
		return "common/formexcel";
	}
	
	
	/**
	 * 获取员工信息list
	 * @param  idcard
	 */
	@RequestMapping(value = "ygxxlist")
	@ResponseBody
	public String sbmcJson(String idcard) {
		String qyid;
		//用户类型(1企业，0安监局，9系统管理员)
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){
				qyid=sessionuser.getQyid()+"";
		}else{
				qyid="0";
		}
		String flag = bisYgxxService.findYgxxList(idcard ,qyid);
		return flag;
	}
	
	
	/**
	 * 获取员工信息list
	 * @param qyid
	 */
	@RequestMapping(value = "ygxxlist/{qyid}")
	@ResponseBody
	public String ygxxJson(@PathVariable("qyid") String qyid) {
		List<Map<String, Object>> ygnmList=new ArrayList<Map<String, Object>>();  
			ygnmList = bisYgxxService.findYgnmList("" ,qyid);
		return JsonMapper.getInstance().toJson(ygnmList);
	}
	
	/**
	 * 获取员工信息
	 * @param id,model
	 */
	@RequestMapping(value = "ygdetail/{id}")
	@ResponseBody
	public String getQyDetailJson(@PathVariable("id") Long id) {
		Map<String,Object> be = bisYgxxService.findQyYgInfoByID(id);
		return JsonMapper.getInstance().toJson(be);
	}
	
	/**
	 * 当前登录用户对应的员工信息页面
	 * @param model
	 */
	@RequestMapping(value = "myinfor", method = RequestMethod.GET)
	public String myInfor(Model model) {	
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EmployeeEntity em =bisYgxxService.findInfoByUserID((long)sessionuser.getId());
		if(em==null){
			Map<String,Object> map = new HashMap<>();
			map.put("m17",UUID.randomUUID().toString().replaceAll("-", ""));
			model.addAttribute("yglist", map);
			model.addAttribute("action", "create2");
		}else{
			Map<String,Object> yg = bisYgxxService.findAllByID(em.getID());
			model.addAttribute("action", "updatePersonal");
			model.addAttribute("yglist",yg);
		}
		return "qyxx/ygxx/myinfor";
	}
	
	/**
	 * 生成二维码图片
	 */
	@RequestMapping(value="erm")
	@ResponseBody
	public String erweima(String ewm,HttpServletResponse response,HttpServletRequest request) {
		String url = ewmurl(ewm,request);
		return url;
	}
	
	/**
	 * 导出员工信息word
	 */
	@RequestMapping(value = "exportword/{id}")
	@ResponseBody
	public String getexportword(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		//员工基本信息
		Map<String, Object> map = bisYgxxService.getExportWord(id);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "ygxx.ftl", filePath, filename, request);
		return "/download/" + filename;
	}

	/**
	 * 导出职业健康监护档案word
	 */
	@RequestMapping(value = "exportword2/{id}")
	@ResponseBody
	public String getexportword2(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		//员工基本信息
		Map<String, Object> map = bisYgxxService.getExportWord2(id);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "zyjk.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	/**
	 * 离职信息页面跳转
	 */
	@RequestMapping(value = "addlzxx/{id}")
	public String addlzxx(@PathVariable("id") Long id,Model model) {
		model.addAttribute("ygid", id);
		model.addAttribute("action", "disable");
		return "qyxx/ygxx/lzform";
	}
	
	/**
	 * 将员工设置为离职
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disableYg(Long ygid,Timestamp lzsj) {
		String str ="success";
		BIS_EmployeeEntity yg= bisYgxxService.findInfoByID(ygid);
		yg.setStatus(2);//状态
		yg.setM15(lzsj);//离职时间
		
		if(yg.getID1()!=null){
			String userid = yg.getID1()+"";
			if( userService.isSupervisor(Integer.valueOf(userid)) ){
				str= "erro";
			}else{
				bisYgxxService.save(yg);
				//禁用员工账号
				userService.disable(Integer.valueOf(userid) );
			}
		}else{
			bisYgxxService.save(yg);
		}
		return str;
	}
	
	/**
	 * 恢复员工状态
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "enable/{id}")
	@ResponseBody
	public String enableYg(@PathVariable("id") Long id) {
		String str="恢复成功！";
		BIS_EmployeeEntity yg= bisYgxxService.findInfoByID(id);
		//恢复员工状态
		yg.setStatus(1);
		yg.setM15(null);
		bisYgxxService.save(yg);
		//恢复员工账号状态
		if(yg.getID1()!=null){
			userService.enable(Integer.parseInt(yg.getID1()+""));
		}
		return str;
	}
	
	public String ewmurl(String ewm,HttpServletRequest request){
		// 取得输出流        
		String dowmloadPath = request.getSession().getServletContext().getRealPath("/")+"/download";
		String url="/download/";
		try {
			url =url+ QRCode.encode(ewm, null, dowmloadPath, true,"");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
}
