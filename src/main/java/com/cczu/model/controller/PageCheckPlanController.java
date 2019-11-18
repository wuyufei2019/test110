package com.cczu.model.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.AQJD_CheckPlanEntity;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IAqjgCheckPlanService;
import com.cczu.model.service.IAqjgCheckRecordService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.IMsgService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 安全监管-检查计划controller
 * 
 * @author jason
 * 
 */
@Controller
@RequestMapping("aqjd/jcjh")                                                                                                    
public class PageCheckPlanController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private IMsgService msgService;
	@Autowired
	private IAqjgCheckPlanService aqjgcheckplanservice;
	@Autowired
	private  IBisQyjbxxService bisqyjbxxservice;
	@Autowired
	private IAqjgCheckRecordService aqjgcheckrecordservice;

	/**
	 * 默认页面
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		// 获取用户角色
		List<Role> list = roleService.findRoleById(sessionuser.getId());
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				// 判断用户角色，如果包含有企业用户角色，则转到企业用户显示的界面
				if (list.get(i).getRoleCode().equals("company")
						|| list.get(i).getRoleCode().equals("companyadmin"))
					return "aqjg/aqjdjc/jcjh/index_qy";
			}
		}
		return "aqjg/aqjdjc/jcjh/index";
	}

	/**
	 * list页面
	 */
	@RequiresPermissions("aqjd:jcjh:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("name", request.getParameter("aqjg_jcjh_name"));
		map.put("year", request.getParameter("aqjg_jcjh_year"));
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("0")){//安监用户  添加网格区域编码查询条件
			map.put("uxzqy",sessionuser.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
				map.put("ujglx",sessionuser.getUserroleflg());
		}
		else if(sessionuser.getUsertype().equals("1")){//0是安监局，1为企业
		map.put("id", sessionuser.getQyid());
		}
		return aqjgcheckplanservice.dataGrid(map);
	}
	
	/**
	 * 检查记录记录点击百分比进度页面跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "jhresult/{id}")
	String jhresult(@PathVariable("id")String id,Model model) {
		try {
			model.addAttribute("ID",id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "aqjg/aqjdjc/jcjh/jhjdview";
	}
	
	/**
	 * 系统新增的企业或者需要补发计划给某些企业，更新检查计划的qyids
	 * 
	 * @param model
	 */
	@RequiresPermissions("aqjd:jcjh:update")
	@RequestMapping(value = "updateqyids")
	@ResponseBody
	public String updateIssueQyids(HttpServletRequest request, Model model) {
		String datasuccess = "success";
		Long id= Long.parseLong(request.getParameter("id"));
		String qyids= request.getParameter("qyids");
		AQJD_CheckPlanEntity cpe=aqjgcheckplanservice.findInfoById(id);
		cpe.setQyids(cpe.getQyids()+qyids);
		try {
			aqjgcheckplanservice.updateInfo(cpe);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			datasuccess="failed";
		}
		return datasuccess;
	}

	
	
	/**
	 * list页面(用于检查计划list页面点击进度进行查看的具体企业进度信息页面)(选择企业数量/未完成企业数量)
	 */
	@RequestMapping(value = "list/{id}/{flag}")
	@ResponseBody
	public Map<String, Object> getData(@PathVariable("id") String id,@PathVariable("flag") String flag,HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("jhid", id);
		map.put("jdflag", flag);
		return aqjgcheckplanservice.dataGrid2(map);
	}
	
	
	/**
	 * list页面(用于检查计划list页面点击进度进行查看的具体企业进度信息页面)未完成/已完成/安监初查/安监复查
	 */
	@RequestMapping(value = "list2/{id}/{flag}")
	@ResponseBody
	public Map<String, Object> getData2(@PathVariable("id") String id,@PathVariable("flag")String flag,HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("flag", flag);
		map.put("jhid", id);
		return aqjgcheckrecordservice.dataGrid(map);
	}
	
	
	/**
	 * 首页提醒
	 * 
	 * @param request
	 */
	@RequestMapping(value = "html")
	@ResponseBody
	public Map<String, Object>  getHtml(HttpServletRequest request, Model model) {
		Map<String, Object> map = getPageMap(request);
		Map<String, Object> remap = new HashMap<>();//返回值
		ShiroUser user= UserUtil.getCurrentShiroUser();
		map.put("year", DateUtils.getYear());
		map.put("month", Integer.parseInt(DateUtils.getMonth()));
		if(user.getUsertype().equals("1")){//0是企业，1为安监局
		map.put("id", user.getQyid());
		}
		remap.put("rows",aqjgcheckplanservice.dataGrid(map).get("rows") );
		remap.put("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return remap;
	}
	

	/**
	 * 添加计划信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("aqjd:jcjh:add")
	@RequestMapping(value = "create")
	public String create(Model model) {
		model.addAttribute("action", "createSub");
		return "aqjg/aqjdjc/jcjh/form";
	}

	/**
	 * 添加计划
	 * @param request
	 */
	@RequiresPermissions("aqjd:jcjh:add")
	@RequestMapping(value = "createSub", method = RequestMethod.POST)
	@ResponseBody
	public String createSub(AQJD_CheckPlanEntity cpe,
			HttpServletRequest request, Model model) throws IOException {
		String datasuccess = "failed";
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		Timestamp t = DateUtils.getSysTimestamp();
		String qyids = request.getParameter("qyids");
		cpe.setS1(t);
		cpe.setS2(t);
		cpe.setS3(0);
		cpe.setID1(sessionuser.getId());//创建者ID
		Map<String, Object> map = new HashMap<String, Object>();
		//当用户没有选择企业时，默认选择全部企业
		if(StringUtils.isBlank(qyids)){
			map.put("xzqy", sessionuser.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
				map.put("jglx",sessionuser.getUserroleflg());
			List<BIS_EnterpriseEntity> list=bisQyjbxxService.dataListE(map);
			for(BIS_EnterpriseEntity bee:list){
				qyids+=bee.getID()+",";
			}
		}
		cpe.setQyids(qyids);
		//因为用对象获取富文本会被转义，所以单独用request获取
		cpe.setM3(request.getParameter("M3"));
		Long newID = aqjgcheckplanservice.addInfoReturnID(cpe);
		//添加成功
		if (newID > 0) {
			datasuccess = "success";
			//msg提醒
			msgService.addAllInfo((long)sessionuser.getId(), cpe.getM1(), "有新的检查计划发布", "5", "2", qyids.substring(0, qyids.length() - 1));
		}
		return datasuccess;
	}

	/**
	 * 修改计划信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("aqjd:jcjh:update")
	@RequestMapping(value = "update/{id}")
	public String update(@PathVariable("id") long id, Model model) {
		AQJD_CheckPlanEntity cpe = aqjgcheckplanservice.findInfoById(id);
		String qyids = cpe.getQyids();
		List<BIS_EnterpriseEntity> list = bisQyjbxxService.findAllQy(qyids.substring(0, qyids.length()-1));
		// 字符串拼接：id||name，
		String idname = "";
		if (list != null && list.size() > 0) {
			for (BIS_EnterpriseEntity bis : list) {
				idname = idname + bis.getID() + "||" + bis.getM1() + ",";
			}
		}
		model.addAttribute("idname", idname.substring(0, idname.length() - 1));
		model.addAttribute("cpe", cpe);
		model.addAttribute("action", "updateSub");
		return "aqjg/aqjdjc/jcjh/form";
	}

	/**
	 * 修改检查计划信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("aqjd:jcjh:update")
	@RequestMapping(value = "updateSub")
	@ResponseBody
	public String updateSub(AQJD_CheckPlanEntity cpe,
			HttpServletRequest request, Model model) {
		String datasuccess = "success";
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		Timestamp t = DateUtils.getSysTimestamp();
		String qyids = request.getParameter("qyids");
		Map<String, Object> map = new HashMap<String, Object>();
		//当用户没有选择企业时，默认选择全部企业
		if(StringUtils.isBlank(qyids)){
			map.put("xzqy", sessionuser.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
				map.put("jglx",sessionuser.getUserroleflg());
			List<BIS_EnterpriseEntity> list=bisQyjbxxService.dataListE(map);
			for(BIS_EnterpriseEntity bee:list){
				qyids+=bee.getID()+",";
			}
		}
		cpe.setS2(t);
		cpe.setQyids(qyids);
		cpe.setID1(sessionuser.getId());
		//因为用对象获取富文本会被转义，所以单独用request获取
		cpe.setM3(request.getParameter("M3"));
		try {
			aqjgcheckplanservice.updateInfo(cpe);
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="failed";
		}
		//批量添加消息提醒
		msgService.addAllInfo((long)sessionuser.getId(), cpe.getM1(), "有新的检查计划发布", "5", "2", qyids.substring(0, qyids.length() - 1));
		
		return datasuccess;
	}

	/**
	 * 查看计划信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("aqjd:jcjh:view")
	@RequestMapping(value = "view/{id}")
	public String view(@PathVariable("id") long id, Model model) {
		AQJD_CheckPlanEntity cpe = aqjgcheckplanservice.findInfoById(id);
		model.addAttribute("cpe", cpe);
		return "aqjg/aqjdjc/jcjh/view";
	}

	/**
	 * 删除计划信息
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("aqjd:jcjh:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			aqjgcheckplanservice.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 企业选择页面跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "choose/{flag}")
	public String choose(@PathVariable("flag")String flag,Model model) {
		model.addAttribute("action", "choose");
		if("add".equals(flag)){
			model.addAttribute("flag","add");
		}else{
			model.addAttribute("flag",flag);
		}
		return "aqjg/aqjdjc/jcjh/index_qychoose";
	}
	
	/**
	 * 选择企业list页面
	 * 发送文件至新增企业ID值为计划id，在新增或修改检查计划的时候id值为add
	 */
	@RequestMapping(value = "qylist/{ID}")
	@ResponseBody
	public Map<String, Object> getQyList(@PathVariable("ID") String id,HttpServletRequest request) {
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("xzqy", sessionuser.getXzqy());
		if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
			map.put("jglx",sessionuser.getUserroleflg());
		map.put("qyname", request.getParameter("qynm"));
		if (!"add".equals(id)) {
			String qyids=aqjgcheckplanservice.getqyids(Long.parseLong(id));
			//计划的id
			map.put("bis_ids",qyids.substring(0,  qyids.length()-1));
		}
		return bisQyjbxxService.findAllQyList(map);

	}

	/**
	 * 获取年份（废弃不用）
	 * @param id,model
	 */
	@RequiresPermissions("aqjd:jcjh:view")
	@RequestMapping(value = "year")
	@ResponseBody
	public String getyear() {
		Object[] objs=aqjgcheckplanservice.getMaxYearAndMinYear();
		int maxyear= Integer.parseInt(objs[0].toString());
		int minyear= Integer.parseInt(objs[1].toString());
		List<Map<String, Object>> yearlist=new ArrayList<Map<String, Object>>();  
		Map<String, Object> map;
		for(int i=minyear;i<=maxyear;i++){
			map=new HashMap<String,Object>();
			map.put("id", i);
			map.put("text", i);
			yearlist.add(map);
		}
		return JsonMapper.getInstance().toJson(yearlist);
	}
	
}
