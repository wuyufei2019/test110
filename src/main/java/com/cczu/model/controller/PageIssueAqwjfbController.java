package com.cczu.model.controller;

import java.io.IOException;
import java.sql.Timestamp;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.ISSUE_FileTransmissionReceivingEntity;
import com.cczu.model.entity.ISSUE_SecurityFileReleaseEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.IIssueAqwjfbService;
import com.cczu.model.service.IIssueWjcdjsService;
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
 * 安全文件信息发布controller
 * 
 * @author jason
 * 
 */
@Controller
@RequestMapping("issue/aqwjfb")
public class PageIssueAqwjfbController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IIssueAqwjfbService issueAqwjfbService;
	@Autowired
	private UserService userService;
	@Autowired
	private IIssueWjcdjsService issueWjcdjsService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private IMsgService msgService;

	/**
	 * 默认页面
	 */
	@RequestMapping(value = "index")
	public String index(Model model,HttpServletRequest request) {
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		// 获取用户角色
		List<Role> list = roleService.findRoleById(sessionuser.getId());
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				// 判断用户角色，如果包含有企业用户角色，则转到企业用户显示的界面
				if (list.get(i).getRoleCode().equals("company")|| list.get(i).getRoleCode().equals("companyadmin")){
					if(request.getParameter("sys")!=null &&"1".equals(request.getParameter("sys")))
						model.addAttribute("sys", "sys");					
					return "issue/aqwjfb/index_qy";
				}
			}
		}
		model.addAttribute("usertype", sessionuser.getUsertype());
		return "issue/aqwjfb/index";
	}
	/**
	 * 企业选择页面跳转
	 * @param model
	 */
	@RequiresPermissions("issue:aqwjfb:view")
	@RequestMapping(value = "choose/{flag}")
	public String choose(@PathVariable("flag")String flag,Model model) {
		model.addAttribute("action", "choose");
		if("add".equals(flag)){
			model.addAttribute("flag","add");
		}else{
			model.addAttribute("flag",flag);
		}
		return "issue/aqwjfb/index_qychoose";
	}
	/**
	 * 选择企业list页面
	 */
	@RequiresPermissions("issue:aqwjfb:add")
	@RequestMapping(value = "qylist/{ID}")
	@ResponseBody
	public Map<String, Object> getQyList(@PathVariable("ID") String id,HttpServletRequest request) {
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		Map<String, Object> map = new HashMap<String, Object>();
		String xzqy=request.getParameter("xzqy");
		if(StringUtils.isBlank(xzqy)){
			map.put("xzqy",sessionuser.getXzqy());
		}else{
			map.put("xzqy",xzqy);
		}
		//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
		if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
			map.put("jglx",sessionuser.getUserroleflg());
		map.put("qyname", request.getParameter("qynm"));
		map.put("qygm", request.getParameter("qygm"));//规模情况
		map.put("xj", request.getParameter("xj"));//是否下级
		if (!"add".equals(id)) {
			String qyids=issueAqwjfbService.getqyids(Long.parseLong(id));
			//发布文件的id
			map.put("bis_ids",qyids.substring(0,  qyids.length()-1));
			
		}
		return bisQyjbxxService.findAllQyList(map);

	}

	/**
	 * list页面
	 */
	@RequiresPermissions("issue:aqwjfb:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		Map<String, Object> map = getPageMap(request);
		map.put("wjname", request.getParameter("issue_aqwjfb_cx_m1"));
		map.put("datestart", request.getParameter("check_company_starttime"));
		map.put("dateend", request.getParameter("check_company_endtime"));
		map.put("sort", request.getParameter("sort"));
		map.put("order", request.getParameter("order"));
		if(sessionuser.getUsertype().equals("0")){//安监用户  添加网格区域编码查询条件
			map.put("xzqy",sessionuser.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
				map.put("jglx",sessionuser.getUserroleflg());
		}
		return issueAqwjfbService.dataGrid(map);
	}

	/**
	 * 企业用户查看的 list2页面
	 */
	@RequiresPermissions("issue:aqwjfb:view")
	@RequestMapping(value = "list2")
	@ResponseBody
	public Map<String, Object> getData2(HttpServletRequest request) {
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		Map<String, Object> map = getPageMap(request);
		map.put("wjname", request.getParameter("issue_aqwjfb_cx_m1"));
		map.put("datestart", request.getParameter("check_company_starttime"));
		map.put("dateend", request.getParameter("check_company_endtime"));
		map.put("order", request.getParameter("order"));
		map.put("uid", sessionuser.getQyid());
		map.put("islook",request.getParameter("issue_islook"));
		// 查询该企业的安全文件的浏览下载情况
		return issueAqwjfbService.dataGrid2(map);

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
		return issueAqwjfbService.dataGrid2(map);
		
	}

	/**
	 * 添加安全文件信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("issue:aqwjfb:add")
	@RequestMapping(value = "create")
	public String create(Model model) {
		model.addAttribute("action", "createSub");
		return "issue/aqwjfb/form";
	}

	/**
	 * 添加安全文件信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("issue:aqwjfb:add")
	@RequestMapping(value = "createSub", method = RequestMethod.POST)
	@ResponseBody
	public String createSub(ISSUE_SecurityFileReleaseEntity sfr,
			HttpServletRequest request, Model model) throws IOException {
		String datasuccess = "failed";
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		long ID = sessionuser.getId(); // 当前用户id
		// String paths=UPload(request, model);//文件保存路径
		Timestamp t = DateUtils.getSysTimestamp();
		String qyids = request.getParameter("qyids");
		sfr.setID1(ID);// D1用户id
		sfr.setS1(t);// s1发布时间
		sfr.setS2(t);
		sfr.setS3(0);// s3删除标识
		sfr.setM2(request.getParameter("M2"));
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isBlank(qyids)){
			map.put("xzqy", sessionuser.getXzqy());
			List<BIS_EnterpriseEntity> list=bisQyjbxxService.dataListE(map);
			for(BIS_EnterpriseEntity bee:list){
				qyids+=bee.getID()+",";
			}
		}
		sfr.setQyids(qyids);
		String filePath = request.getSession().getServletContext().getRealPath("/");
		Long newID = issueAqwjfbService.addInforReturnID(sfr,filePath);
		if (newID > 0) {
			// 发布安全文件后，将文件推送给企业，保存记录在issue_filetransmissionreceiving表中
			map.put("wjid",newID);
			map.put("qyids",qyids);
			if (StringUtils.isNotBlank(qyids))// 发布文件新增企业的qyids字段，用于更新发布文件的企业名称修改
				map.put("qyids", qyids.substring(0, qyids.length() - 1));
			//插入数据到接收表
			issueWjcdjsService.addInfor(map);
			map.put("uid", ID);
			map.put("content",sfr.getM1());
			msgService.addWjInfo(map);
			datasuccess = "success";
		}
		// 返回结果
		return datasuccess;
	}

	/**
	 * 修改安全文件信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("issue:aqwjfb:update")
	@RequestMapping(value = "update/{id}")
	public String update(@PathVariable("id") long id, Model model) {

		ISSUE_SecurityFileReleaseEntity sfr = issueAqwjfbService
				.findInfoById(id);
		String qyids = sfr.getQyids();
		// model.addAttribute("qyids",qyids);
		if (StringUtils.isNotBlank(qyids))
			qyids = qyids.substring(0, qyids.length() - 1);
		List<BIS_EnterpriseEntity> list = bisQyjbxxService.findAllQy(qyids);
		// 字符串拼接：id||name，
		String idname = "";
		if (list != null && list.size() > 0) {
			for (BIS_EnterpriseEntity bis : list) {
				idname = idname + bis.getID() + "||" + bis.getM1() + ",";
			}
		}

		model.addAttribute("idname", idname.substring(0, idname.length() - 1));
		model.addAttribute("sfr", sfr);
		model.addAttribute("action", "updateSub");
		return "issue/aqwjfb/form";
	}

	/**
	 * 修改安全文件信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("issue:aqwjfb:update")
	@RequestMapping(value = "updateSub")
	@ResponseBody
	public String updateSub(ISSUE_SecurityFileReleaseEntity sfr,
			HttpServletRequest request, Model model) {
		String datasuccess = "failed";
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		Map<String, Object> map = new HashMap<String, Object>();
		Timestamp t = DateUtils.getSysTimestamp();
		String qyids = request.getParameter("qyids");
		sfr.setS2(t);
		sfr.setQyids(qyids);
		sfr.setM2(request.getParameter("M2"));
		// 更新发布文件
		String filePath = request.getSession().getServletContext().getRealPath("/");
		if (issueAqwjfbService.updateInfoByID(sfr.getID(), sfr, filePath) > 0)
		//修改接收端信息
		if (StringUtils.isNotBlank(qyids)){
			map.put("qyids", qyids.substring(0, qyids.length() - 1));// 企业用户
		}else{
			map.put("xzqy", sessionuser.getXzqy());
			List<BIS_EnterpriseEntity> list=bisQyjbxxService.dataListE(map);
			for(BIS_EnterpriseEntity bee:list){
				qyids+=bee.getID()+",";
			}
			map.put("qyids", qyids.substring(0, qyids.length() - 1));// 企业用户
		}
		//1、删除 接收端信息 2、重新添加
		issueWjcdjsService.deleteInfoByFid(sfr.getID());
		map.put("wjid",sfr.getID());//文件id
		issueWjcdjsService.addInfor(map);
		//重新添加msg提醒
		map.put("uid", UserUtil.getCurrentShiroUser().getId());
		map.put("content",sfr.getM1());
		msgService.addWjInfo(map);
		datasuccess = "success";
		return datasuccess;
	}
	
	/**
	 * 系统新增的企业或者需要补发给某些企业，更新文件qyids
	 * 
	 * @param model
	 */
	@RequiresPermissions("issue:aqwjfb:update")
	@RequestMapping(value = "updateqyids")
	@ResponseBody
	public String updateIssueQyids(HttpServletRequest request, Model model) {
		String datasuccess = "success";
		Long id= Long.parseLong(request.getParameter("id"));
		String qyids= request.getParameter("qyids");
		ISSUE_SecurityFileReleaseEntity sfr=issueAqwjfbService.findInfoById(id);
		sfr.setQyids(sfr.getQyids()+qyids);
		try {
			String filePath = request.getSession().getServletContext().getRealPath("/");
			issueAqwjfbService.updateInfoByID(id, sfr, filePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			datasuccess="failed";
		}
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(qyids))
			map.put("qyids", qyids.substring(0, qyids.length() - 1));// 企业用户
		//插入数据到接收表
		map.put("wjid",sfr.getID());
		issueWjcdjsService.addInfor(map);
		map.put("uid", sessionuser.getId());
		map.put("content",sfr.getM1());
		msgService.addWjInfo(map);
		datasuccess = "success";
		return datasuccess;
	}

	
	

	/**
	 * 查看安全文件信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("issue:aqwjfb:view")
	@RequestMapping(value = "view/{id}")
	public String view(@PathVariable("id") long id, Model model) {
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		ISSUE_SecurityFileReleaseEntity sfr = issueAqwjfbService
				.findInfoById(id);
		long qyid = sessionuser.getQyid();//当前用户企业id
		issueWjcdjsService.updateIsorNotLook(qyid, sfr.getID());

		// String paths[]=sfr.getM3().split(";");//将多个文件路径分开，分隔符；
		// Map<String, String> downmap=new HashMap<>();
		/*
		 * for(int i=1;i<=paths.length;i++){
		 * if(paths[i-1]!=null&&paths[i-1].trim().equals("")==false)
		 * downmap.put("附件"+i, paths[i-1]); }
		 */
		// model.addAttribute("downmap", downmap);
		model.addAttribute("sfr", sfr);
		model.addAttribute("action", "view");
		return "issue/aqwjfb/view";
	}

	/**
	 * 删除安全文件信息
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("issue:aqwjfb:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			// 删除安全文件信息
			issueAqwjfbService.deleteInfo(Long.parseLong(arrids[i]));
			// 删除安全文件信息 传递与接收信息
			issueWjcdjsService.deleteInfoByFid(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 查询安全文件信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("issue:aqwjfb:view")
	@RequestMapping(value = "search/{id}")
	public String search(@PathVariable("id") long id, Model model) {

		ISSUE_SecurityFileReleaseEntity sfr = issueAqwjfbService
				.findInfoById(id);
		model.addAttribute("sfr", sfr);
		model.addAttribute("action", "view");
		return "issue/aqwjfb/form";
	}

	/**
	 * 查询用户未读文件的信息
	 * 
	 */
	@RequiresPermissions("issue:aqwjfb:view")
	@RequestMapping(value = "searchNoRead", method = RequestMethod.GET)
	@ResponseBody
	public String searchNoRead() {
		String datasuccess = "null";
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		long ID = sessionuser.getId(); // 当前用户id
		List<ISSUE_FileTransmissionReceivingEntity> list = issueWjcdjsService
				.findInfoByUId(ID);
		if (list.size() > 0)
			datasuccess = "您有新下载文件，注意查看！";
		return datasuccess;
	}

	/**
	 * 查询用户未读文件的信息
	 * 
	 */
	@RequestMapping(value = "searchNoRead2", method = RequestMethod.GET)
	@ResponseBody
	public String searchNoRead2() {
		String datasuccess = "";
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		long ID = sessionuser.getId(); // 当前用户id
		List<ISSUE_FileTransmissionReceivingEntity> list = issueWjcdjsService
				.findInfoByUId(ID);
		datasuccess = String.valueOf(list.size());
		return datasuccess;
	}

	/**
	 * 下载文件保存记录
	 */
	@RequiresPermissions("issue:aqwjfb:view")
	@RequestMapping(value = "down/{id}")
	@ResponseBody
	public void down(@PathVariable("id") long id, Model model) {
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			long qyid = sessionuser.getQyid(); // 当前用户企业id
			issueWjcdjsService.updateIsorNotDownload(qyid, id);
		}

	}
	
	/**
	 * 查询文件查看和下载情况的具体企业用户
	 * 
	 */
	@RequiresPermissions("issue:aqwjfb:view")
	@RequestMapping(value = "showqylist", method = RequestMethod.GET)
	public String showqylist(@RequestParam(value="wjid", defaultValue="0")int wjid,
			@RequestParam(value="state", defaultValue="0")int state,
			@RequestParam(value="type", defaultValue="0")int type, Model model) {
		 
		List<Map<String, Object>> list=issueWjcdjsService.findUserListByState(wjid, state, type);
		model.addAttribute("list", JsonMapper.getInstance().toJson(list));
		return "issue/aqwjfb/qylist";
	}

}
