package com.cczu.model.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.YHPC_DailyCheckResultEntity;
import com.cczu.model.entity.YHPC_DailyHiddenInfoEntity;
import com.cczu.model.entity.YHPC_InspectionTaskEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.YhpcJcjlService;
import com.cczu.model.service.YhpcJcrwService;
import com.cczu.model.service.YhpcJcyhService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.Permission;
import com.cczu.sys.system.service.PermissionService;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 隐患排查_日常检查记录controller
 */
@Controller
@RequestMapping("yhpc/rcjl")
public class PageYhpcJcjlController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private YhpcJcjlService yhpcjcjlService;
	@Autowired
	private UserService userService;
	@Autowired
	private YhpcJcyhService yhpcJcyhService;
	@Autowired
	private YhpcJcrwService yhpcJcrwService;
	@Autowired
	private PermissionService permissionService;
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
					model.addAttribute("type", 2);
				else
					model.addAttribute("type", 1);
			}
		}else {//非企业用户页面
			model.addAttribute("type", 2);
		}		
		return "yhpc/rcaqjc/rcjcjl/index";
	}

	/**
	 * list页面
	 * @param request
	 */
	@RequiresPermissions("yhpc:jcjl:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> dataMap = new HashMap<>();
		Map<String, Object> map = getPageMap(request);
		map.putAll(getAuthorityMap());
		map.put("qyname", request.getParameter("view_qyname"));
		map.put("starttime", request.getParameter("yhpc_jcjl_starttime"));
		map.put("finishtime", request.getParameter("yhpc_jcjl_finishtime"));
		
		ShiroUser user = UserUtil.getCurrentShiroUser();
		List<Permission> permissionList=permissionService.getPermissions(user.getId());
		boolean flag = false;
		for (Permission per : permissionList) {
			if ("yhpc:jcjl:viewall".equals(per.getPermCode())) {
				flag = true;
				break;
			}
		}
		if (flag) {
			dataMap = yhpcjcjlService.dataGrid(map);
		} else {
			map.put("userid2", user.getId());
			dataMap = yhpcjcjlService.dataGrid(map);
		}
		map.put("jcjg", request.getParameter("yhpc_jcjl_jcjg"));
		return dataMap;
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequiresPermissions("yhpc:jcjl:add")
	@RequestMapping(value = "create" , method = RequestMethod.POST)
	@ResponseBody
	public String create(YHPC_DailyCheckResultEntity jcjl, HttpServletRequest request) {
		String datasuccess="success";
		String[] nrid=request.getParameterValues("M4");
		String yhids = StringUtils.join(nrid, ","); // 数组转字符串(逗号分隔)(推荐)
		jcjl.setM4(yhids);
		//获取隐患内容id数组并split
		jcjl.setM7(Long.parseLong(UserUtil.getCurrentShiroUser().getId()+""));//检查人id
		jcjl.setCreatetime(jcjl.getM1());//创建时间(便于统计)
		yhpcjcjlService.addInfo(jcjl);//添加检查记录
		//循环获取检查内容及状态
		if(!StringUtils.isEmpty(jcjl.getM4())){
			for(int i=1;i<=nrid.length;i++){
				String czwt=request.getParameter("czwt_"+nrid[i-1]);//存在问题
				String imgurl=request.getParameter("imgurl_"+nrid[i-1]);//现场照片
				String wcsj=request.getParameter("finishtime_"+nrid[i-1]);//计划完成时间
				String yhlb=request.getParameter("yhlb_"+nrid[i-1]);//隐患类别
				String yhdj=request.getParameter("yhdj_"+nrid[i-1]);//隐患等级
				String zgr=request.getParameter("zgr_"+nrid[i-1]);//整改人
				String zrbm=request.getParameter("zrbm_"+nrid[i-1]);//整改人
				String fsqy=request.getParameter("fsqy_"+nrid[i-1]);//整改人
				String jjcs=request.getParameter("jjcs_"+nrid[i-1]);//整改人
				YHPC_DailyHiddenInfoEntity rcyh=new YHPC_DailyHiddenInfoEntity();
				rcyh.setID1(jcjl.getID());//记录id
				rcyh.setQyid(jcjl.getQyid());//企业id
				rcyh.setCreatetime(jcjl.getM1());//隐患发现时间
				rcyh.setM1(czwt);//存在问题
				rcyh.setM1_1(nrid[i-1]);//内容id
				rcyh.setM2(imgurl);//现场照片
				rcyh.setM3(yhlb);//隐患类别
				rcyh.setM7(yhdj);//隐患等级
				rcyh.setM4(new Timestamp(DateUtils.getSqlDate(wcsj).getTime()));//计划完成时间
				rcyh.setM9(zgr);//整改人
				rcyh.setM11("0");//状态
				rcyh.setM12(Long.parseLong(UserUtil.getCurrentShiroUser().getId()+""));//隐患发现人id
				rcyh.setM13(zrbm);//责任部门
				rcyh.setM14(fsqy);//发生区域	
				rcyh.setM15(jjcs);//解决措施
				yhpcJcyhService.addInfo(rcyh);//添加隐患记录
			}
		}
		
		//修改检查任务状态
		YHPC_InspectionTaskEntity jcrw=yhpcJcrwService.find(jcjl.getID1());
		jcrw.setM6(request.getParameter("M6"));
		yhpcJcrwService.updateInfo(jcrw);
		return datasuccess;
	}

	/**
	 * 补充添加信息
	 * @param request,model
	 */
	@RequiresPermissions("yhpc:jcjl:add")
	@RequestMapping(value = "bccreate" , method = RequestMethod.POST)
	@ResponseBody
	public String bccreate(YHPC_DailyCheckResultEntity entity, HttpServletRequest request) {
		String datasuccess="success";
		YHPC_DailyCheckResultEntity jcjl = yhpcjcjlService.find(entity.getID());
		String[] nrid=request.getParameterValues("M4");
		String yhids = StringUtils.join(nrid, ","); // 数组转字符串(逗号分隔)(推荐)
		if(!jcjl.getM4().equals("") && jcjl.getM4() != null) {
			yhids = jcjl.getM4()+","+yhids;
		}
		jcjl.setM4(yhids);
		//获取隐患内容id数组并split
		jcjl.setM7(Long.parseLong(UserUtil.getCurrentShiroUser().getId()+""));//检查人id
		yhpcjcjlService.updateInfo(jcjl);//添加检查记录
		//循环获取检查内容及状态
		if(!StringUtils.isEmpty(jcjl.getM4())){
			for(int i=1;i<=nrid.length;i++){
				String czwt=request.getParameter("czwt_"+nrid[i-1]);//存在问题
				String imgurl=request.getParameter("imgurl_"+nrid[i-1]);//现场照片
				String wcsj=request.getParameter("finishtime_"+nrid[i-1]);//计划完成时间
				String yhlb=request.getParameter("yhlb_"+nrid[i-1]);//隐患类别
				String yhdj=request.getParameter("yhdj_"+nrid[i-1]);//隐患等级
				String zgr=request.getParameter("zgr_"+nrid[i-1]);//整改人
				String zrbm=request.getParameter("zrbm_"+nrid[i-1]);//整改人
				String fsqy=request.getParameter("fsqy_"+nrid[i-1]);//整改人
				String jjcs=request.getParameter("jjcs_"+nrid[i-1]);//整改人
				YHPC_DailyHiddenInfoEntity rcyh=new YHPC_DailyHiddenInfoEntity();
				rcyh.setID1(jcjl.getID());//记录id
				rcyh.setQyid(jcjl.getQyid());//企业id
				rcyh.setCreatetime(jcjl.getM1());//隐患发现时间
				rcyh.setM1(czwt);//存在问题
				rcyh.setM1_1(nrid[i-1]);//内容id
				rcyh.setM2(imgurl);//现场照片
				rcyh.setM3(yhlb);//隐患类别
				rcyh.setM7(yhdj);//隐患等级
				rcyh.setM4(new Timestamp(DateUtils.getSqlDate(wcsj).getTime()));//计划完成时间
				rcyh.setM9(zgr);//整改人
				rcyh.setM11("0");//状态
				rcyh.setM12(Long.parseLong(UserUtil.getCurrentShiroUser().getId()+""));//隐患发现人id
				rcyh.setM13(zrbm);//责任部门
				rcyh.setM14(fsqy);//发生区域
				rcyh.setM15(jjcs);//解决措施
				yhpcJcyhService.addInfo(rcyh);//添加隐患记录
			}
		}

		//修改检查任务状态
		YHPC_InspectionTaskEntity jcrw=yhpcJcrwService.find(jcjl.getID1());
		jcrw.setM6(request.getParameter("M6"));
		yhpcJcrwService.updateInfo(jcrw);
		return datasuccess;
	}
	
	/**
	 * 修改检查任务页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("yhpc:jcjl:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的成员信息
		Map<String, Object> jcjl= yhpcjcjlService.findById(id);
		model.addAttribute("jcjl", jcjl);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "yhpc/rcaqjc/jcjl/form";
	}
	
	/**
	 * 修改检查任务信息
	 * @param jcjl,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(YHPC_DailyCheckResultEntity jcjl, Model model){
		String datasuccess="success";	 
		yhpcjcjlService.updateInfo(jcjl);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除检查任务信息
	 * 
	 * @throws ParseException
	 */
	@RequiresPermissions("yhpc:jcjl:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			yhpcjcjlService.deleteInfo(Long.parseLong(aids[i]));
		}
		
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		//查询选择的成员信息
		Map<String, Object> jcjl= yhpcjcjlService.findById(id);
		model.addAttribute("jcjl", jcjl);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "yhpc/rcaqjc/rcjcjl/view";
	}

	/**
	 * 获取检查内容list
	 * @param request
	 */
	@RequestMapping(value = "nrlist/{id}/{jlid}" , method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getDataNr(@PathVariable("id") Long rwid, @PathVariable("jlid") Long jlid, HttpServletRequest request) {
		Map<String, Object> map =new HashMap<String, Object>();
		//获取检查任务的内容id
		YHPC_InspectionTaskEntity jcrw=yhpcJcrwService.find(rwid);
		map.put("nrid", jcrw.getM4());
		map.put("jlid", jlid);
		//根据内容id返回内容
		return yhpcjcjlService.dataGridNr(map);
		
	}
	
	/**
	 * 添加检查记录跳转
	 * @param id,model
	 */
	@RequestMapping(value = "addJl/{id}" , method = RequestMethod.GET)
	public @ResponseBody String createFa(@PathVariable("id") Long id, Model model) {
		Map<String, Object> jcjl=yhpcjcjlService.findById(id);
		model.addAttribute("action", "create");
		model.addAttribute("jcjl", jcjl);//传检查方案id和企业信息过去
		return "yhpc/rcaqjc/jcjl/jlform";	
	}

	/**
	 * 补充检查记录跳转
	 * @param id1,model
	 */
	@RequestMapping(value = "bcJl/{id1}" , method = RequestMethod.GET)
	public String createBcFa(@PathVariable("id1") Long id1, Model model) {
		Map<String, Object> jcjl=yhpcjcjlService.findById1(id1);
		model.addAttribute("action", "bccreate");
		model.addAttribute("jcjl", jcjl);//传检查方案id和企业信息过去
		Map<String, Object> jcrw=yhpcJcrwService.findById(id1);
		model.addAttribute("jcrw", jcrw);//传检查方案id和企业信息过去
		return "yhpc/rcaqjc/jcrw/bcjlform";
	}

	/**
	 * 添加检查内容页面跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "choosejcnr")
	public String choosejcnr(Model model) {
		return "yhpc/rcaqjc/jcjl/nrForm";
	}
}
