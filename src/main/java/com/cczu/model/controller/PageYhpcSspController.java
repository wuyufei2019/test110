package com.cczu.model.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.YhpcSspService;
import com.cczu.model.service.YhpcYhpcjlService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;


/**
 * 隐患排查记录Controller
 * @author YZH
 */
@Controller
@RequestMapping("yhpc/ssp")
public class PageYhpcSspController extends BaseController {

	@Autowired
	private YhpcSspService yhpcSspService;
	@Autowired
	private YhpcYhpcjlService yhpcYhpcjlService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type","2");//集团公司
				else
					model.addAttribute("type","1");//子公司
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			model.addAttribute("type","2");//其他
		}
		return "yhpc/ssp/index";
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.putAll(getAuthorityMap());
		map.put("qyname",request.getParameter("yhpc_ssp_qyname"));
		map.put("starttime", request.getParameter("yhpc_ssp_starttime"));//检查开始时间
		map.put("finishtime", request.getParameter("yhpc_ssp_finishtime"));//检查结束时间
		map.put("checkresult", request.getParameter("yhpc_ssp_checkresult"));//隐患处理情况
		map.put("yhzt", request.getParameter("yhzt"));//隐患状态
		map.put("yhdj", request.getParameter("yhdj"));//隐患等级
		return yhpcSspService.dataGrid(map);
	}

	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String,Object> ssp = yhpcSspService.findInforById(id);
		
		model.addAttribute("ssp", ssp);
		//返回页面
		model.addAttribute("action", "view");
		return "yhpc/ssp/view";
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		Map<String,Object> ssp = yhpcSspService.findInforById(id);
		model.addAttribute("ssp", ssp);
		//返回页面
		model.addAttribute("action", "update");
		return "yhpc/ssp/form";
	}
	
	/**
	 * 保存修改信息
	 * @throws ParseException 
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(HttpServletRequest request) throws ParseException {
		String datasuccess = "fail";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dangerlevel", request.getParameter("dangerlevel"));
		map.put("dangerdesc", request.getParameter("dangerdesc"));
		map.put("dangerphoto", request.getParameter("dangerphoto"));
		map.put("id", request.getParameter("id"));
		//将 string 类型的时间转换为 timestamp 类型 
		String createtime = request.getParameter("createtime");
		String sechandletime = request.getParameter("sechandletime");
	    Timestamp t = DateUtils.getTimestampFromStr(createtime);  
	    Timestamp t1 = DateUtils.getTimestampFromStr(sechandletime);  
		map.put("createtime", t);
		map.put("sechandletime", t1);	
		
		
		int result = yhpcSspService.updssp(map);
		if (result > 0) {
			datasuccess = "success";
		}
		return datasuccess;
	}
	
	/**
	 * 删除巡检记录信息
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			yhpcYhpcjlService.deleteById(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
}
