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
import com.cczu.model.entity.ISSUE_SecurityFileReleaseEntity;
import com.cczu.model.entity.ISSUE_ZfFileTransmissionReceivingEntity;
import com.cczu.model.entity.ISSUE_GovermentFileReleaseEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.IIssueAqwjfbService;
import com.cczu.model.service.IIssueWjcdjsService;
import com.cczu.model.service.IIssueZfwjfbService;
import com.cczu.model.service.IMsgService;
import com.cczu.model.service.IssueZfWjcdjsService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.entity.User;
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
@RequestMapping("issue/zfwjfb")
public class PageIssueZfwjfbController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IIssueZfwjfbService issueZfwjfbService;
	@Autowired
	private UserService userService;
	@Autowired
	private IssueZfWjcdjsService issueZfWjcdjsService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private IMsgService msgService;
	@Autowired
	private IIssueAqwjfbService issueAqwjfbService;
	@Autowired
	private IIssueWjcdjsService issueWjcdjsService;

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
					return "issue/zfwjfb/index_qy";
			}
		}
		model.addAttribute("usertype", sessionuser.getUsertype());
		return "issue/zfwjfb/index";
	}
	/**
	 * 政府人员选择页面跳转
	 * @param model
	 */
	@RequiresPermissions("issue:zfwjfb:view")
	@RequestMapping(value = "choose/{flag}")
	public String choose(@PathVariable("flag")String flag,Model model) {
		model.addAttribute("action", "choose");
		if("add".equals(flag)){
			model.addAttribute("flag","add");
		}else{
			model.addAttribute("flag",flag);
		}
		return "issue/zfwjfb/index_zfchoose";
	}
	
	/**
	 * 选择政府工作人员list页面
	 */
	@RequiresPermissions("issue:zfwjfb:add")
	@RequestMapping(value = "zflist/{ID}")
	@ResponseBody
	public Map<String, Object> getQyList(@PathVariable("ID") String id,HttpServletRequest request) {
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("xzqy",sessionuser.getXzqy());
		map.put("ryxzqy", request.getParameter("wghgl_zfwjfb_xzqy"));
		map.put("zfname", request.getParameter("zfnm"));
		if (!"add".equals(id)){ 
			//发布文件的id
			map.put("id", id);
		}
			//不包括自己的id
			map.put("userid", sessionuser.getId());
		return issueZfwjfbService.findAllZfList(map);
	}

	/**
	 * 企业选择页面跳转
	 * @param model
	 */
	@RequiresPermissions("issue:zfwjfb:view")
	@RequestMapping(value = "chooseqy/{wjid}")
	public String chooseqy(@PathVariable("wjid")String wjid,Model model) {
		model.addAttribute("wjid",wjid);
		return "issue/zfwjfb/index_qychoose";
	}

	/**
	 * 选择企业list页面
	 */
	@RequiresPermissions("issue:zfwjfb:add")
	@RequestMapping(value = "qylist/{ID}")
	@ResponseBody
	public Map<String, Object> getQyList2(@PathVariable("ID") String id,HttpServletRequest request) {
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("xzqy",sessionuser.getXzqy());
		//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
		if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
			map.put("jglx",sessionuser.getUserroleflg());
		map.put("qyname", request.getParameter("qynm"));
		if(!"0".equals(id)){
			map.put("id", id);
		}
		return bisQyjbxxService.findAllQyList(map);
	}

	/**
	 * 添加安全文件信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("issue:zfwjfb:add")
	@RequestMapping(value = "createSub2", method = RequestMethod.POST)
	@ResponseBody
	public String createSub2(HttpServletRequest request, Model model) {
		String datasuccess = "failed";
		Long id= Long.parseLong(request.getParameter("id"));//政府文件id
		String qyids= request.getParameter("qyids");
		//用政府文件内容给企业文件内容赋值
		ISSUE_GovermentFileReleaseEntity gov=issueZfwjfbService.findInfoById(id);
		ISSUE_SecurityFileReleaseEntity sfr=new ISSUE_SecurityFileReleaseEntity();
		// ID1用户id
		sfr.setID1(gov.getID1());
		sfr.setM1(gov.getM1());
		sfr.setM2(gov.getM2());
		sfr.setM3(gov.getM3());
		sfr.setM4(gov.getM4());
		sfr.setM5(gov.getM5());
		// String paths=UPload(request, model);//文件保存路径
		Timestamp t = DateUtils.getSysTimestamp();
		// s1发布时间
		sfr.setS1(t);
		sfr.setS2(t);
		// s3删除标识
		sfr.setS3(0);
		sfr.setM2(request.getParameter("M2"));
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isBlank(qyids)){
			map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
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
			if (StringUtils.isNotBlank(qyids))// 发布文件新增企业的qyids字段，用于更新发布文件的企业名称修改
				map.put("qyids", qyids.substring(0, qyids.length() - 1));
			//插入数据到接收表
			issueWjcdjsService.addInfor(map);
			map.put("uid", gov.getID1());
			map.put("content",sfr.getM1());
			msgService.addWjInfo(map);
			datasuccess = "success";
		}
		
		//将企业文件id插入政府文件的ID2
		gov.setID2(newID);
		issueZfwjfbService.updateInfoByID(gov.getID(), gov, filePath);
		
		// 返回结果
		return datasuccess;
	}
	
	/**
	 * 系统新增的企业或者需要补发给某些企业，更新企业文件的qyids
	 * 
	 * @param model
	 */
	@RequiresPermissions("issue:zfwjfb:update")
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
	 * list页面
	 */
	@RequiresPermissions("issue:zfwjfb:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		Map<String, Object> map = getPageMap(request);
		map.put("wjname", request.getParameter("issue_zfwjfb_cx_m1"));
		map.put("datestart", request.getParameter("check_company_starttime"));
		map.put("dateend", request.getParameter("check_company_endtime"));
		map.put("sort", request.getParameter("sort"));
		map.put("order", request.getParameter("order"));
		map.put("id1", sessionuser.getId());
		return issueZfwjfbService.dataGrid(map);
	}

	/**
	 * 安监用户查看的 list2页面
	 */
	@RequiresPermissions("issue:zfwjfb:view")
	@RequestMapping(value = "list2")
	@ResponseBody
	public Map<String, Object> getData2(HttpServletRequest request) {
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		Map<String, Object> map = getPageMap(request);
		map.put("wjname", request.getParameter("issue_zfwjfb_cx_m12"));
		map.put("datestart", request.getParameter("check_company_starttime2"));
		map.put("dateend", request.getParameter("check_company_endtime2"));
		map.put("order", request.getParameter("order"));
		map.put("uid", sessionuser.getId());
		map.put("islook",request.getParameter("issue_islook2"));
		// 查询该企业的安全文件的浏览下载情况
		return issueZfwjfbService.dataGrid2(map);

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
		return issueZfwjfbService.dataGrid2(map);
		
	}

	/**
	 * 添加安全文件信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("issue:zfwjfb:add")
	@RequestMapping(value = "create")
	public String create(Model model) {
		model.addAttribute("action", "createSub");
		return "issue/zfwjfb/form";
	}

	

	/**
	 * 添加安全文件信息
	 * 
	 * @param request,model
	 *            
	 */
	@RequiresPermissions("issue:zfwjfb:add")
	@RequestMapping(value = "createSub", method = RequestMethod.POST)
	@ResponseBody
	public String createSub(ISSUE_GovermentFileReleaseEntity sfr,
			HttpServletRequest request, Model model) throws IOException {
		String datasuccess = "failed";
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		long ID = sessionuser.getId(); // 当前用户id
		// String paths=UPload(request, model);//文件保存路径
		Timestamp t = DateUtils.getSysTimestamp();
		String zfids = request.getParameter("zfids");
		// D1用户id
		sfr.setID1(ID);
		// s1发布时间
		sfr.setS1(t);
		sfr.setS2(t);
		// s3删除标识
		sfr.setS3(0);
		sfr.setM2(request.getParameter("M2"));
		Map<String, Object> map = new HashMap<String, Object>();
		//如果没选的话，默认选择所有
		if(StringUtils.isBlank(zfids)){
			map.put("xzqy", sessionuser.getXzqy());
			//因为发送人本身为安监局人员，所以不包括自己的id
			map.put("userid", sessionuser.getId());
			List<User> list=issueZfwjfbService.dataListE(map);
			for(User bee:list){
				zfids+=bee.getId()+",";
			}
		}
		sfr.setZfids(zfids);
		String filePath = request.getSession().getServletContext().getRealPath("/");
		Long newID = issueZfwjfbService.addInforReturnID(sfr,filePath);
		if (newID > 0) {
			// 发布安全文件后，将文件推送给政府工作人员，保存记录在issue_filetransmissionreceiving表中
			map.put("wjid",newID);
			if (StringUtils.isNotBlank(zfids))// 发布文件新增企业的zfids字段，用于更新发布文件的企业名称修改
				map.put("zfids", zfids.substring(0, zfids.length() - 1));
			//插入数据到接收表
			issueZfWjcdjsService.addInfor(map);
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
	@RequiresPermissions("issue:zfwjfb:update")
	@RequestMapping(value = "update/{id}")
	public String update(@PathVariable("id") long id, Model model) {

		ISSUE_GovermentFileReleaseEntity sfr = issueZfwjfbService
				.findInfoById(id);
		String zfids = sfr.getZfids();
		// model.addAttribute("zfids",zfids);
		if (StringUtils.isNotBlank(zfids))
			zfids = zfids.substring(0, zfids.length() - 1);
		List<User> list = issueZfwjfbService.findAllUser(zfids);
		// 字符串拼接：id||name，
		String idname = "";
		if (list != null && list.size() > 0) {
			for (User user : list) {
				idname = idname + user.getId() + "||" + user.getName() + ",";
			}
		}

		model.addAttribute("idname", idname.substring(0, idname.length() - 1));
		model.addAttribute("sfr", sfr);
		model.addAttribute("action", "updateSub");
		return "issue/zfwjfb/form";
	}

	/**
	 * 修改安全文件信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("issue:zfwjfb:update")
	@RequestMapping(value = "updateSub")
	@ResponseBody
	public String updateSub(ISSUE_GovermentFileReleaseEntity sfr,
			HttpServletRequest request, Model model) {
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		String datasuccess = "failed";
		Map<String, Object> map = new HashMap<String, Object>();
		Timestamp t = DateUtils.getSysTimestamp();
		String zfids = request.getParameter("zfids");
		sfr.setS2(t);
		sfr.setZfids(zfids);
		sfr.setM2(request.getParameter("M2"));
		// 更新发布文件
		String filePath = request.getSession().getServletContext().getRealPath("/");
		if (issueZfwjfbService.updateInfoByID(sfr.getID(), sfr, filePath) > 0)
		//修改接收端信息
		if (StringUtils.isNotBlank(zfids)){
			map.put("zfids", zfids.substring(0, zfids.length() - 1));
		}else{
			map.put("xzqy", sessionuser.getXzqy());
			//因为发送人本身为安监局人员，所以不包括自己的id
			map.put("userid", sessionuser.getId());
			List<User> list=issueZfwjfbService.dataListE(map);
			for(User bee:list){
				zfids+=bee.getId()+",";
			}
			map.put("zfids", zfids.substring(0, zfids.length() - 1));// 政府用户
		}
		//1、删除 接收端信息 2、重新添加
		issueZfWjcdjsService.deleteInfoByFid(sfr.getID());
		map.put("wjid",sfr.getID());//文件id
		map.put("wjid",sfr.getID());//文件id
		issueZfWjcdjsService.addInfor(map);
		//重新添加msg提醒
		map.put("uid", UserUtil.getCurrentShiroUser().getId());
		map.put("content",sfr.getM1());
		msgService.addWjInfo(map);
		datasuccess = "success";
		return datasuccess;
	}
	
	/**
	 * 系统新增的企业或者需要补发给某些企业，更新文件zfids
	 * 
	 * @param model
	 */
	@RequiresPermissions("issue:zfwjfb:update")
	@RequestMapping(value = "updatezfids")
	@ResponseBody
	public String updateIssuezfids(HttpServletRequest request, Model model) {
		String datasuccess = "success";
		Long id= Long.parseLong(request.getParameter("id"));
		String zfids= request.getParameter("zfids");
		ISSUE_GovermentFileReleaseEntity sfr=issueZfwjfbService.findInfoById(id);
		sfr.setZfids(sfr.getZfids()+zfids);
		try {
			String filePath = request.getSession().getServletContext().getRealPath("/");
			issueZfwjfbService.updateInfoByID(id, sfr, filePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			datasuccess="failed";
		}
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(zfids))
			map.put("zfids", zfids.substring(0, zfids.length() - 1));// 政府用户
		//插入数据到接收表
		map.put("wjid",sfr.getID());
		issueZfWjcdjsService.addInfor(map);
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
	@RequiresPermissions("issue:zfwjfb:view")
	@RequestMapping(value = "view/{id}")
	public String view(@PathVariable("id") long id, Model model) {
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		ISSUE_GovermentFileReleaseEntity sfr = issueZfwjfbService
				.findInfoById(id);
		long uid = sessionuser.getId();
		issueZfWjcdjsService.updateIsorNotLook(uid, sfr.getID());

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
		return "issue/zfwjfb/view";
	}

	/**
	 * 删除安全文件信息
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("issue:zfwjfb:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			// 删除安全文件信息
			issueZfwjfbService.deleteInfo(Long.parseLong(arrids[i]));
			// 删除安全文件信息 传递与接收信息
			issueZfWjcdjsService.deleteInfoByFid(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 查询安全文件信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("issue:zfwjfb:view")
	@RequestMapping(value = "search/{id}")
	public String search(@PathVariable("id") long id, Model model) {

		ISSUE_GovermentFileReleaseEntity sfr = issueZfwjfbService
				.findInfoById(id);
		model.addAttribute("sfr", sfr);
		model.addAttribute("action", "view");
		return "issue/zfwjfb/form";
	}

	/**
	 * 查询用户未读文件的信息
	 * 
	 */
	@RequiresPermissions("issue:zfwjfb:view")
	@RequestMapping(value = "searchNoRead", method = RequestMethod.GET)
	@ResponseBody
	public String searchNoRead() {
		String datasuccess = "null";
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		long ID = sessionuser.getId(); // 当前用户id
		List<ISSUE_ZfFileTransmissionReceivingEntity> list = issueZfWjcdjsService
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
		List<ISSUE_ZfFileTransmissionReceivingEntity> list = issueZfWjcdjsService
				.findInfoByUId(ID);
		datasuccess = String.valueOf(list.size());
		return datasuccess;
	}

	/**
	 * 下载文件保存记录
	 */
	@RequiresPermissions("issue:zfwjfb:view")
	@RequestMapping(value = "down/{id}")
	@ResponseBody
	public void down(@PathVariable("id") long id, Model model) {
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("0")){//安监用户
			long uid = sessionuser.getId(); // 当前用户id
			issueZfWjcdjsService.updateIsorNotDownload(uid, id);
		}

	}
	
	/**
	 * 查询文件查看和下载情况的具体政府用户
	 * 
	 */
	@RequiresPermissions("issue:zfwjfb:view")
	@RequestMapping(value = "showqylist", method = RequestMethod.GET)
	public String showqylist(@RequestParam(value="wjid", defaultValue="0")int wjid,
			@RequestParam(value="state", defaultValue="0")int state,
			@RequestParam(value="type", defaultValue="0")int type, Model model) {
		 
		List<Map<String, Object>> list=issueZfWjcdjsService.findUserListByState(wjid, state, type);
		model.addAttribute("list", JsonMapper.getInstance().toJson(list));
		return "issue/zfwjfb/qylist";
	}

}
