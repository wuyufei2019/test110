package com.cczu.model.controller;

import java.text.ParseException;
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

import com.cczu.model.entity.YHPC_InterimCheckRecordEntity;
import com.cczu.model.entity.YHPC_InterimCheckContentEntity;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.YhpcLsJcjlService;
import com.cczu.model.service.YhpcLsjcnrService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

/**
 * 临时检查记录controller
 */
@Controller
@RequestMapping("yhpc/lsjcjl")
public class PageYhpcLsJcjlController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private YhpcLsJcjlService yhpcLsJcjlService;
	@Autowired
	private YhpcLsjcnrService yhpcLsjcnrService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "yhpc/rcaqjc/lsjcjl/index";		
	}
	
	/**
	 * list页面
	 * @param request
	 */
	@RequiresPermissions("yhpc:lsjc:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map =getPageMap(request);
		ShiroUser user= UserUtil.getCurrentShiroUser();
		if("1".equals(user.getUsertype())){
			map.put("qyid", user.getQyid());
		}else{
			map.put("xzqy", user.getXzqy());
			map.put("qyname", request.getParameter("yhpc_jcjl_qyname"));
			map.put("type", request.getParameter("yhpc_jcjd_type"));
		}
		map.put("jcjd", request.getParameter("yhpc_jcjd_name"));
		return yhpcLsJcjlService.dataGrid(map);
	}

	/**
	 * 图片选择页面跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "choosenr")
	public String choosepic(Model model) {
		return "yhpc/rcaqjc/lsjcjl/nrForm";
	}
	
	/**
	 * 图片选择页面跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "choose")
	public String choosenr(Model model) {
		return "yhpc/rcaqjc/lsjcjl/picForm";
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("yhpc:lsjc:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("checkflag",'2');
		model.addAttribute("czwt","[]");
		return "yhpc/rcaqjc/lsjcjl/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequiresPermissions("yhpc:lsjc:add")
	@RequestMapping(value = "create" , method = RequestMethod.POST)
	@ResponseBody
	public String create(YHPC_InterimCheckRecordEntity jcjl, Model model, HttpServletRequest request) {
		String datasuccess="success";
		ShiroUser user = UserUtil.getCurrentShiroUser();
		jcjl.setID1(user.getQyid());
		//添加检查记录
		yhpcLsJcjlService.addInfo(jcjl);
		//添加初查问题和图片
		String[] czwt=request.getParameterValues("czwt");
		String[] czwturl=request.getParameterValues("czwturl");
		if(czwt!=null){
			for(int i=0;i<czwt.length;i++){
				YHPC_InterimCheckContentEntity jcnr=new YHPC_InterimCheckContentEntity();
				String tempczwt=czwt[i].replace("\\r"," ");
				tempczwt=czwt[i].replace("\\n"," ");
				jcnr.setID1(jcjl.getID());
				jcnr.setM1(tempczwt);
				jcnr.setM2(czwturl[i]);
				jcnr.setM3("");
				jcnr.setM4("");
				jcnr.setM5(0);
				yhpcLsjcnrService.addInfo(jcnr);
			}
			jcjl.setM15("1");//如果存在隐患，则状态变为待复查
		}else{
			jcjl.setM15("3");//如果不存在隐患，则状态变为已完成整改
		}
		yhpcLsJcjlService.updateInfo(jcjl);//修改检查记录
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
		YHPC_InterimCheckRecordEntity jcjl = yhpcLsJcjlService.findById(id);
		model.addAttribute("lsjcjl", jcjl);
		//查询记录的存在问题
		List<Map<String, Object>> czwt= yhpcLsjcnrService.dataGridCzwt(id);
		String tempczwt=JsonMapper.getInstance().toJson(czwt);
		tempczwt=tempczwt.replace("\\r"," ");
		tempczwt=tempczwt.replace("\\n"," ");
		model.addAttribute("czwt", tempczwt);
		//返回页面
		model.addAttribute("action", "updatecc");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("checkflag",'2');
		return "yhpc/rcaqjc/lsjcjl/form";
	}

	/**
	 * 修改初查信息
	 * @param request,model
	 */
	@RequestMapping(value = "updatecc")
	@ResponseBody
	public String updatecc(YHPC_InterimCheckRecordEntity jcjl, Model model, HttpServletRequest request){
		String datasuccess="success";	
		yhpcLsJcjlService.updateInfo(jcjl);
		String[] czwt=request.getParameterValues("czwt");
		String[] czwturl=request.getParameterValues("czwturl");
		
		//先删除对应的初查问题，再重新添加
		yhpcLsjcnrService.deleteCzwt(jcjl.getID());
		//获取存在的问题
		if(czwt!=null){
			for(int i=0;i<czwt.length;i++){
				YHPC_InterimCheckContentEntity jcnr=new YHPC_InterimCheckContentEntity();
				String tempczwt=czwt[i].replace("\r\n","，");
				jcnr.setID1(jcjl.getID());
				jcnr.setM1(tempczwt);
				jcnr.setM2(czwturl[i]);
				jcnr.setM3("");
				jcnr.setM4("");
				jcnr.setM5(0);
				yhpcLsjcnrService.addInfo(jcnr);
			}
			jcjl.setM15("1");//如果存在隐患，则状态变为待复查
		}else{
			jcjl.setM15("3");//如果不存在隐患，则状态变为已完成整改
		}
		//修改检查记录
		yhpcLsJcjlService.updateInfo(jcjl);
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
		return yhpcLsjcnrService.dataGridCzwt(Long.parseLong(jlid));
		
	}
	
	/**
	 * 添加复查页面跳转
	 * @param model
	 */
	@RequiresPermissions("yhpc:lsjc:update")
	@RequestMapping(value = "addReCheck/{id}" , method = RequestMethod.GET)
	public String addReCheck(@PathVariable("id") Long id, Model model) {
		//查询选择的成员信息
		YHPC_InterimCheckRecordEntity jcjl = yhpcLsJcjlService.findById(id);
		model.addAttribute("lsjcjl", jcjl);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("checkflag","1");
		return "yhpc/rcaqjc/lsjcjl/fjform";
	}
	
	/**
	 * 修改复查信息页面跳转
	 * @param model
	 */
	@RequiresPermissions("yhpc:lsjc:update")
	@RequestMapping(value = "updateCheck/{id}" , method = RequestMethod.GET)
	public String updateCheck(@PathVariable("id") Long id, Model model) {
		//查询选择的成员信息
		YHPC_InterimCheckRecordEntity jcjl = yhpcLsJcjlService.findById(id);
		model.addAttribute("lsjcjl", jcjl);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("checkflag","1");
		return "yhpc/rcaqjc/lsjcjl/fjform";
	}
	
	/**
	 * 添加和修改复查信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(YHPC_InterimCheckRecordEntity jcjl, Model model, HttpServletRequest request){
		String datasuccess="success";	
		jcjl.setM15("3");
		//获取内容id数组并split
		String[] nrid=request.getParameterValues("nrid");
		//循环获取检查内容及状态
		if(nrid.length>0){
			//先删除对应的问题，再重新添加
			yhpcLsjcnrService.deleteCzwt(jcjl.getID());
			for(int i=1;i<=nrid.length;i++){
				String ccwt=request.getParameter("ccwt_"+nrid[i-1]);//获取初查问题
				String ccpic=request.getParameter("ccpic_"+nrid[i-1]);//获取初查图片
				String fcwt=request.getParameter("fcwt_"+nrid[i-1]);//获取复查情况
				String fcpic=request.getParameter("fcpic_"+nrid[i-1]);//获取复查图片
				String jcjg=request.getParameter("ra_"+nrid[i-1]);//获取检查结果（1是，0否）
				YHPC_InterimCheckContentEntity jcnr=new YHPC_InterimCheckContentEntity();
				jcnr.setID1(jcjl.getID());//检查记录id
				jcnr.setM1(ccwt);
				jcnr.setM2(ccpic);
				jcnr.setM3(fcwt);
				jcnr.setM4(fcpic);
				if(jcjg.equals("1")){
					jcnr.setM5(1);//已处理
				}else{
					jcnr.setM5(0);//未处理
					jcjl.setM15("2");//如果存在未处理内容，则状态改为待再次复查
				}	
				yhpcLsjcnrService.addInfo(jcnr);//检查检查内容
			}
			yhpcLsJcjlService.updateInfo(jcjl);//修改检查记录
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
			yhpcLsJcjlService.deleteInfo(Long.parseLong(aids[i]));
			//删除记录对应的检查问题
			yhpcLsjcnrService.deleteCzwt(Long.parseLong(aids[i]));
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
		YHPC_InterimCheckRecordEntity lsjcjl = yhpcLsJcjlService.findById(id);		
		model.addAttribute("lsjcjl", lsjcjl);
		BIS_EnterpriseEntity bis=bisQyjbxxService.findInfoById(lsjcjl.getID1());
		model.addAttribute("qyname", bis.getM1());
		//查询记录的存在问题
		List<Map<String, Object>> czwt= yhpcLsjcnrService.dataGridCzwt(id);
		String tempczwt=JsonMapper.getInstance().toJson(czwt);
		tempczwt=tempczwt.replace("\\r"," ");
		tempczwt=tempczwt.replace("\\n"," ");
		model.addAttribute("czwt", tempczwt);
		//返回页面
		model.addAttribute("action", "view");
		return "yhpc/rcaqjc/lsjcjl/view";
	}

	/**
	 * 导出现场检查记录word
	 * 
	 */
	@RequiresPermissions("yhpc:lsjc:export")
	@RequestMapping(value = "exportword/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> map = yhpcLsJcjlService.getJcjlWord(id);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "lsjcjl.ftl", filePath, filename, request);
		return "/download/" + filename;	}
	
}
