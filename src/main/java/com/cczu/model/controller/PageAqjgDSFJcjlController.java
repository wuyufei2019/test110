package com.cczu.model.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

import com.cczu.model.entity.AQJD_DSFCheckRecordEntity;
import com.cczu.model.entity.AQJD_DSFCheckContentEntity;
import com.cczu.model.entity.AQJG_DSFManageEntity;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.AqjgDSFJcjlService;
import com.cczu.model.service.AqjgDSFManageService;
import com.cczu.model.service.AqjgJcnrService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

/**
 * 第三方检查记录controller
 */
@Controller
@RequestMapping("dsffw/jcjl")
public class PageAqjgDSFJcjlController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private AqjgDSFManageService manageService;
	@Autowired
	private AqjgDSFJcjlService aqjgDSFJcjlService;
	@Autowired
	private AqjgJcnrService aqjgJcnrService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		if(be!=null&& be.getM1() != null){
			return "aqjg/aqjdjc/dsfjcjl/index";
		}else{
			// 获取用户角色
			List<Role> list = roleService.findRoleById(sessionuser.getId());
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					//判断用户角色，如果包含有企业用户角色，则转到企业用户显示的界面
					if (list.get(i).getRoleCode().equals("company")||list.get(i).getRoleCode().equals("companyadmin"))
						return "../error/001";
				}
			}
			return "aqjg/aqjdjc/dsfjcjl/ajindex";
		}		
	}
	
	/**
	 * list页面（企业用户显示界面）
	 * @param request
	 */
	@RequiresPermissions("aqjg:dsfjc:view")
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
		map.put("jcjd", request.getParameter("aqjg_jcjd_name"));
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);
		if(be!=null){
			map.put("qyid", be.getID());
		}

		return aqjgDSFJcjlService.dataGrid(map);
		
	}

	/**
	 * list页面（非企业用户显示界面）
	 * @param request
	 */
	@RequiresPermissions("aqjg:dsfjc:view")
	@RequestMapping(value="ajlist")
	@ResponseBody
	public Map<String, Object> getData2(HttpServletRequest request) {
		int pageNo=1;	//当前页码
		int pageSize=20;	//每页行数
		if(StringUtils.isNotEmpty(request.getParameter("page")))
			pageNo=Integer.valueOf(request.getParameter("page"));
		if(StringUtils.isNotEmpty(request.getParameter("rows")))
			pageSize=Integer.valueOf(request.getParameter("rows"));
		ShiroUser user= UserUtil.getCurrentShiroUser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);
		map.put("xzqy", user.getXzqy());
		//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
		if(user.getUserroleflg()!=null&&user.getUserroleflg()!=0)
			map.put("jglx",user.getUserroleflg());
		map.put("jcjd", request.getParameter("aqjg_jcjd_name"));
		map.put("qyname", request.getParameter("aqjg_jcjl_qyname"));
		return aqjgDSFJcjlService.dataGrid2(map);
		
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
		return aqjgDSFJcjlService.dataGrid(map);
	}

	/**
	 * 图片选择页面跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "choosenr")
	public String choosepic(Model model) {
		return "aqjg/aqjdjc/dsfjcjl/nrForm";
	}
	
	/**
	 * 图片选择页面跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "choose")
	public String choosenr(Model model) {
		return "aqjg/aqjdjc/dsfjcjl/picForm";
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("aqjg:dsfjc:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("checkflag",'2');
		return "aqjg/aqjdjc/dsfjcjl/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequiresPermissions("aqjg:dsfjc:add")
	@RequestMapping(value = "create" , method = RequestMethod.POST)
	@ResponseBody
	public String create(AQJD_DSFCheckRecordEntity jcjl, Model model, HttpServletRequest request) {
		String datasuccess="success";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		//用户类型(1企业，0安监局，9系统管理员)
		if(sessionuser.getUsertype().equals("1")){
			jcjl.setID1(sessionuser.getQyid());
		}else if(sessionuser.getUsertype().equals("0")||sessionuser.getUsertype().equals("9")){
			
		}else{
			return "请先完善企业基本信息";
		}
		aqjgDSFJcjlService.addInfo(jcjl);
		
		//添加初查问题和图片
		String[] czwt=request.getParameterValues("czwt");
		String[] czwturl=request.getParameterValues("czwturl");
		if(czwt!=null){
			for(int i=0;i<czwt.length;i++){
				AQJD_DSFCheckContentEntity jcnr=new AQJD_DSFCheckContentEntity();
				jcnr.setID1(jcjl.getID());
				jcnr.setM1(czwt[i]);
				jcnr.setM2(czwturl[i]);
				jcnr.setM3("");
				jcnr.setM4("");
				aqjgJcnrService.addInfo(jcnr);
			}
		}
		//返回结果
		return datasuccess;
	}

	/**
	 * 修改初查页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "updatecc/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的成员信息
		AQJD_DSFCheckRecordEntity jcjl = aqjgDSFJcjlService.findById(id);
		model.addAttribute("dsfjcjl", jcjl);
		//查询记录的存在问题
		List<Map<String, Object>> czwt= aqjgJcnrService.dataGridCzwt(id);
		model.addAttribute("czwt", JsonMapper.getInstance().toJson(czwt));
		//返回页面
		model.addAttribute("action", "updatecc");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("checkflag",'2');
		return "aqjg/aqjdjc/dsfjcjl/form";
	}

	/**
	 * 修改初查信息
	 * @param request,model
	 */
	@RequestMapping(value = "updatecc")
	@ResponseBody
	public String updatecc(AQJD_DSFCheckRecordEntity jcjl, Model model, HttpServletRequest request){
		String datasuccess="success";	
		aqjgDSFJcjlService.updateInfo(jcjl);
		
		String[] czwt=request.getParameterValues("czwt");
		String[] czwturl=request.getParameterValues("czwturl");
		
		//先删除对应的初查问题，再重新添加
		aqjgJcnrService.deleteCzwt(jcjl.getID());
		//获取存在的问题
		if(czwt!=null){
			for(int i=0;i<czwt.length;i++){
				AQJD_DSFCheckContentEntity jcnr=new AQJD_DSFCheckContentEntity();
				jcnr.setID1(jcjl.getID());
				jcnr.setM1(czwt[i]);
				jcnr.setM2(czwturl[i]);
				jcnr.setM3("");
				jcnr.setM4("");
				jcnr.setM5(0);
				aqjgJcnrService.addInfo(jcnr);
			}
		}
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 根据检查记录id获取检查内容list
	 * @param request
	 */
	@RequestMapping(value = "nrlist/{id}" , method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> getDataNr(@PathVariable("id") String jlid, HttpServletRequest request) {
		int pageNo=1;	//当前页码
		int pageSize=50;	//每页行数

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);

		return aqjgJcnrService.dataGridCzwt(Long.parseLong(jlid));
		
	}
	
	/**
	 * 添加复查页面跳转
	 * @param model
	 */
	@RequiresPermissions("aqjg:dsfjc:update")
	@RequestMapping(value = "addReCheck/{id}" , method = RequestMethod.GET)
	public String addReCheck(@PathVariable("id") Long id, Model model) {
		//查询选择的成员信息
		AQJD_DSFCheckRecordEntity jcjl = aqjgDSFJcjlService.findById(id);
		model.addAttribute("dsfjcjl", jcjl);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("checkflag","1");
		return "aqjg/aqjdjc/dsfjcjl/fjform";
	}
	
	/**
	 * 修改复查信息页面跳转
	 * @param model
	 */
	@RequiresPermissions("aqjg:dsfjc:update")
	@RequestMapping(value = "updateCheck/{id}" , method = RequestMethod.GET)
	public String updateCheck(@PathVariable("id") Long id, Model model) {
		//查询选择的成员信息
		AQJD_DSFCheckRecordEntity jcjl = aqjgDSFJcjlService.findById(id);
		model.addAttribute("dsfjcjl", jcjl);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("checkflag","1");
		return "aqjg/aqjdjc/dsfjcjl/fjform";
	}
	
	/**
	 * 添加和修改复查信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQJD_DSFCheckRecordEntity jcjl, Model model, HttpServletRequest request){
		String datasuccess="success";	
		aqjgDSFJcjlService.updateInfo(jcjl);
		
		//获取内容id数组并split
		String[] nrid=request.getParameterValues("nrid");
		//循环获取检查内容及状态
		if(nrid.length>0){
			//先删除对应的问题，再重新添加
			aqjgJcnrService.deleteCzwt(jcjl.getID());
			for(int i=1;i<=nrid.length;i++){
				String ccwt=request.getParameter("ccwt_"+nrid[i-1]);//获取初查问题
				String ccpic=request.getParameter("ccpic_"+nrid[i-1]);//获取初查图片
				String fcwt=request.getParameter("fcwt_"+nrid[i-1]);//获取复查情况
				String fcpic=request.getParameter("fcpic_"+nrid[i-1]);//获取复查图片
				String jcjg=request.getParameter("ra_"+nrid[i-1]);//获取检查结果（1是，0否）
				AQJD_DSFCheckContentEntity jcnr=new AQJD_DSFCheckContentEntity();
				jcnr.setID1(jcjl.getID());//检查记录id
				jcnr.setM1(ccwt);
				jcnr.setM2(ccpic);
				jcnr.setM3(fcwt);
				jcnr.setM4(fcpic);
				if(jcjg.equals("1")){
					jcnr.setM5(1);//检查结果
				}else{
					jcnr.setM5(0);//检查结果
				}	
				aqjgJcnrService.addInfo(jcnr);//检查检查内容
			}
		}
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除第三方检查记录信息
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
			//删除第三方检查记录问题
			aqjgDSFJcjlService.deleteInfo(Long.parseLong(aids[i]));
			//删除记录对应的检查问题
			aqjgJcnrService.deleteCzwt(Long.parseLong(aids[i]));
		}
		
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		//查询选择的安全备案信息
		AQJD_DSFCheckRecordEntity dsfjcjl = aqjgDSFJcjlService.findById(id);		
		model.addAttribute("dsfjcjl", dsfjcjl);
		AQJG_DSFManageEntity aqjg=manageService.findById(dsfjcjl.getID2());
		BIS_EnterpriseEntity bis=bisQyjbxxService.findInfoById(dsfjcjl.getID1());
		if(aqjg!=null)
		model.addAttribute("dwname", aqjg.getM1());
		model.addAttribute("qyname", bis.getM1());
		//返回页面
		model.addAttribute("action", "view");
		return "aqjg/aqjdjc/dsfjcjl/view";
	}

	/**
	 * 导出现场检查记录word
	 * 
	 */
	@RequiresPermissions("aqjg:dsfjc:export")
	@RequestMapping(value = "exportword/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> map = aqjgDSFJcjlService.getJcjlWord(id);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "dsfjcjl.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("aqjg:dsfjc:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		ShiroUser user= UserUtil.getCurrentShiroUser();
		//用户类型(1企业，0安监局，9系统管理员)
		if(sessionuser.getUsertype().equals("1")){
			map.put("qyid", sessionuser.getQyid());
		}else if(sessionuser.getUsertype().equals("0")){
			map.put("xzqy", user.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
				map.put("jglx",sessionuser.getUserroleflg());
		}
		map.put("jcjd", request.getParameter("aqjg_jcjd_name"));
		map.put("qyname", request.getParameter("aqjg_jcjl_qyname"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		aqjgDSFJcjlService.exportExcel(response, map);
	}
	/**
	 * 显示所有列
	 * @param id,model 
	 */
	@RequiresPermissions("aqjg:dsfjc:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","dsffw/jcjl/export");
		return "common/formexcel";
	}
	
}
