package com.cczu.model.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.BIS_Utilities;
import com.cczu.model.service.AqjgAqbaService;
import com.cczu.model.service.BisGygcService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 公用工程controller
 */
@Controller
@RequestMapping("bis/gygc")
public class PageBisGygcController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private BisGygcService bisGygcService;
	@Autowired
	private AqjgAqbaService aqjgAqbaService;
	
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
					return "qyxx/gygc/adminindex";
				else
					return "qyxx/gygc/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "qyxx/gygc/adminindex";
		}	
	}
	
	/**
	 * list页面（企业用户显示界面）
	 * @param request
	 */
	@RequiresPermissions("bis:gygc:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		
		if(be!=null){
			map.put("qyid", be.getID());
		}
		map.put("m1", request.getParameter("bis_gygc_cx_m1"));
		map.put("m3", request.getParameter("bis_gygc_cx_m3"));
		return bisGygcService.dataGrid(map);
		
	}

	/**
	 * list页面（非企业用户显示界面）
	 * @param request
	 */
	@RequiresPermissions("bis:gygc:view")
	@RequestMapping(value="list2")
	@ResponseBody
	public Map<String, Object> getData2(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("m1", request.getParameter("bis_gygc_cx_m1"));
		map.put("m3", request.getParameter("bis_gygc_cx_m3"));
		map.put("qyname", request.getParameter("bis_gygc_cx_id1"));
		map.putAll(getAuthorityMap());
		return bisGygcService.dataGrid2(map);
		
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
		return bisGygcService.dataGrid(map);
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("bis:gygc:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "qyxx/gygc/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequiresPermissions("bis:gygc:add")
	@RequestMapping(value = "create" )
	@ResponseBody
	public String create(@Valid BIS_Utilities gygc, Model model) {
		String datasuccess="success";				
		bisGygcService.addInfo(gygc);
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			long ID = be.getID();//获取到企业id
			gygc.setID1(ID);
		}
		Timestamp t=DateUtils.getSysTimestamp();
		gygc.setS1(t);
		gygc.setS2(t);
		gygc.setS3(0);
		bisGygcService.addInfo(gygc);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--公用工程信息  【添加操作】");

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
		BIS_Utilities gygc = bisGygcService.findById(id);
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("gygclist", gygc);
		//返回页面
		model.addAttribute("action", "update");
		return "qyxx/gygc/form";
	}
	
	/**
	 * 修改公用工程信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(BIS_Utilities gygc, Model model){
		String datasuccess="success";	
		Timestamp t=DateUtils.getSysTimestamp();
		gygc.setS2(t);
		
		bisGygcService.updateInfo(gygc);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--公用工程信息  【修改操作】");

		//返回结果
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
			bisGygcService.deleteInfo(Long.parseLong(aids[i]));
		}
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--公用工程信息  【删除操作】");

		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		//查询选择的安全备案信息
		BIS_Utilities gygc = bisGygcService.findById(id);		
		model.addAttribute("gygclist", gygc);
		//返回页面
		model.addAttribute("action", "view");
		return "qyxx/gygc/view";
	}
	
	/**
	 * 获取企业list
	 * @param id,model
	 */
	@RequestMapping(value = "qylist")
	@ResponseBody
	public String getUserJson() {
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
	@RequiresPermissions("bis:gygc:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","bis/gygc/export");
		return "common/formexcel";
	}
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("bis:gygc:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("colval", request.getParameter("colval"));	
		map.put("coltext", request.getParameter("coltext"));
		map.put("m1", request.getParameter("bis_gygc_cx_m1"));
		map.put("m3", request.getParameter("bis_gygc_cx_m3"));
		map.put("qyname", request.getParameter("bis_gygc_cx_id1"));
		map.putAll(getAuthorityMap());
		bisGygcService.exportExcel(response, map);
	}
	
	
	//导入页面跳转
	@RequestMapping(value = "exinjump", method = RequestMethod.GET)
	public String exin(Model model) {
		model.addAttribute("action", "exin");
		return "common/importForm";
	}
	
	/**
	 * 导入
	 * @param response
	 * @param request
	 * @return
	 */
	@RequiresPermissions("bis:gygc:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			resultmap = bisGygcService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}
		
}
