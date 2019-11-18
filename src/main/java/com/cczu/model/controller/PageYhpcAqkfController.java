package com.cczu.model.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.YHPC_SafetyDeductionEntity;
import com.cczu.model.entity.YHPC_SafetyDeduction_Second;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.YhpcAqkfSecService;
import com.cczu.model.service.YhpcAqkfService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 安全十二分信息controller
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("yhpc/aqkf")
public class PageYhpcAqkfController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private YhpcAqkfService yhpcAqkfService;
	@Autowired
	private YhpcAqkfSecService yhpcAqkfSecService;
	

	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model,HttpServletRequest request) {
		if(request.getParameter("sys")!=null &&"1".equals(request.getParameter("sys")))
			model.addAttribute("sys", "sys");
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					return "yhpc/aqkf/adminindex";
				else
					return "yhpc/aqkf/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "yhpc/aqkf/adminindex";
		}	
	}
	
	/**
	 * list页面
	 * @param request
	 */
	@RequiresPermissions("yhpc:aqkf:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		
		List<Object> list=new ArrayList<Object>();
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		
		Map<String, Object> map = getPageMap(request);
		if(be!=null){
			map.put("qyid", be.getID());
			map.put("m1", request.getParameter("yhpc_aqkf_cx_m1"));
			map.put("m2", request.getParameter("yhpc_aqkf_cx_m2"));
			return yhpcAqkfService.dataGrid(map);
		}else{
			map.put("rows", list);
			map.put("total", 0);
			
			return map;
		}
		
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
		return yhpcAqkfService.dataGrid(map);
	}

	
	/**
	 * 添加基础信息页面跳转
	 * @param model
	 */
	@RequiresPermissions("yhpc:aqkf:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		model.addAttribute("usertype",sessionuser.getUsertype());
		model.addAttribute("qyid",sessionuser.getQyid());
		model.addAttribute("action", "create");
		return "yhpc/aqkf/form";
		
	}
	
	/**
	 * 添加安全十二分基础信息
	 * @param request,model
	 * @throws ParseException 
	 */
	@RequiresPermissions("yhpc:aqkf:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(YHPC_SafetyDeductionEntity bs, Model model) throws ParseException {
		String datasuccess="success";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		log.info(sessionuser.getLoginName()+":  一企一档--安全十二分信息  【添加基础信息操作】");
		if(sessionuser.getUsertype().equals("1")){
			bs.setID1(sessionuser.getQyid());
		} 
		Timestamp t=DateUtils.getSysTimestamp();
		bs.setS1(t);
		bs.setS2(t);
		bs.setS3(0);
		yhpcAqkfService.addInfo(bs);
		return datasuccess;
		
	}
	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("yhpc:aqkf:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的安全十二分信息
		YHPC_SafetyDeductionEntity bs = yhpcAqkfService.findById(id);
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("qyid", bs.getID1());
		model.addAttribute("aqkf", bs);
		
		//返回页面
		model.addAttribute("action", "update");
		return "yhpc/aqkf/form";
	}
	
	/**
	 * 修改安全十二分信息
	 * @param request,model
	 * @throws ParseException 
	 */
	@RequiresPermissions("yhpc:aqkf:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(YHPC_SafetyDeductionEntity bs, Model model) throws ParseException{
		String datasuccess="success";
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--安全十二分信息  【修改操作】");
		Timestamp t=DateUtils.getSysTimestamp();
		bs.setS2(t);
		yhpcAqkfService.updateInfo(bs);
		
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除安全十二分信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequiresPermissions("yhpc:aqkf:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--安全十二分信息  【删除操作】");
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			yhpcAqkfService.deleteInfo(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("bis:qyjbxx:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		//查询选择的产品信息
		
		long id1 = id;
		YHPC_SafetyDeductionEntity bs = yhpcAqkfService.findById2(id1);
		
		model.addAttribute("qylist", bs);
		
		//返回页面
		return "yhpc/aqkf/view";
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("yhpc:aqkf:view")
	@RequestMapping(value = "sview/{id}", method = RequestMethod.GET)
	public String sview(@PathVariable("id") Integer id, Model model) {
		//查询选择的产品信息
		long id1 = id;
		YHPC_SafetyDeductionEntity bs = yhpcAqkfService.findById(id1);
		model.addAttribute("qylist", bs);
		
		//返回页面
		model.addAttribute("action", "view");
		return "yhpc/aqkf/view";
	}
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("yhpc:aqkf:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id1", request.getParameter("id1"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.putAll(getAuthorityMap());
		yhpcAqkfService.exportExcel(response, map);
		
	}
	
	/**
	 * 显示所有列
	 * @param id,model
	 */
	@RequiresPermissions("yhpc:aqkf:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url", "yhpc/aqkf/export");
		return "common/formexcel";
	}
	
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
/*	@RequiresPermissions("bis:tzsbxx:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			resultmap = yhpcAqkfService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}*/

	
	/**
	 * 安全十二分提示信息
	 * @return
	 * @throws ParseException
	 */
	@RequiresPermissions("yhpc:aqkf:view")
	@RequestMapping(value = "aqpxTimeEnd" ,method = RequestMethod.GET)
	@ResponseBody
	public String aqpxTimeEnd() throws ParseException {
		String datasuccess="null";
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.CHINA);
		String dqtime = format.format(date);
		java.text.SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date1 =  formatter.parse(dqtime);
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
/*		if(be!=null){//假如be不为空，则是企业
			List<YHPC_SafetyDeductionEntity> list = yhpcAqkfService.findAll(be.getID());
			for(int i=0;i<list.size();i++){
				if(list.get(i).getM9()!=null){
					if(list.get(i).getM9().getTime()<date1.getTime()){
						datasuccess="部分有效期已过期，请注意查看！";
						break;
					}
				}
			}
		}*/
		return datasuccess;
	}
	
	/**
	 * 添加指定人员扣分信息
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("yhpc:aqkf:update")
	@RequestMapping(value = "createhis/{id}", method = RequestMethod.GET)
	public String createhis(@PathVariable("id") Long id, Model model) {
		model.addAttribute("mainid", id);//主表id
		// 返回页面
		model.addAttribute("action", "createhis");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "yhpc/aqkf/form2";
	}

	/**
	 * 添加安全十二分扣分信息
	 * @param request,model
	 * @throws ParseException 
	 */
	@RequiresPermissions("yhpc:aqkf:add")
	@RequestMapping(value = "createhis")
	@ResponseBody
	public String createhis(YHPC_SafetyDeduction_Second bs, Model model) throws ParseException {
		String datasuccess="success";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		log.info(sessionuser.getLoginName()+":  一企一档--安全十二分信息  【添加基础信息操作】");
		if(sessionuser.getUsertype().equals("1")){
			bs.setQYID(sessionuser.getQyid());
		} 
		Timestamp t=DateUtils.getSysTimestamp();
		bs.setS1(t);
		bs.setS2(t);
		bs.setS3(0);
		yhpcAqkfSecService.addInfo(bs);
		
		YHPC_SafetyDeductionEntity equip=yhpcAqkfService.findById(bs.getID1());
		equip.setM3(bs.getM1());//最近扣分时间
		equip.setS2(t);
		yhpcAqkfService.updateInfo(equip);
		return datasuccess;
	}
	
	/**
	 * 修改检测日期页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("yhpc:aqkf:update")
	@RequestMapping(value = "updatehis/{id}", method = RequestMethod.GET)
	public String update3(@PathVariable("id") Long id, Model model) {
		// 查询选择的扣分信息
		YHPC_SafetyDeduction_Second bs = yhpcAqkfSecService.findById(id);
		model.addAttribute("aqkf", bs);
		// 返回页面
		model.addAttribute("action", "updatehis");
		model.addAttribute("id", id);
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "yhpc/aqkf/form2";
	}

	/**
	 * 更新历史扣分日期
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("yhpc:aqkf:update")
	@RequestMapping(value = "updatehis")
	@ResponseBody
	public String update2(YHPC_SafetyDeduction_Second bs, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		YHPC_SafetyDeductionEntity equip=yhpcAqkfService.findById(bs.getID1());
		equip.setM3(bs.getM1());//最近扣分时间
		equip.setS2(t);
		
		yhpcAqkfService.updateInfo(equip);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  安全十二分--添加最新扣分记录");

		bs.setS2(t);
		//添加副表信息
		yhpcAqkfSecService.upd(bs);

		// 返回结果
		return datasuccess;
	}
	
	/**
	 * 删除检测日期
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequiresPermissions("yhpc:aqkf:delete")
	@RequestMapping(value = "deleterq/{id}")
	@ResponseBody
	public String deleterq(@PathVariable("id") Long id) {
		String datasuccess="删除成功";
		//可以批量删除
		yhpcAqkfService.deleterq(id);
		return datasuccess;
	}
	
	/**
	 * 更新历史页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("yhpc:aqkf:add")
	@RequestMapping(value = "hisindex/{id}", method = RequestMethod.GET)
	public String showhistory(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("ID1",id);
		return "yhpc/aqkf/hisindex";
	}
	
	/**
	 * 更新历史list页面
	 * 
	 * @param request
	 */
	@RequestMapping(value = "hislist")
	@ResponseBody
	public Map<String, Object> getData2(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		//主表id
		map.put("ID1", request.getParameter("ID1").toString());
		
		return yhpcAqkfSecService.dataGrid(map);
	}
}
