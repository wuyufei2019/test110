package com.cczu.model.mbgl.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.mbgl.entity.Target_Distribute;
import com.cczu.model.mbgl.service.TargetZbfpService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 目标管理-目标分配controller
 * 
 * @author jason
 * 
 */

@Controller
@RequestMapping("target/zbfp")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageTargetZbfpController extends BaseController {

	@Autowired
	private TargetZbfpService targetMbfpService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model,HttpServletRequest request) {
		String ajqyid = request.getParameter("qyid");
		if(StringUtils.isEmpty(ajqyid)){
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(UserUtil.getCurrentShiroUser().getQyid());
			ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
			model.addAttribute("qyid", sessionuser.getQyid());
			if (be.getIsbloc() != null && be.getIsbloc() == 1) {
				model.addAttribute("usertype", "isbloc");
			}
		}else{
			model.addAttribute("ajqyid", ajqyid);
		}
		return "targetmanger/zbfp/index";
	}

	/**
	 * list页面 企业端 
	 * 
	 * @param request
	 */
	@RequestMapping(value = "qylist")
	@ResponseBody
	public Map<String, Object> getQyData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		String ajqyid = request.getParameter("ajqyid");
		if(StringUtils.isEmpty(ajqyid)){
			User user =UserUtil.getCurrentUser();
			Subject subject = SecurityUtils.getSubject();
			if(!subject.isPermitted("target:zbfp:viewall")){//是否拥有viewall权限
				if(user.getDepartmen()!=null)
					map.put("deptid", user.getDepartmen());
			}else{
				map.put("deptname", request.getParameter("target_zbfp_deptname"));
			}
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(user.getId2());
			if (be.getIsbloc() != null && be.getIsbloc() == 1) {//集团公司
				map.put("fid", user.getId2());
				map.put("qyname", request.getParameter("target_zbfp_qyname"));
			}else{
				map.put("qyid", user.getId2());
			}
		}else{
			map.put("qyid", ajqyid);
		}
		map.put("targetname", request.getParameter("target_zbfp_mbname"));
		map.put("deptid", request.getParameter("target_zbfp_deptid"));
		map.put("m1", request.getParameter("target_zbfp_m1"));		
		map.put("m3", request.getParameter("target_zbfp_m3"));		
		return targetMbfpService.dataGrid(map);
	}
	
	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("target:zbfp:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		User user =UserUtil.getCurrentUser();
		Subject subject = SecurityUtils.getSubject();
		if(!subject.isPermitted("target:zbfp:viewall")){//是否拥有viewall权限
			if(user.getDepartmen()!=null) {
				model.addAttribute("deptid", user.getDepartmen());
			}
		}
		model.addAttribute("username", UserUtil.getCurrentShiroUser().getName());
		return "targetmanger/zbfp/form";
	}

	/**
	 * 添加目标信息
	 * 
	 * @param request
	 * model
	 */
	@RequiresPermissions("target:zbfp:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(Target_Distribute target, Model model,HttpServletRequest request) {
		String datasuccess = "success";
		String[] id1s= request.getParameterValues("ID1");
		String[] targetvals=target.getTargetval().split(",");
		String[] m11=target.getM11().split(",");
		String[] m12=target.getM12().split(",");
		String[] m13 = target.getM13().split(",");
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if("1".equals(sessionuser.getUsertype())){
			target.setID2(sessionuser.getQyid());
		}
		Timestamp t = DateUtils.getSysTimestamp();
		target.setS1(t);
		target.setS2(t);
		target.setS3(0);
		System.err.println();
		for(int i=0;i<id1s.length;i++){
			target.setID(null);
			target.setID1(Integer.parseInt(id1s[i]));
			target.setTargetval(targetvals[i]);
			target.setM11(m11[i]);
			target.setM12(m12[i]);
			target.setM13(m13[i]);
			try {
				targetMbfpService.addInfo(target);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				datasuccess="failed";
			}
		}
		// 返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id
	 *            ,model
	 */
	@RequiresPermissions("target:zbfp:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		Target_Distribute target = targetMbfpService.findInfoById(id);
		model.addAttribute("target", target);
		model.addAttribute("action", "update");
		return "targetmanger/zbfp/form";
	}

	/**
	 * 修改目标信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("target:zbfp:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(Target_Distribute target, Model model) {
		String datasuccess = "success";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if("1".equals(sessionuser.getUsertype())){
			target.setID2(sessionuser.getQyid());
		}
		Timestamp t = DateUtils.getSysTimestamp();
		target.setS2(t);
		targetMbfpService.updateInfo(target);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除目标信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("target:zbfp:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			targetMbfpService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * 
	 * @param id
	 *            ,model
	 */
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public String view(Model model, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		map.put("id2", request.getParameter("qyid"));
		map.put("deptname", request.getParameter("deptname"));
		map.put("m1", request.getParameter("m1"));
		map.put("m3", request.getParameter("m3"));
		List<Map<String,Object>> list = targetMbfpService.getView(map);
		model.addAttribute("list", list);
		model.addAttribute("map", map);
		//Target_Distribute target = targetMbfpService.findInfoById(id);
		//model.addAttribute("target", target);
		return "targetmanger/zbfp/view";
	}


	/**
	 * 导入
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
//	@RequiresPermissions("target:zbfp:exin")
//	@RequestMapping(value = "exin")
//	@ResponseBody
//	public String expiExcel(HttpServletResponse response,
//			HttpServletRequest request) {
//		Map<String, Object> map = uploadExcel(request, response);
//		Map<String, Object> resultmap = new HashMap<String, Object>();
//		if (map.get("content") != null) {
//			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
//			resultmap = targetMbfpService.exinExcel(map);
//		} else {
//			resultmap.put("returncode", -2);
//		}
//		return JsonMapper.toJsonString(resultmap);
//	}

	/**
	 * 导出word
	 * 
	 */
	@RequiresPermissions("target:zbfp:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public String export(HttpServletRequest request) {
		Map<String, Object> remap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if("1".equals(sessionuser.getUsertype())){
			map.put("id2", sessionuser.getQyid());
		}
		map.put("deptname", request.getParameter("deptname"));
		map.put("m1", request.getParameter("m1"));
		map.put("m3", request.getParameter("m3"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		List<Map<String, Object>> list = targetMbfpService.exportExcel(map);
		Map<String, Object> map1 = new HashMap<String, Object>();
		for (int i = 0; i < 1; i++) {
			Map<String, Object> map2 = list.get(0);
			map1.put("m1", map2.get("m1"));
			map1.put("m3", map2.get("m3"));
			map1.put("deptname", map2.get("deptname"));
		}
		remap.put("list", list);
		remap.put("head", map1);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(remap, "mbgl_zbfp.ftl", filePath, filename, request);
		return "/download/" + filename;
	}

	/**
	 * 显示所有列
	 * @param id
	 * model
	 */
	/*@RequiresPermissions("target:zbfp:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model, HttpServletRequest request) {
		String deptname = request.getParameter("deptname");
		String m1 = request.getParameter("m1");
		String m3 = request.getParameter("m3");
		model.addAttribute("url", "target/zbfp/export?deptname="+deptname+"&m1="+m1+"&m3="+m3);
		return "common/formexcel";
	}*/
	
	
	/**
	 * 显示各个企业的指标分配 的id和名称
	 * @param id
	 * model
	 */
	@RequestMapping(value = "idjson")
	@ResponseBody
	public String getTargetIDAndName(HttpServletRequest request) {
		String tid= request.getParameter("tid");
		String id= request.getParameter("id");
		String deptid= request.getParameter("deptid");
		String m1= request.getParameter("m1");
		Map<String, Object> map = getAuthorityMap();
		if(StringUtils.isNoneBlank(tid))
			map.put("tid", tid);
		if(StringUtils.isNoneBlank(deptid))
			map.put("deptid", deptid);
		if(StringUtils.isNoneBlank(id))
			map.put("id", id);
		if(StringUtils.isNoneBlank(m1))
			map.put("m1", m1);
		return JsonMapper.getInstance().toJson(targetMbfpService.getTargetDisIdJson(map));
	}

}
