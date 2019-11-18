package com.cczu.model.zdgl.controller;

import java.sql.Timestamp;
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

import com.alibaba.fastjson.JSON;
import com.cczu.model.zdgl.entity.ZDGL_FLFGEntity;
import com.cczu.model.zdgl.entity.ZDGL_FLFGSBEntity;
import com.cczu.model.zdgl.service.ZdglFlfgService;
import com.cczu.model.zdgl.service.ZdglFlsbService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.Message;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.utils.MessageUtil;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.StringUtils;

/**
 * 制度管理-法律法规识别controller
 */
@Controller
@RequestMapping("zdgl/flsb")
public class PageZdglFlsbController extends BaseController {

	@Autowired
	private ZdglFlsbService zdglFlsbService;
	@Autowired
	private ZdglFlfgService zdglFlfgService;
	@Autowired
	private UserService userService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model,HttpServletRequest request) {
		model.addAttribute("qyid", request.getParameter("qyid"));
		return "zdgl/flfgsb/flsb/index";
	}

	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("m2", request.getParameter("zdgl_flsb_m2"));
		map.put("m1_1", request.getParameter("zdgl_flsb_m1_1"));
		map.put("m1", request.getParameter("zdgl_flsb_m1"));
		String qyid = request.getParameter("qyid");
		if(StringUtils.isEmpty(qyid)){
			map.put("qyid", UserUtil.getCurrentUser().getId2());
		}else{
			map.put("qyid", qyid);
		}
		return zdglFlsbService.dataGrid(map);
	}
	
	/**
	 * 删除法律识别
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			zdglFlsbService.deleteInfo(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("zdgl:flsb:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "zdgl/flfgsb/flsb/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequiresPermissions("zdgl:flsb:add")
	@RequestMapping(value = "create" , method = RequestMethod.POST)
	@ResponseBody
	public String create(Long flfgid,String bfdw,String wjbh,Timestamp fbrq,Timestamp ssrq,ZDGL_FLFGSBEntity flfgsb, Model model) {
		String datasuccess="success";
		//修改法律法规信息
		ZDGL_FLFGEntity flfg = zdglFlfgService.find(flfgid);
		flfg.setM3(bfdw);
		flfg.setM4(wjbh);
		flfg.setM5(fbrq);
		flfg.setM6(ssrq);
		zdglFlfgService.updateInfo(flfg);
		//添加法律识别
		Timestamp t=DateUtils.getSysTimestamp();
		flfgsb.setID1(flfgid);
		flfgsb.setM4(UserUtil.getCurrentUser().getId().toString());
		flfgsb.setM5(UserUtil.getCurrentUser().getDepartmen()==null?"":UserUtil.getCurrentUser().getDepartmen().toString());
		flfgsb.setM6(t);
		
		zdglFlsbService.addInfo(flfgsb);
		
		Map<String,Object>  msgmap = new HashMap<String,Object>();
		msgmap.put(Message.MSGTARGET_H5,"weixin/enterprise/zdgl/zdglmain.jsp");
		msgmap.put(Message.MSGTARGET_PC,"zdgl/flsb/update/sh/"+flfgsb.getID());
		List<User> userlist = userService.findUserByPermission(UserUtil.getCurrentShiroUser().getQyid(), "zdgl:flsb:sh");
		for (User user : userlist) {
			MessageUtil.sendMsg(UserUtil.getCurrentShiroUser().getId()+"", user.getId()+"", "法规识别审核", Message.MessageType.DSH.getMsgType(),JSON.toJSONString(msgmap), "法规识别审核");
		}
		//返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{type}/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, @PathVariable("type") String type, Model model) {
		//法律识别
		ZDGL_FLFGSBEntity flfgsb = zdglFlsbService.find(id);
		model.addAttribute("flsb", flfgsb);				
		ZDGL_FLFGEntity flfg = zdglFlfgService.find(flfgsb.getID1());
		model.addAttribute("flfg", flfg);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("type", type);
		return "zdgl/flfgsb/flsb/form";
	}
	
	/**
	 * 修改信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(String role,String bfdw,String wjbh,Timestamp fbrq,Timestamp ssrq,ZDGL_FLFGSBEntity flfgsb, Model model){
		String datasuccess="success";
		//修改法律法规信息
		ZDGL_FLFGEntity flfg = zdglFlfgService.find(flfgsb.getID1());
		flfg.setM3(bfdw);
		flfg.setM4(wjbh);
		flfg.setM5(fbrq);
		flfg.setM6(ssrq);
		zdglFlfgService.updateInfo(flfg);
		if(!StringUtils.isEmpty(role)){
			if(role.equals("2")){
				//审核
				flfgsb.setM7(UserUtil.getCurrentUser().getId().toString());
				Timestamp t=DateUtils.getSysTimestamp();
				flfgsb.setM9(t);

				Map<String,Object>  msgmap = new HashMap<String,Object>();
				msgmap.put(Message.MSGTARGET_H5,"weixin/enterprise/zdgl/zdglmain.jsp");
				msgmap.put(Message.MSGTARGET_PC,"zdgl/flsb/update/pz/"+flfgsb.getID());
				List<User> userlist = userService.findUserByPermission(UserUtil.getCurrentShiroUser().getQyid(), "zdgl:flsb:sp");
				for (User user : userlist) {
					MessageUtil.sendMsg(UserUtil.getCurrentShiroUser().getId()+"", user.getId()+"", "法规识别批准", Message.MessageType.DSP.getMsgType(),JSON.toJSONString(msgmap), "法规识别批准");
				}
			}else if(role.equals("3")){
				//审批
				flfgsb.setM10(UserUtil.getCurrentUser().getId().toString());
				Timestamp t=DateUtils.getSysTimestamp();
				flfgsb.setM12(t);
			}
		}
		//修改法律识别信息
		zdglFlsbService.updateInfo(flfgsb);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String,Object> flsb = zdglFlsbService.findById(id);		
		model.addAttribute("flsb", flsb);
		Map<String,Object> flfg = zdglFlfgService.findById(Long.parseLong(flsb.get("id1").toString()));		
		model.addAttribute("flfg", flfg);
		return "zdgl/flfgsb/flsb/view";
	}

	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("zdgl:flsb:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("m2", request.getParameter("zdgl_flsb_m2"));
		map.put("m1", request.getParameter("zdgl_flsb_m1"));
		map.put("m1_1", request.getParameter("zdgl_flsb_m1_1"));
		map.put("qyid", UserUtil.getCurrentUser().getId2());
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		zdglFlsbService.exportExcel(response, map);

	}

	/**
	 * 显示所有列
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("zdgl:flsb:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","zdgl/flsb/export");
		return "/common/formexcel";
	}
}
