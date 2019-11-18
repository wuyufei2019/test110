package com.cczu.model.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
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

import com.cczu.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.model.entity.XZCF_GzsInfoEntity;
import com.cczu.model.entity.XZCF_YbcfLaspEntity;
import com.cczu.model.entity.XZCF_YbcfTzgzEntity;
import com.cczu.model.service.AqzfSetBasicInfoService;
import com.cczu.model.service.AqzfSetNumberService;
import com.cczu.model.service.IMsgService;
import com.cczu.model.service.IPunishSimpleGzService;
import com.cczu.model.service.IXzcfCommonLaspService;
import com.cczu.model.service.IXzcfCommonTzService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

/**
 * 行政处罚-简易处罚-告知controller
 * 
 * @author jason
 * 
 */
@Controller
@RequestMapping("xzcf/ybcf/tzgz")
public class PageXzcfCommonTzController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private IMsgService msgService;
	@Autowired
	private IXzcfCommonTzService xzcfcommontzservice;
	@Autowired
	private IPunishSimpleGzService punishsimplegzservice;
	@Autowired
	private IXzcfCommonLaspService xzcfcommonlaspservice;
	@Autowired
	private AqzfSetNumberService setNumberService;
	@Autowired
	private AqzfSetBasicInfoService aqzfsetbasicinfoservice;
	/**
	 * 默认页面
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		return "aqzf/xzcf/ybcf/tzgz/index";
	}

	/**
	 * list页面
	 */
	@RequiresPermissions("ybcf:tzgz:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("name", request.getParameter("ybcf_tzgz_name"));
		map.put("number", request.getParameter("ybcf_tzgz_number"));
		map.put("startdate", request.getParameter("ybcf_tzgz_startdate"));
		map.put("enddate", request.getParameter("ybcf_tzgz_enddate"));
		map.put("xzqy",UserUtil.getCurrentShiroUser().getXzqy());
		return xzcfcommontzservice.dataGrid(map);
	}
	
	/**
	 * 首页提醒
	 * 
	 * @param request
	 */
	//@RequestMapping(value = "html")
	//@ResponseBody
//	public Map<String, Object>  getHtml(HttpServletRequest request, Model model) {
//		Map<String, Object> map = getPageMap(request);
//		Map<String, Object> remap = new HashMap<>();//返回值
//		ShiroUser user= UserUtil.getCurrentShiroUser();
//		map.put("year", DateUtils.getYear());
//		map.put("month", Integer.parseInt(DateUtils.getMonth()));
//		if(user.getUsertype().equals("1")){//0是企业，1为安监局
//		map.put("id", user.getQyid());
//		}
//		remap.put("rows",aqjgcheckplanservice.dataGrid(map).get("rows") );
//		remap.put("usertype",UserUtil.getCurrentShiroUser().getUsertype());
//		return remap;
//	}
	

	/**
	 * 添加告知信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("ybcf:tzgz:add")
	@RequestMapping(value = "create/{id}")
	public String create(@PathVariable("id") long id,Model model) {
		XZCF_GzsInfoEntity gie=punishsimplegzservice.findInfoByLaId(id);
		model.addAttribute("id1", id);
		model.addAttribute("yte", gie);
		model.addAttribute("action", "createSub");
		return "aqzf/xzcf/ybcf/tzgz/form";
	}

	/**
	 * 添加计划
	 * @param request
	 */
	@RequiresPermissions("ybcf:tzgz:add")
	@RequestMapping(value = "createSub", method = RequestMethod.POST)
	@ResponseBody
	public String createSub(XZCF_YbcfTzgzEntity yte,
			HttpServletRequest request, Model model) throws IOException {
		String datasuccess = "failed";
		Timestamp t = DateUtils.getSysTimestamp();
		yte.setS1(t);
		yte.setS2(t);
		yte.setS3(0);
		
		//设置编号
		AQZF_SetBasicInfoEntity sbe=aqzfsetbasicinfoservice.findInfor();
		XZCF_YbcfLaspEntity ybcf = xzcfcommonlaspservice.findInfoById(yte.getId1());
		String m0 = ybcf.getNumber();
		yte.setNumber("（"+sbe.getSsqjc()+"）安监听告〔"+DateUtils.getYear()+"〕"+m0.substring(m0.indexOf("〕")+1, m0.length()));
		
		if (xzcfcommontzservice.addInfoReturnID(yte) > 0) {
			XZCF_YbcfLaspEntity yle= xzcfcommonlaspservice.findInfoById(yte.getId1());
			yle.setTzflag("1");
			xzcfcommonlaspservice.updateInfo(yle);
			datasuccess = "success";
		}
		return datasuccess;
	}

	/**
	 * 修改计划信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("ybcf:tzgz:update")
	@RequestMapping(value = "update/{id}")
	public String update(@PathVariable("id") long id, Model model) {

		XZCF_YbcfTzgzEntity yte = xzcfcommontzservice.findInfoById(id);
		model.addAttribute("yte", yte);
		//model.addAttribute("jid", id);
		model.addAttribute("action", "updateSub");
		return "aqzf/xzcf/ybcf/tzgz/form";
	}

	/**
	 * 修改告知信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("ybcf:tzgz:update")
	@RequestMapping(value = "updateSub")
	@ResponseBody
	public String updateSub(XZCF_YbcfTzgzEntity yte,
			HttpServletRequest request, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		yte.setS2(t);
		try {
			xzcfcommontzservice.updateInfo(yte);
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="failed";
		}
		return datasuccess;
	}

	/**
	 * 查看告知信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("ybcf:tzgz:view")
	@RequestMapping(value = "view/{id}")
	public String view(@PathVariable("id") long id, Model model) {
		XZCF_YbcfTzgzEntity yte = xzcfcommontzservice.findInfoById(id);
		model.addAttribute("yte", yte);
		return "aqzf/xzcf/ybcf/tzgz/view";
	}

	/**
	 * 删除告知信息
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("ybcf:tzgz:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功！";
		// 可以批量删除
		String[] arrids = ids.split(",");
		try {
			for (int i = 0; i < arrids.length; i++) {
				xzcfcommontzservice.deleteInfo(Long.parseLong(arrids[i]));
				xzcfcommontzservice.updateLaspInfo(Long.parseLong(arrids[i]));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="删除失败";
		}
		return datasuccess;
	}
	
	
	/**
	 * 导出告知书记录word
	 * 
	 */
	@RequiresPermissions("ybcf:tzgz:export")
	@RequestMapping(value = "exporttz/{flag}/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("flag") String  flag,@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		AQZF_SetBasicInfoEntity sbe=aqzfsetbasicinfoservice.findInfor();
		XZCF_YbcfTzgzEntity yte ;
		if("la".equals(flag))
			yte=xzcfcommontzservice.findInfoByLaId(id);
		else
			yte= xzcfcommontzservice.findInfoById(id);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("address", sbe.getAddress()==null?"":sbe.getAddress());
		map.put("ybcode", sbe.getYbcode()==null?"":sbe.getYbcode());
	    map.put("contactname", sbe.getContact()==null?"":sbe.getContact());
		map.put("phone", sbe.getPhone()==null?"":sbe.getPhone());
		map.put("name", yte.getName());
		map.put("comorper",(yte.getName().length()>=4?"你单位":"你"));
		map.put("punishdate",DateUtils.formatDate(yte.getPunishdate(), "yyyy年MM月dd日"));
		map.put("illegalact", yte.getIllegalact()==null?"":yte.getIllegalact());
		map.put("evidence", yte.getEvidence()==null?"":yte.getEvidence());
		map.put("unlaw", yte.getUnlaw()==null?"":yte.getUnlaw());
		map.put("enlaw",yte.getEnlaw()==null?"":yte.getEnlaw());
		map.put("punishresult", yte.getPunishresult()==null?"":yte.getPunishresult());
	    map.put("number", yte.getNumber());
	    
	    map.put("ssqmc", sbe.getSsqmc());

		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "tzgzrecord.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
}
